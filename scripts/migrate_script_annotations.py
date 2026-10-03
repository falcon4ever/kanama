#!/usr/bin/env python3
"""Migrate Kotlin game scripts to Kanama's one annotation set (task 133 parcel B).

Kanama 0.5 keeps one GDScript-shaped name per concept and removes the aliases outright (no
deprecation release), registers every public function of a script class with Godot (so
`@RegisterFunction` / `@Method` are gone), and hands input handlers a typed `InputEvent`. This
script rewrites a tree of `.kt` files to match:

  * renames the removed annotations (and their imports) to the canonical ones:

        @Ready -> @OnReady            @EnterTree -> @OnEnterTree      @ExitTree -> @OnExitTree
        @Process -> @OnProcess        @PhysicsProcess -> @OnPhysicsProcess
        @Input -> @OnInput            @UnhandledInput -> @OnUnhandledInput
        @ShortcutInput -> @OnShortcutInput   @UnhandledKeyInput -> @OnUnhandledKeyInput
        @ScriptProperty -> @Export    @RegisterProperty -> @Export
        @ClassName -> @GlobalClass    @ToolButton -> @ExportToolButton

  * deletes `@RegisterFunction` / `@Method`: a public function is registered automatically under
    its snake_case name. Where the annotation named a different Godot name (a `.tscn` connection
    to `_on_start_button_pressed`, say), it becomes `@GodotName("...")`;
  * retypes the parameter of every `@OnInput` / `@OnUnhandledInput` / `@OnShortcutInput` /
    `@OnUnhandledKeyInput` handler from `GodotObject` to `InputEvent`, and replaces the
    `InputEvent(<param>.handle)` rewraps inside it with the parameter itself;
  * fixes the imports it touched (adds `GodotName` / `InputEvent`, drops imports nothing uses).

It prints every change per file, then what needs a human: a removed annotation it could not
place, an input handler whose parameter is neither `GodotObject` nor `InputEvent`, and each
public function that was NOT registered before and now is (the compiler rejects one whose
parameter or return type Godot cannot carry; mark it `internal` or `private` to keep it
Kotlin-only).

    python3 scripts/migrate_script_annotations.py path/to/kotlin-src [more paths...]
    python3 scripts/migrate_script_annotations.py --check path/...   # exit 1 if anything would change
"""

from __future__ import annotations

import argparse
import re
import sys
from dataclasses import dataclass, field
from pathlib import Path

ANN_PKG = "net.multigesture.kanama.annotations"
API_PKG = "net.multigesture.kanama.api"

RENAMES = {
    "Ready": "OnReady",
    "EnterTree": "OnEnterTree",
    "ExitTree": "OnExitTree",
    "Process": "OnProcess",
    "PhysicsProcess": "OnPhysicsProcess",
    "Input": "OnInput",
    "UnhandledInput": "OnUnhandledInput",
    "ShortcutInput": "OnShortcutInput",
    "UnhandledKeyInput": "OnUnhandledKeyInput",
    "ScriptProperty": "Export",
    "RegisterProperty": "Export",
    "ClassName": "GlobalClass",
    "ToolButton": "ExportToolButton",
}
REGISTER = ("RegisterFunction", "Method")
INPUT_HANDLERS = ("OnInput", "OnUnhandledInput", "OnShortcutInput", "OnUnhandledKeyInput")
# Annotations that wire a function some other way: such a function is not auto-registered.
FUNCTION_ROLES = {
    "OnReady",
    "OnEnterTree",
    "OnExitTree",
    "OnProcess",
    "OnPhysicsProcess",
    *INPUT_HANDLERS,
    "OverrideVirtual",
    "Signal",
    "ExportToolButton",
}
KOTLIN_MODIFIERS = {
    "public",
    "internal",
    "private",
    "protected",
    "open",
    "override",
    "final",
    "abstract",
    "suspend",
    "inline",
    "operator",
    "infix",
    "tailrec",
    "external",
    "actual",
    "expect",
}

IDENT = r"[A-Za-z_][A-Za-z0-9_]*"
ANNOTATION_RE = re.compile(
    r"@(?:" + re.escape(ANN_PKG) + r"\.)?(" + IDENT + r")\b(\s*\((?:[^()\"]|\"(?:\\.|[^\"\\])*\")*\))?"
)


