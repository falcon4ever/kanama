package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Audio effect used for recording the sound from an audio bus.
 *
 * Generated from Godot docs: AudioEffectRecord
 */
class AudioEffectRecord(handle: GodotHandle) : AudioEffect(handle) {
    var format: AudioStreamWAV.Format
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    /**
     * If `true`, the sound will be recorded. Note that restarting the recording will remove the
     * previously recorded sample.
     *
     * Generated from Godot docs: AudioEffectRecord.set_recording_active
     */
    fun setRecordingActive(record: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setRecordingActiveBind, segment, record)
    }

    /**
     * Returns whether the recording is active or not.
     *
     * Generated from Godot docs: AudioEffectRecord.is_recording_active
     */
    fun isRecordingActive(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRecordingActiveBind, segment)
    }

    /**
     * Specifies the format in which the sample will be recorded.
     *
     * Generated from Godot docs: AudioEffectRecord.set_format
     */
    fun setFormat(format: AudioStreamWAV.Format) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFormatBind, segment, format.value)
    }

    /**
     * Specifies the format in which the sample will be recorded.
     *
     * Generated from Godot docs: AudioEffectRecord.get_format
     */
    fun getFormat(): AudioStreamWAV.Format {
        checkOpen()
        return AudioStreamWAV.Format(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatBind, segment))
    }

    /**
     * Returns the recorded sample.
     *
     * Generated from Godot docs: AudioEffectRecord.get_recording
     */
    fun getRecording(): AudioStreamWAV? {
        checkOpen()
        return AudioStreamWAV.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getRecordingBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectRecord? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectRecord? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectRecord(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectRecord? =
            if (handle.address() == 0L) null else AudioEffectRecord(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RECORDING_ACTIVE_HASH = 2586408642L
        @JvmField
        val setRecordingActiveBind =
            ObjectCalls.getMethodBind("AudioEffectRecord", "set_recording_active", SET_RECORDING_ACTIVE_HASH)

        private const val IS_RECORDING_ACTIVE_HASH = 36873697L
        @JvmField
        val isRecordingActiveBind =
            ObjectCalls.getMethodBind("AudioEffectRecord", "is_recording_active", IS_RECORDING_ACTIVE_HASH)

        private const val SET_FORMAT_HASH = 60648488L
        @JvmField
        val setFormatBind =
            ObjectCalls.getMethodBind("AudioEffectRecord", "set_format", SET_FORMAT_HASH)

        private const val GET_FORMAT_HASH = 3151724922L
        @JvmField
        val getFormatBind =
            ObjectCalls.getMethodBind("AudioEffectRecord", "get_format", GET_FORMAT_HASH)

        private const val GET_RECORDING_HASH = 2964110865L
        @JvmField
        val getRecordingBind =
            ObjectCalls.getMethodBind("AudioEffectRecord", "get_recording", GET_RECORDING_HASH)
    }
}
