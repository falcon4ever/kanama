#!/usr/bin/env python3
"""Audit builtin storage allocations against extension_api.json.

This guards the opaque builtin values that are easy to confuse with pointer
cells. On 64-bit Godot builds `String`, `Array`, and `Dictionary` are 8 bytes,
but every `Packed*Array` builtin and `Callable` are 16 bytes. Allocating those
as 8 bytes can appear to work until a constructor or return path writes past the
temporary storage.
"""

from __future__ import annotations

import json
import re
import sys
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
API_PATH = ROOT / "extension_api.json"
RUNTIME_FILES = [
    ROOT / "src/main/kotlin/binding/runtime/BuiltinTypes.kt",
    ROOT / "src/main/kotlin/binding/runtime/ObjectCalls.kt",
]
BUILTIN_TYPES = ROOT / "src/main/kotlin/binding/runtime/BuiltinTypes.kt"
# iOS GDExtension shim: hand-written C marshalling. Packed*Array ptrcall storage there is
# a raw stack slot, not a sized Kotlin allocation — so it needs its own size guardrail.
IOS_SHIM = ROOT / "ios/bootstrap/kanama_ios_shim.c"
IOS_PACKED_SIZE_MACRO = "KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE"
IOS_PACKED_STORAGE_MACRO = "KANAMA_IOS_PACKED_ARRAY_STORAGE"
# A Packed*Array marshalling helper definition at column 0 (lowercase C function name
# containing 'packed'; the uppercase macros and indented call sites don't match). Covers
# both return helpers (..._ret_packed_*_array) and arg helpers (..._with_packed_*_arg); the
# g_object_method_bind_ptrcall filter below excludes pass-by-pointer/cache helpers.
IOS_PACKED_FUN_RE = re.compile(
    r"^[A-Za-z_][\w ]*\*?\s*\b(kanama_ios_[a-z0-9_]*packed[a-z0-9_]*)\s*\(", re.M
)
IOS_PACKED_SIZE_DEF_RE = re.compile(
    rf"#define\s+{IOS_PACKED_SIZE_MACRO}\s+(\d+)"
)
C_COMMENT_RE = re.compile(r"/\*.*?\*/|//[^\n]*", re.S)


def strip_c_comments(text: str) -> str:
    return C_COMMENT_RE.sub(" ", text)

PACKED_TYPES = {
    "PackedByteArray",
    "PackedInt32Array",
    "PackedInt64Array",
    "PackedFloat32Array",
    "PackedFloat64Array",
    "PackedStringArray",
    "PackedVector2Array",
    "PackedVector3Array",
    "PackedColorArray",
    "PackedVector4Array",
}
CALLABLE_TYPES = {"Callable"}
OPAQUE_8_BYTE_TYPES = {"String", "StringName", "Array", "Dictionary"}

ALLOC_8_RE = re.compile(r"val\s+(?P<name>\w+)\s*=\s*arena\.allocate\(8L,\s*8L\)")
FUN_RE = re.compile(r"fun\s+(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*\(")


def find_function_end(content: str, start: int) -> int:
    brace = content.find("{", start)
    if brace == -1:
        return len(content)

    depth = 0
    in_string = False
    escaped = False
    for index in range(brace, len(content)):
        char = content[index]
        if in_string:
            if escaped:
                escaped = False
            elif char == "\\":
                escaped = True
            elif char == '"':
                in_string = False
            continue
        if char == '"':
            in_string = True
        elif char == "{":
            depth += 1
        elif char == "}":
            depth -= 1
            if depth == 0:
                return index + 1
    return len(content)


def float64_builtin_sizes() -> dict[str, int]:
    api = json.loads(API_PATH.read_text(encoding="utf-8"))
    for entry in api["builtin_class_sizes"]:
        if entry["build_configuration"] == "float_64":
            return {item["name"]: int(item["size"]) for item in entry["sizes"]}
    raise RuntimeError("extension_api.json has no float_64 builtin size table")


