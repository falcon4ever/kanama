package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * A mathematical curve.
 *
 * Generated from Godot docs: Curve
 */
class Curve(handle: GodotHandle) : Resource(handle) {
    var minDomain: Double
        @JvmName("minDomainProperty")
        get() = getMinDomain()
        @JvmName("setMinDomainProperty")
        set(value) = setMinDomain(value)

    var maxDomain: Double
        @JvmName("maxDomainProperty")
        get() = getMaxDomain()
        @JvmName("setMaxDomainProperty")
        set(value) = setMaxDomain(value)

    var minValue: Double
        @JvmName("minValueProperty")
        get() = getMinValue()
        @JvmName("setMinValueProperty")
        set(value) = setMinValue(value)

    var maxValue: Double
        @JvmName("maxValueProperty")
        get() = getMaxValue()
        @JvmName("setMaxValueProperty")
        set(value) = setMaxValue(value)

    var bakeResolution: Int
        @JvmName("bakeResolutionProperty")
        get() = getBakeResolution()
        @JvmName("setBakeResolutionProperty")
        set(value) = setBakeResolution(value)

    var pointCount: Int
        @JvmName("pointCountProperty")
        get() = getPointCount()
        @JvmName("setPointCountProperty")
        set(value) = setPointCount(value)

    /**
     * The number of points describing the curve.
     *
     * Generated from Godot docs: Curve.get_point_count
     */
    fun getPointCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPointCountBind, segment)
    }

    /**
     * The number of points describing the curve.
     *
     * Generated from Godot docs: Curve.set_point_count
     */
    fun setPointCount(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setPointCountBind, segment, count)
    }

    /**
     * Adds a point to the curve. For each side, if the `*_mode` is `TangentMode.LINEAR`, the
     * `*_tangent` angle (in degrees) uses the slope of the curve halfway to the adjacent point. Allows
     * custom assignments to the `*_tangent` angle if `*_mode` is set to `TangentMode.FREE`.
     *
     * Generated from Godot docs: Curve.add_point
     */
    fun addPoint(position: Vector2, leftTangent: Double = 0.0, rightTangent: Double = 0.0, leftMode: Curve.TangentMode = Curve.TangentMode.FREE, rightMode: Curve.TangentMode = Curve.TangentMode.FREE): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2TwoDoubleTwoLongArgsRetInt(Binds.addPointBind, segment, position, leftTangent, rightTangent, leftMode.value, rightMode.value)
    }

    /**
     * Removes the point at `index` from the curve.
     *
     * Generated from Godot docs: Curve.remove_point
     */
    fun removePoint(index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removePointBind, segment, index)
    }

    /**
     * Removes all points from the curve.
     *
     * Generated from Godot docs: Curve.clear_points
     */
    fun clearPoints() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearPointsBind, segment)
    }

    /**
     * Returns the curve coordinates for the point at `index`.
     *
     * Generated from Godot docs: Curve.get_point_position
     */
    fun getPointPosition(index: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getPointPositionBind, segment, index)
    }

    /**
     * Assigns the vertical position `y` to the point at `index`.
     *
     * Generated from Godot docs: Curve.set_point_value
     */
    fun setPointValue(index: Int, y: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setPointValueBind, segment, index, y)
    }

    /**
     * Assigns the horizontal position `offset` to the point at `index`.
     *
     * Generated from Godot docs: Curve.set_point_offset
     */
    fun setPointOffset(index: Int, offset: Double): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndDoubleArgRetInt(Binds.setPointOffsetBind, segment, index, offset)
    }

    /**
     * Returns the Y value for the point that would exist at the X position `offset` along the curve.
     *
     * Generated from Godot docs: Curve.sample
     */
    fun sample(offset: Double): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithFloatArgRetFloat(Binds.sampleBind, segment, offset)
    }

    /**
     * Returns the Y value for the point that would exist at the X position `offset` along the curve
     * using the baked cache. Bakes the curve's points if not already baked.
     *
     * Generated from Godot docs: Curve.sample_baked
     */
    fun sampleBaked(offset: Double): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithFloatArgRetFloat(Binds.sampleBakedBind, segment, offset)
    }

    /**
     * Returns the left tangent angle (in degrees) for the point at `index`.
     *
     * Generated from Godot docs: Curve.get_point_left_tangent
     */
    fun getPointLeftTangent(index: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getPointLeftTangentBind, segment, index)
    }

    /**
     * Returns the right tangent angle (in degrees) for the point at `index`.
     *
     * Generated from Godot docs: Curve.get_point_right_tangent
     */
    fun getPointRightTangent(index: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getPointRightTangentBind, segment, index)
    }

    /**
     * Returns the left `TangentMode` for the point at `index`.
     *
     * Generated from Godot docs: Curve.get_point_left_mode
     */
    fun getPointLeftMode(index: Int): Curve.TangentMode {
        checkOpen()
        return Curve.TangentMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getPointLeftModeBind, segment, index))
    }

    /**
     * Returns the right `TangentMode` for the point at `index`.
     *
     * Generated from Godot docs: Curve.get_point_right_mode
     */
    fun getPointRightMode(index: Int): Curve.TangentMode {
        checkOpen()
        return Curve.TangentMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getPointRightModeBind, segment, index))
    }

    /**
     * Sets the left tangent angle for the point at `index` to `tangent`.
     *
     * Generated from Godot docs: Curve.set_point_left_tangent
     */
    fun setPointLeftTangent(index: Int, tangent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setPointLeftTangentBind, segment, index, tangent)
    }

    /**
     * Sets the right tangent angle for the point at `index` to `tangent`.
     *
     * Generated from Godot docs: Curve.set_point_right_tangent
     */
    fun setPointRightTangent(index: Int, tangent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setPointRightTangentBind, segment, index, tangent)
    }

    /**
     * Sets the left `TangentMode` for the point at `index` to `mode`.
     *
     * Generated from Godot docs: Curve.set_point_left_mode
     */
    fun setPointLeftMode(index: Int, mode: Curve.TangentMode) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setPointLeftModeBind, segment, index, mode.value)
    }

    /**
     * Sets the right `TangentMode` for the point at `index` to `mode`.
     *
     * Generated from Godot docs: Curve.set_point_right_mode
     */
    fun setPointRightMode(index: Int, mode: Curve.TangentMode) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setPointRightModeBind, segment, index, mode.value)
    }

    /**
     * The minimum value (y-coordinate) that points can have. Tangents can cause lower values between
     * points.
     *
     * Generated from Godot docs: Curve.get_min_value
     */
    fun getMinValue(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinValueBind, segment)
    }

    /**
     * The minimum value (y-coordinate) that points can have. Tangents can cause lower values between
     * points.
     *
     * Generated from Godot docs: Curve.set_min_value
     */
    fun setMinValue(min: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMinValueBind, segment, min)
    }

    /**
     * The maximum value (y-coordinate) that points can have. Tangents can cause higher values between
     * points.
     *
     * Generated from Godot docs: Curve.get_max_value
     */
    fun getMaxValue(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxValueBind, segment)
    }

    /**
     * The maximum value (y-coordinate) that points can have. Tangents can cause higher values between
     * points.
     *
     * Generated from Godot docs: Curve.set_max_value
     */
    fun setMaxValue(max: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxValueBind, segment, max)
    }

    /**
     * Returns the difference between `min_value` and `max_value`.
     *
     * Generated from Godot docs: Curve.get_value_range
     */
    fun getValueRange(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getValueRangeBind, segment)
    }

    /**
     * The minimum domain (x-coordinate) that points can have.
     *
     * Generated from Godot docs: Curve.get_min_domain
     */
    fun getMinDomain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinDomainBind, segment)
    }

    /**
     * The minimum domain (x-coordinate) that points can have.
     *
     * Generated from Godot docs: Curve.set_min_domain
     */
    fun setMinDomain(min: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMinDomainBind, segment, min)
    }

    /**
     * The maximum domain (x-coordinate) that points can have.
     *
     * Generated from Godot docs: Curve.get_max_domain
     */
    fun getMaxDomain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxDomainBind, segment)
    }

    /**
     * The maximum domain (x-coordinate) that points can have.
     *
     * Generated from Godot docs: Curve.set_max_domain
     */
    fun setMaxDomain(max: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxDomainBind, segment, max)
    }

    /**
     * Returns the difference between `min_domain` and `max_domain`.
     *
     * Generated from Godot docs: Curve.get_domain_range
     */
    fun getDomainRange(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainRangeBind, segment)
    }

    /**
     * Removes duplicate points, i.e. points that are less than 0.00001 units (engine epsilon value)
     * away from their neighbor on the curve.
     *
     * Generated from Godot docs: Curve.clean_dupes
     */
    fun cleanDupes() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.cleanDupesBind, segment)
    }

    /**
     * Recomputes the baked cache of points for the curve.
     *
     * Generated from Godot docs: Curve.bake
     */
    fun bake() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.bakeBind, segment)
    }

    /**
     * The number of points to include in the baked (i.e. cached) curve data.
     *
     * Generated from Godot docs: Curve.get_bake_resolution
     */
    fun getBakeResolution(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBakeResolutionBind, segment)
    }

    /**
     * The number of points to include in the baked (i.e. cached) curve data.
     *
     * Generated from Godot docs: Curve.set_bake_resolution
     */
    fun setBakeResolution(resolution: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBakeResolutionBind, segment, resolution)
    }

    /** Signal `range_changed()`; see [TypedSignal]. */
    val rangeChanged: Signal0
        @JvmName("rangeChangedTypedSignal")
        get() = Signal0(this, "range_changed")

    /** Signal `domain_changed()`; see [TypedSignal]. */
    val domainChanged: Signal0
        @JvmName("domainChangedTypedSignal")
        get() = Signal0(this, "domain_changed")

    object Signals {
        const val rangeChanged: String = "range_changed"
        const val domainChanged: String = "domain_changed"
    }

    /**
     * Godot's `Curve.TangentMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Curve.TangentMode.<NAME>`).
     *
     * Generated from Godot docs: Curve.TangentMode
     */
    @JvmInline
    value class TangentMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The tangent on this side of the point is user-defined.
             *
             * Generated from Godot docs: Curve.TANGENT_FREE
             */
            val FREE: TangentMode get() = TangentMode(0L)
            /**
             * The curve calculates the tangent on this side of the point as the slope halfway towards the
             * adjacent point.
             *
             * Generated from Godot docs: Curve.TANGENT_LINEAR
             */
            val LINEAR: TangentMode get() = TangentMode(1L)
            /**
             * The total number of available tangent modes.
             *
             * Generated from Godot docs: Curve.TANGENT_MODE_COUNT
             */
            val MODE_COUNT: TangentMode get() = TangentMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Curve? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Curve? =
            if (handle.address() == 0L) null else RefCounted.owned(Curve(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Curve? =
            if (handle.address() == 0L) null else Curve(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_POINT_COUNT_HASH = 3905245786L
        @JvmField
        val getPointCountBind =
            ObjectCalls.getMethodBind("Curve", "get_point_count", GET_POINT_COUNT_HASH)

        private const val SET_POINT_COUNT_HASH = 1286410249L
        @JvmField
        val setPointCountBind =
            ObjectCalls.getMethodBind("Curve", "set_point_count", SET_POINT_COUNT_HASH)

        private const val ADD_POINT_HASH = 434072736L
        @JvmField
        val addPointBind =
            ObjectCalls.getMethodBind("Curve", "add_point", ADD_POINT_HASH)

        private const val REMOVE_POINT_HASH = 1286410249L
        @JvmField
        val removePointBind =
            ObjectCalls.getMethodBind("Curve", "remove_point", REMOVE_POINT_HASH)

        private const val CLEAR_POINTS_HASH = 3218959716L
        @JvmField
        val clearPointsBind =
            ObjectCalls.getMethodBind("Curve", "clear_points", CLEAR_POINTS_HASH)

        private const val GET_POINT_POSITION_HASH = 2299179447L
        @JvmField
        val getPointPositionBind =
            ObjectCalls.getMethodBind("Curve", "get_point_position", GET_POINT_POSITION_HASH)

        private const val SET_POINT_VALUE_HASH = 1602489585L
        @JvmField
        val setPointValueBind =
            ObjectCalls.getMethodBind("Curve", "set_point_value", SET_POINT_VALUE_HASH)

        private const val SET_POINT_OFFSET_HASH = 3780573764L
        @JvmField
        val setPointOffsetBind =
            ObjectCalls.getMethodBind("Curve", "set_point_offset", SET_POINT_OFFSET_HASH)

        private const val SAMPLE_HASH = 3919130443L
        @JvmField
        val sampleBind =
            ObjectCalls.getMethodBind("Curve", "sample", SAMPLE_HASH)

        private const val SAMPLE_BAKED_HASH = 3919130443L
        @JvmField
        val sampleBakedBind =
            ObjectCalls.getMethodBind("Curve", "sample_baked", SAMPLE_BAKED_HASH)

        private const val GET_POINT_LEFT_TANGENT_HASH = 2339986948L
        @JvmField
        val getPointLeftTangentBind =
            ObjectCalls.getMethodBind("Curve", "get_point_left_tangent", GET_POINT_LEFT_TANGENT_HASH)

        private const val GET_POINT_RIGHT_TANGENT_HASH = 2339986948L
        @JvmField
        val getPointRightTangentBind =
            ObjectCalls.getMethodBind("Curve", "get_point_right_tangent", GET_POINT_RIGHT_TANGENT_HASH)

        private const val GET_POINT_LEFT_MODE_HASH = 426950354L
        @JvmField
        val getPointLeftModeBind =
            ObjectCalls.getMethodBind("Curve", "get_point_left_mode", GET_POINT_LEFT_MODE_HASH)

        private const val GET_POINT_RIGHT_MODE_HASH = 426950354L
        @JvmField
        val getPointRightModeBind =
            ObjectCalls.getMethodBind("Curve", "get_point_right_mode", GET_POINT_RIGHT_MODE_HASH)

        private const val SET_POINT_LEFT_TANGENT_HASH = 1602489585L
        @JvmField
        val setPointLeftTangentBind =
            ObjectCalls.getMethodBind("Curve", "set_point_left_tangent", SET_POINT_LEFT_TANGENT_HASH)

        private const val SET_POINT_RIGHT_TANGENT_HASH = 1602489585L
        @JvmField
        val setPointRightTangentBind =
            ObjectCalls.getMethodBind("Curve", "set_point_right_tangent", SET_POINT_RIGHT_TANGENT_HASH)

        private const val SET_POINT_LEFT_MODE_HASH = 1217242874L
        @JvmField
        val setPointLeftModeBind =
            ObjectCalls.getMethodBind("Curve", "set_point_left_mode", SET_POINT_LEFT_MODE_HASH)

        private const val SET_POINT_RIGHT_MODE_HASH = 1217242874L
        @JvmField
        val setPointRightModeBind =
            ObjectCalls.getMethodBind("Curve", "set_point_right_mode", SET_POINT_RIGHT_MODE_HASH)

        private const val GET_MIN_VALUE_HASH = 1740695150L
        @JvmField
        val getMinValueBind =
            ObjectCalls.getMethodBind("Curve", "get_min_value", GET_MIN_VALUE_HASH)

        private const val SET_MIN_VALUE_HASH = 373806689L
        @JvmField
        val setMinValueBind =
            ObjectCalls.getMethodBind("Curve", "set_min_value", SET_MIN_VALUE_HASH)

        private const val GET_MAX_VALUE_HASH = 1740695150L
        @JvmField
        val getMaxValueBind =
            ObjectCalls.getMethodBind("Curve", "get_max_value", GET_MAX_VALUE_HASH)

        private const val SET_MAX_VALUE_HASH = 373806689L
        @JvmField
        val setMaxValueBind =
            ObjectCalls.getMethodBind("Curve", "set_max_value", SET_MAX_VALUE_HASH)

        private const val GET_VALUE_RANGE_HASH = 1740695150L
        @JvmField
        val getValueRangeBind =
            ObjectCalls.getMethodBind("Curve", "get_value_range", GET_VALUE_RANGE_HASH)

        private const val GET_MIN_DOMAIN_HASH = 1740695150L
        @JvmField
        val getMinDomainBind =
            ObjectCalls.getMethodBind("Curve", "get_min_domain", GET_MIN_DOMAIN_HASH)

        private const val SET_MIN_DOMAIN_HASH = 373806689L
        @JvmField
        val setMinDomainBind =
            ObjectCalls.getMethodBind("Curve", "set_min_domain", SET_MIN_DOMAIN_HASH)

        private const val GET_MAX_DOMAIN_HASH = 1740695150L
        @JvmField
        val getMaxDomainBind =
            ObjectCalls.getMethodBind("Curve", "get_max_domain", GET_MAX_DOMAIN_HASH)

        private const val SET_MAX_DOMAIN_HASH = 373806689L
        @JvmField
        val setMaxDomainBind =
            ObjectCalls.getMethodBind("Curve", "set_max_domain", SET_MAX_DOMAIN_HASH)

        private const val GET_DOMAIN_RANGE_HASH = 1740695150L
        @JvmField
        val getDomainRangeBind =
            ObjectCalls.getMethodBind("Curve", "get_domain_range", GET_DOMAIN_RANGE_HASH)

        private const val CLEAN_DUPES_HASH = 3218959716L
        @JvmField
        val cleanDupesBind =
            ObjectCalls.getMethodBind("Curve", "clean_dupes", CLEAN_DUPES_HASH)

        private const val BAKE_HASH = 3218959716L
        @JvmField
        val bakeBind =
            ObjectCalls.getMethodBind("Curve", "bake", BAKE_HASH)

        private const val GET_BAKE_RESOLUTION_HASH = 3905245786L
        @JvmField
        val getBakeResolutionBind =
            ObjectCalls.getMethodBind("Curve", "get_bake_resolution", GET_BAKE_RESOLUTION_HASH)

        private const val SET_BAKE_RESOLUTION_HASH = 1286410249L
        @JvmField
        val setBakeResolutionBind =
            ObjectCalls.getMethodBind("Curve", "set_bake_resolution", SET_BAKE_RESOLUTION_HASH)
    }
}
