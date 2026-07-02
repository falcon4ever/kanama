package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB

/**
 * Placeholder class for a mesh.
 *
 * Generated from Godot docs: PlaceholderMesh
 */
class PlaceholderMesh(handle: MemorySegment) : Mesh(handle) {
    fun setAabb(aabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setAabbBind, handle, aabb)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PlaceholderMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PlaceholderMesh? =
            if (handle.address() == 0L) null else PlaceholderMesh(handle)

        private const val SET_AABB_HASH = 259215842L
        private val setAabbBind by lazy {
            ObjectCalls.getMethodBind("PlaceholderMesh", "set_aabb", SET_AABB_HASH)
        }
    }
}
