package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An audio stream with utilities for procedural sound generation.
 *
 * Generated from Godot docs: AudioStreamGenerator
 */
class AudioStreamGenerator(handle: MemorySegment) : AudioStream(handle) {
    var mixRateMode: Long
        @JvmName("mixRateModeProperty")
        get() = getMixRateMode()
        @JvmName("setMixRateModeProperty")
        set(value) = setMixRateMode(value)

    var mixRate: Double
        @JvmName("mixRateProperty")
        get() = getMixRate()
        @JvmName("setMixRateProperty")
        set(value) = setMixRate(value)

    var bufferLength: Double
        @JvmName("bufferLengthProperty")
        get() = getBufferLength()
        @JvmName("setBufferLengthProperty")
        set(value) = setBufferLength(value)

    fun setMixRate(hz: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMixRateBind, handle, hz)
    }

    fun getMixRate(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMixRateBind, handle)
    }

    fun setMixRateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMixRateModeBind, handle, mode)
    }

    fun getMixRateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMixRateModeBind, handle)
    }

    fun setBufferLength(seconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBufferLengthBind, handle, seconds)
    }

    fun getBufferLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBufferLengthBind, handle)
    }

    companion object {
        const val MIX_RATE_OUTPUT: Long = 0L
        const val MIX_RATE_INPUT: Long = 1L
        const val MIX_RATE_CUSTOM: Long = 2L
        const val MIX_RATE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamGenerator? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamGenerator? =
            if (handle.address() == 0L) null else AudioStreamGenerator(handle)

        private const val SET_MIX_RATE_HASH = 373806689L
        private val setMixRateBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_mix_rate", SET_MIX_RATE_HASH)
        }

        private const val GET_MIX_RATE_HASH = 1740695150L
        private val getMixRateBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_mix_rate", GET_MIX_RATE_HASH)
        }

        private const val SET_MIX_RATE_MODE_HASH = 3354885803L
        private val setMixRateModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_mix_rate_mode", SET_MIX_RATE_MODE_HASH)
        }

        private const val GET_MIX_RATE_MODE_HASH = 3537132591L
        private val getMixRateModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_mix_rate_mode", GET_MIX_RATE_MODE_HASH)
        }

        private const val SET_BUFFER_LENGTH_HASH = 373806689L
        private val setBufferLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_buffer_length", SET_BUFFER_LENGTH_HASH)
        }

        private const val GET_BUFFER_LENGTH_HASH = 1740695150L
        private val getBufferLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_buffer_length", GET_BUFFER_LENGTH_HASH)
        }
    }
}
