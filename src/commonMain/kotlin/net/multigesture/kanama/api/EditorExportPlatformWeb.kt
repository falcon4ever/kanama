package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorExportPlatformWeb
 */
class EditorExportPlatformWeb(handle: GodotHandle) : EditorExportPlatform(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPlatformWeb? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): EditorExportPlatformWeb? =
            if (handle.address() == 0L) null else EditorExportPlatformWeb(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
