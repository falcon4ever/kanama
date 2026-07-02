package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: ResourceImporterOggVorbis
 */
class ResourceImporterOggVorbis(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        fun loadFromBuffer(streamData: ByteArray): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrap(ObjectCalls.ptrcallWithByteArrayArgRetObject(loadFromBufferBind, MemorySegment.NULL, streamData))
        }

        fun loadFromFile(path: String): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrap(ObjectCalls.ptrcallWithStringArgRetObject(loadFromFileBind, MemorySegment.NULL, path))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterOggVorbis? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterOggVorbis? =
            if (handle.address() == 0L) null else ResourceImporterOggVorbis(handle)

        private const val LOAD_FROM_BUFFER_HASH = 354904730L
        private val loadFromBufferBind by lazy {
            ObjectCalls.getMethodBind("ResourceImporterOggVorbis", "load_from_buffer", LOAD_FROM_BUFFER_HASH)
        }

        private const val LOAD_FROM_FILE_HASH = 797568536L
        private val loadFromFileBind by lazy {
            ObjectCalls.getMethodBind("ResourceImporterOggVorbis", "load_from_file", LOAD_FROM_FILE_HASH)
        }
    }
}
