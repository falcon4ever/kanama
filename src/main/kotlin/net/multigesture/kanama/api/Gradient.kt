package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A color transition.
 *
 * Generated from Godot docs: Gradient
 */
class Gradient(handle: MemorySegment) : Resource(handle) {
    var interpolationMode: Long
        @JvmName("interpolationModeProperty")
        get() = getInterpolationMode()
        @JvmName("setInterpolationModeProperty")
        set(value) = setInterpolationMode(value)

    var interpolationColorSpace: Long
        @JvmName("interpolationColorSpaceProperty")
        get() = getInterpolationColorSpace()
        @JvmName("setInterpolationColorSpaceProperty")
        set(value) = setInterpolationColorSpace(value)

    var offsets: List<Float>
        @JvmName("offsetsProperty")
        get() = getOffsets()
        @JvmName("setOffsetsProperty")
        set(value) = setOffsets(value)

    var colors: List<Color>
        @JvmName("colorsProperty")
        get() = getColors()
        @JvmName("setColorsProperty")
        set(value) = setColors(value)

    fun addPoint(offset: Double, color: Color) {
        ObjectCalls.ptrcallWithDoubleAndColorArg(addPointBind, handle, offset, color)
    }

    fun removePoint(point: Int) {
        ObjectCalls.ptrcallWithIntArg(removePointBind, handle, point)
    }

    fun setOffset(point: Int, offset: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setOffsetBind, handle, point, offset)
    }

    fun getOffset(point: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getOffsetBind, handle, point)
    }

    /**
     * Reverses/mirrors the gradient. Note: This method mirrors all points around the middle of the
     * gradient, which may produce unexpected results when `interpolation_mode` is set to
     * `GRADIENT_INTERPOLATE_CONSTANT`.
     *
     * Generated from Godot docs: Gradient.reverse
     */
    fun reverse() {
        ObjectCalls.ptrcallNoArgs(reverseBind, handle)
    }

    fun setColor(point: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setColorBind, handle, point, color)
    }

    fun getColor(point: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getColorBind, handle, point)
    }

    /**
     * Returns the interpolated color specified by `offset`. `offset` should be between `0.0` and `1.0`
     * (inclusive). Using a value lower than `0.0` will return the same color as `0.0`, and using a
     * value higher than `1.0` will return the same color as `1.0`. If your input value is not within
     * this range, consider using `@GlobalScope.remap` on the input value with output values set to
     * `0.0` and `1.0`.
     *
     * Generated from Godot docs: Gradient.sample
     */
    fun sample(offset: Double): Color {
        return ObjectCalls.ptrcallWithDoubleArgRetColor(sampleBind, handle, offset)
    }

    fun getPointCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPointCountBind, handle)
    }

    fun setOffsets(offsets: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setOffsetsBind, handle, offsets)
    }

    fun getOffsets(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getOffsetsBind, handle)
    }

    fun setColors(colors: List<Color>) {
        ObjectCalls.ptrcallWithPackedColorListArg(setColorsBind, handle, colors)
    }

    fun getColors(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getColorsBind, handle)
    }

    fun setInterpolationMode(interpolationMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setInterpolationModeBind, handle, interpolationMode)
    }

    fun getInterpolationMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getInterpolationModeBind, handle)
    }

    fun setInterpolationColorSpace(interpolationColorSpace: Long) {
        ObjectCalls.ptrcallWithLongArg(setInterpolationColorSpaceBind, handle, interpolationColorSpace)
    }

    fun getInterpolationColorSpace(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getInterpolationColorSpaceBind, handle)
    }

    companion object {
        const val GRADIENT_INTERPOLATE_LINEAR: Long = 0L
        const val GRADIENT_INTERPOLATE_CONSTANT: Long = 1L
        const val GRADIENT_INTERPOLATE_CUBIC: Long = 2L
        const val GRADIENT_COLOR_SPACE_SRGB: Long = 0L
        const val GRADIENT_COLOR_SPACE_LINEAR_SRGB: Long = 1L
        const val GRADIENT_COLOR_SPACE_OKLAB: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Gradient? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Gradient? =
            if (handle.address() == 0L) null else Gradient(handle)

        private const val ADD_POINT_HASH = 3629403827L
        private val addPointBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "add_point", ADD_POINT_HASH)
        }

        private const val REMOVE_POINT_HASH = 1286410249L
        private val removePointBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "remove_point", REMOVE_POINT_HASH)
        }

        private const val SET_OFFSET_HASH = 1602489585L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 4025615559L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "get_offset", GET_OFFSET_HASH)
        }

        private const val REVERSE_HASH = 3218959716L
        private val reverseBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "reverse", REVERSE_HASH)
        }

        private const val SET_COLOR_HASH = 2878471219L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 2624840992L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "get_color", GET_COLOR_HASH)
        }

        private const val SAMPLE_HASH = 1250405064L
        private val sampleBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "sample", SAMPLE_HASH)
        }

        private const val GET_POINT_COUNT_HASH = 3905245786L
        private val getPointCountBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "get_point_count", GET_POINT_COUNT_HASH)
        }

        private const val SET_OFFSETS_HASH = 2899603908L
        private val setOffsetsBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "set_offsets", SET_OFFSETS_HASH)
        }

        private const val GET_OFFSETS_HASH = 675695659L
        private val getOffsetsBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "get_offsets", GET_OFFSETS_HASH)
        }

        private const val SET_COLORS_HASH = 3546319833L
        private val setColorsBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "set_colors", SET_COLORS_HASH)
        }

        private const val GET_COLORS_HASH = 1392750486L
        private val getColorsBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "get_colors", GET_COLORS_HASH)
        }

        private const val SET_INTERPOLATION_MODE_HASH = 1971444490L
        private val setInterpolationModeBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "set_interpolation_mode", SET_INTERPOLATION_MODE_HASH)
        }

        private const val GET_INTERPOLATION_MODE_HASH = 3674172981L
        private val getInterpolationModeBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "get_interpolation_mode", GET_INTERPOLATION_MODE_HASH)
        }

        private const val SET_INTERPOLATION_COLOR_SPACE_HASH = 3685995981L
        private val setInterpolationColorSpaceBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "set_interpolation_color_space", SET_INTERPOLATION_COLOR_SPACE_HASH)
        }

        private const val GET_INTERPOLATION_COLOR_SPACE_HASH = 1538296000L
        private val getInterpolationColorSpaceBind by lazy {
            ObjectCalls.getMethodBind("Gradient", "get_interpolation_color_space", GET_INTERPOLATION_COLOR_SPACE_HASH)
        }
    }
}
