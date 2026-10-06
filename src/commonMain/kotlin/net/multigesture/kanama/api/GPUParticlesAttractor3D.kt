package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract base class for 3D particle attractors.
 *
 * Generated from Godot docs: GPUParticlesAttractor3D
 */
open class GPUParticlesAttractor3D(handle: GodotHandle) : VisualInstance3D(handle) {
    var strength: Double
        @JvmName("strengthProperty")
        get() = getStrength()
        @JvmName("setStrengthProperty")
        set(value) = setStrength(value)

    var attenuation: Double
        @JvmName("attenuationProperty")
        get() = getAttenuation()
        @JvmName("setAttenuationProperty")
        set(value) = setAttenuation(value)

    var directionality: Double
        @JvmName("directionalityProperty")
        get() = getDirectionality()
        @JvmName("setDirectionalityProperty")
        set(value) = setDirectionality(value)

    var cullMask: Long
        @JvmName("cullMaskProperty")
        get() = getCullMask()
        @JvmName("setCullMaskProperty")
        set(value) = setCullMask(value)

    /**
     * The particle rendering layers (`VisualInstance3D.layers`) that will be affected by the
     * attractor. By default, all particles are affected by an attractor. After configuring particle
     * nodes accordingly, specific layers can be unchecked to prevent certain particles from being
     * affected by attractors. For example, this can be used if you're using an attractor as part of a
     * spell effect but don't want the attractor to affect unrelated weather particles at the same
     * position. Particle attraction can also be disabled on a per-process material basis by setting
     * `ParticleProcessMaterial.attractor_interaction_enabled` on the `GPUParticles3D` node.
     *
     * Generated from Godot docs: GPUParticlesAttractor3D.set_cull_mask
     */
    fun setCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCullMaskBind, segment, mask)
    }

    /**
     * The particle rendering layers (`VisualInstance3D.layers`) that will be affected by the
     * attractor. By default, all particles are affected by an attractor. After configuring particle
     * nodes accordingly, specific layers can be unchecked to prevent certain particles from being
     * affected by attractors. For example, this can be used if you're using an attractor as part of a
     * spell effect but don't want the attractor to affect unrelated weather particles at the same
     * position. Particle attraction can also be disabled on a per-process material basis by setting
     * `ParticleProcessMaterial.attractor_interaction_enabled` on the `GPUParticles3D` node.
     *
     * Generated from Godot docs: GPUParticlesAttractor3D.get_cull_mask
     */
    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCullMaskBind, segment)
    }

    /**
     * Adjusts the strength of the attractor. If `strength` is negative, particles will be pushed in
     * the opposite direction. Particles will be pushed away from the attractor's origin if
     * `directionality` is `0.0`, or towards local +Z if `directionality` is greater than `0.0`.
     *
     * Generated from Godot docs: GPUParticlesAttractor3D.set_strength
     */
    fun setStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStrengthBind, segment, strength)
    }

    /**
     * Adjusts the strength of the attractor. If `strength` is negative, particles will be pushed in
     * the opposite direction. Particles will be pushed away from the attractor's origin if
     * `directionality` is `0.0`, or towards local +Z if `directionality` is greater than `0.0`.
     *
     * Generated from Godot docs: GPUParticlesAttractor3D.get_strength
     */
    fun getStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStrengthBind, segment)
    }

    /**
     * The particle attractor's attenuation. Higher values result in more gradual pushing of particles
     * as they come closer to the attractor's origin. Zero or negative values will cause particles to
     * be pushed very fast as soon as the touch the attractor's edges.
     *
     * Generated from Godot docs: GPUParticlesAttractor3D.set_attenuation
     */
    fun setAttenuation(attenuation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAttenuationBind, segment, attenuation)
    }

    /**
     * The particle attractor's attenuation. Higher values result in more gradual pushing of particles
     * as they come closer to the attractor's origin. Zero or negative values will cause particles to
     * be pushed very fast as soon as the touch the attractor's edges.
     *
     * Generated from Godot docs: GPUParticlesAttractor3D.get_attenuation
     */
    fun getAttenuation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAttenuationBind, segment)
    }

    /**
     * Adjusts how directional the attractor is. At `0.0`, the attractor is not directional at all: it
     * will attract particles towards its center. At `1.0`, the attractor is fully directional:
     * particles will always be pushed towards local -Z (or +Z if `strength` is negative). Note: If
     * `directionality` is greater than `0.0`, the direction in which particles are pushed can be
     * changed by rotating the `GPUParticlesAttractor3D` node.
     *
     * Generated from Godot docs: GPUParticlesAttractor3D.set_directionality
     */
    fun setDirectionality(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDirectionalityBind, segment, amount)
    }

    /**
     * Adjusts how directional the attractor is. At `0.0`, the attractor is not directional at all: it
     * will attract particles towards its center. At `1.0`, the attractor is fully directional:
     * particles will always be pushed towards local -Z (or +Z if `strength` is negative). Note: If
     * `directionality` is greater than `0.0`, the direction in which particles are pushed can be
     * changed by rotating the `GPUParticlesAttractor3D` node.
     *
     * Generated from Godot docs: GPUParticlesAttractor3D.get_directionality
     */
    fun getDirectionality(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDirectionalityBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GPUParticlesAttractor3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GPUParticlesAttractor3D? =
            if (handle.address() == 0L) null else GPUParticlesAttractor3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CULL_MASK_HASH = 1286410249L
        @JvmField
        val setCullMaskBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "set_cull_mask", SET_CULL_MASK_HASH)

        private const val GET_CULL_MASK_HASH = 3905245786L
        @JvmField
        val getCullMaskBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "get_cull_mask", GET_CULL_MASK_HASH)

        private const val SET_STRENGTH_HASH = 373806689L
        @JvmField
        val setStrengthBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "set_strength", SET_STRENGTH_HASH)

        private const val GET_STRENGTH_HASH = 1740695150L
        @JvmField
        val getStrengthBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "get_strength", GET_STRENGTH_HASH)

        private const val SET_ATTENUATION_HASH = 373806689L
        @JvmField
        val setAttenuationBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "set_attenuation", SET_ATTENUATION_HASH)

        private const val GET_ATTENUATION_HASH = 1740695150L
        @JvmField
        val getAttenuationBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "get_attenuation", GET_ATTENUATION_HASH)

        private const val SET_DIRECTIONALITY_HASH = 373806689L
        @JvmField
        val setDirectionalityBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "set_directionality", SET_DIRECTIONALITY_HASH)

        private const val GET_DIRECTIONALITY_HASH = 1740695150L
        @JvmField
        val getDirectionalityBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "get_directionality", GET_DIRECTIONALITY_HASH)
    }
}
