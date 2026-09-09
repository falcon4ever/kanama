package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: InputEventWithModifiers
 */
open class InputEventWithModifiers(handle: MemorySegment) : InputEventFromWindow(handle) {
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

    fun setCommandOrControlAutoremap(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setCommandOrControlAutoremapBind, handle, enable)
    }

    fun isCommandOrControlAutoremap(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isCommandOrControlAutoremapBind, handle)
    }

    fun isCommandOrControlPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isCommandOrControlPressedBind, handle)
    }

    fun setAltPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAltPressedBind, handle, pressed)
    }

    fun isAltPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isAltPressedBind, handle)
    }

    fun setShiftPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setShiftPressedBind, handle, pressed)
    }

    fun isShiftPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isShiftPressedBind, handle)
    }

    fun setCtrlPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setCtrlPressedBind, handle, pressed)
    }

    fun isCtrlPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isCtrlPressedBind, handle)
    }

    fun setMetaPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setMetaPressedBind, handle, pressed)
    }

    fun isMetaPressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isMetaPressedBind, handle)
    }

    fun getModifiersMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getModifiersMaskBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): InputEventWithModifiers? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventWithModifiers? =
            if (handle.address() == 0L) null else InputEventWithModifiers(handle)

        private const val SET_COMMAND_OR_CONTROL_AUTOREMAP_HASH = 2586408642L
        private val setCommandOrControlAutoremapBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_command_or_control_autoremap", SET_COMMAND_OR_CONTROL_AUTOREMAP_HASH)
        }

        private const val IS_COMMAND_OR_CONTROL_AUTOREMAP_HASH = 36873697L
        private val isCommandOrControlAutoremapBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_command_or_control_autoremap", IS_COMMAND_OR_CONTROL_AUTOREMAP_HASH)
        }

        private const val IS_COMMAND_OR_CONTROL_PRESSED_HASH = 36873697L
        private val isCommandOrControlPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_command_or_control_pressed", IS_COMMAND_OR_CONTROL_PRESSED_HASH)
        }

        private const val SET_ALT_PRESSED_HASH = 2586408642L
        private val setAltPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_alt_pressed", SET_ALT_PRESSED_HASH)
        }

        private const val IS_ALT_PRESSED_HASH = 36873697L
        private val isAltPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_alt_pressed", IS_ALT_PRESSED_HASH)
        }

        private const val SET_SHIFT_PRESSED_HASH = 2586408642L
        private val setShiftPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_shift_pressed", SET_SHIFT_PRESSED_HASH)
        }

        private const val IS_SHIFT_PRESSED_HASH = 36873697L
        private val isShiftPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_shift_pressed", IS_SHIFT_PRESSED_HASH)
        }

        private const val SET_CTRL_PRESSED_HASH = 2586408642L
        private val setCtrlPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_ctrl_pressed", SET_CTRL_PRESSED_HASH)
        }

        private const val IS_CTRL_PRESSED_HASH = 36873697L
        private val isCtrlPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_ctrl_pressed", IS_CTRL_PRESSED_HASH)
        }

        private const val SET_META_PRESSED_HASH = 2586408642L
        private val setMetaPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "set_meta_pressed", SET_META_PRESSED_HASH)
        }

        private const val IS_META_PRESSED_HASH = 36873697L
        private val isMetaPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "is_meta_pressed", IS_META_PRESSED_HASH)
        }

        private const val GET_MODIFIERS_MASK_HASH = 1258259499L
        private val getModifiersMaskBind by lazy {
            ObjectCalls.getMethodBind("InputEventWithModifiers", "get_modifiers_mask", GET_MODIFIERS_MASK_HASH)
        }
    }
}
