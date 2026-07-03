package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A 2D polyline that can optionally be textured.
 *
 * Generated from Godot docs: Line2D
 */
class Line2D(handle: MemorySegment) : Node2D(handle) {
    var points: List<Vector2>
        @JvmName("pointsProperty")
        get() = getPoints()
        @JvmName("setPointsProperty")
        set(value) = setPoints(value)

    var closed: Boolean
        @JvmName("closedProperty")
        get() = isClosed()
        @JvmName("setClosedProperty")
        set(value) = setClosed(value)

    var width: Double
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

    var widthCurve: Curve?
        @JvmName("widthCurveProperty")
        get() = getCurve()
        @JvmName("setWidthCurveProperty")
        set(value) = setCurve(value)

    var defaultColor: Color
        @JvmName("defaultColorProperty")
        get() = getDefaultColor()
        @JvmName("setDefaultColorProperty")
        set(value) = setDefaultColor(value)

    var gradient: Gradient?
        @JvmName("gradientProperty")
        get() = getGradient()
        @JvmName("setGradientProperty")
        set(value) = setGradient(value)

    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var textureMode: Long
        @JvmName("textureModeProperty")
        get() = getTextureMode()
        @JvmName("setTextureModeProperty")
        set(value) = setTextureMode(value)

    var jointMode: Long
        @JvmName("jointModeProperty")
        get() = getJointMode()
        @JvmName("setJointModeProperty")
        set(value) = setJointMode(value)

    var beginCapMode: Long
        @JvmName("beginCapModeProperty")
        get() = getBeginCapMode()
        @JvmName("setBeginCapModeProperty")
        set(value) = setBeginCapMode(value)

    var endCapMode: Long
        @JvmName("endCapModeProperty")
        get() = getEndCapMode()
        @JvmName("setEndCapModeProperty")
        set(value) = setEndCapMode(value)

    var sharpLimit: Double
        @JvmName("sharpLimitProperty")
        get() = getSharpLimit()
        @JvmName("setSharpLimitProperty")
        set(value) = setSharpLimit(value)

    var roundPrecision: Int
        @JvmName("roundPrecisionProperty")
        get() = getRoundPrecision()
        @JvmName("setRoundPrecisionProperty")
        set(value) = setRoundPrecision(value)

    var antialiased: Boolean
        @JvmName("antialiasedProperty")
        get() = getAntialiased()
        @JvmName("setAntialiasedProperty")
        set(value) = setAntialiased(value)

