package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * Texture-based progress bar. Useful for loading screens and life or stamina bars.
 *
 * Generated from Godot docs: TextureProgressBar
 */
class TextureProgressBar(handle: MemorySegment) : Range(handle) {
    var fillMode: Int
        @JvmName("fillModeProperty")
        get() = getFillMode()
        @JvmName("setFillModeProperty")
        set(value) = setFillMode(value)

    var radialInitialAngle: Double
        @JvmName("radialInitialAngleProperty")
        get() = getRadialInitialAngle()
        @JvmName("setRadialInitialAngleProperty")
        set(value) = setRadialInitialAngle(value)

    var radialFillDegrees: Double
        @JvmName("radialFillDegreesProperty")
        get() = getFillDegrees()
        @JvmName("setRadialFillDegreesProperty")
        set(value) = setFillDegrees(value)

    var radialCenterOffset: Vector2
        @JvmName("radialCenterOffsetProperty")
        get() = getRadialCenterOffset()
        @JvmName("setRadialCenterOffsetProperty")
        set(value) = setRadialCenterOffset(value)

    var ninePatchStretch: Boolean
        @JvmName("ninePatchStretchProperty")
        get() = getNinePatchStretch()
        @JvmName("setNinePatchStretchProperty")
        set(value) = setNinePatchStretch(value)

    var textureUnder: Texture2D?
        @JvmName("textureUnderProperty")
        get() = getUnderTexture()
        @JvmName("setTextureUnderProperty")
        set(value) = setUnderTexture(value)

    var textureOver: Texture2D?
        @JvmName("textureOverProperty")
        get() = getOverTexture()
        @JvmName("setTextureOverProperty")
        set(value) = setOverTexture(value)

    var textureProgress: Texture2D?
        @JvmName("textureProgressProperty")
        get() = getProgressTexture()
        @JvmName("setTextureProgressProperty")
        set(value) = setProgressTexture(value)

    var textureProgressOffset: Vector2
        @JvmName("textureProgressOffsetProperty")
        get() = getTextureProgressOffset()
        @JvmName("setTextureProgressOffsetProperty")
        set(value) = setTextureProgressOffset(value)

    var tintUnder: Color
        @JvmName("tintUnderProperty")
        get() = getTintUnder()
        @JvmName("setTintUnderProperty")
        set(value) = setTintUnder(value)

    var tintOver: Color
        @JvmName("tintOverProperty")
        get() = getTintOver()
        @JvmName("setTintOverProperty")
        set(value) = setTintOver(value)

    var tintProgress: Color
        @JvmName("tintProgressProperty")
        get() = getTintProgress()
        @JvmName("setTintProgressProperty")
        set(value) = setTintProgress(value)

