package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Polygon2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Polygon2D waits on: ptrcallWithArrayArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The list of polygons, in case more than one is being represented. Every individual polygon is
 * stored as a `PackedInt32Array` where each `int` is an index to a point in `polygon`. If empty,
 * this property will be ignored, and the resulting single polygon will be composed of all points
 * in `polygon`, using the order they are stored in.
 *
 * Generated from Godot docs: Polygon2D.set_polygons
 */
fun Polygon2D.setPolygons(polygons: List<Any?>) {
    ObjectCalls.ptrcallWithArrayArg(setPolygonsBind, handle, polygons)
}

private const val SET_POLYGONS_HASH = 381264803L
private val setPolygonsBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "set_polygons", SET_POLYGONS_HASH)
}
