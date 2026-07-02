package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: CSGCylinder3D
 */
class CSGCylinder3D(handle: MemorySegment) : CSGPrimitive3D(handle) {
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

    fun setSides(sides: Int) {
        ObjectCalls.ptrcallWithIntArg(setSidesBind, handle, sides)
    }

    fun getSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSidesBind, handle)
    }

    fun setCone(cone: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setConeBind, handle, cone)
    }

    fun isCone(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isConeBind, handle)
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
        fun fromHandle(handle: MemorySegment): CSGCylinder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CSGCylinder3D? =
            if (handle.address() == 0L) null else CSGCylinder3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_SIDES_HASH = 1286410249L
        private val setSidesBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_sides", SET_SIDES_HASH)
        }

        private const val GET_SIDES_HASH = 3905245786L
        private val getSidesBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_sides", GET_SIDES_HASH)
        }

        private const val SET_CONE_HASH = 2586408642L
        private val setConeBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_cone", SET_CONE_HASH)
        }

        private const val IS_CONE_HASH = 36873697L
        private val isConeBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "is_cone", IS_CONE_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_material", GET_MATERIAL_HASH)
        }

        private const val SET_SMOOTH_FACES_HASH = 2586408642L
        private val setSmoothFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "set_smooth_faces", SET_SMOOTH_FACES_HASH)
        }

        private const val GET_SMOOTH_FACES_HASH = 36873697L
        private val getSmoothFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGCylinder3D", "get_smooth_faces", GET_SMOOTH_FACES_HASH)
        }
    }
}
