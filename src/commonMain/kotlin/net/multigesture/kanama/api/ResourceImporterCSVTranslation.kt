package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterCSVTranslation? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterCSVTranslation(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterCSVTranslation? =
            if (handle.address() == 0L) null else ResourceImporterCSVTranslation(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
