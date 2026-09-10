package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorSettings (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorSettings waits on: ptrcallWithStringAndObjectListArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Overrides the built-in editor action `name` with the input actions defined in `actions_list`.
 *
 * Generated from Godot docs: EditorSettings.set_builtin_action_override
 */
fun EditorSettings.setBuiltinActionOverride(name: String, actionsList: List<InputEvent>) {
    checkOpen()
    ObjectCalls.ptrcallWithStringAndObjectListArgs(setBuiltinActionOverrideBind, handle, name, actionsList)
}

private const val SET_BUILTIN_ACTION_OVERRIDE_HASH = 1209351045L
private val setBuiltinActionOverrideBind by lazy {
    ObjectCalls.getMethodBind("EditorSettings", "set_builtin_action_override", SET_BUILTIN_ACTION_OVERRIDE_HASH)
}
