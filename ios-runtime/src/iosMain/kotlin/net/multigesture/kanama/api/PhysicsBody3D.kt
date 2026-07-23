package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: PhysicsBody3D
 */
open class PhysicsBody3D(handle: MemorySegment) : CollisionObject3D(handle) {
    var axisLockLinearX: Boolean
        @JvmName("axisLockLinearXProperty")
        get() = getAxisLock(1L)
        @JvmName("setAxisLockLinearXProperty")
        set(value) = setAxisLock(1L, value)

    var axisLockLinearY: Boolean
        @JvmName("axisLockLinearYProperty")
        get() = getAxisLock(2L)
        @JvmName("setAxisLockLinearYProperty")
        set(value) = setAxisLock(2L, value)

    var axisLockLinearZ: Boolean
        @JvmName("axisLockLinearZProperty")
        get() = getAxisLock(4L)
        @JvmName("setAxisLockLinearZProperty")
        set(value) = setAxisLock(4L, value)

    var axisLockAngularX: Boolean
        @JvmName("axisLockAngularXProperty")
        get() = getAxisLock(8L)
        @JvmName("setAxisLockAngularXProperty")
        set(value) = setAxisLock(8L, value)

    var axisLockAngularY: Boolean
        @JvmName("axisLockAngularYProperty")
        get() = getAxisLock(16L)
        @JvmName("setAxisLockAngularYProperty")
        set(value) = setAxisLock(16L, value)

    var axisLockAngularZ: Boolean
        @JvmName("axisLockAngularZProperty")
        get() = getAxisLock(32L)
        @JvmName("setAxisLockAngularZProperty")
        set(value) = setAxisLock(32L, value)

    fun moveAndCollide(motion: Vector3, testOnly: Boolean = false, safeMargin: Double = 0.001, recoveryAsCollision: Boolean = false, maxCollisions: Int = 1): KinematicCollision3D? {
        return KinematicCollision3D.wrap(ObjectCalls.ptrcallWithVector3BoolFloatBoolIntArgsRetObject(moveAndCollideBind, handle, motion, testOnly, safeMargin, recoveryAsCollision, maxCollisions))
    }

    fun testMove(from: Transform3D, motion: Vector3, collision: KinematicCollision3D?, safeMargin: Double = 0.001, recoveryAsCollision: Boolean = false, maxCollisions: Int = 1): Boolean {
        return ObjectCalls.ptrcallWithTransform3DVector3ObjectDoubleBoolIntArgsRetBool(testMoveBind, handle, from, motion, collision?.requireOpenHandle() ?: MemorySegment.NULL, safeMargin, recoveryAsCollision, maxCollisions)
    }

    fun getGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityBind, handle)
    }

    fun setAxisLock(axis: Long, lock: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setAxisLockBind, handle, axis, lock)
    }

    fun getAxisLock(axis: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getAxisLockBind, handle, axis)
    }

    fun getCollisionExceptions(): List<PhysicsBody3D> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCollisionExceptionsBind, handle, PhysicsBody3D::fromHandle)
    }

    fun addCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(addCollisionExceptionWithBind, handle, listOf(body.handle))
    }

    fun removeCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(removeCollisionExceptionWithBind, handle, listOf(body.handle))
    }

    companion object {
        fun fromHandle(handle: MemorySegment): PhysicsBody3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsBody3D? =
            if (handle.address() == 0L) null else PhysicsBody3D(handle)

        // PhysicsServer3D.BodyAxis flags, exposed on PhysicsBody3D to match the desktop/Android API
        // (used by set_axis_lock). Values are @GlobalScope PhysicsServer3D.BODY_AXIS_* bit flags.
        const val BODY_AXIS_LINEAR_X = 1L
        const val BODY_AXIS_LINEAR_Y = 2L
        const val BODY_AXIS_LINEAR_Z = 4L
        const val BODY_AXIS_ANGULAR_X = 8L
        const val BODY_AXIS_ANGULAR_Y = 16L
        const val BODY_AXIS_ANGULAR_Z = 32L

        private const val MOVE_AND_COLLIDE_HASH = 3208792678L
        private val moveAndCollideBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody3D", "move_and_collide", MOVE_AND_COLLIDE_HASH)
        }

        private const val TEST_MOVE_HASH = 2481691619L
        private val testMoveBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody3D", "test_move", TEST_MOVE_HASH)
        }

        private const val GET_GRAVITY_HASH = 3360562783L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody3D", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val SET_AXIS_LOCK_HASH = 1787895195L
        private val setAxisLockBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody3D", "set_axis_lock", SET_AXIS_LOCK_HASH)
        }

        private const val GET_AXIS_LOCK_HASH = 2264617709L
        private val getAxisLockBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody3D", "get_axis_lock", GET_AXIS_LOCK_HASH)
        }

        private const val GET_COLLISION_EXCEPTIONS_HASH = 2915620761L
        private val getCollisionExceptionsBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody3D", "get_collision_exceptions", GET_COLLISION_EXCEPTIONS_HASH)
        }

        private const val ADD_COLLISION_EXCEPTION_WITH_HASH = 1078189570L
        private val addCollisionExceptionWithBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody3D", "add_collision_exception_with", ADD_COLLISION_EXCEPTION_WITH_HASH)
        }

        private const val REMOVE_COLLISION_EXCEPTION_WITH_HASH = 1078189570L
        private val removeCollisionExceptionWithBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody3D", "remove_collision_exception_with", REMOVE_COLLISION_EXCEPTION_WITH_HASH)
        }
    }
}
