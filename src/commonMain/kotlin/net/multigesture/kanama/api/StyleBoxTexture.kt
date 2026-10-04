package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2

/**
 * A texture-based nine-patch `StyleBox`.
 *
 * Generated from Godot docs: StyleBoxTexture
 */
class StyleBoxTexture(handle: GodotHandle) : StyleBox(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var textureMarginLeft: Double
        @JvmName("textureMarginLeftProperty")
        get() = getTextureMargin(Side.LEFT)
        @JvmName("setTextureMarginLeftProperty")
        set(value) = setTextureMargin(Side.LEFT, value)

    var textureMarginTop: Double
        @JvmName("textureMarginTopProperty")
        get() = getTextureMargin(Side.TOP)
        @JvmName("setTextureMarginTopProperty")
        set(value) = setTextureMargin(Side.TOP, value)

    var textureMarginRight: Double
        @JvmName("textureMarginRightProperty")
        get() = getTextureMargin(Side.RIGHT)
        @JvmName("setTextureMarginRightProperty")
        set(value) = setTextureMargin(Side.RIGHT, value)

    var textureMarginBottom: Double
        @JvmName("textureMarginBottomProperty")
        get() = getTextureMargin(Side.BOTTOM)
        @JvmName("setTextureMarginBottomProperty")
        set(value) = setTextureMargin(Side.BOTTOM, value)

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

    var axisStretchHorizontal: StyleBoxTexture.AxisStretchMode
        @JvmName("axisStretchHorizontalProperty")
        get() = getHAxisStretchMode()
        @JvmName("setAxisStretchHorizontalProperty")
        set(value) = setHAxisStretchMode(value)

    var axisStretchVertical: StyleBoxTexture.AxisStretchMode
        @JvmName("axisStretchVerticalProperty")
        get() = getVAxisStretchMode()
        @JvmName("setAxisStretchVerticalProperty")
        set(value) = setVAxisStretchMode(value)

    var regionRect: Rect2
        @JvmName("regionRectProperty")
        get() = getRegionRect()
        @JvmName("setRegionRectProperty")
        set(value) = setRegionRect(value)

    var modulateColor: Color
        @JvmName("modulateColorProperty")
        get() = getModulate()
        @JvmName("setModulateColorProperty")
        set(value) = setModulate(value)

    var drawCenter: Boolean
        @JvmName("drawCenterProperty")
        get() = isDrawCenterEnabled()
        @JvmName("setDrawCenterProperty")
        set(value) = setDrawCenter(value)

    /**
     * The texture to use when drawing this style box.
     *
     * Generated from Godot docs: StyleBoxTexture.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The texture to use when drawing this style box.
     *
     * Generated from Godot docs: StyleBoxTexture.get_texture
     */
    fun getTexture(): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    /**
     * Increases the top margin of the 3×3 texture box. A higher value means more of the source texture
     * is considered to be part of the top border of the 3×3 box. This is also the value used as
     * fallback for `StyleBox.content_margin_top` if it is negative.
     *
     * Generated from Godot docs: StyleBoxTexture.set_texture_margin
     */
    fun setTextureMargin(margin: Side, size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(setTextureMarginBind, segment, margin.value, size)
    }

    /**
     * Sets the margin to `size` pixels for all sides.
     *
     * Generated from Godot docs: StyleBoxTexture.set_texture_margin_all
     */
    fun setTextureMarginAll(size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setTextureMarginAllBind, segment, size)
    }

    /**
     * Increases the top margin of the 3×3 texture box. A higher value means more of the source texture
     * is considered to be part of the top border of the 3×3 box. This is also the value used as
     * fallback for `StyleBox.content_margin_top` if it is negative.
     *
     * Generated from Godot docs: StyleBoxTexture.get_texture_margin
     */
    fun getTextureMargin(margin: Side): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(getTextureMarginBind, segment, margin.value)
    }

    /**
     * Expands the top margin of this style box when drawing, causing it to be drawn larger than
     * requested.
     *
     * Generated from Godot docs: StyleBoxTexture.set_expand_margin
     */
    fun setExpandMargin(margin: Side, size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(setExpandMarginBind, segment, margin.value, size)
    }

    /**
     * Sets the expand margin to `size` pixels for all sides.
     *
     * Generated from Godot docs: StyleBoxTexture.set_expand_margin_all
     */
    fun setExpandMarginAll(size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setExpandMarginAllBind, segment, size)
    }

    /**
     * Expands the top margin of this style box when drawing, causing it to be drawn larger than
     * requested.
     *
     * Generated from Godot docs: StyleBoxTexture.get_expand_margin
     */
    fun getExpandMargin(margin: Side): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(getExpandMarginBind, segment, margin.value)
    }

    /**
     * The region to use from the `texture`. This is equivalent to first wrapping the `texture` in an
     * `AtlasTexture` with the same region. If empty (`Rect2(0, 0, 0, 0)`), the whole `texture` is
     * used.
     *
     * Generated from Godot docs: StyleBoxTexture.set_region_rect
     */
    fun setRegionRect(region: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2Arg(setRegionRectBind, segment, region)
    }

    /**
     * The region to use from the `texture`. This is equivalent to first wrapping the `texture` in an
     * `AtlasTexture` with the same region. If empty (`Rect2(0, 0, 0, 0)`), the whole `texture` is
     * used.
     *
     * Generated from Godot docs: StyleBoxTexture.get_region_rect
     */
    fun getRegionRect(): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionRectBind, segment)
    }

    /**
     * If `true`, the nine-patch texture's center tile will be drawn.
     *
     * Generated from Godot docs: StyleBoxTexture.set_draw_center
     */
    fun setDrawCenter(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setDrawCenterBind, segment, enable)
    }

    /**
     * If `true`, the nine-patch texture's center tile will be drawn.
     *
     * Generated from Godot docs: StyleBoxTexture.is_draw_center_enabled
     */
    fun isDrawCenterEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawCenterEnabledBind, segment)
    }

    /**
     * Modulates the color of the texture when this style box is drawn.
     *
     * Generated from Godot docs: StyleBoxTexture.set_modulate
     */
    fun setModulate(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setModulateBind, segment, color)
    }

    /**
     * Modulates the color of the texture when this style box is drawn.
     *
     * Generated from Godot docs: StyleBoxTexture.get_modulate
     */
    fun getModulate(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, segment)
    }

    /**
     * Controls how the stylebox's texture will be stretched or tiled horizontally.
     *
     * Generated from Godot docs: StyleBoxTexture.set_h_axis_stretch_mode
     */
    fun setHAxisStretchMode(mode: StyleBoxTexture.AxisStretchMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setHAxisStretchModeBind, segment, mode.value)
    }

    /**
     * Controls how the stylebox's texture will be stretched or tiled horizontally.
     *
     * Generated from Godot docs: StyleBoxTexture.get_h_axis_stretch_mode
     */
    fun getHAxisStretchMode(): StyleBoxTexture.AxisStretchMode {
        checkOpen()
        return StyleBoxTexture.AxisStretchMode(ObjectCalls.ptrcallNoArgsRetLong(getHAxisStretchModeBind, segment))
    }

    /**
     * Controls how the stylebox's texture will be stretched or tiled vertically.
     *
     * Generated from Godot docs: StyleBoxTexture.set_v_axis_stretch_mode
     */
    fun setVAxisStretchMode(mode: StyleBoxTexture.AxisStretchMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setVAxisStretchModeBind, segment, mode.value)
    }

    /**
     * Controls how the stylebox's texture will be stretched or tiled vertically.
     *
     * Generated from Godot docs: StyleBoxTexture.get_v_axis_stretch_mode
     */
    fun getVAxisStretchMode(): StyleBoxTexture.AxisStretchMode {
        checkOpen()
        return StyleBoxTexture.AxisStretchMode(ObjectCalls.ptrcallNoArgsRetLong(getVAxisStretchModeBind, segment))
    }

    /**
     * Godot's `StyleBoxTexture.AxisStretchMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`StyleBoxTexture.AxisStretchMode.<NAME>`).
     *
     * Generated from Godot docs: StyleBoxTexture.AxisStretchMode
     */
    @JvmInline
    value class AxisStretchMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Stretch the stylebox's texture. This results in visible distortion unless the texture size
             * matches the stylebox's size perfectly.
             *
             * Generated from Godot docs: StyleBoxTexture.AXIS_STRETCH_MODE_STRETCH
             */
            val STRETCH: AxisStretchMode get() = AxisStretchMode(0L)
            /**
             * Repeats the stylebox's texture to match the stylebox's size according to the nine-patch system.
             *
             * Generated from Godot docs: StyleBoxTexture.AXIS_STRETCH_MODE_TILE
             */
            val TILE: AxisStretchMode get() = AxisStretchMode(1L)
            /**
             * Repeats the stylebox's texture to match the stylebox's size according to the nine-patch system.
             * Unlike `AxisStretchMode.TILE`, the texture may be slightly stretched to make the nine-patch
             * texture tile seamlessly.
             *
             * Generated from Godot docs: StyleBoxTexture.AXIS_STRETCH_MODE_TILE_FIT
             */
            val TILE_FIT: AxisStretchMode get() = AxisStretchMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StyleBoxTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StyleBoxTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(StyleBoxTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StyleBoxTexture? =
            if (handle.address() == 0L) null else StyleBoxTexture(GodotHandle(handle))

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_TEXTURE_MARGIN_HASH = 4290182280L
        private val setTextureMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_texture_margin", SET_TEXTURE_MARGIN_HASH)
        }

        private const val SET_TEXTURE_MARGIN_ALL_HASH = 373806689L
        private val setTextureMarginAllBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_texture_margin_all", SET_TEXTURE_MARGIN_ALL_HASH)
        }

        private const val GET_TEXTURE_MARGIN_HASH = 2869120046L
        private val getTextureMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "get_texture_margin", GET_TEXTURE_MARGIN_HASH)
        }

        private const val SET_EXPAND_MARGIN_HASH = 4290182280L
        private val setExpandMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_expand_margin", SET_EXPAND_MARGIN_HASH)
        }

        private const val SET_EXPAND_MARGIN_ALL_HASH = 373806689L
        private val setExpandMarginAllBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_expand_margin_all", SET_EXPAND_MARGIN_ALL_HASH)
        }

        private const val GET_EXPAND_MARGIN_HASH = 2869120046L
        private val getExpandMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "get_expand_margin", GET_EXPAND_MARGIN_HASH)
        }

        private const val SET_REGION_RECT_HASH = 2046264180L
        private val setRegionRectBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_region_rect", SET_REGION_RECT_HASH)
        }

        private const val GET_REGION_RECT_HASH = 1639390495L
        private val getRegionRectBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "get_region_rect", GET_REGION_RECT_HASH)
        }

        private const val SET_DRAW_CENTER_HASH = 2586408642L
        private val setDrawCenterBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_draw_center", SET_DRAW_CENTER_HASH)
        }

        private const val IS_DRAW_CENTER_ENABLED_HASH = 36873697L
        private val isDrawCenterEnabledBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "is_draw_center_enabled", IS_DRAW_CENTER_ENABLED_HASH)
        }

        private const val SET_MODULATE_HASH = 2920490490L
        private val setModulateBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_modulate", SET_MODULATE_HASH)
        }

        private const val GET_MODULATE_HASH = 3444240500L
        private val getModulateBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "get_modulate", GET_MODULATE_HASH)
        }

        private const val SET_H_AXIS_STRETCH_MODE_HASH = 2965538783L
        private val setHAxisStretchModeBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_h_axis_stretch_mode", SET_H_AXIS_STRETCH_MODE_HASH)
        }

        private const val GET_H_AXIS_STRETCH_MODE_HASH = 3807744063L
        private val getHAxisStretchModeBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "get_h_axis_stretch_mode", GET_H_AXIS_STRETCH_MODE_HASH)
        }

        private const val SET_V_AXIS_STRETCH_MODE_HASH = 2965538783L
        private val setVAxisStretchModeBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "set_v_axis_stretch_mode", SET_V_AXIS_STRETCH_MODE_HASH)
        }

        private const val GET_V_AXIS_STRETCH_MODE_HASH = 3807744063L
        private val getVAxisStretchModeBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxTexture", "get_v_axis_stretch_mode", GET_V_AXIS_STRETCH_MODE_HASH)
        }
    }
}
