#!/usr/bin/env python3
"""Migrate Kotlin sources from the pre-0.5 enum constants to the typed Godot enums (task 128 A).

Until task 128 every Godot enum value was a `const val NAME: Long` on its owner's companion
(`Node.PROCESS_MODE_ALWAYS`, `Tween.TRANS_SINE`), plus a few hand-written subsets on other classes
(`InputEventKey.KEY_W`, `InputEventMouseButton.MOUSE_BUTTON_LEFT`, `PhysicsBody3D.BODY_AXIS_LINEAR_X`,
`GodotObject.CONNECT_DEFAULT`). Since task 128 each enum is a value class nested in its owner
(`Node.ProcessMode.ALWAYS`) or a top-level global (`Key.W`), named by the generator's one naming
function (`godot_enum_model.enum_value_name` under the frozen prefix lock). This script rewrites
`Owner.OLD_NAME` to the new spelling in a Kotlin tree:

  * `Owner.VALUE` where VALUE is a value of one of Owner's enums -> `Owner.Enum.NEW`;
  * `Owner.VALUE` where Owner has no such value but exactly one enum in the API has it (the hand
    subsets: `InputEventKey.KEY_W` -> `Key.W`) -> that enum's spelling;
  * `GodotObject.CONNECT_DEFAULT` (Kanama's own zero, which Godot does not name) ->
    `GodotObject.ConnectFlags(0L)`.

It prints every rewrite, notes rewritten values that flow into a dynamic `Any?` argument (`call`,
`set`, `ConfigFile.setValue`: encoded as INT through `GodotEnumValue`, nothing to do), and lists what it
could NOT fix and a human must: a raw number passed where a method now takes an enum
(`setProcessMode(3L)`), and `Owner.UPPER_CASE` references to a known class that matched no enum value.
Comparisons against raw numbers (`connect(...) == 0L`) and values read back from a dynamic call (a
`Long`) surface as compile errors.

    python3 scripts/migrate_enum_constants.py /path/to/kotlin-src [--dry-run]
    python3 scripts/migrate_enum_constants.py --table docs/reference/generated/enum-migration.md [--check]
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from api_wrapper_candidates import camel_name  # noqa: E402
from godot_enum_model import (  # noqa: E402
    API_PACKAGE,
    EnumSpec,
    enum_value_name,
    load_enums,
    read_lock,
)

ROOT = Path(__file__).resolve().parents[1]
TAG = "[migrate_enum_constants]"
TABLE_PATH = ROOT / "docs/reference/generated/enum-migration.md"
# Kanama-only constants that have no Godot value to map to.
SPECIAL = {"GodotObject.CONNECT_DEFAULT": "GodotObject.ConnectFlags(0L)"}
# The hand-written subsets the pre-0.5 API carried on another class (shown in the table; the
# rewrite itself finds them through the unique-value rule).
HAND_SUBSET_OWNERS = {
    "InputEventKey": "Key",
    "InputEventMouseButton": "MouseButton",
    "PhysicsBody3D": "PhysicsServer3D.BodyAxis",
}


def kotlin_owner(godot_class: str) -> str:
    return "GodotObject" if godot_class == "Object" else godot_class


def build_tables(api_path: Path) -> tuple[dict[str, dict[str, str]], dict[str, str], list[EnumSpec], dict[str, str]]:
    """(per-owner old->new, unique value name -> new, all specs, lock)."""
    enums = load_enums(api_path)
    lock = read_lock()
    per_owner: dict[str, dict[str, str]] = {}
    by_value: dict[str, set[str]] = {}
    for spec in enums.values():
        prefix = lock.get(spec.key, "")
        for value in spec.values:
            new = f"{spec.kotlin_name}.{enum_value_name(prefix, value.name)}"
            if spec.owner is not None:
                per_owner.setdefault(kotlin_owner(spec.owner), {}).setdefault(value.name, new)
            by_value.setdefault(value.name, set()).add(new)
    unique = {name: next(iter(news)) for name, news in by_value.items() if len(news) == 1}
    return per_owner, unique, list(enums.values()), lock


def enum_typed_methods(api_path: Path) -> dict[str, set[int]]:
    """camelCase method name -> argument indexes that are enum-typed in EVERY non-virtual Godot method
    of that name (a name shared by unrelated methods only counts where they all agree)."""
    data = json.loads(api_path.read_text(encoding="utf-8"))
    signatures: dict[str, list[list[str]]] = {}
    for cls in data.get("classes", []):
        for method in cls.get("methods", []):
            if not method.get("is_virtual"):
                signatures.setdefault(camel_name(method["name"]), []).append(
                    [a["type"] for a in method.get("arguments") or []]
                )
    result: dict[str, set[int]] = {}
    for name, overloads in signatures.items():
        width = max(len(o) for o in overloads)
        slots = {
            i
            for i in range(width)
            if all(len(o) > i and o[i].startswith(("enum::", "bitfield::")) for o in overloads)
        }
        if slots:
            result[name] = slots
    return result


# Calls whose arguments are `Any?` Variants. A value class passed there arrives boxed; every enum
# value class implements GodotEnumValue and the Variant encoders turn it into the INT it stands for,
# so these are informational notes, not fixes (dynamic RETURNS still come back as Long).
DYNAMIC_SINK_RE = re.compile(r"\b(?:setValue|call|callDeferred|set|setDeferred|setMeta|emitSignal|emit|rpc|rpcId)\(")
# Not preceded by a word character or a SINGLE dot (`x.Node.FOO` is a member chain), but a range
# operand after `..` (`Window.MODE_A..Window.MODE_B`) is a reference too.
REF_RE = re.compile(r"(?<!\w)(?:(?<=\.\.)|(?<!\.))((?:net\.multigesture\.kanama\.api\.)?)([A-Z][A-Za-z0-9]*)\.([A-Z][A-Z0-9_]*[A-Z0-9])\b(?!\s*\()(?!\.\w)")


def literal_mask(text: str) -> list[bool]:
    """True for every offset inside string / char literal TEXT: rewrites skip it, so
    `"Node.PROCESS_MODE_ALWAYS"` in a string stays. A `${...}` template inside a string is code."""
    mask = [False] * len(text)
    i, n = 0, len(text)

    def skip_template(j: int) -> int:
        depth = 0
        while j < n:
            if text[j] == "{":
                depth += 1
            elif text[j] == "}":
                depth -= 1
                if depth == 0:
                    return j + 1
            j += 1
        return j

    while i < n:
        if text.startswith("//", i):
            # Comments are rewritten (they describe the code); only skip their quotes.
            j = text.find("\n", i)
            i = n if j == -1 else j
        elif text.startswith("/*", i):
            j = text.find("*/", i + 2)
            i = n if j == -1 else j + 2
        elif text[i] == '"' or text[i] == "'":
            triple = text.startswith('"""', i)
            quote = '"""' if triple else text[i]
            j = i + len(quote)
            while j < n and not text.startswith(quote, j):
                if text[j] == "\\" and not triple:
                    mask[j] = True
                    if j + 1 < n:
                        mask[j + 1] = True
                    j += 2
                    continue
                if text.startswith("${", j) and quote != "'":
                    j = skip_template(j + 1)
                    continue
                mask[j] = True
                j += 1
            i = j + len(quote)
        else:
            i += 1
    return mask


