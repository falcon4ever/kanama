package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents a straight tube-shaped `PrimitiveMesh` with variable width.
 *
 * Generated from Godot docs: TubeTrailMesh
 */
class TubeTrailMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
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

    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setRadialSteps(radialSteps: Int) {
        ObjectCalls.ptrcallWithIntArg(setRadialStepsBind, handle, radialSteps)
    }

    fun getRadialSteps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRadialStepsBind, handle)
    }

    fun setSections(sections: Int) {
        ObjectCalls.ptrcallWithIntArg(setSectionsBind, handle, sections)
    }

    fun getSections(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSectionsBind, handle)
    }

    fun setSectionLength(sectionLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSectionLengthBind, handle, sectionLength)
    }

    fun getSectionLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSectionLengthBind, handle)
    }

    fun setSectionRings(sectionRings: Int) {
        ObjectCalls.ptrcallWithIntArg(setSectionRingsBind, handle, sectionRings)
    }

    fun getSectionRings(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSectionRingsBind, handle)
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

    fun setCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TubeTrailMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TubeTrailMesh? =
            if (handle.address() == 0L) null else TubeTrailMesh(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_RADIAL_STEPS_HASH = 1286410249L
        private val setRadialStepsBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_radial_steps", SET_RADIAL_STEPS_HASH)
        }

        private const val GET_RADIAL_STEPS_HASH = 3905245786L
        private val getRadialStepsBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_radial_steps", GET_RADIAL_STEPS_HASH)
        }

        private const val SET_SECTIONS_HASH = 1286410249L
        private val setSectionsBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_sections", SET_SECTIONS_HASH)
        }

        private const val GET_SECTIONS_HASH = 3905245786L
        private val getSectionsBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_sections", GET_SECTIONS_HASH)
        }

        private const val SET_SECTION_LENGTH_HASH = 373806689L
        private val setSectionLengthBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_section_length", SET_SECTION_LENGTH_HASH)
        }

        private const val GET_SECTION_LENGTH_HASH = 1740695150L
        private val getSectionLengthBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_section_length", GET_SECTION_LENGTH_HASH)
        }

        private const val SET_SECTION_RINGS_HASH = 1286410249L
        private val setSectionRingsBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_section_rings", SET_SECTION_RINGS_HASH)
        }

        private const val GET_SECTION_RINGS_HASH = 3905245786L
        private val getSectionRingsBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_section_rings", GET_SECTION_RINGS_HASH)
        }

        private const val SET_CAP_TOP_HASH = 2586408642L
        private val setCapTopBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_cap_top", SET_CAP_TOP_HASH)
        }

        private const val IS_CAP_TOP_HASH = 36873697L
        private val isCapTopBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "is_cap_top", IS_CAP_TOP_HASH)
        }

        private const val SET_CAP_BOTTOM_HASH = 2586408642L
        private val setCapBottomBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_cap_bottom", SET_CAP_BOTTOM_HASH)
        }

        private const val IS_CAP_BOTTOM_HASH = 36873697L
        private val isCapBottomBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "is_cap_bottom", IS_CAP_BOTTOM_HASH)
        }

        private const val SET_CURVE_HASH = 270443179L
        private val setCurveBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "set_curve", SET_CURVE_HASH)
        }

        private const val GET_CURVE_HASH = 2460114913L
        private val getCurveBind by lazy {
            ObjectCalls.getMethodBind("TubeTrailMesh", "get_curve", GET_CURVE_HASH)
        }
    }
}
