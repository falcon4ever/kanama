package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for SpringBoneSimulator3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SpringBoneSimulator3D waits on: ptrcallWithIntArgRetNodePath,
//   ptrcallWithIntArgRetString, ptrcallWithTwoIntArgsRetNodePath, ptrcallWithTwoIntArgsRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the root bone name of the bone chain.
 *
 * Generated from Godot docs: SpringBoneSimulator3D.get_root_bone_name
 */
fun SpringBoneSimulator3D.getRootBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
}

/**
 * Returns the end bone name of the bone chain.
 *
 * Generated from Godot docs: SpringBoneSimulator3D.get_end_bone_name
 */
fun SpringBoneSimulator3D.getEndBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
}

/**
 * Returns the center node path of the bone chain.
 *
 * Generated from Godot docs: SpringBoneSimulator3D.get_center_node
 */
fun SpringBoneSimulator3D.getCenterNode(index: Int): NodePath {
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getCenterNodeBind, handle, index)
}

/**
 * Returns the center bone name of the bone chain.
 *
 * Generated from Godot docs: SpringBoneSimulator3D.get_center_bone_name
 */
fun SpringBoneSimulator3D.getCenterBoneName(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getCenterBoneNameBind, handle, index)
}

/**
 * Returns the bone name at `joint` in the bone chain's joint list.
 *
 * Generated from Godot docs: SpringBoneSimulator3D.get_joint_bone_name
 */
fun SpringBoneSimulator3D.getJointBoneName(index: Int, joint: Int): String {
    return ObjectCalls.ptrcallWithTwoIntArgsRetString(getJointBoneNameBind, handle, index, joint)
}

/**
 * Returns the node path of the `SpringBoneCollision3D` at `collision` in the bone chain's exclude
 * collision list when `are_all_child_collisions_enabled` is `true`.
 *
 * Generated from Godot docs: SpringBoneSimulator3D.get_exclude_collision_path
 */
fun SpringBoneSimulator3D.getExcludeCollisionPath(index: Int, collision: Int): NodePath {
    return ObjectCalls.ptrcallWithTwoIntArgsRetNodePath(getExcludeCollisionPathBind, handle, index, collision)
}

/**
 * Returns the node path of the `SpringBoneCollision3D` at `collision` in the bone chain's
 * collision list when `are_all_child_collisions_enabled` is `false`.
 *
 * Generated from Godot docs: SpringBoneSimulator3D.get_collision_path
 */
fun SpringBoneSimulator3D.getCollisionPath(index: Int, collision: Int): NodePath {
    return ObjectCalls.ptrcallWithTwoIntArgsRetNodePath(getCollisionPathBind, handle, index, collision)
}

private const val GET_ROOT_BONE_NAME_HASH = 844755477L
private val getRootBoneNameBind by lazy {
    ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
}

private const val GET_END_BONE_NAME_HASH = 844755477L
private val getEndBoneNameBind by lazy {
    ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
}

private const val GET_CENTER_NODE_HASH = 408788394L
private val getCenterNodeBind by lazy {
    ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_node", GET_CENTER_NODE_HASH)
}

private const val GET_CENTER_BONE_NAME_HASH = 844755477L
private val getCenterBoneNameBind by lazy {
    ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_bone_name", GET_CENTER_BONE_NAME_HASH)
}

private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
private val getJointBoneNameBind by lazy {
    ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)
}

private const val GET_EXCLUDE_COLLISION_PATH_HASH = 464924783L
private val getExcludeCollisionPathBind by lazy {
    ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_exclude_collision_path", GET_EXCLUDE_COLLISION_PATH_HASH)
}

private const val GET_COLLISION_PATH_HASH = 464924783L
private val getCollisionPathBind by lazy {
    ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_collision_path", GET_COLLISION_PATH_HASH)
}
