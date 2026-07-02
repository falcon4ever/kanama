package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for audio streams.
 *
 * Generated from Godot docs: AudioStream
 */
open class AudioStream(handle: MemorySegment) : Resource(handle) {
    fun getLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, handle)
    }

    fun isMonophonic(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMonophonicBind, handle)
    }

    fun instantiatePlayback(): AudioStreamPlayback? {
        return AudioStreamPlayback.wrap(ObjectCalls.ptrcallNoArgsRetObject(instantiatePlaybackBind, handle))
    }

    fun canBeSampled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(canBeSampledBind, handle)
    }

    fun generateSample(): AudioSample? {
        return AudioSample.wrap(ObjectCalls.ptrcallNoArgsRetObject(generateSampleBind, handle))
    }

    fun isMetaStream(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMetaStreamBind, handle)
    }

    object Signals {
        const val parameterListChanged: String = "parameter_list_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStream? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStream? =
            if (handle.address() == 0L) null else AudioStream(handle)

        private const val GET_LENGTH_HASH = 1740695150L
        private val getLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStream", "get_length", GET_LENGTH_HASH)
        }

        private const val IS_MONOPHONIC_HASH = 36873697L
        private val isMonophonicBind by lazy {
            ObjectCalls.getMethodBind("AudioStream", "is_monophonic", IS_MONOPHONIC_HASH)
        }

        private const val INSTANTIATE_PLAYBACK_HASH = 210135309L
        private val instantiatePlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStream", "instantiate_playback", INSTANTIATE_PLAYBACK_HASH)
        }

        private const val CAN_BE_SAMPLED_HASH = 36873697L
        private val canBeSampledBind by lazy {
            ObjectCalls.getMethodBind("AudioStream", "can_be_sampled", CAN_BE_SAMPLED_HASH)
        }

        private const val GENERATE_SAMPLE_HASH = 2646048999L
        private val generateSampleBind by lazy {
            ObjectCalls.getMethodBind("AudioStream", "generate_sample", GENERATE_SAMPLE_HASH)
        }

        private const val IS_META_STREAM_HASH = 36873697L
        private val isMetaStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStream", "is_meta_stream", IS_META_STREAM_HASH)
        }
    }
}
