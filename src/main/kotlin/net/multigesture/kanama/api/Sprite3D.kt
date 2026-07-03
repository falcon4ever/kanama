package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2i

/**
 * 2D sprite node in a 3D world.
 *
 * Generated from Godot docs: Sprite3D
 */
class Sprite3D(handle: MemorySegment) : SpriteBase3D(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

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

    /**
     * `Texture2D` object to draw. If `GeometryInstance3D.material_override` is used, this will be
     * overridden. The size information is still used.
     *
     * Generated from Godot docs: Sprite3D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Texture2D` object to draw. If `GeometryInstance3D.material_override` is used, this will be
     * overridden. The size information is still used.
     *
     * Generated from Godot docs: Sprite3D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    /**
     * If `true`, the sprite will use `region_rect` and display only the specified part of its texture.
     *
     * Generated from Godot docs: Sprite3D.set_region_enabled
     */
    fun setRegionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRegionEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the sprite will use `region_rect` and display only the specified part of its texture.
     *
     * Generated from Godot docs: Sprite3D.is_region_enabled
     */
    fun isRegionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRegionEnabledBind, handle)
    }

    /**
     * The region of the atlas texture to display. `region_enabled` must be `true`.
     *
     * Generated from Godot docs: Sprite3D.set_region_rect
     */
    fun setRegionRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRegionRectBind, handle, rect)
    }

    /**
     * The region of the atlas texture to display. `region_enabled` must be `true`.
     *
     * Generated from Godot docs: Sprite3D.get_region_rect
     */
    fun getRegionRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionRectBind, handle)
    }

    /**
     * Current frame to display from sprite sheet. `hframes` or `vframes` must be greater than 1. This
     * property is automatically adjusted when `hframes` or `vframes` are changed to keep pointing to
     * the same visual frame (same column and row). If that's impossible, this value is reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.set_frame
     */
    fun setFrame(frame: Int) {
        ObjectCalls.ptrcallWithIntArg(setFrameBind, handle, frame)
    }

    /**
     * Current frame to display from sprite sheet. `hframes` or `vframes` must be greater than 1. This
     * property is automatically adjusted when `hframes` or `vframes` are changed to keep pointing to
     * the same visual frame (same column and row). If that's impossible, this value is reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.get_frame
     */
    fun getFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameBind, handle)
    }

    /**
     * Coordinates of the frame to display from sprite sheet. This is as an alias for the `frame`
     * property. `hframes` or `vframes` must be greater than 1.
     *
     * Generated from Godot docs: Sprite3D.set_frame_coords
     */
    fun setFrameCoords(coords: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setFrameCoordsBind, handle, coords)
    }

    /**
     * Coordinates of the frame to display from sprite sheet. This is as an alias for the `frame`
     * property. `hframes` or `vframes` must be greater than 1.
     *
     * Generated from Godot docs: Sprite3D.get_frame_coords
     */
    fun getFrameCoords(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getFrameCoordsBind, handle)
    }

    /**
     * The number of rows in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.set_vframes
     */
    fun setVframes(vframes: Int) {
        ObjectCalls.ptrcallWithIntArg(setVframesBind, handle, vframes)
    }

    /**
     * The number of rows in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.get_vframes
     */
    fun getVframes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVframesBind, handle)
    }

    /**
     * The number of columns in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.set_hframes
     */
    fun setHframes(hframes: Int) {
        ObjectCalls.ptrcallWithIntArg(setHframesBind, handle, hframes)
    }

    /**
     * The number of columns in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.get_hframes
     */
    fun getHframes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHframesBind, handle)
    }

    object Signals {
        const val frameChanged: String = "frame_changed"
        const val textureChanged: String = "texture_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Sprite3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Sprite3D? =
            if (handle.address() == 0L) null else Sprite3D(handle)

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_REGION_ENABLED_HASH = 2586408642L
        private val setRegionEnabledBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "set_region_enabled", SET_REGION_ENABLED_HASH)
        }

        private const val IS_REGION_ENABLED_HASH = 36873697L
        private val isRegionEnabledBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "is_region_enabled", IS_REGION_ENABLED_HASH)
        }

        private const val SET_REGION_RECT_HASH = 2046264180L
        private val setRegionRectBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "set_region_rect", SET_REGION_RECT_HASH)
        }

        private const val GET_REGION_RECT_HASH = 1639390495L
        private val getRegionRectBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "get_region_rect", GET_REGION_RECT_HASH)
        }

        private const val SET_FRAME_HASH = 1286410249L
        private val setFrameBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "set_frame", SET_FRAME_HASH)
        }

        private const val GET_FRAME_HASH = 3905245786L
        private val getFrameBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "get_frame", GET_FRAME_HASH)
        }

        private const val SET_FRAME_COORDS_HASH = 1130785943L
        private val setFrameCoordsBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "set_frame_coords", SET_FRAME_COORDS_HASH)
        }

        private const val GET_FRAME_COORDS_HASH = 3690982128L
        private val getFrameCoordsBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "get_frame_coords", GET_FRAME_COORDS_HASH)
        }

        private const val SET_VFRAMES_HASH = 1286410249L
        private val setVframesBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "set_vframes", SET_VFRAMES_HASH)
        }

        private const val GET_VFRAMES_HASH = 3905245786L
        private val getVframesBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "get_vframes", GET_VFRAMES_HASH)
        }

        private const val SET_HFRAMES_HASH = 1286410249L
        private val setHframesBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "set_hframes", SET_HFRAMES_HASH)
        }

        private const val GET_HFRAMES_HASH = 3905245786L
        private val getHframesBind by lazy {
            ObjectCalls.getMethodBind("Sprite3D", "get_hframes", GET_HFRAMES_HASH)
        }
    }
}
