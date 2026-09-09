package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * This class allows for a RenderData implementation to be made in GDExtension.
 *
 * Generated from Godot docs: RenderDataExtension
 */
class RenderDataExtension(handle: MemorySegment) : RenderData(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderDataExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderDataExtension? =
            if (handle.address() == 0L) null else RenderDataExtension(handle)

        // No MethodBinds emitted yet.
    }
}
