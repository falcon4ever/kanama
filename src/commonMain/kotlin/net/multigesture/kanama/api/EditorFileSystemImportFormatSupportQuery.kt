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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorFileSystemImportFormatSupportQuery? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorFileSystemImportFormatSupportQuery(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorFileSystemImportFormatSupportQuery? =
            if (handle.address() == 0L) null else EditorFileSystemImportFormatSupportQuery(GodotHandle(handle))
    }
}
