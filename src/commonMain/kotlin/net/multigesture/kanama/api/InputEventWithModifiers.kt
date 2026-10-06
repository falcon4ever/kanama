package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract base class for input events affected by modifier keys like Shift and Alt.
 *
 * Generated from Godot docs: InputEventWithModifiers
 */
open class InputEventWithModifiers(handle: GodotHandle) : InputEventFromWindow(handle) {
    var commandOrControlAutoremap: Boolean
        @JvmName("commandOrControlAutoremapProperty")
        get() = isCommandOrControlAutoremap()
        @JvmName("setCommandOrControlAutoremapProperty")
        set(value) = setCommandOrControlAutoremap(value)

    var altPressed: Boolean
        @JvmName("altPressedProperty")
        get() = isAltPressed()
        @JvmName("setAltPressedProperty")
        set(value) = setAltPressed(value)

    var shiftPressed: Boolean
        @JvmName("shiftPressedProperty")
        get() = isShiftPressed()
        @JvmName("setShiftPressedProperty")
        set(value) = setShiftPressed(value)

    var ctrlPressed: Boolean
        @JvmName("ctrlPressedProperty")
        get() = isCtrlPressed()
        @JvmName("setCtrlPressedProperty")
        set(value) = setCtrlPressed(value)

    var metaPressed: Boolean
        @JvmName("metaPressedProperty")
        get() = isMetaPressed()
        @JvmName("setMetaPressedProperty")
        set(value) = setMetaPressed(value)

    /**
     * Automatically use Meta (Cmd) on macOS and Ctrl on other platforms. If `true`, `ctrl_pressed` and
     * `meta_pressed` cannot be set.
     *
     * Generated from Godot docs: InputEventWithModifiers.set_command_or_control_autoremap
     */
    fun setCommandOrControlAutoremap(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCommandOrControlAutoremapBind, segment, enable)
    }

    /**
     * Automatically use Meta (Cmd) on macOS and Ctrl on other platforms. If `true`, `ctrl_pressed` and
     * `meta_pressed` cannot be set.
     *
     * Generated from Godot docs: InputEventWithModifiers.is_command_or_control_autoremap
     */
    fun isCommandOrControlAutoremap(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCommandOrControlAutoremapBind, segment)
    }

    /**
     * On macOS, returns `true` if Meta (Cmd) is pressed. On other platforms, returns `true` if Ctrl is
     * pressed.
     *
     * Generated from Godot docs: InputEventWithModifiers.is_command_or_control_pressed
     */
    fun isCommandOrControlPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCommandOrControlPressedBind, segment)
    }

    /**
     * State of the Alt modifier.
     *
     * Generated from Godot docs: InputEventWithModifiers.set_alt_pressed
     */
    fun setAltPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAltPressedBind, segment, pressed)
    }

    /**
     * State of the Alt modifier.
     *
     * Generated from Godot docs: InputEventWithModifiers.is_alt_pressed
     */
    fun isAltPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAltPressedBind, segment)
    }

    /**
     * State of the Shift modifier.
     *
     * Generated from Godot docs: InputEventWithModifiers.set_shift_pressed
     */
    fun setShiftPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setShiftPressedBind, segment, pressed)
    }

    /**
     * State of the Shift modifier.
     *
     * Generated from Godot docs: InputEventWithModifiers.is_shift_pressed
     */
    fun isShiftPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShiftPressedBind, segment)
    }

    /**
     * State of the Ctrl modifier.
     *
     * Generated from Godot docs: InputEventWithModifiers.set_ctrl_pressed
     */
    fun setCtrlPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCtrlPressedBind, segment, pressed)
    }

    /**
     * State of the Ctrl modifier.
     *
     * Generated from Godot docs: InputEventWithModifiers.is_ctrl_pressed
     */
    fun isCtrlPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCtrlPressedBind, segment)
    }

    /**
     * State of the Meta modifier. On Windows and Linux, this represents the Windows key (sometimes
     * called "meta" or "super" on Linux). On macOS, this represents the Command key.
     *
     * Generated from Godot docs: InputEventWithModifiers.set_meta_pressed
     */
    fun setMetaPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setMetaPressedBind, segment, pressed)
    }

    /**
     * State of the Meta modifier. On Windows and Linux, this represents the Windows key (sometimes
     * called "meta" or "super" on Linux). On macOS, this represents the Command key.
     *
     * Generated from Godot docs: InputEventWithModifiers.is_meta_pressed
     */
    fun isMetaPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMetaPressedBind, segment)
    }

    /**
     * Returns the keycode combination of modifier keys.
     *
     * Generated from Godot docs: InputEventWithModifiers.get_modifiers_mask
     */
    fun getModifiersMask(): KeyModifierMask {
        checkOpen()
        return KeyModifierMask(ObjectCalls.ptrcallNoArgsRetLong(Binds.getModifiersMaskBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventWithModifiers? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventWithModifiers? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventWithModifiers(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventWithModifiers? =
            if (handle.address() == 0L) null else InputEventWithModifiers(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_COMMAND_OR_CONTROL_AUTOREMAP_HASH = 2586408642L
        @JvmField
        val setCommandOrControlAutoremapBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_command_or_control_autoremap", SET_COMMAND_OR_CONTROL_AUTOREMAP_HASH)

        private const val IS_COMMAND_OR_CONTROL_AUTOREMAP_HASH = 36873697L
        @JvmField
        val isCommandOrControlAutoremapBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_command_or_control_autoremap", IS_COMMAND_OR_CONTROL_AUTOREMAP_HASH)

        private const val IS_COMMAND_OR_CONTROL_PRESSED_HASH = 36873697L
        @JvmField
        val isCommandOrControlPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_command_or_control_pressed", IS_COMMAND_OR_CONTROL_PRESSED_HASH)

        private const val SET_ALT_PRESSED_HASH = 2586408642L
        @JvmField
        val setAltPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_alt_pressed", SET_ALT_PRESSED_HASH)

        private const val IS_ALT_PRESSED_HASH = 36873697L
        @JvmField
        val isAltPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_alt_pressed", IS_ALT_PRESSED_HASH)

        private const val SET_SHIFT_PRESSED_HASH = 2586408642L
        @JvmField
        val setShiftPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_shift_pressed", SET_SHIFT_PRESSED_HASH)

        private const val IS_SHIFT_PRESSED_HASH = 36873697L
        @JvmField
        val isShiftPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_shift_pressed", IS_SHIFT_PRESSED_HASH)

        private const val SET_CTRL_PRESSED_HASH = 2586408642L
        @JvmField
        val setCtrlPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_ctrl_pressed", SET_CTRL_PRESSED_HASH)

        private const val IS_CTRL_PRESSED_HASH = 36873697L
        @JvmField
        val isCtrlPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_ctrl_pressed", IS_CTRL_PRESSED_HASH)

        private const val SET_META_PRESSED_HASH = 2586408642L
        @JvmField
        val setMetaPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_meta_pressed", SET_META_PRESSED_HASH)

        private const val IS_META_PRESSED_HASH = 36873697L
        @JvmField
        val isMetaPressedBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_meta_pressed", IS_META_PRESSED_HASH)

        private const val GET_MODIFIERS_MASK_HASH = 1258259499L
        @JvmField
        val getModifiersMaskBind =
            ObjectCalls.getMethodBind("InputEventWithModifiers", "get_modifiers_mask", GET_MODIFIERS_MASK_HASH)
    }
}
