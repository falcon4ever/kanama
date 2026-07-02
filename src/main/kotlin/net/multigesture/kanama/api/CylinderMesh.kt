package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Class representing a cylindrical `PrimitiveMesh`.
 *
 * Generated from Godot docs: CylinderMesh
 */
class CylinderMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
    var topRadius: Double
        @JvmName("topRadiusProperty")
        get() = getTopRadius()
        @JvmName("setTopRadiusProperty")
        set(value) = setTopRadius(value)

    var bottomRadius: Double
        @JvmName("bottomRadiusProperty")
        get() = getBottomRadius()
        @JvmName("setBottomRadiusProperty")
        set(value) = setBottomRadius(value)

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

    var capTop: Boolean
        @JvmName("capTopProperty")
        get() = isCapTop()
        @JvmName("setCapTopProperty")
        set(value) = setCapTop(value)

    var capBottom: Boolean
        @JvmName("capBottomProperty")
        get() = isCapBottom()
        @JvmName("setCapBottomProperty")
        set(value) = setCapBottom(value)

    fun setTopRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTopRadiusBind, handle, radius)
    }

    fun getTopRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTopRadiusBind, handle)
    }

    fun setBottomRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBottomRadiusBind, handle, radius)
    }

    fun getBottomRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBottomRadiusBind, handle)
    }

    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    fun setRadialSegments(segments: Int) {
        ObjectCalls.ptrcallWithIntArg(setRadialSegmentsBind, handle, segments)
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

    fun setCapTop(capTop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCapTopBind, handle, capTop)
    }

    fun isCapTop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCapTopBind, handle)
    }

    fun setCapBottom(capBottom: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCapBottomBind, handle, capBottom)
    }

    fun isCapBottom(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCapBottomBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CylinderMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CylinderMesh? =
            if (handle.address() == 0L) null else CylinderMesh(handle)

        private const val SET_TOP_RADIUS_HASH = 373806689L
        private val setTopRadiusBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "set_top_radius", SET_TOP_RADIUS_HASH)
        }

        private const val GET_TOP_RADIUS_HASH = 1740695150L
        private val getTopRadiusBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "get_top_radius", GET_TOP_RADIUS_HASH)
        }

        private const val SET_BOTTOM_RADIUS_HASH = 373806689L
        private val setBottomRadiusBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "set_bottom_radius", SET_BOTTOM_RADIUS_HASH)
        }

        private const val GET_BOTTOM_RADIUS_HASH = 1740695150L
        private val getBottomRadiusBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "get_bottom_radius", GET_BOTTOM_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_RADIAL_SEGMENTS_HASH = 1286410249L
        private val setRadialSegmentsBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "set_radial_segments", SET_RADIAL_SEGMENTS_HASH)
        }

        private const val GET_RADIAL_SEGMENTS_HASH = 3905245786L
        private val getRadialSegmentsBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "get_radial_segments", GET_RADIAL_SEGMENTS_HASH)
        }

        private const val SET_RINGS_HASH = 1286410249L
        private val setRingsBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "set_rings", SET_RINGS_HASH)
        }

        private const val GET_RINGS_HASH = 3905245786L
        private val getRingsBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "get_rings", GET_RINGS_HASH)
        }

        private const val SET_CAP_TOP_HASH = 2586408642L
        private val setCapTopBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "set_cap_top", SET_CAP_TOP_HASH)
        }

        private const val IS_CAP_TOP_HASH = 36873697L
        private val isCapTopBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "is_cap_top", IS_CAP_TOP_HASH)
        }

        private const val SET_CAP_BOTTOM_HASH = 2586408642L
        private val setCapBottomBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "set_cap_bottom", SET_CAP_BOTTOM_HASH)
        }

        private const val IS_CAP_BOTTOM_HASH = 36873697L
        private val isCapBottomBind by lazy {
            ObjectCalls.getMethodBind("CylinderMesh", "is_cap_bottom", IS_CAP_BOTTOM_HASH)
        }
    }
}
