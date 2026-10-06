package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A cone shape limitation that interacts with `ChainIK3D`.
 *
 * Generated from Godot docs: JointLimitationCone3D
 */
class JointLimitationCone3D(handle: GodotHandle) : JointLimitation3D(handle) {
    var angle: Double
        @JvmName("angleProperty")
        get() = getAngle()
        @JvmName("setAngleProperty")
        set(value) = setAngle(value)

    /**
     * The radius range of the hole made by the cone. `0` degrees makes a sphere without hole, `180`
     * degrees makes a hemisphere, and `360` degrees become empty (no limitation).
     *
     * Generated from Godot docs: JointLimitationCone3D.set_angle
     */
    fun setAngle(angle: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAngleBind, segment, angle)
    }

    /**
     * The radius range of the hole made by the cone. `0` degrees makes a sphere without hole, `180`
     * degrees makes a hemisphere, and `360` degrees become empty (no limitation).
     *
     * Generated from Godot docs: JointLimitationCone3D.get_angle
     */
    fun getAngle(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAngleBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): JointLimitationCone3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): JointLimitationCone3D? =
            if (handle.address() == 0L) null else RefCounted.owned(JointLimitationCone3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): JointLimitationCone3D? =
            if (handle.address() == 0L) null else JointLimitationCone3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ANGLE_HASH = 373806689L
        @JvmField
        val setAngleBind =
            ObjectCalls.getMethodBind("JointLimitationCone3D", "set_angle", SET_ANGLE_HASH)

        private const val GET_ANGLE_HASH = 1740695150L
        @JvmField
        val getAngleBind =
            ObjectCalls.getMethodBind("JointLimitationCone3D", "get_angle", GET_ANGLE_HASH)
    }
}
