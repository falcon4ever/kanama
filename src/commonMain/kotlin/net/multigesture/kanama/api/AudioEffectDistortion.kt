package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a distortion audio effect to an audio bus. Remaps audio samples using a nonlinear function
 * to achieve a distorted sound.
 *
 * Generated from Godot docs: AudioEffectDistortion
 */
class AudioEffectDistortion(handle: GodotHandle) : AudioEffect(handle) {
    var mode: AudioEffectDistortion.Mode
        @JvmName("modeProperty")
        get() = getMode()
        @JvmName("setModeProperty")
        set(value) = setMode(value)

    var preGain: Double
        @JvmName("preGainProperty")
        get() = getPreGain()
        @JvmName("setPreGainProperty")
        set(value) = setPreGain(value)

    var keepHfHz: Double
        @JvmName("keepHfHzProperty")
        get() = getKeepHfHz()
        @JvmName("setKeepHfHzProperty")
        set(value) = setKeepHfHz(value)

    var drive: Double
        @JvmName("driveProperty")
        get() = getDrive()
        @JvmName("setDriveProperty")
        set(value) = setDrive(value)

    var postGain: Double
        @JvmName("postGainProperty")
        get() = getPostGain()
        @JvmName("setPostGainProperty")
        set(value) = setPostGain(value)