def audit_constants() -> list[str]:
    sizes = float64_builtin_sizes()
    errors: list[str] = []
    packed_sizes = {name: sizes.get(name) for name in PACKED_TYPES}
    wrong = {name: size for name, size in packed_sizes.items() if size != 16}
    if wrong:
        errors.append(f"extension_api.json unexpected float_64 packed array sizes: {wrong}")
    opaque_sizes = {name: sizes.get(name) for name in OPAQUE_8_BYTE_TYPES}
    wrong_opaque = {name: size for name, size in opaque_sizes.items() if size != 8}
    if wrong_opaque:
        errors.append(f"extension_api.json unexpected float_64 opaque builtin sizes: {wrong_opaque}")

    content = BUILTIN_TYPES.read_text(encoding="utf-8")
    if "const val PACKED_ARRAY_SIZE: Long = 16L" not in content:
        errors.append("BuiltinTypes.PACKED_ARRAY_SIZE must be 16L for 64-bit Godot packed arrays")
    if "fun allocatePackedArray(arena: Arena): MemorySegment" not in content:
        errors.append("BuiltinTypes.allocatePackedArray helper is missing")
    callable_sizes = {name: sizes.get(name) for name in CALLABLE_TYPES}
    wrong_callable = {name: size for name, size in callable_sizes.items() if size != 16}
    if wrong_callable:
        errors.append(f"extension_api.json unexpected float_64 Callable size: {wrong_callable}")
    if "const val CALLABLE_SIZE: Long = 16L" not in content:
        errors.append("BuiltinTypes.CALLABLE_SIZE must be 16L for 64-bit Godot Callable")
    if "fun allocateCallable(arena: Arena): MemorySegment" not in content:
        errors.append("BuiltinTypes.allocateCallable helper is missing")
    return errors


def packed_uses_var(body: str, var_name: str) -> bool:
    escaped = re.escape(var_name)
    return any(
        re.search(pattern, body)
        for pattern in (
            rf"\binitPacked[A-Za-z0-9_]*Array\(\s*{escaped}\b",
            rf"\breadPacked[A-Za-z0-9_]*Array\(\s*{escaped}\b",
            rf"destroyTyped\(\s*VariantType\.PACKED_[A-Z0-9_]+,\s*{escaped}\b",
            rf"variantFromType\(\s*VariantType\.PACKED_[A-Z0-9_]+\)\.invoke\([^,\n]+,\s*{escaped}\b",
            rf"variantToType\(\s*VariantType\.PACKED_[A-Z0-9_]+\)\.invoke\(\s*{escaped}\b",
        )
    )


def callable_uses_var(body: str, var_name: str) -> bool:
    escaped = re.escape(var_name)
    return any(
        re.search(pattern, body)
        for pattern in (
            rf"\binitCallable\(\s*{escaped}\b",
            rf"destroyTyped\(\s*VariantType\.CALLABLE,\s*{escaped}\b",
            rf"variantFromType\(\s*VariantType\.CALLABLE\)\.invoke\([^,\n]+,\s*{escaped}\b",
            rf"variantToType\(\s*VariantType\.CALLABLE\)\.invoke\(\s*{escaped}\b",
        )
    )


def audit_file(path: Path) -> list[str]:
    content = path.read_text(encoding="utf-8")
    errors: list[str] = []
    for match in FUN_RE.finditer(content):
        body_start = match.start()
        body_end = find_function_end(content, match.end())
        body = content[body_start:body_end]
        base_line = content.count("\n", 0, body_start)
        for alloc in ALLOC_8_RE.finditer(body):
            var_name = alloc.group("name")
            local_use = body[alloc.start() : min(len(body), alloc.start() + 900)]
            if not packed_uses_var(local_use, var_name):
                if not callable_uses_var(local_use, var_name):
                    continue
            line = base_line + body.count("\n", 0, alloc.start()) + 1
            rel = path.relative_to(ROOT)
            if packed_uses_var(local_use, var_name):
                errors.append(
                    f"{rel}:{line}: {var_name} is used as Packed*Array storage but allocates 8 bytes; "
                    "use BuiltinTypes.allocatePackedArray(arena)"
                )
            else:
                errors.append(
                    f"{rel}:{line}: {var_name} is used as Callable storage but allocates 8 bytes; "
                    "use BuiltinTypes.allocateCallable(arena)"
                )
    return errors


