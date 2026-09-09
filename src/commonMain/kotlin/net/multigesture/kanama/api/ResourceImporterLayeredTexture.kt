package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports a 3-dimensional texture (`Texture3D`), a `Texture2DArray`, a `Cubemap` or a
 * `CubemapArray`.
 *
 * Generated from Godot docs: ResourceImporterLayeredTexture
 */
class ResourceImporterLayeredTexture(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterLayeredTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterLayeredTexture? =
            if (handle.address() == 0L) null else ResourceImporterLayeredTexture(handle)

        // No MethodBinds emitted yet.
    }
}
