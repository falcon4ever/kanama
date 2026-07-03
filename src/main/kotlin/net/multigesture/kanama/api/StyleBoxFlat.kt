package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A customizable `StyleBox` that doesn't use a texture.
 *
 * Generated from Godot docs: StyleBoxFlat
 */
class StyleBoxFlat(handle: MemorySegment) : StyleBox(handle) {
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

    var cornerDetail: Int
        @JvmName("cornerDetailProperty")
        get() = getCornerDetail()
        @JvmName("setCornerDetailProperty")
        set(value) = setCornerDetail(value)

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
        ObjectCalls.ptrcallWithColorArg(setBgColorBind, handle, color)
    }

    /**
     * The background color of the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.get_bg_color
     */
    fun getBgColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBgColorBind, handle)
    }

    /**
     * Sets the color of the border.
     *
     * Generated from Godot docs: StyleBoxFlat.set_border_color
     */
    fun setBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setBorderColorBind, handle, color)
    }

    /**
     * Sets the color of the border.
     *
     * Generated from Godot docs: StyleBoxFlat.get_border_color
     */
    fun getBorderColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBorderColorBind, handle)
    }

    /**
     * Sets the border width to `width` pixels for all sides.
     *
     * Generated from Godot docs: StyleBoxFlat.set_border_width_all
     */
    fun setBorderWidthAll(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setBorderWidthAllBind, handle, width)
    }

    /**
     * Returns the smallest border width out of all four borders.
     *
     * Generated from Godot docs: StyleBoxFlat.get_border_width_min
     */
    fun getBorderWidthMin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBorderWidthMinBind, handle)
    }

    /**
     * Border width for the top border.
     *
     * Generated from Godot docs: StyleBoxFlat.set_border_width
     */
    fun setBorderWidth(margin: Long, width: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setBorderWidthBind, handle, margin, width)
    }

    /**
     * Border width for the top border.
     *
     * Generated from Godot docs: StyleBoxFlat.get_border_width
     */
    fun getBorderWidth(margin: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getBorderWidthBind, handle, margin)
    }

    /**
     * If `true`, the border will fade into the background color.
     *
     * Generated from Godot docs: StyleBoxFlat.set_border_blend
     */
    fun setBorderBlend(blend: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBorderBlendBind, handle, blend)
    }

    /**
     * If `true`, the border will fade into the background color.
     *
     * Generated from Godot docs: StyleBoxFlat.get_border_blend
     */
    fun getBorderBlend(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getBorderBlendBind, handle)
    }

    /**
     * Sets the corner radius to `radius` pixels for all corners.
     *
     * Generated from Godot docs: StyleBoxFlat.set_corner_radius_all
     */
    fun setCornerRadiusAll(radius: Int) {
        ObjectCalls.ptrcallWithIntArg(setCornerRadiusAllBind, handle, radius)
    }

    /**
     * The top-right corner's radius. If `0`, the corner is not rounded.
     *
     * Generated from Godot docs: StyleBoxFlat.set_corner_radius
     */
    fun setCornerRadius(corner: Long, radius: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setCornerRadiusBind, handle, corner, radius)
    }

    /**
     * The top-right corner's radius. If `0`, the corner is not rounded.
     *
     * Generated from Godot docs: StyleBoxFlat.get_corner_radius
     */
    fun getCornerRadius(corner: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getCornerRadiusBind, handle, corner)
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
    fun setExpandMargin(margin: Long, size: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setExpandMarginBind, handle, margin, size)
    }

    /**
     * Sets the expand margin to `size` pixels for all sides.
     *
     * Generated from Godot docs: StyleBoxFlat.set_expand_margin_all
     */
    fun setExpandMarginAll(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExpandMarginAllBind, handle, size)
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
    fun getExpandMargin(margin: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getExpandMarginBind, handle, margin)
    }

    /**
     * Toggles drawing of the inner part of the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.set_draw_center
     */
    fun setDrawCenter(drawCenter: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawCenterBind, handle, drawCenter)
    }

    /**
     * Toggles drawing of the inner part of the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.is_draw_center_enabled
     */
    fun isDrawCenterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawCenterEnabledBind, handle)
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
        ObjectCalls.ptrcallWithVector2Arg(setSkewBind, handle, skew)
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
        return ObjectCalls.ptrcallNoArgsRetVector2(getSkewBind, handle)
    }

    /**
     * The color of the shadow. This has no effect if `shadow_size` is lower than 1.
     *
     * Generated from Godot docs: StyleBoxFlat.set_shadow_color
     */
    fun setShadowColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setShadowColorBind, handle, color)
    }

    /**
     * The color of the shadow. This has no effect if `shadow_size` is lower than 1.
     *
     * Generated from Godot docs: StyleBoxFlat.get_shadow_color
     */
    fun getShadowColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getShadowColorBind, handle)
    }

    /**
     * The shadow size in pixels.
     *
     * Generated from Godot docs: StyleBoxFlat.set_shadow_size
     */
    fun setShadowSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setShadowSizeBind, handle, size)
    }

    /**
     * The shadow size in pixels.
     *
     * Generated from Godot docs: StyleBoxFlat.get_shadow_size
     */
    fun getShadowSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getShadowSizeBind, handle)
    }

    /**
     * The shadow offset in pixels. Adjusts the position of the shadow relatively to the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.set_shadow_offset
     */
    fun setShadowOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setShadowOffsetBind, handle, offset)
    }

    /**
     * The shadow offset in pixels. Adjusts the position of the shadow relatively to the stylebox.
     *
     * Generated from Godot docs: StyleBoxFlat.get_shadow_offset
     */
    fun getShadowOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getShadowOffsetBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setAntiAliasedBind, handle, antiAliased)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isAntiAliasedBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setAaSizeBind, handle, size)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getAaSizeBind, handle)
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
        ObjectCalls.ptrcallWithIntArg(setCornerDetailBind, handle, detail)
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
        return ObjectCalls.ptrcallNoArgsRetInt(getCornerDetailBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StyleBoxFlat? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StyleBoxFlat? =
            if (handle.address() == 0L) null else StyleBoxFlat(handle)

        private const val SET_BG_COLOR_HASH = 2920490490L
        private val setBgColorBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_bg_color", SET_BG_COLOR_HASH)
        }

        private const val GET_BG_COLOR_HASH = 3444240500L
        private val getBgColorBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_bg_color", GET_BG_COLOR_HASH)
        }

        private const val SET_BORDER_COLOR_HASH = 2920490490L
        private val setBorderColorBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_border_color", SET_BORDER_COLOR_HASH)
        }

        private const val GET_BORDER_COLOR_HASH = 3444240500L
        private val getBorderColorBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_border_color", GET_BORDER_COLOR_HASH)
        }

        private const val SET_BORDER_WIDTH_ALL_HASH = 1286410249L
        private val setBorderWidthAllBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_border_width_all", SET_BORDER_WIDTH_ALL_HASH)
        }

        private const val GET_BORDER_WIDTH_MIN_HASH = 3905245786L
        private val getBorderWidthMinBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_border_width_min", GET_BORDER_WIDTH_MIN_HASH)
        }

        private const val SET_BORDER_WIDTH_HASH = 437707142L
        private val setBorderWidthBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_border_width", SET_BORDER_WIDTH_HASH)
        }

        private const val GET_BORDER_WIDTH_HASH = 1983885014L
        private val getBorderWidthBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_border_width", GET_BORDER_WIDTH_HASH)
        }

        private const val SET_BORDER_BLEND_HASH = 2586408642L
        private val setBorderBlendBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_border_blend", SET_BORDER_BLEND_HASH)
        }

        private const val GET_BORDER_BLEND_HASH = 36873697L
        private val getBorderBlendBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_border_blend", GET_BORDER_BLEND_HASH)
        }

        private const val SET_CORNER_RADIUS_ALL_HASH = 1286410249L
        private val setCornerRadiusAllBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_corner_radius_all", SET_CORNER_RADIUS_ALL_HASH)
        }

        private const val SET_CORNER_RADIUS_HASH = 2696158768L
        private val setCornerRadiusBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_corner_radius", SET_CORNER_RADIUS_HASH)
        }

        private const val GET_CORNER_RADIUS_HASH = 3982397690L
        private val getCornerRadiusBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_corner_radius", GET_CORNER_RADIUS_HASH)
        }

        private const val SET_EXPAND_MARGIN_HASH = 4290182280L
        private val setExpandMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_expand_margin", SET_EXPAND_MARGIN_HASH)
        }

        private const val SET_EXPAND_MARGIN_ALL_HASH = 373806689L
        private val setExpandMarginAllBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_expand_margin_all", SET_EXPAND_MARGIN_ALL_HASH)
        }

        private const val GET_EXPAND_MARGIN_HASH = 2869120046L
        private val getExpandMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_expand_margin", GET_EXPAND_MARGIN_HASH)
        }

        private const val SET_DRAW_CENTER_HASH = 2586408642L
        private val setDrawCenterBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_draw_center", SET_DRAW_CENTER_HASH)
        }

        private const val IS_DRAW_CENTER_ENABLED_HASH = 36873697L
        private val isDrawCenterEnabledBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "is_draw_center_enabled", IS_DRAW_CENTER_ENABLED_HASH)
        }

        private const val SET_SKEW_HASH = 743155724L
        private val setSkewBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_skew", SET_SKEW_HASH)
        }

        private const val GET_SKEW_HASH = 3341600327L
        private val getSkewBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_skew", GET_SKEW_HASH)
        }

        private const val SET_SHADOW_COLOR_HASH = 2920490490L
        private val setShadowColorBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_shadow_color", SET_SHADOW_COLOR_HASH)
        }

        private const val GET_SHADOW_COLOR_HASH = 3444240500L
        private val getShadowColorBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_shadow_color", GET_SHADOW_COLOR_HASH)
        }

        private const val SET_SHADOW_SIZE_HASH = 1286410249L
        private val setShadowSizeBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_shadow_size", SET_SHADOW_SIZE_HASH)
        }

        private const val GET_SHADOW_SIZE_HASH = 3905245786L
        private val getShadowSizeBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_shadow_size", GET_SHADOW_SIZE_HASH)
        }

        private const val SET_SHADOW_OFFSET_HASH = 743155724L
        private val setShadowOffsetBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_shadow_offset", SET_SHADOW_OFFSET_HASH)
        }

        private const val GET_SHADOW_OFFSET_HASH = 3341600327L
        private val getShadowOffsetBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_shadow_offset", GET_SHADOW_OFFSET_HASH)
        }

        private const val SET_ANTI_ALIASED_HASH = 2586408642L
        private val setAntiAliasedBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_anti_aliased", SET_ANTI_ALIASED_HASH)
        }

        private const val IS_ANTI_ALIASED_HASH = 36873697L
        private val isAntiAliasedBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "is_anti_aliased", IS_ANTI_ALIASED_HASH)
        }

        private const val SET_AA_SIZE_HASH = 373806689L
        private val setAaSizeBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_aa_size", SET_AA_SIZE_HASH)
        }

        private const val GET_AA_SIZE_HASH = 1740695150L
        private val getAaSizeBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_aa_size", GET_AA_SIZE_HASH)
        }

        private const val SET_CORNER_DETAIL_HASH = 1286410249L
        private val setCornerDetailBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "set_corner_detail", SET_CORNER_DETAIL_HASH)
        }

        private const val GET_CORNER_DETAIL_HASH = 3905245786L
        private val getCornerDetailBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxFlat", "get_corner_detail", GET_CORNER_DETAIL_HASH)
        }
    }
}
