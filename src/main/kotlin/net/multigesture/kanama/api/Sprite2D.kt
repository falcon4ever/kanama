package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * General-purpose sprite node.
 *
 * Generated from Godot docs: Sprite2D
 */
class Sprite2D(handle: MemorySegment) : Node2D(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var centered: Boolean
        @JvmName("centeredProperty")
        get() = isCentered()
        @JvmName("setCenteredProperty")
        set(value) = setCentered(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var flipH: Boolean
        @JvmName("flipHProperty")
        get() = isFlippedH()
        @JvmName("setFlipHProperty")
        set(value) = setFlipH(value)

    var flipV: Boolean
        @JvmName("flipVProperty")
        get() = isFlippedV()
        @JvmName("setFlipVProperty")
        set(value) = setFlipV(value)

    var hframes: Int
        @JvmName("hframesProperty")
        get() = getHframes()
        @JvmName("setHframesProperty")
        set(value) = setHframes(value)

    var vframes: Int
        @JvmName("vframesProperty")
        get() = getVframes()
        @JvmName("setVframesProperty")
        set(value) = setVframes(value)

    var frame: Int
        @JvmName("frameProperty")
        get() = getFrame()
        @JvmName("setFrameProperty")
        set(value) = setFrame(value)

    var frameCoords: Vector2i
        @JvmName("frameCoordsProperty")
        get() = getFrameCoords()
        @JvmName("setFrameCoordsProperty")
        set(value) = setFrameCoords(value)

    var regionEnabled: Boolean
        @JvmName("regionEnabledProperty")
        get() = isRegionEnabled()
        @JvmName("setRegionEnabledProperty")
        set(value) = setRegionEnabled(value)

    var regionRect: Rect2
        @JvmName("regionRectProperty")
        get() = getRegionRect()
        @JvmName("setRegionRectProperty")
        set(value) = setRegionRect(value)

    var regionFilterClipEnabled: Boolean
        @JvmName("regionFilterClipEnabledProperty")
        get() = isRegionFilterClipEnabled()
        @JvmName("setRegionFilterClipEnabledProperty")
        set(value) = setRegionFilterClipEnabled(value)

    /**
     * `Texture2D` object to draw.
     *
     * Generated from Godot docs: Sprite2D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Texture2D` object to draw.
     *
     * Generated from Godot docs: Sprite2D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    /**
     * If `true`, texture is centered. Note: For games with a pixel art aesthetic, textures may appear
     * deformed when centered. This is caused by their position being between pixels. To prevent this,
     * set this property to `false`, or consider enabling
     * `ProjectSettings.rendering/2d/snap/snap_2d_vertices_to_pixel` and
     * `ProjectSettings.rendering/2d/snap/snap_2d_transforms_to_pixel`.
     *
     * Generated from Godot docs: Sprite2D.set_centered
     */
    fun setCentered(centered: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCenteredBind, handle, centered)
    }

    /**
     * If `true`, texture is centered. Note: For games with a pixel art aesthetic, textures may appear
     * deformed when centered. This is caused by their position being between pixels. To prevent this,
     * set this property to `false`, or consider enabling
     * `ProjectSettings.rendering/2d/snap/snap_2d_vertices_to_pixel` and
     * `ProjectSettings.rendering/2d/snap/snap_2d_transforms_to_pixel`.
     *
     * Generated from Godot docs: Sprite2D.is_centered
     */
    fun isCentered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCenteredBind, handle)
    }

    /**
     * The texture's drawing offset. Note: When you increase `offset`.y in Sprite2D, the sprite moves
     * downward on screen (i.e., +Y is down).
     *
     * Generated from Godot docs: Sprite2D.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    /**
     * The texture's drawing offset. Note: When you increase `offset`.y in Sprite2D, the sprite moves
     * downward on screen (i.e., +Y is down).
     *
     * Generated from Godot docs: Sprite2D.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: Sprite2D.set_flip_h
     */
    fun setFlipH(flipH: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, handle, flipH)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: Sprite2D.is_flipped_h
     */
    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedHBind, handle)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: Sprite2D.set_flip_v
     */
    fun setFlipV(flipV: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, handle, flipV)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: Sprite2D.is_flipped_v
     */
    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedVBind, handle)
    }

    /**
     * If `true`, texture is cut from a larger atlas texture. See `region_rect`. Note: When using a
     * custom `Shader` on a `Sprite2D`, the `UV` shader built-in will refer to the entire texture
     * space. Use the `REGION_RECT` built-in to get the currently visible region defined in
     * `region_rect` instead. See CanvasItem shaders
     * ($DOCS_URL/tutorials/shaders/shader_reference/canvas_item_shader.html) for details.
     *
     * Generated from Godot docs: Sprite2D.set_region_enabled
     */
    fun setRegionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRegionEnabledBind, handle, enabled)
    }

    /**
     * If `true`, texture is cut from a larger atlas texture. See `region_rect`. Note: When using a
     * custom `Shader` on a `Sprite2D`, the `UV` shader built-in will refer to the entire texture
     * space. Use the `REGION_RECT` built-in to get the currently visible region defined in
     * `region_rect` instead. See CanvasItem shaders
     * ($DOCS_URL/tutorials/shaders/shader_reference/canvas_item_shader.html) for details.
     *
     * Generated from Godot docs: Sprite2D.is_region_enabled
     */
    fun isRegionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRegionEnabledBind, handle)
    }

    /**
     * Returns `true` if the pixel at the given position is opaque, `false` otherwise. Also returns
     * `false` if the given position is out of bounds or this sprite's `texture` is `null`. `pos` is in
     * local coordinates.
     *
     * Generated from Godot docs: Sprite2D.is_pixel_opaque
     */
    fun isPixelOpaque(pos: Vector2): Boolean {
        return ObjectCalls.ptrcallWithVector2ArgRetBool(isPixelOpaqueBind, handle, pos)
    }

    /**
     * The region of the atlas texture to display. `region_enabled` must be `true`.
     *
     * Generated from Godot docs: Sprite2D.set_region_rect
     */
    fun setRegionRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRegionRectBind, handle, rect)
    }

    /**
     * The region of the atlas texture to display. `region_enabled` must be `true`.
     *
     * Generated from Godot docs: Sprite2D.get_region_rect
     */
    fun getRegionRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionRectBind, handle)
    }

    /**
     * If `true`, the area outside of the `region_rect` is clipped to avoid bleeding of the surrounding
     * texture pixels. `region_enabled` must be `true`.
     *
     * Generated from Godot docs: Sprite2D.set_region_filter_clip_enabled
     */
    fun setRegionFilterClipEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRegionFilterClipEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the area outside of the `region_rect` is clipped to avoid bleeding of the surrounding
     * texture pixels. `region_enabled` must be `true`.
     *
     * Generated from Godot docs: Sprite2D.is_region_filter_clip_enabled
     */
    fun isRegionFilterClipEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRegionFilterClipEnabledBind, handle)
    }

    /**
     * Current frame to display from sprite sheet. `hframes` or `vframes` must be greater than 1. This
     * property is automatically adjusted when `hframes` or `vframes` are changed to keep pointing to
     * the same visual frame (same column and row). If that's impossible, this value is reset to `0`.
     *
     * Generated from Godot docs: Sprite2D.set_frame
     */
    fun setFrame(frame: Int) {
        ObjectCalls.ptrcallWithIntArg(setFrameBind, handle, frame)
    }

    /**
     * Current frame to display from sprite sheet. `hframes` or `vframes` must be greater than 1. This
     * property is automatically adjusted when `hframes` or `vframes` are changed to keep pointing to
     * the same visual frame (same column and row). If that's impossible, this value is reset to `0`.
     *
     * Generated from Godot docs: Sprite2D.get_frame
     */
    fun getFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameBind, handle)
    }

    /**
     * Coordinates of the frame to display from sprite sheet. This is as an alias for the `frame`
     * property. `hframes` or `vframes` must be greater than 1.
     *
     * Generated from Godot docs: Sprite2D.set_frame_coords
     */
    fun setFrameCoords(coords: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setFrameCoordsBind, handle, coords)
    }

    /**
     * Coordinates of the frame to display from sprite sheet. This is as an alias for the `frame`
     * property. `hframes` or `vframes` must be greater than 1.
     *
     * Generated from Godot docs: Sprite2D.get_frame_coords
     */
    fun getFrameCoords(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getFrameCoordsBind, handle)
    }

    /**
     * The number of rows in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite2D.set_vframes
     */
    fun setVframes(vframes: Int) {
        ObjectCalls.ptrcallWithIntArg(setVframesBind, handle, vframes)
    }

    /**
     * The number of rows in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite2D.get_vframes
     */
    fun getVframes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVframesBind, handle)
    }

    /**
     * The number of columns in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite2D.set_hframes
     */
    fun setHframes(hframes: Int) {
        ObjectCalls.ptrcallWithIntArg(setHframesBind, handle, hframes)
    }

    /**
     * The number of columns in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite2D.get_hframes
     */
    fun getHframes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHframesBind, handle)
    }

    /**
     * Returns a `Rect2` representing the Sprite2D's boundary in local coordinates.
     *
     * Generated from Godot docs: Sprite2D.get_rect
     */
    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRectBind, handle)
    }

    object Signals {
        const val frameChanged: String = "frame_changed"
        const val textureChanged: String = "texture_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Sprite2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Sprite2D? =
            if (handle.address() == 0L) null else Sprite2D(handle)

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_CENTERED_HASH = 2586408642L
        private val setCenteredBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_centered", SET_CENTERED_HASH)
        }

        private const val IS_CENTERED_HASH = 36873697L
        private val isCenteredBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "is_centered", IS_CENTERED_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_FLIP_H_HASH = 2586408642L
        private val setFlipHBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_flip_h", SET_FLIP_H_HASH)
        }

        private const val IS_FLIPPED_H_HASH = 36873697L
        private val isFlippedHBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "is_flipped_h", IS_FLIPPED_H_HASH)
        }

        private const val SET_FLIP_V_HASH = 2586408642L
        private val setFlipVBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_flip_v", SET_FLIP_V_HASH)
        }

        private const val IS_FLIPPED_V_HASH = 36873697L
        private val isFlippedVBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "is_flipped_v", IS_FLIPPED_V_HASH)
        }

        private const val SET_REGION_ENABLED_HASH = 2586408642L
        private val setRegionEnabledBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_region_enabled", SET_REGION_ENABLED_HASH)
        }

        private const val IS_REGION_ENABLED_HASH = 36873697L
        private val isRegionEnabledBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "is_region_enabled", IS_REGION_ENABLED_HASH)
        }

        private const val IS_PIXEL_OPAQUE_HASH = 556197845L
        private val isPixelOpaqueBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "is_pixel_opaque", IS_PIXEL_OPAQUE_HASH)
        }

        private const val SET_REGION_RECT_HASH = 2046264180L
        private val setRegionRectBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_region_rect", SET_REGION_RECT_HASH)
        }

        private const val GET_REGION_RECT_HASH = 1639390495L
        private val getRegionRectBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "get_region_rect", GET_REGION_RECT_HASH)
        }

        private const val SET_REGION_FILTER_CLIP_ENABLED_HASH = 2586408642L
        private val setRegionFilterClipEnabledBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_region_filter_clip_enabled", SET_REGION_FILTER_CLIP_ENABLED_HASH)
        }

        private const val IS_REGION_FILTER_CLIP_ENABLED_HASH = 36873697L
        private val isRegionFilterClipEnabledBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "is_region_filter_clip_enabled", IS_REGION_FILTER_CLIP_ENABLED_HASH)
        }

        private const val SET_FRAME_HASH = 1286410249L
        private val setFrameBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_frame", SET_FRAME_HASH)
        }

        private const val GET_FRAME_HASH = 3905245786L
        private val getFrameBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "get_frame", GET_FRAME_HASH)
        }

        private const val SET_FRAME_COORDS_HASH = 1130785943L
        private val setFrameCoordsBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_frame_coords", SET_FRAME_COORDS_HASH)
        }

        private const val GET_FRAME_COORDS_HASH = 3690982128L
        private val getFrameCoordsBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "get_frame_coords", GET_FRAME_COORDS_HASH)
        }

        private const val SET_VFRAMES_HASH = 1286410249L
        private val setVframesBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_vframes", SET_VFRAMES_HASH)
        }

        private const val GET_VFRAMES_HASH = 3905245786L
        private val getVframesBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "get_vframes", GET_VFRAMES_HASH)
        }

        private const val SET_HFRAMES_HASH = 1286410249L
        private val setHframesBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "set_hframes", SET_HFRAMES_HASH)
        }

        private const val GET_HFRAMES_HASH = 3905245786L
        private val getHframesBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "get_hframes", GET_HFRAMES_HASH)
        }

        private const val GET_RECT_HASH = 1639390495L
        private val getRectBind by lazy {
            ObjectCalls.getMethodBind("Sprite2D", "get_rect", GET_RECT_HASH)
        }
    }
}
