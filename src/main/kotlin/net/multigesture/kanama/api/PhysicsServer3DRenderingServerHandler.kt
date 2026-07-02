package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3

/**
 * A class used to provide `PhysicsServer3DExtension._soft_body_update_rendering_server` with a
 * rendering handler for soft bodies.
 *
 * Generated from Godot docs: PhysicsServer3DRenderingServerHandler
 */
class PhysicsServer3DRenderingServerHandler(handle: MemorySegment) : GodotObject(handle) {
    fun setVertex(vertexId: Int, vertex: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setVertexBind, handle, vertexId, vertex)
    }

    fun setNormal(vertexId: Int, normal: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setNormalBind, handle, vertexId, normal)
    }

    fun setAabb(aabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setAabbBind, handle, aabb)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsServer3DRenderingServerHandler? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsServer3DRenderingServerHandler? =
            if (handle.address() == 0L) null else PhysicsServer3DRenderingServerHandler(handle)

        private const val SET_VERTEX_HASH = 1530502735L
        private val setVertexBind by lazy {
            ObjectCalls.getMethodBind("PhysicsServer3DRenderingServerHandler", "set_vertex", SET_VERTEX_HASH)
        }

        private const val SET_NORMAL_HASH = 1530502735L
        private val setNormalBind by lazy {
            ObjectCalls.getMethodBind("PhysicsServer3DRenderingServerHandler", "set_normal", SET_NORMAL_HASH)
        }

        private const val SET_AABB_HASH = 259215842L
        private val setAabbBind by lazy {
            ObjectCalls.getMethodBind("PhysicsServer3DRenderingServerHandler", "set_aabb", SET_AABB_HASH)
        }
    }
}