def camel_to_snake(name: str) -> str:
    """The processor's Godot name for a Kotlin function (KanamaProcessor.camelToSnake)."""
    out = []
    for i, ch in enumerate(name):
        if i > 0 and ch.isupper():
            out.append("_")
        out.append(ch.lower())
    return "".join(out)


@dataclass
class FileReport:
    path: Path
    changes: list[str] = field(default_factory=list)
    human: list[str] = field(default_factory=list)
    counts: dict[str, int] = field(default_factory=dict)

    def count(self, key: str, n: int = 1) -> None:
        self.counts[key] = self.counts.get(key, 0) + n


def line_of(text: str, index: int) -> int:
    return text.count("\n", 0, index) + 1


def code_mask(text: str) -> list[bool]:
    """True for every character outside comments and string literals."""
    mask = [True] * len(text)
    i = 0
    n = len(text)
    while i < n:
        if text.startswith("//", i):
            j = text.find("\n", i)
            j = n if j < 0 else j
            for k in range(i, j):
                mask[k] = False
            i = j
        elif text.startswith("/*", i):
            j = text.find("*/", i + 2)
            j = n if j < 0 else j + 2
            for k in range(i, j):
                mask[k] = False
            i = j
        elif text.startswith('"""', i):
            j = text.find('"""', i + 3)
            j = n if j < 0 else j + 3
            for k in range(i, j):
                mask[k] = False
            i = j
        elif text[i] == '"':
            j = i + 1
            while j < n and text[j] != '"':
                j += 2 if text[j] == "\\" else 1
            j = min(n, j + 1)
            for k in range(i, j):
                mask[k] = False
            i = j
        elif text[i] == "'" and i + 2 < n:
            j = i + 1
            while j < n and text[j] != "'":
                j += 2 if text[j] == "\\" else 1
            j = min(n, j + 1)
            for k in range(i, j):
                mask[k] = False
            i = j
        else:
            i += 1
    return mask


def annotation_name_value(args: str | None) -> str | None:
    """The `name` of `@RegisterFunction("x")` / `@RegisterFunction(name = "x")`, else None."""
    if not args:
        return None
    m = re.search(r"\(\s*(?:name\s*=\s*)?\"((?:\\.|[^\"\\])*)\"\s*\)", args)
    return m.group(1) if m else None


@dataclass
class Decl:
    """A function declaration with the annotations directly above or before it."""

    start: int  # start of the first annotation
    fun_index: int  # index of the `fun` keyword
    name: str
    modifiers: list[str]
    annotations: list[tuple[str, str | None, int, int]]  # (name, args, start, end)


def parse_decl_after(text: str, mask: list[bool], pos: int) -> tuple[list, list[str], int, str] | None:
    """From [pos], skip annotations/modifiers/whitespace; return (annotations, modifiers, fun index, name)."""
    anns = []
    mods = []
    i = pos
    n = len(text)
    while i < n:
        while i < n and text[i] in " \t\r\n":
            i += 1
        if i < n and text[i] == "@" and mask[i]:
            m = ANNOTATION_RE.match(text, i)
            if not m:
                return None
            anns.append((m.group(1), m.group(2), m.start(), m.end()))
            i = m.end()
            continue
        m = re.match(IDENT, text[i:])
        if not m:
            return None
        word = m.group(0)
        if word in KOTLIN_MODIFIERS:
            mods.append(word)
            i += len(word)
            continue
        if word == "fun":
            j = i + 3
            while j < n and text[j] in " \t":
                j += 1
            # extension receivers / type parameters: `fun <T> Foo.bar(`
            if j < n and text[j] == "<":
                depth = 0
                while j < n:
                    if text[j] == "<":
                        depth += 1
                    elif text[j] == ">":
                        depth -= 1
                        if depth == 0:
                            j += 1
                            break
                    j += 1
                while j < n and text[j] in " \t":
                    j += 1
            nm = re.match(r"`([^`]+)`|(" + IDENT + r"(?:\." + IDENT + r")*)", text[j:])
            if not nm:
                return None
            name = nm.group(1) or nm.group(2)
            return anns, mods, i, name
        return None
    return None


def matching_paren(text: str, mask: list[bool], open_index: int) -> int:
    depth = 0
    for k in range(open_index, len(text)):
        if not mask[k]:
            continue
        if text[k] in "([{":
            depth += 1
        elif text[k] in ")]}":
            depth -= 1
            if depth == 0:
                return k
    return -1


