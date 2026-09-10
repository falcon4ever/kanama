package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for TriangleMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TriangleMesh waits on: ptrcallWithPackedVector3ListArgRetBool,
//   ptrcallWithTwoVector3ArgsRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates the BVH tree from an array of faces. Each 3 vertices of the input `faces` array
 * represent one triangle (face). Returns `true` if the tree is successfully built, `false`
 * otherwise.
 *
 * Generated from Godot docs: TriangleMesh.create_from_faces
 */
fun TriangleMesh.createFromFaces(faces: List<Vector3>): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithPackedVector3ListArgRetBool(createFromFacesBind, handle, faces)
}

/**
 * Tests for intersection with a segment going from `begin` to `end`. If an intersection with a
 * triangle happens returns a `Dictionary` with the following fields: `position`: The position on
 * the intersected triangle. `normal`: The normal of the intersected triangle. `face_index`: The
 * index of the intersected triangle. Returns an empty `Dictionary` if no intersection happens. See
 * also `intersect_ray`, which is similar but uses an infinite-length ray.
 *
 * Generated from Godot docs: TriangleMesh.intersect_segment
 */
fun TriangleMesh.intersectSegment(begin: Vector3, end: Vector3): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoVector3ArgsRetDictionary(intersectSegmentBind, handle, begin, end)
}

/**
 * Tests for intersection with a ray starting at `begin` and facing `dir` and extending toward
 * infinity. If an intersection with a triangle happens, returns a `Dictionary` with the following
 * fields: `position`: The position on the intersected triangle. `normal`: The normal of the
 * intersected triangle. `face_index`: The index of the intersected triangle. Returns an empty
 * `Dictionary` if no intersection happens. See also `intersect_segment`, which is similar but uses
 * a finite-length segment.
 *
 * Generated from Godot docs: TriangleMesh.intersect_ray
 */
fun TriangleMesh.intersectRay(begin: Vector3, dir: Vector3): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoVector3ArgsRetDictionary(intersectRayBind, handle, begin, dir)
}

private const val CREATE_FROM_FACES_HASH = 2637816732L
private val createFromFacesBind by lazy {
    ObjectCalls.getMethodBind("TriangleMesh", "create_from_faces", CREATE_FROM_FACES_HASH)
}

private const val INTERSECT_SEGMENT_HASH = 3648293151L
private val intersectSegmentBind by lazy {
    ObjectCalls.getMethodBind("TriangleMesh", "intersect_segment", INTERSECT_SEGMENT_HASH)
}

private const val INTERSECT_RAY_HASH = 3648293151L
private val intersectRayBind by lazy {
    ObjectCalls.getMethodBind("TriangleMesh", "intersect_ray", INTERSECT_RAY_HASH)
}
