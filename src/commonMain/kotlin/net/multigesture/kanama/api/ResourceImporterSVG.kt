package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports an SVG file as an automatically scalable texture for use in UI elements and 2D
 * rendering.
 *
 * Generated from Godot docs: ResourceImporterSVG
 */
class ResourceImporterSVG(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterSVG? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): ResourceImporterSVG? =
            if (handle.address() == 0L) null else ResourceImporterSVG(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
