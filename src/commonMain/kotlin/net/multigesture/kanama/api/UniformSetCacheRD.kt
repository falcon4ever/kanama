package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Uniform set cache manager for Rendering Device based renderers.
 *
 * Generated from Godot docs: UniformSetCacheRD
 */
class UniformSetCacheRD(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): UniformSetCacheRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): UniformSetCacheRD? =
            if (handle.address() == 0L) null else UniformSetCacheRD(handle)

        // No MethodBinds emitted yet.
    }
}
