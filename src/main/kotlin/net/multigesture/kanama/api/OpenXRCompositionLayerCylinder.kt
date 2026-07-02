package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRCompositionLayerCylinder
 */
class OpenXRCompositionLayerCylinder(handle: MemorySegment) : OpenXRCompositionLayer(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setAspectRatio(aspectRatio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAspectRatioBind, handle, aspectRatio)
    }

    fun getAspectRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAspectRatioBind, handle)
    }

    fun setCentralAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCentralAngleBind, handle, angle)
    }

    fun getCentralAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCentralAngleBind, handle)
    }

    fun setFallbackSegments(segments: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setFallbackSegmentsBind, handle, segments)
    }

    fun getFallbackSegments(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFallbackSegmentsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRCompositionLayerCylinder? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRCompositionLayerCylinder? =
            if (handle.address() == 0L) null else OpenXRCompositionLayerCylinder(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_ASPECT_RATIO_HASH = 373806689L
        private val setAspectRatioBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "set_aspect_ratio", SET_ASPECT_RATIO_HASH)
        }

        private const val GET_ASPECT_RATIO_HASH = 1740695150L
        private val getAspectRatioBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "get_aspect_ratio", GET_ASPECT_RATIO_HASH)
        }

        private const val SET_CENTRAL_ANGLE_HASH = 373806689L
        private val setCentralAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "set_central_angle", SET_CENTRAL_ANGLE_HASH)
        }

        private const val GET_CENTRAL_ANGLE_HASH = 1740695150L
        private val getCentralAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "get_central_angle", GET_CENTRAL_ANGLE_HASH)
        }

        private const val SET_FALLBACK_SEGMENTS_HASH = 1286410249L
        private val setFallbackSegmentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "set_fallback_segments", SET_FALLBACK_SEGMENTS_HASH)
        }

        private const val GET_FALLBACK_SEGMENTS_HASH = 3905245786L
        private val getFallbackSegmentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerCylinder", "get_fallback_segments", GET_FALLBACK_SEGMENTS_HASH)
        }
    }
}
