package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A node that provides a thickened polygon shape (a prism) to a `CollisionObject3D` parent.
 *
 * Generated from Godot docs: CollisionPolygon3D
 */
class CollisionPolygon3D(handle: MemorySegment) : Node3D(handle) {
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

    fun setDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBind, handle, depth)
    }

    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, handle)
    }

    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
    }

    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, handle)
    }

    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisabledBind, handle, disabled)
    }

    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisabledBind, handle)
    }

    fun setDebugColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugColorBind, handle, color)
    }

    fun getDebugColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugColorBind, handle)
    }

    fun setEnableDebugFill(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDebugFillBind, handle, enable)
    }

    fun getEnableDebugFill(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDebugFillBind, handle)
    }

    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, handle, margin)
    }

    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CollisionPolygon3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CollisionPolygon3D? =
            if (handle.address() == 0L) null else CollisionPolygon3D(handle)

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
