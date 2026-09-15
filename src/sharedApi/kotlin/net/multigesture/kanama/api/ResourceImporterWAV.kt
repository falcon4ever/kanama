package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Imports a WAV audio file for playback.
 *
 * Generated from Godot docs: ResourceImporterWAV
 */
class ResourceImporterWAV(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterWAV? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceImporterWAV? =
            if (handle.address() == 0L) null else ResourceImporterWAV(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
