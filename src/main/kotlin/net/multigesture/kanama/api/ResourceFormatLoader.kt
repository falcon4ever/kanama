package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/** Reference-counted resource loader extension object. Generated from Godot docs: ResourceFormatLoader */
class ResourceFormatLoader(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val CACHE_MODE_IGNORE: Long = 0L
        const val CACHE_MODE_REUSE: Long = 1L
        const val CACHE_MODE_REPLACE: Long = 2L
        const val CACHE_MODE_IGNORE_DEEP: Long = 3L
        const val CACHE_MODE_REPLACE_DEEP: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceFormatLoader? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceFormatLoader? =
            if (handle.address() == 0L) null else ResourceFormatLoader(handle)

        // No MethodBinds emitted yet.
    }
}
