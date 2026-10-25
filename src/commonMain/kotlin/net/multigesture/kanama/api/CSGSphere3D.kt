package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A CSG Sphere shape.
 *
 * Generated from Godot docs: CSGSphere3D
 */
class CSGSphere3D(handle: GodotHandle) : CSGPrimitive3D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

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

    var smoothFaces: Boolean
        @JvmName("smoothFacesProperty")
        get() = getSmoothFaces()
        @JvmName("setSmoothFacesProperty")
        set(value) = setSmoothFaces(value)

    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    /**
     * Radius of the sphere.
     *
     * Generated from Godot docs: CSGSphere3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * Radius of the sphere.
     *
     * Generated from Godot docs: CSGSphere3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * Number of vertical slices for the sphere.
     *
     * Generated from Godot docs: CSGSphere3D.set_radial_segments
     */
    fun setRadialSegments(radialSegments: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setRadialSegmentsBind, segment, radialSegments)
    }

    /**
     * Number of vertical slices for the sphere.
     *
     * Generated from Godot docs: CSGSphere3D.get_radial_segments
     */
    fun getRadialSegments(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRadialSegmentsBind, segment)
    }

    /**
     * Number of horizontal slices for the sphere.
     *
     * Generated from Godot docs: CSGSphere3D.set_rings
     */
    fun setRings(rings: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setRingsBind, segment, rings)
    }

    /**
     * Number of horizontal slices for the sphere.
     *
     * Generated from Godot docs: CSGSphere3D.get_rings
     */
    fun getRings(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRingsBind, segment)
    }

    /**
     * If `true` the normals of the sphere are set to give a smooth effect making the sphere seem
     * rounded. If `false` the sphere will have a flat shaded look.
     *
     * Generated from Godot docs: CSGSphere3D.set_smooth_faces
     */
    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSmoothFacesBind, segment, smoothFaces)
    }

    /**
     * If `true` the normals of the sphere are set to give a smooth effect making the sphere seem
     * rounded. If `false` the sphere will have a flat shaded look.
     *
     * Generated from Godot docs: CSGSphere3D.get_smooth_faces
     */
    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSmoothFacesBind, segment)
    }

    /**
     * The material used to render the sphere.
     *
     * Generated from Godot docs: CSGSphere3D.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(material)
    }

    /**
     * The material used to render the sphere.
     *
     * Generated from Godot docs: CSGSphere3D.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGSphere3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGSphere3D? =
            if (handle.address() == 0L) null else CSGSphere3D(GodotHandle(handle))

        /** A new CSGSphere3D, GDScript's `CSGSphere3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): CSGSphere3D =
            CSGSphere3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("CSGSphere3D"), "CSGSphere3D")))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "get_radius", GET_RADIUS_HASH)

        private const val SET_RADIAL_SEGMENTS_HASH = 1286410249L
        @JvmField
        val setRadialSegmentsBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "set_radial_segments", SET_RADIAL_SEGMENTS_HASH)

        private const val GET_RADIAL_SEGMENTS_HASH = 3905245786L
        @JvmField
        val getRadialSegmentsBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "get_radial_segments", GET_RADIAL_SEGMENTS_HASH)

        private const val SET_RINGS_HASH = 1286410249L
        @JvmField
        val setRingsBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "set_rings", SET_RINGS_HASH)

        private const val GET_RINGS_HASH = 3905245786L
        @JvmField
        val getRingsBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "get_rings", GET_RINGS_HASH)

        private const val SET_SMOOTH_FACES_HASH = 2586408642L
        @JvmField
        val setSmoothFacesBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "set_smooth_faces", SET_SMOOTH_FACES_HASH)

        private const val GET_SMOOTH_FACES_HASH = 36873697L
        @JvmField
        val getSmoothFacesBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "get_smooth_faces", GET_SMOOTH_FACES_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("CSGSphere3D", "get_material", GET_MATERIAL_HASH)
    }
}
