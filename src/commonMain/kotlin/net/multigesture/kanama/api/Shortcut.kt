package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A shortcut for binding input.
 *
 * Generated from Godot docs: Shortcut
 */
class Shortcut(handle: GodotHandle) : Resource(handle) {
    var events: List<Any?>
        @JvmName("eventsProperty")
        get() = getEvents()
        @JvmName("setEventsProperty")
        set(value) = setEvents(value)

    /**
     * The shortcut's `InputEvent` array. Generally the `InputEvent` used is an `InputEventKey`, though
     * it can be any `InputEvent`, including an `InputEventAction`.
     *
     * Generated from Godot docs: Shortcut.set_events
     */
    fun setEvents(events: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setEventsBind, segment, events)
    }

    /**
     * The shortcut's `InputEvent` array. Generally the `InputEvent` used is an `InputEventKey`, though
     * it can be any `InputEvent`, including an `InputEventAction`.
     *
     * Generated from Godot docs: Shortcut.get_events
     */
    fun getEvents(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getEventsBind, segment)
    }

    /**
     * Returns whether `events` contains an `InputEvent` which is valid.
     *
     * Generated from Godot docs: Shortcut.has_valid_event
     */
    fun hasValidEvent(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasValidEventBind, segment)
    }

    /**
     * Returns whether any `InputEvent` in `events` equals `event`. This uses `InputEvent.is_match` to
     * compare events.
     *
     * Generated from Godot docs: Shortcut.matches_event
     */
    fun matchesEvent(event: InputEvent?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetBool(Binds.matchesEventBind, segment, event?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the shortcut's first valid `InputEvent` as a `String`.
     *
     * Generated from Godot docs: Shortcut.get_as_text
     */
    fun getAsText(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getAsTextBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Shortcut? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Shortcut? =
            if (handle.address() == 0L) null else RefCounted.owned(Shortcut(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Shortcut? =
            if (handle.address() == 0L) null else Shortcut(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_EVENTS_HASH = 381264803L
        @JvmField
        val setEventsBind =
            ObjectCalls.getMethodBind("Shortcut", "set_events", SET_EVENTS_HASH)

        private const val GET_EVENTS_HASH = 3995934104L
        @JvmField
        val getEventsBind =
            ObjectCalls.getMethodBind("Shortcut", "get_events", GET_EVENTS_HASH)

        private const val HAS_VALID_EVENT_HASH = 36873697L
        @JvmField
        val hasValidEventBind =
            ObjectCalls.getMethodBind("Shortcut", "has_valid_event", HAS_VALID_EVENT_HASH)

        private const val MATCHES_EVENT_HASH = 3738334489L
        @JvmField
        val matchesEventBind =
            ObjectCalls.getMethodBind("Shortcut", "matches_event", MATCHES_EVENT_HASH)

        private const val GET_AS_TEXT_HASH = 201670096L
        @JvmField
        val getAsTextBind =
            ObjectCalls.getMethodBind("Shortcut", "get_as_text", GET_AS_TEXT_HASH)
    }
}
