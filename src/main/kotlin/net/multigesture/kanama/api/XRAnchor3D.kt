package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector3

/**
 * An anchor point in AR space.
 *
 * Generated from Godot docs: XRAnchor3D
 */
class XRAnchor3D(handle: MemorySegment) : XRNode3D(handle) {
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    fun getPlane(): Plane {
        return ObjectCalls.ptrcallNoArgsRetPlane(getPlaneBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRAnchor3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRAnchor3D? =
            if (handle.address() == 0L) null else XRAnchor3D(handle)

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("XRAnchor3D", "get_size", GET_SIZE_HASH)
        }

        private const val GET_PLANE_HASH = 2753500971L
        private val getPlaneBind by lazy {
            ObjectCalls.getMethodBind("XRAnchor3D", "get_plane", GET_PLANE_HASH)
        }
    }
}
