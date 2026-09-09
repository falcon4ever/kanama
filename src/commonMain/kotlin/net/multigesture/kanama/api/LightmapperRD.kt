package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * The built-in GPU-based lightmapper for use with `LightmapGI`.
 *
 * Generated from Godot docs: LightmapperRD
 */
class LightmapperRD(handle: MemorySegment) : Lightmapper(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): LightmapperRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LightmapperRD? =
            if (handle.address() == 0L) null else LightmapperRD(handle)

        // No MethodBinds emitted yet.
    }
}