def migrate_text(
    text: str,
    per_owner: dict[str, dict[str, str]],
    unique: dict[str, str],
    known_owners: set[str],
    class_constants: set[str] | None = None,
    range_hits: list[tuple[int, str]] | None = None,
) -> tuple[str, list[tuple[int, str, str]], list[tuple[int, str]]]:
    changes: list[tuple[int, str, str]] = []
    unknown: list[tuple[int, str]] = []
    ranges = range_hits if range_hits is not None else []
    in_literal = literal_mask(text)

    def replace(match: re.Match[str]) -> str:
        if in_literal[match.start()]:
            return match.group(0)
        package, owner, name = match.group(1), match.group(2), match.group(3)
        old = f"{owner}.{name}"
        new = SPECIAL.get(old) or per_owner.get(owner, {}).get(name)
        if new is None and owner in known_owners:
            new = unique.get(name)
        line = text.count("\n", 0, match.start()) + 1
        if new is None:
            if owner in known_owners and old not in (class_constants or set()):
                unknown.append((line, old))
            return match.group(0)
        changes.append((line, old, new))
        before = text[max(0, match.start() - 8) : match.start()].rstrip()
        after = text[match.end() : match.end() + 8].lstrip()
        if before.endswith(("..", "until", "downTo")) or after.startswith(("..", "until", "downTo")):
            ranges.append((line, new))
        return package + new

    return REF_RE.sub(replace, text), changes, unknown


