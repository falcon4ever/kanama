package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract class to expose editor gizmos for `Node3D`.
 *
 * Generated from Godot docs: Node3DGizmo
 */
open class Node3DGizmo(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Node3DGizmo? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Node3DGizmo? =
            if (handle.address() == 0L) null else Node3DGizmo(handle)

        // No MethodBinds emitted yet.
    }
}
