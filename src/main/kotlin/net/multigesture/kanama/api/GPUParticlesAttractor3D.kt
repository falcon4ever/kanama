package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract base class for 3D particle attractors.
 *
 * Generated from Godot docs: GPUParticlesAttractor3D
 */
open class GPUParticlesAttractor3D(handle: MemorySegment) : VisualInstance3D(handle) {
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

    fun setCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, handle, mask)
    }

    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, handle)
    }

    fun setStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStrengthBind, handle, strength)
    }

    fun getStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStrengthBind, handle)
    }

    fun setAttenuation(attenuation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAttenuationBind, handle, attenuation)
    }

    fun getAttenuation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAttenuationBind, handle)
    }

    fun setDirectionality(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDirectionalityBind, handle, amount)
    }

    fun getDirectionality(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDirectionalityBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GPUParticlesAttractor3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticlesAttractor3D? =
            if (handle.address() == 0L) null else GPUParticlesAttractor3D(handle)

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "get_cull_mask", GET_CULL_MASK_HASH)
        }

        private const val SET_STRENGTH_HASH = 373806689L
        private val setStrengthBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "set_strength", SET_STRENGTH_HASH)
        }

        private const val GET_STRENGTH_HASH = 1740695150L
        private val getStrengthBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "get_strength", GET_STRENGTH_HASH)
        }

        private const val SET_ATTENUATION_HASH = 373806689L
        private val setAttenuationBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "set_attenuation", SET_ATTENUATION_HASH)
        }

        private const val GET_ATTENUATION_HASH = 1740695150L
        private val getAttenuationBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "get_attenuation", GET_ATTENUATION_HASH)
        }

        private const val SET_DIRECTIONALITY_HASH = 373806689L
        private val setDirectionalityBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "set_directionality", SET_DIRECTIONALITY_HASH)
        }

        private const val GET_DIRECTIONALITY_HASH = 1740695150L
        private val getDirectionalityBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractor3D", "get_directionality", GET_DIRECTIONALITY_HASH)
        }
    }
}
