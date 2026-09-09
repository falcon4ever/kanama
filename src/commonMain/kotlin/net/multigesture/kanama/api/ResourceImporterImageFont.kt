package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports a bitmap font where all glyphs have the same width and height.
 *
 * Generated from Godot docs: ResourceImporterImageFont
 */
class ResourceImporterImageFont(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterImageFont? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterImageFont? =
            if (handle.address() == 0L) null else ResourceImporterImageFont(handle)

        // No MethodBinds emitted yet.
    }
}