def function_body_span(text: str, mask: list[bool], fun_index: int) -> tuple[int, int, int, int] | None:
    """(params_open, params_close, body_start, body_end) of the function at [fun_index]."""
    p_open = text.find("(", fun_index)
    if p_open < 0:
        return None
    p_close = matching_paren(text, mask, p_open)
    if p_close < 0:
        return None
    k = p_close + 1
    n = len(text)
    while k < n and (text[k] != "{" or not mask[k]) and text[k] != "=":
        if text[k] == "\n" and text[k + 1 : k + 2].strip() and text[k + 1 : k + 2] == "@":
            break
        k += 1
    if k >= n:
        return None
    if text[k] == "=":
        # expression body: up to the end of the line
        end = text.find("\n", k)
        return p_open, p_close, k, n if end < 0 else end
    b_close = matching_paren(text, mask, k)
    if b_close < 0:
        return None
    return p_open, p_close, k, b_close + 1


def class_spans(text: str, mask: list[bool]) -> list[tuple[int, int, bool]]:
    """(body start, body end, is a Kanama script/registered class) for every class body."""
    spans = []
    for m in re.finditer(r"\bclass\s+(" + IDENT + r")", text):
        if not mask[m.start()]:
            continue
        before = text[max(0, m.start() - 400) : m.start()]
        is_script = bool(re.search(r"@(?:" + re.escape(ANN_PKG) + r"\.)?(ScriptClass|RegisterClass)\b", before))
        # the class body: the first '{' at depth 0 after the header's parentheses
        k = m.end()
        n = len(text)
        depth = 0
        while k < n:
            if mask[k]:
                c = text[k]
                if c in "(<":
                    depth += 1
                elif c in ")>":
                    depth -= 1
                elif c == "{" and depth <= 0:
                    break
                elif c == "\n" and depth <= 0:
                    nxt = text[k + 1 :].lstrip()
                    if nxt.startswith(("@", "class ", "fun ", "val ", "var ", "object ", "}", "private ", "internal ")) and not nxt.startswith(("{",)):
                        k = -1
                        break
            k += 1
        if k < 0 or k >= n:
            continue
        end = matching_paren(text, mask, k)
        if end > 0:
            spans.append((k, end, is_script))
    return spans


def brace_blocks(text: str, mask: list[bool]) -> list[tuple[int, int]]:
    """(open, close) of every `{ ... }` block in code."""
    blocks = []
    stack = []
    for i, c in enumerate(text):
        if not mask[i]:
            continue
        if c == "{":
            stack.append(i)
        elif c == "}" and stack:
            blocks.append((stack.pop(), i))
    return blocks


def depth_one_member(spans, blocks, index: int) -> bool:
    """True when [index] sits directly in a script class body (not in a nested block)."""
    owners = [b for b in blocks if b[0] < index < b[1]]
    if not owners:
        return False
    innermost = max(owners, key=lambda b: b[0])
    return any(s[0] == innermost[0] and s[2] for s in spans)


