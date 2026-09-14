#!/usr/bin/env python3
"""
Cross-reference every Kotlin type under
src/commonMain/kotlin/net/multigesture/kanama/types/ against the four coverage
tables maintained elsewhere:

  1. VariantType enum entry (binding/runtime/VariantType.kt)
  2. Read case in BuiltinTypes.variantToScalar (binding/runtime/BuiltinTypes.kt)
  3. Write branch in BuiltinTypes.initVariantFromAny (same file)
  4. Typed ObjectCalls overloads — Ret<Type> and With<Type>... (binding/runtime/ObjectCalls.kt)

Surfaces asymmetries — e.g. types with typed ObjectCalls overloads but
missing Variant marshal (the bug class fd38fb9 / b13ff12 fixed).

Run from repo root:

    python3 scripts/type_coverage_audit.py

The PtrcallRet/Arg columns are intentionally sparse — typed overloads
are added lazily as wrappers actually need them. A "-" there is fine.
A "-" under VariantType / Read / Write usually is not.
"""
import os
import re
import sys

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
# One shared set of value types since task 104 step 2 (the root JVM module,
# :ios-runtime and the Android copy task all compile this tree).
TYPES_DIR = os.path.join(ROOT, "src/commonMain/kotlin/net/multigesture/kanama/types")
BUILTIN = os.path.join(ROOT, "src/main/kotlin/binding/runtime/BuiltinTypes.kt")
OBJCALLS = os.path.join(ROOT, "src/main/kotlin/binding/runtime/ObjectCalls.kt")
VARIANT_TYPE = os.path.join(ROOT, "src/main/kotlin/binding/runtime/VariantType.kt")


def read(path: str) -> str:
    with open(path) as fp:
        return fp.read()


def main() -> int:
    type_names = sorted(
        f[:-3] for f in os.listdir(TYPES_DIR) if f.endswith(".kt")
    )
    builtin = read(BUILTIN)
    objcalls = read(OBJCALLS)
    variant_type = read(VARIANT_TYPE)

    print(
        f"{'Type':<14} {'VariantType':<12} {'Read':<5} {'Write':<6} "
        f"{'PtrcallRet':<10} {'PtrcallArg':<10}"
    )
    print("-" * 64)

    # PascalCase → UPPER_SNAKE_CASE so e.g. NodePath → NODE_PATH, Vector3i → VECTOR3I.
    # Insert an underscore before any uppercase letter that follows a lowercase one.
    def to_upper_snake(name: str) -> str:
        return re.sub(r"(?<=[a-z])(?=[A-Z])", "_", name).upper()

    gap_count = 0
    for t in type_names:
        upper = to_upper_snake(t)
        vt_present = bool(re.search(rf"\b{upper}\(\d+\)", variant_type))
        read_present = bool(re.search(rf"VariantType\.{upper}\s*->", builtin))
        write_present = bool(re.search(rf"\bis\s+{t}\s*->", builtin))
        ret_present = bool(re.search(rf"Ret{t}\b", objcalls))
        arg_present = (
            bool(re.search(rf"With{t}\b", objcalls))
            or bool(re.search(rf"value:\s*{t}\b", objcalls))
            or bool(re.search(rf"{t}Arg\b", objcalls))
        )
        # Symmetric-gap heuristic: a type that appears in ObjectCalls (typed
        # ptrcall path) without write-side Variant marshal is the bug class
        # we care about — caller can then `GodotObject.call(...)` with the
        # type and have it silently fail. Read-side missing alone is fine
        # if intentional (we currently leave NodePath read-side deferred).
        if (ret_present or arg_present) and not write_present:
            gap_count += 1
        print(
            f"{t:<14} {('YES' if vt_present else '-'):<12} "
            f"{('YES' if read_present else '-'):<5} "
            f"{('YES' if write_present else '-'):<6} "
            f"{('YES' if ret_present else '-'):<10} "
            f"{('YES' if arg_present else '-'):<10}"
        )

    if gap_count > 0:
        print(
            f"\n[type_coverage_audit] {gap_count} type(s) appear in ObjectCalls "
            "but lack Variant marshal coverage (potential symmetric-gap bug)."
        )
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(main())
