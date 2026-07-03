package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2

/**
 * A texture-based nine-patch `StyleBox`.
 *
 * Generated from Godot docs: StyleBoxTexture
 */
class StyleBoxTexture(handle: MemorySegment) : StyleBox(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var axisStretchHorizontal: Long
        @JvmName("axisStretchHorizontalProperty")
        get() = getHAxisStretchMode()
        @JvmName("setAxisStretchHorizontalProperty")
        set(value) = setHAxisStretchMode(value)

    var axisStretchVertical: Long
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
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The texture to use when drawing this style box.
     *
     * Generated from Godot docs: StyleBoxTexture.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    /**
     * Increases the top margin of the 3×3 texture box. A higher value means more of the source texture
     * is considered to be part of the top border of the 3×3 box. This is also the value used as
     * fallback for `StyleBox.content_margin_top` if it is negative.
     *
     * Generated from Godot docs: StyleBoxTexture.set_texture_margin
     */
    fun setTextureMargin(margin: Long, size: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setTextureMarginBind, handle, margin, size)
    }

    /**
     * Sets the margin to `size` pixels for all sides.
     *
     * Generated from Godot docs: StyleBoxTexture.set_texture_margin_all
     */
    fun setTextureMarginAll(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTextureMarginAllBind, handle, size)
    }

    /**
     * Increases the top margin of the 3×3 texture box. A higher value means more of the source texture
     * is considered to be part of the top border of the 3×3 box. This is also the value used as
     * fallback for `StyleBox.content_margin_top` if it is negative.
     *
     * Generated from Godot docs: StyleBoxTexture.get_texture_margin
     */
    fun getTextureMargin(margin: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getTextureMarginBind, handle, margin)
    }

    /**
     * Expands the top margin of this style box when drawing, causing it to be drawn larger than
     * requested.
     *
     * Generated from Godot docs: StyleBoxTexture.set_expand_margin
     */
    fun setExpandMargin(margin: Long, size: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setExpandMarginBind, handle, margin, size)
    }

    /**
     * Sets the expand margin to `size` pixels for all sides.
     *
     * Generated from Godot docs: StyleBoxTexture.set_expand_margin_all
     */
    fun setExpandMarginAll(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExpandMarginAllBind, handle, size)
    }

    /**
     * Expands the top margin of this style box when drawing, causing it to be drawn larger than
     * requested.
     *
     * Generated from Godot docs: StyleBoxTexture.get_expand_margin
     */
    fun getExpandMargin(margin: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getExpandMarginBind, handle, margin)
    }

    /**
     * The region to use from the `texture`. This is equivalent to first wrapping the `texture` in an
     * `AtlasTexture` with the same region. If empty (`Rect2(0, 0, 0, 0)`), the whole `texture` is
     * used.
     *
     * Generated from Godot docs: StyleBoxTexture.set_region_rect
     */
    fun setRegionRect(region: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRegionRectBind, handle, region)
    }

    /**
     * The region to use from the `texture`. This is equivalent to first wrapping the `texture` in an
     * `AtlasTexture` with the same region. If empty (`Rect2(0, 0, 0, 0)`), the whole `texture` is
     * used.
     *
     * Generated from Godot docs: StyleBoxTexture.get_region_rect
     */
    fun getRegionRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionRectBind, handle)
    }

    /**
     * If `true`, the nine-patch texture's center tile will be drawn.
     *
     * Generated from Godot docs: StyleBoxTexture.set_draw_center
     */
    fun setDrawCenter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawCenterBind, handle, enable)
    }

    /**
     * If `true`, the nine-patch texture's center tile will be drawn.
     *
     * Generated from Godot docs: StyleBoxTexture.is_draw_center_enabled
     */
    fun isDrawCenterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawCenterEnabledBind, handle)
    }

    /**
     * Modulates the color of the texture when this style box is drawn.
     *
     * Generated from Godot docs: StyleBoxTexture.set_modulate
     */
    fun setModulate(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, color)
    }

    /**
     * Modulates the color of the texture when this style box is drawn.
     *
     * Generated from Godot docs: StyleBoxTexture.get_modulate
     */
    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    /**
     * Controls how the stylebox's texture will be stretched or tiled horizontally.
     *
     * Generated from Godot docs: StyleBoxTexture.set_h_axis_stretch_mode
     */
    fun setHAxisStretchMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setHAxisStretchModeBind, handle, mode)
    }

    /**
     * Controls how the stylebox's texture will be stretched or tiled horizontally.
     *
     * Generated from Godot docs: StyleBoxTexture.get_h_axis_stretch_mode
     */
    fun getHAxisStretchMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHAxisStretchModeBind, handle)
    }

    /**
     * Controls how the stylebox's texture will be stretched or tiled vertically.
     *
     * Generated from Godot docs: StyleBoxTexture.set_v_axis_stretch_mode
     */
    fun setVAxisStretchMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVAxisStretchModeBind, handle, mode)
    }

    /**
     * Controls how the stylebox's texture will be stretched or tiled vertically.
     *
     * Generated from Godot docs: StyleBoxTexture.get_v_axis_stretch_mode
     */
    fun getVAxisStretchMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVAxisStretchModeBind, handle)
    }

    companion object {
        const val AXIS_STRETCH_MODE_STRETCH: Long = 0L
        const val AXIS_STRETCH_MODE_TILE: Long = 1L
        const val AXIS_STRETCH_MODE_TILE_FIT: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): StyleBoxTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StyleBoxTexture? =
            if (handle.address() == 0L) null else StyleBoxTexture(handle)

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
