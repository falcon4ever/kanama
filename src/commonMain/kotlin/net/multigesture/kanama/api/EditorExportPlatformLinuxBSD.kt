package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorExportPlatformLinuxBSD
 */
class EditorExportPlatformLinuxBSD(handle: MemorySegment) : EditorExportPlatformPC(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorExportPlatformLinuxBSD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPlatformLinuxBSD? =
            if (handle.address() == 0L) null else EditorExportPlatformLinuxBSD(handle)

        // No MethodBinds emitted yet.
    }
}
