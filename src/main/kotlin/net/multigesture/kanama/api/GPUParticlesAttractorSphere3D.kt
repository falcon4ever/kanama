package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A spheroid-shaped attractor that influences particles from `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesAttractorSphere3D
 */
class GPUParticlesAttractorSphere3D(handle: MemorySegment) : GPUParticlesAttractor3D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    /**
     * The attractor sphere's radius in 3D units. Note: Stretched ellipses can be obtained by using
     * non-uniform scaling on the `GPUParticlesAttractorSphere3D` node.
     *
     * Generated from Godot docs: GPUParticlesAttractorSphere3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    /**
     * The attractor sphere's radius in 3D units. Note: Stretched ellipses can be obtained by using
     * non-uniform scaling on the `GPUParticlesAttractorSphere3D` node.
     *
     * Generated from Godot docs: GPUParticlesAttractorSphere3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GPUParticlesAttractorSphere3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticlesAttractorSphere3D? =
            if (handle.address() == 0L) null else GPUParticlesAttractorSphere3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractorSphere3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractorSphere3D", "get_radius", GET_RADIUS_HASH)
        }
    }
}
