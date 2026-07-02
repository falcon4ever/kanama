package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Provides direct access to a physics space in the `PhysicsServer2D`.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D
 */
open class PhysicsDirectSpaceState2D(handle: MemorySegment) : GodotObject(handle) {
    fun intersectPoint(parameters: PhysicsPointQueryParameters2D?, maxResults: Int = 32): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(intersectPointBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL, maxResults)
    }

    fun intersectRay(parameters: PhysicsRayQueryParameters2D?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithObjectArgRetDictionary(intersectRayBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun intersectShape(parameters: PhysicsShapeQueryParameters2D?, maxResults: Int = 32): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(intersectShapeBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL, maxResults)
    }

    fun castMotion(parameters: PhysicsShapeQueryParameters2D?): List<Float> {
        return ObjectCalls.ptrcallWithObjectArgRetPackedFloat32List(castMotionBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun collideShape(parameters: PhysicsShapeQueryParameters2D?, maxResults: Int = 32): List<Vector2> {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetVector2List(collideShapeBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL, maxResults)
    }

    fun getRestInfo(parameters: PhysicsShapeQueryParameters2D?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithObjectArgRetDictionary(getRestInfoBind, handle, parameters?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsDirectSpaceState2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsDirectSpaceState2D? =
            if (handle.address() == 0L) null else PhysicsDirectSpaceState2D(handle)

        private const val INTERSECT_POINT_HASH = 2118456068L
        private val intersectPointBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_point", INTERSECT_POINT_HASH)
        }

        private const val INTERSECT_RAY_HASH = 1590275562L
        private val intersectRayBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_ray", INTERSECT_RAY_HASH)
        }

        private const val INTERSECT_SHAPE_HASH = 2488867228L
        private val intersectShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_shape", INTERSECT_SHAPE_HASH)
        }

        private const val CAST_MOTION_HASH = 711275086L
        private val castMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "cast_motion", CAST_MOTION_HASH)
        }

        private const val COLLIDE_SHAPE_HASH = 2488867228L
        private val collideShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "collide_shape", COLLIDE_SHAPE_HASH)
        }

        private const val GET_REST_INFO_HASH = 2803666496L
        private val getRestInfoBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "get_rest_info", GET_REST_INFO_HASH)
        }
    }
}