    /**
     * `Texture2D` that draws under the progress bar. The bar's background.
     *
     * Generated from Godot docs: TextureProgressBar.set_under_texture
     */
    fun setUnderTexture(tex: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setUnderTextureBind, handle, listOf(tex?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Texture2D` that draws under the progress bar. The bar's background.
     *
     * Generated from Godot docs: TextureProgressBar.get_under_texture
     */
    fun getUnderTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getUnderTextureBind, handle))
    }

    /**
     * `Texture2D` that clips based on the node's `value` and `fill_mode`. As `value` increased, the
     * texture fills up. It shows entirely when `value` reaches `max_value`. It doesn't show at all if
     * `value` is equal to `min_value`. The `value` property comes from `Range`. See `Range.value`,
     * `Range.min_value`, `Range.max_value`.
     *
     * Generated from Godot docs: TextureProgressBar.set_progress_texture
     */
    fun setProgressTexture(tex: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setProgressTextureBind, handle, listOf(tex?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Texture2D` that clips based on the node's `value` and `fill_mode`. As `value` increased, the
     * texture fills up. It shows entirely when `value` reaches `max_value`. It doesn't show at all if
     * `value` is equal to `min_value`. The `value` property comes from `Range`. See `Range.value`,
     * `Range.min_value`, `Range.max_value`.
     *
     * Generated from Godot docs: TextureProgressBar.get_progress_texture
     */
    fun getProgressTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProgressTextureBind, handle))
    }

    /**
     * `Texture2D` that draws over the progress bar. Use it to add highlights or an upper-frame that
     * hides part of `texture_progress`.
     *
     * Generated from Godot docs: TextureProgressBar.set_over_texture
     */
    fun setOverTexture(tex: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setOverTextureBind, handle, listOf(tex?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Texture2D` that draws over the progress bar. Use it to add highlights or an upper-frame that
     * hides part of `texture_progress`.
     *
     * Generated from Godot docs: TextureProgressBar.get_over_texture
     */
    fun getOverTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOverTextureBind, handle))
    }

    /**
     * The fill direction. See `FillMode` for possible values.
     *
     * Generated from Godot docs: TextureProgressBar.set_fill_mode
     */
    fun setFillMode(mode: Int) {
        ObjectCalls.ptrcallWithIntArg(setFillModeBind, handle, mode)
    }

    /**
     * The fill direction. See `FillMode` for possible values.
     *
     * Generated from Godot docs: TextureProgressBar.get_fill_mode
     */
    fun getFillMode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFillModeBind, handle)
    }

    /**
     * Multiplies the color of the bar's `texture_under` texture.
     *
     * Generated from Godot docs: TextureProgressBar.set_tint_under
     */
    fun setTintUnder(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(setTintUnderBind, handle, tint)
    }

    /**
     * Multiplies the color of the bar's `texture_under` texture.
     *
     * Generated from Godot docs: TextureProgressBar.get_tint_under
     */
    fun getTintUnder(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTintUnderBind, handle)
    }

    /**
     * Multiplies the color of the bar's `texture_progress` texture.
     *
     * Generated from Godot docs: TextureProgressBar.set_tint_progress
     */
    fun setTintProgress(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(setTintProgressBind, handle, tint)
    }

    /**
     * Multiplies the color of the bar's `texture_progress` texture.
     *
     * Generated from Godot docs: TextureProgressBar.get_tint_progress
     */
    fun getTintProgress(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTintProgressBind, handle)
    }

    /**
     * Multiplies the color of the bar's `texture_over` texture. The effect is similar to
     * `CanvasItem.modulate`, except it only affects this specific texture instead of the entire node.
     *
     * Generated from Godot docs: TextureProgressBar.set_tint_over
     */
    fun setTintOver(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(setTintOverBind, handle, tint)
    }

    /**
     * Multiplies the color of the bar's `texture_over` texture. The effect is similar to
     * `CanvasItem.modulate`, except it only affects this specific texture instead of the entire node.
     *
     * Generated from Godot docs: TextureProgressBar.get_tint_over
     */
    fun getTintOver(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTintOverBind, handle)
    }

    /**
     * The offset of `texture_progress`. Useful for `texture_over` and `texture_under` with fancy
     * borders, to avoid transparent margins in your progress texture.
     *
     * Generated from Godot docs: TextureProgressBar.set_texture_progress_offset
     */
    fun setTextureProgressOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTextureProgressOffsetBind, handle, offset)
    }

    /**
     * The offset of `texture_progress`. Useful for `texture_over` and `texture_under` with fancy
     * borders, to avoid transparent margins in your progress texture.
     *
     * Generated from Godot docs: TextureProgressBar.get_texture_progress_offset
     */
    fun getTextureProgressOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTextureProgressOffsetBind, handle)
    }

    /**
     * Starting angle for the fill of `texture_progress` if `fill_mode` is `FILL_CLOCKWISE`,
     * `FILL_COUNTER_CLOCKWISE`, or `FILL_CLOCKWISE_AND_COUNTER_CLOCKWISE`. When the node's `value` is
     * equal to its `min_value`, the texture doesn't show up at all. When the `value` increases, the
     * texture fills and tends towards `radial_fill_degrees`. Note: `radial_initial_angle` is wrapped
     * between `0` and `360` degrees (inclusive).
     *
     * Generated from Godot docs: TextureProgressBar.set_radial_initial_angle
     */
    fun setRadialInitialAngle(mode: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadialInitialAngleBind, handle, mode)
    }

    /**
     * Starting angle for the fill of `texture_progress` if `fill_mode` is `FILL_CLOCKWISE`,
     * `FILL_COUNTER_CLOCKWISE`, or `FILL_CLOCKWISE_AND_COUNTER_CLOCKWISE`. When the node's `value` is
     * equal to its `min_value`, the texture doesn't show up at all. When the `value` increases, the
     * texture fills and tends towards `radial_fill_degrees`. Note: `radial_initial_angle` is wrapped
     * between `0` and `360` degrees (inclusive).
     *
     * Generated from Godot docs: TextureProgressBar.get_radial_initial_angle
     */
    fun getRadialInitialAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadialInitialAngleBind, handle)
    }

    /**
     * Offsets `texture_progress` if `fill_mode` is `FILL_CLOCKWISE`, `FILL_COUNTER_CLOCKWISE`, or
     * `FILL_CLOCKWISE_AND_COUNTER_CLOCKWISE`. Note: The effective radial center always stays within
     * the `texture_progress` bounds. If you need to move it outside the texture's bounds, modify the
     * `texture_progress` to contain additional empty space where needed.
     *
     * Generated from Godot docs: TextureProgressBar.set_radial_center_offset
     */
    fun setRadialCenterOffset(mode: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setRadialCenterOffsetBind, handle, mode)
    }

    /**
     * Offsets `texture_progress` if `fill_mode` is `FILL_CLOCKWISE`, `FILL_COUNTER_CLOCKWISE`, or
     * `FILL_CLOCKWISE_AND_COUNTER_CLOCKWISE`. Note: The effective radial center always stays within
     * the `texture_progress` bounds. If you need to move it outside the texture's bounds, modify the
     * `texture_progress` to contain additional empty space where needed.
     *
     * Generated from Godot docs: TextureProgressBar.get_radial_center_offset
     */
    fun getRadialCenterOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getRadialCenterOffsetBind, handle)
    }

    /**
     * Upper limit for the fill of `texture_progress` if `fill_mode` is `FILL_CLOCKWISE`,
     * `FILL_COUNTER_CLOCKWISE`, or `FILL_CLOCKWISE_AND_COUNTER_CLOCKWISE`. When the node's `value` is
     * equal to its `max_value`, the texture fills up to this angle. See `Range.value`,
     * `Range.max_value`.
     *
     * Generated from Godot docs: TextureProgressBar.set_fill_degrees
     */
    fun setFillDegrees(mode: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFillDegreesBind, handle, mode)
    }

    /**
     * Upper limit for the fill of `texture_progress` if `fill_mode` is `FILL_CLOCKWISE`,
     * `FILL_COUNTER_CLOCKWISE`, or `FILL_CLOCKWISE_AND_COUNTER_CLOCKWISE`. When the node's `value` is
     * equal to its `max_value`, the texture fills up to this angle. See `Range.value`,
     * `Range.max_value`.
     *
     * Generated from Godot docs: TextureProgressBar.get_fill_degrees
     */
    fun getFillDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFillDegreesBind, handle)
    }

    /**
     * The height of the 9-patch's top row. Only effective if `nine_patch_stretch` is `true`.
     *
     * Generated from Godot docs: TextureProgressBar.set_stretch_margin
     */
    fun setStretchMargin(margin: Long, value: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setStretchMarginBind, handle, margin, value)
    }

    /**
     * The height of the 9-patch's top row. Only effective if `nine_patch_stretch` is `true`.
     *
     * Generated from Godot docs: TextureProgressBar.get_stretch_margin
     */
    fun getStretchMargin(margin: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getStretchMarginBind, handle, margin)
    }

    /**
     * If `true`, Godot treats the bar's textures like in `NinePatchRect`. Use the `stretch_margin_*`
     * properties like `stretch_margin_bottom` to set up the nine patch's 3×3 grid. When using a radial
     * `fill_mode`, this setting will only enable stretching for `texture_progress`, while
     * `texture_under` and `texture_over` will be treated like in `NinePatchRect`.
     *
     * Generated from Godot docs: TextureProgressBar.set_nine_patch_stretch
     */
    fun setNinePatchStretch(stretch: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNinePatchStretchBind, handle, stretch)
    }

    /**
     * If `true`, Godot treats the bar's textures like in `NinePatchRect`. Use the `stretch_margin_*`
     * properties like `stretch_margin_bottom` to set up the nine patch's 3×3 grid. When using a radial
     * `fill_mode`, this setting will only enable stretching for `texture_progress`, while
     * `texture_under` and `texture_over` will be treated like in `NinePatchRect`.
     *
     * Generated from Godot docs: TextureProgressBar.get_nine_patch_stretch
     */
    fun getNinePatchStretch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getNinePatchStretchBind, handle)
    }

    companion object {
        const val FILL_LEFT_TO_RIGHT: Long = 0L
        const val FILL_RIGHT_TO_LEFT: Long = 1L
        const val FILL_TOP_TO_BOTTOM: Long = 2L
        const val FILL_BOTTOM_TO_TOP: Long = 3L
        const val FILL_CLOCKWISE: Long = 4L
        const val FILL_COUNTER_CLOCKWISE: Long = 5L
        const val FILL_BILINEAR_LEFT_AND_RIGHT: Long = 6L
        const val FILL_BILINEAR_TOP_AND_BOTTOM: Long = 7L
        const val FILL_CLOCKWISE_AND_COUNTER_CLOCKWISE: Long = 8L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextureProgressBar? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextureProgressBar? =
            if (handle.address() == 0L) null else TextureProgressBar(handle)

        private const val SET_UNDER_TEXTURE_HASH = 4051416890L
        private val setUnderTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_under_texture", SET_UNDER_TEXTURE_HASH)
        }

        private const val GET_UNDER_TEXTURE_HASH = 3635182373L
        private val getUnderTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_under_texture", GET_UNDER_TEXTURE_HASH)
        }

        private const val SET_PROGRESS_TEXTURE_HASH = 4051416890L
        private val setProgressTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_progress_texture", SET_PROGRESS_TEXTURE_HASH)
        }

        private const val GET_PROGRESS_TEXTURE_HASH = 3635182373L
        private val getProgressTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_progress_texture", GET_PROGRESS_TEXTURE_HASH)
        }

        private const val SET_OVER_TEXTURE_HASH = 4051416890L
        private val setOverTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_over_texture", SET_OVER_TEXTURE_HASH)
        }

        private const val GET_OVER_TEXTURE_HASH = 3635182373L
        private val getOverTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_over_texture", GET_OVER_TEXTURE_HASH)
        }

        private const val SET_FILL_MODE_HASH = 1286410249L
        private val setFillModeBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_fill_mode", SET_FILL_MODE_HASH)
        }

        private const val GET_FILL_MODE_HASH = 2455072627L
        private val getFillModeBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_fill_mode", GET_FILL_MODE_HASH)
        }

        private const val SET_TINT_UNDER_HASH = 2920490490L
        private val setTintUnderBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_tint_under", SET_TINT_UNDER_HASH)
        }

        private const val GET_TINT_UNDER_HASH = 3444240500L
        private val getTintUnderBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_tint_under", GET_TINT_UNDER_HASH)
        }

        private const val SET_TINT_PROGRESS_HASH = 2920490490L
        private val setTintProgressBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_tint_progress", SET_TINT_PROGRESS_HASH)
        }

        private const val GET_TINT_PROGRESS_HASH = 3444240500L
        private val getTintProgressBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_tint_progress", GET_TINT_PROGRESS_HASH)
        }

        private const val SET_TINT_OVER_HASH = 2920490490L
        private val setTintOverBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_tint_over", SET_TINT_OVER_HASH)
        }

        private const val GET_TINT_OVER_HASH = 3444240500L
        private val getTintOverBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_tint_over", GET_TINT_OVER_HASH)
        }

        private const val SET_TEXTURE_PROGRESS_OFFSET_HASH = 743155724L
        private val setTextureProgressOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_texture_progress_offset", SET_TEXTURE_PROGRESS_OFFSET_HASH)
        }

        private const val GET_TEXTURE_PROGRESS_OFFSET_HASH = 3341600327L
        private val getTextureProgressOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_texture_progress_offset", GET_TEXTURE_PROGRESS_OFFSET_HASH)
        }

        private const val SET_RADIAL_INITIAL_ANGLE_HASH = 373806689L
        private val setRadialInitialAngleBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_radial_initial_angle", SET_RADIAL_INITIAL_ANGLE_HASH)
        }

        private const val GET_RADIAL_INITIAL_ANGLE_HASH = 191475506L
        private val getRadialInitialAngleBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_radial_initial_angle", GET_RADIAL_INITIAL_ANGLE_HASH)
        }

        private const val SET_RADIAL_CENTER_OFFSET_HASH = 743155724L
        private val setRadialCenterOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_radial_center_offset", SET_RADIAL_CENTER_OFFSET_HASH)
        }

        private const val GET_RADIAL_CENTER_OFFSET_HASH = 1497962370L
        private val getRadialCenterOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_radial_center_offset", GET_RADIAL_CENTER_OFFSET_HASH)
        }

        private const val SET_FILL_DEGREES_HASH = 373806689L
        private val setFillDegreesBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_fill_degrees", SET_FILL_DEGREES_HASH)
        }

        private const val GET_FILL_DEGREES_HASH = 191475506L
        private val getFillDegreesBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_fill_degrees", GET_FILL_DEGREES_HASH)
        }

        private const val SET_STRETCH_MARGIN_HASH = 437707142L
        private val setStretchMarginBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_stretch_margin", SET_STRETCH_MARGIN_HASH)
        }

        private const val GET_STRETCH_MARGIN_HASH = 1983885014L
        private val getStretchMarginBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_stretch_margin", GET_STRETCH_MARGIN_HASH)
        }

        private const val SET_NINE_PATCH_STRETCH_HASH = 2586408642L
        private val setNinePatchStretchBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "set_nine_patch_stretch", SET_NINE_PATCH_STRETCH_HASH)
        }

        private const val GET_NINE_PATCH_STRETCH_HASH = 36873697L
        private val getNinePatchStretchBind by lazy {
            ObjectCalls.getMethodBind("TextureProgressBar", "get_nine_patch_stretch", GET_NINE_PATCH_STRETCH_HASH)
        }
    }
}
