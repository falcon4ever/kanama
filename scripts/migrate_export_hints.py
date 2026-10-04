#!/usr/bin/env python3
"""Migrate Kotlin game scripts from raw `@Export(hint = ..., hintString = ...)` to Kanama's typed
hint annotations (task 133 parcel C).

Kanama 0.5 removes the `hint` / `hintString` parameters of `@Export`: each GDScript `@export_*`
annotation has a typed Kotlin twin that exports the property by itself and builds the hint string
the way GDScript does (so `get_property_list()` matches a GDScript script exactly). This script
rewrites a tree of `.kt` files:

    @Export(hint = PropertyHint.RANGE, hintString = "0,100,1")      -> @ExportRange(0.0, 100.0, 1.0)
    @Export(hint = PropertyHint.RANGE, hintString = "0,1,0.01,or_greater,suffix:m")
                                     -> @ExportRange(0.0, 1.0, 0.01, orGreater = true, suffix = "m")
    @Export(hint = PropertyHint.ENUM, hintString = "Easy,Normal")   -> @ExportEnum("Easy", "Normal")
    @Export(hint = PropertyHint.FLAGS, hintString = "A,B")          -> @ExportFlags("A", "B")
    @Export(hint = PropertyHint.FILE, hintString = "*.png,*.jpg")   -> @ExportFile("*.png", "*.jpg")
    @Export(hint = PropertyHint.DIR)                                -> @ExportDir
    @Export(hint = PropertyHint.GLOBAL_FILE / GLOBAL_DIR ...)       -> @ExportGlobalFile(...) / @ExportGlobalDir
    @Export(hint = PropertyHint.MULTILINE_TEXT)                     -> @ExportMultiline
    @Export(hint = PropertyHint.PLACEHOLDER_TEXT, hintString = "x") -> @ExportPlaceholder("x")
    @Export(hint = PropertyHint.EXP_EASING, hintString = "attenuation")
                                                                    -> @ExportExpEasing(attenuation = true)
    any other hint (a raw number, LINK, ...)                        -> @ExportCustom(<hint>, "<hintString>")

`name` and `usage` stay on `@Export` (`@Export(name = "x") @ExportRange(...)`); a bare `@Export`
left with no arguments next to a hint annotation is dropped (the hint annotation exports). Imports
are fixed (the new annotation added, `PropertyHint` dropped when nothing uses it). It prints each
change and what needs a human (a hint string it cannot parse is kept as `@ExportCustom`). Running
it again on migrated sources changes nothing.

Note the hint strings change spelling, not meaning: `@ExportRange(0.0, 100.0, 1.0)` reports
"0.0,100.0,1.0", exactly what GDScript's `@export_range(0, 100, 1)` reports.

    python3 scripts/migrate_export_hints.py path/to/kotlin-src [more paths...]
    python3 scripts/migrate_export_hints.py --check path/...   # exit 1 if anything would change
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

ANN_PKG = "net.multigesture.kanama.annotations"

SIMPLE = {
    "FILE": "ExportFile",
    "FILE_PATH": "ExportFilePath",
    "GLOBAL_FILE": "ExportGlobalFile",
    "ENUM": "ExportEnum",
    "FLAGS": "ExportFlags",
}
NO_ARGS = {
    "DIR": "ExportDir",
    "GLOBAL_DIR": "ExportGlobalDir",
    "COLOR_NO_ALPHA": "ExportColorNoAlpha",
    "LAYERS_2D_RENDER": "ExportFlags2DRender",
    "LAYERS_2D_PHYSICS": "ExportFlags2DPhysics",
    "LAYERS_2D_NAVIGATION": "ExportFlags2DNavigation",
    "LAYERS_3D_RENDER": "ExportFlags3DRender",
    "LAYERS_3D_PHYSICS": "ExportFlags3DPhysics",
    "LAYERS_3D_NAVIGATION": "ExportFlags3DNavigation",
    "LAYERS_AVOIDANCE": "ExportFlagsAvoidance",
}
HINT_VALUES = {
    "1": "RANGE", "2": "ENUM", "4": "EXP_EASING", "6": "FLAGS", "7": "LAYERS_2D_RENDER",
    "8": "LAYERS_2D_PHYSICS", "9": "LAYERS_2D_NAVIGATION", "10": "LAYERS_3D_RENDER",
    "11": "LAYERS_3D_PHYSICS", "12": "LAYERS_3D_NAVIGATION", "13": "FILE", "14": "DIR",
    "15": "GLOBAL_FILE", "16": "GLOBAL_DIR", "18": "MULTILINE_TEXT", "20": "PLACEHOLDER_TEXT", "21": "COLOR_NO_ALPHA",
    "37": "LAYERS_AVOIDANCE", "44": "FILE_PATH",
}
RANGE_FLAGS = {
    "or_greater": "orGreater",
    "or_less": "orLess",
    "exp": "exp",
    "radians_as_degrees": "radiansAsDegrees",
    "radians": "radiansAsDegrees",
    "degrees": "degrees",
    "prefer_slider": "preferSlider",
    "hide_control": "hideControl",
    "hide_slider": "hideControl",
}
NUMBER = re.compile(r"^[-+]?(?:\d+\.\d*|\.\d+|\d+)(?:[eE][-+]?\d+)?$")
EXPORT_START = re.compile(r"@Export\(")


def split_args(body: str) -> list[str]:
    """Top-level comma split of an argument list (strings and parentheses respected)."""
    parts, depth, cur, in_str, esc = [], 0, [], False, False
    for ch in body:
        if in_str:
            cur.append(ch)
            if esc:
                esc = False
            elif ch == "\\":
                esc = True
            elif ch == '"':
                in_str = False
            continue
        if ch == '"':
            in_str = True
        elif ch in "([{":
            depth += 1
        elif ch in ")]}":
            depth -= 1
        elif ch == "," and depth == 0:
            parts.append("".join(cur).strip())
            cur = []
            continue
        cur.append(ch)
    tail = "".join(cur).strip()
    if tail:
        parts.append(tail)
    return parts


def matching_paren(text: str, open_index: int) -> int:
    depth, in_str, esc = 0, False, False
    for i in range(open_index, len(text)):
        ch = text[i]
        if in_str:
            if esc:
                esc = False
            elif ch == "\\":
                esc = True
            elif ch == '"':
                in_str = False
            continue
        if ch == '"':
            in_str = True
        elif ch == "(":
            depth += 1
        elif ch == ")":
            depth -= 1
            if depth == 0:
                return i
    return -1


def unquote(literal: str) -> str | None:
    literal = literal.strip()
    if len(literal) >= 2 and literal[0] == '"' and literal[-1] == '"':
        return literal[1:-1].replace('\\"', '"').replace("\\\\", "\\")
    return None


def quote(value: str) -> str:
    return '"' + value.replace("\\", "\\\\").replace('"', '\\"').replace("$", "\\$") + '"'


def double_literal(text: str) -> str:
    text = text.strip()
    if re.fullmatch(r"[-+]?\d+", text):
        return text.lstrip("+") + ".0"
    if text.endswith("."):
        return text + "0"
    if text.startswith("."):
        return "0" + text
    return text.lstrip("+")


def hint_name(expr: str) -> str | None:
    expr = expr.strip()
    m = re.fullmatch(r"(?:[\w.]*\.)?PropertyHint\.(\w+)", expr)
    if m:
        return m.group(1)
    if re.fullmatch(r"\d+", expr):
        return HINT_VALUES.get(expr)
    return None


def typed_annotation(hint_expr: str, hint_string: str | None, raw_hint_string: str | None):
    """(annotation text, simple name, note) for one hint, or ExportCustom when not typed."""
    name = hint_name(hint_expr)
    hs = hint_string if hint_string is not None else ""
    parts = [p.strip() for p in hs.split(",")] if hs else []
    if name == "RANGE":
        numbers = []
        for p in parts:
            if NUMBER.match(p) and len(numbers) < 3:
                numbers.append(p)
            else:
                break
        rest = parts[len(numbers):]
        args = [double_literal(n) for n in numbers]
        ok = len(numbers) >= 2
        for extra in rest:
            if extra in RANGE_FLAGS:
                args.append(f"{RANGE_FLAGS[extra]} = true")
            elif extra.startswith("suffix:"):
                args.append(f"suffix = {quote(extra[len('suffix:'):])}")
            else:
                ok = False
        if ok:
            return f"@ExportRange({', '.join(args)})", "ExportRange", None
    elif name in SIMPLE and (parts or name not in ("ENUM", "FLAGS")):
        ann = SIMPLE[name]
        text = f"@{ann}({', '.join(quote(p) for p in parts)})" if parts else f"@{ann}"
        return text, ann, None
    elif name in NO_ARGS and not parts:
        return f"@{NO_ARGS[name]}", NO_ARGS[name], None
    elif name == "MULTILINE_TEXT" and all(p in ("monospace", "no_wrap") for p in parts):
        args = [("monospace = true" if p == "monospace" else "noWrap = true") for p in parts]
        return (f"@ExportMultiline({', '.join(args)})" if args else "@ExportMultiline"), "ExportMultiline", None
    elif name == "PLACEHOLDER_TEXT":
        return f"@ExportPlaceholder({quote(hs)})", "ExportPlaceholder", None
    elif name == "EXP_EASING" and all(p in ("attenuation", "positive_only") for p in parts):
        args = [("attenuation = true" if p == "attenuation" else "positiveOnly = true") for p in parts]
        return (f"@ExportExpEasing({', '.join(args)})" if args else "@ExportExpEasing"), "ExportExpEasing", None
    hs_arg = raw_hint_string if raw_hint_string is not None else '""'
    note = f"kept as @ExportCustom({hint_expr.strip()}, ...): no typed annotation matches"
    return f"@ExportCustom({hint_expr.strip()}, {hs_arg})", "ExportCustom", note


def migrate_text(text: str, path: str, notes: list[str]) -> tuple[str, list[str], set[str]]:
    changes: list[str] = []
    added: set[str] = set()
    out = []
    pos = 0
    for m in EXPORT_START.finditer(text):
        start = m.start()
        if start < pos:
            continue
        open_i = m.end() - 1
        close_i = matching_paren(text, open_i)
        if close_i < 0:
            continue
        args = split_args(text[open_i + 1:close_i])
        named: dict[str, str] = {}
        order = ["name", "hint", "hintString", "usage"]
        for i, arg in enumerate(args):
            km = re.match(r"^(\w+)\s*=\s*(.*)$", arg, re.S)
            if km and km.group(1) in order:
                named[km.group(1)] = km.group(2).strip()
            elif i < len(order):
                named[order[i]] = arg
        if "hint" not in named and "hintString" not in named:
            continue
        hint_expr = named.get("hint", "PropertyHint.NONE")
        raw_hs = named.get("hintString")
        hs = unquote(raw_hs) if raw_hs is not None else None
        if raw_hs is not None and hs is None:
            ann_text, simple, note = (f"@ExportCustom({hint_expr}, {raw_hs})", "ExportCustom",
                                      "hintString is not a plain string literal")
        elif hint_name(hint_expr) == "NONE" or hint_expr.strip() == "0":
            ann_text, simple, note = (None, None, None)
        else:
            ann_text, simple, note = typed_annotation(hint_expr, hs, raw_hs)
        keep = [f"{k} = {named[k]}" for k in ("name", "usage") if k in named]
        export_text = f"@Export({', '.join(keep)})" if keep else ("@Export" if ann_text is None else "")
        replacement = " ".join(t for t in (export_text, ann_text) if t)
        original = text[start:close_i + 1]
        out.append(text[pos:start])
        out.append(replacement)
        pos = close_i + 1
        line = text.count("\n", 0, start) + 1
        changes.append(f"{path}:{line}: {' '.join(original.split())} -> {replacement}")
        if simple:
            added.add(simple)
        if note:
            notes.append(f"{path}:{line}: {note}")
    out.append(text[pos:])
    return "".join(out), changes, added


def fix_imports(text: str, added: set[str]) -> str:
    lines = text.split("\n")
    existing = {l.strip() for l in lines if l.startswith("import ")}
    new_imports = [f"import {ANN_PKG}.{a}" for a in sorted(added) if f"import {ANN_PKG}.{a}" not in existing]
    if new_imports:
        idx = max((i for i, l in enumerate(lines) if l.startswith("import ")), default=-1)
        if idx < 0:
            pkg = next((i for i, l in enumerate(lines) if l.startswith("package ")), -1)
            lines[pkg + 1:pkg + 1] = [""] + new_imports
        else:
            lines[idx + 1:idx + 1] = new_imports
    body = "\n".join(l for l in lines if not l.startswith("import "))
    result = []
    for l in lines:
        m = re.match(rf"^import {re.escape(ANN_PKG)}\.(\w+)$", l.strip())
        if m and m.group(1) in ("PropertyHint", "Export") and not re.search(rf"\b{m.group(1)}\b", body):
            continue
        result.append(l)
    # Re-sort the import block (ktfmt order: lexicographic), so an added import lands in place.
    idx = [i for i, l in enumerate(result) if l.startswith("import ")]
    if idx and all(result[i].startswith("import ") for i in range(idx[0], idx[-1] + 1)):
        result[idx[0]:idx[-1] + 1] = sorted(dict.fromkeys(result[idx[0]:idx[-1] + 1]))
    return "\n".join(result)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("paths", nargs="+")
    parser.add_argument("--check", action="store_true", help="exit 1 if anything would change")
    args = parser.parse_args()
    files: list[Path] = []
    for p in args.paths:
        path = Path(p)
        if path.is_file() and path.suffix == ".kt":
            files.append(path)
        elif path.is_dir():
            files.extend(sorted(q for q in path.rglob("*.kt") if "/build/" not in str(q) and "/.godot/" not in str(q)))
    notes: list[str] = []
    changed = 0
    for f in files:
        text = f.read_text(encoding="utf-8")
        new, changes, added = migrate_text(text, str(f), notes)
        if not changes:
            continue
        new = fix_imports(new, added)
        changed += 1
        for c in changes:
            print(c)
        if not args.check:
            f.write_text(new, encoding="utf-8")
    for n in notes:
        print(f"NEEDS A LOOK: {n}")
    print(f"[migrate_export_hints] {changed} file(s) {'would change' if args.check else 'changed'}")
    return 1 if (args.check and changed) else 0


if __name__ == "__main__":
    sys.exit(main())
