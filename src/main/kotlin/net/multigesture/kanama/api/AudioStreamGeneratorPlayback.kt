package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Plays back audio generated using `AudioStreamGenerator`.
 *
 * Generated from Godot docs: AudioStreamGeneratorPlayback
 */
class AudioStreamGeneratorPlayback(handle: MemorySegment) : AudioStreamPlaybackResampled(handle) {
    /**
     * Pushes a single audio data frame to the buffer. This is usually less efficient than
     * `push_buffer` in C# and compiled languages via GDExtension, but `push_frame` may be more
     * efficient in GDScript.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.push_frame
     */
    fun pushFrame(frame: Vector2): Boolean {
        return ObjectCalls.ptrcallWithVector2ArgRetBool(pushFrameBind, handle, frame)
    }

    /**
     * Returns `true` if a buffer of the size `amount` can be pushed to the audio sample data buffer
     * without overflowing it, `false` otherwise.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.can_push_buffer
     */
    fun canPushBuffer(amount: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(canPushBufferBind, handle, amount)
    }

    /**
     * Pushes several audio data frames to the buffer. This is usually more efficient than `push_frame`
     * in C# and compiled languages via GDExtension, but `push_buffer` may be less efficient in
     * GDScript.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.push_buffer
     */
    fun pushBuffer(frames: List<Vector2>): Boolean {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetBool(pushBufferBind, handle, frames)
    }

    /**
     * Returns the number of frames that can be pushed to the audio sample data buffer without
     * overflowing it. If the result is `0`, the buffer is full.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.get_frames_available
     */
    fun getFramesAvailable(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFramesAvailableBind, handle)
    }

    /**
     * Returns the number of times the playback skipped due to a buffer underrun in the audio sample
     * data. This value is reset at the start of the playback.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.get_skips
     */
    fun getSkips(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSkipsBind, handle)
    }

    /**
     * Clears the audio sample data buffer.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.clear_buffer
     */
    fun clearBuffer() {
        ObjectCalls.ptrcallNoArgs(clearBufferBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamGeneratorPlayback? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamGeneratorPlayback? =
            if (handle.address() == 0L) null else AudioStreamGeneratorPlayback(handle)

        private const val PUSH_FRAME_HASH = 3975407249L
        private val pushFrameBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "push_frame", PUSH_FRAME_HASH)
        }

        private const val CAN_PUSH_BUFFER_HASH = 1116898809L
        private val canPushBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "can_push_buffer", CAN_PUSH_BUFFER_HASH)
        }

        private const val PUSH_BUFFER_HASH = 1361156557L
        private val pushBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "push_buffer", PUSH_BUFFER_HASH)
        }

        private const val GET_FRAMES_AVAILABLE_HASH = 3905245786L
        private val getFramesAvailableBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "get_frames_available", GET_FRAMES_AVAILABLE_HASH)
        }

        private const val GET_SKIPS_HASH = 3905245786L
        private val getSkipsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "get_skips", GET_SKIPS_HASH)
        }

        private const val CLEAR_BUFFER_HASH = 3218959716L
        private val clearBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "clear_buffer", CLEAR_BUFFER_HASH)
        }
    }
}
