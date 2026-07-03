package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A resource class for managing a palette of colors, which can be loaded and saved using
 * `ColorPicker`.
 *
 * Generated from Godot docs: ColorPalette
 */
class ColorPalette(handle: MemorySegment) : Resource(handle) {
    var colors: List<Color>
        @JvmName("colorsProperty")
        get() = getColors()
        @JvmName("setColorsProperty")
        set(value) = setColors(value)

    /**
     * A `PackedColorArray` containing the colors in the palette.
     *
     * Generated from Godot docs: ColorPalette.set_colors
     */
    fun setColors(colors: List<Color>) {
        ObjectCalls.ptrcallWithPackedColorListArg(setColorsBind, handle, colors)
    }

    /**
     * A `PackedColorArray` containing the colors in the palette.
     *
     * Generated from Godot docs: ColorPalette.get_colors
     */
    fun getColors(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getColorsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ColorPalette? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ColorPalette? =
            if (handle.address() == 0L) null else ColorPalette(handle)

        private const val SET_COLORS_HASH = 3546319833L
        private val setColorsBind by lazy {
            ObjectCalls.getMethodBind("ColorPalette", "set_colors", SET_COLORS_HASH)
        }

        private const val GET_COLORS_HASH = 1392750486L
        private val getColorsBind by lazy {
            ObjectCalls.getMethodBind("ColorPalette", "get_colors", GET_COLORS_HASH)
        }
    }
}