def raw_number_suspects(text: str, methods: dict[str, set[int]]) -> list[tuple[int, str]]:
    """Calls of an enum-typed method whose enum-slot argument is a raw integer literal."""
    suspects: list[tuple[int, str]] = []
    for match in re.finditer(r"\.(\w+)\(", text):
        slots = methods.get(match.group(1))
        if not slots:
            continue
        start = match.end()
        depth, i = 1, start
        while i < len(text) and depth:
            depth += {"(": 1, ")": -1}.get(text[i], 0)
            i += 1
        args, depth, cur = [], 0, ""
        for c in text[start : i - 1]:
            if c in "([{":
                depth += 1
            elif c in ")]}":
                depth -= 1
            if c == "," and depth == 0:
                args.append(cur.strip())
                cur = ""
            else:
                cur += c
        args.append(cur.strip())
        for index in sorted(slots):
            if index < len(args) and re.fullmatch(r"-?\d+L?", args[index].split("=")[-1].strip()):
                line = text.count("\n", 0, match.start()) + 1
                suspects.append((line, f"{match.group(1)}({', '.join(args)})"))
                break
    return suspects


def add_missing_imports(text: str, new_refs: list[str]) -> str:
    """A file that imports the api package by name (no `api.*`) needs the rewritten references' first
    segment imported when it is a type the file did not name before (`Key`, `PhysicsServer3D`)."""
    if f"import {API_PACKAGE}.*" in text or f"import {API_PACKAGE}." not in text:
        return text
    lines = text.split("\n")
    imports = [i for i, line in enumerate(lines) if line.startswith("import ")]
    present = {line.split()[1] for line in lines if line.startswith("import ")}
    needed = sorted({f"{API_PACKAGE}.{ref.split('.')[0].split('(')[0]}" for ref in new_refs} - present)
    if not needed:
        return text
    block = sorted([*(lines[i] for i in imports), *(f"import {name}" for name in needed)])
    first, last = imports[0], imports[-1]
    if last - first + 1 != len(imports):  # non-contiguous imports: append after the last one
        return "\n".join([*lines[: last + 1], *(f"import {name}" for name in needed), *lines[last + 1 :]])
    return "\n".join([*lines[:first], *block, *lines[last + 1 :]])


