package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Vibration haptic feedback.
 *
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

    /**
     * The duration of the pulse in nanoseconds. Use `-1` for a minimum duration pulse for the current
     * XR runtime.
     *
     * Generated from Godot docs: OpenXRHapticVibration.set_duration
     */
    fun setDuration(duration: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDurationBind, segment, duration)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The duration of the pulse in nanoseconds. Use `-1` for a minimum duration pulse for the current
     * XR runtime.
     *
     * Generated from Godot docs: OpenXRHapticVibration.get_duration
     */
    fun getDuration(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getDurationBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The frequency of the pulse in Hz. `0.0` will let the XR runtime chose an optimal frequency for
     * the device used.
     *
     * Generated from Godot docs: OpenXRHapticVibration.set_frequency
     */
    fun setFrequency(frequency: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFrequencyBind, segment, frequency)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The frequency of the pulse in Hz. `0.0` will let the XR runtime chose an optimal frequency for
     * the device used.
     *
     * Generated from Godot docs: OpenXRHapticVibration.get_frequency
     */
    fun getFrequency(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFrequencyBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The amplitude of the pulse between `0.0` and `1.0`.
     *
     * Generated from Godot docs: OpenXRHapticVibration.set_amplitude
     */
    fun setAmplitude(amplitude: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAmplitudeBind, segment, amplitude)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The amplitude of the pulse between `0.0` and `1.0`.
     *
     * Generated from Godot docs: OpenXRHapticVibration.get_amplitude
     */
    fun getAmplitude(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAmplitudeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRHapticVibration? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRHapticVibration? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRHapticVibration(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRHapticVibration? =
            if (handle.address() == 0L) null else OpenXRHapticVibration(GodotHandle(handle))

        /** A new OpenXRHapticVibration, GDScript's `OpenXRHapticVibration.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRHapticVibration =
            RefCounted.owned(OpenXRHapticVibration(GodotHandle(ObjectCalls.constructObject("OpenXRHapticVibration"))))
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
