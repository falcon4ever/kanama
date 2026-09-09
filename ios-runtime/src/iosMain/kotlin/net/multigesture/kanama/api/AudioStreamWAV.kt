package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamWAV
 */
class AudioStreamWAV(handle: MemorySegment) : AudioStream(handle) {
    var format: Long
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var loopMode: Long
        @JvmName("loopModeProperty")
        get() = getLoopMode()
        @JvmName("setLoopModeProperty")
        set(value) = setLoopMode(value)

    var loopBegin: Int
        @JvmName("loopBeginProperty")
        get() = getLoopBegin()
        @JvmName("setLoopBeginProperty")
        set(value) = setLoopBegin(value)

    var loopEnd: Int
        @JvmName("loopEndProperty")
        get() = getLoopEnd()
        @JvmName("setLoopEndProperty")
        set(value) = setLoopEnd(value)

    var mixRate: Int
        @JvmName("mixRateProperty")
        get() = getMixRate()
        @JvmName("setMixRateProperty")
        set(value) = setMixRate(value)

    var stereo: Boolean
        @JvmName("stereoProperty")
        get() = isStereo()
        @JvmName("setStereoProperty")
        set(value) = setStereo(value)

    fun setFormat(format: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFormatBind, handle, format)
    }

    fun getFormat(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    fun setLoopMode(loopMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setLoopModeBind, handle, loopMode)
    }

    fun getLoopMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getLoopModeBind, handle)
    }

    fun setLoopBegin(loopBegin: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setLoopBeginBind, handle, loopBegin)
    }

    fun getLoopBegin(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLoopBeginBind, handle)
    }

    fun setLoopEnd(loopEnd: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setLoopEndBind, handle, loopEnd)
    }

    fun getLoopEnd(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLoopEndBind, handle)
    }

    fun setMixRate(mixRate: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMixRateBind, handle, mixRate)
    }

    fun getMixRate(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMixRateBind, handle)
    }

    fun setStereo(stereo: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setStereoBind, handle, stereo)
    }

    fun isStereo(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isStereoBind, handle)
    }

    fun saveToWav(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(saveToWavBind, handle, path)
    }

    companion object {
        const val FORMAT_8_BITS: Long = 0L
        const val FORMAT_16_BITS: Long = 1L
        const val FORMAT_IMA_ADPCM: Long = 2L
        const val FORMAT_QOA: Long = 3L
        const val LOOP_DISABLED: Long = 0L
        const val LOOP_FORWARD: Long = 1L
        const val LOOP_PINGPONG: Long = 2L
        const val LOOP_BACKWARD: Long = 3L

        fun fromHandle(handle: MemorySegment): AudioStreamWAV? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamWAV? =
            if (handle.address() == 0L) null else AudioStreamWAV(handle)

        private const val SET_FORMAT_HASH = 60648488L
        private val setFormatBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_format", SET_FORMAT_HASH)
        }

        private const val GET_FORMAT_HASH = 3151724922L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_format", GET_FORMAT_HASH)
        }

        private const val SET_LOOP_MODE_HASH = 2444882972L
        private val setLoopModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_mode", SET_LOOP_MODE_HASH)
        }

        private const val GET_LOOP_MODE_HASH = 393560655L
        private val getLoopModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_mode", GET_LOOP_MODE_HASH)
        }

        private const val SET_LOOP_BEGIN_HASH = 1286410249L
        private val setLoopBeginBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_begin", SET_LOOP_BEGIN_HASH)
        }

        private const val GET_LOOP_BEGIN_HASH = 3905245786L
        private val getLoopBeginBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_begin", GET_LOOP_BEGIN_HASH)
        }

        private const val SET_LOOP_END_HASH = 1286410249L
        private val setLoopEndBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_end", SET_LOOP_END_HASH)
        }

        private const val GET_LOOP_END_HASH = 3905245786L
        private val getLoopEndBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_end", GET_LOOP_END_HASH)
        }

        private const val SET_MIX_RATE_HASH = 1286410249L
        private val setMixRateBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_mix_rate", SET_MIX_RATE_HASH)
        }

        private const val GET_MIX_RATE_HASH = 3905245786L
        private val getMixRateBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_mix_rate", GET_MIX_RATE_HASH)
        }

        private const val SET_STEREO_HASH = 2586408642L
        private val setStereoBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_stereo", SET_STEREO_HASH)
        }

        private const val IS_STEREO_HASH = 36873697L
        private val isStereoBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "is_stereo", IS_STEREO_HASH)
        }

        private const val SAVE_TO_WAV_HASH = 166001499L
        private val saveToWavBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "save_to_wav", SAVE_TO_WAV_HASH)
        }
    }
}
