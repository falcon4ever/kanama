package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * A ray in 2D space, used to find the first collision object it intersects.
 *
 * Generated from Godot docs: RayCast2D
 */
class RayCast2D(handle: MemorySegment) : Node2D(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var excludeParent: Boolean
        @JvmName("excludeParentProperty")
        get() = getExcludeParentBody()
        @JvmName("setExcludeParentProperty")
        set(value) = setExcludeParentBody(value)

    var targetPosition: Vector2
        @JvmName("targetPositionProperty")
        get() = getTargetPosition()
        @JvmName("setTargetPositionProperty")
        set(value) = setTargetPosition(value)

    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var hitFromInside: Boolean
        @JvmName("hitFromInsideProperty")
        get() = isHitFromInsideEnabled()
        @JvmName("setHitFromInsideProperty")
        set(value) = setHitFromInside(value)

    var collideWithAreas: Boolean
        @JvmName("collideWithAreasProperty")
        get() = isCollideWithAreasEnabled()
        @JvmName("setCollideWithAreasProperty")
        set(value) = setCollideWithAreas(value)

    var collideWithBodies: Boolean
        @JvmName("collideWithBodiesProperty")
        get() = isCollideWithBodiesEnabled()
        @JvmName("setCollideWithBodiesProperty")
        set(value) = setCollideWithBodies(value)

    /**
     * If `true`, collisions will be reported.
     *
     * Generated from Godot docs: RayCast2D.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    /**
     * If `true`, collisions will be reported.
     *
     * Generated from Godot docs: RayCast2D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    /**
     * The ray's destination point, relative to this raycast's `Node2D.position`.
     *
     * Generated from Godot docs: RayCast2D.set_target_position
     */
    fun setTargetPosition(localPoint: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTargetPositionBind, handle, localPoint)
    }

    /**
     * The ray's destination point, relative to this raycast's `Node2D.position`.
     *
     * Generated from Godot docs: RayCast2D.get_target_position
     */
    fun getTargetPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTargetPositionBind, handle)
    }

    /**
     * Returns whether any object is intersecting with the ray's vector (considering the vector
     * length).
     *
     * Generated from Godot docs: RayCast2D.is_colliding
     */
    fun isColliding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollidingBind, handle)
    }

    /**
     * Updates the collision information for the ray immediately, without waiting for the next
     * `_physics_process` call. Use this method, for example, when the ray or its parent has changed
     * state. Note: `enabled` does not need to be `true` for this to work.
     *
     * Generated from Godot docs: RayCast2D.force_raycast_update
     */
    fun forceRaycastUpdate() {
        ObjectCalls.ptrcallNoArgs(forceRaycastUpdateBind, handle)
    }

    /**
     * Returns the first object that the ray intersects, or `null` if no object is intersecting the ray
     * (i.e. `is_colliding` returns `false`). Note: This object is not guaranteed to be a
     * `CollisionObject2D`. For example, if the ray intersects a `TileMapLayer`, the method will return
     * a `TileMapLayer` instance.
     *
     * Generated from Godot docs: RayCast2D.get_collider
     */
    fun getCollider(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColliderBind, handle))
    }

    /**
     * Returns the `RID` of the first object that the ray intersects, or an empty `RID` if no object is
     * intersecting the ray (i.e. `is_colliding` returns `false`).
     *
     * Generated from Godot docs: RayCast2D.get_collider_rid
     */
    fun getColliderRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getColliderRidBind, handle)
    }

    /**
     * Returns the shape ID of the first object that the ray intersects, or `0` if no object is
     * intersecting the ray (i.e. `is_colliding` returns `false`). To get the intersected shape node,
     * for a `CollisionObject2D` target, use:
     *
     * Generated from Godot docs: RayCast2D.get_collider_shape
     */
    fun getColliderShape(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getColliderShapeBind, handle)
    }

    /**
     * Returns the collision point at which the ray intersects the closest object, in the global
     * coordinate system. If `hit_from_inside` is `true` and the ray starts inside of a collision
     * shape, this function will return the origin point of the ray. Note: Check that `is_colliding`
     * returns `true` before calling this method to ensure the returned point is valid and up-to-date.
     *
     * Generated from Godot docs: RayCast2D.get_collision_point
     */
    fun getCollisionPoint(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCollisionPointBind, handle)
    }

    /**
     * Returns the normal of the intersecting object's shape at the collision point, or `Vector2(0, 0)`
     * if the ray starts inside the shape and `hit_from_inside` is `true`. Note: Check that
     * `is_colliding` returns `true` before calling this method to ensure the returned normal is valid
     * and up-to-date.
     *
     * Generated from Godot docs: RayCast2D.get_collision_normal
     */
    fun getCollisionNormal(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCollisionNormalBind, handle)
    }

    /**
     * Adds a collision exception so the ray does not report collisions with the specified `RID`.
     *
     * Generated from Godot docs: RayCast2D.add_exception_rid
     */
    fun addExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(addExceptionRidBind, handle, rid)
    }

    /**
     * Adds a collision exception so the ray does not report collisions with the specified `node`.
     *
     * Generated from Godot docs: RayCast2D.add_exception
     */
    fun addException(node: CollisionObject2D) {
        ObjectCalls.ptrcallWithObjectArgs(addExceptionBind, handle, listOf(node.handle))
    }

    /**
     * Removes a collision exception so the ray can report collisions with the specified `RID`.
     *
     * Generated from Godot docs: RayCast2D.remove_exception_rid
     */
    fun removeExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(removeExceptionRidBind, handle, rid)
    }

    /**
     * Removes a collision exception so the ray can report collisions with the specified `node`.
     *
     * Generated from Godot docs: RayCast2D.remove_exception
     */
    fun removeException(node: CollisionObject2D) {
        ObjectCalls.ptrcallWithObjectArgs(removeExceptionBind, handle, listOf(node.handle))
    }

    /**
     * Removes all collision exceptions for this ray.
     *
     * Generated from Godot docs: RayCast2D.clear_exceptions
     */
    fun clearExceptions() {
        ObjectCalls.ptrcallNoArgs(clearExceptionsBind, handle)
    }

    /**
     * The ray's collision mask. Only objects in at least one collision layer enabled in the mask will
     * be detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: RayCast2D.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    /**
     * The ray's collision mask. Only objects in at least one collision layer enabled in the mask will
     * be detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: RayCast2D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: RayCast2D.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: RayCast2D.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    /**
     * If `true`, this raycast will not report collisions with its parent node. This property only has
     * an effect if the parent node is a `CollisionObject2D`. See also `Node.get_parent` and
     * `add_exception`.
     *
     * Generated from Godot docs: RayCast2D.set_exclude_parent_body
     */
    fun setExcludeParentBody(mask: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExcludeParentBodyBind, handle, mask)
    }

    /**
     * If `true`, this raycast will not report collisions with its parent node. This property only has
     * an effect if the parent node is a `CollisionObject2D`. See also `Node.get_parent` and
     * `add_exception`.
     *
     * Generated from Godot docs: RayCast2D.get_exclude_parent_body
     */
    fun getExcludeParentBody(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getExcludeParentBodyBind, handle)
    }

    /**
     * If `true`, collisions with `Area2D`s will be reported.
     *
     * Generated from Godot docs: RayCast2D.set_collide_with_areas
     */
    fun setCollideWithAreas(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithAreasBind, handle, enable)
    }

    /**
     * If `true`, collisions with `Area2D`s will be reported.
     *
     * Generated from Godot docs: RayCast2D.is_collide_with_areas_enabled
     */
    fun isCollideWithAreasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithAreasEnabledBind, handle)
    }

    /**
     * If `true`, collisions with `PhysicsBody2D`s will be reported.
     *
     * Generated from Godot docs: RayCast2D.set_collide_with_bodies
     */
    fun setCollideWithBodies(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithBodiesBind, handle, enable)
    }

    /**
     * If `true`, collisions with `PhysicsBody2D`s will be reported.
     *
     * Generated from Godot docs: RayCast2D.is_collide_with_bodies_enabled
     */
    fun isCollideWithBodiesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithBodiesEnabledBind, handle)
    }

    /**
     * If `true`, the ray will detect a hit when starting inside shapes. In this case the collision
     * normal will be `Vector2(0, 0)`. Does not affect concave polygon shapes.
     *
     * Generated from Godot docs: RayCast2D.set_hit_from_inside
     */
    fun setHitFromInside(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHitFromInsideBind, handle, enable)
    }

    /**
     * If `true`, the ray will detect a hit when starting inside shapes. In this case the collision
     * normal will be `Vector2(0, 0)`. Does not affect concave polygon shapes.
     *
     * Generated from Godot docs: RayCast2D.is_hit_from_inside_enabled
     */
    fun isHitFromInsideEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHitFromInsideEnabledBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RayCast2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RayCast2D? =
            if (handle.address() == 0L) null else RayCast2D(handle)

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_TARGET_POSITION_HASH = 743155724L
        private val setTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "set_target_position", SET_TARGET_POSITION_HASH)
        }

        private const val GET_TARGET_POSITION_HASH = 3341600327L
        private val getTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_target_position", GET_TARGET_POSITION_HASH)
        }

        private const val IS_COLLIDING_HASH = 36873697L
        private val isCollidingBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "is_colliding", IS_COLLIDING_HASH)
        }

        private const val FORCE_RAYCAST_UPDATE_HASH = 3218959716L
        private val forceRaycastUpdateBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "force_raycast_update", FORCE_RAYCAST_UPDATE_HASH)
        }

        private const val GET_COLLIDER_HASH = 1981248198L
        private val getColliderBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_collider", GET_COLLIDER_HASH)
        }

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        private val getColliderRidBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_collider_rid", GET_COLLIDER_RID_HASH)
        }

        private const val GET_COLLIDER_SHAPE_HASH = 3905245786L
        private val getColliderShapeBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
        }

        private const val GET_COLLISION_POINT_HASH = 3341600327L
        private val getCollisionPointBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_collision_point", GET_COLLISION_POINT_HASH)
        }

        private const val GET_COLLISION_NORMAL_HASH = 3341600327L
        private val getCollisionNormalBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)
        }

        private const val ADD_EXCEPTION_RID_HASH = 2722037293L
        private val addExceptionRidBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "add_exception_rid", ADD_EXCEPTION_RID_HASH)
        }

        private const val ADD_EXCEPTION_HASH = 3090941106L
        private val addExceptionBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "add_exception", ADD_EXCEPTION_HASH)
        }

        private const val REMOVE_EXCEPTION_RID_HASH = 2722037293L
        private val removeExceptionRidBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "remove_exception_rid", REMOVE_EXCEPTION_RID_HASH)
        }

        private const val REMOVE_EXCEPTION_HASH = 3090941106L
        private val removeExceptionBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "remove_exception", REMOVE_EXCEPTION_HASH)
        }

        private const val CLEAR_EXCEPTIONS_HASH = 3218959716L
        private val clearExceptionsBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "clear_exceptions", CLEAR_EXCEPTIONS_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_EXCLUDE_PARENT_BODY_HASH = 2586408642L
        private val setExcludeParentBodyBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "set_exclude_parent_body", SET_EXCLUDE_PARENT_BODY_HASH)
        }

        private const val GET_EXCLUDE_PARENT_BODY_HASH = 36873697L
        private val getExcludeParentBodyBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "get_exclude_parent_body", GET_EXCLUDE_PARENT_BODY_HASH)
        }

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        private val setCollideWithAreasBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)
        }

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        private val isCollideWithAreasEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
        }

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        private val setCollideWithBodiesBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)
        }

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        private val isCollideWithBodiesEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)
        }

        private const val SET_HIT_FROM_INSIDE_HASH = 2586408642L
        private val setHitFromInsideBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "set_hit_from_inside", SET_HIT_FROM_INSIDE_HASH)
        }

        private const val IS_HIT_FROM_INSIDE_ENABLED_HASH = 36873697L
        private val isHitFromInsideEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast2D", "is_hit_from_inside_enabled", IS_HIT_FROM_INSIDE_ENABLED_HASH)
        }
    }
}
