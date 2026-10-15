package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A customizable `StyleBox` that doesn't use a texture.
 *
 * Generated from Godot docs: StyleBoxFlat
 */
class StyleBoxFlat(handle: GodotHandle) : StyleBox(handle) {
    var bgColor: Color
        @JvmName("bgColorProperty")
        get() = getBgColor()
        @JvmName("setBgColorProperty")
        set(value) = setBgColor(value)

    var drawCenter: Boolean
        @JvmName("drawCenterProperty")
        get() = isDrawCenterEnabled()
        @JvmName("setDrawCenterProperty")
        set(value) = setDrawCenter(value)

    var skew: Vector2
        @JvmName("skewProperty")
        get() = getSkew()
        @JvmName("setSkewProperty")
        set(value) = setSkew(value)

    var borderWidthLeft: Int
        @JvmName("borderWidthLeftProperty")
        get() = getBorderWidth(Side.LEFT)
        @JvmName("setBorderWidthLeftProperty")
        set(value) = setBorderWidth(Side.LEFT, value)

    var borderWidthTop: Int
        @JvmName("borderWidthTopProperty")
        get() = getBorderWidth(Side.TOP)
        @JvmName("setBorderWidthTopProperty")
        set(value) = setBorderWidth(Side.TOP, value)

    var borderWidthRight: Int
        @JvmName("borderWidthRightProperty")
        get() = getBorderWidth(Side.RIGHT)
        @JvmName("setBorderWidthRightProperty")
        set(value) = setBorderWidth(Side.RIGHT, value)

    var borderWidthBottom: Int
        @JvmName("borderWidthBottomProperty")
        get() = getBorderWidth(Side.BOTTOM)
        @JvmName("setBorderWidthBottomProperty")
        set(value) = setBorderWidth(Side.BOTTOM, value)

    var borderColor: Color
        @JvmName("borderColorProperty")
        get() = getBorderColor()
        @JvmName("setBorderColorProperty")
        set(value) = setBorderColor(value)

    var borderBlend: Boolean
        @JvmName("borderBlendProperty")
        get() = getBorderBlend()
        @JvmName("setBorderBlendProperty")
        set(value) = setBorderBlend(value)

    var cornerRadiusTopLeft: Int
        @JvmName("cornerRadiusTopLeftProperty")
        get() = getCornerRadius(Corner.TOP_LEFT)
        @JvmName("setCornerRadiusTopLeftProperty")
        set(value) = setCornerRadius(Corner.TOP_LEFT, value)

    var cornerRadiusTopRight: Int
        @JvmName("cornerRadiusTopRightProperty")
        get() = getCornerRadius(Corner.TOP_RIGHT)
        @JvmName("setCornerRadiusTopRightProperty")
        set(value) = setCornerRadius(Corner.TOP_RIGHT, value)

    var cornerRadiusBottomRight: Int
        @JvmName("cornerRadiusBottomRightProperty")
        get() = getCornerRadius(Corner.BOTTOM_RIGHT)
        @JvmName("setCornerRadiusBottomRightProperty")
        set(value) = setCornerRadius(Corner.BOTTOM_RIGHT, value)

    var cornerRadiusBottomLeft: Int
        @JvmName("cornerRadiusBottomLeftProperty")
        get() = getCornerRadius(Corner.BOTTOM_LEFT)
        @JvmName("setCornerRadiusBottomLeftProperty")
        set(value) = setCornerRadius(Corner.BOTTOM_LEFT, value)

    var cornerDetail: Int
        @JvmName("cornerDetailProperty")
        get() = getCornerDetail()
        @JvmName("setCornerDetailProperty")
        set(value) = setCornerDetail(value)

    var expandMarginLeft: Double
        @JvmName("expandMarginLeftProperty")
        get() = getExpandMargin(Side.LEFT)
        @JvmName("setExpandMarginLeftProperty")
        set(value) = setExpandMargin(Side.LEFT, value)

    var expandMarginTop: Double
        @JvmName("expandMarginTopProperty")
        get() = getExpandMargin(Side.TOP)
        @JvmName("setExpandMarginTopProperty")
        set(value) = setExpandMargin(Side.TOP, value)

    var expandMarginRight: Double
        @JvmName("expandMarginRightProperty")
        get() = getExpandMargin(Side.RIGHT)
        @JvmName("setExpandMarginRightProperty")
        set(value) = setExpandMargin(Side.RIGHT, value)

