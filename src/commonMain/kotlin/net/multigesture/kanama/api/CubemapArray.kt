package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * An array of `Cubemap`s, stored together and with a single reference.
 *
 * Generated from Godot docs: CubemapArray
 */
class CubemapArray(handle: MemorySegment) : ImageTextureLayered(handle) {
    /**
     * Creates a placeholder version of this resource (`PlaceholderCubemapArray`).
     *
     * Generated from Godot docs: CubemapArray.create_placeholder
     */
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
