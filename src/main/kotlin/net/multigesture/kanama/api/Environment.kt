package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * Resource for environment nodes (like `WorldEnvironment`) that define multiple rendering options.
 *
 * Generated from Godot docs: Environment
 */
class Environment(handle: MemorySegment) : Resource(handle) {
    var backgroundMode: Long
        @JvmName("backgroundModeProperty")
        get() = getBackground()
        @JvmName("setBackgroundModeProperty")
        set(value) = setBackground(value)

    var backgroundColor: Color
        @JvmName("backgroundColorProperty")
        get() = getBgColor()
        @JvmName("setBackgroundColorProperty")
        set(value) = setBgColor(value)

    var backgroundEnergyMultiplier: Double
        @JvmName("backgroundEnergyMultiplierProperty")
        get() = getBgEnergyMultiplier()
        @JvmName("setBackgroundEnergyMultiplierProperty")
        set(value) = setBgEnergyMultiplier(value)

    var backgroundIntensity: Double
        @JvmName("backgroundIntensityProperty")
        get() = getBgIntensity()
        @JvmName("setBackgroundIntensityProperty")
        set(value) = setBgIntensity(value)

    var backgroundCanvasMaxLayer: Int
        @JvmName("backgroundCanvasMaxLayerProperty")
        get() = getCanvasMaxLayer()
        @JvmName("setBackgroundCanvasMaxLayerProperty")
        set(value) = setCanvasMaxLayer(value)

    var backgroundCameraFeedId: Int
        @JvmName("backgroundCameraFeedIdProperty")
        get() = getCameraFeedId()
        @JvmName("setBackgroundCameraFeedIdProperty")
        set(value) = setCameraFeedId(value)

    var sky: Sky?
        @JvmName("skyProperty")
        get() = getSky()
        @JvmName("setSkyProperty")
        set(value) = setSky(value)

    var skyCustomFov: Double
        @JvmName("skyCustomFovProperty")
        get() = getSkyCustomFov()
        @JvmName("setSkyCustomFovProperty")
        set(value) = setSkyCustomFov(value)

    var skyRotation: Vector3
        @JvmName("skyRotationProperty")
        get() = getSkyRotation()
        @JvmName("setSkyRotationProperty")
        set(value) = setSkyRotation(value)

    var ambientLightSource: Long
        @JvmName("ambientLightSourceProperty")
        get() = getAmbientSource()
        @JvmName("setAmbientLightSourceProperty")
        set(value) = setAmbientSource(value)

    var ambientLightColor: Color
        @JvmName("ambientLightColorProperty")
        get() = getAmbientLightColor()
        @JvmName("setAmbientLightColorProperty")
        set(value) = setAmbientLightColor(value)

    var ambientLightSkyContribution: Double
        @JvmName("ambientLightSkyContributionProperty")
        get() = getAmbientLightSkyContribution()
        @JvmName("setAmbientLightSkyContributionProperty")
        set(value) = setAmbientLightSkyContribution(value)

    var ambientLightEnergy: Double
        @JvmName("ambientLightEnergyProperty")
        get() = getAmbientLightEnergy()
        @JvmName("setAmbientLightEnergyProperty")
        set(value) = setAmbientLightEnergy(value)

    var reflectedLightSource: Long
        @JvmName("reflectedLightSourceProperty")
        get() = getReflectionSource()
        @JvmName("setReflectedLightSourceProperty")
        set(value) = setReflectionSource(value)

    var tonemapMode: Long
        @JvmName("tonemapModeProperty")
        get() = getTonemapper()
        @JvmName("setTonemapModeProperty")
        set(value) = setTonemapper(value)

    var tonemapExposure: Double
        @JvmName("tonemapExposureProperty")
        get() = getTonemapExposure()
        @JvmName("setTonemapExposureProperty")
        set(value) = setTonemapExposure(value)

    var tonemapWhite: Double
        @JvmName("tonemapWhiteProperty")
        get() = getTonemapWhite()
        @JvmName("setTonemapWhiteProperty")
        set(value) = setTonemapWhite(value)

    var tonemapAgxWhite: Double
        @JvmName("tonemapAgxWhiteProperty")
        get() = getTonemapAgxWhite()
        @JvmName("setTonemapAgxWhiteProperty")
        set(value) = setTonemapAgxWhite(value)

    var tonemapAgxContrast: Double
        @JvmName("tonemapAgxContrastProperty")
        get() = getTonemapAgxContrast()
        @JvmName("setTonemapAgxContrastProperty")
        set(value) = setTonemapAgxContrast(value)

    var ssrEnabled: Boolean
        @JvmName("ssrEnabledProperty")
        get() = isSsrEnabled()
        @JvmName("setSsrEnabledProperty")
        set(value) = setSsrEnabled(value)

    var ssrMaxSteps: Int
        @JvmName("ssrMaxStepsProperty")
        get() = getSsrMaxSteps()
        @JvmName("setSsrMaxStepsProperty")
        set(value) = setSsrMaxSteps(value)

    var ssrFadeIn: Double
        @JvmName("ssrFadeInProperty")
        get() = getSsrFadeIn()
        @JvmName("setSsrFadeInProperty")
        set(value) = setSsrFadeIn(value)

    var ssrFadeOut: Double
        @JvmName("ssrFadeOutProperty")
        get() = getSsrFadeOut()
        @JvmName("setSsrFadeOutProperty")
        set(value) = setSsrFadeOut(value)

    var ssrDepthTolerance: Double
        @JvmName("ssrDepthToleranceProperty")
        get() = getSsrDepthTolerance()
        @JvmName("setSsrDepthToleranceProperty")
        set(value) = setSsrDepthTolerance(value)

    var ssaoEnabled: Boolean
        @JvmName("ssaoEnabledProperty")
        get() = isSsaoEnabled()
        @JvmName("setSsaoEnabledProperty")
        set(value) = setSsaoEnabled(value)

    var ssaoRadius: Double
        @JvmName("ssaoRadiusProperty")
        get() = getSsaoRadius()
        @JvmName("setSsaoRadiusProperty")
        set(value) = setSsaoRadius(value)

    var ssaoIntensity: Double
        @JvmName("ssaoIntensityProperty")
        get() = getSsaoIntensity()
        @JvmName("setSsaoIntensityProperty")
        set(value) = setSsaoIntensity(value)

    var ssaoPower: Double
        @JvmName("ssaoPowerProperty")
        get() = getSsaoPower()
        @JvmName("setSsaoPowerProperty")
        set(value) = setSsaoPower(value)

    var ssaoDetail: Double
        @JvmName("ssaoDetailProperty")
        get() = getSsaoDetail()
        @JvmName("setSsaoDetailProperty")
        set(value) = setSsaoDetail(value)

    var ssaoHorizon: Double
        @JvmName("ssaoHorizonProperty")
        get() = getSsaoHorizon()
        @JvmName("setSsaoHorizonProperty")
        set(value) = setSsaoHorizon(value)

    var ssaoSharpness: Double
        @JvmName("ssaoSharpnessProperty")
        get() = getSsaoSharpness()
        @JvmName("setSsaoSharpnessProperty")
        set(value) = setSsaoSharpness(value)

    var ssaoLightAffect: Double
        @JvmName("ssaoLightAffectProperty")
        get() = getSsaoDirectLightAffect()
        @JvmName("setSsaoLightAffectProperty")
        set(value) = setSsaoDirectLightAffect(value)

    var ssaoAoChannelAffect: Double
        @JvmName("ssaoAoChannelAffectProperty")
        get() = getSsaoAoChannelAffect()
        @JvmName("setSsaoAoChannelAffectProperty")
        set(value) = setSsaoAoChannelAffect(value)

    var ssilEnabled: Boolean
        @JvmName("ssilEnabledProperty")
        get() = isSsilEnabled()
        @JvmName("setSsilEnabledProperty")
        set(value) = setSsilEnabled(value)

    var ssilRadius: Double
        @JvmName("ssilRadiusProperty")
        get() = getSsilRadius()
        @JvmName("setSsilRadiusProperty")
        set(value) = setSsilRadius(value)

    var ssilIntensity: Double
        @JvmName("ssilIntensityProperty")
        get() = getSsilIntensity()
        @JvmName("setSsilIntensityProperty")
        set(value) = setSsilIntensity(value)

    var ssilSharpness: Double
        @JvmName("ssilSharpnessProperty")
        get() = getSsilSharpness()
        @JvmName("setSsilSharpnessProperty")
        set(value) = setSsilSharpness(value)

    var ssilNormalRejection: Double
        @JvmName("ssilNormalRejectionProperty")
        get() = getSsilNormalRejection()
        @JvmName("setSsilNormalRejectionProperty")
        set(value) = setSsilNormalRejection(value)

    var sdfgiEnabled: Boolean
        @JvmName("sdfgiEnabledProperty")
        get() = isSdfgiEnabled()
        @JvmName("setSdfgiEnabledProperty")
        set(value) = setSdfgiEnabled(value)

    var sdfgiUseOcclusion: Boolean
        @JvmName("sdfgiUseOcclusionProperty")
        get() = isSdfgiUsingOcclusion()
        @JvmName("setSdfgiUseOcclusionProperty")
        set(value) = setSdfgiUseOcclusion(value)

    var sdfgiReadSkyLight: Boolean
        @JvmName("sdfgiReadSkyLightProperty")
        get() = isSdfgiReadingSkyLight()
        @JvmName("setSdfgiReadSkyLightProperty")
        set(value) = setSdfgiReadSkyLight(value)

    var sdfgiBounceFeedback: Double
        @JvmName("sdfgiBounceFeedbackProperty")
        get() = getSdfgiBounceFeedback()
        @JvmName("setSdfgiBounceFeedbackProperty")
        set(value) = setSdfgiBounceFeedback(value)

