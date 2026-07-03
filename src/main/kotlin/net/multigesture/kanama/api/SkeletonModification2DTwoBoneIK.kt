package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A modification that rotates two bones using the law of cosines to reach the target.
 *
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

    /**
     * The NodePath to the node that is the target for the TwoBoneIK modification. This node is what
     * the modification will use when bending the `Bone2D` nodes.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_target_node
     */
    fun setTargetNode(targetNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNodepath)
    }

    /**
     * The NodePath to the node that is the target for the TwoBoneIK modification. This node is what
     * the modification will use when bending the `Bone2D` nodes.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_target_node
     */
    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    /**
     * The minimum distance the target can be at. If the target is closer than this distance, the
     * modification will solve as if it's at this minimum distance. When set to `0`, the modification
     * will solve without distance constraints.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_target_minimum_distance
     */
    fun setTargetMinimumDistance(minimumDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTargetMinimumDistanceBind, handle, minimumDistance)
    }

    /**
     * The minimum distance the target can be at. If the target is closer than this distance, the
     * modification will solve as if it's at this minimum distance. When set to `0`, the modification
     * will solve without distance constraints.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_target_minimum_distance
     */
    fun getTargetMinimumDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTargetMinimumDistanceBind, handle)
    }

    /**
     * The maximum distance the target can be at. If the target is farther than this distance, the
     * modification will solve as if it's at this maximum distance. When set to `0`, the modification
     * will solve without distance constraints.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_target_maximum_distance
     */
    fun setTargetMaximumDistance(maximumDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTargetMaximumDistanceBind, handle, maximumDistance)
    }

    /**
     * The maximum distance the target can be at. If the target is farther than this distance, the
     * modification will solve as if it's at this maximum distance. When set to `0`, the modification
     * will solve without distance constraints.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_target_maximum_distance
     */
    fun getTargetMaximumDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTargetMaximumDistanceBind, handle)
    }

    /**
     * If `true`, the bones in the modification will bend outward as opposed to inwards when
     * contracting. If `false`, the bones will bend inwards when contracting.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_flip_bend_direction
     */
    fun setFlipBendDirection(flipDirection: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipBendDirectionBind, handle, flipDirection)
    }

    /**
     * If `true`, the bones in the modification will bend outward as opposed to inwards when
     * contracting. If `false`, the bones will bend inwards when contracting.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_flip_bend_direction
     */
    fun getFlipBendDirection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFlipBendDirectionBind, handle)
    }

    /**
     * Sets the `Bone2D` node that is being used as the first bone in the TwoBoneIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_joint_one_bone2d_node
     */
    fun setJointOneBone2dNode(bone2dNode: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setJointOneBone2dNodeBind, handle, bone2dNode)
    }

    /**
     * Returns the `Bone2D` node that is being used as the first bone in the TwoBoneIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_joint_one_bone2d_node
     */
    fun getJointOneBone2dNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getJointOneBone2dNodeBind, handle)
    }

    /**
     * Sets the index of the `Bone2D` node that is being used as the first bone in the TwoBoneIK
     * modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_joint_one_bone_idx
     */
    fun setJointOneBoneIdx(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(setJointOneBoneIdxBind, handle, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node that is being used as the first bone in the TwoBoneIK
     * modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_joint_one_bone_idx
     */
    fun getJointOneBoneIdx(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getJointOneBoneIdxBind, handle)
    }

    /**
     * Sets the `Bone2D` node that is being used as the second bone in the TwoBoneIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_joint_two_bone2d_node
     */
    fun setJointTwoBone2dNode(bone2dNode: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setJointTwoBone2dNodeBind, handle, bone2dNode)
    }

    /**
     * Returns the `Bone2D` node that is being used as the second bone in the TwoBoneIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_joint_two_bone2d_node
     */
    fun getJointTwoBone2dNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getJointTwoBone2dNodeBind, handle)
    }

    /**
     * Sets the index of the `Bone2D` node that is being used as the second bone in the TwoBoneIK
     * modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_joint_two_bone_idx
     */
    fun setJointTwoBoneIdx(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(setJointTwoBoneIdxBind, handle, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node that is being used as the second bone in the TwoBoneIK
     * modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_joint_two_bone_idx
     */
    fun getJointTwoBoneIdx(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getJointTwoBoneIdxBind, handle)
    }

    companion object {
        @JvmStatic
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
