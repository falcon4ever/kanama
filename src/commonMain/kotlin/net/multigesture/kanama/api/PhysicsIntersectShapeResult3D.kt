package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.RID

/**
 * Stores the results of a shape intersection query.
 *
 * Generated from Godot docs: PhysicsIntersectShapeResult3D
 */
class PhysicsIntersectShapeResult3D(handle: GodotHandle) : RefCounted(handle) {
    var maxIntersections: Int
        @JvmName("maxIntersectionsProperty")
        get() = getMaxIntersections()
        @JvmName("setMaxIntersectionsProperty")
        set(value) = setMaxIntersections(value)

    /**
     * The maximum number of intersections this result object can store.
     *
     * Generated from Godot docs: PhysicsIntersectShapeResult3D.get_max_intersections
     */
    fun getMaxIntersections(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxIntersectionsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The maximum number of intersections this result object can store.
     *
     * Generated from Godot docs: PhysicsIntersectShapeResult3D.set_max_intersections
     */
    fun setMaxIntersections(maxIntersections: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxIntersectionsBind, segment, maxIntersections)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the number of shapes that intersect with the query shape.
     *
     * Generated from Godot docs: PhysicsIntersectShapeResult3D.get_intersection_count
     */
    fun getIntersectionCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getIntersectionCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The colliding object's ID.
     *
     * Generated from Godot docs: PhysicsIntersectShapeResult3D.get_collider_id
     */
    fun getColliderId(intersectionIndex: Int): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetLong(Binds.getColliderIdBind, segment, intersectionIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The intersecting object's `RID`.
     *
     * Generated from Godot docs: PhysicsIntersectShapeResult3D.get_collider_rid
     */
    fun getColliderRid(intersectionIndex: Int): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetRID(Binds.getColliderRidBind, segment, intersectionIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The colliding object.
     *
     * Generated from Godot docs: PhysicsIntersectShapeResult3D.get_collider
     */
    fun getCollider(intersectionIndex: Int): GodotObject? {
        checkOpen()
        val ret = GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getColliderBind, segment, intersectionIndex))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The shape index of the colliding shape.
     *
     * Generated from Godot docs: PhysicsIntersectShapeResult3D.get_collider_shape
     */
    fun getColliderShape(intersectionIndex: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetInt(Binds.getColliderShapeBind, segment, intersectionIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsIntersectShapeResult3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsIntersectShapeResult3D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsIntersectShapeResult3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsIntersectShapeResult3D? =
            if (handle.address() == 0L) null else PhysicsIntersectShapeResult3D(GodotHandle(handle))

        /** A new PhysicsIntersectShapeResult3D, GDScript's `PhysicsIntersectShapeResult3D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PhysicsIntersectShapeResult3D =
            RefCounted.owned(PhysicsIntersectShapeResult3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("PhysicsIntersectShapeResult3D"), "PhysicsIntersectShapeResult3D"))))
    }

    private object Binds {
        private const val GET_MAX_INTERSECTIONS_HASH = 3905245786L
        @JvmField
        val getMaxIntersectionsBind =
            ObjectCalls.getMethodBind("PhysicsIntersectShapeResult3D", "get_max_intersections", GET_MAX_INTERSECTIONS_HASH)

        private const val SET_MAX_INTERSECTIONS_HASH = 1286410249L
        @JvmField
        val setMaxIntersectionsBind =
            ObjectCalls.getMethodBind("PhysicsIntersectShapeResult3D", "set_max_intersections", SET_MAX_INTERSECTIONS_HASH)

        private const val GET_INTERSECTION_COUNT_HASH = 3905245786L
        @JvmField
        val getIntersectionCountBind =
            ObjectCalls.getMethodBind("PhysicsIntersectShapeResult3D", "get_intersection_count", GET_INTERSECTION_COUNT_HASH)

        private const val GET_COLLIDER_ID_HASH = 923996154L
        @JvmField
        val getColliderIdBind =
            ObjectCalls.getMethodBind("PhysicsIntersectShapeResult3D", "get_collider_id", GET_COLLIDER_ID_HASH)

        private const val GET_COLLIDER_RID_HASH = 495598643L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("PhysicsIntersectShapeResult3D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_HASH = 3332903315L
        @JvmField
        val getColliderBind =
            ObjectCalls.getMethodBind("PhysicsIntersectShapeResult3D", "get_collider", GET_COLLIDER_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 923996154L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("PhysicsIntersectShapeResult3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
    }
}
