package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Generated from Godot docs: EditorExportPlatformMacOS
 */
class EditorExportPlatformMacOS(handle: GodotHandle) : EditorExportPlatform(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPlatformMacOS? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorExportPlatformMacOS? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorExportPlatformMacOS(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorExportPlatformMacOS? =
            if (handle.address() == 0L) null else EditorExportPlatformMacOS(GodotHandle(handle))

        /** A new EditorExportPlatformMacOS, GDScript's `EditorExportPlatformMacOS.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorExportPlatformMacOS =
            RefCounted.owned(EditorExportPlatformMacOS(GodotHandle(requireConstructed(ObjectCalls.constructObject("EditorExportPlatformMacOS"), "EditorExportPlatformMacOS"))))
    }
}
