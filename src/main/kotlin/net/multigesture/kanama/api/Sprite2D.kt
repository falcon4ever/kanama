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

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setCentered(centered: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCenteredBind, handle, centered)
    }

    fun isCentered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCenteredBind, handle)
    }

    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    fun setFlipH(flipH: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, handle, flipH)
    }

    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedHBind, handle)
    }

    fun setFlipV(flipV: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, handle, flipV)
    }

    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedVBind, handle)
    }

    fun setRegionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRegionEnabledBind, handle, enabled)
    }

    fun isRegionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRegionEnabledBind, handle)
    }

    fun isPixelOpaque(pos: Vector2): Boolean {
        return ObjectCalls.ptrcallWithVector2ArgRetBool(isPixelOpaqueBind, handle, pos)
    }

    fun setRegionRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRegionRectBind, handle, rect)
    }

    fun getRegionRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionRectBind, handle)
    }

    fun setRegionFilterClipEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRegionFilterClipEnabledBind, handle, enabled)
    }

    fun isRegionFilterClipEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRegionFilterClipEnabledBind, handle)
    }

    fun setFrame(frame: Int) {
        ObjectCalls.ptrcallWithIntArg(setFrameBind, handle, frame)
    }

    fun getFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameBind, handle)
    }

    fun setFrameCoords(coords: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setFrameCoordsBind, handle, coords)
    }

    fun getFrameCoords(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getFrameCoordsBind, handle)
    }

    fun setVframes(vframes: Int) {
        ObjectCalls.ptrcallWithIntArg(setVframesBind, handle, vframes)
    }

    fun getVframes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVframesBind, handle)
    }

    fun setHframes(hframes: Int) {
        ObjectCalls.ptrcallWithIntArg(setHframesBind, handle, hframes)
    }

    fun getHframes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHframesBind, handle)
    }

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
