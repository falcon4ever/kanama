package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A node that provides a polygon shape to a `CollisionObject2D` parent.
 *
 * Generated from Godot docs: CollisionPolygon2D
 */
class CollisionPolygon2D(handle: MemorySegment) : Node2D(handle) {
    var buildMode: Long
        @JvmName("buildModeProperty")
        get() = getBuildMode()
        @JvmName("setBuildModeProperty")
        set(value) = setBuildMode(value)

    var polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()
        @JvmName("setPolygonProperty")
        set(value) = setPolygon(value)

    var disabled: Boolean
        @JvmName("disabledProperty")
        get() = isDisabled()
        @JvmName("setDisabledProperty")
        set(value) = setDisabled(value)

    var oneWayCollision: Boolean
        @JvmName("oneWayCollisionProperty")
        get() = isOneWayCollisionEnabled()
        @JvmName("setOneWayCollisionProperty")
        set(value) = setOneWayCollision(value)

    var oneWayCollisionMargin: Double
        @JvmName("oneWayCollisionMarginProperty")
        get() = getOneWayCollisionMargin()
        @JvmName("setOneWayCollisionMarginProperty")
        set(value) = setOneWayCollisionMargin(value)

    var oneWayCollisionDirection: Vector2
        @JvmName("oneWayCollisionDirectionProperty")
        get() = getOneWayCollisionDirection()
        @JvmName("setOneWayCollisionDirectionProperty")
        set(value) = setOneWayCollisionDirection(value)

    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
    }

    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, handle)
    }

    fun setBuildMode(buildMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBuildModeBind, handle, buildMode)
    }

    fun getBuildMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBuildModeBind, handle)
    }

    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisabledBind, handle, disabled)
    }

    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisabledBind, handle)
    }

    fun setOneWayCollision(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneWayCollisionBind, handle, enabled)
    }

    fun isOneWayCollisionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOneWayCollisionEnabledBind, handle)
    }

    fun setOneWayCollisionMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOneWayCollisionMarginBind, handle, margin)
    }

    fun getOneWayCollisionMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOneWayCollisionMarginBind, handle)
    }

    fun setOneWayCollisionDirection(direction: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOneWayCollisionDirectionBind, handle, direction)
    }

    fun getOneWayCollisionDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOneWayCollisionDirectionBind, handle)
    }

    companion object {
        const val BUILD_SOLIDS: Long = 0L
        const val BUILD_SEGMENTS: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CollisionPolygon2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CollisionPolygon2D? =
            if (handle.address() == 0L) null else CollisionPolygon2D(handle)

        private const val SET_POLYGON_HASH = 1509147220L
        private val setPolygonBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_polygon", SET_POLYGON_HASH)
        }

        private const val GET_POLYGON_HASH = 2961356807L
        private val getPolygonBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "get_polygon", GET_POLYGON_HASH)
        }

        private const val SET_BUILD_MODE_HASH = 2780803135L
        private val setBuildModeBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_build_mode", SET_BUILD_MODE_HASH)
        }

        private const val GET_BUILD_MODE_HASH = 3044948800L
        private val getBuildModeBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "get_build_mode", GET_BUILD_MODE_HASH)
        }

        private const val SET_DISABLED_HASH = 2586408642L
        private val setDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_disabled", SET_DISABLED_HASH)
        }

        private const val IS_DISABLED_HASH = 36873697L
        private val isDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "is_disabled", IS_DISABLED_HASH)
        }

        private const val SET_ONE_WAY_COLLISION_HASH = 2586408642L
        private val setOneWayCollisionBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_one_way_collision", SET_ONE_WAY_COLLISION_HASH)
        }

        private const val IS_ONE_WAY_COLLISION_ENABLED_HASH = 36873697L
        private val isOneWayCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "is_one_way_collision_enabled", IS_ONE_WAY_COLLISION_ENABLED_HASH)
        }

        private const val SET_ONE_WAY_COLLISION_MARGIN_HASH = 373806689L
        private val setOneWayCollisionMarginBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_one_way_collision_margin", SET_ONE_WAY_COLLISION_MARGIN_HASH)
        }

        private const val GET_ONE_WAY_COLLISION_MARGIN_HASH = 1740695150L
        private val getOneWayCollisionMarginBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "get_one_way_collision_margin", GET_ONE_WAY_COLLISION_MARGIN_HASH)
        }

        private const val SET_ONE_WAY_COLLISION_DIRECTION_HASH = 743155724L
        private val setOneWayCollisionDirectionBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_one_way_collision_direction", SET_ONE_WAY_COLLISION_DIRECTION_HASH)
        }

        private const val GET_ONE_WAY_COLLISION_DIRECTION_HASH = 3341600327L
        private val getOneWayCollisionDirectionBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon2D", "get_one_way_collision_direction", GET_ONE_WAY_COLLISION_DIRECTION_HASH)
        }
    }
}
