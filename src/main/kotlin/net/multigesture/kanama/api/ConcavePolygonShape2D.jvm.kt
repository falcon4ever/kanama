package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for ConcavePolygonShape2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ConcavePolygonShape2D waits on: ptrcallWithPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The array of points that make up the `ConcavePolygonShape2D`'s line segments. The array (of
 * length divisible by two) is naturally divided into pairs (one pair for each segment); each pair
 * consists of the starting point of a segment and the endpoint of a segment.
 *
 * Generated from Godot docs: ConcavePolygonShape2D.set_segments
 */
fun ConcavePolygonShape2D.setSegments(segments: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(setSegmentsBind, handle, segments)
}

private const val SET_SEGMENTS_HASH = 1509147220L
private val setSegmentsBind by lazy {
    ObjectCalls.getMethodBind("ConcavePolygonShape2D", "set_segments", SET_SEGMENTS_HASH)
}
