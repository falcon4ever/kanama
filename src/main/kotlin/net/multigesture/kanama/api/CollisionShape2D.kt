package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A node that provides a `Shape2D` to a `CollisionObject2D` parent.
 *
 * Generated from Godot docs: CollisionShape2D
 */
class CollisionShape2D(handle: MemorySegment) : Node2D(handle) {
    var shape: Shape2D?
        @JvmName("shapeProperty")
        get() = getShape()
        @JvmName("setShapeProperty")
        set(value) = setShape(value)

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

    var debugColor: Color
        @JvmName("debugColorProperty")
        get() = getDebugColor()
        @JvmName("setDebugColorProperty")
        set(value) = setDebugColor(value)

    fun setShape(shape: Shape2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setShapeBind, handle, listOf(shape?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShape(): Shape2D? {
        return Shape2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShapeBind, handle))
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

    fun setDebugColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugColorBind, handle, color)
    }

    fun getDebugColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugColorBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CollisionShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CollisionShape2D? =
            if (handle.address() == 0L) null else CollisionShape2D(handle)

        private const val SET_SHAPE_HASH = 771364740L
        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "set_shape", SET_SHAPE_HASH)
        }

        private const val GET_SHAPE_HASH = 522005891L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "get_shape", GET_SHAPE_HASH)
        }

        private const val SET_DISABLED_HASH = 2586408642L
        private val setDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "set_disabled", SET_DISABLED_HASH)
        }

        private const val IS_DISABLED_HASH = 36873697L
        private val isDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "is_disabled", IS_DISABLED_HASH)
        }

        private const val SET_ONE_WAY_COLLISION_HASH = 2586408642L
        private val setOneWayCollisionBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "set_one_way_collision", SET_ONE_WAY_COLLISION_HASH)
        }

        private const val IS_ONE_WAY_COLLISION_ENABLED_HASH = 36873697L
        private val isOneWayCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "is_one_way_collision_enabled", IS_ONE_WAY_COLLISION_ENABLED_HASH)
        }

        private const val SET_ONE_WAY_COLLISION_MARGIN_HASH = 373806689L
        private val setOneWayCollisionMarginBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "set_one_way_collision_margin", SET_ONE_WAY_COLLISION_MARGIN_HASH)
        }

        private const val GET_ONE_WAY_COLLISION_MARGIN_HASH = 1740695150L
        private val getOneWayCollisionMarginBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "get_one_way_collision_margin", GET_ONE_WAY_COLLISION_MARGIN_HASH)
        }

        private const val SET_ONE_WAY_COLLISION_DIRECTION_HASH = 743155724L
        private val setOneWayCollisionDirectionBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "set_one_way_collision_direction", SET_ONE_WAY_COLLISION_DIRECTION_HASH)
        }

        private const val GET_ONE_WAY_COLLISION_DIRECTION_HASH = 3341600327L
        private val getOneWayCollisionDirectionBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "get_one_way_collision_direction", GET_ONE_WAY_COLLISION_DIRECTION_HASH)
        }

        private const val SET_DEBUG_COLOR_HASH = 2920490490L
        private val setDebugColorBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "set_debug_color", SET_DEBUG_COLOR_HASH)
        }

        private const val GET_DEBUG_COLOR_HASH = 3444240500L
        private val getDebugColorBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape2D", "get_debug_color", GET_DEBUG_COLOR_HASH)
        }
    }
}
