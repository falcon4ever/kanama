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

    /**
     * The baseline size of the ribbon. The size of a particular section segment is obtained by
     * multiplying this size by the value of the `curve` at the given distance.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_size
     */
    fun setSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSizeBind, handle, size)
    }

    /**
     * The baseline size of the ribbon. The size of a particular section segment is obtained by
     * multiplying this size by the value of the `curve` at the given distance.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_size
     */
    fun getSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSizeBind, handle)
    }

    /**
     * The total number of sections on the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_sections
     */
    fun setSections(sections: Int) {
        ObjectCalls.ptrcallWithIntArg(setSectionsBind, handle, sections)
    }

    /**
     * The total number of sections on the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_sections
     */
    fun getSections(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSectionsBind, handle)
    }

    /**
     * The length of a section of the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_section_length
     */
    fun setSectionLength(sectionLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSectionLengthBind, handle, sectionLength)
    }

    /**
     * The length of a section of the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_section_length
     */
    fun getSectionLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSectionLengthBind, handle)
    }

    /**
     * The number of segments in a section. The `curve` is sampled on each segment to determine its
     * size. Higher values result in a more detailed ribbon at the cost of performance.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_section_segments
     */
    fun setSectionSegments(sectionSegments: Int) {
        ObjectCalls.ptrcallWithIntArg(setSectionSegmentsBind, handle, sectionSegments)
    }

    /**
     * The number of segments in a section. The `curve` is sampled on each segment to determine its
     * size. Higher values result in a more detailed ribbon at the cost of performance.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_section_segments
     */
    fun getSectionSegments(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSectionSegmentsBind, handle)
    }

    /**
     * Determines the size of the ribbon along its length. The size of a particular section segment is
     * obtained by multiplying the baseline `size` by the value of this curve at the given distance.
     * For values smaller than `0`, the faces will be inverted. Should be a unit `Curve`.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_curve
     */
    fun setCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Determines the size of the ribbon along its length. The size of a particular section segment is
     * obtained by multiplying the baseline `size` by the value of this curve at the given distance.
     * For values smaller than `0`, the faces will be inverted. Should be a unit `Curve`.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_curve
     */
    fun getCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, handle))
    }

    /**
     * Determines the shape of the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_shape
     */
    fun setShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setShapeBind, handle, shape)
    }

    /**
     * Determines the shape of the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_shape
     */
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
