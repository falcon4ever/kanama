package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Framebuffer cache manager for Rendering Device based renderers.
 *
 * Generated from Godot docs: FramebufferCacheRD
 */
class FramebufferCacheRD(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): FramebufferCacheRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FramebufferCacheRD? =
            if (handle.address() == 0L) null else FramebufferCacheRD(handle)

        // No MethodBinds emitted yet.
    }
}
