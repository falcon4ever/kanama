#!/usr/bin/env python3
"""Gate: nothing can hand a zero instance to a guarded iOS C entry point.

A zero instance is the wrapper generator's static-method marker -- `_null_segment()` in
`scripts/generate_api_wrapper.py` renders `NULL_SEGMENT` as the instance for every `is_static`
method, and the shared wrapper tree reaches Godot statics that way on BOTH platforms. Desktop
hands `MemorySegment.NULL` straight to `object_method_bind_ptrcall`, which Godot accepts for a
static bind. The iOS C shim does not: `kanama_ios_godot_ptrcall` deliberately early-returns when
`instance == 0` (commit `30c949a1`, which added the separate `kanama_ios_godot_ptrcall_static`
entry point for statics), so a static routed through the instance entry is a SILENT no-op -- it
returns null / 0 / the default, on a phone, with no Kotlin frame and no log line.

That is not a one-entry-point problem. Over twenty `kanama_ios_*` entry points carry the same
`instance == 0` guard (the packed-array, array-blob, UTF-8, Variant-scalar, object-call and
ClassDB-instantiate shapes), and the shared tree reaches statics through their helpers too --
which is how thirteen static call sites stayed no-ops after the generic `ptrcallDispatch` landed
(task 117 P2' follow-up). This gate is the standing check that the whole family is covered, so the
next static the generator renders through any shape is safe by construction rather than by review.

Two rules, one per side of the boundary:

  * Kotlin: every guarded entry point is named EXACTLY ONCE in `ObjectCalls.kt`, inside a private
    `*Dispatch` function that also names its `_static` sibling.
  * C: no `*_dispatch` body in the shim -- the unguarded half of a split -- names a guarded
    exported entry point. Those bodies exist to be reachable with a zero instance; calling a
    guarded entry from one re-introduces the early return one level down, where no Kotlin
    dispatcher can see it. They must call the callee's `_dispatch` body instead.

Scope is derived, never hand-listed. An entry point is in scope when it is

  * exported (not `static`) from `ios/bootstrap/kanama_ios_shim.c`, named `kanama_ios_*`;
  * taking BOTH an `int64_t method_bind` and an instance parameter -- that pair is the ptrcall
    shape the generator renders a static through; and
  * EARLY-RETURNING when that instance parameter is zero: an `if (...)` whose condition tests
    `instance == 0` / `object == 0` (whatever the guard spells) and whose body returns. A zero
    test elsewhere in a body is a branch, not a guard, and does not put an entry point in scope.

The symbol pattern is `kanama_ios_[a-z0-9_]+`, not the narrower `kanama_ios_godot_` prefix: the
prefix hid `kanama_ios_classdb_instantiate_owned`, a guarded `method_bind` + `instance` entry that
`ObjectCalls.ptrcallWithStringNameArgRetVariantScalarOwned` calls, for the whole of the first two
follow-ups. Scope follows the SIGNATURE, which is what makes an entry reachable by a static, never
the spelling of its name.

Guarded entry points WITHOUT a `method_bind` (`kanama_ios_godot_object_destroy`,
`..._object_connect_bound`, `..._object_disconnect_bound`, ...) are out of scope on purpose: their
zero check guards a live engine object handle, which the generator's static marker never reaches.
They are listed on the PASS line so the subset stays visible instead of silently widening.

Failures, one line each:
  * raw-call             -- `ObjectCalls.kt` names a guarded entry point outside a `*Dispatch`
                            function (the defect this gate exists to catch);
  * dispatcher-no-static -- a `*Dispatch` function calls the guarded entry point but never its
                            `_static` sibling, so a zero instance still reaches the guard;
  * shim-raw-call        -- a `*_dispatch` body in the shim calls a guarded entry point instead of
                            that entry's own `_dispatch` body, so the guard bites one frame below
                            the Kotlin dispatcher;
  * missing-sibling      -- a called guarded entry point has no `<entry>_static` defined in the
                            shim or declared in `ios/include/kanama_ios.h` (the cinterop header);
  * parse-error          -- a file did not parse. Never silenced: a file this gate cannot read is
                            a file it is not guarding, which is the failure mode it exists to
                            remove (task 118).

Negative tests (task 118: every gate ships with its way to make it fail). `--shim`, `--header` and
`--objectcalls` (or `KANAMA_IOS_SHIM`, `KANAMA_IOS_HEADER`, `KANAMA_IOS_OBJECTCALLS`) point the
gate at a scratch copy, so a defect can be re-introduced without touching the tree:

  1. raw-call. In `ObjectCalls.kt`, replace one dispatcher call with the raw entry point, e.g. in
     `ptrcallNoArgsRetString`

         -        ptrcallNoArgsRetStringDispatch(
         +        kanama_ios_godot_ptrcall_no_args_ret_string(

     and re-run: the gate reports `raw-call` for that line and exits 1.
  2. shim-raw-call. In a scratch copy of the shim, put the `:4626` defect back -- inside
     `kanama_ios_godot_ptrcall_ret_raycast_dict_dispatch`, call `kanama_ios_godot_ptrcall(` where
     it calls `kanama_ios_godot_ptrcall_dispatch(` -- and run with `--shim <copy>`.
  3. generic members. Insert a generic member (`private fun <T> scratch(...)`) into a scratch
     `ObjectCalls.kt` between a `*Dispatch` function and a raw guarded call: the call must still be
     attributed to the generic member, not to the `*Dispatch` above it, and the gate must FAIL.
  4. And the false-positive direction, which matters just as much: re-write one `_static`
     declaration in a scratch header onto a single line (`int64_t ..._static(int64_t method_bind,
     char *out_buf, int64_t buf_size);`) and run with `--header <copy>`. The gate must still PASS
     — `kanama_ios.h` spells 57 of its declarations that way, and a gate that cries
     `missing-sibling` over a line break is a gate people learn to ignore.

Usage:
    python3 scripts/check_ios_static_dispatch.py           # gate
    python3 scripts/check_ios_static_dispatch.py --json    # the parsed model + findings as JSON
    python3 scripts/check_ios_static_dispatch.py --shim /tmp/scratch_shim.c   # negative tests
"""

