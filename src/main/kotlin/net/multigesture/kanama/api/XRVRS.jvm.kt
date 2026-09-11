package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i

// GENERATED desktop/Android companion for XRVRS (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XRVRS waits on: ptrcallWithRect2iArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The render region that the VRS texture will be scaled to when generated.
 *
 * Generated from Godot docs: XRVRS.set_vrs_render_region
 */
fun XRVRS.setVrsRenderRegion(renderRegion: Rect2i) {
    ObjectCalls.ptrcallWithRect2iArg(setVrsRenderRegionBind, handle, renderRegion)
}

private const val SET_VRS_RENDER_REGION_HASH = 1763793166L
private val setVrsRenderRegionBind by lazy {
    ObjectCalls.getMethodBind("XRVRS", "set_vrs_render_region", SET_VRS_RENDER_REGION_HASH)
}
