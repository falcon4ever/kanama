package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorExportPlatformLinuxBSD
 */
class EditorExportPlatformLinuxBSD(handle: GodotHandle) : EditorExportPlatformPC(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPlatformLinuxBSD? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): EditorExportPlatformLinuxBSD? =
            if (handle.address() == 0L) null else EditorExportPlatformLinuxBSD(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
