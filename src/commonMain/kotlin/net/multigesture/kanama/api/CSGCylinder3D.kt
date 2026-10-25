package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A CSG Cylinder shape.
 *
 * Generated from Godot docs: CSGCylinder3D
 */
class CSGCylinder3D(handle: GodotHandle) : CSGPrimitive3D(handle) {
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

    var sides: Int
        @JvmName("sidesProperty")
        get() = getSides()
        @JvmName("setSidesProperty")
        set(value) = setSides(value)

    var cone: Boolean
        @JvmName("coneProperty")
        get() = isCone()
        @JvmName("setConeProperty")
        set(value) = setCone(value)

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
     * The radius of the cylinder.
     *
     * Generated from Godot docs: CSGCylinder3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * The radius of the cylinder.
     *
     * Generated from Godot docs: CSGCylinder3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * The height of the cylinder.
     *
     * Generated from Godot docs: CSGCylinder3D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
    }

    /**
     * The height of the cylinder.
     *
     * Generated from Godot docs: CSGCylinder3D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
    }

    /**
     * The number of sides of the cylinder, the higher this number the more detail there will be in the
     * cylinder.
     *
     * Generated from Godot docs: CSGCylinder3D.set_sides
     */
    fun setSides(sides: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSidesBind, segment, sides)
    }

    /**
     * The number of sides of the cylinder, the higher this number the more detail there will be in the
     * cylinder.
     *
     * Generated from Godot docs: CSGCylinder3D.get_sides
     */
    fun getSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSidesBind, segment)
    }

    /**
     * If `true` a cone is created, the `radius` will only apply to one side.
     *
     * Generated from Godot docs: CSGCylinder3D.set_cone
     */
    fun setCone(cone: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setConeBind, segment, cone)
    }

    /**
     * If `true` a cone is created, the `radius` will only apply to one side.
     *
     * Generated from Godot docs: CSGCylinder3D.is_cone
     */
    fun isCone(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isConeBind, segment)
    }

    /**
     * The material used to render the cylinder.
     *
     * Generated from Godot docs: CSGCylinder3D.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(material)
    }

    /**
     * The material used to render the cylinder.
     *
     * Generated from Godot docs: CSGCylinder3D.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    /**
     * If `true` the normals of the cylinder are set to give a smooth effect making the cylinder seem
     * rounded. If `false` the cylinder will have a flat shaded look.
     *
     * Generated from Godot docs: CSGCylinder3D.set_smooth_faces
     */
    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSmoothFacesBind, segment, smoothFaces)
    }

    /**
     * If `true` the normals of the cylinder are set to give a smooth effect making the cylinder seem
     * rounded. If `false` the cylinder will have a flat shaded look.
     *
     * Generated from Godot docs: CSGCylinder3D.get_smooth_faces
     */
    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSmoothFacesBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGCylinder3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGCylinder3D? =
            if (handle.address() == 0L) null else CSGCylinder3D(GodotHandle(handle))

        /** A new CSGCylinder3D, GDScript's `CSGCylinder3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): CSGCylinder3D =
            CSGCylinder3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("CSGCylinder3D"), "CSGCylinder3D")))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_radius", GET_RADIUS_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_height", SET_HEIGHT_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_height", GET_HEIGHT_HASH)

        private const val SET_SIDES_HASH = 1286410249L
        @JvmField
        val setSidesBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_sides", SET_SIDES_HASH)

        private const val GET_SIDES_HASH = 3905245786L
        @JvmField
        val getSidesBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_sides", GET_SIDES_HASH)

        private const val SET_CONE_HASH = 2586408642L
        @JvmField
        val setConeBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_cone", SET_CONE_HASH)

        private const val IS_CONE_HASH = 36873697L
        @JvmField
        val isConeBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "is_cone", IS_CONE_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_material", GET_MATERIAL_HASH)

        private const val SET_SMOOTH_FACES_HASH = 2586408642L
        @JvmField
        val setSmoothFacesBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_smooth_faces", SET_SMOOTH_FACES_HASH)

        private const val GET_SMOOTH_FACES_HASH = 36873697L
        @JvmField
        val getSmoothFacesBind =
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_smooth_faces", GET_SMOOTH_FACES_HASH)
    }
}
