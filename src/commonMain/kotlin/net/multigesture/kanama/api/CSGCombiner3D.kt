package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A CSG node that allows you to combine other CSG modifiers.
 *
 * Generated from Godot docs: CSGCombiner3D
 */
class CSGCombiner3D(handle: GodotHandle) : CSGShape3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGCombiner3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGCombiner3D? =
            if (handle.address() == 0L) null else CSGCombiner3D(GodotHandle(handle))

        /** A new CSGCombiner3D, GDScript's `CSGCombiner3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): CSGCombiner3D =
            CSGCombiner3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("CSGCombiner3D"), "CSGCombiner3D")))
    }
}
