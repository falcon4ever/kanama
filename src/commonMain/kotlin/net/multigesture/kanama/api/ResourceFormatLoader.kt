package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Loads a specific resource type from a file.
 *
 * Generated from Godot docs: ResourceFormatLoader
 */
class ResourceFormatLoader(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val CACHE_MODE_IGNORE: Long = 0L
        const val CACHE_MODE_REUSE: Long = 1L
        const val CACHE_MODE_REPLACE: Long = 2L
        const val CACHE_MODE_IGNORE_DEEP: Long = 3L
        const val CACHE_MODE_REPLACE_DEEP: Long = 4L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceFormatLoader? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceFormatLoader? =
            if (handle.address() == 0L) null else ResourceFormatLoader(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
