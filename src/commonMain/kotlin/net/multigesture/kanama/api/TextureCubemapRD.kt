package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Texture for Cubemap that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: TextureCubemapRD
 */
class TextureCubemapRD(handle: MemorySegment) : TextureLayeredRD(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextureCubemapRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextureCubemapRD? =
            if (handle.address() == 0L) null else TextureCubemapRD(handle)

        // No MethodBinds emitted yet.
    }
}
