package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ClassDB (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ClassDB waits on: ptrcallWithObjectStringNameAndVariantArgRetLong,
//   ptrcallWithStringNameAndBoolArgRetDictionaryList, ptrcallWithTwoStringNameArgsRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the `signal` data of `class` or its ancestry. The returned value is a `Dictionary` with
 * the following keys: `args`, `default_args`, `flags`, `id`, `name`, `return: (class_name, hint,
 * hint_string, name, type, usage)`.
 *
 * Generated from Godot docs: ClassDB.class_get_signal
 */
fun ClassDB.classGetSignal(classValue: String, signal: String): Map<String, Any?> {
    return ObjectCalls.ptrcallWithTwoStringNameArgsRetDictionary(classGetSignalBind, classDBSingleton, classValue, signal)
}

/**
 * Returns an array with all the signals of `class` or its ancestry if `no_inheritance` is `false`.
 * Every element of the array is a `Dictionary` as described in `class_get_signal`.
 *
 * Generated from Godot docs: ClassDB.class_get_signal_list
 */
fun ClassDB.classGetSignalList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(classGetSignalListBind, classDBSingleton, classValue, noInheritance)
}

/**
 * Returns an array with all the properties of `class` or its ancestry if `no_inheritance` is
 * `false`.
 *
 * Generated from Godot docs: ClassDB.class_get_property_list
 */
fun ClassDB.classGetPropertyList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(classGetPropertyListBind, classDBSingleton, classValue, noInheritance)
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
 * Returns an array with all the methods of `class` or its ancestry if `no_inheritance` is `false`.
 * Every element of the array is a `Dictionary` with the following keys: `args`, `default_args`,
 * `flags`, `id`, `name`, `return: (class_name, hint, hint_string, name, type, usage)`. Note: In
 * exported release builds the debug info is not available, so the returned dictionaries will
 * contain only method names.
 *
 * Generated from Godot docs: ClassDB.class_get_method_list
 */
fun ClassDB.classGetMethodList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(classGetMethodListBind, classDBSingleton, classValue, noInheritance)
}

private val classDBSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("ClassDB")
}

private const val CLASS_GET_SIGNAL_HASH = 3061114238L
private val classGetSignalBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_get_signal", CLASS_GET_SIGNAL_HASH)
}

private const val CLASS_GET_SIGNAL_LIST_HASH = 3504980660L
private val classGetSignalListBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_get_signal_list", CLASS_GET_SIGNAL_LIST_HASH)
}

private const val CLASS_GET_PROPERTY_LIST_HASH = 3504980660L
private val classGetPropertyListBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_get_property_list", CLASS_GET_PROPERTY_LIST_HASH)
}

private const val CLASS_SET_PROPERTY_HASH = 1690314931L
private val classSetPropertyBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_set_property", CLASS_SET_PROPERTY_HASH)
}

private const val CLASS_GET_METHOD_LIST_HASH = 3504980660L
private val classGetMethodListBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_get_method_list", CLASS_GET_METHOD_LIST_HASH)
}
