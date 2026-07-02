package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents axis motions (such as joystick or analog triggers) from a gamepad.
 *
 * Generated from Godot docs: InputEventJoypadMotion
 */
class InputEventJoypadMotion(handle: MemorySegment) : InputEvent(handle) {
    var axis: Long
        @JvmName("axisProperty")
        get() = getAxis()
        @JvmName("setAxisProperty")
        set(value) = setAxis(value)

    var axisValue: Double
        @JvmName("axisValueProperty")
        get() = getAxisValue()
        @JvmName("setAxisValueProperty")
        set(value) = setAxisValue(value)

    fun setAxis(axis: Long) {
        ObjectCalls.ptrcallWithLongArg(setAxisBind, handle, axis)
    }

    fun getAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAxisBind, handle)
    }

    fun setAxisValue(axisValue: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAxisValueBind, handle, axisValue)
    }

    fun getAxisValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAxisValueBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventJoypadMotion? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventJoypadMotion? =
            if (handle.address() == 0L) null else InputEventJoypadMotion(handle)

        private const val SET_AXIS_HASH = 1332685170L
        private val setAxisBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadMotion", "set_axis", SET_AXIS_HASH)
        }

        private const val GET_AXIS_HASH = 4019121683L
        private val getAxisBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadMotion", "get_axis", GET_AXIS_HASH)
        }

        private const val SET_AXIS_VALUE_HASH = 373806689L
        private val setAxisValueBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadMotion", "set_axis_value", SET_AXIS_VALUE_HASH)
        }

        private const val GET_AXIS_VALUE_HASH = 1740695150L
        private val getAxisValueBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadMotion", "get_axis_value", GET_AXIS_VALUE_HASH)
        }
    }
}
