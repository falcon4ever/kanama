package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Render scene data implementation for the RenderingDevice based renderers.
 *
 * Generated from Godot docs: RenderSceneDataRD
 */
class RenderSceneDataRD(handle: GodotHandle) : RenderSceneData(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RenderSceneDataRD? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RenderSceneDataRD? =
            if (handle.address() == 0L) null else RenderSceneDataRD(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
