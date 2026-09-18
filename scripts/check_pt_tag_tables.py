#!/usr/bin/env python3
"""Gate: the five copies of the iOS ptrcall type-tag (`PT_*`) table agree, name by name and
value by value.

The numbers a Kotlin call site writes into an argument-tag array are the wire protocol of the iOS
ptrcall seam: `kanama_ios_godot_ptrcall` in the C shim switches on them to decide how to read each
argument slot and how to decode the return. Today that one table exists five times:

  1. `ios/bootstrap/kanama_ios_shim.c` -- `enum { KANAMA_IOS_PT_VOID = 0, ... }`, the AUTHORITY
     (the shim dispatches on these; the enumerators are implicit and append-only);
  2. `scripts/generate_api_wrapper.py` -- `IOS_PT_TAG_VALUES`, the generator's name -> value dict,
     which it also writes as tag literals into the helpers it emits;
  3. `src/iosMain/.../binding/runtime/ObjectCalls.kt` -- `private const val PT_* = N` inside the
     GENERATED MEMBERS region, a rendering of copy 2 (`--write-tree`);
  4. `src/iosMain/.../ios/KanamaIosRuntime.kt` -- `private const val IOS_PT_* = N`, hand-written,
     a subset: the Variant/return path and the inbound call-arg decoder;
  5. `src/commonMain/.../binding/runtime/BuiltinTags.kt` -- common `const val PT_* = N`, the seven
     tags the shared builtin call sites need (task 119 finding 16's hoist).

Nothing compared any two of them. A renumber in one copy compiles everywhere, passes the drift
gate (which compares the generated region's member NAMES only, task 119 item 4) and fails only at
the shim's tag dispatch on a phone -- as a wrong-looking value or a crash, with no Kotlin frame.
This gate is that comparison (task 119 item 30).

Failures, one line each:
  * value-mismatch      -- a tag name carries different numbers in two copies;
  * unknown-tag         -- a Kotlin or Python copy names a tag the C enum does not declare (the
                           shim would dispatch on a tag it has no case for);
  * duplicate-value     -- two enumerators of the C enum resolve to the same number;
  * generated-region-drift -- copy 3 is not an exact rendering of copy 2 (same names, same values);
  * parse-error         -- a copy did not parse. Never silence: a table this gate cannot read is a
                           table it is not guarding, which is the failure mode it exists to remove.

A tag that exists ONLY in the C enum is not a failure: copies 4 and 5 are subsets by design (and
copy 2/3 may lag an appended C tag by one commit). The PASS line prints all five sizes so the
subsets stay visible.

Usage:
    python3 scripts/check_pt_tag_tables.py           # gate
    python3 scripts/check_pt_tag_tables.py --json    # the five parsed tables + findings as JSON
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SCRIPTS = ROOT / "scripts"
TAG = "[pt_tags]"

# Blanks comments and string/char literals in place (offsets and line numbers survive), so a
# `PT_*` mentioned in a comment or a string is never read as a declaration. Imported rather than
# copied: it is the same Kotlin-source utility the hand-shaped wrapper parity gate parses with, and
# one function cannot disagree with itself (task 119 findings 12/17). The import is clean --
# `check_wrapper_parity` runs nothing at module level.
sys.path.insert(0, str(SCRIPTS))
from check_wrapper_parity import strip_noise  # noqa: E402
from generate_api_wrapper import (  # noqa: E402
    IOS_GENERATED_BEGIN,
    IOS_GENERATED_END,
    IOS_PT_TAG_VALUES,
)

C_SHIM = ROOT / "ios/bootstrap/kanama_ios_shim.c"
GENERATOR = ROOT / "scripts/generate_api_wrapper.py"
OBJECT_CALLS = ROOT / "src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt"
IOS_RUNTIME = ROOT / "src/iosMain/kotlin/net/multigesture/kanama/ios/KanamaIosRuntime.kt"
BUILTIN_TAGS = ROOT / "src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/BuiltinTags.kt"

C_PREFIX = "KANAMA_IOS_PT_"
# The READER: a tag declaration whose value this gate can evaluate. Covers every spelling the three
# Kotlin copies use and the near ones they could grow --
#   `const val PT_X = 1`, `private const val IOS_PT_X = 1`, `internal const val IOS_PT_VARIANT = 38`,
#   `const val PT_X: Int = 41`, `const val PT_X = 0x29`, `const val PT_X = 1_000`
# -- so visibility is not part of the match and the type annotation is optional. Anything this
# does NOT match is caught by the pattern below and fails; it is never dropped.
KOTLIN_TAG_RE = re.compile(
    r"\bconst\s+val\s+(?:IOS_)?PT_(?P<name>[A-Za-z0-9_]+)"
    r"(?:\s*:\s*[A-Za-z_][A-Za-z0-9_.]*)?"
    r"\s*=\s*(?P<value>[+-]?(?:0[xX])?[0-9A-Fa-f_]+)\b"
)
# The COMPLETENESS CHECK, not a parser: every tag DECLARATION, whatever its right-hand side. A
# declaration this finds that the reader above did not parse is a parse-error naming the tag.
# Without it the reader's misses are silent, and silent is the whole failure mode this gate exists
# to remove: copies 4 and 5 are subsets by design, so a dropped declaration produces NO finding at
# all and the gate reports PASS over a tag that is live in Kotlin and unread here (task 118 -- a
# gate that swallows a failure produces green evidence for a red state). The C side already holds
# this standard: a non-literal enumerator is a parse-error, not a skipped row.
KOTLIN_TAG_DECL_RE = re.compile(r"\bconst\s+val\s+(?P<decl>(?:IOS_)?PT_[A-Za-z0-9_]+)")
# An `enum` block, with or without a tag name. The shim has several; the PT one is picked by body.
C_ENUM_RE = re.compile(r"\benum\b(?:\s+[A-Za-z_][A-Za-z0-9_]*)?\s*\{")


class ParseError(Exception):
    """A copy did not parse. Reported as a `parse-error` finding naming the file, never as silence."""


def strip_c_noise(src: str) -> str:
    """Blank C comments and string/char literals in place (the C twin of `strip_noise`).

    Kept local rather than shared with the Kotlin one because the two languages disagree about
    nested block comments (Kotlin nests, C does not) and about raw strings.
    """
    out = list(src)
    i = 0
    n = len(src)
    while i < n:
        ch = src[i]
        if ch == "/" and i + 1 < n and src[i + 1] == "/":
            j = src.find("\n", i)
            j = n if j == -1 else j
            for k in range(i, j):
                out[k] = " "
            i = j
        elif ch == "/" and i + 1 < n and src[i + 1] == "*":
            j = src.find("*/", i + 2)
            j = n if j == -1 else j + 2
            for k in range(i, j):
                if out[k] != "\n":
                    out[k] = " "
            i = j
        elif ch in "\"'":
            quote = ch
            j = i + 1
            while j < n:
                if src[j] == "\\":
                    j += 2
                    continue
                if src[j] == quote:
                    j += 1
                    break
                if src[j] == "\n":
                    break
                j += 1
            for k in range(i, min(j, n)):
                if out[k] != "\n":
                    out[k] = " "
            i = j
        else:
            i += 1
    return "".join(out)


def match_brace(src: str, open_index: int) -> int:
    """Index of the `}` closing the `{` at [open_index], on comment/string-blanked source."""
    depth = 0
    for i in range(open_index, len(src)):
        if src[i] == "{":
            depth += 1
        elif src[i] == "}":
            depth -= 1
            if depth == 0:
                return i
    raise ParseError(f"unbalanced braces from offset {open_index}")


def split_top_level(body: str) -> list[str]:
    """Split an enum body on its top-level commas (a value may carry parentheses or a cast)."""
    out: list[str] = []
    depth = 0
    current = ""
    for ch in body:
        if ch in "([{":
            depth += 1
        elif ch in ")]}":
            depth -= 1
        elif ch == "," and depth == 0:
            out.append(current)
            current = ""
            continue
        current += ch
    out.append(current)
    return [item.strip() for item in out if item.strip()]


def parse_c_enum(path: Path) -> dict[str, int]:
    """The `KANAMA_IOS_PT_*` enum of the shim -> bare tag name -> value.

    Walks the enumerators in order: an explicit `= N` (decimal or hex) sets the counter, anything
    else is previous + 1 -- the C rule, which is what the compiler the shim is built with applies.
    An enumerator whose value is an expression this walker cannot evaluate is a parse-error, not a
    skipped row.
    """
    src = strip_c_noise(path.read_text(encoding="utf-8"))
    blocks: list[tuple[int, int]] = []
    for match in C_ENUM_RE.finditer(src):
        open_index = src.index("{", match.start())
        close_index = match_brace(src, open_index)
        if C_PREFIX in src[open_index:close_index]:
            blocks.append((open_index, close_index))
    if not blocks:
        raise ParseError(f"no `enum {{ ... {C_PREFIX}* ... }}` block found")
    if len(blocks) > 1:
        raise ParseError(
            f"{len(blocks)} enum blocks declare {C_PREFIX}* enumerators; the tag table must be one block"
        )
    open_index, close_index = blocks[0]

    tags: dict[str, int] = {}
    counter = -1
    for item in split_top_level(src[open_index + 1 : close_index]):
        name, _, value = item.partition("=")
        name = name.strip()
        if not re.fullmatch(r"[A-Za-z_][A-Za-z0-9_]*", name):
            raise ParseError(f"enumerator {item!r} is not a plain identifier")
        if not name.startswith(C_PREFIX):
            raise ParseError(f"enumerator {name} in the PT enum does not start with {C_PREFIX}")
        if value.strip():
            try:
                counter = int(value.strip(), 0)
            except ValueError as exc:
                raise ParseError(f"enumerator {name} has a non-literal value {value.strip()!r}") from exc
        else:
            counter += 1
        bare = name[len(C_PREFIX) :]
        if bare in tags:
            raise ParseError(f"enumerator {name} is declared twice")
        tags[bare] = counter
    if not tags:
        raise ParseError("the PT enum block is empty")
    return tags


def parse_generator() -> dict[str, int]:
    """`IOS_PT_TAG_VALUES` -> bare tag name -> value.

    Imported, not regexed: the dict is what the generator actually writes into the helpers it
    emits, so a `# ` in front of a line or a computed entry must move this gate's view too.
    """
    tags: dict[str, int] = {}
    for name, value in IOS_PT_TAG_VALUES.items():
        if not name.startswith("PT_"):
            raise ParseError(f"IOS_PT_TAG_VALUES key {name!r} does not start with PT_")
        if not isinstance(value, int) or isinstance(value, bool):
            raise ParseError(f"IOS_PT_TAG_VALUES[{name!r}] is {value!r}, not an int")
        tags[name[len("PT_") :]] = value
    if not tags:
        raise ParseError("IOS_PT_TAG_VALUES is empty")
    return tags


def parse_kotlin_int(text: str) -> int:
    """A Kotlin integer literal -> int. Raises ValueError on anything else.

    Decimal or hex; `_` digit separators are allowed. Not `int(text, 0)`: Python reads a leading
    zero as an octal prefix and rejects `010`, which Kotlin (no octal literals) reads as 10.
    """
    cleaned = text.replace("_", "")
    if re.fullmatch(r"[+-]?0[xX][0-9A-Fa-f]+", cleaned):
        return int(cleaned, 16)
    if re.fullmatch(r"[+-]?[0-9]+", cleaned):
        return int(cleaned, 10)
    raise ValueError(f"{text!r} is not a decimal or hex integer literal")


def parse_kotlin(path: Path, *, region: tuple[str, str] | None = None) -> dict[str, int]:
    """`const val (IOS_)?PT_X = N` declarations of a Kotlin file -> bare tag name -> value.

    EVERY such declaration is either read or reported: the value must be a decimal or hex integer
    literal, and a declaration whose value is anything else (another constant, an expression, a
    `Long` suffix) is a parse-error naming the tag. Dropping it instead would be invisible --
    copies 4 and 5 are subsets by design, so an unread tag produces no finding at all.

    [region] restricts the scan to the text between two markers (the generated region of the iOS
    `ObjectCalls.kt`), so a hand-written tag above the BEGIN marker cannot be mistaken for part of
    the rendering of the generator's dict.
    """
    src = strip_noise(path.read_text(encoding="utf-8"))
    first_line = 1  # line number of src[0] in the file, so a slice still reports file line numbers
    if region is not None:
        begin, end = region
        # The markers live in comments, which `strip_noise` blanked -- locate them in the raw text
        # and slice the blanked copy at the same offsets (strip_noise preserves length).
        raw = path.read_text(encoding="utf-8")
        start = raw.find(begin)
        stop = raw.find(end)
        if start == -1 or stop == -1 or stop < start:
            raise ParseError(f"generated region markers {begin!r} / {end!r} not found in order")
        first_line += src.count("\n", 0, start)
        src = src[start:stop]
    tags: dict[str, int] = {}
    read: set[int] = set()  # offsets of the `const` keywords the reader parsed, for the check below
    for match in KOTLIN_TAG_RE.finditer(src):
        name = match.group("name")
        try:
            value = parse_kotlin_int(match.group("value"))
        except ValueError as exc:
            raise ParseError(f"tag PT_{name}: {exc}") from exc
        if name in tags and tags[name] != value:
            raise ParseError(f"tag PT_{name} is declared twice with different values")
        tags[name] = value
        read.add(match.start())

    # Every declaration the reader did not parse, by the OFFSET of its `const` keyword -- both
    # patterns anchor there, so the offsets coincide exactly for a declaration both match. Comparing
    # offsets rather than names also catches the case where one tag is declared twice and only the
    # second spelling is unreadable.
    for match in KOTLIN_TAG_DECL_RE.finditer(src):
        if match.start() not in read:
            line = first_line + src.count("\n", 0, match.start())
            raise ParseError(
                f"line {line}: `const val {match.group('decl')}` has a value this gate cannot read "
                "(only a decimal or hex integer literal is accepted); a tag it cannot read is a tag "
                "it is not guarding"
            )
    if not tags:
        raise ParseError("no `const val PT_*` declarations found")
    return tags


def load_tables() -> tuple[dict[str, dict[str, int]], list[str]]:
    """Parse all five copies. Returns (label -> table, parse-error findings)."""
    begin, end = objectcalls_region_markers()
    loaders = {
        "C enum": (C_SHIM, lambda: parse_c_enum(C_SHIM)),
        "generator": (GENERATOR, parse_generator),
        "ObjectCalls": (OBJECT_CALLS, lambda: parse_kotlin(OBJECT_CALLS, region=(begin, end))),
        "KanamaIosRuntime": (IOS_RUNTIME, lambda: parse_kotlin(IOS_RUNTIME)),
        "BuiltinTags": (BUILTIN_TAGS, lambda: parse_kotlin(BUILTIN_TAGS)),
    }
    tables: dict[str, dict[str, int]] = {}
    findings: list[str] = []
    for label, (path, loader) in loaders.items():
        if not path.exists():
            findings.append(f"parse-error {label} ({rel(path)}): file does not exist")
            continue
        try:
            tables[label] = loader()
        except ParseError as exc:
            findings.append(f"parse-error {label} ({rel(path)}): {exc}")
        except OSError as exc:
            findings.append(f"parse-error {label} ({rel(path)}): {exc}")
    return tables, findings


def objectcalls_region_markers() -> tuple[str, str]:
    """The generator's own BEGIN/END markers for the generated region of the iOS ObjectCalls.kt.

    Read from the generator (not spelled out here) so the marker text cannot drift between the
    writer and this reader.
    """
    return IOS_GENERATED_BEGIN, IOS_GENERATED_END


def rel(path: Path) -> str:
    try:
        return str(path.relative_to(ROOT))
    except ValueError:
        return str(path)


PATHS = {
    "C enum": C_SHIM,
    "generator": GENERATOR,
    "ObjectCalls": OBJECT_CALLS,
    "KanamaIosRuntime": IOS_RUNTIME,
    "BuiltinTags": BUILTIN_TAGS,
}
ORDER = ("C enum", "generator", "ObjectCalls", "KanamaIosRuntime", "BuiltinTags")


def compare(tables: dict[str, dict[str, int]]) -> list[str]:
    """Every disagreement between the parsed copies, one string per finding."""
    findings: list[str] = []

    # 1. The C enum must not resolve two enumerators to the same number: the shim's switch would
    #    have two labels for one case and the second family would be unreachable.
    c_table = tables.get("C enum", {})
    by_value: dict[int, list[str]] = {}
    for name, value in c_table.items():
        by_value.setdefault(value, []).append(name)
    for value, names in sorted(by_value.items()):
        if len(names) > 1:
            findings.append(
                f"duplicate-value C enum ({rel(C_SHIM)}): {value} is "
                f"{', '.join('KANAMA_IOS_PT_' + n for n in sorted(names))}"
            )

    # 2. A tag name whose number differs between any two copies. One line per name, listing every
    #    copy that declares it with its file, so the fix has both ends in front of it.
    every_name = sorted({name for table in tables.values() for name in table})
    for name in every_name:
        declared = [(label, tables[label][name]) for label in ORDER if name in tables.get(label, {})]
        values = {value for _, value in declared}
        if len(values) > 1:
            where = ", ".join(f"{label}={value} ({rel(PATHS[label])})" for label, value in declared)
            findings.append(f"value-mismatch {name}: {where}")

    # 3. A Kotlin or Python copy naming a tag the C enum does not declare: the shim has no case for
    #    it, so the call would be dispatched as an unknown tag on device.
    if c_table:
        for label in ORDER:
            if label == "C enum" or label not in tables:
                continue
            for name in sorted(set(tables[label]) - set(c_table)):
                findings.append(
                    f"unknown-tag {name}: declared by {label} ({rel(PATHS[label])}) = "
                    f"{tables[label][name]}, absent from the C enum ({rel(C_SHIM)})"
                )

    # 4. The generated region is supposed to be a rendering of the generator's dict: every entry,
    #    both ways. The drift gate compares member NAMES only (task 119 item 4), so this is the
    #    only thing that notices a hand edit to the region's tag block or a stale --write-tree.
    if "generator" in tables and "ObjectCalls" in tables:
        generator, region = tables["generator"], tables["ObjectCalls"]
        for name in sorted(set(generator) - set(region)):
            findings.append(
                f"generated-region-drift PT_{name}: in IOS_PT_TAG_VALUES ({rel(GENERATOR)}) = "
                f"{generator[name]}, missing from the generated region ({rel(OBJECT_CALLS)})"
            )
        for name in sorted(set(region) - set(generator)):
            findings.append(
                f"generated-region-drift PT_{name}: in the generated region ({rel(OBJECT_CALLS)}) = "
                f"{region[name]}, missing from IOS_PT_TAG_VALUES ({rel(GENERATOR)})"
            )
        for name in sorted(set(region) & set(generator)):
            if region[name] != generator[name]:
                findings.append(
                    f"generated-region-drift PT_{name}: IOS_PT_TAG_VALUES ({rel(GENERATOR)}) = "
                    f"{generator[name]}, generated region ({rel(OBJECT_CALLS)}) = {region[name]}"
                )
    return findings


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("--json", action="store_true", help="print the parsed tables and findings as JSON")
    args = parser.parse_args()

    tables, findings = load_tables()
    if len(tables) == len(ORDER):
        findings += compare(tables)

    if args.json:
        print(
            json.dumps(
                {
                    "tables": {label: tables.get(label, {}) for label in ORDER},
                    "files": {label: rel(path) for label, path in PATHS.items()},
                    "findings": findings,
                    "status": "FAIL" if findings else "PASS",
                },
                indent=2,
                sort_keys=False,
            )
        )

    if findings:
        for finding in findings:
            print(f"{TAG} FAIL {finding}", file=sys.stderr)
        print(
            f"{TAG} FAIL {len(findings)} finding(s): the five copies of the iOS ptrcall tag table "
            "disagree",
            file=sys.stderr,
        )
        print(
            "  The C enum in ios/bootstrap/kanama_ios_shim.c is the authority -- the shim\n"
            "  dispatches on these numbers. Fix the other copies to match it, then regenerate the\n"
            "  iOS ObjectCalls region: python3 scripts/generate_api_wrapper.py --write-tree\n"
            "  (then ./gradlew ktfmtFormat). Never renumber an existing tag: the enum is\n"
            "  append-only because a shipped app's tag arrays carry the old numbers.",
            file=sys.stderr,
        )
        return 1

    sizes = ", ".join(f"{label} {len(tables[label])}" for label in ORDER[1:])
    print(f"{TAG} PASS {len(ORDER)} copies, C enum {len(tables['C enum'])} tags, {sizes}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
