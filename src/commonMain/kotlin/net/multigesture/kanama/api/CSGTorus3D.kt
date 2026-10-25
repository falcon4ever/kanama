package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A CSG Torus shape.
 *
 * Generated from Godot docs: CSGTorus3D
 */
class CSGTorus3D(handle: GodotHandle) : CSGPrimitive3D(handle) {
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

    var sides: Int
        @JvmName("sidesProperty")
        get() = getSides()
        @JvmName("setSidesProperty")
        set(value) = setSides(value)

    var ringSides: Int
        @JvmName("ringSidesProperty")
        get() = getRingSides()
        @JvmName("setRingSidesProperty")
        set(value) = setRingSides(value)

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
     * The inner radius of the torus.
     *
     * Generated from Godot docs: CSGTorus3D.set_inner_radius
     */
    fun setInnerRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setInnerRadiusBind, segment, radius)
    }

    /**
     * The inner radius of the torus.
     *
     * Generated from Godot docs: CSGTorus3D.get_inner_radius
     */
    fun getInnerRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInnerRadiusBind, segment)
    }

    /**
     * The outer radius of the torus.
     *
     * Generated from Godot docs: CSGTorus3D.set_outer_radius
     */
    fun setOuterRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOuterRadiusBind, segment, radius)
    }

    /**
     * The outer radius of the torus.
     *
     * Generated from Godot docs: CSGTorus3D.get_outer_radius
     */
    fun getOuterRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOuterRadiusBind, segment)
    }

    /**
     * The number of slices the torus is constructed of.
     *
     * Generated from Godot docs: CSGTorus3D.set_sides
     */
    fun setSides(sides: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSidesBind, segment, sides)
    }

    /**
     * The number of slices the torus is constructed of.
     *
     * Generated from Godot docs: CSGTorus3D.get_sides
     */
    fun getSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSidesBind, segment)
    }

    /**
     * The number of edges each ring of the torus is constructed of.
     *
     * Generated from Godot docs: CSGTorus3D.set_ring_sides
     */
    fun setRingSides(sides: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setRingSidesBind, segment, sides)
    }

    /**
     * The number of edges each ring of the torus is constructed of.
     *
     * Generated from Godot docs: CSGTorus3D.get_ring_sides
     */
    fun getRingSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRingSidesBind, segment)
    }

    /**
     * The material used to render the torus.
     *
     * Generated from Godot docs: CSGTorus3D.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(material)
    }

    /**
     * The material used to render the torus.
     *
     * Generated from Godot docs: CSGTorus3D.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    /**
     * If `true` the normals of the torus are set to give a smooth effect making the torus seem
     * rounded. If `false` the torus will have a flat shaded look.
     *
     * Generated from Godot docs: CSGTorus3D.set_smooth_faces
     */
    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSmoothFacesBind, segment, smoothFaces)
    }

    /**
     * If `true` the normals of the torus are set to give a smooth effect making the torus seem
     * rounded. If `false` the torus will have a flat shaded look.
     *
     * Generated from Godot docs: CSGTorus3D.get_smooth_faces
     */
    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSmoothFacesBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGTorus3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGTorus3D? =
            if (handle.address() == 0L) null else CSGTorus3D(GodotHandle(handle))

        /** A new CSGTorus3D, GDScript's `CSGTorus3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): CSGTorus3D =
            CSGTorus3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("CSGTorus3D"), "CSGTorus3D")))
    }

    private object Binds {
        private const val SET_INNER_RADIUS_HASH = 373806689L
        @JvmField
        val setInnerRadiusBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "set_inner_radius", SET_INNER_RADIUS_HASH)

        private const val GET_INNER_RADIUS_HASH = 1740695150L
        @JvmField
        val getInnerRadiusBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "get_inner_radius", GET_INNER_RADIUS_HASH)

        private const val SET_OUTER_RADIUS_HASH = 373806689L
        @JvmField
        val setOuterRadiusBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "set_outer_radius", SET_OUTER_RADIUS_HASH)

        private const val GET_OUTER_RADIUS_HASH = 1740695150L
        @JvmField
        val getOuterRadiusBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "get_outer_radius", GET_OUTER_RADIUS_HASH)

        private const val SET_SIDES_HASH = 1286410249L
        @JvmField
        val setSidesBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "set_sides", SET_SIDES_HASH)

        private const val GET_SIDES_HASH = 3905245786L
        @JvmField
        val getSidesBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "get_sides", GET_SIDES_HASH)

        private const val SET_RING_SIDES_HASH = 1286410249L
        @JvmField
        val setRingSidesBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "set_ring_sides", SET_RING_SIDES_HASH)

        private const val GET_RING_SIDES_HASH = 3905245786L
        @JvmField
        val getRingSidesBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "get_ring_sides", GET_RING_SIDES_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "get_material", GET_MATERIAL_HASH)

        private const val SET_SMOOTH_FACES_HASH = 2586408642L
        @JvmField
        val setSmoothFacesBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "set_smooth_faces", SET_SMOOTH_FACES_HASH)

        private const val GET_SMOOTH_FACES_HASH = 36873697L
        @JvmField
        val getSmoothFacesBind =
            ObjectCalls.getMethodBind("CSGTorus3D", "get_smooth_faces", GET_SMOOTH_FACES_HASH)
    }
}
