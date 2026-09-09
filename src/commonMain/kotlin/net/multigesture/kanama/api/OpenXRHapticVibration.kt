package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRHapticVibration
 */
class OpenXRHapticVibration(handle: MemorySegment) : OpenXRHapticBase(handle) {
    var duration: Long
        @JvmName("durationProperty")
        get() = getDuration()
        @JvmName("setDurationProperty")
        set(value) = setDuration(value)

    var frequency: Double
        @JvmName("frequencyProperty")
        get() = getFrequency()
        @JvmName("setFrequencyProperty")
        set(value) = setFrequency(value)

    var amplitude: Double
        @JvmName("amplitudeProperty")
        get() = getAmplitude()
        @JvmName("setAmplitudeProperty")
        set(value) = setAmplitude(value)

    fun setDuration(duration: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDurationBind, handle, duration)
    }

    fun getDuration(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getDurationBind, handle)
    }

    fun setFrequency(frequency: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFrequencyBind, handle, frequency)
    }

    fun getFrequency(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrequencyBind, handle)
    }

    fun setAmplitude(amplitude: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAmplitudeBind, handle, amplitude)
    }

    fun getAmplitude(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAmplitudeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRHapticVibration? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRHapticVibration? =
            if (handle.address() == 0L) null else OpenXRHapticVibration(handle)

        private const val SET_DURATION_HASH = 1286410249L
        private val setDurationBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "set_duration", SET_DURATION_HASH)
        }

        private const val GET_DURATION_HASH = 3905245786L
        private val getDurationBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "get_duration", GET_DURATION_HASH)
        }

        private const val SET_FREQUENCY_HASH = 373806689L
        private val setFrequencyBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "set_frequency", SET_FREQUENCY_HASH)
        }

        private const val GET_FREQUENCY_HASH = 1740695150L
        private val getFrequencyBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "get_frequency", GET_FREQUENCY_HASH)
        }

        private const val SET_AMPLITUDE_HASH = 373806689L
        private val setAmplitudeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "set_amplitude", SET_AMPLITUDE_HASH)
        }

        private const val GET_AMPLITUDE_HASH = 1740695150L
        private val getAmplitudeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "get_amplitude", GET_AMPLITUDE_HASH)
        }
    }
}
