#!/usr/bin/env python3
"""Audit conservative wrapper-generator call-shape policy.

The generator's CALL_SHAPES table is the gate that promotes methods from
extension_api.json into Kotlin wrappers. This audit keeps that table tied to
strict runtime helpers for the shapes that are easiest to widen unsafely:

- generic Variant values must use the strict Variant marshaller,
- generic Array/Dictionary values must use the explicit builtin initializers,
- Packed*Array values must use the 16-byte packed storage helpers,
- Callable must not be exposed through the generic generator table,
- every RefCounted wrapper site names its ownership (task 132 D1): see
  `refcounted_ownership_problems`.
"""

from __future__ import annotations

import json
import re
import sys
from pathlib import Path

from api_wrapper_candidates import CALL_SHAPES
from wrapper_model import exact_abi_kind, logical_type, storage_abi_kind, value_policy


ROOT = Path(__file__).resolve().parents[1]
OBJECT_CALLS = ROOT / "src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt"

UNSIGNED_POLICY = {
    ("int", "uint8"): ("uint8", "int32", "int32", "Int"),
    ("int", "uint16"): ("uint16", "int32", "int32", "Int"),
    ("int", "uint32"): ("uint32", "uint32", "uint32", "Long"),
    ("int", "uint64"): ("uint64", "int64", "int64", "Long"),
}

PACKED_VARIANT_TYPES = {
    "PackedByteArray": "PACKED_BYTE_ARRAY",
    "PackedStringArray": "PACKED_STRING_ARRAY",
    "PackedInt32Array": "PACKED_INT32_ARRAY",
    "PackedInt64Array": "PACKED_INT64_ARRAY",
    "PackedFloat32Array": "PACKED_FLOAT32_ARRAY",
    "PackedFloat64Array": "PACKED_FLOAT64_ARRAY",
    "PackedVector2Array": "PACKED_VECTOR2_ARRAY",
    "PackedVector3Array": "PACKED_VECTOR3_ARRAY",
    "PackedColorArray": "PACKED_COLOR_ARRAY",
    "PackedVector4Array": "PACKED_VECTOR4_ARRAY",
}

PACKED_INIT_MARKERS = {
    "PackedByteArray": "initPackedByteArray",
    "PackedStringArray": "initPackedStringArray",
    "PackedInt32Array": "initPackedInt32Array",
    "PackedInt64Array": "initPackedInt64Array",
    "PackedFloat32Array": "initPackedFloat32Array",
    "PackedFloat64Array": "initPackedFloat64Array",
    "PackedVector2Array": "initPackedVector2Array",
    "PackedVector3Array": "initPackedVector3Array",
    "PackedColorArray": "initPackedColorArray",
    "PackedVector4Array": "initPackedVector4Array",
}

PACKED_READ_MARKERS = {
    "PackedByteArray": "readPackedByteArray",
    "PackedStringArray": "readPackedStringArray",
    "PackedInt32Array": "readPackedInt32Array",
    "PackedInt64Array": "readPackedInt64Array",
    "PackedFloat32Array": "readPackedFloat32Array",
    "PackedFloat64Array": "readPackedFloat64Array",
    "PackedVector2Array": "readPackedVector2Array",
    "PackedVector3Array": "readPackedVector3Array",
    "PackedColorArray": "readPackedColorArray",
    "PackedVector4Array": "readPackedVector4Array",
}

DYNAMIC_TYPED_OBJECT_ARRAY_HELPERS = {
    "ptrcallNoArgsRetTypedObjectList": "ptrcallNoArgsRetObjectList",
    "ptrcallWithBoolArgRetTypedObjectList": "ptrcallWithBoolArgRetObjectList",
    "ptrcallWithRIDArgRetTypedObjectList": "ptrcallWithRIDArgRetObjectList",
    "ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList": "ptrcallWithRIDRIDListVector2iArgsRetObjectList",
    "ptrcallWithStringAndBoolArgRetTypedObjectList": "ptrcallWithStringAndBoolArgRetObjectList",
    "ptrcallWithStringNameArgRetTypedObjectList": "ptrcallWithStringNameArgRetObjectList",
    "ptrcallWithStringTwoIntArgsRetTypedObjectList": "ptrcallWithStringTwoIntArgsRetObjectList",
    "ptrcallWithObjectListIntArgsRetTypedObjectList": "ptrcallWithObjectListIntArgsRetObjectList",
    "ptrcallWithThreeIntTwoBoolArgsRetTypedObjectList": "ptrcallWithThreeIntTwoBoolArgsRetObjectList",
    "ptrcallWithThreeIntBoolDoubleBoolArgsRetTypedObjectList": (
        "ptrcallWithThreeIntBoolDoubleBoolArgsRetObjectList"
    ),
}

DYNAMIC_TYPED_OBJECT_ARRAY_HELPERS_BY_OBJECT = {
    untyped: typed for typed, untyped in DYNAMIC_TYPED_OBJECT_ARRAY_HELPERS.items()
}

DYNAMIC_TYPED_OBJECT_ARRAY_ARG_HELPERS = {
    "ptrcallWithObjectListArg",
    "ptrcallWithObjectListArgRetLong",
    "ptrcallWithObjectListRIDUInt32ArgsRetRID",
    "ptrcallWithObjectListUInt32ArgsRetLong",
    "ptrcallWithObjectListLongArgsRetRID",
    "ptrcallWithObjectListIntArgsRetObjectList",
    "ptrcallWithObjectListObjectCallableArgsRetObject",
    "ptrcallWithRIDListObjectListUInt32ArgsRetRID",
    "ptrcallWithRIDListObjectListLongUInt32ArgsRetRID",
    "ptrcallWithRIDObjectListArgsRetRID",
    "ptrcallWithRIDObjectListObjectArgsRetBool",
    "ptrcallWithRIDUInt32ObjectListArgsRetRID",
    "ptrcallWithRIDThreeLongFourObjectLongUInt32ObjectListArgsRetRID",
    "ptrcallWithStringAndObjectListArgs",
    "ptrcallWithRIDAndObjectListArgs",
    "ptrcallWithRIDAndObjectListArgsRetLong",
    "ptrcallWithRIDObjectListTwoObjectArgs",
    "ptrcallWithLongThreeIntBoolObjectListArgsRetLong",
    "ptrcallWithLongThreeIntBoolObjectListArgsRetRID",
    "ptrcallWithTwoObjectListUInt32ArgsRetLong",
    "ptrcallWithThreeObjectListUInt32ArgsRetRID",
    "ptrcallWithRect2iTwoObjectListColorIntObjectArgs",
    "ptrcallWithObjectListTransform3DListBoolArgsRetObject",
}

