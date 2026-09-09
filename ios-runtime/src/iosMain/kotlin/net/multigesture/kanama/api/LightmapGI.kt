package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: LightmapGI
 */
class LightmapGI(handle: MemorySegment) : VisualInstance3D(handle) {
    var quality: Long
        @JvmName("qualityProperty")
        get() = getBakeQuality()
        @JvmName("setQualityProperty")
        set(value) = setBakeQuality(value)

    var supersampling: Boolean
        @JvmName("supersamplingProperty")
        get() = isSupersamplingEnabled()
        @JvmName("setSupersamplingProperty")
        set(value) = setSupersamplingEnabled(value)

    var supersamplingFactor: Double
        @JvmName("supersamplingFactorProperty")
        get() = getSupersamplingFactor()
        @JvmName("setSupersamplingFactorProperty")
        set(value) = setSupersamplingFactor(value)

    var bounces: Int
        @JvmName("bouncesProperty")
        get() = getBounces()
        @JvmName("setBouncesProperty")
        set(value) = setBounces(value)

    var bounceIndirectEnergy: Double
        @JvmName("bounceIndirectEnergyProperty")
        get() = getBounceIndirectEnergy()
        @JvmName("setBounceIndirectEnergyProperty")
        set(value) = setBounceIndirectEnergy(value)

    var directional: Boolean
        @JvmName("directionalProperty")
        get() = isDirectional()
        @JvmName("setDirectionalProperty")
        set(value) = setDirectional(value)

    var shadowmaskMode: Long
        @JvmName("shadowmaskModeProperty")
        get() = getShadowmaskMode()
        @JvmName("setShadowmaskModeProperty")
        set(value) = setShadowmaskMode(value)

    var useTextureForBounces: Boolean
        @JvmName("useTextureForBouncesProperty")
        get() = isUsingTextureForBounces()
        @JvmName("setUseTextureForBouncesProperty")
        set(value) = setUseTextureForBounces(value)

    var interior: Boolean
        @JvmName("interiorProperty")
        get() = isInterior()
        @JvmName("setInteriorProperty")
        set(value) = setInterior(value)

    var useDenoiser: Boolean
        @JvmName("useDenoiserProperty")
        get() = isUsingDenoiser()
        @JvmName("setUseDenoiserProperty")
        set(value) = setUseDenoiser(value)

    var denoiserStrength: Double
        @JvmName("denoiserStrengthProperty")
        get() = getDenoiserStrength()
        @JvmName("setDenoiserStrengthProperty")
        set(value) = setDenoiserStrength(value)

    var denoiserRange: Int
        @JvmName("denoiserRangeProperty")
        get() = getDenoiserRange()
        @JvmName("setDenoiserRangeProperty")
        set(value) = setDenoiserRange(value)

    var bias: Double
        @JvmName("biasProperty")
        get() = getBias()
        @JvmName("setBiasProperty")
        set(value) = setBias(value)

    var texelScale: Double
        @JvmName("texelScaleProperty")
        get() = getTexelScale()
        @JvmName("setTexelScaleProperty")
        set(value) = setTexelScale(value)

    var maxTextureSize: Int
        @JvmName("maxTextureSizeProperty")
        get() = getMaxTextureSize()
        @JvmName("setMaxTextureSizeProperty")
        set(value) = setMaxTextureSize(value)

    var environmentMode: Long
        @JvmName("environmentModeProperty")
        get() = getEnvironmentMode()
        @JvmName("setEnvironmentModeProperty")
        set(value) = setEnvironmentMode(value)

    var environmentCustomSky: Sky?
        @JvmName("environmentCustomSkyProperty")
        get() = getEnvironmentCustomSky()
        @JvmName("setEnvironmentCustomSkyProperty")
        set(value) = setEnvironmentCustomSky(value)

    var environmentCustomColor: Color
        @JvmName("environmentCustomColorProperty")
        get() = getEnvironmentCustomColor()
        @JvmName("setEnvironmentCustomColorProperty")
        set(value) = setEnvironmentCustomColor(value)

