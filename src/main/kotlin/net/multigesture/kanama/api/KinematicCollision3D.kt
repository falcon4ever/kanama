package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * Holds collision data from the movement of a `PhysicsBody3D`.
 *
 * Generated from Godot docs: KinematicCollision3D
 */
class KinematicCollision3D(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the moving object's travel before collision.
     *
     * Generated from Godot docs: KinematicCollision3D.get_travel
     */
    fun getTravel(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTravelBind, handle)
    }

    /**
     * Returns the moving object's remaining movement vector.
     *
     * Generated from Godot docs: KinematicCollision3D.get_remainder
     */
    fun getRemainder(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRemainderBind, handle)
    }

    /**
     * Returns the colliding body's length of overlap along the collision normal.
     *
     * Generated from Godot docs: KinematicCollision3D.get_depth
     */
    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, handle)
    }

    /**
     * Returns the number of detected collisions.
     *
     * Generated from Godot docs: KinematicCollision3D.get_collision_count
     */
    fun getCollisionCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionCountBind, handle)
    }

    /**
     * Returns the point of collision in global coordinates given a collision index (the deepest
     * collision by default).
     *
     * Generated from Godot docs: KinematicCollision3D.get_position
     */
    fun getPosition(collisionIndex: Int = 0): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getPositionBind, handle, collisionIndex)
    }

    /**
     * Returns the colliding body's shape's normal at the point of collision given a collision index
     * (the deepest collision by default).
     *
     * Generated from Godot docs: KinematicCollision3D.get_normal
     */
    fun getNormal(collisionIndex: Int = 0): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getNormalBind, handle, collisionIndex)
    }

    /**
     * Returns the collision angle according to `up_direction`, which is `Vector3.UP` by default. This
     * value is always positive.
     *
     * Generated from Godot docs: KinematicCollision3D.get_angle
     */
    fun getAngle(collisionIndex: Int = 0, upDirection: Vector3): Double {
        return ObjectCalls.ptrcallWithIntVector3ArgsRetDouble(getAngleBind, handle, collisionIndex, upDirection)
    }

    /**
     * Returns the moving object's colliding shape given a collision index (the deepest collision by
     * default).
     *
     * Generated from Godot docs: KinematicCollision3D.get_local_shape
     */
    fun getLocalShape(collisionIndex: Int = 0): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getLocalShapeBind, handle, collisionIndex))
    }

    /**
     * Returns the colliding body's attached `Object` given a collision index (the deepest collision by
     * default).
     *
     * Generated from Godot docs: KinematicCollision3D.get_collider
     */
    fun getCollider(collisionIndex: Int = 0): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getColliderBind, handle, collisionIndex))
    }

    /**
     * Returns the unique instance ID of the colliding body's attached `Object` given a collision index
     * (the deepest collision by default). See `Object.get_instance_id`.
     *
     * Generated from Godot docs: KinematicCollision3D.get_collider_id
     */
    fun getColliderId(collisionIndex: Int = 0): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getColliderIdBind, handle, collisionIndex)
    }

    /**
     * Returns the colliding body's `RID` used by the `PhysicsServer3D` given a collision index (the
     * deepest collision by default).
     *
     * Generated from Godot docs: KinematicCollision3D.get_collider_rid
     */
    fun getColliderRid(collisionIndex: Int = 0): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getColliderRidBind, handle, collisionIndex)
    }

    /**
     * Returns the colliding body's shape given a collision index (the deepest collision by default).
     *
     * Generated from Godot docs: KinematicCollision3D.get_collider_shape
     */
    fun getColliderShape(collisionIndex: Int = 0): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getColliderShapeBind, handle, collisionIndex))
    }

    /**
     * Returns the colliding body's shape index given a collision index (the deepest collision by
     * default). See `CollisionObject3D`.
     *
     * Generated from Godot docs: KinematicCollision3D.get_collider_shape_index
     */
    fun getColliderShapeIndex(collisionIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getColliderShapeIndexBind, handle, collisionIndex)
    }

    /**
     * Returns the colliding body's velocity given a collision index (the deepest collision by
     * default).
     *
     * Generated from Godot docs: KinematicCollision3D.get_collider_velocity
     */
    fun getColliderVelocity(collisionIndex: Int = 0): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getColliderVelocityBind, handle, collisionIndex)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): KinematicCollision3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): KinematicCollision3D? =
            if (handle.address() == 0L) null else KinematicCollision3D(handle)

        private const val GET_TRAVEL_HASH = 3360562783L
        private val getTravelBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_travel", GET_TRAVEL_HASH)
        }

        private const val GET_REMAINDER_HASH = 3360562783L
        private val getRemainderBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_remainder", GET_REMAINDER_HASH)
        }

        private const val GET_DEPTH_HASH = 1740695150L
        private val getDepthBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_depth", GET_DEPTH_HASH)
        }

        private const val GET_COLLISION_COUNT_HASH = 3905245786L
        private val getCollisionCountBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_collision_count", GET_COLLISION_COUNT_HASH)
        }

        private const val GET_POSITION_HASH = 1914908202L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_position", GET_POSITION_HASH)
        }

        private const val GET_NORMAL_HASH = 1914908202L
        private val getNormalBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_normal", GET_NORMAL_HASH)
        }

        private const val GET_ANGLE_HASH = 1242741860L
        private val getAngleBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_angle", GET_ANGLE_HASH)
        }

        private const val GET_LOCAL_SHAPE_HASH = 2639523548L
        private val getLocalShapeBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_local_shape", GET_LOCAL_SHAPE_HASH)
        }

        private const val GET_COLLIDER_HASH = 2639523548L
        private val getColliderBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_collider", GET_COLLIDER_HASH)
        }

        private const val GET_COLLIDER_ID_HASH = 1591665591L
        private val getColliderIdBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_collider_id", GET_COLLIDER_ID_HASH)
        }

        private const val GET_COLLIDER_RID_HASH = 1231817359L
        private val getColliderRidBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_collider_rid", GET_COLLIDER_RID_HASH)
        }

        private const val GET_COLLIDER_SHAPE_HASH = 2639523548L
        private val getColliderShapeBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
        }

        private const val GET_COLLIDER_SHAPE_INDEX_HASH = 1591665591L
        private val getColliderShapeIndexBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_collider_shape_index", GET_COLLIDER_SHAPE_INDEX_HASH)
        }

        private const val GET_COLLIDER_VELOCITY_HASH = 1914908202L
        private val getColliderVelocityBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision3D", "get_collider_velocity", GET_COLLIDER_VELOCITY_HASH)
        }
    }
}
