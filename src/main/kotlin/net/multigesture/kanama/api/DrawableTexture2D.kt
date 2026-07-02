package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A 2D texture that supports drawing to itself via Blit calls.
 *
 * Generated from Godot docs: DrawableTexture2D
 */
class DrawableTexture2D(handle: MemorySegment) : Texture2D(handle) {
    fun setFormat(format: Long) {
        ObjectCalls.ptrcallWithLongArg(setFormatBind, handle, format)
    }

    fun setUseMipmaps(mipmaps: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseMipmapsBind, handle, mipmaps)
    }

    fun getUseMipmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseMipmapsBind, handle)
    }

    fun setup(width: Int, height: Int, format: Long, color: Color, useMipmaps: Boolean = false) {
        ObjectCalls.ptrcallWithTwoIntLongColorBoolArgs(setupBind, handle, width, height, format, color, useMipmaps)
    }

    fun generateMipmaps() {
        ObjectCalls.ptrcallNoArgs(generateMipmapsBind, handle)
    }

    companion object {
        const val DRAWABLE_FORMAT_RGBA8: Long = 0L
        const val DRAWABLE_FORMAT_RGBA8_SRGB: Long = 1L
        const val DRAWABLE_FORMAT_RGBAH: Long = 2L
        const val DRAWABLE_FORMAT_RGBAF: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): DrawableTexture2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): DrawableTexture2D? =
            if (handle.address() == 0L) null else DrawableTexture2D(handle)

        private const val SET_FORMAT_HASH = 2875673594L
        private val setFormatBind by lazy {
            ObjectCalls.getMethodBind("DrawableTexture2D", "set_format", SET_FORMAT_HASH)
        }

        private const val SET_USE_MIPMAPS_HASH = 2586408642L
        private val setUseMipmapsBind by lazy {
            ObjectCalls.getMethodBind("DrawableTexture2D", "set_use_mipmaps", SET_USE_MIPMAPS_HASH)
        }

        private const val GET_USE_MIPMAPS_HASH = 36873697L
        private val getUseMipmapsBind by lazy {
            ObjectCalls.getMethodBind("DrawableTexture2D", "get_use_mipmaps", GET_USE_MIPMAPS_HASH)
        }

        private const val SETUP_HASH = 674365339L
        private val setupBind by lazy {
            ObjectCalls.getMethodBind("DrawableTexture2D", "setup", SETUP_HASH)
        }

        private const val GENERATE_MIPMAPS_HASH = 3218959716L
        private val generateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("DrawableTexture2D", "generate_mipmaps", GENERATE_MIPMAPS_HASH)
        }
    }
}
