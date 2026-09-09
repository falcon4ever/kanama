package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for SkeletonModification2DJiggle (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SkeletonModification2DJiggle waits on: ptrcallWithIntArgRetNodePath
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the `Bone2D` node assigned to the Jiggle joint at `joint_idx`.
 *
 * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_bone2d_node
 */
fun SkeletonModification2DJiggle.getJiggleJointBone2dNode(jointIdx: Int): NodePath {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getJiggleJointBone2dNodeBind, handle, jointIdx)
}

private const val GET_JIGGLE_JOINT_BONE2D_NODE_HASH = 408788394L
private val getJiggleJointBone2dNodeBind by lazy {
    ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_bone2d_node", GET_JIGGLE_JOINT_BONE2D_NODE_HASH)
}
