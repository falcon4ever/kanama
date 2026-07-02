package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An internal class used by `PackedDataContainer` to pack nested arrays and dictionaries.
 *
 * Generated from Godot docs: PackedDataContainerRef
 */
class PackedDataContainerRef(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the size of the packed container (see `Array.size` and `Dictionary.size`).
     *
     * Generated from Godot docs: PackedDataContainerRef.size
     */
    fun size(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(sizeBind, handle)
    }

    companion object {
        @JvmStatic
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
