package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorProperty (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorProperty waits on: ptrcallWithStringNameVariantStringNameBoolArgs
// Index: docs/contributing/ios-shape-gap.md

/**
 * If one or several properties have changed, this must be called. `field` is used in case your
 * editor can modify fields separately (as an example, Vector3.x). The `changing` argument avoids
 * the editor requesting this property to be refreshed (leave as `false` if unsure).
 *
 * Generated from Godot docs: EditorProperty.emit_changed
 */
fun EditorProperty.emitChanged(property: String, value: Any?, field: String = "", changing: Boolean = false) {
    ObjectCalls.ptrcallWithStringNameVariantStringNameBoolArgs(emitChangedBind, handle, property, value, field, changing)
}

private const val EMIT_CHANGED_HASH = 1822500399L
private val emitChangedBind by lazy {
    ObjectCalls.getMethodBind("EditorProperty", "emit_changed", EMIT_CHANGED_HASH)
}
