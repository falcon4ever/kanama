package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2i

/**
 * A 2D texture that supports drawing to itself via Blit calls.
 *
 * Generated from Godot docs: DrawableTexture2D
 */
class DrawableTexture2D(handle: GodotHandle) : Texture2D(handle) {
    /**
     * Sets the format of this DrawableTexture.
     *
     * Generated from Godot docs: DrawableTexture2D.set_format
     */
    fun setFormat(format: DrawableTexture2D.DrawableFormat) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFormatBind, segment, format.value)
    }

    /**
     * Sets if mipmaps should be used on this DrawableTexture.
     *
     * Generated from Godot docs: DrawableTexture2D.set_use_mipmaps
     */
    fun setUseMipmaps(mipmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseMipmapsBind, segment, mipmaps)
    }

    /**
     * Returns `true` if mipmaps are set to be used on this DrawableTexture.
     *
     * Generated from Godot docs: DrawableTexture2D.get_use_mipmaps
     */
    fun getUseMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseMipmapsBind, segment)
    }

    /**
     * Initializes the DrawableTexture to a White texture of the given `width`, `height`, and `format`.
     *
     * Generated from Godot docs: DrawableTexture2D.setup
     */
    fun setup(width: Int, height: Int, format: DrawableTexture2D.DrawableFormat, color: Color, useMipmaps: Boolean = false) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntLongColorBoolArgs(Binds.setupBind, segment, width, height, format.value, color, useMipmaps)
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
        checkOpen()
        ObjectCalls.ptrcallWithRect2iObjectColorIntObjectArgs(Binds.blitRectBind, segment, rect, source?.requireOpenHandle() ?: NULL_SEGMENT, modulate, mipmap, material?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Draws to the given `rect` on this texture, as well as on up to 3 DrawableTexture
     * `extra_targets`. All `extra_targets` must be the same size and DrawableFormat as the original
     * target, otherwise the Shader may fail. Expects up to 4 Texture `sources`, but will replace
     * missing `sources` with default Black Textures.
     *
     * Generated from Godot docs: DrawableTexture2D.blit_rect_multi
     */
    fun blitRectMulti(rect: Rect2i, sources: List<Texture2D>, extraTargets: List<DrawableTexture2D>, modulate: Color, mipmap: Int = 0, material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2iTwoObjectListColorIntObjectArgs(Binds.blitRectMultiBind, segment, rect, sources, extraTargets, modulate, mipmap, material?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Re-calculates the mipmaps for this texture on demand.
     *
     * Generated from Godot docs: DrawableTexture2D.generate_mipmaps
     */
    fun generateMipmaps() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.generateMipmapsBind, segment)
    }

    /**
     * Godot's `DrawableTexture2D.DrawableFormat` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`DrawableTexture2D.DrawableFormat.<NAME>`).
     *
     * Generated from Godot docs: DrawableTexture2D.DrawableFormat
     */
    @JvmInline
    value class DrawableFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * OpenGL texture format RGBA with four components, each with a bitdepth of 8.
             *
             * Generated from Godot docs: DrawableTexture2D.DRAWABLE_FORMAT_RGBA8
             */
            val RGBA8: DrawableFormat get() = DrawableFormat(0L)
            /**
             * OpenGL texture format RGBA with four components, each with a bitdepth of 8. When drawn to, an
             * sRGB to linear color space conversion is performed.
             *
             * Generated from Godot docs: DrawableTexture2D.DRAWABLE_FORMAT_RGBA8_SRGB
             */
            val RGBA8_SRGB: DrawableFormat get() = DrawableFormat(1L)
            /**
             * OpenGL texture format GL_RGBA16F where there are four components, each a 16-bit "half-precision"
             * floating-point value.
             *
             * Generated from Godot docs: DrawableTexture2D.DRAWABLE_FORMAT_RGBAH
             */
            val RGBAH: DrawableFormat get() = DrawableFormat(2L)
            /**
             * OpenGL texture format GL_RGBA32F where there are four components, each a 32-bit floating-point
             * value.
             *
             * Generated from Godot docs: DrawableTexture2D.DRAWABLE_FORMAT_RGBAF
             */
            val RGBAF: DrawableFormat get() = DrawableFormat(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): DrawableTexture2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): DrawableTexture2D? =
            if (handle.address() == 0L) null else RefCounted.owned(DrawableTexture2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): DrawableTexture2D? =
            if (handle.address() == 0L) null else DrawableTexture2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FORMAT_HASH = 2875673594L
        @JvmField
        val setFormatBind =
            ObjectCalls.getMethodBind("DrawableTexture2D", "set_format", SET_FORMAT_HASH)

        private const val SET_USE_MIPMAPS_HASH = 2586408642L
        @JvmField
        val setUseMipmapsBind =
            ObjectCalls.getMethodBind("DrawableTexture2D", "set_use_mipmaps", SET_USE_MIPMAPS_HASH)

        private const val GET_USE_MIPMAPS_HASH = 36873697L
        @JvmField
        val getUseMipmapsBind =
            ObjectCalls.getMethodBind("DrawableTexture2D", "get_use_mipmaps", GET_USE_MIPMAPS_HASH)

        private const val SETUP_HASH = 674365339L
        @JvmField
        val setupBind =
            ObjectCalls.getMethodBind("DrawableTexture2D", "setup", SETUP_HASH)

        private const val BLIT_RECT_HASH = 319217173L
        @JvmField
        val blitRectBind =
            ObjectCalls.getMethodBind("DrawableTexture2D", "blit_rect", BLIT_RECT_HASH)

        private const val BLIT_RECT_MULTI_HASH = 3074783066L
        @JvmField
        val blitRectMultiBind =
            ObjectCalls.getMethodBind("DrawableTexture2D", "blit_rect_multi", BLIT_RECT_MULTI_HASH)

        private const val GENERATE_MIPMAPS_HASH = 3218959716L
        @JvmField
        val generateMipmapsBind =
            ObjectCalls.getMethodBind("DrawableTexture2D", "generate_mipmaps", GENERATE_MIPMAPS_HASH)
    }
}
