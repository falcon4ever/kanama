#!/usr/bin/env python3
"""Audit conservative wrapper-generator call-shape policy.

The generator's CALL_SHAPES table is the gate that promotes methods from
extension_api.json into Kotlin wrappers. This audit keeps that table tied to
strict runtime helpers for the shapes that are easiest to widen unsafely:

- generic Variant values must use the strict Variant marshaller,
- generic Array/Dictionary values must use the explicit builtin initializers,
- Packed*Array values must use the 16-byte packed storage helpers,
- Callable must not be exposed through the generic generator table.
"""

from __future__ import annotations

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


def audit_typed_object_array_helpers(content: str) -> list[str]:
    errors: list[str] = []
    for typed_helper, object_helper in DYNAMIC_TYPED_OBJECT_ARRAY_HELPERS.items():
        object_result = find_function_text(content, object_helper)
        if object_result is None:
            errors.append(f"dynamic typed object-array helper {typed_helper} references missing {object_helper}")
            continue
        object_line, object_text = object_result
        if "BuiltinTypes.readArrayObjects" not in object_text and "BuiltinTypes::readArrayObjects" not in object_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{object_line}: "
                f"{object_helper} does not decode through readArrayObjects",
            )
        if "BuiltinTypes.destroyTyped(VariantType.ARRAY" not in object_text and "callArrayReturn(" not in object_text:
            errors.append(
                f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{object_line}: "
                f"{object_helper} does not destroy Array storage",
            )

        typed_result = find_function_text(content, typed_helper)
        if typed_result is None:
            errors.append(f"dynamic typed object-array helper {typed_helper} is missing")
            continue
        typed_line, typed_text = typed_result
        if object_helper in typed_text:
            if ".mapNotNull" not in typed_text or "wrapper(it.segment)" not in typed_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{typed_line}: "
                    f"{typed_helper} does not map through nullable typed wrappers",
                )
        else:
            if "BuiltinTypes.readArrayObjects(" not in typed_text or ", wrapper)" not in typed_text:
                errors.append(
                    f"src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:{typed_line}: "
                    f"{typed_helper} does not decode directly through nullable typed wrappers",
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
