package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2i

/**
 * 2D sprite node in a 3D world.
 *
 * Generated from Godot docs: Sprite3D
 */
class Sprite3D(handle: GodotHandle) : SpriteBase3D(handle) {
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * `Texture2D` object to draw. If `GeometryInstance3D.material_override` is used, this will be
     * overridden. The size information is still used.
     *
     * Generated from Godot docs: Sprite3D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    /**
     * If `true`, the sprite will use `region_rect` and display only the specified part of its texture.
     *
     * Generated from Godot docs: Sprite3D.set_region_enabled
     */
    fun setRegionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setRegionEnabledBind, segment, enabled)
    }

    /**
     * If `true`, the sprite will use `region_rect` and display only the specified part of its texture.
     *
     * Generated from Godot docs: Sprite3D.is_region_enabled
     */
    fun isRegionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRegionEnabledBind, segment)
    }

    /**
     * The region of the atlas texture to display. `region_enabled` must be `true`.
     *
     * Generated from Godot docs: Sprite3D.set_region_rect
     */
    fun setRegionRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(Binds.setRegionRectBind, segment, rect)
    }

    /**
     * The region of the atlas texture to display. `region_enabled` must be `true`.
     *
     * Generated from Godot docs: Sprite3D.get_region_rect
     */
    fun getRegionRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.getRegionRectBind, segment)
    }

    /**
     * Current frame to display from sprite sheet. `hframes` or `vframes` must be greater than 1. This
     * property is automatically adjusted when `hframes` or `vframes` are changed to keep pointing to
     * the same visual frame (same column and row). If that's impossible, this value is reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.set_frame
     */
    fun setFrame(frame: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setFrameBind, segment, frame)
    }

    /**
     * Current frame to display from sprite sheet. `hframes` or `vframes` must be greater than 1. This
     * property is automatically adjusted when `hframes` or `vframes` are changed to keep pointing to
     * the same visual frame (same column and row). If that's impossible, this value is reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.get_frame
     */
    fun getFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFrameBind, segment)
    }

    /**
     * Coordinates of the frame to display from sprite sheet. This is as an alias for the `frame`
     * property. `hframes` or `vframes` must be greater than 1.
     *
     * Generated from Godot docs: Sprite3D.set_frame_coords
     */
    fun setFrameCoords(coords: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setFrameCoordsBind, segment, coords)
    }

    /**
     * Coordinates of the frame to display from sprite sheet. This is as an alias for the `frame`
     * property. `hframes` or `vframes` must be greater than 1.
     *
     * Generated from Godot docs: Sprite3D.get_frame_coords
     */
    fun getFrameCoords(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getFrameCoordsBind, segment)
    }

    /**
     * The number of rows in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.set_vframes
     */
    fun setVframes(vframes: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setVframesBind, segment, vframes)
    }

    /**
     * The number of rows in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.get_vframes
     */
    fun getVframes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getVframesBind, segment)
    }

    /**
     * The number of columns in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.set_hframes
     */
    fun setHframes(hframes: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setHframesBind, segment, hframes)
    }

    /**
     * The number of columns in the sprite sheet. When this property is changed, `frame` is adjusted so
     * that the same visual frame is maintained (same row and column). If that's impossible, `frame` is
     * reset to `0`.
     *
     * Generated from Godot docs: Sprite3D.get_hframes
     */
    fun getHframes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getHframesBind, segment)
    }

    /** Signal `frame_changed()`; see [TypedSignal]. */
    val frameChanged: Signal0
        @JvmName("frameChangedTypedSignal")
        get() = Signal0(this, "frame_changed")

    /** Signal `texture_changed()`; see [TypedSignal]. */
    val textureChanged: Signal0
        @JvmName("textureChangedTypedSignal")
        get() = Signal0(this, "texture_changed")

    object Signals {
        const val frameChanged: String = "frame_changed"
        const val textureChanged: String = "texture_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Sprite3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Sprite3D? =
            if (handle.address() == 0L) null else Sprite3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TEXTURE_HASH = 4051416890L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("Sprite3D", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 3635182373L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("Sprite3D", "get_texture", GET_TEXTURE_HASH)

        private const val SET_REGION_ENABLED_HASH = 2586408642L
        @JvmField
        val setRegionEnabledBind =
            ObjectCalls.getMethodBind("Sprite3D", "set_region_enabled", SET_REGION_ENABLED_HASH)

        private const val IS_REGION_ENABLED_HASH = 36873697L
        @JvmField
        val isRegionEnabledBind =
            ObjectCalls.getMethodBind("Sprite3D", "is_region_enabled", IS_REGION_ENABLED_HASH)

        private const val SET_REGION_RECT_HASH = 2046264180L
        @JvmField
        val setRegionRectBind =
            ObjectCalls.getMethodBind("Sprite3D", "set_region_rect", SET_REGION_RECT_HASH)

        private const val GET_REGION_RECT_HASH = 1639390495L
        @JvmField
        val getRegionRectBind =
            ObjectCalls.getMethodBind("Sprite3D", "get_region_rect", GET_REGION_RECT_HASH)

        private const val SET_FRAME_HASH = 1286410249L
        @JvmField
        val setFrameBind =
            ObjectCalls.getMethodBind("Sprite3D", "set_frame", SET_FRAME_HASH)

        private const val GET_FRAME_HASH = 3905245786L
        @JvmField
        val getFrameBind =
            ObjectCalls.getMethodBind("Sprite3D", "get_frame", GET_FRAME_HASH)

        private const val SET_FRAME_COORDS_HASH = 1130785943L
        @JvmField
        val setFrameCoordsBind =
            ObjectCalls.getMethodBind("Sprite3D", "set_frame_coords", SET_FRAME_COORDS_HASH)

        private const val GET_FRAME_COORDS_HASH = 3690982128L
        @JvmField
        val getFrameCoordsBind =
            ObjectCalls.getMethodBind("Sprite3D", "get_frame_coords", GET_FRAME_COORDS_HASH)

        private const val SET_VFRAMES_HASH = 1286410249L
        @JvmField
        val setVframesBind =
            ObjectCalls.getMethodBind("Sprite3D", "set_vframes", SET_VFRAMES_HASH)

        private const val GET_VFRAMES_HASH = 3905245786L
        @JvmField
        val getVframesBind =
            ObjectCalls.getMethodBind("Sprite3D", "get_vframes", GET_VFRAMES_HASH)

        private const val SET_HFRAMES_HASH = 1286410249L
        @JvmField
        val setHframesBind =
            ObjectCalls.getMethodBind("Sprite3D", "set_hframes", SET_HFRAMES_HASH)

        private const val GET_HFRAMES_HASH = 3905245786L
        @JvmField
        val getHframesBind =
            ObjectCalls.getMethodBind("Sprite3D", "get_hframes", GET_HFRAMES_HASH)
    }
}
