package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/** Reference-counted resource saver extension object. Generated from Godot docs: ResourceFormatSaver */
class ResourceFormatSaver(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceFormatSaver? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceFormatSaver? =
            if (handle.address() == 0L) null else ResourceFormatSaver(handle)

        // No MethodBinds emitted yet.
    }
}
