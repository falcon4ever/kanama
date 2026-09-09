package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamPolyphonic
 */
class AudioStreamPolyphonic(handle: MemorySegment) : AudioStream(handle) {
    var polyphony: Int
        @JvmName("polyphonyProperty")
        get() = getPolyphony()
        @JvmName("setPolyphonyProperty")
        set(value) = setPolyphony(value)

    fun setPolyphony(voices: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setPolyphonyBind, handle, voices)
    }

    fun getPolyphony(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPolyphonyBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): AudioStreamPolyphonic? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPolyphonic? =
            if (handle.address() == 0L) null else AudioStreamPolyphonic(handle)

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
