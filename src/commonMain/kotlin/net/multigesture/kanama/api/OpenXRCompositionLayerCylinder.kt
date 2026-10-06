package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRCompositionLayerCylinder
 */
class OpenXRCompositionLayerCylinder(handle: GodotHandle) : OpenXRCompositionLayer(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var aspectRatio: Double
        @JvmName("aspectRatioProperty")
        get() = getAspectRatio()
        @JvmName("setAspectRatioProperty")
        set(value) = setAspectRatio(value)

    var centralAngle: Double
        @JvmName("centralAngleProperty")
        get() = getCentralAngle()
        @JvmName("setCentralAngleProperty")
        set(value) = setCentralAngle(value)

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

    fun setAspectRatio(aspectRatio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAspectRatioBind, segment, aspectRatio)
    }

    fun getAspectRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAspectRatioBind, segment)
    }

    fun setCentralAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCentralAngleBind, segment, angle)
    }

    fun getCentralAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCentralAngleBind, segment)
    }

    fun setFallbackSegments(segments: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setFallbackSegmentsBind, segment, segments)
    }

    fun getFallbackSegments(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getFallbackSegmentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRCompositionLayerCylinder? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRCompositionLayerCylinder? =
            if (handle.address() == 0L) null else OpenXRCompositionLayerCylinder(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "get_radius", GET_RADIUS_HASH)

        private const val SET_ASPECT_RATIO_HASH = 373806689L
        @JvmField
        val setAspectRatioBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "set_aspect_ratio", SET_ASPECT_RATIO_HASH)

        private const val GET_ASPECT_RATIO_HASH = 1740695150L
        @JvmField
        val getAspectRatioBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "get_aspect_ratio", GET_ASPECT_RATIO_HASH)

        private const val SET_CENTRAL_ANGLE_HASH = 373806689L
        @JvmField
        val setCentralAngleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "set_central_angle", SET_CENTRAL_ANGLE_HASH)

        private const val GET_CENTRAL_ANGLE_HASH = 1740695150L
        @JvmField
        val getCentralAngleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "get_central_angle", GET_CENTRAL_ANGLE_HASH)

        private const val SET_FALLBACK_SEGMENTS_HASH = 1286410249L
        @JvmField
        val setFallbackSegmentsBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "set_fallback_segments", SET_FALLBACK_SEGMENTS_HASH)

        private const val GET_FALLBACK_SEGMENTS_HASH = 3905245786L
        @JvmField
        val getFallbackSegmentsBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "get_fallback_segments", GET_FALLBACK_SEGMENTS_HASH)
    }
}
