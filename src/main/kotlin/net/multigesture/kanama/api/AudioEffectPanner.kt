package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a panner audio effect to an audio bus. Pans the sound left or right.
 *
 * Generated from Godot docs: AudioEffectPanner
 */
class AudioEffectPanner(handle: MemorySegment) : AudioEffect(handle) {
    var pan: Double
        @JvmName("panProperty")
        get() = getPan()
        @JvmName("setPanProperty")
        set(value) = setPan(value)

    fun setPan(cpanume: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPanBind, handle, cpanume)
    }

    fun getPan(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPanBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectPanner? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectPanner? =
            if (handle.address() == 0L) null else AudioEffectPanner(handle)

        private const val SET_PAN_HASH = 373806689L
        private val setPanBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPanner", "set_pan", SET_PAN_HASH)
        }

        private const val GET_PAN_HASH = 1740695150L
        private val getPanBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPanner", "get_pan", GET_PAN_HASH)
        }
    }
}
