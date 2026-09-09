package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: PackedDataContainerRef
 */
class PackedDataContainerRef(handle: MemorySegment) : RefCounted(handle) {
    fun size(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(sizeBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): PackedDataContainerRef? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PackedDataContainerRef? =
            if (handle.address() == 0L) null else PackedDataContainerRef(handle)

        private const val SIZE_HASH = 3905245786L
        private val sizeBind by lazy {
            ObjectCalls.getMethodBind("PackedDataContainerRef", "size", SIZE_HASH)
        }
    }
}
