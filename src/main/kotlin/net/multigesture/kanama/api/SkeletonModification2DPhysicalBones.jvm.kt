package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for SkeletonModification2DPhysicalBones (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SkeletonModification2DPhysicalBones waits on: ptrcallWithIntArgRetNodePath,
//   ptrcallWithStringNameListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the `PhysicalBone2D` node at `joint_idx`.
 *
 * Generated from Godot docs: SkeletonModification2DPhysicalBones.get_physical_bone_node
 */
fun SkeletonModification2DPhysicalBones.getPhysicalBoneNode(jointIdx: Int): NodePath {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getPhysicalBoneNodeBind, handle, jointIdx)
}

/**
 * Tell the `PhysicalBone2D` nodes to start simulating and interacting with the physics world.
 * Optionally, an array of bone names can be passed to this function, and that will cause only
 * `PhysicalBone2D` nodes with those names to start simulating.
 *
 * Generated from Godot docs: SkeletonModification2DPhysicalBones.start_simulation
 */
fun SkeletonModification2DPhysicalBones.startSimulation(bones: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameListArg(startSimulationBind, handle, bones)
}

/**
 * Tell the `PhysicalBone2D` nodes to stop simulating and interacting with the physics world.
 * Optionally, an array of bone names can be passed to this function, and that will cause only
 * `PhysicalBone2D` nodes with those names to stop simulating.
 *
 * Generated from Godot docs: SkeletonModification2DPhysicalBones.stop_simulation
 */
fun SkeletonModification2DPhysicalBones.stopSimulation(bones: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameListArg(stopSimulationBind, handle, bones)
}

private const val GET_PHYSICAL_BONE_NODE_HASH = 408788394L
private val getPhysicalBoneNodeBind by lazy {
    ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "get_physical_bone_node", GET_PHYSICAL_BONE_NODE_HASH)
}

private const val START_SIMULATION_HASH = 2787316981L
private val startSimulationBind by lazy {
    ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "start_simulation", START_SIMULATION_HASH)
}

private const val STOP_SIMULATION_HASH = 2787316981L
private val stopSimulationBind by lazy {
    ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "stop_simulation", STOP_SIMULATION_HASH)
}
