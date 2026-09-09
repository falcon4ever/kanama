package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for BoneConstraint3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP BoneConstraint3D waits on: ptrcallWithIntArgRetNodePath,
//   ptrcallWithIntArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the apply bone name of the setting at `index`. This bone will be modified.
 *
 * Generated from Godot docs: BoneConstraint3D.get_apply_bone_name
 */
fun BoneConstraint3D.getApplyBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getApplyBoneNameBind, handle, index)
}

/**
 * Returns the reference bone name of the setting at `index`. This bone will be only referenced and
 * not modified by this modifier.
 *
 * Generated from Godot docs: BoneConstraint3D.get_reference_bone_name
 */
fun BoneConstraint3D.getReferenceBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getReferenceBoneNameBind, handle, index)
}

/**
 * Returns the reference node path of the setting at `index`. This node will be only referenced and
 * not modified by this modifier.
 *
 * Generated from Godot docs: BoneConstraint3D.get_reference_node
 */
fun BoneConstraint3D.getReferenceNode(index: Int): NodePath {
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getReferenceNodeBind, handle, index)
}

private const val GET_APPLY_BONE_NAME_HASH = 844755477L
private val getApplyBoneNameBind by lazy {
    ObjectCalls.getMethodBind("BoneConstraint3D", "get_apply_bone_name", GET_APPLY_BONE_NAME_HASH)
}

private const val GET_REFERENCE_BONE_NAME_HASH = 844755477L
private val getReferenceBoneNameBind by lazy {
    ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_bone_name", GET_REFERENCE_BONE_NAME_HASH)
}

private const val GET_REFERENCE_NODE_HASH = 408788394L
private val getReferenceNodeBind by lazy {
    ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_node", GET_REFERENCE_NODE_HASH)
}