from __future__ import annotations

import argparse
import json
import os
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SCRIPTS = ROOT / "scripts"
TAG = "[ios_static_dispatch]"

# Blanks comments and string/char literals in place (offsets and line numbers survive), so an
# entry point named in a comment or a KDoc example is never read as a call. Shared with the other
# Kotlin-source gates rather than re-implemented: one function cannot disagree with itself.
sys.path.insert(0, str(SCRIPTS))
from check_wrapper_parity import strip_noise  # noqa: E402

C_SHIM = ROOT / "ios/bootstrap/kanama_ios_shim.c"
C_HEADER = ROOT / "ios/include/kanama_ios.h"
OBJECT_CALLS = ROOT / "src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt"

# An exported definition opens at column 0 with its return type and the name. The shim writes the
# parameters one per line (signature ending at a lone `) {`) for anything with more than one, and
# inlines a single parameter on the opening line — BOTH forms are parsed, because a form this gate
# skips is a function it silently stops guarding.
C_DEF_RE = re.compile(
    r"^(?!static\b)(?!extern\b)[A-Za-z_][A-Za-z0-9_ *]*?\b(kanama_ios_[a-z0-9_]+)\("
    r"(?:$|(?P<inline>[^)]*)\)\s*\{$)"
)
# The unguarded half of a split. Always `static` today, but the optional prefix keeps an exported
# one in scope rather than quietly dropping it.
C_DISPATCH_DEF_RE = re.compile(
    r"^(?:static\s+)?(?!extern\b)[A-Za-z_][A-Za-z0-9_ *]*?\b(kanama_ios_[a-z0-9_]*_dispatch)\("
)
# A header declaration, in BOTH the forms `kanama_ios.h` uses: parameters one per line after the
# opening `(`, and the whole parameter list on the opening line ending `);`. Matching only the
# first form made the 57 single-line declarations invisible, which would report a `_static` sibling
# declared in the header as missing the moment one was written on one line.
C_DECL_RE = re.compile(
    r"^[A-Za-z_][A-Za-z0-9_ *]*?\b(kanama_ios_[a-z0-9_]+)\((?:$|[^;{]*\)\s*;$)", re.M
)
# The zero test itself. `instance == 0` / `object == 0`: the shim spells the rejection both ways.
C_ZERO_RE = re.compile(r"\b(instance|object)\s*==\s*0\b")
C_IF_RE = re.compile(r"\bif\s*\(")
C_RETURN_RE = re.compile(r"\breturn\b")
C_PARAM_RE = re.compile(
    r"^\s*(?:const\s+)?([A-Za-z_][A-Za-z0-9_]*)"  # the base type
    r"(?:\s*\*\s*(?:const\s*)?)*\s*"  # any pointer/const-pointer depth
    r"([A-Za-z_][A-Za-z0-9_]*)\s*,?\s*$"  # the parameter name
)

