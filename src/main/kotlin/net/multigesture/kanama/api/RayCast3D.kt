package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A ray in 3D space, used to find the first collision object it intersects.
 *
 * Generated from Godot docs: RayCast3D
 */
class RayCast3D(handle: MemorySegment) : Node3D(handle) {
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

    var targetPosition: Vector3
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

    var hitBackFaces: Boolean
        @JvmName("hitBackFacesProperty")
        get() = isHitBackFacesEnabled()
        @JvmName("setHitBackFacesProperty")
        set(value) = setHitBackFaces(value)

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

    var debugShapeCustomColor: Color
        @JvmName("debugShapeCustomColorProperty")
        get() = getDebugShapeCustomColor()
        @JvmName("setDebugShapeCustomColorProperty")
        set(value) = setDebugShapeCustomColor(value)

    var debugShapeThickness: Int
        @JvmName("debugShapeThicknessProperty")
        get() = getDebugShapeThickness()
        @JvmName("setDebugShapeThicknessProperty")
        set(value) = setDebugShapeThickness(value)

    /**
     * If `true`, collisions will be reported.
     *
     * Generated from Godot docs: RayCast3D.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    /**
     * If `true`, collisions will be reported.
     *
     * Generated from Godot docs: RayCast3D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    /**
     * The ray's destination point, relative to this raycast's `Node3D.position`.
     *
     * Generated from Godot docs: RayCast3D.set_target_position
     */
    fun setTargetPosition(localPoint: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setTargetPositionBind, handle, localPoint)
    }

    /**
     * The ray's destination point, relative to this raycast's `Node3D.position`.
     *
     * Generated from Godot docs: RayCast3D.get_target_position
     */
    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTargetPositionBind, handle)
    }

    /**
     * Returns whether any object is intersecting with the ray's vector (considering the vector
     * length).
     *
     * Generated from Godot docs: RayCast3D.is_colliding
     */
    fun isColliding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollidingBind, handle)
    }

    /**
     * Updates the collision information for the ray immediately, without waiting for the next
     * `_physics_process` call. Use this method, for example, when the ray or its parent has changed
     * state. Note: `enabled` does not need to be `true` for this to work.
     *
     * Generated from Godot docs: RayCast3D.force_raycast_update
     */
    fun forceRaycastUpdate() {
        ObjectCalls.ptrcallNoArgs(forceRaycastUpdateBind, handle)
    }

    /**
     * Returns the first object that the ray intersects, or `null` if no object is intersecting the ray
     * (i.e. `is_colliding` returns `false`). Note: This object is not guaranteed to be a
     * `CollisionObject3D`. For example, if the ray intersects a `CSGShape3D` or a `GridMap`, the
     * method will return a `CSGShape3D` or `GridMap` instance.
     *
     * Generated from Godot docs: RayCast3D.get_collider
     */
    fun getCollider(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColliderBind, handle))
    }

    /**
     * Returns the `RID` of the first object that the ray intersects, or an empty `RID` if no object is
     * intersecting the ray (i.e. `is_colliding` returns `false`).
     *
     * Generated from Godot docs: RayCast3D.get_collider_rid
     */
    fun getColliderRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getColliderRidBind, handle)
    }

    /**
     * Returns the shape ID of the first object that the ray intersects, or `0` if no object is
     * intersecting the ray (i.e. `is_colliding` returns `false`). To get the intersected shape node,
     * for a `CollisionObject3D` target, use:
     *
     * Generated from Godot docs: RayCast3D.get_collider_shape
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
     * Generated from Godot docs: RayCast3D.get_collision_point
     */
    fun getCollisionPoint(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCollisionPointBind, handle)
    }

    /**
     * Returns the normal of the intersecting object's shape at the collision point, or `Vector3(0, 0,
     * 0)` if the ray starts inside the shape and `hit_from_inside` is `true`. Note: Check that
     * `is_colliding` returns `true` before calling this method to ensure the returned normal is valid
     * and up-to-date.
     *
     * Generated from Godot docs: RayCast3D.get_collision_normal
     */
    fun getCollisionNormal(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCollisionNormalBind, handle)
    }

    /**
     * Returns the collision object's face index at the collision point, or `-1` if the shape
     * intersecting the ray is not a `ConcavePolygonShape3D`.
     *
     * Generated from Godot docs: RayCast3D.get_collision_face_index
     */
    fun getCollisionFaceIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCollisionFaceIndexBind, handle)
    }

    /**
     * Adds a collision exception so the ray does not report collisions with the specified `RID`.
     *
     * Generated from Godot docs: RayCast3D.add_exception_rid
     */
    fun addExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(addExceptionRidBind, handle, rid)
    }

    /**
     * Adds a collision exception so the ray does not report collisions with the specified `node`.
     *
     * Generated from Godot docs: RayCast3D.add_exception
     */
    fun addException(node: CollisionObject3D) {
        ObjectCalls.ptrcallWithObjectArgs(addExceptionBind, handle, listOf(node.handle))
    }

    /**
     * Removes a collision exception so the ray can report collisions with the specified `RID`.
     *
     * Generated from Godot docs: RayCast3D.remove_exception_rid
     */
    fun removeExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(removeExceptionRidBind, handle, rid)
    }

    /**
     * Removes a collision exception so the ray can report collisions with the specified `node`.
     *
     * Generated from Godot docs: RayCast3D.remove_exception
     */
    fun removeException(node: CollisionObject3D) {
        ObjectCalls.ptrcallWithObjectArgs(removeExceptionBind, handle, listOf(node.handle))
    }

    /**
     * Removes all collision exceptions for this ray.
     *
     * Generated from Godot docs: RayCast3D.clear_exceptions
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
     * Generated from Godot docs: RayCast3D.set_collision_mask
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
     * Generated from Godot docs: RayCast3D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: RayCast3D.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: RayCast3D.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    /**
     * If `true`, this raycast will not report collisions with its parent node. This property only has
     * an effect if the parent node is a `CollisionObject3D`. See also `Node.get_parent` and
     * `add_exception`.
     *
     * Generated from Godot docs: RayCast3D.set_exclude_parent_body
     */
    fun setExcludeParentBody(mask: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExcludeParentBodyBind, handle, mask)
    }

    /**
     * If `true`, this raycast will not report collisions with its parent node. This property only has
     * an effect if the parent node is a `CollisionObject3D`. See also `Node.get_parent` and
     * `add_exception`.
     *
     * Generated from Godot docs: RayCast3D.get_exclude_parent_body
     */
    fun getExcludeParentBody(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getExcludeParentBodyBind, handle)
    }

    /**
     * If `true`, collisions with `Area3D`s will be reported.
     *
     * Generated from Godot docs: RayCast3D.set_collide_with_areas
     */
    fun setCollideWithAreas(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithAreasBind, handle, enable)
    }

    /**
     * If `true`, collisions with `Area3D`s will be reported.
     *
     * Generated from Godot docs: RayCast3D.is_collide_with_areas_enabled
     */
    fun isCollideWithAreasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithAreasEnabledBind, handle)
    }

    /**
     * If `true`, collisions with `PhysicsBody3D`s will be reported.
     *
     * Generated from Godot docs: RayCast3D.set_collide_with_bodies
     */
    fun setCollideWithBodies(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithBodiesBind, handle, enable)
    }

    /**
     * If `true`, collisions with `PhysicsBody3D`s will be reported.
     *
     * Generated from Godot docs: RayCast3D.is_collide_with_bodies_enabled
     */
    fun isCollideWithBodiesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithBodiesEnabledBind, handle)
    }

    /**
     * If `true`, the ray will detect a hit when starting inside shapes. In this case the collision
     * normal will be `Vector3(0, 0, 0)`. Does not affect shapes with no volume like concave polygon or
     * heightmap.
     *
     * Generated from Godot docs: RayCast3D.set_hit_from_inside
     */
    fun setHitFromInside(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHitFromInsideBind, handle, enable)
    }

    /**
     * If `true`, the ray will detect a hit when starting inside shapes. In this case the collision
     * normal will be `Vector3(0, 0, 0)`. Does not affect shapes with no volume like concave polygon or
     * heightmap.
     *
     * Generated from Godot docs: RayCast3D.is_hit_from_inside_enabled
     */
    fun isHitFromInsideEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHitFromInsideEnabledBind, handle)
    }

    /**
     * If `true`, the ray will hit back faces with concave polygon shapes with back face enabled or
     * heightmap shapes.
     *
     * Generated from Godot docs: RayCast3D.set_hit_back_faces
     */
    fun setHitBackFaces(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHitBackFacesBind, handle, enable)
    }

    /**
     * If `true`, the ray will hit back faces with concave polygon shapes with back face enabled or
     * heightmap shapes.
     *
     * Generated from Godot docs: RayCast3D.is_hit_back_faces_enabled
     */
    fun isHitBackFacesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHitBackFacesEnabledBind, handle)
    }

    /**
     * The custom color to use to draw the shape in the editor and at run-time if Visible Collision
     * Shapes is enabled in the Debug menu. This color will be highlighted at run-time if the
     * `RayCast3D` is colliding with something. If set to `Color(0.0, 0.0, 0.0)` (by default), the
     * color set in `ProjectSettings.debug/shapes/collision/shape_color` is used.
     *
     * Generated from Godot docs: RayCast3D.set_debug_shape_custom_color
     */
    fun setDebugShapeCustomColor(debugShapeCustomColor: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugShapeCustomColorBind, handle, debugShapeCustomColor)
    }

    /**
     * The custom color to use to draw the shape in the editor and at run-time if Visible Collision
     * Shapes is enabled in the Debug menu. This color will be highlighted at run-time if the
     * `RayCast3D` is colliding with something. If set to `Color(0.0, 0.0, 0.0)` (by default), the
     * color set in `ProjectSettings.debug/shapes/collision/shape_color` is used.
     *
     * Generated from Godot docs: RayCast3D.get_debug_shape_custom_color
     */
    fun getDebugShapeCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugShapeCustomColorBind, handle)
    }

    /**
     * If set to `1`, a line is used as the debug shape. Otherwise, a truncated pyramid is drawn to
     * represent the `RayCast3D`. Requires Visible Collision Shapes to be enabled in the Debug menu for
     * the debug shape to be visible at run-time.
     *
     * Generated from Godot docs: RayCast3D.set_debug_shape_thickness
     */
    fun setDebugShapeThickness(debugShapeThickness: Int) {
        ObjectCalls.ptrcallWithIntArg(setDebugShapeThicknessBind, handle, debugShapeThickness)
    }

    /**
     * If set to `1`, a line is used as the debug shape. Otherwise, a truncated pyramid is drawn to
     * represent the `RayCast3D`. Requires Visible Collision Shapes to be enabled in the Debug menu for
     * the debug shape to be visible at run-time.
     *
     * Generated from Godot docs: RayCast3D.get_debug_shape_thickness
     */
    fun getDebugShapeThickness(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDebugShapeThicknessBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RayCast3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RayCast3D? =
            if (handle.address() == 0L) null else RayCast3D(handle)

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_TARGET_POSITION_HASH = 3460891852L
        private val setTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_target_position", SET_TARGET_POSITION_HASH)
        }

        private const val GET_TARGET_POSITION_HASH = 3360562783L
        private val getTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_target_position", GET_TARGET_POSITION_HASH)
        }

        private const val IS_COLLIDING_HASH = 36873697L
        private val isCollidingBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "is_colliding", IS_COLLIDING_HASH)
        }

        private const val FORCE_RAYCAST_UPDATE_HASH = 3218959716L
        private val forceRaycastUpdateBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "force_raycast_update", FORCE_RAYCAST_UPDATE_HASH)
        }

        private const val GET_COLLIDER_HASH = 1981248198L
        private val getColliderBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_collider", GET_COLLIDER_HASH)
        }

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        private val getColliderRidBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_collider_rid", GET_COLLIDER_RID_HASH)
        }

        private const val GET_COLLIDER_SHAPE_HASH = 3905245786L
        private val getColliderShapeBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
        }

        private const val GET_COLLISION_POINT_HASH = 3360562783L
        private val getCollisionPointBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_point", GET_COLLISION_POINT_HASH)
        }

        private const val GET_COLLISION_NORMAL_HASH = 3360562783L
        private val getCollisionNormalBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)
        }

        private const val GET_COLLISION_FACE_INDEX_HASH = 3905245786L
        private val getCollisionFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_face_index", GET_COLLISION_FACE_INDEX_HASH)
        }

        private const val ADD_EXCEPTION_RID_HASH = 2722037293L
        private val addExceptionRidBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "add_exception_rid", ADD_EXCEPTION_RID_HASH)
        }

        private const val ADD_EXCEPTION_HASH = 1976431078L
        private val addExceptionBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "add_exception", ADD_EXCEPTION_HASH)
        }

        private const val REMOVE_EXCEPTION_RID_HASH = 2722037293L
        private val removeExceptionRidBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "remove_exception_rid", REMOVE_EXCEPTION_RID_HASH)
        }

        private const val REMOVE_EXCEPTION_HASH = 1976431078L
        private val removeExceptionBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "remove_exception", REMOVE_EXCEPTION_HASH)
        }

        private const val CLEAR_EXCEPTIONS_HASH = 3218959716L
        private val clearExceptionsBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "clear_exceptions", CLEAR_EXCEPTIONS_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_EXCLUDE_PARENT_BODY_HASH = 2586408642L
        private val setExcludeParentBodyBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_exclude_parent_body", SET_EXCLUDE_PARENT_BODY_HASH)
        }

        private const val GET_EXCLUDE_PARENT_BODY_HASH = 36873697L
        private val getExcludeParentBodyBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_exclude_parent_body", GET_EXCLUDE_PARENT_BODY_HASH)
        }

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        private val setCollideWithAreasBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)
        }

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        private val isCollideWithAreasEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
        }

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        private val setCollideWithBodiesBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)
        }

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        private val isCollideWithBodiesEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)
        }

        private const val SET_HIT_FROM_INSIDE_HASH = 2586408642L
        private val setHitFromInsideBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_hit_from_inside", SET_HIT_FROM_INSIDE_HASH)
        }

        private const val IS_HIT_FROM_INSIDE_ENABLED_HASH = 36873697L
        private val isHitFromInsideEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "is_hit_from_inside_enabled", IS_HIT_FROM_INSIDE_ENABLED_HASH)
        }

        private const val SET_HIT_BACK_FACES_HASH = 2586408642L
        private val setHitBackFacesBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_hit_back_faces", SET_HIT_BACK_FACES_HASH)
        }

        private const val IS_HIT_BACK_FACES_ENABLED_HASH = 36873697L
        private val isHitBackFacesEnabledBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "is_hit_back_faces_enabled", IS_HIT_BACK_FACES_ENABLED_HASH)
        }

        private const val SET_DEBUG_SHAPE_CUSTOM_COLOR_HASH = 2920490490L
        private val setDebugShapeCustomColorBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_debug_shape_custom_color", SET_DEBUG_SHAPE_CUSTOM_COLOR_HASH)
        }

        private const val GET_DEBUG_SHAPE_CUSTOM_COLOR_HASH = 3444240500L
        private val getDebugShapeCustomColorBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_debug_shape_custom_color", GET_DEBUG_SHAPE_CUSTOM_COLOR_HASH)
        }

        private const val SET_DEBUG_SHAPE_THICKNESS_HASH = 1286410249L
        private val setDebugShapeThicknessBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "set_debug_shape_thickness", SET_DEBUG_SHAPE_THICKNESS_HASH)
        }

        private const val GET_DEBUG_SHAPE_THICKNESS_HASH = 3905245786L
        private val getDebugShapeThicknessBind by lazy {
            ObjectCalls.getMethodBind("RayCast3D", "get_debug_shape_thickness", GET_DEBUG_SHAPE_THICKNESS_HASH)
        }
    }
}
