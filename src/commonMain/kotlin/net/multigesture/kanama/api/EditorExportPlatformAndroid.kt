package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: EditorExportPlatformAndroid
 */
class EditorExportPlatformAndroid(handle: GodotHandle) : EditorExportPlatform(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPlatformAndroid? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorExportPlatformAndroid? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorExportPlatformAndroid(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorExportPlatformAndroid? =
            if (handle.address() == 0L) null else EditorExportPlatformAndroid(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
