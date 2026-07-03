package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Holds collision data from the movement of a `PhysicsBody2D`.
 *
 * Generated from Godot docs: KinematicCollision2D
 */
class KinematicCollision2D(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the point of collision in global coordinates.
     *
     * Generated from Godot docs: KinematicCollision2D.get_position
     */
    fun getPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
    }

    /**
     * Returns the colliding body's shape's normal at the point of collision.
     *
     * Generated from Godot docs: KinematicCollision2D.get_normal
     */
    fun getNormal(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getNormalBind, handle)
    }

    /**
     * Returns the moving object's travel before collision.
     *
     * Generated from Godot docs: KinematicCollision2D.get_travel
     */
    fun getTravel(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTravelBind, handle)
    }

    /**
     * Returns the moving object's remaining movement vector.
     *
     * Generated from Godot docs: KinematicCollision2D.get_remainder
     */
    fun getRemainder(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getRemainderBind, handle)
    }

    /**
     * Returns the collision angle according to `up_direction`, which is `Vector2.UP` by default. This
     * value is always positive.
     *
     * Generated from Godot docs: KinematicCollision2D.get_angle
     */
    fun getAngle(upDirection: Vector2): Double {
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(getAngleBind, handle, upDirection)
    }

    /**
     * Returns the colliding body's length of overlap along the collision normal.
     *
     * Generated from Godot docs: KinematicCollision2D.get_depth
     */
    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, handle)
    }

    /**
     * Returns the moving object's colliding shape.
     *
     * Generated from Godot docs: KinematicCollision2D.get_local_shape
     */
    fun getLocalShape(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLocalShapeBind, handle))
    }

    /**
     * Returns the colliding body's attached `Object`.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider
     */
    fun getCollider(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColliderBind, handle))
    }

    /**
     * Returns the unique instance ID of the colliding body's attached `Object`. See
     * `Object.get_instance_id`.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_id
     */
    fun getColliderId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getColliderIdBind, handle)
    }

    /**
     * Returns the colliding body's `RID` used by the `PhysicsServer2D`.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_rid
     */
    fun getColliderRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getColliderRidBind, handle)
    }

    /**
     * Returns the colliding body's shape.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_shape
     */
    fun getColliderShape(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColliderShapeBind, handle))
    }

    /**
     * Returns the colliding body's shape index. See `CollisionObject2D`.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_shape_index
     */
    fun getColliderShapeIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getColliderShapeIndexBind, handle)
    }

    /**
     * Returns the colliding body's velocity.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_velocity
     */
    fun getColliderVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getColliderVelocityBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): KinematicCollision2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): KinematicCollision2D? =
            if (handle.address() == 0L) null else KinematicCollision2D(handle)

        private const val GET_POSITION_HASH = 3341600327L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_position", GET_POSITION_HASH)
        }

        private const val GET_NORMAL_HASH = 3341600327L
        private val getNormalBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_normal", GET_NORMAL_HASH)
        }

        private const val GET_TRAVEL_HASH = 3341600327L
        private val getTravelBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_travel", GET_TRAVEL_HASH)
        }

        private const val GET_REMAINDER_HASH = 3341600327L
        private val getRemainderBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_remainder", GET_REMAINDER_HASH)
        }

        private const val GET_ANGLE_HASH = 2841063350L
        private val getAngleBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_angle", GET_ANGLE_HASH)
        }

        private const val GET_DEPTH_HASH = 1740695150L
        private val getDepthBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_depth", GET_DEPTH_HASH)
        }

        private const val GET_LOCAL_SHAPE_HASH = 1981248198L
        private val getLocalShapeBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_local_shape", GET_LOCAL_SHAPE_HASH)
        }

        private const val GET_COLLIDER_HASH = 1981248198L
        private val getColliderBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider", GET_COLLIDER_HASH)
        }

        private const val GET_COLLIDER_ID_HASH = 3905245786L
        private val getColliderIdBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_id", GET_COLLIDER_ID_HASH)
        }

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        private val getColliderRidBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_rid", GET_COLLIDER_RID_HASH)
        }

        private const val GET_COLLIDER_SHAPE_HASH = 1981248198L
        private val getColliderShapeBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
        }

        private const val GET_COLLIDER_SHAPE_INDEX_HASH = 3905245786L
        private val getColliderShapeIndexBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_shape_index", GET_COLLIDER_SHAPE_INDEX_HASH)
        }

        private const val GET_COLLIDER_VELOCITY_HASH = 3341600327L
        private val getColliderVelocityBind by lazy {
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_velocity", GET_COLLIDER_VELOCITY_HASH)
        }
    }
}
