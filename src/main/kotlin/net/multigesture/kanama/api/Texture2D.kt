package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Texture for 2D and 3D.
 *
 * Generated from Godot docs: Texture2D
 */
open class Texture2D(handle: MemorySegment) : Texture(handle) {
    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    fun getMipmapCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMipmapCountBind, handle)
    }

    fun getWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getWidthBind, handle)
    }

    fun getHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightBind, handle)
    }

    fun getSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    fun hasAlpha(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAlphaBind, handle)
    }

    fun hasMipmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasMipmapsBind, handle)
    }

    /**
     * Draws the texture using a `CanvasItem` with the `RenderingServer` API at the specified
     * `position`.
     *
     * Generated from Godot docs: Texture2D.draw
     */
    fun draw(canvasItem: RID, position: Vector2, modulate: Color, transpose: Boolean = false) {
        ObjectCalls.ptrcallWithRIDVector2ColorBoolArgs(drawBind, handle, canvasItem, position, modulate, transpose)
    }

    fun drawRect(canvasItem: RID, rect: Rect2, tile: Boolean, modulate: Color, transpose: Boolean = false) {
        ObjectCalls.ptrcallWithRIDRect2BoolColorBoolArgs(drawRectBind, handle, canvasItem, rect, tile, modulate, transpose)
    }

    fun drawRectRegion(canvasItem: RID, rect: Rect2, srcRect: Rect2, modulate: Color, transpose: Boolean = false, clipUv: Boolean = true) {
        ObjectCalls.ptrcallWithRIDTwoRect2ColorTwoBoolArgs(drawRectRegionBind, handle, canvasItem, rect, srcRect, modulate, transpose, clipUv)
    }

    fun getImage(): Image? {
        return Image.wrap(ObjectCalls.ptrcallNoArgsRetObject(getImageBind, handle))
    }

    fun createPlaceholder(): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Texture2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Texture2D? =
            if (handle.address() == 0L) null else Texture2D(handle)

        private const val GET_FORMAT_HASH = 3847873762L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "get_format", GET_FORMAT_HASH)
        }

        private const val GET_MIPMAP_COUNT_HASH = 3905245786L
        private val getMipmapCountBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "get_mipmap_count", GET_MIPMAP_COUNT_HASH)
        }

        private const val GET_WIDTH_HASH = 3905245786L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "get_width", GET_WIDTH_HASH)
        }

        private const val GET_HEIGHT_HASH = 3905245786L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "get_height", GET_HEIGHT_HASH)
        }

        private const val GET_SIZE_HASH = 3341600327L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "get_size", GET_SIZE_HASH)
        }

        private const val HAS_ALPHA_HASH = 36873697L
        private val hasAlphaBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "has_alpha", HAS_ALPHA_HASH)
        }

        private const val HAS_MIPMAPS_HASH = 36873697L
        private val hasMipmapsBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "has_mipmaps", HAS_MIPMAPS_HASH)
        }

        private const val DRAW_HASH = 2729649137L
        private val drawBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "draw", DRAW_HASH)
        }

        private const val DRAW_RECT_HASH = 3499451691L
        private val drawRectBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "draw_rect", DRAW_RECT_HASH)
        }

        private const val DRAW_RECT_REGION_HASH = 2963678660L
        private val drawRectRegionBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "draw_rect_region", DRAW_RECT_REGION_HASH)
        }

        private const val GET_IMAGE_HASH = 4190603485L
        private val getImageBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "get_image", GET_IMAGE_HASH)
        }

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }
    }
}
