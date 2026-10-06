package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * AudioStream that lets the user play custom streams at any time from code, simultaneously using a
 * single player.
 *
 * Generated from Godot docs: AudioStreamPolyphonic
 */
class AudioStreamPolyphonic(handle: GodotHandle) : AudioStream(handle) {
    var polyphony: Int
        @JvmName("polyphonyProperty")
        get() = getPolyphony()
        @JvmName("setPolyphonyProperty")
        set(value) = setPolyphony(value)

    /**
     * Maximum amount of simultaneous streams that can be played.
     *
     * Generated from Godot docs: AudioStreamPolyphonic.set_polyphony
     */
    fun setPolyphony(voices: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setPolyphonyBind, segment, voices)
    }

    /**
     * Maximum amount of simultaneous streams that can be played.
     *
     * Generated from Godot docs: AudioStreamPolyphonic.get_polyphony
     */
    fun getPolyphony(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPolyphonyBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPolyphonic? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamPolyphonic? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamPolyphonic(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamPolyphonic? =
            if (handle.address() == 0L) null else AudioStreamPolyphonic(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_POLYPHONY_HASH = 1286410249L
        @JvmField
        val setPolyphonyBind =
            ObjectCalls.getMethodBind("AudioStreamPolyphonic", "set_polyphony", SET_POLYPHONY_HASH)

        private const val GET_POLYPHONY_HASH = 3905245786L
        @JvmField
        val getPolyphonyBind =
            ObjectCalls.getMethodBind("AudioStreamPolyphonic", "get_polyphony", GET_POLYPHONY_HASH)
    }
}
