package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: PackedDataContainer
 */
class PackedDataContainer(handle: MemorySegment) : Resource(handle) {
    fun size(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(sizeBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): PackedDataContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PackedDataContainer? =
            if (handle.address() == 0L) null else PackedDataContainer(handle)

        private const val SIZE_HASH = 3905245786L
        private val sizeBind by lazy {
            ObjectCalls.getMethodBind("PackedDataContainer", "size", SIZE_HASH)
        }
    }
}
