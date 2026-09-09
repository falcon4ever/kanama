package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * An anchor point in AR space.
 *
 * Generated from Godot docs: XRAnchor3D
 */
class XRAnchor3D(handle: MemorySegment) : XRNode3D(handle) {
    /**
     * Returns the estimated size of the plane that was detected. Say when the anchor relates to a
     * table in the real world, this is the estimated size of the surface of that table.
     *
     * Generated from Godot docs: XRAnchor3D.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
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
    }
}
