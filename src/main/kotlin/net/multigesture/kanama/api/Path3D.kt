package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Contains a `Curve3D` path for `PathFollow3D` nodes to follow.
 *
 * Generated from Godot docs: Path3D
 */
class Path3D(handle: MemorySegment) : Node3D(handle) {
    var curve: Curve3D?
        @JvmName("curveProperty")
        get() = getCurve()
        @JvmName("setCurveProperty")
        set(value) = setCurve(value)

    var debugCustomColor: Color
        @JvmName("debugCustomColorProperty")
        get() = getDebugCustomColor()
        @JvmName("setDebugCustomColorProperty")
        set(value) = setDebugCustomColor(value)

    /**
     * A `Curve3D` describing the path.
     *
     * Generated from Godot docs: Path3D.set_curve
     */
    fun setCurve(curve: Curve3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * A `Curve3D` describing the path.
     *
     * Generated from Godot docs: Path3D.get_curve
     */
    fun getCurve(): Curve3D? {
        return Curve3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, handle))
    }

    /**
     * The custom color used to draw the path in the editor. If set to `Color.BLACK` (as by default),
     * the color set in `ProjectSettings.debug/shapes/paths/geometry_color` is used.
     *
     * Generated from Godot docs: Path3D.set_debug_custom_color
     */
    fun setDebugCustomColor(debugCustomColor: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugCustomColorBind, handle, debugCustomColor)
    }

    /**
     * The custom color used to draw the path in the editor. If set to `Color.BLACK` (as by default),
     * the color set in `ProjectSettings.debug/shapes/paths/geometry_color` is used.
     *
     * Generated from Godot docs: Path3D.get_debug_custom_color
     */
    fun getDebugCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugCustomColorBind, handle)
    }

    object Signals {
        const val curveChanged: String = "curve_changed"
        const val debugColorChanged: String = "debug_color_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Path3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Path3D? =
            if (handle.address() == 0L) null else Path3D(handle)

        private const val SET_CURVE_HASH = 408955118L
        private val setCurveBind by lazy {
            ObjectCalls.getMethodBind("Path3D", "set_curve", SET_CURVE_HASH)
        }

        private const val GET_CURVE_HASH = 4244715212L
        private val getCurveBind by lazy {
            ObjectCalls.getMethodBind("Path3D", "get_curve", GET_CURVE_HASH)
        }

        private const val SET_DEBUG_CUSTOM_COLOR_HASH = 2920490490L
        private val setDebugCustomColorBind by lazy {
            ObjectCalls.getMethodBind("Path3D", "set_debug_custom_color", SET_DEBUG_CUSTOM_COLOR_HASH)
        }

        private const val GET_DEBUG_CUSTOM_COLOR_HASH = 3444240500L
        private val getDebugCustomColorBind by lazy {
            ObjectCalls.getMethodBind("Path3D", "get_debug_custom_color", GET_DEBUG_CUSTOM_COLOR_HASH)
        }
    }
}
