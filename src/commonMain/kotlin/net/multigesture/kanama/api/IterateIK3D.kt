package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
 * A `SkeletonModifier3D` to approach the goal by repeating small rotations.
 *
 * Generated from Godot docs: IterateIK3D
 */
open class IterateIK3D(handle: GodotHandle) : ChainIK3D(handle) {
    var maxIterations: Int
        @JvmName("maxIterationsProperty")
        get() = getMaxIterations()
        @JvmName("setMaxIterationsProperty")
        set(value) = setMaxIterations(value)

    var minDistance: Double
        @JvmName("minDistanceProperty")
        get() = getMinDistance()
        @JvmName("setMinDistanceProperty")
        set(value) = setMinDistance(value)

    var angularDeltaLimit: Double
        @JvmName("angularDeltaLimitProperty")
        get() = getAngularDeltaLimit()
        @JvmName("setAngularDeltaLimitProperty")
        set(value) = setAngularDeltaLimit(value)

    var deterministic: Boolean
        @JvmName("deterministicProperty")
        get() = isDeterministic()
        @JvmName("setDeterministicProperty")
        set(value) = setDeterministic(value)

    /**
     * The number of iteration loops used by the IK solver to produce more accurate results.
     *
     * Generated from Godot docs: IterateIK3D.set_max_iterations
     */
    fun setMaxIterations(maxIterations: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxIterationsBind, segment, maxIterations)
    }

    /**
     * The number of iteration loops used by the IK solver to produce more accurate results.
     *
     * Generated from Godot docs: IterateIK3D.get_max_iterations
     */
    fun getMaxIterations(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxIterationsBind, segment)
    }

    /**
     * The minimum distance between the end bone and the target. If the distance is below this value,
     * the IK solver stops any further iterations.
     *
     * Generated from Godot docs: IterateIK3D.set_min_distance
     */
    fun setMinDistance(minDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMinDistanceBind, segment, minDistance)
    }

    /**
     * The minimum distance between the end bone and the target. If the distance is below this value,
     * the IK solver stops any further iterations.
     *
     * Generated from Godot docs: IterateIK3D.get_min_distance
     */
    fun getMinDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinDistanceBind, segment)
    }

    /**
     * The maximum amount each bone can rotate in a single iteration. Note: This limitation is applied
     * during each iteration. For example, if `max_iterations` is `4` and `angular_delta_limit` is `5`
     * degrees, the maximum rotation possible in a single frame is `20` degrees.
     *
     * Generated from Godot docs: IterateIK3D.set_angular_delta_limit
     */
    fun setAngularDeltaLimit(angularDeltaLimit: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAngularDeltaLimitBind, segment, angularDeltaLimit)
    }

    /**
     * The maximum amount each bone can rotate in a single iteration. Note: This limitation is applied
     * during each iteration. For example, if `max_iterations` is `4` and `angular_delta_limit` is `5`
     * degrees, the maximum rotation possible in a single frame is `20` degrees.
     *
     * Generated from Godot docs: IterateIK3D.get_angular_delta_limit
     */
    fun getAngularDeltaLimit(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAngularDeltaLimitBind, segment)
    }

    /**
     * If `false`, the result is calculated from the previous frame's `IterateIK3D` result as the
     * initial state. If `true`, the previous frame's `IterateIK3D` result is discarded. At this point,
     * the new result is calculated from the bone pose excluding the `IterateIK3D` as the initial
     * state. This means the result will be always equal as long as the target position and the
     * previous bone pose are the same. However, if `angular_delta_limit` and `max_iterations` are set
     * too small, the end bone of the chain will never reach the target.
     *
     * Generated from Godot docs: IterateIK3D.set_deterministic
     */
    fun setDeterministic(deterministic: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDeterministicBind, segment, deterministic)
    }

    /**
     * If `false`, the result is calculated from the previous frame's `IterateIK3D` result as the
     * initial state. If `true`, the previous frame's `IterateIK3D` result is discarded. At this point,
     * the new result is calculated from the bone pose excluding the `IterateIK3D` as the initial
     * state. This means the result will be always equal as long as the target position and the
     * previous bone pose are the same. However, if `angular_delta_limit` and `max_iterations` are set
     * too small, the end bone of the chain will never reach the target.
     *
     * Generated from Godot docs: IterateIK3D.is_deterministic
     */
    fun isDeterministic(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDeterministicBind, segment)
    }

    /**
     * Sets the target node that the end bone is trying to reach.
     *
     * Generated from Godot docs: IterateIK3D.set_target_node
     */
    fun setTargetNode(index: Int, targetNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.setTargetNodeBind, segment, index, targetNode)
    }

    /**
     * Returns the target node that the end bone is trying to reach.
     *
     * Generated from Godot docs: IterateIK3D.get_target_node
     */
    fun getTargetNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getTargetNodeBind, segment, index)
    }

    /**
     * Sets the rotation axis at `joint` in the bone chain's joint list. The axes are based on the
     * reference pose's space, if `axis` is `SkeletonModifier3D.RotationAxis.CUSTOM`, you can specify
     * any axis. In here, the reference pose is the bone pose immediately before processing IK. Note:
     * The rotation axis and the forward vector shouldn't be colinear to avoid unintended rotation
     * since `ChainIK3D` does not factor in twisting forces.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_rotation_axis
     */
    fun setJointRotationAxis(index: Int, joint: Int, axis: SkeletonModifier3D.RotationAxis) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(Binds.setJointRotationAxisBind, segment, index, joint, axis.value)
    }

    /**
     * Returns the rotation axis at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_rotation_axis
     */
    fun getJointRotationAxis(index: Int, joint: Int): SkeletonModifier3D.RotationAxis {
        return SkeletonModifier3D.RotationAxis(ObjectCalls.ptrcallWithTwoIntArgsRetLong(Binds.getJointRotationAxisBind, segment, index, joint))
    }

    /**
     * Sets the rotation axis vector for the specified joint in the bone chain. This vector is
     * normalized by an internal process and represents the axis around which the bone chain can
     * rotate. If the vector length is `0`, it is considered synonymous with
     * `SkeletonModifier3D.RotationAxis.ALL`.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_rotation_axis_vector
     */
    fun setJointRotationAxisVector(index: Int, joint: Int, axisVector: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(Binds.setJointRotationAxisVectorBind, segment, index, joint, axisVector)
    }

    /**
     * Returns the rotation axis vector for the specified joint in the bone chain. This vector
     * represents the axis around which the joint can rotate. It is determined based on the rotation
     * axis set for the joint. If `get_joint_rotation_axis` is `SkeletonModifier3D.RotationAxis.ALL`,
     * this method returns `Vector3(0, 0, 0)`.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_rotation_axis_vector
     */
    fun getJointRotationAxisVector(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(Binds.getJointRotationAxisVectorBind, segment, index, joint)
    }

    /**
     * Sets the joint limitation at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_limitation
     */
    fun setJointLimitation(index: Int, joint: Int, limitation: JointLimitation3D?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(Binds.setJointLimitationBind, segment, index, joint, limitation?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the joint limitation at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_limitation
     */
    fun getJointLimitation(index: Int, joint: Int): JointLimitation3D? {
        return JointLimitation3D.wrapOwned(ObjectCalls.ptrcallWithTwoIntArgsRetObject(Binds.getJointLimitationBind, segment, index, joint))
    }

    /**
     * Sets the joint limitation right axis at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_limitation_right_axis
     */
    fun setJointLimitationRightAxis(index: Int, joint: Int, direction: SkeletonModifier3D.SecondaryDirection) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(Binds.setJointLimitationRightAxisBind, segment, index, joint, direction.value)
    }

    /**
     * Returns the joint limitation right axis at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_limitation_right_axis
     */
    fun getJointLimitationRightAxis(index: Int, joint: Int): SkeletonModifier3D.SecondaryDirection {
        return SkeletonModifier3D.SecondaryDirection(ObjectCalls.ptrcallWithTwoIntArgsRetLong(Binds.getJointLimitationRightAxisBind, segment, index, joint))
    }

    /**
     * Sets the optional joint limitation right axis vector at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_limitation_right_axis_vector
     */
    fun setJointLimitationRightAxisVector(index: Int, joint: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(Binds.setJointLimitationRightAxisVectorBind, segment, index, joint, vector)
    }

    /**
     * Returns the joint limitation right axis vector at `joint` in the bone chain's joint list. If
     * `get_joint_limitation_right_axis` is `SkeletonModifier3D.SecondaryDirection.NONE`, this method
     * returns `Vector3(0, 0, 0)`.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_limitation_right_axis_vector
     */
    fun getJointLimitationRightAxisVector(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(Binds.getJointLimitationRightAxisVectorBind, segment, index, joint)
    }

    /**
     * Sets the joint limitation rotation offset at `joint` in the bone chain's joint list. Rotation is
     * done in the local space which is constructed by the bone direction (in general parent to child)
     * as the +Y axis and `get_joint_limitation_right_axis_vector` as the +X axis. If the +X and +Y
     * axes are not orthogonal, the +X axis is implicitly modified to make it orthogonal. Also, if the
     * length of `get_joint_limitation_right_axis_vector` is zero, the space is created by rotating the
     * reference pose using the shortest arc that rotates the +Y axis of the reference pose to match
     * the bone direction. In here, the reference pose is the bone pose immediately before processing
     * IK.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_limitation_rotation_offset
     */
    fun setJointLimitationRotationOffset(index: Int, joint: Int, offset: Quaternion) {
        ObjectCalls.ptrcallWithTwoIntAndQuaternionArg(Binds.setJointLimitationRotationOffsetBind, segment, index, joint, offset)
    }

    /**
     * Returns the joint limitation rotation offset at `joint` in the bone chain's joint list. Rotation
     * is done in the local space which is constructed by the bone direction (in general parent to
     * child) as the +Y axis and `get_joint_limitation_right_axis_vector` as the +X axis. If the +X and
     * +Y axes are not orthogonal, the +X axis is implicitly modified to make it orthogonal. Also, if
     * the length of `get_joint_limitation_right_axis_vector` is zero, the space is created by rotating
     * the reference pose using the shortest arc that rotates the +Y axis of the reference pose to
     * match the bone direction. In here, the reference pose is the bone pose immediately before
     * processing IK.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_limitation_rotation_offset
     */
    fun getJointLimitationRotationOffset(index: Int, joint: Int): Quaternion {
        return ObjectCalls.ptrcallWithTwoIntArgsRetQuaternion(Binds.getJointLimitationRotationOffsetBind, segment, index, joint)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): IterateIK3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): IterateIK3D? =
            if (handle.address() == 0L) null else IterateIK3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_MAX_ITERATIONS_HASH = 1286410249L
        @JvmField
        val setMaxIterationsBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_max_iterations", SET_MAX_ITERATIONS_HASH)

        private const val GET_MAX_ITERATIONS_HASH = 3905245786L
        @JvmField
        val getMaxIterationsBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_max_iterations", GET_MAX_ITERATIONS_HASH)

        private const val SET_MIN_DISTANCE_HASH = 373806689L
        @JvmField
        val setMinDistanceBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_min_distance", SET_MIN_DISTANCE_HASH)

        private const val GET_MIN_DISTANCE_HASH = 1740695150L
        @JvmField
        val getMinDistanceBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_min_distance", GET_MIN_DISTANCE_HASH)

        private const val SET_ANGULAR_DELTA_LIMIT_HASH = 373806689L
        @JvmField
        val setAngularDeltaLimitBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_angular_delta_limit", SET_ANGULAR_DELTA_LIMIT_HASH)

        private const val GET_ANGULAR_DELTA_LIMIT_HASH = 1740695150L
        @JvmField
        val getAngularDeltaLimitBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_angular_delta_limit", GET_ANGULAR_DELTA_LIMIT_HASH)

        private const val SET_DETERMINISTIC_HASH = 2586408642L
        @JvmField
        val setDeterministicBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_deterministic", SET_DETERMINISTIC_HASH)

        private const val IS_DETERMINISTIC_HASH = 36873697L
        @JvmField
        val isDeterministicBind =
            ObjectCalls.getMethodBind("IterateIK3D", "is_deterministic", IS_DETERMINISTIC_HASH)

        private const val SET_TARGET_NODE_HASH = 2761262315L
        @JvmField
        val setTargetNodeBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_target_node", SET_TARGET_NODE_HASH)

        private const val GET_TARGET_NODE_HASH = 408788394L
        @JvmField
        val getTargetNodeBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_target_node", GET_TARGET_NODE_HASH)

        private const val SET_JOINT_ROTATION_AXIS_HASH = 1391134969L
        @JvmField
        val setJointRotationAxisBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_rotation_axis", SET_JOINT_ROTATION_AXIS_HASH)

        private const val GET_JOINT_ROTATION_AXIS_HASH = 3312594080L
        @JvmField
        val getJointRotationAxisBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_rotation_axis", GET_JOINT_ROTATION_AXIS_HASH)

        private const val SET_JOINT_ROTATION_AXIS_VECTOR_HASH = 2866752138L
        @JvmField
        val setJointRotationAxisVectorBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_rotation_axis_vector", SET_JOINT_ROTATION_AXIS_VECTOR_HASH)

        private const val GET_JOINT_ROTATION_AXIS_VECTOR_HASH = 1592972041L
        @JvmField
        val getJointRotationAxisVectorBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_rotation_axis_vector", GET_JOINT_ROTATION_AXIS_VECTOR_HASH)

        private const val SET_JOINT_LIMITATION_HASH = 1194636955L
        @JvmField
        val setJointLimitationBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_limitation", SET_JOINT_LIMITATION_HASH)

        private const val GET_JOINT_LIMITATION_HASH = 91665146L
        @JvmField
        val getJointLimitationBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_limitation", GET_JOINT_LIMITATION_HASH)

        private const val SET_JOINT_LIMITATION_RIGHT_AXIS_HASH = 3838967147L
        @JvmField
        val setJointLimitationRightAxisBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_limitation_right_axis", SET_JOINT_LIMITATION_RIGHT_AXIS_HASH)

        private const val GET_JOINT_LIMITATION_RIGHT_AXIS_HASH = 623936134L
        @JvmField
        val getJointLimitationRightAxisBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_limitation_right_axis", GET_JOINT_LIMITATION_RIGHT_AXIS_HASH)

        private const val SET_JOINT_LIMITATION_RIGHT_AXIS_VECTOR_HASH = 2866752138L
        @JvmField
        val setJointLimitationRightAxisVectorBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_limitation_right_axis_vector", SET_JOINT_LIMITATION_RIGHT_AXIS_VECTOR_HASH)

        private const val GET_JOINT_LIMITATION_RIGHT_AXIS_VECTOR_HASH = 1592972041L
        @JvmField
        val getJointLimitationRightAxisVectorBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_limitation_right_axis_vector", GET_JOINT_LIMITATION_RIGHT_AXIS_VECTOR_HASH)

        private const val SET_JOINT_LIMITATION_ROTATION_OFFSET_HASH = 4188936002L
        @JvmField
        val setJointLimitationRotationOffsetBind =
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_limitation_rotation_offset", SET_JOINT_LIMITATION_ROTATION_OFFSET_HASH)

        private const val GET_JOINT_LIMITATION_ROTATION_OFFSET_HASH = 2722473700L
        @JvmField
        val getJointLimitationRotationOffsetBind =
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_limitation_rotation_offset", GET_JOINT_LIMITATION_ROTATION_OFFSET_HASH)
    }
}
