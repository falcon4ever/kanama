package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: ColorPalette
 */
class ColorPalette(handle: MemorySegment) : Resource(handle) {
    val colors: List<Color>
        @JvmName("colorsProperty")
        get() = getColors()

    fun getColors(): List<Color> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getColorsBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): ColorPalette? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ColorPalette? =
            if (handle.address() == 0L) null else ColorPalette(handle)

        private const val GET_COLORS_HASH = 1392750486L
        private val getColorsBind by lazy {
            ObjectCalls.getMethodBind("ColorPalette", "get_colors", GET_COLORS_HASH)
        }
    }
}
