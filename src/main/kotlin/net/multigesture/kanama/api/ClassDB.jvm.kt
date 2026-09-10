package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ClassDB (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ClassDB waits on: ptrcallWithObjectStringNameAndVariantArgRetLong
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets `property` value of `object` to `value`.
 *
 * Generated from Godot docs: ClassDB.class_set_property
 */
fun ClassDB.classSetProperty(objectValue: GodotObject, property: String, value: Any?): Long {
    return ObjectCalls.ptrcallWithObjectStringNameAndVariantArgRetLong(classSetPropertyBind, classDBSingleton, objectValue.handle, property, value)
}

private val classDBSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("ClassDB")
}

private const val CLASS_SET_PROPERTY_HASH = 1690314931L
private val classSetPropertyBind by lazy {
    ObjectCalls.getMethodBind("ClassDB", "class_set_property", CLASS_SET_PROPERTY_HASH)
}
