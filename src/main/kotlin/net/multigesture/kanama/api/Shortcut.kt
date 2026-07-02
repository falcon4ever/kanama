package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A shortcut for binding input.
 *
 * Generated from Godot docs: Shortcut
 */
class Shortcut(handle: MemorySegment) : Resource(handle) {
    var events: List<Any?>
        @JvmName("eventsProperty")
        get() = getEvents()
        @JvmName("setEventsProperty")
        set(value) = setEvents(value)

    fun setEvents(events: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setEventsBind, handle, events)
    }

    fun getEvents(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getEventsBind, handle)
    }

    fun hasValidEvent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasValidEventBind, handle)
    }

    fun matchesEvent(event: InputEvent?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(matchesEventBind, handle, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getAsText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getAsTextBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Shortcut? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Shortcut? =
            if (handle.address() == 0L) null else Shortcut(handle)

        private const val SET_EVENTS_HASH = 381264803L
        private val setEventsBind by lazy {
            ObjectCalls.getMethodBind("Shortcut", "set_events", SET_EVENTS_HASH)
        }

        private const val GET_EVENTS_HASH = 3995934104L
        private val getEventsBind by lazy {
            ObjectCalls.getMethodBind("Shortcut", "get_events", GET_EVENTS_HASH)
        }

        private const val HAS_VALID_EVENT_HASH = 36873697L
        private val hasValidEventBind by lazy {
            ObjectCalls.getMethodBind("Shortcut", "has_valid_event", HAS_VALID_EVENT_HASH)
        }

        private const val MATCHES_EVENT_HASH = 3738334489L
        private val matchesEventBind by lazy {
            ObjectCalls.getMethodBind("Shortcut", "matches_event", MATCHES_EVENT_HASH)
        }

        private const val GET_AS_TEXT_HASH = 201670096L
        private val getAsTextBind by lazy {
            ObjectCalls.getMethodBind("Shortcut", "get_as_text", GET_AS_TEXT_HASH)
        }
    }
}
