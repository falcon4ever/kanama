package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A box-shaped 3D particle collision shape affecting `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesCollisionBox3D
 */
class GPUParticlesCollisionBox3D(handle: MemorySegment) : GPUParticlesCollision3D(handle) {
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
        fun fromHandle(handle: MemorySegment): GPUParticlesCollisionBox3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticlesCollisionBox3D? =
            if (handle.address() == 0L) null else GPUParticlesCollisionBox3D(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionBox3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionBox3D", "get_size", GET_SIZE_HASH)
        }
    }
}
