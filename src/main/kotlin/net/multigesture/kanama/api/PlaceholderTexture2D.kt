package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Placeholder class for a 2-dimensional texture.
 *
 * Generated from Godot docs: PlaceholderTexture2D
 */
class PlaceholderTexture2D(handle: MemorySegment) : Texture2D(handle) {
    /**
     * The texture's size (in pixels).
     *
     * Generated from Godot docs: PlaceholderTexture2D.set_size
     */
    fun setSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, handle, size)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PlaceholderTexture2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PlaceholderTexture2D? =
            if (handle.address() == 0L) null else PlaceholderTexture2D(handle)

        private const val SET_SIZE_HASH = 743155724L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("PlaceholderTexture2D", "set_size", SET_SIZE_HASH)
        }
    }
}
