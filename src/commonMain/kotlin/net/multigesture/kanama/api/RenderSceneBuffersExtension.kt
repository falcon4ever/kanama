package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * This class allows for a RenderSceneBuffer implementation to be made in GDExtension.
 *
 * Generated from Godot docs: RenderSceneBuffersExtension
 */
class RenderSceneBuffersExtension(handle: MemorySegment) : RenderSceneBuffers(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderSceneBuffersExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderSceneBuffersExtension? =
            if (handle.address() == 0L) null else RenderSceneBuffersExtension(handle)

        // No MethodBinds emitted yet.
    }
}
