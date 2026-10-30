package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Imports a glTF, FBX, COLLADA, or Blender 3D scene.
 *
 * Generated from Godot docs: ResourceImporterScene
 */
class ResourceImporterScene(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterScene? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterScene? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterScene(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterScene? =
            if (handle.address() == 0L) null else ResourceImporterScene(GodotHandle(handle))

        /** A new ResourceImporterScene, GDScript's `ResourceImporterScene.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): ResourceImporterScene =
            RefCounted.owned(ResourceImporterScene(GodotHandle(requireConstructed(ObjectCalls.constructObject("ResourceImporterScene"), "ResourceImporterScene"))))
    }
}