# A member of `actual object ObjectCalls` sits at exactly two spaces (ktfmt googleStyle), with an
# optional type-parameter list and an optional extension receiver. Anything deeper is inside the
# member the gate is already attributing the call to. The `<…>` is NOT optional decoration: without
# it every call inside a generic member (`internal inline fun <T> retTypedObjectList(`) was
# attributed to the member ABOVE it, so a raw guarded call in a generic member read as a call from
# whatever `*Dispatch` happened to precede it — a hole exactly where this gate must not have one.
KT_FUN_RE = re.compile(
    r"^  (?:(?:private|internal|public|actual|inline|suspend|override)\s+)*"
    r"fun\s+(?:<[^>]*>\s+)?(?:[\w.<>?]+\.)?(\w+)\s*[(<]"
)
# A call is spelled the same in C and in Kotlin, so one pattern serves both sides of the boundary.
CALL_RE = re.compile(r"\b(kanama_ios_[a-z0-9_]+)\s*\(")


def strip_c_noise(text: str) -> str:
    """Blank C comments and string literals in place, preserving offsets and line numbers.

    The C analogue of `strip_noise`: the shim's comments name guarded entry points constantly (the
    `_dispatch` bodies carry a comment block about the split), and a name in a comment is not a
    call.
    """

    def blank(match: re.Match[str]) -> str:
        return "".join(c if c == "\n" else " " for c in match.group(0))

    return re.sub(r"/\*.*?\*/|//[^\n]*|\"(?:\\.|[^\"\\\n])*\"|'(?:\\.|[^'\\\n])*'", blank, text, flags=re.S)


class ParseError(RuntimeError):
    pass


def rel(path: Path) -> str:
    try:
        return str(path.relative_to(ROOT))
    except ValueError:
        return str(path)


def early_return_guard(body: str) -> str | None:
    """The zero-instance EARLY RETURN in `body`, or None.

    A guard is an `if (...)` whose condition tests the instance for zero and whose statement
    returns. A bare `instance == 0` anywhere in the body is a branch — `kanama_ios_godot_ptrcall`'s
    own arg marshalling compares handles — and must not put an entry point in scope.
    """
    pos = 0
    while True:
        opener = C_IF_RE.search(body, pos)
        if opener is None:
            return None
        depth, i = 0, opener.end() - 1  # at the '('
        while i < len(body):
            if body[i] == "(":
                depth += 1
            elif body[i] == ")":
                depth -= 1
                if depth == 0:
                    break
            i += 1
        if i >= len(body):
            return None
        condition = body[opener.end() : i]
        pos = i + 1
        zero = C_ZERO_RE.search(condition)
        if zero is None:
            continue
        rest = body[i + 1 :]
        stripped = rest.lstrip()
        if stripped.startswith("{"):
            start = rest.index("{")
            depth, end = 0, len(rest)
            for j in range(start, len(rest)):
                if rest[j] == "{":
                    depth += 1
                elif rest[j] == "}":
                    depth -= 1
                    if depth == 0:
                        end = j
                        break
            guarded = rest[start:end]
        else:
            guarded = stripped.split(";", 1)[0]
        if C_RETURN_RE.search(guarded):
            return zero.group(0)


