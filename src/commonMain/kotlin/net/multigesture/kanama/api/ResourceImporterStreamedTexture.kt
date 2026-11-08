package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Imports an image as a streaming-capable texture for dynamic mipmap loading.
 *
 * Generated from Godot docs: ResourceImporterStreamedTexture
 */
class ResourceImporterStreamedTexture(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterStreamedTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterStreamedTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterStreamedTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterStreamedTexture? =
            if (handle.address() == 0L) null else ResourceImporterStreamedTexture(GodotHandle(handle))

        /** A new ResourceImporterStreamedTexture, GDScript's `ResourceImporterStreamedTexture.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): ResourceImporterStreamedTexture =
            RefCounted.owned(ResourceImporterStreamedTexture(GodotHandle(requireConstructed(ObjectCalls.constructObject("ResourceImporterStreamedTexture"), "ResourceImporterStreamedTexture"))))
    }
}
