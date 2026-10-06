package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Six square textures representing the faces of a cube. Commonly used as a skybox.
 *
 * Generated from Godot docs: Cubemap
 */
class Cubemap(handle: GodotHandle) : ImageTextureLayered(handle) {
    /**
     * Creates a placeholder version of this resource (`PlaceholderCubemap`).
     *
     * Generated from Godot docs: Cubemap.create_placeholder
     */
    fun createPlaceholder(): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.createPlaceholderBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrapOwned(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Cubemap? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Cubemap? =
            if (handle.address() == 0L) null else RefCounted.owned(Cubemap(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Cubemap? =
            if (handle.address() == 0L) null else Cubemap(GodotHandle(handle))
    }

    private object Binds {
        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        @JvmField
        val createPlaceholderBind =
            ObjectCalls.getMethodBind("Cubemap", "create_placeholder", CREATE_PLACEHOLDER_HASH)
    }
}
