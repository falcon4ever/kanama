package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for Curve2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Curve2D waits on: ptrcallWithIntAndDoubleArgRetPackedVector2List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a list of points along the curve, with a curvature controlled point density. That is,
 * the curvier parts will have more points than the straighter parts. This approximation makes
 * straight segments between each point, then subdivides those segments until the resulting shape
 * is similar enough. `max_stages` controls how many subdivisions a curve segment may face before
 * it is considered approximate enough. Each subdivision splits the segment in half, so the default
 * 5 stages may mean up to 32 subdivisions per curve segment. Increase with care!
 * `tolerance_degrees` controls how many degrees the midpoint of a segment may deviate from the
 * real curve, before the segment has to be subdivided.
 *
 * Generated from Godot docs: Curve2D.tessellate
 */
fun Curve2D.tessellate(maxStages: Int = 5, toleranceDegrees: Double = 4.0): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndDoubleArgRetPackedVector2List(tessellateBind, handle, maxStages, toleranceDegrees)
}

/**
 * Returns a list of points along the curve, with almost uniform density. `max_stages` controls how
 * many subdivisions a curve segment may face before it is considered approximate enough. Each
 * subdivision splits the segment in half, so the default 5 stages may mean up to 32 subdivisions
 * per curve segment. Increase with care! `tolerance_length` controls the maximal distance between
 * two neighboring points, before the segment has to be subdivided.
 *
 * Generated from Godot docs: Curve2D.tessellate_even_length
 */
fun Curve2D.tessellateEvenLength(maxStages: Int = 5, toleranceLength: Double = 20.0): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndDoubleArgRetPackedVector2List(tessellateEvenLengthBind, handle, maxStages, toleranceLength)
}

private const val TESSELLATE_HASH = 958145977L
private val tessellateBind by lazy {
    ObjectCalls.getMethodBind("Curve2D", "tessellate", TESSELLATE_HASH)
}

private const val TESSELLATE_EVEN_LENGTH_HASH = 2319761637L
private val tessellateEvenLengthBind by lazy {
    ObjectCalls.getMethodBind("Curve2D", "tessellate_even_length", TESSELLATE_EVEN_LENGTH_HASH)
}
