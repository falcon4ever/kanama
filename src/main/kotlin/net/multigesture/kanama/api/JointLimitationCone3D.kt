package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A cone shape limitation that interacts with `ChainIK3D`.
 *
 * Generated from Godot docs: JointLimitationCone3D
 */
class JointLimitationCone3D(handle: MemorySegment) : JointLimitation3D(handle) {
    var angle: Double
        @JvmName("angleProperty")
        get() = getAngle()
        @JvmName("setAngleProperty")
        set(value) = setAngle(value)

    fun setAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngleBind, handle, angle)
    }

    fun getAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngleBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): JointLimitationCone3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JointLimitationCone3D? =
            if (handle.address() == 0L) null else JointLimitationCone3D(handle)

        private const val SET_ANGLE_HASH = 373806689L
        private val setAngleBind by lazy {
            ObjectCalls.getMethodBind("JointLimitationCone3D", "set_angle", SET_ANGLE_HASH)
        }

        private const val GET_ANGLE_HASH = 1740695150L
        private val getAngleBind by lazy {
            ObjectCalls.getMethodBind("JointLimitationCone3D", "get_angle", GET_ANGLE_HASH)
        }
    }
}
