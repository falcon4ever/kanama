package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for PolygonPathFinder (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PolygonPathFinder waits on: ptrcallWithPackedVector2ListAndPackedInt32ListArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets up `PolygonPathFinder` with an array of points that define the vertices of the polygon, and
 * an array of indices that determine the edges of the polygon. The length of `connections` must be
 * even, returns an error if odd.
 *
 * Generated from Godot docs: PolygonPathFinder.setup
 */
fun PolygonPathFinder.setup(points: List<Vector2>, connections: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListAndPackedInt32ListArgs(setupBind, handle, points, connections)
}

private const val SETUP_HASH = 3251786936L
private val setupBind by lazy {
    ObjectCalls.getMethodBind("PolygonPathFinder", "setup", SETUP_HASH)
}
