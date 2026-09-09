package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Represents a mouse button being pressed or released.
 *
 * Generated from Godot docs: InputEventMouseButton
 */
class InputEventMouseButton(handle: MemorySegment) : InputEventMouse(handle) {
    var factor: Double
        @JvmName("factorProperty")
        get() = getFactor()
        @JvmName("setFactorProperty")
        set(value) = setFactor(value)

    var buttonIndex: Long
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
        ObjectCalls.ptrcallWithDoubleArg(setFactorBind, handle, factor)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getFactorBind, handle)
    }

    /**
     * The mouse button identifier, one of the `MouseButton` button or button wheel constants.
     *
     * Generated from Godot docs: InputEventMouseButton.set_button_index
     */
    fun setButtonIndex(buttonIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setButtonIndexBind, handle, buttonIndex)
    }

    /**
     * The mouse button identifier, one of the `MouseButton` button or button wheel constants.
     *
     * Generated from Godot docs: InputEventMouseButton.get_button_index
     */
    fun getButtonIndex(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getButtonIndexBind, handle)
    }

    /**
     * If `true`, the mouse button's state is pressed. If `false`, the mouse button's state is
     * released.
     *
     * Generated from Godot docs: InputEventMouseButton.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, handle, pressed)
    }

    /**
     * If `true`, the mouse button event has been canceled.
     *
     * Generated from Godot docs: InputEventMouseButton.set_canceled
     */
    fun setCanceled(canceled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setCanceledBind, handle, canceled)
    }

    /**
     * If `true`, the mouse button's state is a double-click.
     *
     * Generated from Godot docs: InputEventMouseButton.set_double_click
     */
    fun setDoubleClick(doubleClick: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setDoubleClickBind, handle, doubleClick)
    }

    /**
     * If `true`, the mouse button's state is a double-click.
     *
     * Generated from Godot docs: InputEventMouseButton.is_double_click
     */
    fun isDoubleClick(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isDoubleClickBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventMouseButton? =
            wrap(handle)

        @JvmStatic
        fun from(value: GodotObject): InputEventMouseButton? =
            if (value.isClass("InputEventMouseButton")) InputEventMouseButton(value.handle) else null

        internal fun wrap(handle: MemorySegment): InputEventMouseButton? =
            if (handle.address() == 0L) null else InputEventMouseButton(handle)

        const val MOUSE_BUTTON_LEFT = 1L
        const val MOUSE_BUTTON_RIGHT = 2L
        const val MOUSE_BUTTON_WHEEL_UP = 4L
        const val MOUSE_BUTTON_WHEEL_DOWN = 5L

        private const val SET_FACTOR_HASH = 373806689L
        private val setFactorBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_factor", SET_FACTOR_HASH)
        }

        private const val GET_FACTOR_HASH = 1740695150L
        private val getFactorBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "get_factor", GET_FACTOR_HASH)
        }

        private const val SET_BUTTON_INDEX_HASH = 3624991109L
        private val setButtonIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_button_index", SET_BUTTON_INDEX_HASH)
        }

        private const val GET_BUTTON_INDEX_HASH = 1132662608L
        private val getButtonIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "get_button_index", GET_BUTTON_INDEX_HASH)
        }

        private const val SET_PRESSED_HASH = 2586408642L
        private val setPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_pressed", SET_PRESSED_HASH)
        }

        private const val SET_CANCELED_HASH = 2586408642L
        private val setCanceledBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_canceled", SET_CANCELED_HASH)
        }

        private const val SET_DOUBLE_CLICK_HASH = 2586408642L
        private val setDoubleClickBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_double_click", SET_DOUBLE_CLICK_HASH)
        }

        private const val IS_DOUBLE_CLICK_HASH = 36873697L
        private val isDoubleClickBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "is_double_click", IS_DOUBLE_CLICK_HASH)
        }
    }
}
