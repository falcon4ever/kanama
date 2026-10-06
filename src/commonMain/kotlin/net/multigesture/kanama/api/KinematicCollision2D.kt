package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Holds collision data from the movement of a `PhysicsBody2D`.
 *
 * Generated from Godot docs: KinematicCollision2D
 */
class KinematicCollision2D(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the point of collision in global coordinates.
     *
     * Generated from Godot docs: KinematicCollision2D.get_position
     */
    fun getPosition(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getPositionBind, segment)
    }

    /**
     * Returns the colliding body's shape's normal at the point of collision.
     *
     * Generated from Godot docs: KinematicCollision2D.get_normal
     */
    fun getNormal(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getNormalBind, segment)
    }

    /**
     * Returns the moving object's travel before collision.
     *
     * Generated from Godot docs: KinematicCollision2D.get_travel
     */
    fun getTravel(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getTravelBind, segment)
    }

    /**
     * Returns the moving object's remaining movement vector.
     *
     * Generated from Godot docs: KinematicCollision2D.get_remainder
     */
    fun getRemainder(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getRemainderBind, segment)
    }

    /**
     * Returns the collision angle according to `up_direction`, which is `Vector2.UP` by default. This
     * value is always positive.
     *
     * Generated from Godot docs: KinematicCollision2D.get_angle
     */
    fun getAngle(upDirection: Vector2): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(Binds.getAngleBind, segment, upDirection)
    }

    /**
     * Returns the colliding body's length of overlap along the collision normal.
     *
     * Generated from Godot docs: KinematicCollision2D.get_depth
     */
    fun getDepth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthBind, segment)
    }

    /**
     * Returns the moving object's colliding shape.
     *
     * Generated from Godot docs: KinematicCollision2D.get_local_shape
     */
    fun getLocalShape(): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getLocalShapeBind, segment))
    }

    /**
     * Returns the colliding body's attached `Object`.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider
     */
    fun getCollider(): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColliderBind, segment))
    }

    /**
     * Returns the unique instance ID of the colliding body's attached `Object`. See
     * `Object.get_instance_id`.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_id
     */
    fun getColliderId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getColliderIdBind, segment)
    }

    /**
     * Returns the colliding body's `RID` used by the `PhysicsServer2D`.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_rid
     */
    fun getColliderRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getColliderRidBind, segment)
    }

    /**
     * Returns the colliding body's shape.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_shape
     */
    fun getColliderShape(): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColliderShapeBind, segment))
    }

    /**
     * Returns the colliding body's shape index. See `CollisionObject2D`.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_shape_index
     */
    fun getColliderShapeIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getColliderShapeIndexBind, segment)
    }

    /**
     * Returns the colliding body's velocity.
     *
     * Generated from Godot docs: KinematicCollision2D.get_collider_velocity
     */
    fun getColliderVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getColliderVelocityBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): KinematicCollision2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): KinematicCollision2D? =
            if (handle.address() == 0L) null else RefCounted.owned(KinematicCollision2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): KinematicCollision2D? =
            if (handle.address() == 0L) null else KinematicCollision2D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_POSITION_HASH = 3341600327L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_position", GET_POSITION_HASH)

        private const val GET_NORMAL_HASH = 3341600327L
        @JvmField
        val getNormalBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_normal", GET_NORMAL_HASH)

        private const val GET_TRAVEL_HASH = 3341600327L
        @JvmField
        val getTravelBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_travel", GET_TRAVEL_HASH)

        private const val GET_REMAINDER_HASH = 3341600327L
        @JvmField
        val getRemainderBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_remainder", GET_REMAINDER_HASH)

        private const val GET_ANGLE_HASH = 2841063350L
        @JvmField
        val getAngleBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_angle", GET_ANGLE_HASH)

        private const val GET_DEPTH_HASH = 1740695150L
        @JvmField
        val getDepthBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_depth", GET_DEPTH_HASH)

        private const val GET_LOCAL_SHAPE_HASH = 1981248198L
        @JvmField
        val getLocalShapeBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_local_shape", GET_LOCAL_SHAPE_HASH)

        private const val GET_COLLIDER_HASH = 1981248198L
        @JvmField
        val getColliderBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider", GET_COLLIDER_HASH)

        private const val GET_COLLIDER_ID_HASH = 3905245786L
        @JvmField
        val getColliderIdBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_id", GET_COLLIDER_ID_HASH)

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 1981248198L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)

        private const val GET_COLLIDER_SHAPE_INDEX_HASH = 3905245786L
        @JvmField
        val getColliderShapeIndexBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_shape_index", GET_COLLIDER_SHAPE_INDEX_HASH)

        private const val GET_COLLIDER_VELOCITY_HASH = 3341600327L
        @JvmField
        val getColliderVelocityBind =
            ObjectCalls.getMethodBind("KinematicCollision2D", "get_collider_velocity", GET_COLLIDER_VELOCITY_HASH)
    }
}
