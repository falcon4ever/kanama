package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Cubemap
 */
class Cubemap(handle: MemorySegment) : ImageTextureLayered(handle) {
    fun createPlaceholder(): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrap(ret)
    }

    companion object {
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
