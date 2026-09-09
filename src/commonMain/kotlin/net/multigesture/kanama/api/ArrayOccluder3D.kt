package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * 3D polygon shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: ArrayOccluder3D
 */
class ArrayOccluder3D(handle: MemorySegment) : Occluder3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ArrayOccluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ArrayOccluder3D? =
            if (handle.address() == 0L) null else ArrayOccluder3D(handle)

        // No MethodBinds emitted yet.
    }
}
