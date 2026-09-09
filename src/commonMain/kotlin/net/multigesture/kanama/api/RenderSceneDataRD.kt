package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Render scene data implementation for the RenderingDevice based renderers.
 *
 * Generated from Godot docs: RenderSceneDataRD
 */
class RenderSceneDataRD(handle: MemorySegment) : RenderSceneData(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderSceneDataRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderSceneDataRD? =
            if (handle.address() == 0L) null else RenderSceneDataRD(handle)

        // No MethodBinds emitted yet.
    }
}
