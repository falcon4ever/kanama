package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: KinematicCollision2D
 */
class KinematicCollision2D(handle: MemorySegment) : RefCounted(handle) {
    fun getPosition(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
    }

    fun getNormal(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getNormalBind, handle)
    }

    fun getTravel(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getTravelBind, handle)
    }

    fun getRemainder(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getRemainderBind, handle)
    }

    fun getAngle(upDirection: Vector2): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(getAngleBind, handle, upDirection)
    }

    fun getDepth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, handle)
    }

    fun getLocalShape(): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLocalShapeBind, handle))
    }

    fun getCollider(): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColliderBind, handle))
    }

    fun getColliderId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getColliderIdBind, handle)
    }

    fun getColliderRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getColliderRidBind, handle)
    }

    fun getColliderShape(): GodotObject? {
        checkOpen()
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColliderShapeBind, handle))
    }

    fun getColliderShapeIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getColliderShapeIndexBind, handle)
    }

    fun getColliderVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getColliderVelocityBind, handle)
    }

    companion object {
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
