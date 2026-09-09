package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioEffectCapture
 */
class AudioEffectCapture(handle: MemorySegment) : AudioEffect(handle) {
    var bufferLength: Double
        @JvmName("bufferLengthProperty")
        get() = getBufferLength()
        @JvmName("setBufferLengthProperty")
        set(value) = setBufferLength(value)

    fun canGetBuffer(frames: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(canGetBufferBind, handle, frames)
    }

    fun clearBuffer() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBufferBind, handle)
    }

    fun setBufferLength(bufferLengthSeconds: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBufferLengthBind, handle, bufferLengthSeconds)
    }

    fun getBufferLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBufferLengthBind, handle)
    }

    fun getFramesAvailable(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFramesAvailableBind, handle)
    }

    fun getDiscardedFrames(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getDiscardedFramesBind, handle)
    }

    fun getBufferLengthFrames(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferLengthFramesBind, handle)
    }

    fun getPushedFrames(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPushedFramesBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): AudioEffectCapture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectCapture? =
            if (handle.address() == 0L) null else AudioEffectCapture(handle)

        private const val CAN_GET_BUFFER_HASH = 1116898809L
        private val canGetBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCapture", "can_get_buffer", CAN_GET_BUFFER_HASH)
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
