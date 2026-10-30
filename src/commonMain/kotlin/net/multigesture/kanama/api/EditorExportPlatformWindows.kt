package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

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

        /** A new EditorExportPlatformWindows, GDScript's `EditorExportPlatformWindows.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorExportPlatformWindows =
            RefCounted.owned(EditorExportPlatformWindows(GodotHandle(requireConstructed(ObjectCalls.constructObject("EditorExportPlatformWindows"), "EditorExportPlatformWindows"))))
    }
}
