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

    /**
     * The NodePath to the node that is the target for the CCDIK modification. This node is what the
     * CCDIK chain will attempt to rotate the bone chain to.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_target_node
     */
    fun setTargetNode(targetNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNodepath)
    }

    /**
     * The NodePath to the node that is the target for the CCDIK modification. This node is what the
     * CCDIK chain will attempt to rotate the bone chain to.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_target_node
     */
    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    /**
     * The end position of the CCDIK chain. Typically, this should be a child of a `Bone2D` node
     * attached to the final `Bone2D` in the CCDIK chain.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_tip_node
     */
    fun setTipNode(tipNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTipNodeBind, handle, tipNodepath)
    }

    /**
     * The end position of the CCDIK chain. Typically, this should be a child of a `Bone2D` node
     * attached to the final `Bone2D` in the CCDIK chain.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_tip_node
     */
    fun getTipNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTipNodeBind, handle)
    }

    /**
     * The number of CCDIK joints in the CCDIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_ccdik_data_chain_length
     */
    fun setCcdikDataChainLength(length: Int) {
        ObjectCalls.ptrcallWithIntArg(setCcdikDataChainLengthBind, handle, length)
    }

    /**
     * The number of CCDIK joints in the CCDIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_ccdik_data_chain_length
     */
    fun getCcdikDataChainLength(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCcdikDataChainLengthBind, handle)
    }

    /**
     * Sets the `Bone2D` node assigned to the CCDIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_ccdik_joint_bone2d_node
     */
    fun setCcdikJointBone2dNode(jointIdx: Int, bone2dNodepath: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setCcdikJointBone2dNodeBind, handle, jointIdx, bone2dNodepath)
    }

    /**
     * Returns the `Bone2D` node assigned to the CCDIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_ccdik_joint_bone2d_node
     */
    fun getCcdikJointBone2dNode(jointIdx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getCcdikJointBone2dNodeBind, handle, jointIdx)
    }

    /**
     * Sets the bone index, `bone_idx`, of the CCDIK joint at `joint_idx`. When possible, this will
     * also update the `bone2d_node` of the CCDIK joint based on data provided by the linked skeleton.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_ccdik_joint_bone_index
     */
    fun setCcdikJointBoneIndex(jointIdx: Int, boneIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setCcdikJointBoneIndexBind, handle, jointIdx, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node assigned to the CCDIK joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_ccdik_joint_bone_index
     */
    fun getCcdikJointBoneIndex(jointIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCcdikJointBoneIndexBind, handle, jointIdx)
    }

    /**
     * Sets whether the joint at `joint_idx` is set to rotate from the joint, `true`, or to rotate from
     * the tip, `false`.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_ccdik_joint_rotate_from_joint
     */
    fun setCcdikJointRotateFromJoint(jointIdx: Int, rotateFromJoint: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCcdikJointRotateFromJointBind, handle, jointIdx, rotateFromJoint)
    }

    /**
     * Returns whether the joint at `joint_idx` is set to rotate from the joint, `true`, or to rotate
     * from the tip, `false`. The default is to rotate from the tip.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_ccdik_joint_rotate_from_joint
     */
    fun getCcdikJointRotateFromJoint(jointIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCcdikJointRotateFromJointBind, handle, jointIdx)
    }

    /**
     * Determines whether angle constraints on the CCDIK joint at `joint_idx` are enabled. When `true`,
     * constraints will be enabled and taken into account when solving.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_ccdik_joint_enable_constraint
     */
    fun setCcdikJointEnableConstraint(jointIdx: Int, enableConstraint: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCcdikJointEnableConstraintBind, handle, jointIdx, enableConstraint)
    }

    /**
     * Returns whether angle constraints on the CCDIK joint at `joint_idx` are enabled.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_ccdik_joint_enable_constraint
     */
    fun getCcdikJointEnableConstraint(jointIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCcdikJointEnableConstraintBind, handle, jointIdx)
    }

    /**
     * Sets the minimum angle constraint for the joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_ccdik_joint_constraint_angle_min
     */
    fun setCcdikJointConstraintAngleMin(jointIdx: Int, angleMin: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setCcdikJointConstraintAngleMinBind, handle, jointIdx, angleMin)
    }

    /**
     * Returns the minimum angle constraint for the joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_ccdik_joint_constraint_angle_min
     */
    fun getCcdikJointConstraintAngleMin(jointIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getCcdikJointConstraintAngleMinBind, handle, jointIdx)
    }

    /**
     * Sets the maximum angle constraint for the joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_ccdik_joint_constraint_angle_max
     */
    fun setCcdikJointConstraintAngleMax(jointIdx: Int, angleMax: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setCcdikJointConstraintAngleMaxBind, handle, jointIdx, angleMax)
    }

    /**
     * Returns the maximum angle constraint for the joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_ccdik_joint_constraint_angle_max
     */
    fun getCcdikJointConstraintAngleMax(jointIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getCcdikJointConstraintAngleMaxBind, handle, jointIdx)
    }

    /**
     * Sets whether the CCDIK joint at `joint_idx` uses an inverted joint constraint. An inverted joint
     * constraint only constraints the CCDIK joint to the angles outside of the inputted minimum and
     * maximum angles. For this reason, it is referred to as an inverted joint constraint, as it
     * constraints the joint to the outside of the inputted values.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.set_ccdik_joint_constraint_angle_invert
     */
    fun setCcdikJointConstraintAngleInvert(jointIdx: Int, invert: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCcdikJointConstraintAngleInvertBind, handle, jointIdx, invert)
    }

    /**
     * Returns whether the CCDIK joint at `joint_idx` uses an inverted joint constraint. See
     * `set_ccdik_joint_constraint_angle_invert` for details.
     *
     * Generated from Godot docs: SkeletonModification2DCCDIK.get_ccdik_joint_constraint_angle_invert
     */
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
