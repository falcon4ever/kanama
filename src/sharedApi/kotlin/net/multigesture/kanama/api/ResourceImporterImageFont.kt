package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports a bitmap font where all glyphs have the same width and height.
 *
 * Generated from Godot docs: ResourceImporterImageFont
 */
class ResourceImporterImageFont(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterImageFont? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceImporterImageFont? =
            if (handle.address() == 0L) null else ResourceImporterImageFont(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
