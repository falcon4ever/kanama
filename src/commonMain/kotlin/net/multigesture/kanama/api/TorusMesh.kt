package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Class representing a torus `PrimitiveMesh`.
 *
 * Generated from Godot docs: TorusMesh
 */
class TorusMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
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

    /**
     * The inner radius of the torus.
     *
     * Generated from Godot docs: TorusMesh.set_inner_radius
     */
    fun setInnerRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setInnerRadiusBind, segment, radius)
    }

    /**
     * The inner radius of the torus.
     *
     * Generated from Godot docs: TorusMesh.get_inner_radius
     */
    fun getInnerRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInnerRadiusBind, segment)
    }

    /**
     * The outer radius of the torus.
     *
     * Generated from Godot docs: TorusMesh.set_outer_radius
     */
    fun setOuterRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOuterRadiusBind, segment, radius)
    }

    /**
     * The outer radius of the torus.
     *
     * Generated from Godot docs: TorusMesh.get_outer_radius
     */
    fun getOuterRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOuterRadiusBind, segment)
    }

    /**
     * The number of slices the torus is constructed of.
     *
     * Generated from Godot docs: TorusMesh.set_rings
     */
    fun setRings(rings: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setRingsBind, segment, rings)
    }

    /**
     * The number of slices the torus is constructed of.
     *
     * Generated from Godot docs: TorusMesh.get_rings
     */
    fun getRings(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRingsBind, segment)
    }

    /**
     * The number of edges each ring of the torus is constructed of.
     *
     * Generated from Godot docs: TorusMesh.set_ring_segments
     */
    fun setRingSegments(rings: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setRingSegmentsBind, segment, rings)
    }

    /**
     * The number of edges each ring of the torus is constructed of.
     *
     * Generated from Godot docs: TorusMesh.get_ring_segments
     */
    fun getRingSegments(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRingSegmentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TorusMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TorusMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(TorusMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TorusMesh? =
            if (handle.address() == 0L) null else TorusMesh(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_INNER_RADIUS_HASH = 373806689L
        @JvmField
        val setInnerRadiusBind =
            ObjectCalls.getMethodBind("TorusMesh", "set_inner_radius", SET_INNER_RADIUS_HASH)

        private const val GET_INNER_RADIUS_HASH = 1740695150L
        @JvmField
        val getInnerRadiusBind =
            ObjectCalls.getMethodBind("TorusMesh", "get_inner_radius", GET_INNER_RADIUS_HASH)

        private const val SET_OUTER_RADIUS_HASH = 373806689L
        @JvmField
        val setOuterRadiusBind =
            ObjectCalls.getMethodBind("TorusMesh", "set_outer_radius", SET_OUTER_RADIUS_HASH)

        private const val GET_OUTER_RADIUS_HASH = 1740695150L
        @JvmField
        val getOuterRadiusBind =
            ObjectCalls.getMethodBind("TorusMesh", "get_outer_radius", GET_OUTER_RADIUS_HASH)

        private const val SET_RINGS_HASH = 1286410249L
        @JvmField
        val setRingsBind =
            ObjectCalls.getMethodBind("TorusMesh", "set_rings", SET_RINGS_HASH)

        private const val GET_RINGS_HASH = 3905245786L
        @JvmField
        val getRingsBind =
            ObjectCalls.getMethodBind("TorusMesh", "get_rings", GET_RINGS_HASH)

        private const val SET_RING_SEGMENTS_HASH = 1286410249L
        @JvmField
        val setRingSegmentsBind =
            ObjectCalls.getMethodBind("TorusMesh", "set_ring_segments", SET_RING_SEGMENTS_HASH)

        private const val GET_RING_SEGMENTS_HASH = 3905245786L
        @JvmField
        val getRingSegmentsBind =
            ObjectCalls.getMethodBind("TorusMesh", "get_ring_segments", GET_RING_SEGMENTS_HASH)
    }
}
