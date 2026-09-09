package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EngineDebugger (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EngineDebugger waits on: ptrcallWithStringAndArrayArg,
//   ptrcallWithStringNameArrayArgs, ptrcallWithStringNameBoolArrayArgs
// Index: docs/contributing/ios-shape-gap.md

/**
 * Calls the `add` callable of the profiler with given `name` and `data`.
 *
 * Generated from Godot docs: EngineDebugger.profiler_add_frame_data
 */
fun EngineDebugger.profilerAddFrameData(name: String, data: List<Any?>) {
    ObjectCalls.ptrcallWithStringNameArrayArgs(profilerAddFrameDataBind, engineDebuggerSingleton, name, data)
}

/**
 * Calls the `toggle` callable of the profiler with given `name` and `arguments`. Enables/Disables
 * the same profiler depending on `enable` argument.
 *
 * Generated from Godot docs: EngineDebugger.profiler_enable
 */
fun EngineDebugger.profilerEnable(name: String, enable: Boolean, arguments: List<Any?> = emptyList()) {
    ObjectCalls.ptrcallWithStringNameBoolArrayArgs(profilerEnableBind, engineDebuggerSingleton, name, enable, arguments)
}

/**
 * Sends a message with given `message` and `data` array.
 *
 * Generated from Godot docs: EngineDebugger.send_message
 */
fun EngineDebugger.sendMessage(message: String, data: List<Any?>) {
    ObjectCalls.ptrcallWithStringAndArrayArg(sendMessageBind, engineDebuggerSingleton, message, data)
}

private val engineDebuggerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("EngineDebugger")
}

private const val PROFILER_ADD_FRAME_DATA_HASH = 1895267858L
private val profilerAddFrameDataBind by lazy {
    ObjectCalls.getMethodBind("EngineDebugger", "profiler_add_frame_data", PROFILER_ADD_FRAME_DATA_HASH)
}

private const val PROFILER_ENABLE_HASH = 3192561009L
private val profilerEnableBind by lazy {
    ObjectCalls.getMethodBind("EngineDebugger", "profiler_enable", PROFILER_ENABLE_HASH)
}

private const val SEND_MESSAGE_HASH = 1209351045L
private val sendMessageBind by lazy {
    ObjectCalls.getMethodBind("EngineDebugger", "send_message", SEND_MESSAGE_HASH)
}
