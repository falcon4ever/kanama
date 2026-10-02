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
class LightmapGI(handle: GodotHandle) : VisualInstance3D(handle) {
    var quality: LightmapGI.BakeQuality
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

    var shadowmaskMode: LightmapGIData.ShadowmaskMode
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

    var environmentMode: LightmapGI.EnvironmentMode
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

    var generateProbesSubdiv: LightmapGI.GenerateProbes
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
        ObjectCalls.ptrcallWithObjectArgs(setLightDataBind, segment, listOf(data?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getLightData(): LightmapGIData? {
        return LightmapGIData.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLightDataBind, segment))
    }

    fun setBakeQuality(bakeQuality: LightmapGI.BakeQuality) {
        ObjectCalls.ptrcallWithLongArg(setBakeQualityBind, segment, bakeQuality.value)
    }

    fun getBakeQuality(): LightmapGI.BakeQuality {
        return LightmapGI.BakeQuality(ObjectCalls.ptrcallNoArgsRetLong(getBakeQualityBind, segment))
    }

    fun setBounces(bounces: Int) {
        ObjectCalls.ptrcallWithIntArg(setBouncesBind, segment, bounces)
    }

    fun getBounces(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBouncesBind, segment)
    }

    fun setBounceIndirectEnergy(bounceIndirectEnergy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBounceIndirectEnergyBind, segment, bounceIndirectEnergy)
    }

    fun getBounceIndirectEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBounceIndirectEnergyBind, segment)
    }

    fun setGenerateProbes(subdivision: LightmapGI.GenerateProbes) {
        ObjectCalls.ptrcallWithLongArg(setGenerateProbesBind, segment, subdivision.value)
    }

    fun getGenerateProbes(): LightmapGI.GenerateProbes {
        return LightmapGI.GenerateProbes(ObjectCalls.ptrcallNoArgsRetLong(getGenerateProbesBind, segment))
    }

    fun setBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBiasBind, segment, bias)
    }

    fun getBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBiasBind, segment)
    }

    fun setEnvironmentMode(mode: LightmapGI.EnvironmentMode) {
        ObjectCalls.ptrcallWithLongArg(setEnvironmentModeBind, segment, mode.value)
    }

    fun getEnvironmentMode(): LightmapGI.EnvironmentMode {
        return LightmapGI.EnvironmentMode(ObjectCalls.ptrcallNoArgsRetLong(getEnvironmentModeBind, segment))
    }

    fun setEnvironmentCustomSky(sky: Sky?) {
        ObjectCalls.ptrcallWithObjectArgs(setEnvironmentCustomSkyBind, segment, listOf(sky?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getEnvironmentCustomSky(): Sky? {
        return Sky.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEnvironmentCustomSkyBind, segment))
    }

    fun setEnvironmentCustomColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setEnvironmentCustomColorBind, segment, color)
    }

    fun getEnvironmentCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getEnvironmentCustomColorBind, segment)
    }

    fun setEnvironmentCustomEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnvironmentCustomEnergyBind, segment, energy)
    }

    fun getEnvironmentCustomEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnvironmentCustomEnergyBind, segment)
    }

    fun setTexelScale(texelScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTexelScaleBind, segment, texelScale)
    }

    fun getTexelScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTexelScaleBind, segment)
    }

    fun setMaxTextureSize(maxTextureSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxTextureSizeBind, segment, maxTextureSize)
    }

    fun getMaxTextureSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxTextureSizeBind, segment)
    }

    fun setSupersamplingEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSupersamplingEnabledBind, segment, enable)
    }

    fun isSupersamplingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSupersamplingEnabledBind, segment)
    }

    fun setSupersamplingFactor(factor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSupersamplingFactorBind, segment, factor)
    }

    fun getSupersamplingFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSupersamplingFactorBind, segment)
    }

    fun setUseDenoiser(useDenoiser: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseDenoiserBind, segment, useDenoiser)
    }

    fun isUsingDenoiser(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingDenoiserBind, segment)
    }

    fun setDenoiserStrength(denoiserStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDenoiserStrengthBind, segment, denoiserStrength)
    }

    fun getDenoiserStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDenoiserStrengthBind, segment)
    }

    fun setDenoiserRange(denoiserRange: Int) {
        ObjectCalls.ptrcallWithIntArg(setDenoiserRangeBind, segment, denoiserRange)
    }

    fun getDenoiserRange(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDenoiserRangeBind, segment)
    }

    fun setInterior(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInteriorBind, segment, enable)
    }

    fun isInterior(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInteriorBind, segment)
    }

    fun setDirectional(directional: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDirectionalBind, segment, directional)
    }

    fun isDirectional(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDirectionalBind, segment)
    }

    fun setShadowmaskMode(mode: LightmapGIData.ShadowmaskMode) {
        ObjectCalls.ptrcallWithLongArg(setShadowmaskModeBind, segment, mode.value)
    }

    fun getShadowmaskMode(): LightmapGIData.ShadowmaskMode {
        return LightmapGIData.ShadowmaskMode(ObjectCalls.ptrcallNoArgsRetLong(getShadowmaskModeBind, segment))
    }

    fun setUseTextureForBounces(useTextureForBounces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseTextureForBouncesBind, segment, useTextureForBounces)
    }

    fun isUsingTextureForBounces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTextureForBouncesBind, segment)
    }

    fun setCameraAttributes(cameraAttributes: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(setCameraAttributesBind, segment, listOf(cameraAttributes?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCameraAttributes(): CameraAttributes? {
        return CameraAttributes.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCameraAttributesBind, segment))
    }

    value class BakeQuality(val value: Long) {
        companion object {
            val LOW: BakeQuality get() = BakeQuality(0L)
            val MEDIUM: BakeQuality get() = BakeQuality(1L)
            val HIGH: BakeQuality get() = BakeQuality(2L)
            val ULTRA: BakeQuality get() = BakeQuality(3L)
        }
    }

    value class GenerateProbes(val value: Long) {
        companion object {
            val DISABLED: GenerateProbes get() = GenerateProbes(0L)
            val SUBDIV_4: GenerateProbes get() = GenerateProbes(1L)
            val SUBDIV_8: GenerateProbes get() = GenerateProbes(2L)
            val SUBDIV_16: GenerateProbes get() = GenerateProbes(3L)
            val SUBDIV_32: GenerateProbes get() = GenerateProbes(4L)
        }
    }

    value class BakeError(val value: Long) {
        companion object {
            val OK: BakeError get() = BakeError(0L)
            val NO_SCENE_ROOT: BakeError get() = BakeError(1L)
            val FOREIGN_DATA: BakeError get() = BakeError(2L)
            val NO_LIGHTMAPPER: BakeError get() = BakeError(3L)
            val NO_SAVE_PATH: BakeError get() = BakeError(4L)
            val NO_MESHES: BakeError get() = BakeError(5L)
            val MESHES_INVALID: BakeError get() = BakeError(6L)
            val CANT_CREATE_IMAGE: BakeError get() = BakeError(7L)
            val USER_ABORTED: BakeError get() = BakeError(8L)
            val TEXTURE_SIZE_TOO_SMALL: BakeError get() = BakeError(9L)
            val LIGHTMAP_TOO_SMALL: BakeError get() = BakeError(10L)
            val ATLAS_TOO_SMALL: BakeError get() = BakeError(11L)
        }
    }

    value class EnvironmentMode(val value: Long) {
        companion object {
            val DISABLED: EnvironmentMode get() = EnvironmentMode(0L)
            val SCENE: EnvironmentMode get() = EnvironmentMode(1L)
            val CUSTOM_SKY: EnvironmentMode get() = EnvironmentMode(2L)
            val CUSTOM_COLOR: EnvironmentMode get() = EnvironmentMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): LightmapGI? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): LightmapGI? =
            if (handle.address() == 0L) null else LightmapGI(GodotHandle(handle))

        // Instantiate a LightmapGI.
        fun create(): LightmapGI =
            LightmapGI(GodotHandle(MemorySegment.ofAddress(IosGodot.constructObject("LightmapGI"))))

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
