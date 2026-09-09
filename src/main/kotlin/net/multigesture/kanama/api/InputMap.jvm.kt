package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for InputMap (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP InputMap waits on: ptrcallWithStringNameArgRetString,
//   ptrcallWithStringNameArgRetTypedObjectList
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the human-readable description of the given action.
 *
 * Generated from Godot docs: InputMap.get_action_description
 */
fun InputMap.getActionDescription(action: String): String {
    return ObjectCalls.ptrcallWithStringNameArgRetString(getActionDescriptionBind, inputMapSingleton, action)
}

/**
 * Returns an array of `InputEvent`s associated with a given action. Note: When used in the editor
 * (e.g. a tool script or `EditorPlugin`), this method will return events for the editor action. If
 * you want to access your project's input binds from the editor, read the `input/\*` settings from
 * `ProjectSettings`.
 *
 * Generated from Godot docs: InputMap.action_get_events
 */
fun InputMap.actionGetEvents(action: String): List<InputEvent> {
    return ObjectCalls.ptrcallWithStringNameArgRetTypedObjectList(actionGetEventsBind, inputMapSingleton, action, InputEvent::fromHandle)
}

private val inputMapSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("InputMap")
}

private const val GET_ACTION_DESCRIPTION_HASH = 957595536L
private val getActionDescriptionBind by lazy {
    ObjectCalls.getMethodBind("InputMap", "get_action_description", GET_ACTION_DESCRIPTION_HASH)
}

private const val ACTION_GET_EVENTS_HASH = 689397652L
private val actionGetEventsBind by lazy {
    ObjectCalls.getMethodBind("InputMap", "action_get_events", ACTION_GET_EVENTS_HASH)
}
