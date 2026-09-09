package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for UndoRedo (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP UndoRedo waits on: ptrcallWithIntArgRetString,
//   ptrcallWithObjectStringNameAndVariantArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Register a `property` that would change its value to `value` when the action is committed.
 *
 * Generated from Godot docs: UndoRedo.add_do_property
 */
fun UndoRedo.addDoProperty(objectValue: GodotObject, property: String, value: Any?) {
    ObjectCalls.ptrcallWithObjectStringNameAndVariantArg(addDoPropertyBind, handle, objectValue.handle, property, value)
}

/**
 * Register a `property` that would change its value to `value` when the action is undone.
 *
 * Generated from Godot docs: UndoRedo.add_undo_property
 */
fun UndoRedo.addUndoProperty(objectValue: GodotObject, property: String, value: Any?) {
    ObjectCalls.ptrcallWithObjectStringNameAndVariantArg(addUndoPropertyBind, handle, objectValue.handle, property, value)
}

/**
 * Gets the action name from its index.
 *
 * Generated from Godot docs: UndoRedo.get_action_name
 */
fun UndoRedo.getActionName(id: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getActionNameBind, handle, id)
}

private const val ADD_DO_PROPERTY_HASH = 1017172818L
private val addDoPropertyBind by lazy {
    ObjectCalls.getMethodBind("UndoRedo", "add_do_property", ADD_DO_PROPERTY_HASH)
}

private const val ADD_UNDO_PROPERTY_HASH = 1017172818L
private val addUndoPropertyBind by lazy {
    ObjectCalls.getMethodBind("UndoRedo", "add_undo_property", ADD_UNDO_PROPERTY_HASH)
}

private const val GET_ACTION_NAME_HASH = 990163283L
private val getActionNameBind by lazy {
    ObjectCalls.getMethodBind("UndoRedo", "get_action_name", GET_ACTION_NAME_HASH)
}