def _body(lines: list[str], open_at: int) -> str | None:
    """The braced body that opens on line `open_at` (0-based), brace-balanced.

    None means the braces never closed — never the same value as an empty body, so the caller
    raises `parse-error` instead of silently guarding nothing.
    """
    depth, k, out = 1, open_at + 1, []
    while k < len(lines) and depth > 0:
        depth += lines[k].count("{") - lines[k].count("}")
        if depth > 0:
            out.append(lines[k])
        k += 1
    if depth > 0:
        return None
    return "\n".join(out)


def c_functions(text: str, path: Path) -> dict[str, dict]:
    """Every exported `kanama_ios_*` definition: its parameters and whether it zero-guards."""
    lines = text.split("\n")
    out: dict[str, dict] = {}
    for i, line in enumerate(lines):
        match = C_DEF_RE.match(line)
        if not match:
            continue
        name = match.group(1)
        params: list[tuple[str, str]] = []
        if match.group("inline") is not None:
            j = i
            raw = [p for p in match.group("inline").split(",") if p.strip() and p.strip() != "void"]
            for chunk in raw:
                param = C_PARAM_RE.match(chunk)
                if param is None:
                    raise ParseError(f"{rel(path)}:{i + 1} unreadable parameter in {name}: {chunk!r}")
                params.append((param.group(1), param.group(2)))
        else:
            j = i + 1
            while j < len(lines) and lines[j] != ") {":
                param = C_PARAM_RE.match(lines[j])
                if param is None:
                    raise ParseError(
                        f"{rel(path)}:{j + 1} unreadable parameter in {name}: {lines[j]!r}"
                    )
                params.append((param.group(1), param.group(2)))
                j += 1
            if j >= len(lines):
                raise ParseError(f"{rel(path)}:{i + 1} unterminated signature for {name}")
        body = _body(lines, j)
        if body is None:
            raise ParseError(f"{rel(path)}:{i + 1} unterminated body for {name}")
        out[name] = {
            "line": i + 1,
            "params": [p[1] for p in params],
            "has_method_bind": any(p == ("int64_t", "method_bind") for p in params),
            "guard": early_return_guard(body),
        }
    if not out:
        raise ParseError(f"{rel(path)}: no exported kanama_ios_* definition parsed")
    return out


def c_dispatch_calls(text: str, path: Path) -> dict[str, list[tuple[int, str]]]:
    """Each `*_dispatch` body in the shim -> the (line, callee) `kanama_ios_*` calls it makes.

    These bodies are the unguarded halves of the guarded/`_dispatch`/`_static` splits, so they are
    exactly the frames a zero instance travels through. A guarded entry point called from one is a
    guard the Kotlin dispatcher cannot route around.
    """
    lines = text.split("\n")
    out: dict[str, list[tuple[int, str]]] = {}
    for i, line in enumerate(lines):
        match = C_DISPATCH_DEF_RE.match(line)
        if not match:
            continue
        name = match.group(1)
        j = i
        while j < len(lines) and not lines[j].rstrip().endswith("{"):
            if lines[j].rstrip().endswith(";"):  # a forward declaration, not a definition
                j = -1
                break
            j += 1
        if j < 0:
            continue
        if j >= len(lines):
            raise ParseError(f"{rel(path)}:{i + 1} unterminated signature for {name}")
        body = _body(lines, j)
        if body is None:
            raise ParseError(f"{rel(path)}:{i + 1} unterminated body for {name}")
        sites: list[tuple[int, str]] = []
        for offset, body_line in enumerate(body.split("\n")):
            for callee in CALL_RE.findall(body_line):
                sites.append((j + 2 + offset, callee))
        out[name] = sites
    if not out:
        raise ParseError(
            f"{rel(path)}: no `*_dispatch` body parsed — the shim splits every guarded entry point "
            "that way, so zero of them means this half of the gate is reading nothing"
        )
    return out


