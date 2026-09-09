package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: SkeletonModification2DFABRIK
 */
class SkeletonModification2DFABRIK(handle: MemorySegment) : SkeletonModification2D(handle) {
    var targetNodepath: NodePath
        @JvmName("targetNodepathProperty")
        get() = getTargetNode()
        @JvmName("setTargetNodepathProperty")
        set(value) = setTargetNode(value)

    var fabrikDataChainLength: Int
        @JvmName("fabrikDataChainLengthProperty")
        get() = getFabrikDataChainLength()
        @JvmName("setFabrikDataChainLengthProperty")
        set(value) = setFabrikDataChainLength(value)

    fun setTargetNode(targetNodepath: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNodepath)
    }

    fun getTargetNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    fun setFabrikDataChainLength(length: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setFabrikDataChainLengthBind, handle, length)
    }

    fun getFabrikDataChainLength(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFabrikDataChainLengthBind, handle)
    }

    fun setFabrikJointBone2dNode(jointIdx: Int, bone2dNodepath: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndNodePathArg(setFabrikJointBone2dNodeBind, handle, jointIdx, bone2dNodepath)
    }

    fun setFabrikJointBoneIndex(jointIdx: Int, boneIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(setFabrikJointBoneIndexBind, handle, jointIdx, boneIdx)
    }

    fun getFabrikJointBoneIndex(jointIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getFabrikJointBoneIndexBind, handle, jointIdx)
    }

    fun setFabrikJointMagnetPosition(jointIdx: Int, magnetPosition: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(setFabrikJointMagnetPositionBind, handle, jointIdx, magnetPosition)
    }

    fun getFabrikJointMagnetPosition(jointIdx: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(getFabrikJointMagnetPositionBind, handle, jointIdx)
    }

    fun setFabrikJointUseTargetRotation(jointIdx: Int, useTargetRotation: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(setFabrikJointUseTargetRotationBind, handle, jointIdx, useTargetRotation)
    }

    fun getFabrikJointUseTargetRotation(jointIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(getFabrikJointUseTargetRotationBind, handle, jointIdx)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): SkeletonModification2DFABRIK? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2DFABRIK? =
            if (handle.address() == 0L) null else SkeletonModification2DFABRIK(handle)

        private const val SET_TARGET_NODE_HASH = 1348162250L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 4075236667L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_FABRIK_DATA_CHAIN_LENGTH_HASH = 1286410249L
        private val setFabrikDataChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_data_chain_length", SET_FABRIK_DATA_CHAIN_LENGTH_HASH)
        }

        private const val GET_FABRIK_DATA_CHAIN_LENGTH_HASH = 2455072627L
        private val getFabrikDataChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_data_chain_length", GET_FABRIK_DATA_CHAIN_LENGTH_HASH)
        }

        private const val SET_FABRIK_JOINT_BONE2D_NODE_HASH = 2761262315L
        private val setFabrikJointBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_joint_bone2d_node", SET_FABRIK_JOINT_BONE2D_NODE_HASH)
        }

        private const val SET_FABRIK_JOINT_BONE_INDEX_HASH = 3937882851L
        private val setFabrikJointBoneIndexBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_joint_bone_index", SET_FABRIK_JOINT_BONE_INDEX_HASH)
        }

        private const val GET_FABRIK_JOINT_BONE_INDEX_HASH = 923996154L
        private val getFabrikJointBoneIndexBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_joint_bone_index", GET_FABRIK_JOINT_BONE_INDEX_HASH)
        }

        private const val SET_FABRIK_JOINT_MAGNET_POSITION_HASH = 163021252L
        private val setFabrikJointMagnetPositionBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_joint_magnet_position", SET_FABRIK_JOINT_MAGNET_POSITION_HASH)
        }

        private const val GET_FABRIK_JOINT_MAGNET_POSITION_HASH = 2299179447L
        private val getFabrikJointMagnetPositionBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_joint_magnet_position", GET_FABRIK_JOINT_MAGNET_POSITION_HASH)
        }

        private const val SET_FABRIK_JOINT_USE_TARGET_ROTATION_HASH = 300928843L
        private val setFabrikJointUseTargetRotationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_joint_use_target_rotation", SET_FABRIK_JOINT_USE_TARGET_ROTATION_HASH)
        }

        private const val GET_FABRIK_JOINT_USE_TARGET_ROTATION_HASH = 1116898809L
        private val getFabrikJointUseTargetRotationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_joint_use_target_rotation", GET_FABRIK_JOINT_USE_TARGET_ROTATION_HASH)
        }
    }
}
