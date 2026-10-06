package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Provides parameters for `PhysicsDirectSpaceState3D`'s methods.
 *
 * Generated from Godot docs: PhysicsShapeQueryParameters3D
 */
class PhysicsShapeQueryParameters3D(handle: GodotHandle) : RefCounted(handle) {
    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var exclude: List<RID>
        @JvmName("excludeProperty")
        get() = getExclude()
        @JvmName("setExcludeProperty")
        set(value) = setExclude(value)

    var margin: Double
        @JvmName("marginProperty")
        get() = getMargin()
        @JvmName("setMarginProperty")
        set(value) = setMargin(value)

    var motion: Vector3
        @JvmName("motionProperty")
        get() = getMotion()
        @JvmName("setMotionProperty")
        set(value) = setMotion(value)

    var shape: Resource?
        @JvmName("shapeProperty")
        get() = getShape()
        @JvmName("setShapeProperty")
        set(value) = setShape(value)

    var shapeRid: RID
        @JvmName("shapeRidProperty")
        get() = getShapeRid()
        @JvmName("setShapeRidProperty")
        set(value) = setShapeRid(value)

    var transform: Transform3D
        @JvmName("transformProperty")
        get() = getTransform()
        @JvmName("setTransformProperty")
        set(value) = setTransform(value)

    var collideWithBodies: Boolean
        @JvmName("collideWithBodiesProperty")
        get() = isCollideWithBodiesEnabled()
        @JvmName("setCollideWithBodiesProperty")
        set(value) = setCollideWithBodies(value)

    var collideWithAreas: Boolean
        @JvmName("collideWithAreasProperty")
        get() = isCollideWithAreasEnabled()
        @JvmName("setCollideWithAreasProperty")
        set(value) = setCollideWithAreas(value)

    /**
     * The `Shape3D` that will be used for collision/intersection queries. This stores the actual
     * reference which avoids the shape to be released while being used for queries, so always prefer
     * using this over `shape_rid`.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_shape
     */
    fun setShape(shape: Resource?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setShapeBind, segment, listOf(shape?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Shape3D` that will be used for collision/intersection queries. This stores the actual
     * reference which avoids the shape to be released while being used for queries, so always prefer
     * using this over `shape_rid`.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_shape
     */
    fun getShape(): Resource? {
        checkOpen()
        return Resource.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getShapeBind, segment))
    }

    /**
     * The queried shape's `RID` that will be used for collision/intersection queries.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_shape_rid
     */
    fun setShapeRid(shape: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setShapeRidBind, segment, shape)
    }

    /**
     * The queried shape's `RID` that will be used for collision/intersection queries.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_shape_rid
     */
    fun getShapeRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getShapeRidBind, segment)
    }

    /**
     * The queried shape's transform matrix.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_transform
     */
    fun setTransform(transform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DArg(Binds.setTransformBind, segment, transform)
    }

    /**
     * The queried shape's transform matrix.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_transform
     */
    fun getTransform(): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getTransformBind, segment)
    }

    /**
     * The motion of the shape being queried for.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_motion
     */
    fun setMotion(motion: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setMotionBind, segment, motion)
    }

    /**
     * The motion of the shape being queried for.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_motion
     */
    fun getMotion(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getMotionBind, segment)
    }

    /**
     * The collision margin for the shape.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_margin
     */
    fun setMargin(margin: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMarginBind, segment, margin)
    }

    /**
     * The collision margin for the shape.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_margin
     */
    fun getMargin(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMarginBind, segment)
    }

    /**
     * The physics layers the query will detect (as a bitmask). By default, all collision layers are
     * detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_collision_mask
     */
    fun setCollisionMask(collisionMask: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, collisionMask)
    }

    /**
     * The physics layers the query will detect (as a bitmask). By default, all collision layers are
     * detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    /**
     * The list of object `RID`s that will be excluded from collisions. Use `CollisionObject3D.get_rid`
     * to get the `RID` associated with a `CollisionObject3D`-derived node. Note: The returned array is
     * copied and any changes to it will not update the original property value. To update the value
     * you need to modify the returned array, and then assign it to the property again.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_exclude
     */
    fun setExclude(exclude: List<RID>) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDListArg(Binds.setExcludeBind, segment, exclude)
    }

    /**
     * The list of object `RID`s that will be excluded from collisions. Use `CollisionObject3D.get_rid`
     * to get the `RID` associated with a `CollisionObject3D`-derived node. Note: The returned array is
     * copied and any changes to it will not update the original property value. To update the value
     * you need to modify the returned array, and then assign it to the property again.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_exclude
     */
    fun getExclude(): List<RID> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRIDList(Binds.getExcludeBind, segment)
    }

    /**
     * If `true`, the query will take `PhysicsBody3D`s into account.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_collide_with_bodies
     */
    fun setCollideWithBodies(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollideWithBodiesBind, segment, enable)
    }

    /**
     * If `true`, the query will take `PhysicsBody3D`s into account.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.is_collide_with_bodies_enabled
     */
    fun isCollideWithBodiesEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollideWithBodiesEnabledBind, segment)
    }

    /**
     * If `true`, the query will take `Area3D`s into account.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_collide_with_areas
     */
    fun setCollideWithAreas(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollideWithAreasBind, segment, enable)
    }

    /**
     * If `true`, the query will take `Area3D`s into account.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.is_collide_with_areas_enabled
     */
    fun isCollideWithAreasEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollideWithAreasEnabledBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsShapeQueryParameters3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsShapeQueryParameters3D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsShapeQueryParameters3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsShapeQueryParameters3D? =
            if (handle.address() == 0L) null else PhysicsShapeQueryParameters3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SHAPE_HASH = 968641751L
        @JvmField
        val setShapeBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_shape", SET_SHAPE_HASH)

        private const val GET_SHAPE_HASH = 121922552L
        @JvmField
        val getShapeBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_shape", GET_SHAPE_HASH)

        private const val SET_SHAPE_RID_HASH = 2722037293L
        @JvmField
        val setShapeRidBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_shape_rid", SET_SHAPE_RID_HASH)

        private const val GET_SHAPE_RID_HASH = 2944877500L
        @JvmField
        val getShapeRidBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_shape_rid", GET_SHAPE_RID_HASH)

        private const val SET_TRANSFORM_HASH = 2952846383L
        @JvmField
        val setTransformBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_transform", SET_TRANSFORM_HASH)

        private const val GET_TRANSFORM_HASH = 3229777777L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_transform", GET_TRANSFORM_HASH)

        private const val SET_MOTION_HASH = 3460891852L
        @JvmField
        val setMotionBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_motion", SET_MOTION_HASH)

        private const val GET_MOTION_HASH = 3360562783L
        @JvmField
        val getMotionBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_motion", GET_MOTION_HASH)

        private const val SET_MARGIN_HASH = 373806689L
        @JvmField
        val setMarginBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_margin", SET_MARGIN_HASH)

        private const val GET_MARGIN_HASH = 1740695150L
        @JvmField
        val getMarginBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_margin", GET_MARGIN_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_EXCLUDE_HASH = 381264803L
        @JvmField
        val setExcludeBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_exclude", SET_EXCLUDE_HASH)

        private const val GET_EXCLUDE_HASH = 3995934104L
        @JvmField
        val getExcludeBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_exclude", GET_EXCLUDE_HASH)

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        @JvmField
        val setCollideWithBodiesBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        @JvmField
        val isCollideWithBodiesEnabledBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        @JvmField
        val setCollideWithAreasBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        @JvmField
        val isCollideWithAreasEnabledBind =
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
    }
}
