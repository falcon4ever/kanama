package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract base class for GUI buttons.
 *
 * Generated from Godot docs: BaseButton
 */
open class BaseButton(handle: MemorySegment) : Control(handle) {
    var disabled: Boolean
        @JvmName("disabledProperty")
        get() = isDisabled()
        @JvmName("setDisabledProperty")
        set(value) = setDisabled(value)

    var toggleMode: Boolean
        @JvmName("toggleModeProperty")
        get() = isToggleMode()
        @JvmName("setToggleModeProperty")
        set(value) = setToggleMode(value)

    var buttonPressed: Boolean
        @JvmName("buttonPressedProperty")
        get() = isPressed()
        @JvmName("setButtonPressedProperty")
        set(value) = setPressed(value)

    var actionMode: Long
        @JvmName("actionModeProperty")
        get() = getActionMode()
        @JvmName("setActionModeProperty")
        set(value) = setActionMode(value)

    var buttonMask: Long
        @JvmName("buttonMaskProperty")
        get() = getButtonMask()
        @JvmName("setButtonMaskProperty")
        set(value) = setButtonMask(value)

    var keepPressedOutside: Boolean
        @JvmName("keepPressedOutsideProperty")
        get() = isKeepPressedOutside()
        @JvmName("setKeepPressedOutsideProperty")
        set(value) = setKeepPressedOutside(value)

    var buttonGroup: ButtonGroup?
        @JvmName("buttonGroupProperty")
        get() = getButtonGroup()
        @JvmName("setButtonGroupProperty")
        set(value) = setButtonGroup(value)

    var shortcut: Shortcut?
        @JvmName("shortcutProperty")
        get() = getShortcut()
        @JvmName("setShortcutProperty")
        set(value) = setShortcut(value)

    var shortcutFeedback: Boolean
        @JvmName("shortcutFeedbackProperty")
        get() = isShortcutFeedback()
        @JvmName("setShortcutFeedbackProperty")
        set(value) = setShortcutFeedback(value)

    var shortcutInTooltip: Boolean
        @JvmName("shortcutInTooltipProperty")
        get() = isShortcutInTooltipEnabled()
        @JvmName("setShortcutInTooltipProperty")
        set(value) = setShortcutInTooltip(value)

