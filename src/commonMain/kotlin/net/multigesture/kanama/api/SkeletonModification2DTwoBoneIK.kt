package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A modification that rotates two bones using the law of cosines to reach the target.
 *
 * Generated from Godot docs: SkeletonModification2DTwoBoneIK
 */
class SkeletonModification2DTwoBoneIK(handle: GodotHandle) : SkeletonModification2D(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setTargetNodeBind, segment, targetNodepath)
    }

    /**
     * The NodePath to the node that is the target for the TwoBoneIK modification. This node is what
     * the modification will use when bending the `Bone2D` nodes.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_target_node
     */
    fun getTargetNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getTargetNodeBind, segment)
    }

    /**
     * The minimum distance the target can be at. If the target is closer than this distance, the
     * modification will solve as if it's at this minimum distance. When set to `0`, the modification
     * will solve without distance constraints.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_target_minimum_distance
     */
    fun setTargetMinimumDistance(minimumDistance: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTargetMinimumDistanceBind, segment, minimumDistance)
    }

    /**
     * The minimum distance the target can be at. If the target is closer than this distance, the
     * modification will solve as if it's at this minimum distance. When set to `0`, the modification
     * will solve without distance constraints.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_target_minimum_distance
     */
    fun getTargetMinimumDistance(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTargetMinimumDistanceBind, segment)
    }

    /**
     * The maximum distance the target can be at. If the target is farther than this distance, the
     * modification will solve as if it's at this maximum distance. When set to `0`, the modification
     * will solve without distance constraints.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_target_maximum_distance
     */
    fun setTargetMaximumDistance(maximumDistance: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTargetMaximumDistanceBind, segment, maximumDistance)
    }

    /**
     * The maximum distance the target can be at. If the target is farther than this distance, the
     * modification will solve as if it's at this maximum distance. When set to `0`, the modification
     * will solve without distance constraints.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_target_maximum_distance
     */
    fun getTargetMaximumDistance(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTargetMaximumDistanceBind, segment)
    }

    /**
     * If `true`, the bones in the modification will bend outward as opposed to inwards when
     * contracting. If `false`, the bones will bend inwards when contracting.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_flip_bend_direction
     */
    fun setFlipBendDirection(flipDirection: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlipBendDirectionBind, segment, flipDirection)
    }

    /**
     * If `true`, the bones in the modification will bend outward as opposed to inwards when
     * contracting. If `false`, the bones will bend inwards when contracting.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_flip_bend_direction
     */
    fun getFlipBendDirection(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFlipBendDirectionBind, segment)
    }

    /**
     * Sets the `Bone2D` node that is being used as the first bone in the TwoBoneIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_joint_one_bone2d_node
     */
    fun setJointOneBone2dNode(bone2dNode: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setJointOneBone2dNodeBind, segment, bone2dNode)
    }

    /**
     * Returns the `Bone2D` node that is being used as the first bone in the TwoBoneIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_joint_one_bone2d_node
     */
    fun getJointOneBone2dNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getJointOneBone2dNodeBind, segment)
    }

    /**
     * Sets the index of the `Bone2D` node that is being used as the first bone in the TwoBoneIK
     * modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_joint_one_bone_idx
     */
    fun setJointOneBoneIdx(boneIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setJointOneBoneIdxBind, segment, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node that is being used as the first bone in the TwoBoneIK
     * modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_joint_one_bone_idx
     */
    fun getJointOneBoneIdx(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getJointOneBoneIdxBind, segment)
    }

    /**
     * Sets the `Bone2D` node that is being used as the second bone in the TwoBoneIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_joint_two_bone2d_node
     */
    fun setJointTwoBone2dNode(bone2dNode: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setJointTwoBone2dNodeBind, segment, bone2dNode)
    }

    /**
     * Returns the `Bone2D` node that is being used as the second bone in the TwoBoneIK modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_joint_two_bone2d_node
     */
    fun getJointTwoBone2dNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getJointTwoBone2dNodeBind, segment)
    }

    /**
     * Sets the index of the `Bone2D` node that is being used as the second bone in the TwoBoneIK
     * modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.set_joint_two_bone_idx
     */
    fun setJointTwoBoneIdx(boneIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setJointTwoBoneIdxBind, segment, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node that is being used as the second bone in the TwoBoneIK
     * modification.
     *
     * Generated from Godot docs: SkeletonModification2DTwoBoneIK.get_joint_two_bone_idx
     */
    fun getJointTwoBoneIdx(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getJointTwoBoneIdxBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonModification2DTwoBoneIK? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SkeletonModification2DTwoBoneIK? =
            if (handle.address() == 0L) null else RefCounted.owned(SkeletonModification2DTwoBoneIK(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SkeletonModification2DTwoBoneIK? =
            if (handle.address() == 0L) null else SkeletonModification2DTwoBoneIK(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TARGET_NODE_HASH = 1348162250L
        @JvmField
        val setTargetNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_target_node", SET_TARGET_NODE_HASH)

        private const val GET_TARGET_NODE_HASH = 4075236667L
        @JvmField
        val getTargetNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_target_node", GET_TARGET_NODE_HASH)

        private const val SET_TARGET_MINIMUM_DISTANCE_HASH = 373806689L
        @JvmField
        val setTargetMinimumDistanceBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_target_minimum_distance", SET_TARGET_MINIMUM_DISTANCE_HASH)

        private const val GET_TARGET_MINIMUM_DISTANCE_HASH = 1740695150L
        @JvmField
        val getTargetMinimumDistanceBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_target_minimum_distance", GET_TARGET_MINIMUM_DISTANCE_HASH)

        private const val SET_TARGET_MAXIMUM_DISTANCE_HASH = 373806689L
        @JvmField
        val setTargetMaximumDistanceBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_target_maximum_distance", SET_TARGET_MAXIMUM_DISTANCE_HASH)

        private const val GET_TARGET_MAXIMUM_DISTANCE_HASH = 1740695150L
        @JvmField
        val getTargetMaximumDistanceBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_target_maximum_distance", GET_TARGET_MAXIMUM_DISTANCE_HASH)

        private const val SET_FLIP_BEND_DIRECTION_HASH = 2586408642L
        @JvmField
        val setFlipBendDirectionBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_flip_bend_direction", SET_FLIP_BEND_DIRECTION_HASH)

        private const val GET_FLIP_BEND_DIRECTION_HASH = 36873697L
        @JvmField
        val getFlipBendDirectionBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_flip_bend_direction", GET_FLIP_BEND_DIRECTION_HASH)

        private const val SET_JOINT_ONE_BONE2D_NODE_HASH = 1348162250L
        @JvmField
        val setJointOneBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_joint_one_bone2d_node", SET_JOINT_ONE_BONE2D_NODE_HASH)

        private const val GET_JOINT_ONE_BONE2D_NODE_HASH = 4075236667L
        @JvmField
        val getJointOneBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_joint_one_bone2d_node", GET_JOINT_ONE_BONE2D_NODE_HASH)

        private const val SET_JOINT_ONE_BONE_IDX_HASH = 1286410249L
        @JvmField
        val setJointOneBoneIdxBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_joint_one_bone_idx", SET_JOINT_ONE_BONE_IDX_HASH)

        private const val GET_JOINT_ONE_BONE_IDX_HASH = 3905245786L
        @JvmField
        val getJointOneBoneIdxBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_joint_one_bone_idx", GET_JOINT_ONE_BONE_IDX_HASH)

        private const val SET_JOINT_TWO_BONE2D_NODE_HASH = 1348162250L
        @JvmField
        val setJointTwoBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_joint_two_bone2d_node", SET_JOINT_TWO_BONE2D_NODE_HASH)

        private const val GET_JOINT_TWO_BONE2D_NODE_HASH = 4075236667L
        @JvmField
        val getJointTwoBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_joint_two_bone2d_node", GET_JOINT_TWO_BONE2D_NODE_HASH)

        private const val SET_JOINT_TWO_BONE_IDX_HASH = 1286410249L
        @JvmField
        val setJointTwoBoneIdxBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "set_joint_two_bone_idx", SET_JOINT_TWO_BONE_IDX_HASH)

        private const val GET_JOINT_TWO_BONE_IDX_HASH = 3905245786L
        @JvmField
        val getJointTwoBoneIdxBind =
            ObjectCalls.getMethodBind("SkeletonModification2DTwoBoneIK", "get_joint_two_bone_idx", GET_JOINT_TWO_BONE_IDX_HASH)
    }
}
