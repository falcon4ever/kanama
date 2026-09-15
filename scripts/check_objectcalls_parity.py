#!/usr/bin/env python3
"""Gate: every `ObjectCalls` helper the shared wrapper tree calls is a MEMBER of
`object ObjectCalls` on desktop and on iOS, with the same parameter names in the same order.

`src/sharedApi/kotlin` is compiled by the root module's JVM and iOS targets and (through the
Android remap) by the Android plugin, and it reaches the engine through exactly one seam:
`net.multigesture.kanama.binding.runtime.ObjectCalls`. Task 104 step 3 turns that object into an
`expect object` with one `actual` per platform, and an `expect` member can only be actualized by a
MEMBER with the SAME parameter names -- a platform-only extension function `fun ObjectCalls.x(...)`
is invisible from common code, and `foo(bar = 1)` does not compile against an `actual` that spells
the parameter differently. Desktop's names are canonical (a 40k-line hand-written file against
iOS's generated ones), so this gate compares iOS to desktop.

Parameter TYPES are deliberately not compared: the two platforms name the raw engine pointer
differently by design (`RawSegment` is FFM's `MemorySegment` on desktop/Android and the
Kotlin/Native shim on iOS, task 104 step 3 parcel A), and `expect`/`actual` resolves those through
the typealias. Names, order and arity are what the compiler will hold us to.

Overloads are compared as sets of parameter-name tuples: every DESKTOP signature must have an iOS
member with the same parameter names, and iOS may declare more (an `actual object` may carry extra
members -- desktop carries ~135 helpers of its own the shared tree never calls).

Reported deltas, all of them failures:
  * missing-on-desktop / missing-on-iOS -- the helper is not declared on that side at all;
  * extension-not-member -- iOS declares it only as `fun ObjectCalls.x(...)`, which common code
    cannot see (this is what task 104 step 3 parcel B removed: the generated helpers now land as
    members inside the GENERATED MEMBERS region of the iOS `ObjectCalls.kt`);
  * param-count -- same name, no iOS member of the desktop arity (a design question, not a rename);
  * name-mismatch -- same name and arity, different parameter spelling.

Usage:
    python3 scripts/check_objectcalls_parity.py           # gate
    python3 scripts/check_objectcalls_parity.py --list    # print the referenced helper contract
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

# The Kotlin source utilities are the ones the sibling BuiltinCalls gate already proved on these
# same two runtime files (ktfmt-formatted, one top-level object): comment stripping that leaves
# string literals alone, and brace/paren matching.
from check_builtin_calls_contract import match_closer, strip_comments

ROOT = Path(__file__).resolve().parents[1]

DESKTOP = ROOT / "src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt"
IOS = ROOT / "src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt"
IOS_SOURCE_ROOT = ROOT / "src/iosMain"
# The sources that call the seam: the generated wrapper tree (compiled per platform) and the
# common fragment beside it (the value types reach the engine through BuiltinCalls, not
# ObjectCalls, but scanning both means a future common caller is covered by this gate too).
SHARED_TREES = (ROOT / "src/sharedApi/kotlin", ROOT / "src/commonMain/kotlin")

# A member function of `object ObjectCalls`. Both files are ktfmt-formatted (`ktfmtCheck` is a
# local_ci stage), so an object member starts at exactly two spaces; anything deeper is a local
# function or a nested scope and anything shallower is top level. A member with a receiver
# (`fun MemScope.packByteDesc(...)`) is an internal marshalling helper, not part of the seam, and
# falls out because the name group cannot span the dot.
MEMBER_RE = re.compile(
    r"^(?P<indent>[ ]*)"
    r"(?P<vis>private |internal |public )?"
    r"(?P<mods>(?:inline |operator |infix |suspend )*)"
    r"fun\s+(?:<[^>]*>\s*)?"
    r"(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*\(",
    re.MULTILINE,
)

# An extension function on the object, wherever it is declared. ktfmt breaks
# `fun ObjectCalls.<name>(` across lines when the helper name is long (three of the task-100
# helpers are), so the receiver, dot and name may be separated by whitespace.
EXTENSION_RE = re.compile(r"\bfun\s+(?:<[^>]*>\s*)?ObjectCalls\s*\.\s*(?P<name>[A-Za-z0-9_]+)\s*\(")

# What the shared tree calls through the seam.
REFERENCE_RE = re.compile(r"\bObjectCalls\.([A-Za-z0-9_]+)")

Signature = tuple[str, ...]


def split_params(params: str) -> list[str]:
    """Split a parameter list on top-level commas.

    Local rather than shared with the BuiltinCalls gate because these helpers take function-type
    parameters (`wrapper: (MemorySegment) -> T?`): the `>` of an arrow must not close a generic.
    """
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
        elif ch in ")]":
            depth -= 1
        elif ch == ">" and params[i - 1 : i] != "-":
            depth -= 1
        elif ch == "," and depth == 0:
            out.append(current.strip())
            current = ""
            i += 1
            continue
        current += ch
        i += 1
    out.append(current.strip())
    return [p for p in out if p]


def parameter_names(src: str, open_paren: int) -> Signature:
    """Parameter names, in order, of the parameter list whose '(' is at [open_paren]."""
    close = match_closer(src, open_paren)
    names: list[str] = []
    for param in split_params(src[open_paren + 1 : close]):
        # `vararg name: T`, `noinline name: T`, `name: T = default`.
        head = param.split(":", 1)[0].strip()
        names.append(head.split()[-1] if head else "")
    return tuple(names)


def parse_members(path: Path) -> dict[str, list[Signature]]:
    """Public/internal member functions of `object ObjectCalls` -> parameter-name tuples.

    One name maps to several tuples only when the object overloads it; desktop has four
    type-differentiated overload pairs (`path: String` / `path: NodePath`) that share their
    parameter names, so the tuples collapse. A private member cannot be called from the shared
    tree, so it is not part of the contract.
    """
    src = strip_comments(path.read_text(encoding="utf-8"))
    obj = re.search(r"^(?:actual )?object\s+ObjectCalls\s*\{", src, re.MULTILINE)
    if not obj:
        raise SystemExit(f"{path}: no top-level `object ObjectCalls` declaration")
    body_open = src.index("{", obj.start())
    body_close = match_closer(src, body_open)

    members: dict[str, list[Signature]] = {}
    for match in MEMBER_RE.finditer(src, body_open, body_close):
        if len(match.group("indent")) != 2:
            continue
        if (match.group("vis") or "").strip() == "private":
            continue
        signature = parameter_names(src, match.end() - 1)
        signatures = members.setdefault(match.group("name"), [])
        if signature not in signatures:
            signatures.append(signature)
    return members


def parse_extensions(root: Path) -> dict[str, Path]:
    """`fun ObjectCalls.<name>(...)` extensions anywhere under [root] -> the file declaring it."""
    extensions: dict[str, Path] = {}
    for path in sorted(root.rglob("*.kt")):
        src = strip_comments(path.read_text(encoding="utf-8"))
        for match in EXTENSION_RE.finditer(src):
            extensions.setdefault(match.group("name"), path)
    return extensions


def referenced_helpers(trees: tuple[Path, ...]) -> list[str]:
    """Distinct `ObjectCalls.<name>` the shared sources call, sorted."""
    names: set[str] = set()
    for tree in trees:
        for path in sorted(tree.rglob("*.kt")):
            # Code only: a KDoc or line comment mentioning `ObjectCalls.foo` is prose, not a call
            # (task 119 finding 5 — the sibling parsers already strip comments).
            names.update(REFERENCE_RE.findall(strip_comments(path.read_text(encoding="utf-8"))))
    return sorted(names)


def render(signature: Signature) -> str:
    return f"({', '.join(signature)})"


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--list", action="store_true", help="print the referenced helper contract")
    args = parser.parse_args()

    desktop = parse_members(DESKTOP)
    ios = parse_members(IOS)
    ios_extensions = parse_extensions(IOS_SOURCE_ROOT)
    referenced = referenced_helpers(SHARED_TREES)

    missing_desktop: list[str] = []
    missing_ios: list[str] = []
    extension_only: list[tuple[str, Path]] = []
    count_mismatch: list[tuple[str, Signature, list[Signature]]] = []
    name_mismatch: list[tuple[str, Signature, Signature]] = []
    ok = 0

    for name in referenced:
        want_all = desktop.get(name)
        if not want_all:
            missing_desktop.append(name)
            continue
        have = ios.get(name, [])
        for want in want_all:
            if want in have:
                ok += 1
            elif same_arity := [got for got in have if len(got) == len(want)]:
                name_mismatch.append((name, want, same_arity[0]))
            elif have:
                count_mismatch.append((name, want, have))
            elif name in ios_extensions:
                extension_only.append((name, ios_extensions[name]))
            else:
                missing_ios.append(name)

    print(
        f"[objectcalls_parity] shared tree references {len(referenced)} helper(s); "
        f"desktop declares {len(desktop)} member(s), iOS {len(ios)} member(s) "
        f"+ {len(ios_extensions)} extension(s); {ok} referenced signature(s) match"
    )
    if args.list:
        for name in referenced:
            for signature in desktop.get(name, []):
                print(f"  {name}{render(signature)}")

    deltas = (
        len(missing_desktop)
        + len(missing_ios)
        + len(extension_only)
        + len(count_mismatch)
        + len(name_mismatch)
    )
    if not deltas:
        print(
            "[objectcalls_parity] every referenced helper is a member on both platforms, "
            "same parameter names"
        )
        return 0

    print()
    print(f"[objectcalls_parity] FAIL: {deltas} referenced signature(s) are not in parity")
    if missing_desktop:
        print(f"\n  missing-on-desktop ({len(missing_desktop)}) -- {DESKTOP.relative_to(ROOT)}:")
        for name in missing_desktop:
            print(f"    {name}")
    if missing_ios:
        print(f"\n  missing-on-iOS ({len(missing_ios)}) -- {IOS.relative_to(ROOT)}:")
        for name in missing_ios:
            print(f"    {name}")
    if extension_only:
        print(
            f"\n  extension-not-member ({len(extension_only)}) -- declared as `fun ObjectCalls.x(...)`,\n"
            "  which common code cannot see; the generator must emit them inside the object:"
        )
        for name, path in extension_only[:20]:
            print(f"    {name}  ({path.relative_to(ROOT)})")
        if len(extension_only) > 20:
            print(f"    ... and {len(extension_only) - 20} more")
    if count_mismatch:
        print(f"\n  param-count ({len(count_mismatch)}) -- a design question, not a rename:")
        for name, want, have in count_mismatch:
            print(f"    {name}\n      desktop: {render(want)}")
            for got in have:
                print(f"      iOS:     {render(got)}")
    if name_mismatch:
        print(f"\n  name-mismatch ({len(name_mismatch)}) -- desktop's spelling is canonical:")
        for name, want, got in name_mismatch:
            diff = ", ".join(f"{w}->{g}" for w, g in zip(want, got, strict=True) if w != g)
            print(f"    {name}: {diff}")
            print(f"      desktop: {render(want)}\n      iOS:     {render(got)}")
    print()
    print(
        "  Task 104 step 3 makes ObjectCalls an `expect object`: an expect member is actualized\n"
        "  only by a member with the same parameter names. Fix iOS to match desktop -- generated\n"
        "  helpers through scripts/generate_api_wrapper.py --write-tree (they land in the\n"
        "  GENERATED MEMBERS region of the iOS ObjectCalls.kt), hand-written ones by renaming the\n"
        "  parameters and following the named call sites the compiler then reports."
    )
    return 1


if __name__ == "__main__":
    sys.exit(main())
