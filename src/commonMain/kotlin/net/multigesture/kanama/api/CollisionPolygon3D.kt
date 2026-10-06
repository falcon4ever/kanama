package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthBind, segment, depth)
    }

    /**
     * Length that the resulting collision extends in either direction perpendicular to its 2D polygon.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_depth
     */
    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthBind, segment)
    }

    /**
     * Array of vertices which define the 2D polygon in the local XY plane.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_polygon
     */
    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(Binds.setPolygonBind, segment, polygon)
    }

    /**
     * Array of vertices which define the 2D polygon in the local XY plane.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_polygon
     */
    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(Binds.getPolygonBind, segment)
    }

    /**
     * If `true`, no collision will be produced. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_disabled
     */
    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDisabledBind, segment, disabled)
    }

    /**
     * If `true`, no collision will be produced. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionPolygon3D.is_disabled
     */
    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDisabledBind, segment)
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
        ObjectCalls.ptrcallWithColorArg(Binds.setDebugColorBind, segment, color)
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
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDebugColorBind, segment)
    }

    /**
     * If `true`, when the shape is displayed, it will show a solid fill color in addition to its
     * wireframe.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_enable_debug_fill
     */
    fun setEnableDebugFill(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableDebugFillBind, segment, enable)
    }

    /**
     * If `true`, when the shape is displayed, it will show a solid fill color in addition to its
     * wireframe.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_enable_debug_fill
     */
    fun getEnableDebugFill(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableDebugFillBind, segment)
    }

    /**
     * The collision margin for the generated `Shape3D`. See `Shape3D.margin` for more details.
     *
     * Generated from Godot docs: CollisionPolygon3D.set_margin
     */
    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMarginBind, segment, margin)
    }

    /**
     * The collision margin for the generated `Shape3D`. See `Shape3D.margin` for more details.
     *
     * Generated from Godot docs: CollisionPolygon3D.get_margin
     */
    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMarginBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CollisionPolygon3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CollisionPolygon3D? =
            if (handle.address() == 0L) null else CollisionPolygon3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DEPTH_HASH = 373806689L
        @JvmField
        val setDepthBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_depth", SET_DEPTH_HASH)

        private const val GET_DEPTH_HASH = 1740695150L
        @JvmField
        val getDepthBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_depth", GET_DEPTH_HASH)

        private const val SET_POLYGON_HASH = 1509147220L
        @JvmField
        val setPolygonBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_polygon", SET_POLYGON_HASH)

        private const val GET_POLYGON_HASH = 2961356807L
        @JvmField
        val getPolygonBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_polygon", GET_POLYGON_HASH)

        private const val SET_DISABLED_HASH = 2586408642L
        @JvmField
        val setDisabledBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_disabled", SET_DISABLED_HASH)

        private const val IS_DISABLED_HASH = 36873697L
        @JvmField
        val isDisabledBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "is_disabled", IS_DISABLED_HASH)

        private const val SET_DEBUG_COLOR_HASH = 2920490490L
        @JvmField
        val setDebugColorBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_debug_color", SET_DEBUG_COLOR_HASH)

        private const val GET_DEBUG_COLOR_HASH = 3444240500L
        @JvmField
        val getDebugColorBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_debug_color", GET_DEBUG_COLOR_HASH)

        private const val SET_ENABLE_DEBUG_FILL_HASH = 2586408642L
        @JvmField
        val setEnableDebugFillBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_enable_debug_fill", SET_ENABLE_DEBUG_FILL_HASH)

        private const val GET_ENABLE_DEBUG_FILL_HASH = 36873697L
        @JvmField
        val getEnableDebugFillBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_enable_debug_fill", GET_ENABLE_DEBUG_FILL_HASH)

        private const val SET_MARGIN_HASH = 373806689L
        @JvmField
        val setMarginBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "set_margin", SET_MARGIN_HASH)

        private const val GET_MARGIN_HASH = 1740695150L
        @JvmField
        val getMarginBind =
            ObjectCalls.getMethodBind("CollisionPolygon3D", "get_margin", GET_MARGIN_HASH)
    }
}
