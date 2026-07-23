package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * Abstract base class for defining the 3D rendering properties of meshes.
 *
 * Generated from Godot docs: BaseMaterial3D
 */
open class BaseMaterial3D(handle: MemorySegment) : Material(handle) {
    var transparency: Long
        @JvmName("transparencyProperty")
        get() = getTransparency()
        @JvmName("setTransparencyProperty")
        set(value) = setTransparency(value)

    var alphaScissorThreshold: Double
        @JvmName("alphaScissorThresholdProperty")
        get() = getAlphaScissorThreshold()
        @JvmName("setAlphaScissorThresholdProperty")
        set(value) = setAlphaScissorThreshold(value)

    var alphaHashScale: Double
        @JvmName("alphaHashScaleProperty")
        get() = getAlphaHashScale()
        @JvmName("setAlphaHashScaleProperty")
        set(value) = setAlphaHashScale(value)

    var alphaAntialiasingMode: Long
        @JvmName("alphaAntialiasingModeProperty")
        get() = getAlphaAntialiasing()
        @JvmName("setAlphaAntialiasingModeProperty")
        set(value) = setAlphaAntialiasing(value)

    var alphaAntialiasingEdge: Double
        @JvmName("alphaAntialiasingEdgeProperty")
        get() = getAlphaAntialiasingEdge()
        @JvmName("setAlphaAntialiasingEdgeProperty")
        set(value) = setAlphaAntialiasingEdge(value)

    var blendMode: Long
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    var cullMode: Long
        @JvmName("cullModeProperty")
        get() = getCullMode()
        @JvmName("setCullModeProperty")
        set(value) = setCullMode(value)

    var depthDrawMode: Long
        @JvmName("depthDrawModeProperty")
        get() = getDepthDrawMode()
        @JvmName("setDepthDrawModeProperty")
        set(value) = setDepthDrawMode(value)

    var noDepthTest: Boolean
        @JvmName("noDepthTestProperty")
        get() = getFlag(0L)
        @JvmName("setNoDepthTestProperty")
        set(value) = setFlag(0L, value)

    var depthTest: Long
        @JvmName("depthTestProperty")
        get() = getDepthTest()
        @JvmName("setDepthTestProperty")
        set(value) = setDepthTest(value)

    var shadingMode: Long
        @JvmName("shadingModeProperty")
        get() = getShadingMode()
        @JvmName("setShadingModeProperty")
        set(value) = setShadingMode(value)

    var diffuseMode: Long
        @JvmName("diffuseModeProperty")
        get() = getDiffuseMode()
        @JvmName("setDiffuseModeProperty")
        set(value) = setDiffuseMode(value)

    var specularMode: Long
        @JvmName("specularModeProperty")
        get() = getSpecularMode()
        @JvmName("setSpecularModeProperty")
        set(value) = setSpecularMode(value)

    var disableAmbientLight: Boolean
        @JvmName("disableAmbientLightProperty")
        get() = getFlag(14L)
        @JvmName("setDisableAmbientLightProperty")
        set(value) = setFlag(14L, value)

    var disableFog: Boolean
        @JvmName("disableFogProperty")
        get() = getFlag(21L)
        @JvmName("setDisableFogProperty")
        set(value) = setFlag(21L, value)

    var disableSpecularOcclusion: Boolean
        @JvmName("disableSpecularOcclusionProperty")
        get() = getFlag(22L)
        @JvmName("setDisableSpecularOcclusionProperty")
        set(value) = setFlag(22L, value)

    var vertexColorUseAsAlbedo: Boolean
        @JvmName("vertexColorUseAsAlbedoProperty")
        get() = getFlag(1L)
        @JvmName("setVertexColorUseAsAlbedoProperty")
        set(value) = setFlag(1L, value)

    var vertexColorIsSrgb: Boolean
        @JvmName("vertexColorIsSrgbProperty")
        get() = getFlag(2L)
        @JvmName("setVertexColorIsSrgbProperty")
        set(value) = setFlag(2L, value)

    var albedoColor: Color
        @JvmName("albedoColorProperty")
        get() = getAlbedo()
        @JvmName("setAlbedoColorProperty")
        set(value) = setAlbedo(value)

    var albedoTexture: Texture2D?
        @JvmName("albedoTextureProperty")
        get() = getTexture(0L)
        @JvmName("setAlbedoTextureProperty")
        set(value) = setTexture(0L, value)

    var albedoTextureForceSrgb: Boolean
        @JvmName("albedoTextureForceSrgbProperty")
        get() = getFlag(12L)
        @JvmName("setAlbedoTextureForceSrgbProperty")
        set(value) = setFlag(12L, value)

    var albedoTextureMsdf: Boolean
        @JvmName("albedoTextureMsdfProperty")
        get() = getFlag(20L)
        @JvmName("setAlbedoTextureMsdfProperty")
        set(value) = setFlag(20L, value)

    var ormTexture: Texture2D?
        @JvmName("ormTextureProperty")
        get() = getTexture(17L)
        @JvmName("setOrmTextureProperty")
        set(value) = setTexture(17L, value)

    var metallic: Double
        @JvmName("metallicProperty")
        get() = getMetallic()
        @JvmName("setMetallicProperty")
        set(value) = setMetallic(value)

    var metallicSpecular: Double
        @JvmName("metallicSpecularProperty")
        get() = getSpecular()
        @JvmName("setMetallicSpecularProperty")
        set(value) = setSpecular(value)

    var metallicTexture: Texture2D?
        @JvmName("metallicTextureProperty")
        get() = getTexture(1L)
        @JvmName("setMetallicTextureProperty")
        set(value) = setTexture(1L, value)

    var metallicTextureChannel: Long
        @JvmName("metallicTextureChannelProperty")
        get() = getMetallicTextureChannel()
        @JvmName("setMetallicTextureChannelProperty")
        set(value) = setMetallicTextureChannel(value)

    var roughness: Double
        @JvmName("roughnessProperty")
        get() = getRoughness()
        @JvmName("setRoughnessProperty")
        set(value) = setRoughness(value)

    var roughnessTexture: Texture2D?
        @JvmName("roughnessTextureProperty")
        get() = getTexture(2L)
        @JvmName("setRoughnessTextureProperty")
        set(value) = setTexture(2L, value)

    var roughnessTextureChannel: Long
        @JvmName("roughnessTextureChannelProperty")
        get() = getRoughnessTextureChannel()
        @JvmName("setRoughnessTextureChannelProperty")
        set(value) = setRoughnessTextureChannel(value)

    var emissionEnabled: Boolean
        @JvmName("emissionEnabledProperty")
        get() = getFeature(0L)
        @JvmName("setEmissionEnabledProperty")
        set(value) = setFeature(0L, value)

    var emission: Color
        @JvmName("emissionProperty")
        get() = getEmission()
        @JvmName("setEmissionProperty")
        set(value) = setEmission(value)

    var emissionEnergyMultiplier: Double
        @JvmName("emissionEnergyMultiplierProperty")
        get() = getEmissionEnergyMultiplier()
        @JvmName("setEmissionEnergyMultiplierProperty")
        set(value) = setEmissionEnergyMultiplier(value)

    var emissionIntensity: Double
        @JvmName("emissionIntensityProperty")
        get() = getEmissionIntensity()
        @JvmName("setEmissionIntensityProperty")
        set(value) = setEmissionIntensity(value)

    var emissionOperator: Long
        @JvmName("emissionOperatorProperty")
        get() = getEmissionOperator()
        @JvmName("setEmissionOperatorProperty")
        set(value) = setEmissionOperator(value)

    var emissionOnUv2: Boolean
        @JvmName("emissionOnUv2Property")
        get() = getFlag(11L)
        @JvmName("setEmissionOnUv2Property")
        set(value) = setFlag(11L, value)

    var emissionTexture: Texture2D?
        @JvmName("emissionTextureProperty")
        get() = getTexture(3L)
        @JvmName("setEmissionTextureProperty")
        set(value) = setTexture(3L, value)

    var normalEnabled: Boolean
        @JvmName("normalEnabledProperty")
        get() = getFeature(1L)
        @JvmName("setNormalEnabledProperty")
        set(value) = setFeature(1L, value)

    var normalScale: Double
        @JvmName("normalScaleProperty")
        get() = getNormalScale()
        @JvmName("setNormalScaleProperty")
        set(value) = setNormalScale(value)

    var normalTexture: Texture2D?
        @JvmName("normalTextureProperty")
        get() = getTexture(4L)
        @JvmName("setNormalTextureProperty")
        set(value) = setTexture(4L, value)

    var bentNormalEnabled: Boolean
        @JvmName("bentNormalEnabledProperty")
        get() = getFeature(12L)
        @JvmName("setBentNormalEnabledProperty")
        set(value) = setFeature(12L, value)

    var bentNormalTexture: Texture2D?
        @JvmName("bentNormalTextureProperty")
        get() = getTexture(18L)
        @JvmName("setBentNormalTextureProperty")
        set(value) = setTexture(18L, value)

    var rimEnabled: Boolean
        @JvmName("rimEnabledProperty")
        get() = getFeature(2L)
        @JvmName("setRimEnabledProperty")
        set(value) = setFeature(2L, value)

    var rim: Double
        @JvmName("rimProperty")
        get() = getRim()
        @JvmName("setRimProperty")
        set(value) = setRim(value)

    var rimTint: Double
        @JvmName("rimTintProperty")
        get() = getRimTint()
        @JvmName("setRimTintProperty")
        set(value) = setRimTint(value)

    var rimTexture: Texture2D?
        @JvmName("rimTextureProperty")
        get() = getTexture(5L)
        @JvmName("setRimTextureProperty")
        set(value) = setTexture(5L, value)

    var clearcoatEnabled: Boolean
        @JvmName("clearcoatEnabledProperty")
        get() = getFeature(3L)
        @JvmName("setClearcoatEnabledProperty")
        set(value) = setFeature(3L, value)

    var clearcoat: Double
        @JvmName("clearcoatProperty")
        get() = getClearcoat()
        @JvmName("setClearcoatProperty")
        set(value) = setClearcoat(value)

    var clearcoatRoughness: Double
        @JvmName("clearcoatRoughnessProperty")
        get() = getClearcoatRoughness()
        @JvmName("setClearcoatRoughnessProperty")
        set(value) = setClearcoatRoughness(value)

    var clearcoatTexture: Texture2D?
        @JvmName("clearcoatTextureProperty")
        get() = getTexture(6L)
        @JvmName("setClearcoatTextureProperty")
        set(value) = setTexture(6L, value)

    var anisotropyEnabled: Boolean
        @JvmName("anisotropyEnabledProperty")
        get() = getFeature(4L)
        @JvmName("setAnisotropyEnabledProperty")
        set(value) = setFeature(4L, value)

    var anisotropy: Double
        @JvmName("anisotropyProperty")
        get() = getAnisotropy()
        @JvmName("setAnisotropyProperty")
        set(value) = setAnisotropy(value)

    var anisotropyFlowmap: Texture2D?
        @JvmName("anisotropyFlowmapProperty")
        get() = getTexture(7L)
        @JvmName("setAnisotropyFlowmapProperty")
        set(value) = setTexture(7L, value)

    var aoEnabled: Boolean
        @JvmName("aoEnabledProperty")
        get() = getFeature(5L)
        @JvmName("setAoEnabledProperty")
        set(value) = setFeature(5L, value)

    var aoLightAffect: Double
        @JvmName("aoLightAffectProperty")
        get() = getAoLightAffect()
        @JvmName("setAoLightAffectProperty")
        set(value) = setAoLightAffect(value)

    var aoTexture: Texture2D?
        @JvmName("aoTextureProperty")
        get() = getTexture(8L)
        @JvmName("setAoTextureProperty")
        set(value) = setTexture(8L, value)

    var aoOnUv2: Boolean
        @JvmName("aoOnUv2Property")
        get() = getFlag(10L)
        @JvmName("setAoOnUv2Property")
        set(value) = setFlag(10L, value)

    var aoTextureChannel: Long
        @JvmName("aoTextureChannelProperty")
        get() = getAoTextureChannel()
        @JvmName("setAoTextureChannelProperty")
        set(value) = setAoTextureChannel(value)

    var heightmapEnabled: Boolean
        @JvmName("heightmapEnabledProperty")
        get() = getFeature(6L)
        @JvmName("setHeightmapEnabledProperty")
        set(value) = setFeature(6L, value)

    var heightmapScale: Double
        @JvmName("heightmapScaleProperty")
        get() = getHeightmapScale()
        @JvmName("setHeightmapScaleProperty")
        set(value) = setHeightmapScale(value)

    var heightmapDeepParallax: Boolean
        @JvmName("heightmapDeepParallaxProperty")
        get() = isHeightmapDeepParallaxEnabled()
        @JvmName("setHeightmapDeepParallaxProperty")
        set(value) = setHeightmapDeepParallax(value)

    var heightmapMinLayers: Int
        @JvmName("heightmapMinLayersProperty")
        get() = getHeightmapDeepParallaxMinLayers()
        @JvmName("setHeightmapMinLayersProperty")
        set(value) = setHeightmapDeepParallaxMinLayers(value)

    var heightmapMaxLayers: Int
        @JvmName("heightmapMaxLayersProperty")
        get() = getHeightmapDeepParallaxMaxLayers()
        @JvmName("setHeightmapMaxLayersProperty")
        set(value) = setHeightmapDeepParallaxMaxLayers(value)

    var heightmapFlipTangent: Boolean
        @JvmName("heightmapFlipTangentProperty")
        get() = getHeightmapDeepParallaxFlipTangent()
        @JvmName("setHeightmapFlipTangentProperty")
        set(value) = setHeightmapDeepParallaxFlipTangent(value)

    var heightmapFlipBinormal: Boolean
        @JvmName("heightmapFlipBinormalProperty")
        get() = getHeightmapDeepParallaxFlipBinormal()
        @JvmName("setHeightmapFlipBinormalProperty")
        set(value) = setHeightmapDeepParallaxFlipBinormal(value)

    var heightmapTexture: Texture2D?
        @JvmName("heightmapTextureProperty")
        get() = getTexture(9L)
        @JvmName("setHeightmapTextureProperty")
        set(value) = setTexture(9L, value)

    var heightmapFlipTexture: Boolean
        @JvmName("heightmapFlipTextureProperty")
        get() = getFlag(17L)
        @JvmName("setHeightmapFlipTextureProperty")
        set(value) = setFlag(17L, value)

