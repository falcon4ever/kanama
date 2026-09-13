package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * An optionally compressed `CubemapArray`.
 *
 * Generated from Godot docs: CompressedCubemapArray
 */
class CompressedCubemapArray(handle: GodotHandle) : CompressedTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedCubemapArray? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): CompressedCubemapArray? =
            if (handle.address() == 0L) null else CompressedCubemapArray(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
