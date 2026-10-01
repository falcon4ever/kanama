package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Used to query and configure import format support.
 *
 * Generated from Godot docs: EditorFileSystemImportFormatSupportQuery
 */
class EditorFileSystemImportFormatSupportQuery(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorFileSystemImportFormatSupportQuery? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorFileSystemImportFormatSupportQuery? =
            if (handle.address() == 0L) null else EditorFileSystemImportFormatSupportQuery(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
