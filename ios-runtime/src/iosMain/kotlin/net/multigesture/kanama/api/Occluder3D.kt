package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Occluder3D
 */
open class Occluder3D(handle: MemorySegment) : Resource(handle) {
    fun getIndices(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getIndicesBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Occluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Occluder3D? =
            if (handle.address() == 0L) null else Occluder3D(handle)

        private const val GET_INDICES_HASH = 1930428628L
        private val getIndicesBind by lazy {
            ObjectCalls.getMethodBind("Occluder3D", "get_indices", GET_INDICES_HASH)
        }
    }
}