    var sdfgiCascades: Int
        @JvmName("sdfgiCascadesProperty")
        get() = getSdfgiCascades()
        @JvmName("setSdfgiCascadesProperty")
        set(value) = setSdfgiCascades(value)

    var sdfgiMinCellSize: Double
        @JvmName("sdfgiMinCellSizeProperty")
        get() = getSdfgiMinCellSize()
        @JvmName("setSdfgiMinCellSizeProperty")
        set(value) = setSdfgiMinCellSize(value)

    var sdfgiCascade0Distance: Double
        @JvmName("sdfgiCascade0DistanceProperty")
        get() = getSdfgiCascade0Distance()
        @JvmName("setSdfgiCascade0DistanceProperty")
        set(value) = setSdfgiCascade0Distance(value)

    var sdfgiMaxDistance: Double
        @JvmName("sdfgiMaxDistanceProperty")
        get() = getSdfgiMaxDistance()
        @JvmName("setSdfgiMaxDistanceProperty")
        set(value) = setSdfgiMaxDistance(value)

    var sdfgiYScale: Long
        @JvmName("sdfgiYScaleProperty")
        get() = getSdfgiYScale()
        @JvmName("setSdfgiYScaleProperty")
        set(value) = setSdfgiYScale(value)

    var sdfgiEnergy: Double
        @JvmName("sdfgiEnergyProperty")
        get() = getSdfgiEnergy()
        @JvmName("setSdfgiEnergyProperty")
        set(value) = setSdfgiEnergy(value)

    var sdfgiNormalBias: Double
        @JvmName("sdfgiNormalBiasProperty")
        get() = getSdfgiNormalBias()
        @JvmName("setSdfgiNormalBiasProperty")
        set(value) = setSdfgiNormalBias(value)

    var sdfgiProbeBias: Double
        @JvmName("sdfgiProbeBiasProperty")
        get() = getSdfgiProbeBias()
        @JvmName("setSdfgiProbeBiasProperty")
        set(value) = setSdfgiProbeBias(value)

    var glowEnabled: Boolean
        @JvmName("glowEnabledProperty")
        get() = isGlowEnabled()
        @JvmName("setGlowEnabledProperty")
        set(value) = setGlowEnabled(value)

    var glowNormalized: Boolean
        @JvmName("glowNormalizedProperty")
        get() = isGlowNormalized()
        @JvmName("setGlowNormalizedProperty")
        set(value) = setGlowNormalized(value)

    var glowIntensity: Double
        @JvmName("glowIntensityProperty")
        get() = getGlowIntensity()
        @JvmName("setGlowIntensityProperty")
        set(value) = setGlowIntensity(value)

    var glowStrength: Double
        @JvmName("glowStrengthProperty")
        get() = getGlowStrength()
        @JvmName("setGlowStrengthProperty")
        set(value) = setGlowStrength(value)

    var glowMix: Double
        @JvmName("glowMixProperty")
        get() = getGlowMix()
        @JvmName("setGlowMixProperty")
        set(value) = setGlowMix(value)

    var glowBloom: Double
        @JvmName("glowBloomProperty")
        get() = getGlowBloom()
        @JvmName("setGlowBloomProperty")
        set(value) = setGlowBloom(value)

    var glowBlendMode: Long
        @JvmName("glowBlendModeProperty")
        get() = getGlowBlendMode()
        @JvmName("setGlowBlendModeProperty")
        set(value) = setGlowBlendMode(value)

    var glowHdrThreshold: Double
        @JvmName("glowHdrThresholdProperty")
        get() = getGlowHdrBleedThreshold()
        @JvmName("setGlowHdrThresholdProperty")
        set(value) = setGlowHdrBleedThreshold(value)

    var glowHdrScale: Double
        @JvmName("glowHdrScaleProperty")
        get() = getGlowHdrBleedScale()
        @JvmName("setGlowHdrScaleProperty")
        set(value) = setGlowHdrBleedScale(value)

    var glowHdrLuminanceCap: Double
        @JvmName("glowHdrLuminanceCapProperty")
        get() = getGlowHdrLuminanceCap()
        @JvmName("setGlowHdrLuminanceCapProperty")
        set(value) = setGlowHdrLuminanceCap(value)

    var glowMapStrength: Double
        @JvmName("glowMapStrengthProperty")
        get() = getGlowMapStrength()
        @JvmName("setGlowMapStrengthProperty")
        set(value) = setGlowMapStrength(value)

    var glowMap: Texture?
        @JvmName("glowMapProperty")
        get() = getGlowMap()
        @JvmName("setGlowMapProperty")
        set(value) = setGlowMap(value)

    var fogEnabled: Boolean
        @JvmName("fogEnabledProperty")
        get() = isFogEnabled()
        @JvmName("setFogEnabledProperty")
        set(value) = setFogEnabled(value)

    var fogMode: Long
        @JvmName("fogModeProperty")
        get() = getFogMode()
        @JvmName("setFogModeProperty")
        set(value) = setFogMode(value)

    var fogLightColor: Color
        @JvmName("fogLightColorProperty")
        get() = getFogLightColor()
        @JvmName("setFogLightColorProperty")
        set(value) = setFogLightColor(value)

    var fogLightEnergy: Double
        @JvmName("fogLightEnergyProperty")
        get() = getFogLightEnergy()
        @JvmName("setFogLightEnergyProperty")
        set(value) = setFogLightEnergy(value)

    var fogSunScatter: Double
        @JvmName("fogSunScatterProperty")
        get() = getFogSunScatter()
        @JvmName("setFogSunScatterProperty")
        set(value) = setFogSunScatter(value)

    var fogDensity: Double
        @JvmName("fogDensityProperty")
        get() = getFogDensity()
        @JvmName("setFogDensityProperty")
        set(value) = setFogDensity(value)

    var fogAerialPerspective: Double
        @JvmName("fogAerialPerspectiveProperty")
        get() = getFogAerialPerspective()
        @JvmName("setFogAerialPerspectiveProperty")
        set(value) = setFogAerialPerspective(value)

    var fogSkyAffect: Double
        @JvmName("fogSkyAffectProperty")
        get() = getFogSkyAffect()
        @JvmName("setFogSkyAffectProperty")
        set(value) = setFogSkyAffect(value)

    var fogHeight: Double
        @JvmName("fogHeightProperty")
        get() = getFogHeight()
        @JvmName("setFogHeightProperty")
        set(value) = setFogHeight(value)

    var fogHeightDensity: Double
        @JvmName("fogHeightDensityProperty")
        get() = getFogHeightDensity()
        @JvmName("setFogHeightDensityProperty")
        set(value) = setFogHeightDensity(value)

    var fogDepthCurve: Double
        @JvmName("fogDepthCurveProperty")
        get() = getFogDepthCurve()
        @JvmName("setFogDepthCurveProperty")
        set(value) = setFogDepthCurve(value)

    var fogDepthBegin: Double
        @JvmName("fogDepthBeginProperty")
        get() = getFogDepthBegin()
        @JvmName("setFogDepthBeginProperty")
        set(value) = setFogDepthBegin(value)

    var fogDepthEnd: Double
        @JvmName("fogDepthEndProperty")
        get() = getFogDepthEnd()
        @JvmName("setFogDepthEndProperty")
        set(value) = setFogDepthEnd(value)

    var volumetricFogEnabled: Boolean
        @JvmName("volumetricFogEnabledProperty")
        get() = isVolumetricFogEnabled()
        @JvmName("setVolumetricFogEnabledProperty")
        set(value) = setVolumetricFogEnabled(value)

    var volumetricFogDensity: Double
        @JvmName("volumetricFogDensityProperty")
        get() = getVolumetricFogDensity()
        @JvmName("setVolumetricFogDensityProperty")
        set(value) = setVolumetricFogDensity(value)

    var volumetricFogAlbedo: Color
        @JvmName("volumetricFogAlbedoProperty")
        get() = getVolumetricFogAlbedo()
        @JvmName("setVolumetricFogAlbedoProperty")
        set(value) = setVolumetricFogAlbedo(value)

    var volumetricFogEmission: Color
        @JvmName("volumetricFogEmissionProperty")
        get() = getVolumetricFogEmission()
        @JvmName("setVolumetricFogEmissionProperty")
        set(value) = setVolumetricFogEmission(value)

    var volumetricFogEmissionEnergy: Double
        @JvmName("volumetricFogEmissionEnergyProperty")
        get() = getVolumetricFogEmissionEnergy()
        @JvmName("setVolumetricFogEmissionEnergyProperty")
        set(value) = setVolumetricFogEmissionEnergy(value)

    var volumetricFogGiInject: Double
        @JvmName("volumetricFogGiInjectProperty")
        get() = getVolumetricFogGiInject()
        @JvmName("setVolumetricFogGiInjectProperty")
        set(value) = setVolumetricFogGiInject(value)

    var volumetricFogAnisotropy: Double
        @JvmName("volumetricFogAnisotropyProperty")
        get() = getVolumetricFogAnisotropy()
        @JvmName("setVolumetricFogAnisotropyProperty")
        set(value) = setVolumetricFogAnisotropy(value)

    var volumetricFogLength: Double
        @JvmName("volumetricFogLengthProperty")
        get() = getVolumetricFogLength()
        @JvmName("setVolumetricFogLengthProperty")
        set(value) = setVolumetricFogLength(value)

    var volumetricFogDetailSpread: Double
        @JvmName("volumetricFogDetailSpreadProperty")
        get() = getVolumetricFogDetailSpread()
        @JvmName("setVolumetricFogDetailSpreadProperty")
        set(value) = setVolumetricFogDetailSpread(value)

