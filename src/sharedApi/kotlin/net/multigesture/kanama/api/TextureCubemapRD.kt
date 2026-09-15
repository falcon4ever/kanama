package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Texture for Cubemap that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: TextureCubemapRD
 */
class TextureCubemapRD(handle: GodotHandle) : TextureLayeredRD(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureCubemapRD? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextureCubemapRD? =
            if (handle.address() == 0L) null else TextureCubemapRD(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
