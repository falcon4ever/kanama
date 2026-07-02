package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract class for non-real-time video recording encoders.
 *
 * Generated from Godot docs: MovieWriter
 */
class MovieWriter(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        fun addWriter(writer: MovieWriter) {
            ObjectCalls.ptrcallWithObjectArgs(addWriterBind, MemorySegment.NULL, listOf(writer.handle))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): MovieWriter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MovieWriter? =
            if (handle.address() == 0L) null else MovieWriter(handle)

        private const val ADD_WRITER_HASH = 4023702871L
        private val addWriterBind by lazy {
            ObjectCalls.getMethodBind("MovieWriter", "add_writer", ADD_WRITER_HASH)
        }
    }
}
