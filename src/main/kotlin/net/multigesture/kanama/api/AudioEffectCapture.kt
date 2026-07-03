package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Exposes audio samples from an audio bus in real-time, such that it can be accessed as data.
 *
 * Generated from Godot docs: AudioEffectCapture
 */
class AudioEffectCapture(handle: MemorySegment) : AudioEffect(handle) {
    var bufferLength: Double
        @JvmName("bufferLengthProperty")
        get() = getBufferLength()
        @JvmName("setBufferLengthProperty")
        set(value) = setBufferLength(value)

    /**
     * Returns `true` if at least `frames` samples are available to read in the internal ring buffer.
     *
     * Generated from Godot docs: AudioEffectCapture.can_get_buffer
     */
    fun canGetBuffer(frames: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(canGetBufferBind, handle, frames)
    }

    /**
     * Gets the next `frames` samples from the internal ring buffer. Returns a `PackedVector2Array`
     * containing exactly `frames` samples if available, or an empty `PackedVector2Array` if
     * insufficient data was available. The samples are signed floating-point PCM between `-1` and `1`.
     * You will have to scale them if you want to use them as 8 or 16-bit integer samples. (`v = 0x7fff
     * * samples[0].x`)
     *
     * Generated from Godot docs: AudioEffectCapture.get_buffer
     */
    fun getBuffer(frames: Int): List<Vector2> {
        return ObjectCalls.ptrcallWithIntArgRetPackedVector2List(getBufferBind, handle, frames)
    }

    /**
     * Clears the internal ring buffer. Note: Calling this during a capture can cause the loss of
     * samples which causes popping in the playback.
     *
     * Generated from Godot docs: AudioEffectCapture.clear_buffer
     */
    fun clearBuffer() {
        ObjectCalls.ptrcallNoArgs(clearBufferBind, handle)
    }

    /**
     * Length of the internal ring buffer, in seconds. Higher values keep data around for longer, but
     * require more memory. Value can range from 0.01 to 10. Note: Setting the buffer length will have
     * no effect if already initialized.
     *
     * Generated from Godot docs: AudioEffectCapture.set_buffer_length
     */
    fun setBufferLength(bufferLengthSeconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBufferLengthBind, handle, bufferLengthSeconds)
    }

    /**
     * Length of the internal ring buffer, in seconds. Higher values keep data around for longer, but
     * require more memory. Value can range from 0.01 to 10. Note: Setting the buffer length will have
     * no effect if already initialized.
     *
     * Generated from Godot docs: AudioEffectCapture.get_buffer_length
     */
    fun getBufferLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBufferLengthBind, handle)
    }

    /**
     * Returns the number of samples available to read using `get_buffer`.
     *
     * Generated from Godot docs: AudioEffectCapture.get_frames_available
     */
    fun getFramesAvailable(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFramesAvailableBind, handle)
    }

    /**
     * Returns the number of samples discarded from the audio bus due to full buffer.
     *
     * Generated from Godot docs: AudioEffectCapture.get_discarded_frames
     */
    fun getDiscardedFrames(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDiscardedFramesBind, handle)
    }

    /**
     * Returns the total size of the internal ring buffer in number of samples.
     *
     * Generated from Godot docs: AudioEffectCapture.get_buffer_length_frames
     */
    fun getBufferLengthFrames(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferLengthFramesBind, handle)
    }

    /**
     * Returns the number of samples inserted from the audio bus.
     *
     * Generated from Godot docs: AudioEffectCapture.get_pushed_frames
     */
    fun getPushedFrames(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPushedFramesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectCapture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectCapture? =
            if (handle.address() == 0L) null else AudioEffectCapture(handle)

        private const val CAN_GET_BUFFER_HASH = 1116898809L
        private val canGetBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "can_get_buffer", CAN_GET_BUFFER_HASH)
        }

        private const val GET_BUFFER_HASH = 2649534757L
        private val getBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "get_buffer", GET_BUFFER_HASH)
        }

        private const val CLEAR_BUFFER_HASH = 3218959716L
        private val clearBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "clear_buffer", CLEAR_BUFFER_HASH)
        }

        private const val SET_BUFFER_LENGTH_HASH = 373806689L
        private val setBufferLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "set_buffer_length", SET_BUFFER_LENGTH_HASH)
        }

        private const val GET_BUFFER_LENGTH_HASH = 191475506L
        private val getBufferLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "get_buffer_length", GET_BUFFER_LENGTH_HASH)
        }

        private const val GET_FRAMES_AVAILABLE_HASH = 3905245786L
        private val getFramesAvailableBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "get_frames_available", GET_FRAMES_AVAILABLE_HASH)
        }

        private const val GET_DISCARDED_FRAMES_HASH = 3905245786L
        private val getDiscardedFramesBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "get_discarded_frames", GET_DISCARDED_FRAMES_HASH)
        }

        private const val GET_BUFFER_LENGTH_FRAMES_HASH = 3905245786L
        private val getBufferLengthFramesBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "get_buffer_length_frames", GET_BUFFER_LENGTH_FRAMES_HASH)
        }

        private const val GET_PUSHED_FRAMES_HASH = 3905245786L
        private val getPushedFramesBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "get_pushed_frames", GET_PUSHED_FRAMES_HASH)
        }
    }
}
