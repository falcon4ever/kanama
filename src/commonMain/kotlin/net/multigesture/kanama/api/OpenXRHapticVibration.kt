package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRHapticVibration
 */
class OpenXRHapticVibration(handle: GodotHandle) : OpenXRHapticBase(handle) {
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
        ObjectCalls.ptrcallWithLongArg(Binds.setDurationBind, segment, duration)
    }

    fun getDuration(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getDurationBind, segment)
    }

    fun setFrequency(frequency: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFrequencyBind, segment, frequency)
    }

    fun getFrequency(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFrequencyBind, segment)
    }

    fun setAmplitude(amplitude: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAmplitudeBind, segment, amplitude)
    }

    fun getAmplitude(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAmplitudeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRHapticVibration? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRHapticVibration? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRHapticVibration(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRHapticVibration? =
            if (handle.address() == 0L) null else OpenXRHapticVibration(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DURATION_HASH = 1286410249L
        @JvmField
        val setDurationBind =
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "set_duration", SET_DURATION_HASH)

        private const val GET_DURATION_HASH = 3905245786L
        @JvmField
        val getDurationBind =
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "get_duration", GET_DURATION_HASH)

        private const val SET_FREQUENCY_HASH = 373806689L
        @JvmField
        val setFrequencyBind =
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "set_frequency", SET_FREQUENCY_HASH)

        private const val GET_FREQUENCY_HASH = 1740695150L
        @JvmField
        val getFrequencyBind =
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "get_frequency", GET_FREQUENCY_HASH)

        private const val SET_AMPLITUDE_HASH = 373806689L
        @JvmField
        val setAmplitudeBind =
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "set_amplitude", SET_AMPLITUDE_HASH)

        private const val GET_AMPLITUDE_HASH = 1740695150L
        @JvmField
        val getAmplitudeBind =
            ObjectCalls.getMethodBind("OpenXRHapticVibration", "get_amplitude", GET_AMPLITUDE_HASH)
    }
}
