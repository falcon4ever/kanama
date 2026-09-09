package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for OpenXRPlaneTracker (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRPlaneTracker waits on: ptrcallWithTransform3DPackedVector2ListPackedInt32ListArgs
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRPlaneTracker.setMeshData(origin: Transform3D, vertices: List<Vector2>, indices: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithTransform3DPackedVector2ListPackedInt32ListArgs(setMeshDataBind, handle, origin, vertices, indices)
}

private const val SET_MESH_DATA_HASH = 1877193149L
private val setMeshDataBind by lazy {
    ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_mesh_data", SET_MESH_DATA_HASH)
}
