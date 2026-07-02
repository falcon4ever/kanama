package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Texture-based button. Supports Pressed, Hover, Disabled and Focused states.
 *
 * Generated from Godot docs: TextureButton
 */
class TextureButton(handle: MemorySegment) : BaseButton(handle) {
    var textureNormal: Texture2D?
        @JvmName("textureNormalProperty")
        get() = getTextureNormal()
        @JvmName("setTextureNormalProperty")
        set(value) = setTextureNormal(value)

    var texturePressed: Texture2D?
        @JvmName("texturePressedProperty")
        get() = getTexturePressed()
        @JvmName("setTexturePressedProperty")
        set(value) = setTexturePressed(value)

    var textureHover: Texture2D?
        @JvmName("textureHoverProperty")
        get() = getTextureHover()
        @JvmName("setTextureHoverProperty")
        set(value) = setTextureHover(value)

    var textureDisabled: Texture2D?
        @JvmName("textureDisabledProperty")
        get() = getTextureDisabled()
        @JvmName("setTextureDisabledProperty")
        set(value) = setTextureDisabled(value)

    var textureFocused: Texture2D?
        @JvmName("textureFocusedProperty")
        get() = getTextureFocused()
        @JvmName("setTextureFocusedProperty")
        set(value) = setTextureFocused(value)

    var textureClickMask: BitMap?
        @JvmName("textureClickMaskProperty")
        get() = getClickMask()
        @JvmName("setTextureClickMaskProperty")
        set(value) = setClickMask(value)

    var ignoreTextureSize: Boolean
        @JvmName("ignoreTextureSizeProperty")
        get() = getIgnoreTextureSize()
        @JvmName("setIgnoreTextureSizeProperty")
        set(value) = setIgnoreTextureSize(value)

    var stretchMode: Long
        @JvmName("stretchModeProperty")
        get() = getStretchMode()
        @JvmName("setStretchModeProperty")
        set(value) = setStretchMode(value)

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

    fun setTextureNormal(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureNormalBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setTexturePressed(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTexturePressedBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setTextureHover(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureHoverBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setTextureDisabled(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureDisabledBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setTextureFocused(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureFocusedBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setClickMask(mask: BitMap?) {
        ObjectCalls.ptrcallWithObjectArgs(setClickMaskBind, handle, listOf(mask?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setIgnoreTextureSize(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreTextureSizeBind, handle, ignore)
    }

    fun setStretchMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setStretchModeBind, handle, mode)
    }

    fun setFlipH(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, handle, enable)
    }

    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedHBind, handle)
    }

    fun setFlipV(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, handle, enable)
    }

    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedVBind, handle)
    }

    fun getTextureNormal(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureNormalBind, handle))
    }

    fun getTexturePressed(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTexturePressedBind, handle))
    }

    fun getTextureHover(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureHoverBind, handle))
    }

    fun getTextureDisabled(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureDisabledBind, handle))
    }

    fun getTextureFocused(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureFocusedBind, handle))
    }

    fun getClickMask(): BitMap? {
        return BitMap.wrap(ObjectCalls.ptrcallNoArgsRetObject(getClickMaskBind, handle))
    }

    fun getIgnoreTextureSize(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getIgnoreTextureSizeBind, handle)
    }

    fun getStretchMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStretchModeBind, handle)
    }

    companion object {
        const val STRETCH_SCALE: Long = 0L
        const val STRETCH_TILE: Long = 1L
        const val STRETCH_KEEP: Long = 2L
        const val STRETCH_KEEP_CENTERED: Long = 3L
        const val STRETCH_KEEP_ASPECT: Long = 4L
        const val STRETCH_KEEP_ASPECT_CENTERED: Long = 5L
        const val STRETCH_KEEP_ASPECT_COVERED: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextureButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextureButton? =
            if (handle.address() == 0L) null else TextureButton(handle)

        private const val SET_TEXTURE_NORMAL_HASH = 4051416890L
        private val setTextureNormalBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_texture_normal", SET_TEXTURE_NORMAL_HASH)
        }

        private const val SET_TEXTURE_PRESSED_HASH = 4051416890L
        private val setTexturePressedBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_texture_pressed", SET_TEXTURE_PRESSED_HASH)
        }

        private const val SET_TEXTURE_HOVER_HASH = 4051416890L
        private val setTextureHoverBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_texture_hover", SET_TEXTURE_HOVER_HASH)
        }

        private const val SET_TEXTURE_DISABLED_HASH = 4051416890L
        private val setTextureDisabledBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_texture_disabled", SET_TEXTURE_DISABLED_HASH)
        }

        private const val SET_TEXTURE_FOCUSED_HASH = 4051416890L
        private val setTextureFocusedBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_texture_focused", SET_TEXTURE_FOCUSED_HASH)
        }

        private const val SET_CLICK_MASK_HASH = 698588216L
        private val setClickMaskBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_click_mask", SET_CLICK_MASK_HASH)
        }

        private const val SET_IGNORE_TEXTURE_SIZE_HASH = 2586408642L
        private val setIgnoreTextureSizeBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_ignore_texture_size", SET_IGNORE_TEXTURE_SIZE_HASH)
        }

        private const val SET_STRETCH_MODE_HASH = 252530840L
        private val setStretchModeBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_stretch_mode", SET_STRETCH_MODE_HASH)
        }

        private const val SET_FLIP_H_HASH = 2586408642L
        private val setFlipHBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_flip_h", SET_FLIP_H_HASH)
        }

        private const val IS_FLIPPED_H_HASH = 36873697L
        private val isFlippedHBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "is_flipped_h", IS_FLIPPED_H_HASH)
        }

        private const val SET_FLIP_V_HASH = 2586408642L
        private val setFlipVBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "set_flip_v", SET_FLIP_V_HASH)
        }

        private const val IS_FLIPPED_V_HASH = 36873697L
        private val isFlippedVBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "is_flipped_v", IS_FLIPPED_V_HASH)
        }

        private const val GET_TEXTURE_NORMAL_HASH = 3635182373L
        private val getTextureNormalBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "get_texture_normal", GET_TEXTURE_NORMAL_HASH)
        }

        private const val GET_TEXTURE_PRESSED_HASH = 3635182373L
        private val getTexturePressedBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "get_texture_pressed", GET_TEXTURE_PRESSED_HASH)
        }

        private const val GET_TEXTURE_HOVER_HASH = 3635182373L
        private val getTextureHoverBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "get_texture_hover", GET_TEXTURE_HOVER_HASH)
        }

        private const val GET_TEXTURE_DISABLED_HASH = 3635182373L
        private val getTextureDisabledBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "get_texture_disabled", GET_TEXTURE_DISABLED_HASH)
        }

        private const val GET_TEXTURE_FOCUSED_HASH = 3635182373L
        private val getTextureFocusedBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "get_texture_focused", GET_TEXTURE_FOCUSED_HASH)
        }

        private const val GET_CLICK_MASK_HASH = 2459671998L
        private val getClickMaskBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "get_click_mask", GET_CLICK_MASK_HASH)
        }

        private const val GET_IGNORE_TEXTURE_SIZE_HASH = 36873697L
        private val getIgnoreTextureSizeBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "get_ignore_texture_size", GET_IGNORE_TEXTURE_SIZE_HASH)
        }

        private const val GET_STRETCH_MODE_HASH = 33815122L
        private val getStretchModeBind by lazy {
            ObjectCalls.getMethodBind("TextureButton", "get_stretch_mode", GET_STRETCH_MODE_HASH)
        }
    }
}
