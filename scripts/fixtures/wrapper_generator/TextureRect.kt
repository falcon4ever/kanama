package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: TextureRect
 */
class TextureRect(handle: GodotHandle) : Control(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var expandMode: TextureRect.ExpandMode
        @JvmName("expandModeProperty")
        get() = getExpandMode()
        @JvmName("setExpandModeProperty")
        set(value) = setExpandMode(value)

    var stretchMode: TextureRect.StretchMode
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

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    fun setExpandMode(expandMode: TextureRect.ExpandMode) {
        ObjectCalls.ptrcallWithLongArg(setExpandModeBind, segment, expandMode.value)
    }

    fun getExpandMode(): TextureRect.ExpandMode {
        return TextureRect.ExpandMode(ObjectCalls.ptrcallNoArgsRetLong(getExpandModeBind, segment))
    }

    fun setFlipH(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, segment, enable)
    }

    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedHBind, segment)
    }

    fun setFlipV(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, segment, enable)
    }

    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedVBind, segment)
    }

    fun setStretchMode(stretchMode: TextureRect.StretchMode) {
        ObjectCalls.ptrcallWithLongArg(setStretchModeBind, segment, stretchMode.value)
    }

    fun getStretchMode(): TextureRect.StretchMode {
        return TextureRect.StretchMode(ObjectCalls.ptrcallNoArgsRetLong(getStretchModeBind, segment))
    }

    @JvmInline
    value class ExpandMode(override val value: Long) : GodotEnumValue {
        companion object {
            val KEEP_SIZE: ExpandMode get() = ExpandMode(0L)
            val IGNORE_SIZE: ExpandMode get() = ExpandMode(1L)
            val FIT_WIDTH: ExpandMode get() = ExpandMode(2L)
            val FIT_WIDTH_PROPORTIONAL: ExpandMode get() = ExpandMode(3L)
            val FIT_HEIGHT: ExpandMode get() = ExpandMode(4L)
            val FIT_HEIGHT_PROPORTIONAL: ExpandMode get() = ExpandMode(5L)
        }
    }

    @JvmInline
    value class StretchMode(override val value: Long) : GodotEnumValue {
        companion object {
            val SCALE: StretchMode get() = StretchMode(0L)
            val TILE: StretchMode get() = StretchMode(1L)
            val KEEP: StretchMode get() = StretchMode(2L)
            val KEEP_CENTERED: StretchMode get() = StretchMode(3L)
            val KEEP_ASPECT: StretchMode get() = StretchMode(4L)
            val KEEP_ASPECT_CENTERED: StretchMode get() = StretchMode(5L)
            val KEEP_ASPECT_COVERED: StretchMode get() = StretchMode(6L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureRect? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): TextureRect? =
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
