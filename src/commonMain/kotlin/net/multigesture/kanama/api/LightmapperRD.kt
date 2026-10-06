package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * The built-in GPU-based lightmapper for use with `LightmapGI`.
 *
 * Generated from Godot docs: LightmapperRD
 */
class LightmapperRD(handle: GodotHandle) : Lightmapper(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): LightmapperRD? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): LightmapperRD? =
            if (handle.address() == 0L) null else RefCounted.owned(LightmapperRD(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): LightmapperRD? =
            if (handle.address() == 0L) null else LightmapperRD(GodotHandle(handle))
    }
}
