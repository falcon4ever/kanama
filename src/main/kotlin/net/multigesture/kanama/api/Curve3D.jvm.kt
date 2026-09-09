package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for Curve3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Curve3D waits on: ptrcallNoArgsRetPackedVector3List,
//   ptrcallWithIntAndDoubleArgRetPackedVector3List
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the cache of points as a `PackedVector3Array`.
 *
 * Generated from Godot docs: Curve3D.get_baked_points
 */
fun Curve3D.getBakedPoints(): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getBakedPointsBind, handle)
}

/**
 * Returns the cache of up vectors as a `PackedVector3Array`. If `up_vector_enabled` is `false`,
 * the cache will be empty.
 *
 * Generated from Godot docs: Curve3D.get_baked_up_vectors
 */
fun Curve3D.getBakedUpVectors(): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getBakedUpVectorsBind, handle)
}

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
 * Generated from Godot docs: Curve3D.tessellate
 */
fun Curve3D.tessellate(maxStages: Int = 5, toleranceDegrees: Double = 4.0): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndDoubleArgRetPackedVector3List(tessellateBind, handle, maxStages, toleranceDegrees)
}

/**
 * Returns a list of points along the curve, with almost uniform density. `max_stages` controls how
 * many subdivisions a curve segment may face before it is considered approximate enough. Each
 * subdivision splits the segment in half, so the default 5 stages may mean up to 32 subdivisions
 * per curve segment. Increase with care! `tolerance_length` controls the maximal distance between
 * two neighboring points, before the segment has to be subdivided.
 *
 * Generated from Godot docs: Curve3D.tessellate_even_length
 */
fun Curve3D.tessellateEvenLength(maxStages: Int = 5, toleranceLength: Double = 0.2): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndDoubleArgRetPackedVector3List(tessellateEvenLengthBind, handle, maxStages, toleranceLength)
}

private const val GET_BAKED_POINTS_HASH = 497664490L
private val getBakedPointsBind by lazy {
    ObjectCalls.getMethodBind("Curve3D", "get_baked_points", GET_BAKED_POINTS_HASH)
}

private const val GET_BAKED_UP_VECTORS_HASH = 497664490L
private val getBakedUpVectorsBind by lazy {
    ObjectCalls.getMethodBind("Curve3D", "get_baked_up_vectors", GET_BAKED_UP_VECTORS_HASH)
}

private const val TESSELLATE_HASH = 1519759391L
private val tessellateBind by lazy {
    ObjectCalls.getMethodBind("Curve3D", "tessellate", TESSELLATE_HASH)
}

private const val TESSELLATE_EVEN_LENGTH_HASH = 133237049L
private val tessellateEvenLengthBind by lazy {
    ObjectCalls.getMethodBind("Curve3D", "tessellate_even_length", TESSELLATE_EVEN_LENGTH_HASH)
}
