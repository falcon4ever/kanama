package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ClassDB (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ClassDB waits on: ptrcallWithObjectStringNameAndVariantArgRetLong,
//   ptrcallWithStringNameAndBoolArgRetPackedStringList, ptrcallWithStringNameArgRetPackedStringList,
//   ptrcallWithTwoStringNameAndBoolArgsRetPackedStringList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the names of all engine classes that directly or indirectly inherit from `class`.
 *
 * Generated from Godot docs: ClassDB.get_inheriters_from_class
 */
fun ClassDB.getInheritersFromClass(classValue: String): List<String> {
    return ObjectCalls.ptrcallWithStringNameArgRetPackedStringList(getInheritersFromClassBind, classDBSingleton, classValue)
}

/**
 * Sets `property` value of `object` to `value`.
 *
 * Generated from Godot docs: ClassDB.class_set_property
 */
fun ClassDB.classSetProperty(objectValue: GodotObject, property: String, value: Any?): Long {
    return ObjectCalls.ptrcallWithObjectStringNameAndVariantArgRetLong(classSetPropertyBind, classDBSingleton, objectValue.handle, property, value)
}

/**
 * Returns an array with the names all the integer constants of `class` or its ancestry.
 *
 * Generated from Godot docs: ClassDB.class_get_integer_constant_list
 */
fun ClassDB.classGetIntegerConstantList(classValue: String, noInheritance: Boolean = false): List<String> {
    return ObjectCalls.ptrcallWithStringNameAndBoolArgRetPackedStringList(classGetIntegerConstantListBind, classDBSingleton, classValue, noInheritance)
}

/**
 * Returns an array with all the enums of `class` or its ancestry.
 *
 * Generated from Godot docs: ClassDB.class_get_enum_list
 */
fun ClassDB.classGetEnumList(classValue: String, noInheritance: Boolean = false): List<String> {
    return ObjectCalls.ptrcallWithStringNameAndBoolArgRetPackedStringList(classGetEnumListBind, classDBSingleton, classValue, noInheritance)
}

/**
 * Returns an array with all the keys in `enum` of `class` or its ancestry.
 *
 * Generated from Godot docs: ClassDB.class_get_enum_constants
 */
fun ClassDB.classGetEnumConstants(classValue: String, enum: String, noInheritance: Boolean = false): List<String> {
    return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetPackedStringList(classGetEnumConstantsBind, classDBSingleton, classValue, enum, noInheritance)
}

private val classDBSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("ClassDB")
}

private const val GET_INHERITERS_FROM_CLASS_HASH = 1761182771L
private val getInheritersFromClassBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "get_inheriters_from_class", GET_INHERITERS_FROM_CLASS_HASH)
}

private const val CLASS_SET_PROPERTY_HASH = 1690314931L
private val classSetPropertyBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_set_property", CLASS_SET_PROPERTY_HASH)
}

private const val CLASS_GET_INTEGER_CONSTANT_LIST_HASH = 3031669221L
private val classGetIntegerConstantListBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_get_integer_constant_list", CLASS_GET_INTEGER_CONSTANT_LIST_HASH)
}

private const val CLASS_GET_ENUM_LIST_HASH = 3031669221L
private val classGetEnumListBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_get_enum_list", CLASS_GET_ENUM_LIST_HASH)
}

private const val CLASS_GET_ENUM_CONSTANTS_HASH = 661528303L
private val classGetEnumConstantsBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_get_enum_constants", CLASS_GET_ENUM_CONSTANTS_HASH)
}
