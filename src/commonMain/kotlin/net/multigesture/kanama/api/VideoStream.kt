package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base resource for video streams.
 *
 * Generated from Godot docs: VideoStream
 */
open class VideoStream(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setFileBind, segment, file)
    }

    /**
     * The video file path or URI that this `VideoStream` resource handles. For `VideoStreamTheora`,
     * this filename should be an Ogg Theora video file with the `.ogv` extension.
     *
     * Generated from Godot docs: VideoStream.get_file
     */
    fun getFile(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getFileBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VideoStream? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VideoStream? =
            if (handle.address() == 0L) null else RefCounted.owned(VideoStream(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VideoStream? =
            if (handle.address() == 0L) null else VideoStream(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FILE_HASH = 83702148L
        @JvmField
        val setFileBind =
            ObjectCalls.getMethodBind("VideoStream", "set_file", SET_FILE_HASH)

        private const val GET_FILE_HASH = 2841200299L
        @JvmField
        val getFileBind =
            ObjectCalls.getMethodBind("VideoStream", "get_file", GET_FILE_HASH)
    }
}