def audit_builtin_types_specifics() -> list[str]:
    content = BUILTIN_TYPES.read_text(encoding="utf-8")
    errors: list[str] = []
    for variant_type in ("ARRAY", "DICTIONARY"):
        marker = f"VariantType.{variant_type} ->"
        start = content.find(marker)
        if start == -1:
            continue
        # Bound the arm by the next `VariantType.<X> ->` arm, indent-agnostic: ktfmt
        # reindents the `when` block, so a hardcoded leading-space count would miss the
        # delimiter and let the window bleed into the following Packed*Array arms.
        next_arm = re.compile(r"\n[ \t]*VariantType\.\w+[ \t]*->").search(
            content, start + len(marker)
        )
        end = next_arm.start() if next_arm else -1
        body = content[start : end if end != -1 else start + 800]
        if "allocatePackedArray(arena)" in body:
            line = content.count("\n", 0, start) + 1
            errors.append(
                f"src/main/kotlin/binding/runtime/BuiltinTypes.kt:{line}: "
                f"VariantType.{variant_type} storage is 8 bytes, not PACKED_ARRAY_SIZE"
            )
    return errors


def audit_ios_shim() -> list[str]:
    """Guard the iOS C shim: every Packed*Array ptrcall storage slot must be sized by
    KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE (16 on 64-bit), not a bare 8-byte cell. A too-small
    slot overflows the ptrcall return-encode and crashes reading CowData metadata on device."""
    if not IOS_SHIM.exists():
        return []
    content = IOS_SHIM.read_text(encoding="utf-8")
    errors: list[str] = []
    rel = IOS_SHIM.relative_to(ROOT)

    packed_abi = float64_builtin_sizes().get("PackedInt32Array")
    size_def = IOS_PACKED_SIZE_DEF_RE.search(content)
    if size_def is None:
        errors.append(f"{rel}: #define {IOS_PACKED_SIZE_MACRO} is missing")
    elif int(size_def.group(1)) != packed_abi:
        errors.append(
            f"{rel}: {IOS_PACKED_SIZE_MACRO} is {size_def.group(1)}, expected {packed_abi} "
            "(float_64 builtin_class_sizes — all Packed*Array are 16 on 64-bit)"
        )
    if f"#define {IOS_PACKED_STORAGE_MACRO}(" not in content:
        errors.append(f"{rel}: {IOS_PACKED_STORAGE_MACRO} storage macro is missing")

    for match in IOS_PACKED_FUN_RE.finditer(content):
        name = match.group(1)
        # Strip comments so a comment that merely names the macro can't satisfy the check.
        body = strip_c_comments(content[match.start() : find_function_end(content, match.end())])
        # Only ptrcall helpers declare a local Packed*Array storage slot (they invoke
        # g_object_method_bind_ptrcall). Pass-by-pointer builders (init_*_packed_*, taking a
        # GDExtensionTypePtr out/ret that Godot already sized) don't, and must not be flagged.
        if "g_object_method_bind_ptrcall" not in body:
            continue
        if f"{IOS_PACKED_STORAGE_MACRO}(" not in body and IOS_PACKED_SIZE_MACRO not in body:
            line = content.count("\n", 0, match.start()) + 1
            errors.append(
                f"{rel}:{line}: {name} ptrcalls a Packed*Array but does not size its storage "
                f"via {IOS_PACKED_STORAGE_MACRO}/{IOS_PACKED_SIZE_MACRO} — a bare 8-byte slot "
                "overflows the return-encode (see the macro comment)"
            )
    return errors


def main() -> int:
    errors = audit_constants()
    for path in RUNTIME_FILES:
        errors.extend(audit_file(path))
    errors.extend(audit_builtin_types_specifics())
    errors.extend(audit_ios_shim())

    if errors:
        print("[builtin_storage_size_audit] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print("[builtin_storage_size_audit] PASS")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
