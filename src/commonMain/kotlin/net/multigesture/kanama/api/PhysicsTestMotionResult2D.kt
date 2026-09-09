package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Describes the motion and collision result from `PhysicsServer2D.body_test_motion`.
 *
 * Generated from Godot docs: PhysicsTestMotionResult2D
 */
class PhysicsTestMotionResult2D(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the moving object's travel before collision.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_travel
     */
    fun getTravel(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getTravelBind, handle)
    }

    /**
     * Returns the moving object's remaining movement vector.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_remainder
     */
    fun getRemainder(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getRemainderBind, handle)
    }

    /**
     * Returns the point of collision in global coordinates, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_point
     */
    fun getCollisionPoint(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getCollisionPointBind, handle)
    }

    /**
     * Returns the colliding body's shape's normal at the point of collision, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_normal
     */
    fun getCollisionNormal(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getCollisionNormalBind, handle)
    }

    /**
     * Returns the colliding body's velocity, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider_velocity
     */
    fun getColliderVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getColliderVelocityBind, handle)
    }

    /**
     * Returns the unique instance ID of the colliding body's attached `Object`, if a collision
     * occurred. See `Object.get_instance_id`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider_id
     */
    fun getColliderId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getColliderIdBind, handle)
    }

    /**
     * Returns the colliding body's `RID` used by the `PhysicsServer2D`, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider_rid
     */
    fun getColliderRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getColliderRidBind, handle)
    }

    /**
     * Returns the colliding body's attached `Object`, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider
     */
    fun getCollider(): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColliderBind, handle))
    }

    /**
     * Returns the colliding body's shape index, if a collision occurred. See `CollisionObject2D`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider_shape
     */
    fun getColliderShape(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getColliderShapeBind, handle)
    }

    /**
     * Returns the moving object's colliding shape, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_local_shape
     */
    fun getCollisionLocalShape(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getCollisionLocalShapeBind, handle)
    }

    /**
     * Returns the length of overlap along the collision normal, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_depth
     */
    fun getCollisionDepth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionDepthBind, handle)
    }

    /**
     * Returns the maximum fraction of the motion that can occur without a collision, between `0` and
     * `1`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_safe_fraction
     */
    fun getCollisionSafeFraction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionSafeFractionBind, handle)
    }

    /**
     * Returns the minimum fraction of the motion needed to collide, if a collision occurred, between
     * `0` and `1`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_unsafe_fraction
     */
    fun getCollisionUnsafeFraction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionUnsafeFractionBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsTestMotionResult2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsTestMotionResult2D? =
            if (handle.address() == 0L) null else PhysicsTestMotionResult2D(handle)

        private const val GET_TRAVEL_HASH = 3341600327L
        private val getTravelBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_travel", GET_TRAVEL_HASH)
        }

        private const val GET_REMAINDER_HASH = 3341600327L
        private val getRemainderBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_remainder", GET_REMAINDER_HASH)
        }

        private const val GET_COLLISION_POINT_HASH = 3341600327L
        private val getCollisionPointBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_point", GET_COLLISION_POINT_HASH)
        }

        private const val GET_COLLISION_NORMAL_HASH = 3341600327L
        private val getCollisionNormalBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)
        }

        private const val GET_COLLIDER_VELOCITY_HASH = 3341600327L
        private val getColliderVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider_velocity", GET_COLLIDER_VELOCITY_HASH)
        }

        private const val GET_COLLIDER_ID_HASH = 3905245786L
        private val getColliderIdBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider_id", GET_COLLIDER_ID_HASH)
        }

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        private val getColliderRidBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider_rid", GET_COLLIDER_RID_HASH)
        }

        private const val GET_COLLIDER_HASH = 1981248198L
        private val getColliderBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider", GET_COLLIDER_HASH)
        }

        private const val GET_COLLIDER_SHAPE_HASH = 3905245786L
        private val getColliderShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
        }

        private const val GET_COLLISION_LOCAL_SHAPE_HASH = 3905245786L
        private val getCollisionLocalShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_local_shape", GET_COLLISION_LOCAL_SHAPE_HASH)
        }

        private const val GET_COLLISION_DEPTH_HASH = 1740695150L
        private val getCollisionDepthBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_depth", GET_COLLISION_DEPTH_HASH)
        }

        private const val GET_COLLISION_SAFE_FRACTION_HASH = 1740695150L
        private val getCollisionSafeFractionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_safe_fraction", GET_COLLISION_SAFE_FRACTION_HASH)
        }

        private const val GET_COLLISION_UNSAFE_FRACTION_HASH = 1740695150L
        private val getCollisionUnsafeFractionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_unsafe_fraction", GET_COLLISION_UNSAFE_FRACTION_HASH)
        }
    }
}