    fun setPressed(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, handle, pressed)
    }

    fun isPressed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPressedBind, handle)
    }

    fun setPressedNoSignal(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPressedNoSignalBind, handle, pressed)
    }

    fun isHovered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHoveredBind, handle)
    }

    fun setToggleMode(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setToggleModeBind, handle, enabled)
    }

    fun isToggleMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isToggleModeBind, handle)
    }

    fun setShortcutInTooltip(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShortcutInTooltipBind, handle, enabled)
    }

    fun isShortcutInTooltipEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShortcutInTooltipEnabledBind, handle)
    }

    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisabledBind, handle, disabled)
    }

    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisabledBind, handle)
    }

    fun setActionMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setActionModeBind, handle, mode)
    }

    fun getActionMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getActionModeBind, handle)
    }

    fun setButtonMask(mask: Long) {
        ObjectCalls.ptrcallWithLongArg(setButtonMaskBind, handle, mask)
    }

    fun getButtonMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getButtonMaskBind, handle)
    }

    fun getDrawMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDrawModeBind, handle)
    }

    fun setKeepPressedOutside(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepPressedOutsideBind, handle, enabled)
    }

    fun isKeepPressedOutside(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isKeepPressedOutsideBind, handle)
    }

    fun setShortcutFeedback(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShortcutFeedbackBind, handle, enabled)
    }

    fun isShortcutFeedback(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShortcutFeedbackBind, handle)
    }

    fun setShortcut(shortcut: Shortcut?) {
        ObjectCalls.ptrcallWithObjectArgs(setShortcutBind, handle, listOf(shortcut?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShortcut(): Shortcut? {
        return Shortcut.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShortcutBind, handle))
    }

    fun setButtonGroup(buttonGroup: ButtonGroup?) {
        ObjectCalls.ptrcallWithObjectArgs(setButtonGroupBind, handle, listOf(buttonGroup?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getButtonGroup(): ButtonGroup? {
        return ButtonGroup.wrap(ObjectCalls.ptrcallNoArgsRetObject(getButtonGroupBind, handle))
    }

    object Signals {
        const val pressed: String = "pressed"
        const val buttonUp: String = "button_up"
        const val buttonDown: String = "button_down"
        const val toggled: String = "toggled"
    }

    companion object {
        const val DRAW_NORMAL: Long = 0L
        const val DRAW_PRESSED: Long = 1L
        const val DRAW_HOVER: Long = 2L
        const val DRAW_DISABLED: Long = 3L
        const val DRAW_HOVER_PRESSED: Long = 4L
        const val ACTION_MODE_BUTTON_PRESS: Long = 0L
        const val ACTION_MODE_BUTTON_RELEASE: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): BaseButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BaseButton? =
            if (handle.address() == 0L) null else BaseButton(handle)

        private const val SET_PRESSED_HASH = 2586408642L
        private val setPressedBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_pressed", SET_PRESSED_HASH)
        }

        private const val IS_PRESSED_HASH = 36873697L
        private val isPressedBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "is_pressed", IS_PRESSED_HASH)
        }

        private const val SET_PRESSED_NO_SIGNAL_HASH = 2586408642L
        private val setPressedNoSignalBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_pressed_no_signal", SET_PRESSED_NO_SIGNAL_HASH)
        }

        private const val IS_HOVERED_HASH = 36873697L
        private val isHoveredBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "is_hovered", IS_HOVERED_HASH)
        }

        private const val SET_TOGGLE_MODE_HASH = 2586408642L
        private val setToggleModeBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_toggle_mode", SET_TOGGLE_MODE_HASH)
        }

        private const val IS_TOGGLE_MODE_HASH = 36873697L
        private val isToggleModeBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "is_toggle_mode", IS_TOGGLE_MODE_HASH)
        }

        private const val SET_SHORTCUT_IN_TOOLTIP_HASH = 2586408642L
        private val setShortcutInTooltipBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_shortcut_in_tooltip", SET_SHORTCUT_IN_TOOLTIP_HASH)
        }

        private const val IS_SHORTCUT_IN_TOOLTIP_ENABLED_HASH = 36873697L
        private val isShortcutInTooltipEnabledBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "is_shortcut_in_tooltip_enabled", IS_SHORTCUT_IN_TOOLTIP_ENABLED_HASH)
        }

        private const val SET_DISABLED_HASH = 2586408642L
        private val setDisabledBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_disabled", SET_DISABLED_HASH)
        }

        private const val IS_DISABLED_HASH = 36873697L
        private val isDisabledBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "is_disabled", IS_DISABLED_HASH)
        }

        private const val SET_ACTION_MODE_HASH = 1985162088L
        private val setActionModeBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_action_mode", SET_ACTION_MODE_HASH)
        }

        private const val GET_ACTION_MODE_HASH = 2589712189L
        private val getActionModeBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "get_action_mode", GET_ACTION_MODE_HASH)
        }

        private const val SET_BUTTON_MASK_HASH = 3950145251L
        private val setButtonMaskBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_button_mask", SET_BUTTON_MASK_HASH)
        }

        private const val GET_BUTTON_MASK_HASH = 2512161324L
        private val getButtonMaskBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "get_button_mask", GET_BUTTON_MASK_HASH)
        }

        private const val GET_DRAW_MODE_HASH = 2492721305L
        private val getDrawModeBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "get_draw_mode", GET_DRAW_MODE_HASH)
        }

        private const val SET_KEEP_PRESSED_OUTSIDE_HASH = 2586408642L
        private val setKeepPressedOutsideBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_keep_pressed_outside", SET_KEEP_PRESSED_OUTSIDE_HASH)
        }

        private const val IS_KEEP_PRESSED_OUTSIDE_HASH = 36873697L
        private val isKeepPressedOutsideBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "is_keep_pressed_outside", IS_KEEP_PRESSED_OUTSIDE_HASH)
        }

        private const val SET_SHORTCUT_FEEDBACK_HASH = 2586408642L
        private val setShortcutFeedbackBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_shortcut_feedback", SET_SHORTCUT_FEEDBACK_HASH)
        }

        private const val IS_SHORTCUT_FEEDBACK_HASH = 36873697L
        private val isShortcutFeedbackBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "is_shortcut_feedback", IS_SHORTCUT_FEEDBACK_HASH)
        }

        private const val SET_SHORTCUT_HASH = 857163497L
        private val setShortcutBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_shortcut", SET_SHORTCUT_HASH)
        }

        private const val GET_SHORTCUT_HASH = 3415666916L
        private val getShortcutBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "get_shortcut", GET_SHORTCUT_HASH)
        }

        private const val SET_BUTTON_GROUP_HASH = 1794463739L
        private val setButtonGroupBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "set_button_group", SET_BUTTON_GROUP_HASH)
        }

        private const val GET_BUTTON_GROUP_HASH = 281644053L
        private val getButtonGroupBind by lazy {
            ObjectCalls.getMethodBind("BaseButton", "get_button_group", GET_BUTTON_GROUP_HASH)
        }
    }
}