def render_table(per_owner: dict[str, dict[str, str]], specs: list[EnumSpec], lock: dict[str, str]) -> str:
    lines = [
        "# Enum Constant Migration",
        "",
        "<!-- GENERATED by scripts/migrate_enum_constants.py --table. DO NOT EDIT BY HAND; the docs gate",
        "     (`--check`) fails when this page is stale. -->",
        "",
        "Since Kanama 0.5 (task 128) every Godot enum and bitfield is a `@JvmInline value class` nested in",
        "its owner (`Node.ProcessMode`) or, for Godot's global enums, top-level in",
        f"`{API_PACKAGE}` (`Key`, `GodotError`). This table maps every pre-0.5 constant to its new",
        "spelling; `scripts/migrate_enum_constants.py <kotlin-src>` applies it to a source tree. Value names",
        "drop the enum's common prefix by Godot's C# rule, frozen per enum in `scripts/enum_prefix_lock.json`",
        "(`PROCESS_MODE_ALWAYS` -> `ALWAYS`); enums without a common prefix keep Godot's names",
        "(`GodotError.ERR_FILE_NOT_FOUND`).",
        "",
        "## Kanama-only constants and hand-written subsets",
        "",
        "| Before 0.5 | Since 0.5 |",
        "|---|---|",
        *(f"| `{old}` | `{new}` |" for old, new in SPECIAL.items()),
        *(f"| `{owner}.<Godot name>` (hand subset) | `{target}.<value>` |" for owner, target in HAND_SUBSET_OWNERS.items()),
        "",
        "## Global enums",
        "",
        "| Godot | Kotlin type | Values (Godot name -> Kotlin name) |",
        "|---|---|---|",
    ]
    for spec in sorted((s for s in specs if s.owner is None), key=lambda s: s.key):
        prefix = lock.get(spec.key, "")
        values = ", ".join(f"`{v.name}` -> `{enum_value_name(prefix, v.name)}`" for v in spec.values)
        lines.append(f"| `{spec.key}` | `{spec.kotlin_name}` | {values} |")
    lines += ["", "## Class enums", "", "| Before 0.5 | Since 0.5 |", "|---|---|"]
    for owner in sorted(per_owner):
        for old, new in per_owner[owner].items():
            lines.append(f"| `{owner}.{old}` | `{new}` |")
    lines.append("")
    return "\n".join(lines)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    parser.add_argument("paths", nargs="*", type=Path, help="Kotlin source files or directories to migrate")
    parser.add_argument("--api", type=Path, default=ROOT / "extension_api.json")
    parser.add_argument("--dry-run", action="store_true", help="report, do not write")
    parser.add_argument("--table", type=Path, help="write (or with --check verify) the old->new table page")
    parser.add_argument("--check", action="store_true", help="with --table: fail if the page is stale")
    args = parser.parse_args()
    per_owner, unique, specs, lock = build_tables(args.api)

    if args.table is not None:
        content = render_table(per_owner, specs, lock)
        if args.check:
            current = args.table.read_text(encoding="utf-8") if args.table.exists() else ""
            if current != content:
                print(f"{TAG} FAIL {args.table} is stale; re-run with --table {args.table}", file=sys.stderr)
                return 1
            print(f"{TAG} PASS {args.table} is current ({sum(len(v) for v in per_owner.values())} class-enum rows)")
            return 0
        args.table.parent.mkdir(parents=True, exist_ok=True)
        args.table.write_text(content, encoding="utf-8")
        print(f"{TAG} wrote {args.table}")
        if not args.paths:
            return 0

    if not args.paths:
        parser.error("give Kotlin source paths to migrate, or --table")
    data = json.loads(args.api.read_text(encoding="utf-8"))
    known_owners = {kotlin_owner(c["name"]) for c in data["classes"]} | {"GodotObject"}
    # Class constants that are not enum values stay `const val` and need no migration.
    class_constants = {
        f"{kotlin_owner(c['name'])}.{k['name']}" for c in data["classes"] for k in c.get("constants") or ()
    }
    # An all-caps nested enum TYPE (`Viewport.MSAA`, `Viewport.SDFScale`-style names that happen to be
    # SCREAMING_CASE) is a type reference, not an unmatched value: never report it (task 128 C).
    class_constants |= {f"{kotlin_owner(str(spec.owner))}.{spec.name}" for spec in specs if spec.owner}
    methods = enum_typed_methods(args.api)
    files = [p for path in args.paths for p in ([path] if path.is_file() else sorted(path.rglob("*.kt")))]
    changed_total = 0
    changed_files = 0
    human: list[str] = []
    info: list[str] = []
    for path in files:
        text = path.read_text(encoding="utf-8")
        range_hits: list[tuple[int, str]] = []
        new_text, changes, unknown = migrate_text(text, per_owner, unique, known_owners, class_constants, range_hits)
        for line, old, new in changes:
            print(f"{path}:{line}: {old} -> {new}")
        changed_total += len(changes)
        changed_files += 1 if changes else 0
        human += [f"{path}:{line}: {ref} matched no enum value (constant removed or renamed?)" for line, ref in unknown]
        human += [
            f"{path}:{line}: {new} is a range bound (`..` / `until` / `downTo`): value classes have no ranges; "
            "iterate the `.value`s or list the values"
            for line, new in range_hits
        ]
        human += [f"{path}:{line}: raw number where an enum is expected: {call}" for line, call in raw_number_suspects(new_text, methods)]
        info += [
            f"{path}:{line}: {new} is passed to a dynamic (Variant/Any?) argument: now encoded as INT "
            "(GodotEnumValue), no change needed"
            for line, _, new in changes
            if DYNAMIC_SINK_RE.search(new_text.split("\n")[line - 1])
        ]
        new_text = add_missing_imports(new_text, [new for _, _, new in changes])
        if changes and not args.dry_run:
            path.write_text(new_text, encoding="utf-8")
    print(
        f"{TAG} {changed_total} reference(s) rewritten in {changed_files} file(s) "
        f"({len(files)} scanned){' (dry run)' if args.dry_run else ''}"
    )
    if info:
        print(f"{TAG} {len(info)} note(s):")
        for item in info:
            print(f"    {item}")
    if human:
        print(f"{TAG} {len(human)} place(s) need a human:")
        for item in human:
            print(f"    {item}")
    else:
        print(f"{TAG} 0 places need a human")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
