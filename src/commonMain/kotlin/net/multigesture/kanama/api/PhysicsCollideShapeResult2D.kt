package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Vector2

/**
 * Stores the collision points of a shape collision query.
 *
 * Generated from Godot docs: PhysicsCollideShapeResult2D
 */
class PhysicsCollideShapeResult2D(handle: GodotHandle) : RefCounted(handle) {
    var maxCollisions: Int
        @JvmName("maxCollisionsProperty")
        get() = getMaxCollisions()
        @JvmName("setMaxCollisionsProperty")
        set(value) = setMaxCollisions(value)

    /**
     * The maximum number of collisions this object can store.
     *
     * Generated from Godot docs: PhysicsCollideShapeResult2D.get_max_collisions
     */
    fun getMaxCollisions(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxCollisionsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The maximum number of collisions this object can store.
     *
     * Generated from Godot docs: PhysicsCollideShapeResult2D.set_max_collisions
     */
    fun setMaxCollisions(maxCollisions: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxCollisionsBind, segment, maxCollisions)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the number of point pairs detected by the query.
     *
     * Generated from Godot docs: PhysicsCollideShapeResult2D.get_collision_count
     */
    fun getCollisionCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getCollisionCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the collision point on the query shape for the collision at the given index.
     *
     * Generated from Godot docs: PhysicsCollideShapeResult2D.get_point_on_queried_shape
     */
    fun getPointOnQueriedShape(collisionIndex: Int): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getPointOnQueriedShapeBind, segment, collisionIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the collision point on the colliding shape for the collision at the given index.
     *
     * Generated from Godot docs: PhysicsCollideShapeResult2D.get_point_on_colliding_shape
     */
    fun getPointOnCollidingShape(collisionIndex: Int): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getPointOnCollidingShapeBind, segment, collisionIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsCollideShapeResult2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsCollideShapeResult2D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsCollideShapeResult2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsCollideShapeResult2D? =
            if (handle.address() == 0L) null else PhysicsCollideShapeResult2D(GodotHandle(handle))

        /** A new PhysicsCollideShapeResult2D, GDScript's `PhysicsCollideShapeResult2D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PhysicsCollideShapeResult2D =
            RefCounted.owned(PhysicsCollideShapeResult2D(GodotHandle(requireConstructed(ObjectCalls.constructObject("PhysicsCollideShapeResult2D"), "PhysicsCollideShapeResult2D"))))
    }

    private object Binds {
        private const val GET_MAX_COLLISIONS_HASH = 3905245786L
        @JvmField
        val getMaxCollisionsBind =
            ObjectCalls.getMethodBind("PhysicsCollideShapeResult2D", "get_max_collisions", GET_MAX_COLLISIONS_HASH)

        private const val SET_MAX_COLLISIONS_HASH = 1286410249L
        @JvmField
        val setMaxCollisionsBind =
            ObjectCalls.getMethodBind("PhysicsCollideShapeResult2D", "set_max_collisions", SET_MAX_COLLISIONS_HASH)

        private const val GET_COLLISION_COUNT_HASH = 3905245786L
        @JvmField
        val getCollisionCountBind =
            ObjectCalls.getMethodBind("PhysicsCollideShapeResult2D", "get_collision_count", GET_COLLISION_COUNT_HASH)

        private const val GET_POINT_ON_QUERIED_SHAPE_HASH = 2299179447L
        @JvmField
        val getPointOnQueriedShapeBind =
            ObjectCalls.getMethodBind("PhysicsCollideShapeResult2D", "get_point_on_queried_shape", GET_POINT_ON_QUERIED_SHAPE_HASH)

        private const val GET_POINT_ON_COLLIDING_SHAPE_HASH = 2299179447L
        @JvmField
        val getPointOnCollidingShapeBind =
            ObjectCalls.getMethodBind("PhysicsCollideShapeResult2D", "get_point_on_colliding_shape", GET_POINT_ON_COLLIDING_SHAPE_HASH)
    }
}
