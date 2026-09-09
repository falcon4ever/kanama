package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: SkeletonModification2DTwoBoneIK
 */
class SkeletonModification2DTwoBoneIK(handle: MemorySegment) : SkeletonModification2D(handle) {
    var targetNodepath: NodePath
        @JvmName("targetNodepathProperty")
        get() = getTargetNode()
        @JvmName("setTargetNodepathProperty")
        set(value) = setTargetNode(value)

    var targetMinimumDistance: Double
        @JvmName("targetMinimumDistanceProperty")
        get() = getTargetMinimumDistance()
        @JvmName("setTargetMinimumDistanceProperty")
        set(value) = setTargetMinimumDistance(value)

    var targetMaximumDistance: Double
        @JvmName("targetMaximumDistanceProperty")
        get() = getTargetMaximumDistance()
        @JvmName("setTargetMaximumDistanceProperty")
        set(value) = setTargetMaximumDistance(value)

    var flipBendDirection: Boolean
        @JvmName("flipBendDirectionProperty")
        get() = getFlipBendDirection()
        @JvmName("setFlipBendDirectionProperty")
        set(value) = setFlipBendDirection(value)

    fun setTargetNode(targetNodepath: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNodepath)
    }

    fun getTargetNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    fun setTargetMinimumDistance(minimumDistance: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setTargetMinimumDistanceBind, handle, minimumDistance)
    }

    fun getTargetMinimumDistance(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getTargetMinimumDistanceBind, handle)
    }

    fun setTargetMaximumDistance(maximumDistance: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setTargetMaximumDistanceBind, handle, maximumDistance)
    }

    fun getTargetMaximumDistance(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getTargetMaximumDistanceBind, handle)
    }

    fun setFlipBendDirection(flipDirection: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setFlipBendDirectionBind, handle, flipDirection)
    }

    fun getFlipBendDirection(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getFlipBendDirectionBind, handle)
    }

    fun setJointOneBone2dNode(bone2dNode: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(setJointOneBone2dNodeBind, handle, bone2dNode)
    }

    fun getJointOneBone2dNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(getJointOneBone2dNodeBind, handle)
    }

    fun setJointOneBoneIdx(boneIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setJointOneBoneIdxBind, handle, boneIdx)
    }

    fun getJointOneBoneIdx(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getJointOneBoneIdxBind, handle)
    }

    fun setJointTwoBone2dNode(bone2dNode: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(setJointTwoBone2dNodeBind, handle, bone2dNode)
    }

    fun getJointTwoBone2dNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(getJointTwoBone2dNodeBind, handle)
    }

    fun setJointTwoBoneIdx(boneIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setJointTwoBoneIdxBind, handle, boneIdx)
    }

    fun getJointTwoBoneIdx(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getJointTwoBoneIdxBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): SkeletonModification2DTwoBoneIK? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2DTwoBoneIK? =
            if (handle.address() == 0L) null else SkeletonModification2DTwoBoneIK(handle)

        private const val SET_TARGET_NODE_HASH = 1348162250L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 4075236667L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_TARGET_MINIMUM_DISTANCE_HASH = 373806689L
        private val setTargetMinimumDistanceBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_target_minimum_distance", SET_TARGET_MINIMUM_DISTANCE_HASH)
        }

        private const val GET_TARGET_MINIMUM_DISTANCE_HASH = 1740695150L
        private val getTargetMinimumDistanceBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_target_minimum_distance", GET_TARGET_MINIMUM_DISTANCE_HASH)
        }

        private const val SET_TARGET_MAXIMUM_DISTANCE_HASH = 373806689L
        private val setTargetMaximumDistanceBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_target_maximum_distance", SET_TARGET_MAXIMUM_DISTANCE_HASH)
        }

        private const val GET_TARGET_MAXIMUM_DISTANCE_HASH = 1740695150L
        private val getTargetMaximumDistanceBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_target_maximum_distance", GET_TARGET_MAXIMUM_DISTANCE_HASH)
        }

        private const val SET_FLIP_BEND_DIRECTION_HASH = 2586408642L
        private val setFlipBendDirectionBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_flip_bend_direction", SET_FLIP_BEND_DIRECTION_HASH)
        }

        private const val GET_FLIP_BEND_DIRECTION_HASH = 36873697L
        private val getFlipBendDirectionBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_flip_bend_direction", GET_FLIP_BEND_DIRECTION_HASH)
        }

        private const val SET_JOINT_ONE_BONE2D_NODE_HASH = 1348162250L
        private val setJointOneBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_joint_one_bone2d_node", SET_JOINT_ONE_BONE2D_NODE_HASH)
        }

        private const val GET_JOINT_ONE_BONE2D_NODE_HASH = 4075236667L
        private val getJointOneBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_joint_one_bone2d_node", GET_JOINT_ONE_BONE2D_NODE_HASH)
        }

        private const val SET_JOINT_ONE_BONE_IDX_HASH = 1286410249L
        private val setJointOneBoneIdxBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_joint_one_bone_idx", SET_JOINT_ONE_BONE_IDX_HASH)
        }

        private const val GET_JOINT_ONE_BONE_IDX_HASH = 3905245786L
        private val getJointOneBoneIdxBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_joint_one_bone_idx", GET_JOINT_ONE_BONE_IDX_HASH)
        }

        private const val SET_JOINT_TWO_BONE2D_NODE_HASH = 1348162250L
        private val setJointTwoBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_joint_two_bone2d_node", SET_JOINT_TWO_BONE2D_NODE_HASH)
        }

        private const val GET_JOINT_TWO_BONE2D_NODE_HASH = 4075236667L
        private val getJointTwoBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_joint_two_bone2d_node", GET_JOINT_TWO_BONE2D_NODE_HASH)
        }

        private const val SET_JOINT_TWO_BONE_IDX_HASH = 1286410249L
        private val setJointTwoBoneIdxBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_joint_two_bone_idx", SET_JOINT_TWO_BONE_IDX_HASH)
        }

        private const val GET_JOINT_TWO_BONE_IDX_HASH = 3905245786L
        private val getJointTwoBoneIdxBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_joint_two_bone_idx", GET_JOINT_TWO_BONE_IDX_HASH)
        }
    }
}
