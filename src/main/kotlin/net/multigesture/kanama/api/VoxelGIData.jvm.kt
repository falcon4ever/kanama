package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for VoxelGIData (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP VoxelGIData waits on: ptrcallWithTransform3DAABBVector3ThreeByteArrayPackedInt32ListArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Initializes this `VoxelGIData` with the specified data. `octree_cells` must be a multiple of 32.
 * `octree_cells` must be double the size of `data_cells`. The allocated data can be retrieved
 * later using the various getter methods.
 *
 * Generated from Godot docs: VoxelGIData.allocate
 */
fun VoxelGIData.allocate(toCellXform: Transform3D, aabb: AABB, octreeSize: Vector3, octreeCells: ByteArray, dataCells: ByteArray, distanceField: ByteArray, levelCounts: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithTransform3DAABBVector3ThreeByteArrayPackedInt32ListArgs(allocateBind, handle, toCellXform, aabb, octreeSize, octreeCells, dataCells, distanceField, levelCounts)
}

private const val ALLOCATE_HASH = 4041601946L
private val allocateBind by lazy {
    ObjectCalls.getMethodBind("VoxelGIData", "allocate", ALLOCATE_HASH)
}
