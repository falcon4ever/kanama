package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Texture for 2D and 3D.
 *
 * Generated from Godot docs: Texture2D
 */
open class Texture2D(handle: GodotHandle) : Texture(handle) {
    /**
     * Returns the image format of the texture.
     *
     * Generated from Godot docs: Texture2D.get_format
     */
    fun getFormat(): Image.Format {
        checkOpen()
        return Image.Format(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatBind, segment))
    }

    /**
     * Returns the number of mipmaps of the texture.
     *
     * Generated from Godot docs: Texture2D.get_mipmap_count
     */
    fun getMipmapCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMipmapCountBind, segment)
    }

    /**
     * Returns the texture width in pixels.
     *
     * Generated from Godot docs: Texture2D.get_width
     */
    fun getWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getWidthBind, segment)
    }

    /**
     * Returns the texture height in pixels.
     *
     * Generated from Godot docs: Texture2D.get_height
     */
    fun getHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getHeightBind, segment)
    }

    /**
     * Returns the texture size in pixels.
     *
     * Generated from Godot docs: Texture2D.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSizeBind, segment)
    }

    /**
     * Returns `true` if this `Texture2D` has an alpha channel.
     *
     * Generated from Godot docs: Texture2D.has_alpha
     */
    fun hasAlpha(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasAlphaBind, segment)
    }

    /**
     * Returns `true` if the texture has mipmaps.
     *
     * Generated from Godot docs: Texture2D.has_mipmaps
     */
    fun hasMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasMipmapsBind, segment)
    }

    /**
     * Draws the texture using a `CanvasItem` with the `RenderingServer` API at the specified
     * `position`.
     *
     * Generated from Godot docs: Texture2D.draw
     */
    fun draw(canvasItem: RID, position: Vector2, modulate: Color, transpose: Boolean = false) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2ColorBoolArgs(Binds.drawBind, segment, canvasItem, position, modulate, transpose)
    }

    /**
     * Draws the texture using a `CanvasItem` with the `RenderingServer` API.
     *
     * Generated from Godot docs: Texture2D.draw_rect
     */
    fun drawRect(canvasItem: RID, rect: Rect2, tile: Boolean, modulate: Color, transpose: Boolean = false) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDRect2BoolColorBoolArgs(Binds.drawRectBind, segment, canvasItem, rect, tile, modulate, transpose)
    }

    /**
     * Draws a part of the texture using a `CanvasItem` with the `RenderingServer` API.
     *
     * Generated from Godot docs: Texture2D.draw_rect_region
     */
    fun drawRectRegion(canvasItem: RID, rect: Rect2, srcRect: Rect2, modulate: Color, transpose: Boolean = false, clipUv: Boolean = true) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDTwoRect2ColorTwoBoolArgs(Binds.drawRectRegionBind, segment, canvasItem, rect, srcRect, modulate, transpose, clipUv)
    }

    /**
     * Returns an `Image` that is a copy of data from this `Texture2D` (a new `Image` is created each
     * time). `Image`s can be accessed and manipulated directly. Note: This will return `null` if this
     * `Texture2D` is invalid. Note: This will fetch the texture data from the GPU, which might cause
     * performance problems when overused. Avoid calling `get_image` every frame, especially on large
     * textures.
     *
     * Generated from Godot docs: Texture2D.get_image
     */
    fun getImage(): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getImageBind, segment))
    }

    /**
     * Creates a placeholder version of this resource (`PlaceholderTexture2D`).
     *
     * Generated from Godot docs: Texture2D.create_placeholder
     */
    fun createPlaceholder(): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.createPlaceholderBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrapOwned(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Texture2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Texture2D? =
            if (handle.address() == 0L) null else RefCounted.owned(Texture2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Texture2D? =
            if (handle.address() == 0L) null else Texture2D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_FORMAT_HASH = 3847873762L
        @JvmField
        val getFormatBind =
            ObjectCalls.getMethodBind("Texture2D", "get_format", GET_FORMAT_HASH)

        private const val GET_MIPMAP_COUNT_HASH = 3905245786L
        @JvmField
        val getMipmapCountBind =
            ObjectCalls.getMethodBind("Texture2D", "get_mipmap_count", GET_MIPMAP_COUNT_HASH)

        private const val GET_WIDTH_HASH = 3905245786L
        @JvmField
        val getWidthBind =
            ObjectCalls.getMethodBind("Texture2D", "get_width", GET_WIDTH_HASH)

        private const val GET_HEIGHT_HASH = 3905245786L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("Texture2D", "get_height", GET_HEIGHT_HASH)

        private const val GET_SIZE_HASH = 3341600327L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("Texture2D", "get_size", GET_SIZE_HASH)

        private const val HAS_ALPHA_HASH = 36873697L
        @JvmField
        val hasAlphaBind =
            ObjectCalls.getMethodBind("Texture2D", "has_alpha", HAS_ALPHA_HASH)

        private const val HAS_MIPMAPS_HASH = 36873697L
        @JvmField
        val hasMipmapsBind =
            ObjectCalls.getMethodBind("Texture2D", "has_mipmaps", HAS_MIPMAPS_HASH)

        private const val DRAW_HASH = 2729649137L
        @JvmField
        val drawBind =
            ObjectCalls.getMethodBind("Texture2D", "draw", DRAW_HASH)

        private const val DRAW_RECT_HASH = 3499451691L
        @JvmField
        val drawRectBind =
            ObjectCalls.getMethodBind("Texture2D", "draw_rect", DRAW_RECT_HASH)

        private const val DRAW_RECT_REGION_HASH = 2963678660L
        @JvmField
        val drawRectRegionBind =
            ObjectCalls.getMethodBind("Texture2D", "draw_rect_region", DRAW_RECT_REGION_HASH)

        private const val GET_IMAGE_HASH = 4190603485L
        @JvmField
        val getImageBind =
            ObjectCalls.getMethodBind("Texture2D", "get_image", GET_IMAGE_HASH)

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        @JvmField
        val createPlaceholderBind =
            ObjectCalls.getMethodBind("Texture2D", "create_placeholder", CREATE_PLACEHOLDER_HASH)
    }
}
