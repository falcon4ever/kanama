package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Imports a bitmap font where all glyphs have the same width and height.
 *
 * Generated from Godot docs: ResourceImporterImageFont
 */
class ResourceImporterImageFont(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterImageFont? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterImageFont? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterImageFont(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterImageFont? =
            if (handle.address() == 0L) null else ResourceImporterImageFont(GodotHandle(handle))
    }
}
