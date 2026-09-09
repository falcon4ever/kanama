package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports a TTF, TTC, OTF, OTC, WOFF or WOFF2 font file for font rendering that adapts to any
 * size.
 *
 * Generated from Godot docs: ResourceImporterDynamicFont
 */
class ResourceImporterDynamicFont(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterDynamicFont? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterDynamicFont? =
            if (handle.address() == 0L) null else ResourceImporterDynamicFont(handle)

        // No MethodBinds emitted yet.
    }
}
