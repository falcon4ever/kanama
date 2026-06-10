package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: PhysicsBody3D
 */
open class PhysicsBody3D(handle: MemorySegment) : CollisionObject3D(handle) {
    fun getGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityBind, handle)
    }

    fun setAxisLock(axis: Long, lock: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setAxisLockBind, handle, axis, lock)
    }

    fun getAxisLock(axis: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getAxisLockBind, handle, axis)
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
