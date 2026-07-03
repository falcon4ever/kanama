package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents a magnifying touch gesture.
 *
 * Generated from Godot docs: InputEventMagnifyGesture
 */
class InputEventMagnifyGesture(handle: MemorySegment) : InputEventGesture(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(setFactorBind, handle, factor)
    }

    /**
     * The amount (or delta) of the event. This value is closer to `1.0` the slower the gesture is
     * performed.
     *
     * Generated from Godot docs: InputEventMagnifyGesture.get_factor
     */
    fun getFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFactorBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventMagnifyGesture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventMagnifyGesture? =
            if (handle.address() == 0L) null else InputEventMagnifyGesture(handle)

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
