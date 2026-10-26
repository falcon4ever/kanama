package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A PBR (Physically Based Rendering) material to be used on 3D objects. Uses an ORM texture.
 *
 * Generated from Godot docs: ORMMaterial3D
 */
class ORMMaterial3D(handle: GodotHandle) : BaseMaterial3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ORMMaterial3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ORMMaterial3D? =
            if (handle.address() == 0L) null else RefCounted.owned(ORMMaterial3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ORMMaterial3D? =
            if (handle.address() == 0L) null else ORMMaterial3D(GodotHandle(handle))

        /** A new ORMMaterial3D, GDScript's `ORMMaterial3D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): ORMMaterial3D =
            RefCounted.owned(ORMMaterial3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("ORMMaterial3D"), "ORMMaterial3D"))))
    }
}
