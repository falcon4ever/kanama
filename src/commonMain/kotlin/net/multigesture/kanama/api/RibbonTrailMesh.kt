package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a straight ribbon-shaped `PrimitiveMesh` with variable width.
 *
 * Generated from Godot docs: RibbonTrailMesh
 */
class RibbonTrailMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
    var shape: RibbonTrailMesh.Shape
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSizeBind, segment, size)
    }

    /**
     * The baseline size of the ribbon. The size of a particular section segment is obtained by
     * multiplying this size by the value of the `curve` at the given distance.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_size
     */
    fun getSize(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSizeBind, segment)
    }

    /**
     * The total number of sections on the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_sections
     */
    fun setSections(sections: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSectionsBind, segment, sections)
    }

    /**
     * The total number of sections on the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_sections
     */
    fun getSections(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSectionsBind, segment)
    }

    /**
     * The length of a section of the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_section_length
     */
    fun setSectionLength(sectionLength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSectionLengthBind, segment, sectionLength)
    }

    /**
     * The length of a section of the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_section_length
     */
    fun getSectionLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSectionLengthBind, segment)
    }

    /**
     * The number of segments in a section. The `curve` is sampled on each segment to determine its
     * size. Higher values result in a more detailed ribbon at the cost of performance.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_section_segments
     */
    fun setSectionSegments(sectionSegments: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSectionSegmentsBind, segment, sectionSegments)
    }

    /**
     * The number of segments in a section. The `curve` is sampled on each segment to determine its
     * size. Higher values result in a more detailed ribbon at the cost of performance.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_section_segments
     */
    fun getSectionSegments(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSectionSegmentsBind, segment)
    }

    /**
     * Determines the size of the ribbon along its length. The size of a particular section segment is
     * obtained by multiplying the baseline `size` by the value of this curve at the given distance.
     * For values smaller than `0`, the faces will be inverted. Should be a unit `Curve`.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_curve
     */
    fun setCurve(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Determines the size of the ribbon along its length. The size of a particular section segment is
     * obtained by multiplying the baseline `size` by the value of this curve at the given distance.
     * For values smaller than `0`, the faces will be inverted. Should be a unit `Curve`.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_curve
     */
    fun getCurve(): Curve? {
        checkOpen()
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurveBind, segment))
    }

    /**
     * Determines the shape of the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.set_shape
     */
    fun setShape(shape: RibbonTrailMesh.Shape) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setShapeBind, segment, shape.value)
    }

    /**
     * Determines the shape of the ribbon.
     *
     * Generated from Godot docs: RibbonTrailMesh.get_shape
     */
    fun getShape(): RibbonTrailMesh.Shape {
        checkOpen()
        return RibbonTrailMesh.Shape(ObjectCalls.ptrcallNoArgsRetLong(Binds.getShapeBind, segment))
    }

    /**
     * Godot's `RibbonTrailMesh.Shape` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`RibbonTrailMesh.Shape.<NAME>`).
     *
     * Generated from Godot docs: RibbonTrailMesh.Shape
     */
    @JvmInline
    value class Shape(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Gives the mesh a single flat face.
             *
             * Generated from Godot docs: RibbonTrailMesh.SHAPE_FLAT
             */
            val FLAT: Shape get() = Shape(0L)
            /**
             * Gives the mesh two perpendicular flat faces, making a cross shape.
             *
             * Generated from Godot docs: RibbonTrailMesh.SHAPE_CROSS
             */
            val CROSS: Shape get() = Shape(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RibbonTrailMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RibbonTrailMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(RibbonTrailMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RibbonTrailMesh? =
            if (handle.address() == 0L) null else RibbonTrailMesh(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 373806689L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 1740695150L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_size", GET_SIZE_HASH)

        private const val SET_SECTIONS_HASH = 1286410249L
        @JvmField
        val setSectionsBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_sections", SET_SECTIONS_HASH)

        private const val GET_SECTIONS_HASH = 3905245786L
        @JvmField
        val getSectionsBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_sections", GET_SECTIONS_HASH)

        private const val SET_SECTION_LENGTH_HASH = 373806689L
        @JvmField
        val setSectionLengthBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_section_length", SET_SECTION_LENGTH_HASH)

        private const val GET_SECTION_LENGTH_HASH = 1740695150L
        @JvmField
        val getSectionLengthBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_section_length", GET_SECTION_LENGTH_HASH)

        private const val SET_SECTION_SEGMENTS_HASH = 1286410249L
        @JvmField
        val setSectionSegmentsBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_section_segments", SET_SECTION_SEGMENTS_HASH)

        private const val GET_SECTION_SEGMENTS_HASH = 3905245786L
        @JvmField
        val getSectionSegmentsBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_section_segments", GET_SECTION_SEGMENTS_HASH)

        private const val SET_CURVE_HASH = 270443179L
        @JvmField
        val setCurveBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_curve", SET_CURVE_HASH)

        private const val GET_CURVE_HASH = 2460114913L
        @JvmField
        val getCurveBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_curve", GET_CURVE_HASH)

        private const val SET_SHAPE_HASH = 1684440262L
        @JvmField
        val setShapeBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "set_shape", SET_SHAPE_HASH)

        private const val GET_SHAPE_HASH = 1317484155L
        @JvmField
        val getShapeBind =
            ObjectCalls.getMethodBind("RibbonTrailMesh", "get_shape", GET_SHAPE_HASH)
    }
}
