#!/usr/bin/env python3
"""Gate: the desktop and iOS `BuiltinCalls` facades declare the same public members.

The shared value-type bodies under `src/commonMain/kotlin/net/multigesture/kanama/types/`
(task 104 step 2) have ONE implementation per method and reach the engine through exactly
one seam: `net.multigesture.kanama.binding.runtime.BuiltinCalls`. That object exists twice
-- once over Panama/FFM for desktop and Android, once over the C shim for iOS -- under the
same fully-qualified name, because the root is a plain JVM module and the Android source
remap forbids `expect`/`actual` (the same reason `GodotHandle` and `Real.kt` are paired
files). So no compiler checks that the two halves agree: a member added to one side and not
the other breaks the other platform's build of the shared bodies, far from the edit.

This script is that check until step 3 turns the pair into an `expect object`. It parses
both files and compares the public member sets: `const val`s, functions (name, parameter
types, defaults, return type) and the `BArg` variants with their parameter lists. Private
members are implementation and are ignored -- FFM Arenas on one side, memScoped cinterop on
the other.

Usage:
    python3 scripts/check_builtin_calls_contract.py           # gate
    python3 scripts/check_builtin_calls_contract.py --list    # print the shared contract
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]

DESKTOP = ROOT / "src/main/kotlin/binding/runtime/BuiltinCalls.kt"
IOS = ROOT / "ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/BuiltinCalls.kt"

PACKAGE = "net.multigesture.kanama.binding.runtime"

# A member declaration at the START of a line, with its indentation. Both files are
# ktfmt-formatted (`ktfmtCheck` is a local_ci stage), so an object member is indented by
# exactly two spaces and a member of the nested `BArg` interface by four; a wrapped
# signature's continuation lines are indented further. Anchoring on that is what keeps a
# `val` inside a function body or a parameter list out of the contract.
DECL_RE = re.compile(
    r"^(?P<indent>[ ]*)"
    r"(?P<vis>private |internal |public )?"
    r"(?P<mods>(?:const |sealed |data |abstract |open |operator |inline |override |value )*)"
    r"(?P<kind>val|var|fun|class|interface|object)\s+"
    r"(?P<name>[A-Za-z_][A-Za-z0-9_]*)",
    re.MULTILINE,
)


def strip_comments(src: str) -> str:
    """Remove // and /* */ comments, leaving string literals intact."""
    out: list[str] = []
    i, n = 0, len(src)
    in_string = False
    while i < n:
        ch = src[i]
        if in_string:
            out.append(ch)
            if ch == "\\" and i + 1 < n:
                out.append(src[i + 1])
                i += 2
                continue
            if ch == '"':
                in_string = False
            i += 1
            continue
        if ch == '"':
            in_string = True
            out.append(ch)
            i += 1
            continue
        if src.startswith("//", i):
            while i < n and src[i] != "\n":
                i += 1
            continue
        if src.startswith("/*", i):
            depth, i = 1, i + 2
            while i < n and depth:
                if src.startswith("/*", i):
                    depth += 1
                    i += 2
                elif src.startswith("*/", i):
                    depth -= 1
                    i += 2
                else:
                    # Keep newlines so line-start anchoring survives comment removal.
                    out.append("\n" if src[i] == "\n" else "")
                    i += 1
            continue
        out.append(ch)
        i += 1
    return "".join(out)


def match_closer(src: str, start: int) -> int:
    """Index of the closer matching the opener at [start] ('{', '(' or '[')."""
    pairs = {"{": "}", "(": ")", "[": "]"}
    opener = src[start]
    closer = pairs[opener]
    depth = 0
    in_string = False
    i = start
    while i < len(src):
        ch = src[i]
        if in_string:
            if ch == "\\":
                i += 2
                continue
            if ch == '"':
                in_string = False
            i += 1
            continue
        if ch == '"':
            in_string = True
        elif ch == opener:
            depth += 1
        elif ch == closer:
            depth -= 1
            if depth == 0:
                return i
        i += 1
    raise ValueError(f"unbalanced '{opener}' at offset {start}")


def collapse(text: str) -> str:
    return re.sub(r"\s+", " ", text).strip()


def split_params(params: str) -> list[str]:
    """Split a parameter list on top-level commas, dropping a trailing empty entry."""
    out: list[str] = []
    depth = 0
    current = ""
    in_string = False
    i = 0
    while i < len(params):
        ch = params[i]
        if in_string:
            current += ch
            if ch == "\\" and i + 1 < len(params):
                current += params[i + 1]
                i += 2
                continue
            if ch == '"':
                in_string = False
            i += 1
            continue
        if ch == '"':
            in_string = True
        elif ch in "(<[":
            depth += 1
        elif ch in ")>]":
            depth -= 1
        elif ch == "," and depth == 0:
            out.append(collapse(current))
            current = ""
            i += 1
            continue
        current += ch
        i += 1
    if collapse(current):
        out.append(collapse(current))
    return [p for p in out if p]


