package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Base class for a profile of a virtual skeleton used as a target for retargeting.
 *
 * Generated from Godot docs: SkeletonProfile
 */
open class SkeletonProfile(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(Binds.setRootBoneBind, segment, boneName)
    }

    /**
     * A bone name that will be used as the root bone in `AnimationTree`. This should be the bone of
     * the parent of hips that exists at the world origin.
     *
     * Generated from Godot docs: SkeletonProfile.get_root_bone
     */
    fun getRootBone(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getRootBoneBind, segment)
    }

    /**
     * A bone name which will use model's height as the coefficient for normalization. For example,
     * `SkeletonProfileHumanoid` defines it as `Hips`.
     *
     * Generated from Godot docs: SkeletonProfile.set_scale_base_bone
     */
    fun setScaleBaseBone(boneName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(Binds.setScaleBaseBoneBind, segment, boneName)
    }

    /**
     * A bone name which will use model's height as the coefficient for normalization. For example,
     * `SkeletonProfileHumanoid` defines it as `Hips`.
     *
     * Generated from Godot docs: SkeletonProfile.get_scale_base_bone
     */
    fun getScaleBaseBone(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getScaleBaseBoneBind, segment)
    }

    /**
     * The amount of groups of bones in retargeting section's `BoneMap` editor. For example,
     * `SkeletonProfileHumanoid` has 4 groups. This property exists to separate the bone list into
     * several sections in the editor.
     *
     * Generated from Godot docs: SkeletonProfile.set_group_size
     */
    fun setGroupSize(size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setGroupSizeBind, segment, size)
    }

    /**
     * The amount of groups of bones in retargeting section's `BoneMap` editor. For example,
     * `SkeletonProfileHumanoid` has 4 groups. This property exists to separate the bone list into
     * several sections in the editor.
     *
     * Generated from Godot docs: SkeletonProfile.get_group_size
     */
    fun getGroupSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getGroupSizeBind, segment)
    }

    /**
     * Returns the name of the group at `group_idx` that will be the drawing group in the `BoneMap`
     * editor.
     *
     * Generated from Godot docs: SkeletonProfile.get_group_name
     */
    fun getGroupName(groupIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getGroupNameBind, segment, groupIdx)
    }

    /**
     * Sets the name of the group at `group_idx` that will be the drawing group in the `BoneMap`
     * editor.
     *
     * Generated from Godot docs: SkeletonProfile.set_group_name
     */
    fun setGroupName(groupIdx: Int, groupName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setGroupNameBind, segment, groupIdx, groupName)
    }

    /**
     * Returns the texture of the group at `group_idx` that will be the drawing group background image
     * in the `BoneMap` editor.
     *
     * Generated from Godot docs: SkeletonProfile.get_texture
     */
    fun getTexture(groupIdx: Int): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getTextureBind, segment, groupIdx))
    }

    /**
     * Sets the texture of the group at `group_idx` that will be the drawing group background image in
     * the `BoneMap` editor.
     *
     * Generated from Godot docs: SkeletonProfile.set_texture
     */
    fun setTexture(groupIdx: Int, texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setTextureBind, segment, groupIdx, texture?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * The amount of bones in retargeting section's `BoneMap` editor. For example,
     * `SkeletonProfileHumanoid` has 56 bones. The size of elements in `BoneMap` updates when changing
     * this property in it's assigned `SkeletonProfile`.
     *
     * Generated from Godot docs: SkeletonProfile.set_bone_size
     */
    fun setBoneSize(size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBoneSizeBind, segment, size)
    }

    /**
     * The amount of bones in retargeting section's `BoneMap` editor. For example,
     * `SkeletonProfileHumanoid` has 56 bones. The size of elements in `BoneMap` updates when changing
     * this property in it's assigned `SkeletonProfile`.
     *
     * Generated from Godot docs: SkeletonProfile.get_bone_size
     */
    fun getBoneSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBoneSizeBind, segment)
    }

    /**
     * Returns the bone index that matches `bone_name` as its name.
     *
     * Generated from Godot docs: SkeletonProfile.find_bone
     */
    fun findBone(boneName: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetInt(Binds.findBoneBind, segment, boneName)
    }

    /**
     * Returns the name of the bone at `bone_idx` that will be the key name in the `BoneMap`. In the
     * retargeting process, the returned bone name is the bone name of the target skeleton.
     *
     * Generated from Godot docs: SkeletonProfile.get_bone_name
     */
    fun getBoneName(boneIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getBoneNameBind, segment, boneIdx)
    }

    /**
     * Sets the name of the bone at `bone_idx` that will be the key name in the `BoneMap`. In the
     * retargeting process, the setting bone name is the bone name of the target skeleton.
     *
     * Generated from Godot docs: SkeletonProfile.set_bone_name
     */
    fun setBoneName(boneIdx: Int, boneName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setBoneNameBind, segment, boneIdx, boneName)
    }

    /**
     * Returns the name of the bone which is the parent to the bone at `bone_idx`. The result is empty
     * if the bone has no parent.
     *
     * Generated from Godot docs: SkeletonProfile.get_bone_parent
     */
    fun getBoneParent(boneIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getBoneParentBind, segment, boneIdx)
    }

    /**
     * Sets the bone with name `bone_parent` as the parent of the bone at `bone_idx`. If an empty
     * string is passed, then the bone has no parent.
     *
     * Generated from Godot docs: SkeletonProfile.set_bone_parent
     */
    fun setBoneParent(boneIdx: Int, boneParent: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setBoneParentBind, segment, boneIdx, boneParent)
    }

    /**
     * Returns the tail direction of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.get_tail_direction
     */
    fun getTailDirection(boneIdx: Int): SkeletonProfile.TailDirection {
        checkOpen()
        return SkeletonProfile.TailDirection(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getTailDirectionBind, segment, boneIdx))
    }

    /**
     * Sets the tail direction of the bone at `bone_idx`. Note: This only specifies the method of
     * calculation. The actual coordinates required should be stored in an external skeleton, so the
     * calculation itself needs to be done externally.
     *
     * Generated from Godot docs: SkeletonProfile.set_tail_direction
     */
    fun setTailDirection(boneIdx: Int, tailDirection: SkeletonProfile.TailDirection) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setTailDirectionBind, segment, boneIdx, tailDirection.value)
    }

    /**
     * Returns the name of the bone which is the tail of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.get_bone_tail
     */
    fun getBoneTail(boneIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getBoneTailBind, segment, boneIdx)
    }

    /**
     * Sets the bone with name `bone_tail` as the tail of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.set_bone_tail
     */
    fun setBoneTail(boneIdx: Int, boneTail: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setBoneTailBind, segment, boneIdx, boneTail)
    }

    /**
     * Returns the reference pose transform for bone `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.get_reference_pose
     */
    fun getReferencePose(boneIdx: Int): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(Binds.getReferencePoseBind, segment, boneIdx)
    }

    /**
     * Sets the reference pose transform for bone `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.set_reference_pose
     */
    fun setReferencePose(boneIdx: Int, boneName: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(Binds.setReferencePoseBind, segment, boneIdx, boneName)
    }

    /**
     * Returns the offset of the bone at `bone_idx` that will be the button position in the `BoneMap`
     * editor. This is the offset with origin at the top left corner of the square.
     *
     * Generated from Godot docs: SkeletonProfile.get_handle_offset
     */
    fun getHandleOffset(boneIdx: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getHandleOffsetBind, segment, boneIdx)
    }

    /**
     * Sets the offset of the bone at `bone_idx` that will be the button position in the `BoneMap`
     * editor. This is the offset with origin at the top left corner of the square.
     *
     * Generated from Godot docs: SkeletonProfile.set_handle_offset
     */
    fun setHandleOffset(boneIdx: Int, handleOffset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setHandleOffsetBind, segment, boneIdx, handleOffset)
    }

    /**
     * Returns the group of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.get_group
     */
    fun getGroup(boneIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getGroupBind, segment, boneIdx)
    }

    /**
     * Sets the group of the bone at `bone_idx`.
     *
     * Generated from Godot docs: SkeletonProfile.set_group
     */
    fun setGroup(boneIdx: Int, group: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setGroupBind, segment, boneIdx, group)
    }

    /**
     * Returns whether the bone at `bone_idx` is required for retargeting. This value is used by the
     * bone map editor. If this method returns `true`, and no bone is assigned, the handle color will
     * be red on the bone map editor.
     *
     * Generated from Godot docs: SkeletonProfile.is_required
     */
    fun isRequired(boneIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isRequiredBind, segment, boneIdx)
    }

    /**
     * Sets the required status for bone `bone_idx` to `required`.
     *
     * Generated from Godot docs: SkeletonProfile.set_required
     */
    fun setRequired(boneIdx: Int, required: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setRequiredBind, segment, boneIdx, required)
    }

    /** Signal `profile_updated()`; see [TypedSignal]. */
    val profileUpdated: Signal0
        @JvmName("profileUpdatedTypedSignal")
        get() = Signal0(this, "profile_updated")

    object Signals {
        const val profileUpdated: String = "profile_updated"
    }

    /**
     * Godot's `SkeletonProfile.TailDirection` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`SkeletonProfile.TailDirection.<NAME>`).
     *
     * Generated from Godot docs: SkeletonProfile.TailDirection
     */
    @JvmInline
    value class TailDirection(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Direction to the average coordinates of bone children.
             *
             * Generated from Godot docs: SkeletonProfile.TAIL_DIRECTION_AVERAGE_CHILDREN
             */
            val AVERAGE_CHILDREN: TailDirection get() = TailDirection(0L)
            /**
             * Direction to the coordinates of specified bone child.
             *
             * Generated from Godot docs: SkeletonProfile.TAIL_DIRECTION_SPECIFIC_CHILD
             */
            val SPECIFIC_CHILD: TailDirection get() = TailDirection(1L)
            /**
             * Direction is not calculated.
             *
             * Generated from Godot docs: SkeletonProfile.TAIL_DIRECTION_END
             */
            val END: TailDirection get() = TailDirection(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonProfile? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SkeletonProfile? =
            if (handle.address() == 0L) null else RefCounted.owned(SkeletonProfile(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SkeletonProfile? =
            if (handle.address() == 0L) null else SkeletonProfile(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ROOT_BONE_HASH = 3304788590L
        @JvmField
        val setRootBoneBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_root_bone", SET_ROOT_BONE_HASH)

        private const val GET_ROOT_BONE_HASH = 2737447660L
        @JvmField
        val getRootBoneBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_root_bone", GET_ROOT_BONE_HASH)

        private const val SET_SCALE_BASE_BONE_HASH = 3304788590L
        @JvmField
        val setScaleBaseBoneBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_scale_base_bone", SET_SCALE_BASE_BONE_HASH)

        private const val GET_SCALE_BASE_BONE_HASH = 2737447660L
        @JvmField
        val getScaleBaseBoneBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_scale_base_bone", GET_SCALE_BASE_BONE_HASH)

        private const val SET_GROUP_SIZE_HASH = 1286410249L
        @JvmField
        val setGroupSizeBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_group_size", SET_GROUP_SIZE_HASH)

        private const val GET_GROUP_SIZE_HASH = 2455072627L
        @JvmField
        val getGroupSizeBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_group_size", GET_GROUP_SIZE_HASH)

        private const val GET_GROUP_NAME_HASH = 659327637L
        @JvmField
        val getGroupNameBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_group_name", GET_GROUP_NAME_HASH)

        private const val SET_GROUP_NAME_HASH = 3780747571L
        @JvmField
        val setGroupNameBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_group_name", SET_GROUP_NAME_HASH)

        private const val GET_TEXTURE_HASH = 3536238170L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_texture", GET_TEXTURE_HASH)

        private const val SET_TEXTURE_HASH = 666127730L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_texture", SET_TEXTURE_HASH)

        private const val SET_BONE_SIZE_HASH = 1286410249L
        @JvmField
        val setBoneSizeBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_bone_size", SET_BONE_SIZE_HASH)

        private const val GET_BONE_SIZE_HASH = 2455072627L
        @JvmField
        val getBoneSizeBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_size", GET_BONE_SIZE_HASH)

        private const val FIND_BONE_HASH = 2458036349L
        @JvmField
        val findBoneBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "find_bone", FIND_BONE_HASH)

        private const val GET_BONE_NAME_HASH = 659327637L
        @JvmField
        val getBoneNameBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_name", GET_BONE_NAME_HASH)

        private const val SET_BONE_NAME_HASH = 3780747571L
        @JvmField
        val setBoneNameBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_bone_name", SET_BONE_NAME_HASH)

        private const val GET_BONE_PARENT_HASH = 659327637L
        @JvmField
        val getBoneParentBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_parent", GET_BONE_PARENT_HASH)

        private const val SET_BONE_PARENT_HASH = 3780747571L
        @JvmField
        val setBoneParentBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_bone_parent", SET_BONE_PARENT_HASH)

        private const val GET_TAIL_DIRECTION_HASH = 2675997574L
        @JvmField
        val getTailDirectionBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_tail_direction", GET_TAIL_DIRECTION_HASH)

        private const val SET_TAIL_DIRECTION_HASH = 1231951015L
        @JvmField
        val setTailDirectionBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_tail_direction", SET_TAIL_DIRECTION_HASH)

        private const val GET_BONE_TAIL_HASH = 659327637L
        @JvmField
        val getBoneTailBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_bone_tail", GET_BONE_TAIL_HASH)

        private const val SET_BONE_TAIL_HASH = 3780747571L
        @JvmField
        val setBoneTailBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_bone_tail", SET_BONE_TAIL_HASH)

        private const val GET_REFERENCE_POSE_HASH = 1965739696L
        @JvmField
        val getReferencePoseBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_reference_pose", GET_REFERENCE_POSE_HASH)

        private const val SET_REFERENCE_POSE_HASH = 3616898986L
        @JvmField
        val setReferencePoseBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_reference_pose", SET_REFERENCE_POSE_HASH)

        private const val GET_HANDLE_OFFSET_HASH = 2299179447L
        @JvmField
        val getHandleOffsetBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_handle_offset", GET_HANDLE_OFFSET_HASH)

        private const val SET_HANDLE_OFFSET_HASH = 163021252L
        @JvmField
        val setHandleOffsetBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_handle_offset", SET_HANDLE_OFFSET_HASH)

        private const val GET_GROUP_HASH = 659327637L
        @JvmField
        val getGroupBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "get_group", GET_GROUP_HASH)

        private const val SET_GROUP_HASH = 3780747571L
        @JvmField
        val setGroupBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_group", SET_GROUP_HASH)

        private const val IS_REQUIRED_HASH = 1116898809L
        @JvmField
        val isRequiredBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "is_required", IS_REQUIRED_HASH)

        private const val SET_REQUIRED_HASH = 300928843L
        @JvmField
        val setRequiredBind =
            ObjectCalls.getMethodBind("SkeletonProfile", "set_required", SET_REQUIRED_HASH)
    }
}
