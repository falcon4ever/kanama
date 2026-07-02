package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: CSGSphere3D
 */
class CSGSphere3D(handle: MemorySegment) : CSGPrimitive3D(handle) {
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

    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setRadialSegments(radialSegments: Int) {
        ObjectCalls.ptrcallWithIntArg(setRadialSegmentsBind, handle, radialSegments)
    }

    fun getRadialSegments(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRadialSegmentsBind, handle)
    }

    fun setRings(rings: Int) {
        ObjectCalls.ptrcallWithIntArg(setRingsBind, handle, rings)
    }

    fun getRings(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRingsBind, handle)
    }

    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSmoothFacesBind, handle, smoothFaces)
    }

    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSmoothFacesBind, handle)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CSGSphere3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CSGSphere3D? =
            if (handle.address() == 0L) null else CSGSphere3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_RADIAL_SEGMENTS_HASH = 1286410249L
        private val setRadialSegmentsBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "set_radial_segments", SET_RADIAL_SEGMENTS_HASH)
        }

        private const val GET_RADIAL_SEGMENTS_HASH = 3905245786L
        private val getRadialSegmentsBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "get_radial_segments", GET_RADIAL_SEGMENTS_HASH)
        }

        private const val SET_RINGS_HASH = 1286410249L
        private val setRingsBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "set_rings", SET_RINGS_HASH)
        }

        private const val GET_RINGS_HASH = 3905245786L
        private val getRingsBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "get_rings", GET_RINGS_HASH)
        }

        private const val SET_SMOOTH_FACES_HASH = 2586408642L
        private val setSmoothFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "set_smooth_faces", SET_SMOOTH_FACES_HASH)
        }

        private const val GET_SMOOTH_FACES_HASH = 36873697L
        private val getSmoothFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "get_smooth_faces", GET_SMOOTH_FACES_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGSphere3D", "get_material", GET_MATERIAL_HASH)
        }
    }
}