    var expandMarginBottom: Double
        @JvmName("expandMarginBottomProperty")
        get() = getExpandMargin(Side.BOTTOM)
        @JvmName("setExpandMarginBottomProperty")
        set(value) = setExpandMargin(Side.BOTTOM, value)

    var shadowColor: Color
        @JvmName("shadowColorProperty")
        get() = getShadowColor()
        @JvmName("setShadowColorProperty")
        set(value) = setShadowColor(value)

    var shadowSize: Int
        @JvmName("shadowSizeProperty")
        get() = getShadowSize()
        @JvmName("setShadowSizeProperty")
        set(value) = setShadowSize(value)

    var shadowOffset: Vector2
        @JvmName("shadowOffsetProperty")
        get() = getShadowOffset()
        @JvmName("setShadowOffsetProperty")
        set(value) = setShadowOffset(value)

    var antiAliasing: Boolean
        @JvmName("antiAliasingProperty")
        get() = isAntiAliased()
        @JvmName("setAntiAliasingProperty")
        set(value) = setAntiAliased(value)

    var antiAliasingSize: Double
        @JvmName("antiAliasingSizeProperty")
        get() = getAaSize()
        @JvmName("setAntiAliasingSizeProperty")
        set(value) = setAaSize(value)

    /**
     * The background color of the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.set_bg_color
     */
    fun setBgColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setBgColorBind, segment, color)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The background color of the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.get_bg_color
     */
    fun getBgColor(): Color {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetColor(Binds.getBgColorBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the color of the border.
     *
     * Generated from Godot docs: StyleBoxFlat.set_border_color
     */
    fun setBorderColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setBorderColorBind, segment, color)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the color of the border.
     *
     * Generated from Godot docs: StyleBoxFlat.get_border_color
     */
    fun getBorderColor(): Color {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetColor(Binds.getBorderColorBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the border width to `width` pixels for all sides.
     *
     * Generated from Godot docs: StyleBoxFlat.set_border_width_all
     */
    fun setBorderWidthAll(width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBorderWidthAllBind, segment, width)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the smallest border width out of all four borders.
     *
     * Generated from Godot docs: StyleBoxFlat.get_border_width_min
     */
    fun getBorderWidthMin(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getBorderWidthMinBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Border width for the top border.
     *
     * Generated from Godot docs: StyleBoxFlat.set_border_width
     */
    fun setBorderWidth(margin: Side, width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndIntArgs(Binds.setBorderWidthBind, segment, margin.value, width)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Border width for the top border.
     *
     * Generated from Godot docs: StyleBoxFlat.get_border_width
     */
    fun getBorderWidth(margin: Side): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetInt(Binds.getBorderWidthBind, segment, margin.value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the border will fade into the background color.
     *
     * Generated from Godot docs: StyleBoxFlat.set_border_blend
     */
    fun setBorderBlend(blend: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setBorderBlendBind, segment, blend)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the border will fade into the background color.
     *
     * Generated from Godot docs: StyleBoxFlat.get_border_blend
     */
    fun getBorderBlend(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getBorderBlendBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the corner radius to `radius` pixels for all corners.
     *
     * Generated from Godot docs: StyleBoxFlat.set_corner_radius_all
     */
    fun setCornerRadiusAll(radius: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setCornerRadiusAllBind, segment, radius)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The top-right corner's radius. If `0`, the corner is not rounded.
     *
     * Generated from Godot docs: StyleBoxFlat.set_corner_radius
     */
    fun setCornerRadius(corner: Corner, radius: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndIntArgs(Binds.setCornerRadiusBind, segment, corner.value, radius)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The top-right corner's radius. If `0`, the corner is not rounded.
     *
     * Generated from Godot docs: StyleBoxFlat.get_corner_radius
     */
    fun getCornerRadius(corner: Corner): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetInt(Binds.getCornerRadiusBind, segment, corner.value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Expands the stylebox outside of the control rect on the top edge. Useful in combination with
     * `border_width_top` to draw a border outside the control rect. Note: Unlike
     * `StyleBox.content_margin_top`, `expand_margin_top` does not affect the size of the clickable
     * area for `Control`s. This can negatively impact usability if used wrong, as the user may try to
     * click an area of the StyleBox that cannot actually receive clicks.
     *
     * Generated from Godot docs: StyleBoxFlat.set_expand_margin
     */
    fun setExpandMargin(margin: Side, size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(Binds.setExpandMarginBind, segment, margin.value, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the expand margin to `size` pixels for all sides.
     *
     * Generated from Godot docs: StyleBoxFlat.set_expand_margin_all
     */
    fun setExpandMarginAll(size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setExpandMarginAllBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Expands the stylebox outside of the control rect on the top edge. Useful in combination with
     * `border_width_top` to draw a border outside the control rect. Note: Unlike
     * `StyleBox.content_margin_top`, `expand_margin_top` does not affect the size of the clickable
     * area for `Control`s. This can negatively impact usability if used wrong, as the user may try to
     * click an area of the StyleBox that cannot actually receive clicks.
     *
     * Generated from Godot docs: StyleBoxFlat.get_expand_margin
     */
    fun getExpandMargin(margin: Side): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getExpandMarginBind, segment, margin.value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Toggles drawing of the inner part of the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.set_draw_center
     */
    fun setDrawCenter(drawCenter: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDrawCenterBind, segment, drawCenter)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Toggles drawing of the inner part of the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.is_draw_center_enabled
     */
    fun isDrawCenterEnabled(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isDrawCenterEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If set to a non-zero value on either axis, `skew` distorts the StyleBox horizontally and/or
     * vertically. This can be used for "futuristic"-style UIs. Positive values skew the StyleBox
     * towards the right (X axis) and upwards (Y axis), while negative values skew the StyleBox towards
     * the left (X axis) and downwards (Y axis). Note: To ensure text does not touch the StyleBox's
     * edges, consider increasing the `StyleBox`'s content margin (see
     * `StyleBox.content_margin_bottom`). It is preferable to increase the content margin instead of
     * the expand margin (see `expand_margin_bottom`), as increasing the expand margin does not
     * increase the size of the clickable area for `Control`s.
     *
     * Generated from Godot docs: StyleBoxFlat.set_skew
     */
    fun setSkew(skew: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setSkewBind, segment, skew)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If set to a non-zero value on either axis, `skew` distorts the StyleBox horizontally and/or
     * vertically. This can be used for "futuristic"-style UIs. Positive values skew the StyleBox
     * towards the right (X axis) and upwards (Y axis), while negative values skew the StyleBox towards
     * the left (X axis) and downwards (Y axis). Note: To ensure text does not touch the StyleBox's
     * edges, consider increasing the `StyleBox`'s content margin (see
     * `StyleBox.content_margin_bottom`). It is preferable to increase the content margin instead of
     * the expand margin (see `expand_margin_bottom`), as increasing the expand margin does not
     * increase the size of the clickable area for `Control`s.
     *
     * Generated from Godot docs: StyleBoxFlat.get_skew
     */
    fun getSkew(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSkewBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The color of the shadow. This has no effect if `shadow_size` is lower than 1.
     *
     * Generated from Godot docs: StyleBoxFlat.set_shadow_color
     */
    fun setShadowColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setShadowColorBind, segment, color)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The color of the shadow. This has no effect if `shadow_size` is lower than 1.
     *
     * Generated from Godot docs: StyleBoxFlat.get_shadow_color
     */
    fun getShadowColor(): Color {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetColor(Binds.getShadowColorBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The shadow size in pixels.
     *
     * Generated from Godot docs: StyleBoxFlat.set_shadow_size
     */
    fun setShadowSize(size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setShadowSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The shadow size in pixels.
     *
     * Generated from Godot docs: StyleBoxFlat.get_shadow_size
     */
    fun getShadowSize(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getShadowSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The shadow offset in pixels. Adjusts the position of the shadow relatively to the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.set_shadow_offset
     */
    fun setShadowOffset(offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setShadowOffsetBind, segment, offset)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The shadow offset in pixels. Adjusts the position of the shadow relatively to the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.get_shadow_offset
     */
    fun getShadowOffset(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getShadowOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Antialiasing draws a small ring around the edges, which fades to transparency. As a result,
     * edges look much smoother. This is only noticeable when using rounded corners or `skew`. Note:
     * When using beveled corners with 45-degree angles (`corner_detail` = 1), it is recommended to set
     * `anti_aliasing` to `false` to ensure crisp visuals and avoid possible visual glitches.
     *
     * Generated from Godot docs: StyleBoxFlat.set_anti_aliased
     */
    fun setAntiAliased(antiAliased: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAntiAliasedBind, segment, antiAliased)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Antialiasing draws a small ring around the edges, which fades to transparency. As a result,
     * edges look much smoother. This is only noticeable when using rounded corners or `skew`. Note:
     * When using beveled corners with 45-degree angles (`corner_detail` = 1), it is recommended to set
     * `anti_aliasing` to `false` to ensure crisp visuals and avoid possible visual glitches.
     *
     * Generated from Godot docs: StyleBoxFlat.is_anti_aliased
     */
    fun isAntiAliased(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isAntiAliasedBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * This changes the size of the antialiasing effect. `1.0` is recommended for an optimal result at
     * 100% scale, identical to how rounded rectangles are rendered in web browsers and most vector
     * drawing software. Note: Higher values may produce a blur effect but can also create undesired
     * artifacts on small boxes with large-radius corners.
     *
     * Generated from Godot docs: StyleBoxFlat.set_aa_size
     */
    fun setAaSize(size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAaSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * This changes the size of the antialiasing effect. `1.0` is recommended for an optimal result at
     * 100% scale, identical to how rounded rectangles are rendered in web browsers and most vector
     * drawing software. Note: Higher values may produce a blur effect but can also create undesired
     * artifacts on small boxes with large-radius corners.
     *
     * Generated from Godot docs: StyleBoxFlat.get_aa_size
     */
    fun getAaSize(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAaSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * This sets the number of vertices used for each corner. Higher values result in rounder corners
     * but take more processing power to compute. When choosing a value, you should take the corner
     * radius (`set_corner_radius_all`) into account. For corner radii less than 10, `4` or `5` should
     * be enough. For corner radii less than 30, values between `8` and `12` should be enough. A corner
     * detail of `1` will result in chamfered corners instead of rounded corners, which is useful for
     * some artistic effects.
     *
     * Generated from Godot docs: StyleBoxFlat.set_corner_detail
     */
    fun setCornerDetail(detail: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setCornerDetailBind, segment, detail)
        ObjectCalls.keepReachable(this)
    }

    /**
     * This sets the number of vertices used for each corner. Higher values result in rounder corners
     * but take more processing power to compute. When choosing a value, you should take the corner
     * radius (`set_corner_radius_all`) into account. For corner radii less than 10, `4` or `5` should
     * be enough. For corner radii less than 30, values between `8` and `12` should be enough. A corner
     * detail of `1` will result in chamfered corners instead of rounded corners, which is useful for
     * some artistic effects.
     *
     * Generated from Godot docs: StyleBoxFlat.get_corner_detail
     */
    fun getCornerDetail(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getCornerDetailBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StyleBoxFlat? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StyleBoxFlat? =
            if (handle.address() == 0L) null else RefCounted.owned(StyleBoxFlat(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StyleBoxFlat? =
            if (handle.address() == 0L) null else StyleBoxFlat(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BG_COLOR_HASH = 2920490490L
        @JvmField
        val setBgColorBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_bg_color", SET_BG_COLOR_HASH)

        private const val GET_BG_COLOR_HASH = 3444240500L
        @JvmField
        val getBgColorBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_bg_color", GET_BG_COLOR_HASH)

        private const val SET_BORDER_COLOR_HASH = 2920490490L
        @JvmField
        val setBorderColorBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_border_color", SET_BORDER_COLOR_HASH)

        private const val GET_BORDER_COLOR_HASH = 3444240500L
        @JvmField
        val getBorderColorBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_border_color", GET_BORDER_COLOR_HASH)

        private const val SET_BORDER_WIDTH_ALL_HASH = 1286410249L
        @JvmField
        val setBorderWidthAllBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_border_width_all", SET_BORDER_WIDTH_ALL_HASH)

        private const val GET_BORDER_WIDTH_MIN_HASH = 3905245786L
        @JvmField
        val getBorderWidthMinBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_border_width_min", GET_BORDER_WIDTH_MIN_HASH)

        private const val SET_BORDER_WIDTH_HASH = 437707142L
        @JvmField
        val setBorderWidthBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_border_width", SET_BORDER_WIDTH_HASH)

        private const val GET_BORDER_WIDTH_HASH = 1983885014L
        @JvmField
        val getBorderWidthBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_border_width", GET_BORDER_WIDTH_HASH)

        private const val SET_BORDER_BLEND_HASH = 2586408642L
        @JvmField
        val setBorderBlendBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_border_blend", SET_BORDER_BLEND_HASH)

        private const val GET_BORDER_BLEND_HASH = 36873697L
        @JvmField
        val getBorderBlendBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_border_blend", GET_BORDER_BLEND_HASH)

        private const val SET_CORNER_RADIUS_ALL_HASH = 1286410249L
        @JvmField
        val setCornerRadiusAllBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_corner_radius_all", SET_CORNER_RADIUS_ALL_HASH)

        private const val SET_CORNER_RADIUS_HASH = 2696158768L
        @JvmField
        val setCornerRadiusBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_corner_radius", SET_CORNER_RADIUS_HASH)

        private const val GET_CORNER_RADIUS_HASH = 3982397690L
        @JvmField
        val getCornerRadiusBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_corner_radius", GET_CORNER_RADIUS_HASH)

        private const val SET_EXPAND_MARGIN_HASH = 4290182280L
        @JvmField
        val setExpandMarginBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_expand_margin", SET_EXPAND_MARGIN_HASH)

        private const val SET_EXPAND_MARGIN_ALL_HASH = 373806689L
        @JvmField
        val setExpandMarginAllBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_expand_margin_all", SET_EXPAND_MARGIN_ALL_HASH)

        private const val GET_EXPAND_MARGIN_HASH = 2869120046L
        @JvmField
        val getExpandMarginBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_expand_margin", GET_EXPAND_MARGIN_HASH)

        private const val SET_DRAW_CENTER_HASH = 2586408642L
        @JvmField
        val setDrawCenterBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_draw_center", SET_DRAW_CENTER_HASH)

        private const val IS_DRAW_CENTER_ENABLED_HASH = 36873697L
        @JvmField
        val isDrawCenterEnabledBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "is_draw_center_enabled", IS_DRAW_CENTER_ENABLED_HASH)

        private const val SET_SKEW_HASH = 743155724L
        @JvmField
        val setSkewBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_skew", SET_SKEW_HASH)

        private const val GET_SKEW_HASH = 3341600327L
        @JvmField
        val getSkewBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_skew", GET_SKEW_HASH)

        private const val SET_SHADOW_COLOR_HASH = 2920490490L
        @JvmField
        val setShadowColorBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_shadow_color", SET_SHADOW_COLOR_HASH)

        private const val GET_SHADOW_COLOR_HASH = 3444240500L
        @JvmField
        val getShadowColorBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_shadow_color", GET_SHADOW_COLOR_HASH)

        private const val SET_SHADOW_SIZE_HASH = 1286410249L
        @JvmField
        val setShadowSizeBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_shadow_size", SET_SHADOW_SIZE_HASH)

        private const val GET_SHADOW_SIZE_HASH = 3905245786L
        @JvmField
        val getShadowSizeBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_shadow_size", GET_SHADOW_SIZE_HASH)

        private const val SET_SHADOW_OFFSET_HASH = 743155724L
        @JvmField
        val setShadowOffsetBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_shadow_offset", SET_SHADOW_OFFSET_HASH)

        private const val GET_SHADOW_OFFSET_HASH = 3341600327L
        @JvmField
        val getShadowOffsetBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_shadow_offset", GET_SHADOW_OFFSET_HASH)

        private const val SET_ANTI_ALIASED_HASH = 2586408642L
        @JvmField
        val setAntiAliasedBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_anti_aliased", SET_ANTI_ALIASED_HASH)

        private const val IS_ANTI_ALIASED_HASH = 36873697L
        @JvmField
        val isAntiAliasedBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "is_anti_aliased", IS_ANTI_ALIASED_HASH)

        private const val SET_AA_SIZE_HASH = 373806689L
        @JvmField
        val setAaSizeBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_aa_size", SET_AA_SIZE_HASH)

        private const val GET_AA_SIZE_HASH = 1740695150L
        @JvmField
        val getAaSizeBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_aa_size", GET_AA_SIZE_HASH)

        private const val SET_CORNER_DETAIL_HASH = 1286410249L
        @JvmField
        val setCornerDetailBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_corner_detail", SET_CORNER_DETAIL_HASH)

        private const val GET_CORNER_DETAIL_HASH = 3905245786L
        @JvmField
        val getCornerDetailBind =
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_corner_detail", GET_CORNER_DETAIL_HASH)
    }
}
