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
class ResourceImporterCSVTranslation(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterCSVTranslation? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterCSVTranslation? =
            if (handle.address() == 0L) null else ResourceImporterCSVTranslation(handle)

        // No MethodBinds emitted yet.
    }
}
