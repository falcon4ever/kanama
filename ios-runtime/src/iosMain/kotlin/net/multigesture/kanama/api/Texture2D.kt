package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
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

    fun createPlaceholder(): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle))
    }

    companion object {
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

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Texture2D", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }
    }
}
