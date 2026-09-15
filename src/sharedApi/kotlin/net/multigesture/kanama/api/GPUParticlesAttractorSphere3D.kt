package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A spheroid-shaped attractor that influences particles from `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesAttractorSphere3D
 */
class GPUParticlesAttractorSphere3D(handle: GodotHandle) : GPUParticlesAttractor3D(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, segment, radius)
    }

    /**
     * The attractor sphere's radius in 3D units. Note: Stretched ellipses can be obtained by using
     * non-uniform scaling on the `GPUParticlesAttractorSphere3D` node.
     *
     * Generated from Godot docs: GPUParticlesAttractorSphere3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GPUParticlesAttractorSphere3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GPUParticlesAttractorSphere3D? =
            if (handle.address() == 0L) null else GPUParticlesAttractorSphere3D(GodotHandle(handle))

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
