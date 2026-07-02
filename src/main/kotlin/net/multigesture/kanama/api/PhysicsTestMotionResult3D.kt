package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * Describes the motion and collision result from `PhysicsServer3D.body_test_motion`.
 *
 * Generated from Godot docs: PhysicsTestMotionResult3D
 */
class PhysicsTestMotionResult3D(handle: MemorySegment) : RefCounted(handle) {
    fun getTravel(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTravelBind, handle)
    }

    fun getRemainder(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRemainderBind, handle)
    }

    fun getCollisionSafeFraction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionSafeFractionBind, handle)
    }

    fun getCollisionUnsafeFraction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionUnsafeFractionBind, handle)
    }

    fun getCollisionCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionCountBind, handle)
    }

    fun getCollisionPoint(collisionIndex: Int = 0): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getCollisionPointBind, handle, collisionIndex)
    }

    fun getCollisionNormal(collisionIndex: Int = 0): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getCollisionNormalBind, handle, collisionIndex)
    }

    fun getColliderVelocity(collisionIndex: Int = 0): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getColliderVelocityBind, handle, collisionIndex)
    }

    fun getColliderId(collisionIndex: Int = 0): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getColliderIdBind, handle, collisionIndex)
    }

    fun getColliderRid(collisionIndex: Int = 0): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getColliderRidBind, handle, collisionIndex)
    }

    fun getCollider(collisionIndex: Int = 0): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getColliderBind, handle, collisionIndex))
    }

    fun getColliderShape(collisionIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getColliderShapeBind, handle, collisionIndex)
    }

    fun getCollisionLocalShape(collisionIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCollisionLocalShapeBind, handle, collisionIndex)
    }

    fun getCollisionDepth(collisionIndex: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getCollisionDepthBind, handle, collisionIndex)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsTestMotionResult3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsTestMotionResult3D? =
            if (handle.address() == 0L) null else PhysicsTestMotionResult3D(handle)

        private const val GET_TRAVEL_HASH = 3360562783L
        private val getTravelBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_travel", GET_TRAVEL_HASH)
        }

        private const val GET_REMAINDER_HASH = 3360562783L
        private val getRemainderBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_remainder", GET_REMAINDER_HASH)
        }

        private const val GET_COLLISION_SAFE_FRACTION_HASH = 1740695150L
        private val getCollisionSafeFractionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_safe_fraction", GET_COLLISION_SAFE_FRACTION_HASH)
        }

        private const val GET_COLLISION_UNSAFE_FRACTION_HASH = 1740695150L
        private val getCollisionUnsafeFractionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_unsafe_fraction", GET_COLLISION_UNSAFE_FRACTION_HASH)
        }

        private const val GET_COLLISION_COUNT_HASH = 3905245786L
        private val getCollisionCountBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_count", GET_COLLISION_COUNT_HASH)
        }

        private const val GET_COLLISION_POINT_HASH = 1914908202L
        private val getCollisionPointBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_point", GET_COLLISION_POINT_HASH)
        }

        private const val GET_COLLISION_NORMAL_HASH = 1914908202L
        private val getCollisionNormalBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)
        }

        private const val GET_COLLIDER_VELOCITY_HASH = 1914908202L
        private val getColliderVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider_velocity", GET_COLLIDER_VELOCITY_HASH)
        }

        private const val GET_COLLIDER_ID_HASH = 1591665591L
        private val getColliderIdBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider_id", GET_COLLIDER_ID_HASH)
        }

        private const val GET_COLLIDER_RID_HASH = 1231817359L
        private val getColliderRidBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider_rid", GET_COLLIDER_RID_HASH)
        }

        private const val GET_COLLIDER_HASH = 2639523548L
        private val getColliderBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider", GET_COLLIDER_HASH)
        }

        private const val GET_COLLIDER_SHAPE_HASH = 1591665591L
        private val getColliderShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
        }

        private const val GET_COLLISION_LOCAL_SHAPE_HASH = 1591665591L
        private val getCollisionLocalShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_local_shape", GET_COLLISION_LOCAL_SHAPE_HASH)
        }

        private const val GET_COLLISION_DEPTH_HASH = 218038398L
        private val getCollisionDepthBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_depth", GET_COLLISION_DEPTH_HASH)
        }
    }
}
