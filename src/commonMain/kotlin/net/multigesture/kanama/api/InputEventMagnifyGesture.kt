package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Represents a magnifying touch gesture.
 *
 * Generated from Godot docs: InputEventMagnifyGesture
 */
class InputEventMagnifyGesture(handle: GodotHandle) : InputEventGesture(handle) {
    var factor: Double
        @JvmName("factorProperty")
        get() = getFactor()
        @JvmName("setFactorProperty")
        set(value) = setFactor(value)

    /**
     * The amount (or delta) of the event. This value is closer to `1.0` the slower the gesture is
     * performed.
     *
     * Generated from Godot docs: InputEventMagnifyGesture.set_factor
     */
    fun setFactor(factor: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFactorBind, segment, factor)
    }

    /**
     * The amount (or delta) of the event. This value is closer to `1.0` the slower the gesture is
     * performed.
     *
     * Generated from Godot docs: InputEventMagnifyGesture.get_factor
     */
    fun getFactor(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFactorBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventMagnifyGesture? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): InputEventMagnifyGesture? =
            if (handle.address() == 0L) null else InputEventMagnifyGesture(GodotHandle(handle))

        private const val SET_FACTOR_HASH = 373806689L
        private val setFactorBind by lazy {
            ObjectCalls.getMethodBind("InputEventMagnifyGesture", "set_factor", SET_FACTOR_HASH)
        }

        private const val GET_FACTOR_HASH = 1740695150L
        private val getFactorBind by lazy {
            ObjectCalls.getMethodBind("InputEventMagnifyGesture", "get_factor", GET_FACTOR_HASH)
        }
    }
}