DYNAMIC_TYPED_RID_ARRAY_ARG_HELPERS = {
    "ptrcallWithRIDListLongUInt32ArgsRetRID",
    "ptrcallWithRIDListRect2iRIDColorRIDListIntArgs",
    "ptrcallWithUInt32LongRIDListPackedInt64ListArgsRetRID",
}

DYNAMIC_DICTIONARY_RETURN_HELPERS = {
    "ptrcallWithDictionaryDictionaryListArgsRetDictionary",
}

DYNAMIC_RAW_POINTER_HELPERS = {
    "ptrcallWithConstVoidPtrArg",
    "ptrcallWithConstVoidPtrArgRetTransform3D",
    "ptrcallWithStringConstGDExtensionInitializationFunctionPtrArgsRetLong",
}

DYNAMIC_CALLABLE_ARG_HELPERS = {
    "ptrcallWithCallableArg",
    "ptrcallWithCallableArgRetBool",
    "ptrcallWithCallableArgRetObject",
    "ptrcallWithCallableBoolStringArgsRetLong",
    "ptrcallWithCallableIntArgs",
    "ptrcallWithCallableLongArgsRetLong",
    "ptrcallWithCallableStringNameListArgs",
    "ptrcallWithCallableStringNameListObjectArgs",
    "ptrcallWithCallableStringNameTwoStringStringNameListArgs",
    "ptrcallWithCallableTwoIntBoolStringArgsRetLong",
    "ptrcallWithCallableVariantVariantDoubleArgsRetObject",
    "ptrcallWithIntCallableArgs",
    "ptrcallWithLongCallableArgs",
    "ptrcallWithLongCallableArgsRetObject",
    "ptrcallWithObjectBoolTwoCallableArgsRetLong",
    "ptrcallWithObjectCallableArgs",
    "ptrcallWithObjectCallablePackedInt32ListStringArgs",
    "ptrcallWithObjectCallableStringArgs",
    "ptrcallWithObjectListObjectCallableArgsRetObject",
    "ptrcallWithObjectRIDCallableArgsRetObject",
    "ptrcallWithObjectStringCallableArgsRetInt",
    "ptrcallWithRIDBoolRect2TwoCallableArgs",
    "ptrcallWithRIDCallableArgs",
    "ptrcallWithRIDCallableTwoUInt32ArgsRetLong",
    "ptrcallWithRIDCallableVariantArgs",
    "ptrcallWithRIDIntCallableArgs",
    "ptrcallWithRIDLongCallableArgs",
    "ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt",
    "ptrcallWithRIDPackedInt64ListObjectCallableArgsRetObject",
    "ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt",
    "ptrcallWithRIDStringTwoIntTwoCallableVariantLongIntArgsRetInt",
    "ptrcallWithRIDTwoCallableArgs",
    "ptrcallWithStringCallableArgs",
    "ptrcallWithStringCallableObjectArgs",
    "ptrcallWithStringIntCallableArgs",
    "ptrcallWithStringNameAndCallableArgs",
    "ptrcallWithStringNameAndCallableArgsRetBool",
    "ptrcallWithStringNameCallableArrayLongArgs",
    "ptrcallWithStringNameCallableAndUInt32ArgsRetLong",
    "ptrcallWithStringTwoCallableArgs",
    "ptrcallWithThreeCallableArgs",
    "ptrcallWithThreeStringBoolLongPackedStringListCallableIntArgsRetLong",
    "ptrcallWithThreeStringCallableArgsRetLong",
    "ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong",
    "ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt",
    "ptrcallWithTwoStringCallableStringArgs",
    "ptrcallWithThreeObjectCallableArgs",
    "ptrcallWithTwoCallableArgs",
    "ptrcallWithTwoObjectCallableArgs",
    "ptrcallWithTwoStringPackedStringListCallableArgsRetLong",
    "ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt",
    "ptrcallWithTwoStringTwoIntTwoCallableVariantLongIntArgsRetInt",
    "ptrcallWithRIDUInt32CallableArgsRetLong",
}

DYNAMIC_CALLABLE_RETURN_HELPERS = {
    "ptrcallNoArgsRetCallable",
    "ptrcallWithIntArgRetCallable",
    "ptrcallWithRIDArgRetCallable",
    "ptrcallWithRIDIntArgsRetCallable",
    "ptrcallWithStringIntArgsRetCallable",
}

RAW_POINTER_POLICY = {
    "const void*": ("const_void_pointer", "raw_pointer", "ConstVoidPtr", "MemorySegment"),
    "const GDExtensionInitializationFunction*": (
        "const_gdextension_initialization_function_pointer",
        "raw_pointer",
        "ConstGDExtensionInitializationFunctionPtr",
        "MemorySegment",
    ),
}


def find_matching(content: str, start: int, open_char: str, close_char: str) -> int:
    depth = 1
    in_string = False
    escaped = False
    for index in range(start, len(content)):
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
        elif char == open_char:
            depth += 1
        elif char == close_char:
            depth -= 1
            if depth == 0:
                return index + 1
    return len(content)


def find_signature_end(content: str, start: int) -> int:
    return find_matching(content, start, "(", ")")


def find_body_end(content: str, start: int) -> int:
    return find_matching(content, start, "{", "}")


def find_function_text(content: str, name: str) -> tuple[int, str] | None:
    match = re.search(rf"\bfun\s+(?:<[^>]+>\s+)?{re.escape(name)}\s*\(", content)
    if match is None:
        return None

    line = content.count("\n", 0, match.start()) + 1
    signature_open = content.find("(", match.start())
    signature_end = find_signature_end(content, signature_open + 1)
    next_equals = content.find("=", signature_end)
    next_brace = content.find("{", signature_end)
    if next_brace != -1 and (next_equals == -1 or next_brace < next_equals):
        return line, content[match.start() : find_body_end(content, next_brace + 1)]
    if next_equals != -1:
        expression_end = content.find("\n\n", next_equals)
        if expression_end == -1:
            expression_end = len(content)
        return line, content[match.start() : expression_end]
    return line, content[match.start() : signature_end]


def is_direct_packed(type_name: str) -> bool:
    return type_name in PACKED_VARIANT_TYPES


