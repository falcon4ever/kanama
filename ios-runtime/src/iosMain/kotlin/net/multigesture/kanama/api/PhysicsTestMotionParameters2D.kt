package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: PhysicsTestMotionParameters2D
 */
class PhysicsTestMotionParameters2D(handle: MemorySegment) : RefCounted(handle) {
    var from: Transform2D
        @JvmName("fromProperty")
        get() = getFrom()
        @JvmName("setFromProperty")
        set(value) = setFrom(value)

    var motion: Vector2
        @JvmName("motionProperty")
        get() = getMotion()
        @JvmName("setMotionProperty")
        set(value) = setMotion(value)

    var margin: Double
        @JvmName("marginProperty")
        get() = getMargin()
        @JvmName("setMarginProperty")
        set(value) = setMargin(value)

    var collideSeparationRay: Boolean
        @JvmName("collideSeparationRayProperty")
        get() = isCollideSeparationRayEnabled()
        @JvmName("setCollideSeparationRayProperty")
        set(value) = setCollideSeparationRayEnabled(value)

    val excludeObjects: List<Long>
        @JvmName("excludeObjectsProperty")
        get() = getExcludeObjects()

    var recoveryAsCollision: Boolean
        @JvmName("recoveryAsCollisionProperty")
        get() = isRecoveryAsCollisionEnabled()
        @JvmName("setRecoveryAsCollisionProperty")
        set(value) = setRecoveryAsCollisionEnabled(value)

    fun getFrom(): Transform2D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getFromBind, handle)
    }

    fun setFrom(from: Transform2D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform2DArg(setFromBind, handle, from)
    }

    fun getMotion(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getMotionBind, handle)
    }

    fun setMotion(motion: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setMotionBind, handle, motion)
    }

    fun getMargin(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, handle)
    }

    fun setMargin(margin: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, handle, margin)
    }

    fun isCollideSeparationRayEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideSeparationRayEnabledBind, handle)
    }

    fun setCollideSeparationRayEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setCollideSeparationRayEnabledBind, handle, enabled)
    }

    fun getExcludeObjects(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLongList(getExcludeObjectsBind, handle)
    }

    fun isRecoveryAsCollisionEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isRecoveryAsCollisionEnabledBind, handle)
    }

    fun setRecoveryAsCollisionEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setRecoveryAsCollisionEnabledBind, handle, enabled)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): PhysicsTestMotionParameters2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsTestMotionParameters2D? =
            if (handle.address() == 0L) null else PhysicsTestMotionParameters2D(handle)

        private const val GET_FROM_HASH = 3814499831L
        private val getFromBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "get_from", GET_FROM_HASH)
        }

        private const val SET_FROM_HASH = 2761652528L
        private val setFromBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "set_from", SET_FROM_HASH)
        }

        private const val GET_MOTION_HASH = 3341600327L
        private val getMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "get_motion", GET_MOTION_HASH)
        }

        private const val SET_MOTION_HASH = 743155724L
        private val setMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "set_motion", SET_MOTION_HASH)
        }

        private const val GET_MARGIN_HASH = 1740695150L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "get_margin", GET_MARGIN_HASH)
        }

        private const val SET_MARGIN_HASH = 373806689L
        private val setMarginBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "set_margin", SET_MARGIN_HASH)
        }

        private const val IS_COLLIDE_SEPARATION_RAY_ENABLED_HASH = 36873697L
        private val isCollideSeparationRayEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "is_collide_separation_ray_enabled", IS_COLLIDE_SEPARATION_RAY_ENABLED_HASH)
        }

        private const val SET_COLLIDE_SEPARATION_RAY_ENABLED_HASH = 2586408642L
        private val setCollideSeparationRayEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "set_collide_separation_ray_enabled", SET_COLLIDE_SEPARATION_RAY_ENABLED_HASH)
        }

        private const val GET_EXCLUDE_OBJECTS_HASH = 3995934104L
        private val getExcludeObjectsBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "get_exclude_objects", GET_EXCLUDE_OBJECTS_HASH)
        }

        private const val IS_RECOVERY_AS_COLLISION_ENABLED_HASH = 36873697L
        private val isRecoveryAsCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "is_recovery_as_collision_enabled", IS_RECOVERY_AS_COLLISION_ENABLED_HASH)
        }

        private const val SET_RECOVERY_AS_COLLISION_ENABLED_HASH = 2586408642L
        private val setRecoveryAsCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "set_recovery_as_collision_enabled", SET_RECOVERY_AS_COLLISION_ENABLED_HASH)
        }
    }
}
