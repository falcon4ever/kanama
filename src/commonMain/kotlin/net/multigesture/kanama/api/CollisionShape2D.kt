package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A node that provides a `Shape2D` to a `CollisionObject2D` parent.
 *
 * Generated from Godot docs: CollisionShape2D
 */
class CollisionShape2D(handle: GodotHandle) : Node2D(handle) {
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setShapeBind, segment, listOf(shape?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The actual shape owned by this collision shape.
     *
     * Generated from Godot docs: CollisionShape2D.get_shape
     */
    fun getShape(): Shape2D? {
        return Shape2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getShapeBind, segment))
    }

    /**
     * A disabled collision shape has no effect in the world. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionShape2D.set_disabled
     */
    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDisabledBind, segment, disabled)
    }

    /**
     * A disabled collision shape has no effect in the world. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionShape2D.is_disabled
     */
    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDisabledBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setOneWayCollisionBind, segment, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isOneWayCollisionEnabledBind, segment)
    }

    /**
     * The margin used for one-way collision (in pixels). Higher values will make the shape thicker,
     * and work better for colliders that enter the shape at a high velocity.
     *
     * Generated from Godot docs: CollisionShape2D.set_one_way_collision_margin
     */
    fun setOneWayCollisionMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOneWayCollisionMarginBind, segment, margin)
    }

    /**
     * The margin used for one-way collision (in pixels). Higher values will make the shape thicker,
     * and work better for colliders that enter the shape at a high velocity.
     *
     * Generated from Godot docs: CollisionShape2D.get_one_way_collision_margin
     */
    fun getOneWayCollisionMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOneWayCollisionMarginBind, segment)
    }

    /**
     * The direction used for one-way collision.
     *
     * Generated from Godot docs: CollisionShape2D.set_one_way_collision_direction
     */
    fun setOneWayCollisionDirection(direction: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setOneWayCollisionDirectionBind, segment, direction)
    }

    /**
     * The direction used for one-way collision.
     *
     * Generated from Godot docs: CollisionShape2D.get_one_way_collision_direction
     */
    fun getOneWayCollisionDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getOneWayCollisionDirectionBind, segment)
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
        ObjectCalls.ptrcallWithColorArg(Binds.setDebugColorBind, segment, color)
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
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDebugColorBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CollisionShape2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CollisionShape2D? =
            if (handle.address() == 0L) null else CollisionShape2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SHAPE_HASH = 771364740L
        @JvmField
        val setShapeBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "set_shape", SET_SHAPE_HASH)

        private const val GET_SHAPE_HASH = 522005891L
        @JvmField
        val getShapeBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "get_shape", GET_SHAPE_HASH)

        private const val SET_DISABLED_HASH = 2586408642L
        @JvmField
        val setDisabledBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "set_disabled", SET_DISABLED_HASH)

        private const val IS_DISABLED_HASH = 36873697L
        @JvmField
        val isDisabledBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "is_disabled", IS_DISABLED_HASH)

        private const val SET_ONE_WAY_COLLISION_HASH = 2586408642L
        @JvmField
        val setOneWayCollisionBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "set_one_way_collision", SET_ONE_WAY_COLLISION_HASH)

        private const val IS_ONE_WAY_COLLISION_ENABLED_HASH = 36873697L
        @JvmField
        val isOneWayCollisionEnabledBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "is_one_way_collision_enabled", IS_ONE_WAY_COLLISION_ENABLED_HASH)

        private const val SET_ONE_WAY_COLLISION_MARGIN_HASH = 373806689L
        @JvmField
        val setOneWayCollisionMarginBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "set_one_way_collision_margin", SET_ONE_WAY_COLLISION_MARGIN_HASH)

        private const val GET_ONE_WAY_COLLISION_MARGIN_HASH = 1740695150L
        @JvmField
        val getOneWayCollisionMarginBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "get_one_way_collision_margin", GET_ONE_WAY_COLLISION_MARGIN_HASH)

        private const val SET_ONE_WAY_COLLISION_DIRECTION_HASH = 743155724L
        @JvmField
        val setOneWayCollisionDirectionBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "set_one_way_collision_direction", SET_ONE_WAY_COLLISION_DIRECTION_HASH)

        private const val GET_ONE_WAY_COLLISION_DIRECTION_HASH = 3341600327L
        @JvmField
        val getOneWayCollisionDirectionBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "get_one_way_collision_direction", GET_ONE_WAY_COLLISION_DIRECTION_HASH)

        private const val SET_DEBUG_COLOR_HASH = 2920490490L
        @JvmField
        val setDebugColorBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "set_debug_color", SET_DEBUG_COLOR_HASH)

        private const val GET_DEBUG_COLOR_HASH = 3444240500L
        @JvmField
        val getDebugColorBind =
            ObjectCalls.getMethodBind("CollisionShape2D", "get_debug_color", GET_DEBUG_COLOR_HASH)
    }
}
