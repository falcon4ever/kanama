package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector3

/**
 * An anchor point in AR space.
 *
 * Generated from Godot docs: XRAnchor3D
 */
class XRAnchor3D(handle: GodotHandle) : XRNode3D(handle) {
    /**
     * Returns the estimated size of the plane that was detected. Say when the anchor relates to a
     * table in the real world, this is the estimated size of the surface of that table.
     *
     * Generated from Godot docs: XRAnchor3D.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, segment)
    }

    /**
     * Returns a plane aligned with our anchor; handy for intersection testing.
     *
     * Generated from Godot docs: XRAnchor3D.get_plane
     */
    fun getPlane(): Plane {
        return ObjectCalls.ptrcallNoArgsRetPlane(getPlaneBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRAnchor3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRAnchor3D? =
            if (handle.address() == 0L) null else XRAnchor3D(GodotHandle(handle))

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
