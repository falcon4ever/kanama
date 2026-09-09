package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Used to query and configure import format support.
 *
 * Generated from Godot docs: EditorFileSystemImportFormatSupportQuery
 */
class EditorFileSystemImportFormatSupportQuery(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorFileSystemImportFormatSupportQuery? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorFileSystemImportFormatSupportQuery? =
            if (handle.address() == 0L) null else EditorFileSystemImportFormatSupportQuery(handle)

        // No MethodBinds emitted yet.
    }
}
