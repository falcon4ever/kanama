package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Plays back audio generated using `AudioStreamGenerator`.
 *
 * Generated from Godot docs: AudioStreamGeneratorPlayback
 */
class AudioStreamGeneratorPlayback(handle: GodotHandle) : AudioStreamPlaybackResampled(handle) {
    /**
     * Pushes a single audio data frame to the buffer. This is usually less efficient than
     * `push_buffer` in C# and compiled languages via GDExtension, but `push_frame` may be more
     * efficient in GDScript.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.push_frame
     */
    fun pushFrame(frame: Vector2): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2ArgRetBool(Binds.pushFrameBind, segment, frame)
    }

    /**
     * Returns `true` if a buffer of the size `amount` can be pushed to the audio sample data buffer
     * without overflowing it, `false` otherwise.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.can_push_buffer
     */
    fun canPushBuffer(amount: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.canPushBufferBind, segment, amount)
    }

    /**
     * Pushes several audio data frames to the buffer. This is usually more efficient than `push_frame`
     * in C# and compiled languages via GDExtension, but `push_buffer` may be less efficient in
     * GDScript.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.push_buffer
     */
    fun pushBuffer(frames: List<Vector2>): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetBool(Binds.pushBufferBind, segment, frames)
    }

    /**
     * Returns the number of frames that can be pushed to the audio sample data buffer without
     * overflowing it. If the result is `0`, the buffer is full.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.get_frames_available
     */
    fun getFramesAvailable(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFramesAvailableBind, segment)
    }

    /**
     * Returns the number of times the playback skipped due to a buffer underrun in the audio sample
     * data. This value is reset at the start of the playback.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.get_skips
     */
    fun getSkips(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSkipsBind, segment)
    }

    /**
     * Clears the audio sample data buffer.
     *
     * Generated from Godot docs: AudioStreamGeneratorPlayback.clear_buffer
     */
    fun clearBuffer() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBufferBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamGeneratorPlayback? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamGeneratorPlayback? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamGeneratorPlayback(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamGeneratorPlayback? =
            if (handle.address() == 0L) null else AudioStreamGeneratorPlayback(GodotHandle(handle))
    }

    private object Binds {
        private const val PUSH_FRAME_HASH = 3975407249L
        @JvmField
        val pushFrameBind =
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "push_frame", PUSH_FRAME_HASH)

        private const val CAN_PUSH_BUFFER_HASH = 1116898809L
        @JvmField
        val canPushBufferBind =
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "can_push_buffer", CAN_PUSH_BUFFER_HASH)

        private const val PUSH_BUFFER_HASH = 1361156557L
        @JvmField
        val pushBufferBind =
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "push_buffer", PUSH_BUFFER_HASH)

        private const val GET_FRAMES_AVAILABLE_HASH = 3905245786L
        @JvmField
        val getFramesAvailableBind =
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "get_frames_available", GET_FRAMES_AVAILABLE_HASH)

        private const val GET_SKIPS_HASH = 3905245786L
        @JvmField
        val getSkipsBind =
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "get_skips", GET_SKIPS_HASH)

        private const val CLEAR_BUFFER_HASH = 3218959716L
        @JvmField
        val clearBufferBind =
            ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "clear_buffer", CLEAR_BUFFER_HASH)
    }
}
