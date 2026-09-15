package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract class to expose editor gizmos for `Node3D`.
 *
 * Generated from Godot docs: Node3DGizmo
 */
open class Node3DGizmo(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Node3DGizmo? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Node3DGizmo? =
            if (handle.address() == 0L) null else Node3DGizmo(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
