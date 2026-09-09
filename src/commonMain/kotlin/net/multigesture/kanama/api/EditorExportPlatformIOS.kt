package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorExportPlatformIOS
 */
class EditorExportPlatformIOS(handle: MemorySegment) : EditorExportPlatformAppleEmbedded(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorExportPlatformIOS? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPlatformIOS? =
            if (handle.address() == 0L) null else EditorExportPlatformIOS(handle)

        // No MethodBinds emitted yet.
    }
}