def audit_shape(function_text: str, args: tuple[str, ...], ret: str) -> list[str]:
    errors: list[str] = []
    normalized_text = function_text.lower().replace("_", "")

    if "uint32" in args and "BuiltinTypes.requireUInt32(" not in function_text:
        errors.append("uint32 argument helper does not range-check through BuiltinTypes.requireUInt32")

    if ret == "uint32":
        if "JAVA_INT" not in function_text:
            errors.append("uint32 return helper does not use a 32-bit return slot")
        if "0xffffffffl" not in normalized_text:
            errors.append("uint32 return helper does not widen through an unsigned 32-bit mask")

    if "Variant" in args:
        if "initVariantCell(" not in function_text and "BuiltinTypes.initVariantFromAny(" not in function_text:
            errors.append("Variant argument is not initialized through the strict Variant marshaller")
        if "BuiltinTypes.destroyVariant(" not in function_text:
            errors.append("Variant argument helper does not destroy initialized Variant storage")

    if ret == "Variant":
        if "readVariantReturn(" not in function_text and "BuiltinTypes.readVariantScalar(" not in function_text:
            errors.append("Variant return is not decoded through the strict scalar Variant reader")
        if "BuiltinTypes.destroyVariant(" not in function_text and "readVariantReturn(" not in function_text:
            errors.append("Variant return helper does not destroy returned Variant storage")

    if "Array" in args:
        if "BuiltinTypes.initArray(" not in function_text:
            errors.append("generic Array argument is not initialized through BuiltinTypes.initArray")
        if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in function_text:
            errors.append("generic Array argument helper does not destroy Array storage")

    if ret == "Array":
        if (
            "BuiltinTypes.readArrayScalars" not in function_text
            and "BuiltinTypes::readArrayScalars" not in function_text
        ):
            errors.append("generic Array return is not decoded through readArrayScalars")
        if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in function_text and "callArrayReturn(" not in function_text:
            errors.append("generic Array return helper does not destroy Array storage")

    if "TypedRIDArray" in args:
        if "BuiltinTypes.initArrayOfRids(" not in function_text:
            errors.append("TypedRIDArray argument is not initialized through BuiltinTypes.initArrayOfRids")
        if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in function_text:
            errors.append("TypedRIDArray argument helper does not destroy Array storage")

    if ret == "TypedRIDArray":
        if "BuiltinTypes.readArrayRids" not in function_text and "BuiltinTypes::readArrayRids" not in function_text:
            errors.append("TypedRIDArray return is not decoded through readArrayRids")
        if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in function_text and "callArrayReturn(" not in function_text:
            errors.append("TypedRIDArray return helper does not destroy Array storage")

    if "Dictionary" in args:
        if "BuiltinTypes.initDictionary(" not in function_text:
            errors.append("Dictionary argument is not initialized through BuiltinTypes.initDictionary")
        if "BuiltinTypes.destroyTyped(VariantType.DICTIONARY" not in function_text:
            errors.append("Dictionary argument helper does not destroy Dictionary storage")

    if ret == "Dictionary":
        if "BuiltinTypes.readDictionaryScalars" not in function_text:
            errors.append("Dictionary return is not decoded through readDictionaryScalars")
        if "BuiltinTypes.destroyTyped(VariantType.DICTIONARY" not in function_text:
            errors.append("Dictionary return helper does not destroy Dictionary storage")

    for arg in args:
        if not is_direct_packed(arg):
            continue
        variant_type = PACKED_VARIANT_TYPES[arg]
        if "BuiltinTypes.allocatePackedArray(" not in function_text and "packedArrayRet" not in function_text:
            errors.append(f"{arg} argument helper does not use 16-byte packed storage")
        if PACKED_INIT_MARKERS[arg] not in function_text:
            errors.append(f"{arg} argument helper does not initialize through {PACKED_INIT_MARKERS[arg]}")
        if f"BuiltinTypes.destroyTyped(VariantType.{variant_type}" not in function_text:
            errors.append(f"{arg} argument helper does not destroy {variant_type} storage")

    if is_direct_packed(ret):
        variant_type = PACKED_VARIANT_TYPES[ret]
        if "BuiltinTypes.allocatePackedArray(" not in function_text and "packedArrayRet" not in function_text:
            errors.append(f"{ret} return helper does not use 16-byte packed storage")
        if PACKED_READ_MARKERS[ret] not in function_text:
            errors.append(f"{ret} return helper does not decode through {PACKED_READ_MARKERS[ret]}")
        if f"BuiltinTypes.destroyTyped(VariantType.{variant_type}" not in function_text:
            errors.append(f"{ret} return helper does not destroy {variant_type} storage")

    return errors


# Task 131 (S5 + review 2): every ptrcall-returned object Array is destroyed right after the
# decode, so the decode must be the OWNING one (RefCounted elements retained before the destroy).
# Matched exactly: `readArrayObjectsOwned` passes, a bare `readArrayObjects` call or reference fails.
BARE_ARRAY_OBJECTS_DECODE = re.compile(r"\breadArrayObjects\b(?!Owned)")
# A typed helper that maps an untyped helper's wrappers (`.mapNotNull { wrapper(it.segment) }`)
# leaks: the owned +1 sits on the discarded untyped wrapper. Typed helpers decode directly.
TYPED_MAP_FROM_UNTYPED = re.compile(r"wrapper\(it\.segment\)")


def audit_owned_array_decodes(content: str) -> list[str]:
    errors: list[str] = []
    for number, line in enumerate(content.splitlines(), start=1):
        if BARE_ARRAY_OBJECTS_DECODE.search(line):
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{number}: decodes a returned "
                "object Array with readArrayObjects; use BuiltinTypes.readArrayObjectsOwned (the "
                "Array is destroyed after the decode, task 131 S5)"
            )
        if TYPED_MAP_FROM_UNTYPED.search(line):
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{number}: maps an untyped "
                "object list through `wrapper(it.segment)`; decode directly with "
                "BuiltinTypes.readArrayObjectsOwned(..., wrapper)"
            )
    return errors


