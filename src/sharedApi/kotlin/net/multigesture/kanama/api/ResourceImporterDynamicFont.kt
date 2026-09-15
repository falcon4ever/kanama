package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Imports a TTF, TTC, OTF, OTC, WOFF or WOFF2 font file for font rendering that adapts to any
 * size.
 *
 * Generated from Godot docs: ResourceImporterDynamicFont
 */
class ResourceImporterDynamicFont(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterDynamicFont? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceImporterDynamicFont? =
            if (handle.address() == 0L) null else ResourceImporterDynamicFont(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
