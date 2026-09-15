package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Render data implementation for the RenderingDevice based renderers.
 *
 * Generated from Godot docs: RenderDataRD
 */
class RenderDataRD(handle: GodotHandle) : RenderData(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RenderDataRD? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RenderDataRD? =
            if (handle.address() == 0L) null else RenderDataRD(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
