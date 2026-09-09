package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Occluder shape resource for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: Occluder3D
 */
open class Occluder3D(handle: MemorySegment) : Resource(handle) {
    /**
     * Returns the occluder shape's vertex indices.
     *
     * Generated from Godot docs: Occluder3D.get_indices
     */
    fun getIndices(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getIndicesBind, handle)
    }

    companion object {
        @JvmStatic
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
