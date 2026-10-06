package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A modification that rotates a `Bone2D` node to look at a target.
 *
 * Generated from Godot docs: SkeletonModification2DLookAt
 */
class SkeletonModification2DLookAt(handle: GodotHandle) : SkeletonModification2D(handle) {
    var boneIndex: Int
        @JvmName("boneIndexProperty")
        get() = getBoneIndex()
        @JvmName("setBoneIndexProperty")
        set(value) = setBoneIndex(value)

    var bone2dNode: NodePath
        @JvmName("bone2dNodeProperty")
        get() = getBone2dNode()
        @JvmName("setBone2dNodeProperty")
        set(value) = setBone2dNode(value)

    var targetNodepath: NodePath
        @JvmName("targetNodepathProperty")
        get() = getTargetNode()
        @JvmName("setTargetNodepathProperty")
        set(value) = setTargetNode(value)

    /**
     * The `Bone2D` node that the modification will operate on.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.set_bone2d_node
     */
    fun setBone2dNode(bone2dNodepath: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setBone2dNodeBind, segment, bone2dNodepath)
    }

    /**
     * The `Bone2D` node that the modification will operate on.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.get_bone2d_node
     */
    fun getBone2dNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getBone2dNodeBind, segment)
    }

    /**
     * The index of the `Bone2D` node that the modification will operate on.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.set_bone_index
     */
    fun setBoneIndex(boneIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBoneIndexBind, segment, boneIdx)
    }

    /**
     * The index of the `Bone2D` node that the modification will operate on.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.get_bone_index
     */
    fun getBoneIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBoneIndexBind, segment)
    }

    /**
     * The NodePath to the node that is the target for the LookAt modification. This node is what the
     * modification will rotate the `Bone2D` to.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.set_target_node
     */
    fun setTargetNode(targetNodepath: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setTargetNodeBind, segment, targetNodepath)
    }

    /**
     * The NodePath to the node that is the target for the LookAt modification. This node is what the
     * modification will rotate the `Bone2D` to.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.get_target_node
     */
    fun getTargetNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getTargetNodeBind, segment)
    }

    /**
     * Sets the amount of additional rotation that is to be applied after executing the modification.
     * This allows for offsetting the results by the inputted rotation amount.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.set_additional_rotation
     */
    fun setAdditionalRotation(rotation: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAdditionalRotationBind, segment, rotation)
    }

    /**
     * Returns the amount of additional rotation that is applied after the LookAt modification
     * executes.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.get_additional_rotation
     */
    fun getAdditionalRotation(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAdditionalRotationBind, segment)
    }

    /**
     * Sets whether this modification will use constraints or not. When `true`, constraints will be
     * applied when solving the LookAt modification.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.set_enable_constraint
     */
    fun setEnableConstraint(enableConstraint: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableConstraintBind, segment, enableConstraint)
    }

    /**
     * Returns `true` if the LookAt modification is using constraints.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.get_enable_constraint
     */
    fun getEnableConstraint(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableConstraintBind, segment)
    }

    /**
     * Sets the constraint's minimum allowed angle.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.set_constraint_angle_min
     */
    fun setConstraintAngleMin(angleMin: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setConstraintAngleMinBind, segment, angleMin)
    }

    /**
     * Returns the constraint's minimum allowed angle.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.get_constraint_angle_min
     */
    fun getConstraintAngleMin(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getConstraintAngleMinBind, segment)
    }

    /**
     * Sets the constraint's maximum allowed angle.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.set_constraint_angle_max
     */
    fun setConstraintAngleMax(angleMax: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setConstraintAngleMaxBind, segment, angleMax)
    }

    /**
     * Returns the constraint's maximum allowed angle.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.get_constraint_angle_max
     */
    fun getConstraintAngleMax(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getConstraintAngleMaxBind, segment)
    }

    /**
     * When `true`, the modification will use an inverted joint constraint. An inverted joint
     * constraint only constraints the `Bone2D` to the angles outside of the inputted minimum and
     * maximum angles. For this reason, it is referred to as an inverted joint constraint, as it
     * constraints the joint to the outside of the inputted values.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.set_constraint_angle_invert
     */
    fun setConstraintAngleInvert(invert: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setConstraintAngleInvertBind, segment, invert)
    }

    /**
     * Returns whether the constraints to this modification are inverted or not.
     *
     * Generated from Godot docs: SkeletonModification2DLookAt.get_constraint_angle_invert
     */
    fun getConstraintAngleInvert(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getConstraintAngleInvertBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonModification2DLookAt? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SkeletonModification2DLookAt? =
            if (handle.address() == 0L) null else RefCounted.owned(SkeletonModification2DLookAt(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SkeletonModification2DLookAt? =
            if (handle.address() == 0L) null else SkeletonModification2DLookAt(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BONE2D_NODE_HASH = 1348162250L
        @JvmField
        val setBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_bone2d_node", SET_BONE2D_NODE_HASH)

        private const val GET_BONE2D_NODE_HASH = 4075236667L
        @JvmField
        val getBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_bone2d_node", GET_BONE2D_NODE_HASH)

        private const val SET_BONE_INDEX_HASH = 1286410249L
        @JvmField
        val setBoneIndexBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_bone_index", SET_BONE_INDEX_HASH)

        private const val GET_BONE_INDEX_HASH = 3905245786L
        @JvmField
        val getBoneIndexBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_bone_index", GET_BONE_INDEX_HASH)

        private const val SET_TARGET_NODE_HASH = 1348162250L
        @JvmField
        val setTargetNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_target_node", SET_TARGET_NODE_HASH)

        private const val GET_TARGET_NODE_HASH = 4075236667L
        @JvmField
        val getTargetNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_target_node", GET_TARGET_NODE_HASH)

        private const val SET_ADDITIONAL_ROTATION_HASH = 373806689L
        @JvmField
        val setAdditionalRotationBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_additional_rotation", SET_ADDITIONAL_ROTATION_HASH)

        private const val GET_ADDITIONAL_ROTATION_HASH = 1740695150L
        @JvmField
        val getAdditionalRotationBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_additional_rotation", GET_ADDITIONAL_ROTATION_HASH)

        private const val SET_ENABLE_CONSTRAINT_HASH = 2586408642L
        @JvmField
        val setEnableConstraintBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_enable_constraint", SET_ENABLE_CONSTRAINT_HASH)

        private const val GET_ENABLE_CONSTRAINT_HASH = 36873697L
        @JvmField
        val getEnableConstraintBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_enable_constraint", GET_ENABLE_CONSTRAINT_HASH)

        private const val SET_CONSTRAINT_ANGLE_MIN_HASH = 373806689L
        @JvmField
        val setConstraintAngleMinBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_constraint_angle_min", SET_CONSTRAINT_ANGLE_MIN_HASH)

        private const val GET_CONSTRAINT_ANGLE_MIN_HASH = 1740695150L
        @JvmField
        val getConstraintAngleMinBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_constraint_angle_min", GET_CONSTRAINT_ANGLE_MIN_HASH)

        private const val SET_CONSTRAINT_ANGLE_MAX_HASH = 373806689L
        @JvmField
        val setConstraintAngleMaxBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_constraint_angle_max", SET_CONSTRAINT_ANGLE_MAX_HASH)

        private const val GET_CONSTRAINT_ANGLE_MAX_HASH = 1740695150L
        @JvmField
        val getConstraintAngleMaxBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_constraint_angle_max", GET_CONSTRAINT_ANGLE_MAX_HASH)

        private const val SET_CONSTRAINT_ANGLE_INVERT_HASH = 2586408642L
        @JvmField
        val setConstraintAngleInvertBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_constraint_angle_invert", SET_CONSTRAINT_ANGLE_INVERT_HASH)

        private const val GET_CONSTRAINT_ANGLE_INVERT_HASH = 36873697L
        @JvmField
        val getConstraintAngleInvertBind =
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_constraint_angle_invert", GET_CONSTRAINT_ANGLE_INVERT_HASH)
    }
}
