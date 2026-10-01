package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Abstract base class for 3D game objects affected by physics.
 *
 * Generated from Godot docs: PhysicsBody3D
 */
open class PhysicsBody3D(handle: GodotHandle) : CollisionObject3D(handle) {
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

    /**
     * Moves the body along the vector `motion`. In order to be frame rate independent in
     * `Node._physics_process` or `Node._process`, `motion` should be computed using `delta`. The body
     * will stop if it collides. Returns a `KinematicCollision3D`, which contains information about the
     * collision when stopped, or when touching another body along the motion. If `test_only` is
     * `true`, the body does not move but the would-be collision information is given. `safe_margin` is
     * the extra margin used for collision recovery (see `CharacterBody3D.safe_margin` for more
     * details). If `recovery_as_collision` is `true`, any depenetration from the recovery phase is
     * also reported as a collision; this is used e.g. by `CharacterBody3D` for improving floor
     * detection during floor snapping. `max_collisions` allows to retrieve more than one collision
     * result.
     *
     * Generated from Godot docs: PhysicsBody3D.move_and_collide
     */
    fun moveAndCollide(motion: Vector3, testOnly: Boolean = false, safeMargin: Double = 0.001, recoveryAsCollision: Boolean = false, maxCollisions: Int = 1): KinematicCollision3D? {
        return KinematicCollision3D.wrap(ObjectCalls.ptrcallWithVector3BoolFloatBoolIntArgsRetObject(moveAndCollideBind, segment, motion, testOnly, safeMargin, recoveryAsCollision, maxCollisions))
    }

    /**
     * Checks for collisions without moving the body. In order to be frame rate independent in
     * `Node._physics_process` or `Node._process`, `motion` should be computed using `delta`. Virtually
     * sets the node's position, scale and rotation to that of the given `Transform3D`, then tries to
     * move the body along the vector `motion`. Returns `true` if a collision would stop the body from
     * moving along the whole path. `collision` is an optional object of type `KinematicCollision3D`,
     * which contains additional information about the collision when stopped, or when touching another
     * body along the motion. `safe_margin` is the extra margin used for collision recovery (see
     * `CharacterBody3D.safe_margin` for more details). If `recovery_as_collision` is `true`, any
     * depenetration from the recovery phase is also reported as a collision; this is useful for
     * checking whether the body would touch any other bodies. `max_collisions` allows to retrieve more
     * than one collision result.
     *
     * Generated from Godot docs: PhysicsBody3D.test_move
     */
    fun testMove(from: Transform3D, motion: Vector3, collision: KinematicCollision3D?, safeMargin: Double = 0.001, recoveryAsCollision: Boolean = false, maxCollisions: Int = 1): Boolean {
        return ObjectCalls.ptrcallWithTransform3DVector3ObjectDoubleBoolIntArgsRetBool(testMoveBind, segment, from, motion, collision?.requireOpenHandle() ?: NULL_SEGMENT, safeMargin, recoveryAsCollision, maxCollisions)
    }

    /**
     * Returns the gravity vector computed from all sources that can affect the body, including all
     * gravity overrides from `Area3D` nodes and the global world gravity.
     *
     * Generated from Godot docs: PhysicsBody3D.get_gravity
     */
    fun getGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityBind, segment)
    }

    /**
     * Lock the body's linear movement in the Z axis.
     *
     * Generated from Godot docs: PhysicsBody3D.set_axis_lock
     */
    fun setAxisLock(axis: Long, lock: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setAxisLockBind, segment, axis, lock)
    }

    /**
     * Lock the body's linear movement in the Z axis.
     *
     * Generated from Godot docs: PhysicsBody3D.get_axis_lock
     */
    fun getAxisLock(axis: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getAxisLockBind, segment, axis)
    }

    /**
     * Returns an array of nodes that were added as collision exceptions for this body.
     *
     * Generated from Godot docs: PhysicsBody3D.get_collision_exceptions
     */
    fun getCollisionExceptions(): List<PhysicsBody3D> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCollisionExceptionsBind, segment, PhysicsBody3D::wrap)
    }

    /**
     * Adds a body to the list of bodies that this body can't collide with.
     *
     * Generated from Godot docs: PhysicsBody3D.add_collision_exception_with
     */
    fun addCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(addCollisionExceptionWithBind, segment, listOf(body.segment))
    }

    /**
     * Removes a body from the list of bodies that this body can't collide with.
     *
     * Generated from Godot docs: PhysicsBody3D.remove_collision_exception_with
     */
    fun removeCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(removeCollisionExceptionWithBind, segment, listOf(body.segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsBody3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsBody3D? =
            if (handle.address() == 0L) null else PhysicsBody3D(GodotHandle(handle))

        // PhysicsServer3D.BodyAxis flags, exposed on PhysicsBody3D to match the desktop/Android API
        // (used by set_axis_lock). Aliases of the @GlobalScope PhysicsServer3D.BODY_AXIS_* bit flags,
        // which the shared tree generates as `const val`s on PhysicsServer3D.
        const val BODY_AXIS_LINEAR_X: Long = PhysicsServer3D.BODY_AXIS_LINEAR_X
        const val BODY_AXIS_LINEAR_Y: Long = PhysicsServer3D.BODY_AXIS_LINEAR_Y
        const val BODY_AXIS_LINEAR_Z: Long = PhysicsServer3D.BODY_AXIS_LINEAR_Z
        const val BODY_AXIS_ANGULAR_X: Long = PhysicsServer3D.BODY_AXIS_ANGULAR_X
        const val BODY_AXIS_ANGULAR_Y: Long = PhysicsServer3D.BODY_AXIS_ANGULAR_Y
        const val BODY_AXIS_ANGULAR_Z: Long = PhysicsServer3D.BODY_AXIS_ANGULAR_Z

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
