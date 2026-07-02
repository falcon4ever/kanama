package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A modification that uses CCDIK to manipulate a series of bones to reach a target in 2D.
 *
 * Generated from Godot docs: SkeletonModification2DCCDIK
 */
class SkeletonModification2DCCDIK(handle: MemorySegment) : SkeletonModification2D(handle) {
    var targetNodepath: NodePath
        @JvmName("targetNodepathProperty")
        get() = getTargetNode()
        @JvmName("setTargetNodepathProperty")
        set(value) = setTargetNode(value)

    var tipNodepath: NodePath
        @JvmName("tipNodepathProperty")
        get() = getTipNode()
        @JvmName("setTipNodepathProperty")
        set(value) = setTipNode(value)

    var ccdikDataChainLength: Int
        @JvmName("ccdikDataChainLengthProperty")
        get() = getCcdikDataChainLength()
        @JvmName("setCcdikDataChainLengthProperty")
        set(value) = setCcdikDataChainLength(value)

    fun setTargetNode(targetNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNodepath)
    }

    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    fun setTipNode(tipNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTipNodeBind, handle, tipNodepath)
    }

    fun getTipNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTipNodeBind, handle)
    }

    fun setCcdikDataChainLength(length: Int) {
        ObjectCalls.ptrcallWithIntArg(setCcdikDataChainLengthBind, handle, length)
    }

    fun getCcdikDataChainLength(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCcdikDataChainLengthBind, handle)
    }

    fun setCcdikJointBone2dNode(jointIdx: Int, bone2dNodepath: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setCcdikJointBone2dNodeBind, handle, jointIdx, bone2dNodepath)
    }

    fun getCcdikJointBone2dNode(jointIdx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getCcdikJointBone2dNodeBind, handle, jointIdx)
    }

    fun setCcdikJointBoneIndex(jointIdx: Int, boneIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setCcdikJointBoneIndexBind, handle, jointIdx, boneIdx)
    }

    fun getCcdikJointBoneIndex(jointIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCcdikJointBoneIndexBind, handle, jointIdx)
    }

    fun setCcdikJointRotateFromJoint(jointIdx: Int, rotateFromJoint: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCcdikJointRotateFromJointBind, handle, jointIdx, rotateFromJoint)
    }

    fun getCcdikJointRotateFromJoint(jointIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCcdikJointRotateFromJointBind, handle, jointIdx)
    }

    fun setCcdikJointEnableConstraint(jointIdx: Int, enableConstraint: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCcdikJointEnableConstraintBind, handle, jointIdx, enableConstraint)
    }

    fun getCcdikJointEnableConstraint(jointIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCcdikJointEnableConstraintBind, handle, jointIdx)
    }

    fun setCcdikJointConstraintAngleMin(jointIdx: Int, angleMin: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setCcdikJointConstraintAngleMinBind, handle, jointIdx, angleMin)
    }

    fun getCcdikJointConstraintAngleMin(jointIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getCcdikJointConstraintAngleMinBind, handle, jointIdx)
    }

    fun setCcdikJointConstraintAngleMax(jointIdx: Int, angleMax: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setCcdikJointConstraintAngleMaxBind, handle, jointIdx, angleMax)
    }

    fun getCcdikJointConstraintAngleMax(jointIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getCcdikJointConstraintAngleMaxBind, handle, jointIdx)
    }

    fun setCcdikJointConstraintAngleInvert(jointIdx: Int, invert: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCcdikJointConstraintAngleInvertBind, handle, jointIdx, invert)
    }

    fun getCcdikJointConstraintAngleInvert(jointIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCcdikJointConstraintAngleInvertBind, handle, jointIdx)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonModification2DCCDIK? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2DCCDIK? =
            if (handle.address() == 0L) null else SkeletonModification2DCCDIK(handle)

        private const val SET_TARGET_NODE_HASH = 1348162250L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 4075236667L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_TIP_NODE_HASH = 1348162250L
        private val setTipNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_tip_node", SET_TIP_NODE_HASH)
        }

        private const val GET_TIP_NODE_HASH = 4075236667L
        private val getTipNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_tip_node", GET_TIP_NODE_HASH)
        }

        private const val SET_CCDIK_DATA_CHAIN_LENGTH_HASH = 1286410249L
        private val setCcdikDataChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_ccdik_data_chain_length", SET_CCDIK_DATA_CHAIN_LENGTH_HASH)
        }

        private const val GET_CCDIK_DATA_CHAIN_LENGTH_HASH = 2455072627L
        private val getCcdikDataChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_ccdik_data_chain_length", GET_CCDIK_DATA_CHAIN_LENGTH_HASH)
        }

        private const val SET_CCDIK_JOINT_BONE2D_NODE_HASH = 2761262315L
        private val setCcdikJointBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_ccdik_joint_bone2d_node", SET_CCDIK_JOINT_BONE2D_NODE_HASH)
        }

        private const val GET_CCDIK_JOINT_BONE2D_NODE_HASH = 408788394L
        private val getCcdikJointBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_ccdik_joint_bone2d_node", GET_CCDIK_JOINT_BONE2D_NODE_HASH)
        }

        private const val SET_CCDIK_JOINT_BONE_INDEX_HASH = 3937882851L
        private val setCcdikJointBoneIndexBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_ccdik_joint_bone_index", SET_CCDIK_JOINT_BONE_INDEX_HASH)
        }

        private const val GET_CCDIK_JOINT_BONE_INDEX_HASH = 923996154L
        private val getCcdikJointBoneIndexBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_ccdik_joint_bone_index", GET_CCDIK_JOINT_BONE_INDEX_HASH)
        }

        private const val SET_CCDIK_JOINT_ROTATE_FROM_JOINT_HASH = 300928843L
        private val setCcdikJointRotateFromJointBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_ccdik_joint_rotate_from_joint", SET_CCDIK_JOINT_ROTATE_FROM_JOINT_HASH)
        }

        private const val GET_CCDIK_JOINT_ROTATE_FROM_JOINT_HASH = 1116898809L
        private val getCcdikJointRotateFromJointBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_ccdik_joint_rotate_from_joint", GET_CCDIK_JOINT_ROTATE_FROM_JOINT_HASH)
        }

        private const val SET_CCDIK_JOINT_ENABLE_CONSTRAINT_HASH = 300928843L
        private val setCcdikJointEnableConstraintBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_ccdik_joint_enable_constraint", SET_CCDIK_JOINT_ENABLE_CONSTRAINT_HASH)
        }

        private const val GET_CCDIK_JOINT_ENABLE_CONSTRAINT_HASH = 1116898809L
        private val getCcdikJointEnableConstraintBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_ccdik_joint_enable_constraint", GET_CCDIK_JOINT_ENABLE_CONSTRAINT_HASH)
        }

        private const val SET_CCDIK_JOINT_CONSTRAINT_ANGLE_MIN_HASH = 1602489585L
        private val setCcdikJointConstraintAngleMinBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_ccdik_joint_constraint_angle_min", SET_CCDIK_JOINT_CONSTRAINT_ANGLE_MIN_HASH)
        }

        private const val GET_CCDIK_JOINT_CONSTRAINT_ANGLE_MIN_HASH = 2339986948L
        private val getCcdikJointConstraintAngleMinBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_ccdik_joint_constraint_angle_min", GET_CCDIK_JOINT_CONSTRAINT_ANGLE_MIN_HASH)
        }

        private const val SET_CCDIK_JOINT_CONSTRAINT_ANGLE_MAX_HASH = 1602489585L
        private val setCcdikJointConstraintAngleMaxBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_ccdik_joint_constraint_angle_max", SET_CCDIK_JOINT_CONSTRAINT_ANGLE_MAX_HASH)
        }

        private const val GET_CCDIK_JOINT_CONSTRAINT_ANGLE_MAX_HASH = 2339986948L
        private val getCcdikJointConstraintAngleMaxBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_ccdik_joint_constraint_angle_max", GET_CCDIK_JOINT_CONSTRAINT_ANGLE_MAX_HASH)
        }

        private const val SET_CCDIK_JOINT_CONSTRAINT_ANGLE_INVERT_HASH = 300928843L
        private val setCcdikJointConstraintAngleInvertBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "set_ccdik_joint_constraint_angle_invert", SET_CCDIK_JOINT_CONSTRAINT_ANGLE_INVERT_HASH)
        }

        private const val GET_CCDIK_JOINT_CONSTRAINT_ANGLE_INVERT_HASH = 1116898809L
        private val getCcdikJointConstraintAngleInvertBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DCCDIK", "get_ccdik_joint_constraint_angle_invert", GET_CCDIK_JOINT_CONSTRAINT_ANGLE_INVERT_HASH)
        }
    }
}
