package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Describes the motion and collision result from `PhysicsServer2D.body_test_motion`.
 *
 * Generated from Godot docs: PhysicsTestMotionResult2D
 */
class PhysicsTestMotionResult2D(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the moving object's travel before collision.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_travel
     */
    fun getTravel(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getTravelBind, segment)
    }

    /**
     * Returns the moving object's remaining movement vector.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_remainder
     */
    fun getRemainder(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getRemainderBind, segment)
    }

    /**
     * Returns the point of collision in global coordinates, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_point
     */
    fun getCollisionPoint(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getCollisionPointBind, segment)
    }

    /**
     * Returns the colliding body's shape's normal at the point of collision, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_normal
     */
    fun getCollisionNormal(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getCollisionNormalBind, segment)
    }

    /**
     * Returns the colliding body's velocity, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider_velocity
     */
    fun getColliderVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getColliderVelocityBind, segment)
    }

    /**
     * Returns the unique instance ID of the colliding body's attached `Object`, if a collision
     * occurred. See `Object.get_instance_id`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider_id
     */
    fun getColliderId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getColliderIdBind, segment)
    }

    /**
     * Returns the colliding body's `RID` used by the `PhysicsServer2D`, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider_rid
     */
    fun getColliderRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getColliderRidBind, segment)
    }

    /**
     * Returns the colliding body's attached `Object`, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider
     */
    fun getCollider(): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColliderBind, segment))
    }

    /**
     * Returns the colliding body's shape index, if a collision occurred. See `CollisionObject2D`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collider_shape
     */
    fun getColliderShape(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getColliderShapeBind, segment)
    }

    /**
     * Returns the moving object's colliding shape, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_local_shape
     */
    fun getCollisionLocalShape(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCollisionLocalShapeBind, segment)
    }

    /**
     * Returns the length of overlap along the collision normal, if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_depth
     */
    fun getCollisionDepth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionDepthBind, segment)
    }

    /**
     * Returns the maximum fraction of the motion that can occur without a collision, between `0` and
     * `1`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_safe_fraction
     */
    fun getCollisionSafeFraction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionSafeFractionBind, segment)
    }

    /**
     * Returns the minimum fraction of the motion needed to collide, if a collision occurred, between
     * `0` and `1`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult2D.get_collision_unsafe_fraction
     */
    fun getCollisionUnsafeFraction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionUnsafeFractionBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsTestMotionResult2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsTestMotionResult2D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsTestMotionResult2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsTestMotionResult2D? =
            if (handle.address() == 0L) null else PhysicsTestMotionResult2D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_TRAVEL_HASH = 3341600327L
        @JvmField
        val getTravelBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_travel", GET_TRAVEL_HASH)

        private const val GET_REMAINDER_HASH = 3341600327L
        @JvmField
        val getRemainderBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_remainder", GET_REMAINDER_HASH)

        private const val GET_COLLISION_POINT_HASH = 3341600327L
        @JvmField
        val getCollisionPointBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_point", GET_COLLISION_POINT_HASH)

        private const val GET_COLLISION_NORMAL_HASH = 3341600327L
        @JvmField
        val getCollisionNormalBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)

        private const val GET_COLLIDER_VELOCITY_HASH = 3341600327L
        @JvmField
        val getColliderVelocityBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider_velocity", GET_COLLIDER_VELOCITY_HASH)

        private const val GET_COLLIDER_ID_HASH = 3905245786L
        @JvmField
        val getColliderIdBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider_id", GET_COLLIDER_ID_HASH)

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_HASH = 1981248198L
        @JvmField
        val getColliderBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider", GET_COLLIDER_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 3905245786L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)

        private const val GET_COLLISION_LOCAL_SHAPE_HASH = 3905245786L
        @JvmField
        val getCollisionLocalShapeBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_local_shape", GET_COLLISION_LOCAL_SHAPE_HASH)

        private const val GET_COLLISION_DEPTH_HASH = 1740695150L
        @JvmField
        val getCollisionDepthBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_depth", GET_COLLISION_DEPTH_HASH)

        private const val GET_COLLISION_SAFE_FRACTION_HASH = 1740695150L
        @JvmField
        val getCollisionSafeFractionBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_safe_fraction", GET_COLLISION_SAFE_FRACTION_HASH)

        private const val GET_COLLISION_UNSAFE_FRACTION_HASH = 1740695150L
        @JvmField
        val getCollisionUnsafeFractionBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult2D", "get_collision_unsafe_fraction", GET_COLLISION_UNSAFE_FRACTION_HASH)
    }
}
