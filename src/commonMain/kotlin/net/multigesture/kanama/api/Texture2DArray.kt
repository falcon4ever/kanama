package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A single texture resource which consists of multiple, separate images. Each image has the same
 * dimensions and number of mipmap levels.
 *
 * Generated from Godot docs: Texture2DArray
 */
class Texture2DArray(handle: GodotHandle) : ImageTextureLayered(handle) {
    /**
     * Creates a placeholder version of this resource (`PlaceholderTexture2DArray`).
     *
     * Generated from Godot docs: Texture2DArray.create_placeholder
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
        fun fromHandle(handle: GodotHandle): Texture2DArray? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Texture2DArray? =
            if (handle.address() == 0L) null else RefCounted.owned(Texture2DArray(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Texture2DArray? =
            if (handle.address() == 0L) null else Texture2DArray(GodotHandle(handle))
    }

    private object Binds {
        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        @JvmField
        val createPlaceholderBind =
            ObjectCalls.getMethodBind("Texture2DArray", "create_placeholder", CREATE_PLACEHOLDER_HASH)
    }
}
