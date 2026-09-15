package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Saves a specific resource type to a file.
 *
 * Generated from Godot docs: ResourceFormatSaver
 */
class ResourceFormatSaver(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceFormatSaver? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceFormatSaver? =
            if (handle.address() == 0L) null else ResourceFormatSaver(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