    var subsurfScatterEnabled: Boolean
        @JvmName("subsurfScatterEnabledProperty")
        get() = getFeature(7L)
        @JvmName("setSubsurfScatterEnabledProperty")
        set(value) = setFeature(7L, value)

    var subsurfScatterStrength: Double
        @JvmName("subsurfScatterStrengthProperty")
        get() = getSubsurfaceScatteringStrength()
        @JvmName("setSubsurfScatterStrengthProperty")
        set(value) = setSubsurfaceScatteringStrength(value)

    var subsurfScatterSkinMode: Boolean
        @JvmName("subsurfScatterSkinModeProperty")
        get() = getFlag(18L)
        @JvmName("setSubsurfScatterSkinModeProperty")
        set(value) = setFlag(18L, value)

    var subsurfScatterTexture: Texture2D?
        @JvmName("subsurfScatterTextureProperty")
        get() = getTexture(10L)
        @JvmName("setSubsurfScatterTextureProperty")
        set(value) = setTexture(10L, value)

    var subsurfScatterTransmittanceEnabled: Boolean
        @JvmName("subsurfScatterTransmittanceEnabledProperty")
        get() = getFeature(8L)
        @JvmName("setSubsurfScatterTransmittanceEnabledProperty")
        set(value) = setFeature(8L, value)

    var subsurfScatterTransmittanceColor: Color
        @JvmName("subsurfScatterTransmittanceColorProperty")
        get() = getTransmittanceColor()
        @JvmName("setSubsurfScatterTransmittanceColorProperty")
        set(value) = setTransmittanceColor(value)

    var subsurfScatterTransmittanceTexture: Texture2D?
        @JvmName("subsurfScatterTransmittanceTextureProperty")
        get() = getTexture(11L)
        @JvmName("setSubsurfScatterTransmittanceTextureProperty")
        set(value) = setTexture(11L, value)

    var subsurfScatterTransmittanceDepth: Double
        @JvmName("subsurfScatterTransmittanceDepthProperty")
        get() = getTransmittanceDepth()
        @JvmName("setSubsurfScatterTransmittanceDepthProperty")
        set(value) = setTransmittanceDepth(value)

    var subsurfScatterTransmittanceBoost: Double
        @JvmName("subsurfScatterTransmittanceBoostProperty")
        get() = getTransmittanceBoost()
        @JvmName("setSubsurfScatterTransmittanceBoostProperty")
        set(value) = setTransmittanceBoost(value)

    var backlightEnabled: Boolean
        @JvmName("backlightEnabledProperty")
        get() = getFeature(9L)
        @JvmName("setBacklightEnabledProperty")
        set(value) = setFeature(9L, value)

    var backlight: Color
        @JvmName("backlightProperty")
        get() = getBacklight()
        @JvmName("setBacklightProperty")
        set(value) = setBacklight(value)

    var backlightTexture: Texture2D?
        @JvmName("backlightTextureProperty")
        get() = getTexture(12L)
        @JvmName("setBacklightTextureProperty")
        set(value) = setTexture(12L, value)

    var refractionEnabled: Boolean
        @JvmName("refractionEnabledProperty")
        get() = getFeature(10L)
        @JvmName("setRefractionEnabledProperty")
        set(value) = setFeature(10L, value)

    var refractionScale: Double
        @JvmName("refractionScaleProperty")
        get() = getRefraction()
        @JvmName("setRefractionScaleProperty")
        set(value) = setRefraction(value)

    var refractionTexture: Texture2D?
        @JvmName("refractionTextureProperty")
        get() = getTexture(13L)
        @JvmName("setRefractionTextureProperty")
        set(value) = setTexture(13L, value)

    var refractionTextureChannel: Long
        @JvmName("refractionTextureChannelProperty")
        get() = getRefractionTextureChannel()
        @JvmName("setRefractionTextureChannelProperty")
        set(value) = setRefractionTextureChannel(value)

    var detailEnabled: Boolean
        @JvmName("detailEnabledProperty")
        get() = getFeature(11L)
        @JvmName("setDetailEnabledProperty")
        set(value) = setFeature(11L, value)

    var detailMask: Texture2D?
        @JvmName("detailMaskProperty")
        get() = getTexture(14L)
        @JvmName("setDetailMaskProperty")
        set(value) = setTexture(14L, value)

    var detailBlendMode: Long
        @JvmName("detailBlendModeProperty")
        get() = getDetailBlendMode()
        @JvmName("setDetailBlendModeProperty")
        set(value) = setDetailBlendMode(value)

    var detailUvLayer: Long
        @JvmName("detailUvLayerProperty")
        get() = getDetailUv()
        @JvmName("setDetailUvLayerProperty")
        set(value) = setDetailUv(value)

    var detailAlbedo: Texture2D?
        @JvmName("detailAlbedoProperty")
        get() = getTexture(15L)
        @JvmName("setDetailAlbedoProperty")
        set(value) = setTexture(15L, value)

    var detailNormal: Texture2D?
        @JvmName("detailNormalProperty")
        get() = getTexture(16L)
        @JvmName("setDetailNormalProperty")
        set(value) = setTexture(16L, value)

    var uv1Scale: Vector3
        @JvmName("uv1ScaleProperty")
        get() = getUv1Scale()
        @JvmName("setUv1ScaleProperty")
        set(value) = setUv1Scale(value)

    var uv1Offset: Vector3
        @JvmName("uv1OffsetProperty")
        get() = getUv1Offset()
        @JvmName("setUv1OffsetProperty")
        set(value) = setUv1Offset(value)

    var uv1Triplanar: Boolean
        @JvmName("uv1TriplanarProperty")
        get() = getFlag(6L)
        @JvmName("setUv1TriplanarProperty")
        set(value) = setFlag(6L, value)

    var uv1TriplanarSharpness: Double
        @JvmName("uv1TriplanarSharpnessProperty")
        get() = getUv1TriplanarBlendSharpness()
        @JvmName("setUv1TriplanarSharpnessProperty")
        set(value) = setUv1TriplanarBlendSharpness(value)

    var uv1WorldTriplanar: Boolean
        @JvmName("uv1WorldTriplanarProperty")
        get() = getFlag(8L)
        @JvmName("setUv1WorldTriplanarProperty")
        set(value) = setFlag(8L, value)

    var uv2Scale: Vector3
        @JvmName("uv2ScaleProperty")
        get() = getUv2Scale()
        @JvmName("setUv2ScaleProperty")
        set(value) = setUv2Scale(value)

    var uv2Offset: Vector3
        @JvmName("uv2OffsetProperty")
        get() = getUv2Offset()
        @JvmName("setUv2OffsetProperty")
        set(value) = setUv2Offset(value)

    var uv2Triplanar: Boolean
        @JvmName("uv2TriplanarProperty")
        get() = getFlag(7L)
        @JvmName("setUv2TriplanarProperty")
        set(value) = setFlag(7L, value)

    var uv2TriplanarSharpness: Double
        @JvmName("uv2TriplanarSharpnessProperty")
        get() = getUv2TriplanarBlendSharpness()
        @JvmName("setUv2TriplanarSharpnessProperty")
        set(value) = setUv2TriplanarBlendSharpness(value)

    var uv2WorldTriplanar: Boolean
        @JvmName("uv2WorldTriplanarProperty")
        get() = getFlag(9L)
        @JvmName("setUv2WorldTriplanarProperty")
        set(value) = setFlag(9L, value)

    var textureFilter: Long
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var textureRepeat: Boolean
        @JvmName("textureRepeatProperty")
        get() = getFlag(16L)
        @JvmName("setTextureRepeatProperty")
        set(value) = setFlag(16L, value)

    var disableReceiveShadows: Boolean
        @JvmName("disableReceiveShadowsProperty")
        get() = getFlag(13L)
        @JvmName("setDisableReceiveShadowsProperty")
        set(value) = setFlag(13L, value)

    var shadowToOpacity: Boolean
        @JvmName("shadowToOpacityProperty")
        get() = getFlag(15L)
        @JvmName("setShadowToOpacityProperty")
        set(value) = setFlag(15L, value)

    var billboardMode: Long
        @JvmName("billboardModeProperty")
        get() = getBillboardMode()
        @JvmName("setBillboardModeProperty")
        set(value) = setBillboardMode(value)

    var billboardKeepScale: Boolean
        @JvmName("billboardKeepScaleProperty")
        get() = getFlag(5L)
        @JvmName("setBillboardKeepScaleProperty")
        set(value) = setFlag(5L, value)

    var particlesAnimHFrames: Int
        @JvmName("particlesAnimHFramesProperty")
        get() = getParticlesAnimHFrames()
        @JvmName("setParticlesAnimHFramesProperty")
        set(value) = setParticlesAnimHFrames(value)

    var particlesAnimVFrames: Int
        @JvmName("particlesAnimVFramesProperty")
        get() = getParticlesAnimVFrames()
        @JvmName("setParticlesAnimVFramesProperty")
        set(value) = setParticlesAnimVFrames(value)

    var particlesAnimLoop: Boolean
        @JvmName("particlesAnimLoopProperty")
        get() = getParticlesAnimLoop()
        @JvmName("setParticlesAnimLoopProperty")
        set(value) = setParticlesAnimLoop(value)

    var grow: Boolean
        @JvmName("growProperty")
        get() = isGrowEnabled()
        @JvmName("setGrowProperty")
        set(value) = setGrowEnabled(value)

    var growAmount: Double
        @JvmName("growAmountProperty")
        get() = getGrow()
        @JvmName("setGrowAmountProperty")
        set(value) = setGrow(value)

    var fixedSize: Boolean
        @JvmName("fixedSizeProperty")
        get() = getFlag(4L)
        @JvmName("setFixedSizeProperty")
        set(value) = setFlag(4L, value)

    var usePointSize: Boolean
        @JvmName("usePointSizeProperty")
        get() = getFlag(3L)
        @JvmName("setUsePointSizeProperty")
        set(value) = setFlag(3L, value)

    var pointSize: Double
        @JvmName("pointSizeProperty")
        get() = getPointSize()
        @JvmName("setPointSizeProperty")
        set(value) = setPointSize(value)

    var useParticleTrails: Boolean
        @JvmName("useParticleTrailsProperty")
        get() = getFlag(19L)
        @JvmName("setUseParticleTrailsProperty")
        set(value) = setFlag(19L, value)

    var useZClipScale: Boolean
        @JvmName("useZClipScaleProperty")
        get() = getFlag(23L)
        @JvmName("setUseZClipScaleProperty")
        set(value) = setFlag(23L, value)

    var zClipScale: Double
        @JvmName("zClipScaleProperty")
        get() = getZClipScale()
        @JvmName("setZClipScaleProperty")
        set(value) = setZClipScale(value)

    var useFovOverride: Boolean
        @JvmName("useFovOverrideProperty")
        get() = getFlag(24L)
        @JvmName("setUseFovOverrideProperty")
        set(value) = setFlag(24L, value)

    var fovOverride: Double
        @JvmName("fovOverrideProperty")
        get() = getFovOverride()
        @JvmName("setFovOverrideProperty")
        set(value) = setFovOverride(value)

    var proximityFadeEnabled: Boolean
        @JvmName("proximityFadeEnabledProperty")
        get() = isProximityFadeEnabled()
        @JvmName("setProximityFadeEnabledProperty")
        set(value) = setProximityFadeEnabled(value)

    var proximityFadeDistance: Double
        @JvmName("proximityFadeDistanceProperty")
        get() = getProximityFadeDistance()
        @JvmName("setProximityFadeDistanceProperty")
        set(value) = setProximityFadeDistance(value)

    var msdfPixelRange: Double
        @JvmName("msdfPixelRangeProperty")
        get() = getMsdfPixelRange()
        @JvmName("setMsdfPixelRangeProperty")
        set(value) = setMsdfPixelRange(value)

    var msdfOutlineSize: Double
        @JvmName("msdfOutlineSizeProperty")
        get() = getMsdfOutlineSize()
        @JvmName("setMsdfOutlineSizeProperty")
        set(value) = setMsdfOutlineSize(value)

    var distanceFadeMode: Long
        @JvmName("distanceFadeModeProperty")
        get() = getDistanceFade()
        @JvmName("setDistanceFadeModeProperty")
        set(value) = setDistanceFade(value)

    var distanceFadeMinDistance: Double
        @JvmName("distanceFadeMinDistanceProperty")
        get() = getDistanceFadeMinDistance()
        @JvmName("setDistanceFadeMinDistanceProperty")
        set(value) = setDistanceFadeMinDistance(value)

    var distanceFadeMaxDistance: Double
        @JvmName("distanceFadeMaxDistanceProperty")
        get() = getDistanceFadeMaxDistance()
        @JvmName("setDistanceFadeMaxDistanceProperty")
        set(value) = setDistanceFadeMaxDistance(value)

    var stencilMode: Long
        @JvmName("stencilModeProperty")
        get() = getStencilMode()
        @JvmName("setStencilModeProperty")
        set(value) = setStencilMode(value)

    var stencilFlags: Int
        @JvmName("stencilFlagsProperty")
        get() = getStencilFlags()
        @JvmName("setStencilFlagsProperty")
        set(value) = setStencilFlags(value)

    var stencilCompare: Long
        @JvmName("stencilCompareProperty")
        get() = getStencilCompare()
        @JvmName("setStencilCompareProperty")
        set(value) = setStencilCompare(value)

    var stencilReference: Int
        @JvmName("stencilReferenceProperty")
        get() = getStencilReference()
        @JvmName("setStencilReferenceProperty")
        set(value) = setStencilReference(value)

    var stencilColor: Color
        @JvmName("stencilColorProperty")
        get() = getStencilEffectColor()
        @JvmName("setStencilColorProperty")
        set(value) = setStencilEffectColor(value)

    var stencilOutlineThickness: Double
        @JvmName("stencilOutlineThicknessProperty")
        get() = getStencilEffectOutlineThickness()
        @JvmName("setStencilOutlineThicknessProperty")
        set(value) = setStencilEffectOutlineThickness(value)

    /**
     * The material's base color. Note: If `detail_enabled` is `true` and a `detail_albedo` texture is
     * specified, `albedo_color` will not modulate the detail texture. This can be used to color
     * partial areas of a material by not specifying an albedo texture and using a transparent
     * `detail_albedo` texture instead.
     *
     * Generated from Godot docs: BaseMaterial3D.set_albedo
     */
    fun setAlbedo(albedo: Color) {
        ObjectCalls.ptrcallWithColorArg(setAlbedoBind, handle, albedo)
    }

