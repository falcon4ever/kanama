package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Performance (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Performance waits on: ptrcallWithStringNameCallableArrayLongArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Adds a custom monitor with the name `id`. You can specify the category of the monitor using
 * slash delimiters in `id` (for example: `"Game/NumberOfNPCs"`). If there is more than one slash
 * delimiter, then the default category is used. The default category is `"Custom"`. Prints an
 * error if given `id` is already present.
 *
 * Generated from Godot docs: Performance.add_custom_monitor
 */
fun Performance.addCustomMonitor(id: String, callable: GodotCallable, arguments: List<Any?> = emptyList(), type: Long = 0L) {
    ObjectCalls.ptrcallWithStringNameCallableArrayLongArgs(addCustomMonitorBind, performanceSingleton, id, callable.target.handle, callable.method, arguments, type)
}

private val performanceSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("Performance")
}

private const val ADD_CUSTOM_MONITOR_HASH = 3655788610L
private val addCustomMonitorBind by lazy {
    ObjectCalls.getMethodBind("Performance", "add_custom_monitor", ADD_CUSTOM_MONITOR_HASH)
}
