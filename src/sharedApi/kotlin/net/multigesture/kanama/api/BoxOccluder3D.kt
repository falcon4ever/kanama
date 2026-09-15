package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Cuboid shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: BoxOccluder3D
 */
class BoxOccluder3D(handle: GodotHandle) : Occluder3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    /**
     * The box's size in 3D units.
     *
     * Generated from Godot docs: BoxOccluder3D.set_size
     */
    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, segment, size)
    }

    /**
     * The box's size in 3D units.
     *
     * Generated from Godot docs: BoxOccluder3D.get_size
     */
    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BoxOccluder3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): BoxOccluder3D? =
            if (handle.address() == 0L) null else BoxOccluder3D(GodotHandle(handle))

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
