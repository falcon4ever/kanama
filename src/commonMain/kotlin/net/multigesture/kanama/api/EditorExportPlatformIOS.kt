package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: EditorExportPlatformIOS
 */
class EditorExportPlatformIOS(handle: GodotHandle) : EditorExportPlatformAppleEmbedded(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPlatformIOS? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorExportPlatformIOS? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorExportPlatformIOS(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorExportPlatformIOS? =
            if (handle.address() == 0L) null else EditorExportPlatformIOS(GodotHandle(handle))
    }
}
