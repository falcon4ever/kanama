package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for the Apple embedded platform exporters (iOS and visionOS).
 *
 * Generated from Godot docs: EditorExportPlatformAppleEmbedded
 */
open class EditorExportPlatformAppleEmbedded(handle: GodotHandle) : EditorExportPlatform(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPlatformAppleEmbedded? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorExportPlatformAppleEmbedded? =
            if (handle.address() == 0L) null else EditorExportPlatformAppleEmbedded(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