    var volumetricFogAmbientInject: Double
        @JvmName("volumetricFogAmbientInjectProperty")
        get() = getVolumetricFogAmbientInject()
        @JvmName("setVolumetricFogAmbientInjectProperty")
        set(value) = setVolumetricFogAmbientInject(value)

    var volumetricFogSkyAffect: Double
        @JvmName("volumetricFogSkyAffectProperty")
        get() = getVolumetricFogSkyAffect()
        @JvmName("setVolumetricFogSkyAffectProperty")
        set(value) = setVolumetricFogSkyAffect(value)

    var volumetricFogTemporalReprojectionEnabled: Boolean
        @JvmName("volumetricFogTemporalReprojectionEnabledProperty")
        get() = isVolumetricFogTemporalReprojectionEnabled()
        @JvmName("setVolumetricFogTemporalReprojectionEnabledProperty")
        set(value) = setVolumetricFogTemporalReprojectionEnabled(value)

    var volumetricFogTemporalReprojectionAmount: Double
        @JvmName("volumetricFogTemporalReprojectionAmountProperty")
        get() = getVolumetricFogTemporalReprojectionAmount()
        @JvmName("setVolumetricFogTemporalReprojectionAmountProperty")
        set(value) = setVolumetricFogTemporalReprojectionAmount(value)

    var adjustmentEnabled: Boolean
        @JvmName("adjustmentEnabledProperty")
        get() = isAdjustmentEnabled()
        @JvmName("setAdjustmentEnabledProperty")
        set(value) = setAdjustmentEnabled(value)

    var adjustmentBrightness: Double
        @JvmName("adjustmentBrightnessProperty")
        get() = getAdjustmentBrightness()
        @JvmName("setAdjustmentBrightnessProperty")
        set(value) = setAdjustmentBrightness(value)

    var adjustmentContrast: Double
        @JvmName("adjustmentContrastProperty")
        get() = getAdjustmentContrast()
        @JvmName("setAdjustmentContrastProperty")
        set(value) = setAdjustmentContrast(value)

    var adjustmentSaturation: Double
        @JvmName("adjustmentSaturationProperty")
        get() = getAdjustmentSaturation()
        @JvmName("setAdjustmentSaturationProperty")
        set(value) = setAdjustmentSaturation(value)

    var adjustmentColorCorrection: Texture?
        @JvmName("adjustmentColorCorrectionProperty")
        get() = getAdjustmentColorCorrection()
        @JvmName("setAdjustmentColorCorrectionProperty")
        set(value) = setAdjustmentColorCorrection(value)

