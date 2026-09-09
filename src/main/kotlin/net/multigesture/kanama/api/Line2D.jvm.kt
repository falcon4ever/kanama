package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for Line2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Line2D waits on: ptrcallWithPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The points of the polyline, interpreted in local 2D coordinates. Segments are drawn between the
 * adjacent points in this array.
 *
 * Generated from Godot docs: Line2D.set_points
 */
fun Line2D.setPoints(points: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setPointsBind, handle, points)
}

private const val SET_POINTS_HASH = 1509147220L
private val setPointsBind by lazy {
    ObjectCalls.getMethodBind("Line2D", "set_points", SET_POINTS_HASH)
}
