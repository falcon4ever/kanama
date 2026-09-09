package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports an image for use in scripting, with no rendering capabilities.
 *
 * Generated from Godot docs: ResourceImporterImage
 */
class ResourceImporterImage(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterImage? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterImage? =
            if (handle.address() == 0L) null else ResourceImporterImage(handle)

        // No MethodBinds emitted yet.
    }
}
