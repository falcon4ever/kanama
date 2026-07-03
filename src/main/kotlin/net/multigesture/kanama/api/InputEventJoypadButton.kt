package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents a gamepad button being pressed or released.
 *
 * Generated from Godot docs: InputEventJoypadButton
 */
class InputEventJoypadButton(handle: MemorySegment) : InputEvent(handle) {
    var buttonIndex: Long
        @JvmName("buttonIndexProperty")
        get() = getButtonIndex()
        @JvmName("setButtonIndexProperty")
        set(value) = setButtonIndex(value)

    var pressure: Double
        @JvmName("pressureProperty")
        get() = getPressure()
        @JvmName("setPressureProperty")
        set(value) = setPressure(value)

    /**
     * Button identifier. One of the `JoyButton` button constants.
     *
     * Generated from Godot docs: InputEventJoypadButton.set_button_index
     */
    fun setButtonIndex(buttonIndex: Long) {
        ObjectCalls.ptrcallWithLongArg(setButtonIndexBind, handle, buttonIndex)
    }

    /**
     * Button identifier. One of the `JoyButton` button constants.
     *
     * Generated from Godot docs: InputEventJoypadButton.get_button_index
     */
    fun getButtonIndex(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getButtonIndexBind, handle)
    }

    fun setPressure(pressure: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPressureBind, handle, pressure)
    }

    fun getPressure(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPressureBind, handle)
    }

    /**
     * If `true`, the button's state is pressed. If `false`, the button's state is released.
     *
     * Generated from Godot docs: InputEventJoypadButton.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, handle, pressed)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventJoypadButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventJoypadButton? =
            if (handle.address() == 0L) null else InputEventJoypadButton(handle)

        private const val SET_BUTTON_INDEX_HASH = 1466368136L
        private val setButtonIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadButton", "set_button_index", SET_BUTTON_INDEX_HASH)
        }

        private const val GET_BUTTON_INDEX_HASH = 595588182L
        private val getButtonIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadButton", "get_button_index", GET_BUTTON_INDEX_HASH)
        }

        private const val SET_PRESSURE_HASH = 373806689L
        private val setPressureBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadButton", "set_pressure", SET_PRESSURE_HASH)
        }

        private const val GET_PRESSURE_HASH = 1740695150L
        private val getPressureBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadButton", "get_pressure", GET_PRESSURE_HASH)
        }

        private const val SET_PRESSED_HASH = 2586408642L
        private val setPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventJoypadButton", "set_pressed", SET_PRESSED_HASH)
        }
    }
}
