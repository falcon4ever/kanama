package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * A modification that uses FABRIK to manipulate a series of `Bone2D` nodes to reach a target.
 *
 * Generated from Godot docs: SkeletonModification2DFABRIK
 */
class SkeletonModification2DFABRIK(handle: GodotHandle) : SkeletonModification2D(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setTargetNodeBind, segment, targetNodepath)
    }

    /**
     * The NodePath to the node that is the target for the FABRIK modification. This node is what the
     * FABRIK chain will attempt to rotate the bone chain to.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_target_node
     */
    fun getTargetNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getTargetNodeBind, segment)
    }

    /**
     * The number of FABRIK joints in the FABRIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_data_chain_length
     */
    fun setFabrikDataChainLength(length: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFabrikDataChainLengthBind, segment, length)
    }

    /**
     * The number of FABRIK joints in the FABRIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_data_chain_length
     */
    fun getFabrikDataChainLength(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFabrikDataChainLengthBind, segment)
    }

    /**
     * Sets the `Bone2D` node assigned to the FABRIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_joint_bone2d_node
     */
    fun setFabrikJointBone2dNode(jointIdx: Int, bone2dNodepath: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.setFabrikJointBone2dNodeBind, segment, jointIdx, bone2dNodepath)
    }

    /**
     * Returns the `Bone2D` node assigned to the FABRIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_joint_bone2d_node
     */
    fun getFabrikJointBone2dNode(jointIdx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getFabrikJointBone2dNodeBind, segment, jointIdx)
    }

    /**
     * Sets the bone index, `bone_idx`, of the FABRIK joint at `joint_idx`. When possible, this will
     * also update the `bone2d_node` of the FABRIK joint based on data provided by the linked skeleton.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_joint_bone_index
     */
    fun setFabrikJointBoneIndex(jointIdx: Int, boneIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setFabrikJointBoneIndexBind, segment, jointIdx, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node assigned to the FABRIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_joint_bone_index
     */
    fun getFabrikJointBoneIndex(jointIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getFabrikJointBoneIndexBind, segment, jointIdx)
    }

    /**
     * Sets the magnet position vector for the joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_joint_magnet_position
     */
    fun setFabrikJointMagnetPosition(jointIdx: Int, magnetPosition: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setFabrikJointMagnetPositionBind, segment, jointIdx, magnetPosition)
    }

    /**
     * Returns the magnet position vector for the joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_joint_magnet_position
     */
    fun getFabrikJointMagnetPosition(jointIdx: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getFabrikJointMagnetPositionBind, segment, jointIdx)
    }

    /**
     * Sets whether the joint at `joint_idx` will use the target node's rotation rather than letting
     * FABRIK rotate the node. Note: This option only works for the tip/final joint in the chain. For
     * all other nodes, this option will be ignored.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.set_fabrik_joint_use_target_rotation
     */
    fun setFabrikJointUseTargetRotation(jointIdx: Int, useTargetRotation: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setFabrikJointUseTargetRotationBind, segment, jointIdx, useTargetRotation)
    }

    /**
     * Returns whether the joint is using the target's rotation rather than allowing FABRIK to rotate
     * the joint. This option only applies to the tip/final joint in the chain.
     *
     * Generated from Godot docs: SkeletonModification2DFABRIK.get_fabrik_joint_use_target_rotation
     */
    fun getFabrikJointUseTargetRotation(jointIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getFabrikJointUseTargetRotationBind, segment, jointIdx)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonModification2DFABRIK? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SkeletonModification2DFABRIK? =
            if (handle.address() == 0L) null else RefCounted.owned(SkeletonModification2DFABRIK(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SkeletonModification2DFABRIK? =
            if (handle.address() == 0L) null else SkeletonModification2DFABRIK(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TARGET_NODE_HASH = 1348162250L
        @JvmField
        val setTargetNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_target_node", SET_TARGET_NODE_HASH)

        private const val GET_TARGET_NODE_HASH = 4075236667L
        @JvmField
        val getTargetNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_target_node", GET_TARGET_NODE_HASH)

        private const val SET_FABRIK_DATA_CHAIN_LENGTH_HASH = 1286410249L
        @JvmField
        val setFabrikDataChainLengthBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_data_chain_length", SET_FABRIK_DATA_CHAIN_LENGTH_HASH)

        private const val GET_FABRIK_DATA_CHAIN_LENGTH_HASH = 2455072627L
        @JvmField
        val getFabrikDataChainLengthBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_data_chain_length", GET_FABRIK_DATA_CHAIN_LENGTH_HASH)

        private const val SET_FABRIK_JOINT_BONE2D_NODE_HASH = 2761262315L
        @JvmField
        val setFabrikJointBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_joint_bone2d_node", SET_FABRIK_JOINT_BONE2D_NODE_HASH)

        private const val GET_FABRIK_JOINT_BONE2D_NODE_HASH = 408788394L
        @JvmField
        val getFabrikJointBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_joint_bone2d_node", GET_FABRIK_JOINT_BONE2D_NODE_HASH)

        private const val SET_FABRIK_JOINT_BONE_INDEX_HASH = 3937882851L
        @JvmField
        val setFabrikJointBoneIndexBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_joint_bone_index", SET_FABRIK_JOINT_BONE_INDEX_HASH)

        private const val GET_FABRIK_JOINT_BONE_INDEX_HASH = 923996154L
        @JvmField
        val getFabrikJointBoneIndexBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_joint_bone_index", GET_FABRIK_JOINT_BONE_INDEX_HASH)

        private const val SET_FABRIK_JOINT_MAGNET_POSITION_HASH = 163021252L
        @JvmField
        val setFabrikJointMagnetPositionBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_joint_magnet_position", SET_FABRIK_JOINT_MAGNET_POSITION_HASH)

        private const val GET_FABRIK_JOINT_MAGNET_POSITION_HASH = 2299179447L
        @JvmField
        val getFabrikJointMagnetPositionBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_joint_magnet_position", GET_FABRIK_JOINT_MAGNET_POSITION_HASH)

        private const val SET_FABRIK_JOINT_USE_TARGET_ROTATION_HASH = 300928843L
        @JvmField
        val setFabrikJointUseTargetRotationBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "set_fabrik_joint_use_target_rotation", SET_FABRIK_JOINT_USE_TARGET_ROTATION_HASH)

        private const val GET_FABRIK_JOINT_USE_TARGET_ROTATION_HASH = 1116898809L
        @JvmField
        val getFabrikJointUseTargetRotationBind =
            ObjectCalls.getMethodBind("SkeletonModification2DFABRIK", "get_fabrik_joint_use_target_rotation", GET_FABRIK_JOINT_USE_TARGET_ROTATION_HASH)
    }
}
