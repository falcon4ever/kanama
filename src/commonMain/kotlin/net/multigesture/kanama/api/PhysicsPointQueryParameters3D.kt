package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * Provides parameters for `PhysicsDirectSpaceState3D.intersect_point`.
 *
 * Generated from Godot docs: PhysicsPointQueryParameters3D
 */
class PhysicsPointQueryParameters3D(handle: GodotHandle) : RefCounted(handle) {
    var position: Vector3
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

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
     * The position being queried for, in global coordinates.
     *
     * Generated from Godot docs: PhysicsPointQueryParameters3D.set_position
     */
    fun setPosition(position: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setPositionBind, segment, position)
    }

    /**
     * The position being queried for, in global coordinates.
     *
     * Generated from Godot docs: PhysicsPointQueryParameters3D.get_position
     */
    fun getPosition(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getPositionBind, segment)
    }

    /**
     * The physics layers the query will detect (as a bitmask). By default, all collision layers are
     * detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: PhysicsPointQueryParameters3D.set_collision_mask
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
     * Generated from Godot docs: PhysicsPointQueryParameters3D.get_collision_mask
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
     * Generated from Godot docs: PhysicsPointQueryParameters3D.set_exclude
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
     * Generated from Godot docs: PhysicsPointQueryParameters3D.get_exclude
     */
    fun getExclude(): List<RID> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRIDList(Binds.getExcludeBind, segment)
    }

    /**
     * If `true`, the query will take `PhysicsBody3D`s into account.
     *
     * Generated from Godot docs: PhysicsPointQueryParameters3D.set_collide_with_bodies
     */
    fun setCollideWithBodies(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollideWithBodiesBind, segment, enable)
    }

    /**
     * If `true`, the query will take `PhysicsBody3D`s into account.
     *
     * Generated from Godot docs: PhysicsPointQueryParameters3D.is_collide_with_bodies_enabled
     */
    fun isCollideWithBodiesEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollideWithBodiesEnabledBind, segment)
    }

    /**
     * If `true`, the query will take `Area3D`s into account.
     *
     * Generated from Godot docs: PhysicsPointQueryParameters3D.set_collide_with_areas
     */
    fun setCollideWithAreas(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollideWithAreasBind, segment, enable)
    }

    /**
     * If `true`, the query will take `Area3D`s into account.
     *
     * Generated from Godot docs: PhysicsPointQueryParameters3D.is_collide_with_areas_enabled
     */
    fun isCollideWithAreasEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollideWithAreasEnabledBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsPointQueryParameters3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsPointQueryParameters3D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsPointQueryParameters3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsPointQueryParameters3D? =
            if (handle.address() == 0L) null else PhysicsPointQueryParameters3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_POSITION_HASH = 3460891852L
        @JvmField
        val setPositionBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "set_position", SET_POSITION_HASH)

        private const val GET_POSITION_HASH = 3360562783L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "get_position", GET_POSITION_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_EXCLUDE_HASH = 381264803L
        @JvmField
        val setExcludeBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "set_exclude", SET_EXCLUDE_HASH)

        private const val GET_EXCLUDE_HASH = 3995934104L
        @JvmField
        val getExcludeBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "get_exclude", GET_EXCLUDE_HASH)

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        @JvmField
        val setCollideWithBodiesBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        @JvmField
        val isCollideWithBodiesEnabledBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        @JvmField
        val setCollideWithAreasBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        @JvmField
        val isCollideWithAreasEnabledBind =
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters3D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
    }
}
