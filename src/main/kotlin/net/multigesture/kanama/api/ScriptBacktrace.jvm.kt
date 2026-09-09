package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ScriptBacktrace (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ScriptBacktrace waits on: ptrcallWithIntArgRetString,
//   ptrcallWithIntArgRetVariantScalar, ptrcallWithTwoIntArgsRetString,
//   ptrcallWithTwoIntArgsRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the name of the function called at the stack frame at the specified index.
 *
 * Generated from Godot docs: ScriptBacktrace.get_frame_function
 */
fun ScriptBacktrace.getFrameFunction(index: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getFrameFunctionBind, handle, index)
}

/**
 * Returns the file name of the call site represented by the stack frame at the specified index.
 *
 * Generated from Godot docs: ScriptBacktrace.get_frame_file
 */
fun ScriptBacktrace.getFrameFile(index: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getFrameFileBind, handle, index)
}

/**
 * Returns the name of the global variable at the specified index.
 *
 * Generated from Godot docs: ScriptBacktrace.get_global_variable_name
 */
fun ScriptBacktrace.getGlobalVariableName(variableIndex: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getGlobalVariableNameBind, handle, variableIndex)
}

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
 * Returns the name of the local variable at the specified `variable_index` in the stack frame at
 * the specified `frame_index`.
 *
 * Generated from Godot docs: ScriptBacktrace.get_local_variable_name
 */
fun ScriptBacktrace.getLocalVariableName(frameIndex: Int, variableIndex: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetString(getLocalVariableNameBind, handle, frameIndex, variableIndex)
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
 * Returns the name of the member variable at the specified `variable_index` in the stack frame at
 * the specified `frame_index`.
 *
 * Generated from Godot docs: ScriptBacktrace.get_member_variable_name
 */
fun ScriptBacktrace.getMemberVariableName(frameIndex: Int, variableIndex: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetString(getMemberVariableNameBind, handle, frameIndex, variableIndex)
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

/**
 * Converts the backtrace to a `String`, where the entire string will be indented by `indent_all`
 * number of spaces, and the individual stack frames will be additionally indented by
 * `indent_frames` number of spaces. Note: Calling `Object.to_string` on a `ScriptBacktrace` will
 * produce the same output as calling `format` with all parameters left at their default values.
 *
 * Generated from Godot docs: ScriptBacktrace.format
 */
fun ScriptBacktrace.format(indentAll: Int = 0, indentFrames: Int = 4): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetString(formatBind, handle, indentAll, indentFrames)
}

private const val GET_FRAME_FUNCTION_HASH = 844755477L
private val getFrameFunctionBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_frame_function", GET_FRAME_FUNCTION_HASH)
}

private const val GET_FRAME_FILE_HASH = 844755477L
private val getFrameFileBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_frame_file", GET_FRAME_FILE_HASH)
}

private const val GET_GLOBAL_VARIABLE_NAME_HASH = 844755477L
private val getGlobalVariableNameBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_global_variable_name", GET_GLOBAL_VARIABLE_NAME_HASH)
}

private const val GET_GLOBAL_VARIABLE_VALUE_HASH = 4227898402L
private val getGlobalVariableValueBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_global_variable_value", GET_GLOBAL_VARIABLE_VALUE_HASH)
}

private const val GET_LOCAL_VARIABLE_NAME_HASH = 1391810591L
private val getLocalVariableNameBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_local_variable_name", GET_LOCAL_VARIABLE_NAME_HASH)
}

private const val GET_LOCAL_VARIABLE_VALUE_HASH = 678354945L
private val getLocalVariableValueBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_local_variable_value", GET_LOCAL_VARIABLE_VALUE_HASH)
}

private const val GET_MEMBER_VARIABLE_NAME_HASH = 1391810591L
private val getMemberVariableNameBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_member_variable_name", GET_MEMBER_VARIABLE_NAME_HASH)
}

private const val GET_MEMBER_VARIABLE_VALUE_HASH = 678354945L
private val getMemberVariableValueBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "get_member_variable_value", GET_MEMBER_VARIABLE_VALUE_HASH)
}

private const val FORMAT_HASH = 3464456933L
private val formatBind by lazy {
    ObjectCalls.getMethodBind("ScriptBacktrace", "format", FORMAT_HASH)
}
