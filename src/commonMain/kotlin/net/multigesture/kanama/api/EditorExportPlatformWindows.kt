package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: EditorExportPlatformWindows
 */
class EditorExportPlatformWindows(handle: GodotHandle) : EditorExportPlatformPC(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPlatformWindows? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorExportPlatformWindows? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorExportPlatformWindows(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorExportPlatformWindows? =
            if (handle.address() == 0L) null else EditorExportPlatformWindows(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
