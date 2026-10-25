package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Mesh with a single point primitive.
 *
 * Generated from Godot docs: PointMesh
 */
class PointMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PointMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PointMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(PointMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PointMesh? =
            if (handle.address() == 0L) null else PointMesh(GodotHandle(handle))

        /** A new PointMesh, GDScript's `PointMesh.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PointMesh =
            RefCounted.owned(PointMesh(GodotHandle(ObjectCalls.constructObject("PointMesh"))))
    }
}
