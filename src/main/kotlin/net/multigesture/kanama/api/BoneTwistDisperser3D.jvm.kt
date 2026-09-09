package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for BoneTwistDisperser3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP BoneTwistDisperser3D waits on: ptrcallWithIntArgRetString,
//   ptrcallWithTwoIntArgsRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the root bone name of the bone chain.
 *
 * Generated from Godot docs: BoneTwistDisperser3D.get_root_bone_name
 */
fun BoneTwistDisperser3D.getRootBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
}

/**
 * Returns the end bone name of the bone chain.
 *
 * Generated from Godot docs: BoneTwistDisperser3D.get_end_bone_name
 */
fun BoneTwistDisperser3D.getEndBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
}

/**
 * Returns the reference bone name to extract twist of the setting at `index`. This bone is either
 * the end of the chain or its parent, depending on `is_end_bone_extended`.
 *
 * Generated from Godot docs: BoneTwistDisperser3D.get_reference_bone_name
 */
fun BoneTwistDisperser3D.getReferenceBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getReferenceBoneNameBind, handle, index)
}

/**
 * Returns the bone name at `joint` in the bone chain's joint list.
 *
 * Generated from Godot docs: BoneTwistDisperser3D.get_joint_bone_name
 */
fun BoneTwistDisperser3D.getJointBoneName(index: Int, joint: Int): String {
    return ObjectCalls.ptrcallWithTwoIntArgsRetString(getJointBoneNameBind, handle, index, joint)
}

private const val GET_ROOT_BONE_NAME_HASH = 844755477L
private val getRootBoneNameBind by lazy {
    ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
}

private const val GET_END_BONE_NAME_HASH = 844755477L
private val getEndBoneNameBind by lazy {
    ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
}

private const val GET_REFERENCE_BONE_NAME_HASH = 844755477L
private val getReferenceBoneNameBind by lazy {
    ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_reference_bone_name", GET_REFERENCE_BONE_NAME_HASH)
}

private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
private val getJointBoneNameBind by lazy {
    ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)
}
