package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
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

    var borderWidthLeft: Int
        @JvmName("borderWidthLeftProperty")
        get() = getBorderWidth(0L)
        @JvmName("setBorderWidthLeftProperty")
        set(value) = setBorderWidth(0L, value)

    var borderWidthTop: Int
        @JvmName("borderWidthTopProperty")
        get() = getBorderWidth(1L)
        @JvmName("setBorderWidthTopProperty")
        set(value) = setBorderWidth(1L, value)

    var borderWidthRight: Int
        @JvmName("borderWidthRightProperty")
        get() = getBorderWidth(2L)
        @JvmName("setBorderWidthRightProperty")
        set(value) = setBorderWidth(2L, value)

    var borderWidthBottom: Int
        @JvmName("borderWidthBottomProperty")
        get() = getBorderWidth(3L)
        @JvmName("setBorderWidthBottomProperty")
        set(value) = setBorderWidth(3L, value)

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
        get() = getCornerRadius(0L)
        @JvmName("setCornerRadiusTopLeftProperty")
        set(value) = setCornerRadius(0L, value)

    var cornerRadiusTopRight: Int
        @JvmName("cornerRadiusTopRightProperty")
        get() = getCornerRadius(1L)
        @JvmName("setCornerRadiusTopRightProperty")
        set(value) = setCornerRadius(1L, value)

    var cornerRadiusBottomRight: Int
        @JvmName("cornerRadiusBottomRightProperty")
        get() = getCornerRadius(2L)
        @JvmName("setCornerRadiusBottomRightProperty")
        set(value) = setCornerRadius(2L, value)

    var cornerRadiusBottomLeft: Int
        @JvmName("cornerRadiusBottomLeftProperty")
        get() = getCornerRadius(3L)
        @JvmName("setCornerRadiusBottomLeftProperty")
        set(value) = setCornerRadius(3L, value)

    var cornerDetail: Int
        @JvmName("cornerDetailProperty")
        get() = getCornerDetail()
        @JvmName("setCornerDetailProperty")
        set(value) = setCornerDetail(value)

    var expandMarginLeft: Double
        @JvmName("expandMarginLeftProperty")
        get() = getExpandMargin(0L)
        @JvmName("setExpandMarginLeftProperty")
        set(value) = setExpandMargin(0L, value)

    var expandMarginTop: Double
        @JvmName("expandMarginTopProperty")
        get() = getExpandMargin(1L)
        @JvmName("setExpandMarginTopProperty")
        set(value) = setExpandMargin(1L, value)

    var expandMarginRight: Double
        @JvmName("expandMarginRightProperty")
        get() = getExpandMargin(2L)
        @JvmName("setExpandMarginRightProperty")
        set(value) = setExpandMargin(2L, value)

    var expandMarginBottom: Double
        @JvmName("expandMarginBottomProperty")
        get() = getExpandMargin(3L)
        @JvmName("setExpandMarginBottomProperty")
        set(value) = setExpandMargin(3L, value)

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

    fun setBgColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setBgColorBind, handle, color)
    }

    fun getBgColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBgColorBind, handle)
    }

    fun setBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setBorderColorBind, handle, color)
    }

    fun getBorderColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBorderColorBind, handle)
    }

    fun setBorderWidthAll(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setBorderWidthAllBind, handle, width)
    }

    fun getBorderWidthMin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBorderWidthMinBind, handle)
    }

    fun setBorderWidth(margin: Long, width: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setBorderWidthBind, handle, margin, width)
    }

    fun getBorderWidth(margin: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getBorderWidthBind, handle, margin)
    }

    fun setBorderBlend(blend: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBorderBlendBind, handle, blend)
    }

    fun getBorderBlend(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getBorderBlendBind, handle)
    }

    fun setCornerRadiusAll(radius: Int) {
        ObjectCalls.ptrcallWithIntArg(setCornerRadiusAllBind, handle, radius)
    }

    fun setCornerRadius(corner: Long, radius: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setCornerRadiusBind, handle, corner, radius)
    }

    fun getCornerRadius(corner: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getCornerRadiusBind, handle, corner)
    }

    fun setExpandMargin(margin: Long, size: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setExpandMarginBind, handle, margin, size)
    }

    fun setExpandMarginAll(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExpandMarginAllBind, handle, size)
    }

    fun getExpandMargin(margin: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getExpandMarginBind, handle, margin)
    }

    fun setDrawCenter(drawCenter: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawCenterBind, handle, drawCenter)
    }

    fun isDrawCenterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawCenterEnabledBind, handle)
    }

    fun setSkew(skew: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setSkewBind, handle, skew)
    }

    fun getSkew(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSkewBind, handle)
    }

    fun setShadowColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setShadowColorBind, handle, color)
    }

    fun getShadowColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getShadowColorBind, handle)
    }

    fun setShadowSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setShadowSizeBind, handle, size)
    }

    fun getShadowSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getShadowSizeBind, handle)
    }

    fun setShadowOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setShadowOffsetBind, handle, offset)
    }

    fun getShadowOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getShadowOffsetBind, handle)
    }

    fun setAntiAliased(antiAliased: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAntiAliasedBind, handle, antiAliased)
    }

    fun isAntiAliased(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAntiAliasedBind, handle)
    }

    fun setAaSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAaSizeBind, handle, size)
    }

    fun getAaSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAaSizeBind, handle)
    }

    fun setCornerDetail(detail: Int) {
        ObjectCalls.ptrcallWithIntArg(setCornerDetailBind, handle, detail)
    }

    fun getCornerDetail(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCornerDetailBind, handle)
    }

    companion object {
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
