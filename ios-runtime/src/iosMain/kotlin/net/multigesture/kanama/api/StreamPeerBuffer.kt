package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: StreamPeerBuffer
 */
class StreamPeerBuffer(handle: MemorySegment) : StreamPeer(handle) {
    fun seek(position: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(seekBind, handle, position)
    }

    fun getSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSizeBind, handle)
    }

    fun getPosition(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPositionBind, handle)
    }

    fun resize(size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(resizeBind, handle, size)
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

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
