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

    fun setAction(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(setActionBind, handle, action)
    }

    fun getAction(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getActionBind, handle)
    }

    fun setPressed(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, handle, pressed)
    }

    fun setStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStrengthBind, handle, strength)
    }

    fun getStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStrengthBind, handle)
    }

    fun setEventIndex(index: Int) {
        ObjectCalls.ptrcallWithIntArg(setEventIndexBind, handle, index)
    }

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
