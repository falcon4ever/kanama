package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * Node that projects a texture onto a `MeshInstance3D`.
 *
 * Generated from Godot docs: Decal
 */
class Decal(handle: MemorySegment) : VisualInstance3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var emissionEnergy: Double
        @JvmName("emissionEnergyProperty")
        get() = getEmissionEnergy()
        @JvmName("setEmissionEnergyProperty")
        set(value) = setEmissionEnergy(value)

    var modulate: Color
        @JvmName("modulateProperty")
        get() = getModulate()
        @JvmName("setModulateProperty")
        set(value) = setModulate(value)

    var albedoMix: Double
        @JvmName("albedoMixProperty")
        get() = getAlbedoMix()
        @JvmName("setAlbedoMixProperty")
        set(value) = setAlbedoMix(value)

    var normalFade: Double
        @JvmName("normalFadeProperty")
        get() = getNormalFade()
        @JvmName("setNormalFadeProperty")
        set(value) = setNormalFade(value)

    var upperFade: Double
        @JvmName("upperFadeProperty")
        get() = getUpperFade()
        @JvmName("setUpperFadeProperty")
        set(value) = setUpperFade(value)

    var lowerFade: Double
        @JvmName("lowerFadeProperty")
        get() = getLowerFade()
        @JvmName("setLowerFadeProperty")
        set(value) = setLowerFade(value)

    var distanceFadeEnabled: Boolean
        @JvmName("distanceFadeEnabledProperty")
        get() = isDistanceFadeEnabled()
        @JvmName("setDistanceFadeEnabledProperty")
        set(value) = setEnableDistanceFade(value)

    var distanceFadeBegin: Double
        @JvmName("distanceFadeBeginProperty")
        get() = getDistanceFadeBegin()
        @JvmName("setDistanceFadeBeginProperty")
        set(value) = setDistanceFadeBegin(value)

    var distanceFadeLength: Double
        @JvmName("distanceFadeLengthProperty")
        get() = getDistanceFadeLength()
        @JvmName("setDistanceFadeLengthProperty")
        set(value) = setDistanceFadeLength(value)

    var cullMask: Long
        @JvmName("cullMaskProperty")
        get() = getCullMask()
        @JvmName("setCullMaskProperty")
        set(value) = setCullMask(value)

    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    fun setTexture(type: Long, texture: Texture2D?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(setTextureBind, handle, type, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getTexture(type: Long): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getTextureBind, handle, type))
    }

    fun setEmissionEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionEnergyBind, handle, energy)
    }

    fun getEmissionEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionEnergyBind, handle)
    }

    fun setAlbedoMix(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlbedoMixBind, handle, energy)
    }

    fun getAlbedoMix(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlbedoMixBind, handle)
    }

    fun setModulate(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, color)
    }

    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    fun setUpperFade(fade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setUpperFadeBind, handle, fade)
    }

    fun getUpperFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUpperFadeBind, handle)
    }

    fun setLowerFade(fade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLowerFadeBind, handle, fade)
    }

    fun getLowerFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLowerFadeBind, handle)
    }

    fun setNormalFade(fade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNormalFadeBind, handle, fade)
    }

    fun getNormalFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNormalFadeBind, handle)
    }

    fun setEnableDistanceFade(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDistanceFadeBind, handle, enable)
    }

    fun isDistanceFadeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDistanceFadeEnabledBind, handle)
    }

    fun setDistanceFadeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeBeginBind, handle, distance)
    }

    fun getDistanceFadeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeBeginBind, handle)
    }

    fun setDistanceFadeLength(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeLengthBind, handle, distance)
    }

    fun getDistanceFadeLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeLengthBind, handle)
    }

    fun setCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, handle, mask)
    }

    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, handle)
    }

    companion object {
        const val TEXTURE_ALBEDO: Long = 0L
        const val TEXTURE_NORMAL: Long = 1L
        const val TEXTURE_ORM: Long = 2L
        const val TEXTURE_EMISSION: Long = 3L
        const val TEXTURE_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Decal? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Decal? =
            if (handle.address() == 0L) null else Decal(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_size", GET_SIZE_HASH)
        }

        private const val SET_TEXTURE_HASH = 2086764391L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3244119503L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_EMISSION_ENERGY_HASH = 373806689L
        private val setEmissionEnergyBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_emission_energy", SET_EMISSION_ENERGY_HASH)
        }

        private const val GET_EMISSION_ENERGY_HASH = 1740695150L
        private val getEmissionEnergyBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_emission_energy", GET_EMISSION_ENERGY_HASH)
        }

        private const val SET_ALBEDO_MIX_HASH = 373806689L
        private val setAlbedoMixBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_albedo_mix", SET_ALBEDO_MIX_HASH)
        }

        private const val GET_ALBEDO_MIX_HASH = 1740695150L
        private val getAlbedoMixBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_albedo_mix", GET_ALBEDO_MIX_HASH)
        }

        private const val SET_MODULATE_HASH = 2920490490L
        private val setModulateBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_modulate", SET_MODULATE_HASH)
        }

        private const val GET_MODULATE_HASH = 3444240500L
        private val getModulateBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_modulate", GET_MODULATE_HASH)
        }

        private const val SET_UPPER_FADE_HASH = 373806689L
        private val setUpperFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_upper_fade", SET_UPPER_FADE_HASH)
        }

        private const val GET_UPPER_FADE_HASH = 1740695150L
        private val getUpperFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_upper_fade", GET_UPPER_FADE_HASH)
        }

        private const val SET_LOWER_FADE_HASH = 373806689L
        private val setLowerFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_lower_fade", SET_LOWER_FADE_HASH)
        }

        private const val GET_LOWER_FADE_HASH = 1740695150L
        private val getLowerFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_lower_fade", GET_LOWER_FADE_HASH)
        }

        private const val SET_NORMAL_FADE_HASH = 373806689L
        private val setNormalFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_normal_fade", SET_NORMAL_FADE_HASH)
        }

        private const val GET_NORMAL_FADE_HASH = 1740695150L
        private val getNormalFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_normal_fade", GET_NORMAL_FADE_HASH)
        }

        private const val SET_ENABLE_DISTANCE_FADE_HASH = 2586408642L
        private val setEnableDistanceFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_enable_distance_fade", SET_ENABLE_DISTANCE_FADE_HASH)
        }

        private const val IS_DISTANCE_FADE_ENABLED_HASH = 36873697L
        private val isDistanceFadeEnabledBind by lazy {
            ObjectCalls.getMethodBind("Decal", "is_distance_fade_enabled", IS_DISTANCE_FADE_ENABLED_HASH)
        }

        private const val SET_DISTANCE_FADE_BEGIN_HASH = 373806689L
        private val setDistanceFadeBeginBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_distance_fade_begin", SET_DISTANCE_FADE_BEGIN_HASH)
        }

        private const val GET_DISTANCE_FADE_BEGIN_HASH = 1740695150L
        private val getDistanceFadeBeginBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_distance_fade_begin", GET_DISTANCE_FADE_BEGIN_HASH)
        }

        private const val SET_DISTANCE_FADE_LENGTH_HASH = 373806689L
        private val setDistanceFadeLengthBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_distance_fade_length", SET_DISTANCE_FADE_LENGTH_HASH)
        }

        private const val GET_DISTANCE_FADE_LENGTH_HASH = 1740695150L
        private val getDistanceFadeLengthBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_distance_fade_length", GET_DISTANCE_FADE_LENGTH_HASH)
        }

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_cull_mask", GET_CULL_MASK_HASH)
        }
    }
}
