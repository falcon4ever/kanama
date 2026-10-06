package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.createPlaceholderBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrapOwned(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CubemapArray? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CubemapArray? =
            if (handle.address() == 0L) null else RefCounted.owned(CubemapArray(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CubemapArray? =
            if (handle.address() == 0L) null else CubemapArray(GodotHandle(handle))
    }

    private object Binds {
        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        @JvmField
        val createPlaceholderBind =
            ObjectCalls.getMethodBind("CubemapArray", "create_placeholder", CREATE_PLACEHOLDER_HASH)
    }
}
