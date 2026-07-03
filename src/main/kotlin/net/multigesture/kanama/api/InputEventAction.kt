package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An input event type for actions.
 *
 * Generated from Godot docs: InputEventAction
 */
class InputEventAction(handle: MemorySegment) : InputEvent(handle) {
    var action: String
        @JvmName("actionProperty")
        get() = getAction()
        @JvmName("setActionProperty")
        set(value) = setAction(value)

    var strength: Double
        @JvmName("strengthProperty")
        get() = getStrength()
        @JvmName("setStrengthProperty")
        set(value) = setStrength(value)

    var eventIndex: Int
        @JvmName("eventIndexProperty")
        get() = getEventIndex()
        @JvmName("setEventIndexProperty")
        set(value) = setEventIndex(value)

    /**
     * The action's name. This is usually the name of an existing action in the `InputMap` which you
     * want this custom event to match.
     *
     * Generated from Godot docs: InputEventAction.set_action
     */
    fun setAction(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(setActionBind, handle, action)
    }

    /**
     * The action's name. This is usually the name of an existing action in the `InputMap` which you
     * want this custom event to match.
     *
     * Generated from Godot docs: InputEventAction.get_action
     */
    fun getAction(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getActionBind, handle)
    }

    /**
     * If `true`, the action's state is pressed. If `false`, the action's state is released.
     *
     * Generated from Godot docs: InputEventAction.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, handle, pressed)
    }

    /**
     * The action's strength between 0 and 1. This value is considered as equal to 0 if pressed is
     * `false`. The event strength allows faking analog joypad motion events, by specifying how
     * strongly the joypad axis is bent or pressed.
     *
     * Generated from Godot docs: InputEventAction.set_strength
     */
    fun setStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStrengthBind, handle, strength)
    }

    /**
     * The action's strength between 0 and 1. This value is considered as equal to 0 if pressed is
     * `false`. The event strength allows faking analog joypad motion events, by specifying how
     * strongly the joypad axis is bent or pressed.
     *
     * Generated from Godot docs: InputEventAction.get_strength
     */
    fun getStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStrengthBind, handle)
    }

    /**
     * The real event index in action this event corresponds to (from events defined for this action in
     * the `InputMap`). If `-1`, a unique ID will be used and actions pressed with this ID will need to
     * be released with another `InputEventAction`.
     *
     * Generated from Godot docs: InputEventAction.set_event_index
     */
    fun setEventIndex(index: Int) {
        ObjectCalls.ptrcallWithIntArg(setEventIndexBind, handle, index)
    }

    /**
     * The real event index in action this event corresponds to (from events defined for this action in
     * the `InputMap`). If `-1`, a unique ID will be used and actions pressed with this ID will need to
     * be released with another `InputEventAction`.
     *
     * Generated from Godot docs: InputEventAction.get_event_index
     */
    fun getEventIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEventIndexBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventAction? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventAction? =
            if (handle.address() == 0L) null else InputEventAction(handle)

        private const val SET_ACTION_HASH = 3304788590L
        private val setActionBind by lazy {
            ObjectCalls.getMethodBind("InputEventAction", "set_action", SET_ACTION_HASH)
        }

        private const val GET_ACTION_HASH = 2002593661L
        private val getActionBind by lazy {
            ObjectCalls.getMethodBind("InputEventAction", "get_action", GET_ACTION_HASH)
        }

        private const val SET_PRESSED_HASH = 2586408642L
        private val setPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventAction", "set_pressed", SET_PRESSED_HASH)
        }

        private const val SET_STRENGTH_HASH = 373806689L
        private val setStrengthBind by lazy {
            ObjectCalls.getMethodBind("InputEventAction", "set_strength", SET_STRENGTH_HASH)
        }

        private const val GET_STRENGTH_HASH = 1740695150L
        private val getStrengthBind by lazy {
            ObjectCalls.getMethodBind("InputEventAction", "get_strength", GET_STRENGTH_HASH)
        }

        private const val SET_EVENT_INDEX_HASH = 1286410249L
        private val setEventIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventAction", "set_event_index", SET_EVENT_INDEX_HASH)
        }

        private const val GET_EVENT_INDEX_HASH = 3905245786L
        private val getEventIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventAction", "get_event_index", GET_EVENT_INDEX_HASH)
        }
    }
}
