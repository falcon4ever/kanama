package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Imports a bitmap font in the BMFont (`.fnt`) format.
 *
 * Generated from Godot docs: ResourceImporterBMFont
 */
class ResourceImporterBMFont(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterBMFont? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterBMFont? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterBMFont(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterBMFont? =
            if (handle.address() == 0L) null else ResourceImporterBMFont(GodotHandle(handle))
    }
}
