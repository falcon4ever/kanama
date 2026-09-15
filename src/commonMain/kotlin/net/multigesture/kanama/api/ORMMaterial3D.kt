package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

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
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ORMMaterial3D? =
            if (handle.address() == 0L) null else ORMMaterial3D(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
