package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * This class allows for a RenderSceneData implementation to be made in GDExtension.
 *
 * Generated from Godot docs: RenderSceneDataExtension
 */
class RenderSceneDataExtension(handle: GodotHandle) : RenderSceneData(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RenderSceneDataExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RenderSceneDataExtension? =
            if (handle.address() == 0L) null else RenderSceneDataExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
