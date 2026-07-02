package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Audio effect used for recording the sound from an audio bus.
 *
 * Generated from Godot docs: AudioEffectRecord
 */
class AudioEffectRecord(handle: MemorySegment) : AudioEffect(handle) {
    var format: Long
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    fun setRecordingActive(record: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRecordingActiveBind, handle, record)
    }

    fun isRecordingActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRecordingActiveBind, handle)
    }

    fun setFormat(format: Long) {
        ObjectCalls.ptrcallWithLongArg(setFormatBind, handle, format)
    }

    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    fun getRecording(): AudioStreamWAV? {
        return AudioStreamWAV.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRecordingBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectRecord? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectRecord? =
            if (handle.address() == 0L) null else AudioEffectRecord(handle)

        private const val SET_RECORDING_ACTIVE_HASH = 2586408642L
        private val setRecordingActiveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectRecord", "set_recording_active", SET_RECORDING_ACTIVE_HASH)
        }

        private const val IS_RECORDING_ACTIVE_HASH = 36873697L
        private val isRecordingActiveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectRecord", "is_recording_active", IS_RECORDING_ACTIVE_HASH)
        }

        private const val SET_FORMAT_HASH = 60648488L
        private val setFormatBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectRecord", "set_format", SET_FORMAT_HASH)
        }

        private const val GET_FORMAT_HASH = 3151724922L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectRecord", "get_format", GET_FORMAT_HASH)
        }

        private const val GET_RECORDING_HASH = 2964110865L
        private val getRecordingBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectRecord", "get_recording", GET_RECORDING_HASH)
        }
    }
}
