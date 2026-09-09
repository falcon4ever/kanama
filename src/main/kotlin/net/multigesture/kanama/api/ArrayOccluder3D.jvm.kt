package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for ArrayOccluder3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ArrayOccluder3D waits on: ptrcallWithPackedInt32ListArg,
//   ptrcallWithPackedVector3ListAndPackedInt32ListArgs, ptrcallWithPackedVector3ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets `indices` and `vertices`, while updating the final occluder only once after both values are
 * set.
 *
 * Generated from Godot docs: ArrayOccluder3D.set_arrays
 */
fun ArrayOccluder3D.setArrays(vertices: List<Vector3>, indices: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListAndPackedInt32ListArgs(setArraysBind, handle, vertices, indices)
}

/**
 * The occluder's vertex positions in local 3D coordinates. Note: The occluder is always updated
 * after setting this value. If creating occluders procedurally, consider using `set_arrays`
 * instead to avoid updating the occluder twice when it's created.
 *
 * Generated from Godot docs: ArrayOccluder3D.set_vertices
 */
fun ArrayOccluder3D.setVertices(vertices: List<Vector3>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListArg(setVerticesBind, handle, vertices)
}

/**
 * The occluder's index position. Indices determine which points from the `vertices` array should
 * be drawn, and in which order. Note: The occluder is always updated after setting this value. If
 * creating occluders procedurally, consider using `set_arrays` instead to avoid updating the
 * occluder twice when it's created.
 *
 * Generated from Godot docs: ArrayOccluder3D.set_indices
 */
fun ArrayOccluder3D.setIndices(indices: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setIndicesBind, handle, indices)
}

private const val SET_ARRAYS_HASH = 3233972621L
private val setArraysBind by lazy {
    ObjectCalls.getMethodBind("ArrayOccluder3D", "set_arrays", SET_ARRAYS_HASH)
}

private const val SET_VERTICES_HASH = 334873810L
private val setVerticesBind by lazy {
    ObjectCalls.getMethodBind("ArrayOccluder3D", "set_vertices", SET_VERTICES_HASH)
}

private const val SET_INDICES_HASH = 3614634198L
private val setIndicesBind by lazy {
    ObjectCalls.getMethodBind("ArrayOccluder3D", "set_indices", SET_INDICES_HASH)
}
