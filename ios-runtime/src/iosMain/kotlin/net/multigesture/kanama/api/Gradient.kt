package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color

/**
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

    val colors: List<Color>
        @JvmName("colorsProperty")
        get() = getColors()

    fun addPoint(offset: Double, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleAndColorArg(addPointBind, handle, offset, color)
    }

    fun removePoint(point: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(removePointBind, handle, point)
    }

    fun setOffset(point: Int, offset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(setOffsetBind, handle, point, offset)
    }

    fun getOffset(point: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getOffsetBind, handle, point)
    }

    fun reverse() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(reverseBind, handle)
    }

    fun setColor(point: Int, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndColorArg(setColorBind, handle, point, color)
    }

    fun getColor(point: Int): Color {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetColor(getColorBind, handle, point)
    }

    fun sample(offset: Double): Color {
        checkOpen()
        return ObjectCalls.ptrcallWithDoubleArgRetColor(sampleBind, handle, offset)
    }

    fun getPointCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPointCountBind, handle)
    }

    fun setOffsets(offsets: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setOffsetsBind, handle, offsets)
    }

    fun getOffsets(): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getOffsetsBind, handle)
    }

    fun getColors(): List<Color> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getColorsBind, handle)
    }

    fun setInterpolationMode(interpolationMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setInterpolationModeBind, handle, interpolationMode)
    }

    fun getInterpolationMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getInterpolationModeBind, handle)
    }

    fun setInterpolationColorSpace(interpolationColorSpace: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setInterpolationColorSpaceBind, handle, interpolationColorSpace)
    }

    fun getInterpolationColorSpace(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getInterpolationColorSpaceBind, handle)
    }

    companion object {
        const val GRADIENT_INTERPOLATE_LINEAR: Long = 0L
        const val GRADIENT_INTERPOLATE_CONSTANT: Long = 1L
        const val GRADIENT_INTERPOLATE_CUBIC: Long = 2L
        const val GRADIENT_COLOR_SPACE_SRGB: Long = 0L
        const val GRADIENT_COLOR_SPACE_LINEAR_SRGB: Long = 1L
        const val GRADIENT_COLOR_SPACE_OKLAB: Long = 2L

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
