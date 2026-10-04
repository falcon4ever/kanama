package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2

/**
 * A control that displays a texture by keeping its corners intact, but tiling its edges and
 * center.
 *
 * Generated from Godot docs: NinePatchRect
 */
class NinePatchRect(handle: GodotHandle) : Control(handle) {
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

    var patchMarginLeft: Int
        @JvmName("patchMarginLeftProperty")
        get() = getPatchMargin(Side.LEFT)
        @JvmName("setPatchMarginLeftProperty")
        set(value) = setPatchMargin(Side.LEFT, value)

    var patchMarginTop: Int
        @JvmName("patchMarginTopProperty")
        get() = getPatchMargin(Side.TOP)
        @JvmName("setPatchMarginTopProperty")
        set(value) = setPatchMargin(Side.TOP, value)

    var patchMarginRight: Int
        @JvmName("patchMarginRightProperty")
        get() = getPatchMargin(Side.RIGHT)
        @JvmName("setPatchMarginRightProperty")
        set(value) = setPatchMargin(Side.RIGHT, value)

    var patchMarginBottom: Int
        @JvmName("patchMarginBottomProperty")
        get() = getPatchMargin(Side.BOTTOM)
        @JvmName("setPatchMarginBottomProperty")
        set(value) = setPatchMargin(Side.BOTTOM, value)

    var axisStretchHorizontal: NinePatchRect.AxisStretchMode
        @JvmName("axisStretchHorizontalProperty")
        get() = getHAxisStretchMode()
        @JvmName("setAxisStretchHorizontalProperty")
        set(value) = setHAxisStretchMode(value)

    var axisStretchVertical: NinePatchRect.AxisStretchMode
        @JvmName("axisStretchVerticalProperty")
        get() = getVAxisStretchMode()
        @JvmName("setAxisStretchVerticalProperty")
        set(value) = setVAxisStretchMode(value)

    /**
     * The node's texture resource.
     *
     * Generated from Godot docs: NinePatchRect.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The node's texture resource.
     *
     * Generated from Godot docs: NinePatchRect.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    /**
     * The height of the 9-slice's top row. A margin of 16 means the 9-slice's top corners and side
     * will have a height of 16 pixels. You can set all 4 margin values individually to create panels
     * with non-uniform borders.
     *
     * Generated from Godot docs: NinePatchRect.set_patch_margin
     */
    fun setPatchMargin(margin: Side, value: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setPatchMarginBind, segment, margin.value, value)
    }

    /**
     * The height of the 9-slice's top row. A margin of 16 means the 9-slice's top corners and side
     * will have a height of 16 pixels. You can set all 4 margin values individually to create panels
     * with non-uniform borders.
     *
     * Generated from Godot docs: NinePatchRect.get_patch_margin
     */
    fun getPatchMargin(margin: Side): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getPatchMarginBind, segment, margin.value)
    }

    /**
     * Rectangular region of the texture to sample from. If you're working with an atlas, use this
     * property to define the area the 9-slice should use. All other properties are relative to this
     * one. If the rect is empty, NinePatchRect will use the whole texture.
     *
     * Generated from Godot docs: NinePatchRect.set_region_rect
     */
    fun setRegionRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRegionRectBind, segment, rect)
    }

    /**
     * Rectangular region of the texture to sample from. If you're working with an atlas, use this
     * property to define the area the 9-slice should use. All other properties are relative to this
     * one. If the rect is empty, NinePatchRect will use the whole texture.
     *
     * Generated from Godot docs: NinePatchRect.get_region_rect
     */
    fun getRegionRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionRectBind, segment)
    }

    /**
     * If `true`, draw the panel's center. Else, only draw the 9-slice's borders.
     *
     * Generated from Godot docs: NinePatchRect.set_draw_center
     */
    fun setDrawCenter(drawCenter: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawCenterBind, segment, drawCenter)
    }

    /**
     * If `true`, draw the panel's center. Else, only draw the 9-slice's borders.
     *
     * Generated from Godot docs: NinePatchRect.is_draw_center_enabled
     */
    fun isDrawCenterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawCenterEnabledBind, segment)
    }

    /**
     * The stretch mode to use for horizontal stretching/tiling.
     *
     * Generated from Godot docs: NinePatchRect.set_h_axis_stretch_mode
     */
    fun setHAxisStretchMode(mode: NinePatchRect.AxisStretchMode) {
        ObjectCalls.ptrcallWithLongArg(setHAxisStretchModeBind, segment, mode.value)
    }

    /**
     * The stretch mode to use for horizontal stretching/tiling.
     *
     * Generated from Godot docs: NinePatchRect.get_h_axis_stretch_mode
     */
    fun getHAxisStretchMode(): NinePatchRect.AxisStretchMode {
        return NinePatchRect.AxisStretchMode(ObjectCalls.ptrcallNoArgsRetLong(getHAxisStretchModeBind, segment))
    }

    /**
     * The stretch mode to use for vertical stretching/tiling.
     *
     * Generated from Godot docs: NinePatchRect.set_v_axis_stretch_mode
     */
    fun setVAxisStretchMode(mode: NinePatchRect.AxisStretchMode) {
        ObjectCalls.ptrcallWithLongArg(setVAxisStretchModeBind, segment, mode.value)
    }

    /**
     * The stretch mode to use for vertical stretching/tiling.
     *
     * Generated from Godot docs: NinePatchRect.get_v_axis_stretch_mode
     */
    fun getVAxisStretchMode(): NinePatchRect.AxisStretchMode {
        return NinePatchRect.AxisStretchMode(ObjectCalls.ptrcallNoArgsRetLong(getVAxisStretchModeBind, segment))
    }

    object Signals {
        const val textureChanged: String = "texture_changed"
    }

    /**
     * Godot's `NinePatchRect.AxisStretchMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`NinePatchRect.AxisStretchMode.<NAME>`).
     *
     * Generated from Godot docs: NinePatchRect.AxisStretchMode
     */
    @JvmInline
    value class AxisStretchMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Stretches the center texture across the NinePatchRect. This may cause the texture to be
             * distorted.
             *
             * Generated from Godot docs: NinePatchRect.AXIS_STRETCH_MODE_STRETCH
             */
            val STRETCH: AxisStretchMode get() = AxisStretchMode(0L)
            /**
             * Repeats the center texture across the NinePatchRect. This won't cause any visible distortion.
             * The texture must be seamless for this to work without displaying artifacts between edges.
             *
             * Generated from Godot docs: NinePatchRect.AXIS_STRETCH_MODE_TILE
             */
            val TILE: AxisStretchMode get() = AxisStretchMode(1L)
            /**
             * Repeats the center texture across the NinePatchRect, but will also stretch the texture to make
             * sure each tile is visible in full. This may cause the texture to be distorted, but less than
             * `AxisStretchMode.STRETCH`. The texture must be seamless for this to work without displaying
             * artifacts between edges.
             *
             * Generated from Godot docs: NinePatchRect.AXIS_STRETCH_MODE_TILE_FIT
             */
            val TILE_FIT: AxisStretchMode get() = AxisStretchMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): NinePatchRect? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): NinePatchRect? =
            if (handle.address() == 0L) null else NinePatchRect(GodotHandle(handle))

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
