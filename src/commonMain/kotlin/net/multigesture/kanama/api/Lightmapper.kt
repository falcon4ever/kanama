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
open class Lightmapper(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Lightmapper? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): Lightmapper? =
            if (handle.address() == 0L) null else Lightmapper(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
