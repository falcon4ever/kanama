package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * A modification that uses FABRIK to manipulate a series of `Bone2D` nodes to reach a target.
 *
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

    /**
     * The NodePath to the node that is the target for the FABRIK modification. This node is what the
     * FABRIK chain will attempt to rotate the bone chain to.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_target_node
     */
    fun setTargetNode(targetNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNodepath)
    }

    /**
     * The NodePath to the node that is the target for the FABRIK modification. This node is what the
     * FABRIK chain will attempt to rotate the bone chain to.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_target_node
     */
    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    /**
     * The number of FABRIK joints in the FABRIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_data_chain_length
     */
    fun setFabrikDataChainLength(length: Int) {
        ObjectCalls.ptrcallWithIntArg(setFabrikDataChainLengthBind, handle, length)
    }

    /**
     * The number of FABRIK joints in the FABRIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_data_chain_length
     */
    fun getFabrikDataChainLength(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFabrikDataChainLengthBind, handle)
    }

    /**
     * Sets the `Bone2D` node assigned to the FABRIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_joint_bone2d_node
     */
    fun setFabrikJointBone2dNode(jointIdx: Int, bone2dNodepath: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setFabrikJointBone2dNodeBind, handle, jointIdx, bone2dNodepath)
    }

    /**
     * Returns the `Bone2D` node assigned to the FABRIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_joint_bone2d_node
     */
    fun getFabrikJointBone2dNode(jointIdx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getFabrikJointBone2dNodeBind, handle, jointIdx)
    }

    /**
     * Sets the bone index, `bone_idx`, of the FABRIK joint at `joint_idx`. When possible, this will
     * also update the `bone2d_node` of the FABRIK joint based on data provided by the linked skeleton.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_joint_bone_index
     */
    fun setFabrikJointBoneIndex(jointIdx: Int, boneIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setFabrikJointBoneIndexBind, handle, jointIdx, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node assigned to the FABRIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_joint_bone_index
     */
    fun getFabrikJointBoneIndex(jointIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getFabrikJointBoneIndexBind, handle, jointIdx)
    }

    /**
     * Sets the magnet position vector for the joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_joint_magnet_position
     */
    fun setFabrikJointMagnetPosition(jointIdx: Int, magnetPosition: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setFabrikJointMagnetPositionBind, handle, jointIdx, magnetPosition)
    }

    /**
     * Returns the magnet position vector for the joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_joint_magnet_position
     */
    fun getFabrikJointMagnetPosition(jointIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getFabrikJointMagnetPositionBind, handle, jointIdx)
    }

    /**
     * Sets whether the joint at `joint_idx` will use the target node's rotation rather than letting
     * FABRIK rotate the node. Note: This option only works for the tip/final joint in the chain. For
     * all other nodes, this option will be ignored.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_joint_use_target_rotation
     */
    fun setFabrikJointUseTargetRotation(jointIdx: Int, useTargetRotation: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setFabrikJointUseTargetRotationBind, handle, jointIdx, useTargetRotation)
    }

    /**
     * Returns whether the joint is using the target's rotation rather than allowing FABRIK to rotate
     * the joint. This option only applies to the tip/final joint in the chain.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_joint_use_target_rotation
     */
    fun getFabrikJointUseTargetRotation(jointIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getFabrikJointUseTargetRotationBind, handle, jointIdx)
    }

    companion object {
        @JvmStatic
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

        private const val GET_FABRIK_JOINT_BONE2D_NODE_HASH = 408788394L
        private val getFabrikJointBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_joint_bone2d_node", GET_FABRIK_JOINT_BONE2D_NODE_HASH)
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
