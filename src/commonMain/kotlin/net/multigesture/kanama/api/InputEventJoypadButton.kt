package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a gamepad button being pressed or released.
 *
 * Generated from Godot docs: InputEventJoypadButton
 */
class InputEventJoypadButton(handle: GodotHandle) : InputEvent(handle) {
    var buttonIndex: JoyButton
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
    fun setButtonIndex(buttonIndex: JoyButton) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setButtonIndexBind, segment, buttonIndex.value)
    }

    /**
     * Button identifier. One of the `JoyButton` button constants.
     *
     * Generated from Godot docs: InputEventJoypadButton.get_button_index
     */
    fun getButtonIndex(): JoyButton {
        checkOpen()
        return JoyButton(ObjectCalls.ptrcallNoArgsRetLong(Binds.getButtonIndexBind, segment))
    }

    fun setPressure(pressure: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPressureBind, segment, pressure)
    }

    fun getPressure(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPressureBind, segment)
    }

    /**
     * If `true`, the button's state is pressed. If `false`, the button's state is released.
     *
     * Generated from Godot docs: InputEventJoypadButton.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPressedBind, segment, pressed)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventJoypadButton? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventJoypadButton? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventJoypadButton(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventJoypadButton? =
            if (handle.address() == 0L) null else InputEventJoypadButton(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BUTTON_INDEX_HASH = 1466368136L
        @JvmField
        val setButtonIndexBind =
            ObjectCalls.getMethodBind("InputEventJoypadButton", "set_button_index", SET_BUTTON_INDEX_HASH)

        private const val GET_BUTTON_INDEX_HASH = 595588182L
        @JvmField
        val getButtonIndexBind =
            ObjectCalls.getMethodBind("InputEventJoypadButton", "get_button_index", GET_BUTTON_INDEX_HASH)

        private const val SET_PRESSURE_HASH = 373806689L
        @JvmField
        val setPressureBind =
            ObjectCalls.getMethodBind("InputEventJoypadButton", "set_pressure", SET_PRESSURE_HASH)

        private const val GET_PRESSURE_HASH = 1740695150L
        @JvmField
        val getPressureBind =
            ObjectCalls.getMethodBind("InputEventJoypadButton", "get_pressure", GET_PRESSURE_HASH)

        private const val SET_PRESSED_HASH = 2586408642L
        @JvmField
        val setPressedBind =
            ObjectCalls.getMethodBind("InputEventJoypadButton", "set_pressed", SET_PRESSED_HASH)
    }
}
