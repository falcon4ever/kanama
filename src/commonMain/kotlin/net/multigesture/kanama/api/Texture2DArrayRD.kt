package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Texture Array for 2D that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: Texture2DArrayRD
 */
class Texture2DArrayRD(handle: GodotHandle) : TextureLayeredRD(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Texture2DArrayRD? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Texture2DArrayRD? =
            if (handle.address() == 0L) null else Texture2DArrayRD(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
