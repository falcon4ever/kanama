package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        return BaseButton.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPressedButtonBind, segment))
    }

    /**
     * Returns an `Array` of `Button`s who have this as their `ButtonGroup` (see
     * `BaseButton.button_group`).
     *
     * Generated from Godot docs: ButtonGroup.get_buttons
     */
    fun getButtons(): List<BaseButton> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getButtonsBind, segment, BaseButton::wrap)
    }

    /**
     * If `true`, it is possible to unpress all buttons in this `ButtonGroup`.
     *
     * Generated from Godot docs: ButtonGroup.set_allow_unpress
     */
    fun setAllowUnpress(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllowUnpressBind, segment, enabled)
    }

    /**
     * If `true`, it is possible to unpress all buttons in this `ButtonGroup`.
     *
     * Generated from Godot docs: ButtonGroup.is_allow_unpress
     */
    fun isAllowUnpress(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAllowUnpressBind, segment)
    }

    /** Signal `pressed(button: BaseButton)`; see [TypedSignal]. */
    val pressed: Signal1<BaseButton>
        @JvmName("pressedTypedSignal")
        get() = Signal1(this, "pressed", SignalArgType.objectOf("BaseButton") { BaseButton(it) })

    object Signals {
        const val pressed: String = "pressed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ButtonGroup? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ButtonGroup? =
            if (handle.address() == 0L) null else RefCounted.owned(ButtonGroup(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ButtonGroup? =
            if (handle.address() == 0L) null else ButtonGroup(GodotHandle(handle))

        // Instantiate a ButtonGroup.
        @JvmStatic
        fun create(): ButtonGroup =
            RefCounted.owned(ButtonGroup(GodotHandle(ObjectCalls.constructObject("ButtonGroup"))))
    }

    private object Binds {
        private const val GET_PRESSED_BUTTON_HASH = 3886434893L
        @JvmField
        val getPressedButtonBind =
            ObjectCalls.getMethodBind("ButtonGroup", "get_pressed_button", GET_PRESSED_BUTTON_HASH)

        private const val GET_BUTTONS_HASH = 2915620761L
        @JvmField
        val getButtonsBind =
            ObjectCalls.getMethodBind("ButtonGroup", "get_buttons", GET_BUTTONS_HASH)

        private const val SET_ALLOW_UNPRESS_HASH = 2586408642L
        @JvmField
        val setAllowUnpressBind =
            ObjectCalls.getMethodBind("ButtonGroup", "set_allow_unpress", SET_ALLOW_UNPRESS_HASH)

        private const val IS_ALLOW_UNPRESS_HASH = 2240911060L
        @JvmField
        val isAllowUnpressBind =
            ObjectCalls.getMethodBind("ButtonGroup", "is_allow_unpress", IS_ALLOW_UNPRESS_HASH)
    }
}
