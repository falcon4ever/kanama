package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports an image for use in scripting, with no rendering capabilities.
 *
 * Generated from Godot docs: ResourceImporterImage
 */
class ResourceImporterImage(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterImage? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceImporterImage? =
            if (handle.address() == 0L) null else ResourceImporterImage(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