def audit_typed_object_array_helpers(content: str) -> list[str]:
    errors: list[str] = audit_owned_array_decodes(content)
    for typed_helper, object_helper in DYNAMIC_TYPED_OBJECT_ARRAY_HELPERS.items():
        object_result = find_function_text(content, object_helper)
        if object_result is None:
            errors.append(f"dynamic typed object-array helper {typed_helper} references missing {object_helper}")
            continue
        object_line, object_text = object_result
        typed_result = find_function_text(content, typed_helper)
        if typed_result is None:
            errors.append(f"dynamic typed object-array helper {typed_helper} is missing")
            continue
        typed_line, typed_text = typed_result
        # The untyped helper either delegates to the typed one or decodes owned itself.
        if typed_helper not in object_text and "readArrayObjectsOwned" not in object_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{object_line}: "
                f"{object_helper} neither delegates to {typed_helper} nor decodes through "
                "readArrayObjectsOwned",
            )
        if typed_helper not in object_text and (
            "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in object_text
            and "callArrayReturn(" not in object_text
        ):
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{object_line}: "
                f"{object_helper} does not destroy Array storage",
            )
        if "readArrayObjectsOwned(" not in typed_text or "wrapper)" not in typed_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{typed_line}: "
                f"{typed_helper} does not decode directly through nullable typed wrappers "
                f"with BuiltinTypes.readArrayObjectsOwned",
            )
        if "callArrayReturn(" not in typed_text and "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in typed_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{typed_line}: "
                f"{typed_helper} does not destroy Array storage",
            )
    for object_arg_helper in sorted(DYNAMIC_TYPED_OBJECT_ARRAY_ARG_HELPERS):
        result = find_function_text(content, object_arg_helper)
        if result is None:
            errors.append(f"dynamic typed object-array argument helper {object_arg_helper} is missing")
            continue
        line, function_text = result
        # An untyped list-returning helper that delegates to its typed twin (task 131 review 2) is
        # checked through that twin, which holds the body.
        typed_twin = DYNAMIC_TYPED_OBJECT_ARRAY_HELPERS_BY_OBJECT.get(object_arg_helper)
        if typed_twin is not None and typed_twin in function_text:
            twin = find_function_text(content, typed_twin)
            if twin is not None:
                line, function_text = twin
        if "BuiltinTypes.initArrayOfObjects(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{object_arg_helper} does not initialize through initArrayOfObjects",
            )
        if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{object_arg_helper} does not destroy Array storage",
            )
        if "UInt32" in object_arg_helper and "BuiltinTypes.requireUInt32(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{object_arg_helper} does not range-check UInt32 arguments",
            )
        if "RIDList" in object_arg_helper:
            if "BuiltinTypes.initArrayOfRids(" not in function_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                    f"{object_arg_helper} does not initialize RID lists through initArrayOfRids",
                )
            if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in function_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                    f"{object_arg_helper} does not destroy RID Array storage",
                )
        if "PackedInt64List" in object_arg_helper:
            if "BuiltinTypes.allocatePackedArray(" not in function_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                    f"{object_arg_helper} does not allocate packed storage",
                )
            if "BuiltinTypes.initPackedInt64Array(" not in function_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                    f"{object_arg_helper} does not initialize PackedInt64Array storage",
                )
            if "BuiltinTypes.destroyTyped(VariantType.PACKED_INT64_ARRAY" not in function_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                    f"{object_arg_helper} does not destroy PackedInt64Array storage",
                )
    for rid_arg_helper in sorted(DYNAMIC_TYPED_RID_ARRAY_ARG_HELPERS):
        result = find_function_text(content, rid_arg_helper)
        if result is None:
            errors.append(f"dynamic typed RID-array argument helper {rid_arg_helper} is missing")
            continue
        line, function_text = result
        if "BuiltinTypes.initArrayOfRids(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{rid_arg_helper} does not initialize RID lists through initArrayOfRids",
            )
        if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{rid_arg_helper} does not destroy RID Array storage",
            )
        if "UInt32" in rid_arg_helper and "BuiltinTypes.requireUInt32(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{rid_arg_helper} does not range-check UInt32 arguments",
            )
        if "PackedInt64List" in rid_arg_helper:
            if "BuiltinTypes.allocatePackedArray(" not in function_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                    f"{rid_arg_helper} does not allocate packed storage",
                )
            if "BuiltinTypes.initPackedInt64Array(" not in function_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                    f"{rid_arg_helper} does not initialize PackedInt64Array storage",
                )
            if "BuiltinTypes.destroyTyped(VariantType.PACKED_INT64_ARRAY" not in function_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                    f"{rid_arg_helper} does not destroy PackedInt64Array storage",
                )
    for dictionary_helper in sorted(DYNAMIC_DICTIONARY_RETURN_HELPERS):
        result = find_function_text(content, dictionary_helper)
        if result is None:
            errors.append(f"dynamic Dictionary-return helper {dictionary_helper} is missing")
            continue
        line, function_text = result
        if "BuiltinTypes.initDictionary(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{dictionary_helper} does not initialize Dictionary arguments through initDictionary",
            )
        if "BuiltinTypes.initArrayOfDictionaries(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{dictionary_helper} does not initialize typed Dictionary arrays through initArrayOfDictionaries",
            )
        if "BuiltinTypes.readDictionaryScalars(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{dictionary_helper} does not decode through readDictionaryScalars",
            )
        if "BuiltinTypes.destroyTyped(VariantType.DICTIONARY" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{dictionary_helper} does not destroy Dictionary storage",
            )
        if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{dictionary_helper} does not destroy typed Dictionary Array storage",
            )
    for raw_pointer_helper in sorted(DYNAMIC_RAW_POINTER_HELPERS):
        result = find_function_text(content, raw_pointer_helper)
        if result is None:
            errors.append(f"dynamic raw pointer helper {raw_pointer_helper} is missing")
            continue
        line, function_text = result
        if "MemorySegment" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{raw_pointer_helper} does not expose raw pointers as MemorySegment",
            )
        if "arena.allocate(ADDRESS)" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{raw_pointer_helper} does not allocate ADDRESS storage for raw pointer arguments",
            )
        if ".set(ADDRESS, 0," not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{raw_pointer_helper} does not write raw pointer arguments through ADDRESS slots",
            )
    for callable_helper in sorted(DYNAMIC_CALLABLE_ARG_HELPERS):
        result = find_function_text(content, callable_helper)
        if result is None:
            errors.append(f"dynamic Callable argument helper {callable_helper} is missing")
            continue
        line, function_text = result
        if "BuiltinTypes.allocateCallable(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} does not allocate Callable storage through BuiltinTypes.allocateCallable",
            )
        if "BuiltinTypes.initCallable(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} does not initialize Callable storage through BuiltinTypes.initCallable",
            )
        if "BuiltinTypes.destroyTyped(VariantType.CALLABLE" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} does not destroy Callable storage",
            )
        if "UInt32" in callable_helper and "BuiltinTypes.requireUInt32(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} does not range-check uint32 arguments",
            )
        expected_callable_count = 3 if "ThreeCallable" in callable_helper else 2 if "TwoCallable" in callable_helper else 1
        init_count = function_text.count("BuiltinTypes.initCallable(")
        destroy_count = function_text.count("BuiltinTypes.destroyTyped(VariantType.CALLABLE")
        if init_count < expected_callable_count:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} initializes {init_count} Callable values, expected at least {expected_callable_count}",
            )
        if destroy_count < expected_callable_count:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} destroys {destroy_count} Callable values, expected at least {expected_callable_count}",
            )
    for callable_helper in sorted(DYNAMIC_CALLABLE_RETURN_HELPERS):
        result = find_function_text(content, callable_helper)
        if result is None:
            errors.append(f"dynamic Callable return helper {callable_helper} is missing")
            continue
        line, function_text = result
        if "GodotCallable?" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} does not expose nullable GodotCallable return",
            )
        if "BuiltinTypes.allocateCallable(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} does not allocate Callable return storage",
            )
        if "BuiltinTypes.readCallable(" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} does not decode through BuiltinTypes.readCallable",
            )
        if "BuiltinTypes.destroyTyped(VariantType.CALLABLE" not in function_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{callable_helper} does not destroy Callable return storage",
            )
    return errors


