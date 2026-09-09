package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * This class allows for a RenderSceneData implementation to be made in GDExtension.
 *
 * Generated from Godot docs: RenderSceneDataExtension
 */
class RenderSceneDataExtension(handle: MemorySegment) : RenderSceneData(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderSceneDataExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderSceneDataExtension? =
            if (handle.address() == 0L) null else RenderSceneDataExtension(handle)

        // No MethodBinds emitted yet.
    }
}