def migrate_text(text: str, report: FileReport) -> str:
    # ---- 1. renames ---------------------------------------------------------------------
    mask = code_mask(text)
    edits: list[tuple[int, int, str]] = []
    for m in ANNOTATION_RE.finditer(text):
        if not mask[m.start()]:
            continue
        name = m.group(1)
        if name in RENAMES:
            # `@Input` might be a user's own annotation; only rename when the import or the
            # qualified form says it is Kanama's (or when nothing else named it is imported).
            if not is_kanama_annotation(text, name, m.group(0)):
                continue
            new = RENAMES[name]
            head = m.group(0)[: m.end(1) - m.start()]
            new_head = head[: -len(name)] + new
            edits.append((m.start(), m.start() + len(head), new_head))
            report.changes.append(f"  line {line_of(text, m.start())}: @{name} -> @{new}")
            report.count(f"@{name} -> @{new}")
    text = apply_edits(text, edits)
    for old, new in RENAMES.items():
        text, n = re.subn(
            r"^(\s*import\s+)" + re.escape(ANN_PKG) + r"\." + old + r"\s*$",
            r"\g<1>" + ANN_PKG + "." + new,
            text,
            flags=re.M,
        )

    # ---- 2. @RegisterFunction / @Method ---------------------------------------------------
    mask = code_mask(text)
    spans = class_spans(text, mask)
    edits = []
    seen_funs: set[str] = set()  # names of the functions that carried @RegisterFunction
    for m in ANNOTATION_RE.finditer(text):
        if not mask[m.start()] or m.group(1) not in REGISTER:
            continue
        if not is_kanama_annotation(text, m.group(1), m.group(0)):
            continue
        decl = parse_decl_after(text, mask, m.start())
        line = line_of(text, m.start())
        if decl is None:
            report.human.append(f"  line {line}: @{m.group(1)} not followed by a function; remove it by hand")
            continue
        anns, mods, fun_index, fname = decl
        seen_funs.add(fname)
        godot = annotation_name_value(m.group(2))
        # delete the annotation plus the whitespace after it (or the whole line when alone)
        start, end = m.start(), m.end()
        line_start = text.rfind("\n", 0, start) + 1
        line_end = text.find("\n", end)
        line_end = len(text) if line_end < 0 else line_end
        alone = text[line_start:start].strip() == "" and text[end:line_end].strip() == ""
        non_public = any(x in mods for x in ("private", "internal", "protected"))
        if godot is not None and godot != camel_to_snake(fname) and not non_public:
            edits.append((start, end, f'@GodotName("{godot}")'))
            report.changes.append(f'  line {line}: @{m.group(1)}("{godot}") on {fname} -> @GodotName("{godot}")')
            report.count("@RegisterFunction -> @GodotName")
            continue
        if alone:
            edits.append((line_start, line_end + 1 if line_end < len(text) else line_end, ""))
        else:
            k = end
            while k < len(text) and text[k] in " \t":
                k += 1
            edits.append((start, k, ""))
        if non_public:
            report.changes.append(f"  line {line}: @{m.group(1)} on non-public {fname} removed (it was never registered)")
            report.human.append(
                f"  line {line}: {fname} is {'/'.join(x for x in mods if x in ('private','internal','protected'))} and had "
                f"@{m.group(1)}: Kanama never registered a non-public function; make it public if Godot calls it"
            )
            report.count("@RegisterFunction removed (non-public)")
        else:
            report.changes.append(f"  line {line}: @{m.group(1)}{m.group(2) or ''} on {fname} removed (registered automatically)")
            report.count("@RegisterFunction removed")
    text = apply_edits(text, edits)

    # ---- 3. typed input handlers ----------------------------------------------------------
    mask = code_mask(text)
    edits = []
    handled: set[int] = set()
    for m in ANNOTATION_RE.finditer(text):
        if not mask[m.start()] or m.group(1) not in INPUT_HANDLERS:
            continue
        decl = parse_decl_after(text, mask, m.start())
        if decl is None:
            continue
        anns, mods, fun_index, fname = decl
        if fun_index in handled:
            continue
        handled.add(fun_index)
        span = function_body_span(text, mask, fun_index)
        if span is None:
            report.human.append(f"  line {line_of(text, fun_index)}: could not read handler {fname}; check it by hand")
            continue
        p_open, p_close, b_start, b_end = span
        params = text[p_open + 1 : p_close]
        pm = re.fullmatch(r"\s*(" + IDENT + r")\s*:\s*([A-Za-z0-9_.]+)(\??)\s*,?\s*", params)
        if not pm:
            report.human.append(
                f"  line {line_of(text, fun_index)}: input handler {fname}({params.strip()}) — expected one parameter; "
                "declare it `(event: InputEvent)`"
            )
            continue
        pname, ptype, pnull = pm.group(1), pm.group(2), pm.group(3)
        short = ptype.rsplit(".", 1)[-1]
        if short == "InputEvent":
            continue
        if short != "GodotObject":
            report.human.append(
                f"  line {line_of(text, fun_index)}: input handler {fname}({pname}: {ptype}) — Godot passes an "
                "InputEvent; declare `(event: InputEvent)` and cast inside"
            )
            continue
        type_start = p_open + 1 + pm.start(2)
        edits.append((type_start, type_start + len(ptype) + len(pnull), "InputEvent"))
        report.changes.append(f"  line {line_of(text, fun_index)}: {fname}({pname}: {ptype}) -> ({pname}: InputEvent)")
        report.count("input handler retyped")
        body = text[b_start:b_end]
        rewrap = re.compile(r"(?:" + re.escape(API_PKG) + r"\.)?InputEvent\(\s*" + re.escape(pname) + r"\.handle\s*\)")
        for rm in rewrap.finditer(body):
            at = b_start + rm.start()
            if not mask[at]:
                continue
            edits.append((at, b_start + rm.end(), pname))
            report.changes.append(f"  line {line_of(text, at)}: {rm.group(0)} -> {pname}")
            report.count("InputEvent rewrap removed")
    text = apply_edits(text, edits)

    # ---- 4. newly registered public functions (information for a human) -------------------
    mask = code_mask(text)
    spans = class_spans(text, mask)
    blocks = brace_blocks(text, mask)
    for m in re.finditer(r"\bfun\b", text):
        if not mask[m.start()] or not depth_one_member(spans, blocks, m.start()):
            continue
        # walk back over modifiers and annotations to the start of the declaration
        head_start = declaration_start(text, mask, m.start())
        decl = parse_decl_after(text, mask, head_start)
        if decl is None:
            continue
        anns, mods, fun_index, fname = decl
        if fun_index != m.start() or fname in seen_funs:
            continue
        if any(x in mods for x in ("private", "internal", "protected")):
            continue
        if "override" in mods:
            continue
        names = {a[0] for a in anns}
        if names & FUNCTION_ROLES or "GodotName" in names or "." in fname:
            continue
        report.human.append(
            f"  line {line_of(text, fun_index)}: public {fname} is now registered as "
            f"'{camel_to_snake(fname)}' (mark it internal/private if Godot never calls it)"
        )
        report.count("newly registered public function")

    # ---- 5. imports (not in import-less snippets) -------------------------------------------
    if not ASSUME_KANAMA or re.search(r"^\s*import\s+", text, re.M):
        text = fix_imports(text, report)
    return text


