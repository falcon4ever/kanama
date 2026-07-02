package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Abstract base class for input events.
 *
 * Generated from Godot docs: InputEvent
 */
open class InputEvent(handle: MemorySegment) : Resource(handle) {
    var device: Int
        @JvmName("deviceProperty")
        get() = getDevice()
        @JvmName("setDeviceProperty")
        set(value) = setDevice(value)

    fun setDevice(device: Int) {
        ObjectCalls.ptrcallWithIntArg(setDeviceBind, handle, device)
    }

    fun getDevice(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDeviceBind, handle)
    }

    fun isAction(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionBind, handle, action, exactMatch)
    }

    fun isActionPressed(action: String, allowEcho: Boolean = false, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndTwoBoolArgsRetBool(isActionPressedBind, handle, action, allowEcho, exactMatch)
    }

    fun isActionReleased(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionReleasedBind, handle, action, exactMatch)
    }

    fun getActionStrength(action: String, exactMatch: Boolean = false): Double {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDouble(getActionStrengthBind, handle, action, exactMatch)
    }

    fun isCanceled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCanceledBind, handle)
    }

    fun isPressed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPressedBind, handle)
    }

    fun isReleased(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isReleasedBind, handle)
    }

    fun isEcho(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEchoBind, handle)
    }

    fun asText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextBind, handle)
    }

    fun isMatch(event: InputEvent?, exactMatch: Boolean = true): Boolean {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetBool(isMatchBind, handle, event?.requireOpenHandle() ?: MemorySegment.NULL, exactMatch)
    }

    fun isActionType(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActionTypeBind, handle)
    }

    /**
     * Returns `true` if the given input event and this input event can be added together (only for
     * events of type `InputEventMouseMotion`). The given input event's position, global position and
     * speed will be copied. The resulting `relative` is a sum of both events. Both events' modifiers
     * have to be identical.
     *
     * Generated from Godot docs: InputEvent.accumulate
     */
    fun accumulate(withEvent: InputEvent?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(accumulateBind, handle, withEvent?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun xformedBy(xform: Transform2D, localOfs: Vector2 = Vector2(0f, 0f)): InputEvent? {
        return InputEvent.wrap(ObjectCalls.ptrcallWithTransform2DVector2ArgsRetObject(xformedByBind, handle, xform, localOfs))
    }

    companion object {
        const val DEVICE_ID_EMULATION: Long = -1L
        const val DEVICE_ID_KEYBOARD: Long = 16L
        const val DEVICE_ID_MOUSE: Long = 32L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEvent? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEvent? =
            if (handle.address() == 0L) null else InputEvent(handle)

        private const val SET_DEVICE_HASH = 1286410249L
        private val setDeviceBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "set_device", SET_DEVICE_HASH)
        }

        private const val GET_DEVICE_HASH = 3905245786L
        private val getDeviceBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "get_device", GET_DEVICE_HASH)
        }

        private const val IS_ACTION_HASH = 1558498928L
        private val isActionBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_action", IS_ACTION_HASH)
        }

        private const val IS_ACTION_PRESSED_HASH = 1631499404L
        private val isActionPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_action_pressed", IS_ACTION_PRESSED_HASH)
        }

        private const val IS_ACTION_RELEASED_HASH = 1558498928L
        private val isActionReleasedBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_action_released", IS_ACTION_RELEASED_HASH)
        }

        private const val GET_ACTION_STRENGTH_HASH = 801543509L
        private val getActionStrengthBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "get_action_strength", GET_ACTION_STRENGTH_HASH)
        }

        private const val IS_CANCELED_HASH = 36873697L
        private val isCanceledBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_canceled", IS_CANCELED_HASH)
        }

        private const val IS_PRESSED_HASH = 36873697L
        private val isPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_pressed", IS_PRESSED_HASH)
        }

        private const val IS_RELEASED_HASH = 36873697L
        private val isReleasedBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_released", IS_RELEASED_HASH)
        }

        private const val IS_ECHO_HASH = 36873697L
        private val isEchoBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_echo", IS_ECHO_HASH)
        }

        private const val AS_TEXT_HASH = 201670096L
        private val asTextBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "as_text", AS_TEXT_HASH)
        }

        private const val IS_MATCH_HASH = 1754951977L
        private val isMatchBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_match", IS_MATCH_HASH)
        }

        private const val IS_ACTION_TYPE_HASH = 36873697L
        private val isActionTypeBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "is_action_type", IS_ACTION_TYPE_HASH)
        }

        private const val ACCUMULATE_HASH = 1062211774L
        private val accumulateBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "accumulate", ACCUMULATE_HASH)
        }

        private const val XFORMED_BY_HASH = 1282766827L
        private val xformedByBind by lazy {
            ObjectCalls.getMethodBind("InputEvent", "xformed_by", XFORMED_BY_HASH)
        }
    }
}
