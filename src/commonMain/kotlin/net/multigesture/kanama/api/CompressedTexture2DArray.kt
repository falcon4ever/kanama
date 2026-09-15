package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Array of 2-dimensional textures, optionally compressed.
 *
 * Generated from Godot docs: CompressedTexture2DArray
 */
class CompressedTexture2DArray(handle: GodotHandle) : CompressedTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedTexture2DArray? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CompressedTexture2DArray? =
            if (handle.address() == 0L) null else CompressedTexture2DArray(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
