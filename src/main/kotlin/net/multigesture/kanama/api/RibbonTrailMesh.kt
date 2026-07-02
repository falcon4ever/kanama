package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents a straight ribbon-shaped `PrimitiveMesh` with variable width.
 *
 * Generated from Godot docs: RibbonTrailMesh
 */
class RibbonTrailMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
    var shape: Long
        @JvmName("shapeProperty")
        get() = getShape()
        @JvmName("setShapeProperty")
        set(value) = setShape(value)

    var size: Double
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

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

    var sectionSegments: Int
        @JvmName("sectionSegmentsProperty")
        get() = getSectionSegments()
        @JvmName("setSectionSegmentsProperty")
        set(value) = setSectionSegments(value)

    var curve: Curve?
        @JvmName("curveProperty")
        get() = getCurve()
        @JvmName("setCurveProperty")
        set(value) = setCurve(value)

    fun setSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSizeBind, handle, size)
    }

    fun getSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSizeBind, handle)
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

    fun setSectionSegments(sectionSegments: Int) {
        ObjectCalls.ptrcallWithIntArg(setSectionSegmentsBind, handle, sectionSegments)
    }

    fun getSectionSegments(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSectionSegmentsBind, handle)
    }

    fun setCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, handle))
    }

    fun setShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setShapeBind, handle, shape)
    }

    fun getShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShapeBind, handle)
    }

    companion object {
        const val SHAPE_FLAT: Long = 0L
        const val SHAPE_CROSS: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): RibbonTrailMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RibbonTrailMesh? =
            if (handle.address() == 0L) null else RibbonTrailMesh(handle)

        private const val SET_SIZE_HASH = 373806689L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 1740695150L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_size", GET_SIZE_HASH)
        }

        private const val SET_SECTIONS_HASH = 1286410249L
        private val setSectionsBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_sections", SET_SECTIONS_HASH)
        }

        private const val GET_SECTIONS_HASH = 3905245786L
        private val getSectionsBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_sections", GET_SECTIONS_HASH)
        }

        private const val SET_SECTION_LENGTH_HASH = 373806689L
        private val setSectionLengthBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_section_length", SET_SECTION_LENGTH_HASH)
        }

        private const val GET_SECTION_LENGTH_HASH = 1740695150L
        private val getSectionLengthBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_section_length", GET_SECTION_LENGTH_HASH)
        }

        private const val SET_SECTION_SEGMENTS_HASH = 1286410249L
        private val setSectionSegmentsBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_section_segments", SET_SECTION_SEGMENTS_HASH)
        }

        private const val GET_SECTION_SEGMENTS_HASH = 3905245786L
        private val getSectionSegmentsBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_section_segments", GET_SECTION_SEGMENTS_HASH)
        }

        private const val SET_CURVE_HASH = 270443179L
        private val setCurveBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_curve", SET_CURVE_HASH)
        }

        private const val GET_CURVE_HASH = 2460114913L
        private val getCurveBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_curve", GET_CURVE_HASH)
        }

        private const val SET_SHAPE_HASH = 1684440262L
        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_shape", SET_SHAPE_HASH)
        }

        private const val GET_SHAPE_HASH = 1317484155L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_shape", GET_SHAPE_HASH)
        }
    }
}
