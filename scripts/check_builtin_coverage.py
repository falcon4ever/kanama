#!/usr/bin/env python3
"""Gate: every builtin operator and method in extension_api.json has a Kotlin member or a recorded
reason, and every builtin constant and enum a Kotlin counterpart (task 134 B).

Godot lists, per builtin class, its operators (`builtin_classes[*].operators`: 749) and methods
(`builtin_classes[*].methods`: 999). Before task 134 B, Kotlin had 95 of the value types' 279
operators and 70 of their 381 methods, and nothing noticed a missing one. This gate reads the
Kotlin sources -- the value-type classes (generated regions and hand-written members alike), the
scalar operator file -- and fails when an API entry has neither a member nor a reason here.

How an entry is covered:
  * a value-type method: a member named like the Godot method (camelCase, or the generator's
    NAME_OVERRIDES / PROPERTY_MEMBERS), or a reason in the generator's DEFERRED_METHODS;
  * a value-type operator: the Kotlin operator member (`plus`/`minus`/`times`/`div`/`rem`/
    `unaryMinus`/`unaryPlus`/`compareTo`) taking the right operand's Kotlin type;
  * `int`/`float` `*` a value type, `PackedVector2/3Array * Transform`: the extension operators of
    `types/BuiltinScalarOperators.kt`;
  * `==`/`!=` (equals), `not` (no truthiness in Kotlin), `in` (the container's own `contains`):
    language-level reasons below;
  * the classes Kotlin maps to its own types (String, Array, Dictionary, Packed*, ...): the class
    reasons below.

Usage:
    python3 scripts/check_builtin_coverage.py            # gate
    python3 scripts/check_builtin_coverage.py --report   # per-class table as well
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "scripts"))
from generate_builtin_ops import (  # noqa: E402
    DEFERRED_METHODS,
    OP_KOTLIN,
    OWNED_CLASSES,
    PROPERTY_MEMBERS,
    SCALAR_OPS,
    TYPES_DIR,
    class_body,
    kotlin_name,
    kotlin_type,
)

API_PATH = ROOT / "extension_api.json"

# Operators answered by the Kotlin language rather than a member.
EQUALITY_REASON = "equals(): `==`/`!=` compare the stored components, as Godot's `==` does"
NOT_REASON = "Kotlin has no truthiness: compare with the zero value (`v == Vector2.ZERO`, `isEmpty()`)"
IN_REASON = "the right operand's Kotlin type answers `in` (List/Map/String `contains`)"
FORMAT_REASON = "GDScript `%` formatting: Kotlin string templates / `String.format`"

POW_REASON = (
    "Kotlin has no power operator: `x ** y` is `x.pow(y)` (kotlin.math) or `GD.pow(x, y)` (Godot's "
    "`pow`), as porting-gdscript.md says"
)

PARCEL_D = "task 134 parcel D (builtin methods for String, NodePath, Callable, Signal, packed arrays)"

# Builtin classes Kotlin represents with its own types; their entries are covered by the language
# and the stdlib, or deferred to parcel D as stated.
CLASS_REASONS = {
    "Nil": "Kotlin `null`: the language's own operators",
    "bool": "Kotlin `Boolean`: the language's own operators",
    "int": "Kotlin `Int`/`Long`: the language's own operators",
    "float": "Kotlin `Double`: the language's own operators",
    "String": f"Kotlin `String` and its stdlib for the common operations; Godot's String methods are deferred: {PARCEL_D}",
    "StringName": f"Kotlin `String` (a StringName is marshalled from it); its methods are deferred: {PARCEL_D}",
    "NodePath": f"NodePath wraps the path text; its methods need a NodePath facade base, deferred: {PARCEL_D}",
    "Callable": f"deferred (no call/bind API yet): {PARCEL_D}",
    "Signal": f"GodotSignal's connect/emit/await; Signal's builtin methods are deferred: {PARCEL_D}",
    "Dictionary": "Kotlin `Map` and its stdlib",
    "Array": "Kotlin `List` and its stdlib",
}
PACKED_REASON = "Kotlin arrays / `List<T>` and their stdlib (`size`, `contains`, `binarySearch`, `sorted`, ...)"
# Packed-array methods with no stdlib equivalent: deferred, not "covered by the stdlib".
PACKED_DEFERRED_PREFIXES = ("compress", "decompress", "encode_", "decode_", "has_encoded_var", "bswap", "to_")
PACKED_DEFERRED = {"hex_encode", "get_string_from_utf16", "get_string_from_utf32", "get_string_from_wchar",
                   "get_string_from_multibyte_char"}
PACKED_DEFERRED_REASON = f"no stdlib equivalent; deferred: {PARCEL_D}"

MEMBER_RE = re.compile(
    r"\bfun\s+(?:<[^>]*>\s*)?(?:(?P<receiver>[A-Za-z0-9_.<>]+)\.)?(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*\("
    r"\s*(?:(?:vararg\s+)?[A-Za-z_][A-Za-z0-9_]*\s*:\s*(?P<ptype>[A-Za-z0-9_.<>?]+))?"
)


def members(body: str) -> set[tuple[str, str | None]]:
    return {(m.group("name"), m.group("ptype")) for m in MEMBER_RE.finditer(body) if not m.group("receiver")}


def extensions(source: str) -> set[tuple[str, str, str | None]]:
    return {
        (m.group("receiver"), m.group("name"), m.group("ptype"))
        for m in MEMBER_RE.finditer(source)
        if m.group("receiver")
    }


def has(found: set, name: str, ptype: str | None) -> bool:
    return (name, ptype) in found


def operator_status(cls: str, op: dict, found: set, scalar: set) -> tuple[str, str]:
    """('member', what) or ('reason', why) or ('missing', expected member)."""
    name = op["name"]
    right = op.get("right_type", "")
    if name in ("==", "!="):
        return ("reason", EQUALITY_REASON)
    if name == "not":
        return ("reason", NOT_REASON)
    if name == "in":
        return ("reason", IN_REASON)
    if name == "**":
        return ("reason", POW_REASON)
    if cls in ("int", "float") and name == "*" and right in OWNED_CLASSES:
        receivers = ("Int", "Long") if cls == "int" else ("Double",)
        missing = [r for r in receivers if (r, "times", right) not in scalar]
        if missing:
            return ("missing", f"operator fun {missing[0]}.times(other: {right})")
        return ("member", f"{'/'.join(receivers)}.times({right})")
    if cls in ("PackedVector2Array", "PackedVector3Array") and name == "*" and right.startswith("Transform"):
        element = "Vector2" if cls == "PackedVector2Array" else "Vector3"
        if (f"List<{element}>", "times", right) in scalar:
            return ("member", f"List<{element}>.times({right})")
        return ("missing", f"operator fun List<{element}>.times(transform: {right})")
    if cls == "String" or cls == "StringName":
        if name == "%":
            return ("reason", FORMAT_REASON)
    if cls not in OWNED_CLASSES:
        return ("reason", CLASS_REASONS.get(cls, PACKED_REASON))
    if name in ("<", "<=", ">", ">="):
        return ("member", "compareTo") if has(found, "compareTo", cls) else ("missing", f"operator fun compareTo(other: {cls})")
    fn = OP_KOTLIN[name]
    if not right:
        return ("member", fn) if has(found, fn, None) else ("missing", f"operator fun {fn}()")
    if right == "int":
        ok = has(found, fn, "Int") and has(found, fn, "Long")
        return ("member", f"{fn}(Int/Long)") if ok else ("missing", f"operator fun {fn}(scalar: Int/Long)")
    ptype = "Double" if right == "float" else kotlin_type(right)
    return ("member", f"{fn}({ptype})") if has(found, fn, ptype) else ("missing", f"operator fun {fn}(other: {ptype})")


def method_status(cls: str, m: dict, found: set) -> tuple[str, str]:
    if cls not in OWNED_CLASSES:
        if cls.startswith("Packed"):
            name = m["name"]
            if name in PACKED_DEFERRED or name.startswith(PACKED_DEFERRED_PREFIXES):
                return ("reason", PACKED_DEFERRED_REASON)
            return ("reason", PACKED_REASON)
        return ("reason", CLASS_REASONS[cls])
    if (cls, m["name"]) in PROPERTY_MEMBERS:
        return ("member", f"property {PROPERTY_MEMBERS[(cls, m['name'])]}")
    kn = kotlin_name(cls, m["name"])
    if any(name == kn for name, _ in found):
        return ("member", kn)
    if (cls, m["name"]) in DEFERRED_METHODS:
        return ("reason", DEFERRED_METHODS[(cls, m["name"])])
    return ("missing", f"fun {kn}(...)")


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--report", action="store_true", help="print the per-class table")
    args = parser.parse_args()
    api = json.loads(API_PATH.read_text(encoding="utf-8"))
    scalar = extensions(SCALAR_OPS.read_text(encoding="utf-8"))
    missing: list[str] = []
    totals = {"op": [0, 0, 0], "method": [0, 0, 0]}  # member, reason, missing
    const_total = const_have = enum_total = enum_have = 0
    rows = []
    for cls_api in api["builtin_classes"]:
        cls = cls_api["name"]
        found: set = set()
        if cls in OWNED_CLASSES:
            found = members(class_body((TYPES_DIR / f"{cls}.kt").read_text(encoding="utf-8"), cls))
        counts = {"op": [0, 0, 0], "method": [0, 0, 0]}
        for op in cls_api.get("operators", []):
            status, what = operator_status(cls, op, found, scalar)
            index = {"member": 0, "reason": 1, "missing": 2}[status]
            counts["op"][index] += 1
            if status == "missing":
                right = op.get("right_type", "")
                missing.append(f"{cls} {op['name']} {right}".rstrip() + f": no Kotlin member ({what}) and no recorded reason")
        for m in cls_api.get("methods", []):
            status, what = method_status(cls, m, found)
            index = {"member": 0, "reason": 1, "missing": 2}[status]
            counts["method"][index] += 1
            if status == "missing":
                missing.append(f"{cls}.{m['name']}: no Kotlin member ({what}) and no recorded reason")
        body = ""
        if cls in OWNED_CLASSES:
            body = class_body((TYPES_DIR / f"{cls}.kt").read_text(encoding="utf-8"), cls)
        for const in cls_api.get("constants", []):
            const_total += 1
            if re.search(rf"\bval {const['name']}\b", body):
                const_have += 1
            else:
                missing.append(f"{cls}.{const['name']}: no Kotlin constant")
        for enum in cls_api.get("enums", []):
            enum_total += 1
            if re.search(rf"\bvalue class {enum['name']}\b", body):
                enum_have += 1
            else:
                missing.append(f"{cls}.{enum['name']}: no Kotlin enum value class")
        for kind in ("op", "method"):
            for i in range(3):
                totals[kind][i] += counts[kind][i]
        rows.append((cls, counts))
    if args.report:
        print(f"{'class':20s} {'ops member/reason/missing':>28s} {'methods member/reason/missing':>32s}")
        for cls, c in rows:
            print(f"{cls:20s} {'/'.join(map(str, c['op'])):>28s} {'/'.join(map(str, c['method'])):>32s}")
    op_total = sum(totals["op"])
    method_total = sum(totals["method"])
    summary = (
        f"operators {op_total} (Kotlin member {totals['op'][0]}, reason {totals['op'][1]}), "
        f"methods {method_total} (Kotlin member {totals['method'][0]}, reason {totals['method'][1]}), "
        f"constants {const_have}/{const_total}, enums {enum_have}/{enum_total}"
    )
    if missing:
        print(f"[builtin_coverage] FAIL: {len(missing)} builtin operator(s)/method(s) uncovered; {summary}", file=sys.stderr)
        for line in missing:
            print(f"    {line}", file=sys.stderr)
        print(
            "    fix: python3 scripts/generate_builtin_ops.py --write (a new API entry), or record a reason in"
            " generate_builtin_ops.DEFERRED_METHODS / check_builtin_coverage.CLASS_REASONS",
            file=sys.stderr,
        )
        return 1
    print(f"[builtin_coverage] PASS: {summary}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
