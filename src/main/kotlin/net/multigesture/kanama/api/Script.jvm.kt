package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Script (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Script waits on: ptrcallNoArgsRetDictionary, ptrcallNoArgsRetDictionaryList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the list of properties in this `Script`. Note: The dictionaries returned by this method
 * are formatted identically to those returned by `Object.get_property_list`.
 *
 * Generated from Godot docs: Script.get_script_property_list
 */
fun Script.getScriptPropertyList(): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(getScriptPropertyListBind, handle)
}

/**
 * Returns the list of methods in this `Script`. Note: The dictionaries returned by this method are
 * formatted identically to those returned by `Object.get_method_list`.
 *
 * Generated from Godot docs: Script.get_script_method_list
 */
fun Script.getScriptMethodList(): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(getScriptMethodListBind, handle)
}

/**
 * Returns the list of signals defined in this `Script`. Note: The dictionaries returned by this
 * method are formatted identically to those returned by `Object.get_signal_list`.
 *
 * Generated from Godot docs: Script.get_script_signal_list
 */
fun Script.getScriptSignalList(): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(getScriptSignalListBind, handle)
}

/**
 * Returns a dictionary containing constant names and their values.
 *
 * Generated from Godot docs: Script.get_script_constant_map
 */
fun Script.getScriptConstantMap(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getScriptConstantMapBind, handle)
}

private const val GET_SCRIPT_PROPERTY_LIST_HASH = 2915620761L
private val getScriptPropertyListBind by lazy {
    ObjectCalls.getMethodBind("Script", "get_script_property_list", GET_SCRIPT_PROPERTY_LIST_HASH)
}

private const val GET_SCRIPT_METHOD_LIST_HASH = 2915620761L
private val getScriptMethodListBind by lazy {
    ObjectCalls.getMethodBind("Script", "get_script_method_list", GET_SCRIPT_METHOD_LIST_HASH)
}

private const val GET_SCRIPT_SIGNAL_LIST_HASH = 2915620761L
private val getScriptSignalListBind by lazy {
    ObjectCalls.getMethodBind("Script", "get_script_signal_list", GET_SCRIPT_SIGNAL_LIST_HASH)
}

private const val GET_SCRIPT_CONSTANT_MAP_HASH = 2382534195L
private val getScriptConstantMapBind by lazy {
    ObjectCalls.getMethodBind("Script", "get_script_constant_map", GET_SCRIPT_CONSTANT_MAP_HASH)
}
