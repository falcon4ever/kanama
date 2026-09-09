package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for TwoBoneIK3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TwoBoneIK3D waits on: ptrcallWithIntArgRetNodePath, ptrcallWithIntArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the target node that the end bone is trying to reach.
 *
 * Generated from Godot docs: TwoBoneIK3D.get_target_node
 */
fun TwoBoneIK3D.getTargetNode(index: Int): NodePath {
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getTargetNodeBind, handle, index)
}

/**
 * Returns the pole target node that constructs a plane which the joints are all on and the pole is
 * trying to direct.
 *
 * Generated from Godot docs: TwoBoneIK3D.get_pole_node
 */
fun TwoBoneIK3D.getPoleNode(index: Int): NodePath {
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getPoleNodeBind, handle, index)
}

/**
 * Returns the root bone name.
 *
 * Generated from Godot docs: TwoBoneIK3D.get_root_bone_name
 */
fun TwoBoneIK3D.getRootBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
}

/**
 * Returns the middle bone name.
 *
 * Generated from Godot docs: TwoBoneIK3D.get_middle_bone_name
 */
fun TwoBoneIK3D.getMiddleBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getMiddleBoneNameBind, handle, index)
}

/**
 * Returns the end bone name.
 *
 * Generated from Godot docs: TwoBoneIK3D.get_end_bone_name
 */
fun TwoBoneIK3D.getEndBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
}

private const val GET_TARGET_NODE_HASH = 408788394L
private val getTargetNodeBind by lazy {
    ObjectCalls.getMethodBind("TwoBoneIK3D", "get_target_node", GET_TARGET_NODE_HASH)
}

private const val GET_POLE_NODE_HASH = 408788394L
private val getPoleNodeBind by lazy {
    ObjectCalls.getMethodBind("TwoBoneIK3D", "get_pole_node", GET_POLE_NODE_HASH)
}

private const val GET_ROOT_BONE_NAME_HASH = 844755477L
private val getRootBoneNameBind by lazy {
    ObjectCalls.getMethodBind("TwoBoneIK3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
}

private const val GET_MIDDLE_BONE_NAME_HASH = 844755477L
private val getMiddleBoneNameBind by lazy {
    ObjectCalls.getMethodBind("TwoBoneIK3D", "get_middle_bone_name", GET_MIDDLE_BONE_NAME_HASH)
}

private const val GET_END_BONE_NAME_HASH = 844755477L
private val getEndBoneNameBind by lazy {
    ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
}
