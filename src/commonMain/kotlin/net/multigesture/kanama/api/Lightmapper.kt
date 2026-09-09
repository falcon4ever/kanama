package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Abstract class extended by lightmappers, for use in `LightmapGI`.
 *
 * Generated from Godot docs: Lightmapper
 */
open class Lightmapper(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Lightmapper? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Lightmapper? =
            if (handle.address() == 0L) null else Lightmapper(handle)

        // No MethodBinds emitted yet.
    }
}
