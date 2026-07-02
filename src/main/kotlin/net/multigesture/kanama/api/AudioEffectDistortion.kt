package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a distortion audio effect to an audio bus. Remaps audio samples using a nonlinear function
 * to achieve a distorted sound.
 *
 * Generated from Godot docs: AudioEffectDistortion
 */
class AudioEffectDistortion(handle: MemorySegment) : AudioEffect(handle) {
    var mode: Long
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

    fun setMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setModeBind, handle, mode)
    }

    fun getMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getModeBind, handle)
    }

    fun setPreGain(preGain: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPreGainBind, handle, preGain)
    }

    fun getPreGain(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPreGainBind, handle)
    }

    fun setKeepHfHz(keepHfHz: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setKeepHfHzBind, handle, keepHfHz)
    }

    fun getKeepHfHz(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getKeepHfHzBind, handle)
    }

    fun setDrive(drive: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDriveBind, handle, drive)
    }

    fun getDrive(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDriveBind, handle)
    }

    fun setPostGain(postGain: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPostGainBind, handle, postGain)
    }

    fun getPostGain(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPostGainBind, handle)
    }

    companion object {
        const val MODE_CLIP: Long = 0L
        const val MODE_ATAN: Long = 1L
        const val MODE_LOFI: Long = 2L
        const val MODE_OVERDRIVE: Long = 3L
        const val MODE_WAVESHAPE: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectDistortion? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectDistortion? =
            if (handle.address() == 0L) null else AudioEffectDistortion(handle)

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
