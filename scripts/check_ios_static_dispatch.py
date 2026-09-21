#!/usr/bin/env python3
"""Gate: no iOS `ObjectCalls` helper can hand a zero instance to a guarded C entry point.

A zero instance is the wrapper generator's static-method marker -- `_null_segment()` in
`scripts/generate_api_wrapper.py` renders `NULL_SEGMENT` as the instance for every `is_static`
method, and the shared wrapper tree reaches Godot statics that way on BOTH platforms. Desktop
hands `MemorySegment.NULL` straight to `object_method_bind_ptrcall`, which Godot accepts for a
static bind. The iOS C shim does not: `kanama_ios_godot_ptrcall` deliberately early-returns when
`instance == 0` (commit `30c949a1`, which added the separate `kanama_ios_godot_ptrcall_static`
entry point for statics), so a static routed through the instance entry is a SILENT no-op -- it
returns null / 0 / the default, on a phone, with no Kotlin frame and no log line.

That is not a one-entry-point problem. Over twenty `kanama_ios_godot_*` entry points carry the
same `instance == 0` guard (the packed-array, array-blob, UTF-8, Variant-scalar and object-call
shapes), and the shared tree reaches statics through their helpers too -- which is how thirteen
static call sites stayed no-ops after the generic `ptrcallDispatch` landed (task 117 P2'
follow-up). This gate is the standing check that the whole family is covered, so the next static
the generator renders through any shape is safe by construction rather than by review.

The rule, in one line: every guarded entry point is named EXACTLY ONCE in `ObjectCalls.kt`, inside
a private `*Dispatch` function that also names its `_static` sibling.

Scope is derived, never hand-listed. An entry point is in scope when it is

  * exported (not `static`) from `ios/bootstrap/kanama_ios_shim.c`, named `kanama_ios_godot_*`;
  * taking BOTH an `int64_t method_bind` and an instance parameter -- that pair is the ptrcall
    shape the generator renders a static through; and
  * early-returning when that instance parameter is zero (`instance == 0` / `object == 0`,
    whatever the guard spells).

Guarded entry points WITHOUT a `method_bind` (`kanama_ios_godot_object_destroy`,
`..._object_connect_bound`, `..._object_disconnect_bound`, ...) are out of scope on purpose: their
zero check guards a live engine object handle, which the generator's static marker never reaches.
They are listed on the PASS line so the subset stays visible instead of silently widening.

Failures, one line each:
  * raw-call             -- `ObjectCalls.kt` names a guarded entry point outside a `*Dispatch`
                            function (the defect this gate exists to catch);
  * dispatcher-no-static -- a `*Dispatch` function calls the guarded entry point but never its
                            `_static` sibling, so a zero instance still reaches the guard;
  * missing-sibling      -- a called guarded entry point has no `<entry>_static` defined in the
                            shim or declared in `ios/include/kanama_ios.h` (the cinterop header);
  * parse-error          -- a file did not parse. Never silenced: a file this gate cannot read is
                            a file it is not guarding, which is the failure mode it exists to
                            remove (task 118).

Negative test (task 118: every gate ships with its way to make it fail). In
`src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt`, replace one
dispatcher call with the raw entry point, e.g. in `ptrcallNoArgsRetString`

    -        ptrcallNoArgsRetStringDispatch(
    +        kanama_ios_godot_ptrcall_no_args_ret_string(

and re-run: the gate reports `raw-call` for that line and exits 1. Restore the dispatcher call.

Usage:
    python3 scripts/check_ios_static_dispatch.py           # gate
    python3 scripts/check_ios_static_dispatch.py --json    # the parsed model + findings as JSON
"""

from __future__ import annotations

import argparse
import json
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
    r"^(?!static\b)(?!extern\b)[A-Za-z_][A-Za-z0-9_ *]*?\b(kanama_ios_godot_[a-z0-9_]+)\("
    r"(?:$|(?P<inline>[^)]*)\)\s*\{$)"
)
C_DECL_RE = re.compile(r"^[A-Za-z_][A-Za-z0-9_ *]*?\b(kanama_ios_godot_[a-z0-9_]+)\($", re.M)
# `instance == 0` / `object == 0`, in a guard or anywhere else in the body: the shim spells the
# rejection both ways and this gate only needs to know the parameter is checked for zero.
C_GUARD_RE = re.compile(r"\b(instance|object)\s*==\s*0\b")
C_PARAM_RE = re.compile(
    r"^\s*(?:const\s+)?([A-Za-z_][A-Za-z0-9_]*)"  # the base type
    r"(?:\s*\*\s*(?:const\s*)?)*\s*"  # any pointer/const-pointer depth
    r"([A-Za-z_][A-Za-z0-9_]*)\s*,?\s*$"  # the parameter name
)

# A member of `actual object ObjectCalls` sits at exactly two spaces (ktfmt googleStyle), with an
# optional extension receiver. Anything deeper is inside the member the gate is already attributing
# the call to.
KT_FUN_RE = re.compile(r"^  (?:(?:private|internal|public|actual|inline|suspend|override)\s+)*fun\s+(?:[\w.<>?]+\.)?(\w+)\s*[(<]")
KT_CALL_RE = re.compile(r"\b(kanama_ios_godot_[a-z0-9_]+)\s*\(")


class ParseError(RuntimeError):
    pass


def rel(path: Path) -> str:
    return str(path.relative_to(ROOT))


