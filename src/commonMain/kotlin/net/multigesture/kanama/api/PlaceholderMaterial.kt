package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Placeholder class for a material.
 *
 * Generated from Godot docs: PlaceholderMaterial
 */
class PlaceholderMaterial(handle: MemorySegment) : Material(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PlaceholderMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PlaceholderMaterial? =
            if (handle.address() == 0L) null else PlaceholderMaterial(handle)

        // No MethodBinds emitted yet.
    }
}
