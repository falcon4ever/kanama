package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: SpotLight3D
 */
class SpotLight3D(handle: MemorySegment) : Light3D(handle) {
    var spotRange: Double
        @JvmName("spotRangeProperty")
        get() = getParam(4L)
        @JvmName("setSpotRangeProperty")
        set(value) = setParam(4L, value)

    var spotAttenuation: Double
        @JvmName("spotAttenuationProperty")
        get() = getParam(6L)
        @JvmName("setSpotAttenuationProperty")
        set(value) = setParam(6L, value)

    var spotAngle: Double
        @JvmName("spotAngleProperty")
        get() = getParam(7L)
        @JvmName("setSpotAngleProperty")
        set(value) = setParam(7L, value)

    var spotAngleAttenuation: Double
        @JvmName("spotAngleAttenuationProperty")
        get() = getParam(8L)
        @JvmName("setSpotAngleAttenuationProperty")
        set(value) = setParam(8L, value)

    // No conservative instance methods emitted yet.

    companion object {
        fun fromHandle(handle: MemorySegment): SpotLight3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpotLight3D? =
            if (handle.address() == 0L) null else SpotLight3D(handle)

        // No MethodBinds emitted yet.
    }
}
