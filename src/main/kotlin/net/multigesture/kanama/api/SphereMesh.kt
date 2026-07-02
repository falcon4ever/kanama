package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Class representing a spherical `PrimitiveMesh`.
 *
 * Generated from Godot docs: SphereMesh
 */
class SphereMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var height: Double
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var radialSegments: Int
        @JvmName("radialSegmentsProperty")
        get() = getRadialSegments()
        @JvmName("setRadialSegmentsProperty")
        set(value) = setRadialSegments(value)

    var rings: Int
        @JvmName("ringsProperty")
        get() = getRings()
        @JvmName("setRingsProperty")
        set(value) = setRings(value)

    var isHemisphere: Boolean
        @JvmName("isHemisphereProperty")
        get() = getIsHemisphere()
        @JvmName("setIsHemisphereProperty")
        set(value) = setIsHemisphere(value)

    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    fun setRadialSegments(radialSegments: Int) {
        ObjectCalls.ptrcallWithIntArg(setRadialSegmentsBind, handle, radialSegments)
    }

    fun getRadialSegments(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRadialSegmentsBind, handle)
    }

    fun setRings(rings: Int) {
        ObjectCalls.ptrcallWithIntArg(setRingsBind, handle, rings)
    }

    fun getRings(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRingsBind, handle)
    }

    fun setIsHemisphere(isHemisphere: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIsHemisphereBind, handle, isHemisphere)
    }

    fun getIsHemisphere(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getIsHemisphereBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SphereMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SphereMesh? =
            if (handle.address() == 0L) null else SphereMesh(handle)

        @JvmStatic
        fun fromResource(value: Resource): SphereMesh? =
            if (value.isClass("SphereMesh")) SphereMesh(value.handle) else null

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_RADIAL_SEGMENTS_HASH = 1286410249L
        private val setRadialSegmentsBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "set_radial_segments", SET_RADIAL_SEGMENTS_HASH)
        }

        private const val GET_RADIAL_SEGMENTS_HASH = 3905245786L
        private val getRadialSegmentsBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "get_radial_segments", GET_RADIAL_SEGMENTS_HASH)
        }

        private const val SET_RINGS_HASH = 1286410249L
        private val setRingsBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "set_rings", SET_RINGS_HASH)
        }

        private const val GET_RINGS_HASH = 3905245786L
        private val getRingsBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "get_rings", GET_RINGS_HASH)
        }

        private const val SET_IS_HEMISPHERE_HASH = 2586408642L
        private val setIsHemisphereBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "set_is_hemisphere", SET_IS_HEMISPHERE_HASH)
        }

        private const val GET_IS_HEMISPHERE_HASH = 36873697L
        private val getIsHemisphereBind by lazy {
            ObjectCalls.getMethodBind("SphereMesh", "get_is_hemisphere", GET_IS_HEMISPHERE_HASH)
        }
    }
}
