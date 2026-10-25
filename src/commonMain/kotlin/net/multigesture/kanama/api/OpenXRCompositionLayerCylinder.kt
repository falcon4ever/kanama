package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An OpenXR composition layer that is rendered as an internal slice of a cylinder.
 *
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

    /**
     * The radius of the cylinder.
     *
     * Generated from Godot docs: OpenXRCompositionLayerCylinder.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * The radius of the cylinder.
     *
     * Generated from Godot docs: OpenXRCompositionLayerCylinder.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * The aspect ratio of the slice. Used to set the height relative to the width.
     *
     * Generated from Godot docs: OpenXRCompositionLayerCylinder.set_aspect_ratio
     */
    fun setAspectRatio(aspectRatio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAspectRatioBind, segment, aspectRatio)
    }

    /**
     * The aspect ratio of the slice. Used to set the height relative to the width.
     *
     * Generated from Godot docs: OpenXRCompositionLayerCylinder.get_aspect_ratio
     */
    fun getAspectRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAspectRatioBind, segment)
    }

    /**
     * The central angle of the cylinder. Used to set the width.
     *
     * Generated from Godot docs: OpenXRCompositionLayerCylinder.set_central_angle
     */
    fun setCentralAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCentralAngleBind, segment, angle)
    }

    /**
     * The central angle of the cylinder. Used to set the width.
     *
     * Generated from Godot docs: OpenXRCompositionLayerCylinder.get_central_angle
     */
    fun getCentralAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCentralAngleBind, segment)
    }

    /**
     * The number of segments to use in the fallback mesh.
     *
     * Generated from Godot docs: OpenXRCompositionLayerCylinder.set_fallback_segments
     */
    fun setFallbackSegments(segments: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setFallbackSegmentsBind, segment, segments)
    }

    /**
     * The number of segments to use in the fallback mesh.
     *
     * Generated from Godot docs: OpenXRCompositionLayerCylinder.get_fallback_segments
     */
    fun getFallbackSegments(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getFallbackSegmentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRCompositionLayerCylinder? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRCompositionLayerCylinder? =
            if (handle.address() == 0L) null else OpenXRCompositionLayerCylinder(GodotHandle(handle))

        /** A new OpenXRCompositionLayerCylinder, GDScript's `OpenXRCompositionLayerCylinder.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): OpenXRCompositionLayerCylinder =
            OpenXRCompositionLayerCylinder(GodotHandle(ObjectCalls.constructObject("OpenXRCompositionLayerCylinder")))
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
