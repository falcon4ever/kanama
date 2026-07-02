package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Provides parameters for `PhysicsServer3D.body_test_motion`.
 *
 * Generated from Godot docs: PhysicsTestMotionParameters3D
 */
class PhysicsTestMotionParameters3D(handle: MemorySegment) : RefCounted(handle) {
    var from: Transform3D
        @JvmName("fromProperty")
        get() = getFrom()
        @JvmName("setFromProperty")
        set(value) = setFrom(value)

    var motion: Vector3
        @JvmName("motionProperty")
        get() = getMotion()
        @JvmName("setMotionProperty")
        set(value) = setMotion(value)

    var margin: Double
        @JvmName("marginProperty")
        get() = getMargin()
        @JvmName("setMarginProperty")
        set(value) = setMargin(value)

    var maxCollisions: Int
        @JvmName("maxCollisionsProperty")
        get() = getMaxCollisions()
        @JvmName("setMaxCollisionsProperty")
        set(value) = setMaxCollisions(value)

    var collideSeparationRay: Boolean
        @JvmName("collideSeparationRayProperty")
        get() = isCollideSeparationRayEnabled()
        @JvmName("setCollideSeparationRayProperty")
        set(value) = setCollideSeparationRayEnabled(value)

    var excludeBodies: List<RID>
        @JvmName("excludeBodiesProperty")
        get() = getExcludeBodies()
        @JvmName("setExcludeBodiesProperty")
        set(value) = setExcludeBodies(value)

    var excludeObjects: List<Long>
        @JvmName("excludeObjectsProperty")
        get() = getExcludeObjects()
        @JvmName("setExcludeObjectsProperty")
        set(value) = setExcludeObjects(value)

    var recoveryAsCollision: Boolean
        @JvmName("recoveryAsCollisionProperty")
        get() = isRecoveryAsCollisionEnabled()
        @JvmName("setRecoveryAsCollisionProperty")
        set(value) = setRecoveryAsCollisionEnabled(value)

    fun getFrom(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getFromBind, handle)
    }

    fun setFrom(from: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setFromBind, handle, from)
    }

    fun getMotion(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getMotionBind, handle)
    }

    fun setMotion(motion: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setMotionBind, handle, motion)
    }

    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, handle)
    }

    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, handle, margin)
    }

    fun getMaxCollisions(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxCollisionsBind, handle)
    }

    fun setMaxCollisions(maxCollisions: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxCollisionsBind, handle, maxCollisions)
    }

    fun isCollideSeparationRayEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideSeparationRayEnabledBind, handle)
    }

    fun setCollideSeparationRayEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideSeparationRayEnabledBind, handle, enabled)
    }

    fun getExcludeBodies(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getExcludeBodiesBind, handle)
    }

    fun setExcludeBodies(excludeList: List<RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setExcludeBodiesBind, handle, excludeList)
    }

    fun getExcludeObjects(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetLongList(getExcludeObjectsBind, handle)
    }

    fun setExcludeObjects(excludeList: List<Long>) {
        ObjectCalls.ptrcallWithTypedIntListArg(setExcludeObjectsBind, handle, excludeList)
    }

    fun isRecoveryAsCollisionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRecoveryAsCollisionEnabledBind, handle)
    }

    fun setRecoveryAsCollisionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRecoveryAsCollisionEnabledBind, handle, enabled)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsTestMotionParameters3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsTestMotionParameters3D? =
            if (handle.address() == 0L) null else PhysicsTestMotionParameters3D(handle)

        private const val GET_FROM_HASH = 3229777777L
        private val getFromBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "get_from", GET_FROM_HASH)
        }

        private const val SET_FROM_HASH = 2952846383L
        private val setFromBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "set_from", SET_FROM_HASH)
        }

        private const val GET_MOTION_HASH = 3360562783L
        private val getMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "get_motion", GET_MOTION_HASH)
        }

        private const val SET_MOTION_HASH = 3460891852L
        private val setMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "set_motion", SET_MOTION_HASH)
        }

        private const val GET_MARGIN_HASH = 1740695150L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "get_margin", GET_MARGIN_HASH)
        }

        private const val SET_MARGIN_HASH = 373806689L
        private val setMarginBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "set_margin", SET_MARGIN_HASH)
        }

        private const val GET_MAX_COLLISIONS_HASH = 3905245786L
        private val getMaxCollisionsBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "get_max_collisions", GET_MAX_COLLISIONS_HASH)
        }

        private const val SET_MAX_COLLISIONS_HASH = 1286410249L
        private val setMaxCollisionsBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "set_max_collisions", SET_MAX_COLLISIONS_HASH)
        }

        private const val IS_COLLIDE_SEPARATION_RAY_ENABLED_HASH = 36873697L
        private val isCollideSeparationRayEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "is_collide_separation_ray_enabled", IS_COLLIDE_SEPARATION_RAY_ENABLED_HASH)
        }

        private const val SET_COLLIDE_SEPARATION_RAY_ENABLED_HASH = 2586408642L
        private val setCollideSeparationRayEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "set_collide_separation_ray_enabled", SET_COLLIDE_SEPARATION_RAY_ENABLED_HASH)
        }

        private const val GET_EXCLUDE_BODIES_HASH = 3995934104L
        private val getExcludeBodiesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "get_exclude_bodies", GET_EXCLUDE_BODIES_HASH)
        }

        private const val SET_EXCLUDE_BODIES_HASH = 381264803L
        private val setExcludeBodiesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "set_exclude_bodies", SET_EXCLUDE_BODIES_HASH)
        }

        private const val GET_EXCLUDE_OBJECTS_HASH = 3995934104L
        private val getExcludeObjectsBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "get_exclude_objects", GET_EXCLUDE_OBJECTS_HASH)
        }

        private const val SET_EXCLUDE_OBJECTS_HASH = 381264803L
        private val setExcludeObjectsBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "set_exclude_objects", SET_EXCLUDE_OBJECTS_HASH)
        }

        private const val IS_RECOVERY_AS_COLLISION_ENABLED_HASH = 36873697L
        private val isRecoveryAsCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "is_recovery_as_collision_enabled", IS_RECOVERY_AS_COLLISION_ENABLED_HASH)
        }

        private const val SET_RECOVERY_AS_COLLISION_ENABLED_HASH = 2586408642L
        private val setRecoveryAsCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsTestMotionParameters3D", "set_recovery_as_collision_enabled", SET_RECOVERY_AS_COLLISION_ENABLED_HASH)
        }
    }
}
