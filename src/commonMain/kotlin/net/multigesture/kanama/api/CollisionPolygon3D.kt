package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A node that provides a thickened polygon shape (a prism) to a `CollisionObject3D` parent.
 *
 * Generated from Godot docs: CollisionPolygon3D
 */
class CollisionPolygon3D(handle: GodotHandle) : Node3D(handle) {
    var depth: Double
        @JvmName("depthProperty")
        get() = getDepth()
        @JvmName("setDepthProperty")
        set(value) = setDepth(value)

    var disabled: Boolean
        @JvmName("disabledProperty")
        get() = isDisabled()
        @JvmName("setDisabledProperty")
        set(value) = setDisabled(value)

    var polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()
        @JvmName("setPolygonProperty")
        set(value) = setPolygon(value)

    var margin: Double
        @JvmName("marginProperty")
        get() = getMargin()
        @JvmName("setMarginProperty")
        set(value) = setMargin(value)

    var debugColor: Color
        @JvmName("debugColorProperty")
        get() = getDebugColor()
        @JvmName("setDebugColorProperty")
        set(value) = setDebugColor(value)

    var debugFill: Boolean
        @JvmName("debugFillProperty")
        get() = getEnableDebugFill()
        @JvmName("setDebugFillProperty")
        set(value) = setEnableDebugFill(value)

    /**
     * Length that the resulting collision extends in either direction perpendicular to its 2D polygon.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_depth
     */
    fun setDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBind, segment, depth)
    }

    /**
     * Length that the resulting collision extends in either direction perpendicular to its 2D polygon.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_depth
     */
    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, segment)
    }

    /**
     * Array of vertices which define the 2D polygon in the local XY plane.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_polygon
     */
    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, segment, polygon)
    }

    /**
     * Array of vertices which define the 2D polygon in the local XY plane.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_polygon
     */
    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, segment)
    }

    /**
     * If `true`, no collision will be produced. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_disabled
     */
    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisabledBind, segment, disabled)
    }

    /**
     * If `true`, no collision will be produced. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionPolygon3D.is_disabled
     */
    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisabledBind, segment)
    }

    /**
     * The collision shape color that is displayed in the editor, or in the running project if Debug >
     * Visible Collision Shapes is checked at the top of the editor. Note: The default value is
     * `ProjectSettings.debug/shapes/collision/shape_color`. The `Color(0, 0, 0, 0)` value documented
     * here is a placeholder, and not the actual default debug color.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_debug_color
     */
    fun setDebugColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugColorBind, segment, color)
    }

    /**
     * The collision shape color that is displayed in the editor, or in the running project if Debug >
     * Visible Collision Shapes is checked at the top of the editor. Note: The default value is
     * `ProjectSettings.debug/shapes/collision/shape_color`. The `Color(0, 0, 0, 0)` value documented
     * here is a placeholder, and not the actual default debug color.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_debug_color
     */
    fun getDebugColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugColorBind, segment)
    }

    /**
     * If `true`, when the shape is displayed, it will show a solid fill color in addition to its
     * wireframe.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_enable_debug_fill
     */
    fun setEnableDebugFill(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDebugFillBind, segment, enable)
    }

    /**
     * If `true`, when the shape is displayed, it will show a solid fill color in addition to its
     * wireframe.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_enable_debug_fill
     */
    fun getEnableDebugFill(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDebugFillBind, segment)
    }

    /**
     * The collision margin for the generated `Shape3D`. See `Shape3D.margin` for more details.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_margin
     */
    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, segment, margin)
    }

    /**
     * The collision margin for the generated `Shape3D`. See `Shape3D.margin` for more details.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_margin
     */
    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CollisionPolygon3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): CollisionPolygon3D? =
            if (handle.address() == 0L) null else CollisionPolygon3D(GodotHandle(handle))

        private const val SET_DEPTH_HASH = 373806689L
        private val setDepthBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_depth", SET_DEPTH_HASH)
        }

        private const val GET_DEPTH_HASH = 1740695150L
        private val getDepthBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_depth", GET_DEPTH_HASH)
        }

        private const val SET_POLYGON_HASH = 1509147220L
        private val setPolygonBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_polygon", SET_POLYGON_HASH)
        }

        private const val GET_POLYGON_HASH = 2961356807L
        private val getPolygonBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_polygon", GET_POLYGON_HASH)
        }

        private const val SET_DISABLED_HASH = 2586408642L
        private val setDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_disabled", SET_DISABLED_HASH)
        }

        private const val IS_DISABLED_HASH = 36873697L
        private val isDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "is_disabled", IS_DISABLED_HASH)
        }

        private const val SET_DEBUG_COLOR_HASH = 2920490490L
        private val setDebugColorBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_debug_color", SET_DEBUG_COLOR_HASH)
        }

        private const val GET_DEBUG_COLOR_HASH = 3444240500L
        private val getDebugColorBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_debug_color", GET_DEBUG_COLOR_HASH)
        }

        private const val SET_ENABLE_DEBUG_FILL_HASH = 2586408642L
        private val setEnableDebugFillBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_enable_debug_fill", SET_ENABLE_DEBUG_FILL_HASH)
        }

        private const val GET_ENABLE_DEBUG_FILL_HASH = 36873697L
        private val getEnableDebugFillBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_enable_debug_fill", GET_ENABLE_DEBUG_FILL_HASH)
        }

        private const val SET_MARGIN_HASH = 373806689L
        private val setMarginBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_margin", SET_MARGIN_HASH)
        }

        private const val GET_MARGIN_HASH = 1740695150L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_margin", GET_MARGIN_HASH)
        }
    }
}
