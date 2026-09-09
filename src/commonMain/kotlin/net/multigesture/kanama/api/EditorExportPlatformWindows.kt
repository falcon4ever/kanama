package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorExportPlatformWindows
 */
class EditorExportPlatformWindows(handle: MemorySegment) : EditorExportPlatformPC(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorExportPlatformWindows? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPlatformWindows? =
            if (handle.address() == 0L) null else EditorExportPlatformWindows(handle)

        // No MethodBinds emitted yet.
    }
}