# ---------------------------------------------------------------------------------------------
# Task 132 D1: ownership is a constructor fact. A RefCounted wrapper class has no `wrap`; every
# site that builds one says whether the wrapper owns a +1 or borrows. The rule is the one
# `docs/game-dev/godot-api.md` "Resource Ownership" states:
#   - OWNED by adopting a +1 handed over: a RefCounted-typed ptrcall return
#     (`X.wrapOwned(ObjectCalls.*RetObject(...))`, the self-return collapse's `X.wrapOwned(ret)`),
#     the constructing +1 (`create()`, a hand-written loader or decode): `RefCounted.owned(...)`;
#   - OWNED by taking a +1: the `from*` downcasts and the owned decodes (`RefCounted.retained(...)`,
#     `retainForKotlinWrapper()`), so a view kept in a field keeps the object alive;
#   - BORROWED: `fromHandle` (its body is `wrapBorrowed(handle.segment)` or the constructor), and
#     the element callback of a typed Array return (`X::wrapBorrowed`): the helper decodes through
#     `readArrayObjectsOwned` (desktop) / `ownedListElement` (iOS), which retain each element and
#     make its wrapper owned, so an `X::wrapOwned` callback there would register twice.
# Checked over every wrapper source the tree compiles: the generated shared tree, the
# per-platform generated and hand-shaped files, and the runtime helpers and decodes. A positive
# check walks extension_api.json: every RefCounted-typed method return that a wrapper emits must
# reach `wrapOwned`. RED_RUNS below are known-bad snippets the rules must each reject; the gate
# runs them first, so a rule that stops matching fails loudly instead of passing everything.
WRAPPER_SOURCE_DIRS = (
    ROOT / "src/commonMain/kotlin/net/multigesture/kanama/api",
    ROOT / "src/jvmMain/kotlin/net/multigesture/kanama/api",
    ROOT / "src/iosMain/kotlin/net/multigesture/kanama/api",
    ROOT / "src/jvmMain/kotlin/binding",
    ROOT / "src/iosMain/kotlin/net/multigesture/kanama/binding/runtime",
    ROOT / "src/iosMain/kotlin/net/multigesture/kanama/ios",
)
EXTENSION_API = ROOT / "extension_api.json"

# Borrowed constructions that are correct, each with its reason.
ALLOWED_BORROWED_CONSTRUCTIONS = {
    # The iOS self-test builds raw probe objects it destroys or hands to an owning helper itself.
    ("ObjectCalls.kt", "listOf(RefCounted(GodotHandle(typedNodeA)), RefCounted(GodotHandle(typedNodeB)))"),
    ("ObjectCalls.kt", "RefCounted(GodotHandle(it))"),
    ("ObjectCalls.kt", 'net.multigesture.kanama.api.Resource(GodotHandle(ObjectCalls.constructObject("Resource")))'),
}


def _api_parents() -> dict[str, str | None]:
    api = json.loads(EXTENSION_API.read_text(encoding="utf-8"))
    return {cls["name"]: cls.get("inherits") for cls in api["classes"]}


def refcounted_classes() -> set[str]:
    parents = _api_parents()

    def is_refcounted(name: str | None) -> bool:
        while name:
            if name == "RefCounted":
                return True
            name = parents.get(name)
        return False

    # The hand-shaped FileAccess/DirAccess handle classes stand for Godot's FileAccess/DirAccess.
    return {name for name in parents if is_refcounted(name)} | {"RefCounted", *REFCOUNTED_ALIASES}


# Hand-written RefCounted wrapper classes with no Godot class of their own name.
REFCOUNTED_ALIASES = {"FileAccessHandle": "FileAccess", "DirAccessHandle": "DirAccess"}


WRAP_CALL = re.compile(r"(?<![\w])(?:(\w+)\.)?(wrap|wrapOwned|wrapBorrowed)\(\s*")
WRAP_REFERENCE = re.compile(r"(?<![\w.])(\w+)::(wrap|wrapOwned|wrapBorrowed)\b")
# `X(GodotHandle(...))`, `X(value.handle)`, `X(it.handle)`, `X(handle)` with X a wrapper class.
CONSTRUCTION = re.compile(
    r"(?<![\w])(?:net\.multigesture\.kanama\.api\.)?([A-Z]\w*)\((GodotHandle\(|[\w.]*\bhandle\))"
)
OWNED_SOURCE = re.compile(r"(ObjectCalls\.\w*RetObject\(|ret\))")
# Runtime functions whose result is owned (task 132 round 2): every one takes or adopts its +1.
OWNED_RESULT_FUNCTION = re.compile(
    r"^(?:\w*Owned|ownedListElement|instantiateResourceScript|readVariant\w*Retained\w*)$"
)
OWNED_MARKERS = ("owned(", "retained(", "retainForKotlinWrapper", "referenceBind", "owned = true")
# markOwned is the marker itself, not a function with a result.
NOT_OWNED_RESULT = {"markOwned"}
OWNED_CALL = re.compile(r"\b(\w+Owned)\(")
FUN_DECL = re.compile(r"^[ \t]*(?:@\w+[ \t]+)*(?:(?:internal|private|public|override|actual|inline|operator)[ \t]+)*fun[ \t]+(?:<[^>]*>[ \t]+)?(?:[\w.]+\.)?(\w+)\(", re.M)