    fun setBackground(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBackgroundBind, handle, mode)
    }

    fun getBackground(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBackgroundBind, handle)
    }

    fun setSky(sky: Sky?) {
        ObjectCalls.ptrcallWithObjectArgs(setSkyBind, handle, listOf(sky?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getSky(): Sky? {
        return Sky.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkyBind, handle))
    }

    fun setSkyCustomFov(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSkyCustomFovBind, handle, scale)
    }

    fun getSkyCustomFov(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSkyCustomFovBind, handle)
    }

    fun setSkyRotation(eulerRadians: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSkyRotationBind, handle, eulerRadians)
    }

    fun getSkyRotation(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSkyRotationBind, handle)
    }

    fun setBgColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setBgColorBind, handle, color)
    }

    fun getBgColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBgColorBind, handle)
    }

    fun setBgEnergyMultiplier(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBgEnergyMultiplierBind, handle, energy)
    }

    fun getBgEnergyMultiplier(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBgEnergyMultiplierBind, handle)
    }

    fun setBgIntensity(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBgIntensityBind, handle, energy)
    }

    fun getBgIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBgIntensityBind, handle)
    }

    fun setCanvasMaxLayer(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(setCanvasMaxLayerBind, handle, layer)
    }

    fun getCanvasMaxLayer(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCanvasMaxLayerBind, handle)
    }

    fun setCameraFeedId(id: Int) {
        ObjectCalls.ptrcallWithIntArg(setCameraFeedIdBind, handle, id)
    }

    fun getCameraFeedId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCameraFeedIdBind, handle)
    }

    fun setAmbientLightColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setAmbientLightColorBind, handle, color)
    }

    fun getAmbientLightColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getAmbientLightColorBind, handle)
    }

    fun setAmbientSource(source: Long) {
        ObjectCalls.ptrcallWithLongArg(setAmbientSourceBind, handle, source)
    }

    fun getAmbientSource(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAmbientSourceBind, handle)
    }

    fun setAmbientLightEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAmbientLightEnergyBind, handle, energy)
    }

    fun getAmbientLightEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAmbientLightEnergyBind, handle)
    }

    fun setAmbientLightSkyContribution(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAmbientLightSkyContributionBind, handle, ratio)
    }

    fun getAmbientLightSkyContribution(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAmbientLightSkyContributionBind, handle)
    }

    fun setReflectionSource(source: Long) {
        ObjectCalls.ptrcallWithLongArg(setReflectionSourceBind, handle, source)
    }

    fun getReflectionSource(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getReflectionSourceBind, handle)
    }

    fun setTonemapper(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTonemapperBind, handle, mode)
    }

    fun getTonemapper(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTonemapperBind, handle)
    }

    fun setTonemapExposure(exposure: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTonemapExposureBind, handle, exposure)
    }

    fun getTonemapExposure(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTonemapExposureBind, handle)
    }

    fun setTonemapWhite(white: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTonemapWhiteBind, handle, white)
    }

    fun getTonemapWhite(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTonemapWhiteBind, handle)
    }

    fun setTonemapAgxWhite(white: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTonemapAgxWhiteBind, handle, white)
    }

    fun getTonemapAgxWhite(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTonemapAgxWhiteBind, handle)
    }

    fun setTonemapAgxContrast(contrast: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTonemapAgxContrastBind, handle, contrast)
    }

    fun getTonemapAgxContrast(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTonemapAgxContrastBind, handle)
    }

    fun setSsrEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSsrEnabledBind, handle, enabled)
    }

    fun isSsrEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSsrEnabledBind, handle)
    }

    fun setSsrMaxSteps(maxSteps: Int) {
        ObjectCalls.ptrcallWithIntArg(setSsrMaxStepsBind, handle, maxSteps)
    }

    fun getSsrMaxSteps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSsrMaxStepsBind, handle)
    }

    fun setSsrFadeIn(fadeIn: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsrFadeInBind, handle, fadeIn)
    }

    fun getSsrFadeIn(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsrFadeInBind, handle)
    }

    fun setSsrFadeOut(fadeOut: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsrFadeOutBind, handle, fadeOut)
    }

    fun getSsrFadeOut(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsrFadeOutBind, handle)
    }

    fun setSsrDepthTolerance(depthTolerance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsrDepthToleranceBind, handle, depthTolerance)
    }

    fun getSsrDepthTolerance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsrDepthToleranceBind, handle)
    }

    fun setSsaoEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSsaoEnabledBind, handle, enabled)
    }

    fun isSsaoEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSsaoEnabledBind, handle)
    }

    fun setSsaoRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoRadiusBind, handle, radius)
    }

    fun getSsaoRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoRadiusBind, handle)
    }

    fun setSsaoIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoIntensityBind, handle, intensity)
    }

    fun getSsaoIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoIntensityBind, handle)
    }

    fun setSsaoPower(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoPowerBind, handle, power)
    }

    fun getSsaoPower(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoPowerBind, handle)
    }

    fun setSsaoDetail(detail: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoDetailBind, handle, detail)
    }

    fun getSsaoDetail(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoDetailBind, handle)
    }

    fun setSsaoHorizon(horizon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoHorizonBind, handle, horizon)
    }

    fun getSsaoHorizon(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoHorizonBind, handle)
    }

    fun setSsaoSharpness(sharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoSharpnessBind, handle, sharpness)
    }

    fun getSsaoSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoSharpnessBind, handle)
    }

    fun setSsaoDirectLightAffect(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoDirectLightAffectBind, handle, amount)
    }

    fun getSsaoDirectLightAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoDirectLightAffectBind, handle)
    }

    fun setSsaoAoChannelAffect(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoAoChannelAffectBind, handle, amount)
    }

    fun getSsaoAoChannelAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoAoChannelAffectBind, handle)
    }

    fun setSsilEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSsilEnabledBind, handle, enabled)
    }

    fun isSsilEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSsilEnabledBind, handle)
    }

    fun setSsilRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsilRadiusBind, handle, radius)
    }

    fun getSsilRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsilRadiusBind, handle)
    }

    fun setSsilIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsilIntensityBind, handle, intensity)
    }

    fun getSsilIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsilIntensityBind, handle)
    }

    fun setSsilSharpness(sharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsilSharpnessBind, handle, sharpness)
    }

    fun getSsilSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsilSharpnessBind, handle)
    }

    fun setSsilNormalRejection(normalRejection: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsilNormalRejectionBind, handle, normalRejection)
    }

    fun getSsilNormalRejection(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsilNormalRejectionBind, handle)
    }

    fun setSdfgiEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSdfgiEnabledBind, handle, enabled)
    }

    fun isSdfgiEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSdfgiEnabledBind, handle)
    }

    fun setSdfgiCascades(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setSdfgiCascadesBind, handle, amount)
    }

    fun getSdfgiCascades(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSdfgiCascadesBind, handle)
    }

    fun setSdfgiMinCellSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiMinCellSizeBind, handle, size)
    }

    fun getSdfgiMinCellSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiMinCellSizeBind, handle)
    }

    fun setSdfgiMaxDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiMaxDistanceBind, handle, distance)
    }

    fun getSdfgiMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiMaxDistanceBind, handle)
    }

    fun setSdfgiCascade0Distance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiCascade0DistanceBind, handle, distance)
    }

    fun getSdfgiCascade0Distance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiCascade0DistanceBind, handle)
    }

    fun setSdfgiYScale(scale: Long) {
        ObjectCalls.ptrcallWithLongArg(setSdfgiYScaleBind, handle, scale)
    }

    fun getSdfgiYScale(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSdfgiYScaleBind, handle)
    }

    fun setSdfgiUseOcclusion(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSdfgiUseOcclusionBind, handle, enable)
    }

    fun isSdfgiUsingOcclusion(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSdfgiUsingOcclusionBind, handle)
    }

    fun setSdfgiBounceFeedback(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiBounceFeedbackBind, handle, amount)
    }

    fun getSdfgiBounceFeedback(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiBounceFeedbackBind, handle)
    }

    fun setSdfgiReadSkyLight(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSdfgiReadSkyLightBind, handle, enable)
    }

    fun isSdfgiReadingSkyLight(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSdfgiReadingSkyLightBind, handle)
    }

    fun setSdfgiEnergy(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiEnergyBind, handle, amount)
    }

    fun getSdfgiEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiEnergyBind, handle)
    }

    fun setSdfgiNormalBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiNormalBiasBind, handle, bias)
    }

    fun getSdfgiNormalBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiNormalBiasBind, handle)
    }

    fun setSdfgiProbeBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiProbeBiasBind, handle, bias)
    }

    fun getSdfgiProbeBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiProbeBiasBind, handle)
    }

    fun setGlowEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGlowEnabledBind, handle, enabled)
    }

    fun isGlowEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGlowEnabledBind, handle)
    }

    fun setGlowLevel(idx: Int, intensity: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setGlowLevelBind, handle, idx, intensity)
    }

    fun getGlowLevel(idx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getGlowLevelBind, handle, idx)
    }

    fun setGlowNormalized(normalize: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGlowNormalizedBind, handle, normalize)
    }

    fun isGlowNormalized(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGlowNormalizedBind, handle)
    }

    fun setGlowIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowIntensityBind, handle, intensity)
    }

    fun getGlowIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowIntensityBind, handle)
    }

    fun setGlowStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowStrengthBind, handle, strength)
    }

    fun getGlowStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowStrengthBind, handle)
    }

    fun setGlowMix(mix: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowMixBind, handle, mix)
    }

    fun getGlowMix(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowMixBind, handle)
    }

    fun setGlowBloom(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowBloomBind, handle, amount)
    }

    fun getGlowBloom(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowBloomBind, handle)
    }

    fun setGlowBlendMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setGlowBlendModeBind, handle, mode)
    }

    fun getGlowBlendMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGlowBlendModeBind, handle)
    }

    fun setGlowHdrBleedThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowHdrBleedThresholdBind, handle, threshold)
    }

    fun getGlowHdrBleedThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowHdrBleedThresholdBind, handle)
    }

    fun setGlowHdrBleedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowHdrBleedScaleBind, handle, scale)
    }

    fun getGlowHdrBleedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowHdrBleedScaleBind, handle)
    }

    fun setGlowHdrLuminanceCap(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowHdrLuminanceCapBind, handle, amount)
    }

    fun getGlowHdrLuminanceCap(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowHdrLuminanceCapBind, handle)
    }

    fun setGlowMapStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowMapStrengthBind, handle, strength)
    }

    fun getGlowMapStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowMapStrengthBind, handle)
    }

    fun setGlowMap(mode: Texture?) {
        ObjectCalls.ptrcallWithObjectArgs(setGlowMapBind, handle, listOf(mode?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getGlowMap(): Texture? {
        return Texture.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGlowMapBind, handle))
    }

    fun setFogEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFogEnabledBind, handle, enabled)
    }

    fun isFogEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFogEnabledBind, handle)
    }

    fun setFogMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setFogModeBind, handle, mode)
    }

    fun getFogMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFogModeBind, handle)
    }

    fun setFogLightColor(lightColor: Color) {
        ObjectCalls.ptrcallWithColorArg(setFogLightColorBind, handle, lightColor)
    }

    fun getFogLightColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getFogLightColorBind, handle)
    }

    fun setFogLightEnergy(lightEnergy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogLightEnergyBind, handle, lightEnergy)
    }

    fun getFogLightEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogLightEnergyBind, handle)
    }

    fun setFogSunScatter(sunScatter: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogSunScatterBind, handle, sunScatter)
    }

    fun getFogSunScatter(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogSunScatterBind, handle)
    }

    fun setFogDensity(density: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogDensityBind, handle, density)
    }

    fun getFogDensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogDensityBind, handle)
    }

    fun setFogHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogHeightBind, handle, height)
    }

    fun getFogHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogHeightBind, handle)
    }

    fun setFogHeightDensity(heightDensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogHeightDensityBind, handle, heightDensity)
    }

    fun getFogHeightDensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogHeightDensityBind, handle)
    }

    fun setFogAerialPerspective(aerialPerspective: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogAerialPerspectiveBind, handle, aerialPerspective)
    }

    fun getFogAerialPerspective(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogAerialPerspectiveBind, handle)
    }

    fun setFogSkyAffect(skyAffect: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogSkyAffectBind, handle, skyAffect)
    }

    fun getFogSkyAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogSkyAffectBind, handle)
    }

    fun setFogDepthCurve(curve: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogDepthCurveBind, handle, curve)
    }

    fun getFogDepthCurve(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogDepthCurveBind, handle)
    }

    fun setFogDepthBegin(begin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogDepthBeginBind, handle, begin)
    }

    fun getFogDepthBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogDepthBeginBind, handle)
    }

    fun setFogDepthEnd(end: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogDepthEndBind, handle, end)
    }

    fun getFogDepthEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogDepthEndBind, handle)
    }

    fun setVolumetricFogEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVolumetricFogEnabledBind, handle, enabled)
    }

    fun isVolumetricFogEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVolumetricFogEnabledBind, handle)
    }

    fun setVolumetricFogEmission(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setVolumetricFogEmissionBind, handle, color)
    }

    fun getVolumetricFogEmission(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getVolumetricFogEmissionBind, handle)
    }

    fun setVolumetricFogAlbedo(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setVolumetricFogAlbedoBind, handle, color)
    }

    fun getVolumetricFogAlbedo(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getVolumetricFogAlbedoBind, handle)
    }

    fun setVolumetricFogDensity(density: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogDensityBind, handle, density)
    }

    fun getVolumetricFogDensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogDensityBind, handle)
    }

    fun setVolumetricFogEmissionEnergy(begin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogEmissionEnergyBind, handle, begin)
    }

    fun getVolumetricFogEmissionEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogEmissionEnergyBind, handle)
    }

    fun setVolumetricFogAnisotropy(anisotropy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogAnisotropyBind, handle, anisotropy)
    }

    fun getVolumetricFogAnisotropy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogAnisotropyBind, handle)
    }

    fun setVolumetricFogLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogLengthBind, handle, length)
    }

    fun getVolumetricFogLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogLengthBind, handle)
    }

    fun setVolumetricFogDetailSpread(detailSpread: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogDetailSpreadBind, handle, detailSpread)
    }

    fun getVolumetricFogDetailSpread(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogDetailSpreadBind, handle)
    }

    fun setVolumetricFogGiInject(giInject: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogGiInjectBind, handle, giInject)
    }

    fun getVolumetricFogGiInject(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogGiInjectBind, handle)
    }

    fun setVolumetricFogAmbientInject(enabled: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogAmbientInjectBind, handle, enabled)
    }

    fun getVolumetricFogAmbientInject(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogAmbientInjectBind, handle)
    }

    fun setVolumetricFogSkyAffect(skyAffect: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogSkyAffectBind, handle, skyAffect)
    }

    fun getVolumetricFogSkyAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogSkyAffectBind, handle)
    }

    fun setVolumetricFogTemporalReprojectionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVolumetricFogTemporalReprojectionEnabledBind, handle, enabled)
    }

    fun isVolumetricFogTemporalReprojectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVolumetricFogTemporalReprojectionEnabledBind, handle)
    }

    fun setVolumetricFogTemporalReprojectionAmount(temporalReprojectionAmount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogTemporalReprojectionAmountBind, handle, temporalReprojectionAmount)
    }

    fun getVolumetricFogTemporalReprojectionAmount(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogTemporalReprojectionAmountBind, handle)
    }

    fun setAdjustmentEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAdjustmentEnabledBind, handle, enabled)
    }

    fun isAdjustmentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAdjustmentEnabledBind, handle)
    }

    fun setAdjustmentBrightness(brightness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAdjustmentBrightnessBind, handle, brightness)
    }

    fun getAdjustmentBrightness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAdjustmentBrightnessBind, handle)
    }

    fun setAdjustmentContrast(contrast: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAdjustmentContrastBind, handle, contrast)
    }

    fun getAdjustmentContrast(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAdjustmentContrastBind, handle)
    }

    fun setAdjustmentSaturation(saturation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAdjustmentSaturationBind, handle, saturation)
    }

    fun getAdjustmentSaturation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAdjustmentSaturationBind, handle)
    }

    fun setAdjustmentColorCorrection(colorCorrection: Texture?) {
        ObjectCalls.ptrcallWithObjectArgs(setAdjustmentColorCorrectionBind, handle, listOf(colorCorrection?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getAdjustmentColorCorrection(): Texture? {
        return Texture.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAdjustmentColorCorrectionBind, handle))
    }

    companion object {
        const val BG_CLEAR_COLOR: Long = 0L
        const val BG_COLOR: Long = 1L
        const val BG_SKY: Long = 2L
        const val BG_CANVAS: Long = 3L
        const val BG_KEEP: Long = 4L
        const val BG_CAMERA_FEED: Long = 5L
        const val BG_MAX: Long = 6L
        const val AMBIENT_SOURCE_BG: Long = 0L
        const val AMBIENT_SOURCE_DISABLED: Long = 1L
        const val AMBIENT_SOURCE_COLOR: Long = 2L
        const val AMBIENT_SOURCE_SKY: Long = 3L
        const val REFLECTION_SOURCE_BG: Long = 0L
        const val REFLECTION_SOURCE_DISABLED: Long = 1L
        const val REFLECTION_SOURCE_SKY: Long = 2L
        const val TONE_MAPPER_LINEAR: Long = 0L
        const val TONE_MAPPER_REINHARDT: Long = 1L
        const val TONE_MAPPER_FILMIC: Long = 2L
        const val TONE_MAPPER_ACES: Long = 3L
        const val TONE_MAPPER_AGX: Long = 4L
        const val GLOW_BLEND_MODE_ADDITIVE: Long = 0L
        const val GLOW_BLEND_MODE_SCREEN: Long = 1L
        const val GLOW_BLEND_MODE_SOFTLIGHT: Long = 2L
        const val GLOW_BLEND_MODE_REPLACE: Long = 3L
        const val GLOW_BLEND_MODE_MIX: Long = 4L
        const val FOG_MODE_EXPONENTIAL: Long = 0L
        const val FOG_MODE_DEPTH: Long = 1L
        const val SDFGI_Y_SCALE_50_PERCENT: Long = 0L
        const val SDFGI_Y_SCALE_75_PERCENT: Long = 1L
        const val SDFGI_Y_SCALE_100_PERCENT: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Environment? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Environment? =
            if (handle.address() == 0L) null else Environment(handle)

        private const val SET_BACKGROUND_HASH = 4071623990L
        private val setBackgroundBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_background", SET_BACKGROUND_HASH)
        }

        private const val GET_BACKGROUND_HASH = 1843210413L
        private val getBackgroundBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_background", GET_BACKGROUND_HASH)
        }

        private const val SET_SKY_HASH = 3336722921L
        private val setSkyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sky", SET_SKY_HASH)
        }

        private const val GET_SKY_HASH = 1177136966L
        private val getSkyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sky", GET_SKY_HASH)
        }

        private const val SET_SKY_CUSTOM_FOV_HASH = 373806689L
        private val setSkyCustomFovBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sky_custom_fov", SET_SKY_CUSTOM_FOV_HASH)
        }

        private const val GET_SKY_CUSTOM_FOV_HASH = 1740695150L
        private val getSkyCustomFovBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sky_custom_fov", GET_SKY_CUSTOM_FOV_HASH)
        }

        private const val SET_SKY_ROTATION_HASH = 3460891852L
        private val setSkyRotationBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sky_rotation", SET_SKY_ROTATION_HASH)
        }

        private const val GET_SKY_ROTATION_HASH = 3360562783L
        private val getSkyRotationBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sky_rotation", GET_SKY_ROTATION_HASH)
        }

        private const val SET_BG_COLOR_HASH = 2920490490L
        private val setBgColorBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_bg_color", SET_BG_COLOR_HASH)
        }

        private const val GET_BG_COLOR_HASH = 3444240500L
        private val getBgColorBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_bg_color", GET_BG_COLOR_HASH)
        }

        private const val SET_BG_ENERGY_MULTIPLIER_HASH = 373806689L
        private val setBgEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_bg_energy_multiplier", SET_BG_ENERGY_MULTIPLIER_HASH)
        }

        private const val GET_BG_ENERGY_MULTIPLIER_HASH = 1740695150L
        private val getBgEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_bg_energy_multiplier", GET_BG_ENERGY_MULTIPLIER_HASH)
        }

        private const val SET_BG_INTENSITY_HASH = 373806689L
        private val setBgIntensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_bg_intensity", SET_BG_INTENSITY_HASH)
        }

        private const val GET_BG_INTENSITY_HASH = 1740695150L
        private val getBgIntensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_bg_intensity", GET_BG_INTENSITY_HASH)
        }

        private const val SET_CANVAS_MAX_LAYER_HASH = 1286410249L
        private val setCanvasMaxLayerBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_canvas_max_layer", SET_CANVAS_MAX_LAYER_HASH)
        }

        private const val GET_CANVAS_MAX_LAYER_HASH = 3905245786L
        private val getCanvasMaxLayerBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_canvas_max_layer", GET_CANVAS_MAX_LAYER_HASH)
        }

        private const val SET_CAMERA_FEED_ID_HASH = 1286410249L
        private val setCameraFeedIdBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_camera_feed_id", SET_CAMERA_FEED_ID_HASH)
        }

        private const val GET_CAMERA_FEED_ID_HASH = 3905245786L
        private val getCameraFeedIdBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_camera_feed_id", GET_CAMERA_FEED_ID_HASH)
        }

        private const val SET_AMBIENT_LIGHT_COLOR_HASH = 2920490490L
        private val setAmbientLightColorBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ambient_light_color", SET_AMBIENT_LIGHT_COLOR_HASH)
        }

        private const val GET_AMBIENT_LIGHT_COLOR_HASH = 3444240500L
        private val getAmbientLightColorBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ambient_light_color", GET_AMBIENT_LIGHT_COLOR_HASH)
        }

        private const val SET_AMBIENT_SOURCE_HASH = 2607780160L
        private val setAmbientSourceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ambient_source", SET_AMBIENT_SOURCE_HASH)
        }

        private const val GET_AMBIENT_SOURCE_HASH = 67453933L
        private val getAmbientSourceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ambient_source", GET_AMBIENT_SOURCE_HASH)
        }

        private const val SET_AMBIENT_LIGHT_ENERGY_HASH = 373806689L
        private val setAmbientLightEnergyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ambient_light_energy", SET_AMBIENT_LIGHT_ENERGY_HASH)
        }

        private const val GET_AMBIENT_LIGHT_ENERGY_HASH = 1740695150L
        private val getAmbientLightEnergyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ambient_light_energy", GET_AMBIENT_LIGHT_ENERGY_HASH)
        }

        private const val SET_AMBIENT_LIGHT_SKY_CONTRIBUTION_HASH = 373806689L
        private val setAmbientLightSkyContributionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ambient_light_sky_contribution", SET_AMBIENT_LIGHT_SKY_CONTRIBUTION_HASH)
        }

        private const val GET_AMBIENT_LIGHT_SKY_CONTRIBUTION_HASH = 1740695150L
        private val getAmbientLightSkyContributionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ambient_light_sky_contribution", GET_AMBIENT_LIGHT_SKY_CONTRIBUTION_HASH)
        }

        private const val SET_REFLECTION_SOURCE_HASH = 299673197L
        private val setReflectionSourceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_reflection_source", SET_REFLECTION_SOURCE_HASH)
        }

        private const val GET_REFLECTION_SOURCE_HASH = 777700713L
        private val getReflectionSourceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_reflection_source", GET_REFLECTION_SOURCE_HASH)
        }

        private const val SET_TONEMAPPER_HASH = 1509116664L
        private val setTonemapperBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_tonemapper", SET_TONEMAPPER_HASH)
        }

        private const val GET_TONEMAPPER_HASH = 2908408137L
        private val getTonemapperBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_tonemapper", GET_TONEMAPPER_HASH)
        }

        private const val SET_TONEMAP_EXPOSURE_HASH = 373806689L
        private val setTonemapExposureBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_tonemap_exposure", SET_TONEMAP_EXPOSURE_HASH)
        }

        private const val GET_TONEMAP_EXPOSURE_HASH = 1740695150L
        private val getTonemapExposureBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_tonemap_exposure", GET_TONEMAP_EXPOSURE_HASH)
        }

        private const val SET_TONEMAP_WHITE_HASH = 373806689L
        private val setTonemapWhiteBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_tonemap_white", SET_TONEMAP_WHITE_HASH)
        }

        private const val GET_TONEMAP_WHITE_HASH = 1740695150L
        private val getTonemapWhiteBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_tonemap_white", GET_TONEMAP_WHITE_HASH)
        }

        private const val SET_TONEMAP_AGX_WHITE_HASH = 373806689L
        private val setTonemapAgxWhiteBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_tonemap_agx_white", SET_TONEMAP_AGX_WHITE_HASH)
        }

        private const val GET_TONEMAP_AGX_WHITE_HASH = 1740695150L
        private val getTonemapAgxWhiteBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_tonemap_agx_white", GET_TONEMAP_AGX_WHITE_HASH)
        }

        private const val SET_TONEMAP_AGX_CONTRAST_HASH = 373806689L
        private val setTonemapAgxContrastBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_tonemap_agx_contrast", SET_TONEMAP_AGX_CONTRAST_HASH)
        }

        private const val GET_TONEMAP_AGX_CONTRAST_HASH = 1740695150L
        private val getTonemapAgxContrastBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_tonemap_agx_contrast", GET_TONEMAP_AGX_CONTRAST_HASH)
        }

        private const val SET_SSR_ENABLED_HASH = 2586408642L
        private val setSsrEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssr_enabled", SET_SSR_ENABLED_HASH)
        }

        private const val IS_SSR_ENABLED_HASH = 36873697L
        private val isSsrEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_ssr_enabled", IS_SSR_ENABLED_HASH)
        }

        private const val SET_SSR_MAX_STEPS_HASH = 1286410249L
        private val setSsrMaxStepsBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssr_max_steps", SET_SSR_MAX_STEPS_HASH)
        }

        private const val GET_SSR_MAX_STEPS_HASH = 3905245786L
        private val getSsrMaxStepsBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssr_max_steps", GET_SSR_MAX_STEPS_HASH)
        }

        private const val SET_SSR_FADE_IN_HASH = 373806689L
        private val setSsrFadeInBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssr_fade_in", SET_SSR_FADE_IN_HASH)
        }

        private const val GET_SSR_FADE_IN_HASH = 1740695150L
        private val getSsrFadeInBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssr_fade_in", GET_SSR_FADE_IN_HASH)
        }

        private const val SET_SSR_FADE_OUT_HASH = 373806689L
        private val setSsrFadeOutBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssr_fade_out", SET_SSR_FADE_OUT_HASH)
        }

        private const val GET_SSR_FADE_OUT_HASH = 1740695150L
        private val getSsrFadeOutBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssr_fade_out", GET_SSR_FADE_OUT_HASH)
        }

        private const val SET_SSR_DEPTH_TOLERANCE_HASH = 373806689L
        private val setSsrDepthToleranceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssr_depth_tolerance", SET_SSR_DEPTH_TOLERANCE_HASH)
        }

        private const val GET_SSR_DEPTH_TOLERANCE_HASH = 1740695150L
        private val getSsrDepthToleranceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssr_depth_tolerance", GET_SSR_DEPTH_TOLERANCE_HASH)
        }

        private const val SET_SSAO_ENABLED_HASH = 2586408642L
        private val setSsaoEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_enabled", SET_SSAO_ENABLED_HASH)
        }

        private const val IS_SSAO_ENABLED_HASH = 36873697L
        private val isSsaoEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_ssao_enabled", IS_SSAO_ENABLED_HASH)
        }

        private const val SET_SSAO_RADIUS_HASH = 373806689L
        private val setSsaoRadiusBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_radius", SET_SSAO_RADIUS_HASH)
        }

        private const val GET_SSAO_RADIUS_HASH = 1740695150L
        private val getSsaoRadiusBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssao_radius", GET_SSAO_RADIUS_HASH)
        }

        private const val SET_SSAO_INTENSITY_HASH = 373806689L
        private val setSsaoIntensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_intensity", SET_SSAO_INTENSITY_HASH)
        }

        private const val GET_SSAO_INTENSITY_HASH = 1740695150L
        private val getSsaoIntensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssao_intensity", GET_SSAO_INTENSITY_HASH)
        }

        private const val SET_SSAO_POWER_HASH = 373806689L
        private val setSsaoPowerBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_power", SET_SSAO_POWER_HASH)
        }

        private const val GET_SSAO_POWER_HASH = 1740695150L
        private val getSsaoPowerBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssao_power", GET_SSAO_POWER_HASH)
        }

        private const val SET_SSAO_DETAIL_HASH = 373806689L
        private val setSsaoDetailBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_detail", SET_SSAO_DETAIL_HASH)
        }

        private const val GET_SSAO_DETAIL_HASH = 1740695150L
        private val getSsaoDetailBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssao_detail", GET_SSAO_DETAIL_HASH)
        }

        private const val SET_SSAO_HORIZON_HASH = 373806689L
        private val setSsaoHorizonBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_horizon", SET_SSAO_HORIZON_HASH)
        }

        private const val GET_SSAO_HORIZON_HASH = 1740695150L
        private val getSsaoHorizonBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssao_horizon", GET_SSAO_HORIZON_HASH)
        }

        private const val SET_SSAO_SHARPNESS_HASH = 373806689L
        private val setSsaoSharpnessBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_sharpness", SET_SSAO_SHARPNESS_HASH)
        }

        private const val GET_SSAO_SHARPNESS_HASH = 1740695150L
        private val getSsaoSharpnessBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssao_sharpness", GET_SSAO_SHARPNESS_HASH)
        }

        private const val SET_SSAO_DIRECT_LIGHT_AFFECT_HASH = 373806689L
        private val setSsaoDirectLightAffectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_direct_light_affect", SET_SSAO_DIRECT_LIGHT_AFFECT_HASH)
        }

        private const val GET_SSAO_DIRECT_LIGHT_AFFECT_HASH = 1740695150L
        private val getSsaoDirectLightAffectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssao_direct_light_affect", GET_SSAO_DIRECT_LIGHT_AFFECT_HASH)
        }

        private const val SET_SSAO_AO_CHANNEL_AFFECT_HASH = 373806689L
        private val setSsaoAoChannelAffectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssao_ao_channel_affect", SET_SSAO_AO_CHANNEL_AFFECT_HASH)
        }

        private const val GET_SSAO_AO_CHANNEL_AFFECT_HASH = 1740695150L
        private val getSsaoAoChannelAffectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssao_ao_channel_affect", GET_SSAO_AO_CHANNEL_AFFECT_HASH)
        }

        private const val SET_SSIL_ENABLED_HASH = 2586408642L
        private val setSsilEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssil_enabled", SET_SSIL_ENABLED_HASH)
        }

        private const val IS_SSIL_ENABLED_HASH = 36873697L
        private val isSsilEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_ssil_enabled", IS_SSIL_ENABLED_HASH)
        }

        private const val SET_SSIL_RADIUS_HASH = 373806689L
        private val setSsilRadiusBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssil_radius", SET_SSIL_RADIUS_HASH)
        }

        private const val GET_SSIL_RADIUS_HASH = 1740695150L
        private val getSsilRadiusBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssil_radius", GET_SSIL_RADIUS_HASH)
        }

        private const val SET_SSIL_INTENSITY_HASH = 373806689L
        private val setSsilIntensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssil_intensity", SET_SSIL_INTENSITY_HASH)
        }

        private const val GET_SSIL_INTENSITY_HASH = 1740695150L
        private val getSsilIntensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssil_intensity", GET_SSIL_INTENSITY_HASH)
        }

        private const val SET_SSIL_SHARPNESS_HASH = 373806689L
        private val setSsilSharpnessBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssil_sharpness", SET_SSIL_SHARPNESS_HASH)
        }

        private const val GET_SSIL_SHARPNESS_HASH = 1740695150L
        private val getSsilSharpnessBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssil_sharpness", GET_SSIL_SHARPNESS_HASH)
        }

        private const val SET_SSIL_NORMAL_REJECTION_HASH = 373806689L
        private val setSsilNormalRejectionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_ssil_normal_rejection", SET_SSIL_NORMAL_REJECTION_HASH)
        }

        private const val GET_SSIL_NORMAL_REJECTION_HASH = 1740695150L
        private val getSsilNormalRejectionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_ssil_normal_rejection", GET_SSIL_NORMAL_REJECTION_HASH)
        }

        private const val SET_SDFGI_ENABLED_HASH = 2586408642L
        private val setSdfgiEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_enabled", SET_SDFGI_ENABLED_HASH)
        }

        private const val IS_SDFGI_ENABLED_HASH = 36873697L
        private val isSdfgiEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_sdfgi_enabled", IS_SDFGI_ENABLED_HASH)
        }

        private const val SET_SDFGI_CASCADES_HASH = 1286410249L
        private val setSdfgiCascadesBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_cascades", SET_SDFGI_CASCADES_HASH)
        }

        private const val GET_SDFGI_CASCADES_HASH = 3905245786L
        private val getSdfgiCascadesBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_cascades", GET_SDFGI_CASCADES_HASH)
        }

        private const val SET_SDFGI_MIN_CELL_SIZE_HASH = 373806689L
        private val setSdfgiMinCellSizeBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_min_cell_size", SET_SDFGI_MIN_CELL_SIZE_HASH)
        }

        private const val GET_SDFGI_MIN_CELL_SIZE_HASH = 1740695150L
        private val getSdfgiMinCellSizeBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_min_cell_size", GET_SDFGI_MIN_CELL_SIZE_HASH)
        }

        private const val SET_SDFGI_MAX_DISTANCE_HASH = 373806689L
        private val setSdfgiMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_max_distance", SET_SDFGI_MAX_DISTANCE_HASH)
        }

        private const val GET_SDFGI_MAX_DISTANCE_HASH = 1740695150L
        private val getSdfgiMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_max_distance", GET_SDFGI_MAX_DISTANCE_HASH)
        }

        private const val SET_SDFGI_CASCADE0_DISTANCE_HASH = 373806689L
        private val setSdfgiCascade0DistanceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_cascade0_distance", SET_SDFGI_CASCADE0_DISTANCE_HASH)
        }

        private const val GET_SDFGI_CASCADE0_DISTANCE_HASH = 1740695150L
        private val getSdfgiCascade0DistanceBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_cascade0_distance", GET_SDFGI_CASCADE0_DISTANCE_HASH)
        }

        private const val SET_SDFGI_Y_SCALE_HASH = 3608608372L
        private val setSdfgiYScaleBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_y_scale", SET_SDFGI_Y_SCALE_HASH)
        }

        private const val GET_SDFGI_Y_SCALE_HASH = 2568002245L
        private val getSdfgiYScaleBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_y_scale", GET_SDFGI_Y_SCALE_HASH)
        }

        private const val SET_SDFGI_USE_OCCLUSION_HASH = 2586408642L
        private val setSdfgiUseOcclusionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_use_occlusion", SET_SDFGI_USE_OCCLUSION_HASH)
        }

        private const val IS_SDFGI_USING_OCCLUSION_HASH = 36873697L
        private val isSdfgiUsingOcclusionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_sdfgi_using_occlusion", IS_SDFGI_USING_OCCLUSION_HASH)
        }

        private const val SET_SDFGI_BOUNCE_FEEDBACK_HASH = 373806689L
        private val setSdfgiBounceFeedbackBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_bounce_feedback", SET_SDFGI_BOUNCE_FEEDBACK_HASH)
        }

        private const val GET_SDFGI_BOUNCE_FEEDBACK_HASH = 1740695150L
        private val getSdfgiBounceFeedbackBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_bounce_feedback", GET_SDFGI_BOUNCE_FEEDBACK_HASH)
        }

        private const val SET_SDFGI_READ_SKY_LIGHT_HASH = 2586408642L
        private val setSdfgiReadSkyLightBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_read_sky_light", SET_SDFGI_READ_SKY_LIGHT_HASH)
        }

        private const val IS_SDFGI_READING_SKY_LIGHT_HASH = 36873697L
        private val isSdfgiReadingSkyLightBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_sdfgi_reading_sky_light", IS_SDFGI_READING_SKY_LIGHT_HASH)
        }

        private const val SET_SDFGI_ENERGY_HASH = 373806689L
        private val setSdfgiEnergyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_energy", SET_SDFGI_ENERGY_HASH)
        }

        private const val GET_SDFGI_ENERGY_HASH = 1740695150L
        private val getSdfgiEnergyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_energy", GET_SDFGI_ENERGY_HASH)
        }

        private const val SET_SDFGI_NORMAL_BIAS_HASH = 373806689L
        private val setSdfgiNormalBiasBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_normal_bias", SET_SDFGI_NORMAL_BIAS_HASH)
        }

        private const val GET_SDFGI_NORMAL_BIAS_HASH = 1740695150L
        private val getSdfgiNormalBiasBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_normal_bias", GET_SDFGI_NORMAL_BIAS_HASH)
        }

        private const val SET_SDFGI_PROBE_BIAS_HASH = 373806689L
        private val setSdfgiProbeBiasBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_sdfgi_probe_bias", SET_SDFGI_PROBE_BIAS_HASH)
        }

        private const val GET_SDFGI_PROBE_BIAS_HASH = 1740695150L
        private val getSdfgiProbeBiasBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_sdfgi_probe_bias", GET_SDFGI_PROBE_BIAS_HASH)
        }

        private const val SET_GLOW_ENABLED_HASH = 2586408642L
        private val setGlowEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_enabled", SET_GLOW_ENABLED_HASH)
        }

        private const val IS_GLOW_ENABLED_HASH = 36873697L
        private val isGlowEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_glow_enabled", IS_GLOW_ENABLED_HASH)
        }

        private const val SET_GLOW_LEVEL_HASH = 1602489585L
        private val setGlowLevelBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_level", SET_GLOW_LEVEL_HASH)
        }

        private const val GET_GLOW_LEVEL_HASH = 2339986948L
        private val getGlowLevelBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_level", GET_GLOW_LEVEL_HASH)
        }

        private const val SET_GLOW_NORMALIZED_HASH = 2586408642L
        private val setGlowNormalizedBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_normalized", SET_GLOW_NORMALIZED_HASH)
        }

        private const val IS_GLOW_NORMALIZED_HASH = 36873697L
        private val isGlowNormalizedBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_glow_normalized", IS_GLOW_NORMALIZED_HASH)
        }

        private const val SET_GLOW_INTENSITY_HASH = 373806689L
        private val setGlowIntensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_intensity", SET_GLOW_INTENSITY_HASH)
        }

        private const val GET_GLOW_INTENSITY_HASH = 1740695150L
        private val getGlowIntensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_intensity", GET_GLOW_INTENSITY_HASH)
        }

        private const val SET_GLOW_STRENGTH_HASH = 373806689L
        private val setGlowStrengthBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_strength", SET_GLOW_STRENGTH_HASH)
        }

        private const val GET_GLOW_STRENGTH_HASH = 1740695150L
        private val getGlowStrengthBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_strength", GET_GLOW_STRENGTH_HASH)
        }

        private const val SET_GLOW_MIX_HASH = 373806689L
        private val setGlowMixBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_mix", SET_GLOW_MIX_HASH)
        }

        private const val GET_GLOW_MIX_HASH = 1740695150L
        private val getGlowMixBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_mix", GET_GLOW_MIX_HASH)
        }

        private const val SET_GLOW_BLOOM_HASH = 373806689L
        private val setGlowBloomBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_bloom", SET_GLOW_BLOOM_HASH)
        }

        private const val GET_GLOW_BLOOM_HASH = 1740695150L
        private val getGlowBloomBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_bloom", GET_GLOW_BLOOM_HASH)
        }

        private const val SET_GLOW_BLEND_MODE_HASH = 2561587761L
        private val setGlowBlendModeBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_blend_mode", SET_GLOW_BLEND_MODE_HASH)
        }

        private const val GET_GLOW_BLEND_MODE_HASH = 1529667332L
        private val getGlowBlendModeBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_blend_mode", GET_GLOW_BLEND_MODE_HASH)
        }

        private const val SET_GLOW_HDR_BLEED_THRESHOLD_HASH = 373806689L
        private val setGlowHdrBleedThresholdBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_hdr_bleed_threshold", SET_GLOW_HDR_BLEED_THRESHOLD_HASH)
        }

        private const val GET_GLOW_HDR_BLEED_THRESHOLD_HASH = 1740695150L
        private val getGlowHdrBleedThresholdBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_hdr_bleed_threshold", GET_GLOW_HDR_BLEED_THRESHOLD_HASH)
        }

        private const val SET_GLOW_HDR_BLEED_SCALE_HASH = 373806689L
        private val setGlowHdrBleedScaleBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_hdr_bleed_scale", SET_GLOW_HDR_BLEED_SCALE_HASH)
        }

        private const val GET_GLOW_HDR_BLEED_SCALE_HASH = 1740695150L
        private val getGlowHdrBleedScaleBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_hdr_bleed_scale", GET_GLOW_HDR_BLEED_SCALE_HASH)
        }

        private const val SET_GLOW_HDR_LUMINANCE_CAP_HASH = 373806689L
        private val setGlowHdrLuminanceCapBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_hdr_luminance_cap", SET_GLOW_HDR_LUMINANCE_CAP_HASH)
        }

        private const val GET_GLOW_HDR_LUMINANCE_CAP_HASH = 1740695150L
        private val getGlowHdrLuminanceCapBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_hdr_luminance_cap", GET_GLOW_HDR_LUMINANCE_CAP_HASH)
        }

        private const val SET_GLOW_MAP_STRENGTH_HASH = 373806689L
        private val setGlowMapStrengthBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_map_strength", SET_GLOW_MAP_STRENGTH_HASH)
        }

        private const val GET_GLOW_MAP_STRENGTH_HASH = 1740695150L
        private val getGlowMapStrengthBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_map_strength", GET_GLOW_MAP_STRENGTH_HASH)
        }

        private const val SET_GLOW_MAP_HASH = 1790811099L
        private val setGlowMapBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_glow_map", SET_GLOW_MAP_HASH)
        }

        private const val GET_GLOW_MAP_HASH = 4037048985L
        private val getGlowMapBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_glow_map", GET_GLOW_MAP_HASH)
        }

        private const val SET_FOG_ENABLED_HASH = 2586408642L
        private val setFogEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_enabled", SET_FOG_ENABLED_HASH)
        }

        private const val IS_FOG_ENABLED_HASH = 36873697L
        private val isFogEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_fog_enabled", IS_FOG_ENABLED_HASH)
        }

        private const val SET_FOG_MODE_HASH = 3059806579L
        private val setFogModeBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_mode", SET_FOG_MODE_HASH)
        }

        private const val GET_FOG_MODE_HASH = 2456062483L
        private val getFogModeBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_mode", GET_FOG_MODE_HASH)
        }

        private const val SET_FOG_LIGHT_COLOR_HASH = 2920490490L
        private val setFogLightColorBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_light_color", SET_FOG_LIGHT_COLOR_HASH)
        }

        private const val GET_FOG_LIGHT_COLOR_HASH = 3444240500L
        private val getFogLightColorBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_light_color", GET_FOG_LIGHT_COLOR_HASH)
        }

        private const val SET_FOG_LIGHT_ENERGY_HASH = 373806689L
        private val setFogLightEnergyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_light_energy", SET_FOG_LIGHT_ENERGY_HASH)
        }

        private const val GET_FOG_LIGHT_ENERGY_HASH = 1740695150L
        private val getFogLightEnergyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_light_energy", GET_FOG_LIGHT_ENERGY_HASH)
        }

        private const val SET_FOG_SUN_SCATTER_HASH = 373806689L
        private val setFogSunScatterBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_sun_scatter", SET_FOG_SUN_SCATTER_HASH)
        }

        private const val GET_FOG_SUN_SCATTER_HASH = 1740695150L
        private val getFogSunScatterBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_sun_scatter", GET_FOG_SUN_SCATTER_HASH)
        }

        private const val SET_FOG_DENSITY_HASH = 373806689L
        private val setFogDensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_density", SET_FOG_DENSITY_HASH)
        }

        private const val GET_FOG_DENSITY_HASH = 1740695150L
        private val getFogDensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_density", GET_FOG_DENSITY_HASH)
        }

        private const val SET_FOG_HEIGHT_HASH = 373806689L
        private val setFogHeightBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_height", SET_FOG_HEIGHT_HASH)
        }

        private const val GET_FOG_HEIGHT_HASH = 1740695150L
        private val getFogHeightBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_height", GET_FOG_HEIGHT_HASH)
        }

        private const val SET_FOG_HEIGHT_DENSITY_HASH = 373806689L
        private val setFogHeightDensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_height_density", SET_FOG_HEIGHT_DENSITY_HASH)
        }

        private const val GET_FOG_HEIGHT_DENSITY_HASH = 1740695150L
        private val getFogHeightDensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_height_density", GET_FOG_HEIGHT_DENSITY_HASH)
        }

        private const val SET_FOG_AERIAL_PERSPECTIVE_HASH = 373806689L
        private val setFogAerialPerspectiveBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_aerial_perspective", SET_FOG_AERIAL_PERSPECTIVE_HASH)
        }

        private const val GET_FOG_AERIAL_PERSPECTIVE_HASH = 1740695150L
        private val getFogAerialPerspectiveBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_aerial_perspective", GET_FOG_AERIAL_PERSPECTIVE_HASH)
        }

        private const val SET_FOG_SKY_AFFECT_HASH = 373806689L
        private val setFogSkyAffectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_sky_affect", SET_FOG_SKY_AFFECT_HASH)
        }

        private const val GET_FOG_SKY_AFFECT_HASH = 1740695150L
        private val getFogSkyAffectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_sky_affect", GET_FOG_SKY_AFFECT_HASH)
        }

        private const val SET_FOG_DEPTH_CURVE_HASH = 373806689L
        private val setFogDepthCurveBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_depth_curve", SET_FOG_DEPTH_CURVE_HASH)
        }

        private const val GET_FOG_DEPTH_CURVE_HASH = 1740695150L
        private val getFogDepthCurveBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_depth_curve", GET_FOG_DEPTH_CURVE_HASH)
        }

        private const val SET_FOG_DEPTH_BEGIN_HASH = 373806689L
        private val setFogDepthBeginBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_depth_begin", SET_FOG_DEPTH_BEGIN_HASH)
        }

        private const val GET_FOG_DEPTH_BEGIN_HASH = 1740695150L
        private val getFogDepthBeginBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_depth_begin", GET_FOG_DEPTH_BEGIN_HASH)
        }

        private const val SET_FOG_DEPTH_END_HASH = 373806689L
        private val setFogDepthEndBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_fog_depth_end", SET_FOG_DEPTH_END_HASH)
        }

        private const val GET_FOG_DEPTH_END_HASH = 1740695150L
        private val getFogDepthEndBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_fog_depth_end", GET_FOG_DEPTH_END_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_ENABLED_HASH = 2586408642L
        private val setVolumetricFogEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_enabled", SET_VOLUMETRIC_FOG_ENABLED_HASH)
        }

        private const val IS_VOLUMETRIC_FOG_ENABLED_HASH = 36873697L
        private val isVolumetricFogEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_volumetric_fog_enabled", IS_VOLUMETRIC_FOG_ENABLED_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_EMISSION_HASH = 2920490490L
        private val setVolumetricFogEmissionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_emission", SET_VOLUMETRIC_FOG_EMISSION_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_EMISSION_HASH = 3444240500L
        private val getVolumetricFogEmissionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_emission", GET_VOLUMETRIC_FOG_EMISSION_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_ALBEDO_HASH = 2920490490L
        private val setVolumetricFogAlbedoBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_albedo", SET_VOLUMETRIC_FOG_ALBEDO_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_ALBEDO_HASH = 3444240500L
        private val getVolumetricFogAlbedoBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_albedo", GET_VOLUMETRIC_FOG_ALBEDO_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_DENSITY_HASH = 373806689L
        private val setVolumetricFogDensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_density", SET_VOLUMETRIC_FOG_DENSITY_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_DENSITY_HASH = 1740695150L
        private val getVolumetricFogDensityBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_density", GET_VOLUMETRIC_FOG_DENSITY_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_EMISSION_ENERGY_HASH = 373806689L
        private val setVolumetricFogEmissionEnergyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_emission_energy", SET_VOLUMETRIC_FOG_EMISSION_ENERGY_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_EMISSION_ENERGY_HASH = 1740695150L
        private val getVolumetricFogEmissionEnergyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_emission_energy", GET_VOLUMETRIC_FOG_EMISSION_ENERGY_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_ANISOTROPY_HASH = 373806689L
        private val setVolumetricFogAnisotropyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_anisotropy", SET_VOLUMETRIC_FOG_ANISOTROPY_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_ANISOTROPY_HASH = 1740695150L
        private val getVolumetricFogAnisotropyBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_anisotropy", GET_VOLUMETRIC_FOG_ANISOTROPY_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_LENGTH_HASH = 373806689L
        private val setVolumetricFogLengthBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_length", SET_VOLUMETRIC_FOG_LENGTH_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_LENGTH_HASH = 1740695150L
        private val getVolumetricFogLengthBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_length", GET_VOLUMETRIC_FOG_LENGTH_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_DETAIL_SPREAD_HASH = 373806689L
        private val setVolumetricFogDetailSpreadBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_detail_spread", SET_VOLUMETRIC_FOG_DETAIL_SPREAD_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_DETAIL_SPREAD_HASH = 1740695150L
        private val getVolumetricFogDetailSpreadBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_detail_spread", GET_VOLUMETRIC_FOG_DETAIL_SPREAD_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_GI_INJECT_HASH = 373806689L
        private val setVolumetricFogGiInjectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_gi_inject", SET_VOLUMETRIC_FOG_GI_INJECT_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_GI_INJECT_HASH = 1740695150L
        private val getVolumetricFogGiInjectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_gi_inject", GET_VOLUMETRIC_FOG_GI_INJECT_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_AMBIENT_INJECT_HASH = 373806689L
        private val setVolumetricFogAmbientInjectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_ambient_inject", SET_VOLUMETRIC_FOG_AMBIENT_INJECT_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_AMBIENT_INJECT_HASH = 1740695150L
        private val getVolumetricFogAmbientInjectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_ambient_inject", GET_VOLUMETRIC_FOG_AMBIENT_INJECT_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_SKY_AFFECT_HASH = 373806689L
        private val setVolumetricFogSkyAffectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_sky_affect", SET_VOLUMETRIC_FOG_SKY_AFFECT_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_SKY_AFFECT_HASH = 1740695150L
        private val getVolumetricFogSkyAffectBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_sky_affect", GET_VOLUMETRIC_FOG_SKY_AFFECT_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_TEMPORAL_REPROJECTION_ENABLED_HASH = 2586408642L
        private val setVolumetricFogTemporalReprojectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_temporal_reprojection_enabled", SET_VOLUMETRIC_FOG_TEMPORAL_REPROJECTION_ENABLED_HASH)
        }

        private const val IS_VOLUMETRIC_FOG_TEMPORAL_REPROJECTION_ENABLED_HASH = 36873697L
        private val isVolumetricFogTemporalReprojectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_volumetric_fog_temporal_reprojection_enabled", IS_VOLUMETRIC_FOG_TEMPORAL_REPROJECTION_ENABLED_HASH)
        }

        private const val SET_VOLUMETRIC_FOG_TEMPORAL_REPROJECTION_AMOUNT_HASH = 373806689L
        private val setVolumetricFogTemporalReprojectionAmountBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_volumetric_fog_temporal_reprojection_amount", SET_VOLUMETRIC_FOG_TEMPORAL_REPROJECTION_AMOUNT_HASH)
        }

        private const val GET_VOLUMETRIC_FOG_TEMPORAL_REPROJECTION_AMOUNT_HASH = 1740695150L
        private val getVolumetricFogTemporalReprojectionAmountBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_volumetric_fog_temporal_reprojection_amount", GET_VOLUMETRIC_FOG_TEMPORAL_REPROJECTION_AMOUNT_HASH)
        }

        private const val SET_ADJUSTMENT_ENABLED_HASH = 2586408642L
        private val setAdjustmentEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_adjustment_enabled", SET_ADJUSTMENT_ENABLED_HASH)
        }

        private const val IS_ADJUSTMENT_ENABLED_HASH = 36873697L
        private val isAdjustmentEnabledBind by lazy {
            ObjectCalls.getMethodBind("Environment", "is_adjustment_enabled", IS_ADJUSTMENT_ENABLED_HASH)
        }

        private const val SET_ADJUSTMENT_BRIGHTNESS_HASH = 373806689L
        private val setAdjustmentBrightnessBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_adjustment_brightness", SET_ADJUSTMENT_BRIGHTNESS_HASH)
        }

        private const val GET_ADJUSTMENT_BRIGHTNESS_HASH = 1740695150L
        private val getAdjustmentBrightnessBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_adjustment_brightness", GET_ADJUSTMENT_BRIGHTNESS_HASH)
        }

        private const val SET_ADJUSTMENT_CONTRAST_HASH = 373806689L
        private val setAdjustmentContrastBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_adjustment_contrast", SET_ADJUSTMENT_CONTRAST_HASH)
        }

        private const val GET_ADJUSTMENT_CONTRAST_HASH = 1740695150L
        private val getAdjustmentContrastBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_adjustment_contrast", GET_ADJUSTMENT_CONTRAST_HASH)
        }

        private const val SET_ADJUSTMENT_SATURATION_HASH = 373806689L
        private val setAdjustmentSaturationBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_adjustment_saturation", SET_ADJUSTMENT_SATURATION_HASH)
        }

        private const val GET_ADJUSTMENT_SATURATION_HASH = 1740695150L
        private val getAdjustmentSaturationBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_adjustment_saturation", GET_ADJUSTMENT_SATURATION_HASH)
        }

        private const val SET_ADJUSTMENT_COLOR_CORRECTION_HASH = 1790811099L
        private val setAdjustmentColorCorrectionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "set_adjustment_color_correction", SET_ADJUSTMENT_COLOR_CORRECTION_HASH)
        }

        private const val GET_ADJUSTMENT_COLOR_CORRECTION_HASH = 4037048985L
        private val getAdjustmentColorCorrectionBind by lazy {
            ObjectCalls.getMethodBind("Environment", "get_adjustment_color_correction", GET_ADJUSTMENT_COLOR_CORRECTION_HASH)
        }
    }
}
