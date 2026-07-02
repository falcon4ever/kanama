package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A physics joint that connects two 2D physics bodies with a spring-like force.
 *
 * Generated from Godot docs: DampedSpringJoint2D
 */
class DampedSpringJoint2D(handle: MemorySegment) : Joint2D(handle) {
    var length: Double
        @JvmName("lengthProperty")
        get() = getLength()
        @JvmName("setLengthProperty")
        set(value) = setLength(value)

    var restLength: Double
        @JvmName("restLengthProperty")
        get() = getRestLength()
        @JvmName("setRestLengthProperty")
        set(value) = setRestLength(value)

    var stiffness: Double
        @JvmName("stiffnessProperty")
        get() = getStiffness()
        @JvmName("setStiffnessProperty")
        set(value) = setStiffness(value)

    var damping: Double
        @JvmName("dampingProperty")
        get() = getDamping()
        @JvmName("setDampingProperty")
        set(value) = setDamping(value)

    fun setLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLengthBind, handle, length)
    }

    fun getLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, handle)
    }

    fun setRestLength(restLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRestLengthBind, handle, restLength)
    }

    fun getRestLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRestLengthBind, handle)
    }

    fun setStiffness(stiffness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStiffnessBind, handle, stiffness)
    }

    fun getStiffness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStiffnessBind, handle)
    }

    fun setDamping(damping: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDampingBind, handle, damping)
    }

    fun getDamping(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDampingBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): DampedSpringJoint2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): DampedSpringJoint2D? =
            if (handle.address() == 0L) null else DampedSpringJoint2D(handle)

        private const val SET_LENGTH_HASH = 373806689L
        private val setLengthBind by lazy {
            ObjectCalls.getMethodBind("DampedSpringJoint2D", "set_length", SET_LENGTH_HASH)
        }

        private const val GET_LENGTH_HASH = 1740695150L
        private val getLengthBind by lazy {
            ObjectCalls.getMethodBind("DampedSpringJoint2D", "get_length", GET_LENGTH_HASH)
        }

        private const val SET_REST_LENGTH_HASH = 373806689L
        private val setRestLengthBind by lazy {
            ObjectCalls.getMethodBind("DampedSpringJoint2D", "set_rest_length", SET_REST_LENGTH_HASH)
        }

        private const val GET_REST_LENGTH_HASH = 1740695150L
        private val getRestLengthBind by lazy {
            ObjectCalls.getMethodBind("DampedSpringJoint2D", "get_rest_length", GET_REST_LENGTH_HASH)
        }

        private const val SET_STIFFNESS_HASH = 373806689L
        private val setStiffnessBind by lazy {
            ObjectCalls.getMethodBind("DampedSpringJoint2D", "set_stiffness", SET_STIFFNESS_HASH)
        }

        private const val GET_STIFFNESS_HASH = 1740695150L
        private val getStiffnessBind by lazy {
            ObjectCalls.getMethodBind("DampedSpringJoint2D", "get_stiffness", GET_STIFFNESS_HASH)
        }

        private const val SET_DAMPING_HASH = 373806689L
        private val setDampingBind by lazy {
            ObjectCalls.getMethodBind("DampedSpringJoint2D", "set_damping", SET_DAMPING_HASH)
        }

        private const val GET_DAMPING_HASH = 1740695150L
        private val getDampingBind by lazy {
            ObjectCalls.getMethodBind("DampedSpringJoint2D", "get_damping", GET_DAMPING_HASH)
        }
    }
}