    /**
     * The material's base color. Note: If `detail_enabled` is `true` and a `detail_albedo` texture is
     * specified, `albedo_color` will not modulate the detail texture. This can be used to color
     * partial areas of a material by not specifying an albedo texture and using a transparent
     * `detail_albedo` texture instead.
     *
     * Generated from Godot docs: BaseMaterial3D.get_albedo
     */
    fun getAlbedo(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getAlbedoBind, handle)
    }

    /**
     * The material's transparency mode. Some transparency modes will disable shadow casting. Any
     * transparency mode other than `TRANSPARENCY_DISABLED` has a greater performance impact compared
     * to opaque rendering. See also `blend_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_transparency
     */
    fun setTransparency(transparency: Long) {
        ObjectCalls.ptrcallWithLongArg(setTransparencyBind, handle, transparency)
    }

    /**
     * The material's transparency mode. Some transparency modes will disable shadow casting. Any
     * transparency mode other than `TRANSPARENCY_DISABLED` has a greater performance impact compared
     * to opaque rendering. See also `blend_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_transparency
     */
    fun getTransparency(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTransparencyBind, handle)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: BaseMaterial3D.set_alpha_antialiasing
     */
    fun setAlphaAntialiasing(alphaAa: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaAntialiasingBind, handle, alphaAa)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: BaseMaterial3D.get_alpha_antialiasing
     */
    fun getAlphaAntialiasing(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaAntialiasingBind, handle)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: BaseMaterial3D.set_alpha_antialiasing_edge
     */
    fun setAlphaAntialiasingEdge(edge: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaAntialiasingEdgeBind, handle, edge)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: BaseMaterial3D.get_alpha_antialiasing_edge
     */
    fun getAlphaAntialiasingEdge(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaAntialiasingEdgeBind, handle)
    }

    /**
     * Sets whether the shading takes place, per-pixel, per-vertex or unshaded. Per-vertex lighting is
     * faster, making it the best choice for mobile applications, however it looks considerably worse
     * than per-pixel. Unshaded rendering is the fastest, but disables all interactions with lights.
     *
     * Generated from Godot docs: BaseMaterial3D.set_shading_mode
     */
    fun setShadingMode(shadingMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setShadingModeBind, handle, shadingMode)
    }

    /**
     * Sets whether the shading takes place, per-pixel, per-vertex or unshaded. Per-vertex lighting is
     * faster, making it the best choice for mobile applications, however it looks considerably worse
     * than per-pixel. Unshaded rendering is the fastest, but disables all interactions with lights.
     *
     * Generated from Godot docs: BaseMaterial3D.get_shading_mode
     */
    fun getShadingMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShadingModeBind, handle)
    }

    /**
     * Adjusts the strength of specular reflections. Specular reflections are composed of scene
     * reflections and the specular lobe which is the bright spot that is reflected from light sources.
     * When set to `0.0`, no specular reflections will be visible. This differs from the
     * `SPECULAR_DISABLED` `SpecularMode` as `SPECULAR_DISABLED` only applies to the specular lobe from
     * the light source. Note: Unlike `metallic`, this is not energy-conserving, so it should be left
     * at `0.5` in most cases. See also `roughness`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_specular
     */
    fun setSpecular(specular: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpecularBind, handle, specular)
    }

    /**
     * Adjusts the strength of specular reflections. Specular reflections are composed of scene
     * reflections and the specular lobe which is the bright spot that is reflected from light sources.
     * When set to `0.0`, no specular reflections will be visible. This differs from the
     * `SPECULAR_DISABLED` `SpecularMode` as `SPECULAR_DISABLED` only applies to the specular lobe from
     * the light source. Note: Unlike `metallic`, this is not energy-conserving, so it should be left
     * at `0.5` in most cases. See also `roughness`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_specular
     */
    fun getSpecular(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpecularBind, handle)
    }

    /**
     * A high value makes the material appear more like a metal. Non-metals use their albedo as the
     * diffuse color and add diffuse to the specular reflection. With non-metals, the reflection
     * appears on top of the albedo color. Metals use their albedo as a multiplier to the specular
     * reflection and set the diffuse color to black resulting in a tinted reflection. Materials work
     * better when fully metal or fully non-metal, values between `0` and `1` should only be used for
     * blending between metal and non-metal sections. To alter the amount of reflection use
     * `roughness`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_metallic
     */
    fun setMetallic(metallic: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMetallicBind, handle, metallic)
    }

    /**
     * A high value makes the material appear more like a metal. Non-metals use their albedo as the
     * diffuse color and add diffuse to the specular reflection. With non-metals, the reflection
     * appears on top of the albedo color. Metals use their albedo as a multiplier to the specular
     * reflection and set the diffuse color to black resulting in a tinted reflection. Materials work
     * better when fully metal or fully non-metal, values between `0` and `1` should only be used for
     * blending between metal and non-metal sections. To alter the amount of reflection use
     * `roughness`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_metallic
     */
    fun getMetallic(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMetallicBind, handle)
    }

    /**
     * Surface reflection. A value of `0` represents a perfect mirror while a value of `1` completely
     * blurs the reflection. See also `metallic`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_roughness
     */
    fun setRoughness(roughness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRoughnessBind, handle, roughness)
    }

    /**
     * Surface reflection. A value of `0` represents a perfect mirror while a value of `1` completely
     * blurs the reflection. See also `metallic`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_roughness
     */
    fun getRoughness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRoughnessBind, handle)
    }

    /**
     * The emitted light's color. See `emission_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_emission
     */
    fun setEmission(emission: Color) {
        ObjectCalls.ptrcallWithColorArg(setEmissionBind, handle, emission)
    }

    /**
     * The emitted light's color. See `emission_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_emission
     */
    fun getEmission(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getEmissionBind, handle)
    }

    /**
     * Multiplier for emitted light. See `emission_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_emission_energy_multiplier
     */
    fun setEmissionEnergyMultiplier(emissionEnergyMultiplier: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionEnergyMultiplierBind, handle, emissionEnergyMultiplier)
    }

    /**
     * Multiplier for emitted light. See `emission_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_emission_energy_multiplier
     */
    fun getEmissionEnergyMultiplier(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionEnergyMultiplierBind, handle)
    }

    /**
     * Luminance of emitted light, measured in nits (candela per square meter). Only available when
     * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is enabled. The default
     * is roughly equivalent to an indoor lightbulb.
     *
     * Generated from Godot docs: BaseMaterial3D.set_emission_intensity
     */
    fun setEmissionIntensity(emissionEnergyMultiplier: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionIntensityBind, handle, emissionEnergyMultiplier)
    }

    /**
     * Luminance of emitted light, measured in nits (candela per square meter). Only available when
     * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is enabled. The default
     * is roughly equivalent to an indoor lightbulb.
     *
     * Generated from Godot docs: BaseMaterial3D.get_emission_intensity
     */
    fun getEmissionIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionIntensityBind, handle)
    }

    /**
     * The strength of the normal map's effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_normal_scale
     */
    fun setNormalScale(normalScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNormalScaleBind, handle, normalScale)
    }

    /**
     * The strength of the normal map's effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_normal_scale
     */
    fun getNormalScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNormalScaleBind, handle)
    }

    /**
     * Sets the strength of the rim lighting effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_rim
     */
    fun setRim(rim: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRimBind, handle, rim)
    }

    /**
     * Sets the strength of the rim lighting effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_rim
     */
    fun getRim(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRimBind, handle)
    }

    /**
     * The amount of to blend light and albedo color when rendering rim effect. If `0` the light color
     * is used, while `1` means albedo color is used. An intermediate value generally works best.
     *
     * Generated from Godot docs: BaseMaterial3D.set_rim_tint
     */
    fun setRimTint(rimTint: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRimTintBind, handle, rimTint)
    }

    /**
     * The amount of to blend light and albedo color when rendering rim effect. If `0` the light color
     * is used, while `1` means albedo color is used. An intermediate value generally works best.
     *
     * Generated from Godot docs: BaseMaterial3D.get_rim_tint
     */
    fun getRimTint(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRimTintBind, handle)
    }

    /**
     * Sets the strength of the clearcoat effect. Setting to `0` looks the same as disabling the
     * clearcoat effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_clearcoat
     */
    fun setClearcoat(clearcoat: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setClearcoatBind, handle, clearcoat)
    }

    /**
     * Sets the strength of the clearcoat effect. Setting to `0` looks the same as disabling the
     * clearcoat effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_clearcoat
     */
    fun getClearcoat(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getClearcoatBind, handle)
    }

    /**
     * Sets the roughness of the clearcoat pass. A higher value results in a rougher clearcoat while a
     * lower value results in a smoother clearcoat.
     *
     * Generated from Godot docs: BaseMaterial3D.set_clearcoat_roughness
     */
    fun setClearcoatRoughness(clearcoatRoughness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setClearcoatRoughnessBind, handle, clearcoatRoughness)
    }

    /**
     * Sets the roughness of the clearcoat pass. A higher value results in a rougher clearcoat while a
     * lower value results in a smoother clearcoat.
     *
     * Generated from Godot docs: BaseMaterial3D.get_clearcoat_roughness
     */
    fun getClearcoatRoughness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getClearcoatRoughnessBind, handle)
    }

    /**
     * The strength of the anisotropy effect. This is multiplied by `anisotropy_flowmap`'s alpha
     * channel if a texture is defined there and the texture contains an alpha channel.
     *
     * Generated from Godot docs: BaseMaterial3D.set_anisotropy
     */
    fun setAnisotropy(anisotropy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAnisotropyBind, handle, anisotropy)
    }

    /**
     * The strength of the anisotropy effect. This is multiplied by `anisotropy_flowmap`'s alpha
     * channel if a texture is defined there and the texture contains an alpha channel.
     *
     * Generated from Godot docs: BaseMaterial3D.get_anisotropy
     */
    fun getAnisotropy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAnisotropyBind, handle)
    }

    /**
     * The heightmap scale to use for the parallax effect (see `heightmap_enabled`). The default value
     * is tuned so that the highest point (value = 255) appears to be 5 cm higher than the lowest point
     * (value = 0). Higher values result in a deeper appearance, but may result in artifacts appearing
     * when looking at the material from oblique angles, especially when the camera moves. Negative
     * values can be used to invert the parallax effect, but this is different from inverting the
     * texture using `heightmap_flip_texture` as the material will also appear to be "closer" to the
     * camera. In most cases, `heightmap_scale` should be kept to a positive value. Note: If the height
     * map effect looks strange regardless of this value, try adjusting `heightmap_flip_binormal` and
     * `heightmap_flip_tangent`. See also `heightmap_texture` for recommendations on authoring
     * heightmap textures, as the way the heightmap texture is authored affects how `heightmap_scale`
     * behaves.
     *
     * Generated from Godot docs: BaseMaterial3D.set_heightmap_scale
     */
    fun setHeightmapScale(heightmapScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightmapScaleBind, handle, heightmapScale)
    }

    /**
     * The heightmap scale to use for the parallax effect (see `heightmap_enabled`). The default value
     * is tuned so that the highest point (value = 255) appears to be 5 cm higher than the lowest point
     * (value = 0). Higher values result in a deeper appearance, but may result in artifacts appearing
     * when looking at the material from oblique angles, especially when the camera moves. Negative
     * values can be used to invert the parallax effect, but this is different from inverting the
     * texture using `heightmap_flip_texture` as the material will also appear to be "closer" to the
     * camera. In most cases, `heightmap_scale` should be kept to a positive value. Note: If the height
     * map effect looks strange regardless of this value, try adjusting `heightmap_flip_binormal` and
     * `heightmap_flip_tangent`. See also `heightmap_texture` for recommendations on authoring
     * heightmap textures, as the way the heightmap texture is authored affects how `heightmap_scale`
     * behaves.
     *
     * Generated from Godot docs: BaseMaterial3D.get_heightmap_scale
     */
    fun getHeightmapScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightmapScaleBind, handle)
    }

    /**
     * The strength of the subsurface scattering effect. The depth of the effect is also controlled by
     * `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_scale`, which
     * is set globally.
     *
     * Generated from Godot docs: BaseMaterial3D.set_subsurface_scattering_strength
     */
    fun setSubsurfaceScatteringStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSubsurfaceScatteringStrengthBind, handle, strength)
    }

    /**
     * The strength of the subsurface scattering effect. The depth of the effect is also controlled by
     * `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_scale`, which
     * is set globally.
     *
     * Generated from Godot docs: BaseMaterial3D.get_subsurface_scattering_strength
     */
    fun getSubsurfaceScatteringStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSubsurfaceScatteringStrengthBind, handle)
    }

    /**
     * The color to multiply the subsurface scattering transmittance effect with. Ignored if
     * `subsurf_scatter_skin_mode` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_transmittance_color
     */
    fun setTransmittanceColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setTransmittanceColorBind, handle, color)
    }

    /**
     * The color to multiply the subsurface scattering transmittance effect with. Ignored if
     * `subsurf_scatter_skin_mode` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_transmittance_color
     */
    fun getTransmittanceColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTransmittanceColorBind, handle)
    }

    /**
     * The depth of the subsurface scattering transmittance effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_transmittance_depth
     */
    fun setTransmittanceDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTransmittanceDepthBind, handle, depth)
    }

    /**
     * The depth of the subsurface scattering transmittance effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_transmittance_depth
     */
    fun getTransmittanceDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTransmittanceDepthBind, handle)
    }

    /**
     * The intensity of the subsurface scattering transmittance effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_transmittance_boost
     */
    fun setTransmittanceBoost(boost: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTransmittanceBoostBind, handle, boost)
    }

    /**
     * The intensity of the subsurface scattering transmittance effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_transmittance_boost
     */
    fun getTransmittanceBoost(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTransmittanceBoostBind, handle)
    }

    /**
     * The color used by the backlight effect. Represents the light passing through an object.
     *
     * Generated from Godot docs: BaseMaterial3D.set_backlight
     */
    fun setBacklight(backlight: Color) {
        ObjectCalls.ptrcallWithColorArg(setBacklightBind, handle, backlight)
    }

    /**
     * The color used by the backlight effect. Represents the light passing through an object.
     *
     * Generated from Godot docs: BaseMaterial3D.get_backlight
     */
    fun getBacklight(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBacklightBind, handle)
    }

    /**
     * The strength of the refraction effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_refraction
     */
    fun setRefraction(refraction: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRefractionBind, handle, refraction)
    }

    /**
     * The strength of the refraction effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_refraction
     */
    fun getRefraction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRefractionBind, handle)
    }

    /**
     * The point size in pixels. See `use_point_size`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_point_size
     */
    fun setPointSize(pointSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPointSizeBind, handle, pointSize)
    }

    /**
     * The point size in pixels. See `use_point_size`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_point_size
     */
    fun getPointSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPointSizeBind, handle)
    }

    /**
     * Specifies whether to use `UV` or `UV2` for the detail layer.
     *
     * Generated from Godot docs: BaseMaterial3D.set_detail_uv
     */
    fun setDetailUv(detailUv: Long) {
        ObjectCalls.ptrcallWithLongArg(setDetailUvBind, handle, detailUv)
    }

    /**
     * Specifies whether to use `UV` or `UV2` for the detail layer.
     *
     * Generated from Godot docs: BaseMaterial3D.get_detail_uv
     */
    fun getDetailUv(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDetailUvBind, handle)
    }

    /**
     * The material's blend mode. Note: Values other than `Mix` force the object into the transparent
     * pipeline.
     *
     * Generated from Godot docs: BaseMaterial3D.set_blend_mode
     */
    fun setBlendMode(blendMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, handle, blendMode)
    }

    /**
     * The material's blend mode. Note: Values other than `Mix` force the object into the transparent
     * pipeline.
     *
     * Generated from Godot docs: BaseMaterial3D.get_blend_mode
     */
    fun getBlendMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, handle)
    }

    /**
     * Determines when depth rendering takes place. See also `transparency`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_depth_draw_mode
     */
    fun setDepthDrawMode(depthDrawMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDepthDrawModeBind, handle, depthDrawMode)
    }

    /**
     * Determines when depth rendering takes place. See also `transparency`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_depth_draw_mode
     */
    fun getDepthDrawMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDepthDrawModeBind, handle)
    }

    /**
     * Determines which comparison operator is used when testing depth. Note: Changing `depth_test` to
     * a non-default value only has a visible effect when used on a transparent material, or a material
     * that has `depth_draw_mode` set to `DEPTH_DRAW_DISABLED`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_depth_test
     */
    fun setDepthTest(depthTest: Long) {
        ObjectCalls.ptrcallWithLongArg(setDepthTestBind, handle, depthTest)
    }

    /**
     * Determines which comparison operator is used when testing depth. Note: Changing `depth_test` to
     * a non-default value only has a visible effect when used on a transparent material, or a material
     * that has `depth_draw_mode` set to `DEPTH_DRAW_DISABLED`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_depth_test
     */
    fun getDepthTest(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDepthTestBind, handle)
    }

    /**
     * Determines which side of the triangle to cull depending on whether the triangle faces towards or
     * away from the camera.
     *
     * Generated from Godot docs: BaseMaterial3D.set_cull_mode
     */
    fun setCullMode(cullMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCullModeBind, handle, cullMode)
    }

    /**
     * Determines which side of the triangle to cull depending on whether the triangle faces towards or
     * away from the camera.
     *
     * Generated from Godot docs: BaseMaterial3D.get_cull_mode
     */
    fun getCullMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCullModeBind, handle)
    }

    /**
     * The algorithm used for diffuse light scattering.
     *
     * Generated from Godot docs: BaseMaterial3D.set_diffuse_mode
     */
    fun setDiffuseMode(diffuseMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDiffuseModeBind, handle, diffuseMode)
    }

    /**
     * The algorithm used for diffuse light scattering.
     *
     * Generated from Godot docs: BaseMaterial3D.get_diffuse_mode
     */
    fun getDiffuseMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDiffuseModeBind, handle)
    }

    /**
     * The method for rendering the specular blob. Note: `specular_mode` only applies to the specular
     * blob. It does not affect specular reflections from the sky, screen-space reflections, `VoxelGI`,
     * SDFGI or `ReflectionProbe`s. To disable reflections from these sources as well, set
     * `metallic_specular` to `0.0` instead.
     *
     * Generated from Godot docs: BaseMaterial3D.set_specular_mode
     */
    fun setSpecularMode(specularMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSpecularModeBind, handle, specularMode)
    }

    /**
     * The method for rendering the specular blob. Note: `specular_mode` only applies to the specular
     * blob. It does not affect specular reflections from the sky, screen-space reflections, `VoxelGI`,
     * SDFGI or `ReflectionProbe`s. To disable reflections from these sources as well, set
     * `metallic_specular` to `0.0` instead.
     *
     * Generated from Godot docs: BaseMaterial3D.get_specular_mode
     */
    fun getSpecularMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSpecularModeBind, handle)
    }

    /**
     * If `true`, the vertex color is used as albedo color.
     *
     * Generated from Godot docs: BaseMaterial3D.set_flag
     */
    fun setFlag(flag: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagBind, handle, flag, enable)
    }

    /**
     * If `true`, the vertex color is used as albedo color.
     *
     * Generated from Godot docs: BaseMaterial3D.get_flag
     */
    fun getFlag(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagBind, handle, flag)
    }

    /**
     * Filter flags for the texture. Note: `heightmap_texture` is always sampled with linear filtering,
     * even if nearest-neighbor filtering is selected here. This is to ensure the heightmap effect
     * looks as intended. If you need sharper height transitions between pixels, resize the heightmap
     * texture in an image editor with nearest-neighbor filtering.
     *
     * Generated from Godot docs: BaseMaterial3D.set_texture_filter
     */
    fun setTextureFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, handle, mode)
    }

    /**
     * Filter flags for the texture. Note: `heightmap_texture` is always sampled with linear filtering,
     * even if nearest-neighbor filtering is selected here. This is to ensure the heightmap effect
     * looks as intended. If you need sharper height transitions between pixels, resize the heightmap
     * texture in an image editor with nearest-neighbor filtering.
     *
     * Generated from Godot docs: BaseMaterial3D.get_texture_filter
     */
    fun getTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, handle)
    }

    /**
     * If `true`, enables subsurface scattering transmittance. Only effective if
     * `subsurf_scatter_enabled` is `true`. See also `backlight_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_feature
     */
    fun setFeature(feature: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFeatureBind, handle, feature, enable)
    }

    /**
     * If `true`, enables subsurface scattering transmittance. Only effective if
     * `subsurf_scatter_enabled` is `true`. See also `backlight_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_feature
     */
    fun getFeature(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFeatureBind, handle, feature)
    }

    /**
     * The texture to use for multiplying the intensity of the subsurface scattering transmittance
     * intensity. See also `subsurf_scatter_texture`. Ignored if `subsurf_scatter_skin_mode` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_texture
     */
    fun setTexture(param: Long, texture: Texture2D?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(setTextureBind, handle, param, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * The texture to use for multiplying the intensity of the subsurface scattering transmittance
     * intensity. See also `subsurf_scatter_texture`. Ignored if `subsurf_scatter_skin_mode` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_texture
     */
    fun getTexture(param: Long): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getTextureBind, handle, param))
    }

    /**
     * Specifies how the `detail_albedo` should blend with the current `ALBEDO`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_detail_blend_mode
     */
    fun setDetailBlendMode(detailBlendMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDetailBlendModeBind, handle, detailBlendMode)
    }

    /**
     * Specifies how the `detail_albedo` should blend with the current `ALBEDO`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_detail_blend_mode
     */
    fun getDetailBlendMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDetailBlendModeBind, handle)
    }

    /**
     * How much to scale the `UV` coordinates. This is multiplied by `UV` in the vertex function. The Z
     * component is used when `uv1_triplanar` is enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv1_scale
     */
    fun setUv1Scale(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setUv1ScaleBind, handle, scale)
    }

    /**
     * How much to scale the `UV` coordinates. This is multiplied by `UV` in the vertex function. The Z
     * component is used when `uv1_triplanar` is enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv1_scale
     */
    fun getUv1Scale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getUv1ScaleBind, handle)
    }

    /**
     * How much to offset the `UV` coordinates. This amount will be added to `UV` in the vertex
     * function. This can be used to offset a texture. The Z component is used when `uv1_triplanar` is
     * enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv1_offset
     */
    fun setUv1Offset(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setUv1OffsetBind, handle, offset)
    }

    /**
     * How much to offset the `UV` coordinates. This amount will be added to `UV` in the vertex
     * function. This can be used to offset a texture. The Z component is used when `uv1_triplanar` is
     * enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv1_offset
     */
    fun getUv1Offset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getUv1OffsetBind, handle)
    }

    /**
     * A lower number blends the texture more softly while a higher number blends the texture more
     * sharply. Note: `uv1_triplanar_sharpness` is clamped between `0.0` and `150.0` (inclusive) as
     * values outside that range can look broken depending on the mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv1_triplanar_blend_sharpness
     */
    fun setUv1TriplanarBlendSharpness(sharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setUv1TriplanarBlendSharpnessBind, handle, sharpness)
    }

    /**
     * A lower number blends the texture more softly while a higher number blends the texture more
     * sharply. Note: `uv1_triplanar_sharpness` is clamped between `0.0` and `150.0` (inclusive) as
     * values outside that range can look broken depending on the mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv1_triplanar_blend_sharpness
     */
    fun getUv1TriplanarBlendSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUv1TriplanarBlendSharpnessBind, handle)
    }

    /**
     * How much to scale the `UV2` coordinates. This is multiplied by `UV2` in the vertex function. The
     * Z component is used when `uv2_triplanar` is enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv2_scale
     */
    fun setUv2Scale(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setUv2ScaleBind, handle, scale)
    }

    /**
     * How much to scale the `UV2` coordinates. This is multiplied by `UV2` in the vertex function. The
     * Z component is used when `uv2_triplanar` is enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv2_scale
     */
    fun getUv2Scale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getUv2ScaleBind, handle)
    }

    /**
     * How much to offset the `UV2` coordinates. This amount will be added to `UV2` in the vertex
     * function. This can be used to offset a texture. The Z component is used when `uv2_triplanar` is
     * enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv2_offset
     */
    fun setUv2Offset(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setUv2OffsetBind, handle, offset)
    }

    /**
     * How much to offset the `UV2` coordinates. This amount will be added to `UV2` in the vertex
     * function. This can be used to offset a texture. The Z component is used when `uv2_triplanar` is
     * enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv2_offset
     */
    fun getUv2Offset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getUv2OffsetBind, handle)
    }

    /**
     * A lower number blends the texture more softly while a higher number blends the texture more
     * sharply. Note: `uv2_triplanar_sharpness` is clamped between `0.0` and `150.0` (inclusive) as
     * values outside that range can look broken depending on the mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv2_triplanar_blend_sharpness
     */
    fun setUv2TriplanarBlendSharpness(sharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setUv2TriplanarBlendSharpnessBind, handle, sharpness)
    }

    /**
     * A lower number blends the texture more softly while a higher number blends the texture more
     * sharply. Note: `uv2_triplanar_sharpness` is clamped between `0.0` and `150.0` (inclusive) as
     * values outside that range can look broken depending on the mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv2_triplanar_blend_sharpness
     */
    fun getUv2TriplanarBlendSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUv2TriplanarBlendSharpnessBind, handle)
    }

    /**
     * Controls how the object faces the camera. Note: Billboard mode is not suitable for VR because
     * the left-right vector of the camera is not horizontal when the screen is attached to your head
     * instead of on the table. See GitHub issue #41567
     * (https://github.com/godotengine/godot/issues/41567) for details.
     *
     * Generated from Godot docs: BaseMaterial3D.set_billboard_mode
     */
    fun setBillboardMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBillboardModeBind, handle, mode)
    }

    /**
     * Controls how the object faces the camera. Note: Billboard mode is not suitable for VR because
     * the left-right vector of the camera is not horizontal when the screen is attached to your head
     * instead of on the table. See GitHub issue #41567
     * (https://github.com/godotengine/godot/issues/41567) for details.
     *
     * Generated from Godot docs: BaseMaterial3D.get_billboard_mode
     */
    fun getBillboardMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBillboardModeBind, handle)
    }

    /**
     * The number of horizontal frames in the particle sprite sheet. Only enabled when using
     * `BILLBOARD_PARTICLES`. See `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_particles_anim_h_frames
     */
    fun setParticlesAnimHFrames(frames: Int) {
        ObjectCalls.ptrcallWithIntArg(setParticlesAnimHFramesBind, handle, frames)
    }

    /**
     * The number of horizontal frames in the particle sprite sheet. Only enabled when using
     * `BILLBOARD_PARTICLES`. See `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_particles_anim_h_frames
     */
    fun getParticlesAnimHFrames(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getParticlesAnimHFramesBind, handle)
    }

    /**
     * The number of vertical frames in the particle sprite sheet. Only enabled when using
     * `BILLBOARD_PARTICLES`. See `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_particles_anim_v_frames
     */
    fun setParticlesAnimVFrames(frames: Int) {
        ObjectCalls.ptrcallWithIntArg(setParticlesAnimVFramesBind, handle, frames)
    }

    /**
     * The number of vertical frames in the particle sprite sheet. Only enabled when using
     * `BILLBOARD_PARTICLES`. See `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_particles_anim_v_frames
     */
    fun getParticlesAnimVFrames(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getParticlesAnimVFramesBind, handle)
    }

    /**
     * If `true`, particle animations are looped. Only enabled when using `BILLBOARD_PARTICLES`. See
     * `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_particles_anim_loop
     */
    fun setParticlesAnimLoop(loop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setParticlesAnimLoopBind, handle, loop)
    }

    /**
     * If `true`, particle animations are looped. Only enabled when using `BILLBOARD_PARTICLES`. See
     * `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_particles_anim_loop
     */
    fun getParticlesAnimLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getParticlesAnimLoopBind, handle)
    }

    /**
     * If `true`, uses parallax occlusion mapping to represent depth in the material instead of simple
     * offset mapping (see `heightmap_enabled`). This results in a more convincing depth effect, but is
     * much more expensive on the GPU. Only enable this on materials where it makes a significant
     * visual difference.
     *
     * Generated from Godot docs: BaseMaterial3D.set_heightmap_deep_parallax
     */
    fun setHeightmapDeepParallax(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHeightmapDeepParallaxBind, handle, enable)
    }

    /**
     * If `true`, uses parallax occlusion mapping to represent depth in the material instead of simple
     * offset mapping (see `heightmap_enabled`). This results in a more convincing depth effect, but is
     * much more expensive on the GPU. Only enable this on materials where it makes a significant
     * visual difference.
     *
     * Generated from Godot docs: BaseMaterial3D.is_heightmap_deep_parallax_enabled
     */
    fun isHeightmapDeepParallaxEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHeightmapDeepParallaxEnabledBind, handle)
    }

    /**
     * The number of layers to use for parallax occlusion mapping when the camera is far away from the
     * material. Higher values result in a more convincing depth effect, especially in materials that
     * have steep height changes. Higher values have a significant cost on the GPU, so it should only
     * be increased on materials where it makes a significant visual difference. Note: Only effective
     * if `heightmap_deep_parallax` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_heightmap_deep_parallax_min_layers
     */
    fun setHeightmapDeepParallaxMinLayers(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(setHeightmapDeepParallaxMinLayersBind, handle, layer)
    }

    /**
     * The number of layers to use for parallax occlusion mapping when the camera is far away from the
     * material. Higher values result in a more convincing depth effect, especially in materials that
     * have steep height changes. Higher values have a significant cost on the GPU, so it should only
     * be increased on materials where it makes a significant visual difference. Note: Only effective
     * if `heightmap_deep_parallax` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_heightmap_deep_parallax_min_layers
     */
    fun getHeightmapDeepParallaxMinLayers(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightmapDeepParallaxMinLayersBind, handle)
    }

    /**
     * The number of layers to use for parallax occlusion mapping when the camera is up close to the
     * material. Higher values result in a more convincing depth effect, especially in materials that
     * have steep height changes. Higher values have a significant cost on the GPU, so it should only
     * be increased on materials where it makes a significant visual difference. Note: Only effective
     * if `heightmap_deep_parallax` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_heightmap_deep_parallax_max_layers
     */
    fun setHeightmapDeepParallaxMaxLayers(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(setHeightmapDeepParallaxMaxLayersBind, handle, layer)
    }

    /**
     * The number of layers to use for parallax occlusion mapping when the camera is up close to the
     * material. Higher values result in a more convincing depth effect, especially in materials that
     * have steep height changes. Higher values have a significant cost on the GPU, so it should only
     * be increased on materials where it makes a significant visual difference. Note: Only effective
     * if `heightmap_deep_parallax` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_heightmap_deep_parallax_max_layers
     */
    fun getHeightmapDeepParallaxMaxLayers(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightmapDeepParallaxMaxLayersBind, handle)
    }

    /**
     * If `true`, flips the mesh's tangent vectors when interpreting the height map. If the heightmap
     * effect looks strange when the camera moves (even with a reasonable `heightmap_scale`), try
     * setting this to `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_heightmap_deep_parallax_flip_tangent
     */
    fun setHeightmapDeepParallaxFlipTangent(flip: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHeightmapDeepParallaxFlipTangentBind, handle, flip)
    }

    /**
     * If `true`, flips the mesh's tangent vectors when interpreting the height map. If the heightmap
     * effect looks strange when the camera moves (even with a reasonable `heightmap_scale`), try
     * setting this to `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_heightmap_deep_parallax_flip_tangent
     */
    fun getHeightmapDeepParallaxFlipTangent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getHeightmapDeepParallaxFlipTangentBind, handle)
    }

    /**
     * If `true`, flips the mesh's binormal vectors when interpreting the height map. If the heightmap
     * effect looks strange when the camera moves (even with a reasonable `heightmap_scale`), try
     * setting this to `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_heightmap_deep_parallax_flip_binormal
     */
    fun setHeightmapDeepParallaxFlipBinormal(flip: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHeightmapDeepParallaxFlipBinormalBind, handle, flip)
    }

    /**
     * If `true`, flips the mesh's binormal vectors when interpreting the height map. If the heightmap
     * effect looks strange when the camera moves (even with a reasonable `heightmap_scale`), try
     * setting this to `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_heightmap_deep_parallax_flip_binormal
     */
    fun getHeightmapDeepParallaxFlipBinormal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getHeightmapDeepParallaxFlipBinormalBind, handle)
    }

    /**
     * Grows object vertices in the direction of their normals. Only effective if `grow` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_grow
     */
    fun setGrow(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGrowBind, handle, amount)
    }

    /**
     * Grows object vertices in the direction of their normals. Only effective if `grow` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_grow
     */
    fun getGrow(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGrowBind, handle)
    }

    /**
     * Sets how `emission` interacts with `emission_texture`. Can either add or multiply.
     *
     * Generated from Godot docs: BaseMaterial3D.set_emission_operator
     */
    fun setEmissionOperator(operator: Long) {
        ObjectCalls.ptrcallWithLongArg(setEmissionOperatorBind, handle, operator)
    }

    /**
     * Sets how `emission` interacts with `emission_texture`. Can either add or multiply.
     *
     * Generated from Godot docs: BaseMaterial3D.get_emission_operator
     */
    fun getEmissionOperator(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEmissionOperatorBind, handle)
    }

    /**
     * Amount that ambient occlusion affects lighting from lights. If `0`, ambient occlusion only
     * affects ambient light. If `1`, ambient occlusion affects lights just as much as it affects
     * ambient light. This can be used to impact the strength of the ambient occlusion effect, but
     * typically looks unrealistic.
     *
     * Generated from Godot docs: BaseMaterial3D.set_ao_light_affect
     */
    fun setAoLightAffect(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAoLightAffectBind, handle, amount)
    }

    /**
     * Amount that ambient occlusion affects lighting from lights. If `0`, ambient occlusion only
     * affects ambient light. If `1`, ambient occlusion affects lights just as much as it affects
     * ambient light. This can be used to impact the strength of the ambient occlusion effect, but
     * typically looks unrealistic.
     *
     * Generated from Godot docs: BaseMaterial3D.get_ao_light_affect
     */
    fun getAoLightAffect(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAoLightAffectBind, handle)
    }

    /**
     * Threshold at which the alpha scissor will discard values. Higher values will result in more
     * pixels being discarded. If the material becomes too opaque at a distance, try increasing
     * `alpha_scissor_threshold`. If the material disappears at a distance, try decreasing
     * `alpha_scissor_threshold`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_alpha_scissor_threshold
     */
    fun setAlphaScissorThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaScissorThresholdBind, handle, threshold)
    }

    /**
     * Threshold at which the alpha scissor will discard values. Higher values will result in more
     * pixels being discarded. If the material becomes too opaque at a distance, try increasing
     * `alpha_scissor_threshold`. If the material disappears at a distance, try decreasing
     * `alpha_scissor_threshold`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_alpha_scissor_threshold
     */
    fun getAlphaScissorThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaScissorThresholdBind, handle)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_alpha_hash_scale
     */
    fun setAlphaHashScale(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaHashScaleBind, handle, threshold)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_alpha_hash_scale
     */
    fun getAlphaHashScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaHashScaleBind, handle)
    }

    /**
     * If `true`, enables the vertex grow setting. This can be used to create mesh-based outlines using
     * a second material pass and its `cull_mode` set to `CULL_FRONT`. See also `grow_amount`. Note:
     * Vertex growth cannot create new vertices, which means that visible gaps may occur in sharp
     * corners. This can be alleviated by designing the mesh to use smooth normals exclusively using
     * face weighted normals (http://wiki.polycount.com/wiki/Face_weighted_normals) in the 3D authoring
     * software. In this case, grow will be able to join every outline together, just like in the
     * original mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.set_grow_enabled
     */
    fun setGrowEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGrowEnabledBind, handle, enable)
    }

    /**
     * If `true`, enables the vertex grow setting. This can be used to create mesh-based outlines using
     * a second material pass and its `cull_mode` set to `CULL_FRONT`. See also `grow_amount`. Note:
     * Vertex growth cannot create new vertices, which means that visible gaps may occur in sharp
     * corners. This can be alleviated by designing the mesh to use smooth normals exclusively using
     * face weighted normals (http://wiki.polycount.com/wiki/Face_weighted_normals) in the 3D authoring
     * software. In this case, grow will be able to join every outline together, just like in the
     * original mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.is_grow_enabled
     */
    fun isGrowEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGrowEnabledBind, handle)
    }

    /**
     * Specifies the channel of the `metallic_texture` in which the metallic information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.set_metallic_texture_channel
     */
    fun setMetallicTextureChannel(channel: Long) {
        ObjectCalls.ptrcallWithLongArg(setMetallicTextureChannelBind, handle, channel)
    }

    /**
     * Specifies the channel of the `metallic_texture` in which the metallic information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.get_metallic_texture_channel
     */
    fun getMetallicTextureChannel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMetallicTextureChannelBind, handle)
    }

    /**
     * Specifies the channel of the `roughness_texture` in which the roughness information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.set_roughness_texture_channel
     */
    fun setRoughnessTextureChannel(channel: Long) {
        ObjectCalls.ptrcallWithLongArg(setRoughnessTextureChannelBind, handle, channel)
    }

    /**
     * Specifies the channel of the `roughness_texture` in which the roughness information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.get_roughness_texture_channel
     */
    fun getRoughnessTextureChannel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRoughnessTextureChannelBind, handle)
    }

    /**
     * Specifies the channel of the `ao_texture` in which the ambient occlusion information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.set_ao_texture_channel
     */
    fun setAoTextureChannel(channel: Long) {
        ObjectCalls.ptrcallWithLongArg(setAoTextureChannelBind, handle, channel)
    }

    /**
     * Specifies the channel of the `ao_texture` in which the ambient occlusion information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.get_ao_texture_channel
     */
    fun getAoTextureChannel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAoTextureChannelBind, handle)
    }

    /**
     * Specifies the channel of the `refraction_texture` in which the refraction information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored refraction in the red channel, roughness in the blue, and ambient
     * occlusion in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.set_refraction_texture_channel
     */
    fun setRefractionTextureChannel(channel: Long) {
        ObjectCalls.ptrcallWithLongArg(setRefractionTextureChannelBind, handle, channel)
    }

    /**
     * Specifies the channel of the `refraction_texture` in which the refraction information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored refraction in the red channel, roughness in the blue, and ambient
     * occlusion in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.get_refraction_texture_channel
     */
    fun getRefractionTextureChannel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRefractionTextureChannelBind, handle)
    }

    /**
     * If `true`, the proximity fade effect is enabled. The proximity fade effect fades out each pixel
     * based on its distance to another object.
     *
     * Generated from Godot docs: BaseMaterial3D.set_proximity_fade_enabled
     */
    fun setProximityFadeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProximityFadeEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the proximity fade effect is enabled. The proximity fade effect fades out each pixel
     * based on its distance to another object.
     *
     * Generated from Godot docs: BaseMaterial3D.is_proximity_fade_enabled
     */
    fun isProximityFadeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProximityFadeEnabledBind, handle)
    }

    /**
     * Distance over which the fade effect takes place. The larger the distance the longer it takes for
     * an object to fade.
     *
     * Generated from Godot docs: BaseMaterial3D.set_proximity_fade_distance
     */
    fun setProximityFadeDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setProximityFadeDistanceBind, handle, distance)
    }

    /**
     * Distance over which the fade effect takes place. The larger the distance the longer it takes for
     * an object to fade.
     *
     * Generated from Godot docs: BaseMaterial3D.get_proximity_fade_distance
     */
    fun getProximityFadeDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getProximityFadeDistanceBind, handle)
    }

    /**
     * The width of the range around the shape between the minimum and maximum representable signed
     * distance.
     *
     * Generated from Godot docs: BaseMaterial3D.set_msdf_pixel_range
     */
    fun setMsdfPixelRange(range: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMsdfPixelRangeBind, handle, range)
    }

    /**
     * The width of the range around the shape between the minimum and maximum representable signed
     * distance.
     *
     * Generated from Godot docs: BaseMaterial3D.get_msdf_pixel_range
     */
    fun getMsdfPixelRange(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMsdfPixelRangeBind, handle)
    }

    /**
     * The width of the shape outline.
     *
     * Generated from Godot docs: BaseMaterial3D.set_msdf_outline_size
     */
    fun setMsdfOutlineSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMsdfOutlineSizeBind, handle, size)
    }

    /**
     * The width of the shape outline.
     *
     * Generated from Godot docs: BaseMaterial3D.get_msdf_outline_size
     */
    fun getMsdfOutlineSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMsdfOutlineSizeBind, handle)
    }

    /**
     * Specifies which type of fade to use. Can be any of the `DistanceFadeMode`s.
     *
     * Generated from Godot docs: BaseMaterial3D.set_distance_fade
     */
    fun setDistanceFade(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDistanceFadeBind, handle, mode)
    }

    /**
     * Specifies which type of fade to use. Can be any of the `DistanceFadeMode`s.
     *
     * Generated from Godot docs: BaseMaterial3D.get_distance_fade
     */
    fun getDistanceFade(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDistanceFadeBind, handle)
    }

    /**
     * Distance at which the object appears fully opaque. Note: If `distance_fade_max_distance` is less
     * than `distance_fade_min_distance`, the behavior will be reversed. The object will start to fade
     * away at `distance_fade_max_distance` and will fully disappear once it reaches
     * `distance_fade_min_distance`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_distance_fade_max_distance
     */
    fun setDistanceFadeMaxDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeMaxDistanceBind, handle, distance)
    }

    /**
     * Distance at which the object appears fully opaque. Note: If `distance_fade_max_distance` is less
     * than `distance_fade_min_distance`, the behavior will be reversed. The object will start to fade
     * away at `distance_fade_max_distance` and will fully disappear once it reaches
     * `distance_fade_min_distance`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_distance_fade_max_distance
     */
    fun getDistanceFadeMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeMaxDistanceBind, handle)
    }

    /**
     * Distance at which the object starts to become visible. If the object is less than this distance
     * away, it will be invisible. Note: If `distance_fade_min_distance` is greater than
     * `distance_fade_max_distance`, the behavior will be reversed. The object will start to fade away
     * at `distance_fade_max_distance` and will fully disappear once it reaches
     * `distance_fade_min_distance`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_distance_fade_min_distance
     */
    fun setDistanceFadeMinDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeMinDistanceBind, handle, distance)
    }

    /**
     * Distance at which the object starts to become visible. If the object is less than this distance
     * away, it will be invisible. Note: If `distance_fade_min_distance` is greater than
     * `distance_fade_max_distance`, the behavior will be reversed. The object will start to fade away
     * at `distance_fade_max_distance` and will fully disappear once it reaches
     * `distance_fade_min_distance`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_distance_fade_min_distance
     */
    fun getDistanceFadeMinDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeMinDistanceBind, handle)
    }

    /**
     * Scales the object being rendered towards the camera to avoid clipping into things like walls.
     * This is intended to be used for objects that are fixed with respect to the camera like player
     * arms, tools, etc. Lighting and shadows will continue to work correctly when this setting is
     * adjusted, but screen-space effects like SSAO and SSR may break with lower scales. Therefore, try
     * to keep this setting as close to `1.0` as possible.
     *
     * Generated from Godot docs: BaseMaterial3D.set_z_clip_scale
     */
    fun setZClipScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setZClipScaleBind, handle, scale)
    }

    /**
     * Scales the object being rendered towards the camera to avoid clipping into things like walls.
     * This is intended to be used for objects that are fixed with respect to the camera like player
     * arms, tools, etc. Lighting and shadows will continue to work correctly when this setting is
     * adjusted, but screen-space effects like SSAO and SSR may break with lower scales. Therefore, try
     * to keep this setting as close to `1.0` as possible.
     *
     * Generated from Godot docs: BaseMaterial3D.get_z_clip_scale
     */
    fun getZClipScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getZClipScaleBind, handle)
    }

    /**
     * Overrides the `Camera3D`'s field of view angle (in degrees). Note: This behaves as if the field
     * of view is set on a `Camera3D` with `Camera3D.keep_aspect` set to `Camera3D.KEEP_HEIGHT`.
     * Additionally, it may not look correct on a non-perspective camera where the field of view
     * setting is ignored.
     *
     * Generated from Godot docs: BaseMaterial3D.set_fov_override
     */
    fun setFovOverride(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFovOverrideBind, handle, scale)
    }

    /**
     * Overrides the `Camera3D`'s field of view angle (in degrees). Note: This behaves as if the field
     * of view is set on a `Camera3D` with `Camera3D.keep_aspect` set to `Camera3D.KEEP_HEIGHT`.
     * Additionally, it may not look correct on a non-perspective camera where the field of view
     * setting is ignored.
     *
     * Generated from Godot docs: BaseMaterial3D.get_fov_override
     */
    fun getFovOverride(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFovOverrideBind, handle)
    }

    /**
     * The stencil effect mode.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_mode
     */
    fun setStencilMode(stencilMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setStencilModeBind, handle, stencilMode)
    }

    /**
     * The stencil effect mode.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_mode
     */
    fun getStencilMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStencilModeBind, handle)
    }

    /**
     * The flags dictating how the stencil operation behaves.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_flags
     */
    fun setStencilFlags(stencilFlags: Int) {
        ObjectCalls.ptrcallWithIntArg(setStencilFlagsBind, handle, stencilFlags)
    }

    /**
     * The flags dictating how the stencil operation behaves.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_flags
     */
    fun getStencilFlags(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStencilFlagsBind, handle)
    }

    /**
     * The comparison operator to use for stencil masking operations.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_compare
     */
    fun setStencilCompare(stencilCompare: Long) {
        ObjectCalls.ptrcallWithLongArg(setStencilCompareBind, handle, stencilCompare)
    }

    /**
     * The comparison operator to use for stencil masking operations.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_compare
     */
    fun getStencilCompare(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStencilCompareBind, handle)
    }

    /**
     * The stencil reference value (0-255). Typically a power of 2.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_reference
     */
    fun setStencilReference(stencilReference: Int) {
        ObjectCalls.ptrcallWithIntArg(setStencilReferenceBind, handle, stencilReference)
    }

    /**
     * The stencil reference value (0-255). Typically a power of 2.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_reference
     */
    fun getStencilReference(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStencilReferenceBind, handle)
    }

    /**
     * The primary color of the stencil effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_effect_color
     */
    fun setStencilEffectColor(stencilColor: Color) {
        ObjectCalls.ptrcallWithColorArg(setStencilEffectColorBind, handle, stencilColor)
    }

    /**
     * The primary color of the stencil effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_effect_color
     */
    fun getStencilEffectColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getStencilEffectColorBind, handle)
    }

    /**
     * The outline thickness for `STENCIL_MODE_OUTLINE`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_effect_outline_thickness
     */
    fun setStencilEffectOutlineThickness(stencilOutlineThickness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStencilEffectOutlineThicknessBind, handle, stencilOutlineThickness)
    }

    /**
     * The outline thickness for `STENCIL_MODE_OUTLINE`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_effect_outline_thickness
     */
    fun getStencilEffectOutlineThickness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStencilEffectOutlineThicknessBind, handle)
    }

    companion object {
        const val TEXTURE_ALBEDO: Long = 0L
        const val TEXTURE_METALLIC: Long = 1L
        const val TEXTURE_ROUGHNESS: Long = 2L
        const val TEXTURE_EMISSION: Long = 3L
        const val TEXTURE_NORMAL: Long = 4L
        const val TEXTURE_BENT_NORMAL: Long = 18L
        const val TEXTURE_RIM: Long = 5L
        const val TEXTURE_CLEARCOAT: Long = 6L
        const val TEXTURE_FLOWMAP: Long = 7L
        const val TEXTURE_AMBIENT_OCCLUSION: Long = 8L
        const val TEXTURE_HEIGHTMAP: Long = 9L
        const val TEXTURE_SUBSURFACE_SCATTERING: Long = 10L
        const val TEXTURE_SUBSURFACE_TRANSMITTANCE: Long = 11L
        const val TEXTURE_BACKLIGHT: Long = 12L
        const val TEXTURE_REFRACTION: Long = 13L
        const val TEXTURE_DETAIL_MASK: Long = 14L
        const val TEXTURE_DETAIL_ALBEDO: Long = 15L
        const val TEXTURE_DETAIL_NORMAL: Long = 16L
        const val TEXTURE_ORM: Long = 17L
        const val TEXTURE_MAX: Long = 19L
        const val TEXTURE_FILTER_NEAREST: Long = 0L
        const val TEXTURE_FILTER_LINEAR: Long = 1L
        const val TEXTURE_FILTER_NEAREST_WITH_MIPMAPS: Long = 2L
        const val TEXTURE_FILTER_LINEAR_WITH_MIPMAPS: Long = 3L
        const val TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC: Long = 4L
        const val TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC: Long = 5L
        const val TEXTURE_FILTER_MAX: Long = 6L
        const val DETAIL_UV_1: Long = 0L
        const val DETAIL_UV_2: Long = 1L
        const val TRANSPARENCY_DISABLED: Long = 0L
        const val TRANSPARENCY_ALPHA: Long = 1L
        const val TRANSPARENCY_ALPHA_SCISSOR: Long = 2L
        const val TRANSPARENCY_ALPHA_HASH: Long = 3L
        const val TRANSPARENCY_ALPHA_DEPTH_PRE_PASS: Long = 4L
        const val TRANSPARENCY_MAX: Long = 5L
        const val SHADING_MODE_UNSHADED: Long = 0L
        const val SHADING_MODE_PER_PIXEL: Long = 1L
        const val SHADING_MODE_PER_VERTEX: Long = 2L
        const val SHADING_MODE_MAX: Long = 3L
        const val FEATURE_EMISSION: Long = 0L
        const val FEATURE_NORMAL_MAPPING: Long = 1L
        const val FEATURE_RIM: Long = 2L
        const val FEATURE_CLEARCOAT: Long = 3L
        const val FEATURE_ANISOTROPY: Long = 4L
        const val FEATURE_AMBIENT_OCCLUSION: Long = 5L
        const val FEATURE_HEIGHT_MAPPING: Long = 6L
        const val FEATURE_SUBSURFACE_SCATTERING: Long = 7L
        const val FEATURE_SUBSURFACE_TRANSMITTANCE: Long = 8L
        const val FEATURE_BACKLIGHT: Long = 9L
        const val FEATURE_REFRACTION: Long = 10L
        const val FEATURE_DETAIL: Long = 11L
        const val FEATURE_BENT_NORMAL_MAPPING: Long = 12L
        const val FEATURE_MAX: Long = 13L
        const val BLEND_MODE_MIX: Long = 0L
        const val BLEND_MODE_ADD: Long = 1L
        const val BLEND_MODE_SUB: Long = 2L
        const val BLEND_MODE_MUL: Long = 3L
        const val BLEND_MODE_PREMULT_ALPHA: Long = 4L
        const val ALPHA_ANTIALIASING_OFF: Long = 0L
        const val ALPHA_ANTIALIASING_ALPHA_TO_COVERAGE: Long = 1L
        const val ALPHA_ANTIALIASING_ALPHA_TO_COVERAGE_AND_TO_ONE: Long = 2L
        const val DEPTH_DRAW_OPAQUE_ONLY: Long = 0L
        const val DEPTH_DRAW_ALWAYS: Long = 1L
        const val DEPTH_DRAW_DISABLED: Long = 2L
        const val DEPTH_TEST_DEFAULT: Long = 0L
        const val DEPTH_TEST_INVERTED: Long = 1L
        const val CULL_BACK: Long = 0L
        const val CULL_FRONT: Long = 1L
        const val CULL_DISABLED: Long = 2L
        const val FLAG_DISABLE_DEPTH_TEST: Long = 0L
        const val FLAG_ALBEDO_FROM_VERTEX_COLOR: Long = 1L
        const val FLAG_SRGB_VERTEX_COLOR: Long = 2L
        const val FLAG_USE_POINT_SIZE: Long = 3L
        const val FLAG_FIXED_SIZE: Long = 4L
        const val FLAG_BILLBOARD_KEEP_SCALE: Long = 5L
        const val FLAG_UV1_USE_TRIPLANAR: Long = 6L
        const val FLAG_UV2_USE_TRIPLANAR: Long = 7L
        const val FLAG_UV1_USE_WORLD_TRIPLANAR: Long = 8L
        const val FLAG_UV2_USE_WORLD_TRIPLANAR: Long = 9L
        const val FLAG_AO_ON_UV2: Long = 10L
        const val FLAG_EMISSION_ON_UV2: Long = 11L
        const val FLAG_ALBEDO_TEXTURE_FORCE_SRGB: Long = 12L
        const val FLAG_DONT_RECEIVE_SHADOWS: Long = 13L
        const val FLAG_DISABLE_AMBIENT_LIGHT: Long = 14L
        const val FLAG_USE_SHADOW_TO_OPACITY: Long = 15L
        const val FLAG_USE_TEXTURE_REPEAT: Long = 16L
        const val FLAG_INVERT_HEIGHTMAP: Long = 17L
        const val FLAG_SUBSURFACE_MODE_SKIN: Long = 18L
        const val FLAG_PARTICLE_TRAILS_MODE: Long = 19L
        const val FLAG_ALBEDO_TEXTURE_MSDF: Long = 20L
        const val FLAG_DISABLE_FOG: Long = 21L
        const val FLAG_DISABLE_SPECULAR_OCCLUSION: Long = 22L
        const val FLAG_USE_Z_CLIP_SCALE: Long = 23L
        const val FLAG_USE_FOV_OVERRIDE: Long = 24L
        const val FLAG_MAX: Long = 25L
        const val DIFFUSE_BURLEY: Long = 0L
        const val DIFFUSE_LAMBERT: Long = 1L
        const val DIFFUSE_LAMBERT_WRAP: Long = 2L
        const val DIFFUSE_TOON: Long = 3L
        const val SPECULAR_SCHLICK_GGX: Long = 0L
        const val SPECULAR_TOON: Long = 1L
        const val SPECULAR_DISABLED: Long = 2L
        const val BILLBOARD_DISABLED: Long = 0L
        const val BILLBOARD_ENABLED: Long = 1L
        const val BILLBOARD_FIXED_Y: Long = 2L
        const val BILLBOARD_PARTICLES: Long = 3L
        const val TEXTURE_CHANNEL_RED: Long = 0L
        const val TEXTURE_CHANNEL_GREEN: Long = 1L
        const val TEXTURE_CHANNEL_BLUE: Long = 2L
        const val TEXTURE_CHANNEL_ALPHA: Long = 3L
        const val TEXTURE_CHANNEL_GRAYSCALE: Long = 4L
        const val EMISSION_OP_ADD: Long = 0L
        const val EMISSION_OP_MULTIPLY: Long = 1L
        const val DISTANCE_FADE_DISABLED: Long = 0L
        const val DISTANCE_FADE_PIXEL_ALPHA: Long = 1L
        const val DISTANCE_FADE_PIXEL_DITHER: Long = 2L
        const val DISTANCE_FADE_OBJECT_DITHER: Long = 3L
        const val STENCIL_MODE_DISABLED: Long = 0L
        const val STENCIL_MODE_OUTLINE: Long = 1L
        const val STENCIL_MODE_XRAY: Long = 2L
        const val STENCIL_MODE_CUSTOM: Long = 3L
        const val STENCIL_FLAG_READ: Long = 1L
        const val STENCIL_FLAG_WRITE: Long = 2L
        const val STENCIL_FLAG_WRITE_DEPTH_FAIL: Long = 4L
        const val STENCIL_COMPARE_ALWAYS: Long = 0L
        const val STENCIL_COMPARE_LESS: Long = 1L
        const val STENCIL_COMPARE_EQUAL: Long = 2L
        const val STENCIL_COMPARE_LESS_OR_EQUAL: Long = 3L
        const val STENCIL_COMPARE_GREATER: Long = 4L
        const val STENCIL_COMPARE_NOT_EQUAL: Long = 5L
        const val STENCIL_COMPARE_GREATER_OR_EQUAL: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): BaseMaterial3D? =
            wrap(handle)

        @JvmStatic
        fun fromMaterial(value: Material): BaseMaterial3D? =
            if (value.isClass("BaseMaterial3D")) BaseMaterial3D(value.handle) else null

        internal fun wrap(handle: MemorySegment): BaseMaterial3D? =
            if (handle.address() == 0L) null else BaseMaterial3D(handle)

        private const val SET_ALBEDO_HASH = 2920490490L
        private val setAlbedoBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_albedo", SET_ALBEDO_HASH)
        }

        private const val GET_ALBEDO_HASH = 3444240500L
        private val getAlbedoBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_albedo", GET_ALBEDO_HASH)
        }

        private const val SET_TRANSPARENCY_HASH = 3435651667L
        private val setTransparencyBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_transparency", SET_TRANSPARENCY_HASH)
        }

        private const val GET_TRANSPARENCY_HASH = 990903061L
        private val getTransparencyBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_transparency", GET_TRANSPARENCY_HASH)
        }

        private const val SET_ALPHA_ANTIALIASING_HASH = 3212649852L
        private val setAlphaAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_alpha_antialiasing", SET_ALPHA_ANTIALIASING_HASH)
        }

        private const val GET_ALPHA_ANTIALIASING_HASH = 2889939400L
        private val getAlphaAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_alpha_antialiasing", GET_ALPHA_ANTIALIASING_HASH)
        }

        private const val SET_ALPHA_ANTIALIASING_EDGE_HASH = 373806689L
        private val setAlphaAntialiasingEdgeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_alpha_antialiasing_edge", SET_ALPHA_ANTIALIASING_EDGE_HASH)
        }

        private const val GET_ALPHA_ANTIALIASING_EDGE_HASH = 1740695150L
        private val getAlphaAntialiasingEdgeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_alpha_antialiasing_edge", GET_ALPHA_ANTIALIASING_EDGE_HASH)
        }

        private const val SET_SHADING_MODE_HASH = 3368750322L
        private val setShadingModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_shading_mode", SET_SHADING_MODE_HASH)
        }

        private const val GET_SHADING_MODE_HASH = 2132070559L
        private val getShadingModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_shading_mode", GET_SHADING_MODE_HASH)
        }

        private const val SET_SPECULAR_HASH = 373806689L
        private val setSpecularBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_specular", SET_SPECULAR_HASH)
        }

        private const val GET_SPECULAR_HASH = 1740695150L
        private val getSpecularBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_specular", GET_SPECULAR_HASH)
        }

        private const val SET_METALLIC_HASH = 373806689L
        private val setMetallicBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_metallic", SET_METALLIC_HASH)
        }

        private const val GET_METALLIC_HASH = 1740695150L
        private val getMetallicBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_metallic", GET_METALLIC_HASH)
        }

        private const val SET_ROUGHNESS_HASH = 373806689L
        private val setRoughnessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_roughness", SET_ROUGHNESS_HASH)
        }

        private const val GET_ROUGHNESS_HASH = 1740695150L
        private val getRoughnessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_roughness", GET_ROUGHNESS_HASH)
        }

        private const val SET_EMISSION_HASH = 2920490490L
        private val setEmissionBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_emission", SET_EMISSION_HASH)
        }

        private const val GET_EMISSION_HASH = 3444240500L
        private val getEmissionBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_emission", GET_EMISSION_HASH)
        }

        private const val SET_EMISSION_ENERGY_MULTIPLIER_HASH = 373806689L
        private val setEmissionEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_emission_energy_multiplier", SET_EMISSION_ENERGY_MULTIPLIER_HASH)
        }

        private const val GET_EMISSION_ENERGY_MULTIPLIER_HASH = 1740695150L
        private val getEmissionEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_emission_energy_multiplier", GET_EMISSION_ENERGY_MULTIPLIER_HASH)
        }

        private const val SET_EMISSION_INTENSITY_HASH = 373806689L
        private val setEmissionIntensityBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_emission_intensity", SET_EMISSION_INTENSITY_HASH)
        }

        private const val GET_EMISSION_INTENSITY_HASH = 1740695150L
        private val getEmissionIntensityBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_emission_intensity", GET_EMISSION_INTENSITY_HASH)
        }

        private const val SET_NORMAL_SCALE_HASH = 373806689L
        private val setNormalScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_normal_scale", SET_NORMAL_SCALE_HASH)
        }

        private const val GET_NORMAL_SCALE_HASH = 1740695150L
        private val getNormalScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_normal_scale", GET_NORMAL_SCALE_HASH)
        }

        private const val SET_RIM_HASH = 373806689L
        private val setRimBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_rim", SET_RIM_HASH)
        }

        private const val GET_RIM_HASH = 1740695150L
        private val getRimBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_rim", GET_RIM_HASH)
        }

        private const val SET_RIM_TINT_HASH = 373806689L
        private val setRimTintBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_rim_tint", SET_RIM_TINT_HASH)
        }

        private const val GET_RIM_TINT_HASH = 1740695150L
        private val getRimTintBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_rim_tint", GET_RIM_TINT_HASH)
        }

        private const val SET_CLEARCOAT_HASH = 373806689L
        private val setClearcoatBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_clearcoat", SET_CLEARCOAT_HASH)
        }

        private const val GET_CLEARCOAT_HASH = 1740695150L
        private val getClearcoatBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_clearcoat", GET_CLEARCOAT_HASH)
        }

        private const val SET_CLEARCOAT_ROUGHNESS_HASH = 373806689L
        private val setClearcoatRoughnessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_clearcoat_roughness", SET_CLEARCOAT_ROUGHNESS_HASH)
        }

        private const val GET_CLEARCOAT_ROUGHNESS_HASH = 1740695150L
        private val getClearcoatRoughnessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_clearcoat_roughness", GET_CLEARCOAT_ROUGHNESS_HASH)
        }

        private const val SET_ANISOTROPY_HASH = 373806689L
        private val setAnisotropyBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_anisotropy", SET_ANISOTROPY_HASH)
        }

        private const val GET_ANISOTROPY_HASH = 1740695150L
        private val getAnisotropyBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_anisotropy", GET_ANISOTROPY_HASH)
        }

        private const val SET_HEIGHTMAP_SCALE_HASH = 373806689L
        private val setHeightmapScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_heightmap_scale", SET_HEIGHTMAP_SCALE_HASH)
        }

        private const val GET_HEIGHTMAP_SCALE_HASH = 1740695150L
        private val getHeightmapScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_heightmap_scale", GET_HEIGHTMAP_SCALE_HASH)
        }

        private const val SET_SUBSURFACE_SCATTERING_STRENGTH_HASH = 373806689L
        private val setSubsurfaceScatteringStrengthBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_subsurface_scattering_strength", SET_SUBSURFACE_SCATTERING_STRENGTH_HASH)
        }

        private const val GET_SUBSURFACE_SCATTERING_STRENGTH_HASH = 1740695150L
        private val getSubsurfaceScatteringStrengthBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_subsurface_scattering_strength", GET_SUBSURFACE_SCATTERING_STRENGTH_HASH)
        }

        private const val SET_TRANSMITTANCE_COLOR_HASH = 2920490490L
        private val setTransmittanceColorBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_transmittance_color", SET_TRANSMITTANCE_COLOR_HASH)
        }

        private const val GET_TRANSMITTANCE_COLOR_HASH = 3444240500L
        private val getTransmittanceColorBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_transmittance_color", GET_TRANSMITTANCE_COLOR_HASH)
        }

        private const val SET_TRANSMITTANCE_DEPTH_HASH = 373806689L
        private val setTransmittanceDepthBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_transmittance_depth", SET_TRANSMITTANCE_DEPTH_HASH)
        }

        private const val GET_TRANSMITTANCE_DEPTH_HASH = 1740695150L
        private val getTransmittanceDepthBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_transmittance_depth", GET_TRANSMITTANCE_DEPTH_HASH)
        }

        private const val SET_TRANSMITTANCE_BOOST_HASH = 373806689L
        private val setTransmittanceBoostBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_transmittance_boost", SET_TRANSMITTANCE_BOOST_HASH)
        }

        private const val GET_TRANSMITTANCE_BOOST_HASH = 1740695150L
        private val getTransmittanceBoostBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_transmittance_boost", GET_TRANSMITTANCE_BOOST_HASH)
        }

        private const val SET_BACKLIGHT_HASH = 2920490490L
        private val setBacklightBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_backlight", SET_BACKLIGHT_HASH)
        }

        private const val GET_BACKLIGHT_HASH = 3444240500L
        private val getBacklightBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_backlight", GET_BACKLIGHT_HASH)
        }

        private const val SET_REFRACTION_HASH = 373806689L
        private val setRefractionBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_refraction", SET_REFRACTION_HASH)
        }

        private const val GET_REFRACTION_HASH = 1740695150L
        private val getRefractionBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_refraction", GET_REFRACTION_HASH)
        }

        private const val SET_POINT_SIZE_HASH = 373806689L
        private val setPointSizeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_point_size", SET_POINT_SIZE_HASH)
        }

        private const val GET_POINT_SIZE_HASH = 1740695150L
        private val getPointSizeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_point_size", GET_POINT_SIZE_HASH)
        }

        private const val SET_DETAIL_UV_HASH = 456801921L
        private val setDetailUvBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_detail_uv", SET_DETAIL_UV_HASH)
        }

        private const val GET_DETAIL_UV_HASH = 2306920512L
        private val getDetailUvBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_detail_uv", GET_DETAIL_UV_HASH)
        }

        private const val SET_BLEND_MODE_HASH = 2830186259L
        private val setBlendModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_blend_mode", SET_BLEND_MODE_HASH)
        }

        private const val GET_BLEND_MODE_HASH = 4022690962L
        private val getBlendModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_blend_mode", GET_BLEND_MODE_HASH)
        }

        private const val SET_DEPTH_DRAW_MODE_HASH = 1456584748L
        private val setDepthDrawModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_depth_draw_mode", SET_DEPTH_DRAW_MODE_HASH)
        }

        private const val GET_DEPTH_DRAW_MODE_HASH = 2578197639L
        private val getDepthDrawModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_depth_draw_mode", GET_DEPTH_DRAW_MODE_HASH)
        }

        private const val SET_DEPTH_TEST_HASH = 3918692338L
        private val setDepthTestBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_depth_test", SET_DEPTH_TEST_HASH)
        }

        private const val GET_DEPTH_TEST_HASH = 3434785811L
        private val getDepthTestBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_depth_test", GET_DEPTH_TEST_HASH)
        }

        private const val SET_CULL_MODE_HASH = 2338909218L
        private val setCullModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_cull_mode", SET_CULL_MODE_HASH)
        }

        private const val GET_CULL_MODE_HASH = 1941499586L
        private val getCullModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_cull_mode", GET_CULL_MODE_HASH)
        }

        private const val SET_DIFFUSE_MODE_HASH = 1045299638L
        private val setDiffuseModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_diffuse_mode", SET_DIFFUSE_MODE_HASH)
        }

        private const val GET_DIFFUSE_MODE_HASH = 3973617136L
        private val getDiffuseModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_diffuse_mode", GET_DIFFUSE_MODE_HASH)
        }

        private const val SET_SPECULAR_MODE_HASH = 584737147L
        private val setSpecularModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_specular_mode", SET_SPECULAR_MODE_HASH)
        }

        private const val GET_SPECULAR_MODE_HASH = 2569953298L
        private val getSpecularModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_specular_mode", GET_SPECULAR_MODE_HASH)
        }

        private const val SET_FLAG_HASH = 3070159527L
        private val setFlagBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_flag", SET_FLAG_HASH)
        }

        private const val GET_FLAG_HASH = 1286410065L
        private val getFlagBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_flag", GET_FLAG_HASH)
        }

        private const val SET_TEXTURE_FILTER_HASH = 22904437L
        private val setTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_texture_filter", SET_TEXTURE_FILTER_HASH)
        }

        private const val GET_TEXTURE_FILTER_HASH = 3289213076L
        private val getTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_texture_filter", GET_TEXTURE_FILTER_HASH)
        }

        private const val SET_FEATURE_HASH = 2819288693L
        private val setFeatureBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_feature", SET_FEATURE_HASH)
        }

        private const val GET_FEATURE_HASH = 1965241794L
        private val getFeatureBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_feature", GET_FEATURE_HASH)
        }

        private const val SET_TEXTURE_HASH = 464208135L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 329605813L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_DETAIL_BLEND_MODE_HASH = 2830186259L
        private val setDetailBlendModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_detail_blend_mode", SET_DETAIL_BLEND_MODE_HASH)
        }

        private const val GET_DETAIL_BLEND_MODE_HASH = 4022690962L
        private val getDetailBlendModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_detail_blend_mode", GET_DETAIL_BLEND_MODE_HASH)
        }

        private const val SET_UV1_SCALE_HASH = 3460891852L
        private val setUv1ScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_uv1_scale", SET_UV1_SCALE_HASH)
        }

        private const val GET_UV1_SCALE_HASH = 3360562783L
        private val getUv1ScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_uv1_scale", GET_UV1_SCALE_HASH)
        }

        private const val SET_UV1_OFFSET_HASH = 3460891852L
        private val setUv1OffsetBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_uv1_offset", SET_UV1_OFFSET_HASH)
        }

        private const val GET_UV1_OFFSET_HASH = 3360562783L
        private val getUv1OffsetBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_uv1_offset", GET_UV1_OFFSET_HASH)
        }

        private const val SET_UV1_TRIPLANAR_BLEND_SHARPNESS_HASH = 373806689L
        private val setUv1TriplanarBlendSharpnessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_uv1_triplanar_blend_sharpness", SET_UV1_TRIPLANAR_BLEND_SHARPNESS_HASH)
        }

        private const val GET_UV1_TRIPLANAR_BLEND_SHARPNESS_HASH = 1740695150L
        private val getUv1TriplanarBlendSharpnessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_uv1_triplanar_blend_sharpness", GET_UV1_TRIPLANAR_BLEND_SHARPNESS_HASH)
        }

        private const val SET_UV2_SCALE_HASH = 3460891852L
        private val setUv2ScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_uv2_scale", SET_UV2_SCALE_HASH)
        }

        private const val GET_UV2_SCALE_HASH = 3360562783L
        private val getUv2ScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_uv2_scale", GET_UV2_SCALE_HASH)
        }

        private const val SET_UV2_OFFSET_HASH = 3460891852L
        private val setUv2OffsetBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_uv2_offset", SET_UV2_OFFSET_HASH)
        }

        private const val GET_UV2_OFFSET_HASH = 3360562783L
        private val getUv2OffsetBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_uv2_offset", GET_UV2_OFFSET_HASH)
        }

        private const val SET_UV2_TRIPLANAR_BLEND_SHARPNESS_HASH = 373806689L
        private val setUv2TriplanarBlendSharpnessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_uv2_triplanar_blend_sharpness", SET_UV2_TRIPLANAR_BLEND_SHARPNESS_HASH)
        }

        private const val GET_UV2_TRIPLANAR_BLEND_SHARPNESS_HASH = 1740695150L
        private val getUv2TriplanarBlendSharpnessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_uv2_triplanar_blend_sharpness", GET_UV2_TRIPLANAR_BLEND_SHARPNESS_HASH)
        }

        private const val SET_BILLBOARD_MODE_HASH = 4202036497L
        private val setBillboardModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_billboard_mode", SET_BILLBOARD_MODE_HASH)
        }

        private const val GET_BILLBOARD_MODE_HASH = 1283840139L
        private val getBillboardModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_billboard_mode", GET_BILLBOARD_MODE_HASH)
        }

        private const val SET_PARTICLES_ANIM_H_FRAMES_HASH = 1286410249L
        private val setParticlesAnimHFramesBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_particles_anim_h_frames", SET_PARTICLES_ANIM_H_FRAMES_HASH)
        }

        private const val GET_PARTICLES_ANIM_H_FRAMES_HASH = 3905245786L
        private val getParticlesAnimHFramesBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_particles_anim_h_frames", GET_PARTICLES_ANIM_H_FRAMES_HASH)
        }

        private const val SET_PARTICLES_ANIM_V_FRAMES_HASH = 1286410249L
        private val setParticlesAnimVFramesBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_particles_anim_v_frames", SET_PARTICLES_ANIM_V_FRAMES_HASH)
        }

        private const val GET_PARTICLES_ANIM_V_FRAMES_HASH = 3905245786L
        private val getParticlesAnimVFramesBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_particles_anim_v_frames", GET_PARTICLES_ANIM_V_FRAMES_HASH)
        }

        private const val SET_PARTICLES_ANIM_LOOP_HASH = 2586408642L
        private val setParticlesAnimLoopBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_particles_anim_loop", SET_PARTICLES_ANIM_LOOP_HASH)
        }

        private const val GET_PARTICLES_ANIM_LOOP_HASH = 36873697L
        private val getParticlesAnimLoopBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_particles_anim_loop", GET_PARTICLES_ANIM_LOOP_HASH)
        }

        private const val SET_HEIGHTMAP_DEEP_PARALLAX_HASH = 2586408642L
        private val setHeightmapDeepParallaxBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_heightmap_deep_parallax", SET_HEIGHTMAP_DEEP_PARALLAX_HASH)
        }

        private const val IS_HEIGHTMAP_DEEP_PARALLAX_ENABLED_HASH = 36873697L
        private val isHeightmapDeepParallaxEnabledBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "is_heightmap_deep_parallax_enabled", IS_HEIGHTMAP_DEEP_PARALLAX_ENABLED_HASH)
        }

        private const val SET_HEIGHTMAP_DEEP_PARALLAX_MIN_LAYERS_HASH = 1286410249L
        private val setHeightmapDeepParallaxMinLayersBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_heightmap_deep_parallax_min_layers", SET_HEIGHTMAP_DEEP_PARALLAX_MIN_LAYERS_HASH)
        }

        private const val GET_HEIGHTMAP_DEEP_PARALLAX_MIN_LAYERS_HASH = 3905245786L
        private val getHeightmapDeepParallaxMinLayersBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_heightmap_deep_parallax_min_layers", GET_HEIGHTMAP_DEEP_PARALLAX_MIN_LAYERS_HASH)
        }

        private const val SET_HEIGHTMAP_DEEP_PARALLAX_MAX_LAYERS_HASH = 1286410249L
        private val setHeightmapDeepParallaxMaxLayersBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_heightmap_deep_parallax_max_layers", SET_HEIGHTMAP_DEEP_PARALLAX_MAX_LAYERS_HASH)
        }

        private const val GET_HEIGHTMAP_DEEP_PARALLAX_MAX_LAYERS_HASH = 3905245786L
        private val getHeightmapDeepParallaxMaxLayersBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_heightmap_deep_parallax_max_layers", GET_HEIGHTMAP_DEEP_PARALLAX_MAX_LAYERS_HASH)
        }

        private const val SET_HEIGHTMAP_DEEP_PARALLAX_FLIP_TANGENT_HASH = 2586408642L
        private val setHeightmapDeepParallaxFlipTangentBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_heightmap_deep_parallax_flip_tangent", SET_HEIGHTMAP_DEEP_PARALLAX_FLIP_TANGENT_HASH)
        }

        private const val GET_HEIGHTMAP_DEEP_PARALLAX_FLIP_TANGENT_HASH = 36873697L
        private val getHeightmapDeepParallaxFlipTangentBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_heightmap_deep_parallax_flip_tangent", GET_HEIGHTMAP_DEEP_PARALLAX_FLIP_TANGENT_HASH)
        }

        private const val SET_HEIGHTMAP_DEEP_PARALLAX_FLIP_BINORMAL_HASH = 2586408642L
        private val setHeightmapDeepParallaxFlipBinormalBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_heightmap_deep_parallax_flip_binormal", SET_HEIGHTMAP_DEEP_PARALLAX_FLIP_BINORMAL_HASH)
        }

        private const val GET_HEIGHTMAP_DEEP_PARALLAX_FLIP_BINORMAL_HASH = 36873697L
        private val getHeightmapDeepParallaxFlipBinormalBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_heightmap_deep_parallax_flip_binormal", GET_HEIGHTMAP_DEEP_PARALLAX_FLIP_BINORMAL_HASH)
        }

        private const val SET_GROW_HASH = 373806689L
        private val setGrowBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_grow", SET_GROW_HASH)
        }

        private const val GET_GROW_HASH = 1740695150L
        private val getGrowBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_grow", GET_GROW_HASH)
        }

        private const val SET_EMISSION_OPERATOR_HASH = 3825128922L
        private val setEmissionOperatorBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_emission_operator", SET_EMISSION_OPERATOR_HASH)
        }

        private const val GET_EMISSION_OPERATOR_HASH = 974205018L
        private val getEmissionOperatorBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_emission_operator", GET_EMISSION_OPERATOR_HASH)
        }

        private const val SET_AO_LIGHT_AFFECT_HASH = 373806689L
        private val setAoLightAffectBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_ao_light_affect", SET_AO_LIGHT_AFFECT_HASH)
        }

        private const val GET_AO_LIGHT_AFFECT_HASH = 1740695150L
        private val getAoLightAffectBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_ao_light_affect", GET_AO_LIGHT_AFFECT_HASH)
        }

        private const val SET_ALPHA_SCISSOR_THRESHOLD_HASH = 373806689L
        private val setAlphaScissorThresholdBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_alpha_scissor_threshold", SET_ALPHA_SCISSOR_THRESHOLD_HASH)
        }

        private const val GET_ALPHA_SCISSOR_THRESHOLD_HASH = 1740695150L
        private val getAlphaScissorThresholdBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_alpha_scissor_threshold", GET_ALPHA_SCISSOR_THRESHOLD_HASH)
        }

        private const val SET_ALPHA_HASH_SCALE_HASH = 373806689L
        private val setAlphaHashScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_alpha_hash_scale", SET_ALPHA_HASH_SCALE_HASH)
        }

        private const val GET_ALPHA_HASH_SCALE_HASH = 1740695150L
        private val getAlphaHashScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_alpha_hash_scale", GET_ALPHA_HASH_SCALE_HASH)
        }

        private const val SET_GROW_ENABLED_HASH = 2586408642L
        private val setGrowEnabledBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_grow_enabled", SET_GROW_ENABLED_HASH)
        }

        private const val IS_GROW_ENABLED_HASH = 36873697L
        private val isGrowEnabledBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "is_grow_enabled", IS_GROW_ENABLED_HASH)
        }

        private const val SET_METALLIC_TEXTURE_CHANNEL_HASH = 744167988L
        private val setMetallicTextureChannelBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_metallic_texture_channel", SET_METALLIC_TEXTURE_CHANNEL_HASH)
        }

        private const val GET_METALLIC_TEXTURE_CHANNEL_HASH = 568133867L
        private val getMetallicTextureChannelBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_metallic_texture_channel", GET_METALLIC_TEXTURE_CHANNEL_HASH)
        }

        private const val SET_ROUGHNESS_TEXTURE_CHANNEL_HASH = 744167988L
        private val setRoughnessTextureChannelBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_roughness_texture_channel", SET_ROUGHNESS_TEXTURE_CHANNEL_HASH)
        }

        private const val GET_ROUGHNESS_TEXTURE_CHANNEL_HASH = 568133867L
        private val getRoughnessTextureChannelBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_roughness_texture_channel", GET_ROUGHNESS_TEXTURE_CHANNEL_HASH)
        }

        private const val SET_AO_TEXTURE_CHANNEL_HASH = 744167988L
        private val setAoTextureChannelBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_ao_texture_channel", SET_AO_TEXTURE_CHANNEL_HASH)
        }

        private const val GET_AO_TEXTURE_CHANNEL_HASH = 568133867L
        private val getAoTextureChannelBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_ao_texture_channel", GET_AO_TEXTURE_CHANNEL_HASH)
        }

        private const val SET_REFRACTION_TEXTURE_CHANNEL_HASH = 744167988L
        private val setRefractionTextureChannelBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_refraction_texture_channel", SET_REFRACTION_TEXTURE_CHANNEL_HASH)
        }

        private const val GET_REFRACTION_TEXTURE_CHANNEL_HASH = 568133867L
        private val getRefractionTextureChannelBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_refraction_texture_channel", GET_REFRACTION_TEXTURE_CHANNEL_HASH)
        }

        private const val SET_PROXIMITY_FADE_ENABLED_HASH = 2586408642L
        private val setProximityFadeEnabledBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_proximity_fade_enabled", SET_PROXIMITY_FADE_ENABLED_HASH)
        }

        private const val IS_PROXIMITY_FADE_ENABLED_HASH = 36873697L
        private val isProximityFadeEnabledBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "is_proximity_fade_enabled", IS_PROXIMITY_FADE_ENABLED_HASH)
        }

        private const val SET_PROXIMITY_FADE_DISTANCE_HASH = 373806689L
        private val setProximityFadeDistanceBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_proximity_fade_distance", SET_PROXIMITY_FADE_DISTANCE_HASH)
        }

        private const val GET_PROXIMITY_FADE_DISTANCE_HASH = 1740695150L
        private val getProximityFadeDistanceBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_proximity_fade_distance", GET_PROXIMITY_FADE_DISTANCE_HASH)
        }

        private const val SET_MSDF_PIXEL_RANGE_HASH = 373806689L
        private val setMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_msdf_pixel_range", SET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val GET_MSDF_PIXEL_RANGE_HASH = 1740695150L
        private val getMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_msdf_pixel_range", GET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val SET_MSDF_OUTLINE_SIZE_HASH = 373806689L
        private val setMsdfOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_msdf_outline_size", SET_MSDF_OUTLINE_SIZE_HASH)
        }

        private const val GET_MSDF_OUTLINE_SIZE_HASH = 1740695150L
        private val getMsdfOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_msdf_outline_size", GET_MSDF_OUTLINE_SIZE_HASH)
        }

        private const val SET_DISTANCE_FADE_HASH = 1379478617L
        private val setDistanceFadeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_distance_fade", SET_DISTANCE_FADE_HASH)
        }

        private const val GET_DISTANCE_FADE_HASH = 2694575734L
        private val getDistanceFadeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_distance_fade", GET_DISTANCE_FADE_HASH)
        }

        private const val SET_DISTANCE_FADE_MAX_DISTANCE_HASH = 373806689L
        private val setDistanceFadeMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_distance_fade_max_distance", SET_DISTANCE_FADE_MAX_DISTANCE_HASH)
        }

        private const val GET_DISTANCE_FADE_MAX_DISTANCE_HASH = 1740695150L
        private val getDistanceFadeMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_distance_fade_max_distance", GET_DISTANCE_FADE_MAX_DISTANCE_HASH)
        }

        private const val SET_DISTANCE_FADE_MIN_DISTANCE_HASH = 373806689L
        private val setDistanceFadeMinDistanceBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_distance_fade_min_distance", SET_DISTANCE_FADE_MIN_DISTANCE_HASH)
        }

        private const val GET_DISTANCE_FADE_MIN_DISTANCE_HASH = 1740695150L
        private val getDistanceFadeMinDistanceBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_distance_fade_min_distance", GET_DISTANCE_FADE_MIN_DISTANCE_HASH)
        }

        private const val SET_Z_CLIP_SCALE_HASH = 373806689L
        private val setZClipScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_z_clip_scale", SET_Z_CLIP_SCALE_HASH)
        }

        private const val GET_Z_CLIP_SCALE_HASH = 1740695150L
        private val getZClipScaleBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_z_clip_scale", GET_Z_CLIP_SCALE_HASH)
        }

        private const val SET_FOV_OVERRIDE_HASH = 373806689L
        private val setFovOverrideBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_fov_override", SET_FOV_OVERRIDE_HASH)
        }

        private const val GET_FOV_OVERRIDE_HASH = 1740695150L
        private val getFovOverrideBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_fov_override", GET_FOV_OVERRIDE_HASH)
        }

        private const val SET_STENCIL_MODE_HASH = 2272367200L
        private val setStencilModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_stencil_mode", SET_STENCIL_MODE_HASH)
        }

        private const val GET_STENCIL_MODE_HASH = 2908443456L
        private val getStencilModeBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_stencil_mode", GET_STENCIL_MODE_HASH)
        }

        private const val SET_STENCIL_FLAGS_HASH = 1286410249L
        private val setStencilFlagsBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_stencil_flags", SET_STENCIL_FLAGS_HASH)
        }

        private const val GET_STENCIL_FLAGS_HASH = 3905245786L
        private val getStencilFlagsBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_stencil_flags", GET_STENCIL_FLAGS_HASH)
        }

        private const val SET_STENCIL_COMPARE_HASH = 3741726481L
        private val setStencilCompareBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_stencil_compare", SET_STENCIL_COMPARE_HASH)
        }

        private const val GET_STENCIL_COMPARE_HASH = 2824600492L
        private val getStencilCompareBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_stencil_compare", GET_STENCIL_COMPARE_HASH)
        }

        private const val SET_STENCIL_REFERENCE_HASH = 1286410249L
        private val setStencilReferenceBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_stencil_reference", SET_STENCIL_REFERENCE_HASH)
        }

        private const val GET_STENCIL_REFERENCE_HASH = 3905245786L
        private val getStencilReferenceBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_stencil_reference", GET_STENCIL_REFERENCE_HASH)
        }

        private const val SET_STENCIL_EFFECT_COLOR_HASH = 2920490490L
        private val setStencilEffectColorBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_stencil_effect_color", SET_STENCIL_EFFECT_COLOR_HASH)
        }

        private const val GET_STENCIL_EFFECT_COLOR_HASH = 3444240500L
        private val getStencilEffectColorBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_stencil_effect_color", GET_STENCIL_EFFECT_COLOR_HASH)
        }

        private const val SET_STENCIL_EFFECT_OUTLINE_THICKNESS_HASH = 373806689L
        private val setStencilEffectOutlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "set_stencil_effect_outline_thickness", SET_STENCIL_EFFECT_OUTLINE_THICKNESS_HASH)
        }

        private const val GET_STENCIL_EFFECT_OUTLINE_THICKNESS_HASH = 1740695150L
        private val getStencilEffectOutlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("BaseMaterial3D", "get_stencil_effect_outline_thickness", GET_STENCIL_EFFECT_OUTLINE_THICKNESS_HASH)
        }
    }
}
