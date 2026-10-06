package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3

/**
 * A class used to provide `PhysicsServer3DExtension._soft_body_update_rendering_server` with a
 * rendering handler for soft bodies.
 *
 * Generated from Godot docs: PhysicsServer3DRenderingServerHandler
 */
class PhysicsServer3DRenderingServerHandler(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Sets the position for the `SoftBody3D` vertex at the index specified by `vertex_id`.
     *
     * Generated from Godot docs: PhysicsServer3DRenderingServerHandler.set_vertex
     */
    fun setVertex(vertexId: Int, vertex: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.setVertexBind, segment, vertexId, vertex)
    }

    /**
     * Sets the normal for the `SoftBody3D` vertex at the index specified by `vertex_id`.
     *
     * Generated from Godot docs: PhysicsServer3DRenderingServerHandler.set_normal
     */
    fun setNormal(vertexId: Int, normal: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.setNormalBind, segment, vertexId, normal)
    }

    /**
     * Sets the bounding box for the `SoftBody3D`.
     *
     * Generated from Godot docs: PhysicsServer3DRenderingServerHandler.set_aabb
     */
    fun setAabb(aabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(Binds.setAabbBind, segment, aabb)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsServer3DRenderingServerHandler? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsServer3DRenderingServerHandler? =
            if (handle.address() == 0L) null else PhysicsServer3DRenderingServerHandler(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_VERTEX_HASH = 1530502735L
        @JvmField
        val setVertexBind =
            ObjectCalls.getMethodBind("PhysicsServer3DRenderingServerHandler", "set_vertex", SET_VERTEX_HASH)

        private const val SET_NORMAL_HASH = 1530502735L
        @JvmField
        val setNormalBind =
            ObjectCalls.getMethodBind("PhysicsServer3DRenderingServerHandler", "set_normal", SET_NORMAL_HASH)

        private const val SET_AABB_HASH = 259215842L
        @JvmField
        val setAabbBind =
            ObjectCalls.getMethodBind("PhysicsServer3DRenderingServerHandler", "set_aabb", SET_AABB_HASH)
    }
}
