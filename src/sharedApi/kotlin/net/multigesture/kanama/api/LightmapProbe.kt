package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a single manually placed probe for dynamic object lighting with `LightmapGI`.
 *
 * Generated from Godot docs: LightmapProbe
 */
class LightmapProbe(handle: GodotHandle) : Node3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): LightmapProbe? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): LightmapProbe? =
            if (handle.address() == 0L) null else LightmapProbe(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
