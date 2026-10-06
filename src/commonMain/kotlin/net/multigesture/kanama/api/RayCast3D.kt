package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A ray in 3D space, used to find the first collision object it intersects.
 *
 * Generated from Godot docs: RayCast3D
 */
class RayCast3D(handle: GodotHandle) : Node3D(handle) {
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enabled)
    }

    /**
     * If `true`, collisions will be reported.
     *
     * Generated from Godot docs: RayCast3D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    /**
     * The ray's destination point, relative to this raycast's `Node3D.position`.
     *
     * Generated from Godot docs: RayCast3D.set_target_position
     */
    fun setTargetPosition(localPoint: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setTargetPositionBind, segment, localPoint)
    }

    /**
     * The ray's destination point, relative to this raycast's `Node3D.position`.
     *
     * Generated from Godot docs: RayCast3D.get_target_position
     */
    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getTargetPositionBind, segment)
    }

    /**
     * Returns whether any object is intersecting with the ray's vector (considering the vector
     * length).
     *
     * Generated from Godot docs: RayCast3D.is_colliding
     */
    fun isColliding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollidingBind, segment)
    }

    /**
     * Updates the collision information for the ray immediately, without waiting for the next
     * `_physics_process` call. Use this method, for example, when the ray or its parent has changed
     * state. Note: `enabled` does not need to be `true` for this to work.
     *
     * Generated from Godot docs: RayCast3D.force_raycast_update
     */
    fun forceRaycastUpdate() {
        ObjectCalls.ptrcallNoArgs(Binds.forceRaycastUpdateBind, segment)
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
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColliderBind, segment))
    }

    /**
     * Returns the `RID` of the first object that the ray intersects, or an empty `RID` if no object is
     * intersecting the ray (i.e. `is_colliding` returns `false`).
     *
     * Generated from Godot docs: RayCast3D.get_collider_rid
     */
    fun getColliderRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getColliderRidBind, segment)
    }

    /**
     * Returns the shape ID of the first object that the ray intersects, or `0` if no object is
     * intersecting the ray (i.e. `is_colliding` returns `false`).
     *
     * Generated from Godot docs: RayCast3D.get_collider_shape
     */
    fun getColliderShape(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getColliderShapeBind, segment)
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
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getCollisionPointBind, segment)
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
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getCollisionNormalBind, segment)
    }

    /**
     * Returns the collision object's face index at the collision point, or `-1` if the shape
     * intersecting the ray is not a `ConcavePolygonShape3D`.
     *
     * Generated from Godot docs: RayCast3D.get_collision_face_index
     */
    fun getCollisionFaceIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCollisionFaceIndexBind, segment)
    }

    /**
     * Adds a collision exception so the ray does not report collisions with the specified `RID`.
     *
     * Generated from Godot docs: RayCast3D.add_exception_rid
     */
    fun addExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.addExceptionRidBind, segment, rid)
    }

    /**
     * Adds a collision exception so the ray does not report collisions with the specified `node`.
     *
     * Generated from Godot docs: RayCast3D.add_exception
     */
    fun addException(node: CollisionObject3D) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addExceptionBind, segment, listOf(node.segment))
    }

    /**
     * Removes a collision exception so the ray can report collisions with the specified `RID`.
     *
     * Generated from Godot docs: RayCast3D.remove_exception_rid
     */
    fun removeExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.removeExceptionRidBind, segment, rid)
    }

    /**
     * Removes a collision exception so the ray can report collisions with the specified `node`.
     *
     * Generated from Godot docs: RayCast3D.remove_exception
     */
    fun removeException(node: CollisionObject3D) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeExceptionBind, segment, listOf(node.segment))
    }

    /**
     * Removes all collision exceptions for this ray.
     *
     * Generated from Godot docs: RayCast3D.clear_exceptions
     */
    fun clearExceptions() {
        ObjectCalls.ptrcallNoArgs(Binds.clearExceptionsBind, segment)
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
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, mask)
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
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: RayCast3D.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionMaskValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: RayCast3D.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionMaskValueBind, segment, layerNumber)
    }

    /**
     * If `true`, this raycast will not report collisions with its parent node. This property only has
     * an effect if the parent node is a `CollisionObject3D`. See also `Node.get_parent` and
     * `add_exception`.
     *
     * Generated from Godot docs: RayCast3D.set_exclude_parent_body
     */
    fun setExcludeParentBody(mask: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExcludeParentBodyBind, segment, mask)
    }

    /**
     * If `true`, this raycast will not report collisions with its parent node. This property only has
     * an effect if the parent node is a `CollisionObject3D`. See also `Node.get_parent` and
     * `add_exception`.
     *
     * Generated from Godot docs: RayCast3D.get_exclude_parent_body
     */
    fun getExcludeParentBody(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getExcludeParentBodyBind, segment)
    }

    /**
     * If `true`, collisions with `Area3D`s will be reported.
     *
     * Generated from Godot docs: RayCast3D.set_collide_with_areas
     */
    fun setCollideWithAreas(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollideWithAreasBind, segment, enable)
    }

    /**
     * If `true`, collisions with `Area3D`s will be reported.
     *
     * Generated from Godot docs: RayCast3D.is_collide_with_areas_enabled
     */
    fun isCollideWithAreasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollideWithAreasEnabledBind, segment)
    }

    /**
     * If `true`, collisions with `PhysicsBody3D`s will be reported.
     *
     * Generated from Godot docs: RayCast3D.set_collide_with_bodies
     */
    fun setCollideWithBodies(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollideWithBodiesBind, segment, enable)
    }

    /**
     * If `true`, collisions with `PhysicsBody3D`s will be reported.
     *
     * Generated from Godot docs: RayCast3D.is_collide_with_bodies_enabled
     */
    fun isCollideWithBodiesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollideWithBodiesEnabledBind, segment)
    }

    /**
     * If `true`, the ray will detect a hit when starting inside shapes. In this case the collision
     * normal will be `Vector3(0, 0, 0)`. Does not affect shapes with no volume like concave polygon or
     * heightmap.
     *
     * Generated from Godot docs: RayCast3D.set_hit_from_inside
     */
    fun setHitFromInside(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setHitFromInsideBind, segment, enable)
    }

    /**
     * If `true`, the ray will detect a hit when starting inside shapes. In this case the collision
     * normal will be `Vector3(0, 0, 0)`. Does not affect shapes with no volume like concave polygon or
     * heightmap.
     *
     * Generated from Godot docs: RayCast3D.is_hit_from_inside_enabled
     */
    fun isHitFromInsideEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isHitFromInsideEnabledBind, segment)
    }

    /**
     * If `true`, the ray will hit back faces with concave polygon shapes with back face enabled or
     * heightmap shapes.
     *
     * Generated from Godot docs: RayCast3D.set_hit_back_faces
     */
    fun setHitBackFaces(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setHitBackFacesBind, segment, enable)
    }

    /**
     * If `true`, the ray will hit back faces with concave polygon shapes with back face enabled or
     * heightmap shapes.
     *
     * Generated from Godot docs: RayCast3D.is_hit_back_faces_enabled
     */
    fun isHitBackFacesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isHitBackFacesEnabledBind, segment)
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
        ObjectCalls.ptrcallWithColorArg(Binds.setDebugShapeCustomColorBind, segment, debugShapeCustomColor)
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
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDebugShapeCustomColorBind, segment)
    }

    /**
     * If set to `1`, a line is used as the debug shape. Otherwise, a truncated pyramid is drawn to
     * represent the `RayCast3D`. Requires Visible Collision Shapes to be enabled in the Debug menu for
     * the debug shape to be visible at run-time.
     *
     * Generated from Godot docs: RayCast3D.set_debug_shape_thickness
     */
    fun setDebugShapeThickness(debugShapeThickness: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setDebugShapeThicknessBind, segment, debugShapeThickness)
    }

    /**
     * If set to `1`, a line is used as the debug shape. Otherwise, a truncated pyramid is drawn to
     * represent the `RayCast3D`. Requires Visible Collision Shapes to be enabled in the Debug menu for
     * the debug shape to be visible at run-time.
     *
     * Generated from Godot docs: RayCast3D.get_debug_shape_thickness
     */
    fun getDebugShapeThickness(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDebugShapeThicknessBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RayCast3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RayCast3D? =
            if (handle.address() == 0L) null else RayCast3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_enabled", SET_ENABLED_HASH)

        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("RayCast3D", "is_enabled", IS_ENABLED_HASH)

        private const val SET_TARGET_POSITION_HASH = 3460891852L
        @JvmField
        val setTargetPositionBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_target_position", SET_TARGET_POSITION_HASH)

        private const val GET_TARGET_POSITION_HASH = 3360562783L
        @JvmField
        val getTargetPositionBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_target_position", GET_TARGET_POSITION_HASH)

        private const val IS_COLLIDING_HASH = 36873697L
        @JvmField
        val isCollidingBind =
            ObjectCalls.getMethodBind("RayCast3D", "is_colliding", IS_COLLIDING_HASH)

        private const val FORCE_RAYCAST_UPDATE_HASH = 3218959716L
        @JvmField
        val forceRaycastUpdateBind =
            ObjectCalls.getMethodBind("RayCast3D", "force_raycast_update", FORCE_RAYCAST_UPDATE_HASH)

        private const val GET_COLLIDER_HASH = 1981248198L
        @JvmField
        val getColliderBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_collider", GET_COLLIDER_HASH)

        private const val GET_COLLIDER_RID_HASH = 2944877500L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 3905245786L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)

        private const val GET_COLLISION_POINT_HASH = 3360562783L
        @JvmField
        val getCollisionPointBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_point", GET_COLLISION_POINT_HASH)

        private const val GET_COLLISION_NORMAL_HASH = 3360562783L
        @JvmField
        val getCollisionNormalBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)

        private const val GET_COLLISION_FACE_INDEX_HASH = 3905245786L
        @JvmField
        val getCollisionFaceIndexBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_face_index", GET_COLLISION_FACE_INDEX_HASH)

        private const val ADD_EXCEPTION_RID_HASH = 2722037293L
        @JvmField
        val addExceptionRidBind =
            ObjectCalls.getMethodBind("RayCast3D", "add_exception_rid", ADD_EXCEPTION_RID_HASH)

        private const val ADD_EXCEPTION_HASH = 1976431078L
        @JvmField
        val addExceptionBind =
            ObjectCalls.getMethodBind("RayCast3D", "add_exception", ADD_EXCEPTION_HASH)

        private const val REMOVE_EXCEPTION_RID_HASH = 2722037293L
        @JvmField
        val removeExceptionRidBind =
            ObjectCalls.getMethodBind("RayCast3D", "remove_exception_rid", REMOVE_EXCEPTION_RID_HASH)

        private const val REMOVE_EXCEPTION_HASH = 1976431078L
        @JvmField
        val removeExceptionBind =
            ObjectCalls.getMethodBind("RayCast3D", "remove_exception", REMOVE_EXCEPTION_HASH)

        private const val CLEAR_EXCEPTIONS_HASH = 3218959716L
        @JvmField
        val clearExceptionsBind =
            ObjectCalls.getMethodBind("RayCast3D", "clear_exceptions", CLEAR_EXCEPTIONS_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionMaskValueBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionMaskValueBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)

        private const val SET_EXCLUDE_PARENT_BODY_HASH = 2586408642L
        @JvmField
        val setExcludeParentBodyBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_exclude_parent_body", SET_EXCLUDE_PARENT_BODY_HASH)

        private const val GET_EXCLUDE_PARENT_BODY_HASH = 36873697L
        @JvmField
        val getExcludeParentBodyBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_exclude_parent_body", GET_EXCLUDE_PARENT_BODY_HASH)

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        @JvmField
        val setCollideWithAreasBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        @JvmField
        val isCollideWithAreasEnabledBind =
            ObjectCalls.getMethodBind("RayCast3D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        @JvmField
        val setCollideWithBodiesBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        @JvmField
        val isCollideWithBodiesEnabledBind =
            ObjectCalls.getMethodBind("RayCast3D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)

        private const val SET_HIT_FROM_INSIDE_HASH = 2586408642L
        @JvmField
        val setHitFromInsideBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_hit_from_inside", SET_HIT_FROM_INSIDE_HASH)

        private const val IS_HIT_FROM_INSIDE_ENABLED_HASH = 36873697L
        @JvmField
        val isHitFromInsideEnabledBind =
            ObjectCalls.getMethodBind("RayCast3D", "is_hit_from_inside_enabled", IS_HIT_FROM_INSIDE_ENABLED_HASH)

        private const val SET_HIT_BACK_FACES_HASH = 2586408642L
        @JvmField
        val setHitBackFacesBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_hit_back_faces", SET_HIT_BACK_FACES_HASH)

        private const val IS_HIT_BACK_FACES_ENABLED_HASH = 36873697L
        @JvmField
        val isHitBackFacesEnabledBind =
            ObjectCalls.getMethodBind("RayCast3D", "is_hit_back_faces_enabled", IS_HIT_BACK_FACES_ENABLED_HASH)

        private const val SET_DEBUG_SHAPE_CUSTOM_COLOR_HASH = 2920490490L
        @JvmField
        val setDebugShapeCustomColorBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_debug_shape_custom_color", SET_DEBUG_SHAPE_CUSTOM_COLOR_HASH)

        private const val GET_DEBUG_SHAPE_CUSTOM_COLOR_HASH = 3444240500L
        @JvmField
        val getDebugShapeCustomColorBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_debug_shape_custom_color", GET_DEBUG_SHAPE_CUSTOM_COLOR_HASH)

        private const val SET_DEBUG_SHAPE_THICKNESS_HASH = 1286410249L
        @JvmField
        val setDebugShapeThicknessBind =
            ObjectCalls.getMethodBind("RayCast3D", "set_debug_shape_thickness", SET_DEBUG_SHAPE_THICKNESS_HASH)

        private const val GET_DEBUG_SHAPE_THICKNESS_HASH = 3905245786L
        @JvmField
        val getDebugShapeThicknessBind =
            ObjectCalls.getMethodBind("RayCast3D", "get_debug_shape_thickness", GET_DEBUG_SHAPE_THICKNESS_HASH)
    }
}