def c_functions(text: str) -> dict[str, dict]:
    """Every exported `kanama_ios_godot_*` definition: its parameters and whether it zero-guards."""
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
            for text in raw:
                param = C_PARAM_RE.match(text)
                if param is None:
                    raise ParseError(f"{rel(C_SHIM)}:{i + 1} unreadable parameter in {name}: {text!r}")
                params.append((param.group(1), param.group(2)))
        else:
            j = i + 1
            while j < len(lines) and lines[j] != ") {":
                param = C_PARAM_RE.match(lines[j])
                if param is None:
                    raise ParseError(
                        f"{rel(C_SHIM)}:{j + 1} unreadable parameter in {name}: {lines[j]!r}"
                    )
                params.append((param.group(1), param.group(2)))
                j += 1
            if j >= len(lines):
                raise ParseError(f"{rel(C_SHIM)}:{i + 1} unterminated signature for {name}")
        depth, k = 1, j + 1
        body: list[str] = []
        while k < len(lines) and depth > 0:
            depth += lines[k].count("{") - lines[k].count("}")
            if depth > 0:
                body.append(lines[k])
            k += 1
        if depth > 0:
            raise ParseError(f"{rel(C_SHIM)}:{i + 1} unterminated body for {name}")
        guard = C_GUARD_RE.search("\n".join(body))
        out[name] = {
            "line": i + 1,
            "params": [p[1] for p in params],
            "has_method_bind": any(p == ("int64_t", "method_bind") for p in params),
            "guard": guard.group(0) if guard else None,
        }
    if not out:
        raise ParseError(f"{rel(C_SHIM)}: no exported kanama_ios_godot_* definition parsed")
    return out


def kotlin_calls(text: str) -> tuple[dict[str, list[tuple[int, str]]], dict[str, set[str]]]:
    """Map each `kanama_ios_godot_*` call to (line, enclosing member), and each member to its calls."""
    lines = strip_noise(text).split("\n")
    by_entry: dict[str, list[tuple[int, str]]] = {}
    by_member: dict[str, set[str]] = {}
    member = "<file>"
    for i, line in enumerate(lines):
        fun = KT_FUN_RE.match(line)
        if fun:
            member = fun.group(1)
        for name in KT_CALL_RE.findall(line):
            by_entry.setdefault(name, []).append((i + 1, member))
            by_member.setdefault(member, set()).add(name)
    if not by_entry:
        raise ParseError(f"{rel(OBJECT_CALLS)}: no kanama_ios_godot_* call parsed")
    return by_entry, by_member


def analyse() -> tuple[dict, list[str]]:
    findings: list[str] = []
    try:
        shim = c_functions(C_SHIM.read_text())
        header = set(C_DECL_RE.findall(C_HEADER.read_text()))
        calls, member_calls = kotlin_calls(OBJECT_CALLS.read_text())
    except (ParseError, OSError) as exc:
        return {}, [f"parse-error {exc}"]

    guarded = {
        name: info
        for name, info in shim.items()
        if info["guard"] is not None and info["has_method_bind"]
    }
    object_handle_entries = sorted(
        name
        for name, info in shim.items()
        if info["guard"] is not None and not info["has_method_bind"]
    )

    table: dict[str, str] = {}
    for name in sorted(guarded):
        sites = calls.get(name, [])
        if not sites:
            continue
        sibling = f"{name}_static"
        if sibling not in shim or sibling not in header:
            where = []
            if sibling not in shim:
                where.append(rel(C_SHIM))
            if sibling not in header:
                where.append(rel(C_HEADER))
            findings.append(
                f"missing-sibling {name} is guarded ({guarded[name]['guard']}) and called from "
                f"{rel(OBJECT_CALLS)}, but {sibling} is declared in neither " + " nor ".join(where)
            )
        dispatchers = set()
        for line, member in sites:
            if not member.endswith("Dispatch"):
                findings.append(
                    f"raw-call {rel(OBJECT_CALLS)}:{line} calls the guarded entry point {name} "
                    f"from {member}(), not from a *Dispatch function — a static call site reaches "
                    f"its `{guarded[name]['guard']}` guard and silently no-ops"
                )
                continue
            dispatchers.add(member)
            if sibling not in member_calls.get(member, set()):
                findings.append(
                    f"dispatcher-no-static {rel(OBJECT_CALLS)}:{line} {member}() calls {name} but "
                    f"never {sibling}, so a zero instance still reaches the guard"
                )
        if dispatchers:
            table[name] = ", ".join(sorted(dispatchers))

    model = {
        "guarded_entry_points": {n: guarded[n]["line"] for n in sorted(guarded)},
        "dispatchers": table,
        "object_handle_entry_points": object_handle_entries,
        "files": {
            "shim": rel(C_SHIM),
            "header": rel(C_HEADER),
            "objectcalls": rel(OBJECT_CALLS),
        },
    }
    return model, findings


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("--json", action="store_true", help="print the parsed model and findings as JSON")
    args = parser.parse_args()

    model, findings = analyse()

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
            "  `*Dispatch` function in ObjectCalls.kt (see ptrcallDispatch); if the entry point has\n"
            "  no `_static` sibling yet, split its body in ios/bootstrap/kanama_ios_shim.c the way\n"
            "  kanama_ios_godot_ptrcall / _dispatch / _static is split and declare the new symbol\n"
            "  in ios/include/kanama_ios.h.",
            file=sys.stderr,
        )
        return 1

    stream = sys.stderr if args.json else sys.stdout
    table = model["dispatchers"]
    print(
        f"{TAG} PASS {len(table)} guarded entry point(s) called from {rel(OBJECT_CALLS)}, each "
        f"through its dispatcher ({len(model['guarded_entry_points'])} guarded in total)",
        file=stream,
    )
    width = max((len(n) for n in table), default=0)
    for name, dispatcher in sorted(table.items()):
        print(f"  {name.ljust(width)}  ->  {dispatcher}", file=stream)
    print(
        f"  out of scope (no method_bind — the zero guards a live object handle, not a static "
        f"marker): {', '.join(model['object_handle_entry_points'])}",
        file=stream,
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
