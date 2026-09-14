#!/usr/bin/env python3
"""Audit hand-written Kanama value-type wrappers against Godot builtins.

This is a guardrail for the bug class found in Quaternion.slerp and
Transform3D.interpolateWith:

* a Kotlin method has the same semantic name as a Godot builtin method, but
  reimplements math instead of calling the engine through the BuiltinCalls
  facade;
* a Godot builtin method argument typed as `float` is marshalled as a `real_t`
  component (`BArg.Floats`) instead of the builtin-call ABI's 8-byte double
  (`BArg.Real`).

The value types are one shared set under src/commonMain since task 104 step 2,
and they reach the engine only through
`net.multigesture.kanama.binding.runtime.BuiltinCalls` — the desktop half over
Panama, the iOS half over the C shim, kept identical by
`scripts/check_builtin_calls_contract.py`.

The script is intentionally report-only for now. It exits non-zero only when
`--strict` is passed.
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from dataclasses import dataclass
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
TYPES_DIR = ROOT / "src/commonMain/kotlin/net/multigesture/kanama/types"
API_PATH = ROOT / "extension_api.json"

# Reviewed local implementations whose Godot C++ definitions are direct scalar
# formulas. Keep transform, basis, quaternion interpolation, projection, and
# other orientation/physics-sensitive helpers out of this list unless they have
# focused parity coverage.
REVIEWED_LOCAL_MATH = {
    # isEqualApprox / isZeroApprox reimplement Godot's Math::is_equal_approx /
    # is_zero_approx exactly (see types/ApproxMath.kt: CMP_EPSILON 0.00001, same
    # a==b short-circuit + relative/absolute tolerance) so composite value-type
    # comparisons match the engine without a per-component engine round-trip
    # (task 14). Reviewed as faithful local math.
    "AABB.isEqualApprox",
    "Basis.isEqualApprox",
    "Quaternion.isEqualApprox",
    "Rect2.isEqualApprox",
    "Transform2D.isEqualApprox",
    "Transform3D.isEqualApprox",
    "Vector2.isEqualApprox",
    "Vector2.isZeroApprox",
    "Vector3.isEqualApprox",
    "Vector3.isZeroApprox",
    "Vector4.isEqualApprox",
    "Vector4.isZeroApprox",
    "AABB.hasPoint",
    "Plane.distanceTo",
    "Plane.intersectsRay",
    "Quaternion.dot",
    "Quaternion.inverse",
    "Quaternion.length",
    "Quaternion.lengthSquared",
    "Quaternion.normalized",
    "Rect2.hasPoint",
    "RID.isValid",
    "Vector2.angle",
    "Vector2.distanceSquaredTo",
    "Vector2.distanceTo",
    "Vector2.dot",
    "Vector2.length",
    "Vector2.lengthSquared",
    "Vector2.normalized",
    "Vector3.cross",
    "Vector3.distanceSquaredTo",
    "Vector3.distanceTo",
    "Vector3.dot",
    # is_normalized and max_axis_index were engine-computed on iOS until task 104
    # step 2 and are exact arithmetic: is_normalized is Godot's
    # Math::is_equal_approx(length_squared(), 1, UNIT_EPSILON) including the
    # exact-equality short-circuit that makes an infinite component behave, and
    # max_axis_index is its two-comparison ladder with ties going to the earlier
    # axis. Sharing one body saves a builtin round-trip per call on device.
    "Vector3.isNormalized",
    "Vector3.length",
    "Vector3.lengthSquared",
    "Vector3.maxAxisIndex",
    "Vector3.normalized",
    "Vector4.dot",
    "Vector4.length",
    "Vector4.lengthSquared",
}


FUN_RE = re.compile(
    r"(?P<indent>^[ \t]*)"
    r"(?:(?:public|private|internal)\s+)?"
    r"(?P<operator>operator\s+)?fun\s+"
    r"(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*"
    r"\(",
    re.MULTILINE,
)

# A call that reaches the engine: the BuiltinCalls facade (every shared value-type
# body) or the older BuiltinTypes helpers (still used by the desktop runtime).
BUILTIN_CALL_RE = re.compile(
    r"\bBuiltinCalls\.(?:call|callNoArgsFloat32|callScalar|callBool|callInt)\s*\("
    r"|\bBuiltinTypes\.(?:call|construct)\s*\(",
)
# The two BArg encodings a scalar argument can take. Godot's ptr-ABI passes a
# Variant FLOAT argument as an 8-byte double (BArg.Real) regardless of real_t
# precision; BArg.Floats is a buffer of real_t *components* and is wrong for one.
SCALAR_ARG_RE = re.compile(r"\bBArg\.Real\s*\(")
COMPONENT_ARG_RE = re.compile(r"\bBArg\.Floats\s*\(")


@dataclass(frozen=True)
class KotlinFunction:
    path: Path
    class_name: str
    name: str
    body: str
    line: int


def load_api(path: Path) -> dict:
    with path.open("r", encoding="utf-8") as fp:
        return json.load(fp)


def lower_camel_to_snake(name: str) -> str:
    # getRotationQuaternion -> get_rotation_quaternion, AABB-ish names remain
    # best-effort because this is an audit heuristic, not code generation.
    name = re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", name)
    return name.lower()


def find_matching_paren(content: str, open_index: int) -> int | None:
    depth = 0
    in_string = False
    escaped = False
    for i in range(open_index, len(content)):
        ch = content[i]
        if in_string:
            if escaped:
                escaped = False
            elif ch == "\\":
                escaped = True
            elif ch == '"':
                in_string = False
            continue
        if ch == '"':
            in_string = True
        elif ch == "(":
            depth += 1
        elif ch == ")":
            depth -= 1
            if depth == 0:
                return i
    return None


def find_function_end(content: str, after_signature: int) -> int:
    i = after_signature
    while i < len(content) and content[i].isspace():
        i += 1
    if i < len(content) and content[i] == "=":
        next_fun = FUN_RE.search(content, i + 1)
        return next_fun.start() if next_fun else len(content)
    if i < len(content) and content[i] == "{":
        depth = 0
        in_string = False
        escaped = False
        for j in range(i, len(content)):
            ch = content[j]
            if in_string:
                if escaped:
                    escaped = False
                elif ch == "\\":
                    escaped = True
                elif ch == '"':
                    in_string = False
                continue
            if ch == '"':
                in_string = True
            elif ch == "{":
                depth += 1
            elif ch == "}":
                depth -= 1
                if depth == 0:
                    return j + 1
    next_fun = FUN_RE.search(content, i)
    return next_fun.start() if next_fun else len(content)


def kotlin_functions(path: Path) -> list[KotlinFunction]:
    content = path.read_text(encoding="utf-8")
    class_name = path.stem
    result: list[KotlinFunction] = []
    for match in FUN_RE.finditer(content):
        if match.group("operator"):
            continue
        if "private" in content[max(0, match.start() - 32) : match.start()]:
            continue
        paren = content.find("(", match.start())
        close = find_matching_paren(content, paren)
        if close is None:
            continue
        end = find_function_end(content, close + 1)
        line = content.count("\n", 0, match.start()) + 1
        result.append(
            KotlinFunction(
                path=path,
                class_name=class_name,
                name=match.group("name"),
                body=content[match.start() : end],
                line=line,
            ),
        )
    return result


def builtin_calling_helpers(functions: list[KotlinFunction]) -> set[str]:
    return {
        fn.name
        for fn in functions
        if BUILTIN_CALL_RE.search(fn.body)
    }


def uses_builtin_call(fn: KotlinFunction, helpers: set[str]) -> bool:
    if BUILTIN_CALL_RE.search(fn.body):
        return True
    return any(re.search(rf"\b{re.escape(helper)}\s*\(", fn.body) for helper in helpers)


def effective_body(fn: KotlinFunction, helper_bodies: dict[str, str]) -> str:
    """The method's body plus the body of any call-shape helper it delegates to.

    A one-line method like `fun lerp(to, weight) = callVector3RealRetVector3(...)` carries
    no BArg of its own; the encodings live in the shared private helper. Following one
    level is what lets the ABI check below see them.
    """
    body = fn.body
    for name, helper in helper_bodies.items():
        if name != fn.name and re.search(rf"\b{re.escape(name)}\s*\(", fn.body):
            body += helper
    return body


def builtin_methods(api: dict) -> dict[str, dict[str, dict]]:
    result: dict[str, dict[str, dict]] = {}
    for cls in api.get("builtin_classes", []):
        name = cls.get("name")
        if not name:
            continue
        methods = {}
        for method in cls.get("methods", []):
            method_name = method.get("name")
            if method_name:
                methods[method_name] = method
        result[name] = methods
    return result


def method_float_arguments(method: dict) -> set[str]:
    return {
        arg["name"]
        for arg in method.get("arguments", [])
        if arg.get("type") == "float" and arg.get("name")
    }


def audit_float_abi(fn: KotlinFunction, method: dict, body: str) -> list[str]:
    """A Godot `float` argument must travel as BArg.Real, never as a real_t component."""
    float_args = method_float_arguments(method)
    if not float_args or not BUILTIN_CALL_RE.search(body):
        return []

    scalars = len(SCALAR_ARG_RE.findall(body))
    if scalars >= len(float_args):
        return []
    components = len(COMPONENT_ARG_RE.findall(body))
    return [
        f"{fn.path}:{fn.line}: {fn.class_name}.{fn.name} passes "
        f"{len(float_args)} Godot float arg(s) ({', '.join(sorted(float_args))}) but only "
        f"{scalars} BArg.Real; a scalar float is an 8-byte double at ptrcall, not a real_t "
        f"component (saw {components} BArg.Floats)",
    ]


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--api", type=Path, default=API_PATH)
    parser.add_argument("--strict", action="store_true")
    args = parser.parse_args()

    api = load_api(args.api)
    methods_by_type = builtin_methods(api)

    suspicious = []
    abi_warnings = []
    covered = 0
    reviewed_local = 0

    for path in sorted(TYPES_DIR.glob("*.kt")):
        class_name = path.stem
        methods = methods_by_type.get(class_name, {})
        if not methods:
            continue
        functions = kotlin_functions(path)
        helpers = builtin_calling_helpers(functions)
        helper_bodies = {fn.name: fn.body for fn in functions if fn.name in helpers}
        for fn in functions:
            builtin_name = lower_camel_to_snake(fn.name)
            method = methods.get(builtin_name)
            if not method:
                continue
            covered += 1
            if not uses_builtin_call(fn, helpers):
                qualified = f"{class_name}.{fn.name}"
                if qualified in REVIEWED_LOCAL_MATH:
                    reviewed_local += 1
                    continue
                suspicious.append(
                    f"{fn.path}:{fn.line}: {class_name}.{fn.name} matches Godot "
                    f"{class_name}.{builtin_name} but does not call the engine through "
                    f"BuiltinCalls",
                )
            abi_warnings.extend(audit_float_abi(fn, method, effective_body(fn, helper_bodies)))

    print(f"[value_type_audit] checked {covered} Kotlin methods that map to Godot builtins")
    if reviewed_local:
        print(f"[value_type_audit] accepted {reviewed_local} reviewed local math helper(s)")

    if suspicious:
        print("\n[value_type_audit] methods to review for hand-written math:")
        for line in suspicious:
            print(f"  - {line}")

    if abi_warnings:
        print("\n[value_type_audit] builtin float ABI warnings:")
        for line in abi_warnings:
            print(f"  - {line}")

    if not suspicious and not abi_warnings:
        print("[value_type_audit] no suspicious value-type wrapper methods found")

    return 1 if args.strict and (suspicious or abi_warnings) else 0


if __name__ == "__main__":
    sys.exit(main())
