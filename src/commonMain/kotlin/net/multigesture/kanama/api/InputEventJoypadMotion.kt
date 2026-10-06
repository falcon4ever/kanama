package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents axis motions (such as joystick or analog triggers) from a gamepad.
 *
 * Generated from Godot docs: InputEventJoypadMotion
 */
class InputEventJoypadMotion(handle: GodotHandle) : InputEvent(handle) {
    var axis: JoyAxis
        @JvmName("axisProperty")
        get() = getAxis()
        @JvmName("setAxisProperty")
        set(value) = setAxis(value)

    var axisValue: Double
        @JvmName("axisValueProperty")
        get() = getAxisValue()
        @JvmName("setAxisValueProperty")
        set(value) = setAxisValue(value)

    /**
     * Axis identifier.
     *
     * Generated from Godot docs: InputEventJoypadMotion.set_axis
     */
    fun setAxis(axis: JoyAxis) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAxisBind, segment, axis.value)
    }

    /**
     * Axis identifier.
     *
     * Generated from Godot docs: InputEventJoypadMotion.get_axis
     */
    fun getAxis(): JoyAxis {
        checkOpen()
        return JoyAxis(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAxisBind, segment))
    }

    /**
     * Current position of the joystick on the given axis. The value ranges from `-1.0` to `1.0`. A
     * value of `0` means the axis is in its resting position.
     *
     * Generated from Godot docs: InputEventJoypadMotion.set_axis_value
     */
    fun setAxisValue(axisValue: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAxisValueBind, segment, axisValue)
    }

    /**
     * Current position of the joystick on the given axis. The value ranges from `-1.0` to `1.0`. A
     * value of `0` means the axis is in its resting position.
     *
     * Generated from Godot docs: InputEventJoypadMotion.get_axis_value
     */
    fun getAxisValue(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAxisValueBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventJoypadMotion? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventJoypadMotion? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventJoypadMotion(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventJoypadMotion? =
            if (handle.address() == 0L) null else InputEventJoypadMotion(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_AXIS_HASH = 1332685170L
        @JvmField
        val setAxisBind =
            ObjectCalls.getMethodBind("InputEventJoypadMotion", "set_axis", SET_AXIS_HASH)

        private const val GET_AXIS_HASH = 4019121683L
        @JvmField
        val getAxisBind =
            ObjectCalls.getMethodBind("InputEventJoypadMotion", "get_axis", GET_AXIS_HASH)

        private const val SET_AXIS_VALUE_HASH = 373806689L
        @JvmField
        val setAxisValueBind =
            ObjectCalls.getMethodBind("InputEventJoypadMotion", "set_axis_value", SET_AXIS_VALUE_HASH)

        private const val GET_AXIS_VALUE_HASH = 1740695150L
        @JvmField
        val getAxisValueBind =
            ObjectCalls.getMethodBind("InputEventJoypadMotion", "get_axis_value", GET_AXIS_VALUE_HASH)
    }
}
