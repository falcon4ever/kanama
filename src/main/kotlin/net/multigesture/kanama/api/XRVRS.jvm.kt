package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for XRVRS (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XRVRS waits on: ptrcallWithRect2iArg,
//   ptrcallWithVector2PackedVector2ListArgsRetRID
// Index: docs/contributing/ios-shape-gap.md

/**
 * The render region that the VRS texture will be scaled to when generated.
 *
 * Generated from Godot docs: XRVRS.set_vrs_render_region
 */
fun XRVRS.setVrsRenderRegion(renderRegion: Rect2i) {
    ObjectCalls.ptrcallWithRect2iArg(setVrsRenderRegionBind, handle, renderRegion)
}

/**
 * Generates the VRS texture based on a render `target_size` adjusted by our VRS tile size. For
 * each eyes focal point passed in `eye_foci` a layer is created. Focal point should be in NDC. The
 * result will be cached, requesting a VRS texture with unchanged parameters and settings will
 * return the cached RID.
 *
 * Generated from Godot docs: XRVRS.make_vrs_texture
 */
fun XRVRS.makeVrsTexture(targetSize: Vector2, eyeFoci: List<Vector2>): RID {
    return ObjectCalls.ptrcallWithVector2PackedVector2ListArgsRetRID(makeVrsTextureBind, handle, targetSize, eyeFoci)
}

private const val SET_VRS_RENDER_REGION_HASH = 1763793166L
private val setVrsRenderRegionBind by lazy {
    ObjectCalls.getMethodBind("XRVRS", "set_vrs_render_region", SET_VRS_RENDER_REGION_HASH)
}

private const val MAKE_VRS_TEXTURE_HASH = 3647044786L
private val makeVrsTextureBind by lazy {
    ObjectCalls.getMethodBind("XRVRS", "make_vrs_texture", MAKE_VRS_TEXTURE_HASH)
}
