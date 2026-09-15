package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

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
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): LightmapperRD? =
            if (handle.address() == 0L) null else LightmapperRD(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
