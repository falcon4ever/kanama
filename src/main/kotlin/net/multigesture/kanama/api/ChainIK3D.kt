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
    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setRootBoneNameBind, handle, index, boneName)
    }

    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
    }

    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setRootBoneBind, handle, index, bone)
    }

    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getRootBoneBind, handle, index)
    }

    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setEndBoneNameBind, handle, index, boneName)
    }

    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
    }

    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setEndBoneBind, handle, index, bone)
    }

    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getEndBoneBind, handle, index)
    }

    fun setExtendEndBone(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setExtendEndBoneBind, handle, index, enabled)
    }

    fun isEndBoneExtended(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isEndBoneExtendedBind, handle, index)
    }

    fun setEndBoneDirection(index: Int, boneDirection: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setEndBoneDirectionBind, handle, index, boneDirection)
    }

    fun getEndBoneDirection(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getEndBoneDirectionBind, handle, index)
    }

    fun setEndBoneLength(index: Int, length: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setEndBoneLengthBind, handle, index, length)
    }

    fun getEndBoneLength(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getEndBoneLengthBind, handle, index)
    }

    fun getJointBoneName(index: Int, joint: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getJointBoneNameBind, handle, index, joint)
    }

    fun getJointBone(index: Int, joint: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getJointBoneBind, handle, index, joint)
    }

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
