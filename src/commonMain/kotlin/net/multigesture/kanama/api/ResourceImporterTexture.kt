package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports an image for use in 2D or 3D rendering.
 *
 * Generated from Godot docs: ResourceImporterTexture
 */
class ResourceImporterTexture(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterTexture? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): ResourceImporterTexture? =
            if (handle.address() == 0L) null else ResourceImporterTexture(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
