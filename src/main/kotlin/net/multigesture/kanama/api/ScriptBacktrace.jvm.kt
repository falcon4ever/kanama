package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ScriptBacktrace (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ScriptBacktrace waits on: ptrcallWithIntArgRetVariantScalar,
//   ptrcallWithTwoIntArgsRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the value of the global variable at the specified index. Warning: With GDScript
 * backtraces, the returned `Variant` will be the variable's actual value, including any object
 * references. This means that storing the returned `Variant` will prevent any such object from
 * being deallocated, so it's generally recommended not to do so.
 *
 * Generated from Godot docs: ScriptBacktrace.get_global_variable_value
 */
fun ScriptBacktrace.getGlobalVariableValue(variableIndex: Int): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getGlobalVariableValueBind, handle, variableIndex)
}

/**
 * Returns the value of the local variable at the specified `variable_index` in the stack frame at
 * the specified `frame_index`. Warning: With GDScript backtraces, the returned `Variant` will be
 * the variable's actual value, including any object references. This means that storing the
 * returned `Variant` will prevent any such object from being deallocated, so it's generally
 * recommended not to do so.
 *
 * Generated from Godot docs: ScriptBacktrace.get_local_variable_value
 */
fun ScriptBacktrace.getLocalVariableValue(frameIndex: Int, variableIndex: Int): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getLocalVariableValueBind, handle, frameIndex, variableIndex)
}

/**
 * Returns the value of the member variable at the specified `variable_index` in the stack frame at
 * the specified `frame_index`. Warning: With GDScript backtraces, the returned `Variant` will be
 * the variable's actual value, including any object references. This means that storing the
 * returned `Variant` will prevent any such object from being deallocated, so it's generally
 * recommended not to do so.
 *
 * Generated from Godot docs: ScriptBacktrace.get_member_variable_value
 */
fun ScriptBacktrace.getMemberVariableValue(frameIndex: Int, variableIndex: Int): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getMemberVariableValueBind, handle, frameIndex, variableIndex)
}

private const val GET_GLOBAL_VARIABLE_VALUE_HASH = 4227898402L
private val getGlobalVariableValueBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_global_variable_value", GET_GLOBAL_VARIABLE_VALUE_HASH)
}

private const val GET_LOCAL_VARIABLE_VALUE_HASH = 678354945L
private val getLocalVariableValueBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_local_variable_value", GET_LOCAL_VARIABLE_VALUE_HASH)
}

private const val GET_MEMBER_VARIABLE_VALUE_HASH = 678354945L
private val getMemberVariableValueBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_member_variable_value", GET_MEMBER_VARIABLE_VALUE_HASH)
}
