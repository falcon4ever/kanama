package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class for the desktop platform exporter (Windows and Linux/BSD).
 *
 * Generated from Godot docs: EditorExportPlatformPC
 */
open class EditorExportPlatformPC(handle: MemorySegment) : EditorExportPlatform(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorExportPlatformPC? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPlatformPC? =
            if (handle.address() == 0L) null else EditorExportPlatformPC(handle)

        // No MethodBinds emitted yet.
    }
}
