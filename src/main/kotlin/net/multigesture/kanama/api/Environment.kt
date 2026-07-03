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

    /**
     * The background mode.
     *
     * Generated from Godot docs: Environment.set_background
     */
    fun setBackground(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBackgroundBind, handle, mode)
    }

    /**
     * The background mode.
     *
     * Generated from Godot docs: Environment.get_background
     */
    fun getBackground(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBackgroundBind, handle)
    }

    /**
     * The `Sky` resource used for this `Environment`.
     *
     * Generated from Godot docs: Environment.set_sky
     */
    fun setSky(sky: Sky?) {
        ObjectCalls.ptrcallWithObjectArgs(setSkyBind, handle, listOf(sky?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Sky` resource used for this `Environment`.
     *
     * Generated from Godot docs: Environment.get_sky
     */
    fun getSky(): Sky? {
        return Sky.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkyBind, handle))
    }

    /**
     * If set to a value greater than `0.0`, overrides the field of view to use for sky rendering. If
     * set to `0.0`, the same FOV as the current `Camera3D` is used for sky rendering.
     *
     * Generated from Godot docs: Environment.set_sky_custom_fov
     */
    fun setSkyCustomFov(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSkyCustomFovBind, handle, scale)
    }

    /**
     * If set to a value greater than `0.0`, overrides the field of view to use for sky rendering. If
     * set to `0.0`, the same FOV as the current `Camera3D` is used for sky rendering.
     *
     * Generated from Godot docs: Environment.get_sky_custom_fov
     */
    fun getSkyCustomFov(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSkyCustomFovBind, handle)
    }

    /**
     * The rotation to use for sky rendering.
     *
     * Generated from Godot docs: Environment.set_sky_rotation
     */
    fun setSkyRotation(eulerRadians: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSkyRotationBind, handle, eulerRadians)
    }

    /**
     * The rotation to use for sky rendering.
     *
     * Generated from Godot docs: Environment.get_sky_rotation
     */
    fun getSkyRotation(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSkyRotationBind, handle)
    }

    /**
     * The `Color` displayed for clear areas of the scene. Only effective when using the `BG_COLOR`
     * background mode.
     *
     * Generated from Godot docs: Environment.set_bg_color
     */
    fun setBgColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setBgColorBind, handle, color)
    }

    /**
     * The `Color` displayed for clear areas of the scene. Only effective when using the `BG_COLOR`
     * background mode.
     *
     * Generated from Godot docs: Environment.get_bg_color
     */
    fun getBgColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBgColorBind, handle)
    }

    /**
     * Multiplier for background energy. Increase to make background brighter, decrease to make
     * background dimmer.
     *
     * Generated from Godot docs: Environment.set_bg_energy_multiplier
     */
    fun setBgEnergyMultiplier(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBgEnergyMultiplierBind, handle, energy)
    }

    /**
     * Multiplier for background energy. Increase to make background brighter, decrease to make
     * background dimmer.
     *
     * Generated from Godot docs: Environment.get_bg_energy_multiplier
     */
    fun getBgEnergyMultiplier(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBgEnergyMultiplierBind, handle)
    }

    /**
     * Luminance of background measured in nits (candela per square meter). Only used when
     * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is enabled. The default
     * value is roughly equivalent to the sky at midday.
     *
     * Generated from Godot docs: Environment.set_bg_intensity
     */
    fun setBgIntensity(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBgIntensityBind, handle, energy)
    }

    /**
     * Luminance of background measured in nits (candela per square meter). Only used when
     * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is enabled. The default
     * value is roughly equivalent to the sky at midday.
     *
     * Generated from Godot docs: Environment.get_bg_intensity
     */
    fun getBgIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBgIntensityBind, handle)
    }

    /**
     * The maximum layer ID to display. Only effective when using the `BG_CANVAS` background mode.
     *
     * Generated from Godot docs: Environment.set_canvas_max_layer
     */
    fun setCanvasMaxLayer(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(setCanvasMaxLayerBind, handle, layer)
    }

    /**
     * The maximum layer ID to display. Only effective when using the `BG_CANVAS` background mode.
     *
     * Generated from Godot docs: Environment.get_canvas_max_layer
     */
    fun getCanvasMaxLayer(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCanvasMaxLayerBind, handle)
    }

    /**
     * The ID of the camera feed to show in the background.
     *
     * Generated from Godot docs: Environment.set_camera_feed_id
     */
    fun setCameraFeedId(id: Int) {
        ObjectCalls.ptrcallWithIntArg(setCameraFeedIdBind, handle, id)
    }

    /**
     * The ID of the camera feed to show in the background.
     *
     * Generated from Godot docs: Environment.get_camera_feed_id
     */
    fun getCameraFeedId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCameraFeedIdBind, handle)
    }

    /**
     * The ambient light's `Color`. Only effective if `ambient_light_sky_contribution` is lower than
     * `1.0` (exclusive).
     *
     * Generated from Godot docs: Environment.set_ambient_light_color
     */
    fun setAmbientLightColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setAmbientLightColorBind, handle, color)
    }

    /**
     * The ambient light's `Color`. Only effective if `ambient_light_sky_contribution` is lower than
     * `1.0` (exclusive).
     *
     * Generated from Godot docs: Environment.get_ambient_light_color
     */
    fun getAmbientLightColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getAmbientLightColorBind, handle)
    }

    /**
     * The ambient light source to use for rendering materials and global illumination.
     *
     * Generated from Godot docs: Environment.set_ambient_source
     */
    fun setAmbientSource(source: Long) {
        ObjectCalls.ptrcallWithLongArg(setAmbientSourceBind, handle, source)
    }

    /**
     * The ambient light source to use for rendering materials and global illumination.
     *
     * Generated from Godot docs: Environment.get_ambient_source
     */
    fun getAmbientSource(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAmbientSourceBind, handle)
    }

    /**
     * The ambient light's energy. The higher the value, the stronger the light. Only effective if
     * `ambient_light_sky_contribution` is lower than `1.0` (exclusive).
     *
     * Generated from Godot docs: Environment.set_ambient_light_energy
     */
    fun setAmbientLightEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAmbientLightEnergyBind, handle, energy)
    }

    /**
     * The ambient light's energy. The higher the value, the stronger the light. Only effective if
     * `ambient_light_sky_contribution` is lower than `1.0` (exclusive).
     *
     * Generated from Godot docs: Environment.get_ambient_light_energy
     */
    fun getAmbientLightEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAmbientLightEnergyBind, handle)
    }

    /**
     * Defines the amount of light that the sky brings on the scene. A value of `0.0` means that the
     * sky's light emission has no effect on the scene illumination, thus all ambient illumination is
     * provided by the ambient light. On the contrary, a value of `1.0` means that all the light that
     * affects the scene is provided by the sky, thus the ambient light parameter has no effect on the
     * scene. Note: `ambient_light_sky_contribution` is internally clamped between `0.0` and `1.0`
     * (inclusive).
     *
     * Generated from Godot docs: Environment.set_ambient_light_sky_contribution
     */
    fun setAmbientLightSkyContribution(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAmbientLightSkyContributionBind, handle, ratio)
    }

    /**
     * Defines the amount of light that the sky brings on the scene. A value of `0.0` means that the
     * sky's light emission has no effect on the scene illumination, thus all ambient illumination is
     * provided by the ambient light. On the contrary, a value of `1.0` means that all the light that
     * affects the scene is provided by the sky, thus the ambient light parameter has no effect on the
     * scene. Note: `ambient_light_sky_contribution` is internally clamped between `0.0` and `1.0`
     * (inclusive).
     *
     * Generated from Godot docs: Environment.get_ambient_light_sky_contribution
     */
    fun getAmbientLightSkyContribution(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAmbientLightSkyContributionBind, handle)
    }

    /**
     * The reflected (specular) light source.
     *
     * Generated from Godot docs: Environment.set_reflection_source
     */
    fun setReflectionSource(source: Long) {
        ObjectCalls.ptrcallWithLongArg(setReflectionSourceBind, handle, source)
    }

    /**
     * The reflected (specular) light source.
     *
     * Generated from Godot docs: Environment.get_reflection_source
     */
    fun getReflectionSource(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getReflectionSourceBind, handle)
    }

    /**
     * The tonemapping mode to use. Tonemapping is the process that "converts" HDR values to be
     * suitable for rendering on an LDR display. (Godot doesn't support rendering on HDR displays yet.)
     *
     * Generated from Godot docs: Environment.set_tonemapper
     */
    fun setTonemapper(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTonemapperBind, handle, mode)
    }

    /**
     * The tonemapping mode to use. Tonemapping is the process that "converts" HDR values to be
     * suitable for rendering on an LDR display. (Godot doesn't support rendering on HDR displays yet.)
     *
     * Generated from Godot docs: Environment.get_tonemapper
     */
    fun getTonemapper(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTonemapperBind, handle)
    }

    /**
     * Adjusts the brightness of values before they are provided to the tonemapper. Higher
     * `tonemap_exposure` values result in a brighter image. See also `tonemap_white`. Note: Values
     * provided to the tonemapper will also be multiplied by `2.0` and `1.8` for `TONE_MAPPER_FILMIC`
     * and `TONE_MAPPER_ACES` respectively to produce a similar apparent brightness as
     * `TONE_MAPPER_LINEAR`.
     *
     * Generated from Godot docs: Environment.set_tonemap_exposure
     */
    fun setTonemapExposure(exposure: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTonemapExposureBind, handle, exposure)
    }

    /**
     * Adjusts the brightness of values before they are provided to the tonemapper. Higher
     * `tonemap_exposure` values result in a brighter image. See also `tonemap_white`. Note: Values
     * provided to the tonemapper will also be multiplied by `2.0` and `1.8` for `TONE_MAPPER_FILMIC`
     * and `TONE_MAPPER_ACES` respectively to produce a similar apparent brightness as
     * `TONE_MAPPER_LINEAR`.
     *
     * Generated from Godot docs: Environment.get_tonemap_exposure
     */
    fun getTonemapExposure(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTonemapExposureBind, handle)
    }

    /**
     * The white reference value for tonemapping, which indicates where bright white is located in the
     * scale of values provided to the tonemapper. For photorealistic lighting, it is recommended to
     * set `tonemap_white` to at least `6.0`. Higher values result in less blown out highlights, but
     * may make the scene appear lower contrast. `tonemap_agx_white` will be used instead when using
     * the `TONE_MAPPER_AGX` tonemapper. See also `tonemap_exposure`. Note: `tonemap_white` must be set
     * to `2.0` or lower on the Mobile renderer to produce bright images. Note: `tonemap_white` is
     * ignored when using `TONE_MAPPER_LINEAR` and will be dynamically adjusted at runtime to never be
     * less than the parent window's `Window.get_output_max_linear_value` when using
     * `TONE_MAPPER_REINHARDT` with `Viewport.use_hdr_2d`.
     *
     * Generated from Godot docs: Environment.set_tonemap_white
     */
    fun setTonemapWhite(white: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTonemapWhiteBind, handle, white)
    }

    /**
     * The white reference value for tonemapping, which indicates where bright white is located in the
     * scale of values provided to the tonemapper. For photorealistic lighting, it is recommended to
     * set `tonemap_white` to at least `6.0`. Higher values result in less blown out highlights, but
     * may make the scene appear lower contrast. `tonemap_agx_white` will be used instead when using
     * the `TONE_MAPPER_AGX` tonemapper. See also `tonemap_exposure`. Note: `tonemap_white` must be set
     * to `2.0` or lower on the Mobile renderer to produce bright images. Note: `tonemap_white` is
     * ignored when using `TONE_MAPPER_LINEAR` and will be dynamically adjusted at runtime to never be
     * less than the parent window's `Window.get_output_max_linear_value` when using
     * `TONE_MAPPER_REINHARDT` with `Viewport.use_hdr_2d`.
     *
     * Generated from Godot docs: Environment.get_tonemap_white
     */
    fun getTonemapWhite(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTonemapWhiteBind, handle)
    }

    /**
     * The white reference value for tonemapping, which indicates where bright white is located in the
     * scale of values provided to the tonemapper. For photorealistic lighting, it is recommended to
     * set `tonemap_agx_white` to at least `6.0`. Higher values result in less blown out highlights,
     * but may make the scene appear lower contrast. `tonemap_agx_white` is the same as
     * `tonemap_white`, but is only effective with the `TONE_MAPPER_AGX` tonemapper. See also
     * `tonemap_exposure`. Note: When using the Mobile renderer with `Viewport.use_hdr_2d` disabled,
     * `tonemap_agx_white` is ignored and a white value of `2.0` will always be used instead.
     * Otherwise, `tonemap_agx_white` will be dynamically adjusted at runtime by multiplying it by the
     * parent window's `Window.get_output_max_linear_value` when using `Viewport.use_hdr_2d` to ensure
     * good behavior with both SDR and HDR output.
     *
     * Generated from Godot docs: Environment.set_tonemap_agx_white
     */
    fun setTonemapAgxWhite(white: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTonemapAgxWhiteBind, handle, white)
    }

    /**
     * The white reference value for tonemapping, which indicates where bright white is located in the
     * scale of values provided to the tonemapper. For photorealistic lighting, it is recommended to
     * set `tonemap_agx_white` to at least `6.0`. Higher values result in less blown out highlights,
     * but may make the scene appear lower contrast. `tonemap_agx_white` is the same as
     * `tonemap_white`, but is only effective with the `TONE_MAPPER_AGX` tonemapper. See also
     * `tonemap_exposure`. Note: When using the Mobile renderer with `Viewport.use_hdr_2d` disabled,
     * `tonemap_agx_white` is ignored and a white value of `2.0` will always be used instead.
     * Otherwise, `tonemap_agx_white` will be dynamically adjusted at runtime by multiplying it by the
     * parent window's `Window.get_output_max_linear_value` when using `Viewport.use_hdr_2d` to ensure
     * good behavior with both SDR and HDR output.
     *
     * Generated from Godot docs: Environment.get_tonemap_agx_white
     */
    fun getTonemapAgxWhite(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTonemapAgxWhiteBind, handle)
    }

    /**
     * Increasing `tonemap_agx_contrast` will make dark values darker and bright values brighter.
     * Produces a higher quality result than `adjustment_contrast` without any additional performance
     * cost, but is only available when using the `TONE_MAPPER_AGX` tonemapper.
     *
     * Generated from Godot docs: Environment.set_tonemap_agx_contrast
     */
    fun setTonemapAgxContrast(contrast: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTonemapAgxContrastBind, handle, contrast)
    }

    /**
     * Increasing `tonemap_agx_contrast` will make dark values darker and bright values brighter.
     * Produces a higher quality result than `adjustment_contrast` without any additional performance
     * cost, but is only available when using the `TONE_MAPPER_AGX` tonemapper.
     *
     * Generated from Godot docs: Environment.get_tonemap_agx_contrast
     */
    fun getTonemapAgxContrast(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTonemapAgxContrastBind, handle)
    }

    /**
     * If `true`, screen-space reflections are enabled. Screen-space reflections are more accurate than
     * reflections from `VoxelGI`s or `ReflectionProbe`s, but are slower and can't reflect surfaces
     * occluded by others. Note: SSR is only supported in the Forward+ rendering method, not Mobile or
     * Compatibility. Note: SSR is not supported on viewports that have a transparent background (where
     * `Viewport.transparent_bg` is `true`).
     *
     * Generated from Godot docs: Environment.set_ssr_enabled
     */
    fun setSsrEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSsrEnabledBind, handle, enabled)
    }

    /**
     * If `true`, screen-space reflections are enabled. Screen-space reflections are more accurate than
     * reflections from `VoxelGI`s or `ReflectionProbe`s, but are slower and can't reflect surfaces
     * occluded by others. Note: SSR is only supported in the Forward+ rendering method, not Mobile or
     * Compatibility. Note: SSR is not supported on viewports that have a transparent background (where
     * `Viewport.transparent_bg` is `true`).
     *
     * Generated from Godot docs: Environment.is_ssr_enabled
     */
    fun isSsrEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSsrEnabledBind, handle)
    }

    /**
     * The maximum number of steps for screen-space reflections. Higher values are slower.
     *
     * Generated from Godot docs: Environment.set_ssr_max_steps
     */
    fun setSsrMaxSteps(maxSteps: Int) {
        ObjectCalls.ptrcallWithIntArg(setSsrMaxStepsBind, handle, maxSteps)
    }

    /**
     * The maximum number of steps for screen-space reflections. Higher values are slower.
     *
     * Generated from Godot docs: Environment.get_ssr_max_steps
     */
    fun getSsrMaxSteps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSsrMaxStepsBind, handle)
    }

    /**
     * The fade-in distance for screen-space reflections. Affects the area from the reflected material
     * to the screen-space reflection. Only positive values are valid (negative values will be clamped
     * to `0.0`).
     *
     * Generated from Godot docs: Environment.set_ssr_fade_in
     */
    fun setSsrFadeIn(fadeIn: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsrFadeInBind, handle, fadeIn)
    }

    /**
     * The fade-in distance for screen-space reflections. Affects the area from the reflected material
     * to the screen-space reflection. Only positive values are valid (negative values will be clamped
     * to `0.0`).
     *
     * Generated from Godot docs: Environment.get_ssr_fade_in
     */
    fun getSsrFadeIn(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsrFadeInBind, handle)
    }

    /**
     * The fade-out distance for screen-space reflections. Affects the area from the screen-space
     * reflection to the "global" reflection. Only positive values are valid (negative values will be
     * clamped to `0.0`).
     *
     * Generated from Godot docs: Environment.set_ssr_fade_out
     */
    fun setSsrFadeOut(fadeOut: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsrFadeOutBind, handle, fadeOut)
    }

    /**
     * The fade-out distance for screen-space reflections. Affects the area from the screen-space
     * reflection to the "global" reflection. Only positive values are valid (negative values will be
     * clamped to `0.0`).
     *
     * Generated from Godot docs: Environment.get_ssr_fade_out
     */
    fun getSsrFadeOut(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsrFadeOutBind, handle)
    }

    /**
     * The depth tolerance for screen-space reflections.
     *
     * Generated from Godot docs: Environment.set_ssr_depth_tolerance
     */
    fun setSsrDepthTolerance(depthTolerance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsrDepthToleranceBind, handle, depthTolerance)
    }

    /**
     * The depth tolerance for screen-space reflections.
     *
     * Generated from Godot docs: Environment.get_ssr_depth_tolerance
     */
    fun getSsrDepthTolerance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsrDepthToleranceBind, handle)
    }

    /**
     * If `true`, the screen-space ambient occlusion effect is enabled. This darkens objects' corners
     * and cavities to simulate ambient light not reaching the entire object as in real life. This
     * works well for small, dynamic objects, but baked lighting or ambient occlusion textures will do
     * a better job at displaying ambient occlusion on large static objects. Godot uses a form of SSAO
     * called Adaptive Screen Space Ambient Occlusion which is itself a form of Horizon Based Ambient
     * Occlusion. Note: SSAO is only supported in the Forward+ and Compatibility rendering methods, not
     * Mobile.
     *
     * Generated from Godot docs: Environment.set_ssao_enabled
     */
    fun setSsaoEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSsaoEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the screen-space ambient occlusion effect is enabled. This darkens objects' corners
     * and cavities to simulate ambient light not reaching the entire object as in real life. This
     * works well for small, dynamic objects, but baked lighting or ambient occlusion textures will do
     * a better job at displaying ambient occlusion on large static objects. Godot uses a form of SSAO
     * called Adaptive Screen Space Ambient Occlusion which is itself a form of Horizon Based Ambient
     * Occlusion. Note: SSAO is only supported in the Forward+ and Compatibility rendering methods, not
     * Mobile.
     *
     * Generated from Godot docs: Environment.is_ssao_enabled
     */
    fun isSsaoEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSsaoEnabledBind, handle)
    }

    /**
     * The distance at which objects can occlude each other when calculating screen-space ambient
     * occlusion. Higher values will result in occlusion over a greater distance at the cost of
     * performance and quality.
     *
     * Generated from Godot docs: Environment.set_ssao_radius
     */
    fun setSsaoRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoRadiusBind, handle, radius)
    }

    /**
     * The distance at which objects can occlude each other when calculating screen-space ambient
     * occlusion. Higher values will result in occlusion over a greater distance at the cost of
     * performance and quality.
     *
     * Generated from Godot docs: Environment.get_ssao_radius
     */
    fun getSsaoRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoRadiusBind, handle)
    }

    /**
     * The primary screen-space ambient occlusion intensity. Acts as a multiplier for the screen-space
     * ambient occlusion effect. A higher value results in darker occlusion.
     *
     * Generated from Godot docs: Environment.set_ssao_intensity
     */
    fun setSsaoIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoIntensityBind, handle, intensity)
    }

    /**
     * The primary screen-space ambient occlusion intensity. Acts as a multiplier for the screen-space
     * ambient occlusion effect. A higher value results in darker occlusion.
     *
     * Generated from Godot docs: Environment.get_ssao_intensity
     */
    fun getSsaoIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoIntensityBind, handle)
    }

    /**
     * The distribution of occlusion. A higher value results in darker occlusion, similar to
     * `ssao_intensity`, but with a sharper falloff.
     *
     * Generated from Godot docs: Environment.set_ssao_power
     */
    fun setSsaoPower(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoPowerBind, handle, power)
    }

    /**
     * The distribution of occlusion. A higher value results in darker occlusion, similar to
     * `ssao_intensity`, but with a sharper falloff.
     *
     * Generated from Godot docs: Environment.get_ssao_power
     */
    fun getSsaoPower(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoPowerBind, handle)
    }

    /**
     * Sets the strength of the additional level of detail for the screen-space ambient occlusion
     * effect. A high value makes the detail pass more prominent, but it may contribute to aliasing in
     * your final image.
     *
     * Generated from Godot docs: Environment.set_ssao_detail
     */
    fun setSsaoDetail(detail: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoDetailBind, handle, detail)
    }

    /**
     * Sets the strength of the additional level of detail for the screen-space ambient occlusion
     * effect. A high value makes the detail pass more prominent, but it may contribute to aliasing in
     * your final image.
     *
     * Generated from Godot docs: Environment.get_ssao_detail
     */
    fun getSsaoDetail(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoDetailBind, handle)
    }

    /**
     * The threshold for considering whether a given point on a surface is occluded or not represented
     * as an angle from the horizon mapped into the `0.0-1.0` range. A value of `1.0` results in no
     * occlusion.
     *
     * Generated from Godot docs: Environment.set_ssao_horizon
     */
    fun setSsaoHorizon(horizon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoHorizonBind, handle, horizon)
    }

    /**
     * The threshold for considering whether a given point on a surface is occluded or not represented
     * as an angle from the horizon mapped into the `0.0-1.0` range. A value of `1.0` results in no
     * occlusion.
     *
     * Generated from Godot docs: Environment.get_ssao_horizon
     */
    fun getSsaoHorizon(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoHorizonBind, handle)
    }

    /**
     * The amount that the screen-space ambient occlusion effect is allowed to blur over the edges of
     * objects. Setting too high will result in aliasing around the edges of objects. Setting too low
     * will make object edges appear blurry.
     *
     * Generated from Godot docs: Environment.set_ssao_sharpness
     */
    fun setSsaoSharpness(sharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoSharpnessBind, handle, sharpness)
    }

    /**
     * The amount that the screen-space ambient occlusion effect is allowed to blur over the edges of
     * objects. Setting too high will result in aliasing around the edges of objects. Setting too low
     * will make object edges appear blurry.
     *
     * Generated from Godot docs: Environment.get_ssao_sharpness
     */
    fun getSsaoSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoSharpnessBind, handle)
    }

    /**
     * The screen-space ambient occlusion intensity in direct light. In real life, ambient occlusion
     * only applies to indirect light, which means its effects can't be seen in direct light. Values
     * higher than `0` will make the SSAO effect visible in direct light.
     *
     * Generated from Godot docs: Environment.set_ssao_direct_light_affect
     */
    fun setSsaoDirectLightAffect(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoDirectLightAffectBind, handle, amount)
    }

    /**
     * The screen-space ambient occlusion intensity in direct light. In real life, ambient occlusion
     * only applies to indirect light, which means its effects can't be seen in direct light. Values
     * higher than `0` will make the SSAO effect visible in direct light.
     *
     * Generated from Godot docs: Environment.get_ssao_direct_light_affect
     */
    fun getSsaoDirectLightAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoDirectLightAffectBind, handle)
    }

    /**
     * The screen-space ambient occlusion intensity on materials that have an AO texture defined.
     * Values higher than `0` will make the SSAO effect visible in areas darkened by AO textures.
     *
     * Generated from Godot docs: Environment.set_ssao_ao_channel_affect
     */
    fun setSsaoAoChannelAffect(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsaoAoChannelAffectBind, handle, amount)
    }

    /**
     * The screen-space ambient occlusion intensity on materials that have an AO texture defined.
     * Values higher than `0` will make the SSAO effect visible in areas darkened by AO textures.
     *
     * Generated from Godot docs: Environment.get_ssao_ao_channel_affect
     */
    fun getSsaoAoChannelAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsaoAoChannelAffectBind, handle)
    }

    /**
     * If `true`, the screen-space indirect lighting effect is enabled. Screen space indirect lighting
     * is a form of indirect lighting that allows diffuse light to bounce between nearby objects.
     * Screen-space indirect lighting works very similarly to screen-space ambient occlusion, in that
     * it only affects a limited range. It is intended to be used along with a form of proper global
     * illumination like SDFGI or `VoxelGI`. Screen-space indirect lighting is not affected by
     * individual light's `Light3D.light_indirect_energy`. Note: SSIL is only supported in the Forward+
     * rendering method, not Mobile or Compatibility.
     *
     * Generated from Godot docs: Environment.set_ssil_enabled
     */
    fun setSsilEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSsilEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the screen-space indirect lighting effect is enabled. Screen space indirect lighting
     * is a form of indirect lighting that allows diffuse light to bounce between nearby objects.
     * Screen-space indirect lighting works very similarly to screen-space ambient occlusion, in that
     * it only affects a limited range. It is intended to be used along with a form of proper global
     * illumination like SDFGI or `VoxelGI`. Screen-space indirect lighting is not affected by
     * individual light's `Light3D.light_indirect_energy`. Note: SSIL is only supported in the Forward+
     * rendering method, not Mobile or Compatibility.
     *
     * Generated from Godot docs: Environment.is_ssil_enabled
     */
    fun isSsilEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSsilEnabledBind, handle)
    }

    /**
     * The distance that bounced lighting can travel when using the screen space indirect lighting
     * effect. A larger value will result in light bouncing further in a scene, but may result in
     * under-sampling artifacts which look like long spikes surrounding light sources.
     *
     * Generated from Godot docs: Environment.set_ssil_radius
     */
    fun setSsilRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsilRadiusBind, handle, radius)
    }

    /**
     * The distance that bounced lighting can travel when using the screen space indirect lighting
     * effect. A larger value will result in light bouncing further in a scene, but may result in
     * under-sampling artifacts which look like long spikes surrounding light sources.
     *
     * Generated from Godot docs: Environment.get_ssil_radius
     */
    fun getSsilRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsilRadiusBind, handle)
    }

    /**
     * The brightness multiplier for the screen-space indirect lighting effect. A higher value will
     * result in brighter light.
     *
     * Generated from Godot docs: Environment.set_ssil_intensity
     */
    fun setSsilIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsilIntensityBind, handle, intensity)
    }

    /**
     * The brightness multiplier for the screen-space indirect lighting effect. A higher value will
     * result in brighter light.
     *
     * Generated from Godot docs: Environment.get_ssil_intensity
     */
    fun getSsilIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsilIntensityBind, handle)
    }

    /**
     * The amount that the screen-space indirect lighting effect is allowed to blur over the edges of
     * objects. Setting too high will result in aliasing around the edges of objects. Setting too low
     * will make object edges appear blurry.
     *
     * Generated from Godot docs: Environment.set_ssil_sharpness
     */
    fun setSsilSharpness(sharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsilSharpnessBind, handle, sharpness)
    }

    /**
     * The amount that the screen-space indirect lighting effect is allowed to blur over the edges of
     * objects. Setting too high will result in aliasing around the edges of objects. Setting too low
     * will make object edges appear blurry.
     *
     * Generated from Godot docs: Environment.get_ssil_sharpness
     */
    fun getSsilSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsilSharpnessBind, handle)
    }

    /**
     * Amount of normal rejection used when calculating screen-space indirect lighting. Normal
     * rejection uses the normal of a given sample point to reject samples that are facing away from
     * the current pixel. Normal rejection is necessary to avoid light leaking when only one side of an
     * object is illuminated. However, normal rejection can be disabled if light leaking is desirable,
     * such as when the scene mostly contains emissive objects that emit light from faces that cannot
     * be seen from the camera.
     *
     * Generated from Godot docs: Environment.set_ssil_normal_rejection
     */
    fun setSsilNormalRejection(normalRejection: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSsilNormalRejectionBind, handle, normalRejection)
    }

    /**
     * Amount of normal rejection used when calculating screen-space indirect lighting. Normal
     * rejection uses the normal of a given sample point to reject samples that are facing away from
     * the current pixel. Normal rejection is necessary to avoid light leaking when only one side of an
     * object is illuminated. However, normal rejection can be disabled if light leaking is desirable,
     * such as when the scene mostly contains emissive objects that emit light from faces that cannot
     * be seen from the camera.
     *
     * Generated from Godot docs: Environment.get_ssil_normal_rejection
     */
    fun getSsilNormalRejection(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSsilNormalRejectionBind, handle)
    }

    /**
     * If `true`, enables signed distance field global illumination for meshes that have their
     * `GeometryInstance3D.gi_mode` set to `GeometryInstance3D.GI_MODE_STATIC`. SDFGI is a real-time
     * global illumination technique that works well with procedurally generated and user-built levels,
     * including in situations where geometry is created during gameplay. The signed distance field is
     * automatically generated around the camera as it moves. Dynamic lights are supported, but dynamic
     * occluders and emissive surfaces are not. Note: SDFGI is only supported in the Forward+ rendering
     * method, not Mobile or Compatibility. Performance: SDFGI is relatively demanding on the GPU and
     * is not suited to low-end hardware such as integrated graphics (consider `LightmapGI` instead).
     * To improve SDFGI performance, enable
     * `ProjectSettings.rendering/global_illumination/gi/use_half_resolution` in the Project Settings.
     * Note: Meshes should have sufficiently thick walls to avoid light leaks (avoid one-sided walls).
     * For interior levels, enclose your level geometry in a sufficiently large box and bridge the
     * loops to close the mesh.
     *
     * Generated from Godot docs: Environment.set_sdfgi_enabled
     */
    fun setSdfgiEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSdfgiEnabledBind, handle, enabled)
    }

    /**
     * If `true`, enables signed distance field global illumination for meshes that have their
     * `GeometryInstance3D.gi_mode` set to `GeometryInstance3D.GI_MODE_STATIC`. SDFGI is a real-time
     * global illumination technique that works well with procedurally generated and user-built levels,
     * including in situations where geometry is created during gameplay. The signed distance field is
     * automatically generated around the camera as it moves. Dynamic lights are supported, but dynamic
     * occluders and emissive surfaces are not. Note: SDFGI is only supported in the Forward+ rendering
     * method, not Mobile or Compatibility. Performance: SDFGI is relatively demanding on the GPU and
     * is not suited to low-end hardware such as integrated graphics (consider `LightmapGI` instead).
     * To improve SDFGI performance, enable
     * `ProjectSettings.rendering/global_illumination/gi/use_half_resolution` in the Project Settings.
     * Note: Meshes should have sufficiently thick walls to avoid light leaks (avoid one-sided walls).
     * For interior levels, enclose your level geometry in a sufficiently large box and bridge the
     * loops to close the mesh.
     *
     * Generated from Godot docs: Environment.is_sdfgi_enabled
     */
    fun isSdfgiEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSdfgiEnabledBind, handle)
    }

    /**
     * The number of cascades to use for SDFGI (between 1 and 8). A higher number of cascades allows
     * displaying SDFGI further away while preserving detail up close, at the cost of performance. When
     * using SDFGI on small-scale levels, `sdfgi_cascades` can often be decreased between `1` and `4`
     * to improve performance.
     *
     * Generated from Godot docs: Environment.set_sdfgi_cascades
     */
    fun setSdfgiCascades(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setSdfgiCascadesBind, handle, amount)
    }

    /**
     * The number of cascades to use for SDFGI (between 1 and 8). A higher number of cascades allows
     * displaying SDFGI further away while preserving detail up close, at the cost of performance. When
     * using SDFGI on small-scale levels, `sdfgi_cascades` can often be decreased between `1` and `4`
     * to improve performance.
     *
     * Generated from Godot docs: Environment.get_sdfgi_cascades
     */
    fun getSdfgiCascades(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSdfgiCascadesBind, handle)
    }

    /**
     * The cell size to use for the closest SDFGI cascade (in 3D units). Lower values allow SDFGI to be
     * more precise up close, at the cost of making SDFGI updates more demanding. This can cause
     * stuttering when the camera moves fast. Higher values allow SDFGI to cover more ground, while
     * also reducing the performance impact of SDFGI updates. Note: This property is linked to
     * `sdfgi_max_distance` and `sdfgi_cascade0_distance`. Changing its value will automatically change
     * those properties as well.
     *
     * Generated from Godot docs: Environment.set_sdfgi_min_cell_size
     */
    fun setSdfgiMinCellSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiMinCellSizeBind, handle, size)
    }

    /**
     * The cell size to use for the closest SDFGI cascade (in 3D units). Lower values allow SDFGI to be
     * more precise up close, at the cost of making SDFGI updates more demanding. This can cause
     * stuttering when the camera moves fast. Higher values allow SDFGI to cover more ground, while
     * also reducing the performance impact of SDFGI updates. Note: This property is linked to
     * `sdfgi_max_distance` and `sdfgi_cascade0_distance`. Changing its value will automatically change
     * those properties as well.
     *
     * Generated from Godot docs: Environment.get_sdfgi_min_cell_size
     */
    fun getSdfgiMinCellSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiMinCellSizeBind, handle)
    }

    /**
     * The maximum distance at which SDFGI is visible. Beyond this distance, environment lighting or
     * other sources of GI such as `ReflectionProbe` will be used as a fallback. Note: This property is
     * linked to `sdfgi_min_cell_size` and `sdfgi_cascade0_distance`. Changing its value will
     * automatically change those properties as well.
     *
     * Generated from Godot docs: Environment.set_sdfgi_max_distance
     */
    fun setSdfgiMaxDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiMaxDistanceBind, handle, distance)
    }

    /**
     * The maximum distance at which SDFGI is visible. Beyond this distance, environment lighting or
     * other sources of GI such as `ReflectionProbe` will be used as a fallback. Note: This property is
     * linked to `sdfgi_min_cell_size` and `sdfgi_cascade0_distance`. Changing its value will
     * automatically change those properties as well.
     *
     * Generated from Godot docs: Environment.get_sdfgi_max_distance
     */
    fun getSdfgiMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiMaxDistanceBind, handle)
    }

    /**
     * Note: This property is linked to `sdfgi_min_cell_size` and `sdfgi_max_distance`. Changing its
     * value will automatically change those properties as well.
     *
     * Generated from Godot docs: Environment.set_sdfgi_cascade0_distance
     */
    fun setSdfgiCascade0Distance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiCascade0DistanceBind, handle, distance)
    }

    /**
     * Note: This property is linked to `sdfgi_min_cell_size` and `sdfgi_max_distance`. Changing its
     * value will automatically change those properties as well.
     *
     * Generated from Godot docs: Environment.get_sdfgi_cascade0_distance
     */
    fun getSdfgiCascade0Distance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiCascade0DistanceBind, handle)
    }

    /**
     * The Y scale to use for SDFGI cells. Lower values will result in SDFGI cells being packed
     * together more closely on the Y axis. This is used to balance between quality and covering a lot
     * of vertical ground. `sdfgi_y_scale` should be set depending on how vertical your scene is (and
     * how fast your camera may move on the Y axis).
     *
     * Generated from Godot docs: Environment.set_sdfgi_y_scale
     */
    fun setSdfgiYScale(scale: Long) {
        ObjectCalls.ptrcallWithLongArg(setSdfgiYScaleBind, handle, scale)
    }

    /**
     * The Y scale to use for SDFGI cells. Lower values will result in SDFGI cells being packed
     * together more closely on the Y axis. This is used to balance between quality and covering a lot
     * of vertical ground. `sdfgi_y_scale` should be set depending on how vertical your scene is (and
     * how fast your camera may move on the Y axis).
     *
     * Generated from Godot docs: Environment.get_sdfgi_y_scale
     */
    fun getSdfgiYScale(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSdfgiYScaleBind, handle)
    }

    /**
     * If `true`, SDFGI uses an occlusion detection approach to reduce light leaking. Occlusion may
     * however introduce dark blotches in certain spots, which may be undesired in mostly outdoor
     * scenes. `sdfgi_use_occlusion` has a performance impact and should only be enabled when needed.
     *
     * Generated from Godot docs: Environment.set_sdfgi_use_occlusion
     */
    fun setSdfgiUseOcclusion(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSdfgiUseOcclusionBind, handle, enable)
    }

    /**
     * If `true`, SDFGI uses an occlusion detection approach to reduce light leaking. Occlusion may
     * however introduce dark blotches in certain spots, which may be undesired in mostly outdoor
     * scenes. `sdfgi_use_occlusion` has a performance impact and should only be enabled when needed.
     *
     * Generated from Godot docs: Environment.is_sdfgi_using_occlusion
     */
    fun isSdfgiUsingOcclusion(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSdfgiUsingOcclusionBind, handle)
    }

    /**
     * The energy multiplier applied to light every time it bounces from a surface when using SDFGI.
     * Values greater than `0.0` will simulate multiple bounces, resulting in a more realistic
     * appearance. Increasing `sdfgi_bounce_feedback` generally has no performance impact. See also
     * `sdfgi_energy`. Note: Values greater than `0.5` can cause infinite feedback loops and should be
     * avoided in scenes with bright materials. Note: If `sdfgi_bounce_feedback` is `0.0`, indirect
     * lighting will not be represented in reflections as light will only bounce one time.
     *
     * Generated from Godot docs: Environment.set_sdfgi_bounce_feedback
     */
    fun setSdfgiBounceFeedback(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiBounceFeedbackBind, handle, amount)
    }

    /**
     * The energy multiplier applied to light every time it bounces from a surface when using SDFGI.
     * Values greater than `0.0` will simulate multiple bounces, resulting in a more realistic
     * appearance. Increasing `sdfgi_bounce_feedback` generally has no performance impact. See also
     * `sdfgi_energy`. Note: Values greater than `0.5` can cause infinite feedback loops and should be
     * avoided in scenes with bright materials. Note: If `sdfgi_bounce_feedback` is `0.0`, indirect
     * lighting will not be represented in reflections as light will only bounce one time.
     *
     * Generated from Godot docs: Environment.get_sdfgi_bounce_feedback
     */
    fun getSdfgiBounceFeedback(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiBounceFeedbackBind, handle)
    }

    /**
     * If `true`, SDFGI takes the environment lighting into account. This should be set to `false` for
     * interior scenes.
     *
     * Generated from Godot docs: Environment.set_sdfgi_read_sky_light
     */
    fun setSdfgiReadSkyLight(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSdfgiReadSkyLightBind, handle, enable)
    }

    /**
     * If `true`, SDFGI takes the environment lighting into account. This should be set to `false` for
     * interior scenes.
     *
     * Generated from Godot docs: Environment.is_sdfgi_reading_sky_light
     */
    fun isSdfgiReadingSkyLight(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSdfgiReadingSkyLightBind, handle)
    }

    /**
     * The energy multiplier to use for SDFGI. Higher values will result in brighter indirect lighting
     * and reflections. See also `sdfgi_bounce_feedback`.
     *
     * Generated from Godot docs: Environment.set_sdfgi_energy
     */
    fun setSdfgiEnergy(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiEnergyBind, handle, amount)
    }

    /**
     * The energy multiplier to use for SDFGI. Higher values will result in brighter indirect lighting
     * and reflections. See also `sdfgi_bounce_feedback`.
     *
     * Generated from Godot docs: Environment.get_sdfgi_energy
     */
    fun getSdfgiEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiEnergyBind, handle)
    }

    /**
     * The normal bias to use for SDFGI probes. Increasing this value can reduce visible streaking
     * artifacts on sloped surfaces, at the cost of increased light leaking.
     *
     * Generated from Godot docs: Environment.set_sdfgi_normal_bias
     */
    fun setSdfgiNormalBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiNormalBiasBind, handle, bias)
    }

    /**
     * The normal bias to use for SDFGI probes. Increasing this value can reduce visible streaking
     * artifacts on sloped surfaces, at the cost of increased light leaking.
     *
     * Generated from Godot docs: Environment.get_sdfgi_normal_bias
     */
    fun getSdfgiNormalBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiNormalBiasBind, handle)
    }

    /**
     * The constant bias to use for SDFGI probes. Increasing this value can reduce visible streaking
     * artifacts on sloped surfaces, at the cost of increased light leaking.
     *
     * Generated from Godot docs: Environment.set_sdfgi_probe_bias
     */
    fun setSdfgiProbeBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSdfgiProbeBiasBind, handle, bias)
    }

    /**
     * The constant bias to use for SDFGI probes. Increasing this value can reduce visible streaking
     * artifacts on sloped surfaces, at the cost of increased light leaking.
     *
     * Generated from Godot docs: Environment.get_sdfgi_probe_bias
     */
    fun getSdfgiProbeBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSdfgiProbeBiasBind, handle)
    }

    /**
     * If `true`, the glow effect is enabled. This simulates real world atmosphere and eye/camera
     * behavior by causing bright pixels to bleed onto surrounding pixels. Note: When using the Mobile
     * rendering method, glow looks different due to the lower dynamic range available in the Mobile
     * rendering method. Note: When using the Compatibility rendering method, glow uses a different
     * implementation with some properties being unavailable and hidden from the inspector:
     * `glow_levels/\*`, `glow_normalized`, `glow_strength`, `glow_blend_mode`, `glow_mix`, `glow_map`,
     * and `glow_map_strength`. This implementation is optimized to run on low-end devices and is less
     * flexible as a result.
     *
     * Generated from Godot docs: Environment.set_glow_enabled
     */
    fun setGlowEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGlowEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the glow effect is enabled. This simulates real world atmosphere and eye/camera
     * behavior by causing bright pixels to bleed onto surrounding pixels. Note: When using the Mobile
     * rendering method, glow looks different due to the lower dynamic range available in the Mobile
     * rendering method. Note: When using the Compatibility rendering method, glow uses a different
     * implementation with some properties being unavailable and hidden from the inspector:
     * `glow_levels/\*`, `glow_normalized`, `glow_strength`, `glow_blend_mode`, `glow_mix`, `glow_map`,
     * and `glow_map_strength`. This implementation is optimized to run on low-end devices and is less
     * flexible as a result.
     *
     * Generated from Godot docs: Environment.is_glow_enabled
     */
    fun isGlowEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGlowEnabledBind, handle)
    }

    /**
     * The intensity of the 7th level of glow. This is the most "global" level (blurriest). Note:
     * `glow_levels/7` has no effect when using the Compatibility rendering method, due to this
     * rendering method using a simpler glow implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.set_glow_level
     */
    fun setGlowLevel(idx: Int, intensity: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setGlowLevelBind, handle, idx, intensity)
    }

    /**
     * The intensity of the 7th level of glow. This is the most "global" level (blurriest). Note:
     * `glow_levels/7` has no effect when using the Compatibility rendering method, due to this
     * rendering method using a simpler glow implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.get_glow_level
     */
    fun getGlowLevel(idx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getGlowLevelBind, handle, idx)
    }

    /**
     * If `true`, glow levels will be normalized so that summed together their intensities equal `1.0`.
     * Note: `glow_normalized` has no effect when using the Compatibility rendering method, due to this
     * rendering method using a simpler glow implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.set_glow_normalized
     */
    fun setGlowNormalized(normalize: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGlowNormalizedBind, handle, normalize)
    }

    /**
     * If `true`, glow levels will be normalized so that summed together their intensities equal `1.0`.
     * Note: `glow_normalized` has no effect when using the Compatibility rendering method, due to this
     * rendering method using a simpler glow implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.is_glow_normalized
     */
    fun isGlowNormalized(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGlowNormalizedBind, handle)
    }

    /**
     * The overall brightness multiplier that is applied to the glow effect just before it is blended
     * with the scene. When using the Mobile rendering method (which only supports a lower dynamic
     * range up to `2.0`), this should be increased to `1.5` to compensate.
     *
     * Generated from Godot docs: Environment.set_glow_intensity
     */
    fun setGlowIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowIntensityBind, handle, intensity)
    }

    /**
     * The overall brightness multiplier that is applied to the glow effect just before it is blended
     * with the scene. When using the Mobile rendering method (which only supports a lower dynamic
     * range up to `2.0`), this should be increased to `1.5` to compensate.
     *
     * Generated from Godot docs: Environment.get_glow_intensity
     */
    fun getGlowIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowIntensityBind, handle)
    }

    /**
     * The strength that is used when blurring across the screen to generate the glow effect. This
     * affects the distance and intensity of the blur. When using the Mobile rendering method, this
     * should be increased to compensate for the lower dynamic range. Note: `glow_strength` has no
     * effect when using the Compatibility rendering method, due to this rendering method using a
     * simpler glow implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.set_glow_strength
     */
    fun setGlowStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowStrengthBind, handle, strength)
    }

    /**
     * The strength that is used when blurring across the screen to generate the glow effect. This
     * affects the distance and intensity of the blur. When using the Mobile rendering method, this
     * should be increased to compensate for the lower dynamic range. Note: `glow_strength` has no
     * effect when using the Compatibility rendering method, due to this rendering method using a
     * simpler glow implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.get_glow_strength
     */
    fun getGlowStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowStrengthBind, handle)
    }

    /**
     * When using the `GLOW_BLEND_MODE_MIX` `glow_blend_mode`, this controls how much the source image
     * is blended with the glow layer. A value of `0.0` makes the glow rendering invisible, while a
     * value of `1.0` is equivalent to `GLOW_BLEND_MODE_REPLACE`. Note: `glow_mix` has no effect when
     * using the Compatibility rendering method, due to this rendering method using a simpler glow
     * implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.set_glow_mix
     */
    fun setGlowMix(mix: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowMixBind, handle, mix)
    }

    /**
     * When using the `GLOW_BLEND_MODE_MIX` `glow_blend_mode`, this controls how much the source image
     * is blended with the glow layer. A value of `0.0` makes the glow rendering invisible, while a
     * value of `1.0` is equivalent to `GLOW_BLEND_MODE_REPLACE`. Note: `glow_mix` has no effect when
     * using the Compatibility rendering method, due to this rendering method using a simpler glow
     * implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.get_glow_mix
     */
    fun getGlowMix(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowMixBind, handle)
    }

    /**
     * The bloom's intensity. If set to a value higher than `0`, this will make glow visible in areas
     * darker than the `glow_hdr_threshold`.
     *
     * Generated from Godot docs: Environment.set_glow_bloom
     */
    fun setGlowBloom(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowBloomBind, handle, amount)
    }

    /**
     * The bloom's intensity. If set to a value higher than `0`, this will make glow visible in areas
     * darker than the `glow_hdr_threshold`.
     *
     * Generated from Godot docs: Environment.get_glow_bloom
     */
    fun getGlowBloom(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowBloomBind, handle)
    }

    /**
     * The glow blending mode. Note: The Compatibility renderer always uses `GLOW_BLEND_MODE_SCREEN`
     * and `glow_blend_mode` will have no effect.
     *
     * Generated from Godot docs: Environment.set_glow_blend_mode
     */
    fun setGlowBlendMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setGlowBlendModeBind, handle, mode)
    }

    /**
     * The glow blending mode. Note: The Compatibility renderer always uses `GLOW_BLEND_MODE_SCREEN`
     * and `glow_blend_mode` will have no effect.
     *
     * Generated from Godot docs: Environment.get_glow_blend_mode
     */
    fun getGlowBlendMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGlowBlendModeBind, handle)
    }

    /**
     * The lower threshold of the HDR glow. When using the Mobile rendering method (which only supports
     * a lower dynamic range up to `2.0`), this may need to be below `1.0` for glow to be visible. A
     * value of `0.9` works well in this case. This value also needs to be decreased below `1.0` when
     * using glow in 2D, as 2D rendering is performed in SDR.
     *
     * Generated from Godot docs: Environment.set_glow_hdr_bleed_threshold
     */
    fun setGlowHdrBleedThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowHdrBleedThresholdBind, handle, threshold)
    }

    /**
     * The lower threshold of the HDR glow. When using the Mobile rendering method (which only supports
     * a lower dynamic range up to `2.0`), this may need to be below `1.0` for glow to be visible. A
     * value of `0.9` works well in this case. This value also needs to be decreased below `1.0` when
     * using glow in 2D, as 2D rendering is performed in SDR.
     *
     * Generated from Godot docs: Environment.get_glow_hdr_bleed_threshold
     */
    fun getGlowHdrBleedThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowHdrBleedThresholdBind, handle)
    }

    /**
     * Smooths the transition between values that are below and above `glow_hdr_threshold` by reducing
     * the amount of glow generated by values that are close to `glow_hdr_threshold`. Values above
     * `glow_hdr_threshold + glow_hdr_scale` will not have glow reduced in this way.
     *
     * Generated from Godot docs: Environment.set_glow_hdr_bleed_scale
     */
    fun setGlowHdrBleedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowHdrBleedScaleBind, handle, scale)
    }

    /**
     * Smooths the transition between values that are below and above `glow_hdr_threshold` by reducing
     * the amount of glow generated by values that are close to `glow_hdr_threshold`. Values above
     * `glow_hdr_threshold + glow_hdr_scale` will not have glow reduced in this way.
     *
     * Generated from Godot docs: Environment.get_glow_hdr_bleed_scale
     */
    fun getGlowHdrBleedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowHdrBleedScaleBind, handle)
    }

    /**
     * The higher threshold of the HDR glow. Areas brighter than this threshold will be clamped for the
     * purposes of the glow effect.
     *
     * Generated from Godot docs: Environment.set_glow_hdr_luminance_cap
     */
    fun setGlowHdrLuminanceCap(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowHdrLuminanceCapBind, handle, amount)
    }

    /**
     * The higher threshold of the HDR glow. Areas brighter than this threshold will be clamped for the
     * purposes of the glow effect.
     *
     * Generated from Godot docs: Environment.get_glow_hdr_luminance_cap
     */
    fun getGlowHdrLuminanceCap(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowHdrLuminanceCapBind, handle)
    }

    /**
     * How strong of an influence the `glow_map` should have on the overall glow effect. A strength of
     * `0.0` means the glow map has no influence, while a strength of `1.0` means the glow map has full
     * influence. Note: If the glow map has black areas, a value of `1.0` can also turn off the glow
     * effect entirely in specific areas of the screen. Note: `glow_map_strength` has no effect when
     * using the Compatibility rendering method, due to this rendering method using a simpler glow
     * implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.set_glow_map_strength
     */
    fun setGlowMapStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlowMapStrengthBind, handle, strength)
    }

    /**
     * How strong of an influence the `glow_map` should have on the overall glow effect. A strength of
     * `0.0` means the glow map has no influence, while a strength of `1.0` means the glow map has full
     * influence. Note: If the glow map has black areas, a value of `1.0` can also turn off the glow
     * effect entirely in specific areas of the screen. Note: `glow_map_strength` has no effect when
     * using the Compatibility rendering method, due to this rendering method using a simpler glow
     * implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.get_glow_map_strength
     */
    fun getGlowMapStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlowMapStrengthBind, handle)
    }

    /**
     * The texture that should be used as a glow map to multiply the resulting glow color according to
     * `glow_map_strength`. This can be used to create a "lens dirt" effect. The texture's RGB color
     * channels are used for modulation, but the alpha channel is ignored. Note: The texture will be
     * stretched to fit the screen. Therefore, it's recommended to use a texture with an aspect ratio
     * that matches your project's base aspect ratio (typically 16:9). Note: `glow_map` has no effect
     * when using the Compatibility rendering method, due to this rendering method using a simpler glow
     * implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.set_glow_map
     */
    fun setGlowMap(mode: Texture?) {
        ObjectCalls.ptrcallWithObjectArgs(setGlowMapBind, handle, listOf(mode?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The texture that should be used as a glow map to multiply the resulting glow color according to
     * `glow_map_strength`. This can be used to create a "lens dirt" effect. The texture's RGB color
     * channels are used for modulation, but the alpha channel is ignored. Note: The texture will be
     * stretched to fit the screen. Therefore, it's recommended to use a texture with an aspect ratio
     * that matches your project's base aspect ratio (typically 16:9). Note: `glow_map` has no effect
     * when using the Compatibility rendering method, due to this rendering method using a simpler glow
     * implementation optimized for low-end devices.
     *
     * Generated from Godot docs: Environment.get_glow_map
     */
    fun getGlowMap(): Texture? {
        return Texture.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGlowMapBind, handle))
    }

    /**
     * If `true`, fog effects are enabled.
     *
     * Generated from Godot docs: Environment.set_fog_enabled
     */
    fun setFogEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFogEnabledBind, handle, enabled)
    }

    /**
     * If `true`, fog effects are enabled.
     *
     * Generated from Godot docs: Environment.is_fog_enabled
     */
    fun isFogEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFogEnabledBind, handle)
    }

    /**
     * The fog mode.
     *
     * Generated from Godot docs: Environment.set_fog_mode
     */
    fun setFogMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setFogModeBind, handle, mode)
    }

    /**
     * The fog mode.
     *
     * Generated from Godot docs: Environment.get_fog_mode
     */
    fun getFogMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFogModeBind, handle)
    }

    /**
     * The fog's color.
     *
     * Generated from Godot docs: Environment.set_fog_light_color
     */
    fun setFogLightColor(lightColor: Color) {
        ObjectCalls.ptrcallWithColorArg(setFogLightColorBind, handle, lightColor)
    }

    /**
     * The fog's color.
     *
     * Generated from Godot docs: Environment.get_fog_light_color
     */
    fun getFogLightColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getFogLightColorBind, handle)
    }

    /**
     * The fog's brightness. Higher values result in brighter fog.
     *
     * Generated from Godot docs: Environment.set_fog_light_energy
     */
    fun setFogLightEnergy(lightEnergy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogLightEnergyBind, handle, lightEnergy)
    }

    /**
     * The fog's brightness. Higher values result in brighter fog.
     *
     * Generated from Godot docs: Environment.get_fog_light_energy
     */
    fun getFogLightEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogLightEnergyBind, handle)
    }

    /**
     * If set above `0.0`, renders the scene's directional light(s) in the fog color depending on the
     * view angle. This can be used to give the impression that the sun is "piercing" through the fog.
     *
     * Generated from Godot docs: Environment.set_fog_sun_scatter
     */
    fun setFogSunScatter(sunScatter: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogSunScatterBind, handle, sunScatter)
    }

    /**
     * If set above `0.0`, renders the scene's directional light(s) in the fog color depending on the
     * view angle. This can be used to give the impression that the sun is "piercing" through the fog.
     *
     * Generated from Godot docs: Environment.get_fog_sun_scatter
     */
    fun getFogSunScatter(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogSunScatterBind, handle)
    }

    /**
     * The fog density to be used. This is demonstrated in different ways depending on the `fog_mode`
     * mode chosen: Exponential Fog Mode: Higher values result in denser fog. The fog rendering is
     * exponential like in real life. Depth Fog mode: The maximum intensity of the deep fog, effect
     * will appear in the distance (relative to the camera). At `1.0` the fog will fully obscure the
     * scene, at `0.0` the fog will not be visible.
     *
     * Generated from Godot docs: Environment.set_fog_density
     */
    fun setFogDensity(density: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogDensityBind, handle, density)
    }

    /**
     * The fog density to be used. This is demonstrated in different ways depending on the `fog_mode`
     * mode chosen: Exponential Fog Mode: Higher values result in denser fog. The fog rendering is
     * exponential like in real life. Depth Fog mode: The maximum intensity of the deep fog, effect
     * will appear in the distance (relative to the camera). At `1.0` the fog will fully obscure the
     * scene, at `0.0` the fog will not be visible.
     *
     * Generated from Godot docs: Environment.get_fog_density
     */
    fun getFogDensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogDensityBind, handle)
    }

    /**
     * The height at which the height fog effect begins.
     *
     * Generated from Godot docs: Environment.set_fog_height
     */
    fun setFogHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogHeightBind, handle, height)
    }

    /**
     * The height at which the height fog effect begins.
     *
     * Generated from Godot docs: Environment.get_fog_height
     */
    fun getFogHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogHeightBind, handle)
    }

    /**
     * The density used to increase fog as height decreases. To make fog increase as height increases,
     * use a negative value.
     *
     * Generated from Godot docs: Environment.set_fog_height_density
     */
    fun setFogHeightDensity(heightDensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogHeightDensityBind, handle, heightDensity)
    }

    /**
     * The density used to increase fog as height decreases. To make fog increase as height increases,
     * use a negative value.
     *
     * Generated from Godot docs: Environment.get_fog_height_density
     */
    fun getFogHeightDensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogHeightDensityBind, handle)
    }

    /**
     * If set above `0.0` (exclusive), blends between the fog's color and the color of the background
     * `Sky`, as read from the radiance cubemap. This has a small performance cost when set above
     * `0.0`. Must have `background_mode` set to `BG_SKY`. This is useful to simulate aerial
     * perspective (https://en.wikipedia.org/wiki/Aerial_perspective) in large scenes with low density
     * fog. However, it is not very useful for high-density fog, as the sky will shine through. When
     * set to `1.0`, the fog color comes completely from the `Sky`. If set to `0.0`, aerial perspective
     * is disabled. Notice that this does not sample the `Sky` directly, but rather the radiance
     * cubemap. The cubemap is sampled at a mipmap level depending on the depth of the rendered pixel;
     * the farther away, the higher the resolution of the sampled mipmap. This results in the actual
     * color being a blurred version of the sky, with more blur closer to the camera. The highest
     * mipmap resolution is used at a depth of `Camera3D.far`.
     *
     * Generated from Godot docs: Environment.set_fog_aerial_perspective
     */
    fun setFogAerialPerspective(aerialPerspective: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogAerialPerspectiveBind, handle, aerialPerspective)
    }

    /**
     * If set above `0.0` (exclusive), blends between the fog's color and the color of the background
     * `Sky`, as read from the radiance cubemap. This has a small performance cost when set above
     * `0.0`. Must have `background_mode` set to `BG_SKY`. This is useful to simulate aerial
     * perspective (https://en.wikipedia.org/wiki/Aerial_perspective) in large scenes with low density
     * fog. However, it is not very useful for high-density fog, as the sky will shine through. When
     * set to `1.0`, the fog color comes completely from the `Sky`. If set to `0.0`, aerial perspective
     * is disabled. Notice that this does not sample the `Sky` directly, but rather the radiance
     * cubemap. The cubemap is sampled at a mipmap level depending on the depth of the rendered pixel;
     * the farther away, the higher the resolution of the sampled mipmap. This results in the actual
     * color being a blurred version of the sky, with more blur closer to the camera. The highest
     * mipmap resolution is used at a depth of `Camera3D.far`.
     *
     * Generated from Godot docs: Environment.get_fog_aerial_perspective
     */
    fun getFogAerialPerspective(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogAerialPerspectiveBind, handle)
    }

    /**
     * The factor to use when affecting the sky with non-volumetric fog. `1.0` means that fog can fully
     * obscure the sky. Lower values reduce the impact of fog on sky rendering, with `0.0` not
     * affecting sky rendering at all. Note: `fog_sky_affect` has no visual effect if
     * `fog_aerial_perspective` is `1.0`.
     *
     * Generated from Godot docs: Environment.set_fog_sky_affect
     */
    fun setFogSkyAffect(skyAffect: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogSkyAffectBind, handle, skyAffect)
    }

    /**
     * The factor to use when affecting the sky with non-volumetric fog. `1.0` means that fog can fully
     * obscure the sky. Lower values reduce the impact of fog on sky rendering, with `0.0` not
     * affecting sky rendering at all. Note: `fog_sky_affect` has no visual effect if
     * `fog_aerial_perspective` is `1.0`.
     *
     * Generated from Godot docs: Environment.get_fog_sky_affect
     */
    fun getFogSkyAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogSkyAffectBind, handle)
    }

    /**
     * The fog depth's intensity curve. A number of presets are available in the Inspector by
     * right-clicking the curve. Only available when `fog_mode` is set to `FOG_MODE_DEPTH`.
     *
     * Generated from Godot docs: Environment.set_fog_depth_curve
     */
    fun setFogDepthCurve(curve: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogDepthCurveBind, handle, curve)
    }

    /**
     * The fog depth's intensity curve. A number of presets are available in the Inspector by
     * right-clicking the curve. Only available when `fog_mode` is set to `FOG_MODE_DEPTH`.
     *
     * Generated from Godot docs: Environment.get_fog_depth_curve
     */
    fun getFogDepthCurve(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogDepthCurveBind, handle)
    }

    /**
     * The fog's depth starting distance from the camera. Only available when `fog_mode` is set to
     * `FOG_MODE_DEPTH`.
     *
     * Generated from Godot docs: Environment.set_fog_depth_begin
     */
    fun setFogDepthBegin(begin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogDepthBeginBind, handle, begin)
    }

    /**
     * The fog's depth starting distance from the camera. Only available when `fog_mode` is set to
     * `FOG_MODE_DEPTH`.
     *
     * Generated from Godot docs: Environment.get_fog_depth_begin
     */
    fun getFogDepthBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogDepthBeginBind, handle)
    }

    /**
     * The fog's depth end distance from the camera. If this value is set to `0`, it will be equal to
     * the current camera's `Camera3D.far` value. Only available when `fog_mode` is set to
     * `FOG_MODE_DEPTH`.
     *
     * Generated from Godot docs: Environment.set_fog_depth_end
     */
    fun setFogDepthEnd(end: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFogDepthEndBind, handle, end)
    }

    /**
     * The fog's depth end distance from the camera. If this value is set to `0`, it will be equal to
     * the current camera's `Camera3D.far` value. Only available when `fog_mode` is set to
     * `FOG_MODE_DEPTH`.
     *
     * Generated from Godot docs: Environment.get_fog_depth_end
     */
    fun getFogDepthEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFogDepthEndBind, handle)
    }

    /**
     * Enables the volumetric fog effect. Volumetric fog uses a screen-aligned froxel buffer to
     * calculate accurate volumetric scattering in the short to medium range. Volumetric fog interacts
     * with `FogVolume`s and lights to calculate localized and global fog. Volumetric fog uses a PBR
     * single-scattering model based on extinction, scattering, and emission which it exposes to users
     * as density, albedo, and emission. Note: Volumetric fog is only supported in the Forward+
     * rendering method, not Mobile or Compatibility.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_enabled
     */
    fun setVolumetricFogEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVolumetricFogEnabledBind, handle, enabled)
    }

    /**
     * Enables the volumetric fog effect. Volumetric fog uses a screen-aligned froxel buffer to
     * calculate accurate volumetric scattering in the short to medium range. Volumetric fog interacts
     * with `FogVolume`s and lights to calculate localized and global fog. Volumetric fog uses a PBR
     * single-scattering model based on extinction, scattering, and emission which it exposes to users
     * as density, albedo, and emission. Note: Volumetric fog is only supported in the Forward+
     * rendering method, not Mobile or Compatibility.
     *
     * Generated from Godot docs: Environment.is_volumetric_fog_enabled
     */
    fun isVolumetricFogEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVolumetricFogEnabledBind, handle)
    }

    /**
     * The emitted light from the volumetric fog. Even with emission, volumetric fog will not cast
     * light onto other surfaces. Emission is useful to establish an ambient color. As the volumetric
     * fog effect uses single-scattering only, fog tends to need a little bit of emission to soften the
     * harsh shadows.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_emission
     */
    fun setVolumetricFogEmission(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setVolumetricFogEmissionBind, handle, color)
    }

    /**
     * The emitted light from the volumetric fog. Even with emission, volumetric fog will not cast
     * light onto other surfaces. Emission is useful to establish an ambient color. As the volumetric
     * fog effect uses single-scattering only, fog tends to need a little bit of emission to soften the
     * harsh shadows.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_emission
     */
    fun getVolumetricFogEmission(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getVolumetricFogEmissionBind, handle)
    }

    /**
     * The `Color` of the volumetric fog when interacting with lights. Mist and fog have an albedo
     * close to `Color(1, 1, 1, 1)` while smoke has a darker albedo.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_albedo
     */
    fun setVolumetricFogAlbedo(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setVolumetricFogAlbedoBind, handle, color)
    }

    /**
     * The `Color` of the volumetric fog when interacting with lights. Mist and fog have an albedo
     * close to `Color(1, 1, 1, 1)` while smoke has a darker albedo.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_albedo
     */
    fun getVolumetricFogAlbedo(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getVolumetricFogAlbedoBind, handle)
    }

    /**
     * The base exponential density of the volumetric fog. Set this to the lowest density you want to
     * have globally. `FogVolume`s can be used to add to or subtract from this density in specific
     * areas. Fog rendering is exponential as in real life. A value of `0.0` disables global volumetric
     * fog while allowing `FogVolume`s to display volumetric fog in specific areas. To make volumetric
     * fog work as a volumetric lighting solution, set `volumetric_fog_density` to the lowest non-zero
     * value (`0.0001`) then increase lights' `Light3D.light_volumetric_fog_energy` to values between
     * `10000` and `100000` to compensate for the very low density.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_density
     */
    fun setVolumetricFogDensity(density: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogDensityBind, handle, density)
    }

    /**
     * The base exponential density of the volumetric fog. Set this to the lowest density you want to
     * have globally. `FogVolume`s can be used to add to or subtract from this density in specific
     * areas. Fog rendering is exponential as in real life. A value of `0.0` disables global volumetric
     * fog while allowing `FogVolume`s to display volumetric fog in specific areas. To make volumetric
     * fog work as a volumetric lighting solution, set `volumetric_fog_density` to the lowest non-zero
     * value (`0.0001`) then increase lights' `Light3D.light_volumetric_fog_energy` to values between
     * `10000` and `100000` to compensate for the very low density.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_density
     */
    fun getVolumetricFogDensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogDensityBind, handle)
    }

    /**
     * The brightness of the emitted light from the volumetric fog.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_emission_energy
     */
    fun setVolumetricFogEmissionEnergy(begin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogEmissionEnergyBind, handle, begin)
    }

    /**
     * The brightness of the emitted light from the volumetric fog.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_emission_energy
     */
    fun getVolumetricFogEmissionEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogEmissionEnergyBind, handle)
    }

    /**
     * The direction of scattered light as it goes through the volumetric fog. A value close to `1.0`
     * means almost all light is scattered forward. A value close to `0.0` means light is scattered
     * equally in all directions. A value close to `-1.0` means light is scattered mostly backward. Fog
     * and mist scatter light slightly forward, while smoke scatters light equally in all directions.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_anisotropy
     */
    fun setVolumetricFogAnisotropy(anisotropy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogAnisotropyBind, handle, anisotropy)
    }

    /**
     * The direction of scattered light as it goes through the volumetric fog. A value close to `1.0`
     * means almost all light is scattered forward. A value close to `0.0` means light is scattered
     * equally in all directions. A value close to `-1.0` means light is scattered mostly backward. Fog
     * and mist scatter light slightly forward, while smoke scatters light equally in all directions.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_anisotropy
     */
    fun getVolumetricFogAnisotropy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogAnisotropyBind, handle)
    }

    /**
     * The distance over which the volumetric fog is computed. Increase to compute fog over a greater
     * range, decrease to add more detail when a long range is not needed. For best quality fog, keep
     * this as low as possible. See also
     * `ProjectSettings.rendering/environment/volumetric_fog/volume_depth`.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_length
     */
    fun setVolumetricFogLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogLengthBind, handle, length)
    }

    /**
     * The distance over which the volumetric fog is computed. Increase to compute fog over a greater
     * range, decrease to add more detail when a long range is not needed. For best quality fog, keep
     * this as low as possible. See also
     * `ProjectSettings.rendering/environment/volumetric_fog/volume_depth`.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_length
     */
    fun getVolumetricFogLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogLengthBind, handle)
    }

    /**
     * The distribution of size down the length of the froxel buffer. A higher value compresses the
     * froxels closer to the camera and places more detail closer to the camera.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_detail_spread
     */
    fun setVolumetricFogDetailSpread(detailSpread: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogDetailSpreadBind, handle, detailSpread)
    }

    /**
     * The distribution of size down the length of the froxel buffer. A higher value compresses the
     * froxels closer to the camera and places more detail closer to the camera.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_detail_spread
     */
    fun getVolumetricFogDetailSpread(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogDetailSpreadBind, handle)
    }

    /**
     * Scales the strength of Global Illumination used in the volumetric fog's albedo color. A value of
     * `0.0` means that Global Illumination will not impact the volumetric fog.
     * `volumetric_fog_gi_inject` has a small performance cost when set above `0.0`. Note: This has no
     * visible effect if `volumetric_fog_density` is `0.0` or if `volumetric_fog_albedo` is a fully
     * black color. Note: Only `VoxelGI` and SDFGI (`Environment.sdfgi_enabled`) are taken into account
     * when using `volumetric_fog_gi_inject`. Global illumination from `LightmapGI`, `ReflectionProbe`
     * and SSIL (see `ssil_enabled`) will be ignored by volumetric fog.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_gi_inject
     */
    fun setVolumetricFogGiInject(giInject: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogGiInjectBind, handle, giInject)
    }

    /**
     * Scales the strength of Global Illumination used in the volumetric fog's albedo color. A value of
     * `0.0` means that Global Illumination will not impact the volumetric fog.
     * `volumetric_fog_gi_inject` has a small performance cost when set above `0.0`. Note: This has no
     * visible effect if `volumetric_fog_density` is `0.0` or if `volumetric_fog_albedo` is a fully
     * black color. Note: Only `VoxelGI` and SDFGI (`Environment.sdfgi_enabled`) are taken into account
     * when using `volumetric_fog_gi_inject`. Global illumination from `LightmapGI`, `ReflectionProbe`
     * and SSIL (see `ssil_enabled`) will be ignored by volumetric fog.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_gi_inject
     */
    fun getVolumetricFogGiInject(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogGiInjectBind, handle)
    }

    /**
     * Scales the strength of ambient light used in the volumetric fog. A value of `0.0` means that
     * ambient light will not impact the volumetric fog. `volumetric_fog_ambient_inject` has a small
     * performance cost when set above `0.0`. Note: This has no visible effect if
     * `volumetric_fog_density` is `0.0` or if `volumetric_fog_albedo` is a fully black color.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_ambient_inject
     */
    fun setVolumetricFogAmbientInject(enabled: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogAmbientInjectBind, handle, enabled)
    }

    /**
     * Scales the strength of ambient light used in the volumetric fog. A value of `0.0` means that
     * ambient light will not impact the volumetric fog. `volumetric_fog_ambient_inject` has a small
     * performance cost when set above `0.0`. Note: This has no visible effect if
     * `volumetric_fog_density` is `0.0` or if `volumetric_fog_albedo` is a fully black color.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_ambient_inject
     */
    fun getVolumetricFogAmbientInject(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogAmbientInjectBind, handle)
    }

    /**
     * The factor to use when affecting the sky with volumetric fog. `1.0` means that volumetric fog
     * can fully obscure the sky. Lower values reduce the impact of volumetric fog on sky rendering,
     * with `0.0` not affecting sky rendering at all. Note: `volumetric_fog_sky_affect` also affects
     * `FogVolume`s, even if `volumetric_fog_density` is `0.0`. If you notice `FogVolume`s are
     * disappearing when looking towards the sky, set `volumetric_fog_sky_affect` to `1.0`.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_sky_affect
     */
    fun setVolumetricFogSkyAffect(skyAffect: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogSkyAffectBind, handle, skyAffect)
    }

    /**
     * The factor to use when affecting the sky with volumetric fog. `1.0` means that volumetric fog
     * can fully obscure the sky. Lower values reduce the impact of volumetric fog on sky rendering,
     * with `0.0` not affecting sky rendering at all. Note: `volumetric_fog_sky_affect` also affects
     * `FogVolume`s, even if `volumetric_fog_density` is `0.0`. If you notice `FogVolume`s are
     * disappearing when looking towards the sky, set `volumetric_fog_sky_affect` to `1.0`.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_sky_affect
     */
    fun getVolumetricFogSkyAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogSkyAffectBind, handle)
    }

    /**
     * Enables temporal reprojection in the volumetric fog. Temporal reprojection blends the current
     * frame's volumetric fog with the last frame's volumetric fog to smooth out jagged edges. The
     * performance cost is minimal; however, it leads to moving `FogVolume`s and `Light3D`s "ghosting"
     * and leaving a trail behind them. When temporal reprojection is enabled, try to avoid moving
     * `FogVolume`s or `Light3D`s too fast. Short-lived dynamic lighting effects should have
     * `Light3D.light_volumetric_fog_energy` set to `0.0` to avoid ghosting.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_temporal_reprojection_enabled
     */
    fun setVolumetricFogTemporalReprojectionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVolumetricFogTemporalReprojectionEnabledBind, handle, enabled)
    }

    /**
     * Enables temporal reprojection in the volumetric fog. Temporal reprojection blends the current
     * frame's volumetric fog with the last frame's volumetric fog to smooth out jagged edges. The
     * performance cost is minimal; however, it leads to moving `FogVolume`s and `Light3D`s "ghosting"
     * and leaving a trail behind them. When temporal reprojection is enabled, try to avoid moving
     * `FogVolume`s or `Light3D`s too fast. Short-lived dynamic lighting effects should have
     * `Light3D.light_volumetric_fog_energy` set to `0.0` to avoid ghosting.
     *
     * Generated from Godot docs: Environment.is_volumetric_fog_temporal_reprojection_enabled
     */
    fun isVolumetricFogTemporalReprojectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVolumetricFogTemporalReprojectionEnabledBind, handle)
    }

    /**
     * The amount by which to blend the last frame with the current frame. A higher number results in
     * smoother volumetric fog, but makes "ghosting" much worse. A lower value reduces ghosting but can
     * result in the per-frame temporal jitter becoming visible.
     *
     * Generated from Godot docs: Environment.set_volumetric_fog_temporal_reprojection_amount
     */
    fun setVolumetricFogTemporalReprojectionAmount(temporalReprojectionAmount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumetricFogTemporalReprojectionAmountBind, handle, temporalReprojectionAmount)
    }

    /**
     * The amount by which to blend the last frame with the current frame. A higher number results in
     * smoother volumetric fog, but makes "ghosting" much worse. A lower value reduces ghosting but can
     * result in the per-frame temporal jitter becoming visible.
     *
     * Generated from Godot docs: Environment.get_volumetric_fog_temporal_reprojection_amount
     */
    fun getVolumetricFogTemporalReprojectionAmount(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumetricFogTemporalReprojectionAmountBind, handle)
    }

    /**
     * If `true`, enables the `adjustment_*` properties provided by this resource. If `false`,
     * modifications to the `adjustment_*` properties will have no effect on the rendered scene.
     *
     * Generated from Godot docs: Environment.set_adjustment_enabled
     */
    fun setAdjustmentEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAdjustmentEnabledBind, handle, enabled)
    }

    /**
     * If `true`, enables the `adjustment_*` properties provided by this resource. If `false`,
     * modifications to the `adjustment_*` properties will have no effect on the rendered scene.
     *
     * Generated from Godot docs: Environment.is_adjustment_enabled
     */
    fun isAdjustmentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAdjustmentEnabledBind, handle)
    }

    /**
     * Applies a simple brightness adjustment to the rendered image after tonemaping. To adjust scene
     * brightness use `tonemap_exposure` instead, which is applied before tonemapping and thus less
     * prone to issues with bright colors. Effective only if `adjustment_enabled` is `true`.
     *
     * Generated from Godot docs: Environment.set_adjustment_brightness
     */
    fun setAdjustmentBrightness(brightness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAdjustmentBrightnessBind, handle, brightness)
    }

    /**
     * Applies a simple brightness adjustment to the rendered image after tonemaping. To adjust scene
     * brightness use `tonemap_exposure` instead, which is applied before tonemapping and thus less
     * prone to issues with bright colors. Effective only if `adjustment_enabled` is `true`.
     *
     * Generated from Godot docs: Environment.get_adjustment_brightness
     */
    fun getAdjustmentBrightness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAdjustmentBrightnessBind, handle)
    }

    /**
     * Increasing `adjustment_contrast` will make dark values darker and bright values brighter. This
     * simple adjustment is applied to the rendered image after tonemaping. When set to a value greater
     * than `1.0`, `adjustment_contrast` is prone to clipping colors that become too bright or too
     * dark. Effective only if `adjustment_enabled` is `true`.
     *
     * Generated from Godot docs: Environment.set_adjustment_contrast
     */
    fun setAdjustmentContrast(contrast: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAdjustmentContrastBind, handle, contrast)
    }

    /**
     * Increasing `adjustment_contrast` will make dark values darker and bright values brighter. This
     * simple adjustment is applied to the rendered image after tonemaping. When set to a value greater
     * than `1.0`, `adjustment_contrast` is prone to clipping colors that become too bright or too
     * dark. Effective only if `adjustment_enabled` is `true`.
     *
     * Generated from Godot docs: Environment.get_adjustment_contrast
     */
    fun getAdjustmentContrast(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAdjustmentContrastBind, handle)
    }

    /**
     * Applies a simple saturation adjustment to the rendered image after tonemaping. When
     * `adjustment_saturation` is set to `0.0`, the rendered image will be fully converted to a
     * grayscale image. Effective only if `adjustment_enabled` is `true`.
     *
     * Generated from Godot docs: Environment.set_adjustment_saturation
     */
    fun setAdjustmentSaturation(saturation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAdjustmentSaturationBind, handle, saturation)
    }

    /**
     * Applies a simple saturation adjustment to the rendered image after tonemaping. When
     * `adjustment_saturation` is set to `0.0`, the rendered image will be fully converted to a
     * grayscale image. Effective only if `adjustment_enabled` is `true`.
     *
     * Generated from Godot docs: Environment.get_adjustment_saturation
     */
    fun getAdjustmentSaturation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAdjustmentSaturationBind, handle)
    }

    /**
     * The `Texture2D` or `Texture3D` lookup table (LUT) to use for the built-in post-process color
     * grading. Can use a `GradientTexture1D` for a 1-dimensional LUT, or a `Texture3D` for a more
     * complex LUT. Effective only if `adjustment_enabled` is `true`. Note: Color correction does not
     * currently support HDR output due to only supporting values in the SDR (0.0 to 1.0) range.
     *
     * Generated from Godot docs: Environment.set_adjustment_color_correction
     */
    fun setAdjustmentColorCorrection(colorCorrection: Texture?) {
        ObjectCalls.ptrcallWithObjectArgs(setAdjustmentColorCorrectionBind, handle, listOf(colorCorrection?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Texture2D` or `Texture3D` lookup table (LUT) to use for the built-in post-process color
     * grading. Can use a `GradientTexture1D` for a 1-dimensional LUT, or a `Texture3D` for a more
     * complex LUT. Effective only if `adjustment_enabled` is `true`. Note: Color correction does not
     * currently support HDR output due to only supporting values in the SDR (0.0 to 1.0) range.
     *
     * Generated from Godot docs: Environment.get_adjustment_color_correction
     */
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
