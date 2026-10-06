package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Contains a `Curve3D` path for `PathFollow3D` nodes to follow.
 *
 * Generated from Godot docs: Path3D
 */
class Path3D(handle: GodotHandle) : Node3D(handle) {
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * A `Curve3D` describing the path.
     *
     * Generated from Godot docs: Path3D.get_curve
     */
    fun getCurve(): Curve3D? {
        return Curve3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurveBind, segment))
    }

    /**
     * The custom color used to draw the path in the editor. If set to `Color.BLACK` (as by default),
     * the color set in `ProjectSettings.debug/shapes/paths/geometry_color` is used.
     *
     * Generated from Godot docs: Path3D.set_debug_custom_color
     */
    fun setDebugCustomColor(debugCustomColor: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setDebugCustomColorBind, segment, debugCustomColor)
    }

    /**
     * The custom color used to draw the path in the editor. If set to `Color.BLACK` (as by default),
     * the color set in `ProjectSettings.debug/shapes/paths/geometry_color` is used.
     *
     * Generated from Godot docs: Path3D.get_debug_custom_color
     */
    fun getDebugCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDebugCustomColorBind, segment)
    }

    /** Signal `curve_changed()`; see [TypedSignal]. */
    val curveChanged: Signal0
        @JvmName("curveChangedTypedSignal")
        get() = Signal0(this, "curve_changed")

    /** Signal `debug_color_changed()`; see [TypedSignal]. */
    val debugColorChanged: Signal0
        @JvmName("debugColorChangedTypedSignal")
        get() = Signal0(this, "debug_color_changed")

    object Signals {
        const val curveChanged: String = "curve_changed"
        const val debugColorChanged: String = "debug_color_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Path3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Path3D? =
            if (handle.address() == 0L) null else Path3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CURVE_HASH = 408955118L
        @JvmField
        val setCurveBind =
            ObjectCalls.getMethodBind("Path3D", "set_curve", SET_CURVE_HASH)

        private const val GET_CURVE_HASH = 4244715212L
        @JvmField
        val getCurveBind =
            ObjectCalls.getMethodBind("Path3D", "get_curve", GET_CURVE_HASH)

        private const val SET_DEBUG_CUSTOM_COLOR_HASH = 2920490490L
        @JvmField
        val setDebugCustomColorBind =
            ObjectCalls.getMethodBind("Path3D", "set_debug_custom_color", SET_DEBUG_CUSTOM_COLOR_HASH)

        private const val GET_DEBUG_CUSTOM_COLOR_HASH = 3444240500L
        @JvmField
        val getDebugCustomColorBind =
            ObjectCalls.getMethodBind("Path3D", "get_debug_custom_color", GET_DEBUG_CUSTOM_COLOR_HASH)
    }
}
