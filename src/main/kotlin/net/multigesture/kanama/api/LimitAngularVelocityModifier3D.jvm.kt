package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for LimitAngularVelocityModifier3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP LimitAngularVelocityModifier3D waits on: ptrcallWithIntArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the root bone name of the bone chain.
 *
 * Generated from Godot docs: LimitAngularVelocityModifier3D.get_root_bone_name
 */
fun LimitAngularVelocityModifier3D.getRootBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
}

/**
 * Returns the end bone name of the bone chain.
 *
 * Generated from Godot docs: LimitAngularVelocityModifier3D.get_end_bone_name
 */
fun LimitAngularVelocityModifier3D.getEndBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
}

private const val GET_ROOT_BONE_NAME_HASH = 844755477L
private val getRootBoneNameBind by lazy {
    ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
}

private const val GET_END_BONE_NAME_HASH = 844755477L
private val getEndBoneNameBind by lazy {
    ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
}
