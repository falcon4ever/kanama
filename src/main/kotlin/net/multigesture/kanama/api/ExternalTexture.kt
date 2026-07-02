package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Texture which displays the content of an external buffer.
 *
 * Generated from Godot docs: ExternalTexture
 */
class ExternalTexture(handle: MemorySegment) : Texture2D(handle) {
    fun setSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, handle, size)
    }

    fun getExternalTextureId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getExternalTextureIdBind, handle)
    }

    fun setExternalBufferId(externalBufferId: Long) {
        ObjectCalls.ptrcallWithLongArg(setExternalBufferIdBind, handle, externalBufferId)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ExternalTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ExternalTexture? =
            if (handle.address() == 0L) null else ExternalTexture(handle)

        private const val SET_SIZE_HASH = 743155724L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("ExternalTexture", "set_size", SET_SIZE_HASH)
        }

        private const val GET_EXTERNAL_TEXTURE_ID_HASH = 3905245786L
        private val getExternalTextureIdBind by lazy {
            ObjectCalls.getMethodBind("ExternalTexture", "get_external_texture_id", GET_EXTERNAL_TEXTURE_ID_HASH)
        }

        private const val SET_EXTERNAL_BUFFER_ID_HASH = 1286410249L
        private val setExternalBufferIdBind by lazy {
            ObjectCalls.getMethodBind("ExternalTexture", "set_external_buffer_id", SET_EXTERNAL_BUFFER_ID_HASH)
        }
    }
}
