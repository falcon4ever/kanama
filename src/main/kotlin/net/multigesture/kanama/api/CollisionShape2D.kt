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

    /**
     * The actual shape owned by this collision shape.
     *
     * Generated from Godot docs: CollisionShape2D.set_shape
     */
    fun setShape(shape: Shape2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setShapeBind, handle, listOf(shape?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The actual shape owned by this collision shape.
     *
     * Generated from Godot docs: CollisionShape2D.get_shape
     */
    fun getShape(): Shape2D? {
        return Shape2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShapeBind, handle))
    }

    /**
     * A disabled collision shape has no effect in the world. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionShape2D.set_disabled
     */
    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisabledBind, handle, disabled)
    }

    /**
     * A disabled collision shape has no effect in the world. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionShape2D.is_disabled
     */
    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisabledBind, handle)
    }

    /**
     * Sets whether this collision shape should only detect collision on one side (top or bottom).
     * Note: This property has no effect if this `CollisionShape2D` is a child of an `Area2D` node.
     * Note: The one way collision direction can be configured by setting
     * `one_way_collision_direction`.
     *
     * Generated from Godot docs: CollisionShape2D.set_one_way_collision
     */
    fun setOneWayCollision(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneWayCollisionBind, handle, enabled)
    }

    /**
     * Sets whether this collision shape should only detect collision on one side (top or bottom).
     * Note: This property has no effect if this `CollisionShape2D` is a child of an `Area2D` node.
     * Note: The one way collision direction can be configured by setting
     * `one_way_collision_direction`.
     *
     * Generated from Godot docs: CollisionShape2D.is_one_way_collision_enabled
     */
    fun isOneWayCollisionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOneWayCollisionEnabledBind, handle)
    }

    /**
     * The margin used for one-way collision (in pixels). Higher values will make the shape thicker,
     * and work better for colliders that enter the shape at a high velocity.
     *
     * Generated from Godot docs: CollisionShape2D.set_one_way_collision_margin
     */
    fun setOneWayCollisionMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOneWayCollisionMarginBind, handle, margin)
    }

    /**
     * The margin used for one-way collision (in pixels). Higher values will make the shape thicker,
     * and work better for colliders that enter the shape at a high velocity.
     *
     * Generated from Godot docs: CollisionShape2D.get_one_way_collision_margin
     */
    fun getOneWayCollisionMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOneWayCollisionMarginBind, handle)
    }

    /**
     * The direction used for one-way collision.
     *
     * Generated from Godot docs: CollisionShape2D.set_one_way_collision_direction
     */
    fun setOneWayCollisionDirection(direction: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOneWayCollisionDirectionBind, handle, direction)
    }

    /**
     * The direction used for one-way collision.
     *
     * Generated from Godot docs: CollisionShape2D.get_one_way_collision_direction
     */
    fun getOneWayCollisionDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOneWayCollisionDirectionBind, handle)
    }

    /**
     * The collision shape color that is displayed in the editor, or in the running project if Debug >
     * Visible Collision Shapes is checked at the top of the editor. Note: The default value is
     * `ProjectSettings.debug/shapes/collision/shape_color`. The `Color(0, 0, 0, 0)` value documented
     * here is a placeholder, and not the actual default debug color.
     *
     * Generated from Godot docs: CollisionShape2D.set_debug_color
     */
    fun setDebugColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugColorBind, handle, color)
    }

    /**
     * The collision shape color that is displayed in the editor, or in the running project if Debug >
     * Visible Collision Shapes is checked at the top of the editor. Note: The default value is
     * `ProjectSettings.debug/shapes/collision/shape_color`. The `Color(0, 0, 0, 0)` value documented
     * here is a placeholder, and not the actual default debug color.
     *
     * Generated from Godot docs: CollisionShape2D.get_debug_color
     */
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
