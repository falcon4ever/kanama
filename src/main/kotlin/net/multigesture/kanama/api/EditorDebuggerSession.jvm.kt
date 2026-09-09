package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorDebuggerSession (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorDebuggerSession waits on: ptrcallWithStringAndArrayArg,
//   ptrcallWithStringBoolArrayArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sends the given `message` to the attached remote instance, optionally passing additionally
 * `data`. See `EngineDebugger` for how to retrieve those messages.
 *
 * Generated from Godot docs: EditorDebuggerSession.send_message
 */
fun EditorDebuggerSession.sendMessage(message: String, data: List<Any?> = emptyList()) {
    checkOpen()
    ObjectCalls.ptrcallWithStringAndArrayArg(sendMessageBind, handle, message, data)
}

/**
 * Toggle the given `profiler` on the attached remote instance, optionally passing additionally
 * `data`. See `EngineProfiler` for more details.
 *
 * Generated from Godot docs: EditorDebuggerSession.toggle_profiler
 */
fun EditorDebuggerSession.toggleProfiler(profiler: String, enable: Boolean, data: List<Any?> = emptyList()) {
    checkOpen()
    ObjectCalls.ptrcallWithStringBoolArrayArgs(toggleProfilerBind, handle, profiler, enable, data)
}

private const val SEND_MESSAGE_HASH = 85656714L
private val sendMessageBind by lazy {
    ObjectCalls.getMethodBind("EditorDebuggerSession", "send_message", SEND_MESSAGE_HASH)
}

private const val TOGGLE_PROFILER_HASH = 1198443697L
private val toggleProfilerBind by lazy {
    ObjectCalls.getMethodBind("EditorDebuggerSession", "toggle_profiler", TOGGLE_PROFILER_HASH)
}
