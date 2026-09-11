package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

// GENERATED desktop/Android companion for GLTFSkin (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFSkin waits on: ptrcallWithDictionaryArg, ptrcallWithTransform3DListArg
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFSkin.setInverseBinds(inverseBinds: List<Transform3D>) {
    checkOpen()
    ObjectCalls.ptrcallWithTransform3DListArg(setInverseBindsBind, handle, inverseBinds)
}

fun GLTFSkin.setJointIToBoneI(jointIToBoneI: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setJointIToBoneIBind, handle, jointIToBoneI)
}

fun GLTFSkin.setJointIToName(jointIToName: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setJointIToNameBind, handle, jointIToName)
}

private const val SET_INVERSE_BINDS_HASH = 381264803L
private val setInverseBindsBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkin", "set_inverse_binds", SET_INVERSE_BINDS_HASH)
}

private const val SET_JOINT_I_TO_BONE_I_HASH = 4155329257L
private val setJointIToBoneIBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkin", "set_joint_i_to_bone_i", SET_JOINT_I_TO_BONE_I_HASH)
}

private const val SET_JOINT_I_TO_NAME_HASH = 4155329257L
private val setJointIToNameBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkin", "set_joint_i_to_name", SET_JOINT_I_TO_NAME_HASH)
}
