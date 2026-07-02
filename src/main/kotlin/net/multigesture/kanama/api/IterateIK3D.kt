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

    fun setMaxIterations(maxIterations: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxIterationsBind, handle, maxIterations)
    }

    fun getMaxIterations(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxIterationsBind, handle)
    }

    fun setMinDistance(minDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinDistanceBind, handle, minDistance)
    }

    fun getMinDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinDistanceBind, handle)
    }

    fun setAngularDeltaLimit(angularDeltaLimit: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularDeltaLimitBind, handle, angularDeltaLimit)
    }

    fun getAngularDeltaLimit(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularDeltaLimitBind, handle)
    }

    fun setDeterministic(deterministic: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeterministicBind, handle, deterministic)
    }

    fun isDeterministic(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeterministicBind, handle)
    }

    fun setTargetNode(index: Int, targetNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setTargetNodeBind, handle, index, targetNode)
    }

    fun getTargetNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getTargetNodeBind, handle, index)
    }

    fun setJointRotationAxis(index: Int, joint: Int, axis: Long) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(setJointRotationAxisBind, handle, index, joint, axis)
    }

    fun getJointRotationAxis(index: Int, joint: Int): Long {
        return ObjectCalls.ptrcallWithTwoIntArgsRetLong(getJointRotationAxisBind, handle, index, joint)
    }

    fun setJointRotationAxisVector(index: Int, joint: Int, axisVector: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(setJointRotationAxisVectorBind, handle, index, joint, axisVector)
    }

    fun getJointRotationAxisVector(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(getJointRotationAxisVectorBind, handle, index, joint)
    }

    fun setJointLimitation(index: Int, joint: Int, limitation: JointLimitation3D?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(setJointLimitationBind, handle, index, joint, limitation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getJointLimitation(index: Int, joint: Int): JointLimitation3D? {
        return JointLimitation3D.wrap(ObjectCalls.ptrcallWithTwoIntArgsRetObject(getJointLimitationBind, handle, index, joint))
    }

    fun setJointLimitationRightAxis(index: Int, joint: Int, direction: Long) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(setJointLimitationRightAxisBind, handle, index, joint, direction)
    }

    fun getJointLimitationRightAxis(index: Int, joint: Int): Long {
        return ObjectCalls.ptrcallWithTwoIntArgsRetLong(getJointLimitationRightAxisBind, handle, index, joint)
    }

    fun setJointLimitationRightAxisVector(index: Int, joint: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(setJointLimitationRightAxisVectorBind, handle, index, joint, vector)
    }

    fun getJointLimitationRightAxisVector(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(getJointLimitationRightAxisVectorBind, handle, index, joint)
    }

    fun setJointLimitationRotationOffset(index: Int, joint: Int, offset: Quaternion) {
        ObjectCalls.ptrcallWithTwoIntAndQuaternionArg(setJointLimitationRotationOffsetBind, handle, index, joint, offset)
    }

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
