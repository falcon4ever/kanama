package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Class representing a spherical `PrimitiveMesh`.
 *
 * Generated from Godot docs: SphereMesh
 */
class SphereMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
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

    /**
     * Radius of sphere.
     *
     * Generated from Godot docs: SphereMesh.set_radius
     */
    fun setRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * Radius of sphere.
     *
     * Generated from Godot docs: SphereMesh.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * Full height of the sphere.
     *
     * Generated from Godot docs: SphereMesh.set_height
     */
    fun setHeight(height: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
    }

    /**
     * Full height of the sphere.
     *
     * Generated from Godot docs: SphereMesh.get_height
     */
    fun getHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
    }

    /**
     * Number of radial segments on the sphere.
     *
     * Generated from Godot docs: SphereMesh.set_radial_segments
     */
    fun setRadialSegments(radialSegments: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setRadialSegmentsBind, segment, radialSegments)
    }

    /**
     * Number of radial segments on the sphere.
     *
     * Generated from Godot docs: SphereMesh.get_radial_segments
     */
    fun getRadialSegments(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRadialSegmentsBind, segment)
    }

    /**
     * Number of segments along the height of the sphere.
     *
     * Generated from Godot docs: SphereMesh.set_rings
     */
    fun setRings(rings: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setRingsBind, segment, rings)
    }

    /**
     * Number of segments along the height of the sphere.
     *
     * Generated from Godot docs: SphereMesh.get_rings
     */
    fun getRings(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRingsBind, segment)
    }

    /**
     * If `true`, a hemisphere is created rather than a full sphere. Note: To get a regular hemisphere,
     * the height and radius of the sphere must be equal.
     *
     * Generated from Godot docs: SphereMesh.set_is_hemisphere
     */
    fun setIsHemisphere(isHemisphere: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIsHemisphereBind, segment, isHemisphere)
    }

    /**
     * If `true`, a hemisphere is created rather than a full sphere. Note: To get a regular hemisphere,
     * the height and radius of the sphere must be equal.
     *
     * Generated from Godot docs: SphereMesh.get_is_hemisphere
     */
    fun getIsHemisphere(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getIsHemisphereBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SphereMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SphereMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(SphereMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SphereMesh? =
            if (handle.address() == 0L) null else SphereMesh(GodotHandle(handle))

        // Downcast a Resource to SphereMesh (null if not).
        @JvmStatic
        fun fromResource(value: Resource): SphereMesh? =
            if (value.isClass("SphereMesh")) RefCounted.retained(SphereMesh(value.handle)) else null
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("SphereMesh", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("SphereMesh", "get_radius", GET_RADIUS_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("SphereMesh", "set_height", SET_HEIGHT_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("SphereMesh", "get_height", GET_HEIGHT_HASH)

        private const val SET_RADIAL_SEGMENTS_HASH = 1286410249L
        @JvmField
        val setRadialSegmentsBind =
            ObjectCalls.getMethodBind("SphereMesh", "set_radial_segments", SET_RADIAL_SEGMENTS_HASH)

        private const val GET_RADIAL_SEGMENTS_HASH = 3905245786L
        @JvmField
        val getRadialSegmentsBind =
            ObjectCalls.getMethodBind("SphereMesh", "get_radial_segments", GET_RADIAL_SEGMENTS_HASH)

        private const val SET_RINGS_HASH = 1286410249L
        @JvmField
        val setRingsBind =
            ObjectCalls.getMethodBind("SphereMesh", "set_rings", SET_RINGS_HASH)

        private const val GET_RINGS_HASH = 3905245786L
        @JvmField
        val getRingsBind =
            ObjectCalls.getMethodBind("SphereMesh", "get_rings", GET_RINGS_HASH)

        private const val SET_IS_HEMISPHERE_HASH = 2586408642L
        @JvmField
        val setIsHemisphereBind =
            ObjectCalls.getMethodBind("SphereMesh", "set_is_hemisphere", SET_IS_HEMISPHERE_HASH)

        private const val GET_IS_HEMISPHERE_HASH = 36873697L
        @JvmField
        val getIsHemisphereBind =
            ObjectCalls.getMethodBind("SphereMesh", "get_is_hemisphere", GET_IS_HEMISPHERE_HASH)
    }
}
