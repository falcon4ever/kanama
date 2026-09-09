package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports a collection of textures from a PNG image into an optimized `AtlasTexture` for 2D
 * rendering.
 *
 * Generated from Godot docs: ResourceImporterTextureAtlas
 */
class ResourceImporterTextureAtlas(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterTextureAtlas? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterTextureAtlas? =
            if (handle.address() == 0L) null else ResourceImporterTextureAtlas(handle)

        // No MethodBinds emitted yet.
    }
}
