package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Imports a 3-dimensional texture (`Texture3D`), a `Texture2DArray`, a `Cubemap` or a
 * `CubemapArray`.
 *
 * Generated from Godot docs: ResourceImporterLayeredTexture
 */
class ResourceImporterLayeredTexture(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterLayeredTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterLayeredTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterLayeredTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterLayeredTexture? =
            if (handle.address() == 0L) null else ResourceImporterLayeredTexture(GodotHandle(handle))
    }
}
