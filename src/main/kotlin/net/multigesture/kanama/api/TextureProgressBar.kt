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

    fun setUnderTexture(tex: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setUnderTextureBind, handle, listOf(tex?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getUnderTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getUnderTextureBind, handle))
    }

    fun setProgressTexture(tex: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setProgressTextureBind, handle, listOf(tex?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getProgressTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProgressTextureBind, handle))
    }

    fun setOverTexture(tex: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setOverTextureBind, handle, listOf(tex?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getOverTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOverTextureBind, handle))
    }

    fun setFillMode(mode: Int) {
        ObjectCalls.ptrcallWithIntArg(setFillModeBind, handle, mode)
    }

    fun getFillMode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFillModeBind, handle)
    }

    fun setTintUnder(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(setTintUnderBind, handle, tint)
    }

    fun getTintUnder(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTintUnderBind, handle)
    }

    fun setTintProgress(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(setTintProgressBind, handle, tint)
    }

    fun getTintProgress(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTintProgressBind, handle)
    }

    fun setTintOver(tint: Color) {
        ObjectCalls.ptrcallWithColorArg(setTintOverBind, handle, tint)
    }

    fun getTintOver(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTintOverBind, handle)
    }

    fun setTextureProgressOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTextureProgressOffsetBind, handle, offset)
    }

    fun getTextureProgressOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTextureProgressOffsetBind, handle)
    }

    fun setRadialInitialAngle(mode: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadialInitialAngleBind, handle, mode)
    }

    fun getRadialInitialAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadialInitialAngleBind, handle)
    }

    fun setRadialCenterOffset(mode: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setRadialCenterOffsetBind, handle, mode)
    }

    fun getRadialCenterOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getRadialCenterOffsetBind, handle)
    }

    fun setFillDegrees(mode: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFillDegreesBind, handle, mode)
    }

    fun getFillDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFillDegreesBind, handle)
    }

    fun setStretchMargin(margin: Long, value: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setStretchMarginBind, handle, margin, value)
    }

    fun getStretchMargin(margin: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getStretchMarginBind, handle, margin)
    }

    fun setNinePatchStretch(stretch: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNinePatchStretchBind, handle, stretch)
    }

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
