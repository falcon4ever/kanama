package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A 2D polyline that can optionally be textured.
 *
 * Generated from Godot docs: Line2D
 */
class Line2D(handle: GodotHandle) : Node2D(handle) {
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

    var textureMode: Line2D.LineTextureMode
        @JvmName("textureModeProperty")
        get() = getTextureMode()
        @JvmName("setTextureModeProperty")
        set(value) = setTextureMode(value)

    var jointMode: Line2D.LineJointMode
        @JvmName("jointModeProperty")
        get() = getJointMode()
        @JvmName("setJointModeProperty")
        set(value) = setJointMode(value)

    var beginCapMode: Line2D.LineCapMode
        @JvmName("beginCapModeProperty")
        get() = getBeginCapMode()
        @JvmName("setBeginCapModeProperty")
        set(value) = setBeginCapMode(value)

    var endCapMode: Line2D.LineCapMode
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
        ObjectCalls.ptrcallWithPackedVector2ListArg(Binds.setPointsBind, segment, points)
    }

    /**
     * The points of the polyline, interpreted in local 2D coordinates. Segments are drawn between the
     * adjacent points in this array.
     *
     * Generated from Godot docs: Line2D.get_points
     */
    fun getPoints(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(Binds.getPointsBind, segment)
    }

    /**
     * Overwrites the position of the point at the given `index` with the supplied `position`.
     *
     * Generated from Godot docs: Line2D.set_point_position
     */
    fun setPointPosition(index: Int, position: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setPointPositionBind, segment, index, position)
    }

    /**
     * Returns the position of the point at index `index`.
     *
     * Generated from Godot docs: Line2D.get_point_position
     */
    fun getPointPosition(index: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getPointPositionBind, segment, index)
    }

    /**
     * Returns the number of points in the polyline.
     *
     * Generated from Godot docs: Line2D.get_point_count
     */
    fun getPointCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPointCountBind, segment)
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
        ObjectCalls.ptrcallWithVector2AndIntArg(Binds.addPointBind, segment, position, index)
    }

    /**
     * Removes the point at index `index` from the polyline.
     *
     * Generated from Godot docs: Line2D.remove_point
     */
    fun removePoint(index: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.removePointBind, segment, index)
    }

    /**
     * Removes all points from the polyline, making it empty.
     *
     * Generated from Godot docs: Line2D.clear_points
     */
    fun clearPoints() {
        ObjectCalls.ptrcallNoArgs(Binds.clearPointsBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setClosedBind, segment, closed)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isClosedBind, segment)
    }

    /**
     * The polyline's width.
     *
     * Generated from Godot docs: Line2D.set_width
     */
    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWidthBind, segment, width)
    }

    /**
     * The polyline's width.
     *
     * Generated from Godot docs: Line2D.get_width
     */
    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWidthBind, segment)
    }

    /**
     * The polyline's width curve. The width of the polyline over its length will be equivalent to the
     * value of the width curve over its domain. The width curve should be a unit `Curve`.
     *
     * Generated from Godot docs: Line2D.set_curve
     */
    fun setCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The polyline's width curve. The width of the polyline over its length will be equivalent to the
     * value of the width curve over its domain. The width curve should be a unit `Curve`.
     *
     * Generated from Godot docs: Line2D.get_curve
     */
    fun getCurve(): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurveBind, segment))
    }

    /**
     * The color of the polyline. Will not be used if a gradient is set.
     *
     * Generated from Godot docs: Line2D.set_default_color
     */
    fun setDefaultColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setDefaultColorBind, segment, color)
    }

    /**
     * The color of the polyline. Will not be used if a gradient is set.
     *
     * Generated from Godot docs: Line2D.get_default_color
     */
    fun getDefaultColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDefaultColorBind, segment)
    }

    /**
     * The gradient is drawn through the whole line from start to finish. The `default_color` will not
     * be used if this property is set.
     *
     * Generated from Godot docs: Line2D.set_gradient
     */
    fun setGradient(color: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setGradientBind, segment, listOf(color?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The gradient is drawn through the whole line from start to finish. The `default_color` will not
     * be used if this property is set.
     *
     * Generated from Godot docs: Line2D.get_gradient
     */
    fun getGradient(): Gradient? {
        return Gradient.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGradientBind, segment))
    }

    /**
     * The texture used for the polyline. Uses `texture_mode` for drawing style.
     *
     * Generated from Godot docs: Line2D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The texture used for the polyline. Uses `texture_mode` for drawing style.
     *
     * Generated from Godot docs: Line2D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    /**
     * The style to render the `texture` of the polyline.
     *
     * Generated from Godot docs: Line2D.set_texture_mode
     */
    fun setTextureMode(mode: Line2D.LineTextureMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureModeBind, segment, mode.value)
    }

    /**
     * The style to render the `texture` of the polyline.
     *
     * Generated from Godot docs: Line2D.get_texture_mode
     */
    fun getTextureMode(): Line2D.LineTextureMode {
        return Line2D.LineTextureMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureModeBind, segment))
    }

    /**
     * The style of the connections between segments of the polyline.
     *
     * Generated from Godot docs: Line2D.set_joint_mode
     */
    fun setJointMode(mode: Line2D.LineJointMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setJointModeBind, segment, mode.value)
    }

    /**
     * The style of the connections between segments of the polyline.
     *
     * Generated from Godot docs: Line2D.get_joint_mode
     */
    fun getJointMode(): Line2D.LineJointMode {
        return Line2D.LineJointMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getJointModeBind, segment))
    }

    /**
     * The style of the beginning of the polyline, if `closed` is `false`.
     *
     * Generated from Godot docs: Line2D.set_begin_cap_mode
     */
    fun setBeginCapMode(mode: Line2D.LineCapMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBeginCapModeBind, segment, mode.value)
    }

    /**
     * The style of the beginning of the polyline, if `closed` is `false`.
     *
     * Generated from Godot docs: Line2D.get_begin_cap_mode
     */
    fun getBeginCapMode(): Line2D.LineCapMode {
        return Line2D.LineCapMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBeginCapModeBind, segment))
    }

    /**
     * The style of the end of the polyline, if `closed` is `false`.
     *
     * Generated from Godot docs: Line2D.set_end_cap_mode
     */
    fun setEndCapMode(mode: Line2D.LineCapMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setEndCapModeBind, segment, mode.value)
    }

    /**
     * The style of the end of the polyline, if `closed` is `false`.
     *
     * Generated from Godot docs: Line2D.get_end_cap_mode
     */
    fun getEndCapMode(): Line2D.LineCapMode {
        return Line2D.LineCapMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getEndCapModeBind, segment))
    }

    /**
     * Determines the miter limit of the polyline. Normally, when `joint_mode` is set to
     * `LineJointMode.SHARP`, sharp angles fall back to using the logic of `LineJointMode.BEVEL` joints
     * to prevent very long miters. Higher values of this property mean that the fallback to a bevel
     * joint will happen at sharper angles.
     *
     * Generated from Godot docs: Line2D.set_sharp_limit
     */
    fun setSharpLimit(limit: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSharpLimitBind, segment, limit)
    }

    /**
     * Determines the miter limit of the polyline. Normally, when `joint_mode` is set to
     * `LineJointMode.SHARP`, sharp angles fall back to using the logic of `LineJointMode.BEVEL` joints
     * to prevent very long miters. Higher values of this property mean that the fallback to a bevel
     * joint will happen at sharper angles.
     *
     * Generated from Godot docs: Line2D.get_sharp_limit
     */
    fun getSharpLimit(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSharpLimitBind, segment)
    }

    /**
     * The smoothness used for rounded joints and caps. Higher values result in smoother corners, but
     * are more demanding to render and update.
     *
     * Generated from Godot docs: Line2D.set_round_precision
     */
    fun setRoundPrecision(precision: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setRoundPrecisionBind, segment, precision)
    }

    /**
     * The smoothness used for rounded joints and caps. Higher values result in smoother corners, but
     * are more demanding to render and update.
     *
     * Generated from Godot docs: Line2D.get_round_precision
     */
    fun getRoundPrecision(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRoundPrecisionBind, segment)
    }

    /**
     * If `true`, the polyline's border will be anti-aliased. Note: `Line2D` is not accelerated by
     * batching when being anti-aliased.
     *
     * Generated from Godot docs: Line2D.set_antialiased
     */
    fun setAntialiased(antialiased: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAntialiasedBind, segment, antialiased)
    }

    /**
     * If `true`, the polyline's border will be anti-aliased. Note: `Line2D` is not accelerated by
     * batching when being anti-aliased.
     *
     * Generated from Godot docs: Line2D.get_antialiased
     */
    fun getAntialiased(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAntialiasedBind, segment)
    }

    /**
     * Godot's `Line2D.LineJointMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Line2D.LineJointMode.<NAME>`).
     *
     * Generated from Godot docs: Line2D.LineJointMode
     */
    @JvmInline
    value class LineJointMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Makes the polyline's joints pointy, connecting the sides of the two segments by extending them
             * until they intersect. If the rotation of a joint is too big (based on `sharp_limit`), the joint
             * falls back to `LineJointMode.BEVEL` to prevent very long miters.
             *
             * Generated from Godot docs: Line2D.LINE_JOINT_SHARP
             */
            val SHARP: LineJointMode get() = LineJointMode(0L)
            /**
             * Makes the polyline's joints bevelled/chamfered, connecting the sides of the two segments with a
             * simple line.
             *
             * Generated from Godot docs: Line2D.LINE_JOINT_BEVEL
             */
            val BEVEL: LineJointMode get() = LineJointMode(1L)
            /**
             * Makes the polyline's joints rounded, connecting the sides of the two segments with an arc. The
             * detail of this arc depends on `round_precision`.
             *
             * Generated from Godot docs: Line2D.LINE_JOINT_ROUND
             */
            val ROUND: LineJointMode get() = LineJointMode(2L)
        }
    }

    /**
     * Godot's `Line2D.LineCapMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Line2D.LineCapMode.<NAME>`).
     *
     * Generated from Godot docs: Line2D.LineCapMode
     */
    @JvmInline
    value class LineCapMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Draws no line cap.
             *
             * Generated from Godot docs: Line2D.LINE_CAP_NONE
             */
            val NONE: LineCapMode get() = LineCapMode(0L)
            /**
             * Draws the line cap as a box, slightly extending the first/last segment.
             *
             * Generated from Godot docs: Line2D.LINE_CAP_BOX
             */
            val BOX: LineCapMode get() = LineCapMode(1L)
            /**
             * Draws the line cap as a semicircle attached to the first/last segment.
             *
             * Generated from Godot docs: Line2D.LINE_CAP_ROUND
             */
            val ROUND: LineCapMode get() = LineCapMode(2L)
        }
    }

    /**
     * Godot's `Line2D.LineTextureMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Line2D.LineTextureMode.<NAME>`).
     *
     * Generated from Godot docs: Line2D.LineTextureMode
     */
    @JvmInline
    value class LineTextureMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Takes the left pixels of the texture and renders them over the whole polyline.
             *
             * Generated from Godot docs: Line2D.LINE_TEXTURE_NONE
             */
            val NONE: LineTextureMode get() = LineTextureMode(0L)
            /**
             * Tiles the texture over the polyline. `CanvasItem.texture_repeat` of the `Line2D` node must be
             * `CanvasItem.TextureRepeat.ENABLED` or `CanvasItem.TextureRepeat.MIRROR` for it to work properly.
             *
             * Generated from Godot docs: Line2D.LINE_TEXTURE_TILE
             */
            val TILE: LineTextureMode get() = LineTextureMode(1L)
            /**
             * Stretches the texture across the polyline. `CanvasItem.texture_repeat` of the `Line2D` node must
             * be `CanvasItem.TextureRepeat.DISABLED` for best results.
             *
             * Generated from Godot docs: Line2D.LINE_TEXTURE_STRETCH
             */
            val STRETCH: LineTextureMode get() = LineTextureMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Line2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Line2D? =
            if (handle.address() == 0L) null else Line2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_POINTS_HASH = 1509147220L
        @JvmField
        val setPointsBind =
            ObjectCalls.getMethodBind("Line2D", "set_points", SET_POINTS_HASH)

        private const val GET_POINTS_HASH = 2961356807L
        @JvmField
        val getPointsBind =
            ObjectCalls.getMethodBind("Line2D", "get_points", GET_POINTS_HASH)

        private const val SET_POINT_POSITION_HASH = 163021252L
        @JvmField
        val setPointPositionBind =
            ObjectCalls.getMethodBind("Line2D", "set_point_position", SET_POINT_POSITION_HASH)

        private const val GET_POINT_POSITION_HASH = 2299179447L
        @JvmField
        val getPointPositionBind =
            ObjectCalls.getMethodBind("Line2D", "get_point_position", GET_POINT_POSITION_HASH)

        private const val GET_POINT_COUNT_HASH = 3905245786L
        @JvmField
        val getPointCountBind =
            ObjectCalls.getMethodBind("Line2D", "get_point_count", GET_POINT_COUNT_HASH)

        private const val ADD_POINT_HASH = 2654014372L
        @JvmField
        val addPointBind =
            ObjectCalls.getMethodBind("Line2D", "add_point", ADD_POINT_HASH)

        private const val REMOVE_POINT_HASH = 1286410249L
        @JvmField
        val removePointBind =
            ObjectCalls.getMethodBind("Line2D", "remove_point", REMOVE_POINT_HASH)

        private const val CLEAR_POINTS_HASH = 3218959716L
        @JvmField
        val clearPointsBind =
            ObjectCalls.getMethodBind("Line2D", "clear_points", CLEAR_POINTS_HASH)

        private const val SET_CLOSED_HASH = 2586408642L
        @JvmField
        val setClosedBind =
            ObjectCalls.getMethodBind("Line2D", "set_closed", SET_CLOSED_HASH)

        private const val IS_CLOSED_HASH = 36873697L
        @JvmField
        val isClosedBind =
            ObjectCalls.getMethodBind("Line2D", "is_closed", IS_CLOSED_HASH)

        private const val SET_WIDTH_HASH = 373806689L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("Line2D", "set_width", SET_WIDTH_HASH)

        private const val GET_WIDTH_HASH = 1740695150L
        @JvmField
        val getWidthBind =
            ObjectCalls.getMethodBind("Line2D", "get_width", GET_WIDTH_HASH)

        private const val SET_CURVE_HASH = 270443179L
        @JvmField
        val setCurveBind =
            ObjectCalls.getMethodBind("Line2D", "set_curve", SET_CURVE_HASH)

        private const val GET_CURVE_HASH = 2460114913L
        @JvmField
        val getCurveBind =
            ObjectCalls.getMethodBind("Line2D", "get_curve", GET_CURVE_HASH)

        private const val SET_DEFAULT_COLOR_HASH = 2920490490L
        @JvmField
        val setDefaultColorBind =
            ObjectCalls.getMethodBind("Line2D", "set_default_color", SET_DEFAULT_COLOR_HASH)

        private const val GET_DEFAULT_COLOR_HASH = 3444240500L
        @JvmField
        val getDefaultColorBind =
            ObjectCalls.getMethodBind("Line2D", "get_default_color", GET_DEFAULT_COLOR_HASH)

        private const val SET_GRADIENT_HASH = 2756054477L
        @JvmField
        val setGradientBind =
            ObjectCalls.getMethodBind("Line2D", "set_gradient", SET_GRADIENT_HASH)

        private const val GET_GRADIENT_HASH = 132272999L
        @JvmField
        val getGradientBind =
            ObjectCalls.getMethodBind("Line2D", "get_gradient", GET_GRADIENT_HASH)

        private const val SET_TEXTURE_HASH = 4051416890L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("Line2D", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 3635182373L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("Line2D", "get_texture", GET_TEXTURE_HASH)

        private const val SET_TEXTURE_MODE_HASH = 1952559516L
        @JvmField
        val setTextureModeBind =
            ObjectCalls.getMethodBind("Line2D", "set_texture_mode", SET_TEXTURE_MODE_HASH)

        private const val GET_TEXTURE_MODE_HASH = 2341040722L
        @JvmField
        val getTextureModeBind =
            ObjectCalls.getMethodBind("Line2D", "get_texture_mode", GET_TEXTURE_MODE_HASH)

        private const val SET_JOINT_MODE_HASH = 604292979L
        @JvmField
        val setJointModeBind =
            ObjectCalls.getMethodBind("Line2D", "set_joint_mode", SET_JOINT_MODE_HASH)

        private const val GET_JOINT_MODE_HASH = 2546544037L
        @JvmField
        val getJointModeBind =
            ObjectCalls.getMethodBind("Line2D", "get_joint_mode", GET_JOINT_MODE_HASH)

        private const val SET_BEGIN_CAP_MODE_HASH = 1669024546L
        @JvmField
        val setBeginCapModeBind =
            ObjectCalls.getMethodBind("Line2D", "set_begin_cap_mode", SET_BEGIN_CAP_MODE_HASH)

        private const val GET_BEGIN_CAP_MODE_HASH = 1107511441L
        @JvmField
        val getBeginCapModeBind =
            ObjectCalls.getMethodBind("Line2D", "get_begin_cap_mode", GET_BEGIN_CAP_MODE_HASH)

        private const val SET_END_CAP_MODE_HASH = 1669024546L
        @JvmField
        val setEndCapModeBind =
            ObjectCalls.getMethodBind("Line2D", "set_end_cap_mode", SET_END_CAP_MODE_HASH)

        private const val GET_END_CAP_MODE_HASH = 1107511441L
        @JvmField
        val getEndCapModeBind =
            ObjectCalls.getMethodBind("Line2D", "get_end_cap_mode", GET_END_CAP_MODE_HASH)

        private const val SET_SHARP_LIMIT_HASH = 373806689L
        @JvmField
        val setSharpLimitBind =
            ObjectCalls.getMethodBind("Line2D", "set_sharp_limit", SET_SHARP_LIMIT_HASH)

        private const val GET_SHARP_LIMIT_HASH = 1740695150L
        @JvmField
        val getSharpLimitBind =
            ObjectCalls.getMethodBind("Line2D", "get_sharp_limit", GET_SHARP_LIMIT_HASH)

        private const val SET_ROUND_PRECISION_HASH = 1286410249L
        @JvmField
        val setRoundPrecisionBind =
            ObjectCalls.getMethodBind("Line2D", "set_round_precision", SET_ROUND_PRECISION_HASH)

        private const val GET_ROUND_PRECISION_HASH = 3905245786L
        @JvmField
        val getRoundPrecisionBind =
            ObjectCalls.getMethodBind("Line2D", "get_round_precision", GET_ROUND_PRECISION_HASH)

        private const val SET_ANTIALIASED_HASH = 2586408642L
        @JvmField
        val setAntialiasedBind =
            ObjectCalls.getMethodBind("Line2D", "set_antialiased", SET_ANTIALIASED_HASH)

        private const val GET_ANTIALIASED_HASH = 36873697L
        @JvmField
        val getAntialiasedBind =
            ObjectCalls.getMethodBind("Line2D", "get_antialiased", GET_ANTIALIASED_HASH)
    }
}
