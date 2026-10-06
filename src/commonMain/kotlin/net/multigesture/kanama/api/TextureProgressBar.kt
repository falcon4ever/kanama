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
 * Texture-based progress bar. Useful for loading screens and life or stamina bars.
 *
 * Generated from Godot docs: TextureProgressBar
 */
class TextureProgressBar(handle: GodotHandle) : Range(handle) {
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

    var stretchMarginLeft: Int
        @JvmName("stretchMarginLeftProperty")
        get() = getStretchMargin(Side.LEFT)
        @JvmName("setStretchMarginLeftProperty")
        set(value) = setStretchMargin(Side.LEFT, value)

    var stretchMarginTop: Int
        @JvmName("stretchMarginTopProperty")
        get() = getStretchMargin(Side.TOP)
        @JvmName("setStretchMarginTopProperty")
        set(value) = setStretchMargin(Side.TOP, value)

    var stretchMarginRight: Int
        @JvmName("stretchMarginRightProperty")
        get() = getStretchMargin(Side.RIGHT)
        @JvmName("setStretchMarginRightProperty")
        set(value) = setStretchMargin(Side.RIGHT, value)

    var stretchMarginBottom: Int
        @JvmName("stretchMarginBottomProperty")
        get() = getStretchMargin(Side.BOTTOM)
        @JvmName("setStretchMarginBottomProperty")
        set(value) = setStretchMargin(Side.BOTTOM, value)

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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setUnderTextureBind, segment, listOf(tex?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * `Texture2D` that draws under the progress bar. The bar's background.
     *
     * Generated from Godot docs: TextureProgressBar.get_under_texture
     */
    fun getUnderTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getUnderTextureBind, segment))
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setProgressTextureBind, segment, listOf(tex?.requireOpenHandle() ?: NULL_SEGMENT))
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
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getProgressTextureBind, segment))
    }

    /**
     * `Texture2D` that draws over the progress bar. Use it to add highlights or an upper-frame that
     * hides part of `texture_progress`.
     *
     * Generated from Godot docs: TextureProgressBar.set_over_texture
     */
    fun setOverTexture(tex: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setOverTextureBind, segment, listOf(tex?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * `Texture2D` that draws over the progress bar. Use it to add highlights or an upper-frame that
     * hides part of `texture_progress`.
     *
     * Generated from Godot docs: TextureProgressBar.get_over_texture
     */
    fun getOverTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getOverTextureBind, segment))
    }

    /**
     * The fill direction. See `FillMode` for possible values.
     *
     * Generated from Godot docs: TextureProgressBar.set_fill_mode
     */
    fun setFillMode(mode: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setFillModeBind, segment, mode)
    }

    /**
     * The fill direction. See `FillMode` for possible values.
     *
     * Generated from Godot docs: TextureProgressBar.get_fill_mode
     */
    fun getFillMode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFillModeBind, segment)
    }

    /**
     * Multiplies the color of the bar's `texture_under` texture.
     *
     * Generated from Godot docs: TextureProgressBar.set_tint_under
     */
    fun setTintUnder(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setTintUnderBind, segment, tint)
    }

    /**
     * Multiplies the color of the bar's `texture_under` texture.
     *
     * Generated from Godot docs: TextureProgressBar.get_tint_under
     */
    fun getTintUnder(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getTintUnderBind, segment)
    }

    /**
     * Multiplies the color of the bar's `texture_progress` texture.
     *
     * Generated from Godot docs: TextureProgressBar.set_tint_progress
     */
    fun setTintProgress(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setTintProgressBind, segment, tint)
    }

    /**
     * Multiplies the color of the bar's `texture_progress` texture.
     *
     * Generated from Godot docs: TextureProgressBar.get_tint_progress
     */
    fun getTintProgress(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getTintProgressBind, segment)
    }

    /**
     * Multiplies the color of the bar's `texture_over` texture. The effect is similar to
     * `CanvasItem.modulate`, except it only affects this specific texture instead of the entire node.
     *
     * Generated from Godot docs: TextureProgressBar.set_tint_over
     */
    fun setTintOver(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setTintOverBind, segment, tint)
    }

    /**
     * Multiplies the color of the bar's `texture_over` texture. The effect is similar to
     * `CanvasItem.modulate`, except it only affects this specific texture instead of the entire node.
     *
     * Generated from Godot docs: TextureProgressBar.get_tint_over
     */
    fun getTintOver(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getTintOverBind, segment)
    }

    /**
     * The offset of `texture_progress`. Useful for `texture_over` and `texture_under` with fancy
     * borders, to avoid transparent margins in your progress texture.
     *
     * Generated from Godot docs: TextureProgressBar.set_texture_progress_offset
     */
    fun setTextureProgressOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setTextureProgressOffsetBind, segment, offset)
    }

    /**
     * The offset of `texture_progress`. Useful for `texture_over` and `texture_under` with fancy
     * borders, to avoid transparent margins in your progress texture.
     *
     * Generated from Godot docs: TextureProgressBar.get_texture_progress_offset
     */
    fun getTextureProgressOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getTextureProgressOffsetBind, segment)
    }

    /**
     * Starting angle for the fill of `texture_progress` if `fill_mode` is `FillMode.CLOCKWISE`,
     * `FillMode.COUNTER_CLOCKWISE`, or `FillMode.CLOCKWISE_AND_COUNTER_CLOCKWISE`. When the node's
     * `value` is equal to its `min_value`, the texture doesn't show up at all. When the `value`
     * increases, the texture fills and tends towards `radial_fill_degrees`. Note:
     * `radial_initial_angle` is wrapped between `0` and `360` degrees (inclusive).
     *
     * Generated from Godot docs: TextureProgressBar.set_radial_initial_angle
     */
    fun setRadialInitialAngle(mode: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadialInitialAngleBind, segment, mode)
    }

    /**
     * Starting angle for the fill of `texture_progress` if `fill_mode` is `FillMode.CLOCKWISE`,
     * `FillMode.COUNTER_CLOCKWISE`, or `FillMode.CLOCKWISE_AND_COUNTER_CLOCKWISE`. When the node's
     * `value` is equal to its `min_value`, the texture doesn't show up at all. When the `value`
     * increases, the texture fills and tends towards `radial_fill_degrees`. Note:
     * `radial_initial_angle` is wrapped between `0` and `360` degrees (inclusive).
     *
     * Generated from Godot docs: TextureProgressBar.get_radial_initial_angle
     */
    fun getRadialInitialAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadialInitialAngleBind, segment)
    }

    /**
     * Offsets `texture_progress` if `fill_mode` is `FillMode.CLOCKWISE`, `FillMode.COUNTER_CLOCKWISE`,
     * or `FillMode.CLOCKWISE_AND_COUNTER_CLOCKWISE`. Note: The effective radial center always stays
     * within the `texture_progress` bounds. If you need to move it outside the texture's bounds,
     * modify the `texture_progress` to contain additional empty space where needed.
     *
     * Generated from Godot docs: TextureProgressBar.set_radial_center_offset
     */
    fun setRadialCenterOffset(mode: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setRadialCenterOffsetBind, segment, mode)
    }

    /**
     * Offsets `texture_progress` if `fill_mode` is `FillMode.CLOCKWISE`, `FillMode.COUNTER_CLOCKWISE`,
     * or `FillMode.CLOCKWISE_AND_COUNTER_CLOCKWISE`. Note: The effective radial center always stays
     * within the `texture_progress` bounds. If you need to move it outside the texture's bounds,
     * modify the `texture_progress` to contain additional empty space where needed.
     *
     * Generated from Godot docs: TextureProgressBar.get_radial_center_offset
     */
    fun getRadialCenterOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getRadialCenterOffsetBind, segment)
    }

    /**
     * Upper limit for the fill of `texture_progress` if `fill_mode` is `FillMode.CLOCKWISE`,
     * `FillMode.COUNTER_CLOCKWISE`, or `FillMode.CLOCKWISE_AND_COUNTER_CLOCKWISE`. When the node's
     * `value` is equal to its `max_value`, the texture fills up to this angle. See `Range.value`,
     * `Range.max_value`.
     *
     * Generated from Godot docs: TextureProgressBar.set_fill_degrees
     */
    fun setFillDegrees(mode: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFillDegreesBind, segment, mode)
    }

    /**
     * Upper limit for the fill of `texture_progress` if `fill_mode` is `FillMode.CLOCKWISE`,
     * `FillMode.COUNTER_CLOCKWISE`, or `FillMode.CLOCKWISE_AND_COUNTER_CLOCKWISE`. When the node's
     * `value` is equal to its `max_value`, the texture fills up to this angle. See `Range.value`,
     * `Range.max_value`.
     *
     * Generated from Godot docs: TextureProgressBar.get_fill_degrees
     */
    fun getFillDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFillDegreesBind, segment)
    }

    /**
     * The height of the 9-patch's top row. Only effective if `nine_patch_stretch` is `true`.
     *
     * Generated from Godot docs: TextureProgressBar.set_stretch_margin
     */
    fun setStretchMargin(margin: Side, value: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(Binds.setStretchMarginBind, segment, margin.value, value)
    }

    /**
     * The height of the 9-patch's top row. Only effective if `nine_patch_stretch` is `true`.
     *
     * Generated from Godot docs: TextureProgressBar.get_stretch_margin
     */
    fun getStretchMargin(margin: Side): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(Binds.getStretchMarginBind, segment, margin.value)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setNinePatchStretchBind, segment, stretch)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getNinePatchStretchBind, segment)
    }

    /**
     * Godot's `TextureProgressBar.FillMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextureProgressBar.FillMode.<NAME>`).
     *
     * Generated from Godot docs: TextureProgressBar.FillMode
     */
    @JvmInline
    value class FillMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The `texture_progress` fills from left to right.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_LEFT_TO_RIGHT
             */
            val LEFT_TO_RIGHT: FillMode get() = FillMode(0L)
            /**
             * The `texture_progress` fills from right to left.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_RIGHT_TO_LEFT
             */
            val RIGHT_TO_LEFT: FillMode get() = FillMode(1L)
            /**
             * The `texture_progress` fills from top to bottom.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_TOP_TO_BOTTOM
             */
            val TOP_TO_BOTTOM: FillMode get() = FillMode(2L)
            /**
             * The `texture_progress` fills from bottom to top.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_BOTTOM_TO_TOP
             */
            val BOTTOM_TO_TOP: FillMode get() = FillMode(3L)
            /**
             * Turns the node into a radial bar. The `texture_progress` fills clockwise. See
             * `radial_center_offset`, `radial_initial_angle` and `radial_fill_degrees` to control the way the
             * bar fills up.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_CLOCKWISE
             */
            val CLOCKWISE: FillMode get() = FillMode(4L)
            /**
             * Turns the node into a radial bar. The `texture_progress` fills counterclockwise. See
             * `radial_center_offset`, `radial_initial_angle` and `radial_fill_degrees` to control the way the
             * bar fills up.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_COUNTER_CLOCKWISE
             */
            val COUNTER_CLOCKWISE: FillMode get() = FillMode(5L)
            /**
             * The `texture_progress` fills from the center, expanding both towards the left and the right.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_BILINEAR_LEFT_AND_RIGHT
             */
            val BILINEAR_LEFT_AND_RIGHT: FillMode get() = FillMode(6L)
            /**
             * The `texture_progress` fills from the center, expanding both towards the top and the bottom.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_BILINEAR_TOP_AND_BOTTOM
             */
            val BILINEAR_TOP_AND_BOTTOM: FillMode get() = FillMode(7L)
            /**
             * Turns the node into a radial bar. The `texture_progress` fills radially from the center,
             * expanding both clockwise and counterclockwise. See `radial_center_offset`,
             * `radial_initial_angle` and `radial_fill_degrees` to control the way the bar fills up.
             *
             * Generated from Godot docs: TextureProgressBar.FILL_CLOCKWISE_AND_COUNTER_CLOCKWISE
             */
            val CLOCKWISE_AND_COUNTER_CLOCKWISE: FillMode get() = FillMode(8L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureProgressBar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextureProgressBar? =
            if (handle.address() == 0L) null else TextureProgressBar(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_UNDER_TEXTURE_HASH = 4051416890L
        @JvmField
        val setUnderTextureBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_under_texture", SET_UNDER_TEXTURE_HASH)

        private const val GET_UNDER_TEXTURE_HASH = 3635182373L
        @JvmField
        val getUnderTextureBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_under_texture", GET_UNDER_TEXTURE_HASH)

        private const val SET_PROGRESS_TEXTURE_HASH = 4051416890L
        @JvmField
        val setProgressTextureBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_progress_texture", SET_PROGRESS_TEXTURE_HASH)

        private const val GET_PROGRESS_TEXTURE_HASH = 3635182373L
        @JvmField
        val getProgressTextureBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_progress_texture", GET_PROGRESS_TEXTURE_HASH)

        private const val SET_OVER_TEXTURE_HASH = 4051416890L
        @JvmField
        val setOverTextureBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_over_texture", SET_OVER_TEXTURE_HASH)

        private const val GET_OVER_TEXTURE_HASH = 3635182373L
        @JvmField
        val getOverTextureBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_over_texture", GET_OVER_TEXTURE_HASH)

        private const val SET_FILL_MODE_HASH = 1286410249L
        @JvmField
        val setFillModeBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_fill_mode", SET_FILL_MODE_HASH)

        private const val GET_FILL_MODE_HASH = 2455072627L
        @JvmField
        val getFillModeBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_fill_mode", GET_FILL_MODE_HASH)

        private const val SET_TINT_UNDER_HASH = 2920490490L
        @JvmField
        val setTintUnderBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_tint_under", SET_TINT_UNDER_HASH)

        private const val GET_TINT_UNDER_HASH = 3444240500L
        @JvmField
        val getTintUnderBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_tint_under", GET_TINT_UNDER_HASH)

        private const val SET_TINT_PROGRESS_HASH = 2920490490L
        @JvmField
        val setTintProgressBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_tint_progress", SET_TINT_PROGRESS_HASH)

        private const val GET_TINT_PROGRESS_HASH = 3444240500L
        @JvmField
        val getTintProgressBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_tint_progress", GET_TINT_PROGRESS_HASH)

        private const val SET_TINT_OVER_HASH = 2920490490L
        @JvmField
        val setTintOverBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_tint_over", SET_TINT_OVER_HASH)

        private const val GET_TINT_OVER_HASH = 3444240500L
        @JvmField
        val getTintOverBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_tint_over", GET_TINT_OVER_HASH)

        private const val SET_TEXTURE_PROGRESS_OFFSET_HASH = 743155724L
        @JvmField
        val setTextureProgressOffsetBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_texture_progress_offset", SET_TEXTURE_PROGRESS_OFFSET_HASH)

        private const val GET_TEXTURE_PROGRESS_OFFSET_HASH = 3341600327L
        @JvmField
        val getTextureProgressOffsetBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_texture_progress_offset", GET_TEXTURE_PROGRESS_OFFSET_HASH)

        private const val SET_RADIAL_INITIAL_ANGLE_HASH = 373806689L
        @JvmField
        val setRadialInitialAngleBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_radial_initial_angle", SET_RADIAL_INITIAL_ANGLE_HASH)

        private const val GET_RADIAL_INITIAL_ANGLE_HASH = 191475506L
        @JvmField
        val getRadialInitialAngleBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_radial_initial_angle", GET_RADIAL_INITIAL_ANGLE_HASH)

        private const val SET_RADIAL_CENTER_OFFSET_HASH = 743155724L
        @JvmField
        val setRadialCenterOffsetBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_radial_center_offset", SET_RADIAL_CENTER_OFFSET_HASH)

        private const val GET_RADIAL_CENTER_OFFSET_HASH = 1497962370L
        @JvmField
        val getRadialCenterOffsetBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_radial_center_offset", GET_RADIAL_CENTER_OFFSET_HASH)

        private const val SET_FILL_DEGREES_HASH = 373806689L
        @JvmField
        val setFillDegreesBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_fill_degrees", SET_FILL_DEGREES_HASH)

        private const val GET_FILL_DEGREES_HASH = 191475506L
        @JvmField
        val getFillDegreesBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_fill_degrees", GET_FILL_DEGREES_HASH)

        private const val SET_STRETCH_MARGIN_HASH = 437707142L
        @JvmField
        val setStretchMarginBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_stretch_margin", SET_STRETCH_MARGIN_HASH)

        private const val GET_STRETCH_MARGIN_HASH = 1983885014L
        @JvmField
        val getStretchMarginBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_stretch_margin", GET_STRETCH_MARGIN_HASH)

        private const val SET_NINE_PATCH_STRETCH_HASH = 2586408642L
        @JvmField
        val setNinePatchStretchBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "set_nine_patch_stretch", SET_NINE_PATCH_STRETCH_HASH)

        private const val GET_NINE_PATCH_STRETCH_HASH = 36873697L
        @JvmField
        val getNinePatchStretchBind =
            ObjectCalls.getMethodBind("TextureProgressBar", "get_nine_patch_stretch", GET_NINE_PATCH_STRETCH_HASH)
    }
}
