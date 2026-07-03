package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2i

/**
 * A 2D texture that supports drawing to itself via Blit calls.
 *
 * Generated from Godot docs: DrawableTexture2D
 */
class DrawableTexture2D(handle: MemorySegment) : Texture2D(handle) {
    /**
     * Sets the format of this DrawableTexture.
     *
     * Generated from Godot docs: DrawableTexture2D.set_format
     */
    fun setFormat(format: Long) {
        ObjectCalls.ptrcallWithLongArg(setFormatBind, handle, format)
    }

    /**
     * Sets if mipmaps should be used on this DrawableTexture.
     *
     * Generated from Godot docs: DrawableTexture2D.set_use_mipmaps
     */
    fun setUseMipmaps(mipmaps: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseMipmapsBind, handle, mipmaps)
    }

    /**
     * Returns `true` if mipmaps are set to be used on this DrawableTexture.
     *
     * Generated from Godot docs: DrawableTexture2D.get_use_mipmaps
     */
    fun getUseMipmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseMipmapsBind, handle)
    }

    /**
     * Initializes the DrawableTexture to a White texture of the given `width`, `height`, and `format`.
     *
     * Generated from Godot docs: DrawableTexture2D.setup
     */
    fun setup(width: Int, height: Int, format: Long, color: Color, useMipmaps: Boolean = false) {
        ObjectCalls.ptrcallWithTwoIntLongColorBoolArgs(setupBind, handle, width, height, format, color, useMipmaps)
    }

    /**
     * Draws to given `rect` on this texture by copying from the given `source`. A `modulate` color can
     * be passed in for the shader to use, but defaults to White. The `mipmap` value can specify a draw
     * to a lower mipmap level. The `material` parameter can take a ShaderMaterial with a TextureBlit
     * Shader for custom drawing behavior.
     *
     * Generated from Godot docs: DrawableTexture2D.blit_rect
     */
    fun blitRect(rect: Rect2i, source: Texture2D?, modulate: Color, mipmap: Int = 0, material: Material?) {
        ObjectCalls.ptrcallWithRect2iObjectColorIntObjectArgs(blitRectBind, handle, rect, source?.requireOpenHandle() ?: MemorySegment.NULL, modulate, mipmap, material?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Re-calculates the mipmaps for this texture on demand.
     *
     * Generated from Godot docs: DrawableTexture2D.generate_mipmaps
     */
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

        private const val BLIT_RECT_HASH = 319217173L
        private val blitRectBind by lazy {
            ObjectCalls.getMethodBind("DrawableTexture2D", "blit_rect", BLIT_RECT_HASH)
        }

        private const val GENERATE_MIPMAPS_HASH = 3218959716L
        private val generateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("DrawableTexture2D", "generate_mipmaps", GENERATE_MIPMAPS_HASH)
        }
    }
}
