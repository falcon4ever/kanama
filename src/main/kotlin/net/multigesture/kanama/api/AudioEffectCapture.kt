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

    fun canGetBuffer(frames: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(canGetBufferBind, handle, frames)
    }

    fun getBuffer(frames: Int): List<Vector2> {
        return ObjectCalls.ptrcallWithIntArgRetPackedVector2List(getBufferBind, handle, frames)
    }

    fun clearBuffer() {
        ObjectCalls.ptrcallNoArgs(clearBufferBind, handle)
    }

    fun setBufferLength(bufferLengthSeconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBufferLengthBind, handle, bufferLengthSeconds)
    }

    fun getBufferLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBufferLengthBind, handle)
    }

    fun getFramesAvailable(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFramesAvailableBind, handle)
    }

    fun getDiscardedFrames(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDiscardedFramesBind, handle)
    }

    fun getBufferLengthFrames(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferLengthFramesBind, handle)
    }

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