def declaration_start(text: str, mask: list[bool], fun_index: int) -> int:
    """Start of the line block (annotations + modifiers) that ends at the `fun` at [fun_index]."""
    start = text.rfind("\n", 0, fun_index) + 1
    while start > 0:
        prev_start = text.rfind("\n", 0, start - 1) + 1
        prev = text[prev_start : start - 1].strip()
        if prev.startswith("@") and not prev.startswith("@file"):
            start = prev_start
            continue
        break
    return start


# Set for snippets without imports (documentation code blocks): every name is Kanama's.
ASSUME_KANAMA = False


def is_kanama_annotation(text: str, name: str, token: str) -> bool:
    if f"{ANN_PKG}." in token or ASSUME_KANAMA:
        return True
    if re.search(r"^\s*import\s+" + re.escape(ANN_PKG) + r"\.(" + re.escape(name) + r"|\*)\s*$", text, re.M):
        return True
    # imported from somewhere else under that name: not ours
    if re.search(r"^\s*import\s+[\w.]+\." + re.escape(name) + r"\s*$", text, re.M):
        return False
    # same package as the annotations (test fixtures) or an unqualified use without import
    return bool(re.search(r"^\s*package\s+" + re.escape(ANN_PKG) + r"\s*$", text, re.M))


def apply_edits(text: str, edits: list[tuple[int, int, str]]) -> str:
    for start, end, new in sorted(edits, key=lambda e: e[0], reverse=True):
        text = text[:start] + new + text[end:]
    return text


