package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a straight tube-shaped `PrimitiveMesh` with variable width.
 *
 * Generated from Godot docs: TubeTrailMesh
 */
class TubeTrailMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var radialSteps: Int
        @JvmName("radialStepsProperty")
        get() = getRadialSteps()
        @JvmName("setRadialStepsProperty")
        set(value) = setRadialSteps(value)

    var sections: Int
        @JvmName("sectionsProperty")
        get() = getSections()
        @JvmName("setSectionsProperty")
        set(value) = setSections(value)

    var sectionLength: Double
        @JvmName("sectionLengthProperty")
        get() = getSectionLength()
        @JvmName("setSectionLengthProperty")
        set(value) = setSectionLength(value)

    var sectionRings: Int
        @JvmName("sectionRingsProperty")
        get() = getSectionRings()
        @JvmName("setSectionRingsProperty")
        set(value) = setSectionRings(value)

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

    var curve: Curve?
        @JvmName("curveProperty")
        get() = getCurve()
        @JvmName("setCurveProperty")
        set(value) = setCurve(value)

    /**
     * The baseline radius of the tube. The radius of a particular section ring is obtained by
     * multiplying this radius by the value of the `curve` at the given distance.
     *
     * Generated from Godot docs: TubeTrailMesh.set_radius
     */
    fun setRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * The baseline radius of the tube. The radius of a particular section ring is obtained by
     * multiplying this radius by the value of the `curve` at the given distance.
     *
     * Generated from Godot docs: TubeTrailMesh.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * The number of sides on the tube. For example, a value of `5` means the tube will be pentagonal.
     * Higher values result in a more detailed tube at the cost of performance.
     *
     * Generated from Godot docs: TubeTrailMesh.set_radial_steps
     */
    fun setRadialSteps(radialSteps: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setRadialStepsBind, segment, radialSteps)
    }

    /**
     * The number of sides on the tube. For example, a value of `5` means the tube will be pentagonal.
     * Higher values result in a more detailed tube at the cost of performance.
     *
     * Generated from Godot docs: TubeTrailMesh.get_radial_steps
     */
    fun getRadialSteps(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRadialStepsBind, segment)
    }

    /**
     * The total number of sections on the tube.
     *
     * Generated from Godot docs: TubeTrailMesh.set_sections
     */
    fun setSections(sections: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSectionsBind, segment, sections)
    }

    /**
     * The total number of sections on the tube.
     *
     * Generated from Godot docs: TubeTrailMesh.get_sections
     */
    fun getSections(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSectionsBind, segment)
    }

    /**
     * The length of a section of the tube.
     *
     * Generated from Godot docs: TubeTrailMesh.set_section_length
     */
    fun setSectionLength(sectionLength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSectionLengthBind, segment, sectionLength)
    }

    /**
     * The length of a section of the tube.
     *
     * Generated from Godot docs: TubeTrailMesh.get_section_length
     */
    fun getSectionLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSectionLengthBind, segment)
    }

    /**
     * The number of rings in a section. The `curve` is sampled on each ring to determine its radius.
     * Higher values result in a more detailed tube at the cost of performance.
     *
     * Generated from Godot docs: TubeTrailMesh.set_section_rings
     */
    fun setSectionRings(sectionRings: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSectionRingsBind, segment, sectionRings)
    }

    /**
     * The number of rings in a section. The `curve` is sampled on each ring to determine its radius.
     * Higher values result in a more detailed tube at the cost of performance.
     *
     * Generated from Godot docs: TubeTrailMesh.get_section_rings
     */
    fun getSectionRings(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSectionRingsBind, segment)
    }

    /**
     * If `true`, generates a cap at the top of the tube. This can be set to `false` to speed up
     * generation and rendering when the cap is never seen by the camera.
     *
     * Generated from Godot docs: TubeTrailMesh.set_cap_top
     */
    fun setCapTop(capTop: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCapTopBind, segment, capTop)
    }

    /**
     * If `true`, generates a cap at the top of the tube. This can be set to `false` to speed up
     * generation and rendering when the cap is never seen by the camera.
     *
     * Generated from Godot docs: TubeTrailMesh.is_cap_top
     */
    fun isCapTop(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCapTopBind, segment)
    }

    /**
     * If `true`, generates a cap at the bottom of the tube. This can be set to `false` to speed up
     * generation and rendering when the cap is never seen by the camera.
     *
     * Generated from Godot docs: TubeTrailMesh.set_cap_bottom
     */
    fun setCapBottom(capBottom: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCapBottomBind, segment, capBottom)
    }

    /**
     * If `true`, generates a cap at the bottom of the tube. This can be set to `false` to speed up
     * generation and rendering when the cap is never seen by the camera.
     *
     * Generated from Godot docs: TubeTrailMesh.is_cap_bottom
     */
    fun isCapBottom(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCapBottomBind, segment)
    }

    /**
     * Determines the radius of the tube along its length. The radius of a particular section ring is
     * obtained by multiplying the baseline `radius` by the value of this curve at the given distance.
     * For values smaller than `0`, the faces will be inverted. Should be a unit `Curve`.
     *
     * Generated from Godot docs: TubeTrailMesh.set_curve
     */
    fun setCurve(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Determines the radius of the tube along its length. The radius of a particular section ring is
     * obtained by multiplying the baseline `radius` by the value of this curve at the given distance.
     * For values smaller than `0`, the faces will be inverted. Should be a unit `Curve`.
     *
     * Generated from Godot docs: TubeTrailMesh.get_curve
     */
    fun getCurve(): Curve? {
        checkOpen()
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurveBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TubeTrailMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TubeTrailMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(TubeTrailMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TubeTrailMesh? =
            if (handle.address() == 0L) null else TubeTrailMesh(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_radius", GET_RADIUS_HASH)

        private const val SET_RADIAL_STEPS_HASH = 1286410249L
        @JvmField
        val setRadialStepsBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_radial_steps", SET_RADIAL_STEPS_HASH)

        private const val GET_RADIAL_STEPS_HASH = 3905245786L
        @JvmField
        val getRadialStepsBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_radial_steps", GET_RADIAL_STEPS_HASH)

        private const val SET_SECTIONS_HASH = 1286410249L
        @JvmField
        val setSectionsBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_sections", SET_SECTIONS_HASH)

        private const val GET_SECTIONS_HASH = 3905245786L
        @JvmField
        val getSectionsBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_sections", GET_SECTIONS_HASH)

        private const val SET_SECTION_LENGTH_HASH = 373806689L
        @JvmField
        val setSectionLengthBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_section_length", SET_SECTION_LENGTH_HASH)

        private const val GET_SECTION_LENGTH_HASH = 1740695150L
        @JvmField
        val getSectionLengthBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_section_length", GET_SECTION_LENGTH_HASH)

        private const val SET_SECTION_RINGS_HASH = 1286410249L
        @JvmField
        val setSectionRingsBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_section_rings", SET_SECTION_RINGS_HASH)

        private const val GET_SECTION_RINGS_HASH = 3905245786L
        @JvmField
        val getSectionRingsBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_section_rings", GET_SECTION_RINGS_HASH)

        private const val SET_CAP_TOP_HASH = 2586408642L
        @JvmField
        val setCapTopBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_cap_top", SET_CAP_TOP_HASH)

        private const val IS_CAP_TOP_HASH = 36873697L
        @JvmField
        val isCapTopBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "is_cap_top", IS_CAP_TOP_HASH)

        private const val SET_CAP_BOTTOM_HASH = 2586408642L
        @JvmField
        val setCapBottomBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_cap_bottom", SET_CAP_BOTTOM_HASH)

        private const val IS_CAP_BOTTOM_HASH = 36873697L
        @JvmField
        val isCapBottomBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "is_cap_bottom", IS_CAP_BOTTOM_HASH)

        private const val SET_CURVE_HASH = 270443179L
        @JvmField
        val setCurveBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_curve", SET_CURVE_HASH)

        private const val GET_CURVE_HASH = 2460114913L
        @JvmField
        val getCurveBind =
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_curve", GET_CURVE_HASH)
    }
}
