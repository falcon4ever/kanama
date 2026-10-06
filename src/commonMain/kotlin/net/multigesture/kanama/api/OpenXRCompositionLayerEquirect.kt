package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRCompositionLayerEquirect
 */
class OpenXRCompositionLayerEquirect(handle: GodotHandle) : OpenXRCompositionLayer(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    fun setCentralHorizontalAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCentralHorizontalAngleBind, segment, angle)
    }

    fun getCentralHorizontalAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCentralHorizontalAngleBind, segment)
    }

    fun setUpperVerticalAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setUpperVerticalAngleBind, segment, angle)
    }

    fun getUpperVerticalAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getUpperVerticalAngleBind, segment)
    }

    fun setLowerVerticalAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLowerVerticalAngleBind, segment, angle)
    }

    fun getLowerVerticalAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLowerVerticalAngleBind, segment)
    }

    fun setFallbackSegments(segments: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setFallbackSegmentsBind, segment, segments)
    }

    fun getFallbackSegments(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getFallbackSegmentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRCompositionLayerEquirect? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRCompositionLayerEquirect? =
            if (handle.address() == 0L) null else OpenXRCompositionLayerEquirect(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_radius", GET_RADIUS_HASH)

        private const val SET_CENTRAL_HORIZONTAL_ANGLE_HASH = 373806689L
        @JvmField
        val setCentralHorizontalAngleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_central_horizontal_angle", SET_CENTRAL_HORIZONTAL_ANGLE_HASH)

        private const val GET_CENTRAL_HORIZONTAL_ANGLE_HASH = 1740695150L
        @JvmField
        val getCentralHorizontalAngleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_central_horizontal_angle", GET_CENTRAL_HORIZONTAL_ANGLE_HASH)

        private const val SET_UPPER_VERTICAL_ANGLE_HASH = 373806689L
        @JvmField
        val setUpperVerticalAngleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_upper_vertical_angle", SET_UPPER_VERTICAL_ANGLE_HASH)

        private const val GET_UPPER_VERTICAL_ANGLE_HASH = 1740695150L
        @JvmField
        val getUpperVerticalAngleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_upper_vertical_angle", GET_UPPER_VERTICAL_ANGLE_HASH)

        private const val SET_LOWER_VERTICAL_ANGLE_HASH = 373806689L
        @JvmField
        val setLowerVerticalAngleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_lower_vertical_angle", SET_LOWER_VERTICAL_ANGLE_HASH)

        private const val GET_LOWER_VERTICAL_ANGLE_HASH = 1740695150L
        @JvmField
        val getLowerVerticalAngleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_lower_vertical_angle", GET_LOWER_VERTICAL_ANGLE_HASH)

        private const val SET_FALLBACK_SEGMENTS_HASH = 1286410249L
        @JvmField
        val setFallbackSegmentsBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "set_fallback_segments", SET_FALLBACK_SEGMENTS_HASH)

        private const val GET_FALLBACK_SEGMENTS_HASH = 3905245786L
        @JvmField
        val getFallbackSegmentsBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerEquirect", "get_fallback_segments", GET_FALLBACK_SEGMENTS_HASH)
    }
}
