package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Provides parameters for `PhysicsDirectSpaceState2D.intersect_point`.
 *
 * Generated from Godot docs: PhysicsPointQueryParameters2D
 */
class PhysicsPointQueryParameters2D(handle: MemorySegment) : RefCounted(handle) {
    var position: Vector2
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var canvasInstanceId: Long
        @JvmName("canvasInstanceIdProperty")
        get() = getCanvasInstanceId()
        @JvmName("setCanvasInstanceIdProperty")
        set(value) = setCanvasInstanceId(value)

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

    fun setPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPositionBind, handle, position)
    }

    fun getPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
    }

    fun setCanvasInstanceId(canvasInstanceId: Long) {
        ObjectCalls.ptrcallWithLongArg(setCanvasInstanceIdBind, handle, canvasInstanceId)
    }

    fun getCanvasInstanceId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCanvasInstanceIdBind, handle)
    }

    fun setCollisionMask(collisionMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, collisionMask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun setExclude(exclude: List<RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
    }

    fun getExclude(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getExcludeBind, handle)
    }

    fun setCollideWithBodies(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithBodiesBind, handle, enable)
    }

    fun isCollideWithBodiesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithBodiesEnabledBind, handle)
    }

    fun setCollideWithAreas(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithAreasBind, handle, enable)
    }

    fun isCollideWithAreasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithAreasEnabledBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsPointQueryParameters2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsPointQueryParameters2D? =
            if (handle.address() == 0L) null else PhysicsPointQueryParameters2D(handle)

        private const val SET_POSITION_HASH = 743155724L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "set_position", SET_POSITION_HASH)
        }

        private const val GET_POSITION_HASH = 3341600327L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "get_position", GET_POSITION_HASH)
        }

        private const val SET_CANVAS_INSTANCE_ID_HASH = 1286410249L
        private val setCanvasInstanceIdBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "set_canvas_instance_id", SET_CANVAS_INSTANCE_ID_HASH)
        }

        private const val GET_CANVAS_INSTANCE_ID_HASH = 3905245786L
        private val getCanvasInstanceIdBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "get_canvas_instance_id", GET_CANVAS_INSTANCE_ID_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_EXCLUDE_HASH = 381264803L
        private val setExcludeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "set_exclude", SET_EXCLUDE_HASH)
        }

        private const val GET_EXCLUDE_HASH = 3995934104L
        private val getExcludeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "get_exclude", GET_EXCLUDE_HASH)
        }

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        private val setCollideWithBodiesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)
        }

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        private val isCollideWithBodiesEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)
        }

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        private val setCollideWithAreasBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)
        }

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        private val isCollideWithAreasEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
        }
    }
}