    /**
     * The points of the polyline, interpreted in local 2D coordinates. Segments are drawn between the
     * adjacent points in this array.
     *
     * Generated from Godot docs: Line2D.set_points
     */
    fun setPoints(points: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPointsBind, handle, points)
    }

    /**
     * The points of the polyline, interpreted in local 2D coordinates. Segments are drawn between the
     * adjacent points in this array.
     *
     * Generated from Godot docs: Line2D.get_points
     */
    fun getPoints(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPointsBind, handle)
    }

    /**
     * Overwrites the position of the point at the given `index` with the supplied `position`.
     *
     * Generated from Godot docs: Line2D.set_point_position
     */
    fun setPointPosition(index: Int, position: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setPointPositionBind, handle, index, position)
    }

    /**
     * Returns the position of the point at index `index`.
     *
     * Generated from Godot docs: Line2D.get_point_position
     */
    fun getPointPosition(index: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getPointPositionBind, handle, index)
    }

    /**
     * Returns the number of points in the polyline.
     *
     * Generated from Godot docs: Line2D.get_point_count
     */
    fun getPointCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPointCountBind, handle)
    }

    /**
     * Adds a point with the specified `position` relative to the polyline's own position. If no
     * `index` is provided, the new point will be added to the end of the points array. If `index` is
     * given, the new point is inserted before the existing point identified by index `index`. The
     * indices of the points after the new point get increased by 1. The provided `index` must not
     * exceed the number of existing points in the polyline. See `get_point_count`.
     *
     * Generated from Godot docs: Line2D.add_point
     */
    fun addPoint(position: Vector2, index: Int = -1) {
        ObjectCalls.ptrcallWithVector2AndIntArg(addPointBind, handle, position, index)
    }

    /**
     * Removes the point at index `index` from the polyline.
     *
     * Generated from Godot docs: Line2D.remove_point
     */
    fun removePoint(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removePointBind, handle, index)
    }

    /**
     * Removes all points from the polyline, making it empty.
     *
     * Generated from Godot docs: Line2D.clear_points
     */
    fun clearPoints() {
        ObjectCalls.ptrcallNoArgs(clearPointsBind, handle)
    }

    /**
     * If `true` and the polyline has more than 2 points, the last point and the first one will be
     * connected by a segment. Note: The shape of the closing segment is not guaranteed to be seamless
     * if a `width_curve` is provided. Note: The joint between the closing segment and the first
     * segment is drawn first and it samples the `gradient` and the `width_curve` at the beginning.
     * This is an implementation detail that might change in a future version.
     *
     * Generated from Godot docs: Line2D.set_closed
     */
    fun setClosed(closed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClosedBind, handle, closed)
    }

    /**
     * If `true` and the polyline has more than 2 points, the last point and the first one will be
     * connected by a segment. Note: The shape of the closing segment is not guaranteed to be seamless
     * if a `width_curve` is provided. Note: The joint between the closing segment and the first
     * segment is drawn first and it samples the `gradient` and the `width_curve` at the beginning.
     * This is an implementation detail that might change in a future version.
     *
     * Generated from Godot docs: Line2D.is_closed
     */
    fun isClosed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClosedBind, handle)
    }

    /**
     * The polyline's width.
     *
     * Generated from Godot docs: Line2D.set_width
     */
    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWidthBind, handle, width)
    }

    /**
     * The polyline's width.
     *
     * Generated from Godot docs: Line2D.get_width
     */
    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWidthBind, handle)
    }

    /**
     * The polyline's width curve. The width of the polyline over its length will be equivalent to the
     * value of the width curve over its domain. The width curve should be a unit `Curve`.
     *
     * Generated from Godot docs: Line2D.set_curve
     */
    fun setCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The polyline's width curve. The width of the polyline over its length will be equivalent to the
     * value of the width curve over its domain. The width curve should be a unit `Curve`.
     *
     * Generated from Godot docs: Line2D.get_curve
     */
    fun getCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, handle))
    }

    /**
     * The color of the polyline. Will not be used if a gradient is set.
     *
     * Generated from Godot docs: Line2D.set_default_color
     */
    fun setDefaultColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDefaultColorBind, handle, color)
    }

    /**
     * The color of the polyline. Will not be used if a gradient is set.
     *
     * Generated from Godot docs: Line2D.get_default_color
     */
    fun getDefaultColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDefaultColorBind, handle)
    }

    /**
     * The gradient is drawn through the whole line from start to finish. The `default_color` will not
     * be used if this property is set.
     *
     * Generated from Godot docs: Line2D.set_gradient
     */
    fun setGradient(color: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setGradientBind, handle, listOf(color?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The gradient is drawn through the whole line from start to finish. The `default_color` will not
     * be used if this property is set.
     *
     * Generated from Godot docs: Line2D.get_gradient
     */
    fun getGradient(): Gradient? {
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGradientBind, handle))
    }

    /**
     * The texture used for the polyline. Uses `texture_mode` for drawing style.
     *
     * Generated from Godot docs: Line2D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The texture used for the polyline. Uses `texture_mode` for drawing style.
     *
     * Generated from Godot docs: Line2D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    /**
     * The style to render the `texture` of the polyline.
     *
     * Generated from Godot docs: Line2D.set_texture_mode
     */
    fun setTextureMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureModeBind, handle, mode)
    }

    /**
     * The style to render the `texture` of the polyline.
     *
     * Generated from Godot docs: Line2D.get_texture_mode
     */
    fun getTextureMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureModeBind, handle)
    }

    /**
     * The style of the connections between segments of the polyline.
     *
     * Generated from Godot docs: Line2D.set_joint_mode
     */
    fun setJointMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setJointModeBind, handle, mode)
    }

    /**
     * The style of the connections between segments of the polyline.
     *
     * Generated from Godot docs: Line2D.get_joint_mode
     */
    fun getJointMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJointModeBind, handle)
    }

    /**
     * The style of the beginning of the polyline, if `closed` is `false`.
     *
     * Generated from Godot docs: Line2D.set_begin_cap_mode
     */
    fun setBeginCapMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBeginCapModeBind, handle, mode)
    }

    /**
     * The style of the beginning of the polyline, if `closed` is `false`.
     *
     * Generated from Godot docs: Line2D.get_begin_cap_mode
     */
    fun getBeginCapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBeginCapModeBind, handle)
    }

    /**
     * The style of the end of the polyline, if `closed` is `false`.
     *
     * Generated from Godot docs: Line2D.set_end_cap_mode
     */
    fun setEndCapMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setEndCapModeBind, handle, mode)
    }

    /**
     * The style of the end of the polyline, if `closed` is `false`.
     *
     * Generated from Godot docs: Line2D.get_end_cap_mode
     */
    fun getEndCapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEndCapModeBind, handle)
    }

    /**
     * Determines the miter limit of the polyline. Normally, when `joint_mode` is set to
     * `LINE_JOINT_SHARP`, sharp angles fall back to using the logic of `LINE_JOINT_BEVEL` joints to
     * prevent very long miters. Higher values of this property mean that the fallback to a bevel joint
     * will happen at sharper angles.
     *
     * Generated from Godot docs: Line2D.set_sharp_limit
     */
    fun setSharpLimit(limit: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSharpLimitBind, handle, limit)
    }

    /**
     * Determines the miter limit of the polyline. Normally, when `joint_mode` is set to
     * `LINE_JOINT_SHARP`, sharp angles fall back to using the logic of `LINE_JOINT_BEVEL` joints to
     * prevent very long miters. Higher values of this property mean that the fallback to a bevel joint
     * will happen at sharper angles.
     *
     * Generated from Godot docs: Line2D.get_sharp_limit
     */
    fun getSharpLimit(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSharpLimitBind, handle)
    }

    /**
     * The smoothness used for rounded joints and caps. Higher values result in smoother corners, but
     * are more demanding to render and update.
     *
     * Generated from Godot docs: Line2D.set_round_precision
     */
    fun setRoundPrecision(precision: Int) {
        ObjectCalls.ptrcallWithIntArg(setRoundPrecisionBind, handle, precision)
    }

    /**
     * The smoothness used for rounded joints and caps. Higher values result in smoother corners, but
     * are more demanding to render and update.
     *
     * Generated from Godot docs: Line2D.get_round_precision
     */
    fun getRoundPrecision(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRoundPrecisionBind, handle)
    }

    /**
     * If `true`, the polyline's border will be anti-aliased. Note: `Line2D` is not accelerated by
     * batching when being anti-aliased.
     *
     * Generated from Godot docs: Line2D.set_antialiased
     */
    fun setAntialiased(antialiased: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAntialiasedBind, handle, antialiased)
    }

    /**
     * If `true`, the polyline's border will be anti-aliased. Note: `Line2D` is not accelerated by
     * batching when being anti-aliased.
     *
     * Generated from Godot docs: Line2D.get_antialiased
     */
    fun getAntialiased(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAntialiasedBind, handle)
    }

    companion object {
        const val LINE_JOINT_SHARP: Long = 0L
        const val LINE_JOINT_BEVEL: Long = 1L
        const val LINE_JOINT_ROUND: Long = 2L
        const val LINE_CAP_NONE: Long = 0L
        const val LINE_CAP_BOX: Long = 1L
        const val LINE_CAP_ROUND: Long = 2L
        const val LINE_TEXTURE_NONE: Long = 0L
        const val LINE_TEXTURE_TILE: Long = 1L
        const val LINE_TEXTURE_STRETCH: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Line2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Line2D? =
            if (handle.address() == 0L) null else Line2D(handle)

        private const val SET_POINTS_HASH = 1509147220L
        private val setPointsBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_points", SET_POINTS_HASH)
        }

        private const val GET_POINTS_HASH = 2961356807L
        private val getPointsBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_points", GET_POINTS_HASH)
        }

        private const val SET_POINT_POSITION_HASH = 163021252L
        private val setPointPositionBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_point_position", SET_POINT_POSITION_HASH)
        }

        private const val GET_POINT_POSITION_HASH = 2299179447L
        private val getPointPositionBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_point_position", GET_POINT_POSITION_HASH)
        }

        private const val GET_POINT_COUNT_HASH = 3905245786L
        private val getPointCountBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_point_count", GET_POINT_COUNT_HASH)
        }

        private const val ADD_POINT_HASH = 2654014372L
        private val addPointBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "add_point", ADD_POINT_HASH)
        }

        private const val REMOVE_POINT_HASH = 1286410249L
        private val removePointBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "remove_point", REMOVE_POINT_HASH)
        }

        private const val CLEAR_POINTS_HASH = 3218959716L
        private val clearPointsBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "clear_points", CLEAR_POINTS_HASH)
        }

        private const val SET_CLOSED_HASH = 2586408642L
        private val setClosedBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_closed", SET_CLOSED_HASH)
        }

        private const val IS_CLOSED_HASH = 36873697L
        private val isClosedBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "is_closed", IS_CLOSED_HASH)
        }

        private const val SET_WIDTH_HASH = 373806689L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_width", SET_WIDTH_HASH)
        }

        private const val GET_WIDTH_HASH = 1740695150L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_width", GET_WIDTH_HASH)
        }

        private const val SET_CURVE_HASH = 270443179L
        private val setCurveBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_curve", SET_CURVE_HASH)
        }

        private const val GET_CURVE_HASH = 2460114913L
        private val getCurveBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_curve", GET_CURVE_HASH)
        }

        private const val SET_DEFAULT_COLOR_HASH = 2920490490L
        private val setDefaultColorBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_default_color", SET_DEFAULT_COLOR_HASH)
        }

        private const val GET_DEFAULT_COLOR_HASH = 3444240500L
        private val getDefaultColorBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_default_color", GET_DEFAULT_COLOR_HASH)
        }

        private const val SET_GRADIENT_HASH = 2756054477L
        private val setGradientBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_gradient", SET_GRADIENT_HASH)
        }

        private const val GET_GRADIENT_HASH = 132272999L
        private val getGradientBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_gradient", GET_GRADIENT_HASH)
        }

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_TEXTURE_MODE_HASH = 1952559516L
        private val setTextureModeBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_texture_mode", SET_TEXTURE_MODE_HASH)
        }

        private const val GET_TEXTURE_MODE_HASH = 2341040722L
        private val getTextureModeBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_texture_mode", GET_TEXTURE_MODE_HASH)
        }

        private const val SET_JOINT_MODE_HASH = 604292979L
        private val setJointModeBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_joint_mode", SET_JOINT_MODE_HASH)
        }

        private const val GET_JOINT_MODE_HASH = 2546544037L
        private val getJointModeBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_joint_mode", GET_JOINT_MODE_HASH)
        }

        private const val SET_BEGIN_CAP_MODE_HASH = 1669024546L
        private val setBeginCapModeBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_begin_cap_mode", SET_BEGIN_CAP_MODE_HASH)
        }

        private const val GET_BEGIN_CAP_MODE_HASH = 1107511441L
        private val getBeginCapModeBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_begin_cap_mode", GET_BEGIN_CAP_MODE_HASH)
        }

        private const val SET_END_CAP_MODE_HASH = 1669024546L
        private val setEndCapModeBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_end_cap_mode", SET_END_CAP_MODE_HASH)
        }

        private const val GET_END_CAP_MODE_HASH = 1107511441L
        private val getEndCapModeBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_end_cap_mode", GET_END_CAP_MODE_HASH)
        }

        private const val SET_SHARP_LIMIT_HASH = 373806689L
        private val setSharpLimitBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_sharp_limit", SET_SHARP_LIMIT_HASH)
        }

        private const val GET_SHARP_LIMIT_HASH = 1740695150L
        private val getSharpLimitBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_sharp_limit", GET_SHARP_LIMIT_HASH)
        }

        private const val SET_ROUND_PRECISION_HASH = 1286410249L
        private val setRoundPrecisionBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_round_precision", SET_ROUND_PRECISION_HASH)
        }

        private const val GET_ROUND_PRECISION_HASH = 3905245786L
        private val getRoundPrecisionBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_round_precision", GET_ROUND_PRECISION_HASH)
        }

        private const val SET_ANTIALIASED_HASH = 2586408642L
        private val setAntialiasedBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "set_antialiased", SET_ANTIALIASED_HASH)
        }

        private const val GET_ANTIALIASED_HASH = 36873697L
        private val getAntialiasedBind by lazy {
            ObjectCalls.getMethodBind("Line2D", "get_antialiased", GET_ANTIALIASED_HASH)
        }
    }
}