def _function_spans(text: str) -> list[tuple[str, int, int]]:
    """(name, start, end) of each `fun` in [text]; a body ends where the next `fun` starts."""
    starts = [(m.group(1), m.start()) for m in FUN_DECL.finditer(text)]
    spans = []
    for index, (name, start) in enumerate(starts):
        end = starts[index + 1][1] if index + 1 < len(starts) else len(text)
        spans.append((name, start, end))
    return spans


def _enclosing_function(spans: list[tuple[str, int, int]], offset: int) -> tuple[str, int, int] | None:
    for span in spans:
        if span[1] <= offset < span[2]:
            return span
    return None


def _ownership_problems_in(rel: str, stem: str, text: str, refcounted: set[str]) -> list[str]:
    problems: list[str] = []
    spans = _function_spans(text)
    file_class = stem.split(".")[0]

    def where(offset: int) -> str:
        return f"{rel}:{text.count(chr(10), 0, offset) + 1}"

    def line_at(offset: int) -> str:
        start = text.rfind("\n", 0, offset) + 1
        end = text.find("\n", offset)
        return text[start : end if end >= 0 else len(text)]

    for match in WRAP_CALL.finditer(text):
        qualifier, helper = match.group(1), match.group(2)
        prefix = text[max(0, match.start() - 4) : match.start()]
        if prefix.endswith("fun "):
            continue  # the declaration itself
        cls = qualifier or file_class
        if cls not in refcounted:
            continue
        function = _enclosing_function(spans, match.start())
        function_name = function[0] if function else ""
        if helper == "wrap":
            problems.append(
                f"{where(match.start())}: {cls}.wrap(...) -- a RefCounted wrapper has no wrap; "
                "use wrapOwned (a returned +1) or wrapBorrowed (fromHandle only)"
            )
        elif helper == "wrapOwned":
            if function_name == "fromHandle":
                problems.append(
                    f"{where(match.start())}: wrapOwned(...) inside fromHandle -- fromHandle is a view "
                    "of a handle the caller already holds; adopting it releases a reference never taken"
                )
            elif not OWNED_SOURCE.match(text, match.end()):
                problems.append(
                    f"{where(match.start())}: {cls}.wrapOwned(...) of something other than a ptrcall "
                    "object return or the collapse's `ret`: only a returned +1 is adopted"
                )
        elif helper == "wrapBorrowed" and function_name != "fromHandle":
            problems.append(
                f"{where(match.start())}: {cls}.wrapBorrowed(...) outside fromHandle: a site that "
                "builds a wrapper over a returned or decoded +1 owns it (wrapOwned / RefCounted.owned)"
            )

    for match in WRAP_REFERENCE.finditer(text):
        cls, helper = match.group(1), match.group(2)
        if cls in refcounted and helper != "wrapBorrowed":
            problems.append(
                f"{where(match.start())}: {cls}::{helper} -- a typed Array element callback is "
                "wrapBorrowed: the helper retains the element and makes the wrapper owned"
            )

    for match in CONSTRUCTION.finditer(text):
        cls = match.group(1)
        if cls not in refcounted:
            continue
        line = line_at(match.start())
        stripped = line.strip()
        before = text[max(0, match.start() - 40) : match.start()]
        after = text[match.end() : match.end() + 220]
        if re.search(r"\)\s*:\s*$", before) or re.match(r"(?:(?:open|abstract|actual|internal|sealed)\s+)*class\s", stripped):
            continue  # a class declaration's superclass call
        if before.rstrip().endswith(("owned(", "retained(")):
            continue  # RefCounted.owned / .retained (unqualified inside the RefCounted root)
        if re.match(r"[^\n]*\)?[\s)]*\.(?:also|apply)\s*\{\s*(?:it\.)?retainForKotlinWrapper\(\)", after):
            continue
        function = _enclosing_function(spans, match.start())
        function_name = function[0] if function else ""
        if function_name in ("fromHandle", "wrapBorrowed"):
            continue  # the borrowed view, by definition
        if function_name == "wrap" and "GodotClassTable()" in text:
            # Task 133 A's class-token tables: the checked casts (castOrNull, requireAs, ...) return
            # a non-owning view of an object the caller already holds, by that parcel's contract.
            continue
        if any(Path(rel).name == name and snippet in line for name, snippet in ALLOWED_BORROWED_CONSTRUCTIONS):
            continue
        problems.append(
            f"{where(match.start())}: {cls}({match.group(2)}...) builds a borrowed {cls} outside "
            "fromHandle/wrapBorrowed: wrap it in RefCounted.owned(...) (it adopts a +1) or "
            "RefCounted.retained(...) (it takes one)"
        )

    # Functions whose result is owned must make it so (or hand it to one that does).
    for name, start, end in spans:
        if not OWNED_RESULT_FUNCTION.match(name) or name in NOT_OWNED_RESULT:
            continue
        body = text[start:end]
        rest = body[body.find("(") + 1 :]
        # A call to another *Owned function (or an overload of this one) hands the +1 on.
        delegates = any(True for _ in OWNED_CALL.finditer(rest))
        if not any(marker in body for marker in OWNED_MARKERS) and not delegates:
            problems.append(
                f"{where(start)}: {name} returns an owned result but never takes or adopts a +1 "
                "(RefCounted.owned / retained / retainForKotlinWrapper, or a call to another *Owned)"
            )
        # The branch and its continuation lines, up to the next `when` branch.
        for branch in re.finditer(r"is RefCounted\s*->[^\n]*(?:\n(?![^\n]*->)[^\n]*){0,2}", body):
            if not any(marker in branch.group(0) for marker in OWNED_MARKERS):
                problems.append(
                    f"{where(start + branch.start())}: {name}: a RefCounted branch hands its wrapper "
                    "back without marking it owned"
                )

    if file_class in refcounted:
        for name, start, end in spans:
            body = text[start:end]
            if name == "wrapOwned" and "owned(" not in body:
                problems.append(f"{where(start)}: wrapOwned does not mark its wrapper owned (RefCounted.owned)")
            elif name == "create" and "constructObject(" in body and "RefCounted.owned(" not in body:
                problems.append(f"{where(start)}: {file_class}.create() does not own the constructing +1")
            elif (
                name.startswith("from")
                and name != "fromHandle"
                and ".isClass(" in body
                and "RefCounted.retained(" not in body
            ):
                problems.append(
                    f"{where(start)}: {file_class}.{name} is a downcast that does not take its own +1 "
                    "(RefCounted.retained): a view kept in a field would dangle"
                )
        if Path(rel).parent.name == "api" and "internal fun wrap(handle:" in text:
            problems.append(
                f"{rel}: declares `internal fun wrap(handle: ...)` for a RefCounted class; declare "
                "wrapOwned and wrapBorrowed"
            )
    return problems


