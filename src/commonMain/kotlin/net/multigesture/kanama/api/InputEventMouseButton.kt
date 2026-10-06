package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a mouse button being pressed or released.
 *
 * Generated from Godot docs: InputEventMouseButton
 */
class InputEventMouseButton(handle: GodotHandle) : InputEventMouse(handle) {
    var factor: Double
        @JvmName("factorProperty")
        get() = getFactor()
        @JvmName("setFactorProperty")
        set(value) = setFactor(value)

    var buttonIndex: MouseButton
        @JvmName("buttonIndexProperty")
        get() = getButtonIndex()
        @JvmName("setButtonIndexProperty")
        set(value) = setButtonIndex(value)

    var doubleClick: Boolean
        @JvmName("doubleClickProperty")
        get() = isDoubleClick()
        @JvmName("setDoubleClickProperty")
        set(value) = setDoubleClick(value)

    /**
     * The amount (or delta) of the event. When used for high-precision scroll events, this indicates
     * the scroll amount (vertical or horizontal). This is only supported on some platforms; the
     * reported sensitivity varies depending on the platform. May be `0` if not supported.
     *
     * Generated from Godot docs: InputEventMouseButton.set_factor
     */
    fun setFactor(factor: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFactorBind, segment, factor)
    }

    /**
     * The amount (or delta) of the event. When used for high-precision scroll events, this indicates
     * the scroll amount (vertical or horizontal). This is only supported on some platforms; the
     * reported sensitivity varies depending on the platform. May be `0` if not supported.
     *
     * Generated from Godot docs: InputEventMouseButton.get_factor
     */
    fun getFactor(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFactorBind, segment)
    }

    /**
     * The mouse button identifier, one of the `MouseButton` button or button wheel constants.
     *
     * Generated from Godot docs: InputEventMouseButton.set_button_index
     */
    fun setButtonIndex(buttonIndex: MouseButton) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setButtonIndexBind, segment, buttonIndex.value)
    }

    /**
     * The mouse button identifier, one of the `MouseButton` button or button wheel constants.
     *
     * Generated from Godot docs: InputEventMouseButton.get_button_index
     */
    fun getButtonIndex(): MouseButton {
        checkOpen()
        return MouseButton(ObjectCalls.ptrcallNoArgsRetLong(Binds.getButtonIndexBind, segment))
    }

    /**
     * If `true`, the mouse button's state is pressed. If `false`, the mouse button's state is
     * released.
     *
     * Generated from Godot docs: InputEventMouseButton.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPressedBind, segment, pressed)
    }

    /**
     * If `true`, the mouse button event has been canceled.
     *
     * Generated from Godot docs: InputEventMouseButton.set_canceled
     */
    fun setCanceled(canceled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCanceledBind, segment, canceled)
    }

    /**
     * If `true`, the mouse button's state is a double-click.
     *
     * Generated from Godot docs: InputEventMouseButton.set_double_click
     */
    fun setDoubleClick(doubleClick: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDoubleClickBind, segment, doubleClick)
    }

    /**
     * If `true`, the mouse button's state is a double-click.
     *
     * Generated from Godot docs: InputEventMouseButton.is_double_click
     */
    fun isDoubleClick(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDoubleClickBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventMouseButton? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventMouseButton? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventMouseButton(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventMouseButton? =
            if (handle.address() == 0L) null else InputEventMouseButton(GodotHandle(handle))

        // Instantiate an InputEventMouseButton.
        @JvmStatic
        fun create(): InputEventMouseButton =
            RefCounted.owned(InputEventMouseButton(GodotHandle(ObjectCalls.constructObject("InputEventMouseButton"))))

        // Downcast a GodotObject to InputEventMouseButton (null if not).
        @JvmStatic
        fun from(value: GodotObject): InputEventMouseButton? =
            if (value.isClass("InputEventMouseButton")) RefCounted.retained(InputEventMouseButton(value.handle)) else null
    }

    private object Binds {
        private const val SET_FACTOR_HASH = 373806689L
        @JvmField
        val setFactorBind =
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_factor", SET_FACTOR_HASH)

        private const val GET_FACTOR_HASH = 1740695150L
        @JvmField
        val getFactorBind =
            ObjectCalls.getMethodBind("InputEventMouseButton", "get_factor", GET_FACTOR_HASH)

        private const val SET_BUTTON_INDEX_HASH = 3624991109L
        @JvmField
        val setButtonIndexBind =
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_button_index", SET_BUTTON_INDEX_HASH)

        private const val GET_BUTTON_INDEX_HASH = 1132662608L
        @JvmField
        val getButtonIndexBind =
            ObjectCalls.getMethodBind("InputEventMouseButton", "get_button_index", GET_BUTTON_INDEX_HASH)

        private const val SET_PRESSED_HASH = 2586408642L
        @JvmField
        val setPressedBind =
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_pressed", SET_PRESSED_HASH)

        private const val SET_CANCELED_HASH = 2586408642L
        @JvmField
        val setCanceledBind =
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_canceled", SET_CANCELED_HASH)

        private const val SET_DOUBLE_CLICK_HASH = 2586408642L
        @JvmField
        val setDoubleClickBind =
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_double_click", SET_DOUBLE_CLICK_HASH)

        private const val IS_DOUBLE_CLICK_HASH = 36873697L
        @JvmField
        val isDoubleClickBind =
            ObjectCalls.getMethodBind("InputEventMouseButton", "is_double_click", IS_DOUBLE_CLICK_HASH)
    }
}
