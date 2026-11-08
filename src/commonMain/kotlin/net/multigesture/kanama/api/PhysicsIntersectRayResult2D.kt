package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Stores the result of a ray intersection query.
 *
 * Generated from Godot docs: PhysicsIntersectRayResult2D
 */
class PhysicsIntersectRayResult2D(handle: GodotHandle) : RefCounted(handle) {
    val position: Vector2
        @JvmName("positionProperty")
        get() = getPosition()

    val normal: Vector2
        @JvmName("normalProperty")
        get() = getNormal()

    val colliderRid: RID
        @JvmName("colliderRidProperty")
        get() = getColliderRid()

    val colliderId: Long
        @JvmName("colliderIdProperty")
        get() = getColliderId()

    val collider: GodotObject?
        @JvmName("colliderProperty")
        get() = getCollider()

    val colliderShape: Int
        @JvmName("colliderShapeProperty")
        get() = getColliderShape()

    /**
     * The intersection point.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult2D.get_position
     */
    fun getPosition(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getPositionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The object's surface normal at the intersection point, or `Vector2(0, 0)` if the ray starts
     * inside the shape and `PhysicsRayQueryParameters2D.hit_from_inside` is `true`.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult2D.get_normal
     */
    fun getNormal(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getNormalBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The intersecting object's `RID`.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult2D.get_collider_rid
     */
    fun getColliderRid(): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetRID(Binds.getColliderRidBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The colliding object's ID.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult2D.get_collider_id
     */
    fun getColliderId(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getColliderIdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The colliding object.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult2D.get_collider
     */
    fun getCollider(): GodotObject? {
        checkOpen()
        val ret = GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColliderBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The shape index of the colliding shape.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult2D.get_collider_shape
     */
    fun getColliderShape(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getColliderShapeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsIntersectRayResult2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsIntersectRayResult2D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsIntersectRayResult2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsIntersectRayResult2D? =
            if (handle.address() == 0L) null else PhysicsIntersectRayResult2D(GodotHandle(handle))

        /** A new PhysicsIntersectRayResult2D, GDScript's `PhysicsIntersectRayResult2D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PhysicsIntersectRayResult2D =
            RefCounted.owned(PhysicsIntersectRayResult2D(GodotHandle(requireConstructed(ObjectCalls.constructObject("PhysicsIntersectRayResult2D"), "PhysicsIntersectRayResult2D"))))
    }

    private object Binds {
        private const val GET_POSITION_HASH = 3341600327L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult2D", "get_position", GET_POSITION_HASH)

        private const val GET_NORMAL_HASH = 3341600327L
        @JvmField
        val getNormalBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult2D", "get_normal", GET_NORMAL_HASH)

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult2D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_ID_HASH = 3905245786L
        @JvmField
        val getColliderIdBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult2D", "get_collider_id", GET_COLLIDER_ID_HASH)

        private const val GET_COLLIDER_HASH = 1981248198L
        @JvmField
        val getColliderBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult2D", "get_collider", GET_COLLIDER_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 3905245786L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult2D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
    }
}
