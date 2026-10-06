package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * A resource class for managing a palette of colors, which can be loaded and saved using
 * `ColorPicker`.
 *
 * Generated from Godot docs: ColorPalette
 */
class ColorPalette(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithPackedColorListArg(Binds.setColorsBind, segment, colors)
    }

    /**
     * A `PackedColorArray` containing the colors in the palette.
     *
     * Generated from Godot docs: ColorPalette.get_colors
     */
    fun getColors(): List<Color> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(Binds.getColorsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ColorPalette? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ColorPalette? =
            if (handle.address() == 0L) null else RefCounted.owned(ColorPalette(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ColorPalette? =
            if (handle.address() == 0L) null else ColorPalette(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_COLORS_HASH = 3546319833L
        @JvmField
        val setColorsBind =
            ObjectCalls.getMethodBind("ColorPalette", "set_colors", SET_COLORS_HASH)

        private const val GET_COLORS_HASH = 1392750486L
        @JvmField
        val getColorsBind =
            ObjectCalls.getMethodBind("ColorPalette", "get_colors", GET_COLORS_HASH)
    }
}
