package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * Provides direct access to a physics space in the `PhysicsServer3D`.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState3D
 */
open class PhysicsDirectSpaceState3D(handle: MemorySegment) : GodotObject(handle) {
    fun intersectPoint(parameters: PhysicsPointQueryParameters3D?, maxResults: Int = 32): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(intersectPointBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL, maxResults)
    }

    fun intersectRay(parameters: PhysicsRayQueryParameters3D?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithObjectArgRetDictionary(intersectRayBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun intersectShape(parameters: PhysicsShapeQueryParameters3D?, maxResults: Int = 32): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(intersectShapeBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL, maxResults)
    }

    fun castMotion(parameters: PhysicsShapeQueryParameters3D?): List<Float> {
        return ObjectCalls.ptrcallWithObjectArgRetPackedFloat32List(castMotionBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun collideShape(parameters: PhysicsShapeQueryParameters3D?, maxResults: Int = 32): List<Vector3> {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetVector3List(collideShapeBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL, maxResults)
    }

    fun getRestInfo(parameters: PhysicsShapeQueryParameters3D?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithObjectArgRetDictionary(getRestInfoBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsDirectSpaceState3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsDirectSpaceState3D? =
            if (handle.address() == 0L) null else PhysicsDirectSpaceState3D(handle)

        private const val INTERSECT_POINT_HASH = 975173756L
        private val intersectPointBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_point", INTERSECT_POINT_HASH)
        }

        private const val INTERSECT_RAY_HASH = 3957970750L
        private val intersectRayBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_ray", INTERSECT_RAY_HASH)
        }

        private const val INTERSECT_SHAPE_HASH = 3762137681L
        private val intersectShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_shape", INTERSECT_SHAPE_HASH)
        }

        private const val CAST_MOTION_HASH = 1778757334L
        private val castMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "cast_motion", CAST_MOTION_HASH)
        }

        private const val COLLIDE_SHAPE_HASH = 3762137681L
        private val collideShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "collide_shape", COLLIDE_SHAPE_HASH)
        }

        private const val GET_REST_INFO_HASH = 1376751592L
        private val getRestInfoBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "get_rest_info", GET_REST_INFO_HASH)
        }
    }
}
