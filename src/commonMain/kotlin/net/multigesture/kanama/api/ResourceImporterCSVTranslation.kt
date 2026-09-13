package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports comma-separated values as `Translation`s.
 *
 * Generated from Godot docs: ResourceImporterCSVTranslation
 */
class ResourceImporterCSVTranslation(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterCSVTranslation? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): ResourceImporterCSVTranslation? =
            if (handle.address() == 0L) null else ResourceImporterCSVTranslation(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
