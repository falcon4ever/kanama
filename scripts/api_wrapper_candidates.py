#!/usr/bin/env python3
"""Suggest Kotlin wrapper constants/stubs for marshalable API methods."""

from __future__ import annotations

import argparse
from dataclasses import dataclass
from pathlib import Path

from wrapper_model import ApiMethod, load_api_classes, logical_type, object_type_names, scan_wrappers


@dataclass(frozen=True)
class CallShape:
    function: str
    kotlin_return: str
    fallback: str | None = None


CALL_SHAPES: dict[tuple[tuple[str, ...], str], CallShape] = {
    ((), "void"): CallShape("ptrcallNoArgs", "Unit"),
    ((), "bool"): CallShape("ptrcallNoArgsRetBool", "Boolean", "false"),
    ((), "int64"): CallShape("ptrcallNoArgsRetLong", "Long", "0L"),
    ((), "int32"): CallShape("ptrcallNoArgsRetInt", "Int", "0"),
    ((), "uint32"): CallShape("ptrcallNoArgsRetUInt32", "Long", "0L"),
    ((), "enum"): CallShape("ptrcallNoArgsRetLong", "Long", "0L"),
    ((), "bitfield"): CallShape("ptrcallNoArgsRetLong", "Long", "0L"),
    ((), "float"): CallShape("ptrcallNoArgsRetDouble", "Double", "0.0"),
    ((), "String"): CallShape("ptrcallNoArgsRetString", "String", '""'),
    ((), "StringName"): CallShape("ptrcallNoArgsRetStringName", "String", '""'),
    ((), "NodePath"): CallShape("ptrcallNoArgsRetNodePath", "NodePath", "NodePath.EMPTY"),
    ((), "Dictionary"): CallShape("ptrcallNoArgsRetDictionary", "Map<String, Any?>", "emptyMap()"),
    ((), "Array"): CallShape("ptrcallNoArgsRetArray", "List<Any?>", "emptyList()"),
    ((), "Variant"): CallShape("ptrcallNoArgsRetVariantScalar", "Any?", "null"),
    ((), "PackedStringArray"): CallShape("ptrcallNoArgsRetPackedStringList", "List<String>", "emptyList()"),
    ((), "PackedByteArray"): CallShape("ptrcallNoArgsRetByteArray", "ByteArray", "ByteArray(0)"),
    ((), "PackedInt32Array"): CallShape("ptrcallNoArgsRetPackedInt32List", "List<Int>", "emptyList()"),
    ((), "PackedInt64Array"): CallShape("ptrcallNoArgsRetPackedInt64List", "List<Long>", "emptyList()"),
    ((), "PackedFloat32Array"): CallShape("ptrcallNoArgsRetPackedFloat32List", "List<Float>", "emptyList()"),
    ((), "PackedFloat64Array"): CallShape("ptrcallNoArgsRetPackedFloat64List", "List<Double>", "emptyList()"),
    ((), "PackedVector2Array"): CallShape("ptrcallNoArgsRetPackedVector2List", "List<Vector2>", "emptyList()"),
    ((), "PackedVector3Array"): CallShape("ptrcallNoArgsRetPackedVector3List", "List<Vector3>", "emptyList()"),
    ((), "PackedColorArray"): CallShape("ptrcallNoArgsRetPackedColorList", "List<Color>", "emptyList()"),
    ((), "PackedVector4Array"): CallShape("ptrcallNoArgsRetPackedVector4List", "List<Vector4>", "emptyList()"),
    ((), "TypedRIDArray"): CallShape("ptrcallNoArgsRetRIDList", "List<RID>", "emptyList()"),
    ((), "TypedStringArray"): CallShape("ptrcallNoArgsRetTypedStringList", "List<String>", "emptyList()"),
    ((), "TypedIntArray"): CallShape("ptrcallNoArgsRetLongList", "List<Long>", "emptyList()"),
    ((), "TypedNodePathArray"): CallShape("ptrcallNoArgsRetNodePathList", "List<NodePath>", "emptyList()"),
    ((), "TypedArrayArray"): CallShape("ptrcallNoArgsRetArrayList", "List<List<Any?>>", "emptyList()"),
    ((), "TypedPackedByteArray"): CallShape("ptrcallNoArgsRetByteArrayList", "List<ByteArray>", "emptyList()"),
    ((), "TypedPackedStringArray"): CallShape(
        "ptrcallNoArgsRetPackedStringListList",
        "List<List<String>>",
        "emptyList()",
    ),
    ((), "TypedPackedVector2Array"): CallShape(
        "ptrcallNoArgsRetPackedVector2ListList",
        "List<List<Vector2>>",
        "emptyList()",
    ),
    ((), "TypedPlaneArray"): CallShape("ptrcallNoArgsRetPlaneList", "List<Plane>", "emptyList()"),
    ((), "TypedRect2Array"): CallShape("ptrcallNoArgsRetRect2List", "List<Rect2>", "emptyList()"),
    ((), "TypedTransform3DArray"): CallShape("ptrcallNoArgsRetTransform3DList", "List<Transform3D>", "emptyList()"),
    ((), "TypedVector2Array"): CallShape("ptrcallNoArgsRetVector2List", "List<Vector2>", "emptyList()"),
    ((), "TypedVector3Array"): CallShape("ptrcallNoArgsRetVector3List", "List<Vector3>", "emptyList()"),
    ((), "TypedNodeArray"): CallShape("ptrcallNoArgsRetTypedNodeList", "List<Node>", "emptyList()"),
    ((), "TypedNode2DArray"): CallShape("ptrcallNoArgsRetTypedNode2DList", "List<Node2D>", "emptyList()"),
    ((), "TypedNode3DArray"): CallShape("ptrcallNoArgsRetTypedNode3DList", "List<Node3D>", "emptyList()"),
    ((), "TypedMaterialArray"): CallShape("ptrcallNoArgsRetTypedMaterialList", "List<Material>", "emptyList()"),
    ((), "TypedArea2DArray"): CallShape("ptrcallNoArgsRetTypedArea2DList", "List<Area2D>", "emptyList()"),
    ((), "TypedArea3DArray"): CallShape("ptrcallNoArgsRetTypedArea3DList", "List<Area3D>", "emptyList()"),
    ((), "TypedBaseButtonArray"): CallShape(
        "ptrcallNoArgsRetTypedBaseButtonList",
        "List<BaseButton>",
        "emptyList()",
    ),
    ((), "TypedPhysicsBody3DArray"): CallShape(
        "ptrcallNoArgsRetTypedPhysicsBody3DList",
        "List<PhysicsBody3D>",
        "emptyList()",
    ),
    ((), "TypedVector2iArray"): CallShape("ptrcallNoArgsRetVector2iList", "List<Vector2i>", "emptyList()"),
    ((), "TypedVector3iArray"): CallShape("ptrcallNoArgsRetVector3iList", "List<Vector3i>", "emptyList()"),
    ((), "TypedStringNameArray"): CallShape("ptrcallNoArgsRetStringNameList", "List<String>", "emptyList()"),
    ((), "TypedDictionaryArray"): CallShape(
        "ptrcallNoArgsRetDictionaryList",
        "List<Map<String, Any?>>",
        "emptyList()",
    ),
    ((), "RID"): CallShape("ptrcallNoArgsRetRID", "RID", "RID.EMPTY"),
    ((), "Color"): CallShape("ptrcallNoArgsRetColor", "Color", "Color(0f, 0f, 0f, 0f)"),
    ((), "Object"): CallShape("ptrcallNoArgsRetObject", "MemorySegment", "MemorySegment.NULL"),
    ((), "Vector2"): CallShape("ptrcallNoArgsRetVector2", "Vector2", "Vector2.ZERO"),
    ((), "Vector2i"): CallShape("ptrcallNoArgsRetVector2i", "Vector2i", "Vector2i.ZERO"),
    ((), "Vector3"): CallShape("ptrcallNoArgsRetVector3", "Vector3", "Vector3.ZERO"),
    ((), "Vector3i"): CallShape("ptrcallNoArgsRetVector3i", "Vector3i", "Vector3i.ZERO"),
    ((), "Vector4"): CallShape("ptrcallNoArgsRetVector4", "Vector4", "Vector4.ZERO"),
    ((), "Rect2"): CallShape("ptrcallNoArgsRetRect2", "Rect2", "Rect2.ZERO"),
    ((), "Rect2i"): CallShape("ptrcallNoArgsRetRect2i", "Rect2i", "Rect2i.ZERO"),
    ((), "AABB"): CallShape("ptrcallNoArgsRetAABB", "AABB", "AABB.ZERO"),
    ((), "Basis"): CallShape("ptrcallNoArgsRetBasis", "Basis", "Basis.IDENTITY"),
    ((), "Transform2D"): CallShape("ptrcallNoArgsRetTransform2D", "Transform2D", "Transform2D.IDENTITY"),
    ((), "Transform3D"): CallShape("ptrcallNoArgsRetTransform3D", "Transform3D", "Transform3D.IDENTITY"),
    ((), "Quaternion"): CallShape("ptrcallNoArgsRetQuaternion", "Quaternion", "Quaternion.IDENTITY"),
    ((), "Projection"): CallShape("ptrcallNoArgsRetProjection", "Projection", "Projection.IDENTITY"),
    (("Vector2i",), "void"): CallShape("ptrcallWithVector2iArg", "Unit"),
    (("Vector2i", "Vector2i"), "TypedVector2iArray"): CallShape(
        "ptrcallWithTwoVector2iArgsRetVector2iList",
        "List<Vector2i>",
        "emptyList()",
    ),
    (("Vector2i", "Vector2i", "bool"), "PackedVector2Array"): CallShape(
        "ptrcallWithTwoVector2iAndBoolArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    # Phase 1.3 — Variant-return, Variant-arg, and Packed*-arg helper shapes.
    (("Dictionary",), "Variant"): CallShape(
        "ptrcallWithDictionaryArgRetVariantScalar",
        "Any?",
        "null",
    ),
    (("String", "Variant", "int32"), "void"): CallShape(
        "ptrcallWithStringVariantAndIntArg",
        "Unit",
    ),
    (
        ("Object", "float", "float", "Color", "enum", "Rect2", "Variant", "bool", "String", "enum", "enum", "String"),
        "void",
    ): CallShape(
        "ptrcallWithObjectTwoDoubleColorLongRect2VariantBoolStringTwoLongStringArgs",
        "Unit",
    ),
    (
        ("Variant", "bitfield", "Object", "float", "float", "Color", "enum", "Rect2", "bool", "String", "enum", "enum"),
        "void",
    ): CallShape(
        "ptrcallWithVariantLongObjectTwoDoubleColorLongRect2BoolStringTwoLongArgs",
        "Unit",
    ),
    (("String", "PackedByteArray", "bool"), "enum"): CallShape(
        "ptrcallWithStringByteArrayAndBoolArgRetLong",
        "Long",
        "0L",
    ),
    (("Object", "PackedStringArray"), "Object"): CallShape(
        "ptrcallWithObjectAndPackedStringListArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Object", "String", "PackedStringArray"), "void"): CallShape(
        "ptrcallWithObjectStringAndPackedStringListArgs",
        "Unit",
    ),
    (("RID", "PackedColorArray"), "void"): CallShape(
        "ptrcallWithRIDAndPackedColorListArgs",
        "Unit",
    ),
    (("RID", "int64", "uint32", "PackedInt32Array"), "enum"): CallShape(
        "ptrcallWithRIDLongUInt32AndPackedInt32ListArgRetLong",
        "Long",
        "0L",
    ),
    (("Vector3i",), "TypedVector3iArray"): CallShape(
        "ptrcallWithVector3iArgRetVector3iList",
        "List<Vector3i>",
        "emptyList()",
    ),
    (("Vector3i", "int32"), "TypedVector3iArray"): CallShape(
        "ptrcallWithVector3iAndIntArgRetVector3iList",
        "List<Vector3i>",
        "emptyList()",
    ),
    (("AABB",), "TypedVector3iArray"): CallShape(
        "ptrcallWithAABBArgRetVector3iList",
        "List<Vector3i>",
        "emptyList()",
    ),
    (("int64",), "PackedColorArray"): CallShape(
        "ptrcallWithLongArgRetPackedColorList",
        "List<Color>",
        "emptyList()",
    ),
    (("RID", "int64"), "PackedColorArray"): CallShape(
        "ptrcallWithRIDAndLongArgRetPackedColorList",
        "List<Color>",
        "emptyList()",
    ),
    (("Dictionary",), "Array"): CallShape(
        "ptrcallWithDictionaryArgRetArray",
        "List<Any?>",
        "emptyList()",
    ),
    (("Dictionary",), "Dictionary"): CallShape(
        "ptrcallWithDictionaryArgRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("Object",), "void"): CallShape("ptrcallWithObjectArgs", "Unit"),
    (("Object",), "bool"): CallShape("ptrcallWithObjectArgRetBool", "Boolean", "false"),
    (("Object",), "int64"): CallShape("ptrcallWithObjectArgRetLong", "Long", "0L"),
    (("Object",), "int32"): CallShape("ptrcallWithObjectArgRetInt", "Int", "0"),
    (("Object",), "enum"): CallShape("ptrcallWithObjectArgRetLong", "Long", "0L"),
    (("Object",), "RID"): CallShape("ptrcallWithObjectArgRetRID", "RID", "RID.EMPTY"),
    (("Object", "RID"), "RID"): CallShape("ptrcallWithObjectRIDArgsRetRID", "RID", "RID.EMPTY"),
    (("Object", "String"), "RID"): CallShape("ptrcallWithObjectStringArgsRetRID", "RID", "RID.EMPTY"),
    (("Object", "Object", "TypedPackedByteArray"), "RID"): CallShape(
        "ptrcallWithTwoObjectByteArrayListArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("Object", "RID", "uint32", "uint32", "uint32", "enum"), "RID"): CallShape(
        "ptrcallWithObjectRIDThreeUInt32LongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("Object",), "Dictionary"): CallShape("ptrcallWithObjectArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("Object",), "StringName"): CallShape("ptrcallWithObjectArgRetStringName", "String", '""'),
    (("Object",), "Object"): CallShape("ptrcallWithObjectArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("Object",), "Transform2D"): CallShape("ptrcallWithObjectArgRetTransform2D", "Transform2D", "Transform2D.IDENTITY"),
    (("Object",), "PackedStringArray"): CallShape(
        "ptrcallWithObjectArgRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("Object",), "PackedByteArray"): CallShape("ptrcallWithObjectArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("Object",), "PackedFloat32Array"): CallShape(
        "ptrcallWithObjectArgRetPackedFloat32List",
        "List<Float>",
        "emptyList()",
    ),
    (("Object", "bool"), "void"): CallShape("ptrcallWithObjectAndBoolArg", "Unit"),
    (("Object", "bool"), "bool"): CallShape("ptrcallWithObjectAndBoolArgRetBool", "Boolean", "false"),
    (("Object", "bool"), "int32"): CallShape("ptrcallWithObjectAndBoolArgRetInt", "Int", "0"),
    (("Object", "bool"), "Dictionary"): CallShape(
        "ptrcallWithObjectAndBoolArgRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("Object", "bool"), "Object"): CallShape(
        "ptrcallWithObjectAndBoolArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Object", "bool"), "NodePath"): CallShape(
        "ptrcallWithObjectAndBoolArgRetNodePath",
        "NodePath",
        "NodePath.EMPTY",
    ),
    (("Object", "bool", "enum"), "void"): CallShape("ptrcallWithObjectBoolLongArgs", "Unit"),
    (("Object", "bool", "bool"), "void"): CallShape("ptrcallWithObjectAndTwoBoolArgs", "Unit"),
    (("Object", "bool", "int64"), "void"): CallShape("ptrcallWithObjectBoolLongArgs", "Unit"),
    (("Object", "bool", "String"), "Dictionary"): CallShape(
        "ptrcallWithObjectBoolStringArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("Object", "bool", "String", "bool"), "Dictionary"): CallShape(
        "ptrcallWithObjectBoolStringBoolArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("Object", "bool", "String", "bitfield"), "enum"): CallShape(
        "ptrcallWithObjectBoolStringLongArgsRetLong",
        "Long",
        "0L",
    ),
    (("Object", "bool", "String", "PackedStringArray", "bitfield"), "enum"): CallShape(
        "ptrcallWithObjectBoolStringPackedStringListLongArgsRetLong",
        "Long",
        "0L",
    ),
    (("Object", "Dictionary"), "void"): CallShape("ptrcallWithObjectAndDictionaryArg", "Unit"),
    (("Object", "int32"), "int64"): CallShape("ptrcallWithObjectAndIntArgRetLong", "Long", "0L"),
    (("Object", "int32"), "enum"): CallShape("ptrcallWithObjectAndIntArgRetLong", "Long", "0L"),
    (("Object", "int32"), "int32"): CallShape("ptrcallWithObjectAndIntArgRetInt", "Int", "0"),
    (("Object", "int32"), "void"): CallShape("ptrcallWithObjectAndIntArg", "Unit"),
    (("Object", "int32", "String"), "void"): CallShape("ptrcallWithObjectIntStringArgs", "Unit"),
    (("Object", "int32"), "Object"): CallShape("ptrcallWithObjectAndIntArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("Object", "int32"), "TypedVector2Array"): CallShape(
        "ptrcallWithObjectAndIntArgRetVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("Object", "int32"), "TypedVector3Array"): CallShape(
        "ptrcallWithObjectAndIntArgRetVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("Object", "int32", "int32"), "Rect2"): CallShape(
        "ptrcallWithObjectAndTwoIntArgsRetRect2",
        "Rect2",
        "Rect2.ZERO",
    ),
    (("Object", "int32"), "TypedDictionaryArray"): CallShape(
        "ptrcallWithObjectAndIntArgRetDictionaryList",
        "List<Map<String, Any?>>",
        "emptyList()",
    ),
    (("Object", "int32", "Transform3D"), "void"): CallShape("ptrcallWithObjectIntTransform3DArgs", "Unit"),
    (("Object", "Transform3D"), "void"): CallShape("ptrcallWithObjectAndTransform3DArg", "Unit"),
    (("Object", "int32", "bool"), "void"): CallShape("ptrcallWithObjectIntBoolArgs", "Unit"),
    (("Object", "int32", "bool", "bool"), "void"): CallShape("ptrcallWithObjectIntTwoBoolArgs", "Unit"),
    (("Object", "Object", "int32", "bool"), "void"): CallShape("ptrcallWithObjectObjectIntBoolArgs", "Unit"),
    (("Object", "Object", "int32", "bool", "bool"), "void"): CallShape(
        "ptrcallWithObjectObjectIntTwoBoolArgs",
        "Unit",
    ),
    (("Object", "Object", "int32"), "int32"): CallShape("ptrcallWithTwoObjectIntArgsRetInt", "Int", "0"),
    (("Object", "Object", "Transform2D", "Color"), "void"): CallShape(
        "ptrcallWithTwoObjectTransform2DColorArgs",
        "Unit",
    ),
    (("Object", "Object", "Transform3D", "Object"), "void"): CallShape(
        "ptrcallWithTwoObjectTransform3DObjectArgs",
        "Unit",
    ),
    (("Object", "int32", "int32", "Color", "enum", "Rect2", "Variant", "bool", "String", "bool", "bool", "String"), "void"): CallShape(
        "ptrcallWithObjectTwoIntColorLongRect2VariantBoolStringTwoBoolStringArgs",
        "Unit",
    ),
    (("Object", "float", "int32"), "void"): CallShape("ptrcallWithObjectDoubleAndIntArgs", "Unit"),
    (("Object", "float", "Color"), "void"): CallShape("ptrcallWithObjectDoubleColorArgs", "Unit"),
    (("Object", "enum", "bool", "float"), "void"): CallShape("ptrcallWithObjectLongBoolDoubleArgs", "Unit"),
    (("Object", "Color", "bool", "bool"), "void"): CallShape("ptrcallWithObjectColorTwoBoolArgs", "Unit"),
    (("Object", "Color", "enum", "bool"), "void"): CallShape("ptrcallWithObjectColorLongBoolArgs", "Unit"),
    (("Object", "Vector2", "Color"), "void"): CallShape("ptrcallWithObjectVector2AndColorArgs", "Unit"),
    (("Object", "Vector2", "String", "int32", "Color", "float"), "void"): CallShape(
        "ptrcallWithObjectVector2StringIntColorDoubleArgs",
        "Unit",
    ),
    (("Object", "Vector2", "String", "int32", "int32", "Color", "float"), "void"): CallShape(
        "ptrcallWithObjectVector2StringTwoIntColorDoubleArgs",
        "Unit",
    ),
    (("Object", "Vector2", "String", "enum", "float", "int32", "Color", "bitfield", "enum", "enum", "float"), "void"): CallShape(
        "ptrcallWithObjectVector2StringLongDoubleIntColorThreeLongDoubleArgs",
        "Unit",
    ),
    (("Object", "Vector2", "String", "enum", "float", "int32", "int32", "Color", "bitfield", "enum", "enum", "float"), "void"): CallShape(
        "ptrcallWithObjectVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs",
        "Unit",
    ),
    (("Object", "Vector2", "String", "enum", "float", "int32", "int32", "Color", "bitfield", "bitfield", "enum", "enum", "float"), "void"): CallShape(
        "ptrcallWithObjectVector2StringLongDoubleTwoIntColorFourLongDoubleArgs",
        "Unit",
    ),
    (("Object", "Vector2", "String", "enum", "float", "int32", "int32", "int32", "Color", "bitfield", "bitfield", "enum", "enum", "float"), "void"): CallShape(
        "ptrcallWithObjectVector2StringLongDoubleThreeIntColorFourLongDoubleArgs",
        "Unit",
    ),
    (("Object", "Vector2", "int32"), "void"): CallShape("ptrcallWithObjectVector2AndIntArgs", "Unit"),
    (("Object", "PackedByteArray"), "PackedByteArray"): CallShape(
        "ptrcallWithObjectAndByteArrayArgRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("Object", "String"), "PackedByteArray"): CallShape(
        "ptrcallWithObjectAndStringArgRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("Object", "StringName"), "void"): CallShape("ptrcallWithObjectAndStringNameArg", "Unit"),
    (("Object", "StringName", "bool"), "bool"): CallShape(
        "ptrcallWithObjectStringNameAndBoolArgRetBool",
        "Boolean",
        "false",
    ),
    (("Object", "String", "int64"), "int64"): CallShape("ptrcallWithObjectStringLongArgsRetLong", "Long", "0L"),
    (("Object", "String", "bitfield"), "enum"): CallShape(
        "ptrcallWithObjectStringLongArgsRetLong",
        "Long",
        "0L",
    ),
    (("Object", "String", "Object"), "enum"): CallShape(
        "ptrcallWithObjectStringAndObjectArgsRetLong",
        "Long",
        "0L",
    ),
    (("Object", "String"), "enum"): CallShape("ptrcallWithObjectAndStringArgRetLong", "Long", "0L"),
    (("Object", "String"), "Object"): CallShape(
        "ptrcallWithObjectStringArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Object", "String", "String", "String"), "Object"): CallShape(
        "ptrcallWithObjectThreeStringArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Object", "String", "Object"), "Object"): CallShape(
        "ptrcallWithObjectStringObjectArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Object", "String"), "void"): CallShape("ptrcallWithObjectAndStringArg", "Unit"),
    (("Object", "String", "bool"), "void"): CallShape("ptrcallWithObjectStringBoolArgs", "Unit"),
    (("Object", "String", "int32", "enum"), "void"): CallShape("ptrcallWithObjectStringIntLongArgs", "Unit"),
    (("Object", "int32", "int32", "bool"), "void"): CallShape("ptrcallWithObjectTwoIntBoolArgs", "Unit"),
    (("Object", "int32", "int32"), "enum"): CallShape("ptrcallWithObjectTwoIntArgsRetLong", "Long", "0L"),
    (("Object", "int64", "Vector2"), "void"): CallShape("ptrcallWithObjectLongAndVector2Arg", "Unit"),
    (("Object", "NodePath"), "void"): CallShape("ptrcallWithObjectAndNodePathArg", "Unit"),
    (("Object", "NodePath", "Object", "int32"), "Object"): CallShape(
        "ptrcallWithObjectNodePathObjectIntArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("bool",), "Object"): CallShape("ptrcallWithBoolArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("String",), "void"): CallShape("ptrcallWithStringArg", "Unit"),
    (("String",), "bool"): CallShape("ptrcallWithStringArgRetBool", "Boolean", "false"),
    (("String",), "int64"): CallShape("ptrcallWithStringArgRetLong", "Long", "0L"),
    (("String",), "int32"): CallShape("ptrcallWithStringArgRetInt", "Int", "0"),
    (("String",), "enum"): CallShape("ptrcallWithStringArgRetLong", "Long", "0L"),
    (("String",), "bitfield"): CallShape("ptrcallWithStringArgRetLong", "Long", "0L"),
    (("String",), "String"): CallShape("ptrcallWithStringArgRetString", "String", '""'),
    (("String",), "Color"): CallShape("ptrcallWithStringArgRetColor", "Color", "Color(0f, 0f, 0f, 0f)"),
    (("String",), "Variant"): CallShape("ptrcallWithStringArgRetVariantScalar", "Any?", "null"),
    (("String",), "PackedByteArray"): CallShape("ptrcallWithStringArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("String",), "Dictionary"): CallShape("ptrcallWithStringArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("String",), "PackedStringArray"): CallShape(
        "ptrcallWithStringArgRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("String",), "Object"): CallShape("ptrcallWithStringArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("String", "StringName"), "String"): CallShape("ptrcallWithStringAndStringNameArgRetString", "String", '""'),
    (("StringName",), "void"): CallShape("ptrcallWithStringNameArg", "Unit"),
    (("StringName",), "bool"): CallShape("ptrcallWithStringNameArgRetBool", "Boolean", "false"),
    (("StringName",), "int64"): CallShape("ptrcallWithStringNameArgRetLong", "Long", "0L"),
    (("StringName",), "int32"): CallShape("ptrcallWithStringNameArgRetInt", "Int", "0"),
    (("StringName",), "float"): CallShape("ptrcallWithStringNameArgRetDouble", "Double", "0.0"),
    (("StringName",), "Color"): CallShape("ptrcallWithStringNameArgRetColor", "Color", "Color(0f, 0f, 0f, 0f)"),
    (("StringName",), "Vector2"): CallShape("ptrcallWithStringNameArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("StringName",), "String"): CallShape("ptrcallWithStringNameArgRetString", "String", '""'),
    (("StringName",), "StringName"): CallShape("ptrcallWithStringNameArgRetStringName", "String", '""'),
    (("StringName",), "Variant"): CallShape("ptrcallWithStringNameArgRetVariantScalar", "Any?", "null"),
    (("StringName",), "enum"): CallShape("ptrcallWithStringNameArgRetLong", "Long", "0L"),
    (("StringName",), "PackedStringArray"): CallShape(
        "ptrcallWithStringNameArgRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("StringName",), "TypedStringNameArray"): CallShape(
        "ptrcallWithStringNameArgRetStringNameList",
        "List<String>",
        "emptyList()",
    ),
    (("StringName",), "TypedNodeArray"): CallShape(
        "ptrcallWithStringNameArgRetTypedNodeList",
        "List<Node>",
        "emptyList()",
    ),
    (("StringName",), "TypedDictionaryArray"): CallShape(
        "ptrcallWithStringNameArgRetDictionaryList",
        "List<Map<String, Any?>>",
        "emptyList()",
    ),
    (("StringName",), "Object"): CallShape("ptrcallWithStringNameArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("StringName", "StringName"), "void"): CallShape("ptrcallWithTwoStringNameArgs", "Unit"),
    (("StringName", "StringName"), "bool"): CallShape(
        "ptrcallWithTwoStringNameArgsRetBool",
        "Boolean",
        "false",
    ),
    (("StringName", "StringName"), "int32"): CallShape("ptrcallWithTwoStringNameArgsRetInt", "Int", "0"),
    (("StringName", "StringName"), "int64"): CallShape("ptrcallWithTwoStringNameArgsRetLong", "Long", "0L"),
    (("StringName", "StringName"), "String"): CallShape("ptrcallWithTwoStringNameArgsRetString", "String", '""'),
    (("StringName", "StringName"), "StringName"): CallShape(
        "ptrcallWithTwoStringNameArgsRetStringName",
        "String",
        '""',
    ),
    (("StringName", "StringName"), "RID"): CallShape("ptrcallWithTwoStringNameArgsRetRID", "RID", "RID.EMPTY"),
    (("StringName", "StringName"), "float"): CallShape(
        "ptrcallWithTwoStringNameArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("StringName", "StringName"), "Dictionary"): CallShape(
        "ptrcallWithTwoStringNameArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("StringName", "StringName"), "Variant"): CallShape(
        "ptrcallWithTwoStringNameArgsRetVariantScalar",
        "Any?",
        "null",
    ),
    (("StringName", "StringName"), "Color"): CallShape(
        "ptrcallWithTwoStringNameArgsRetColor",
        "Color",
        "Color(0f, 0f, 0f, 0f)",
    ),
    (("StringName", "StringName"), "Object"): CallShape(
        "ptrcallWithTwoStringNameArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("StringName", "StringName", "Color"), "void"): CallShape("ptrcallWithTwoStringNameAndColorArg", "Unit"),
    (("StringName", "StringName", "int32"), "void"): CallShape("ptrcallWithTwoStringNameAndIntArg", "Unit"),
    (("StringName", "StringName", "float"), "void"): CallShape("ptrcallWithTwoStringNameAndDoubleArg", "Unit"),
    (("StringName", "StringName", "uint32"), "Vector2i"): CallShape(
        "ptrcallWithTwoStringNameUInt32ArgRetVector2i",
        "Vector2i",
        "Vector2i.ZERO",
    ),
    (("StringName", "StringName", "uint32", "uint32", "uint32", "uint32"), "RID"): CallShape(
        "ptrcallWithTwoStringNameFourUInt32ArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("StringName", "StringName", "Object", "Object", "bool"), "RID"): CallShape(
        "ptrcallWithTwoStringNameTwoObjectBoolArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("StringName", "StringName", "StringName", "Object"), "RID"): CallShape(
        "ptrcallWithThreeStringNameObjectArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("StringName", "StringName", "uint32", "uint32", "uint32", "uint32", "Object"), "RID"): CallShape(
        "ptrcallWithTwoStringNameFourUInt32ObjectArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("StringName", "StringName", "enum", "uint32", "enum", "Vector2i", "uint32", "uint32", "bool", "bool"), "RID"): CallShape(
        "ptrcallWithTwoStringNameLongUInt32LongVector2iTwoUInt32TwoBoolArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("StringName", "StringName", "bool"), "bool"): CallShape(
        "ptrcallWithTwoStringNameAndBoolArgsRetBool",
        "Boolean",
        "false",
    ),
    (("StringName", "bool", "bool"), "bool"): CallShape(
        "ptrcallWithStringNameAndTwoBoolArgsRetBool",
        "Boolean",
        "false",
    ),
    (("StringName", "StringName", "bool"), "int32"): CallShape(
        "ptrcallWithTwoStringNameAndBoolArgsRetInt",
        "Int",
        "0",
    ),
    (("StringName", "StringName", "bool"), "StringName"): CallShape(
        "ptrcallWithTwoStringNameAndBoolArgsRetStringName",
        "String",
        '""',
    ),
    (("StringName", "Object", "bool"), "bool"): CallShape(
        "ptrcallWithStringNameObjectAndBoolArgRetBool",
        "Boolean",
        "false",
    ),
    (("StringName", "Object"), "bool"): CallShape("ptrcallWithStringNameAndObjectArgRetBool", "Boolean", "false"),
    (("StringName", "Object"), "enum"): CallShape("ptrcallWithStringNameAndObjectArgRetLong", "Long", "0L"),
    (("StringName", "Object", "int32"), "void"): CallShape("ptrcallWithStringNameObjectIntArgs", "Unit"),
    (("StringName", "Object", "Vector2"), "void"): CallShape("ptrcallWithStringNameObjectAndVector2Arg", "Unit"),
    (("StringName", "enum", "Variant"), "void"): CallShape("ptrcallWithStringNameLongVariantArgs", "Unit"),
    (("StringName", "Object", "float", "int32"), "void"): CallShape(
        "ptrcallWithStringNameObjectDoubleIntArgs",
        "Unit",
    ),
    (("StringName", "StringName", "int32", "StringName"), "StringName"): CallShape(
        "ptrcallWithTwoStringNameIntStringNameArgsRetStringName",
        "String",
        '""',
    ),
    (("StringName", "StringName", "int32", "StringName"), "String"): CallShape(
        "ptrcallWithTwoStringNameIntStringNameArgsRetString",
        "String",
        '""',
    ),
    (("StringName", "StringName", "StringName"), "void"): CallShape("ptrcallWithThreeStringNameArgs", "Unit"),
    (("enum", "StringName", "StringName"), "void"): CallShape("ptrcallWithLongAndTwoStringNameArgs", "Unit"),
    (("enum", "StringName", "StringName"), "bool"): CallShape(
        "ptrcallWithLongAndTwoStringNameArgsRetBool",
        "Boolean",
        "false",
    ),
    (("enum", "StringName", "StringName", "StringName"), "void"): CallShape(
        "ptrcallWithLongAndThreeStringNameArgs",
        "Unit",
    ),
    (("StringName", "StringName", "StringName", "float"), "void"): CallShape(
        "ptrcallWithThreeStringNameAndDoubleArg",
        "Unit",
    ),
    (("StringName", "StringName", "StringName", "float", "float", "bool"), "void"): CallShape(
        "ptrcallWithThreeStringNameTwoDoubleBoolArgs",
        "Unit",
    ),
    (("StringName", "Transform3D", "Vector3", "Vector3", "enum"), "void"): CallShape(
        "ptrcallWithStringNameTransform3DTwoVector3LongArgs",
        "Unit",
    ),
    (("StringName", "PackedStringArray", "StringName"), "void"): CallShape(
        "ptrcallWithStringNamePackedStringListAndStringNameArgs",
        "Unit",
    ),
    (("StringName", "int32", "StringName"), "void"): CallShape("ptrcallWithStringNameIntAndStringNameArgs", "Unit"),
    (("StringName", "int32", "StringName", "int32"), "void"): CallShape(
        "ptrcallWithStringNameIntStringNameIntArgs",
        "Unit",
    ),
    (("StringName", "int32", "StringName", "int32"), "bool"): CallShape(
        "ptrcallWithStringNameIntStringNameIntArgsRetBool",
        "Boolean",
        "false",
    ),
    (("StringName", "int32", "StringName", "int32", "bool"), "enum"): CallShape(
        "ptrcallWithStringNameIntStringNameIntBoolArgsRetLong",
        "Long",
        "0L",
    ),
    (("StringName", "int32", "StringName", "int32", "float"), "void"): CallShape(
        "ptrcallWithStringNameIntStringNameIntDoubleArgs",
        "Unit",
    ),
    (("StringName", "StringName", "StringName", "StringName", "float"), "Vector2"): CallShape(
        "ptrcallWithFourStringNameAndFloatArgRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("StringName", "float", "float", "bool"), "void"): CallShape(
        "ptrcallWithStringNameDoubleDoubleBoolArgs",
        "Unit",
    ),
    (("StringName", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithStringNameAndThreeDoubleArgs",
        "Unit",
    ),
    (("StringName", "float", "float", "float", "float", "bool"), "void"): CallShape(
        "ptrcallWithStringNameFourDoubleBoolArgs",
        "Unit",
    ),
    (("StringName", "float", "float", "float", "bool", "enum", "enum"), "void"): CallShape(
        "ptrcallWithStringNameThreeDoubleBoolTwoLongArgs",
        "Unit",
    ),
    (("StringName", "StringName", "Object"), "void"): CallShape("ptrcallWithTwoStringNameAndObjectArg", "Unit"),
    (("StringName", "StringName", "bool"), "PackedStringArray"): CallShape(
        "ptrcallWithTwoStringNameAndBoolArgsRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("StringName", "StringName", "bool"), "void"): CallShape("ptrcallWithTwoStringNameAndBoolArgs", "Unit"),
    (("StringName", "bool"), "String"): CallShape("ptrcallWithStringNameAndBoolArgRetString", "String", '""'),
    (("NodePath",), "void"): CallShape("ptrcallWithNodePathArg", "Unit"),
    (("NodePath",), "bool"): CallShape("ptrcallWithNodePathArgRetBool", "Boolean", "false"),
    (("NodePath",), "int32"): CallShape("ptrcallWithNodePathArgRetInt", "Int", "0"),
    (("NodePath",), "enum"): CallShape("ptrcallWithNodePathArgRetLong", "Long", "0L"),
    (("NodePath",), "Object"): CallShape("ptrcallWithNodePathArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("NodePath",), "Array"): CallShape("ptrcallWithNodePathArgRetArray", "List<Any?>", "emptyList()"),
    (("NodePath", "StringName"), "void"): CallShape("ptrcallWithNodePathStringNameArgs", "Unit"),
    (("NodePath", "PackedFloat32Array"), "void"): CallShape("ptrcallWithNodePathAndPackedFloat32ListArgs", "Unit"),
    (("String", "String", "int32"), "void"): CallShape("ptrcallWithTwoStringAndIntArgs", "Unit"),
    (("String", "String", "int32", "float", "float", "int64", "bool"), "void"): CallShape(
        "ptrcallWithTwoStringIntTwoDoubleLongBoolArgs",
        "Unit",
    ),
    (("String", "String"), "void"): CallShape("ptrcallWithTwoStringArgs", "Unit"),
    (("String", "String"), "bool"): CallShape("ptrcallWithTwoStringArgsRetBool", "Boolean", "false"),
    (("String", "String"), "int32"): CallShape("ptrcallWithTwoStringArgsRetInt", "Int", "0"),
    (("String", "String"), "String"): CallShape("ptrcallWithTwoStringArgsRetString", "String", '""'),
    (("String", "String"), "enum"): CallShape("ptrcallWithTwoStringArgsRetLong", "Long", "0L"),
    (("String", "String"), "Dictionary"): CallShape("ptrcallWithTwoStringArgsRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("String", "Variant", "Variant"), "Dictionary"): CallShape(
        "ptrcallWithStringAndTwoVariantArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("String", "String"), "PackedInt32Array"): CallShape(
        "ptrcallWithTwoStringArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("String", "bool", "String"), "Object"): CallShape(
        "ptrcallWithStringBoolStringArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("String", "String", "int32"), "enum"): CallShape("ptrcallWithTwoStringAndIntArgsRetLong", "Long", "0L"),
    (("String", "String", "bool"), "enum"): CallShape("ptrcallWithTwoStringBoolArgsRetLong", "Long", "0L"),
    (("String", "String", "Color", "bool"), "void"): CallShape("ptrcallWithTwoStringColorBoolArgs", "Unit"),
    (("String", "String", "Object", "Object"), "void"): CallShape("ptrcallWithTwoStringTwoObjectArgs", "Unit"),
    (("String", "int32", "int32", "int32", "int32", "int32"), "enum"): CallShape(
        "ptrcallWithStringAndFiveIntArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "String"), "PackedByteArray"): CallShape(
        "ptrcallWithTwoStringArgsRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("String", "String", "PackedByteArray"), "enum"): CallShape(
        "ptrcallWithTwoStringAndByteArrayArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "String", "bool", "bool"), "TypedNodeArray"): CallShape(
        "ptrcallWithTwoStringAndTwoBoolArgsRetTypedNodeList",
        "List<Node>",
        "emptyList()",
    ),
    (("String", "String", "enum"), "Object"): CallShape(
        "ptrcallWithTwoStringAndLongArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("String", "String", "String"), "void"): CallShape("ptrcallWithThreeStringArgs", "Unit"),
    (("String", "String", "String"), "enum"): CallShape("ptrcallWithThreeStringArgsRetLong", "Long", "0L"),
    (("String", "String", "String", "String", "String", "enum"), "void"): CallShape(
        "ptrcallWithStringStringStringStringStringLongArgs",
        "Unit",
    ),
    (("String", "String", "String", "int64", "int64"), "Dictionary"): CallShape(
        "ptrcallWithThreeStringTwoLongArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("String", "Object", "bool"), "int32"): CallShape("ptrcallWithStringObjectBoolArgsRetInt", "Int", "0"),
    (("String", "Object", "int32"), "void"): CallShape("ptrcallWithStringObjectIntArgs", "Unit"),
    (("String", "Object", "uint32", "String"), "enum"): CallShape(
        "ptrcallWithStringObjectUInt32StringArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "Object", "bool", "Color"), "void"): CallShape("ptrcallWithStringObjectBoolColorArgs", "Unit"),
    (("String", "int32", "String"), "enum"): CallShape("ptrcallWithStringIntStringArgsRetLong", "Long", "0L"),
    (("String", "StringName", "int32", "StringName"), "String"): CallShape(
        "ptrcallWithStringStringNameIntStringNameArgsRetString",
        "String",
        '""',
    ),
    (("String", "int32", "Object"), "enum"): CallShape("ptrcallWithStringIntObjectArgsRetLong", "Long", "0L"),
    (("String", "String", "PackedStringArray", "String", "int32"), "int64"): CallShape(
        "ptrcallWithTwoStringPackedStringListStringIntArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "Array"), "enum"): CallShape("ptrcallWithStringAndArrayArgRetLong", "Long", "0L"),
    (("String", "String", "PackedStringArray", "String", "String"), "enum"): CallShape(
        "ptrcallWithTwoStringPackedStringListAndTwoStringArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "String", "PackedStringArray", "String", "Array", "int32"), "enum"): CallShape(
        "ptrcallWithTwoStringPackedStringListStringArrayIntArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "String", "String", "int32"), "int32"): CallShape(
        "ptrcallWithThreeStringAndIntArgRetInt",
        "Int",
        "0",
    ),
    (("String", "Array"), "void"): CallShape("ptrcallWithStringAndArrayArg", "Unit"),
    (("String", "bool", "Array"), "void"): CallShape("ptrcallWithStringBoolArrayArgs", "Unit"),
    (("String", "Dictionary"), "Object"): CallShape(
        "ptrcallWithStringAndDictionaryArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("String", "String", "bool"), "void"): CallShape("ptrcallWithTwoStringAndBoolArgs", "Unit"),
    (("String", "String", "bool", "bool"), "void"): CallShape("ptrcallWithTwoStringAndTwoBoolArgs", "Unit"),
    (("String", "String", "bool", "enum"), "enum"): CallShape(
        "ptrcallWithTwoStringBoolLongArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "String", "bool", "int32", "int32"), "String"): CallShape(
        "ptrcallWithTwoStringBoolTwoIntArgsRetString",
        "String",
        '""',
    ),
    (("String", "String", "String", "String", "int32", "int32", "bool"), "PackedStringArray"): CallShape(
        "ptrcallWithFourStringTwoIntBoolArgsRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("String", "String", "int64"), "PackedInt32Array"): CallShape(
        "ptrcallWithTwoStringAndLongArgRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("String", "PackedByteArray"), "enum"): CallShape("ptrcallWithStringAndByteArrayArgRetLong", "Long", "0L"),
    (("String", "PackedStringArray"), "enum"): CallShape("ptrcallWithStringAndPackedStringListArgRetLong", "Long", "0L"),
    (("String", "PackedStringArray"), "int64"): CallShape("ptrcallWithStringAndPackedStringListArgRetLong", "Long", "0L"),
    (("String", "PackedStringArray", "enum", "PackedByteArray"), "enum"): CallShape(
        "ptrcallWithStringPackedStringListLongByteArrayArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "PackedStringArray", "enum", "String"), "enum"): CallShape(
        "ptrcallWithStringPackedStringListLongStringArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "PackedStringArray", "Array", "bool", "bool"), "int32"): CallShape(
        "ptrcallWithStringPackedStringListArrayTwoBoolArgsRetInt",
        "Int",
        "0",
    ),
    (("String", "PackedStringArray", "bool"), "int32"): CallShape(
        "ptrcallWithStringPackedStringListBoolArgsRetInt",
        "Int",
        "0",
    ),
    (("String", "PackedStringArray", "int32"), "void"): CallShape("ptrcallWithStringPackedStringListAndIntArgs", "Unit"),
    (("String", "PackedStringArray", "String"), "void"): CallShape("ptrcallWithStringPackedStringListAndStringArgs", "Unit"),
    (("String", "PackedStringArray", "Object"), "void"): CallShape("ptrcallWithStringPackedStringListAndObjectArgs", "Unit"),
    (("String", "Transform3D"), "void"): CallShape("ptrcallWithStringAndTransform3DArg", "Unit"),
    (("String", "PackedByteArray", "bool"), "void"): CallShape("ptrcallWithStringByteArrayBoolArgs", "Unit"),
    (("String", "RID"), "RID"): CallShape("ptrcallWithStringAndRIDArgRetRID", "RID", "RID.EMPTY"),
    (("String", "int32"), "void"): CallShape("ptrcallWithStringAndIntArg", "Unit"),
    (("String", "int32"), "String"): CallShape("ptrcallWithStringAndIntArgRetString", "String", '""'),
    (("String", "int32", "String"), "void"): CallShape("ptrcallWithStringIntAndStringArgs", "Unit"),
    (("String", "int32", "String", "bool"), "enum"): CallShape(
        "ptrcallWithStringIntStringBoolArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "int32", "int32"), "void"): CallShape("ptrcallWithStringIntAndIntArgs", "Unit"),
    (("String", "int32", "int32", "bool", "bool"), "void"): CallShape("ptrcallWithStringTwoIntTwoBoolArgs", "Unit"),
    (("String", "int32", "enum"), "void"): CallShape("ptrcallWithStringIntAndLongArgs", "Unit"),
    (("String", "int32", "int32", "int32", "enum"), "void"): CallShape("ptrcallWithStringThreeIntLongArgs", "Unit"),
    (("String", "int32"), "bool"): CallShape("ptrcallWithStringAndIntArgRetBool", "Boolean", "false"),
    (("String", "int32"), "int32"): CallShape("ptrcallWithStringAndIntArgRetInt", "Int", "0"),
    (("String", "int32"), "enum"): CallShape("ptrcallWithStringAndIntArgRetLong", "Long", "0L"),
    (("String", "int32"), "Vector2i"): CallShape("ptrcallWithStringAndIntArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("String", "int32"), "Object"): CallShape("ptrcallWithStringAndIntArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("String", "int32", "int32"), "Object"): CallShape(
        "ptrcallWithStringAndTwoIntArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("String", "int32", "Object"), "void"): CallShape("ptrcallWithStringIntAndObjectArgs", "Unit"),
    (("String", "int32", "int32", "int32"), "Object"): CallShape(
        "ptrcallWithStringAndThreeIntArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("String", "int64"), "enum"): CallShape("ptrcallWithStringAndLongArgRetLong", "Long", "0L"),
    (("String", "enum"), "enum"): CallShape("ptrcallWithStringAndLongArgRetLong", "Long", "0L"),
    (("String", "enum"), "String"): CallShape("ptrcallWithStringAndLongArgRetString", "String", '""'),
    (("String", "enum"), "int32"): CallShape("ptrcallWithStringAndLongArgRetInt", "Int", "0"),
    (("String", "bitfield"), "enum"): CallShape("ptrcallWithStringAndLongArgRetLong", "Long", "0L"),
    (("String", "enum"), "PackedStringArray"): CallShape(
        "ptrcallWithStringAndLongArgRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("String", "enum", "float", "int32", "bitfield", "enum", "enum"), "Vector2"): CallShape(
        "ptrcallWithStringLongDoubleIntThreeLongArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("String", "enum", "float", "int32", "int32", "bitfield", "bitfield", "enum", "enum"), "Vector2"): CallShape(
        "ptrcallWithStringLongDoubleTwoIntFourLongArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("String", "float"), "enum"): CallShape("ptrcallWithStringAndDoubleArgRetLong", "Long", "0L"),
    (("String", "float", "float", "Dictionary"), "Object"): CallShape(
        "ptrcallWithStringTwoDoubleDictionaryArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("String", "float", "float", "float", "float"), "void"): CallShape("ptrcallWithStringFourDoubleArgs", "Unit"),
    (("String", "StringName", "float", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithStringStringNameFourDoubleArgs",
        "Unit",
    ),
    (("String", "int32", "bool"), "void"): CallShape("ptrcallWithStringIntAndBoolArgs", "Unit"),
    (("String", "int32"), "Variant"): CallShape("ptrcallWithStringAndIntArgRetVariantScalar", "Any?", "null"),
    (("String", "int32", "Variant"), "void"): CallShape("ptrcallWithStringIntAndVariantArg", "Unit"),
    (("String", "int32", "int32", "bool"), "String"): CallShape(
        "ptrcallWithStringTwoIntBoolArgsRetString",
        "String",
        '""',
    ),
    (("String", "Object"), "void"): CallShape("ptrcallWithStringAndObjectArg", "Unit"),
    (("String", "Object"), "bool"): CallShape("ptrcallWithStringAndObjectArgRetBool", "Boolean", "false"),
    (("String", "Object"), "enum"): CallShape("ptrcallWithStringAndObjectArgRetLong", "Long", "0L"),
    (("String", "Object"), "Object"): CallShape("ptrcallWithStringAndObjectArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("String", "enum", "Object", "bool", "bool"), "void"): CallShape("ptrcallWithStringLongObjectTwoBoolArgs", "Unit"),
    (("String", "bool", "Object"), "void"): CallShape("ptrcallWithStringBoolObjectArgs", "Unit"),
    (("String", "Object", "bool", "String"), "void"): CallShape("ptrcallWithStringObjectBoolStringArgs", "Unit"),
    (("String", "Object", "Object"), "void"): CallShape("ptrcallWithStringAndTwoObjectArgs", "Unit"),
    (("String", "Object", "int32", "String", "Variant"), "bool"): CallShape(
        "ptrcallWithStringObjectIntStringVariantArgsRetBool",
        "Boolean",
        "false",
    ),
    (("String", "Object", "int32", "Rect2", "String"), "bool"): CallShape(
        "ptrcallWithStringObjectIntRect2StringArgsRetBool",
        "Boolean",
        "false",
    ),
    (("String", "Object", "int32", "Rect2", "Color", "int32", "Color"), "void"): CallShape(
        "ptrcallWithStringObjectIntRect2ColorIntColorArgs",
        "Unit",
    ),
    (("String", "Color"), "void"): CallShape("ptrcallWithStringAndColorArg", "Unit"),
    (("String", "Color", "bool", "bool", "bool"), "void"): CallShape("ptrcallWithStringColorThreeBoolArgs", "Unit"),
    (("String", "enum", "bool"), "void"): CallShape("ptrcallWithStringLongBoolArgs", "Unit"),
    (("String", "enum", "String"), "void"): CallShape("ptrcallWithStringLongStringArgs", "Unit"),
    (("String", "enum", "enum"), "void"): CallShape("ptrcallWithStringTwoLongArgs", "Unit"),
    (("String", "enum", "enum"), "Dictionary"): CallShape(
        "ptrcallWithStringTwoLongArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("String", "Variant"), "void"): CallShape("ptrcallWithStringAndVariantArg", "Unit"),
    (("String", "Variant"), "int32"): CallShape("ptrcallWithStringAndVariantArgRetInt", "Int", "0"),
    (("String", "Variant"), "Dictionary"): CallShape(
        "ptrcallWithStringAndVariantArgRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("String", "Variant"), "Variant"): CallShape(
        "ptrcallWithStringAndVariantArgRetVariantScalar",
        "Any?",
        "null",
    ),
    (("Object", "String", "int32"), "void"): CallShape("ptrcallWithObjectStringAndIntArgs", "Unit"),
    (("Object", "Rect2"), "void"): CallShape("ptrcallWithObjectAndRect2Arg", "Unit"),
    (("Object", "Rect2", "bool", "Color", "bool"), "void"): CallShape(
        "ptrcallWithObjectRect2BoolColorBoolArgs",
        "Unit",
    ),
    (("Object", "Rect2", "Rect2", "Color"), "void"): CallShape(
        "ptrcallWithObjectTwoRect2AndColorArgs",
        "Unit",
    ),
    (("Object", "Rect2", "Rect2", "Color", "bool", "bool"), "void"): CallShape(
        "ptrcallWithObjectTwoRect2ColorTwoBoolArgs",
        "Unit",
    ),
    (("Object", "Rect2", "Rect2", "Color", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithObjectTwoRect2ColorThreeDoubleArgs",
        "Unit",
    ),
    (("String", "bool"), "void"): CallShape("ptrcallWithStringAndBoolArg", "Unit"),
    (("String", "bool"), "bool"): CallShape("ptrcallWithStringAndBoolArgRetBool", "Boolean", "false"),
    (("String", "bool"), "int64"): CallShape("ptrcallWithStringAndBoolArgRetLong", "Long", "0L"),
    (("String", "bool"), "int32"): CallShape("ptrcallWithStringAndBoolArgRetInt", "Int", "0"),
    (("String", "bool"), "enum"): CallShape("ptrcallWithStringAndBoolArgRetLong", "Long", "0L"),
    (("String", "bool"), "String"): CallShape("ptrcallWithStringAndBoolArgRetString", "String", '""'),
    (("String", "bool", "float"), "enum"): CallShape("ptrcallWithStringBoolDoubleArgsRetLong", "Long", "0L"),
    (("String", "bool"), "PackedByteArray"): CallShape("ptrcallWithStringAndBoolArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("String", "bool"), "Object"): CallShape("ptrcallWithStringAndBoolArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("String", "bool"), "Variant"): CallShape("ptrcallWithStringAndBoolArgRetVariantScalar", "Any?", "null"),
    (("String", "bool"), "Dictionary"): CallShape(
        "ptrcallWithStringAndBoolArgRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("String", "bool", "bool"), "Object"): CallShape(
        "ptrcallWithStringAndTwoBoolArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("String", "bool", "int64"), "bool"): CallShape("ptrcallWithStringBoolLongArgsRetBool", "Boolean", "false"),
    (("String", "bool", "int32"), "bool"): CallShape("ptrcallWithStringBoolIntArgsRetBool", "Boolean", "false"),
    (("bool",), "void"): CallShape("ptrcallWithBoolArg", "Unit"),
    (("bool",), "int32"): CallShape("ptrcallWithBoolArgRetInt", "Int", "0"),
    (("bool",), "int64"): CallShape("ptrcallWithBoolArgRetLong", "Long", "0L"),
    (("bool",), "enum"): CallShape("ptrcallWithBoolArgRetLong", "Long", "0L"),
    (("bool",), "bool"): CallShape("ptrcallWithBoolArgRetBool", "Boolean", "false"),
    (("bool",), "String"): CallShape("ptrcallWithBoolArgRetString", "String", '""'),
    (("bool",), "RID"): CallShape("ptrcallWithBoolArgRetRID", "RID", "RID.EMPTY"),
    (("bool",), "PackedByteArray"): CallShape("ptrcallWithBoolArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("bool",), "PackedInt32Array"): CallShape("ptrcallWithBoolArgRetPackedInt32List", "List<Int>", "emptyList()"),
    (("bool", "PackedStringArray"), "void"): CallShape("ptrcallWithBoolAndPackedStringArrayArgs", "Unit"),
    (("bool",), "Variant"): CallShape("ptrcallWithBoolArgRetVariantScalar", "Any?", "null"),
    (("bool",), "Array"): CallShape("ptrcallWithBoolArgRetArray", "List<Any?>", "emptyList()"),
    (("bool",), "Dictionary"): CallShape("ptrcallWithBoolArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("bool",), "TypedNodeArray"): CallShape("ptrcallWithBoolArgRetTypedNodeList", "List<Node>", "emptyList()"),
    (("bool", "Object"), "Object"): CallShape(
        "ptrcallWithBoolObjectArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("bool", "float"), "void"): CallShape("ptrcallWithBoolAndDoubleArgs", "Unit"),
    (("bool", "float"), "PackedByteArray"): CallShape(
        "ptrcallWithBoolAndDoubleArgRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("Array",), "void"): CallShape("ptrcallWithArrayArg", "Unit"),
    (("Array",), "enum"): CallShape("ptrcallWithArrayArgRetLong", "Long", "0L"),
    (("Array", "enum"), "void"): CallShape("ptrcallWithArrayLongArgs", "Unit"),
    (("Array", "Transform3D"), "void"): CallShape("ptrcallWithArrayTransform3DArgs", "Unit"),
    (("enum", "Array", "TypedArrayArray", "Dictionary", "bitfield"), "void"): CallShape(
        "ptrcallWithLongArrayArrayListDictionaryLongArgs",
        "Unit",
    ),
    (("enum", "Array", "TypedArrayArray", "Dictionary", "Object", "String", "int64"), "void"): CallShape(
        "ptrcallWithLongArrayArrayListDictionaryObjectStringLongArgs",
        "Unit",
    ),
    (("Dictionary",), "void"): CallShape("ptrcallWithDictionaryArg", "Unit"),
    (("Dictionary",), "int64"): CallShape("ptrcallWithDictionaryArgRetLong", "Long", "0L"),
    (("Dictionary",), "enum"): CallShape("ptrcallWithDictionaryArgRetLong", "Long", "0L"),
    (("Dictionary",), "String"): CallShape("ptrcallWithDictionaryArgRetString", "String", '""'),
    (("Dictionary",), "Object"): CallShape("ptrcallWithDictionaryArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("Dictionary", "bool"), "String"): CallShape("ptrcallWithDictionaryAndBoolArgRetString", "String", '""'),
    (("Dictionary", "int32", "float", "Transform2D", "int32", "int32", "int32", "int32", "float"), "RID"): CallShape(
        "ptrcallWithDictionaryIntDoubleTransform2DFourIntDoubleArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("bool", "int32"), "void"): CallShape("ptrcallWithBoolAndIntArgs", "Unit"),
    (("bool", "int32"), "bool"): CallShape("ptrcallWithBoolAndIntArgsRetBool", "Boolean", "false"),
    (("bool", "int32"), "enum"): CallShape("ptrcallWithBoolAndIntArgsRetLong", "Long", "0L"),
    (("bool", "bool"), "void"): CallShape("ptrcallWithTwoBoolArgs", "Unit"),
    (("bool", "bool"), "String"): CallShape("ptrcallWithTwoBoolArgsRetString", "String", '""'),
    (("int64",), "void"): CallShape("ptrcallWithLongArg", "Unit"),
    (("int32",), "void"): CallShape("ptrcallWithIntArg", "Unit"),
    (("uint32",), "void"): CallShape("ptrcallWithUInt32Arg", "Unit"),
    (("enum",), "void"): CallShape("ptrcallWithLongArg", "Unit"),
    (("bitfield",), "void"): CallShape("ptrcallWithLongArg", "Unit"),
    (("int64",), "bool"): CallShape("ptrcallWithLongArgRetBool", "Boolean", "false"),
    (("int32",), "bool"): CallShape("ptrcallWithIntArgRetBool", "Boolean", "false"),
    (("uint32",), "bool"): CallShape("ptrcallWithUInt32ArgRetBool", "Boolean", "false"),
    (("enum",), "bool"): CallShape("ptrcallWithLongArgRetBool", "Boolean", "false"),
    (("bitfield",), "bool"): CallShape("ptrcallWithLongArgRetBool", "Boolean", "false"),
    (("int64",), "int64"): CallShape("ptrcallWithLongArgRetLong", "Long", "0L"),
    (("uint32",), "int64"): CallShape("ptrcallWithUInt32ArgRetLong", "Long", "0L"),
    (("int64",), "int32"): CallShape("ptrcallWithLongArgRetInt", "Int", "0"),
    (("int64",), "uint32"): CallShape("ptrcallWithLongArgRetUInt32", "Long", "0L"),
    (("uint32",), "int32"): CallShape("ptrcallWithUInt32ArgRetInt", "Int", "0"),
    (("int64",), "enum"): CallShape("ptrcallWithLongArgRetLong", "Long", "0L"),
    (("int64",), "RID"): CallShape("ptrcallWithLongArgRetRID", "RID", "RID.EMPTY"),
    (("int32",), "int64"): CallShape("ptrcallWithIntArgRetLong", "Long", "0L"),
    (("int32",), "int32"): CallShape("ptrcallWithIntArgRetInt", "Int", "0"),
    (("int32",), "uint32"): CallShape("ptrcallWithIntArgRetUInt32", "Long", "0L"),
    (("enum",), "int64"): CallShape("ptrcallWithLongArgRetLong", "Long", "0L"),
    (("enum",), "int32"): CallShape("ptrcallWithLongArgRetInt", "Int", "0"),
    (("enum",), "enum"): CallShape("ptrcallWithLongArgRetLong", "Long", "0L"),
    (("bitfield",), "int64"): CallShape("ptrcallWithLongArgRetLong", "Long", "0L"),
    (("bitfield",), "int32"): CallShape("ptrcallWithLongArgRetInt", "Int", "0"),
    (("int32",), "enum"): CallShape("ptrcallWithIntArgRetLong", "Long", "0L"),
    (("int32",), "bitfield"): CallShape("ptrcallWithIntArgRetLong", "Long", "0L"),
    (("int32",), "Color"): CallShape("ptrcallWithIntArgRetColor", "Color", "Color(0f, 0f, 0f, 0f)"),
    (("int64",), "float"): CallShape("ptrcallWithLongArgRetDouble", "Double", "0.0"),
    (("int32",), "float"): CallShape("ptrcallWithIntArgRetDouble", "Double", "0.0"),
    (("enum",), "float"): CallShape("ptrcallWithLongArgRetDouble", "Double", "0.0"),
    (("bitfield",), "float"): CallShape("ptrcallWithLongArgRetDouble", "Double", "0.0"),
    (("int64",), "String"): CallShape("ptrcallWithLongArgRetString", "String", '""'),
    (("uint32",), "String"): CallShape("ptrcallWithUInt32ArgRetString", "String", '""'),
    (("uint32",), "float"): CallShape("ptrcallWithUInt32ArgRetDouble", "Double", "0.0"),
    (("uint32",), "Vector3"): CallShape("ptrcallWithUInt32ArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("uint32",), "Transform2D"): CallShape("ptrcallWithUInt32ArgRetTransform2D", "Transform2D", "Transform2D.IDENTITY"),
    (("uint32",), "Transform3D"): CallShape("ptrcallWithUInt32ArgRetTransform3D", "Transform3D", "Transform3D.IDENTITY"),
    (("uint32",), "Projection"): CallShape("ptrcallWithUInt32ArgRetProjection", "Projection", "Projection.IDENTITY"),
    (("uint32", "Transform3D"), "Transform3D"): CallShape(
        "ptrcallWithUInt32Transform3DArgsRetTransform3D",
        "Transform3D",
        "Transform3D.IDENTITY",
    ),
    (("uint32", "float", "float", "float"), "Projection"): CallShape(
        "ptrcallWithUInt32ThreeDoubleArgsRetProjection",
        "Projection",
        "Projection.IDENTITY",
    ),
    (("int64",), "NodePath"): CallShape("ptrcallWithLongArgRetNodePath", "NodePath", "NodePath.EMPTY"),
    (("int32",), "String"): CallShape("ptrcallWithIntArgRetString", "String", '""'),
    (("int32",), "StringName"): CallShape("ptrcallWithIntArgRetStringName", "String", '""'),
    (("int32",), "Variant"): CallShape("ptrcallWithIntArgRetVariantScalar", "Any?", "null"),
    (("int32",), "NodePath"): CallShape("ptrcallWithIntArgRetNodePath", "NodePath", "NodePath.EMPTY"),
    (("int32",), "Vector3i"): CallShape("ptrcallWithIntArgRetVector3i", "Vector3i", "Vector3i.ZERO"),
    (("int32",), "PackedInt32Array"): CallShape("ptrcallWithIntArgRetPackedInt32List", "List<Int>", "emptyList()"),
    (("int32",), "PackedFloat32Array"): CallShape(
        "ptrcallWithIntArgRetPackedFloat32List",
        "List<Float>",
        "emptyList()",
    ),
    (("int32",), "PackedByteArray"): CallShape("ptrcallWithIntArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("int32",), "PackedStringArray"): CallShape(
        "ptrcallWithIntArgRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("int32",), "TypedVector2iArray"): CallShape("ptrcallWithIntArgRetVector2iList", "List<Vector2i>", "emptyList()"),
    (("int32",), "TypedVector3iArray"): CallShape("ptrcallWithIntArgRetVector3iList", "List<Vector3i>", "emptyList()"),
    (("int32",), "TypedStringNameArray"): CallShape("ptrcallWithIntArgRetStringNameList", "List<String>", "emptyList()"),
    (("int32",), "TypedArrayArray"): CallShape("ptrcallWithIntArgRetArrayList", "List<List<Any?>>", "emptyList()"),
    (("enum",), "String"): CallShape("ptrcallWithLongArgRetString", "String", '""'),
    (("bitfield",), "String"): CallShape("ptrcallWithLongArgRetString", "String", '""'),
    (("int64",), "Object"): CallShape("ptrcallWithLongArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("int32",), "Object"): CallShape("ptrcallWithIntArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("uint32",), "Object"): CallShape("ptrcallWithUInt32ArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("Object",), "uint32"): CallShape("ptrcallWithObjectArgRetUInt32", "Long", "0L"),
    (("enum",), "Object"): CallShape("ptrcallWithLongArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("bitfield",), "Object"): CallShape("ptrcallWithLongArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("int64",), "PackedByteArray"): CallShape("ptrcallWithLongArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("enum",), "PackedByteArray"): CallShape("ptrcallWithLongArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("enum",), "PackedInt32Array"): CallShape("ptrcallWithLongArgRetPackedInt32List", "List<Int>", "emptyList()"),
    (("enum",), "PackedStringArray"): CallShape("ptrcallWithLongArgRetPackedStringList", "List<String>", "emptyList()"),
    (("int64",), "Dictionary"): CallShape("ptrcallWithLongArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("enum",), "TypedDictionaryArray"): CallShape(
        "ptrcallWithLongArgRetDictionaryList",
        "List<Map<String, Any?>>",
        "emptyList()",
    ),
    (("int64",), "Transform2D"): CallShape("ptrcallWithLongArgRetTransform2D", "Transform2D", "Transform2D.IDENTITY"),
    (("int64",), "Projection"): CallShape("ptrcallWithLongArgRetProjection", "Projection", "Projection.IDENTITY"),
    (("bitfield",), "PackedStringArray"): CallShape("ptrcallWithLongArgRetPackedStringList", "List<String>", "emptyList()"),
    (("int32",), "RID"): CallShape("ptrcallWithIntArgRetRID", "RID", "RID.EMPTY"),
    (("int64",), "Vector2"): CallShape("ptrcallWithLongArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("int32",), "Vector2"): CallShape("ptrcallWithIntArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("enum",), "Vector2"): CallShape("ptrcallWithLongArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("bitfield",), "Vector2"): CallShape("ptrcallWithLongArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("int64",), "Vector2i"): CallShape("ptrcallWithLongArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("int64",), "Transform3D"): CallShape("ptrcallWithLongArgRetTransform3D", "Transform3D", "Transform3D.IDENTITY"),
    (("int32",), "Vector2i"): CallShape("ptrcallWithIntArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("enum",), "Vector2i"): CallShape("ptrcallWithLongArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("bitfield",), "Vector2i"): CallShape("ptrcallWithLongArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("enum",), "NodePath"): CallShape("ptrcallWithLongArgRetNodePath", "NodePath", "NodePath.EMPTY"),
    (("int32",), "Vector3"): CallShape("ptrcallWithIntArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("int32",), "Basis"): CallShape("ptrcallWithIntArgRetBasis", "Basis", "Basis.IDENTITY"),
    (("int32",), "Plane"): CallShape("ptrcallWithIntArgRetPlane", "Plane", "Plane(Vector3.ZERO, 0f)"),
    (("int32",), "Quaternion"): CallShape("ptrcallWithIntArgRetQuaternion", "Quaternion", "Quaternion.IDENTITY"),
    (("int32",), "Rect2"): CallShape("ptrcallWithIntArgRetRect2", "Rect2", "Rect2.ZERO"),
    (("int32",), "Rect2i"): CallShape("ptrcallWithIntArgRetRect2i", "Rect2i", "Rect2i.ZERO"),
    (("int32",), "Transform2D"): CallShape("ptrcallWithIntArgRetTransform2D", "Transform2D", "Transform2D.IDENTITY"),
    (("int32", "Transform2D"), "void"): CallShape("ptrcallWithIntAndTransform2DArg", "Unit"),
    (("int32", "Transform2D", "float", "bool"), "void"): CallShape(
        "ptrcallWithIntTransform2DDoubleBoolArgs",
        "Unit",
    ),
    (("int32",), "Transform3D"): CallShape("ptrcallWithIntArgRetTransform3D", "Transform3D", "Transform3D.IDENTITY"),
    (("int64", "Transform2D"), "void"): CallShape("ptrcallWithLongAndTransform2DArg", "Unit"),
    (("int64", "Transform3D"), "void"): CallShape("ptrcallWithLongAndTransform3DArg", "Unit"),
    (("int64", "Transform3D"), "Transform3D"): CallShape(
        "ptrcallWithLongAndTransform3DArgRetTransform3D",
        "Transform3D",
        "Transform3D.IDENTITY",
    ),
    (("int64", "bool"), "String"): CallShape("ptrcallWithLongAndBoolArgRetString", "String", '""'),
    (("int64", "bool"), "RID"): CallShape("ptrcallWithLongAndBoolArgRetRID", "RID", "RID.EMPTY"),
    (("int64", "bool"), "void"): CallShape("ptrcallWithLongAndBoolArgs", "Unit"),
    (("int64", "String"), "void"): CallShape("ptrcallWithLongAndStringArg", "Unit"),
    (("int64", "int64", "String"), "void"): CallShape("ptrcallWithTwoLongStringArgs", "Unit"),
    (("int64", "String"), "enum"): CallShape("ptrcallWithLongAndStringArgRetLong", "Long", "0L"),
    (("int64", "String"), "PackedStringArray"): CallShape(
        "ptrcallWithLongAndStringArgRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("enum", "uint32"), "bool"): CallShape("ptrcallWithLongAndUInt32ArgRetBool", "Boolean", "false"),
    (("int64", "uint32"), "enum"): CallShape("ptrcallWithLongAndUInt32ArgRetLong", "Long", "0L"),
    (("enum",), "RID"): CallShape("ptrcallWithLongArgRetRID", "RID", "RID.EMPTY"),
    (("int64", "RID"), "void"): CallShape("ptrcallWithLongAndRIDArg", "Unit"),
    (("RID",), "void"): CallShape("ptrcallWithRIDArg", "Unit"),
    (("RID",), "bool"): CallShape("ptrcallWithRIDArgRetBool", "Boolean", "false"),
    (("RID",), "int64"): CallShape("ptrcallWithRIDArgRetLong", "Long", "0L"),
    (("RID",), "int32"): CallShape("ptrcallWithRIDArgRetInt", "Int", "0"),
    (("RID",), "uint32"): CallShape("ptrcallWithRIDArgRetUInt32", "Long", "0L"),
    (("RID",), "enum"): CallShape("ptrcallWithRIDArgRetLong", "Long", "0L"),
    (("RID",), "bitfield"): CallShape("ptrcallWithRIDArgRetLong", "Long", "0L"),
    (("RID",), "float"): CallShape("ptrcallWithRIDArgRetDouble", "Double", "0.0"),
    (("RID",), "RID"): CallShape("ptrcallWithRIDArgRetRID", "RID", "RID.EMPTY"),
    (("RID",), "Object"): CallShape("ptrcallWithRIDArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("RID",), "String"): CallShape("ptrcallWithRIDArgRetString", "String", '""'),
    (("RID",), "Variant"): CallShape("ptrcallWithRIDArgRetVariantScalar", "Any?", "null"),
    (("RID",), "PackedByteArray"): CallShape("ptrcallWithRIDArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("RID",), "PackedFloat32Array"): CallShape("ptrcallWithRIDArgRetPackedFloat32List", "List<Float>", "emptyList()"),
    (("RID",), "PackedStringArray"): CallShape("ptrcallWithRIDArgRetPackedStringList", "List<String>", "emptyList()"),
    (("RID",), "Vector2"): CallShape("ptrcallWithRIDArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("RID",), "Vector3"): CallShape("ptrcallWithRIDArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("RID",), "Vector3i"): CallShape("ptrcallWithRIDArgRetVector3i", "Vector3i", "Vector3i.ZERO"),
    (("RID",), "Vector2i"): CallShape("ptrcallWithRIDArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("RID",), "Rect2"): CallShape("ptrcallWithRIDArgRetRect2", "Rect2", "Rect2.ZERO"),
    (("RID",), "AABB"): CallShape("ptrcallWithRIDArgRetAABB", "AABB", "AABB.ZERO"),
    (("RID",), "Transform2D"): CallShape("ptrcallWithRIDArgRetTransform2D", "Transform2D", "Transform2D.IDENTITY"),
    (("RID",), "Transform3D"): CallShape("ptrcallWithRIDArgRetTransform3D", "Transform3D", "Transform3D.IDENTITY"),
    (("RID",), "PackedInt32Array"): CallShape("ptrcallWithRIDArgRetPackedInt32List", "List<Int>", "emptyList()"),
    (("RID",), "PackedVector2Array"): CallShape(
        "ptrcallWithRIDArgRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("RID",), "PackedVector3Array"): CallShape(
        "ptrcallWithRIDArgRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("RID",), "PackedColorArray"): CallShape("ptrcallWithRIDArgRetPackedColorList", "List<Color>", "emptyList()"),
    (("RID",), "TypedRIDArray"): CallShape("ptrcallWithRIDArgRetRIDList", "List<RID>", "emptyList()"),
    (("RID",), "TypedVector2iArray"): CallShape("ptrcallWithRIDArgRetVector2iList", "List<Vector2i>", "emptyList()"),
    (("RID", "bool"), "void"): CallShape("ptrcallWithRIDAndBoolArg", "Unit"),
    (("uint32", "bool"), "void"): CallShape("ptrcallWithUInt32AndBoolArgs", "Unit"),
    (("uint32", "bool"), "RID"): CallShape("ptrcallWithUInt32AndBoolArgRetRID", "RID", "RID.EMPTY"),
    (("RID", "bool"), "RID"): CallShape("ptrcallWithRIDAndBoolArgRetRID", "RID", "RID.EMPTY"),
    (("RID", "bool"), "int64"): CallShape("ptrcallWithRIDAndBoolArgRetLong", "Long", "0L"),
    (("RID", "bool", "Rect2"), "void"): CallShape("ptrcallWithRIDBoolRect2Args", "Unit"),
    (("RID", "Vector2", "Color", "bool"), "void"): CallShape("ptrcallWithRIDVector2ColorBoolArgs", "Unit"),
    (("RID", "Rect2", "bool", "Color", "bool"), "void"): CallShape(
        "ptrcallWithRIDRect2BoolColorBoolArgs",
        "Unit",
    ),
    (("RID", "Rect2", "Rect2", "Color", "bool", "bool"), "void"): CallShape(
        "ptrcallWithRIDTwoRect2ColorTwoBoolArgs",
        "Unit",
    ),
    (("RID", "float"), "void"): CallShape("ptrcallWithRIDAndDoubleArg", "Unit"),
    (("RID", "float", "float"), "void"): CallShape("ptrcallWithRIDAndTwoDoubleArgs", "Unit"),
    (("RID", "int64"), "void"): CallShape("ptrcallWithRIDAndLongArg", "Unit"),
    (("RID", "uint32"), "void"): CallShape("ptrcallWithRIDAndUInt32Arg", "Unit"),
    (("RID", "uint32"), "bool"): CallShape("ptrcallWithRIDAndUInt32ArgRetBool", "Boolean", "false"),
    (("RID", "uint32"), "String"): CallShape("ptrcallWithRIDAndUInt32ArgRetString", "String", '""'),
    (("RID", "uint32"), "Transform3D"): CallShape(
        "ptrcallWithRIDAndUInt32ArgRetTransform3D",
        "Transform3D",
        "Transform3D.IDENTITY",
    ),
    (("RID", "uint32", "bool"), "Vector2"): CallShape(
        "ptrcallWithRIDUInt32BoolArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("RID", "uint32", "bool"), "Vector3"): CallShape(
        "ptrcallWithRIDUInt32BoolArgsRetVector3",
        "Vector3",
        "Vector3.ZERO",
    ),
    (("RID", "uint32", "uint32"), "RID"): CallShape(
        "ptrcallWithRIDAndTwoUInt32ArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("RID", "uint32", "uint32"), "enum"): CallShape("ptrcallWithRIDAndTwoUInt32ArgsRetLong", "Long", "0L"),
    (("RID", "uint32", "uint32"), "PackedByteArray"): CallShape(
        "ptrcallWithRIDAndTwoUInt32ArgsRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("RID", "uint32", "PackedByteArray"), "enum"): CallShape("ptrcallWithRIDUInt32ByteArrayArgsRetLong", "Long", "0L"),
    (("RID", "uint32"), "PackedByteArray"): CallShape(
        "ptrcallWithRIDAndUInt32ArgRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("RID", "int64"), "float"): CallShape("ptrcallWithRIDAndLongArgRetDouble", "Double", "0.0"),
    (("RID", "int64"), "int64"): CallShape("ptrcallWithRIDAndLongArgRetLong", "Long", "0L"),
    (("RID", "int64"), "int32"): CallShape("ptrcallWithRIDAndLongArgRetInt", "Int", "0"),
    (("RID", "int64"), "enum"): CallShape("ptrcallWithRIDAndLongArgRetLong", "Long", "0L"),
    (("RID", "int64"), "Vector2i"): CallShape("ptrcallWithRIDAndLongArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("RID", "int64"), "Vector2"): CallShape("ptrcallWithRIDAndLongArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("RID", "int64"), "bool"): CallShape("ptrcallWithRIDAndLongArgRetBool", "Boolean", "false"),
    (("RID", "int64"), "RID"): CallShape("ptrcallWithRIDAndLongArgRetRID", "RID", "RID.EMPTY"),
    (("RID", "int64"), "String"): CallShape("ptrcallWithRIDAndLongArgRetString", "String", '""'),
    (("RID", "int64"), "Variant"): CallShape("ptrcallWithRIDAndLongArgRetVariantScalar", "Any?", "null"),
    (("RID", "int32"), "Variant"): CallShape("ptrcallWithRIDAndIntArgRetVariantScalar", "Any?", "null"),
    (("RID", "int64"), "Dictionary"): CallShape("ptrcallWithRIDAndLongArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("RID", "int64"), "PackedByteArray"): CallShape(
        "ptrcallWithRIDAndLongArgRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("RID", "int64", "PackedByteArray"), "enum"): CallShape(
        "ptrcallWithRIDLongByteArrayArgsRetLong",
        "Long",
        "0L",
    ),
    (("RID", "int64"), "PackedFloat32Array"): CallShape(
        "ptrcallWithRIDAndLongArgRetPackedFloat32List",
        "List<Float>",
        "emptyList()",
    ),
    (("RID", "int64"), "PackedInt32Array"): CallShape(
        "ptrcallWithRIDAndLongArgRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("RID", "Vector2i"), "PackedInt32Array"): CallShape(
        "ptrcallWithRIDAndVector2iArgRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("RID", "Vector2i"), "int64"): CallShape("ptrcallWithRIDAndVector2iArgRetLong", "Long", "0L"),
    (("RID", "Vector2i", "int64"), "Object"): CallShape(
        "ptrcallWithRIDVector2iLongArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("RID", "Vector2i", "int64", "Object"), "void"): CallShape("ptrcallWithRIDVector2iLongObjectArgs", "Unit"),
    (("RID", "int64"), "PackedVector2Array"): CallShape(
        "ptrcallWithRIDAndLongArgRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("RID", "int64"), "PackedVector3Array"): CallShape(
        "ptrcallWithRIDAndLongArgRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("RID", "int64", "int64", "int64"), "int64"): CallShape("ptrcallWithRIDAndThreeLongArgsRetLong", "Long", "0L"),
    (("RID", "int32"), "TypedArrayArray"): CallShape(
        "ptrcallWithRIDAndIntArgRetArrayList",
        "List<List<Any?>>",
        "emptyList()",
    ),
    (("RID", "int32"), "Array"): CallShape("ptrcallWithRIDAndIntArgRetArray", "List<Any?>", "emptyList()"),
    (("RID", "int64", "int64"), "enum"): CallShape("ptrcallWithRIDAndTwoLongArgsRetLong", "Long", "0L"),
    (("RID", "int64", "int64"), "int64"): CallShape("ptrcallWithRIDAndTwoLongArgsRetLong", "Long", "0L"),
    (("RID", "int64", "int64"), "Dictionary"): CallShape(
        "ptrcallWithRIDAndTwoLongArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("RID", "int64", "int64"), "Vector2"): CallShape(
        "ptrcallWithRIDAndTwoLongArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("RID", "int64", "int64"), "PackedVector2Array"): CallShape(
        "ptrcallWithRIDAndTwoLongArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("RID", "int64", "int64"), "PackedByteArray"): CallShape(
        "ptrcallWithRIDAndTwoLongArgsRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("RID", "enum", "int32"), "RID"): CallShape("ptrcallWithRIDLongIntArgsRetRID", "RID", "RID.EMPTY"),
    (("RID", "bool", "bool", "bool"), "void"): CallShape("ptrcallWithRIDAndThreeBoolArgs", "Unit"),
    (("RID", "RID", "float", "int32", "bool"), "RID"): CallShape(
        "ptrcallWithTwoRIDDoubleIntBoolArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("RID", "RID", "int32", "RID", "int32"), "void"): CallShape("ptrcallWithTwoRIDIntRIDIntArgs", "Unit"),
    (("RID", "int32"), "void"): CallShape("ptrcallWithRIDAndIntArg", "Unit"),
    (("RID", "int32"), "bool"): CallShape("ptrcallWithRIDAndIntArgRetBool", "Boolean", "false"),
    (("RID", "int32"), "int32"): CallShape("ptrcallWithRIDAndIntArgRetInt", "Int", "0"),
    (("RID", "int32"), "RID"): CallShape("ptrcallWithRIDAndIntArgRetRID", "RID", "RID.EMPTY"),
    (("RID", "int32"), "Object"): CallShape("ptrcallWithRIDAndIntArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("RID", "int32"), "String"): CallShape("ptrcallWithRIDAndIntArgRetString", "String", '""'),
    (("RID", "int32"), "Dictionary"): CallShape("ptrcallWithRIDAndIntArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("RID", "int32"), "Vector2"): CallShape("ptrcallWithRIDAndIntArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("RID", "int32"), "Vector3"): CallShape("ptrcallWithRIDAndIntArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("RID", "int32"), "Color"): CallShape("ptrcallWithRIDAndIntArgRetColor", "Color", "Color(0f, 0f, 0f, 0f)"),
    (("RID", "int32", "Color"), "void"): CallShape("ptrcallWithRIDIntAndColorArgs", "Unit"),
    (("RID", "int32"), "Transform2D"): CallShape(
        "ptrcallWithRIDAndIntArgRetTransform2D",
        "Transform2D",
        "Transform2D.IDENTITY",
    ),
    (("RID", "int32"), "Transform3D"): CallShape(
        "ptrcallWithRIDAndIntArgRetTransform3D",
        "Transform3D",
        "Transform3D.IDENTITY",
    ),
    (("RID", "int64"), "Transform3D"): CallShape(
        "ptrcallWithRIDAndLongArgRetTransform3D",
        "Transform3D",
        "Transform3D.IDENTITY",
    ),
    (("RID", "enum"), "void"): CallShape("ptrcallWithRIDAndLongArg", "Unit"),
    (("RID", "enum"), "int64"): CallShape("ptrcallWithRIDAndLongArgRetLong", "Long", "0L"),
    (("RID", "enum"), "bool"): CallShape("ptrcallWithRIDAndLongArgRetBool", "Boolean", "false"),
    (("RID", "enum"), "RID"): CallShape("ptrcallWithRIDAndLongArgRetRID", "RID", "RID.EMPTY"),
    (("RID", "enum", "int64"), "void"): CallShape("ptrcallWithRIDAndTwoLongArgs", "Unit"),
    (("RID", "enum", "enum"), "void"): CallShape("ptrcallWithRIDAndTwoLongArgs", "Unit"),
    (("RID", "enum", "enum"), "int32"): CallShape("ptrcallWithRIDAndTwoLongArgsRetInt", "Int", "0"),
    (("RID", "enum", "RID"), "void"): CallShape("ptrcallWithRIDLongAndRIDArgs", "Unit"),
    (("enum", "RID", "int64"), "int64"): CallShape("ptrcallWithLongRIDLongArgsRetLong", "Long", "0L"),
    (("RID", "bitfield"), "void"): CallShape("ptrcallWithRIDAndLongArg", "Unit"),
    (("RID", "enum"), "Variant"): CallShape("ptrcallWithRIDAndLongArgRetVariantScalar", "Any?", "null"),
    (("RID", "bitfield"), "Variant"): CallShape("ptrcallWithRIDAndLongArgRetVariantScalar", "Any?", "null"),
    (("RID", "String"), "void"): CallShape("ptrcallWithRIDAndStringArg", "Unit"),
    (("RID", "String"), "bool"): CallShape("ptrcallWithRIDAndStringArgRetBool", "Boolean", "false"),
    (("RID", "String"), "int32"): CallShape("ptrcallWithRIDAndStringArgRetInt", "Int", "0"),
    (("RID", "String", "RID", "Variant", "int32"), "int32"): CallShape(
        "ptrcallWithRIDStringRIDVariantIntArgsRetInt",
        "Int",
        "0",
    ),
    (("RID", "RID"), "int32"): CallShape("ptrcallWithTwoRIDArgsRetInt", "Int", "0"),
    (("RID", "RID"), "enum"): CallShape("ptrcallWithTwoRIDArgsRetLong", "Long", "0L"),
    (("RID", "RID"), "RID"): CallShape("ptrcallWithTwoRIDArgsRetRID", "RID", "RID.EMPTY"),
    (("RID", "Object"), "void"): CallShape("ptrcallWithRIDAndObjectArg", "Unit"),
    (("RID", "Object", "Object"), "bool"): CallShape("ptrcallWithRIDAndTwoObjectArgsRetBool", "Boolean", "false"),
    (("RID", "Object", "int32"), "void"): CallShape("ptrcallWithRIDObjectIntArgs", "Unit"),
    (("RID", "String", "bool"), "void"): CallShape("ptrcallWithRIDStringAndBoolArgs", "Unit"),
    (("RID", "StringName"), "Variant"): CallShape("ptrcallWithRIDAndStringNameArgRetVariantScalar", "Any?", "null"),
    (("RID", "Vector2"), "void"): CallShape("ptrcallWithRIDAndVector2Arg", "Unit"),
    (("RID", "Vector2"), "bool"): CallShape("ptrcallWithRIDAndVector2ArgRetBool", "Boolean", "false"),
    (("RID", "Vector2"), "RID"): CallShape("ptrcallWithRIDAndVector2ArgRetRID", "RID", "RID.EMPTY"),
    (("RID", "Vector2"), "Vector2"): CallShape("ptrcallWithRIDAndVector2ArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("RID", "Vector2", "Vector2"), "void"): CallShape("ptrcallWithRIDAndTwoVector2Args", "Unit"),
    (("RID", "Vector2", "RID", "RID"), "void"): CallShape("ptrcallWithRIDVector2TwoRIDArgs", "Unit"),
    (("RID", "Vector2", "Vector2", "RID", "RID"), "void"): CallShape("ptrcallWithRIDTwoVector2TwoRIDArgs", "Unit"),
    (("RID", "Vector2", "Vector2", "Vector2", "RID", "RID"), "void"): CallShape(
        "ptrcallWithRIDThreeVector2TwoRIDArgs",
        "Unit",
    ),
    (("RID", "Vector2", "Vector2", "bool", "int64"), "PackedVector2Array"): CallShape(
        "ptrcallWithRIDTwoVector2BoolLongArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("RID", "Vector2", "Vector2", "bool", "uint32"), "PackedVector2Array"): CallShape(
        "ptrcallWithRIDTwoVector2BoolUInt32ArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("RID", "Vector3"), "void"): CallShape("ptrcallWithRIDAndVector3Arg", "Unit"),
    (("RID", "Vector3"), "bool"): CallShape("ptrcallWithRIDAndVector3ArgRetBool", "Boolean", "false"),
    (("RID", "Vector3"), "RID"): CallShape("ptrcallWithRIDAndVector3ArgRetRID", "RID", "RID.EMPTY"),
    (("RID", "Vector3", "Vector3"), "void"): CallShape("ptrcallWithRIDAndTwoVector3Args", "Unit"),
    (("RID", "Vector3", "Vector3", "bool"), "Vector3"): CallShape(
        "ptrcallWithRIDTwoVector3BoolArgsRetVector3",
        "Vector3",
        "Vector3.ZERO",
    ),
    (("RID", "Vector3"), "Vector3"): CallShape("ptrcallWithRIDAndVector3ArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("RID", "Vector3", "Vector3", "bool", "int64"), "PackedVector3Array"): CallShape(
        "ptrcallWithRIDTwoVector3BoolLongArgsRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("RID", "Vector3", "Vector3", "bool", "uint32"), "PackedVector3Array"): CallShape(
        "ptrcallWithRIDTwoVector3BoolUInt32ArgsRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("RID", "float", "Vector2", "float", "float"), "void"): CallShape(
        "ptrcallWithRIDDoubleVector2TwoDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2i"), "void"): CallShape("ptrcallWithRIDAndVector2iArg", "Unit"),
    (("RID", "int64", "Vector2i"), "void"): CallShape("ptrcallWithRIDLongVector2iArgs", "Unit"),
    (("RID", "int64", "Vector2i"), "Vector2"): CallShape(
        "ptrcallWithRIDLongVector2iArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("RID", "int64", "Vector2i", "Vector2"), "void"): CallShape(
        "ptrcallWithRIDLongVector2iAndVector2Args",
        "Unit",
    ),
    (("RID", "int64", "int64", "Vector2"), "void"): CallShape("ptrcallWithRIDTwoLongAndVector2Args", "Unit"),
    (("RID", "int32"), "enum"): CallShape("ptrcallWithRIDAndIntArgRetLong", "Long", "0L"),
    (("RID", "int32", "enum"), "void"): CallShape("ptrcallWithRIDIntLongArgs", "Unit"),
    (("RID", "int32", "Object"), "void"): CallShape("ptrcallWithRIDIntAndObjectArg", "Unit"),
    (("RID", "Vector2i", "int64"), "void"): CallShape("ptrcallWithRIDVector2iLongArgs", "Unit"),
    (("RID", "Vector2i", "int64"), "Vector2"): CallShape(
        "ptrcallWithRIDVector2iLongArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("RID", "Vector2i", "int64"), "PackedInt32Array"): CallShape(
        "ptrcallWithRIDVector2iLongArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("RID", "Vector2i", "int64", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithRIDVector2iLongPackedInt32ListArgs",
        "Unit",
    ),
    (("RID", "Vector2i", "int64"), "Rect2"): CallShape(
        "ptrcallWithRIDVector2iLongArgsRetRect2",
        "Rect2",
        "Rect2.ZERO",
    ),
    (("RID", "Vector2i", "int64"), "int64"): CallShape(
        "ptrcallWithRIDVector2iLongArgsRetLong",
        "Long",
        "0L",
    ),
    (("RID", "Vector2i", "int64"), "RID"): CallShape(
        "ptrcallWithRIDVector2iLongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("RID", "Vector2i", "int64", "Vector2"), "void"): CallShape("ptrcallWithRIDVector2iLongVector2Args", "Unit"),
    (("RID", "Vector2i", "int64", "Rect2"), "void"): CallShape("ptrcallWithRIDVector2iLongRect2Args", "Unit"),
    (("RID", "Vector2i", "int64", "int64"), "void"): CallShape("ptrcallWithRIDVector2iTwoLongArgs", "Unit"),
    (("RID", "Transform2D"), "void"): CallShape("ptrcallWithRIDAndTransform2DArg", "Unit"),
    (("RID", "Transform3D"), "void"): CallShape("ptrcallWithRIDAndTransform3DArg", "Unit"),
    (("RID", "AABB"), "void"): CallShape("ptrcallWithRIDAndAABBArg", "Unit"),
    (("RID", "Rect2"), "void"): CallShape("ptrcallWithRIDAndRect2Arg", "Unit"),
    (("RID", "Color"), "void"): CallShape("ptrcallWithRIDAndColorArg", "Unit"),
    (("RID", "PackedByteArray"), "void"): CallShape("ptrcallWithRIDAndByteArrayArg", "Unit"),
    (("RID", "PackedFloat32Array"), "void"): CallShape("ptrcallWithRIDAndPackedFloat32ListArg", "Unit"),
    (("RID", "PackedFloat32Array"), "float"): CallShape(
        "ptrcallWithRIDAndPackedFloat32ListArgRetDouble",
        "Double",
        "0.0",
    ),
    (("RID", "PackedFloat32Array", "int64", "bool", "bitfield"), "PackedInt32Array"): CallShape(
        "ptrcallWithRIDPackedFloat32ListLongBoolLongArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("RID", "PackedFloat32Array", "PackedFloat32Array"), "void"): CallShape(
        "ptrcallWithRIDAndTwoPackedFloat32ListArgs",
        "Unit",
    ),
    (("RID", "float", "bitfield"), "void"): CallShape("ptrcallWithRIDDoubleAndLongArgs", "Unit"),
    (("RID", "float", "bitfield"), "float"): CallShape(
        "ptrcallWithRIDDoubleAndLongArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("RID", "float", "int64", "bitfield"), "PackedInt32Array"): CallShape(
        "ptrcallWithRIDDoubleTwoLongArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("RID", "bitfield", "bitfield"), "PackedInt32Array"): CallShape(
        "ptrcallWithRIDAndTwoLongArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("RID", "PackedVector2Array"), "void"): CallShape("ptrcallWithRIDAndPackedVector2ListArg", "Unit"),
    (("RID", "PackedVector2Array", "bool"), "void"): CallShape(
        "ptrcallWithRIDPackedVector2ListAndBoolArg",
        "Unit",
    ),
    (("RID", "PackedVector3Array"), "void"): CallShape("ptrcallWithRIDAndPackedVector3ListArg", "Unit"),
    (("RID", "RID"), "void"): CallShape("ptrcallWithTwoRIDArgs", "Unit"),
    (("RID", "RID", "bool"), "void"): CallShape("ptrcallWithTwoRIDBoolArgs", "Unit"),
    (("RID", "RID", "RID"), "void"): CallShape("ptrcallWithThreeRIDArgs", "Unit"),
    (("RID", "TypedRIDArray"), "void"): CallShape("ptrcallWithRIDAndRIDListArgs", "Unit"),
    (("RID", "TypedRIDArray", "PackedInt64Array", "Object"), "RID"): CallShape(
        "ptrcallWithRIDRIDListPackedInt64ListObjectArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("PackedByteArray",), "void"): CallShape("ptrcallWithByteArrayArg", "Unit"),
    (("PackedByteArray",), "bool"): CallShape("ptrcallWithByteArrayArgRetBool", "Boolean", "false"),
    (("PackedByteArray",), "enum"): CallShape("ptrcallWithByteArrayArgRetLong", "Long", "0L"),
    (("PackedByteArray",), "Object"): CallShape("ptrcallWithByteArrayArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("PackedByteArray",), "String"): CallShape("ptrcallWithByteArrayArgRetString", "String", '""'),
    (("PackedByteArray",), "Array"): CallShape("ptrcallWithByteArrayArgRetArray", "List<Any?>", "emptyList()"),
    (("PackedByteArray", "bool"), "int32"): CallShape("ptrcallWithByteArrayAndBoolArgRetInt", "Int", "0"),
    (("PackedByteArray", "RID"), "RID"): CallShape("ptrcallWithByteArrayAndRIDArgRetRID", "RID", "RID.EMPTY"),
    (("PackedByteArray", "String", "String"), "void"): CallShape("ptrcallWithByteArrayTwoStringArgs", "Unit"),
    (("PackedByteArray", "enum"), "enum"): CallShape("ptrcallWithByteArrayAndLongArgRetLong", "Long", "0L"),
    (("PackedByteArray", "int32", "enum", "int32"), "enum"): CallShape(
        "ptrcallWithByteArrayIntLongIntArgsRetLong",
        "Long",
        "0L",
    ),
    (("PackedByteArray", "float"), "enum"): CallShape("ptrcallWithByteArrayAndDoubleArgRetLong", "Long", "0L"),
    (("PackedByteArray", "Dictionary"), "Object"): CallShape(
        "ptrcallWithByteArrayAndDictionaryArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("PackedFloat32Array", "bool"), "float"): CallShape(
        "ptrcallWithPackedFloat32ListAndBoolArgRetDouble",
        "Double",
        "0.0",
    ),
    (("PackedByteArray",), "PackedByteArray"): CallShape(
        "ptrcallWithByteArrayArgRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("PackedByteArray", "PackedByteArray"), "bool"): CallShape(
        "ptrcallWithTwoByteArrayArgsRetBool",
        "Boolean",
        "false",
    ),
    (("enum", "PackedByteArray", "PackedByteArray"), "enum"): CallShape(
        "ptrcallWithLongAndTwoByteArrayArgsRetLong",
        "Long",
        "0L",
    ),
    (("enum", "PackedByteArray", "PackedByteArray"), "PackedByteArray"): CallShape(
        "ptrcallWithLongAndTwoByteArrayArgsRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("enum", "PackedByteArray", "Object"), "PackedByteArray"): CallShape(
        "ptrcallWithLongByteArrayObjectArgsRetByteArray",
        "ByteArray",
        "ByteArray(0)",
    ),
    (("uint32", "PackedByteArray", "bitfield"), "RID"): CallShape(
        "ptrcallWithUInt32ByteArrayLongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("uint32", "enum", "PackedByteArray"), "RID"): CallShape(
        "ptrcallWithUInt32LongByteArrayArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("enum", "PackedByteArray", "PackedByteArray", "Object"), "bool"): CallShape(
        "ptrcallWithLongTwoByteArrayObjectArgsRetBool",
        "Boolean",
        "false",
    ),
    (("enum", "PackedByteArray"), "enum"): CallShape("ptrcallWithLongAndByteArrayArgRetLong", "Long", "0L"),
    (("enum", "PackedByteArray"), "void"): CallShape("ptrcallWithLongAndByteArrayArg", "Unit"),
    (("PackedStringArray",), "void"): CallShape("ptrcallWithPackedStringListArg", "Unit"),
    (("PackedStringArray",), "int32"): CallShape("ptrcallWithPackedStringListArgRetInt", "Int", "0"),
    (("PackedStringArray",), "Dictionary"): CallShape("ptrcallWithPackedStringListArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("String", "PackedStringArray", "bool"), "Dictionary"): CallShape(
        "ptrcallWithStringPackedStringListBoolArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("PackedStringArray", "String"), "bool"): CallShape(
        "ptrcallWithPackedStringListAndStringArgsRetBool",
        "Boolean",
        "false",
    ),
    (("PackedInt32Array",), "void"): CallShape("ptrcallWithPackedInt32ListArg", "Unit"),
    (("PackedInt64Array",), "void"): CallShape("ptrcallWithPackedInt64ListArg", "Unit"),
    (("PackedFloat32Array",), "void"): CallShape("ptrcallWithPackedFloat32ListArg", "Unit"),
    (("PackedFloat32Array",), "bool"): CallShape("ptrcallWithPackedFloat32ListArgRetBool", "Boolean", "false"),
    (("PackedFloat32Array",), "int64"): CallShape("ptrcallWithPackedFloat32ListArgRetLong", "Long", "0L"),
    (("PackedFloat32Array", "PackedFloat32Array"), "void"): CallShape("ptrcallWithTwoPackedFloat32ListArgs", "Unit"),
    (("PackedFloat32Array", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithPackedFloat32ListAndPackedInt32ListArgs",
        "Unit",
    ),
    (("enum", "enum", "String", "enum", "bitfield", "PackedFloat32Array"), "void"): CallShape(
        "ptrcallWithTwoLongStringTwoLongPackedFloat32ListArgs",
        "Unit",
    ),
    (("int32", "PackedFloat32Array"), "void"): CallShape("ptrcallWithIntAndPackedFloat32ListArgs", "Unit"),
    (("PackedFloat64Array",), "void"): CallShape("ptrcallWithPackedFloat64ListArg", "Unit"),
    (("PackedVector2Array",), "void"): CallShape("ptrcallWithPackedVector2ListArg", "Unit"),
    (("PackedVector2Array",), "bool"): CallShape("ptrcallWithPackedVector2ListArgRetBool", "Boolean", "false"),
    (("PackedVector2Array",), "Dictionary"): CallShape(
        "ptrcallWithPackedVector2ListArgRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("PackedVector2Array",), "PackedVector2Array"): CallShape(
        "ptrcallWithPackedVector2ListArgRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("PackedVector2Array", "bool"), "void"): CallShape("ptrcallWithPackedVector2ListAndBoolArg", "Unit"),
    (("PackedVector2Array", "float"), "PackedVector2Array"): CallShape(
        "ptrcallWithPackedVector2ListAndDoubleArgRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("PackedVector2Array",), "PackedInt32Array"): CallShape(
        "ptrcallWithPackedVector2ListArgRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("PackedVector2Array", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithPackedVector2ListAndPackedInt32ListArgs",
        "Unit",
    ),
    (("Transform3D", "PackedVector2Array", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithTransform3DPackedVector2ListPackedInt32ListArgs",
        "Unit",
    ),
    (("PackedVector2Array",), "TypedPackedVector2Array"): CallShape(
        "ptrcallWithPackedVector2ListArgRetPackedVector2ListList",
        "List<List<Vector2>>",
        "emptyList()",
    ),
    (("PackedVector2Array", "int32"), "void"): CallShape("ptrcallWithPackedVector2ListAndIntArgs", "Unit"),
    (("PackedVector2Array", "PackedVector2Array"), "TypedPackedVector2Array"): CallShape(
        "ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList",
        "List<List<Vector2>>",
        "emptyList()",
    ),
    (("PackedVector2Array", "Color", "float", "bool"), "void"): CallShape(
        "ptrcallWithPackedVector2ListColorDoubleAndBoolArgs",
        "Unit",
    ),
    (("PackedVector2Array", "PackedColorArray", "float", "bool"), "void"): CallShape(
        "ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs",
        "Unit",
    ),
    (("PackedVector2Array", "Color", "PackedVector2Array", "Object"), "void"): CallShape(
        "ptrcallWithPackedVector2ListColorPackedVector2ListAndObjectArgs",
        "Unit",
    ),
    (("PackedVector2Array", "PackedColorArray", "PackedVector2Array", "Object"), "void"): CallShape(
        "ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs",
        "Unit",
    ),
    (("RID", "PackedVector2Array", "PackedColorArray", "float", "bool"), "void"): CallShape(
        "ptrcallWithRIDPackedVector2ListPackedColorListDoubleAndBoolArgs",
        "Unit",
    ),
    (("RID", "PackedVector2Array", "PackedColorArray", "PackedVector2Array", "RID"), "void"): CallShape(
        "ptrcallWithRIDPackedVector2ListPackedColorListPackedVector2ListAndRIDArgs",
        "Unit",
    ),
    (("PackedVector3Array",), "void"): CallShape("ptrcallWithPackedVector3ListArg", "Unit"),
    (("PackedVector3Array",), "bool"): CallShape("ptrcallWithPackedVector3ListArgRetBool", "Boolean", "false"),
    (("PackedVector3Array",), "PackedInt32Array"): CallShape(
        "ptrcallWithPackedVector3ListArgRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("PackedVector3Array", "Plane"), "PackedVector3Array"): CallShape(
        "ptrcallWithPackedVector3ListAndPlaneArgRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("PackedVector3Array", "float"), "PackedVector3Array"): CallShape(
        "ptrcallWithPackedVector3ListAndDoubleArgRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("PackedVector3Array", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithPackedVector3ListAndPackedInt32ListArgs",
        "Unit",
    ),
    (("RID", "PackedVector3Array", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithRIDPackedVector3ListPackedInt32ListArgs",
        "Unit",
    ),
    (("RID", "PackedVector3Array", "PackedColorArray", "PackedInt32Array", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithRIDPackedVector3ListPackedColorListTwoPackedInt32ListArgs",
        "Unit",
    ),
    (("RID", "bool", "PackedFloat32Array", "float", "float", "float", "float", "enum", "float", "float", "float", "float", "RID"), "void"): CallShape(
        "ptrcallWithRIDBoolPackedFloat32ListFourDoubleLongFourDoubleRIDArgs",
        "Unit",
    ),
    (("RID", "PackedInt32Array", "PackedVector2Array", "PackedColorArray", "PackedVector2Array", "PackedInt32Array", "PackedFloat32Array", "RID", "int32"), "void"): CallShape(
        "ptrcallWithRIDPackedInt32ListPackedVector2ListPackedColorListPackedVector2ListPackedInt32ListPackedFloat32ListRIDIntArgs",
        "Unit",
    ),
    (("PackedVector3Array", "Object", "bool", "Color"), "void"): CallShape(
        "ptrcallWithPackedVector3ListObjectBoolColorArgs",
        "Unit",
    ),
    (("PackedVector3Array", "Object", "PackedInt32Array", "bool", "bool"), "void"): CallShape(
        "ptrcallWithPackedVector3ListObjectPackedInt32ListTwoBoolArgs",
        "Unit",
    ),
    (("PackedVector3Array", "Transform3D"), "void"): CallShape("ptrcallWithPackedVector3ListAndTransform3DArg", "Unit"),
    (("PackedVector3Array", "float", "float", "bool"), "void"): CallShape(
        "ptrcallWithPackedVector3ListTwoDoubleAndBoolArgs",
        "Unit",
    ),
    (("PackedColorArray",), "void"): CallShape("ptrcallWithPackedColorListArg", "Unit"),
    (("PackedVector4Array",), "void"): CallShape("ptrcallWithPackedVector4ListArg", "Unit"),
    (("Transform3D", "AABB", "Vector3", "PackedByteArray", "PackedByteArray", "PackedByteArray", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithTransform3DAABBVector3ThreeByteArrayPackedInt32ListArgs",
        "Unit",
    ),
    (("RID", "Transform3D", "AABB", "Vector3i", "PackedByteArray", "PackedByteArray", "PackedByteArray", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithRIDTransform3DAABBVector3iThreeByteArrayPackedInt32ListArgs",
        "Unit",
    ),
    (("TypedRIDArray",), "void"): CallShape("ptrcallWithRIDListArg", "Unit"),
    (("TypedStringArray",), "void"): CallShape("ptrcallWithTypedStringListArg", "Unit"),
    (("TypedStringNameArray",), "void"): CallShape("ptrcallWithStringNameListArg", "Unit"),
    (("TypedIntArray",), "void"): CallShape("ptrcallWithTypedIntListArg", "Unit"),
    (("TypedNodePathArray",), "void"): CallShape("ptrcallWithNodePathListArg", "Unit"),
    (("TypedArrayArray",), "void"): CallShape("ptrcallWithArrayListArg", "Unit"),
    (("TypedPackedByteArray",), "void"): CallShape("ptrcallWithByteArrayListArg", "Unit"),
    (("TypedPackedStringArray",), "void"): CallShape("ptrcallWithPackedStringListListArg", "Unit"),
    (("TypedPackedVector2Array",), "void"): CallShape("ptrcallWithPackedVector2ListListArg", "Unit"),
    (("TypedPlaneArray",), "void"): CallShape("ptrcallWithPlaneListArg", "Unit"),
    (("TypedPlaneArray",), "PackedVector3Array"): CallShape(
        "ptrcallWithPlaneListArgRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("TypedPlaneArray", "RID"), "PackedInt64Array"): CallShape(
        "ptrcallWithPlaneListAndRIDArgsRetPackedInt64List",
        "List<Long>",
        "emptyList()",
    ),
    (("PackedVector3Array", "PackedVector2Array", "PackedColorArray", "PackedVector2Array", "PackedVector3Array", "TypedPlaneArray"), "void"): CallShape(
        "ptrcallWithPackedVector3ListPackedVector2ListPackedColorListPackedVector2ListPackedVector3ListPlaneListArgs",
        "Unit",
    ),
    (("TypedTransform3DArray",), "void"): CallShape("ptrcallWithTransform3DListArg", "Unit"),
    (("RID", "TypedTransform3DArray"), "void"): CallShape("ptrcallWithRIDAndTransform3DListArgs", "Unit"),
    (("int32", "Rect2", "Rect2"), "void"): CallShape("ptrcallWithIntRect2Rect2Args", "Unit"),
    (("String", "Rect2", "enum", "int32", "int32", "int32"), "void"): CallShape(
        "ptrcallWithStringRect2LongThreeIntArgs",
        "Unit",
    ),
    (("Object", "Vector2i", "Vector2i", "Vector2i"), "PackedVector2Array"): CallShape(
        "ptrcallWithObjectAndThreeVector2iArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("TypedMaterialArray",), "void"): CallShape("ptrcallWithTypedMaterialListArg", "Unit"),
    (("TypedVector2iArray",), "void"): CallShape("ptrcallWithVector2iListArg", "Unit"),
    (("TypedVector2iArray",), "Object"): CallShape(
        "ptrcallWithVector2iListArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("TypedVector2iArray", "int32", "int32", "bool"), "void"): CallShape(
        "ptrcallWithVector2iListTwoIntAndBoolArgs",
        "Unit",
    ),
    (("TypedVector3iArray",), "void"): CallShape("ptrcallWithVector3iListArg", "Unit"),
    (("TypedDictionaryArray",), "void"): CallShape("ptrcallWithDictionaryListArg", "Unit"),
    (("int32", "bool"), "void"): CallShape("ptrcallWithIntAndBoolArgs", "Unit"),
    (("int32", "bool"), "bool"): CallShape("ptrcallWithIntAndBoolArgsRetBool", "Boolean", "false"),
    (("int32", "bool"), "Object"): CallShape("ptrcallWithIntAndBoolArgsRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("int32", "bool"), "NodePath"): CallShape("ptrcallWithIntAndBoolArgRetNodePath", "NodePath", "NodePath.EMPTY"),
    (("int32", "bool"), "Rect2"): CallShape("ptrcallWithIntAndBoolArgRetRect2", "Rect2", "Rect2.ZERO"),
    (("int32", "bool", "NodePath", "int32"), "void"): CallShape("ptrcallWithIntBoolNodePathIntArgs", "Unit"),
    (("int32", "Dictionary"), "void"): CallShape("ptrcallWithIntAndDictionaryArg", "Unit"),
    (("int32", "Dictionary"), "bool"): CallShape("ptrcallWithIntAndDictionaryArgRetBool", "Boolean", "false"),
    (("int32", "Object"), "void"): CallShape("ptrcallWithIntAndObjectArg", "Unit"),
    (("int32", "Object"), "enum"): CallShape("ptrcallWithIntAndObjectArgRetLong", "Long", "0L"),
    (("int32", "Object", "float"), "void"): CallShape("ptrcallWithIntObjectDoubleArgs", "Unit"),
    (("int32", "Object", "StringName"), "void"): CallShape("ptrcallWithIntObjectStringNameArgs", "Unit"),
    (("int32", "Object", "StringName", "Array"), "enum"): CallShape(
        "ptrcallWithIntObjectStringNameArrayArgsRetLong",
        "Long",
        "0L",
    ),
    (("int32", "String"), "void"): CallShape("ptrcallWithIntAndStringArg", "Unit"),
    (("int32", "String"), "int32"): CallShape("ptrcallWithIntAndStringArgRetInt", "Int", "0"),
    (("int32", "String"), "bool"): CallShape("ptrcallWithIntAndStringArgRetBool", "Boolean", "false"),
    (("int32", "String"), "enum"): CallShape("ptrcallWithIntAndStringArgRetLong", "Long", "0L"),
    (("int32", "String", "int32"), "enum"): CallShape(
        "ptrcallWithIntStringAndIntArgsRetLong",
        "Long",
        "0L",
    ),
    (("int32", "String", "Object"), "enum"): CallShape("ptrcallWithIntStringObjectArgsRetLong", "Long", "0L"),
    (("int32", "String", "Variant"), "Dictionary"): CallShape(
        "ptrcallWithIntStringVariantArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("int32", "StringName"), "void"): CallShape("ptrcallWithIntAndStringNameArg", "Unit"),
    (("int32", "StringName"), "bool"): CallShape("ptrcallWithIntAndStringNameArgRetBool", "Boolean", "false"),
    (("int32", "StringName"), "Variant"): CallShape(
        "ptrcallWithIntAndStringNameArgRetVariantScalar",
        "Any?",
        "null",
    ),
    (("int32", "bool", "int32", "Color", "bool", "int32", "Color", "Object", "Object", "bool"), "void"): CallShape(
        "ptrcallWithIntBoolIntColorBoolIntColorTwoObjectBoolArgs",
        "Unit",
    ),
    (("int32", "float", "bool"), "Variant"): CallShape(
        "ptrcallWithIntDoubleBoolArgsRetVariantScalar",
        "Any?",
        "null",
    ),
    (("int32", "float", "Variant", "float"), "int32"): CallShape(
        "ptrcallWithIntDoubleVariantDoubleArgsRetInt",
        "Int",
        "0",
    ),
    (("int32", "float", "enum", "bool", "bool"), "int32"): CallShape(
        "ptrcallWithIntDoubleLongTwoBoolArgsRetInt",
        "Int",
        "0",
    ),
    (("int32", "float", "float", "Vector2", "Vector2"), "int32"): CallShape(
        "ptrcallWithIntTwoDoubleTwoVector2ArgsRetInt",
        "Int",
        "0",
    ),
    (("int32", "float", "Object", "float", "float"), "int32"): CallShape(
        "ptrcallWithIntDoubleObjectTwoDoubleArgsRetInt",
        "Int",
        "0",
    ),
    (("int32", "Variant"), "void"): CallShape("ptrcallWithIntAndVariantArg", "Unit"),
    (("int32", "Variant"), "Rect2"): CallShape("ptrcallWithIntAndVariantArgRetRect2", "Rect2", "Rect2.ZERO"),
    (("int32", "Array"), "void"): CallShape("ptrcallWithIntAndArrayArg", "Unit"),
    (("int32", "Array"), "enum"): CallShape("ptrcallWithIntAndArrayArgRetLong", "Long", "0L"),
    (("int32", "PackedByteArray"), "enum"): CallShape("ptrcallWithIntAndByteArrayArgRetLong", "Long", "0L"),
    (("int32", "PackedFloat32Array", "int32"), "int32"): CallShape(
        "ptrcallWithIntPackedFloat32ListAndIntArgsRetInt",
        "Int",
        "0",
    ),
    (("int32", "PackedInt32Array"), "void"): CallShape("ptrcallWithIntAndPackedInt32ListArgs", "Unit"),
    (("int32", "PackedStringArray"), "void"): CallShape("ptrcallWithIntAndPackedStringListArg", "Unit"),
    (("int32", "PackedVector2Array"), "void"): CallShape("ptrcallWithIntAndPackedVector2ListArgs", "Unit"),
    (("int32", "NodePath"), "void"): CallShape("ptrcallWithIntAndNodePathArg", "Unit"),
    (("int32", "Vector2"), "void"): CallShape("ptrcallWithIntAndVector2Arg", "Unit"),
    (("int32", "Vector2i"), "int32"): CallShape("ptrcallWithIntAndVector2iArgRetInt", "Int", "0"),
    (("int32", "Vector2i"), "bool"): CallShape("ptrcallWithIntAndVector2iArgRetBool", "Boolean", "false"),
    (("int32", "Vector2i"), "PackedInt32Array"): CallShape(
        "ptrcallWithIntVector2iArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("int32", "TypedVector2iArray"), "Object"): CallShape(
        "ptrcallWithIntAndVector2iListArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "int32"), "void"): CallShape("ptrcallWithTwoIntArgs", "Unit"),
    (("int32", "int64"), "void"): CallShape("ptrcallWithIntAndLongArgs", "Unit"),
    (("int32", "enum"), "void"): CallShape("ptrcallWithIntAndLongArgs", "Unit"),
    (("int32", "bitfield"), "void"): CallShape("ptrcallWithIntAndLongArgs", "Unit"),
    (("int32", "enum"), "int64"): CallShape("ptrcallWithIntAndLongArgsRetLong", "Long", "0L"),
    (("int32", "enum"), "bool"): CallShape("ptrcallWithIntAndLongArgsRetBool", "Boolean", "false"),
    (("int32", "enum"), "float"): CallShape("ptrcallWithIntAndLongArgsRetDouble", "Double", "0.0"),
    (("int32", "enum", "int64"), "void"): CallShape("ptrcallWithIntTwoLongArgs", "Unit"),
    (("int32", "RID"), "void"): CallShape("ptrcallWithIntAndRIDArg", "Unit"),
    (("int32", "Color"), "void"): CallShape("ptrcallWithIntAndColorArg", "Unit"),
    (("int32", "Color"), "int64"): CallShape("ptrcallWithIntColorArgsRetLong", "Long", "0L"),
    (("int32", "Color", "bool"), "void"): CallShape("ptrcallWithIntColorBoolArgs", "Unit"),
    (("int32", "int32", "Color", "enum", "bool", "bool"), "void"): CallShape(
        "ptrcallWithTwoIntColorLongTwoBoolArgs",
        "Unit",
    ),
    (("int32", "Vector3"), "void"): CallShape("ptrcallWithIntAndVector3Arg", "Unit"),
    (("int32", "Vector3"), "float"): CallShape("ptrcallWithIntVector3ArgsRetDouble", "Double", "0.0"),
    (("int32", "Quaternion"), "void"): CallShape("ptrcallWithIntAndQuaternionArg", "Unit"),
    (("int32", "Rect2"), "void"): CallShape("ptrcallWithIntAndRect2Arg", "Unit"),
    (("int32", "Rect2i"), "void"): CallShape("ptrcallWithIntAndRect2iArg", "Unit"),
    (("int32", "Object", "int32"), "void"): CallShape("ptrcallWithIntObjectAndIntArgs", "Unit"),
    (("int32", "Object", "bool"), "void"): CallShape("ptrcallWithIntObjectBoolArgs", "Unit"),
    (("int32", "int64"), "bool"): CallShape("ptrcallWithIntAndLongArgsRetBool", "Boolean", "false"),
    (("int32", "int32"), "bool"): CallShape("ptrcallWithTwoIntArgsRetBool", "Boolean", "false"),
    (("int32", "int32"), "int32"): CallShape("ptrcallWithTwoIntArgsRetInt", "Int", "0"),
    (("int32", "int32"), "int64"): CallShape("ptrcallWithTwoIntArgsRetLong", "Long", "0L"),
    (("int32", "int32"), "enum"): CallShape("ptrcallWithTwoIntArgsRetLong", "Long", "0L"),
    (("int32", "int32"), "NodePath"): CallShape("ptrcallWithTwoIntArgsRetNodePath", "NodePath", "NodePath.EMPTY"),
    (("int32", "int32"), "Vector2i"): CallShape("ptrcallWithTwoIntArgsRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("int32", "int32"), "Rect2i"): CallShape("ptrcallWithTwoIntArgsRetRect2i", "Rect2i", "Rect2i.ZERO"),
    (("int32", "int32"), "String"): CallShape("ptrcallWithTwoIntArgsRetString", "String", '""'),
    (("int32", "int32"), "StringName"): CallShape("ptrcallWithTwoIntArgsRetStringName", "String", '""'),
    (("int32", "int32"), "Variant"): CallShape("ptrcallWithTwoIntArgsRetVariantScalar", "Any?", "null"),
    (("int32", "int32"), "Object"): CallShape("ptrcallWithTwoIntArgsRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("int32", "int32"), "PackedInt32Array"): CallShape(
        "ptrcallWithTwoIntArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("int32", "int32", "bool", "bool", "float", "bool"), "Object"): CallShape(
        "ptrcallWithTwoIntTwoBoolDoubleBoolArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "int32", "int32"), "Object"): CallShape(
        "ptrcallWithThreeIntArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "int32", "String"), "int32"): CallShape("ptrcallWithTwoIntStringArgsRetInt", "Int", "0"),
    (("int32", "int32", "bool", "enum"), "Object"): CallShape(
        "ptrcallWithTwoIntBoolLongArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "bool", "bool", "bool"), "Object"): CallShape(
        "ptrcallWithIntAndThreeBoolArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "int32", "bool", "bool", "bool"), "Object"): CallShape(
        "ptrcallWithTwoIntAndThreeBoolArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "int32"), "TypedVector2iArray"): CallShape(
        "ptrcallWithTwoIntArgsRetVector2iList",
        "List<Vector2i>",
        "emptyList()",
    ),
    (("int32", "int32", "NodePath"), "void"): CallShape("ptrcallWithTwoIntAndNodePathArg", "Unit"),
    (("int32", "int32", "StringName"), "void"): CallShape("ptrcallWithTwoIntAndStringNameArg", "Unit"),
    (("int32", "int32", "float"), "RID"): CallShape("ptrcallWithTwoIntDoubleArgsRetRID", "RID", "RID.EMPTY"),
    (("int64", "int64"), "bool"): CallShape("ptrcallWithTwoLongArgsRetBool", "Boolean", "false"),
    (("int64", "Color"), "void"): CallShape("ptrcallWithLongAndColorArg", "Unit"),
    (("int64", "int64", "bool"), "void"): CallShape("ptrcallWithTwoLongAndBoolArgs", "Unit"),
    (("int64", "int64", "bool"), "bool"): CallShape("ptrcallWithTwoLongAndBoolArgsRetBool", "Boolean", "false"),
    (("int64", "int64", "bool"), "PackedInt64Array"): CallShape(
        "ptrcallWithTwoLongAndBoolArgsRetPackedInt64List",
        "List<Long>",
        "emptyList()",
    ),
    (("int64", "int64", "float"), "void"): CallShape("ptrcallWithTwoLongAndDoubleArgs", "Unit"),
    (("enum", "bool", "float", "int32", "float", "float"), "void"): CallShape(
        "ptrcallWithLongBoolDoubleIntAndTwoDoubleArgs",
        "Unit",
    ),
    (("int64", "int64", "bool"), "PackedVector2Array"): CallShape(
        "ptrcallWithTwoLongAndBoolArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("int64", "int64", "bool"), "PackedVector3Array"): CallShape(
        "ptrcallWithTwoLongAndBoolArgsRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("enum", "enum"), "void"): CallShape("ptrcallWithTwoLongArgs", "Unit"),
    (("enum", "enum"), "bool"): CallShape("ptrcallWithTwoLongArgsRetBool", "Boolean", "false"),
    (("enum", "enum"), "int32"): CallShape("ptrcallWithTwoLongArgsRetInt", "Int", "0"),
    (("enum", "enum"), "bitfield"): CallShape("ptrcallWithTwoLongArgsRetLong", "Long", "0L"),
    (("enum", "enum"), "float"): CallShape("ptrcallWithTwoLongArgsRetDouble", "Double", "0.0"),
    (("enum", "enum"), "Quaternion"): CallShape(
        "ptrcallWithTwoLongArgsRetQuaternion",
        "Quaternion",
        "Quaternion.IDENTITY",
    ),
    (("enum", "enum"), "RID"): CallShape("ptrcallWithTwoLongArgsRetRID", "RID", "RID.EMPTY"),
    (("enum", "enum"), "Vector3"): CallShape("ptrcallWithTwoLongArgsRetVector3", "Vector3", "Vector3.ZERO"),
    (("enum", "bitfield"), "bool"): CallShape("ptrcallWithTwoLongArgsRetBool", "Boolean", "false"),
    (("enum", "int32", "int32", "int32", "int32"), "void"): CallShape(
        "ptrcallWithLongAndFourIntArgs",
        "Unit",
    ),
    (("enum", "int32", "int32", "int32", "int32"), "bool"): CallShape(
        "ptrcallWithLongAndFourIntArgsRetBool",
        "Boolean",
        "false",
    ),
    (("enum", "int32", "int32", "int32", "int32"), "enum"): CallShape(
        "ptrcallWithLongAndFourIntArgsRetLong",
        "Long",
        "0L",
    ),
    (("int32", "int64"), "float"): CallShape("ptrcallWithIntAndLongArgsRetDouble", "Double", "0.0"),
    (("int32", "enum"), "RID"): CallShape("ptrcallWithIntAndLongArgsRetRID", "RID", "RID.EMPTY"),
    (("int32", "int32"), "float"): CallShape("ptrcallWithTwoIntArgsRetDouble", "Double", "0.0"),
    (("int32", "int32"), "Color"): CallShape("ptrcallWithTwoIntArgsRetColor", "Color", "Color(0f, 0f, 0f, 0f)"),
    (("int32", "int32"), "Vector3"): CallShape("ptrcallWithTwoIntArgsRetVector3", "Vector3", "Vector3.ZERO"),
    (("int32", "int32"), "PackedVector2Array"): CallShape(
        "ptrcallWithTwoIntArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("int64", "int64"), "float"): CallShape("ptrcallWithTwoLongArgsRetDouble", "Double", "0.0"),
    (("int64", "int64"), "enum"): CallShape("ptrcallWithTwoLongArgsRetLong", "Long", "0L"),
    (("int64", "int64"), "Vector3"): CallShape("ptrcallWithTwoLongArgsRetVector3", "Vector3", "Vector3.ZERO"),
    (("int64", "int64"), "StringName"): CallShape("ptrcallWithTwoLongArgsRetStringName", "String", '""'),
    (("int64", "int64"), "Variant"): CallShape("ptrcallWithTwoLongArgsRetVariantScalar", "Any?", "null"),
    (("int64", "String", "Array"), "bool"): CallShape("ptrcallWithLongStringArrayArgsRetBool", "Boolean", "false"),
    (("int64", "StringName", "int32"), "void"): CallShape("ptrcallWithLongStringNameAndIntArgs", "Unit"),
    (("int64", "int32"), "void"): CallShape("ptrcallWithLongAndIntArgs", "Unit"),
    (("int64", "int32"), "bool"): CallShape("ptrcallWithLongAndIntArgsRetBool", "Boolean", "false"),
    (("int64", "int32"), "int32"): CallShape("ptrcallWithLongAndIntArgsRetInt", "Int", "0"),
    (("int64", "int32"), "int64"): CallShape("ptrcallWithLongAndIntArgsRetLong", "Long", "0L"),
    (("int64", "int32"), "enum"): CallShape("ptrcallWithLongAndIntArgsRetLong", "Long", "0L"),
    (("int64", "int32"), "Object"): CallShape("ptrcallWithLongAndIntArgsRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("int64", "int64"), "Vector2"): CallShape("ptrcallWithTwoLongArgsRetVector2", "Vector2", "Vector2.ZERO"),
    (("int64", "bool", "uint32", "uint32"), "void"): CallShape("ptrcallWithLongBoolTwoUInt32Args", "Unit"),
    (("int64", "uint32", "uint32", "uint32"), "void"): CallShape("ptrcallWithLongAndThreeUInt32Args", "Unit"),
    (("int64", "int64", "int64", "uint32", "uint32", "uint32", "uint32"), "int64"): CallShape(
        "ptrcallWithThreeLongFourUInt32ArgsRetLong",
        "Long",
        "0L",
    ),
    (("int64",), "Vector3"): CallShape("ptrcallWithLongArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("int64",), "PackedInt64Array"): CallShape("ptrcallWithLongArgRetPackedInt64List", "List<Long>", "emptyList()"),
    (("int64", "PackedByteArray", "int64"), "void"): CallShape("ptrcallWithLongByteArrayLongArgs", "Unit"),
    (("int64", "PackedByteArray", "uint32"), "void"): CallShape("ptrcallWithLongByteArrayUInt32Args", "Unit"),
    (("int64", "PackedByteArray", "bitfield"), "RID"): CallShape(
        "ptrcallWithLongByteArrayLongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("int64", "Vector2"), "void"): CallShape("ptrcallWithLongAndVector2Arg", "Unit"),
    (("enum", "Vector2"), "void"): CallShape("ptrcallWithLongAndVector2Arg", "Unit"),
    (("int64", "Vector2", "float"), "void"): CallShape("ptrcallWithLongVector2AndDoubleArgs", "Unit"),
    (("int64", "Vector3"), "void"): CallShape("ptrcallWithLongAndVector3Arg", "Unit"),
    (("int64", "Vector3", "float"), "void"): CallShape("ptrcallWithLongVector3AndDoubleArgs", "Unit"),
    (("int64", "bool", "int64", "int64"), "void"): CallShape("ptrcallWithLongBoolTwoLongArgs", "Unit"),
    (("int64", "int64", "int64", "int64"), "void"): CallShape("ptrcallWithFourLongArgs", "Unit"),
    (("int64", "Rect2"), "void"): CallShape("ptrcallWithLongRect2Args", "Unit"),
    (("enum", "bool"), "void"): CallShape("ptrcallWithLongAndBoolArgs", "Unit"),
    (("enum", "bool"), "String"): CallShape("ptrcallWithLongAndBoolArgRetString", "String", '""'),
    (("enum", "bool", "int32"), "void"): CallShape("ptrcallWithLongBoolIntArgs", "Unit"),
    (("enum", "String"), "void"): CallShape("ptrcallWithLongAndStringArg", "Unit"),
    (("enum", "String", "String"), "void"): CallShape("ptrcallWithLongAndTwoStringArgs", "Unit"),
    (("enum", "String", "PackedStringArray", "PackedByteArray"), "enum"): CallShape(
        "ptrcallWithLongStringPackedStringListByteArrayArgsRetLong",
        "Long",
        "0L",
    ),
    (("enum", "String", "PackedStringArray", "String"), "enum"): CallShape(
        "ptrcallWithLongStringPackedStringListStringArgsRetLong",
        "Long",
        "0L",
    ),
    (("enum", "String"), "PackedStringArray"): CallShape(
        "ptrcallWithLongAndStringArgRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("enum", "int32"), "void"): CallShape("ptrcallWithLongAndIntArgs", "Unit"),
    (("enum", "int32"), "bool"): CallShape("ptrcallWithLongAndIntArgsRetBool", "Boolean", "false"),
    (("enum", "int32"), "int32"): CallShape("ptrcallWithLongAndIntArgsRetInt", "Int", "0"),
    (("enum", "int32"), "int64"): CallShape("ptrcallWithLongAndIntArgsRetLong", "Long", "0L"),
    (("enum", "int32"), "enum"): CallShape("ptrcallWithLongAndIntArgsRetLong", "Long", "0L"),
    (("enum", "int32"), "Object"): CallShape("ptrcallWithLongAndIntArgsRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("enum", "int32"), "Vector2"): CallShape("ptrcallWithLongAndIntArgsRetVector2", "Vector2", "Vector2.ZERO"),
    (("enum", "int32", "int32"), "void"): CallShape("ptrcallWithLongAndTwoIntArgs", "Unit"),
    (("enum", "int32", "Vector2"), "void"): CallShape("ptrcallWithLongIntVector2Args", "Unit"),
    (("enum", "int32", "StringName"), "void"): CallShape("ptrcallWithLongIntStringNameArgs", "Unit"),
    (("enum",), "Vector3"): CallShape("ptrcallWithLongArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("enum",), "bitfield"): CallShape("ptrcallWithLongArgRetLong", "Long", "0L"),
    (("enum", "bitfield"), "void"): CallShape("ptrcallWithTwoLongArgs", "Unit"),
    (("bitfield", "bitfield"), "void"): CallShape("ptrcallWithTwoLongArgs", "Unit"),
    (("enum", "Vector3"), "void"): CallShape("ptrcallWithLongAndVector3Arg", "Unit"),
    (("bitfield", "int32"), "int64"): CallShape("ptrcallWithLongAndIntArgsRetLong", "Long", "0L"),
    (("bitfield", "int32"), "uint32"): CallShape("ptrcallWithLongAndIntArgsRetUInt32", "Long", "0L"),
    (("bitfield", "int32", "int32"), "uint32"): CallShape(
        "ptrcallWithLongAndTwoIntArgsRetUInt32",
        "Long",
        "0L",
    ),
    (("enum", "Object"), "void"): CallShape("ptrcallWithLongAndObjectArg", "Unit"),
    (("int64", "Object"), "void"): CallShape("ptrcallWithLongAndObjectArg", "Unit"),
    (("Object", "int64"), "Object"): CallShape(
        "ptrcallWithObjectAndLongArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Object", "int64"), "enum"): CallShape("ptrcallWithObjectAndLongArgRetLong", "Long", "0L"),
    (("Object", "float", "float"), "void"): CallShape("ptrcallWithObjectTwoDoubleArgs", "Unit"),
    (("Object", "float", "float", "float", "enum", "StringName"), "int64"): CallShape(
        "ptrcallWithObjectThreeDoubleLongStringNameArgsRetLong",
        "Long",
        "0L",
    ),
    (("enum", "float"), "void"): CallShape("ptrcallWithLongAndDoubleArg", "Unit"),
    (("float", "bool", "bool", "bool"), "Object"): CallShape(
        "ptrcallWithDoubleAndThreeBoolArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "int32", "float"), "void"): CallShape("ptrcallWithTwoIntAndDoubleArgs", "Unit"),
    (("int32", "float", "float", "float"), "void"): CallShape("ptrcallWithIntAndThreeDoubleArgs", "Unit"),
    (("int32", "float", "float", "float", "bool"), "void"): CallShape("ptrcallWithIntThreeDoubleBoolArgs", "Unit"),
    (("int32", "float", "bool", "bool", "float", "enum", "bool", "bool"), "float"): CallShape(
        "ptrcallWithIntDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("int32", "int32", "bool"), "void"): CallShape("ptrcallWithTwoIntAndBoolArgs", "Unit"),
    (("int32", "int32", "enum", "enum", "enum", "float", "bool", "int32", "bool"), "void"): CallShape(
        "ptrcallWithTwoIntThreeLongDoubleBoolIntBoolArgs",
        "Unit",
    ),
    (("int32", "int32", "bool", "enum", "PackedByteArray"), "void"): CallShape(
        "ptrcallWithTwoIntBoolLongByteArrayArgs",
        "Unit",
    ),
    (("int32", "int32", "bool", "enum", "PackedByteArray"), "Object"): CallShape(
        "ptrcallWithTwoIntBoolLongByteArrayArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "int32", "int32"), "void"): CallShape("ptrcallWithThreeIntArgs", "Unit"),
    (("int32", "int32", "int32"), "int32"): CallShape("ptrcallWithThreeIntArgsRetInt", "Int", "0"),
    (("int32", "int32", "int32"), "Vector2"): CallShape("ptrcallWithThreeIntArgsRetVector2", "Vector2", "Vector2.ZERO"),
    (("int32", "int32", "int32"), "Vector2i"): CallShape("ptrcallWithThreeIntArgsRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("int32", "int32", "int32", "Vector2"), "void"): CallShape("ptrcallWithThreeIntAndVector2Arg", "Unit"),
    (("int32", "uint32"), "void"): CallShape("ptrcallWithIntAndUInt32Args", "Unit"),
    (("uint32", "int32"), "void"): CallShape("ptrcallWithUInt32AndIntArg", "Unit"),
    (("uint32", "int32"), "int32"): CallShape("ptrcallWithUInt32AndIntArgRetInt", "Int", "0"),
    (("uint32", "StringName", "int32"), "void"): CallShape("ptrcallWithUInt32StringNameAndIntArgs", "Unit"),
    (("uint32", "int32"), "Object"): CallShape(
        "ptrcallWithUInt32AndIntArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("uint32", "uint32", "float"), "void"): CallShape("ptrcallWithTwoUInt32AndDoubleArg", "Unit"),
    (("int32", "int32", "int32", "int32"), "enum"): CallShape("ptrcallWithFourIntArgsRetLong", "Long", "0L"),
    (("int32", "int32", "int32", "int32"), "Dictionary"): CallShape(
        "ptrcallWithFourIntArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("int32", "int32", "int32", "int32", "int32"), "enum"): CallShape(
        "ptrcallWithFiveIntArgsRetLong",
        "Long",
        "0L",
    ),
    (("int32", "PackedByteArray", "int32"), "void"): CallShape("ptrcallWithIntByteArrayIntArgs", "Unit"),
    (("int32", "PackedByteArray", "int32"), "enum"): CallShape("ptrcallWithIntByteArrayIntArgsRetLong", "Long", "0L"),
    (("String", "int32", "PackedByteArray"), "void"): CallShape("ptrcallWithStringIntByteArrayArgs", "Unit"),
    (("int32", "int64", "int64"), "int32"): CallShape("ptrcallWithIntTwoLongArgsRetInt", "Int", "0"),
    (("int32", "int32", "int32", "int32", "int32"), "void"): CallShape("ptrcallWithFiveIntArgs", "Unit"),
    (("int32", "int32", "int32", "int32", "bool"), "void"): CallShape("ptrcallWithFourIntAndBoolArgs", "Unit"),
    (("int32", "int32", "bool", "bool"), "int32"): CallShape("ptrcallWithTwoIntTwoBoolArgsRetInt", "Int", "0"),
    (("int32", "Object", "int32", "bool", "String", "String"), "void"): CallShape(
        "ptrcallWithIntObjectIntBoolTwoStringArgs",
        "Unit",
    ),
    (("uint32", "Object"), "void"): CallShape("ptrcallWithUInt32AndObjectArg", "Unit"),
    (("int32", "bool", "int32"), "void"): CallShape("ptrcallWithIntBoolIntArgs", "Unit"),
    (("int32", "bool", "int32", "bool"), "void"): CallShape("ptrcallWithIntBoolIntBoolArgs", "Unit"),
    (("int32", "bool", "int32", "int32"), "void"): CallShape("ptrcallWithIntBoolTwoIntArgs", "Unit"),
    (("int32", "bool", "bool", "int32", "int32"), "void"): CallShape("ptrcallWithIntTwoBoolTwoIntArgs", "Unit"),
    (("int32", "enum", "bool", "String"), "void"): CallShape("ptrcallWithIntLongBoolStringArgs", "Unit"),
    (("int32", "enum", "int32", "String"), "void"): CallShape("ptrcallWithIntLongIntStringArgs", "Unit"),
    (("int32", "int32", "enum"), "void"): CallShape("ptrcallWithTwoIntAndLongArgs", "Unit"),
    (("int32", "int32", "Color"), "void"): CallShape("ptrcallWithTwoIntAndColorArg", "Unit"),
    (("int32", "int32", "Vector2i"), "void"): CallShape("ptrcallWithTwoIntAndVector2iArg", "Unit"),
    (("int32", "int32", "Vector2i"), "Vector2"): CallShape(
        "ptrcallWithTwoIntVector2iArgRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("int32", "int32", "Vector2i", "Vector2"), "void"): CallShape(
        "ptrcallWithTwoIntVector2iVector2Args",
        "Unit",
    ),
    (("int32", "int32", "Vector3"), "void"): CallShape("ptrcallWithTwoIntAndVector3Arg", "Unit"),
    (("int32", "int32", "String"), "void"): CallShape("ptrcallWithTwoIntAndStringArgs", "Unit"),
    (("int32", "int32", "String", "String"), "Dictionary"): CallShape(
        "ptrcallWithTwoIntTwoStringArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("int32", "int32", "Variant"), "void"): CallShape("ptrcallWithTwoIntAndVariantArg", "Unit"),
    (("int32", "StringName", "Variant"), "void"): CallShape("ptrcallWithIntStringNameAndVariantArg", "Unit"),
    (("int32", "int32", "Object"), "void"): CallShape("ptrcallWithTwoIntAndObjectArg", "Unit"),
    (("int32", "int32", "PackedByteArray"), "void"): CallShape("ptrcallWithTwoIntAndByteArrayArg", "Unit"),
    (("int32", "int32", "PackedVector2Array"), "void"): CallShape("ptrcallWithTwoIntAndPackedVector2ListArg", "Unit"),
    (("int32", "Vector2i", "int32"), "Object"): CallShape(
        "ptrcallWithIntVector2iIntArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("String", "int64", "int32", "int32"), "Vector2i"): CallShape(
        "ptrcallWithStringLongTwoIntArgsRetVector2i",
        "Vector2i",
        "Vector2i.ZERO",
    ),
    (("String", "uint32", "int32", "int32"), "Vector2i"): CallShape(
        "ptrcallWithStringUInt32TwoIntArgsRetVector2i",
        "Vector2i",
        "Vector2i.ZERO",
    ),
    (("int32", "int32", "String", "String", "int32"), "int32"): CallShape(
        "ptrcallWithTwoIntTwoStringAndIntArgsRetInt",
        "Int",
        "0",
    ),
    (("float",), "void"): CallShape("ptrcallWithDoubleArg", "Unit"),
    (("float",), "float"): CallShape("ptrcallWithFloatArgRetFloat", "Double", "0.0"),
    (("float",), "int32"): CallShape("ptrcallWithDoubleArgRetInt", "Int", "0"),
    (("float",), "StringName"): CallShape("ptrcallWithFloatArgRetStringName", "String", '""'),
    (("float",), "bool"): CallShape("ptrcallWithDoubleArgRetBool", "Boolean", "false"),
    (("float",), "Object"): CallShape("ptrcallWithDoubleArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("float",), "Vector2"): CallShape("ptrcallWithDoubleArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("float",), "Vector3"): CallShape("ptrcallWithDoubleArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("float",), "Color"): CallShape("ptrcallWithDoubleArgRetColor", "Color", "Color(0f, 0f, 0f, 0f)"),
    (("float", "Color"), "void"): CallShape("ptrcallWithDoubleAndColorArg", "Unit"),
    (("float",), "PackedByteArray"): CallShape("ptrcallWithDoubleArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("float", "float"), "float"): CallShape("ptrcallWithTwoDoubleArgsRetDouble", "Double", "0.0"),
    (("float", "float"), "void"): CallShape("ptrcallWithTwoDoubleArgs", "Unit"),
    (("float", "float", "Array"), "void"): CallShape("ptrcallWithTwoDoubleArrayArgs", "Unit"),
    (("float", "float", "enum"), "Vector2"): CallShape(
        "ptrcallWithTwoDoubleAndLongArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("float", "float", "float"), "void"): CallShape("ptrcallWithThreeDoubleArgs", "Unit"),
    (("float", "float", "float"), "float"): CallShape("ptrcallWithThreeDoubleArgsRetDouble", "Double", "0.0"),
    (("float", "float", "float", "bool"), "float"): CallShape(
        "ptrcallWithThreeDoubleBoolArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("float", "float", "float", "float"), "void"): CallShape("ptrcallWithFourDoubleArgs", "Unit"),
    (("float", "float", "int32"), "void"): CallShape("ptrcallWithTwoDoubleAndIntArgs", "Unit"),
    (("float", "float", "int32", "enum"), "TypedPlaneArray"): CallShape(
        "ptrcallWithTwoDoubleIntLongArgsRetPlaneList",
        "List<Plane>",
        "emptyList()",
    ),
    (("float", "float", "int32", "int32", "enum"), "TypedPlaneArray"): CallShape(
        "ptrcallWithTwoDoubleTwoIntLongArgsRetPlaneList",
        "List<Plane>",
        "emptyList()",
    ),
    (("float", "int32"), "void"): CallShape("ptrcallWithDoubleAndIntArgs", "Unit"),
    (("float", "int32"), "PackedInt32Array"): CallShape(
        "ptrcallWithDoubleAndIntArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("float", "int32"), "PackedVector2Array"): CallShape(
        "ptrcallWithDoubleAndIntArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("float", "bool"), "void"): CallShape("ptrcallWithDoubleAndBoolArgs", "Unit"),
    (("float", "bool", "bool"), "void"): CallShape("ptrcallWithDoubleAndTwoBoolArgs", "Unit"),
    (("float", "bool", "bool"), "Transform3D"): CallShape(
        "ptrcallWithDoubleAndTwoBoolArgsRetTransform3D",
        "Transform3D",
        "Transform3D.IDENTITY",
    ),
    (("float", "Vector2", "float", "float"), "void"): CallShape("ptrcallWithDoubleVector2TwoDoubleArgs", "Unit"),
    (("float", "bool"), "Vector2"): CallShape("ptrcallWithDoubleAndBoolArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("float", "bool"), "Vector3"): CallShape("ptrcallWithDoubleAndBoolArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("float", "bool"), "Transform2D"): CallShape(
        "ptrcallWithDoubleAndBoolArgRetTransform2D",
        "Transform2D",
        "Transform2D.IDENTITY",
    ),
    (("bool", "enum"), "void"): CallShape("ptrcallWithBoolAndLongArgs", "Unit"),
    (("bool", "bool"), "Object"): CallShape("ptrcallWithTwoBoolArgsRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("Vector2",), "void"): CallShape("ptrcallWithVector2Arg", "Unit"),
    (("Vector2",), "bool"): CallShape("ptrcallWithVector2ArgRetBool", "Boolean", "false"),
    (("Vector2",), "float"): CallShape("ptrcallWithVector2ArgRetDouble", "Double", "0.0"),
    (("Vector2",), "int32"): CallShape("ptrcallWithVector2ArgRetInt", "Int", "0"),
    (("Vector2",), "Object"): CallShape("ptrcallWithVector2ArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("Vector2",), "Vector2"): CallShape("ptrcallWithVector2ArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("Vector2",), "Vector2i"): CallShape("ptrcallWithVector2ArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("Vector2",), "Vector3"): CallShape("ptrcallWithVector2ArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("Vector2",), "String"): CallShape("ptrcallWithVector2ArgRetString", "String", '""'),
    (("Vector2",), "enum"): CallShape("ptrcallWithVector2ArgRetLong", "Long", "0L"),
    (("Vector2", "float"), "Dictionary"): CallShape(
        "ptrcallWithVector2AndDoubleArgRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("Vector2", "float"), "Vector3"): CallShape(
        "ptrcallWithVector2AndDoubleArgRetVector3",
        "Vector3",
        "Vector3.ZERO",
    ),
    (("Vector2", "int32"), "void"): CallShape("ptrcallWithVector2AndIntArg", "Unit"),
    (("Vector2", "Rect2"), "bool"): CallShape("ptrcallWithVector2Rect2ArgsRetBool", "Boolean", "false"),
    (("Vector2", "PackedVector2Array"), "bool"): CallShape(
        "ptrcallWithVector2PackedVector2ListArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Vector2", "PackedVector2Array"), "RID"): CallShape(
        "ptrcallWithVector2PackedVector2ListArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("Vector2", "bool", "float", "bool"), "Object"): CallShape(
        "ptrcallWithVector2BoolFloatBoolArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Vector2", "float", "Vector2"), "void"): CallShape("ptrcallWithVector2DoubleVector2Args", "Unit"),
    (("Vector2", "Vector2"), "void"): CallShape("ptrcallWithTwoVector2Args", "Unit"),
    (("Vector2", "Vector2", "float"), "bool"): CallShape(
        "ptrcallWithTwoVector2DoubleArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Vector2", "Vector2", "Vector2"), "Vector2"): CallShape(
        "ptrcallWithThreeVector2ArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("Vector2", "Vector2", "Vector2", "float"), "float"): CallShape(
        "ptrcallWithThreeVector2DoubleArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("Vector2", "Vector2", "Vector2", "Vector2"), "bool"): CallShape(
        "ptrcallWithFourVector2ArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Vector2", "Vector2", "Vector2", "Vector2"), "PackedVector2Array"): CallShape(
        "ptrcallWithFourVector2ArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("Vector2", "Vector2", "Vector2", "Vector2"), "Variant"): CallShape(
        "ptrcallWithFourVector2ArgsRetVariantScalar",
        "Any?",
        "null",
    ),
    (("Vector2", "float", "float", "enum", "enum"), "int32"): CallShape(
        "ptrcallWithVector2TwoDoubleTwoLongArgsRetInt",
        "Int",
        "0",
    ),
    (("Vector2", "float", "Color", "bool", "float", "bool"), "void"): CallShape(
        "ptrcallWithVector2DoubleColorBoolDoubleBoolArgs",
        "Unit",
    ),
    (("Vector2", "float", "float", "Color", "bool", "float", "bool"), "void"): CallShape(
        "ptrcallWithVector2TwoDoubleColorBoolDoubleBoolArgs",
        "Unit",
    ),
    (("Vector2", "float", "float", "float", "int32", "Color", "float", "bool"), "void"): CallShape(
        "ptrcallWithVector2ThreeDoubleIntColorDoubleBoolArgs",
        "Unit",
    ),
    (("Vector2", "float", "float", "float", "float", "int32", "Color", "float", "bool"), "void"): CallShape(
        "ptrcallWithVector2FourDoubleIntColorDoubleBoolArgs",
        "Unit",
    ),
    (("Vector2", "Vector2", "Vector2", "int32"), "void"): CallShape("ptrcallWithThreeVector2AndIntArg", "Unit"),
    (("Vector2", "Vector2", "Color", "float", "bool"), "void"): CallShape(
        "ptrcallWithTwoVector2ColorDoubleBoolArgs",
        "Unit",
    ),
    (("Vector2", "Vector2", "Color", "float", "float", "bool", "bool"), "void"): CallShape(
        "ptrcallWithTwoVector2ColorTwoDoubleTwoBoolArgs",
        "Unit",
    ),
    (("Vector2", "Vector2"), "PackedVector2Array"): CallShape(
        "ptrcallWithTwoVector2ArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("Vector2", "Vector2", "int64", "TypedRIDArray"), "Object"): CallShape(
        "ptrcallWithTwoVector2LongRIDListArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Vector2", "Vector2", "uint32", "TypedRIDArray"), "Object"): CallShape(
        "ptrcallWithTwoVector2UInt32RIDListArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("PackedVector2Array", "float", "enum"), "TypedPackedVector2Array"): CallShape(
        "ptrcallWithPackedVector2ListDoubleAndLongArgsRetPackedVector2ListList",
        "List<List<Vector2>>",
        "emptyList()",
    ),
    (("PackedVector2Array", "float", "enum", "enum"), "TypedPackedVector2Array"): CallShape(
        "ptrcallWithPackedVector2ListDoubleAndTwoLongArgsRetPackedVector2ListList",
        "List<List<Vector2>>",
        "emptyList()",
    ),
    (("Vector2", "bool"), "void"): CallShape("ptrcallWithVector2AndBoolArg", "Unit"),
    (("Vector2", "bool"), "int64"): CallShape("ptrcallWithVector2AndBoolArgRetLong", "Long", "0L"),
    (("Vector2", "bool"), "int32"): CallShape("ptrcallWithVector2AndBoolArgRetInt", "Int", "0"),
    (("Vector3",), "void"): CallShape("ptrcallWithVector3Arg", "Unit"),
    (("Vector3",), "bool"): CallShape("ptrcallWithVector3ArgRetBool", "Boolean", "false"),
    (("Vector3",), "float"): CallShape("ptrcallWithVector3ArgRetDouble", "Double", "0.0"),
    (("Vector3",), "Vector2"): CallShape("ptrcallWithVector3ArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("Vector3",), "Vector3"): CallShape("ptrcallWithVector3ArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("Vector3",), "Vector3i"): CallShape("ptrcallWithVector3ArgRetVector3i", "Vector3i", "Vector3i.ZERO"),
    (("Vector3",), "TypedPlaneArray"): CallShape("ptrcallWithVector3ArgRetPlaneList", "List<Plane>", "emptyList()"),
    (("Vector3", "float"), "void"): CallShape("ptrcallWithVector3AndDoubleArg", "Unit"),
    (("Vector3", "bool", "float", "bool", "int32"), "Object"): CallShape(
        "ptrcallWithVector3BoolFloatBoolIntArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Vector3", "Vector3"), "void"): CallShape("ptrcallWithTwoVector3Args", "Unit"),
    (("Vector3", "Vector3"), "Vector2"): CallShape("ptrcallWithTwoVector3ArgsRetVector2", "Vector2", "Vector2.ZERO"),
    (("Vector3", "Vector3"), "Dictionary"): CallShape(
        "ptrcallWithTwoVector3ArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("Vector3", "Vector3", "bool"), "void"): CallShape("ptrcallWithTwoVector3AndBoolArgs", "Unit"),
    (("Vector3", "Vector3", "float", "float"), "PackedVector3Array"): CallShape(
        "ptrcallWithTwoVector3TwoDoubleArgsRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("Vector3", "Vector3", "int64", "TypedRIDArray"), "Object"): CallShape(
        "ptrcallWithTwoVector3LongRIDListArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Vector3", "Vector3", "uint32", "TypedRIDArray"), "Object"): CallShape(
        "ptrcallWithTwoVector3UInt32RIDListArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Vector3", "Vector3", "TypedPlaneArray"), "PackedVector3Array"): CallShape(
        "ptrcallWithTwoVector3PlaneListArgsRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("Vector3", "Vector3", "Vector3"), "Vector3"): CallShape(
        "ptrcallWithThreeVector3ArgsRetVector3",
        "Vector3",
        "Vector3.ZERO",
    ),
    (("Vector3", "Vector3", "Vector3", "float"), "PackedVector3Array"): CallShape(
        "ptrcallWithThreeVector3DoubleArgsRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("Vector3", "Vector3", "Vector3", "Vector3"), "Vector3"): CallShape(
        "ptrcallWithFourVector3ArgsRetVector3",
        "Vector3",
        "Vector3.ZERO",
    ),
    (("Vector3", "Vector3", "Vector3", "Vector3"), "PackedVector3Array"): CallShape(
        "ptrcallWithFourVector3ArgsRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("Vector3", "Vector3", "Vector3", "Vector3", "Vector3"), "Variant"): CallShape(
        "ptrcallWithFiveVector3ArgsRetVariantScalar",
        "Any?",
        "null",
    ),
    (("Vector3", "Vector3", "Vector3", "int32"), "void"): CallShape("ptrcallWithThreeVector3AndIntArg", "Unit"),
    (("Vector3", "Vector3", "Vector3", "bool"), "void"): CallShape("ptrcallWithThreeVector3AndBoolArgs", "Unit"),
    (("Vector3", "bool"), "int64"): CallShape("ptrcallWithVector3AndBoolArgRetLong", "Long", "0L"),
    (("RID", "bitfield", "PackedColorArray", "float", "uint32", "Rect2", "uint32"), "int64"): CallShape(
        "ptrcallWithRIDLongPackedColorListDoubleUInt32Rect2UInt32ArgsRetLong",
        "Long",
        "0L",
    ),
    (
        (
            "RID",
            "uint32",
            "enum",
            "enum",
            "enum",
            "enum",
            "PackedColorArray",
            "float",
            "uint32",
            "Rect2",
            "TypedRIDArray",
        ),
        "PackedInt64Array",
    ): CallShape(
        "ptrcallWithRIDUInt32FourLongPackedColorListDoubleUInt32Rect2RIDListArgsRetPackedInt64List",
        "List<Long>",
        "emptyList()",
    ),
    (("int64", "int64", "uint32", "TypedRIDArray", "PackedInt64Array"), "void"): CallShape(
        "ptrcallWithTwoLongUInt32RIDListPackedInt64ListArgs",
        "Unit",
    ),
    (("uint32",), "PackedInt64Array"): CallShape(
        "ptrcallWithUInt32ArgRetPackedInt64List",
        "List<Long>",
        "emptyList()",
    ),
    (("RID", "uint32", "uint32", "PackedByteArray"), "enum"): CallShape(
        "ptrcallWithRIDTwoUInt32PackedByteArrayArgsRetLong",
        "Long",
        "0L",
    ),
    (("uint32", "enum", "PackedByteArray", "bool", "bitfield"), "RID"): CallShape(
        "ptrcallWithUInt32LongPackedByteArrayBoolLongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("uint32", "StringName", "String", "Variant"), "void"): CallShape(
        "ptrcallWithUInt32StringNameStringVariantArgs",
        "Unit",
    ),
    (("uint32", "PackedByteArray", "bitfield", "bitfield"), "RID"): CallShape(
        "ptrcallWithUInt32PackedByteArrayTwoLongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("PackedByteArray", "String", "Object", "uint32"), "enum"): CallShape(
        "ptrcallWithPackedByteArrayStringObjectUInt32ArgsRetLong",
        "Long",
        "0L",
    ),
    (("Vector4",), "void"): CallShape("ptrcallWithVector4Arg", "Unit"),
    (("Color",), "void"): CallShape("ptrcallWithColorArg", "Unit"),
    (("Color", "Color"), "void"): CallShape("ptrcallWithTwoColorArgs", "Unit"),
    (("Rect2",), "void"): CallShape("ptrcallWithRect2Arg", "Unit"),
    (("Rect2",), "int32"): CallShape("ptrcallWithRect2ArgRetInt", "Int", "0"),
    (("Rect2",), "TypedDictionaryArray"): CallShape(
        "ptrcallWithRect2ArgRetDictionaryList",
        "List<Map<String, Any?>>",
        "emptyList()",
    ),
    (("Rect2", "Color", "bool", "float", "bool"), "void"): CallShape(
        "ptrcallWithRect2ColorBoolDoubleBoolArgs",
        "Unit",
    ),
    (("Rect2i",), "void"): CallShape("ptrcallWithRect2iArg", "Unit"),
    (("Rect2i",), "Object"): CallShape("ptrcallWithRect2iArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("Rect2i",), "TypedDictionaryArray"): CallShape(
        "ptrcallWithRect2iArgRetDictionaryList",
        "List<Map<String, Any?>>",
        "emptyList()",
    ),
    (("Rect2i", "Color"), "void"): CallShape("ptrcallWithRect2iAndColorArg", "Unit"),
    (("Rect2i", "bool"), "void"): CallShape("ptrcallWithRect2iAndBoolArg", "Unit"),
    (("Rect2i", "float"), "void"): CallShape("ptrcallWithRect2iAndDoubleArg", "Unit"),
    (("Rect2i", "float"), "TypedPackedVector2Array"): CallShape(
        "ptrcallWithRect2iAndDoubleArgsRetPackedVector2ListList",
        "List<List<Vector2>>",
        "emptyList()",
    ),
    (("AABB",), "void"): CallShape("ptrcallWithAABBArg", "Unit"),
    ((), "Plane"): CallShape("ptrcallNoArgsRetPlane", "Plane", "Plane.ZERO"),
    (("Plane",), "void"): CallShape("ptrcallWithPlaneArg", "Unit"),
    (("int32", "Plane"), "void"): CallShape("ptrcallWithIntAndPlaneArg", "Unit"),
    (("Transform2D",), "void"): CallShape("ptrcallWithTransform2DArg", "Unit"),
    (("Transform2D", "Object", "Transform2D"), "PackedVector2Array"): CallShape(
        "ptrcallWithTransform2DObjectTransform2DArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("Transform2D", "Object", "Transform2D"), "bool"): CallShape(
        "ptrcallWithTransform2DObjectTransform2DArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Transform2D", "Vector2"), "Object"): CallShape(
        "ptrcallWithTransform2DVector2ArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Transform2D", "Vector2", "Object", "Transform2D", "Vector2"), "PackedVector2Array"): CallShape(
        "ptrcallWithTransform2DVector2ObjectTransform2DVector2ArgsRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("Transform2D", "Vector2", "Object", "Transform2D", "Vector2"), "bool"): CallShape(
        "ptrcallWithTransform2DVector2ObjectTransform2DVector2ArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Transform2D", "Vector2", "Color", "Color", "int64"), "void"): CallShape(
        "ptrcallWithTransform2DVector2TwoColorLongArgs",
        "Unit",
    ),
    (("Transform2D", "Vector2", "Color", "Color", "uint32"), "void"): CallShape(
        "ptrcallWithTransform2DVector2TwoColorUInt32Args",
        "Unit",
    ),
    (("Transform2D", "Vector2", "Object", "float", "bool"), "bool"): CallShape(
        "ptrcallWithTransform2DVector2ObjectDoubleBoolArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Transform3D",), "void"): CallShape("ptrcallWithTransform3DArg", "Unit"),
    (("Transform3D", "Vector3", "Color", "Color", "int64"), "void"): CallShape(
        "ptrcallWithTransform3DVector3TwoColorLongArgs",
        "Unit",
    ),
    (("Transform3D", "Vector3", "Color", "Color", "uint32"), "void"): CallShape(
        "ptrcallWithTransform3DVector3TwoColorUInt32Args",
        "Unit",
    ),
    (("Transform3D", "Vector3", "Object", "float", "bool", "int32"), "bool"): CallShape(
        "ptrcallWithTransform3DVector3ObjectDoubleBoolIntArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Transform3D", "RID"), "Object"): CallShape(
        "ptrcallWithTransform3DRIDArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Quaternion",), "void"): CallShape("ptrcallWithQuaternionArg", "Unit"),
    (("int32", "int32"), "Quaternion"): CallShape("ptrcallWithTwoIntArgsRetQuaternion", "Quaternion", "Quaternion.IDENTITY"),
    (("int32", "int32", "Quaternion"), "void"): CallShape("ptrcallWithTwoIntAndQuaternionArg", "Unit"),
    (("int32", "Transform3D"), "void"): CallShape("ptrcallWithIntAndTransform3DArg", "Unit"),
    (("int32", "Transform3D", "float", "bool"), "void"): CallShape(
        "ptrcallWithIntTransform3DDoubleBoolArgs",
        "Unit",
    ),
    (("Transform3D", "enum"), "Transform3D"): CallShape(
        "ptrcallWithTransform3DAndLongArgsRetTransform3D",
        "Transform3D",
        "Transform3D.IDENTITY",
    ),
    (("Transform3D", "float"), "enum"): CallShape("ptrcallWithTransform3DAndDoubleArgRetLong", "Long", "0L"),
    (("enum",), "Transform3D"): CallShape("ptrcallWithLongArgRetTransform3D", "Transform3D", "Transform3D.IDENTITY"),
    (("enum", "Transform3D"), "void"): CallShape("ptrcallWithLongAndTransform3DArg", "Unit"),
    (("uint32", "Transform2D"), "void"): CallShape("ptrcallWithUInt32AndTransform2DArg", "Unit"),
    (("uint32", "Transform3D"), "void"): CallShape("ptrcallWithUInt32AndTransform3DArg", "Unit"),
    (("int64", "float"), "void"): CallShape("ptrcallWithLongAndDoubleArg", "Unit"),
    (("int32", "float"), "void"): CallShape("ptrcallWithIntAndDoubleArg", "Unit"),
    (("uint32", "float"), "void"): CallShape("ptrcallWithUInt32AndDoubleArg", "Unit"),
    (("int32", "float"), "float"): CallShape("ptrcallWithIntAndDoubleArgRetDouble", "Double", "0.0"),
    (("int32", "float"), "int32"): CallShape("ptrcallWithIntAndDoubleArgRetInt", "Int", "0"),
    (("int32", "float"), "Vector2"): CallShape("ptrcallWithIntAndDoubleArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("int32", "float"), "Vector3"): CallShape("ptrcallWithIntAndDoubleArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("int32", "float"), "PackedVector2Array"): CallShape(
        "ptrcallWithIntAndDoubleArgRetPackedVector2List",
        "List<Vector2>",
        "emptyList()",
    ),
    (("int32", "float"), "PackedVector3Array"): CallShape(
        "ptrcallWithIntAndDoubleArgRetPackedVector3List",
        "List<Vector3>",
        "emptyList()",
    ),
    (("RID", "enum"), "float"): CallShape("ptrcallWithRIDAndLongArgRetDouble", "Double", "0.0"),
    (("RID", "float"), "int64"): CallShape("ptrcallWithRIDAndDoubleArgRetLong", "Long", "0L"),
    (("RID", "Basis"), "void"): CallShape("ptrcallWithRIDAndBasisArg", "Unit"),
    (("RID", "int64", "bool"), "Vector2"): CallShape("ptrcallWithRIDLongAndBoolArgsRetVector2", "Vector2", "Vector2.ZERO"),
    (("RID", "int64", "bool"), "Vector3"): CallShape("ptrcallWithRIDLongAndBoolArgsRetVector3", "Vector3", "Vector3.ZERO"),
    (("RID", "int32", "bool"), "void"): CallShape("ptrcallWithRIDIntAndBoolArgs", "Unit"),
    (("RID", "int32", "bool", "float"), "void"): CallShape("ptrcallWithRIDIntBoolDoubleArgs", "Unit"),
    (("RID", "int32", "RID"), "void"): CallShape("ptrcallWithRIDIntAndRIDArgs", "Unit"),
    (("RID", "int32", "Transform2D"), "void"): CallShape("ptrcallWithRIDIntAndTransform2DArg", "Unit"),
    (("RID", "int32", "Transform3D"), "void"): CallShape("ptrcallWithRIDIntAndTransform3DArg", "Unit"),
    (("RID", "int32", "int32"), "void"): CallShape("ptrcallWithRIDAndTwoIntArgs", "Unit"),
    (("RID", "int32", "float"), "void"): CallShape("ptrcallWithRIDIntDoubleArgs", "Unit"),
    (("RID", "Vector2", "int32"), "void"): CallShape("ptrcallWithRIDVector2IntArgs", "Unit"),
    (("RID", "Vector2", "Color", "Color", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2TwoColorDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "int32", "Color", "Color", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2IntTwoColorDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "int32", "int32", "Color", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2TwoIntColorDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "String", "enum", "float", "int32", "Color", "bitfield", "enum", "enum", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2StringLongDoubleIntColorThreeLongDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "String", "enum", "float", "int32", "int32", "Color", "bitfield", "enum", "enum", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "String", "enum", "float", "int32", "int32", "Color", "bitfield", "bitfield", "enum", "enum", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2StringLongDoubleTwoIntColorFourLongDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "String", "enum", "float", "int32", "int32", "int32", "Color", "bitfield", "bitfield", "enum", "enum", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2StringLongDoubleThreeIntColorFourLongDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "int32", "int32", "Color", "float"), "float"): CallShape(
        "ptrcallWithRIDVector2TwoIntColorDoubleArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("RID", "Vector2", "int32", "int32", "int32", "Color", "float"), "float"): CallShape(
        "ptrcallWithRIDVector2ThreeIntColorDoubleArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("RID", "Vector2", "Vector2", "Color", "float", "bool"), "void"): CallShape(
        "ptrcallWithRIDTwoVector2ColorDoubleBoolArgs",
        "Unit",
    ),
    (("RID", "Color", "float"), "void"): CallShape("ptrcallWithRIDColorDoubleArgs", "Unit"),
    (("RID", "Color", "enum", "float", "float", "enum"), "void"): CallShape(
        "ptrcallWithRIDColorLongTwoDoubleLongArgs",
        "Unit",
    ),
    (("RID", "Color", "uint32", "uint32", "uint32", "uint32"), "enum"): CallShape(
        "ptrcallWithRIDColorFourUInt32ArgsRetLong",
        "Long",
        "0L",
    ),
    (("RID", "int32", "String"), "void"): CallShape("ptrcallWithRIDIntAndStringArgs", "Unit"),
    (("RID", "int32", "Vector3"), "void"): CallShape("ptrcallWithRIDIntAndVector3Arg", "Unit"),
    (("RID", "int32", "enum", "bool", "bool", "bool"), "void"): CallShape(
        "ptrcallWithRIDIntLongThreeBoolArgs",
        "Unit",
    ),
    (("RID", "int32", "int32", "PackedByteArray"), "void"): CallShape(
        "ptrcallWithRIDIntIntAndByteArrayArgs",
        "Unit",
    ),
    (("RID", "RID", "Transform3D", "RID", "Transform3D"), "void"): CallShape(
        "ptrcallWithRIDRIDTransform3DRIDTransform3DArgs",
        "Unit",
    ),
    (("RID", "RID", "uint32", "uint32", "uint32"), "enum"): CallShape(
        "ptrcallWithTwoRIDThreeUInt32ArgsRetLong",
        "Long",
        "0L",
    ),
    (("RID", "RID", "Vector3", "Vector3", "Vector3", "uint32", "uint32", "uint32", "uint32"), "enum"): CallShape(
        "ptrcallWithTwoRIDThreeVector3FourUInt32ArgsRetLong",
        "Long",
        "0L",
    ),
    (("RID", "RID", "Transform2D", "Color", "RID"), "void"): CallShape(
        "ptrcallWithTwoRIDTransform2DColorRIDArgs",
        "Unit",
    ),
    (("int64", "bool", "RID", "uint32", "uint32", "uint32"), "void"): CallShape(
        "ptrcallWithLongBoolRIDThreeUInt32Args",
        "Unit",
    ),
    (("RID", "RID", "Vector3", "RID", "Vector3"), "void"): CallShape(
        "ptrcallWithTwoRIDVector3RIDVector3Args",
        "Unit",
    ),
    (("RID", "Transform3D", "Vector3", "Color", "Color", "uint32"), "void"): CallShape(
        "ptrcallWithRIDTransform3DVector3TwoColorUInt32Args",
        "Unit",
    ),
    (("RID", "RID", "int64", "Vector2", "int64", "Color", "float"), "void"): CallShape(
        "ptrcallWithTwoRIDLongVector2LongColorDoubleArgs",
        "Unit",
    ),
    (("RID", "RID", "int64", "int64", "Vector2", "int64", "Color", "float"), "void"): CallShape(
        "ptrcallWithTwoRIDTwoLongVector2LongColorDoubleArgs",
        "Unit",
    ),
    (("RID", "int64", "Vector2", "int64", "Color"), "void"): CallShape(
        "ptrcallWithRIDLongVector2LongColorArgs",
        "Unit",
    ),
    (("RID", "RID", "Vector2", "float", "float", "Color", "float"), "void"): CallShape(
        "ptrcallWithTwoRIDVector2TwoDoubleColorDoubleArgs",
        "Unit",
    ),
    (("RID", "RID", "Vector2", "float", "float", "int64", "Color", "float"), "void"): CallShape(
        "ptrcallWithTwoRIDVector2TwoDoubleLongColorDoubleArgs",
        "Unit",
    ),
    (("RID", "RID", "Vector2"), "void"): CallShape("ptrcallWithTwoRIDAndVector2Arg", "Unit"),
    (("RID", "RID", "Transform2D"), "void"): CallShape("ptrcallWithTwoRIDAndTransform2DArg", "Unit"),
    (("RID", "RID", "Transform2D", "bool"), "void"): CallShape("ptrcallWithTwoRIDTransform2DBoolArgs", "Unit"),
    (("RID", "RID", "Transform3D", "bool"), "void"): CallShape("ptrcallWithTwoRIDTransform3DBoolArgs", "Unit"),
    (("RID", "StringName", "RID", "int32"), "void"): CallShape("ptrcallWithRIDStringNameRIDIntArgs", "Unit"),
    (("RID", "StringName", "int32"), "RID"): CallShape(
        "ptrcallWithRIDStringNameAndIntArgRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("RID", "bool", "float"), "void"): CallShape("ptrcallWithRIDBoolDoubleArgs", "Unit"),
    (("RID", "bool", "int32", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithRIDBoolIntThreeDoubleArgs",
        "Unit",
    ),
    (("RID", "bool", "float", "float"), "void"): CallShape("ptrcallWithRIDBoolTwoDoubleArgs", "Unit"),
    (("RID", "bool", "float", "float", "bool", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithRIDBoolTwoDoubleBoolThreeDoubleArgs",
        "Unit",
    ),
    (("RID", "bool", "float", "float", "float"), "void"): CallShape("ptrcallWithRIDBoolThreeDoubleArgs", "Unit"),
    (("RID", "bool", "float", "float", "float", "bool", "RID"), "void"): CallShape(
        "ptrcallWithRIDBoolThreeDoubleBoolRIDArgs",
        "Unit",
    ),
    (("RID", "bool", "float", "float", "float", "float"), "void"): CallShape("ptrcallWithRIDBoolFourDoubleArgs", "Unit"),
    (("RID", "bool", "float", "float", "float", "float", "float", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithRIDBoolDoubleDoubleDoubleDoubleDoubleDoubleDoubleDoubleArgs",
        "Unit",
    ),
    (("RID", "bool", "Color", "float", "float", "float", "float", "float", "float", "float", "enum"), "void"): CallShape(
        "ptrcallWithRIDBoolColorDoubleDoubleDoubleDoubleDoubleDoubleDoubleLongArgs",
        "Unit",
    ),
    (("RID", "bool", "int32", "float", "enum", "bool", "float", "bool", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithRIDBoolIntDoubleLongBoolDoubleBoolThreeDoubleArgs",
        "Unit",
    ),
    (("RID", "bool", "float", "Color", "Color", "float", "float", "float", "float", "float", "bool", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithRIDBoolDoubleTwoColorDoubleDoubleDoubleDoubleDoubleBoolThreeDoubleArgs",
        "Unit",
    ),
    (("RID", "enum", "float", "bool", "float", "bool"), "void"): CallShape(
        "ptrcallWithRIDLongDoubleBoolDoubleBoolArgs",
        "Unit",
    ),
    (("RID", "float", "float", "float", "float"), "void"): CallShape("ptrcallWithRIDFourDoubleArgs", "Unit"),
    (("RID", "bool", "Vector2i"), "Object"): CallShape(
        "ptrcallWithRIDBoolVector2iArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("enum", "enum", "int64", "int32", "int32", "int32", "int32", "enum"), "RID"): CallShape(
        "ptrcallWithThreeLongFourIntLongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("enum", "enum", "enum", "bitfield", "int64", "int64", "int64", "int64", "int64", "int64"), "RID"): CallShape(
        "ptrcallWithFourLongLongLongLongLongLongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("RID", "float", "bool", "Vector2i"), "Object"): CallShape(
        "ptrcallWithRIDDoubleBoolVector2iArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("RID", "float", "bool"), "void"): CallShape("ptrcallWithRIDDoubleBoolArgs", "Unit"),
    (("RID", "Rect2", "int32"), "void"): CallShape("ptrcallWithRIDRect2IntArgs", "Unit"),
    (("RID", "Rect2", "Color", "bool"), "void"): CallShape("ptrcallWithRIDRect2ColorBoolArgs", "Unit"),
    (("RID", "Rect2", "RID", "bool", "Color", "bool"), "void"): CallShape(
        "ptrcallWithRIDRect2RIDBoolColorBoolArgs",
        "Unit",
    ),
    (("RID", "Rect2", "RID", "Rect2", "Color", "bool", "bool"), "void"): CallShape(
        "ptrcallWithRIDRect2RIDRect2ColorTwoBoolArgs",
        "Unit",
    ),
    (("RID", "Rect2", "RID", "Rect2", "Color"), "void"): CallShape("ptrcallWithRIDRect2RIDRect2ColorArgs", "Unit"),
    (("RID", "Rect2", "RID", "Rect2", "Color", "int32", "float", "float"), "void"): CallShape(
        "ptrcallWithRIDRect2RIDRect2ColorIntTwoDoubleArgs",
        "Unit",
    ),
    (("RID", "Rect2", "Rect2", "RID", "Vector2", "Vector2", "enum", "enum", "bool", "Color"), "void"): CallShape(
        "ptrcallWithRIDTwoRect2RIDTwoVector2TwoLongBoolColorArgs",
        "Unit",
    ),
    (("RID", "Rect2", "Rect2i", "bool", "uint32", "bool", "Vector2", "float", "float", "float", "float"), "void"): CallShape(
        "ptrcallWithRIDRect2Rect2iBoolUInt32BoolVector2FourDoubleArgs",
        "Unit",
    ),
    (("RID", "RID", "Rect2", "int32"), "void"): CallShape("ptrcallWithTwoRIDRect2IntArgs", "Unit"),
    (("RID", "RID", "int32", "int32"), "void"): CallShape("ptrcallWithTwoRIDTwoIntArgs", "Unit"),
    (("RID", "float", "float", "float"), "void"): CallShape("ptrcallWithRIDAndThreeDoubleArgs", "Unit"),
    (("RID", "Vector2", "int32", "Color", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2IntColorDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "Color", "float"), "void"): CallShape(
        "ptrcallWithRIDVector2ColorDoubleArgs",
        "Unit",
    ),
    (("RID", "Vector2", "float", "Color", "bool"), "void"): CallShape(
        "ptrcallWithRIDVector2DoubleColorBoolArgs",
        "Unit",
    ),
    (("RID", "Vector2", "float", "float", "Color", "bool"), "void"): CallShape(
        "ptrcallWithRIDVector2TwoDoubleColorBoolArgs",
        "Unit",
    ),
    (("RID", "enum", "float"), "void"): CallShape("ptrcallWithRIDLongAndDoubleArgs", "Unit"),
    (("RID", "int64", "float"), "void"): CallShape("ptrcallWithRIDLongAndDoubleArgs", "Unit"),
    (("RID", "enum", "float", "float"), "void"): CallShape("ptrcallWithRIDLongTwoDoubleArgs", "Unit"),
    (("RID", "enum", "enum", "float"), "void"): CallShape("ptrcallWithRIDTwoLongDoubleArgs", "Unit"),
    (("RID", "enum", "enum"), "float"): CallShape("ptrcallWithRIDTwoLongArgsRetDouble", "Double", "0.0"),
    (("RID", "enum", "enum", "bool"), "void"): CallShape("ptrcallWithRIDTwoLongBoolArgs", "Unit"),
    (("RID", "enum", "enum"), "bool"): CallShape("ptrcallWithRIDTwoLongArgsRetBool", "Boolean", "false"),
    (("RID", "float", "float", "float", "float", "enum"), "void"): CallShape("ptrcallWithRIDFourDoubleLongArgs", "Unit"),
    (("RID", "enum", "bool"), "void"): CallShape("ptrcallWithRIDLongAndBoolArgs", "Unit"),
    (("enum", "enum", "int32"), "void"): CallShape("ptrcallWithTwoLongAndIntArgs", "Unit"),
    (("enum", "enum", "enum"), "enum"): CallShape("ptrcallWithThreeLongArgsRetLong", "Long", "0L"),
    (("int32", "float", "Vector3"), "int32"): CallShape("ptrcallWithIntDoubleVector3ArgsRetInt", "Int", "0"),
    (("int32", "float", "Quaternion"), "int32"): CallShape(
        "ptrcallWithIntDoubleQuaternionArgsRetInt",
        "Int",
        "0",
    ),
    (("int32", "float", "float"), "int32"): CallShape(
        "ptrcallWithIntAndTwoDoubleArgsRetInt",
        "Int",
        "0",
    ),
    (("int32", "float", "bool"), "Vector3"): CallShape("ptrcallWithIntDoubleBoolArgsRetVector3", "Vector3", "Vector3.ZERO"),
    (("int32", "float", "bool"), "Quaternion"): CallShape(
        "ptrcallWithIntDoubleBoolArgsRetQuaternion",
        "Quaternion",
        "Quaternion.IDENTITY",
    ),
    (("int32", "float", "bool"), "float"): CallShape(
        "ptrcallWithIntDoubleBoolArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("int32", "float", "StringName"), "int32"): CallShape(
        "ptrcallWithIntDoubleStringNameArgsRetInt",
        "Int",
        "0",
    ),
    (("int32", "int32", "Vector2", "float"), "void"): CallShape("ptrcallWithTwoIntVector2DoubleArgs", "Unit"),
    (("int32", "int32", "int32", "int32"), "void"): CallShape("ptrcallWithFourIntArgs", "Unit"),
    (("int32", "Vector2i", "int32"), "int32"): CallShape("ptrcallWithIntVector2iIntArgsRetInt", "Int", "0"),
    (("int32", "Vector2i", "int32"), "bool"): CallShape("ptrcallWithIntVector2iIntArgsRetBool", "Boolean", "false"),
    (("int32", "Vector2i", "int32"), "Rect2"): CallShape("ptrcallWithIntVector2iIntArgsRetRect2", "Rect2", "Rect2.ZERO"),
    (("int32", "Vector2i", "int32"), "PackedInt32Array"): CallShape(
        "ptrcallWithIntVector2iIntArgsRetPackedInt32List",
        "List<Int>",
        "emptyList()",
    ),
    (("int32", "Vector2i", "int32"), "Array"): CallShape(
        "ptrcallWithIntVector2iIntArgsRetArray",
        "List<Any?>",
        "emptyList()",
    ),
    (("int32", "Vector2i", "int32", "Rect2"), "void"): CallShape("ptrcallWithIntVector2iIntRect2Args", "Unit"),
    (("int32", "Vector2i", "int32", "PackedInt32Array"), "void"): CallShape(
        "ptrcallWithIntVector2iIntPackedInt32ListArgs",
        "Unit",
    ),
    (("int32", "Vector2i", "int32", "int32"), "void"): CallShape("ptrcallWithIntVector2iTwoIntArgs", "Unit"),
    (("int32", "Vector2i", "Object"), "void"): CallShape("ptrcallWithIntVector2iAndObjectArg", "Unit"),
    (("int32", "Vector2i", "int32", "Object"), "void"): CallShape(
        "ptrcallWithIntVector2iIntObjectArgs",
        "Unit",
    ),
    (("int32", "Vector2i", "int64", "int64"), "void"): CallShape("ptrcallWithIntVector2iTwoLongArgs", "Unit"),
    (("int32", "Vector2i", "int32", "Vector2i"), "void"): CallShape("ptrcallWithIntVector2iIntVector2iArgs", "Unit"),
    (("int32", "Vector2i", "int32", "Vector2i", "int32"), "void"): CallShape(
        "ptrcallWithIntVector2iIntVector2iIntArgs",
        "Unit",
    ),
    (("int32", "Vector2i", "int32", "int32", "Vector2i", "int32"), "void"): CallShape(
        "ptrcallWithIntVector2iTwoIntVector2iIntArgs",
        "Unit",
    ),
    (("Vector2i",), "int32"): CallShape("ptrcallWithVector2iArgRetInt", "Int", "0"),
    (("Vector2i",), "bool"): CallShape("ptrcallWithVector2iArgRetBool", "Boolean", "false"),
    (("Vector2i",), "float"): CallShape("ptrcallWithVector2iArgRetDouble", "Double", "0.0"),
    (("Vector2i",), "Vector2"): CallShape("ptrcallWithVector2iArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("Vector2i",), "Vector2i"): CallShape("ptrcallWithVector2iArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("Vector2i",), "Object"): CallShape("ptrcallWithVector2iArgRetObject", "MemorySegment", "MemorySegment.NULL"),
    (("Vector2i",), "Color"): CallShape("ptrcallWithVector2iArgRetColor", "Color", "Color(0f, 0f, 0f, 0f)"),
    (("Vector2i",), "enum"): CallShape("ptrcallWithVector2iArgRetLong", "Long", "0L"),
    (("Vector2i", "Color"), "void"): CallShape("ptrcallWithVector2iAndColorArg", "Unit"),
    (("Vector2i", "Object"), "void"): CallShape("ptrcallWithVector2iAndObjectArg", "Unit"),
    (("Vector2i", "int32"), "Object"): CallShape(
        "ptrcallWithVector2iAndIntArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Vector3i",), "void"): CallShape("ptrcallWithVector3iArg", "Unit"),
    (("Vector3i",), "int32"): CallShape("ptrcallWithVector3iArgRetInt", "Int", "0"),
    (("Vector3i",), "Vector3"): CallShape("ptrcallWithVector3iArgRetVector3", "Vector3", "Vector3.ZERO"),
    (("Vector3i",), "Basis"): CallShape("ptrcallWithVector3iArgRetBasis", "Basis", "Basis.IDENTITY"),
    (("Vector3i", "Vector3i"), "void"): CallShape("ptrcallWithTwoVector3iArgs", "Unit"),
    (("Vector3i", "int32", "int32"), "void"): CallShape("ptrcallWithVector3iIntIntArgs", "Unit"),
    (("Vector2i",), "TypedVector2iArray"): CallShape(
        "ptrcallWithVector2iArgRetVector2iList",
        "List<Vector2i>",
        "emptyList()",
    ),
    (("Vector2i", "int32"), "void"): CallShape("ptrcallWithVector2iAndIntArg", "Unit"),
    (("Vector2i", "int32"), "int32"): CallShape("ptrcallWithVector2iAndIntArgRetInt", "Int", "0"),
    (("Vector2i", "int32"), "bool"): CallShape("ptrcallWithVector2iAndIntArgRetBool", "Boolean", "false"),
    (("Vector2i", "int32"), "float"): CallShape("ptrcallWithVector2iAndIntArgRetDouble", "Double", "0.0"),
    (("Vector2i", "int32"), "Rect2i"): CallShape("ptrcallWithVector2iAndIntArgRetRect2i", "Rect2i", "Rect2i.ZERO"),
    (("Vector2i", "enum"), "Vector2i"): CallShape("ptrcallWithVector2iAndLongArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("Vector2i", "enum"), "void"): CallShape("ptrcallWithVector2iAndLongArg", "Unit"),
    (("Vector2i", "Vector2i"), "void"): CallShape("ptrcallWithTwoVector2iArgs", "Unit"),
    (("Vector2i", "Vector2i", "Vector2i"), "void"): CallShape("ptrcallWithThreeVector2iArgs", "Unit"),
    (("Vector2i", "Vector2i", "int32", "Vector2i", "int32", "Vector2i"), "bool"): CallShape(
        "ptrcallWithTwoVector2iIntVector2iIntVector2iArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Vector2i", "bool"), "void"): CallShape("ptrcallWithVector2iAndBoolArg", "Unit"),
    (("Vector2i", "bool", "bool"), "Vector2i"): CallShape(
        "ptrcallWithVector2iAndTwoBoolArgsRetVector2i",
        "Vector2i",
        "Vector2i.ZERO",
    ),
    (("Vector2i", "float"), "void"): CallShape("ptrcallWithVector2iAndDoubleArg", "Unit"),
    (("Vector2i", "int32", "float"), "void"): CallShape("ptrcallWithVector2iIntDoubleArgs", "Unit"),
    (("Vector2i", "int32", "int32"), "void"): CallShape("ptrcallWithVector2iAndTwoIntArgs", "Unit"),
    (("int32", "Vector2i"), "void"): CallShape("ptrcallWithIntAndVector2iArg", "Unit"),
    (("int32", "Vector2i", "bool"), "int32"): CallShape("ptrcallWithIntVector2iAndBoolArgRetInt", "Int", "0"),
    (("int32", "Vector2i", "bool"), "Vector2i"): CallShape(
        "ptrcallWithIntVector2iAndBoolArgRetVector2i",
        "Vector2i",
        "Vector2i.ZERO",
    ),
    (("int32", "Vector2i", "bool"), "bool"): CallShape(
        "ptrcallWithIntVector2iAndBoolArgRetBool",
        "Boolean",
        "false",
    ),
    (("int32", "Vector2i", "bool"), "Object"): CallShape(
        "ptrcallWithIntVector2iAndBoolArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("int32", "Vector2i", "int32"), "void"): CallShape("ptrcallWithIntVector2iAndIntArg", "Unit"),
    (("int32", "Vector2i", "int32"), "TypedVector2iArray"): CallShape(
        "ptrcallWithIntVector2iAndIntArgsRetVector2iList",
        "List<Vector2i>",
        "emptyList()",
    ),
    (("int32", "TypedVector2iArray", "int32", "int32", "bool"), "void"): CallShape(
        "ptrcallWithIntVector2iListTwoIntAndBoolArgs",
        "Unit",
    ),
    (("int32", "Vector2i", "int32"), "Vector2"): CallShape(
        "ptrcallWithIntVector2iIntArgsRetVector2",
        "Vector2",
        "Vector2.ZERO",
    ),
    (("int32", "Vector2i", "int32", "Vector2"), "void"): CallShape("ptrcallWithIntVector2iIntVector2Args", "Unit"),
    (("int32", "int32", "Vector2i", "int32"), "TypedVector2iArray"): CallShape(
        "ptrcallWithTwoIntVector2iAndIntArgsRetVector2iList",
        "List<Vector2i>",
        "emptyList()",
    ),
    (("Vector2i", "int32", "Vector2i", "int32"), "void"): CallShape(
        "ptrcallWithVector2iIntVector2iIntArgs",
        "Unit",
    ),
    (("int32",), "PackedVector2Array"): CallShape("ptrcallWithIntArgRetPackedVector2List", "List<Vector2>", "emptyList()"),
    (("int32",), "Array"): CallShape("ptrcallWithIntArgRetArray", "List<Any?>", "emptyList()"),
    (("int32", "Vector2i"), "Array"): CallShape("ptrcallWithIntVector2iArgsRetArray", "List<Any?>", "emptyList()"),
    (("int32",), "Dictionary"): CallShape("ptrcallWithIntArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("int32", "int32"), "Vector2"): CallShape("ptrcallWithTwoIntArgsRetVector2", "Vector2", "Vector2.ZERO"),
    (("int32", "int32"), "Array"): CallShape("ptrcallWithTwoIntArgsRetArray", "List<Any?>", "emptyList()"),
    (("NodePath", "bool"), "void"): CallShape("ptrcallWithNodePathAndBoolArgs", "Unit"),
    (("NodePath", "int32"), "void"): CallShape("ptrcallWithNodePathAndIntArg", "Unit"),
    (("NodePath", "enum"), "void"): CallShape("ptrcallWithNodePathAndLongArg", "Unit"),
    (("NodePath", "enum"), "int32"): CallShape("ptrcallWithNodePathAndLongArgRetInt", "Int", "0"),
    (("NodePath", "Rect2", "int32", "int32"), "void"): CallShape("ptrcallWithNodePathRect2TwoIntArgs", "Unit"),
    (("enum", "NodePath"), "void"): CallShape("ptrcallWithLongAndNodePathArg", "Unit"),
    (("enum", "String", "Variant", "enum", "String", "int32"), "void"): CallShape(
        "ptrcallWithLongStringVariantLongStringIntArgs",
        "Unit",
    ),
    (("enum", "float", "bool", "bool"), "void"): CallShape("ptrcallWithLongDoubleTwoBoolArgs", "Unit"),
    (("enum", "float", "float", "bool"), "void"): CallShape("ptrcallWithLongTwoDoubleAndBoolArgs", "Unit"),
    (("StringName", "float"), "void"): CallShape("ptrcallWithStringNameAndDoubleArg", "Unit"),
    (("StringName", "float", "enum", "enum"), "void"): CallShape(
        "ptrcallWithStringNameDoubleTwoLongArgs",
        "Unit",
    ),
    (("StringName", "float", "float", "bool", "bool", "float", "enum"), "void"): CallShape(
        "ptrcallWithStringNameTwoDoubleTwoBoolDoubleLongArgs",
        "Unit",
    ),
    (("StringName", "Object", "float", "bool", "bool", "float", "enum", "bool", "bool"), "float"): CallShape(
        "ptrcallWithStringNameObjectDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble",
        "Double",
        "0.0",
    ),
    (("StringName", "float", "bool"), "void"): CallShape("ptrcallWithStringNameDoubleBoolArgs", "Unit"),
    (("StringName", "Color"), "void"): CallShape("ptrcallWithStringNameAndColorArg", "Unit"),
    (("StringName", "Vector2"), "void"): CallShape("ptrcallWithStringNameAndVector2Arg", "Unit"),
    (("StringName", "bool"), "void"): CallShape("ptrcallWithStringNameAndBoolArg", "Unit"),
    (("StringName", "Array"), "void"): CallShape("ptrcallWithStringNameArrayArgs", "Unit"),
    (("StringName", "Array"), "Variant"): CallShape(
        "ptrcallWithStringNameArrayArgsRetVariantScalar",
        "Any?",
        "null",
    ),
    (("StringName", "Array", "bool"), "void"): CallShape("ptrcallWithStringNameArrayBoolArgs", "Unit"),
    (("StringName", "bool", "Array"), "void"): CallShape("ptrcallWithStringNameBoolArrayArgs", "Unit"),
    (("StringName", "bool"), "bool"): CallShape("ptrcallWithStringNameAndBoolArgRetBool", "Boolean", "false"),
    (("StringName", "bool"), "float"): CallShape("ptrcallWithStringNameAndBoolArgRetDouble", "Double", "0.0"),
    (("StringName", "bool"), "PackedStringArray"): CallShape(
        "ptrcallWithStringNameAndBoolArgRetPackedStringList",
        "List<String>",
        "emptyList()",
    ),
    (("StringName", "bool"), "TypedDictionaryArray"): CallShape(
        "ptrcallWithStringNameAndBoolArgRetDictionaryList",
        "List<Map<String, Any?>>",
        "emptyList()",
    ),
    (("StringName", "int32"), "void"): CallShape("ptrcallWithStringNameAndIntArg", "Unit"),
    (("StringName", "int32"), "int32"): CallShape("ptrcallWithStringNameAndIntArgRetInt", "Int", "0"),
    (("StringName", "int32"), "float"): CallShape(
        "ptrcallWithStringNameAndIntArgRetDouble",
        "Double",
        "0.0",
    ),
    (("StringName", "int32", "Object", "float"), "void"): CallShape(
        "ptrcallWithStringNameIntObjectDoubleArgs",
        "Unit",
    ),
    (("StringName", "int32"), "Object"): CallShape(
        "ptrcallWithStringNameAndIntArgRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("StringName", "Object"), "void"): CallShape("ptrcallWithStringNameAndObjectArg", "Unit"),
    (("StringName", "String"), "Variant"): CallShape(
        "ptrcallWithStringNameAndStringArgRetVariantScalar",
        "Any?",
        "null",
    ),
    (("NodePath",), "Variant"): CallShape("ptrcallWithNodePathArgRetVariantScalar", "Any?", "null"),
    (("StringName", "PackedStringArray"), "Variant"): CallShape(
        "ptrcallWithStringNamePackedStringListArgRetVariantScalar",
        "Any?",
        "null",
    ),
    (("StringName", "Variant"), "void"): CallShape("ptrcallWithStringNameAndVariantArg", "Unit"),
    (("StringName", "Variant"), "Variant"): CallShape(
        "ptrcallWithStringNameAndVariantArgRetVariantScalar",
        "Any?",
        "null",
    ),
    (("StringName", "String", "Variant"), "void"): CallShape("ptrcallWithStringNameStringAndVariantArg", "Unit"),
    (("StringName", "Variant", "bool"), "void"): CallShape("ptrcallWithStringNameVariantBoolArgs", "Unit"),
    (("StringName", "Variant", "StringName", "bool"), "void"): CallShape(
        "ptrcallWithStringNameVariantStringNameBoolArgs",
        "Unit",
    ),
    (("Variant",), "void"): CallShape("ptrcallWithVariantArg", "Unit"),
    (("Variant",), "int32"): CallShape("ptrcallWithVariantArgRetInt", "Int", "0"),
    (("Variant",), "enum"): CallShape("ptrcallWithVariantArgRetLong", "Long", "0L"),
    (("Variant",), "String"): CallShape("ptrcallWithVariantArgRetString", "String", '""'),
    (("Variant",), "bool"): CallShape("ptrcallWithVariantArgRetBool", "Boolean", "false"),
    (("Variant",), "Rect2"): CallShape("ptrcallWithVariantArgRetRect2", "Rect2", "Rect2.ZERO"),
    (("Variant", "bool"), "void"): CallShape("ptrcallWithVariantAndBoolArg", "Unit"),
    (("Variant", "bool"), "enum"): CallShape("ptrcallWithVariantAndBoolArgRetLong", "Long", "0L"),
    (("Variant", "bool"), "String"): CallShape("ptrcallWithVariantAndBoolArgRetString", "String", '""'),
    (("Variant", "bool"), "bool"): CallShape("ptrcallWithVariantAndBoolArgRetBool", "Boolean", "false"),
    (("Variant", "bool"), "Variant"): CallShape("ptrcallWithVariantAndBoolArgRetVariantScalar", "Any?", "null"),
    (("Variant", "Variant"), "Dictionary"): CallShape(
        "ptrcallWithTwoVariantArgsRetDictionary",
        "Map<String, Any?>",
        "emptyMap()",
    ),
    (("Variant", "Variant", "float", "float", "enum", "enum"), "Variant"): CallShape(
        "ptrcallWithTwoVariantTwoDoubleTwoLongArgsRetVariantScalar",
        "Any?",
        "null",
    ),
    (("Variant", "Vector2", "enum", "int32", "float"), "bool"): CallShape(
        "ptrcallWithVariantVector2LongIntDoubleArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Variant", "Vector2", "enum", "float"), "bool"): CallShape(
        "ptrcallWithVariantVector2LongDoubleArgsRetBool",
        "Boolean",
        "false",
    ),
    (("Variant", "bitfield", "Object", "int32", "int32", "Color", "enum", "Rect2", "bool", "String", "bool", "bool"), "void"): CallShape(
        "ptrcallWithVariantLongObjectTwoIntColorLongRect2BoolStringTwoBoolArgs",
        "Unit",
    ),
    (("Variant", "enum", "String"), "void"): CallShape("ptrcallWithVariantLongStringArgs", "Unit"),
    (("Array", "Object", "bool", "bool"), "Variant"): CallShape(
        "ptrcallWithArrayObjectTwoBoolArgsRetVariantScalar",
        "Any?",
        "null",
    ),
    (("Variant", "String", "bool", "bool"), "String"): CallShape(
        "ptrcallWithVariantStringTwoBoolArgsRetString",
        "String",
        '""',
    ),
    (("String", "String", "Variant"), "void"): CallShape("ptrcallWithTwoStringAndVariantArg", "Unit"),
    (("String", "String", "Variant"), "Variant"): CallShape(
        "ptrcallWithTwoStringAndVariantArgRetVariantScalar",
        "Any?",
        "null",
    ),
    (("Object", "StringName", "Variant"), "void"): CallShape("ptrcallWithObjectStringNameAndVariantArg", "Unit"),
    (("String", "Object", "StringName", "Variant"), "void"): CallShape(
        "ptrcallWithStringObjectStringNameVariantArgs",
        "Unit",
    ),
    (("Object", "Object", "StringName", "Variant"), "void"): CallShape(
        "ptrcallWithTwoObjectStringNameVariantArgs",
        "Unit",
    ),
    (("Object", "Variant"), "enum"): CallShape("ptrcallWithObjectAndVariantArgRetLong", "Long", "0L"),
    (("Object", "StringName"), "Variant"): CallShape(
        "ptrcallWithObjectStringNameArgRetVariantScalar",
        "Any?",
        "null",
    ),
    (("Object", "StringName", "Variant"), "enum"): CallShape(
        "ptrcallWithObjectStringNameAndVariantArgRetLong",
        "Long",
        "0L",
    ),
    (("Object", "enum", "String", "enum", "String", "uint32", "bool"), "Object"): CallShape(
        "ptrcallWithObjectLongStringLongStringUInt32BoolArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("enum", "StringName", "StringName"), "Variant"): CallShape(
        "ptrcallWithLongAndTwoStringNameArgsRetVariantScalar",
        "Any?",
        "null",
    ),
    (("enum", "StringName", "StringName", "Variant"), "void"): CallShape(
        "ptrcallWithLongAndTwoStringNameAndVariantArg",
        "Unit",
    ),
    (("enum", "String", "String", "Color", "Object", "Variant", "int32"), "void"): CallShape(
        "ptrcallWithLongTwoStringColorObjectVariantIntArgs",
        "Unit",
    ),
    (("NodePath", "Variant"), "void"): CallShape("ptrcallWithNodePathAndVariantArg", "Unit"),
    (("int32", "Variant", "Variant"), "void"): CallShape("ptrcallWithIntAndTwoVariantArgs", "Unit"),
    (("Variant", "Object"), "void"): CallShape("ptrcallWithVariantAndObjectArg", "Unit"),
    (("RID", "Variant"), "void"): CallShape("ptrcallWithRIDAndVariantArg", "Unit"),
    (("RID", "Variant"), "bool"): CallShape("ptrcallWithRIDAndVariantArgRetBool", "Boolean", "false"),
    (("RID", "Variant"), "int32"): CallShape("ptrcallWithRIDAndVariantArgRetInt", "Int", "0"),
    (("RID", "Variant"), "int64"): CallShape("ptrcallWithRIDAndVariantArgRetLong", "Long", "0L"),
    (("RID", "Variant"), "Rect2"): CallShape("ptrcallWithRIDAndVariantArgRetRect2", "Rect2", "Rect2.ZERO"),
    (("RID", "Variant"), "Vector2i"): CallShape("ptrcallWithRIDAndVariantArgRetVector2i", "Vector2i", "Vector2i.ZERO"),
    (("RID", "Variant", "Vector2", "enum", "int64", "float"), "bool"): CallShape(
        "ptrcallWithRIDVariantVector2LongLongDoubleArgsRetBool",
        "Boolean",
        "false",
    ),
    (("RID", "Variant", "Vector2", "enum", "float"), "bool"): CallShape(
        "ptrcallWithRIDVariantVector2LongDoubleArgsRetBool",
        "Boolean",
        "false",
    ),
    (("RID", "int32", "Variant"), "void"): CallShape("ptrcallWithRIDIntAndVariantArgs", "Unit"),
    (("RID", "enum", "Variant"), "void"): CallShape("ptrcallWithRIDLongAndVariantArgs", "Unit"),
    (("RID", "int64", "Variant"), "void"): CallShape("ptrcallWithRIDLongAndVariantArgs", "Unit"),
    (("RID", "StringName", "Variant"), "void"): CallShape("ptrcallWithRIDStringNameAndVariantArgs", "Unit"),
    (("RID", "int64", "int64"), "RID"): CallShape("ptrcallWithRIDAndTwoLongArgsRetRID", "RID", "RID.EMPTY"),
    (("int64", "RID", "int64"), "void"): CallShape("ptrcallWithLongRIDAndLongArgs", "Unit"),
    (("int64", "RID", "uint32"), "void"): CallShape("ptrcallWithLongRIDAndUInt32Args", "Unit"),
    (("Object", "Rect2i"), "void"): CallShape("ptrcallWithObjectAndRect2iArg", "Unit"),
    (("Object", "Rect2i", "Vector2i"), "void"): CallShape("ptrcallWithObjectRect2iAndVector2iArgs", "Unit"),
    (("Object", "Object", "Rect2i", "Vector2i"), "void"): CallShape(
        "ptrcallWithTwoObjectRect2iAndVector2iArgs",
        "Unit",
    ),
    (("Object", "Object"), "void"): CallShape("ptrcallWithTwoObjectArgs", "Unit"),
    (("Object", "Object"), "Object"): CallShape(
        "ptrcallWithTwoObjectArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Object", "Object", "int64"), "enum"): CallShape("ptrcallWithTwoObjectLongArgsRetLong", "Long", "0L"),
    (("Object", "Object"), "enum"): CallShape("ptrcallWithTwoObjectArgsRetLong", "Long", "0L"),
    (("Object", "Object", "uint32"), "enum"): CallShape("ptrcallWithTwoObjectUInt32ArgsRetLong", "Long", "0L"),
    (("Object", "float", "bool", "bool"), "Object"): CallShape(
        "ptrcallWithObjectDoubleTwoBoolArgsRetObject",
        "MemorySegment",
        "MemorySegment.NULL",
    ),
    (("Object", "Vector2i"), "void"): CallShape("ptrcallWithObjectAndVector2iArg", "Unit"),
    (("Object", "float"), "void"): CallShape("ptrcallWithObjectAndDoubleArg", "Unit"),
    (("Object", "enum", "Vector2"), "void"): CallShape("ptrcallWithObjectLongAndVector2Arg", "Unit"),
    (("enum", "Object", "Object"), "void"): CallShape("ptrcallWithLongAndTwoObjectArgs", "Unit"),
    (("enum", "Array", "String"), "TypedVector3iArray"): CallShape(
        "ptrcallWithLongArrayStringArgsRetVector3iList",
        "List<Vector3i>",
        "emptyList()",
    ),
    (("enum", "Object", "Vector2", "int32"), "void"): CallShape("ptrcallWithLongObjectVector2IntArgs", "Unit"),
    (("Object", "Vector2i", "float"), "void"): CallShape("ptrcallWithObjectVector2iAndDoubleArg", "Unit"),
    (("Vector2i", "Vector2i", "bool"), "TypedVector2iArray"): CallShape(
        "ptrcallWithTwoVector2iAndBoolArgsRetVector2iList",
        "List<Vector2i>",
        "emptyList()",
    ),
    (("Vector2i", "Vector2i", "Object"), "Vector2i"): CallShape(
        "ptrcallWithTwoVector2iAndObjectArgRetVector2i",
        "Vector2i",
        "Vector2i.ZERO",
    ),
    (("Vector2i", "enum", "int64"), "RID"): CallShape(
        "ptrcallWithVector2iLongLongArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("AABB", "RID"), "PackedInt64Array"): CallShape(
        "ptrcallWithAABBRIDArgsRetPackedInt64List",
        "List<Long>",
        "emptyList()",
    ),
    (("Vector3", "Vector3", "RID"), "PackedInt64Array"): CallShape(
        "ptrcallWithTwoVector3RIDArgsRetPackedInt64List",
        "List<Long>",
        "emptyList()",
    ),
    (("bool", "float", "float"), "void"): CallShape("ptrcallWithBoolTwoDoubleArgs", "Unit"),
    (("bool", "bool"), "TypedVector2iArray"): CallShape(
        "ptrcallWithTwoBoolArgsRetVector2iList",
        "List<Vector2i>",
        "emptyList()",
    ),
    (("RID", "int64"), "TypedVector2iArray"): CallShape(
        "ptrcallWithRIDAndLongArgRetVector2iList",
        "List<Vector2i>",
        "emptyList()",
    ),
    (("RID",), "Dictionary"): CallShape("ptrcallWithRIDArgRetDictionary", "Map<String, Any?>", "emptyMap()"),
    (("RID",), "Array"): CallShape("ptrcallWithRIDArgRetArray", "List<Any?>", "emptyList()"),
    (("RID", "Dictionary"), "void"): CallShape("ptrcallWithRIDAndDictionaryArg", "Unit"),
    (("RID", "String", "TypedRIDArray", "int64", "Dictionary", "String", "Variant"), "bool"): CallShape(
        "ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool",
        "Boolean",
        "false",
    ),
    (("RID", "int64", "TypedRIDArray", "int64", "Dictionary"), "void"): CallShape(
        "ptrcallWithRIDLongRIDListLongDictionaryArgs",
        "Unit",
    ),
    (("TypedDictionaryArray", "int32"), "RID"): CallShape(
        "ptrcallWithDictionaryListIntArgsRetRID",
        "RID",
        "RID.EMPTY",
    ),
    (("RID", "Array"), "void"): CallShape("ptrcallWithRIDAndArrayArg", "Unit"),
    (("RID", "enum", "Array", "Array", "Dictionary", "bitfield"), "void"): CallShape(
        "ptrcallWithRIDLongTwoArrayDictionaryLongArgs",
        "Unit",
    ),
    (("RID",), "TypedDictionaryArray"): CallShape(
        "ptrcallWithRIDArgRetDictionaryList",
        "List<Map<String, Any?>>",
        "emptyList()",
    ),
    (("String", "String", "PackedByteArray"), "int64"): CallShape(
        "ptrcallWithTwoStringAndByteArrayArgsRetLong",
        "Long",
        "0L",
    ),
    (("String", "Dictionary", "String", "Variant"), "enum"): CallShape(
        "ptrcallWithStringDictionaryStringVariantArgsRetLong",
        "Long",
        "0L",
    ),
    (("Basis",), "void"): CallShape("ptrcallWithBasisArg", "Unit"),
    (("Basis",), "int32"): CallShape("ptrcallWithBasisArgRetInt", "Int", "0"),
    # Phase 1.1 — new scalar-ish combinations (all arg/return kinds individually marshallable)
    (("StringName", "enum"), "void"): CallShape("ptrcallWithStringNameAndLongArg", "Unit"),
    (("String", "bitfield", "int64"), "enum"): CallShape("ptrcallWithStringTwoLongArgsRetLong", "Long", "0L"),
    (("uint32",), "Vector2"): CallShape("ptrcallWithUInt32ArgRetVector2", "Vector2", "Vector2.ZERO"),
    (("uint32", "Vector2"), "void"): CallShape("ptrcallWithUInt32AndVector2Args", "Unit"),
    (("Vector3i",), "Vector3i"): CallShape("ptrcallWithVector3iArgRetVector3i", "Vector3i", "Vector3i.ZERO"),
    (("String", "bool", "bool", "float"), "enum"): CallShape("ptrcallWithStringTwoBoolAndDoubleArgRetLong", "Long", "0L"),
    (("bool", "bool", "float"), "PackedByteArray"): CallShape("ptrcallWithTwoBoolAndDoubleArgRetByteArray", "ByteArray", "ByteArray(0)"),
    (("RID", "int32", "int32"), "int32"): CallShape("ptrcallWithRIDAndTwoIntArgsRetInt", "Int", "0"),
    (("RID", "int32", "bool", "float", "Vector2"), "void"): CallShape("ptrcallWithRIDIntBoolDoubleVector2Args", "Unit"),
    (("RID", "uint32"), "RID"): CallShape("ptrcallWithRIDAndUInt32ArgRetRID", "RID", "RID.EMPTY"),
    (("RID", "uint32"), "int64"): CallShape("ptrcallWithRIDAndUInt32ArgRetLong", "Long", "0L"),
    (("RID", "int32", "int32", "int32"), "void"): CallShape("ptrcallWithRIDAndThreeIntArgs", "Unit"),
    (("RID", "bool", "bool", "bool", "Color"), "void"): CallShape("ptrcallWithRIDThreeBoolAndColorArgs", "Unit"),
    (("Object", "float", "int32", "StringName"), "void"): CallShape("ptrcallWithObjectDoubleIntStringNameArgs", "Unit"),
    (("Object", "Vector2", "int32", "StringName"), "void"): CallShape("ptrcallWithObjectVector2IntStringNameArgs", "Unit"),
    (("int32", "int32", "enum", "Color", "bool"), "void"): CallShape("ptrcallWithTwoIntLongColorBoolArgs", "Unit"),
    (("int32", "int32", "enum", "Color", "bool"), "RID"): CallShape("ptrcallWithTwoIntLongColorBoolArgsRetRID", "RID", "RID.EMPTY"),
    (("int64", "uint32", "RID", "uint32", "uint32", "uint32"), "void"): CallShape("ptrcallWithLongUInt32RIDThreeUInt32Args", "Unit"),
    (("uint32", "bitfield"), "RID"): CallShape("ptrcallWithUInt32AndLongArgRetRID", "RID", "RID.EMPTY"),
}


MUTATING_PREFIXES = (
    "add_",
    "change_",
    "clear_",
    "close",
    "copy",
    "create_",
    "flush",
    "make_",
    "open",
    "register_",
    "remove",
    "rename",
    "request_",
    "resize",
    "save",
    "seek",
    "set_",
    "store_",
    "unregister_",
)

MUTATING_PARTS = (
    "_clear",
    "_create_",
    "_delete",
    "_hide",
    "_move_",
    "_remove_",
    "_request_",
    "_set",
    "_show",
    "_start_",
    "_stop",
    "_write",
)

MUTATING_NAMES = {
    "beep",
    "force_process_and_drop_events",
    "process_events",
    "tts_pause",
    "tts_resume",
}


def camel_name(name: str) -> str:
    parts = name.split("_")
    first = parts[0].lower() if parts[0].isupper() else parts[0]
    return first + "".join("DateTime" if part == "datetime" else part.capitalize() for part in parts[1:])


def const_name(method: ApiMethod) -> str:
    return f"{method.name.upper()}_HASH"


def kotlin_type(type_name: str, object_types: set[str]) -> str:
    classified = logical_type(type_name, object_types=object_types)
    if classified == "int32":
        return "Int"
    if classified == "uint32":
        return "Long"
    if classified in {"int64", "enum", "bitfield"}:
        return "Long"
    if classified == "float":
        return "Double"
    if classified == "bool":
        return "Boolean"
    if classified == "Variant":
        return "Any?"
    if classified == "Plane":
        return "Plane"
    if classified == "Projection":
        return "Projection"
    if classified == "PackedStringArray":
        return "List<String>"
    if classified == "PackedByteArray":
        return "ByteArray"
    if classified == "PackedInt32Array":
        return "List<Int>"
    if classified == "PackedInt64Array":
        return "List<Long>"
    if classified == "PackedFloat32Array":
        return "List<Float>"
    if classified == "PackedFloat64Array":
        return "List<Double>"
    if classified == "PackedVector2Array":
        return "List<Vector2>"
    if classified == "PackedVector3Array":
        return "List<Vector3>"
    if classified == "PackedColorArray":
        return "List<Color>"
    if classified == "PackedVector4Array":
        return "List<Vector4>"
    if classified == "TypedRIDArray":
        return "List<RID>"
    if classified == "TypedStringArray":
        return "List<String>"
    if classified == "TypedIntArray":
        return "List<Long>"
    if classified == "TypedNodePathArray":
        return "List<NodePath>"
    if classified == "TypedPackedVector2Array":
        return "List<List<Vector2>>"
    if classified == "TypedNodeArray":
        return "List<Node>"
    if classified == "TypedNode2DArray":
        return "List<Node2D>"
    if classified == "TypedNode3DArray":
        return "List<Node3D>"
    if classified == "TypedMaterialArray":
        return "List<Material>"
    if classified == "TypedArea2DArray":
        return "List<Area2D>"
    if classified == "TypedArea3DArray":
        return "List<Area3D>"
    if classified == "TypedBaseButtonArray":
        return "List<BaseButton>"
    if classified == "TypedPhysicsBody3DArray":
        return "List<PhysicsBody3D>"
    if classified == "TypedVector2iArray":
        return "List<Vector2i>"
    if classified == "TypedVector3iArray":
        return "List<Vector3i>"
    if classified == "TypedStringNameArray":
        return "List<String>"
    if classified == "TypedDictionaryArray":
        return "List<Map<String, Any?>>"
    if classified == "Object":
        return "MemorySegment"
    return classified


def kotlin_type_for_arg(type_name: str, meta: str) -> str:
    classified = logical_type(type_name, meta, CURRENT_OBJECT_TYPES)
    if classified == "int32":
        return "Int"
    if classified == "uint32":
        return "Long"
    if classified in {"int64", "enum", "bitfield"}:
        return "Long"
    if classified == "float":
        return "Double"
    if classified == "bool":
        return "Boolean"
    if classified == "Variant":
        return "Any?"
    if classified == "Plane":
        return "Plane"
    if classified == "Projection":
        return "Projection"
    if classified == "PackedStringArray":
        return "List<String>"
    if classified == "PackedByteArray":
        return "ByteArray"
    if classified == "PackedInt32Array":
        return "List<Int>"
    if classified == "PackedInt64Array":
        return "List<Long>"
    if classified == "PackedFloat32Array":
        return "List<Float>"
    if classified == "PackedFloat64Array":
        return "List<Double>"
    if classified == "PackedVector2Array":
        return "List<Vector2>"
    if classified == "PackedVector3Array":
        return "List<Vector3>"
    if classified == "PackedColorArray":
        return "List<Color>"
    if classified == "PackedVector4Array":
        return "List<Vector4>"
    if classified == "TypedRIDArray":
        return "List<RID>"
    if classified == "TypedStringArray":
        return "List<String>"
    if classified == "TypedIntArray":
        return "List<Long>"
    if classified == "TypedNodePathArray":
        return "List<NodePath>"
    if classified == "TypedPackedVector2Array":
        return "List<List<Vector2>>"
    if classified == "TypedNodeArray":
        return "List<Node>"
    if classified == "TypedNode2DArray":
        return "List<Node2D>"
    if classified == "TypedNode3DArray":
        return "List<Node3D>"
    if classified == "TypedMaterialArray":
        return "List<Material>"
    if classified == "TypedArea2DArray":
        return "List<Area2D>"
    if classified == "TypedArea3DArray":
        return "List<Area3D>"
    if classified == "TypedBaseButtonArray":
        return "List<BaseButton>"
    if classified == "TypedPhysicsBody3DArray":
        return "List<PhysicsBody3D>"
    if classified == "TypedVector2iArray":
        return "List<Vector2i>"
    if classified == "TypedVector3iArray":
        return "List<Vector3i>"
    if classified == "TypedStringNameArray":
        return "List<String>"
    if classified == "TypedDictionaryArray":
        return "List<Map<String, Any?>>"
    if classified == "Object":
        return "MemorySegment"
    return classified


def arg_names(method: ApiMethod) -> list[str]:
    names: list[str] = []
    for index, name in enumerate(method.argument_names):
        candidate = camel_name(name)
        if candidate in {"class", "object", "var", "val"}:
            candidate = f"{candidate}Value"
        names.append(candidate or f"arg{index + 1}")
    return names


CURRENT_OBJECT_TYPES: set[str] = set()


def render_stub(class_name: str, method: ApiMethod, call_shape: CallShape, *, singleton: bool) -> str:
    function_name = camel_name(method.name)
    bind_name = f"{function_name}Bind"
    receiver = "MemorySegment.NULL"
    leading_params: list[str] = []
    if singleton:
        receiver = "singleton"
    elif not method.is_static:
        receiver = "instance"
        leading_params.append("instance: MemorySegment")
    names = arg_names(method)
    params = ", ".join([
        *leading_params,
        *(
            f"{name}: {kotlin_type_for_arg(arg_type, meta)}"
            for name, arg_type, meta in zip(names, method.argument_types, method.argument_metas, strict=True)
        ),
    ])
    args = ", ".join([bind_name, receiver, *names])
    return_type_text = "" if call_shape.kotlin_return == "Unit" else f": {call_shape.kotlin_return}"
    expression = f"ObjectCalls.{call_shape.function}({args})"
    lines = [
        f"private const val {const_name(method)} = {method.hash}L",
        f"private val {bind_name} by lazy {{",
        f'    ObjectCalls.getMethodBind("{class_name}", "{method.name}", {const_name(method)})',
        "}",
        "",
        "@JvmStatic",
        f"fun {function_name}({params}){return_type_text} =",
        f"    {expression}",
    ]
    return "\n".join(lines)


def return_type(method: ApiMethod) -> str:
    return method.logical_return_kind(CURRENT_OBJECT_TYPES)


def candidate_for(method: ApiMethod) -> CallShape | None:
    return CALL_SHAPES.get((method.logical_arg_kinds(CURRENT_OBJECT_TYPES), return_type(method)))


def is_read_only_candidate(method: ApiMethod) -> bool:
    name = method.name
    if not method.is_const or return_type(method) == "void":
        return False
    if name in MUTATING_NAMES:
        return False
    if name.startswith(MUTATING_PREFIXES):
        return False
    return not any(part in name for part in MUTATING_PARTS)



def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--api", type=Path, default=Path("extension_api.json"))
    parser.add_argument("--api-dir", type=Path, default=Path("src/main/kotlin/net/multigesture/kanama/api"))
    parser.add_argument("--class", dest="classes", action="append", help="Limit output to one class; repeatable.")
    parser.add_argument("--limit", type=int, default=30)
    parser.add_argument("--include-unsupported", action="store_true")
    parser.add_argument("--include-object-returns", action="store_true")
    parser.add_argument("--read-only", action="store_true", help="Skip obvious mutating methods.")
    parser.add_argument("--singleton", action="store_true", help="Emit singleton receiver instead of MemorySegment.NULL.")
    args = parser.parse_args()

    global CURRENT_OBJECT_TYPES
    api_classes = load_api_classes(args.api)
    CURRENT_OBJECT_TYPES = object_type_names(api_classes)
    classes = {name: cls.methods for name, cls in api_classes.items()}
    wrapped = scan_wrappers(args.api_dir)
    target_classes = args.classes if args.classes else sorted(wrapped)
    emitted = 0

    for class_name in target_classes:
        if class_name not in classes:
            print(f"# {class_name}\n\nNot found in extension_api.json.\n")
            continue
        wrapped_methods = wrapped.get(class_name, set())
        missing_names = [name for name in classes[class_name] if name not in wrapped_methods]
        if not missing_names:
            continue
        print(f"# {class_name}\n")
        for method_name in sorted(missing_names):
            for method in classes[class_name][method_name]:
                if args.read_only and not is_read_only_candidate(method):
                    continue
                if not args.include_object_returns and return_type(method) == "Object":
                    continue
                call_shape = candidate_for(method)
                if call_shape is None:
                    if args.include_unsupported:
                        print(f"<!-- unsupported: {method.signature} hash={method.hash} -->")
                    continue
                print("```kotlin")
                print(render_stub(class_name, method, call_shape, singleton=args.singleton))
                print("```\n")
                emitted += 1
                if emitted >= args.limit:
                    return 0

    if emitted == 0:
        print("No marshalable missing wrapper candidates found.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
