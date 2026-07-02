package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A sphere-shaped 3D particle collision shape affecting `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesCollisionSphere3D
 */
class GPUParticlesCollisionSphere3D(handle: MemorySegment) : GPUParticlesCollision3D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GPUParticlesCollisionSphere3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticlesCollisionSphere3D? =
            if (handle.address() == 0L) null else GPUParticlesCollisionSphere3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSphere3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSphere3D", "get_radius", GET_RADIUS_HASH)
        }
    }
}
