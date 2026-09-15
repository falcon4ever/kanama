package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Texture Array for Cubemaps that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: TextureCubemapArrayRD
 */
class TextureCubemapArrayRD(handle: GodotHandle) : TextureLayeredRD(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureCubemapArrayRD? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextureCubemapArrayRD? =
            if (handle.address() == 0L) null else TextureCubemapArrayRD(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
