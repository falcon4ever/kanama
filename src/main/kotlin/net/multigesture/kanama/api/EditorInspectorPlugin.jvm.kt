package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorInspectorPlugin (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorInspectorPlugin waits on: ptrcallWithStringPackedStringListAndObjectArgs
// Index: docs/contributing/ios-shape-gap.md

/**
 * Adds an editor that allows modifying multiple properties. The `editor` control must extend
 * `EditorProperty`.
 *
 * Generated from Godot docs: EditorInspectorPlugin.add_property_editor_for_multiple_properties
 */
fun EditorInspectorPlugin.addPropertyEditorForMultipleProperties(label: String, properties: List<String>, editor: Control) {
    checkOpen()
    ObjectCalls.ptrcallWithStringPackedStringListAndObjectArgs(addPropertyEditorForMultiplePropertiesBind, handle, label, properties, editor.handle)
}

private const val ADD_PROPERTY_EDITOR_FOR_MULTIPLE_PROPERTIES_HASH = 788598683L
private val addPropertyEditorForMultiplePropertiesBind by lazy {
    ObjectCalls.getMethodBind("EditorInspectorPlugin", "add_property_editor_for_multiple_properties", ADD_PROPERTY_EDITOR_FOR_MULTIPLE_PROPERTIES_HASH)
}