def kotlin_calls(text: str, path: Path) -> tuple[dict[str, list[tuple[int, str]]], dict[str, set[str]]]:
    """Map each `kanama_ios_*` call to (line, enclosing member), and each member to its calls."""
    lines = strip_noise(text).split("\n")
    by_entry: dict[str, list[tuple[int, str]]] = {}
    by_member: dict[str, set[str]] = {}
    member = "<file>"
    for i, line in enumerate(lines):
        fun = KT_FUN_RE.match(line)
        if fun:
            member = fun.group(1)
        for name in CALL_RE.findall(line):
            by_entry.setdefault(name, []).append((i + 1, member))
            by_member.setdefault(member, set()).add(name)
    if not by_entry:
        raise ParseError(f"{rel(path)}: no kanama_ios_* call parsed")
    return by_entry, by_member


def analyse(shim: Path, header: Path, object_calls: Path) -> tuple[dict, list[str]]:
    findings: list[str] = []
    try:
        shim_text = strip_c_noise(shim.read_text())
        functions = c_functions(shim_text, shim)
        dispatch_bodies = c_dispatch_calls(shim_text, shim)
        declared = set(C_DECL_RE.findall(strip_c_noise(header.read_text())))
        calls, member_calls = kotlin_calls(object_calls.read_text(), object_calls)
    except (ParseError, OSError) as exc:
        return {}, [f"parse-error {exc}"]

    guarded = {
        name: info
        for name, info in functions.items()
        if info["guard"] is not None and info["has_method_bind"]
    }
    object_handle_entries = sorted(
        name
        for name, info in functions.items()
        if info["guard"] is not None and not info["has_method_bind"]
    )

    table: dict[str, str] = {}
    for name in sorted(guarded):
        sites = calls.get(name, [])
        if not sites:
            continue
        sibling = f"{name}_static"
        if sibling not in functions or sibling not in declared:
            where = []
            if sibling not in functions:
                where.append(rel(shim))
            if sibling not in declared:
                where.append(rel(header))
            findings.append(
                f"missing-sibling {name} is guarded ({guarded[name]['guard']}) and called from "
                f"{rel(object_calls)}, but {sibling} is declared in neither " + " nor ".join(where)
            )
        dispatchers = set()
        for line, member in sites:
            if not member.endswith("Dispatch"):
                findings.append(
                    f"raw-call {rel(object_calls)}:{line} calls the guarded entry point {name} "
                    f"from {member}(), not from a *Dispatch function — a static call site reaches "
                    f"its `{guarded[name]['guard']}` guard and silently no-ops"
                )
                continue
            dispatchers.add(member)
            if sibling not in member_calls.get(member, set()):
                findings.append(
                    f"dispatcher-no-static {rel(object_calls)}:{line} {member}() calls {name} but "
                    f"never {sibling}, so a zero instance still reaches the guard"
                )
        if dispatchers:
            table[name] = ", ".join(sorted(dispatchers))

    for body_name in sorted(dispatch_bodies):
        for line, callee in dispatch_bodies[body_name]:
            if callee not in guarded or callee == body_name:
                continue
            inner = f"{callee}_dispatch"
            fix = (
                f"call {inner} (its unguarded body) instead"
                if inner in dispatch_bodies or inner in functions
                else f"split {callee} into a guarded entry, an unguarded {inner} body and a "
                f"{callee}_static sibling, then call {inner}"
            )
            findings.append(
                f"shim-raw-call {rel(shim)}:{line} {body_name}() calls the guarded entry point "
                f"{callee} (`{guarded[callee]['guard']}`) — {body_name} is the UNGUARDED half of a "
                f"split, so it is reached with a zero instance and the callee's guard then returns "
                f"before the result cell is written; {fix}"
            )

    model = {
        "guarded_entry_points": {n: guarded[n]["line"] for n in sorted(guarded)},
        "dispatchers": table,
        "object_handle_entry_points": object_handle_entries,
        "shim_dispatch_bodies": sorted(dispatch_bodies),
        "files": {
            "shim": rel(shim),
            "header": rel(header),
            "objectcalls": rel(object_calls),
        },
    }
    return model, findings


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("--json", action="store_true", help="print the parsed model and findings as JSON")
    parser.add_argument(
        "--shim",
        default=os.environ.get("KANAMA_IOS_SHIM", str(C_SHIM)),
        help="the C shim to parse (default: ios/bootstrap/kanama_ios_shim.c; env KANAMA_IOS_SHIM). "
        "Point it at a scratch copy to run the gate's negative tests.",
    )
    parser.add_argument(
        "--header",
        default=os.environ.get("KANAMA_IOS_HEADER", str(C_HEADER)),
        help="the cinterop header to parse (default: ios/include/kanama_ios.h; env KANAMA_IOS_HEADER)",
    )
    parser.add_argument(
        "--objectcalls",
        default=os.environ.get("KANAMA_IOS_OBJECTCALLS", str(OBJECT_CALLS)),
        help="the iOS ObjectCalls.kt to parse (default: src/iosMain/.../runtime/ObjectCalls.kt; "
        "env KANAMA_IOS_OBJECTCALLS)",
    )
    args = parser.parse_args()

    model, findings = analyse(Path(args.shim), Path(args.header), Path(args.objectcalls))

    if args.json:
        print(json.dumps({**model, "findings": findings, "status": "FAIL" if findings else "PASS"}, indent=2))

    if findings:
        for finding in findings:
            print(f"{TAG} FAIL {finding}", file=sys.stderr)
        print(
            f"{TAG} FAIL {len(findings)} finding(s): a guarded iOS entry point is reachable with a "
            "zero instance",
            file=sys.stderr,
        )
        print(
            "  A zero instance is the generator's static-method marker (NULL_SEGMENT for an\n"
            "  `is_static` method), and the guarded C entry points early-return on it, so the call\n"
            "  is a silent no-op on a phone. Route the call through the entry point's private\n"
            "  `*Dispatch` function in ObjectCalls.kt (see ptrcallDispatch), or — inside the shim's\n"
            "  own `_dispatch` bodies — through the callee's `_dispatch` body. If the entry point\n"
            "  has no `_static` sibling yet, split its body in ios/bootstrap/kanama_ios_shim.c the\n"
            "  way kanama_ios_godot_ptrcall / _dispatch / _static is split and declare the new\n"
            "  symbol in ios/include/kanama_ios.h.",
            file=sys.stderr,
        )
        return 1

    stream = sys.stderr if args.json else sys.stdout
    table = model["dispatchers"]
    print(
        f"{TAG} PASS {len(model['guarded_entry_points'])} guarded entry point(s) in "
        f"{model['files']['shim']}, {len(table)} of them called from {model['files']['objectcalls']} "
        f"— each through its dispatcher",
        file=stream,
    )
    width = max((len(n) for n in table), default=0)
    for name, dispatcher in sorted(table.items()):
        print(f"  {name.ljust(width)}  ->  {dispatcher}", file=stream)
    print(
        f"  {len(model['shim_dispatch_bodies'])} shim `_dispatch` bodies scanned: none calls a "
        f"guarded entry point",
        file=stream,
    )
    print(
        f"  out of scope (no method_bind — the zero guards a live object handle, not a static "
        f"marker): {', '.join(model['object_handle_entry_points'])}",
        file=stream,
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
