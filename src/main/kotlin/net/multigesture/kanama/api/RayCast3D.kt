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

    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    fun setTargetPosition(localPoint: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setTargetPositionBind, handle, localPoint)
    }

    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTargetPositionBind, handle)
    }

    fun isColliding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollidingBind, handle)
    }

    fun forceRaycastUpdate() {
        ObjectCalls.ptrcallNoArgs(forceRaycastUpdateBind, handle)
    }

    fun getCollider(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColliderBind, handle))
    }

    fun getColliderRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getColliderRidBind, handle)
    }

    fun getColliderShape(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getColliderShapeBind, handle)
    }

    fun getCollisionPoint(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCollisionPointBind, handle)
    }

    fun getCollisionNormal(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCollisionNormalBind, handle)
    }

    fun getCollisionFaceIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCollisionFaceIndexBind, handle)
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

    fun setHitFromInside(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHitFromInsideBind, handle, enable)
    }

    fun isHitFromInsideEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHitFromInsideEnabledBind, handle)
    }

    fun setHitBackFaces(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHitBackFacesBind, handle, enable)
    }

    fun isHitBackFacesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHitBackFacesEnabledBind, handle)
    }

    fun setDebugShapeCustomColor(debugShapeCustomColor: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugShapeCustomColorBind, handle, debugShapeCustomColor)
    }

    fun getDebugShapeCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugShapeCustomColorBind, handle)
    }

    fun setDebugShapeThickness(debugShapeThickness: Int) {
        ObjectCalls.ptrcallWithIntArg(setDebugShapeThicknessBind, handle, debugShapeThickness)
    }

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
