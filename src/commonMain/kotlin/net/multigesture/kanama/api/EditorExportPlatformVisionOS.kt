package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: EditorExportPlatformVisionOS
 */
class EditorExportPlatformVisionOS(handle: GodotHandle) : EditorExportPlatformAppleEmbedded(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPlatformVisionOS? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorExportPlatformVisionOS? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorExportPlatformVisionOS(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorExportPlatformVisionOS? =
            if (handle.address() == 0L) null else EditorExportPlatformVisionOS(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
