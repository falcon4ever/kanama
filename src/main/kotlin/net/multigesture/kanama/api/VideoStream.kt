package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base resource for video streams.
 *
 * Generated from Godot docs: VideoStream
 */
open class VideoStream(handle: MemorySegment) : Resource(handle) {
    var file: String
        @JvmName("fileProperty")
        get() = getFile()
        @JvmName("setFileProperty")
        set(value) = setFile(value)

    /**
     * The video file path or URI that this `VideoStream` resource handles. For `VideoStreamTheora`,
     * this filename should be an Ogg Theora video file with the `.ogv` extension.
     *
     * Generated from Godot docs: VideoStream.set_file
     */
    fun setFile(file: String) {
        ObjectCalls.ptrcallWithStringArg(setFileBind, handle, file)
    }

    /**
     * The video file path or URI that this `VideoStream` resource handles. For `VideoStreamTheora`,
     * this filename should be an Ogg Theora video file with the `.ogv` extension.
     *
     * Generated from Godot docs: VideoStream.get_file
     */
    fun getFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getFileBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VideoStream? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VideoStream? =
            if (handle.address() == 0L) null else VideoStream(handle)

        private const val SET_FILE_HASH = 83702148L
        private val setFileBind by lazy {
            ObjectCalls.getMethodBind("VideoStream", "set_file", SET_FILE_HASH)
        }

        private const val GET_FILE_HASH = 2841200299L
        private val getFileBind by lazy {
            ObjectCalls.getMethodBind("VideoStream", "get_file", GET_FILE_HASH)
        }
    }
}
