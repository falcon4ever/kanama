package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Class representing a capsule-shaped `PrimitiveMesh`.
 *
 * Generated from Godot docs: CapsuleMesh
 */
class CapsuleMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
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

    /**
     * Radius of the capsule mesh. Note: The `radius` of a capsule cannot be greater than half of its
     * `height`. Otherwise, the capsule becomes a circle. If the `radius` is greater than half of the
     * `height`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleMesh.set_radius
     */
    fun setRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * Radius of the capsule mesh. Note: The `radius` of a capsule cannot be greater than half of its
     * `height`. Otherwise, the capsule becomes a circle. If the `radius` is greater than half of the
     * `height`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleMesh.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * Total height of the capsule mesh (including the hemispherical ends). Note: The `height` of a
     * capsule must be at least twice its `radius`. Otherwise, the capsule becomes a circle. If the
     * `height` is less than twice the `radius`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleMesh.set_height
     */
    fun setHeight(height: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
    }

    /**
     * Total height of the capsule mesh (including the hemispherical ends). Note: The `height` of a
     * capsule must be at least twice its `radius`. Otherwise, the capsule becomes a circle. If the
     * `height` is less than twice the `radius`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleMesh.get_height
     */
    fun getHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
    }

    /**
     * Number of radial segments on the capsule mesh.
     *
     * Generated from Godot docs: CapsuleMesh.set_radial_segments
     */
    fun setRadialSegments(segments: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setRadialSegmentsBind, segment, segments)
    }

    /**
     * Number of radial segments on the capsule mesh.
     *
     * Generated from Godot docs: CapsuleMesh.get_radial_segments
     */
    fun getRadialSegments(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRadialSegmentsBind, segment)
    }

    /**
     * Number of rings along the height of the capsule.
     *
     * Generated from Godot docs: CapsuleMesh.set_rings
     */
    fun setRings(rings: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setRingsBind, segment, rings)
    }

    /**
     * Number of rings along the height of the capsule.
     *
     * Generated from Godot docs: CapsuleMesh.get_rings
     */
    fun getRings(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRingsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CapsuleMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CapsuleMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(CapsuleMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CapsuleMesh? =
            if (handle.address() == 0L) null else CapsuleMesh(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("CapsuleMesh", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("CapsuleMesh", "get_radius", GET_RADIUS_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("CapsuleMesh", "set_height", SET_HEIGHT_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("CapsuleMesh", "get_height", GET_HEIGHT_HASH)

        private const val SET_RADIAL_SEGMENTS_HASH = 1286410249L
        @JvmField
        val setRadialSegmentsBind =
            ObjectCalls.getMethodBind("CapsuleMesh", "set_radial_segments", SET_RADIAL_SEGMENTS_HASH)

        private const val GET_RADIAL_SEGMENTS_HASH = 3905245786L
        @JvmField
        val getRadialSegmentsBind =
            ObjectCalls.getMethodBind("CapsuleMesh", "get_radial_segments", GET_RADIAL_SEGMENTS_HASH)

        private const val SET_RINGS_HASH = 1286410249L
        @JvmField
        val setRingsBind =
            ObjectCalls.getMethodBind("CapsuleMesh", "set_rings", SET_RINGS_HASH)

        private const val GET_RINGS_HASH = 3905245786L
        @JvmField
        val getRingsBind =
            ObjectCalls.getMethodBind("CapsuleMesh", "get_rings", GET_RINGS_HASH)
    }
}