    var environmentCustomEnergy: Double
        @JvmName("environmentCustomEnergyProperty")
        get() = getEnvironmentCustomEnergy()
        @JvmName("setEnvironmentCustomEnergyProperty")
        set(value) = setEnvironmentCustomEnergy(value)

    var cameraAttributes: CameraAttributes?
        @JvmName("cameraAttributesProperty")
        get() = getCameraAttributes()
        @JvmName("setCameraAttributesProperty")
        set(value) = setCameraAttributes(value)

    var generateProbesSubdiv: Long
        @JvmName("generateProbesSubdivProperty")
        get() = getGenerateProbes()
        @JvmName("setGenerateProbesSubdivProperty")
        set(value) = setGenerateProbes(value)

    var lightData: LightmapGIData?
        @JvmName("lightDataProperty")
        get() = getLightData()
        @JvmName("setLightDataProperty")
        set(value) = setLightData(value)

    fun setLightData(data: LightmapGIData?) {
        ObjectCalls.ptrcallWithObjectArgs(setLightDataBind, handle, listOf(data?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getLightData(): LightmapGIData? {
        return LightmapGIData.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLightDataBind, handle))
    }

    fun setBakeQuality(bakeQuality: Long) {
        ObjectCalls.ptrcallWithLongArg(setBakeQualityBind, handle, bakeQuality)
    }

    fun getBakeQuality(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBakeQualityBind, handle)
    }

    fun setBounces(bounces: Int) {
        ObjectCalls.ptrcallWithIntArg(setBouncesBind, handle, bounces)
    }

