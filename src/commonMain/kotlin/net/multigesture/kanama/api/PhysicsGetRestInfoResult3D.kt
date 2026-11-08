package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * Stores the nearest collision information of a shape query.
 *
 * Generated from Godot docs: PhysicsGetRestInfoResult3D
 */
class PhysicsGetRestInfoResult3D(handle: GodotHandle) : RefCounted(handle) {
    val point: Vector3
        @JvmName("pointProperty")
        get() = getPoint()

    val normal: Vector3
        @JvmName("normalProperty")
        get() = getNormal()

    val colliderRid: RID
        @JvmName("colliderRidProperty")
        get() = getColliderRid()

    val colliderId: Long
        @JvmName("colliderIdProperty")
        get() = getColliderId()

    val colliderShape: Int
        @JvmName("colliderShapeProperty")
        get() = getColliderShape()

    val colliderVelocity: Vector3
        @JvmName("colliderVelocityProperty")
        get() = getColliderVelocity()

    /**
     * The intersection point.
     *
     * Generated from Godot docs: PhysicsGetRestInfoResult3D.get_point
     */
    fun getPoint(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getPointBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The collision normal of the query shape at the intersection point, pointing away from the
     * intersecting object.
     *
     * Generated from Godot docs: PhysicsGetRestInfoResult3D.get_normal
     */
    fun getNormal(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getNormalBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The intersecting object's `RID`.
     *
     * Generated from Godot docs: PhysicsGetRestInfoResult3D.get_collider_rid
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
     * Generated from Godot docs: PhysicsGetRestInfoResult3D.get_collider_id
     */
    fun getColliderId(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getColliderIdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The shape index of the colliding shape.
     *
     * Generated from Godot docs: PhysicsGetRestInfoResult3D.get_collider_shape
     */
    fun getColliderShape(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getColliderShapeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The colliding object's velocity `Vector3`. If the object is an `Area3D`, the result is `(0, 0,
     * 0)`.
     *
     * Generated from Godot docs: PhysicsGetRestInfoResult3D.get_collider_velocity
     */
    fun getColliderVelocity(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getColliderVelocityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsGetRestInfoResult3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsGetRestInfoResult3D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsGetRestInfoResult3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsGetRestInfoResult3D? =
            if (handle.address() == 0L) null else PhysicsGetRestInfoResult3D(GodotHandle(handle))

        /** A new PhysicsGetRestInfoResult3D, GDScript's `PhysicsGetRestInfoResult3D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PhysicsGetRestInfoResult3D =
            RefCounted.owned(PhysicsGetRestInfoResult3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("PhysicsGetRestInfoResult3D"), "PhysicsGetRestInfoResult3D"))))
    }

    private object Binds {
        private const val GET_POINT_HASH = 3360562783L
        @JvmField
        val getPointBind =
            ObjectCalls.getMethodBind("PhysicsGetRestInfoResult3D", "get_point", GET_POINT_HASH)

        private const val GET_NORMAL_HASH = 3360562783L
        @JvmField
        val getNormalBind =
            ObjectCalls.getMethodBind("PhysicsGetRestInfoResult3D", "get_normal", GET_NORMAL_HASH)

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("PhysicsGetRestInfoResult3D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_ID_HASH = 3905245786L
        @JvmField
        val getColliderIdBind =
            ObjectCalls.getMethodBind("PhysicsGetRestInfoResult3D", "get_collider_id", GET_COLLIDER_ID_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 3905245786L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("PhysicsGetRestInfoResult3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)

        private const val GET_COLLIDER_VELOCITY_HASH = 3360562783L
        @JvmField
        val getColliderVelocityBind =
            ObjectCalls.getMethodBind("PhysicsGetRestInfoResult3D", "get_collider_velocity", GET_COLLIDER_VELOCITY_HASH)
    }
}
