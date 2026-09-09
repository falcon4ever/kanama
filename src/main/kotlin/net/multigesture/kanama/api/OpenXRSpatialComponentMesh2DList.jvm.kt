package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for OpenXRSpatialComponentMesh2DList (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRSpatialComponentMesh2DList waits on: ptrcallWithRIDAndLongArgRetPackedInt32List,
//   ptrcallWithRIDAndLongArgRetPackedVector2List
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRSpatialComponentMesh2DList.getVertices(snapshot: RID, index: Long): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector2List(getVerticesBind, handle, snapshot, index)
}

fun OpenXRSpatialComponentMesh2DList.getIndices(snapshot: RID, index: Long): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(getIndicesBind, handle, snapshot, index)
}

private const val GET_VERTICES_HASH = 110850971L
private val getVerticesBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialComponentMesh2DList", "get_vertices", GET_VERTICES_HASH)
}

private const val GET_INDICES_HASH = 3393655756L
private val getIndicesBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialComponentMesh2DList", "get_indices", GET_INDICES_HASH)
}