    /**
     * Distortion type. Changes the nonlinear function used to distort the waveform. See `Mode`.
     *
     * Generated from Godot docs: AudioEffectDistortion.set_mode
     */
    fun setMode(mode: AudioEffectDistortion.Mode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setModeBind, segment, mode.value)
    }

    /**
     * Distortion type. Changes the nonlinear function used to distort the waveform. See `Mode`.
     *
     * Generated from Godot docs: AudioEffectDistortion.get_mode
     */
    fun getMode(): AudioEffectDistortion.Mode {
        checkOpen()
        return AudioEffectDistortion.Mode(ObjectCalls.ptrcallNoArgsRetLong(getModeBind, segment))
    }

    /**
     * Gain before the effect, in dB. Value can range from -60 to 60.
     *
     * Generated from Godot docs: AudioEffectDistortion.set_pre_gain
     */
    fun setPreGain(preGain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPreGainBind, segment, preGain)
    }

    /**
     * Gain before the effect, in dB. Value can range from -60 to 60.
     *
     * Generated from Godot docs: AudioEffectDistortion.get_pre_gain
     */
    fun getPreGain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPreGainBind, segment)
    }

    /**
     * High-pass filter, in Hz. Frequencies higher than this value will not be affected by the
     * distortion. Value can range from 1 to 20000.
     *
     * Generated from Godot docs: AudioEffectDistortion.set_keep_hf_hz
     */
    fun setKeepHfHz(keepHfHz: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setKeepHfHzBind, segment, keepHfHz)
    }

    /**
     * High-pass filter, in Hz. Frequencies higher than this value will not be affected by the
     * distortion. Value can range from 1 to 20000.
     *
     * Generated from Godot docs: AudioEffectDistortion.get_keep_hf_hz
     */
    fun getKeepHfHz(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getKeepHfHzBind, segment)
    }

    /**
     * Distortion intensity. Controls how much of the input audio is affected by the distortion curve
     * by moving from a linear function to a nonlinear one. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectDistortion.set_drive
     */
    fun setDrive(drive: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDriveBind, segment, drive)
    }

    /**
     * Distortion intensity. Controls how much of the input audio is affected by the distortion curve
     * by moving from a linear function to a nonlinear one. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectDistortion.get_drive
     */
    fun getDrive(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDriveBind, segment)
    }

    /**
     * Gain after the effect, in dB. Value can range from -80 to 24.
     *
     * Generated from Godot docs: AudioEffectDistortion.set_post_gain
     */
    fun setPostGain(postGain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPostGainBind, segment, postGain)
    }

    /**
     * Gain after the effect, in dB. Value can range from -80 to 24.
     *
     * Generated from Godot docs: AudioEffectDistortion.get_post_gain
     */
    fun getPostGain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPostGainBind, segment)
    }

    /**
     * Godot's `AudioEffectDistortion.Mode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`AudioEffectDistortion.Mode.<NAME>`).
     *
     * Generated from Godot docs: AudioEffectDistortion.Mode
     */
    @JvmInline
    value class Mode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Flattens the waveform at 0 dB in a sharp manner. `drive` increases amplitude of samples
             * exponentially. This mode functions as a hard clipper if `drive` is set to 0, and is the only
             * mode that clips audio signals at 0 dB.
             *
             * Generated from Godot docs: AudioEffectDistortion.MODE_CLIP
             */
            val CLIP: Mode get() = Mode(0L)
            /**
             * Flattens the waveform in a smooth manner, following an arctangent curve. The audio decreases in
             * volume, before flattening peaks to `PI * 4.0` (linear value), if it was normalized beforehand.
             *
             * Generated from Godot docs: AudioEffectDistortion.MODE_ATAN
             */
            val ATAN: Mode get() = Mode(1L)
            /**
             * Decreases audio bit depth to achieve a low-resolution audio signal, going from 16-bit to 2-bit.
             * Can be used to emulate the sound of early digital audio devices.
             *
             * Generated from Godot docs: AudioEffectDistortion.MODE_LOFI
             */
            val LOFI: Mode get() = Mode(2L)
            /**
             * Emulates the warm distortion produced by a field effect transistor, which is commonly used in
             * solid-state musical instrument amplifiers. `drive` has no effect in this mode.
             *
             * Generated from Godot docs: AudioEffectDistortion.MODE_OVERDRIVE
             */
            val OVERDRIVE: Mode get() = Mode(3L)
            /**
             * Flattens the waveform in a smooth manner, until it reaches a sharp peak at `drive = 1`,
             * following a generic absolute sigmoid function.
             *
             * Generated from Godot docs: AudioEffectDistortion.MODE_WAVESHAPE
             */
            val WAVESHAPE: Mode get() = Mode(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectDistortion? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectDistortion? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectDistortion(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectDistortion? =
            if (handle.address() == 0L) null else AudioEffectDistortion(GodotHandle(handle))

        private const val SET_MODE_HASH = 1314744793L
        private val setModeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "set_mode", SET_MODE_HASH)
        }

        private const val GET_MODE_HASH = 809118343L
        private val getModeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "get_mode", GET_MODE_HASH)
        }

        private const val SET_PRE_GAIN_HASH = 373806689L
        private val setPreGainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "set_pre_gain", SET_PRE_GAIN_HASH)
        }

        private const val GET_PRE_GAIN_HASH = 1740695150L
        private val getPreGainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "get_pre_gain", GET_PRE_GAIN_HASH)
        }

        private const val SET_KEEP_HF_HZ_HASH = 373806689L
        private val setKeepHfHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "set_keep_hf_hz", SET_KEEP_HF_HZ_HASH)
        }

        private const val GET_KEEP_HF_HZ_HASH = 1740695150L
        private val getKeepHfHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "get_keep_hf_hz", GET_KEEP_HF_HZ_HASH)
        }

        private const val SET_DRIVE_HASH = 373806689L
        private val setDriveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "set_drive", SET_DRIVE_HASH)
        }

        private const val GET_DRIVE_HASH = 1740695150L
        private val getDriveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "get_drive", GET_DRIVE_HASH)
        }

        private const val SET_POST_GAIN_HASH = 373806689L
        private val setPostGainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "set_post_gain", SET_POST_GAIN_HASH)
        }

        private const val GET_POST_GAIN_HASH = 1740695150L
        private val getPostGainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDistortion", "get_post_gain", GET_POST_GAIN_HASH)
        }
    }
}