def _positive_return_problems(roots: tuple[Path, ...], refcounted: set[str]) -> list[str]:
    """Every RefCounted-typed method return a wrapper emits reaches wrapOwned."""
    from generate_api_wrapper import method_function_name

    api = json.loads(EXTENSION_API.read_text(encoding="utf-8"))
    problems: list[str] = []
    files: dict[str, list[Path]] = {}
    for root in roots[:3]:
        for path in root.glob("*.kt"):
            files.setdefault(path.stem.split(".")[0], []).append(path)
    checked = 0
    for cls in api["classes"]:
        paths = files.get(cls["name"], [])
        if not paths:
            continue
        texts = [(p, p.read_text(encoding="utf-8")) for p in paths]
        for alias, godot_name in REFCOUNTED_ALIASES.items():
            if godot_name == cls["name"]:
                texts += [(p, p.read_text(encoding="utf-8")) for p in files.get(alias, [])]
        for method in cls.get("methods", []):
            ret = (method.get("return_value") or {}).get("type", "")
            if ret not in refcounted:
                continue
            name = method_function_name(cls["name"], method["name"])
            for path, text in texts:
                spans = _function_spans(text)
                # A file-local helper that adopts the +1 (Tween's wrapOrThis) counts as adopting.
                adopters = {
                    fname
                    for fname, start, end in spans
                    if "wrapOwned(" in text[start:end] or "RefCounted.owned(" in text[start:end]
                }
                for fname, start, end in spans:
                    if fname != name:
                        continue
                    body = text[start:end]
                    if "RetObject(" not in body and "IosGodot." not in body:
                        continue  # a Variant-path or hand sugar overload with no ptrcall return
                    checked += 1
                    if (
                        "wrapOwned(" not in body
                        and "RefCounted.owned(" not in body
                        and not any(re.search(rf"\b{re.escape(a)}\(", body[1:]) for a in adopters if a != fname)
                    ):
                        problems.append(
                            f"{path.relative_to(ROOT) if path.is_relative_to(ROOT) else path}:"
                            f"{text.count(chr(10), 0, start) + 1}: "
                            f"{cls['name']}.{method['name']} returns {ret} (an owned +1) but its "
                            "wrapper never adopts it (wrapOwned / RefCounted.owned)"
                        )
    if checked == 0:
        problems.append("positive RefCounted-return check matched no method: the walk is broken")
    return problems


# Known-bad snippets (task 132 review red runs): each must make the rules above report.
RED_RUNS: tuple[tuple[str, str, str], ...] = (
    ("R1 wrap on a RefCounted class", "api/MeshInstance3D.kt",
     "    fun getMesh(): Mesh? {\n        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(b, segment))\n    }\n"),
    ("R2 wrapBorrowed on a returned +1", "api/Texture2D.kt",
     "    fun getImage(): Image? {\n        return Image.wrapBorrowed(ObjectCalls.ptrcallNoArgsRetObject(b, segment))\n    }\n"),
    ("R3 wrapOwned of a handle not returned", "api/Texture2D.kt",
     "    fun view(h: GodotHandle): Image? {\n        return Image.wrapOwned(h.segment)\n    }\n"),
    ("R4 wrapOwned element callback", "api/Node.kt",
     "    fun mats(): List<Material> {\n        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(b, segment, Material::wrapOwned)\n    }\n"),
    ("R5 unqualified wrapOwned inside fromHandle", "api/Texture2D.kt",
     "    companion object {\n        fun fromHandle(handle: GodotHandle): Texture2D? =\n            wrapOwned(handle.segment)\n\n"
     "        internal fun wrapOwned(handle: RawSegment): Texture2D? =\n"
     "            if (handle.address() == 0L) null else RefCounted.owned(Texture2D(GodotHandle(handle)))\n    }\n"),
    ("R6 runtime decode without owned", "binding/runtime/BuiltinTypes.kt",
     "  fun readOwned(value: GodotObject): Any? {\n    ObjectCalls.ptrcallNoArgsRetBool(referenceBind, value.segment)\n"
     "    return RefCounted(value.handle)\n  }\n"),
    ("R7 iOS ownedListElement without owned", "binding/runtime/ObjectCalls.kt",
     "  internal fun <T> ownedListElement(obj: T?): T? =\n    if (obj is GodotObject) RefCounted(obj.handle) as T else obj\n"),
    ("R8 create() without owned", "api/StandardMaterial3D.kt",
     "    companion object {\n        fun create(): StandardMaterial3D =\n"
     "            StandardMaterial3D(GodotHandle(ObjectCalls.constructObject(\"StandardMaterial3D\")))\n    }\n"),
    ("R9 iOS loader without owned", "api/IosGodotApi.kt",
     "    fun load(path: String): Resource? =\n        IosGodot.resourceLoaderLoad(path, \"\").takeIf { it != 0L }?.let {\n"
     "            Resource(GodotHandle(MemorySegment.ofAddress(it)))\n        }\n"),
    ("R10 downcast without its own +1", "api/Mesh.kt",
     "    companion object {\n        fun fromObject(value: GodotObject): Mesh? =\n"
     "            if (value.isClass(\"Mesh\")) Mesh(value.handle) else null\n    }\n"),
    ("R11 wrapOwned helper that does not own", "api/Mesh.kt",
     "        internal fun wrapOwned(handle: RawSegment): Mesh? =\n            if (handle.address() == 0L) null else Mesh(GodotHandle(handle))\n"),
    ("R7b iOS ownedListElement hands a typed wrapper back unowned", "binding/runtime/ObjectCalls.kt",
     "  internal fun <T> ownedListElement(obj: T?): T? =\n    when {\n      obj is RefCounted -> obj as T\n"
     "      obj is GodotObject && obj.instanceId < 0L ->\n        RefCounted.owned(RefCounted(obj.handle)) as T\n"
     "      else -> obj\n    }\n"),
    ("R13 newScriptInstance's resource not owned", "binding/KanamaScript.kt",
     "    fun instantiateResourceScript(fqName: String?, simpleName: String?): Pair<Any, Resource> {\n"
     "      val baseHandle = ObjectCalls.constructObject(\"Resource\")\n"
     "      return instance to (net.multigesture.kanama.api.Resource.fromHandle(GodotHandle(baseHandle)))\n    }\n"),
    ("R14 property decode without its retain", "binding/runtime/BuiltinTypes.kt",
     "  fun <T> readVariantObjectRetained(variant: MemorySegment, arena: Arena, wrapper: (MemorySegment) -> T?): T? =\n"
     "    readVariantObject(variant, arena, wrapper).also { value ->\n      if (value is Resource) {\n        Unit\n      }\n    }\n"),
)


