package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector3

/**
 * Rotation based intersection of two circles inverse kinematics solver.
 *
 * Generated from Godot docs: TwoBoneIK3D
 */
class TwoBoneIK3D(handle: MemorySegment) : IKModifier3D(handle) {
    fun setTargetNode(index: Int, targetNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setTargetNodeBind, handle, index, targetNode)
    }

    fun getTargetNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getTargetNodeBind, handle, index)
    }

    fun setPoleNode(index: Int, poleNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setPoleNodeBind, handle, index, poleNode)
    }

    fun getPoleNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getPoleNodeBind, handle, index)
    }

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

    fun setMiddleBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setMiddleBoneNameBind, handle, index, boneName)
    }

    fun getMiddleBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getMiddleBoneNameBind, handle, index)
    }

    fun setMiddleBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setMiddleBoneBind, handle, index, bone)
    }

    fun getMiddleBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getMiddleBoneBind, handle, index)
    }

    fun setPoleDirection(index: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setPoleDirectionBind, handle, index, direction)
    }

    fun getPoleDirection(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getPoleDirectionBind, handle, index)
    }

    fun setPoleDirectionVector(index: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setPoleDirectionVectorBind, handle, index, vector)
    }

    fun getPoleDirectionVector(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getPoleDirectionVectorBind, handle, index)
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

    fun setUseVirtualEnd(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setUseVirtualEndBind, handle, index, enabled)
    }

    fun isUsingVirtualEnd(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isUsingVirtualEndBind, handle, index)
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

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TwoBoneIK3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TwoBoneIK3D? =
            if (handle.address() == 0L) null else TwoBoneIK3D(handle)

        private const val SET_TARGET_NODE_HASH = 2761262315L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 408788394L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_POLE_NODE_HASH = 2761262315L
        private val setPoleNodeBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_pole_node", SET_POLE_NODE_HASH)
        }

        private const val GET_POLE_NODE_HASH = 408788394L
        private val getPoleNodeBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_pole_node", GET_POLE_NODE_HASH)
        }

        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        private val setRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)
        }

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        private val getRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
        }

        private const val SET_ROOT_BONE_HASH = 3937882851L
        private val setRootBoneBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_root_bone", SET_ROOT_BONE_HASH)
        }

        private const val GET_ROOT_BONE_HASH = 923996154L
        private val getRootBoneBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_root_bone", GET_ROOT_BONE_HASH)
        }

        private const val SET_MIDDLE_BONE_NAME_HASH = 501894301L
        private val setMiddleBoneNameBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_middle_bone_name", SET_MIDDLE_BONE_NAME_HASH)
        }

        private const val GET_MIDDLE_BONE_NAME_HASH = 844755477L
        private val getMiddleBoneNameBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_middle_bone_name", GET_MIDDLE_BONE_NAME_HASH)
        }

        private const val SET_MIDDLE_BONE_HASH = 3937882851L
        private val setMiddleBoneBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_middle_bone", SET_MIDDLE_BONE_HASH)
        }

        private const val GET_MIDDLE_BONE_HASH = 923996154L
        private val getMiddleBoneBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_middle_bone", GET_MIDDLE_BONE_HASH)
        }

        private const val SET_POLE_DIRECTION_HASH = 258741388L
        private val setPoleDirectionBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_pole_direction", SET_POLE_DIRECTION_HASH)
        }

        private const val GET_POLE_DIRECTION_HASH = 377522128L
        private val getPoleDirectionBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_pole_direction", GET_POLE_DIRECTION_HASH)
        }

        private const val SET_POLE_DIRECTION_VECTOR_HASH = 1530502735L
        private val setPoleDirectionVectorBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_pole_direction_vector", SET_POLE_DIRECTION_VECTOR_HASH)
        }

        private const val GET_POLE_DIRECTION_VECTOR_HASH = 711720468L
        private val getPoleDirectionVectorBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_pole_direction_vector", GET_POLE_DIRECTION_VECTOR_HASH)
        }

        private const val SET_END_BONE_NAME_HASH = 501894301L
        private val setEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)
        }

        private const val GET_END_BONE_NAME_HASH = 844755477L
        private val getEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
        }

        private const val SET_END_BONE_HASH = 3937882851L
        private val setEndBoneBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_end_bone", SET_END_BONE_HASH)
        }

        private const val GET_END_BONE_HASH = 923996154L
        private val getEndBoneBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone", GET_END_BONE_HASH)
        }

        private const val SET_USE_VIRTUAL_END_HASH = 300928843L
        private val setUseVirtualEndBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_use_virtual_end", SET_USE_VIRTUAL_END_HASH)
        }

        private const val IS_USING_VIRTUAL_END_HASH = 1116898809L
        private val isUsingVirtualEndBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "is_using_virtual_end", IS_USING_VIRTUAL_END_HASH)
        }

        private const val SET_EXTEND_END_BONE_HASH = 300928843L
        private val setExtendEndBoneBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_extend_end_bone", SET_EXTEND_END_BONE_HASH)
        }

        private const val IS_END_BONE_EXTENDED_HASH = 1116898809L
        private val isEndBoneExtendedBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "is_end_bone_extended", IS_END_BONE_EXTENDED_HASH)
        }

        private const val SET_END_BONE_DIRECTION_HASH = 2838484201L
        private val setEndBoneDirectionBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_end_bone_direction", SET_END_BONE_DIRECTION_HASH)
        }

        private const val GET_END_BONE_DIRECTION_HASH = 1843036459L
        private val getEndBoneDirectionBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone_direction", GET_END_BONE_DIRECTION_HASH)
        }

        private const val SET_END_BONE_LENGTH_HASH = 1602489585L
        private val setEndBoneLengthBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_end_bone_length", SET_END_BONE_LENGTH_HASH)
        }

        private const val GET_END_BONE_LENGTH_HASH = 2339986948L
        private val getEndBoneLengthBind by lazy {
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone_length", GET_END_BONE_LENGTH_HASH)
        }
    }
}
