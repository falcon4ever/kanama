package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: CSGTorus3D
 */
class CSGTorus3D(handle: MemorySegment) : CSGPrimitive3D(handle) {
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

    fun setSides(sides: Int) {
        ObjectCalls.ptrcallWithIntArg(setSidesBind, handle, sides)
    }

    fun getSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSidesBind, handle)
    }

    fun setRingSides(sides: Int) {
        ObjectCalls.ptrcallWithIntArg(setRingSidesBind, handle, sides)
    }

    fun getRingSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRingSidesBind, handle)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSmoothFacesBind, handle, smoothFaces)
    }

    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSmoothFacesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CSGTorus3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CSGTorus3D? =
            if (handle.address() == 0L) null else CSGTorus3D(handle)

        private const val SET_INNER_RADIUS_HASH = 373806689L
        private val setInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "set_inner_radius", SET_INNER_RADIUS_HASH)
        }

        private const val GET_INNER_RADIUS_HASH = 1740695150L
        private val getInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "get_inner_radius", GET_INNER_RADIUS_HASH)
        }

        private const val SET_OUTER_RADIUS_HASH = 373806689L
        private val setOuterRadiusBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "set_outer_radius", SET_OUTER_RADIUS_HASH)
        }

        private const val GET_OUTER_RADIUS_HASH = 1740695150L
        private val getOuterRadiusBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "get_outer_radius", GET_OUTER_RADIUS_HASH)
        }

        private const val SET_SIDES_HASH = 1286410249L
        private val setSidesBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "set_sides", SET_SIDES_HASH)
        }

        private const val GET_SIDES_HASH = 3905245786L
        private val getSidesBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "get_sides", GET_SIDES_HASH)
        }

        private const val SET_RING_SIDES_HASH = 1286410249L
        private val setRingSidesBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "set_ring_sides", SET_RING_SIDES_HASH)
        }

        private const val GET_RING_SIDES_HASH = 3905245786L
        private val getRingSidesBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "get_ring_sides", GET_RING_SIDES_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "get_material", GET_MATERIAL_HASH)
        }

        private const val SET_SMOOTH_FACES_HASH = 2586408642L
        private val setSmoothFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "set_smooth_faces", SET_SMOOTH_FACES_HASH)
        }

        private const val GET_SMOOTH_FACES_HASH = 36873697L
        private val getSmoothFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGTorus3D", "get_smooth_faces", GET_SMOOTH_FACES_HASH)
        }
    }
}
