package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Abstract base class for 2D game objects affected by physics.
 *
 * Generated from Godot docs: PhysicsBody2D
 */
open class PhysicsBody2D(handle: MemorySegment) : CollisionObject2D(handle) {
    /**
     * Moves the body along the vector `motion`. In order to be frame rate independent in
     * `Node._physics_process` or `Node._process`, `motion` should be computed using `delta`. Returns a
     * `KinematicCollision2D`, which contains information about the collision when stopped, or when
     * touching another body along the motion. If `test_only` is `true`, the body does not move but the
     * would-be collision information is given. `safe_margin` is the extra margin used for collision
     * recovery (see `CharacterBody2D.safe_margin` for more details). If `recovery_as_collision` is
     * `true`, any depenetration from the recovery phase is also reported as a collision; this is used
     * e.g. by `CharacterBody2D` for improving floor detection during floor snapping.
     *
     * Generated from Godot docs: PhysicsBody2D.move_and_collide
     */
    fun moveAndCollide(motion: Vector2, testOnly: Boolean = false, safeMargin: Double = 0.08, recoveryAsCollision: Boolean = false): KinematicCollision2D? {
        return KinematicCollision2D.wrap(ObjectCalls.ptrcallWithVector2BoolFloatBoolArgsRetObject(moveAndCollideBind, handle, motion, testOnly, safeMargin, recoveryAsCollision))
    }

    /**
     * Checks for collisions without moving the body. In order to be frame rate independent in
     * `Node._physics_process` or `Node._process`, `motion` should be computed using `delta`. Virtually
     * sets the node's position, scale and rotation to that of the given `Transform2D`, then tries to
     * move the body along the vector `motion`. Returns `true` if a collision would stop the body from
     * moving along the whole path. `collision` is an optional object of type `KinematicCollision2D`,
     * which contains additional information about the collision when stopped, or when touching another
     * body along the motion. `safe_margin` is the extra margin used for collision recovery (see
     * `CharacterBody2D.safe_margin` for more details). If `recovery_as_collision` is `true`, any
     * depenetration from the recovery phase is also reported as a collision; this is useful for
     * checking whether the body would touch any other bodies.
     *
     * Generated from Godot docs: PhysicsBody2D.test_move
     */
    fun testMove(from: Transform2D, motion: Vector2, collision: KinematicCollision2D?, safeMargin: Double = 0.08, recoveryAsCollision: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTransform2DVector2ObjectDoubleBoolArgsRetBool(testMoveBind, handle, from, motion, collision?.requireOpenHandle() ?: MemorySegment.NULL, safeMargin, recoveryAsCollision)
    }

    /**
     * Returns the gravity vector computed from all sources that can affect the body, including all
     * gravity overrides from `Area2D` nodes and the global world gravity.
     *
     * Generated from Godot docs: PhysicsBody2D.get_gravity
     */
    fun getGravity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGravityBind, handle)
    }

    /**
     * Returns an array of nodes that were added as collision exceptions for this body.
     *
     * Generated from Godot docs: PhysicsBody2D.get_collision_exceptions
     */
    fun getCollisionExceptions(): List<PhysicsBody2D> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCollisionExceptionsBind, handle, PhysicsBody2D::fromHandle)
    }

    /**
     * Adds a body to the list of bodies that this body can't collide with.
     *
     * Generated from Godot docs: PhysicsBody2D.add_collision_exception_with
     */
    fun addCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(addCollisionExceptionWithBind, handle, listOf(body.handle))
    }

    /**
     * Removes a body from the list of bodies that this body can't collide with.
     *
     * Generated from Godot docs: PhysicsBody2D.remove_collision_exception_with
     */
    fun removeCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(removeCollisionExceptionWithBind, handle, listOf(body.handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsBody2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsBody2D? =
            if (handle.address() == 0L) null else PhysicsBody2D(handle)

        private const val MOVE_AND_COLLIDE_HASH = 3681923724L
        private val moveAndCollideBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody2D", "move_and_collide", MOVE_AND_COLLIDE_HASH)
        }

        private const val TEST_MOVE_HASH = 3324464701L
        private val testMoveBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody2D", "test_move", TEST_MOVE_HASH)
        }

        private const val GET_GRAVITY_HASH = 3341600327L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody2D", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val GET_COLLISION_EXCEPTIONS_HASH = 2915620761L
        private val getCollisionExceptionsBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody2D", "get_collision_exceptions", GET_COLLISION_EXCEPTIONS_HASH)
        }

        private const val ADD_COLLISION_EXCEPTION_WITH_HASH = 1078189570L
        private val addCollisionExceptionWithBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody2D", "add_collision_exception_with", ADD_COLLISION_EXCEPTION_WITH_HASH)
        }

        private const val REMOVE_COLLISION_EXCEPTION_WITH_HASH = 1078189570L
        private val removeCollisionExceptionWithBind by lazy {
            ObjectCalls.getMethodBind("PhysicsBody2D", "remove_collision_exception_with", REMOVE_COLLISION_EXCEPTION_WITH_HASH)
        }
    }
}
