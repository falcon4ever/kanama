package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ResourceImporterOggVorbis
 */
class ResourceImporterOggVorbis(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        fun loadFromBuffer(streamData: ByteArray): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrapOwned(ObjectCalls.ptrcallWithByteArrayArgRetObject(Binds.loadFromBufferBind, NULL_SEGMENT, streamData))
        }

        fun loadFromFile(path: String): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.loadFromFileBind, NULL_SEGMENT, path))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterOggVorbis? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterOggVorbis? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterOggVorbis(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterOggVorbis? =
            if (handle.address() == 0L) null else ResourceImporterOggVorbis(GodotHandle(handle))
    }

    private object Binds {
        private const val LOAD_FROM_BUFFER_HASH = 354904730L
        @JvmField
        val loadFromBufferBind =
            ObjectCalls.getMethodBind("ResourceImporterOggVorbis", "load_from_buffer", LOAD_FROM_BUFFER_HASH)

        private const val LOAD_FROM_FILE_HASH = 797568536L
        @JvmField
        val loadFromFileBind =
            ObjectCalls.getMethodBind("ResourceImporterOggVorbis", "load_from_file", LOAD_FROM_FILE_HASH)
    }
}
