package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * An array of `Cubemap`s, stored together and with a single reference.
 *
 * Generated from Godot docs: CubemapArray
 */
class CubemapArray(handle: GodotHandle) : ImageTextureLayered(handle) {
    /**
     * Creates a placeholder version of this resource (`PlaceholderCubemapArray`).
     *
     * Generated from Godot docs: CubemapArray.create_placeholder
     */
    fun createPlaceholder(): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrap(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CubemapArray? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CubemapArray? =
            if (handle.address() == 0L) null else CubemapArray(GodotHandle(handle))

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("CubemapArray", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }
    }
}
