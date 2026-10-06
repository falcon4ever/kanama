package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract class extended by lightmappers, for use in `LightmapGI`.
 *
 * Generated from Godot docs: Lightmapper
 */
open class Lightmapper(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Lightmapper? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Lightmapper? =
            if (handle.address() == 0L) null else RefCounted.owned(Lightmapper(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Lightmapper? =
            if (handle.address() == 0L) null else Lightmapper(GodotHandle(handle))
    }
}
