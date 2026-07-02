package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRCompositionLayerEquirect
 */
class OpenXRCompositionLayerEquirect(handle: MemorySegment) : OpenXRCompositionLayer(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var centralHorizontalAngle: Double
        @JvmName("centralHorizontalAngleProperty")
        get() = getCentralHorizontalAngle()
        @JvmName("setCentralHorizontalAngleProperty")
        set(value) = setCentralHorizontalAngle(value)

    var upperVerticalAngle: Double
        @JvmName("upperVerticalAngleProperty")
        get() = getUpperVerticalAngle()
        @JvmName("setUpperVerticalAngleProperty")
        set(value) = setUpperVerticalAngle(value)

    var lowerVerticalAngle: Double
        @JvmName("lowerVerticalAngleProperty")
        get() = getLowerVerticalAngle()
        @JvmName("setLowerVerticalAngleProperty")
        set(value) = setLowerVerticalAngle(value)

    var fallbackSegments: Long
        @JvmName("fallbackSegmentsProperty")
        get() = getFallbackSegments()
        @JvmName("setFallbackSegmentsProperty")
        set(value) = setFallbackSegments(value)

    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setCentralHorizontalAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCentralHorizontalAngleBind, handle, angle)
    }

    fun getCentralHorizontalAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCentralHorizontalAngleBind, handle)
    }

    fun setUpperVerticalAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setUpperVerticalAngleBind, handle, angle)
    }

    fun getUpperVerticalAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUpperVerticalAngleBind, handle)
    }

    fun setLowerVerticalAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLowerVerticalAngleBind, handle, angle)
    }

    fun getLowerVerticalAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLowerVerticalAngleBind, handle)
    }

    fun setFallbackSegments(segments: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setFallbackSegmentsBind, handle, segments)
    }

    fun getFallbackSegments(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFallbackSegmentsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRCompositionLayerEquirect? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRCompositionLayerEquirect? =
            if (handle.address() == 0L) null else OpenXRCompositionLayerEquirect(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_CENTRAL_HORIZONTAL_ANGLE_HASH = 373806689L
        private val setCentralHorizontalAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_central_horizontal_angle", SET_CENTRAL_HORIZONTAL_ANGLE_HASH)
        }

        private const val GET_CENTRAL_HORIZONTAL_ANGLE_HASH = 1740695150L
        private val getCentralHorizontalAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_central_horizontal_angle", GET_CENTRAL_HORIZONTAL_ANGLE_HASH)
        }

        private const val SET_UPPER_VERTICAL_ANGLE_HASH = 373806689L
        private val setUpperVerticalAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_upper_vertical_angle", SET_UPPER_VERTICAL_ANGLE_HASH)
        }

        private const val GET_UPPER_VERTICAL_ANGLE_HASH = 1740695150L
        private val getUpperVerticalAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_upper_vertical_angle", GET_UPPER_VERTICAL_ANGLE_HASH)
        }

        private const val SET_LOWER_VERTICAL_ANGLE_HASH = 373806689L
        private val setLowerVerticalAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_lower_vertical_angle", SET_LOWER_VERTICAL_ANGLE_HASH)
        }

        private const val GET_LOWER_VERTICAL_ANGLE_HASH = 1740695150L
        private val getLowerVerticalAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_lower_vertical_angle", GET_LOWER_VERTICAL_ANGLE_HASH)
        }

        private const val SET_FALLBACK_SEGMENTS_HASH = 1286410249L
        private val setFallbackSegmentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_fallback_segments", SET_FALLBACK_SEGMENTS_HASH)
        }

        private const val GET_FALLBACK_SEGMENTS_HASH = 3905245786L
        private val getFallbackSegmentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_fallback_segments", GET_FALLBACK_SEGMENTS_HASH)
        }
    }
}
