package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Efficiently packs and serializes `Array` or `Dictionary`.
 *
 * Generated from Godot docs: PackedDataContainer
 */
class PackedDataContainer(handle: GodotHandle) : Resource(handle) {
    /**
     * Packs the given container into a binary representation. The `value` must be either `Array` or
     * `Dictionary`, any other type will result in invalid data error. Note: Subsequent calls to this
     * method will overwrite the existing data.
     *
     * Generated from Godot docs: PackedDataContainer.pack
     */
    fun pack(value: Any?): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithVariantArgRetLong(packBind, segment, value)
    }

    /**
     * Returns the size of the packed container (see `Array.size` and `Dictionary.size`).
     *
     * Generated from Godot docs: PackedDataContainer.size
     */
    fun size(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(sizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PackedDataContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PackedDataContainer? =
            if (handle.address() == 0L) null else PackedDataContainer(GodotHandle(handle))

        private const val PACK_HASH = 966674026L
        private val packBind by lazy {
            ObjectCalls.getMethodBind("PackedDataContainer", "pack", PACK_HASH)
        }

        private const val SIZE_HASH = 3905245786L
        private val sizeBind by lazy {
            ObjectCalls.getMethodBind("PackedDataContainer", "size", SIZE_HASH)
        }
    }
}
