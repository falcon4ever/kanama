package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ChainIK3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ChainIK3D waits on: ptrcallWithIntArgRetString, ptrcallWithTwoIntArgsRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the root bone name of the bone chain.
 *
 * Generated from Godot docs: ChainIK3D.get_root_bone_name
 */
fun ChainIK3D.getRootBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
}

/**
 * Returns the end bone name of the bone chain.
 *
 * Generated from Godot docs: ChainIK3D.get_end_bone_name
 */
fun ChainIK3D.getEndBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
}

/**
 * Returns the bone name at `joint` in the bone chain's joint list.
 *
 * Generated from Godot docs: ChainIK3D.get_joint_bone_name
 */
fun ChainIK3D.getJointBoneName(index: Int, joint: Int): String {
    return ObjectCalls.ptrcallWithTwoIntArgsRetString(getJointBoneNameBind, handle, index, joint)
}

private const val GET_ROOT_BONE_NAME_HASH = 844755477L
private val getRootBoneNameBind by lazy {
    ObjectCalls.getMethodBind("ChainIK3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
}

private const val GET_END_BONE_NAME_HASH = 844755477L
private val getEndBoneNameBind by lazy {
    ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
}

private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
private val getJointBoneNameBind by lazy {
    ObjectCalls.getMethodBind("ChainIK3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)
}
