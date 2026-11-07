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
 * Stores the result of a ray intersection query.
 *
 * Generated from Godot docs: PhysicsIntersectRayResult3D
 */
class PhysicsIntersectRayResult3D(handle: GodotHandle) : RefCounted(handle) {
    val position: Vector3
        @JvmName("positionProperty")
        get() = getPosition()

    val normal: Vector3
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

    val colliderFaceIndex: Int
        @JvmName("colliderFaceIndexProperty")
        get() = getColliderFaceIndex()

    /**
     * The intersection point.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult3D.get_position
     */
    fun getPosition(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getPositionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The object's surface normal at the intersection point, or `Vector3(0, 0, 0)` if the ray starts
     * inside the shape and `PhysicsRayQueryParameters3D.hit_from_inside` is `true`.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult3D.get_normal
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
     * Generated from Godot docs: PhysicsIntersectRayResult3D.get_collider_rid
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
     * Generated from Godot docs: PhysicsIntersectRayResult3D.get_collider_id
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
     * Generated from Godot docs: PhysicsIntersectRayResult3D.get_collider
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
     * Generated from Godot docs: PhysicsIntersectRayResult3D.get_collider_shape
     */
    fun getColliderShape(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getColliderShapeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The face index at the intersection point. Note: Returns a valid number only if the intersected
     * shape is a `ConcavePolygonShape3D`. Otherwise, `-1` is returned.
     *
     * Generated from Godot docs: PhysicsIntersectRayResult3D.get_collider_face_index
     */
    fun getColliderFaceIndex(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getColliderFaceIndexBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsIntersectRayResult3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsIntersectRayResult3D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsIntersectRayResult3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsIntersectRayResult3D? =
            if (handle.address() == 0L) null else PhysicsIntersectRayResult3D(GodotHandle(handle))

        /** A new PhysicsIntersectRayResult3D, GDScript's `PhysicsIntersectRayResult3D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PhysicsIntersectRayResult3D =
            RefCounted.owned(PhysicsIntersectRayResult3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("PhysicsIntersectRayResult3D"), "PhysicsIntersectRayResult3D"))))
    }

    private object Binds {
        private const val GET_POSITION_HASH = 3360562783L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult3D", "get_position", GET_POSITION_HASH)

        private const val GET_NORMAL_HASH = 3360562783L
        @JvmField
        val getNormalBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult3D", "get_normal", GET_NORMAL_HASH)

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult3D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_ID_HASH = 3905245786L
        @JvmField
        val getColliderIdBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult3D", "get_collider_id", GET_COLLIDER_ID_HASH)

        private const val GET_COLLIDER_HASH = 1981248198L
        @JvmField
        val getColliderBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult3D", "get_collider", GET_COLLIDER_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 3905245786L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)

        private const val GET_COLLIDER_FACE_INDEX_HASH = 3905245786L
        @JvmField
        val getColliderFaceIndexBind =
            ObjectCalls.getMethodBind("PhysicsIntersectRayResult3D", "get_collider_face_index", GET_COLLIDER_FACE_INDEX_HASH)
    }
}
