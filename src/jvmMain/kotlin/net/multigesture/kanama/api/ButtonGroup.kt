package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import kotlin.jvm.JvmName

/**
 * A group of buttons that doesn't allow more than one button to be pressed at a time.
 *
 * Generated from Godot docs: ButtonGroup
 */
class ButtonGroup(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        return BaseButton.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPressedButtonBind, segment))
    }

    /**
     * Returns an `Array` of `Button`s who have this as their `ButtonGroup` (see
     * `BaseButton.button_group`).
     *
     * Generated from Godot docs: ButtonGroup.get_buttons
     */
    fun getButtons(): List<BaseButton> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedBaseButtonList(getButtonsBind, segment)
    }

    /**
     * If `true`, it is possible to unpress all buttons in this `ButtonGroup`.
     *
     * Generated from Godot docs: ButtonGroup.set_allow_unpress
     */
    fun setAllowUnpress(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAllowUnpressBind, segment, enabled)
    }

    /**
     * If `true`, it is possible to unpress all buttons in this `ButtonGroup`.
     *
     * Generated from Godot docs: ButtonGroup.is_allow_unpress
     */
    fun isAllowUnpress(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowUnpressBind, segment)
    }

    object Signals {
        const val pressed: String = "pressed"
    }

    companion object {
        @JvmStatic
        fun create(): ButtonGroup =
            ButtonGroup(GodotHandle(ObjectCalls.constructObject("ButtonGroup")))

        @JvmStatic
        fun fromHandle(handle: GodotHandle): ButtonGroup? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ButtonGroup? =
            if (handle.address() == 0L) null else ButtonGroup(GodotHandle(handle))

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
