package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

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
        ObjectCalls.ptrcallWithIntArg(setPolyphonyBind, segment, voices)
    }

    /**
     * Maximum amount of simultaneous streams that can be played.
     *
     * Generated from Godot docs: AudioStreamPolyphonic.get_polyphony
     */
    fun getPolyphony(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPolyphonyBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPolyphonic? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamPolyphonic? =
            if (handle.address() == 0L) null else AudioStreamPolyphonic(GodotHandle(handle))

        private const val SET_POLYPHONY_HASH = 1286410249L
        private val setPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPolyphonic", "set_polyphony", SET_POLYPHONY_HASH)
        }

        private const val GET_POLYPHONY_HASH = 3905245786L
        private val getPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPolyphonic", "get_polyphony", GET_POLYPHONY_HASH)
        }
    }
}
