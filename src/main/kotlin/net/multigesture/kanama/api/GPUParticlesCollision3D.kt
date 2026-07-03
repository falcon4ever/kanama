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

    /**
     * The particle rendering layers (`VisualInstance3D.layers`) that will be affected by the collision
     * shape. By default, all particles that have `ParticleProcessMaterial.collision_mode` set to
     * `ParticleProcessMaterial.COLLISION_RIGID` or `ParticleProcessMaterial.COLLISION_HIDE_ON_CONTACT`
     * will be affected by a collision shape. After configuring particle nodes accordingly, specific
     * layers can be unchecked to prevent certain particles from being affected by colliders. For
     * example, this can be used if you're using a collider as part of a spell effect but don't want
     * the collider to affect unrelated weather particles at the same position. Particle collision can
     * also be disabled on a per-process material basis by setting
     * `ParticleProcessMaterial.collision_mode` on the `GPUParticles3D` node.
     *
     * Generated from Godot docs: GPUParticlesCollision3D.set_cull_mask
     */
    fun setCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, handle, mask)
    }

    /**
     * The particle rendering layers (`VisualInstance3D.layers`) that will be affected by the collision
     * shape. By default, all particles that have `ParticleProcessMaterial.collision_mode` set to
     * `ParticleProcessMaterial.COLLISION_RIGID` or `ParticleProcessMaterial.COLLISION_HIDE_ON_CONTACT`
     * will be affected by a collision shape. After configuring particle nodes accordingly, specific
     * layers can be unchecked to prevent certain particles from being affected by colliders. For
     * example, this can be used if you're using a collider as part of a spell effect but don't want
     * the collider to affect unrelated weather particles at the same position. Particle collision can
     * also be disabled on a per-process material basis by setting
     * `ParticleProcessMaterial.collision_mode` on the `GPUParticles3D` node.
     *
     * Generated from Godot docs: GPUParticlesCollision3D.get_cull_mask
     */
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
