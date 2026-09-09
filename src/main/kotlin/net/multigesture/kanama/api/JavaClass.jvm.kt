package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for JavaClass (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP JavaClass waits on: ptrcallNoArgsRetDictionaryList
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the object's Java methods and their signatures as an `Array` of dictionaries, in the
 * same format as `Object.get_method_list`.
 *
 * Generated from Godot docs: JavaClass.get_java_method_list
 */
fun JavaClass.getJavaMethodList(): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(getJavaMethodListBind, handle)
}

private const val GET_JAVA_METHOD_LIST_HASH = 3995934104L
private val getJavaMethodListBind by lazy {
    ObjectCalls.getMethodBind("JavaClass", "get_java_method_list", GET_JAVA_METHOD_LIST_HASH)
}
