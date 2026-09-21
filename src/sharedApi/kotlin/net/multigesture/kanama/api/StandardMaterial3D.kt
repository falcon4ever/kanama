package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A PBR (Physically Based Rendering) material to be used on 3D objects.
 *
 * Generated from Godot docs: StandardMaterial3D
 */
class StandardMaterial3D(handle: GodotHandle) : BaseMaterial3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StandardMaterial3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): StandardMaterial3D? =
            if (handle.address() == 0L) null else StandardMaterial3D(GodotHandle(handle))

        // Instantiate a StandardMaterial3D.
        @JvmStatic
        fun create(): StandardMaterial3D =
            StandardMaterial3D(GodotHandle(ObjectCalls.constructObject("StandardMaterial3D")))

        // No MethodBinds emitted yet.
    }
}
