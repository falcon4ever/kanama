package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Texture Array for Cubemaps that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: TextureCubemapArrayRD
 */
class TextureCubemapArrayRD(handle: MemorySegment) : TextureLayeredRD(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextureCubemapArrayRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextureCubemapArrayRD? =
            if (handle.address() == 0L) null else TextureCubemapArrayRD(handle)

        // No MethodBinds emitted yet.
    }
}
