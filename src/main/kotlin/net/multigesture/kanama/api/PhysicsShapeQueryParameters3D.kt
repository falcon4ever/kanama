package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Provides parameters for `PhysicsDirectSpaceState3D`'s methods.
 *
 * Generated from Godot docs: PhysicsShapeQueryParameters3D
 */
class PhysicsShapeQueryParameters3D(handle: MemorySegment) : RefCounted(handle) {
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
        ObjectCalls.ptrcallWithObjectArgs(setShapeBind, handle, listOf(shape?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Shape3D` that will be used for collision/intersection queries. This stores the actual
     * reference which avoids the shape to be released while being used for queries, so always prefer
     * using this over `shape_rid`.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_shape
     */
    fun getShape(): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShapeBind, handle))
    }

    /**
     * The queried shape's `RID` that will be used for collision/intersection queries. Use this over
     * `shape` if you want to optimize for performance using the Servers API:
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_shape_rid
     */
    fun setShapeRid(shape: RID) {
        ObjectCalls.ptrcallWithRIDArg(setShapeRidBind, handle, shape)
    }

    /**
     * The queried shape's `RID` that will be used for collision/intersection queries. Use this over
     * `shape` if you want to optimize for performance using the Servers API:
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_shape_rid
     */
    fun getShapeRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getShapeRidBind, handle)
    }

    /**
     * The queried shape's transform matrix.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_transform
     */
    fun setTransform(transform: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setTransformBind, handle, transform)
    }

    /**
     * The queried shape's transform matrix.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_transform
     */
    fun getTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getTransformBind, handle)
    }

    /**
     * The motion of the shape being queried for.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_motion
     */
    fun setMotion(motion: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setMotionBind, handle, motion)
    }

    /**
     * The motion of the shape being queried for.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_motion
     */
    fun getMotion(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getMotionBind, handle)
    }

    /**
     * The collision margin for the shape.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_margin
     */
    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, handle, margin)
    }

    /**
     * The collision margin for the shape.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.get_margin
     */
    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, handle)
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
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, collisionMask)
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
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
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
        ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
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
        return ObjectCalls.ptrcallNoArgsRetRIDList(getExcludeBind, handle)
    }

    /**
     * If `true`, the query will take `PhysicsBody3D`s into account.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_collide_with_bodies
     */
    fun setCollideWithBodies(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithBodiesBind, handle, enable)
    }

    /**
     * If `true`, the query will take `PhysicsBody3D`s into account.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.is_collide_with_bodies_enabled
     */
    fun isCollideWithBodiesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithBodiesEnabledBind, handle)
    }

    /**
     * If `true`, the query will take `Area3D`s into account.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.set_collide_with_areas
     */
    fun setCollideWithAreas(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithAreasBind, handle, enable)
    }

    /**
     * If `true`, the query will take `Area3D`s into account.
     *
     * Generated from Godot docs: PhysicsShapeQueryParameters3D.is_collide_with_areas_enabled
     */
    fun isCollideWithAreasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithAreasEnabledBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsShapeQueryParameters3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsShapeQueryParameters3D? =
            if (handle.address() == 0L) null else PhysicsShapeQueryParameters3D(handle)

        private const val SET_SHAPE_HASH = 968641751L
        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_shape", SET_SHAPE_HASH)
        }

        private const val GET_SHAPE_HASH = 121922552L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_shape", GET_SHAPE_HASH)
        }

        private const val SET_SHAPE_RID_HASH = 2722037293L
        private val setShapeRidBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_shape_rid", SET_SHAPE_RID_HASH)
        }

        private const val GET_SHAPE_RID_HASH = 2944877500L
        private val getShapeRidBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_shape_rid", GET_SHAPE_RID_HASH)
        }

        private const val SET_TRANSFORM_HASH = 2952846383L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3229777777L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val SET_MOTION_HASH = 3460891852L
        private val setMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_motion", SET_MOTION_HASH)
        }

        private const val GET_MOTION_HASH = 3360562783L
        private val getMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_motion", GET_MOTION_HASH)
        }

        private const val SET_MARGIN_HASH = 373806689L
        private val setMarginBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_margin", SET_MARGIN_HASH)
        }

        private const val GET_MARGIN_HASH = 1740695150L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_margin", GET_MARGIN_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_EXCLUDE_HASH = 381264803L
        private val setExcludeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_exclude", SET_EXCLUDE_HASH)
        }

        private const val GET_EXCLUDE_HASH = 3995934104L
        private val getExcludeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "get_exclude", GET_EXCLUDE_HASH)
        }

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        private val setCollideWithBodiesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)
        }

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        private val isCollideWithBodiesEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)
        }

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        private val setCollideWithAreasBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)
        }

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        private val isCollideWithAreasEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsShapeQueryParameters3D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
        }
    }
}
