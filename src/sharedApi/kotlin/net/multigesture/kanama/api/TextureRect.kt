package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A control that displays a texture.
 *
 * Generated from Godot docs: TextureRect
 */
class TextureRect(handle: GodotHandle) : Control(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var expandMode: Long
        @JvmName("expandModeProperty")
        get() = getExpandMode()
        @JvmName("setExpandModeProperty")
        set(value) = setExpandMode(value)

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

    /**
     * The node's `Texture2D` resource.
     *
     * Generated from Godot docs: TextureRect.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The node's `Texture2D` resource.
     *
     * Generated from Godot docs: TextureRect.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    /**
     * Defines how minimum size is determined based on the texture's size.
     *
     * Generated from Godot docs: TextureRect.set_expand_mode
     */
    fun setExpandMode(expandMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setExpandModeBind, segment, expandMode)
    }

    /**
     * Defines how minimum size is determined based on the texture's size.
     *
     * Generated from Godot docs: TextureRect.get_expand_mode
     */
    fun getExpandMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getExpandModeBind, segment)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: TextureRect.set_flip_h
     */
    fun setFlipH(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, segment, enable)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: TextureRect.is_flipped_h
     */
    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedHBind, segment)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: TextureRect.set_flip_v
     */
    fun setFlipV(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, segment, enable)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: TextureRect.is_flipped_v
     */
    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedVBind, segment)
    }

    /**
     * Controls the texture's behavior when resizing the node's bounding rectangle.
     *
     * Generated from Godot docs: TextureRect.set_stretch_mode
     */
    fun setStretchMode(stretchMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setStretchModeBind, segment, stretchMode)
    }

    /**
     * Controls the texture's behavior when resizing the node's bounding rectangle.
     *
     * Generated from Godot docs: TextureRect.get_stretch_mode
     */
    fun getStretchMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStretchModeBind, segment)
    }

    companion object {
        const val EXPAND_KEEP_SIZE: Long = 0L
        const val EXPAND_IGNORE_SIZE: Long = 1L
        const val EXPAND_FIT_WIDTH: Long = 2L
        const val EXPAND_FIT_WIDTH_PROPORTIONAL: Long = 3L
        const val EXPAND_FIT_HEIGHT: Long = 4L
        const val EXPAND_FIT_HEIGHT_PROPORTIONAL: Long = 5L
        const val STRETCH_SCALE: Long = 0L
        const val STRETCH_TILE: Long = 1L
        const val STRETCH_KEEP: Long = 2L
        const val STRETCH_KEEP_CENTERED: Long = 3L
        const val STRETCH_KEEP_ASPECT: Long = 4L
        const val STRETCH_KEEP_ASPECT_CENTERED: Long = 5L
        const val STRETCH_KEEP_ASPECT_COVERED: Long = 6L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureRect? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextureRect? =
            if (handle.address() == 0L) null else TextureRect(GodotHandle(handle))

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_EXPAND_MODE_HASH = 1870766882L
        private val setExpandModeBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_expand_mode", SET_EXPAND_MODE_HASH)
        }

        private const val GET_EXPAND_MODE_HASH = 3863824733L
        private val getExpandModeBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "get_expand_mode", GET_EXPAND_MODE_HASH)
        }

        private const val SET_FLIP_H_HASH = 2586408642L
        private val setFlipHBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_flip_h", SET_FLIP_H_HASH)
        }

        private const val IS_FLIPPED_H_HASH = 36873697L
        private val isFlippedHBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "is_flipped_h", IS_FLIPPED_H_HASH)
        }

        private const val SET_FLIP_V_HASH = 2586408642L
        private val setFlipVBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_flip_v", SET_FLIP_V_HASH)
        }

        private const val IS_FLIPPED_V_HASH = 36873697L
        private val isFlippedVBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "is_flipped_v", IS_FLIPPED_V_HASH)
        }

        private const val SET_STRETCH_MODE_HASH = 58788729L
        private val setStretchModeBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_stretch_mode", SET_STRETCH_MODE_HASH)
        }

        private const val GET_STRETCH_MODE_HASH = 346396079L
        private val getStretchModeBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "get_stretch_mode", GET_STRETCH_MODE_HASH)
        }
    }
}
