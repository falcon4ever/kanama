package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A spotlight, such as a reflector spotlight or a lantern.
 *
 * Generated from Godot docs: SpotLight3D
 */
class SpotLight3D(handle: GodotHandle) : Light3D(handle) {
    var spotRange: Double
        @JvmName("spotRangeProperty")
        get() = getParam(Light3D.Param.RANGE)
        @JvmName("setSpotRangeProperty")
        set(value) = setParam(Light3D.Param.RANGE, value)

    var spotAttenuation: Double
        @JvmName("spotAttenuationProperty")
        get() = getParam(Light3D.Param.ATTENUATION)
        @JvmName("setSpotAttenuationProperty")
        set(value) = setParam(Light3D.Param.ATTENUATION, value)

    var spotAngle: Double
        @JvmName("spotAngleProperty")
        get() = getParam(Light3D.Param.SPOT_ANGLE)
        @JvmName("setSpotAngleProperty")
        set(value) = setParam(Light3D.Param.SPOT_ANGLE, value)

    var spotAngleAttenuation: Double
        @JvmName("spotAngleAttenuationProperty")
        get() = getParam(Light3D.Param.SPOT_ATTENUATION)
        @JvmName("setSpotAngleAttenuationProperty")
        set(value) = setParam(Light3D.Param.SPOT_ATTENUATION, value)

    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SpotLight3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SpotLight3D? =
            if (handle.address() == 0L) null else SpotLight3D(GodotHandle(handle))
    }
}
