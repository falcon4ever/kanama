package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ResourceImporterMP3
 */
class ResourceImporterMP3(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterMP3? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceImporterMP3? =
            if (handle.address() == 0L) null else ResourceImporterMP3(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
