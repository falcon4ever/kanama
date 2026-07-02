package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * Cuboid shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: BoxOccluder3D
 */
class BoxOccluder3D(handle: MemorySegment) : Occluder3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): BoxOccluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BoxOccluder3D? =
            if (handle.address() == 0L) null else BoxOccluder3D(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxOccluder3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxOccluder3D", "get_size", GET_SIZE_HASH)
        }
    }
}
