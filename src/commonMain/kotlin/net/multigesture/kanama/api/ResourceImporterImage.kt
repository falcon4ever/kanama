package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Imports an image for use in scripting, with no rendering capabilities.
 *
 * Generated from Godot docs: ResourceImporterImage
 */
class ResourceImporterImage(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterImage? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterImage? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterImage(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterImage? =
            if (handle.address() == 0L) null else ResourceImporterImage(GodotHandle(handle))
    }
}
