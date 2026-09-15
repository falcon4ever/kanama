package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract class for non-real-time video recording encoders.
 *
 * Generated from Godot docs: MovieWriter
 */
class MovieWriter(handle: GodotHandle) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        /**
         * Adds a writer to be usable by the engine. The supported file extensions can be set by overriding
         * `_handles_file`. Note: `add_writer` must be called early enough in the engine initialization to
         * work, as movie writing is designed to start at the same time as the rest of the engine.
         *
         * Generated from Godot docs: MovieWriter.add_writer
         */
        fun addWriter(writer: MovieWriter) {
            ObjectCalls.ptrcallWithObjectArgs(addWriterBind, NULL_SEGMENT, listOf(writer.segment))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): MovieWriter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MovieWriter? =
            if (handle.address() == 0L) null else MovieWriter(GodotHandle(handle))

        private const val ADD_WRITER_HASH = 4023702871L
        private val addWriterBind by lazy {
            ObjectCalls.getMethodBind("MovieWriter", "add_writer", ADD_WRITER_HASH)
        }
    }
}
