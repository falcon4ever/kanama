package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AnimationNodeBlendSpace2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AnimationNodeBlendSpace2D waits on: ptrcallWithIntArgRetStringName
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the name of the blend point at index `point`.
 *
 * Generated from Godot docs: AnimationNodeBlendSpace2D.get_blend_point_name
 */
fun AnimationNodeBlendSpace2D.getBlendPointName(point: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getBlendPointNameBind, handle, point)
}

private const val GET_BLEND_POINT_NAME_HASH = 659327637L
private val getBlendPointNameBind by lazy {
    ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_name", GET_BLEND_POINT_NAME_HASH)
}
