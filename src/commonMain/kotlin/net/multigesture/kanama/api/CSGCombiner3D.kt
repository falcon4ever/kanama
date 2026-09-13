package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: CSGCombiner3D
 */
class CSGCombiner3D(handle: GodotHandle) : CSGShape3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGCombiner3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): CSGCombiner3D? =
            if (handle.address() == 0L) null else CSGCombiner3D(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
