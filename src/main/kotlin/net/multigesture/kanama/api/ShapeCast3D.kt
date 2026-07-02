package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A 3D shape that sweeps a region of space to detect `CollisionObject3D`s.
 *
 * Generated from Godot docs: ShapeCast3D
 */
class ShapeCast3D(handle: MemorySegment) : Node3D(handle) {
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

    fun resourceChanged(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(resourceChangedBind, handle, listOf(resource?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    fun setShape(shape: Shape3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setShapeBind, handle, listOf(shape?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShape(): Shape3D? {
        return Shape3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShapeBind, handle))
    }

    fun setTargetPosition(localPoint: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setTargetPositionBind, handle, localPoint)
    }

    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTargetPositionBind, handle)
    }

    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, handle, margin)
    }

    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, handle)
    }

    fun setMaxResults(maxResults: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxResultsBind, handle, maxResults)
    }

    fun getMaxResults(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxResultsBind, handle)
    }

    fun isColliding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollidingBind, handle)
    }

    fun getCollisionCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionCountBind, handle)
    }

    fun forceShapecastUpdate() {
        ObjectCalls.ptrcallNoArgs(forceShapecastUpdateBind, handle)
    }

    fun getCollider(index: Int): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getColliderBind, handle, index))
    }

    fun getColliderRid(index: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getColliderRidBind, handle, index)
    }

    fun getColliderShape(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getColliderShapeBind, handle, index)
    }

    fun getCollisionPoint(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getCollisionPointBind, handle, index)
    }

    fun getCollisionNormal(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getCollisionNormalBind, handle, index)
    }

    fun getClosestCollisionSafeFraction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getClosestCollisionSafeFractionBind, handle)
    }

    fun getClosestCollisionUnsafeFraction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getClosestCollisionUnsafeFractionBind, handle)
    }

    fun addExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(addExceptionRidBind, handle, rid)
    }

    fun addException(node: CollisionObject3D) {
        ObjectCalls.ptrcallWithObjectArgs(addExceptionBind, handle, listOf(node.handle))
    }

    fun removeExceptionRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(removeExceptionRidBind, handle, rid)
    }

    fun removeException(node: CollisionObject3D) {
        ObjectCalls.ptrcallWithObjectArgs(removeExceptionBind, handle, listOf(node.handle))
    }

    fun clearExceptions() {
        ObjectCalls.ptrcallNoArgs(clearExceptionsBind, handle)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    fun setExcludeParentBody(mask: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExcludeParentBodyBind, handle, mask)
    }

    fun getExcludeParentBody(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getExcludeParentBodyBind, handle)
    }

    fun setCollideWithAreas(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithAreasBind, handle, enable)
    }

    fun isCollideWithAreasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithAreasEnabledBind, handle)
    }

    fun setCollideWithBodies(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithBodiesBind, handle, enable)
    }

    fun isCollideWithBodiesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithBodiesEnabledBind, handle)
    }

    fun getCollisionResult(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getCollisionResultBind, handle)
    }

    fun setDebugShapeCustomColor(debugShapeCustomColor: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugShapeCustomColorBind, handle, debugShapeCustomColor)
    }

    fun getDebugShapeCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugShapeCustomColorBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ShapeCast3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ShapeCast3D? =
            if (handle.address() == 0L) null else ShapeCast3D(handle)

        private const val RESOURCE_CHANGED_HASH = 968641751L
        private val resourceChangedBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "resource_changed", RESOURCE_CHANGED_HASH)
        }

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_SHAPE_HASH = 1549710052L
        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_shape", SET_SHAPE_HASH)
        }

        private const val GET_SHAPE_HASH = 3214262478L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_shape", GET_SHAPE_HASH)
        }

        private const val SET_TARGET_POSITION_HASH = 3460891852L
        private val setTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_target_position", SET_TARGET_POSITION_HASH)
        }

        private const val GET_TARGET_POSITION_HASH = 3360562783L
        private val getTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_target_position", GET_TARGET_POSITION_HASH)
        }

        private const val SET_MARGIN_HASH = 373806689L
        private val setMarginBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_margin", SET_MARGIN_HASH)
        }

        private const val GET_MARGIN_HASH = 1740695150L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_margin", GET_MARGIN_HASH)
        }

        private const val SET_MAX_RESULTS_HASH = 1286410249L
        private val setMaxResultsBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_max_results", SET_MAX_RESULTS_HASH)
        }

        private const val GET_MAX_RESULTS_HASH = 3905245786L
        private val getMaxResultsBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_max_results", GET_MAX_RESULTS_HASH)
        }

        private const val IS_COLLIDING_HASH = 36873697L
        private val isCollidingBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "is_colliding", IS_COLLIDING_HASH)
        }

        private const val GET_COLLISION_COUNT_HASH = 3905245786L
        private val getCollisionCountBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_count", GET_COLLISION_COUNT_HASH)
        }

        private const val FORCE_SHAPECAST_UPDATE_HASH = 3218959716L
        private val forceShapecastUpdateBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "force_shapecast_update", FORCE_SHAPECAST_UPDATE_HASH)
        }

        private const val GET_COLLIDER_HASH = 3332903315L
        private val getColliderBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collider", GET_COLLIDER_HASH)
        }

        private const val GET_COLLIDER_RID_HASH = 495598643L
        private val getColliderRidBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collider_rid", GET_COLLIDER_RID_HASH)
        }

        private const val GET_COLLIDER_SHAPE_HASH = 923996154L
        private val getColliderShapeBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collider_shape", GET_COLLIDER_SHAPE_HASH)
        }

        private const val GET_COLLISION_POINT_HASH = 711720468L
        private val getCollisionPointBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_point", GET_COLLISION_POINT_HASH)
        }

        private const val GET_COLLISION_NORMAL_HASH = 711720468L
        private val getCollisionNormalBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_normal", GET_COLLISION_NORMAL_HASH)
        }

        private const val GET_CLOSEST_COLLISION_SAFE_FRACTION_HASH = 1740695150L
        private val getClosestCollisionSafeFractionBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_closest_collision_safe_fraction", GET_CLOSEST_COLLISION_SAFE_FRACTION_HASH)
        }

        private const val GET_CLOSEST_COLLISION_UNSAFE_FRACTION_HASH = 1740695150L
        private val getClosestCollisionUnsafeFractionBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_closest_collision_unsafe_fraction", GET_CLOSEST_COLLISION_UNSAFE_FRACTION_HASH)
        }

        private const val ADD_EXCEPTION_RID_HASH = 2722037293L
        private val addExceptionRidBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "add_exception_rid", ADD_EXCEPTION_RID_HASH)
        }

        private const val ADD_EXCEPTION_HASH = 1976431078L
        private val addExceptionBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "add_exception", ADD_EXCEPTION_HASH)
        }

        private const val REMOVE_EXCEPTION_RID_HASH = 2722037293L
        private val removeExceptionRidBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "remove_exception_rid", REMOVE_EXCEPTION_RID_HASH)
        }

        private const val REMOVE_EXCEPTION_HASH = 1976431078L
        private val removeExceptionBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "remove_exception", REMOVE_EXCEPTION_HASH)
        }

        private const val CLEAR_EXCEPTIONS_HASH = 3218959716L
        private val clearExceptionsBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "clear_exceptions", CLEAR_EXCEPTIONS_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_EXCLUDE_PARENT_BODY_HASH = 2586408642L
        private val setExcludeParentBodyBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_exclude_parent_body", SET_EXCLUDE_PARENT_BODY_HASH)
        }

        private const val GET_EXCLUDE_PARENT_BODY_HASH = 36873697L
        private val getExcludeParentBodyBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_exclude_parent_body", GET_EXCLUDE_PARENT_BODY_HASH)
        }

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        private val setCollideWithAreasBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)
        }

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        private val isCollideWithAreasEnabledBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
        }

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        private val setCollideWithBodiesBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)
        }

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        private val isCollideWithBodiesEnabledBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)
        }

        private const val GET_COLLISION_RESULT_HASH = 3995934104L
        private val getCollisionResultBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_collision_result", GET_COLLISION_RESULT_HASH)
        }

        private const val SET_DEBUG_SHAPE_CUSTOM_COLOR_HASH = 2920490490L
        private val setDebugShapeCustomColorBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "set_debug_shape_custom_color", SET_DEBUG_SHAPE_CUSTOM_COLOR_HASH)
        }

        private const val GET_DEBUG_SHAPE_CUSTOM_COLOR_HASH = 3444240500L
        private val getDebugShapeCustomColorBind by lazy {
            ObjectCalls.getMethodBind("ShapeCast3D", "get_debug_shape_custom_color", GET_DEBUG_SHAPE_CUSTOM_COLOR_HASH)
        }
    }
}
