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

    /**
     * Top radius of the cylinder. If set to `0.0`, the top faces will not be generated, resulting in a
     * conic shape. See also `cap_top`.
     *
     * Generated from Godot docs: CylinderMesh.set_top_radius
     */
    fun setTopRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTopRadiusBind, handle, radius)
    }

    /**
     * Top radius of the cylinder. If set to `0.0`, the top faces will not be generated, resulting in a
     * conic shape. See also `cap_top`.
     *
     * Generated from Godot docs: CylinderMesh.get_top_radius
     */
    fun getTopRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTopRadiusBind, handle)
    }

    /**
     * Bottom radius of the cylinder. If set to `0.0`, the bottom faces will not be generated,
     * resulting in a conic shape. See also `cap_bottom`.
     *
     * Generated from Godot docs: CylinderMesh.set_bottom_radius
     */
    fun setBottomRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBottomRadiusBind, handle, radius)
    }

    /**
     * Bottom radius of the cylinder. If set to `0.0`, the bottom faces will not be generated,
     * resulting in a conic shape. See also `cap_bottom`.
     *
     * Generated from Godot docs: CylinderMesh.get_bottom_radius
     */
    fun getBottomRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBottomRadiusBind, handle)
    }

    /**
     * Full height of the cylinder.
     *
     * Generated from Godot docs: CylinderMesh.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    /**
     * Full height of the cylinder.
     *
     * Generated from Godot docs: CylinderMesh.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    /**
     * Number of radial segments on the cylinder. Higher values result in a more detailed cylinder/cone
     * at the cost of performance.
     *
     * Generated from Godot docs: CylinderMesh.set_radial_segments
     */
    fun setRadialSegments(segments: Int) {
        ObjectCalls.ptrcallWithIntArg(setRadialSegmentsBind, handle, segments)
    }

    /**
     * Number of radial segments on the cylinder. Higher values result in a more detailed cylinder/cone
     * at the cost of performance.
     *
     * Generated from Godot docs: CylinderMesh.get_radial_segments
     */
    fun getRadialSegments(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRadialSegmentsBind, handle)
    }

    /**
     * Number of edge rings along the height of the cylinder. Changing `rings` does not have any visual
     * impact unless a shader or procedural mesh tool is used to alter the vertex data. Higher values
     * result in more subdivisions, which can be used to create smoother-looking effects with shaders
     * or procedural mesh tools (at the cost of performance). When not altering the vertex data using a
     * shader or procedural mesh tool, `rings` should be kept to its default value.
     *
     * Generated from Godot docs: CylinderMesh.set_rings
     */
    fun setRings(rings: Int) {
        ObjectCalls.ptrcallWithIntArg(setRingsBind, handle, rings)
    }

    /**
     * Number of edge rings along the height of the cylinder. Changing `rings` does not have any visual
     * impact unless a shader or procedural mesh tool is used to alter the vertex data. Higher values
     * result in more subdivisions, which can be used to create smoother-looking effects with shaders
     * or procedural mesh tools (at the cost of performance). When not altering the vertex data using a
     * shader or procedural mesh tool, `rings` should be kept to its default value.
     *
     * Generated from Godot docs: CylinderMesh.get_rings
     */
    fun getRings(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRingsBind, handle)
    }

    /**
     * If `true`, generates a cap at the top of the cylinder. This can be set to `false` to speed up
     * generation and rendering when the cap is never seen by the camera. See also `top_radius`. Note:
     * If `top_radius` is `0.0`, cap generation is always skipped even if `cap_top` is `true`.
     *
     * Generated from Godot docs: CylinderMesh.set_cap_top
     */
    fun setCapTop(capTop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCapTopBind, handle, capTop)
    }

    /**
     * If `true`, generates a cap at the top of the cylinder. This can be set to `false` to speed up
     * generation and rendering when the cap is never seen by the camera. See also `top_radius`. Note:
     * If `top_radius` is `0.0`, cap generation is always skipped even if `cap_top` is `true`.
     *
     * Generated from Godot docs: CylinderMesh.is_cap_top
     */
    fun isCapTop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCapTopBind, handle)
    }

    /**
     * If `true`, generates a cap at the bottom of the cylinder. This can be set to `false` to speed up
     * generation and rendering when the cap is never seen by the camera. See also `bottom_radius`.
     * Note: If `bottom_radius` is `0.0`, cap generation is always skipped even if `cap_bottom` is
     * `true`.
     *
     * Generated from Godot docs: CylinderMesh.set_cap_bottom
     */
    fun setCapBottom(capBottom: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCapBottomBind, handle, capBottom)
    }

    /**
     * If `true`, generates a cap at the bottom of the cylinder. This can be set to `false` to speed up
     * generation and rendering when the cap is never seen by the camera. See also `bottom_radius`.
     * Note: If `bottom_radius` is `0.0`, cap generation is always skipped even if `cap_bottom` is
     * `true`.
     *
     * Generated from Godot docs: CylinderMesh.is_cap_bottom
     */
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
