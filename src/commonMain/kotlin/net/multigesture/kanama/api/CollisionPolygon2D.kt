package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * A node that provides a polygon shape to a `CollisionObject2D` parent.
 *
 * Generated from Godot docs: CollisionPolygon2D
 */
class CollisionPolygon2D(handle: GodotHandle) : Node2D(handle) {
    var buildMode: CollisionPolygon2D.BuildMode
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

    /**
     * The polygon's list of vertices. Each point will be connected to the next, and the final point
     * will be connected to the first. Note: The returned vertices are in the local coordinate space of
     * the given `CollisionPolygon2D`.
     *
     * Generated from Godot docs: CollisionPolygon2D.set_polygon
     */
    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(Binds.setPolygonBind, segment, polygon)
    }

    /**
     * The polygon's list of vertices. Each point will be connected to the next, and the final point
     * will be connected to the first. Note: The returned vertices are in the local coordinate space of
     * the given `CollisionPolygon2D`.
     *
     * Generated from Godot docs: CollisionPolygon2D.get_polygon
     */
    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(Binds.getPolygonBind, segment)
    }

    /**
     * Collision build mode.
     *
     * Generated from Godot docs: CollisionPolygon2D.set_build_mode
     */
    fun setBuildMode(buildMode: CollisionPolygon2D.BuildMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBuildModeBind, segment, buildMode.value)
    }

    /**
     * Collision build mode.
     *
     * Generated from Godot docs: CollisionPolygon2D.get_build_mode
     */
    fun getBuildMode(): CollisionPolygon2D.BuildMode {
        return CollisionPolygon2D.BuildMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBuildModeBind, segment))
    }

    /**
     * If `true`, no collisions will be detected. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionPolygon2D.set_disabled
     */
    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDisabledBind, segment, disabled)
    }

    /**
     * If `true`, no collisions will be detected. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionPolygon2D.is_disabled
     */
    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDisabledBind, segment)
    }

    /**
     * If `true`, only edges that face up, relative to `CollisionPolygon2D`'s rotation, will collide
     * with other objects. Note: This property has no effect if this `CollisionPolygon2D` is a child of
     * an `Area2D` node. Note: The one way collision direction can be configured by setting
     * `one_way_collision_direction`.
     *
     * Generated from Godot docs: CollisionPolygon2D.set_one_way_collision
     */
    fun setOneWayCollision(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setOneWayCollisionBind, segment, enabled)
    }

    /**
     * If `true`, only edges that face up, relative to `CollisionPolygon2D`'s rotation, will collide
     * with other objects. Note: This property has no effect if this `CollisionPolygon2D` is a child of
     * an `Area2D` node. Note: The one way collision direction can be configured by setting
     * `one_way_collision_direction`.
     *
     * Generated from Godot docs: CollisionPolygon2D.is_one_way_collision_enabled
     */
    fun isOneWayCollisionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isOneWayCollisionEnabledBind, segment)
    }

    /**
     * The margin used for one-way collision (in pixels). Higher values will make the shape thicker,
     * and work better for colliders that enter the polygon at a high velocity.
     *
     * Generated from Godot docs: CollisionPolygon2D.set_one_way_collision_margin
     */
    fun setOneWayCollisionMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOneWayCollisionMarginBind, segment, margin)
    }

    /**
     * The margin used for one-way collision (in pixels). Higher values will make the shape thicker,
     * and work better for colliders that enter the polygon at a high velocity.
     *
     * Generated from Godot docs: CollisionPolygon2D.get_one_way_collision_margin
     */
    fun getOneWayCollisionMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOneWayCollisionMarginBind, segment)
    }

    /**
     * The direction used for one-way collision.
     *
     * Generated from Godot docs: CollisionPolygon2D.set_one_way_collision_direction
     */
    fun setOneWayCollisionDirection(direction: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setOneWayCollisionDirectionBind, segment, direction)
    }

    /**
     * The direction used for one-way collision.
     *
     * Generated from Godot docs: CollisionPolygon2D.get_one_way_collision_direction
     */
    fun getOneWayCollisionDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getOneWayCollisionDirectionBind, segment)
    }

    /**
     * Godot's `CollisionPolygon2D.BuildMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`CollisionPolygon2D.BuildMode.<NAME>`).
     *
     * Generated from Godot docs: CollisionPolygon2D.BuildMode
     */
    @JvmInline
    value class BuildMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Collisions will include the polygon and its contained area. In this mode the node has the same
             * effect as several `ConvexPolygonShape2D` nodes, one for each convex shape in the convex
             * decomposition of the polygon (but without the overhead of multiple nodes).
             *
             * Generated from Godot docs: CollisionPolygon2D.BUILD_SOLIDS
             */
            val SOLIDS: BuildMode get() = BuildMode(0L)
            /**
             * Collisions will only include the polygon edges. In this mode the node has the same effect as a
             * single `ConcavePolygonShape2D` made of segments, with the restriction that each segment (after
             * the first one) starts where the previous one ends, and the last one ends where the first one
             * starts (forming a closed but hollow polygon).
             *
             * Generated from Godot docs: CollisionPolygon2D.BUILD_SEGMENTS
             */
            val SEGMENTS: BuildMode get() = BuildMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CollisionPolygon2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CollisionPolygon2D? =
            if (handle.address() == 0L) null else CollisionPolygon2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_POLYGON_HASH = 1509147220L
        @JvmField
        val setPolygonBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_polygon", SET_POLYGON_HASH)

        private const val GET_POLYGON_HASH = 2961356807L
        @JvmField
        val getPolygonBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "get_polygon", GET_POLYGON_HASH)

        private const val SET_BUILD_MODE_HASH = 2780803135L
        @JvmField
        val setBuildModeBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_build_mode", SET_BUILD_MODE_HASH)

        private const val GET_BUILD_MODE_HASH = 3044948800L
        @JvmField
        val getBuildModeBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "get_build_mode", GET_BUILD_MODE_HASH)

        private const val SET_DISABLED_HASH = 2586408642L
        @JvmField
        val setDisabledBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_disabled", SET_DISABLED_HASH)

        private const val IS_DISABLED_HASH = 36873697L
        @JvmField
        val isDisabledBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "is_disabled", IS_DISABLED_HASH)

        private const val SET_ONE_WAY_COLLISION_HASH = 2586408642L
        @JvmField
        val setOneWayCollisionBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_one_way_collision", SET_ONE_WAY_COLLISION_HASH)

        private const val IS_ONE_WAY_COLLISION_ENABLED_HASH = 36873697L
        @JvmField
        val isOneWayCollisionEnabledBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "is_one_way_collision_enabled", IS_ONE_WAY_COLLISION_ENABLED_HASH)

        private const val SET_ONE_WAY_COLLISION_MARGIN_HASH = 373806689L
        @JvmField
        val setOneWayCollisionMarginBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_one_way_collision_margin", SET_ONE_WAY_COLLISION_MARGIN_HASH)

        private const val GET_ONE_WAY_COLLISION_MARGIN_HASH = 1740695150L
        @JvmField
        val getOneWayCollisionMarginBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "get_one_way_collision_margin", GET_ONE_WAY_COLLISION_MARGIN_HASH)

        private const val SET_ONE_WAY_COLLISION_DIRECTION_HASH = 743155724L
        @JvmField
        val setOneWayCollisionDirectionBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "set_one_way_collision_direction", SET_ONE_WAY_COLLISION_DIRECTION_HASH)

        private const val GET_ONE_WAY_COLLISION_DIRECTION_HASH = 3341600327L
        @JvmField
        val getOneWayCollisionDirectionBind =
            ObjectCalls.getMethodBind("CollisionPolygon2D", "get_one_way_collision_direction", GET_ONE_WAY_COLLISION_DIRECTION_HASH)
    }
}