def read_type(src: str, start: int) -> str:
    """Read a type starting at [start] (just past ':'), up to a top-level '=', '{' or newline."""
    depth = 0
    i = start
    while i < len(src):
        ch = src[i]
        if ch in "(<[":
            depth += 1
        elif ch in ")>]":
            depth -= 1
        elif depth == 0 and (ch in "={" or (ch == "\n" and collapse(src[start:i]))):
            break
        i += 1
    return collapse(src[start:i])


def signature(src: str, match: re.Match[str], prefix: str) -> tuple[str, int | None]:
    """The member's normalized signature, and the offset of its body '{' for a container."""
    mods = collapse((match.group("mods") or "") + match.group("kind"))
    name = prefix + match.group("name")
    kind = match.group("kind")
    after = match.end("name")

    params = ""
    paren = src.find("(", after)
    brace = src.find("{", after)
    newline = src.find("\n", after)
    takes_params = paren != -1 and (brace == -1 or paren < brace)
    if kind == "fun":
        close = match_closer(src, paren)
        params = "(" + ", ".join(split_params(src[paren + 1 : close])) + ")"
        after = close + 1
    elif kind in ("class", "interface", "object") and takes_params and paren < newline:
        close = match_closer(src, paren)
        params = "(" + ", ".join(split_params(src[paren + 1 : close])) + ")"
        after = close + 1

    tail = ""
    rest = src[after:]
    stripped = rest.lstrip(" ")
    if stripped.startswith(":"):
        offset = after + (len(rest) - len(stripped)) + 1
        declared = read_type(src, offset)
        if declared:
            tail = f": {declared}"

    body = src.find("{", after)
    body_open = body if kind in ("class", "interface", "object") and body != -1 else None
    return f"{mods} {name}{params}{tail}", body_open


def parse_members(path: Path) -> list[str]:
    raw = path.read_text(encoding="utf-8")
    if f"package {PACKAGE}" not in raw:
        raise SystemExit(f"{path}: expected `package {PACKAGE}`")
    src = strip_comments(raw)

    obj = re.search(r"\bobject\s+BuiltinCalls\s*\{", src)
    if not obj:
        raise SystemExit(f"{path}: no `object BuiltinCalls` declaration")
    body_open = src.index("{", obj.start())
    body_close = match_closer(src, body_open)

    members: list[str] = []
    # (indent, start, end, name prefix) of each scope whose members count.
    scopes = [(2, body_open, body_close, "")]
    while scopes:
        indent, scope_start, scope_end, prefix = scopes.pop()
        for match in DECL_RE.finditer(src, scope_start, scope_end):
            if len(match.group("indent")) != indent:
                continue
            if (match.group("vis") or "").strip() in ("private", "internal"):
                continue
            text, nested = signature(src, match, prefix)
            members.append(text)
            if nested is not None and nested < scope_end:
                scopes.append(
                    (indent + 2, nested, match_closer(src, nested), f"{match.group('name')}."),
                )
    return sorted(members)


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--list", action="store_true", help="print the shared contract")
    args = parser.parse_args()

    desktop = parse_members(DESKTOP)
    ios = parse_members(IOS)

    only_desktop = [m for m in desktop if m not in ios]
    only_ios = [m for m in ios if m not in desktop]
    shared = [m for m in desktop if m in ios]

    print(
        f"[builtin_calls_contract] desktop {len(desktop)} public member(s), "
        f"iOS {len(ios)}, shared {len(shared)}"
    )
    if args.list:
        for member in shared:
            print(f"  {member}")

    if not only_desktop and not only_ios:
        print("[builtin_calls_contract] the two BuiltinCalls facades declare the same contract")
        return 0

    print()
    print("[builtin_calls_contract] the two BuiltinCalls facades have DIVERGED:")
    for member in only_desktop:
        print(f"  desktop only ({DESKTOP.relative_to(ROOT)}): {member}")
    for member in only_ios:
        print(f"  iOS only ({IOS.relative_to(ROOT)}): {member}")
    print()
    print(
        "  Every member of this object is called from the shared value-type bodies in\n"
        "  src/commonMain/kotlin/net/multigesture/kanama/types/, which both platforms compile.\n"
        "  Add the member to both files (or make it private on the side that is only using it\n"
        "  internally)."
    )
    return 1


if __name__ == "__main__":
    sys.exit(main())