def red_run_problems(refcounted: set[str]) -> list[str]:
    problems = []
    for label, rel, snippet in RED_RUNS:
        stem = Path(rel).stem
        if not _ownership_problems_in(rel, stem, snippet, refcounted):
            problems.append(f"red run '{label}' passed the ownership rules: a rule stopped matching")
    return problems


def refcounted_ownership_problems(roots: tuple[Path, ...] = WRAPPER_SOURCE_DIRS) -> list[str]:
    refcounted = refcounted_classes()
    problems = red_run_problems(refcounted)
    for root in roots:
        for path in sorted(root.rglob("*.kt")):
            text = path.read_text(encoding="utf-8")
            problems.extend(_ownership_problems_in(str(path.relative_to(ROOT)), path.stem, text, refcounted))
    problems.extend(_positive_return_problems(roots, refcounted))
    return problems


def main() -> int:
    content = OBJECT_CALLS.read_text(encoding="utf-8")
    errors: list[str] = []
    checked_functions: set[str] = set()

    for (api_type, meta), (expected_exact, expected_storage, expected_logical, expected_public) in UNSIGNED_POLICY.items():
        policy = value_policy(api_type, meta)
        if policy.exact_kind != expected_exact:
            errors.append(f"{api_type}[{meta}] exact kind is {policy.exact_kind}, expected {expected_exact}")
        if policy.storage_kind != expected_storage:
            errors.append(f"{api_type}[{meta}] storage kind is {policy.storage_kind}, expected {expected_storage}")
        if policy.public_kotlin_type != expected_public:
            errors.append(f"{api_type}[{meta}] public Kotlin type is {policy.public_kotlin_type}, expected {expected_public}")
        if logical_type(api_type, meta) != expected_logical:
            errors.append(f"{api_type}[{meta}] logical type is {logical_type(api_type, meta)}, expected {expected_logical}")
        if exact_abi_kind(api_type, meta) != expected_exact:
            errors.append(f"{api_type}[{meta}] exact ABI is {exact_abi_kind(api_type, meta)}, expected {expected_exact}")
        if storage_abi_kind(api_type, meta) != expected_storage:
            errors.append(f"{api_type}[{meta}] storage ABI is {storage_abi_kind(api_type, meta)}, expected {expected_storage}")

    for api_type, (expected_exact, expected_storage, expected_logical, expected_public) in RAW_POINTER_POLICY.items():
        policy = value_policy(api_type)
        if policy.exact_kind != expected_exact:
            errors.append(f"{api_type} exact kind is {policy.exact_kind}, expected {expected_exact}")
        if policy.storage_kind != expected_storage:
            errors.append(f"{api_type} storage kind is {policy.storage_kind}, expected {expected_storage}")
        if policy.public_kotlin_type != expected_public:
            errors.append(f"{api_type} public Kotlin type is {policy.public_kotlin_type}, expected {expected_public}")
        if logical_type(api_type) != expected_logical:
            errors.append(f"{api_type} logical type is {logical_type(api_type)}, expected {expected_logical}")
        if exact_abi_kind(api_type) != expected_exact:
            errors.append(f"{api_type} exact ABI is {exact_abi_kind(api_type)}, expected {expected_exact}")
        if storage_abi_kind(api_type) != expected_storage:
            errors.append(f"{api_type} storage ABI is {storage_abi_kind(api_type)}, expected {expected_storage}")

    callable_policy = value_policy("Callable")
    if callable_policy.exact_kind != "callable":
        errors.append(f"Callable exact kind is {callable_policy.exact_kind}, expected callable")
    if callable_policy.storage_kind != "callable":
        errors.append(f"Callable storage kind is {callable_policy.storage_kind}, expected callable")
    if callable_policy.public_kotlin_type != "GodotCallable":
        errors.append(f"Callable public Kotlin type is {callable_policy.public_kotlin_type}, expected GodotCallable")
    if logical_type("Callable") != "Callable":
        errors.append(f"Callable logical type is {logical_type('Callable')}, expected Callable")

    for (args, ret), shape in sorted(CALL_SHAPES.items(), key=lambda item: (item[1].function, item[0])):
        logical_types = set(args)
        logical_types.add(ret)
        if "Callable" in logical_types:
            errors.append(
                f"CALL_SHAPES {args} -> {ret} uses Callable; Callable shapes must stay hand-audited",
            )
            continue
        if ret == "Object" and "Variant" in args:
            errors.append(
                f"CALL_SHAPES {args} -> {ret} promotes Variant-to-Object; ownership/nullability must stay hand-audited",
            )
            continue
        if ret == "Dictionary" and any(arg in {"Dictionary", "TypedDictionaryArray"} for arg in args):
            errors.append(
                f"CALL_SHAPES {args} -> {ret} promotes Dictionary-to-Dictionary; non-String key policy must stay explicit",
            )
            continue
        if any(arg.startswith("TypedObjectArray::") for arg in args) or ret.startswith("TypedObjectArray::"):
            errors.append(
                f"CALL_SHAPES {args} -> {ret} promotes typed object arrays; null/reference policy must stay hand-audited",
            )
            continue

        result = find_function_text(content, shape.function)
        if result is None:
            errors.append(f"CALL_SHAPES {args} -> {ret} references missing ObjectCalls.{shape.function}")
            continue

        line, function_text = result
        checked_functions.add(shape.function)
        for error in audit_shape(function_text, args, ret):
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{line}: "
                f"{shape.function} for {args} -> {ret}: {error}",
            )

    errors.extend(audit_typed_object_array_helpers(content))
    errors.extend(refcounted_ownership_problems())

    if errors:
        print("[generator_shape_policy] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print(
        "[generator_shape_policy] PASS "
        f"shapes={len(CALL_SHAPES)} helpers={len(checked_functions)}",
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