    fun getBounces(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBouncesBind, handle)
    }

    fun setBounceIndirectEnergy(bounceIndirectEnergy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBounceIndirectEnergyBind, handle, bounceIndirectEnergy)
    }

    fun getBounceIndirectEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBounceIndirectEnergyBind, handle)
    }

    fun setGenerateProbes(subdivision: Long) {
        ObjectCalls.ptrcallWithLongArg(setGenerateProbesBind, handle, subdivision)
    }

    fun getGenerateProbes(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGenerateProbesBind, handle)
    }

    fun setBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBiasBind, handle, bias)
    }

    fun getBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBiasBind, handle)
    }

    fun setEnvironmentMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setEnvironmentModeBind, handle, mode)
    }

    fun getEnvironmentMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEnvironmentModeBind, handle)
    }

    fun setEnvironmentCustomSky(sky: Sky?) {
        ObjectCalls.ptrcallWithObjectArgs(setEnvironmentCustomSkyBind, handle, listOf(sky?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getEnvironmentCustomSky(): Sky? {
        return Sky.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEnvironmentCustomSkyBind, handle))
    }

    fun setEnvironmentCustomColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setEnvironmentCustomColorBind, handle, color)
    }

    fun getEnvironmentCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getEnvironmentCustomColorBind, handle)
    }

    fun setEnvironmentCustomEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnvironmentCustomEnergyBind, handle, energy)
    }

    fun getEnvironmentCustomEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnvironmentCustomEnergyBind, handle)
    }

    fun setTexelScale(texelScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTexelScaleBind, handle, texelScale)
    }

    fun getTexelScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTexelScaleBind, handle)
    }

    fun setMaxTextureSize(maxTextureSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxTextureSizeBind, handle, maxTextureSize)
    }

    fun getMaxTextureSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxTextureSizeBind, handle)
    }

    fun setSupersamplingEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSupersamplingEnabledBind, handle, enable)
    }

    fun isSupersamplingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSupersamplingEnabledBind, handle)
    }

    fun setSupersamplingFactor(factor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSupersamplingFactorBind, handle, factor)
    }

    fun getSupersamplingFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSupersamplingFactorBind, handle)
    }

    fun setUseDenoiser(useDenoiser: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseDenoiserBind, handle, useDenoiser)
    }

    fun isUsingDenoiser(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingDenoiserBind, handle)
    }

    fun setDenoiserStrength(denoiserStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDenoiserStrengthBind, handle, denoiserStrength)
    }

    fun getDenoiserStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDenoiserStrengthBind, handle)
    }

    fun setDenoiserRange(denoiserRange: Int) {
        ObjectCalls.ptrcallWithIntArg(setDenoiserRangeBind, handle, denoiserRange)
    }

    fun getDenoiserRange(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDenoiserRangeBind, handle)
    }

    fun setInterior(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInteriorBind, handle, enable)
    }

    fun isInterior(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInteriorBind, handle)
    }

    fun setDirectional(directional: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDirectionalBind, handle, directional)
    }

    fun isDirectional(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDirectionalBind, handle)
    }

    fun setShadowmaskMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setShadowmaskModeBind, handle, mode)
    }

    fun getShadowmaskMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShadowmaskModeBind, handle)
    }

    fun setUseTextureForBounces(useTextureForBounces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseTextureForBouncesBind, handle, useTextureForBounces)
    }

    fun isUsingTextureForBounces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTextureForBouncesBind, handle)
    }

    fun setCameraAttributes(cameraAttributes: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(setCameraAttributesBind, handle, listOf(cameraAttributes?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCameraAttributes(): CameraAttributes? {
        return CameraAttributes.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCameraAttributesBind, handle))
    }

    companion object {
        const val BAKE_QUALITY_LOW: Long = 0L
        const val BAKE_QUALITY_MEDIUM: Long = 1L
        const val BAKE_QUALITY_HIGH: Long = 2L
        const val BAKE_QUALITY_ULTRA: Long = 3L
        const val GENERATE_PROBES_DISABLED: Long = 0L
        const val GENERATE_PROBES_SUBDIV_4: Long = 1L
        const val GENERATE_PROBES_SUBDIV_8: Long = 2L
        const val GENERATE_PROBES_SUBDIV_16: Long = 3L
        const val GENERATE_PROBES_SUBDIV_32: Long = 4L
        const val BAKE_ERROR_OK: Long = 0L
        const val BAKE_ERROR_NO_SCENE_ROOT: Long = 1L
        const val BAKE_ERROR_FOREIGN_DATA: Long = 2L
        const val BAKE_ERROR_NO_LIGHTMAPPER: Long = 3L
        const val BAKE_ERROR_NO_SAVE_PATH: Long = 4L
        const val BAKE_ERROR_NO_MESHES: Long = 5L
        const val BAKE_ERROR_MESHES_INVALID: Long = 6L
        const val BAKE_ERROR_CANT_CREATE_IMAGE: Long = 7L
        const val BAKE_ERROR_USER_ABORTED: Long = 8L
        const val BAKE_ERROR_TEXTURE_SIZE_TOO_SMALL: Long = 9L
        const val BAKE_ERROR_LIGHTMAP_TOO_SMALL: Long = 10L
        const val BAKE_ERROR_ATLAS_TOO_SMALL: Long = 11L
        const val ENVIRONMENT_MODE_DISABLED: Long = 0L
        const val ENVIRONMENT_MODE_SCENE: Long = 1L
        const val ENVIRONMENT_MODE_CUSTOM_SKY: Long = 2L
        const val ENVIRONMENT_MODE_CUSTOM_COLOR: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): LightmapGI? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LightmapGI? =
            if (handle.address() == 0L) null else LightmapGI(handle)

        // Instantiate a LightmapGI node.
        fun create(): LightmapGI =
            LightmapGI(MemorySegment.ofAddress(IosGodot.constructObject("LightmapGI")))

        private const val SET_LIGHT_DATA_HASH = 1790597277L
        private val setLightDataBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_light_data", SET_LIGHT_DATA_HASH)
        }

        private const val GET_LIGHT_DATA_HASH = 290354153L
        private val getLightDataBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_light_data", GET_LIGHT_DATA_HASH)
        }

        private const val SET_BAKE_QUALITY_HASH = 1192215803L
        private val setBakeQualityBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_bake_quality", SET_BAKE_QUALITY_HASH)
        }

        private const val GET_BAKE_QUALITY_HASH = 688832735L
        private val getBakeQualityBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_bake_quality", GET_BAKE_QUALITY_HASH)
        }

        private const val SET_BOUNCES_HASH = 1286410249L
        private val setBouncesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_bounces", SET_BOUNCES_HASH)
        }

        private const val GET_BOUNCES_HASH = 3905245786L
        private val getBouncesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_bounces", GET_BOUNCES_HASH)
        }

        private const val SET_BOUNCE_INDIRECT_ENERGY_HASH = 373806689L
        private val setBounceIndirectEnergyBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_bounce_indirect_energy", SET_BOUNCE_INDIRECT_ENERGY_HASH)
        }

        private const val GET_BOUNCE_INDIRECT_ENERGY_HASH = 1740695150L
        private val getBounceIndirectEnergyBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_bounce_indirect_energy", GET_BOUNCE_INDIRECT_ENERGY_HASH)
        }

        private const val SET_GENERATE_PROBES_HASH = 549981046L
        private val setGenerateProbesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_generate_probes", SET_GENERATE_PROBES_HASH)
        }

        private const val GET_GENERATE_PROBES_HASH = 3930596226L
        private val getGenerateProbesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_generate_probes", GET_GENERATE_PROBES_HASH)
        }

        private const val SET_BIAS_HASH = 373806689L
        private val setBiasBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_bias", SET_BIAS_HASH)
        }

        private const val GET_BIAS_HASH = 1740695150L
        private val getBiasBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_bias", GET_BIAS_HASH)
        }

        private const val SET_ENVIRONMENT_MODE_HASH = 2282650285L
        private val setEnvironmentModeBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_environment_mode", SET_ENVIRONMENT_MODE_HASH)
        }

        private const val GET_ENVIRONMENT_MODE_HASH = 4128646479L
        private val getEnvironmentModeBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_environment_mode", GET_ENVIRONMENT_MODE_HASH)
        }

        private const val SET_ENVIRONMENT_CUSTOM_SKY_HASH = 3336722921L
        private val setEnvironmentCustomSkyBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_environment_custom_sky", SET_ENVIRONMENT_CUSTOM_SKY_HASH)
        }

        private const val GET_ENVIRONMENT_CUSTOM_SKY_HASH = 1177136966L
        private val getEnvironmentCustomSkyBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_environment_custom_sky", GET_ENVIRONMENT_CUSTOM_SKY_HASH)
        }

        private const val SET_ENVIRONMENT_CUSTOM_COLOR_HASH = 2920490490L
        private val setEnvironmentCustomColorBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_environment_custom_color", SET_ENVIRONMENT_CUSTOM_COLOR_HASH)
        }

        private const val GET_ENVIRONMENT_CUSTOM_COLOR_HASH = 3444240500L
        private val getEnvironmentCustomColorBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_environment_custom_color", GET_ENVIRONMENT_CUSTOM_COLOR_HASH)
        }

        private const val SET_ENVIRONMENT_CUSTOM_ENERGY_HASH = 373806689L
        private val setEnvironmentCustomEnergyBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_environment_custom_energy", SET_ENVIRONMENT_CUSTOM_ENERGY_HASH)
        }

        private const val GET_ENVIRONMENT_CUSTOM_ENERGY_HASH = 1740695150L
        private val getEnvironmentCustomEnergyBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_environment_custom_energy", GET_ENVIRONMENT_CUSTOM_ENERGY_HASH)
        }

        private const val SET_TEXEL_SCALE_HASH = 373806689L
        private val setTexelScaleBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_texel_scale", SET_TEXEL_SCALE_HASH)
        }

        private const val GET_TEXEL_SCALE_HASH = 1740695150L
        private val getTexelScaleBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_texel_scale", GET_TEXEL_SCALE_HASH)
        }

        private const val SET_MAX_TEXTURE_SIZE_HASH = 1286410249L
        private val setMaxTextureSizeBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_max_texture_size", SET_MAX_TEXTURE_SIZE_HASH)
        }

        private const val GET_MAX_TEXTURE_SIZE_HASH = 3905245786L
        private val getMaxTextureSizeBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_max_texture_size", GET_MAX_TEXTURE_SIZE_HASH)
        }

        private const val SET_SUPERSAMPLING_ENABLED_HASH = 2586408642L
        private val setSupersamplingEnabledBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_supersampling_enabled", SET_SUPERSAMPLING_ENABLED_HASH)
        }

        private const val IS_SUPERSAMPLING_ENABLED_HASH = 36873697L
        private val isSupersamplingEnabledBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "is_supersampling_enabled", IS_SUPERSAMPLING_ENABLED_HASH)
        }

        private const val SET_SUPERSAMPLING_FACTOR_HASH = 373806689L
        private val setSupersamplingFactorBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_supersampling_factor", SET_SUPERSAMPLING_FACTOR_HASH)
        }

        private const val GET_SUPERSAMPLING_FACTOR_HASH = 1740695150L
        private val getSupersamplingFactorBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_supersampling_factor", GET_SUPERSAMPLING_FACTOR_HASH)
        }

        private const val SET_USE_DENOISER_HASH = 2586408642L
        private val setUseDenoiserBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_use_denoiser", SET_USE_DENOISER_HASH)
        }

        private const val IS_USING_DENOISER_HASH = 36873697L
        private val isUsingDenoiserBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "is_using_denoiser", IS_USING_DENOISER_HASH)
        }

        private const val SET_DENOISER_STRENGTH_HASH = 373806689L
        private val setDenoiserStrengthBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_denoiser_strength", SET_DENOISER_STRENGTH_HASH)
        }

        private const val GET_DENOISER_STRENGTH_HASH = 1740695150L
        private val getDenoiserStrengthBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_denoiser_strength", GET_DENOISER_STRENGTH_HASH)
        }

        private const val SET_DENOISER_RANGE_HASH = 1286410249L
        private val setDenoiserRangeBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_denoiser_range", SET_DENOISER_RANGE_HASH)
        }

        private const val GET_DENOISER_RANGE_HASH = 3905245786L
        private val getDenoiserRangeBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_denoiser_range", GET_DENOISER_RANGE_HASH)
        }

        private const val SET_INTERIOR_HASH = 2586408642L
        private val setInteriorBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_interior", SET_INTERIOR_HASH)
        }

        private const val IS_INTERIOR_HASH = 36873697L
        private val isInteriorBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "is_interior", IS_INTERIOR_HASH)
        }

        private const val SET_DIRECTIONAL_HASH = 2586408642L
        private val setDirectionalBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_directional", SET_DIRECTIONAL_HASH)
        }

        private const val IS_DIRECTIONAL_HASH = 36873697L
        private val isDirectionalBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "is_directional", IS_DIRECTIONAL_HASH)
        }

        private const val SET_SHADOWMASK_MODE_HASH = 3451066572L
        private val setShadowmaskModeBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_shadowmask_mode", SET_SHADOWMASK_MODE_HASH)
        }

        private const val GET_SHADOWMASK_MODE_HASH = 785478560L
        private val getShadowmaskModeBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_shadowmask_mode", GET_SHADOWMASK_MODE_HASH)
        }

        private const val SET_USE_TEXTURE_FOR_BOUNCES_HASH = 2586408642L
        private val setUseTextureForBouncesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_use_texture_for_bounces", SET_USE_TEXTURE_FOR_BOUNCES_HASH)
        }

        private const val IS_USING_TEXTURE_FOR_BOUNCES_HASH = 36873697L
        private val isUsingTextureForBouncesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "is_using_texture_for_bounces", IS_USING_TEXTURE_FOR_BOUNCES_HASH)
        }

        private const val SET_CAMERA_ATTRIBUTES_HASH = 2817810567L
        private val setCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "set_camera_attributes", SET_CAMERA_ATTRIBUTES_HASH)
        }

        private const val GET_CAMERA_ATTRIBUTES_HASH = 3921283215L
        private val getCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGI", "get_camera_attributes", GET_CAMERA_ATTRIBUTES_HASH)
        }
    }
}
