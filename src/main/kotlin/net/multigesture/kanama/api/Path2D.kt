package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Contains a `Curve2D` path for `PathFollow2D` nodes to follow.
 *
 * Generated from Godot docs: Path2D
 */
class Path2D(handle: MemorySegment) : Node2D(handle) {
    var curve: Curve2D?
        @JvmName("curveProperty")
        get() = getCurve()
        @JvmName("setCurveProperty")
        set(value) = setCurve(value)

    /**
     * A `Curve2D` describing the path.
     *
     * Generated from Godot docs: Path2D.set_curve
     */
    fun setCurve(curve: Curve2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * A `Curve2D` describing the path.
     *
     * Generated from Godot docs: Path2D.get_curve
     */
    fun getCurve(): Curve2D? {
        return Curve2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Path2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Path2D? =
            if (handle.address() == 0L) null else Path2D(handle)

        private const val SET_CURVE_HASH = 659985499L
        private val setCurveBind by lazy {
            ObjectCalls.getMethodBind("Path2D", "set_curve", SET_CURVE_HASH)
        }

        private const val GET_CURVE_HASH = 660369445L
        private val getCurveBind by lazy {
            ObjectCalls.getMethodBind("Path2D", "get_curve", GET_CURVE_HASH)
        }
    }
}
