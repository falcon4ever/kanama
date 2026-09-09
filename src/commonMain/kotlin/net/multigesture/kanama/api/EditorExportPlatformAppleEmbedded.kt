package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class for the Apple embedded platform exporters (iOS and visionOS).
 *
 * Generated from Godot docs: EditorExportPlatformAppleEmbedded
 */
open class EditorExportPlatformAppleEmbedded(handle: MemorySegment) : EditorExportPlatform(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorExportPlatformAppleEmbedded? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPlatformAppleEmbedded? =
            if (handle.address() == 0L) null else EditorExportPlatformAppleEmbedded(handle)

        // No MethodBinds emitted yet.
    }
}
