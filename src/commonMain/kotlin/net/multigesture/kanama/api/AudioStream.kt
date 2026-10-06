package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for audio streams.
 *
 * Generated from Godot docs: AudioStream
 */
open class AudioStream(handle: GodotHandle) : Resource(handle) {
    /**
     * Returns the length of the audio stream in seconds. If this stream is an `AudioStreamRandomizer`,
     * returns the length of the last played stream. If this stream has an indefinite length (such as
     * for `AudioStreamGenerator` and `AudioStreamMicrophone`), returns `0.0`.
     *
     * Generated from Godot docs: AudioStream.get_length
     */
    fun getLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLengthBind, segment)
    }

    /**
     * Returns `true` if this audio stream only supports one channel (monophony), or `false` if the
     * audio stream supports two or more channels (polyphony).
     *
     * Generated from Godot docs: AudioStream.is_monophonic
     */
    fun isMonophonic(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMonophonicBind, segment)
    }

    /**
     * Returns a newly created `AudioStreamPlayback` intended to play this audio stream. Useful for
     * when you want to extend `_instantiate_playback` but call `instantiate_playback` from an
     * internally held AudioStream subresource. An example of this can be found in the source code for
     * `AudioStreamRandomPitch::instantiate_playback`.
     *
     * Generated from Godot docs: AudioStream.instantiate_playback
     */
    fun instantiatePlayback(): AudioStreamPlayback? {
        checkOpen()
        return AudioStreamPlayback.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.instantiatePlaybackBind, segment))
    }

    /**
     * Returns if the current `AudioStream` can be used as a sample. Only static streams can be
     * sampled.
     *
     * Generated from Godot docs: AudioStream.can_be_sampled
     */
    fun canBeSampled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.canBeSampledBind, segment)
    }

    /**
     * Generates an `AudioSample` based on the current stream.
     *
     * Generated from Godot docs: AudioStream.generate_sample
     */
    fun generateSample(): AudioSample? {
        checkOpen()
        return AudioSample.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.generateSampleBind, segment))
    }

    /**
     * Returns `true` if the stream is a collection of other streams, `false` otherwise.
     *
     * Generated from Godot docs: AudioStream.is_meta_stream
     */
    fun isMetaStream(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMetaStreamBind, segment)
    }

    /** Signal `parameter_list_changed()`; see [TypedSignal]. */
    val parameterListChanged: Signal0
        @JvmName("parameterListChangedTypedSignal")
        get() = Signal0(this, "parameter_list_changed")

    object Signals {
        const val parameterListChanged: String = "parameter_list_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStream? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStream? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStream(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStream? =
            if (handle.address() == 0L) null else AudioStream(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_LENGTH_HASH = 1740695150L
        @JvmField
        val getLengthBind =
            ObjectCalls.getMethodBind("AudioStream", "get_length", GET_LENGTH_HASH)

        private const val IS_MONOPHONIC_HASH = 36873697L
        @JvmField
        val isMonophonicBind =
            ObjectCalls.getMethodBind("AudioStream", "is_monophonic", IS_MONOPHONIC_HASH)

        private const val INSTANTIATE_PLAYBACK_HASH = 210135309L
        @JvmField
        val instantiatePlaybackBind =
            ObjectCalls.getMethodBind("AudioStream", "instantiate_playback", INSTANTIATE_PLAYBACK_HASH)

        private const val CAN_BE_SAMPLED_HASH = 36873697L
        @JvmField
        val canBeSampledBind =
            ObjectCalls.getMethodBind("AudioStream", "can_be_sampled", CAN_BE_SAMPLED_HASH)

        private const val GENERATE_SAMPLE_HASH = 2646048999L
        @JvmField
        val generateSampleBind =
            ObjectCalls.getMethodBind("AudioStream", "generate_sample", GENERATE_SAMPLE_HASH)

        private const val IS_META_STREAM_HASH = 36873697L
        @JvmField
        val isMetaStreamBind =
            ObjectCalls.getMethodBind("AudioStream", "is_meta_stream", IS_META_STREAM_HASH)
    }
}
