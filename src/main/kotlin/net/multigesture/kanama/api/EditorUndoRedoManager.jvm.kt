package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorUndoRedoManager (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorUndoRedoManager waits on: ptrcallWithObjectStringNameAndVariantArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Register a property value change for "do". If this is the first operation, the `object` will be
 * used to deduce target undo history.
 *
 * Generated from Godot docs: EditorUndoRedoManager.add_do_property
 */
fun EditorUndoRedoManager.addDoProperty(objectValue: GodotObject, property: String, value: Any?) {
    ObjectCalls.ptrcallWithObjectStringNameAndVariantArg(addDoPropertyBind, handle, objectValue.handle, property, value)
}

/**
 * Register a property value change for "undo". If this is the first operation, the `object` will
 * be used to deduce target undo history.
 *
 * Generated from Godot docs: EditorUndoRedoManager.add_undo_property
 */
fun EditorUndoRedoManager.addUndoProperty(objectValue: GodotObject, property: String, value: Any?) {
    ObjectCalls.ptrcallWithObjectStringNameAndVariantArg(addUndoPropertyBind, handle, objectValue.handle, property, value)
}

private const val ADD_DO_PROPERTY_HASH = 1017172818L
private val addDoPropertyBind by lazy {
    ObjectCalls.getMethodBind("EditorUndoRedoManager", "add_do_property", ADD_DO_PROPERTY_HASH)
}

private const val ADD_UNDO_PROPERTY_HASH = 1017172818L
private val addUndoPropertyBind by lazy {
    ObjectCalls.getMethodBind("EditorUndoRedoManager", "add_undo_property", ADD_UNDO_PROPERTY_HASH)
}
