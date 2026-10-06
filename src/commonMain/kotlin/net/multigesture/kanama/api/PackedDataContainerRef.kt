package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An internal class used by `PackedDataContainer` to pack nested arrays and dictionaries.
 *
 * Generated from Godot docs: PackedDataContainerRef
 */
class PackedDataContainerRef(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the size of the packed container (see `Array.size` and `Dictionary.size`).
     *
     * Generated from Godot docs: PackedDataContainerRef.size
     */
    fun size(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.sizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PackedDataContainerRef? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PackedDataContainerRef? =
            if (handle.address() == 0L) null else RefCounted.owned(PackedDataContainerRef(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PackedDataContainerRef? =
            if (handle.address() == 0L) null else PackedDataContainerRef(GodotHandle(handle))
    }

    private object Binds {
        private const val SIZE_HASH = 3905245786L
        @JvmField
        val sizeBind =
            ObjectCalls.getMethodBind("PackedDataContainerRef", "size", SIZE_HASH)
    }
}
