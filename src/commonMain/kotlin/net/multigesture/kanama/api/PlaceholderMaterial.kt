package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Placeholder class for a material.
 *
 * Generated from Godot docs: PlaceholderMaterial
 */
class PlaceholderMaterial(handle: GodotHandle) : Material(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderMaterial? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PlaceholderMaterial? =
            if (handle.address() == 0L) null else PlaceholderMaterial(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
