package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2

/**
 * Generated from Godot docs: StyleBoxTexture
 */
class StyleBoxTexture(handle: MemorySegment) : StyleBox(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var textureMarginLeft: Double
        @JvmName("textureMarginLeftProperty")
        get() = getTextureMargin(0L)
        @JvmName("setTextureMarginLeftProperty")
        set(value) = setTextureMargin(0L, value)

    var textureMarginTop: Double
        @JvmName("textureMarginTopProperty")
        get() = getTextureMargin(1L)
        @JvmName("setTextureMarginTopProperty")
        set(value) = setTextureMargin(1L, value)

    var textureMarginRight: Double
        @JvmName("textureMarginRightProperty")
        get() = getTextureMargin(2L)
        @JvmName("setTextureMarginRightProperty")
        set(value) = setTextureMargin(2L, value)

    var textureMarginBottom: Double
        @JvmName("textureMarginBottomProperty")
        get() = getTextureMargin(3L)
        @JvmName("setTextureMarginBottomProperty")
        set(value) = setTextureMargin(3L, value)

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

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setTextureMargin(margin: Long, size: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setTextureMarginBind, handle, margin, size)
    }

    fun setTextureMarginAll(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTextureMarginAllBind, handle, size)
    }

    fun getTextureMargin(margin: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getTextureMarginBind, handle, margin)
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

    fun setRegionRect(region: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRegionRectBind, handle, region)
    }

    fun getRegionRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionRectBind, handle)
    }

    fun setDrawCenter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawCenterBind, handle, enable)
    }

    fun isDrawCenterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawCenterEnabledBind, handle)
    }

    fun setModulate(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, color)
    }

    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    fun setHAxisStretchMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setHAxisStretchModeBind, handle, mode)
    }

    fun getHAxisStretchMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHAxisStretchModeBind, handle)
    }

    fun setVAxisStretchMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVAxisStretchModeBind, handle, mode)
    }

    fun getVAxisStretchMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVAxisStretchModeBind, handle)
    }

    companion object {
        const val AXIS_STRETCH_MODE_STRETCH: Long = 0L
        const val AXIS_STRETCH_MODE_TILE: Long = 1L
        const val AXIS_STRETCH_MODE_TILE_FIT: Long = 2L

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
