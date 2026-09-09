package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: PlaceholderTexture2D
 */
class PlaceholderTexture2D(handle: MemorySegment) : Texture2D(handle) {
    fun setSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, handle, size)
    }

    companion object {
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
