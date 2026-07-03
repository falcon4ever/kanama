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

    /**
     * The event's device ID. Note: `device` can be negative for special use cases that don't refer to
     * devices physically present on the system. See `DEVICE_ID_EMULATION`.
     *
     * Generated from Godot docs: InputEvent.set_device
     */
    fun setDevice(device: Int) {
        ObjectCalls.ptrcallWithIntArg(setDeviceBind, handle, device)
    }

    /**
     * The event's device ID. Note: `device` can be negative for special use cases that don't refer to
     * devices physically present on the system. See `DEVICE_ID_EMULATION`.
     *
     * Generated from Godot docs: InputEvent.get_device
     */
    fun getDevice(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDeviceBind, handle)
    }

    /**
     * Returns `true` if this input event matches a pre-defined action of any type. If `exact_match` is
     * `false`, it ignores additional input modifiers for `InputEventKey` and `InputEventMouseButton`
     * events, and the direction for `InputEventJoypadMotion` events.
     *
     * Generated from Godot docs: InputEvent.is_action
     */
    fun isAction(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionBind, handle, action, exactMatch)
    }

    /**
     * Returns `true` if the given action matches this event and is being pressed (and is not an echo
     * event for `InputEventKey` events, unless `allow_echo` is `true`). Not relevant for events of
     * type `InputEventMouseMotion` or `InputEventScreenDrag`. If `exact_match` is `false`, it ignores
     * additional input modifiers for `InputEventKey` and `InputEventMouseButton` events, and the
     * direction for `InputEventJoypadMotion` events. Note: Due to keyboard ghosting,
     * `is_action_pressed` may return `false` even if one of the action's keys is pressed. See Input
     * examples ($DOCS_URL/tutorials/inputs/input_examples.html#keyboard-events) in the documentation
     * for more information.
     *
     * Generated from Godot docs: InputEvent.is_action_pressed
     */
    fun isActionPressed(action: String, allowEcho: Boolean = false, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndTwoBoolArgsRetBool(isActionPressedBind, handle, action, allowEcho, exactMatch)
    }

    /**
     * Returns `true` if the given action matches this event and is released (i.e. not pressed). Not
     * relevant for events of type `InputEventMouseMotion` or `InputEventScreenDrag`. If `exact_match`
     * is `false`, it ignores additional input modifiers for `InputEventKey` and
     * `InputEventMouseButton` events, and the direction for `InputEventJoypadMotion` events.
     *
     * Generated from Godot docs: InputEvent.is_action_released
     */
    fun isActionReleased(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionReleasedBind, handle, action, exactMatch)
    }

    /**
     * Returns a value between 0.0 and 1.0 depending on the given actions' state. Useful for getting
     * the value of events of type `InputEventJoypadMotion`. If `exact_match` is `false`, it ignores
     * additional input modifiers for `InputEventKey` and `InputEventMouseButton` events, and the
     * direction for `InputEventJoypadMotion` events.
     *
     * Generated from Godot docs: InputEvent.get_action_strength
     */
    fun getActionStrength(action: String, exactMatch: Boolean = false): Double {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDouble(getActionStrengthBind, handle, action, exactMatch)
    }

    /**
     * Returns `true` if this input event has been canceled.
     *
     * Generated from Godot docs: InputEvent.is_canceled
     */
    fun isCanceled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCanceledBind, handle)
    }

    /**
     * Returns `true` if this input event is pressed. Not relevant for events of type
     * `InputEventMouseMotion` or `InputEventScreenDrag`. Note: Due to keyboard ghosting, `is_pressed`
     * may return `false` even if one of the action's keys is pressed. See Input examples
     * ($DOCS_URL/tutorials/inputs/input_examples.html#keyboard-events) in the documentation for more
     * information.
     *
     * Generated from Godot docs: InputEvent.is_pressed
     */
    fun isPressed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPressedBind, handle)
    }

    /**
     * Returns `true` if this input event is released. Not relevant for events of type
     * `InputEventMouseMotion` or `InputEventScreenDrag`.
     *
     * Generated from Godot docs: InputEvent.is_released
     */
    fun isReleased(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isReleasedBind, handle)
    }

    /**
     * Returns `true` if this input event is an echo event (only for events of type `InputEventKey`).
     * An echo event is a repeated key event sent when the user is holding down the key. Any other
     * event type returns `false`. Note: The rate at which echo events are sent is typically around 20
     * events per second (after holding down the key for roughly half a second). However, the key
     * repeat delay/speed can be changed by the user or disabled entirely in the operating system
     * settings. To ensure your project works correctly on all configurations, do not assume the user
     * has a specific key repeat configuration in your project's behavior.
     *
     * Generated from Godot docs: InputEvent.is_echo
     */
    fun isEcho(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEchoBind, handle)
    }

    /**
     * Returns a `String` representation of the event.
     *
     * Generated from Godot docs: InputEvent.as_text
     */
    fun asText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextBind, handle)
    }

    /**
     * Returns `true` if the specified `event` matches this event. Only valid for action events, which
     * include key (`InputEventKey`), button (`InputEventMouseButton` or `InputEventJoypadButton`),
     * axis `InputEventJoypadMotion`, and action (`InputEventAction`) events. If `exact_match` is
     * `false`, the check ignores additional input modifiers for `InputEventKey` and
     * `InputEventMouseButton` events, and the direction for `InputEventJoypadMotion` events. Note:
     * This method only considers the event configuration (such as the keyboard key or the joypad
     * axis), not state information like `is_pressed`, `is_released`, `is_echo`, or `is_canceled`.
     *
     * Generated from Godot docs: InputEvent.is_match
     */
    fun isMatch(event: InputEvent?, exactMatch: Boolean = true): Boolean {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetBool(isMatchBind, handle, event?.requireOpenHandle() ?: MemorySegment.NULL, exactMatch)
    }

    /**
     * Returns `true` if this input event's type is one that can be assigned to an input action:
     * `InputEventKey`, `InputEventMouseButton`, `InputEventJoypadButton`, `InputEventJoypadMotion`,
     * `InputEventAction`. Returns `false` for all other input event types.
     *
     * Generated from Godot docs: InputEvent.is_action_type
     */
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

    /**
     * Returns a copy of the given input event which has been offset by `local_ofs` and transformed by
     * `xform`. Relevant for events of type `InputEventMouseButton`, `InputEventMouseMotion`,
     * `InputEventScreenTouch`, `InputEventScreenDrag`, `InputEventMagnifyGesture` and
     * `InputEventPanGesture`.
     *
     * Generated from Godot docs: InputEvent.xformed_by
     */
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
