package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * An input event type for actions.
 *
 * Generated from Godot docs: InputEventAction
 */
class InputEventAction(handle: GodotHandle) : InputEvent(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(Binds.setActionBind, segment, action)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The action's name. This is usually the name of an existing action in the `InputMap` which you
     * want this custom event to match.
     *
     * Generated from Godot docs: InputEventAction.get_action
     */
    fun getAction(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetStringName(Binds.getActionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the action's state is pressed. If `false`, the action's state is released.
     *
     * Generated from Godot docs: InputEventAction.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPressedBind, segment, pressed)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The action's strength between 0 and 1. This value is considered as equal to 0 if pressed is
     * `false`. The event strength allows faking analog joypad motion events, by specifying how
     * strongly the joypad axis is bent or pressed.
     *
     * Generated from Godot docs: InputEventAction.set_strength
     */
    fun setStrength(strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStrengthBind, segment, strength)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The action's strength between 0 and 1. This value is considered as equal to 0 if pressed is
     * `false`. The event strength allows faking analog joypad motion events, by specifying how
     * strongly the joypad axis is bent or pressed.
     *
     * Generated from Godot docs: InputEventAction.get_strength
     */
    fun getStrength(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStrengthBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The real event index in action this event corresponds to (from events defined for this action in
     * the `InputMap`). If `-1`, a unique ID will be used and actions pressed with this ID will need to
     * be released with another `InputEventAction`.
     *
     * Generated from Godot docs: InputEventAction.set_event_index
     */
    fun setEventIndex(index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setEventIndexBind, segment, index)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The real event index in action this event corresponds to (from events defined for this action in
     * the `InputMap`). If `-1`, a unique ID will be used and actions pressed with this ID will need to
     * be released with another `InputEventAction`.
     *
     * Generated from Godot docs: InputEventAction.get_event_index
     */
    fun getEventIndex(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getEventIndexBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventAction? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventAction? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventAction(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventAction? =
            if (handle.address() == 0L) null else InputEventAction(GodotHandle(handle))

        /** A new InputEventAction, GDScript's `InputEventAction.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): InputEventAction =
            RefCounted.owned(InputEventAction(GodotHandle(requireConstructed(ObjectCalls.constructObject("InputEventAction"), "InputEventAction"))))
    }

    private object Binds {
        private const val SET_ACTION_HASH = 3304788590L
        @JvmField
        val setActionBind =
            ObjectCalls.getMethodBind("InputEventAction", "set_action", SET_ACTION_HASH)

        private const val GET_ACTION_HASH = 2002593661L
        @JvmField
        val getActionBind =
            ObjectCalls.getMethodBind("InputEventAction", "get_action", GET_ACTION_HASH)

        private const val SET_PRESSED_HASH = 2586408642L
        @JvmField
        val setPressedBind =
            ObjectCalls.getMethodBind("InputEventAction", "set_pressed", SET_PRESSED_HASH)

        private const val SET_STRENGTH_HASH = 373806689L
        @JvmField
        val setStrengthBind =
            ObjectCalls.getMethodBind("InputEventAction", "set_strength", SET_STRENGTH_HASH)

        private const val GET_STRENGTH_HASH = 1740695150L
        @JvmField
        val getStrengthBind =
            ObjectCalls.getMethodBind("InputEventAction", "get_strength", GET_STRENGTH_HASH)

        private const val SET_EVENT_INDEX_HASH = 1286410249L
        @JvmField
        val setEventIndexBind =
            ObjectCalls.getMethodBind("InputEventAction", "set_event_index", SET_EVENT_INDEX_HASH)

        private const val GET_EVENT_INDEX_HASH = 3905245786L
        @JvmField
        val getEventIndexBind =
            ObjectCalls.getMethodBind("InputEventAction", "get_event_index", GET_EVENT_INDEX_HASH)
    }
}
