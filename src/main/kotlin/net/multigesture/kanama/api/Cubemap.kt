package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Six square textures representing the faces of a cube. Commonly used as a skybox.
 *
 * Generated from Godot docs: Cubemap
 */
class Cubemap(handle: MemorySegment) : ImageTextureLayered(handle) {
    fun createPlaceholder(): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Cubemap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Cubemap? =
            if (handle.address() == 0L) null else Cubemap(handle)

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Cubemap", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }
    }
}
