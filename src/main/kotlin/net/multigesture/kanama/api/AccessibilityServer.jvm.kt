package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for AccessibilityServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AccessibilityServer waits on: ptrcallWithRIDAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the metadata of the accessibility element `id` to `meta`.
 *
 * Generated from Godot docs: AccessibilityServer.element_set_meta
 */
fun AccessibilityServer.elementSetMeta(id: RID, meta: Any?) {
    ObjectCalls.ptrcallWithRIDAndVariantArg(elementSetMetaBind, accessibilityServerSingleton, id, meta)
}

private val accessibilityServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("AccessibilityServer")
}

private const val ELEMENT_SET_META_HASH = 3175752987L
private val elementSetMetaBind by lazy {
    ObjectCalls.getMethodBind("AccessibilityServer", "element_set_meta", ELEMENT_SET_META_HASH)
}
