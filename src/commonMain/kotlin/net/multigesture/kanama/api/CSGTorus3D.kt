package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

    fun setInnerRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setInnerRadiusBind, segment, radius)
    }

    fun getInnerRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInnerRadiusBind, segment)
    }

    fun setOuterRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOuterRadiusBind, segment, radius)
    }

    fun getOuterRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOuterRadiusBind, segment)
    }

    fun setSides(sides: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSidesBind, segment, sides)
    }

    fun getSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSidesBind, segment)
    }

    fun setRingSides(sides: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setRingSidesBind, segment, sides)
    }

    fun getRingSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRingSidesBind, segment)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSmoothFacesBind, segment, smoothFaces)
    }

    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSmoothFacesBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGTorus3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGTorus3D? =
            if (handle.address() == 0L) null else CSGTorus3D(GodotHandle(handle))
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
