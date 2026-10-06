package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A 3D shape that sweeps a region of space to detect `CollisionObject3D`s.
 *
 * Generated from Godot docs: ShapeCast3D
 */
class ShapeCast3D(handle: GodotHandle) : Node3D(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var shape: Shape3D?
        @JvmName("shapeProperty")
        get() = getShape()
        @JvmName("setShapeProperty")
        set(value) = setShape(value)

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

    var margin: Double
        @JvmName("marginProperty")
        get() = getMargin()
        @JvmName("setMarginProperty")
        set(value) = setMargin(value)

    var maxResults: Int
        @JvmName("maxResultsProperty")
        get() = getMaxResults()
        @JvmName("setMaxResultsProperty")
        set(value) = setMaxResults(value)

    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    val collisionResult: List<Any?>
        @JvmName("collisionResultProperty")
        get() = getCollisionResult()

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

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: ShapeCast3D.resource_changed
     */
    fun resourceChanged(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.resourceChangedBind, segment, listOf(resource?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * If `true`, collisions will be reported.
     *
     * Generated from Godot docs: ShapeCast3D.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enabled)
    }

    /**
     * If `true`, collisions will be reported.
     *
     * Generated from Godot docs: ShapeCast3D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    /**
     * The shape to be used for collision queries.
     *
     * Generated from Godot docs: ShapeCast3D.set_shape
     */
    fun setShape(shape: Shape3D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setShapeBind, segment, listOf(shape?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The shape to be used for collision queries.
     *
     * Generated from Godot docs: ShapeCast3D.get_shape
     */
    fun getShape(): Shape3D? {
        return Shape3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getShapeBind, segment))
    }

    /**
     * The shape's destination point, relative to this node's `Node3D.position`.
     *
     * Generated from Godot docs: ShapeCast3D.set_target_position
     */
    fun setTargetPosition(localPoint: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setTargetPositionBind, segment, localPoint)
    }

    /**
     * The shape's destination point, relative to this node's `Node3D.position`.
     *
     * Generated from Godot docs: ShapeCast3D.get_target_position
     */
    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getTargetPositionBind, segment)
    }

    /**
     * The collision margin for the shape. A larger margin helps detecting collisions more
     * consistently, at the cost of precision.
     *
     * Generated from Godot docs: ShapeCast3D.set_margin
     */
    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMarginBind, segment, margin)
    }

    /**
     * The collision margin for the shape. A larger margin helps detecting collisions more
     * consistently, at the cost of precision.
     *
     * Generated from Godot docs: ShapeCast3D.get_margin
     */
    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMarginBind, segment)
    }

    /**
     * The number of intersections can be limited with this parameter, to reduce the processing time.
     *
     * Generated from Godot docs: ShapeCast3D.set_max_results
     */
    fun setMaxResults(maxResults: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxResultsBind, segment, maxResults)
    }

    /**
     * The number of intersections can be limited with this parameter, to reduce the processing time.
     *
     * Generated from Godot docs: ShapeCast3D.get_max_results
     */
    fun getMaxResults(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxResultsBind, segment)
    }

    /**
     * Returns whether any object is intersecting with the shape's vector (considering the vector
     * length).
     *
     * Generated from Godot docs: ShapeCast3D.is_colliding
     */
    fun isColliding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollidingBind, segment)
    }

    /**
     * The number of collisions detected at the point of impact. Use this to iterate over multiple
     * collisions as provided by `get_collider`, `get_collider_shape`, `get_collision_point`, and
     * `get_collision_normal` methods.
     *
     * Generated from Godot docs: ShapeCast3D.get_collision_count
     */
    fun getCollisionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCollisionCountBind, segment)
    }

    /**
     * Updates the collision information for the shape immediately, without waiting for the next
     * `_physics_process` call. Use this method, for example, when the shape or its parent has changed
     * state. Note: Setting `enabled` to `true` is not required for this to work.
     *
     * Generated from Godot docs: ShapeCast3D.force_shapecast_update
     */
    fun forceShapecastUpdate() {
        ObjectCalls.ptrcallNoArgs(Binds.forceShapecastUpdateBind, segment)
    }

    /**
     * Returns the collided `Object` of one of the multiple collisions at `index`, or `null` if no
     * object is intersecting the shape (i.e. `is_colliding` returns `false`).
     *
     * Generated from Godot docs: ShapeCast3D.get_collider
     */
    fun getCollider(index: Int): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getColliderBind, segment, index))
    }

    /**
     * Returns the `RID` of the collided object of one of the multiple collisions at `index`.
     *
     * Generated from Godot docs: ShapeCast3D.get_collider_rid
     */
    fun getColliderRid(index: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(Binds.getColliderRidBind, segment, index)
    }

    /**
     * Returns the shape ID of the colliding shape of one of the multiple collisions at `index`, or `0`
     * if no object is intersecting the shape (i.e. `is_colliding` returns `false`).
     *
     * Generated from Godot docs: ShapeCast3D.get_collider_shape
     */
    fun getColliderShape(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getColliderShapeBind, segment, index)
    }

    /**
     * Returns the collision point of one of the multiple collisions at `index` where the shape
     * intersects the colliding object. Note: This point is in the global coordinate system.
     *
     * Generated from Godot docs: ShapeCast3D.get_collision_point
     */
    fun getCollisionPoint(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getCollisionPointBind, segment, index)
    }

    /**
     * Returns the normal of one of the multiple collisions at `index` of the intersecting object.
     *
     * Generated from Godot docs: ShapeCast3D.get_collision_normal
     */
    fun getCollisionNormal(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getCollisionNormalBind, segment, index)
    }

    /**
     * Returns the fraction from this cast's origin to its `target_position` of how far the shape can
     * move without triggering a collision, as a value between `0.0` and `1.0`.
     *
     * Generated from Godot docs: ShapeCast3D.get_closest_collision_safe_fraction
     */
    fun getClosestCollisionSafeFraction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getClosestCollisionSafeFractionBind, segment)
    }

    /**
     * Returns the fraction from this cast's origin to its `target_position` of how far the shape must
     * move to trigger a collision, as a value between `0.0` and `1.0`. In ideal conditions this would
     * be the same as `get_closest_collision_safe_fraction`, however shape casting is calculated in
     * discrete steps, so the precise point of collision can occur between two calculated positions.
     *
     * Generated from Godot docs: ShapeCast3D.get_closest_collision_unsafe_fraction
     */
    fun getClosestCollisionUnsafeFraction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getClosestCollisionUnsafeFractionBind, segment)
    }

    /**
     * Adds a collision exception so the shape does not report collisions with the specified `RID`.
     *
     * Generated from Godot docs: ShapeCast3D.add_exception_rid
     */
    fun addExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.addExceptionRidBind, segment, rid)
    }

    /**
     * Adds a collision exception so the shape does not report collisions with the specified node.
     *
     * Generated from Godot docs: ShapeCast3D.add_exception
     */
    fun addException(node: CollisionObject3D) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addExceptionBind, segment, listOf(node.segment))
    }

    /**
     * Removes a collision exception so the shape does report collisions with the specified `RID`.
     *
     * Generated from Godot docs: ShapeCast3D.remove_exception_rid
     */
    fun removeExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.removeExceptionRidBind, segment, rid)
    }

    /**
     * Removes a collision exception so the shape does report collisions with the specified node.
     *
     * Generated from Godot docs: ShapeCast3D.remove_exception
     */
    fun removeException(node: CollisionObject3D) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeExceptionBind, segment, listOf(node.segment))
    }

    /**
     * Removes all collision exceptions for this shape.
     *
     * Generated from Godot docs: ShapeCast3D.clear_exceptions
     */
    fun clearExceptions() {
        ObjectCalls.ptrcallNoArgs(Binds.clearExceptionsBind, segment)
    }

    /**
     * The shape's collision mask. Only objects in at least one collision layer enabled in the mask
     * will be detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: ShapeCast3D.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, mask)
    }

    /**
     * The shape's collision mask. Only objects in at least one collision layer enabled in the mask
     * will be detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: ShapeCast3D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: ShapeCast3D.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionMaskValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: ShapeCast3D.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionMaskValueBind, segment, layerNumber)
    }

    /**
     * If `true`, the parent node will be excluded from collision detection.
     *
     * Generated from Godot docs: ShapeCast3D.set_exclude_parent_body
     */
    fun setExcludeParentBody(mask: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExcludeParentBodyBind, segment, mask)
    }

    /**
     * If `true`, the parent node will be excluded from collision detection.
     *
     * Generated from Godot docs: ShapeCast3D.get_exclude_parent_body
     */
    fun getExcludeParentBody(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getExcludeParentBodyBind, segment)
    }

    /**
     * If `true`, collisions with `Area3D`s will be reported.
     *
     * Generated from Godot docs: ShapeCast3D.set_collide_with_areas
     */
    fun setCollideWithAreas(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollideWithAreasBind, segment, enable)
    }

    /**
     * If `true`, collisions with `Area3D`s will be reported.
     *
     * Generated from Godot docs: ShapeCast3D.is_collide_with_areas_enabled
     */
    fun isCollideWithAreasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollideWithAreasEnabledBind, segment)
    }

    /**
     * If `true`, collisions with `PhysicsBody3D`s will be reported.
     *
     * Generated from Godot docs: ShapeCast3D.set_collide_with_bodies
     */
    fun setCollideWithBodies(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollideWithBodiesBind, segment, enable)
    }

    /**
     * If `true`, collisions with `PhysicsBody3D`s will be reported.
     *
     * Generated from Godot docs: ShapeCast3D.is_collide_with_bodies_enabled
     */
    fun isCollideWithBodiesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollideWithBodiesEnabledBind, segment)
    }

    /**
     * Returns the complete collision information from the collision sweep. The data returned is the
     * same as in the `PhysicsDirectSpaceState3D.get_rest_info` method.
     *
     * Generated from Godot docs: ShapeCast3D.get_collision_result
     */
    fun getCollisionResult(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getCollisionResultBind, segment)
    }

    /**
     * The custom color to use to draw the shape in the editor and at run-time if Visible Collision
     * Shapes is enabled in the Debug menu. This color will be highlighted at run-time if the
     * `ShapeCast3D` is colliding with something. If set to `Color(0.0, 0.0, 0.0)` (by default), the
     * color set in `ProjectSettings.debug/shapes/collision/shape_color` is used.
     *
     * Generated from Godot docs: ShapeCast3D.set_debug_shape_custom_color
     */
    fun setDebugShapeCustomColor(debugShapeCustomColor: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setDebugShapeCustomColorBind, segment, debugShapeCustomColor)
    }

    /**
     * The custom color to use to draw the shape in the editor and at run-time if Visible Collision
     * Shapes is enabled in the Debug menu. This color will be highlighted at run-time if the
     * `ShapeCast3D` is colliding with something. If set to `Color(0.0, 0.0, 0.0)` (by default), the
     * color set in `ProjectSettings.debug/shapes/collision/shape_color` is used.
     *
     * Generated from Godot docs: ShapeCast3D.get_debug_shape_custom_color
     */
    fun getDebugShapeCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDebugShapeCustomColorBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ShapeCast3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ShapeCast3D? =
            if (handle.address() == 0L) null else ShapeCast3D(GodotHandle(handle))
    }

    private object Binds {
        private const val RESOURCE_CHANGED_HASH = 968641751L
        @JvmField
        val resourceChangedBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "resource_changed", RESOURCE_CHANGED_HASH)

        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_enabled", SET_ENABLED_HASH)

        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "is_enabled", IS_ENABLED_HASH)

        private const val SET_SHAPE_HASH = 1549710052L
        @JvmField
        val setShapeBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_shape", SET_SHAPE_HASH)

        private const val GET_SHAPE_HASH = 3214262478L
        @JvmField
        val getShapeBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_shape", GET_SHAPE_HASH)

        private const val SET_TARGET_POSITION_HASH = 3460891852L
        @JvmField
        val setTargetPositionBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_target_position", SET_TARGET_POSITION_HASH)

        private const val GET_TARGET_POSITION_HASH = 3360562783L
        @JvmField
        val getTargetPositionBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_target_position", GET_TARGET_POSITION_HASH)

        private const val SET_MARGIN_HASH = 373806689L
        @JvmField
        val setMarginBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_margin", SET_MARGIN_HASH)

        private const val GET_MARGIN_HASH = 1740695150L
        @JvmField
        val getMarginBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_margin", GET_MARGIN_HASH)

        private const val SET_MAX_RESULTS_HASH = 1286410249L
        @JvmField
        val setMaxResultsBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_max_results", SET_MAX_RESULTS_HASH)

        private const val GET_MAX_RESULTS_HASH = 3905245786L
        @JvmField
        val getMaxResultsBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_max_results", GET_MAX_RESULTS_HASH)

        private const val IS_COLLIDING_HASH = 36873697L
        @JvmField
        val isCollidingBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "is_colliding", IS_COLLIDING_HASH)

        private const val GET_COLLISION_COUNT_HASH = 3905245786L
        @JvmField
        val getCollisionCountBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_count", GET_COLLISION_COUNT_HASH)

        private const val FORCE_SHAPECAST_UPDATE_HASH = 3218959716L
        @JvmField
        val forceShapecastUpdateBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "force_shapecast_update", FORCE_SHAPECAST_UPDATE_HASH)

        private const val GET_COLLIDER_HASH = 3332903315L
        @JvmField
        val getColliderBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collider", GET_COLLIDER_HASH)

        private const val GET_COLLIDER_RID_HASH = 495598643L
        @JvmField
        val getColliderRidBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collider_rid", GET_COLLIDER_RID_HASH)

        private const val GET_COLLIDER_SHAPE_HASH = 923996154L
        @JvmField
        val getColliderShapeBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)

        private const val GET_COLLISION_POINT_HASH = 711720468L
        @JvmField
        val getCollisionPointBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_point", GET_COLLISION_POINT_HASH)

        private const val GET_COLLISION_NORMAL_HASH = 711720468L
        @JvmField
        val getCollisionNormalBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)

        private const val GET_CLOSEST_COLLISION_SAFE_FRACTION_HASH = 1740695150L
        @JvmField
        val getClosestCollisionSafeFractionBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_closest_collision_safe_fraction", GET_CLOSEST_COLLISION_SAFE_FRACTION_HASH)

        private const val GET_CLOSEST_COLLISION_UNSAFE_FRACTION_HASH = 1740695150L
        @JvmField
        val getClosestCollisionUnsafeFractionBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_closest_collision_unsafe_fraction", GET_CLOSEST_COLLISION_UNSAFE_FRACTION_HASH)

        private const val ADD_EXCEPTION_RID_HASH = 2722037293L
        @JvmField
        val addExceptionRidBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "add_exception_rid", ADD_EXCEPTION_RID_HASH)

        private const val ADD_EXCEPTION_HASH = 1976431078L
        @JvmField
        val addExceptionBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "add_exception", ADD_EXCEPTION_HASH)

        private const val REMOVE_EXCEPTION_RID_HASH = 2722037293L
        @JvmField
        val removeExceptionRidBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "remove_exception_rid", REMOVE_EXCEPTION_RID_HASH)

        private const val REMOVE_EXCEPTION_HASH = 1976431078L
        @JvmField
        val removeExceptionBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "remove_exception", REMOVE_EXCEPTION_HASH)

        private const val CLEAR_EXCEPTIONS_HASH = 3218959716L
        @JvmField
        val clearExceptionsBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "clear_exceptions", CLEAR_EXCEPTIONS_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionMaskValueBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionMaskValueBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)

        private const val SET_EXCLUDE_PARENT_BODY_HASH = 2586408642L
        @JvmField
        val setExcludeParentBodyBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_exclude_parent_body", SET_EXCLUDE_PARENT_BODY_HASH)

        private const val GET_EXCLUDE_PARENT_BODY_HASH = 36873697L
        @JvmField
        val getExcludeParentBodyBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_exclude_parent_body", GET_EXCLUDE_PARENT_BODY_HASH)

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        @JvmField
        val setCollideWithAreasBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        @JvmField
        val isCollideWithAreasEnabledBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        @JvmField
        val setCollideWithBodiesBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        @JvmField
        val isCollideWithBodiesEnabledBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)

        private const val GET_COLLISION_RESULT_HASH = 3995934104L
        @JvmField
        val getCollisionResultBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_result", GET_COLLISION_RESULT_HASH)

        private const val SET_DEBUG_SHAPE_CUSTOM_COLOR_HASH = 2920490490L
        @JvmField
        val setDebugShapeCustomColorBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "set_debug_shape_custom_color", SET_DEBUG_SHAPE_CUSTOM_COLOR_HASH)

        private const val GET_DEBUG_SHAPE_CUSTOM_COLOR_HASH = 3444240500L
        @JvmField
        val getDebugShapeCustomColorBind =
            ObjectCalls.getMethodBind("ShapeCast3D", "get_debug_shape_custom_color", GET_DEBUG_SHAPE_CUSTOM_COLOR_HASH)
    }
}
