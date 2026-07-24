package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * A group of buttons that doesn't allow more than one button to be pressed at a time.
 *
 * Generated from Godot docs: ButtonGroup
 */
class ButtonGroup(handle: MemorySegment) : Resource(handle) {
    var allowUnpress: Boolean
        @JvmName("allowUnpressProperty")
        get() = isAllowUnpress()
        @JvmName("setAllowUnpressProperty")
        set(value) = setAllowUnpress(value)

    /**
     * Returns the current pressed button.
     *
     * Generated from Godot docs: ButtonGroup.get_pressed_button
     */
    fun getPressedButton(): BaseButton? {
        return BaseButton.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPressedButtonBind, handle))
    }

    /**
     * Returns an `Array` of `Button`s who have this as their `ButtonGroup` (see
     * `BaseButton.button_group`).
     *
     * Generated from Godot docs: ButtonGroup.get_buttons
     */
    fun getButtons(): List<BaseButton> {
        return ObjectCalls.ptrcallNoArgsRetTypedBaseButtonList(getButtonsBind, handle)
    }

    /**
     * If `true`, it is possible to unpress all buttons in this `ButtonGroup`.
     *
     * Generated from Godot docs: ButtonGroup.set_allow_unpress
     */
    fun setAllowUnpress(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowUnpressBind, handle, enabled)
    }

    /**
     * If `true`, it is possible to unpress all buttons in this `ButtonGroup`.
     *
     * Generated from Godot docs: ButtonGroup.is_allow_unpress
     */
    fun isAllowUnpress(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowUnpressBind, handle)
    }

    object Signals {
        const val pressed: String = "pressed"
    }

    companion object {
        @JvmStatic
        fun create(): ButtonGroup =
            ButtonGroup(ObjectCalls.constructObject("ButtonGroup"))

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ButtonGroup? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ButtonGroup? =
            if (handle.address() == 0L) null else ButtonGroup(handle)

        private const val GET_PRESSED_BUTTON_HASH = 3886434893L
        private val getPressedButtonBind by lazy {
            ObjectCalls.getMethodBind("ButtonGroup", "get_pressed_button", GET_PRESSED_BUTTON_HASH)
        }

        private const val GET_BUTTONS_HASH = 2915620761L
        private val getButtonsBind by lazy {
            ObjectCalls.getMethodBind("ButtonGroup", "get_buttons", GET_BUTTONS_HASH)
        }

        private const val SET_ALLOW_UNPRESS_HASH = 2586408642L
        private val setAllowUnpressBind by lazy {
            ObjectCalls.getMethodBind("ButtonGroup", "set_allow_unpress", SET_ALLOW_UNPRESS_HASH)
        }

        private const val IS_ALLOW_UNPRESS_HASH = 2240911060L
        private val isAllowUnpressBind by lazy {
            ObjectCalls.getMethodBind("ButtonGroup", "is_allow_unpress", IS_ALLOW_UNPRESS_HASH)
        }
    }
}