def fix_imports(text: str, report: FileReport) -> str:
    lines = text.split("\n")
    # de-duplicate identical imports (two renamed aliases can land on the same name)
    seen = set()
    out = []
    for line in lines:
        s = line.strip()
        if s.startswith("import "):
            if s in seen:
                continue
            seen.add(s)
        out.append(line)
    lines = out
    text = "\n".join(lines)
    mask = code_mask(text)
    body_code = strip_imports_for_scan(text, mask)

    def used(simple: str) -> bool:
        return re.search(r"(?<![\w.])" + re.escape(simple) + r"\b", body_code) is not None

    # drop imports of removed annotations and of names nothing uses any more
    candidates = [f"{ANN_PKG}.{n}" for n in (*REGISTER, *RENAMES.keys(), *RENAMES.values(), "GodotName")]
    candidates.append(f"{API_PKG}.GodotObject")
    new_lines = []
    for line in lines:
        m = re.match(r"^\s*import\s+([\w.]+)\s*$", line)
        if m and m.group(1) in candidates:
            simple = m.group(1).rsplit(".", 1)[-1]
            if m.group(1).rsplit(".", 1)[-1] in REGISTER and m.group(1).startswith(ANN_PKG):
                report.count("import removed")
                continue
            if not used(simple):
                report.count("import removed")
                continue
        new_lines.append(line)
    lines = new_lines
    text = "\n".join(lines)

    def has_import(fq: str) -> bool:
        return re.search(r"^\s*import\s+" + re.escape(fq) + r"\s*$", text, re.M) is not None

    def wildcard(pkg: str) -> bool:
        return re.search(r"^\s*import\s+" + re.escape(pkg) + r"\.\*\s*$", text, re.M) is not None

    mask = code_mask(text)
    body_code = strip_imports_for_scan(text, mask)
    pkg = re.search(r"^\s*package\s+([\w.]+)", text, re.M)
    pkg_name = pkg.group(1) if pkg else ""
    wanted = []
    if re.search(r"@GodotName\b", body_code) and not has_import(f"{ANN_PKG}.GodotName") and not wildcard(ANN_PKG) and pkg_name != ANN_PKG:
        wanted.append(f"{ANN_PKG}.GodotName")
    for new in set(RENAMES.values()):
        if re.search(r"@" + new + r"\b", body_code) and not has_import(f"{ANN_PKG}.{new}") and not wildcard(ANN_PKG) and pkg_name != ANN_PKG:
            wanted.append(f"{ANN_PKG}.{new}")
    if (
        re.search(r"(?<![\w.])InputEvent\b", body_code)
        and not has_import(f"{API_PKG}.InputEvent")
        and not wildcard(API_PKG)
        and pkg_name != API_PKG
        and not re.search(r"^\s*import\s+[\w.]+\.InputEvent\s*$", text, re.M)
    ):
        wanted.append(f"{API_PKG}.InputEvent")
    if wanted:
        text = add_imports(text, wanted)
        for w in wanted:
            report.changes.append(f"  import {w} added")
    return text


def strip_imports_for_scan(text: str, mask: list[bool]) -> str:
    chars = [c if mask[i] else " " for i, c in enumerate(text)]
    code = "".join(chars)
    return re.sub(r"^\s*(import|package)\s+[\w.*]+\s*$", "", code, flags=re.M)


def add_imports(text: str, fqs: list[str]) -> str:
    """Insert each import in alphabetical position among the imports of its package family."""
    lines = text.split("\n")
    for fq in sorted(set(fqs)):
        import_idx = [i for i, l in enumerate(lines) if re.match(r"^\s*import\s+", l)]
        new = f"import {fq}"
        if not import_idx:
            pkg = next((i for i, l in enumerate(lines) if l.startswith("package ")), None)
            if pkg is None:
                lines = [new, ""] + lines
            else:
                lines = lines[: pkg + 1] + ["", new] + lines[pkg + 1 :]
            continue
        family = ".".join(fq.split(".")[:3])
        same = [i for i in import_idx if lines[i].strip()[len("import ") :].startswith(family + ".")]
        pool = same or import_idx
        before = [i for i in pool if lines[i].strip() < new]
        at = (max(before) + 1) if before else min(pool)
        lines.insert(at, new)
    return "\n".join(lines)


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("paths", nargs="+", type=Path, help="files or directories to migrate (recursively, *.kt)")
    parser.add_argument("--check", action="store_true", help="change nothing; exit 1 if a file would change")
    args = parser.parse_args(argv)

    files: list[Path] = []
    for p in args.paths:
        if p.is_dir():
            files.extend(sorted(f for f in p.rglob("*.kt") if "build" not in f.relative_to(p).parts))
        elif p.suffix == ".kt":
            files.append(p)
    totals: dict[str, int] = {}
    changed = 0
    humans = 0
    for f in files:
        original = f.read_text(encoding="utf-8")
        report = FileReport(f)
        migrated = migrate_text(original, report)
        if migrated != original:
            changed += 1
            if not args.check:
                f.write_text(migrated, encoding="utf-8")
        if report.changes or report.human:
            print(f"{f}:")
            for c in report.changes:
                print(c)
            for h in report.human:
                print("  NEEDS A HUMAN:" + h[1:])
                humans += 1
        for k, v in report.counts.items():
            totals[k] = totals.get(k, 0) + v
    print()
    print(f"{len(files)} file(s) scanned, {changed} {'would change' if args.check else 'changed'}")
    for k in sorted(totals):
        print(f"  {k}: {totals[k]}")
    print(f"  items for a human: {humans}")
    if args.check and changed:
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
