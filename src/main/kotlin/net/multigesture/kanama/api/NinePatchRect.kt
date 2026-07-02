package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2

/**
 * A control that displays a texture by keeping its corners intact, but tiling its edges and
 * center.
 *
 * Generated from Godot docs: NinePatchRect
 */
class NinePatchRect(handle: MemorySegment) : Control(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var drawCenter: Boolean
        @JvmName("drawCenterProperty")
        get() = isDrawCenterEnabled()
        @JvmName("setDrawCenterProperty")
        set(value) = setDrawCenter(value)

    var regionRect: Rect2
        @JvmName("regionRectProperty")
        get() = getRegionRect()
        @JvmName("setRegionRectProperty")
        set(value) = setRegionRect(value)

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

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setPatchMargin(margin: Long, value: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setPatchMarginBind, handle, margin, value)
    }

    fun getPatchMargin(margin: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getPatchMarginBind, handle, margin)
    }

    fun setRegionRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRegionRectBind, handle, rect)
    }

    fun getRegionRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionRectBind, handle)
    }

    fun setDrawCenter(drawCenter: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawCenterBind, handle, drawCenter)
    }

    fun isDrawCenterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawCenterEnabledBind, handle)
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

    object Signals {
        const val textureChanged: String = "texture_changed"
    }

    companion object {
        const val AXIS_STRETCH_MODE_STRETCH: Long = 0L
        const val AXIS_STRETCH_MODE_TILE: Long = 1L
        const val AXIS_STRETCH_MODE_TILE_FIT: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): NinePatchRect? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NinePatchRect? =
            if (handle.address() == 0L) null else NinePatchRect(handle)

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_PATCH_MARGIN_HASH = 437707142L
        private val setPatchMarginBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "set_patch_margin", SET_PATCH_MARGIN_HASH)
        }

        private const val GET_PATCH_MARGIN_HASH = 1983885014L
        private val getPatchMarginBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "get_patch_margin", GET_PATCH_MARGIN_HASH)
        }

        private const val SET_REGION_RECT_HASH = 2046264180L
        private val setRegionRectBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "set_region_rect", SET_REGION_RECT_HASH)
        }

        private const val GET_REGION_RECT_HASH = 1639390495L
        private val getRegionRectBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "get_region_rect", GET_REGION_RECT_HASH)
        }

        private const val SET_DRAW_CENTER_HASH = 2586408642L
        private val setDrawCenterBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "set_draw_center", SET_DRAW_CENTER_HASH)
        }

        private const val IS_DRAW_CENTER_ENABLED_HASH = 36873697L
        private val isDrawCenterEnabledBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "is_draw_center_enabled", IS_DRAW_CENTER_ENABLED_HASH)
        }

        private const val SET_H_AXIS_STRETCH_MODE_HASH = 3219608417L
        private val setHAxisStretchModeBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "set_h_axis_stretch_mode", SET_H_AXIS_STRETCH_MODE_HASH)
        }

        private const val GET_H_AXIS_STRETCH_MODE_HASH = 3317113799L
        private val getHAxisStretchModeBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "get_h_axis_stretch_mode", GET_H_AXIS_STRETCH_MODE_HASH)
        }

        private const val SET_V_AXIS_STRETCH_MODE_HASH = 3219608417L
        private val setVAxisStretchModeBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "set_v_axis_stretch_mode", SET_V_AXIS_STRETCH_MODE_HASH)
        }

        private const val GET_V_AXIS_STRETCH_MODE_HASH = 3317113799L
        private val getVAxisStretchModeBind by lazy {
            ObjectCalls.getMethodBind("NinePatchRect", "get_v_axis_stretch_mode", GET_V_AXIS_STRETCH_MODE_HASH)
        }
    }
}
