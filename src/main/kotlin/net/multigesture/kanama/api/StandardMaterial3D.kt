package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * A PBR (Physically Based Rendering) material to be used on 3D objects.
 *
 * Generated from Godot docs: StandardMaterial3D
 */
class StandardMaterial3D internal constructor(handle: MemorySegment) : BaseMaterial3D(handle) {
    companion object {
        @JvmStatic
        fun create(): StandardMaterial3D =
            StandardMaterial3D(ObjectCalls.constructObject("StandardMaterial3D"))

        @JvmStatic
        fun fromHandle(handle: MemorySegment): StandardMaterial3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StandardMaterial3D? =
            if (handle.address() == 0L) null else StandardMaterial3D(handle)
    }
}
