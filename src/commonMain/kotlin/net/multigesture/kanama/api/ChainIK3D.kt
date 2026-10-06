package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A `SkeletonModifier3D` to apply inverse kinematics to bone chains containing an arbitrary number
 * of bones.
 *
 * Generated from Godot docs: ChainIK3D
 */
open class ChainIK3D(handle: GodotHandle) : IKModifier3D(handle) {
    /**
     * Sets the root bone name of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.set_root_bone_name
     */
    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setRootBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the root bone name of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.get_root_bone_name
     */
    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getRootBoneNameBind, segment, index)
    }

    /**
     * Sets the root bone index of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.set_root_bone
     */
    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setRootBoneBind, segment, index, bone)
    }

    /**
     * Returns the root bone index of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.get_root_bone
     */
    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getRootBoneBind, segment, index)
    }

    /**
     * Sets the end bone name of the bone chain. Note: The end bone must be the root bone or a child of
     * the root bone. If they are the same, the tail must be extended by `set_extend_end_bone` to
     * modify the bone.
     *
     * Generated from Godot docs: ChainIK3D.set_end_bone_name
     */
    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setEndBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the end bone name of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.get_end_bone_name
     */
    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getEndBoneNameBind, segment, index)
    }

    /**
     * Sets the end bone index of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.set_end_bone
     */
    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setEndBoneBind, segment, index, bone)
    }

    /**
     * Returns the end bone index of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.get_end_bone
     */
    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getEndBoneBind, segment, index)
    }

    /**
     * If `enabled` is `true`, the end bone is extended to have a tail. The extended tail config is
     * allocated to the last element in the joint list. In other words, if you set `enabled` to
     * `false`, the config of the last element in the joint list has no effect in the simulated result.
     *
     * Generated from Godot docs: ChainIK3D.set_extend_end_bone
     */
    fun setExtendEndBone(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setExtendEndBoneBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the end bone is extended to have a tail.
     *
     * Generated from Godot docs: ChainIK3D.is_end_bone_extended
     */
    fun isEndBoneExtended(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isEndBoneExtendedBind, segment, index)
    }

    /**
     * Sets the end bone tail direction of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: ChainIK3D.set_end_bone_direction
     */
    fun setEndBoneDirection(index: Int, boneDirection: SkeletonModifier3D.BoneDirection) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setEndBoneDirectionBind, segment, index, boneDirection.value)
    }

    /**
     * Returns the tail direction of the end bone of the bone chain when `is_end_bone_extended` is
     * `true`.
     *
     * Generated from Godot docs: ChainIK3D.get_end_bone_direction
     */
    fun getEndBoneDirection(index: Int): SkeletonModifier3D.BoneDirection {
        return SkeletonModifier3D.BoneDirection(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getEndBoneDirectionBind, segment, index))
    }

    /**
     * Sets the end bone tail length of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: ChainIK3D.set_end_bone_length
     */
    fun setEndBoneLength(index: Int, length: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setEndBoneLengthBind, segment, index, length)
    }

    /**
     * Returns the end bone tail length of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: ChainIK3D.get_end_bone_length
     */
    fun getEndBoneLength(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getEndBoneLengthBind, segment, index)
    }

    /**
     * Returns the bone name at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: ChainIK3D.get_joint_bone_name
     */
    fun getJointBoneName(index: Int, joint: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(Binds.getJointBoneNameBind, segment, index, joint)
    }

    /**
     * Returns the bone index at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: ChainIK3D.get_joint_bone
     */
    fun getJointBone(index: Int, joint: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getJointBoneBind, segment, index, joint)
    }

    /**
     * Returns the joint count of the bone chain's joint list.
     *
     * Generated from Godot docs: ChainIK3D.get_joint_count
     */
    fun getJointCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getJointCountBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ChainIK3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ChainIK3D? =
            if (handle.address() == 0L) null else ChainIK3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        @JvmField
        val setRootBoneNameBind =
            ObjectCalls.getMethodBind("ChainIK3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        @JvmField
        val getRootBoneNameBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)

        private const val SET_ROOT_BONE_HASH = 3937882851L
        @JvmField
        val setRootBoneBind =
            ObjectCalls.getMethodBind("ChainIK3D", "set_root_bone", SET_ROOT_BONE_HASH)

        private const val GET_ROOT_BONE_HASH = 923996154L
        @JvmField
        val getRootBoneBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_root_bone", GET_ROOT_BONE_HASH)

        private const val SET_END_BONE_NAME_HASH = 501894301L
        @JvmField
        val setEndBoneNameBind =
            ObjectCalls.getMethodBind("ChainIK3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)

        private const val GET_END_BONE_NAME_HASH = 844755477L
        @JvmField
        val getEndBoneNameBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)

        private const val SET_END_BONE_HASH = 3937882851L
        @JvmField
        val setEndBoneBind =
            ObjectCalls.getMethodBind("ChainIK3D", "set_end_bone", SET_END_BONE_HASH)

        private const val GET_END_BONE_HASH = 923996154L
        @JvmField
        val getEndBoneBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone", GET_END_BONE_HASH)

        private const val SET_EXTEND_END_BONE_HASH = 300928843L
        @JvmField
        val setExtendEndBoneBind =
            ObjectCalls.getMethodBind("ChainIK3D", "set_extend_end_bone", SET_EXTEND_END_BONE_HASH)

        private const val IS_END_BONE_EXTENDED_HASH = 1116898809L
        @JvmField
        val isEndBoneExtendedBind =
            ObjectCalls.getMethodBind("ChainIK3D", "is_end_bone_extended", IS_END_BONE_EXTENDED_HASH)

        private const val SET_END_BONE_DIRECTION_HASH = 2838484201L
        @JvmField
        val setEndBoneDirectionBind =
            ObjectCalls.getMethodBind("ChainIK3D", "set_end_bone_direction", SET_END_BONE_DIRECTION_HASH)

        private const val GET_END_BONE_DIRECTION_HASH = 1843036459L
        @JvmField
        val getEndBoneDirectionBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone_direction", GET_END_BONE_DIRECTION_HASH)

        private const val SET_END_BONE_LENGTH_HASH = 1602489585L
        @JvmField
        val setEndBoneLengthBind =
            ObjectCalls.getMethodBind("ChainIK3D", "set_end_bone_length", SET_END_BONE_LENGTH_HASH)

        private const val GET_END_BONE_LENGTH_HASH = 2339986948L
        @JvmField
        val getEndBoneLengthBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone_length", GET_END_BONE_LENGTH_HASH)

        private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
        @JvmField
        val getJointBoneNameBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)

        private const val GET_JOINT_BONE_HASH = 3175239445L
        @JvmField
        val getJointBoneBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_joint_bone", GET_JOINT_BONE_HASH)

        private const val GET_JOINT_COUNT_HASH = 923996154L
        @JvmField
        val getJointCountBind =
            ObjectCalls.getMethodBind("ChainIK3D", "get_joint_count", GET_JOINT_COUNT_HASH)
    }
}
