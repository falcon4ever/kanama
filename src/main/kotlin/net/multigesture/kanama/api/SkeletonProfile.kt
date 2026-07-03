package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Base class for a profile of a virtual skeleton used as a target for retargeting.
 *
 * Generated from Godot docs: SkeletonProfile
 */
open class SkeletonProfile(handle: MemorySegment) : Resource(handle) {
    var rootBone: String
        @JvmName("rootBoneProperty")
        get() = getRootBone()
        @JvmName("setRootBoneProperty")
        set(value) = setRootBone(value)

    var scaleBaseBone: String
        @JvmName("scaleBaseBoneProperty")
        get() = getScaleBaseBone()
        @JvmName("setScaleBaseBoneProperty")
        set(value) = setScaleBaseBone(value)

    var groupSize: Int
        @JvmName("groupSizeProperty")
        get() = getGroupSize()
        @JvmName("setGroupSizeProperty")
        set(value) = setGroupSize(value)

    var boneSize: Int
        @JvmName("boneSizeProperty")
        get() = getBoneSize()
        @JvmName("setBoneSizeProperty")
        set(value) = setBoneSize(value)

    /**
     * A bone name that will be used as the root bone in `AnimationTree`. This should be the bone of
     * the parent of hips that exists at the world origin.
     *
     * Generated from Godot docs: SkeletonProfile.set_root_bone
     */
    fun setRootBone(boneName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setRootBoneBind, handle, boneName)
    }

    /**
     * A bone name that will be used as the root bone in `AnimationTree`. This should be the bone of
     * the parent of hips that exists at the world origin.
     *
     * Generated from Godot docs: SkeletonProfile.get_root_bone
     */
    fun getRootBone(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getRootBoneBind, handle)
    }

    /**
     * A bone name which will use model's height as the coefficient for normalization. For example,
     * `SkeletonProfileHumanoid` defines it as `Hips`.
     *
     * Generated from Godot docs: SkeletonProfile.set_scale_base_bone
     */
    fun setScaleBaseBone(boneName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setScaleBaseBoneBind, handle, boneName)
    }

    /**
     * A bone name which will use model's height as the coefficient for normalization. For example,
     * `SkeletonProfileHumanoid` defines it as `Hips`.
     *
     * Generated from Godot docs: SkeletonProfile.get_scale_base_bone
     */
    fun getScaleBaseBone(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getScaleBaseBoneBind, handle)
    }

    /**
     * The amount of groups of bones in retargeting section's `BoneMap` editor. For example,
     * `SkeletonProfileHumanoid` has 4 groups. This property exists to separate the bone list into
     * several sections in the editor.
     *
     * Generated from Godot docs: SkeletonProfile.set_group_size
     */
    fun setGroupSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setGroupSizeBind, handle, size)
    }

    /**
     * The amount of groups of bones in retargeting section's `BoneMap` editor. For example,
     * `SkeletonProfileHumanoid` has 4 groups. This property exists to separate the bone list into
     * several sections in the editor.
     *
     * Generated from Godot docs: SkeletonProfile.get_group_size
     */
    fun getGroupSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getGroupSizeBind, handle)
    }

    /**
     * Returns the name of the group at `group_idx` that will be the drawing group in the `BoneMap`
     * editor.
     *
     * Generated from Godot docs: SkeletonProfile.get_group_name
     */
    fun getGroupName(groupIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getGroupNameBind, handle, groupIdx)
    }

    /**
     * Sets the name of the group at `group_idx` that will be the drawing group in the `BoneMap`
     * editor.
     *
     * Generated from Godot docs: SkeletonProfile.set_group_name
     */
    fun setGroupName(groupIdx: Int, groupName: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setGroupNameBind, handle, groupIdx, groupName)
    }

    /**
     * Returns the texture of the group at `group_idx` that will be the drawing group background image
     * in the `BoneMap` editor.
     *
     * Generated from Godot docs: SkeletonProfile.get_texture
     */
    fun getTexture(groupIdx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTextureBind, handle, groupIdx))
    }

    /**
     * Sets the texture of the group at `group_idx` that will be the drawing group background image in
     * the `BoneMap` editor.
     *
     * Generated from Godot docs: SkeletonProfile.set_texture
     */
    fun setTexture(groupIdx: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setTextureBind, handle, groupIdx, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * The amount of bones in retargeting section's `BoneMap` editor. For example,
     * `SkeletonProfileHumanoid` has 56 bones. The size of elements in `BoneMap` updates when changing
     * this property in it's assigned `SkeletonProfile`.
     *
     * Generated from Godot docs: SkeletonProfile.set_bone_size
     */
    fun setBoneSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setBoneSizeBind, handle, size)
    }

    /**
     * The amount of bones in retargeting section's `BoneMap` editor. For example,
     * `SkeletonProfileHumanoid` has 56 bones. The size of elements in `BoneMap` updates when changing
     * this property in it's assigned `SkeletonProfile`.
     *
     * Generated from Godot docs: SkeletonProfile.get_bone_size
     */
    fun getBoneSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneSizeBind, handle)
    }

    /**
     * Returns the bone index that matches `bone_name` as its name.
     *
     * Generated from Godot docs: SkeletonProfile.find_bone
     */
    fun findBone(boneName: String): Int {
        return ObjectCalls.ptrcallWithStringNameArgRetInt(findBoneBind, handle, boneName)
    }

    /**
     * Returns the name of the bone at `bone_idx` that will be the key name in the `BoneMap`. In the
     * retargeting process, the returned bone name is the bone name of the target skeleton.
     *
     * Generated from Godot docs: SkeletonProfile.get_bone_name
     */
    fun getBoneName(boneIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBoneNameBind, handle, boneIdx)
    }

    /**
     * Sets the name of the bone at `bone_idx` that will be the key name in the `BoneMap`. In the
     * retargeting process, the setting bone name is the bone name of the target skeleton.
     *
     * Generated from Godot docs: SkeletonProfile.set_bone_name
     */
    fun setBoneName(boneIdx: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBoneNameBind, handle, boneIdx, boneName)
    }

    /**
     * Returns the name of the bone which is the parent to the bone at `bone_idx`. The result is empty
     * if the bone has no parent.
     *
     * Generated from Godot docs: SkeletonProfile.get_bone_parent
     */
    fun getBoneParent(boneIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBoneParentBind, handle, boneIdx)
    }

    /**
     * Sets the bone with name `bone_parent` as the parent of the bone at `bone_idx`. If an empty
     * string is passed, then the bone has no parent.
     *
     * Generated from Godot docs: SkeletonProfile.set_bone_parent
     */
    fun setBoneParent(boneIdx: Int, boneParent: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBoneParentBind, handle, boneIdx, boneParent)
    }

    /**
     * Returns the tail direction of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.get_tail_direction
     */
    fun getTailDirection(boneIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getTailDirectionBind, handle, boneIdx)
    }

    /**
     * Sets the tail direction of the bone at `bone_idx`. Note: This only specifies the method of
     * calculation. The actual coordinates required should be stored in an external skeleton, so the
     * calculation itself needs to be done externally.
     *
     * Generated from Godot docs: SkeletonProfile.set_tail_direction
     */
    fun setTailDirection(boneIdx: Int, tailDirection: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setTailDirectionBind, handle, boneIdx, tailDirection)
    }

    /**
     * Returns the name of the bone which is the tail of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.get_bone_tail
     */
    fun getBoneTail(boneIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBoneTailBind, handle, boneIdx)
    }

    /**
     * Sets the bone with name `bone_tail` as the tail of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.set_bone_tail
     */
    fun setBoneTail(boneIdx: Int, boneTail: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBoneTailBind, handle, boneIdx, boneTail)
    }

    /**
     * Returns the reference pose transform for bone `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.get_reference_pose
     */
    fun getReferencePose(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getReferencePoseBind, handle, boneIdx)
    }

    /**
     * Sets the reference pose transform for bone `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.set_reference_pose
     */
    fun setReferencePose(boneIdx: Int, boneName: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setReferencePoseBind, handle, boneIdx, boneName)
    }

    /**
     * Returns the offset of the bone at `bone_idx` that will be the button position in the `BoneMap`
     * editor. This is the offset with origin at the top left corner of the square.
     *
     * Generated from Godot docs: SkeletonProfile.get_handle_offset
     */
    fun getHandleOffset(boneIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getHandleOffsetBind, handle, boneIdx)
    }

    /**
     * Sets the offset of the bone at `bone_idx` that will be the button position in the `BoneMap`
     * editor. This is the offset with origin at the top left corner of the square.
     *
     * Generated from Godot docs: SkeletonProfile.set_handle_offset
     */
    fun setHandleOffset(boneIdx: Int, handleOffset: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setHandleOffsetBind, handle, boneIdx, handleOffset)
    }

    /**
     * Returns the group of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.get_group
     */
    fun getGroup(boneIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getGroupBind, handle, boneIdx)
    }

    /**
     * Sets the group of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.set_group
     */
    fun setGroup(boneIdx: Int, group: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setGroupBind, handle, boneIdx, group)
    }

    /**
     * Returns whether the bone at `bone_idx` is required for retargeting. This value is used by the
     * bone map editor. If this method returns `true`, and no bone is assigned, the handle color will
     * be red on the bone map editor.
     *
     * Generated from Godot docs: SkeletonProfile.is_required
     */
    fun isRequired(boneIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isRequiredBind, handle, boneIdx)
    }

    /**
     * Sets the required status for bone `bone_idx` to `required`.
     *
     * Generated from Godot docs: SkeletonProfile.set_required
     */
    fun setRequired(boneIdx: Int, required: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setRequiredBind, handle, boneIdx, required)
    }

    object Signals {
        const val profileUpdated: String = "profile_updated"
    }

    companion object {
        const val TAIL_DIRECTION_AVERAGE_CHILDREN: Long = 0L
        const val TAIL_DIRECTION_SPECIFIC_CHILD: Long = 1L
        const val TAIL_DIRECTION_END: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonProfile? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonProfile? =
            if (handle.address() == 0L) null else SkeletonProfile(handle)

        private const val SET_ROOT_BONE_HASH = 3304788590L
        private val setRootBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_root_bone", SET_ROOT_BONE_HASH)
        }

        private const val GET_ROOT_BONE_HASH = 2737447660L
        private val getRootBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_root_bone", GET_ROOT_BONE_HASH)
        }

        private const val SET_SCALE_BASE_BONE_HASH = 3304788590L
        private val setScaleBaseBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_scale_base_bone", SET_SCALE_BASE_BONE_HASH)
        }

        private const val GET_SCALE_BASE_BONE_HASH = 2737447660L
        private val getScaleBaseBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_scale_base_bone", GET_SCALE_BASE_BONE_HASH)
        }

        private const val SET_GROUP_SIZE_HASH = 1286410249L
        private val setGroupSizeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_group_size", SET_GROUP_SIZE_HASH)
        }

        private const val GET_GROUP_SIZE_HASH = 2455072627L
        private val getGroupSizeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_group_size", GET_GROUP_SIZE_HASH)
        }

        private const val GET_GROUP_NAME_HASH = 659327637L
        private val getGroupNameBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_group_name", GET_GROUP_NAME_HASH)
        }

        private const val SET_GROUP_NAME_HASH = 3780747571L
        private val setGroupNameBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_group_name", SET_GROUP_NAME_HASH)
        }

        private const val GET_TEXTURE_HASH = 3536238170L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_TEXTURE_HASH = 666127730L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_texture", SET_TEXTURE_HASH)
        }

        private const val SET_BONE_SIZE_HASH = 1286410249L
        private val setBoneSizeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_bone_size", SET_BONE_SIZE_HASH)
        }

        private const val GET_BONE_SIZE_HASH = 2455072627L
        private val getBoneSizeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_size", GET_BONE_SIZE_HASH)
        }

        private const val FIND_BONE_HASH = 2458036349L
        private val findBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "find_bone", FIND_BONE_HASH)
        }

        private const val GET_BONE_NAME_HASH = 659327637L
        private val getBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_name", GET_BONE_NAME_HASH)
        }

        private const val SET_BONE_NAME_HASH = 3780747571L
        private val setBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_bone_name", SET_BONE_NAME_HASH)
        }

        private const val GET_BONE_PARENT_HASH = 659327637L
        private val getBoneParentBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_parent", GET_BONE_PARENT_HASH)
        }

        private const val SET_BONE_PARENT_HASH = 3780747571L
        private val setBoneParentBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_bone_parent", SET_BONE_PARENT_HASH)
        }

        private const val GET_TAIL_DIRECTION_HASH = 2675997574L
        private val getTailDirectionBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_tail_direction", GET_TAIL_DIRECTION_HASH)
        }

        private const val SET_TAIL_DIRECTION_HASH = 1231951015L
        private val setTailDirectionBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_tail_direction", SET_TAIL_DIRECTION_HASH)
        }

        private const val GET_BONE_TAIL_HASH = 659327637L
        private val getBoneTailBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_tail", GET_BONE_TAIL_HASH)
        }

        private const val SET_BONE_TAIL_HASH = 3780747571L
        private val setBoneTailBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_bone_tail", SET_BONE_TAIL_HASH)
        }

        private const val GET_REFERENCE_POSE_HASH = 1965739696L
        private val getReferencePoseBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_reference_pose", GET_REFERENCE_POSE_HASH)
        }

        private const val SET_REFERENCE_POSE_HASH = 3616898986L
        private val setReferencePoseBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_reference_pose", SET_REFERENCE_POSE_HASH)
        }

        private const val GET_HANDLE_OFFSET_HASH = 2299179447L
        private val getHandleOffsetBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_handle_offset", GET_HANDLE_OFFSET_HASH)
        }

        private const val SET_HANDLE_OFFSET_HASH = 163021252L
        private val setHandleOffsetBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_handle_offset", SET_HANDLE_OFFSET_HASH)
        }

        private const val GET_GROUP_HASH = 659327637L
        private val getGroupBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "get_group", GET_GROUP_HASH)
        }

        private const val SET_GROUP_HASH = 3780747571L
        private val setGroupBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_group", SET_GROUP_HASH)
        }

        private const val IS_REQUIRED_HASH = 1116898809L
        private val isRequiredBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "is_required", IS_REQUIRED_HASH)
        }

        private const val SET_REQUIRED_HASH = 300928843L
        private val setRequiredBind by lazy {
            ObjectCalls.getMethodBind("SkeletonProfile", "set_required", SET_REQUIRED_HASH)
        }
    }
}
