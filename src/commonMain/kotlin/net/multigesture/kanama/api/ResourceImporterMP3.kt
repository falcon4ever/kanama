package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ResourceImporterMP3
 */
class ResourceImporterMP3(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterMP3? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterMP3? =
            if (handle.address() == 0L) null else ResourceImporterMP3(handle)

        // No MethodBinds emitted yet.
    }
}
