package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract base class for 3D particle collision shapes affecting `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesCollision3D
 */
open class GPUParticlesCollision3D(handle: MemorySegment) : VisualInstance3D(handle) {
    var cullMask: Long
        @JvmName("cullMaskProperty")
        get() = getCullMask()
        @JvmName("setCullMaskProperty")
        set(value) = setCullMask(value)

    fun setCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, handle, mask)
    }

    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GPUParticlesCollision3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticlesCollision3D? =
            if (handle.address() == 0L) null else GPUParticlesCollision3D(handle)

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollision3D", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollision3D", "get_cull_mask", GET_CULL_MASK_HASH)
        }
    }
}
