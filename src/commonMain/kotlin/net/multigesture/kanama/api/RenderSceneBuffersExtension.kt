package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * This class allows for a RenderSceneBuffer implementation to be made in GDExtension.
 *
 * Generated from Godot docs: RenderSceneBuffersExtension
 */
class RenderSceneBuffersExtension(handle: GodotHandle) : RenderSceneBuffers(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RenderSceneBuffersExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RenderSceneBuffersExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(RenderSceneBuffersExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RenderSceneBuffersExtension? =
            if (handle.address() == 0L) null else RenderSceneBuffersExtension(GodotHandle(handle))

        /** A new RenderSceneBuffersExtension, GDScript's `RenderSceneBuffersExtension.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): RenderSceneBuffersExtension =
            RefCounted.owned(RenderSceneBuffersExtension(GodotHandle(requireConstructed(ObjectCalls.constructObject("RenderSceneBuffersExtension"), "RenderSceneBuffersExtension"))))
    }
}
