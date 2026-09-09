package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for SkeletonProfile (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SkeletonProfile waits on: ptrcallWithIntArgRetStringName
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the name of the group at `group_idx` that will be the drawing group in the `BoneMap`
 * editor.
 *
 * Generated from Godot docs: SkeletonProfile.get_group_name
 */
fun SkeletonProfile.getGroupName(groupIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getGroupNameBind, handle, groupIdx)
}

/**
 * Returns the name of the bone at `bone_idx` that will be the key name in the `BoneMap`. In the
 * retargeting process, the returned bone name is the bone name of the target skeleton.
 *
 * Generated from Godot docs: SkeletonProfile.get_bone_name
 */
fun SkeletonProfile.getBoneName(boneIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getBoneNameBind, handle, boneIdx)
}

/**
 * Returns the name of the bone which is the parent to the bone at `bone_idx`. The result is empty
 * if the bone has no parent.
 *
 * Generated from Godot docs: SkeletonProfile.get_bone_parent
 */
fun SkeletonProfile.getBoneParent(boneIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getBoneParentBind, handle, boneIdx)
}

/**
 * Returns the name of the bone which is the tail of the bone at `bone_idx`.
 *
 * Generated from Godot docs: SkeletonProfile.get_bone_tail
 */
fun SkeletonProfile.getBoneTail(boneIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getBoneTailBind, handle, boneIdx)
}

/**
 * Returns the group of the bone at `bone_idx`.
 *
 * Generated from Godot docs: SkeletonProfile.get_group
 */
fun SkeletonProfile.getGroup(boneIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getGroupBind, handle, boneIdx)
}

private const val GET_GROUP_NAME_HASH = 659327637L
private val getGroupNameBind by lazy {
    ObjectCalls.getMethodBind("SkeletonProfile", "get_group_name", GET_GROUP_NAME_HASH)
}

private const val GET_BONE_NAME_HASH = 659327637L
private val getBoneNameBind by lazy {
    ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_name", GET_BONE_NAME_HASH)
}

private const val GET_BONE_PARENT_HASH = 659327637L
private val getBoneParentBind by lazy {
    ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_parent", GET_BONE_PARENT_HASH)
}

private const val GET_BONE_TAIL_HASH = 659327637L
private val getBoneTailBind by lazy {
    ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_tail", GET_BONE_TAIL_HASH)
}

private const val GET_GROUP_HASH = 659327637L
private val getGroupBind by lazy {
    ObjectCalls.getMethodBind("SkeletonProfile", "get_group", GET_GROUP_HASH)
}
