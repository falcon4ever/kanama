package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A sphere-shaped 3D particle collision shape affecting `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesCollisionSphere3D
 */
class GPUParticlesCollisionSphere3D(handle: GodotHandle) : GPUParticlesCollision3D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    /**
     * The collision sphere's radius in 3D units.
     *
     * Generated from Godot docs: GPUParticlesCollisionSphere3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, segment, radius)
    }

    /**
     * The collision sphere's radius in 3D units.
     *
     * Generated from Godot docs: GPUParticlesCollisionSphere3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GPUParticlesCollisionSphere3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GPUParticlesCollisionSphere3D? =
            if (handle.address() == 0L) null else GPUParticlesCollisionSphere3D(GodotHandle(handle))

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
