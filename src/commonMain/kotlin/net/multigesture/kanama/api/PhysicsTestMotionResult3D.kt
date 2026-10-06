package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * Describes the motion and collision result from `PhysicsServer3D.body_test_motion`.
 *
 * Generated from Godot docs: PhysicsTestMotionResult3D
 */
class PhysicsTestMotionResult3D(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the moving object's travel before collision.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_travel
     */
    fun getTravel(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getTravelBind, segment)
    }

    /**
     * Returns the moving object's remaining movement vector.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_remainder
     */
    fun getRemainder(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRemainderBind, segment)
    }

    /**
     * Returns the maximum fraction of the motion that can occur without a collision, between `0` and
     * `1`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collision_safe_fraction
     */
    fun getCollisionSafeFraction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionSafeFractionBind, segment)
    }

    /**
     * Returns the minimum fraction of the motion needed to collide, if a collision occurred, between
     * `0` and `1`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collision_unsafe_fraction
     */
    fun getCollisionUnsafeFraction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionUnsafeFractionBind, segment)
    }

    /**
     * Returns the number of detected collisions.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collision_count
     */
    fun getCollisionCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCollisionCountBind, segment)
    }

    /**
     * Returns the point of collision in global coordinates given a collision index (the deepest
     * collision by default), if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collision_point
     */
    fun getCollisionPoint(collisionIndex: Int = 0): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getCollisionPointBind, segment, collisionIndex)
    }

    /**
     * Returns the colliding body's shape's normal at the point of collision given a collision index
     * (the deepest collision by default), if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collision_normal
     */
    fun getCollisionNormal(collisionIndex: Int = 0): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getCollisionNormalBind, segment, collisionIndex)
    }

    /**
     * Returns the colliding body's velocity given a collision index (the deepest collision by
     * default), if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collider_velocity
     */
    fun getColliderVelocity(collisionIndex: Int = 0): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getColliderVelocityBind, segment, collisionIndex)
    }

    /**
     * Returns the unique instance ID of the colliding body's attached `Object` given a collision index
     * (the deepest collision by default), if a collision occurred. See `Object.get_instance_id`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collider_id
     */
    fun getColliderId(collisionIndex: Int = 0): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(Binds.getColliderIdBind, segment, collisionIndex)
    }

    /**
     * Returns the colliding body's `RID` used by the `PhysicsServer3D` given a collision index (the
     * deepest collision by default), if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collider_rid
     */
    fun getColliderRid(collisionIndex: Int = 0): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetRID(Binds.getColliderRidBind, segment, collisionIndex)
    }

    /**
     * Returns the colliding body's attached `Object` given a collision index (the deepest collision by
     * default), if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collider
     */
    fun getCollider(collisionIndex: Int = 0): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getColliderBind, segment, collisionIndex))
    }

    /**
     * Returns the colliding body's shape index given a collision index (the deepest collision by
     * default), if a collision occurred. See `CollisionObject3D`.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collider_shape
     */
    fun getColliderShape(collisionIndex: Int = 0): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getColliderShapeBind, segment, collisionIndex)
    }

    /**
     * Returns the moving object's colliding shape given a collision index (the deepest collision by
     * default), if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collision_local_shape
     */
    fun getCollisionLocalShape(collisionIndex: Int = 0): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getCollisionLocalShapeBind, segment, collisionIndex)
    }

    /**
     * Returns the length of overlap along the collision normal given a collision index (the deepest
     * collision by default), if a collision occurred.
     *
     * Generated from Godot docs: PhysicsTestMotionResult3D.get_collision_depth
     */
    fun getCollisionDepth(collisionIndex: Int = 0): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getCollisionDepthBind, segment, collisionIndex)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsTestMotionResult3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsTestMotionResult3D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsTestMotionResult3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsTestMotionResult3D? =
            if (handle.address() == 0L) null else PhysicsTestMotionResult3D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_TRAVEL_HASH = 3360562783L
        @JvmField
        val getTravelBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_travel", GET_TRAVEL_HASH)

        private const val GET_REMAINDER_HASH = 3360562783L
        @JvmField
        val getRemainderBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_remainder", GET_REMAINDER_HASH)

        private const val GET_COLLISION_SAFE_FRACTION_HASH = 1740695150L
        @JvmField
        val getCollisionSafeFractionBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_safe_fraction", GET_COLLISION_SAFE_FRACTION_HASH)

        private const val GET_COLLISION_UNSAFE_FRACTION_HASH = 1740695150L
        @JvmField
        val getCollisionUnsafeFractionBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_unsafe_fraction", GET_COLLISION_UNSAFE_FRACTION_HASH)

        private const val GET_COLLISION_COUNT_HASH = 3905245786L
        @JvmField
        val getCollisionCountBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_count", GET_COLLISION_COUNT_HASH)

        private const val GET_COLLISION_POINT_HASH = 1914908202L
        @JvmField
        val getCollisionPointBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_point", GET_COLLISION_POINT_HASH)

        private const val GET_COLLISION_NORMAL_HASH = 1914908202L
        @JvmField
        val getCollisionNormalBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)

        private const val GET_COLLIDER_VELOCITY_HASH = 1914908202L
        @JvmField
        val getColliderVelocityBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider_velocity", GET_COLLIDER_VELOCITY_HASH)

        private const val GET_COLLIDER_ID_HASH = 1591665591L
        @JvmField
        val getColliderIdBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider_id", GET_COLLIDER_ID_HASH)

        private const val GET_COLLIDER_RID_HASH = 1231817359L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_HASH = 2639523548L
        @JvmField
        val getColliderBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider", GET_COLLIDER_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 1591665591L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)

        private const val GET_COLLISION_LOCAL_SHAPE_HASH = 1591665591L
        @JvmField
        val getCollisionLocalShapeBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_local_shape", GET_COLLISION_LOCAL_SHAPE_HASH)

        private const val GET_COLLISION_DEPTH_HASH = 218038398L
        @JvmField
        val getCollisionDepthBind =
            ObjectCalls.getMethodBind("PhysicsTestMotionResult3D", "get_collision_depth", GET_COLLISION_DEPTH_HASH)
    }
}
