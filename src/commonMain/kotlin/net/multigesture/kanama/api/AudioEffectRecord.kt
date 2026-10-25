package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

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
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns whether the recording is active or not.
     *
     * Generated from Godot docs: AudioEffectRecord.is_recording_active
     */
    fun isRecordingActive(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isRecordingActiveBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Specifies the format in which the sample will be recorded.
     *
     * Generated from Godot docs: AudioEffectRecord.set_format
     */
    fun setFormat(format: AudioStreamWAV.Format) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFormatBind, segment, format.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Specifies the format in which the sample will be recorded.
     *
     * Generated from Godot docs: AudioEffectRecord.get_format
     */
    fun getFormat(): AudioStreamWAV.Format {
        checkOpen()
        val ret = AudioStreamWAV.Format(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the recorded sample.
     *
     * Generated from Godot docs: AudioEffectRecord.get_recording
     */
    fun getRecording(): AudioStreamWAV? {
        checkOpen()
        val ret = AudioStreamWAV.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getRecordingBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectRecord? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectRecord? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectRecord(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectRecord? =
            if (handle.address() == 0L) null else AudioEffectRecord(GodotHandle(handle))

        /** A new AudioEffectRecord, GDScript's `AudioEffectRecord.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AudioEffectRecord =
            RefCounted.owned(AudioEffectRecord(GodotHandle(requireConstructed(ObjectCalls.constructObject("AudioEffectRecord"), "AudioEffectRecord"))))
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
