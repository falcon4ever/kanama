package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Class representing a torus `PrimitiveMesh`.
 *
 * Generated from Godot docs: TorusMesh
 */
class TorusMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
    var innerRadius: Double
        @JvmName("innerRadiusProperty")
        get() = getInnerRadius()
        @JvmName("setInnerRadiusProperty")
        set(value) = setInnerRadius(value)

    var outerRadius: Double
        @JvmName("outerRadiusProperty")
        get() = getOuterRadius()
        @JvmName("setOuterRadiusProperty")
        set(value) = setOuterRadius(value)

    var rings: Int
        @JvmName("ringsProperty")
        get() = getRings()
        @JvmName("setRingsProperty")
        set(value) = setRings(value)

    var ringSegments: Int
        @JvmName("ringSegmentsProperty")
        get() = getRingSegments()
        @JvmName("setRingSegmentsProperty")
        set(value) = setRingSegments(value)

    fun setInnerRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInnerRadiusBind, handle, radius)
    }

    fun getInnerRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInnerRadiusBind, handle)
    }

    fun setOuterRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOuterRadiusBind, handle, radius)
    }

    fun getOuterRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOuterRadiusBind, handle)
    }

    fun setRings(rings: Int) {
        ObjectCalls.ptrcallWithIntArg(setRingsBind, handle, rings)
    }

    fun getRings(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRingsBind, handle)
    }

    fun setRingSegments(rings: Int) {
        ObjectCalls.ptrcallWithIntArg(setRingSegmentsBind, handle, rings)
    }

    fun getRingSegments(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRingSegmentsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TorusMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TorusMesh? =
            if (handle.address() == 0L) null else TorusMesh(handle)

        private const val SET_INNER_RADIUS_HASH = 373806689L
        private val setInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("TorusMesh", "set_inner_radius", SET_INNER_RADIUS_HASH)
        }

        private const val GET_INNER_RADIUS_HASH = 1740695150L
        private val getInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("TorusMesh", "get_inner_radius", GET_INNER_RADIUS_HASH)
        }

        private const val SET_OUTER_RADIUS_HASH = 373806689L
        private val setOuterRadiusBind by lazy {
            ObjectCalls.getMethodBind("TorusMesh", "set_outer_radius", SET_OUTER_RADIUS_HASH)
        }

        private const val GET_OUTER_RADIUS_HASH = 1740695150L
        private val getOuterRadiusBind by lazy {
            ObjectCalls.getMethodBind("TorusMesh", "get_outer_radius", GET_OUTER_RADIUS_HASH)
        }

        private const val SET_RINGS_HASH = 1286410249L
        private val setRingsBind by lazy {
            ObjectCalls.getMethodBind("TorusMesh", "set_rings", SET_RINGS_HASH)
        }

        private const val GET_RINGS_HASH = 3905245786L
        private val getRingsBind by lazy {
            ObjectCalls.getMethodBind("TorusMesh", "get_rings", GET_RINGS_HASH)
        }

        private const val SET_RING_SEGMENTS_HASH = 1286410249L
        private val setRingSegmentsBind by lazy {
            ObjectCalls.getMethodBind("TorusMesh", "set_ring_segments", SET_RING_SEGMENTS_HASH)
        }

        private const val GET_RING_SEGMENTS_HASH = 3905245786L
        private val getRingSegmentsBind by lazy {
            ObjectCalls.getMethodBind("TorusMesh", "get_ring_segments", GET_RING_SEGMENTS_HASH)
        }
    }
}
