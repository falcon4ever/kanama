package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Class representing a capsule-shaped `PrimitiveMesh`.
 *
 * Generated from Godot docs: CapsuleMesh
 */
class CapsuleMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
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

    fun setRadialSegments(segments: Int) {
        ObjectCalls.ptrcallWithIntArg(setRadialSegmentsBind, handle, segments)
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

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CapsuleMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CapsuleMesh? =
            if (handle.address() == 0L) null else CapsuleMesh(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("CapsuleMesh", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("CapsuleMesh", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleMesh", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleMesh", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_RADIAL_SEGMENTS_HASH = 1286410249L
        private val setRadialSegmentsBind by lazy {
            ObjectCalls.getMethodBind("CapsuleMesh", "set_radial_segments", SET_RADIAL_SEGMENTS_HASH)
        }

        private const val GET_RADIAL_SEGMENTS_HASH = 3905245786L
        private val getRadialSegmentsBind by lazy {
            ObjectCalls.getMethodBind("CapsuleMesh", "get_radial_segments", GET_RADIAL_SEGMENTS_HASH)
        }

        private const val SET_RINGS_HASH = 1286410249L
        private val setRingsBind by lazy {
            ObjectCalls.getMethodBind("CapsuleMesh", "set_rings", SET_RINGS_HASH)
        }

        private const val GET_RINGS_HASH = 3905245786L
        private val getRingsBind by lazy {
            ObjectCalls.getMethodBind("CapsuleMesh", "get_rings", GET_RINGS_HASH)
        }
    }
}
