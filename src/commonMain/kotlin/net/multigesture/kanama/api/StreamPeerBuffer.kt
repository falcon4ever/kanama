package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A stream peer used to handle binary data streams.
 *
 * Generated from Godot docs: StreamPeerBuffer
 */
class StreamPeerBuffer(handle: MemorySegment) : StreamPeer(handle) {
    var dataArray: ByteArray
        @JvmName("dataArrayProperty")
        get() = getDataArray()
        @JvmName("setDataArrayProperty")
        set(value) = setDataArray(value)

    /**
     * Moves the cursor to the specified position. `position` must be a valid index of `data_array`.
     *
     * Generated from Godot docs: StreamPeerBuffer.seek
     */
    fun seek(position: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(seekBind, handle, position)
    }

    /**
     * Returns the size of `data_array`.
     *
     * Generated from Godot docs: StreamPeerBuffer.get_size
     */
    fun getSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSizeBind, handle)
    }

    /**
     * Returns the current cursor position.
     *
     * Generated from Godot docs: StreamPeerBuffer.get_position
     */
    fun getPosition(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPositionBind, handle)
    }

    /**
     * Resizes the `data_array`. This doesn't update the cursor.
     *
     * Generated from Godot docs: StreamPeerBuffer.resize
     */
    fun resize(size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(resizeBind, handle, size)
    }

    /**
     * The underlying data buffer. Setting this value resets the cursor.
     *
     * Generated from Godot docs: StreamPeerBuffer.set_data_array
     */
    fun setDataArray(data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayArg(setDataArrayBind, handle, data)
    }

    /**
     * The underlying data buffer. Setting this value resets the cursor.
     *
     * Generated from Godot docs: StreamPeerBuffer.get_data_array
     */
    fun getDataArray(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(getDataArrayBind, handle)
    }

    /**
     * Clears the `data_array` and resets the cursor.
     *
     * Generated from Godot docs: StreamPeerBuffer.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Returns a new `StreamPeerBuffer` with the same `data_array` content.
     *
     * Generated from Godot docs: StreamPeerBuffer.duplicate
     */
    fun duplicate(): StreamPeerBuffer? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(duplicateBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return StreamPeerBuffer.wrap(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StreamPeerBuffer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StreamPeerBuffer? =
            if (handle.address() == 0L) null else StreamPeerBuffer(handle)

        private const val SEEK_HASH = 1286410249L
        private val seekBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerBuffer", "seek", SEEK_HASH)
        }

        private const val GET_SIZE_HASH = 3905245786L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerBuffer", "get_size", GET_SIZE_HASH)
        }

        private const val GET_POSITION_HASH = 3905245786L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerBuffer", "get_position", GET_POSITION_HASH)
        }

        private const val RESIZE_HASH = 1286410249L
        private val resizeBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerBuffer", "resize", RESIZE_HASH)
        }

        private const val SET_DATA_ARRAY_HASH = 2971499966L
        private val setDataArrayBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerBuffer", "set_data_array", SET_DATA_ARRAY_HASH)
        }

        private const val GET_DATA_ARRAY_HASH = 2362200018L
        private val getDataArrayBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerBuffer", "get_data_array", GET_DATA_ARRAY_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerBuffer", "clear", CLEAR_HASH)
        }

        private const val DUPLICATE_HASH = 2474064677L
        private val duplicateBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerBuffer", "duplicate", DUPLICATE_HASH)
        }
    }
}
