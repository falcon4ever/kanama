package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
 * A `SkeletonModifier3D` to approach the goal by repeating small rotations.
 *
 * Generated from Godot docs: IterateIK3D
 */
open class IterateIK3D(handle: MemorySegment) : ChainIK3D(handle) {
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
        ObjectCalls.ptrcallWithIntArg(setMaxIterationsBind, handle, maxIterations)
    }

    /**
     * The number of iteration loops used by the IK solver to produce more accurate results.
     *
     * Generated from Godot docs: IterateIK3D.get_max_iterations
     */
    fun getMaxIterations(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxIterationsBind, handle)
    }

    /**
     * The minimum distance between the end bone and the target. If the distance is below this value,
     * the IK solver stops any further iterations.
     *
     * Generated from Godot docs: IterateIK3D.set_min_distance
     */
    fun setMinDistance(minDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinDistanceBind, handle, minDistance)
    }

    /**
     * The minimum distance between the end bone and the target. If the distance is below this value,
     * the IK solver stops any further iterations.
     *
     * Generated from Godot docs: IterateIK3D.get_min_distance
     */
    fun getMinDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinDistanceBind, handle)
    }

    /**
     * The maximum amount each bone can rotate in a single iteration. Note: This limitation is applied
     * during each iteration. For example, if `max_iterations` is `4` and `angular_delta_limit` is `5`
     * degrees, the maximum rotation possible in a single frame is `20` degrees.
     *
     * Generated from Godot docs: IterateIK3D.set_angular_delta_limit
     */
    fun setAngularDeltaLimit(angularDeltaLimit: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularDeltaLimitBind, handle, angularDeltaLimit)
    }

    /**
     * The maximum amount each bone can rotate in a single iteration. Note: This limitation is applied
     * during each iteration. For example, if `max_iterations` is `4` and `angular_delta_limit` is `5`
     * degrees, the maximum rotation possible in a single frame is `20` degrees.
     *
     * Generated from Godot docs: IterateIK3D.get_angular_delta_limit
     */
    fun getAngularDeltaLimit(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularDeltaLimitBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setDeterministicBind, handle, deterministic)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isDeterministicBind, handle)
    }

    /**
     * Sets the target node that the end bone is trying to reach.
     *
     * Generated from Godot docs: IterateIK3D.set_target_node
     */
    fun setTargetNode(index: Int, targetNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setTargetNodeBind, handle, index, targetNode)
    }

    /**
     * Returns the target node that the end bone is trying to reach.
     *
     * Generated from Godot docs: IterateIK3D.get_target_node
     */
    fun getTargetNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getTargetNodeBind, handle, index)
    }

    /**
     * Sets the rotation axis at `joint` in the bone chain's joint list. The axes are based on the
     * reference pose's space, if `axis` is `SkeletonModifier3D.ROTATION_AXIS_CUSTOM`, you can specify
     * any axis. In here, the reference pose is the bone pose immediately before processing IK. Note:
     * The rotation axis and the forward vector shouldn't be colinear to avoid unintended rotation
     * since `ChainIK3D` does not factor in twisting forces.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_rotation_axis
     */
    fun setJointRotationAxis(index: Int, joint: Int, axis: Long) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(setJointRotationAxisBind, handle, index, joint, axis)
    }

    /**
     * Returns the rotation axis at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_rotation_axis
     */
    fun getJointRotationAxis(index: Int, joint: Int): Long {
        return ObjectCalls.ptrcallWithTwoIntArgsRetLong(getJointRotationAxisBind, handle, index, joint)
    }

    /**
     * Sets the rotation axis vector for the specified joint in the bone chain. This vector is
     * normalized by an internal process and represents the axis around which the bone chain can
     * rotate. If the vector length is `0`, it is considered synonymous with
     * `SkeletonModifier3D.ROTATION_AXIS_ALL`.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_rotation_axis_vector
     */
    fun setJointRotationAxisVector(index: Int, joint: Int, axisVector: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(setJointRotationAxisVectorBind, handle, index, joint, axisVector)
    }

    /**
     * Returns the rotation axis vector for the specified joint in the bone chain. This vector
     * represents the axis around which the joint can rotate. It is determined based on the rotation
     * axis set for the joint. If `get_joint_rotation_axis` is `SkeletonModifier3D.ROTATION_AXIS_ALL`,
     * this method returns `Vector3(0, 0, 0)`.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_rotation_axis_vector
     */
    fun getJointRotationAxisVector(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(getJointRotationAxisVectorBind, handle, index, joint)
    }

    /**
     * Sets the joint limitation at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_limitation
     */
    fun setJointLimitation(index: Int, joint: Int, limitation: JointLimitation3D?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(setJointLimitationBind, handle, index, joint, limitation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the joint limitation at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_limitation
     */
    fun getJointLimitation(index: Int, joint: Int): JointLimitation3D? {
        return JointLimitation3D.wrap(ObjectCalls.ptrcallWithTwoIntArgsRetObject(getJointLimitationBind, handle, index, joint))
    }

    /**
     * Sets the joint limitation right axis at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_limitation_right_axis
     */
    fun setJointLimitationRightAxis(index: Int, joint: Int, direction: Long) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(setJointLimitationRightAxisBind, handle, index, joint, direction)
    }

    /**
     * Returns the joint limitation right axis at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_limitation_right_axis
     */
    fun getJointLimitationRightAxis(index: Int, joint: Int): Long {
        return ObjectCalls.ptrcallWithTwoIntArgsRetLong(getJointLimitationRightAxisBind, handle, index, joint)
    }

    /**
     * Sets the optional joint limitation right axis vector at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: IterateIK3D.set_joint_limitation_right_axis_vector
     */
    fun setJointLimitationRightAxisVector(index: Int, joint: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(setJointLimitationRightAxisVectorBind, handle, index, joint, vector)
    }

    /**
     * Returns the joint limitation right axis vector at `joint` in the bone chain's joint list. If
     * `get_joint_limitation_right_axis` is `SkeletonModifier3D.SECONDARY_DIRECTION_NONE`, this method
     * returns `Vector3(0, 0, 0)`.
     *
     * Generated from Godot docs: IterateIK3D.get_joint_limitation_right_axis_vector
     */
    fun getJointLimitationRightAxisVector(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(getJointLimitationRightAxisVectorBind, handle, index, joint)
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
        ObjectCalls.ptrcallWithTwoIntAndQuaternionArg(setJointLimitationRotationOffsetBind, handle, index, joint, offset)
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
        return ObjectCalls.ptrcallWithTwoIntArgsRetQuaternion(getJointLimitationRotationOffsetBind, handle, index, joint)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): IterateIK3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): IterateIK3D? =
            if (handle.address() == 0L) null else IterateIK3D(handle)

        private const val SET_MAX_ITERATIONS_HASH = 1286410249L
        private val setMaxIterationsBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_max_iterations", SET_MAX_ITERATIONS_HASH)
        }

        private const val GET_MAX_ITERATIONS_HASH = 3905245786L
        private val getMaxIterationsBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_max_iterations", GET_MAX_ITERATIONS_HASH)
        }

        private const val SET_MIN_DISTANCE_HASH = 373806689L
        private val setMinDistanceBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_min_distance", SET_MIN_DISTANCE_HASH)
        }

        private const val GET_MIN_DISTANCE_HASH = 1740695150L
        private val getMinDistanceBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_min_distance", GET_MIN_DISTANCE_HASH)
        }

        private const val SET_ANGULAR_DELTA_LIMIT_HASH = 373806689L
        private val setAngularDeltaLimitBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_angular_delta_limit", SET_ANGULAR_DELTA_LIMIT_HASH)
        }

        private const val GET_ANGULAR_DELTA_LIMIT_HASH = 1740695150L
        private val getAngularDeltaLimitBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_angular_delta_limit", GET_ANGULAR_DELTA_LIMIT_HASH)
        }

        private const val SET_DETERMINISTIC_HASH = 2586408642L
        private val setDeterministicBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_deterministic", SET_DETERMINISTIC_HASH)
        }

        private const val IS_DETERMINISTIC_HASH = 36873697L
        private val isDeterministicBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "is_deterministic", IS_DETERMINISTIC_HASH)
        }

        private const val SET_TARGET_NODE_HASH = 2761262315L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 408788394L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_JOINT_ROTATION_AXIS_HASH = 1391134969L
        private val setJointRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_rotation_axis", SET_JOINT_ROTATION_AXIS_HASH)
        }

        private const val GET_JOINT_ROTATION_AXIS_HASH = 3312594080L
        private val getJointRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_rotation_axis", GET_JOINT_ROTATION_AXIS_HASH)
        }

        private const val SET_JOINT_ROTATION_AXIS_VECTOR_HASH = 2866752138L
        private val setJointRotationAxisVectorBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_rotation_axis_vector", SET_JOINT_ROTATION_AXIS_VECTOR_HASH)
        }

        private const val GET_JOINT_ROTATION_AXIS_VECTOR_HASH = 1592972041L
        private val getJointRotationAxisVectorBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_rotation_axis_vector", GET_JOINT_ROTATION_AXIS_VECTOR_HASH)
        }

        private const val SET_JOINT_LIMITATION_HASH = 1194636955L
        private val setJointLimitationBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_limitation", SET_JOINT_LIMITATION_HASH)
        }

        private const val GET_JOINT_LIMITATION_HASH = 91665146L
        private val getJointLimitationBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_limitation", GET_JOINT_LIMITATION_HASH)
        }

        private const val SET_JOINT_LIMITATION_RIGHT_AXIS_HASH = 3838967147L
        private val setJointLimitationRightAxisBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_limitation_right_axis", SET_JOINT_LIMITATION_RIGHT_AXIS_HASH)
        }

        private const val GET_JOINT_LIMITATION_RIGHT_AXIS_HASH = 623936134L
        private val getJointLimitationRightAxisBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_limitation_right_axis", GET_JOINT_LIMITATION_RIGHT_AXIS_HASH)
        }

        private const val SET_JOINT_LIMITATION_RIGHT_AXIS_VECTOR_HASH = 2866752138L
        private val setJointLimitationRightAxisVectorBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_limitation_right_axis_vector", SET_JOINT_LIMITATION_RIGHT_AXIS_VECTOR_HASH)
        }

        private const val GET_JOINT_LIMITATION_RIGHT_AXIS_VECTOR_HASH = 1592972041L
        private val getJointLimitationRightAxisVectorBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_limitation_right_axis_vector", GET_JOINT_LIMITATION_RIGHT_AXIS_VECTOR_HASH)
        }

        private const val SET_JOINT_LIMITATION_ROTATION_OFFSET_HASH = 4188936002L
        private val setJointLimitationRotationOffsetBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "set_joint_limitation_rotation_offset", SET_JOINT_LIMITATION_ROTATION_OFFSET_HASH)
        }

        private const val GET_JOINT_LIMITATION_ROTATION_OFFSET_HASH = 2722473700L
        private val getJointLimitationRotationOffsetBind by lazy {
            ObjectCalls.getMethodBind("IterateIK3D", "get_joint_limitation_rotation_offset", GET_JOINT_LIMITATION_ROTATION_OFFSET_HASH)
        }
    }
}
