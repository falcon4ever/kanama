package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A `SkeletonModifier3D` to apply inverse kinematics to bone chains containing an arbitrary number
 * of bones.
 *
 * Generated from Godot docs: ChainIK3D
 */
open class ChainIK3D(handle: MemorySegment) : IKModifier3D(handle) {
    /**
     * Sets the root bone name of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.set_root_bone_name
     */
    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setRootBoneNameBind, handle, index, boneName)
    }

    /**
     * Returns the root bone name of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.get_root_bone_name
     */
    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
    }

    /**
     * Sets the root bone index of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.set_root_bone
     */
    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setRootBoneBind, handle, index, bone)
    }

    /**
     * Returns the root bone index of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.get_root_bone
     */
    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getRootBoneBind, handle, index)
    }

    /**
     * Sets the end bone name of the bone chain. Note: The end bone must be the root bone or a child of
     * the root bone. If they are the same, the tail must be extended by `set_extend_end_bone` to
     * modify the bone.
     *
     * Generated from Godot docs: ChainIK3D.set_end_bone_name
     */
    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setEndBoneNameBind, handle, index, boneName)
    }

    /**
     * Returns the end bone name of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.get_end_bone_name
     */
    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
    }

    /**
     * Sets the end bone index of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.set_end_bone
     */
    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setEndBoneBind, handle, index, bone)
    }

    /**
     * Returns the end bone index of the bone chain.
     *
     * Generated from Godot docs: ChainIK3D.get_end_bone
     */
    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getEndBoneBind, handle, index)
    }

    /**
     * If `enabled` is `true`, the end bone is extended to have a tail. The extended tail config is
     * allocated to the last element in the joint list. In other words, if you set `enabled` to
     * `false`, the config of the last element in the joint list has no effect in the simulated result.
     *
     * Generated from Godot docs: ChainIK3D.set_extend_end_bone
     */
    fun setExtendEndBone(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setExtendEndBoneBind, handle, index, enabled)
    }

    /**
     * Returns `true` if the end bone is extended to have a tail.
     *
     * Generated from Godot docs: ChainIK3D.is_end_bone_extended
     */
    fun isEndBoneExtended(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isEndBoneExtendedBind, handle, index)
    }

    /**
     * Sets the end bone tail direction of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: ChainIK3D.set_end_bone_direction
     */
    fun setEndBoneDirection(index: Int, boneDirection: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setEndBoneDirectionBind, handle, index, boneDirection)
    }

    /**
     * Returns the tail direction of the end bone of the bone chain when `is_end_bone_extended` is
     * `true`.
     *
     * Generated from Godot docs: ChainIK3D.get_end_bone_direction
     */
    fun getEndBoneDirection(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getEndBoneDirectionBind, handle, index)
    }

    /**
     * Sets the end bone tail length of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: ChainIK3D.set_end_bone_length
     */
    fun setEndBoneLength(index: Int, length: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setEndBoneLengthBind, handle, index, length)
    }

    /**
     * Returns the end bone tail length of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: ChainIK3D.get_end_bone_length
     */
    fun getEndBoneLength(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getEndBoneLengthBind, handle, index)
    }

    /**
     * Returns the bone name at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: ChainIK3D.get_joint_bone_name
     */
    fun getJointBoneName(index: Int, joint: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getJointBoneNameBind, handle, index, joint)
    }

    /**
     * Returns the bone index at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: ChainIK3D.get_joint_bone
     */
    fun getJointBone(index: Int, joint: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getJointBoneBind, handle, index, joint)
    }

    /**
     * Returns the joint count of the bone chain's joint list.
     *
     * Generated from Godot docs: ChainIK3D.get_joint_count
     */
    fun getJointCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getJointCountBind, handle, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ChainIK3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ChainIK3D? =
            if (handle.address() == 0L) null else ChainIK3D(handle)

        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        private val setRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)
        }

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        private val getRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
        }

        private const val SET_ROOT_BONE_HASH = 3937882851L
        private val setRootBoneBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "set_root_bone", SET_ROOT_BONE_HASH)
        }

        private const val GET_ROOT_BONE_HASH = 923996154L
        private val getRootBoneBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_root_bone", GET_ROOT_BONE_HASH)
        }

        private const val SET_END_BONE_NAME_HASH = 501894301L
        private val setEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)
        }

        private const val GET_END_BONE_NAME_HASH = 844755477L
        private val getEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
        }

        private const val SET_END_BONE_HASH = 3937882851L
        private val setEndBoneBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "set_end_bone", SET_END_BONE_HASH)
        }

        private const val GET_END_BONE_HASH = 923996154L
        private val getEndBoneBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone", GET_END_BONE_HASH)
        }

        private const val SET_EXTEND_END_BONE_HASH = 300928843L
        private val setExtendEndBoneBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "set_extend_end_bone", SET_EXTEND_END_BONE_HASH)
        }

        private const val IS_END_BONE_EXTENDED_HASH = 1116898809L
        private val isEndBoneExtendedBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "is_end_bone_extended", IS_END_BONE_EXTENDED_HASH)
        }

        private const val SET_END_BONE_DIRECTION_HASH = 2838484201L
        private val setEndBoneDirectionBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "set_end_bone_direction", SET_END_BONE_DIRECTION_HASH)
        }

        private const val GET_END_BONE_DIRECTION_HASH = 1843036459L
        private val getEndBoneDirectionBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone_direction", GET_END_BONE_DIRECTION_HASH)
        }

        private const val SET_END_BONE_LENGTH_HASH = 1602489585L
        private val setEndBoneLengthBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "set_end_bone_length", SET_END_BONE_LENGTH_HASH)
        }

        private const val GET_END_BONE_LENGTH_HASH = 2339986948L
        private val getEndBoneLengthBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_end_bone_length", GET_END_BONE_LENGTH_HASH)
        }

        private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
        private val getJointBoneNameBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)
        }

        private const val GET_JOINT_BONE_HASH = 3175239445L
        private val getJointBoneBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_joint_bone", GET_JOINT_BONE_HASH)
        }

        private const val GET_JOINT_COUNT_HASH = 923996154L
        private val getJointCountBind by lazy {
            ObjectCalls.getMethodBind("ChainIK3D", "get_joint_count", GET_JOINT_COUNT_HASH)
        }
    }
}
