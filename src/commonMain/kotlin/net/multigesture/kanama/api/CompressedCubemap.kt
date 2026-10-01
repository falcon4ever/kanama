package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An optionally compressed `Cubemap`.
 *
 * Generated from Godot docs: CompressedCubemap
 */
class CompressedCubemap(handle: GodotHandle) : CompressedTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedCubemap? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CompressedCubemap? =
            if (handle.address() == 0L) null else CompressedCubemap(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
