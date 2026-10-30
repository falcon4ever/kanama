package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

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

        /** A new EditorExportPlatformIOS, GDScript's `EditorExportPlatformIOS.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorExportPlatformIOS =
            RefCounted.owned(EditorExportPlatformIOS(GodotHandle(requireConstructed(ObjectCalls.constructObject("EditorExportPlatformIOS"), "EditorExportPlatformIOS"))))
    }
}
