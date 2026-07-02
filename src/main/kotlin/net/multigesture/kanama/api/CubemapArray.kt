package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An array of `Cubemap`s, stored together and with a single reference.
 *
 * Generated from Godot docs: CubemapArray
 */
class CubemapArray(handle: MemorySegment) : ImageTextureLayered(handle) {
    fun createPlaceholder(): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CubemapArray? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CubemapArray? =
            if (handle.address() == 0L) null else CubemapArray(handle)

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("CubemapArray", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }
    }
}
