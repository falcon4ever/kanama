package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * Abstract base class for defining the 3D rendering properties of meshes.
 *
 * Generated from Godot docs: BaseMaterial3D
 */
open class BaseMaterial3D(handle: GodotHandle) : Material(handle) {
    var transparency: BaseMaterial3D.Transparency
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

    var alphaAntialiasingMode: BaseMaterial3D.AlphaAntiAliasing
        @JvmName("alphaAntialiasingModeProperty")
        get() = getAlphaAntialiasing()
        @JvmName("setAlphaAntialiasingModeProperty")
        set(value) = setAlphaAntialiasing(value)

    var alphaAntialiasingEdge: Double
        @JvmName("alphaAntialiasingEdgeProperty")
        get() = getAlphaAntialiasingEdge()
        @JvmName("setAlphaAntialiasingEdgeProperty")
        set(value) = setAlphaAntialiasingEdge(value)

    var blendMode: BaseMaterial3D.BlendMode
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    var cullMode: BaseMaterial3D.CullMode
        @JvmName("cullModeProperty")
        get() = getCullMode()
        @JvmName("setCullModeProperty")
        set(value) = setCullMode(value)

    var depthDrawMode: BaseMaterial3D.DepthDrawMode
        @JvmName("depthDrawModeProperty")
        get() = getDepthDrawMode()
        @JvmName("setDepthDrawModeProperty")
        set(value) = setDepthDrawMode(value)

    var noDepthTest: Boolean
        @JvmName("noDepthTestProperty")
        get() = getFlag(BaseMaterial3D.Flags.DISABLE_DEPTH_TEST)
        @JvmName("setNoDepthTestProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.DISABLE_DEPTH_TEST, value)

    var depthTest: BaseMaterial3D.DepthTest
        @JvmName("depthTestProperty")
        get() = getDepthTest()
        @JvmName("setDepthTestProperty")
        set(value) = setDepthTest(value)

    var shadingMode: BaseMaterial3D.ShadingMode
        @JvmName("shadingModeProperty")
        get() = getShadingMode()
        @JvmName("setShadingModeProperty")
        set(value) = setShadingMode(value)

    var diffuseMode: BaseMaterial3D.DiffuseMode
        @JvmName("diffuseModeProperty")
        get() = getDiffuseMode()
        @JvmName("setDiffuseModeProperty")
        set(value) = setDiffuseMode(value)

    var specularMode: BaseMaterial3D.SpecularMode
        @JvmName("specularModeProperty")
        get() = getSpecularMode()
        @JvmName("setSpecularModeProperty")
        set(value) = setSpecularMode(value)

    var disableAmbientLight: Boolean
        @JvmName("disableAmbientLightProperty")
        get() = getFlag(BaseMaterial3D.Flags.DISABLE_AMBIENT_LIGHT)
        @JvmName("setDisableAmbientLightProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.DISABLE_AMBIENT_LIGHT, value)

    var disableFog: Boolean
        @JvmName("disableFogProperty")
        get() = getFlag(BaseMaterial3D.Flags.DISABLE_FOG)
        @JvmName("setDisableFogProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.DISABLE_FOG, value)

    var disableSpecularOcclusion: Boolean
        @JvmName("disableSpecularOcclusionProperty")
        get() = getFlag(BaseMaterial3D.Flags.DISABLE_SPECULAR_OCCLUSION)
        @JvmName("setDisableSpecularOcclusionProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.DISABLE_SPECULAR_OCCLUSION, value)

    var vertexColorUseAsAlbedo: Boolean
        @JvmName("vertexColorUseAsAlbedoProperty")
        get() = getFlag(BaseMaterial3D.Flags.ALBEDO_FROM_VERTEX_COLOR)
        @JvmName("setVertexColorUseAsAlbedoProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.ALBEDO_FROM_VERTEX_COLOR, value)

    var vertexColorIsSrgb: Boolean
        @JvmName("vertexColorIsSrgbProperty")
        get() = getFlag(BaseMaterial3D.Flags.SRGB_VERTEX_COLOR)
        @JvmName("setVertexColorIsSrgbProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.SRGB_VERTEX_COLOR, value)

    var albedoColor: Color
        @JvmName("albedoColorProperty")
        get() = getAlbedo()
        @JvmName("setAlbedoColorProperty")
        set(value) = setAlbedo(value)

    var albedoTexture: Texture2D?
        @JvmName("albedoTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.ALBEDO)
        @JvmName("setAlbedoTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.ALBEDO, value)

    var albedoTextureForceSrgb: Boolean
        @JvmName("albedoTextureForceSrgbProperty")
        get() = getFlag(BaseMaterial3D.Flags.ALBEDO_TEXTURE_FORCE_SRGB)
        @JvmName("setAlbedoTextureForceSrgbProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.ALBEDO_TEXTURE_FORCE_SRGB, value)

    var albedoTextureMsdf: Boolean
        @JvmName("albedoTextureMsdfProperty")
        get() = getFlag(BaseMaterial3D.Flags.ALBEDO_TEXTURE_MSDF)
        @JvmName("setAlbedoTextureMsdfProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.ALBEDO_TEXTURE_MSDF, value)

    var ormTexture: Texture2D?
        @JvmName("ormTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.ORM)
        @JvmName("setOrmTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.ORM, value)

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
        get() = getTexture(BaseMaterial3D.TextureParam.METALLIC)
        @JvmName("setMetallicTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.METALLIC, value)

    var metallicTextureChannel: BaseMaterial3D.TextureChannel
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
        get() = getTexture(BaseMaterial3D.TextureParam.ROUGHNESS)
        @JvmName("setRoughnessTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.ROUGHNESS, value)

    var roughnessTextureChannel: BaseMaterial3D.TextureChannel
        @JvmName("roughnessTextureChannelProperty")
        get() = getRoughnessTextureChannel()
        @JvmName("setRoughnessTextureChannelProperty")
        set(value) = setRoughnessTextureChannel(value)

    var emissionEnabled: Boolean
        @JvmName("emissionEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.EMISSION)
        @JvmName("setEmissionEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.EMISSION, value)

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

    var emissionOperator: BaseMaterial3D.EmissionOperator
        @JvmName("emissionOperatorProperty")
        get() = getEmissionOperator()
        @JvmName("setEmissionOperatorProperty")
        set(value) = setEmissionOperator(value)

    var emissionOnUv2: Boolean
        @JvmName("emissionOnUv2Property")
        get() = getFlag(BaseMaterial3D.Flags.EMISSION_ON_UV2)
        @JvmName("setEmissionOnUv2Property")
        set(value) = setFlag(BaseMaterial3D.Flags.EMISSION_ON_UV2, value)

    var emissionTexture: Texture2D?
        @JvmName("emissionTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.EMISSION)
        @JvmName("setEmissionTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.EMISSION, value)

    var normalEnabled: Boolean
        @JvmName("normalEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.NORMAL_MAPPING)
        @JvmName("setNormalEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.NORMAL_MAPPING, value)

    var normalScale: Double
        @JvmName("normalScaleProperty")
        get() = getNormalScale()
        @JvmName("setNormalScaleProperty")
        set(value) = setNormalScale(value)

    var normalTexture: Texture2D?
        @JvmName("normalTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.NORMAL)
        @JvmName("setNormalTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.NORMAL, value)

    var bentNormalEnabled: Boolean
        @JvmName("bentNormalEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.BENT_NORMAL_MAPPING)
        @JvmName("setBentNormalEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.BENT_NORMAL_MAPPING, value)

    var bentNormalTexture: Texture2D?
        @JvmName("bentNormalTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.BENT_NORMAL)
        @JvmName("setBentNormalTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.BENT_NORMAL, value)

    var rimEnabled: Boolean
        @JvmName("rimEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.RIM)
        @JvmName("setRimEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.RIM, value)

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
        get() = getTexture(BaseMaterial3D.TextureParam.RIM)
        @JvmName("setRimTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.RIM, value)

    var clearcoatEnabled: Boolean
        @JvmName("clearcoatEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.CLEARCOAT)
        @JvmName("setClearcoatEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.CLEARCOAT, value)

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
        get() = getTexture(BaseMaterial3D.TextureParam.CLEARCOAT)
        @JvmName("setClearcoatTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.CLEARCOAT, value)

    var anisotropyEnabled: Boolean
        @JvmName("anisotropyEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.ANISOTROPY)
        @JvmName("setAnisotropyEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.ANISOTROPY, value)

    var anisotropy: Double
        @JvmName("anisotropyProperty")
        get() = getAnisotropy()
        @JvmName("setAnisotropyProperty")
        set(value) = setAnisotropy(value)

    var anisotropyFlowmap: Texture2D?
        @JvmName("anisotropyFlowmapProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.FLOWMAP)
        @JvmName("setAnisotropyFlowmapProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.FLOWMAP, value)

    var aoEnabled: Boolean
        @JvmName("aoEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.AMBIENT_OCCLUSION)
        @JvmName("setAoEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.AMBIENT_OCCLUSION, value)

    var aoLightAffect: Double
        @JvmName("aoLightAffectProperty")
        get() = getAoLightAffect()
        @JvmName("setAoLightAffectProperty")
        set(value) = setAoLightAffect(value)

    var aoTexture: Texture2D?
        @JvmName("aoTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.AMBIENT_OCCLUSION)
        @JvmName("setAoTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.AMBIENT_OCCLUSION, value)

    var aoOnUv2: Boolean
        @JvmName("aoOnUv2Property")
        get() = getFlag(BaseMaterial3D.Flags.AO_ON_UV2)
        @JvmName("setAoOnUv2Property")
        set(value) = setFlag(BaseMaterial3D.Flags.AO_ON_UV2, value)

    var aoTextureChannel: BaseMaterial3D.TextureChannel
        @JvmName("aoTextureChannelProperty")
        get() = getAoTextureChannel()
        @JvmName("setAoTextureChannelProperty")
        set(value) = setAoTextureChannel(value)

    var heightmapEnabled: Boolean
        @JvmName("heightmapEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.HEIGHT_MAPPING)
        @JvmName("setHeightmapEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.HEIGHT_MAPPING, value)

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
        get() = getTexture(BaseMaterial3D.TextureParam.HEIGHTMAP)
        @JvmName("setHeightmapTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.HEIGHTMAP, value)

    var heightmapFlipTexture: Boolean
        @JvmName("heightmapFlipTextureProperty")
        get() = getFlag(BaseMaterial3D.Flags.INVERT_HEIGHTMAP)
        @JvmName("setHeightmapFlipTextureProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.INVERT_HEIGHTMAP, value)

    var subsurfScatterEnabled: Boolean
        @JvmName("subsurfScatterEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.SUBSURFACE_SCATTERING)
        @JvmName("setSubsurfScatterEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.SUBSURFACE_SCATTERING, value)

    var subsurfScatterStrength: Double
        @JvmName("subsurfScatterStrengthProperty")
        get() = getSubsurfaceScatteringStrength()
        @JvmName("setSubsurfScatterStrengthProperty")
        set(value) = setSubsurfaceScatteringStrength(value)

    var subsurfScatterSkinMode: Boolean
        @JvmName("subsurfScatterSkinModeProperty")
        get() = getFlag(BaseMaterial3D.Flags.SUBSURFACE_MODE_SKIN)
        @JvmName("setSubsurfScatterSkinModeProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.SUBSURFACE_MODE_SKIN, value)

    var subsurfScatterTexture: Texture2D?
        @JvmName("subsurfScatterTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.SUBSURFACE_SCATTERING)
        @JvmName("setSubsurfScatterTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.SUBSURFACE_SCATTERING, value)

    var subsurfScatterTransmittanceEnabled: Boolean
        @JvmName("subsurfScatterTransmittanceEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.SUBSURFACE_TRANSMITTANCE)
        @JvmName("setSubsurfScatterTransmittanceEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.SUBSURFACE_TRANSMITTANCE, value)

    var subsurfScatterTransmittanceColor: Color
        @JvmName("subsurfScatterTransmittanceColorProperty")
        get() = getTransmittanceColor()
        @JvmName("setSubsurfScatterTransmittanceColorProperty")
        set(value) = setTransmittanceColor(value)

    var subsurfScatterTransmittanceTexture: Texture2D?
        @JvmName("subsurfScatterTransmittanceTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.SUBSURFACE_TRANSMITTANCE)
        @JvmName("setSubsurfScatterTransmittanceTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.SUBSURFACE_TRANSMITTANCE, value)

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
        get() = getFeature(BaseMaterial3D.Feature.BACKLIGHT)
        @JvmName("setBacklightEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.BACKLIGHT, value)

    var backlight: Color
        @JvmName("backlightProperty")
        get() = getBacklight()
        @JvmName("setBacklightProperty")
        set(value) = setBacklight(value)

    var backlightTexture: Texture2D?
        @JvmName("backlightTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.BACKLIGHT)
        @JvmName("setBacklightTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.BACKLIGHT, value)

    var refractionEnabled: Boolean
        @JvmName("refractionEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.REFRACTION)
        @JvmName("setRefractionEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.REFRACTION, value)

    var refractionScale: Double
        @JvmName("refractionScaleProperty")
        get() = getRefraction()
        @JvmName("setRefractionScaleProperty")
        set(value) = setRefraction(value)

    var refractionTexture: Texture2D?
        @JvmName("refractionTextureProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.REFRACTION)
        @JvmName("setRefractionTextureProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.REFRACTION, value)

    var refractionTextureChannel: BaseMaterial3D.TextureChannel
        @JvmName("refractionTextureChannelProperty")
        get() = getRefractionTextureChannel()
        @JvmName("setRefractionTextureChannelProperty")
        set(value) = setRefractionTextureChannel(value)

    var detailEnabled: Boolean
        @JvmName("detailEnabledProperty")
        get() = getFeature(BaseMaterial3D.Feature.DETAIL)
        @JvmName("setDetailEnabledProperty")
        set(value) = setFeature(BaseMaterial3D.Feature.DETAIL, value)

    var detailMask: Texture2D?
        @JvmName("detailMaskProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.DETAIL_MASK)
        @JvmName("setDetailMaskProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.DETAIL_MASK, value)

    var detailBlendMode: BaseMaterial3D.BlendMode
        @JvmName("detailBlendModeProperty")
        get() = getDetailBlendMode()
        @JvmName("setDetailBlendModeProperty")
        set(value) = setDetailBlendMode(value)

    var detailUvLayer: BaseMaterial3D.DetailUV
        @JvmName("detailUvLayerProperty")
        get() = getDetailUv()
        @JvmName("setDetailUvLayerProperty")
        set(value) = setDetailUv(value)

    var detailAlbedo: Texture2D?
        @JvmName("detailAlbedoProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.DETAIL_ALBEDO)
        @JvmName("setDetailAlbedoProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.DETAIL_ALBEDO, value)

    var detailNormal: Texture2D?
        @JvmName("detailNormalProperty")
        get() = getTexture(BaseMaterial3D.TextureParam.DETAIL_NORMAL)
        @JvmName("setDetailNormalProperty")
        set(value) = setTexture(BaseMaterial3D.TextureParam.DETAIL_NORMAL, value)

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
        get() = getFlag(BaseMaterial3D.Flags.UV1_USE_TRIPLANAR)
        @JvmName("setUv1TriplanarProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.UV1_USE_TRIPLANAR, value)

    var uv1TriplanarSharpness: Double
        @JvmName("uv1TriplanarSharpnessProperty")
        get() = getUv1TriplanarBlendSharpness()
        @JvmName("setUv1TriplanarSharpnessProperty")
        set(value) = setUv1TriplanarBlendSharpness(value)

    var uv1WorldTriplanar: Boolean
        @JvmName("uv1WorldTriplanarProperty")
        get() = getFlag(BaseMaterial3D.Flags.UV1_USE_WORLD_TRIPLANAR)
        @JvmName("setUv1WorldTriplanarProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.UV1_USE_WORLD_TRIPLANAR, value)

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
        get() = getFlag(BaseMaterial3D.Flags.UV2_USE_TRIPLANAR)
        @JvmName("setUv2TriplanarProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.UV2_USE_TRIPLANAR, value)

    var uv2TriplanarSharpness: Double
        @JvmName("uv2TriplanarSharpnessProperty")
        get() = getUv2TriplanarBlendSharpness()
        @JvmName("setUv2TriplanarSharpnessProperty")
        set(value) = setUv2TriplanarBlendSharpness(value)

    var uv2WorldTriplanar: Boolean
        @JvmName("uv2WorldTriplanarProperty")
        get() = getFlag(BaseMaterial3D.Flags.UV2_USE_WORLD_TRIPLANAR)
        @JvmName("setUv2WorldTriplanarProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.UV2_USE_WORLD_TRIPLANAR, value)

    var textureFilter: BaseMaterial3D.TextureFilter
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var textureRepeat: Boolean
        @JvmName("textureRepeatProperty")
        get() = getFlag(BaseMaterial3D.Flags.USE_TEXTURE_REPEAT)
        @JvmName("setTextureRepeatProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.USE_TEXTURE_REPEAT, value)

    var disableReceiveShadows: Boolean
        @JvmName("disableReceiveShadowsProperty")
        get() = getFlag(BaseMaterial3D.Flags.DONT_RECEIVE_SHADOWS)
        @JvmName("setDisableReceiveShadowsProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.DONT_RECEIVE_SHADOWS, value)

    var shadowToOpacity: Boolean
        @JvmName("shadowToOpacityProperty")
        get() = getFlag(BaseMaterial3D.Flags.USE_SHADOW_TO_OPACITY)
        @JvmName("setShadowToOpacityProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.USE_SHADOW_TO_OPACITY, value)

    var billboardMode: BaseMaterial3D.BillboardMode
        @JvmName("billboardModeProperty")
        get() = getBillboardMode()
        @JvmName("setBillboardModeProperty")
        set(value) = setBillboardMode(value)

    var billboardKeepScale: Boolean
        @JvmName("billboardKeepScaleProperty")
        get() = getFlag(BaseMaterial3D.Flags.BILLBOARD_KEEP_SCALE)
        @JvmName("setBillboardKeepScaleProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.BILLBOARD_KEEP_SCALE, value)

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
        get() = getFlag(BaseMaterial3D.Flags.FIXED_SIZE)
        @JvmName("setFixedSizeProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.FIXED_SIZE, value)

    var usePointSize: Boolean
        @JvmName("usePointSizeProperty")
        get() = getFlag(BaseMaterial3D.Flags.USE_POINT_SIZE)
        @JvmName("setUsePointSizeProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.USE_POINT_SIZE, value)

    var pointSize: Double
        @JvmName("pointSizeProperty")
        get() = getPointSize()
        @JvmName("setPointSizeProperty")
        set(value) = setPointSize(value)

    var useParticleTrails: Boolean
        @JvmName("useParticleTrailsProperty")
        get() = getFlag(BaseMaterial3D.Flags.PARTICLE_TRAILS_MODE)
        @JvmName("setUseParticleTrailsProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.PARTICLE_TRAILS_MODE, value)

    var useZClipScale: Boolean
        @JvmName("useZClipScaleProperty")
        get() = getFlag(BaseMaterial3D.Flags.USE_Z_CLIP_SCALE)
        @JvmName("setUseZClipScaleProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.USE_Z_CLIP_SCALE, value)

    var zClipScale: Double
        @JvmName("zClipScaleProperty")
        get() = getZClipScale()
        @JvmName("setZClipScaleProperty")
        set(value) = setZClipScale(value)

    var useFovOverride: Boolean
        @JvmName("useFovOverrideProperty")
        get() = getFlag(BaseMaterial3D.Flags.USE_FOV_OVERRIDE)
        @JvmName("setUseFovOverrideProperty")
        set(value) = setFlag(BaseMaterial3D.Flags.USE_FOV_OVERRIDE, value)

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

    var distanceFadeMode: BaseMaterial3D.DistanceFadeMode
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

    var stencilMode: BaseMaterial3D.StencilMode
        @JvmName("stencilModeProperty")
        get() = getStencilMode()
        @JvmName("setStencilModeProperty")
        set(value) = setStencilMode(value)

    var stencilFlags: Int
        @JvmName("stencilFlagsProperty")
        get() = getStencilFlags()
        @JvmName("setStencilFlagsProperty")
        set(value) = setStencilFlags(value)

    var stencilCompare: BaseMaterial3D.StencilCompare
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
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setAlbedoBind, segment, albedo)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getAlbedoBind, segment)
    }

    /**
     * The material's transparency mode. Some transparency modes will disable shadow casting. Any
     * transparency mode other than `TRANSPARENCY_DISABLED` has a greater performance impact compared
     * to opaque rendering. See also `blend_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_transparency
     */
    fun setTransparency(transparency: BaseMaterial3D.Transparency) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTransparencyBind, segment, transparency.value)
    }

    /**
     * The material's transparency mode. Some transparency modes will disable shadow casting. Any
     * transparency mode other than `TRANSPARENCY_DISABLED` has a greater performance impact compared
     * to opaque rendering. See also `blend_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_transparency
     */
    fun getTransparency(): BaseMaterial3D.Transparency {
        checkOpen()
        return BaseMaterial3D.Transparency(ObjectCalls.ptrcallNoArgsRetLong(getTransparencyBind, segment))
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: BaseMaterial3D.set_alpha_antialiasing
     */
    fun setAlphaAntialiasing(alphaAa: BaseMaterial3D.AlphaAntiAliasing) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setAlphaAntialiasingBind, segment, alphaAa.value)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: BaseMaterial3D.get_alpha_antialiasing
     */
    fun getAlphaAntialiasing(): BaseMaterial3D.AlphaAntiAliasing {
        checkOpen()
        return BaseMaterial3D.AlphaAntiAliasing(ObjectCalls.ptrcallNoArgsRetLong(getAlphaAntialiasingBind, segment))
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: BaseMaterial3D.set_alpha_antialiasing_edge
     */
    fun setAlphaAntialiasingEdge(edge: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAlphaAntialiasingEdgeBind, segment, edge)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: BaseMaterial3D.get_alpha_antialiasing_edge
     */
    fun getAlphaAntialiasingEdge(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaAntialiasingEdgeBind, segment)
    }

    /**
     * Sets whether the shading takes place, per-pixel, per-vertex or unshaded. Per-vertex lighting is
     * faster, making it the best choice for mobile applications, however it looks considerably worse
     * than per-pixel. Unshaded rendering is the fastest, but disables all interactions with lights.
     *
     * Generated from Godot docs: BaseMaterial3D.set_shading_mode
     */
    fun setShadingMode(shadingMode: BaseMaterial3D.ShadingMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setShadingModeBind, segment, shadingMode.value)
    }

    /**
     * Sets whether the shading takes place, per-pixel, per-vertex or unshaded. Per-vertex lighting is
     * faster, making it the best choice for mobile applications, however it looks considerably worse
     * than per-pixel. Unshaded rendering is the fastest, but disables all interactions with lights.
     *
     * Generated from Godot docs: BaseMaterial3D.get_shading_mode
     */
    fun getShadingMode(): BaseMaterial3D.ShadingMode {
        checkOpen()
        return BaseMaterial3D.ShadingMode(ObjectCalls.ptrcallNoArgsRetLong(getShadingModeBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSpecularBind, segment, specular)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpecularBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMetallicBind, segment, metallic)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMetallicBind, segment)
    }

    /**
     * Surface reflection. A value of `0` represents a perfect mirror while a value of `1` completely
     * blurs the reflection. See also `metallic`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_roughness
     */
    fun setRoughness(roughness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRoughnessBind, segment, roughness)
    }

    /**
     * Surface reflection. A value of `0` represents a perfect mirror while a value of `1` completely
     * blurs the reflection. See also `metallic`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_roughness
     */
    fun getRoughness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRoughnessBind, segment)
    }

    /**
     * The emitted light's color. See `emission_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_emission
     */
    fun setEmission(emission: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setEmissionBind, segment, emission)
    }

    /**
     * The emitted light's color. See `emission_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_emission
     */
    fun getEmission(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getEmissionBind, segment)
    }

    /**
     * Multiplier for emitted light. See `emission_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_emission_energy_multiplier
     */
    fun setEmissionEnergyMultiplier(emissionEnergyMultiplier: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setEmissionEnergyMultiplierBind, segment, emissionEnergyMultiplier)
    }

    /**
     * Multiplier for emitted light. See `emission_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_emission_energy_multiplier
     */
    fun getEmissionEnergyMultiplier(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionEnergyMultiplierBind, segment)
    }

    /**
     * Luminance of emitted light, measured in nits (candela per square meter). Only available when
     * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is enabled. The default
     * is roughly equivalent to an indoor lightbulb.
     *
     * Generated from Godot docs: BaseMaterial3D.set_emission_intensity
     */
    fun setEmissionIntensity(emissionEnergyMultiplier: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setEmissionIntensityBind, segment, emissionEnergyMultiplier)
    }

    /**
     * Luminance of emitted light, measured in nits (candela per square meter). Only available when
     * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is enabled. The default
     * is roughly equivalent to an indoor lightbulb.
     *
     * Generated from Godot docs: BaseMaterial3D.get_emission_intensity
     */
    fun getEmissionIntensity(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionIntensityBind, segment)
    }

    /**
     * The strength of the normal map's effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_normal_scale
     */
    fun setNormalScale(normalScale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setNormalScaleBind, segment, normalScale)
    }

    /**
     * The strength of the normal map's effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_normal_scale
     */
    fun getNormalScale(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getNormalScaleBind, segment)
    }

    /**
     * Sets the strength of the rim lighting effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_rim
     */
    fun setRim(rim: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRimBind, segment, rim)
    }

    /**
     * Sets the strength of the rim lighting effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_rim
     */
    fun getRim(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRimBind, segment)
    }

    /**
     * The amount of to blend light and albedo color when rendering rim effect. If `0` the light color
     * is used, while `1` means albedo color is used. An intermediate value generally works best.
     *
     * Generated from Godot docs: BaseMaterial3D.set_rim_tint
     */
    fun setRimTint(rimTint: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRimTintBind, segment, rimTint)
    }

    /**
     * The amount of to blend light and albedo color when rendering rim effect. If `0` the light color
     * is used, while `1` means albedo color is used. An intermediate value generally works best.
     *
     * Generated from Godot docs: BaseMaterial3D.get_rim_tint
     */
    fun getRimTint(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRimTintBind, segment)
    }

    /**
     * Sets the strength of the clearcoat effect. Setting to `0` looks the same as disabling the
     * clearcoat effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_clearcoat
     */
    fun setClearcoat(clearcoat: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setClearcoatBind, segment, clearcoat)
    }

    /**
     * Sets the strength of the clearcoat effect. Setting to `0` looks the same as disabling the
     * clearcoat effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_clearcoat
     */
    fun getClearcoat(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getClearcoatBind, segment)
    }

    /**
     * Sets the roughness of the clearcoat pass. A higher value results in a rougher clearcoat while a
     * lower value results in a smoother clearcoat.
     *
     * Generated from Godot docs: BaseMaterial3D.set_clearcoat_roughness
     */
    fun setClearcoatRoughness(clearcoatRoughness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setClearcoatRoughnessBind, segment, clearcoatRoughness)
    }

    /**
     * Sets the roughness of the clearcoat pass. A higher value results in a rougher clearcoat while a
     * lower value results in a smoother clearcoat.
     *
     * Generated from Godot docs: BaseMaterial3D.get_clearcoat_roughness
     */
    fun getClearcoatRoughness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getClearcoatRoughnessBind, segment)
    }

    /**
     * The strength of the anisotropy effect. This is multiplied by `anisotropy_flowmap`'s alpha
     * channel if a texture is defined there and the texture contains an alpha channel.
     *
     * Generated from Godot docs: BaseMaterial3D.set_anisotropy
     */
    fun setAnisotropy(anisotropy: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAnisotropyBind, segment, anisotropy)
    }

    /**
     * The strength of the anisotropy effect. This is multiplied by `anisotropy_flowmap`'s alpha
     * channel if a texture is defined there and the texture contains an alpha channel.
     *
     * Generated from Godot docs: BaseMaterial3D.get_anisotropy
     */
    fun getAnisotropy(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAnisotropyBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setHeightmapScaleBind, segment, heightmapScale)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightmapScaleBind, segment)
    }

    /**
     * The strength of the subsurface scattering effect. The depth of the effect is also controlled by
     * `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_scale`, which
     * is set globally.
     *
     * Generated from Godot docs: BaseMaterial3D.set_subsurface_scattering_strength
     */
    fun setSubsurfaceScatteringStrength(strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSubsurfaceScatteringStrengthBind, segment, strength)
    }

    /**
     * The strength of the subsurface scattering effect. The depth of the effect is also controlled by
     * `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_scale`, which
     * is set globally.
     *
     * Generated from Godot docs: BaseMaterial3D.get_subsurface_scattering_strength
     */
    fun getSubsurfaceScatteringStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSubsurfaceScatteringStrengthBind, segment)
    }

    /**
     * The color to multiply the subsurface scattering transmittance effect with. Ignored if
     * `subsurf_scatter_skin_mode` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_transmittance_color
     */
    fun setTransmittanceColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setTransmittanceColorBind, segment, color)
    }

    /**
     * The color to multiply the subsurface scattering transmittance effect with. Ignored if
     * `subsurf_scatter_skin_mode` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_transmittance_color
     */
    fun getTransmittanceColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getTransmittanceColorBind, segment)
    }

    /**
     * The depth of the subsurface scattering transmittance effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_transmittance_depth
     */
    fun setTransmittanceDepth(depth: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setTransmittanceDepthBind, segment, depth)
    }

    /**
     * The depth of the subsurface scattering transmittance effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_transmittance_depth
     */
    fun getTransmittanceDepth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getTransmittanceDepthBind, segment)
    }

    /**
     * The intensity of the subsurface scattering transmittance effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_transmittance_boost
     */
    fun setTransmittanceBoost(boost: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setTransmittanceBoostBind, segment, boost)
    }

    /**
     * The intensity of the subsurface scattering transmittance effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_transmittance_boost
     */
    fun getTransmittanceBoost(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getTransmittanceBoostBind, segment)
    }

    /**
     * The color used by the backlight effect. Represents the light passing through an object.
     *
     * Generated from Godot docs: BaseMaterial3D.set_backlight
     */
    fun setBacklight(backlight: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setBacklightBind, segment, backlight)
    }

    /**
     * The color used by the backlight effect. Represents the light passing through an object.
     *
     * Generated from Godot docs: BaseMaterial3D.get_backlight
     */
    fun getBacklight(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getBacklightBind, segment)
    }

    /**
     * The strength of the refraction effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_refraction
     */
    fun setRefraction(refraction: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRefractionBind, segment, refraction)
    }

    /**
     * The strength of the refraction effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_refraction
     */
    fun getRefraction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRefractionBind, segment)
    }

    /**
     * The point size in pixels. See `use_point_size`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_point_size
     */
    fun setPointSize(pointSize: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPointSizeBind, segment, pointSize)
    }

    /**
     * The point size in pixels. See `use_point_size`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_point_size
     */
    fun getPointSize(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPointSizeBind, segment)
    }

    /**
     * Specifies whether to use `UV` or `UV2` for the detail layer.
     *
     * Generated from Godot docs: BaseMaterial3D.set_detail_uv
     */
    fun setDetailUv(detailUv: BaseMaterial3D.DetailUV) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDetailUvBind, segment, detailUv.value)
    }

    /**
     * Specifies whether to use `UV` or `UV2` for the detail layer.
     *
     * Generated from Godot docs: BaseMaterial3D.get_detail_uv
     */
    fun getDetailUv(): BaseMaterial3D.DetailUV {
        checkOpen()
        return BaseMaterial3D.DetailUV(ObjectCalls.ptrcallNoArgsRetLong(getDetailUvBind, segment))
    }

    /**
     * The material's blend mode. Note: Values other than `Mix` force the object into the transparent
     * pipeline.
     *
     * Generated from Godot docs: BaseMaterial3D.set_blend_mode
     */
    fun setBlendMode(blendMode: BaseMaterial3D.BlendMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, segment, blendMode.value)
    }

    /**
     * The material's blend mode. Note: Values other than `Mix` force the object into the transparent
     * pipeline.
     *
     * Generated from Godot docs: BaseMaterial3D.get_blend_mode
     */
    fun getBlendMode(): BaseMaterial3D.BlendMode {
        checkOpen()
        return BaseMaterial3D.BlendMode(ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, segment))
    }

    /**
     * Determines when depth rendering takes place. See also `transparency`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_depth_draw_mode
     */
    fun setDepthDrawMode(depthDrawMode: BaseMaterial3D.DepthDrawMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDepthDrawModeBind, segment, depthDrawMode.value)
    }

    /**
     * Determines when depth rendering takes place. See also `transparency`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_depth_draw_mode
     */
    fun getDepthDrawMode(): BaseMaterial3D.DepthDrawMode {
        checkOpen()
        return BaseMaterial3D.DepthDrawMode(ObjectCalls.ptrcallNoArgsRetLong(getDepthDrawModeBind, segment))
    }

    /**
     * Determines which comparison operator is used when testing depth. Note: Changing `depth_test` to
     * a non-default value only has a visible effect when used on a transparent material, or a material
     * that has `depth_draw_mode` set to `DEPTH_DRAW_DISABLED`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_depth_test
     */
    fun setDepthTest(depthTest: BaseMaterial3D.DepthTest) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDepthTestBind, segment, depthTest.value)
    }

    /**
     * Determines which comparison operator is used when testing depth. Note: Changing `depth_test` to
     * a non-default value only has a visible effect when used on a transparent material, or a material
     * that has `depth_draw_mode` set to `DEPTH_DRAW_DISABLED`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_depth_test
     */
    fun getDepthTest(): BaseMaterial3D.DepthTest {
        checkOpen()
        return BaseMaterial3D.DepthTest(ObjectCalls.ptrcallNoArgsRetLong(getDepthTestBind, segment))
    }

    /**
     * Determines which side of the triangle to cull depending on whether the triangle faces towards or
     * away from the camera.
     *
     * Generated from Godot docs: BaseMaterial3D.set_cull_mode
     */
    fun setCullMode(cullMode: BaseMaterial3D.CullMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCullModeBind, segment, cullMode.value)
    }

    /**
     * Determines which side of the triangle to cull depending on whether the triangle faces towards or
     * away from the camera.
     *
     * Generated from Godot docs: BaseMaterial3D.get_cull_mode
     */
    fun getCullMode(): BaseMaterial3D.CullMode {
        checkOpen()
        return BaseMaterial3D.CullMode(ObjectCalls.ptrcallNoArgsRetLong(getCullModeBind, segment))
    }

    /**
     * The algorithm used for diffuse light scattering.
     *
     * Generated from Godot docs: BaseMaterial3D.set_diffuse_mode
     */
    fun setDiffuseMode(diffuseMode: BaseMaterial3D.DiffuseMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDiffuseModeBind, segment, diffuseMode.value)
    }

    /**
     * The algorithm used for diffuse light scattering.
     *
     * Generated from Godot docs: BaseMaterial3D.get_diffuse_mode
     */
    fun getDiffuseMode(): BaseMaterial3D.DiffuseMode {
        checkOpen()
        return BaseMaterial3D.DiffuseMode(ObjectCalls.ptrcallNoArgsRetLong(getDiffuseModeBind, segment))
    }

    /**
     * The method for rendering the specular blob. Note: `specular_mode` only applies to the specular
     * blob. It does not affect specular reflections from the sky, screen-space reflections, `VoxelGI`,
     * SDFGI or `ReflectionProbe`s. To disable reflections from these sources as well, set
     * `metallic_specular` to `0.0` instead.
     *
     * Generated from Godot docs: BaseMaterial3D.set_specular_mode
     */
    fun setSpecularMode(specularMode: BaseMaterial3D.SpecularMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSpecularModeBind, segment, specularMode.value)
    }

    /**
     * The method for rendering the specular blob. Note: `specular_mode` only applies to the specular
     * blob. It does not affect specular reflections from the sky, screen-space reflections, `VoxelGI`,
     * SDFGI or `ReflectionProbe`s. To disable reflections from these sources as well, set
     * `metallic_specular` to `0.0` instead.
     *
     * Generated from Godot docs: BaseMaterial3D.get_specular_mode
     */
    fun getSpecularMode(): BaseMaterial3D.SpecularMode {
        checkOpen()
        return BaseMaterial3D.SpecularMode(ObjectCalls.ptrcallNoArgsRetLong(getSpecularModeBind, segment))
    }

    /**
     * If `true`, the vertex color is used as albedo color.
     *
     * Generated from Godot docs: BaseMaterial3D.set_flag
     */
    fun setFlag(flag: BaseMaterial3D.Flags, enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagBind, segment, flag.value, enable)
    }

    /**
     * If `true`, the vertex color is used as albedo color.
     *
     * Generated from Godot docs: BaseMaterial3D.get_flag
     */
    fun getFlag(flag: BaseMaterial3D.Flags): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagBind, segment, flag.value)
    }

    /**
     * Filter flags for the texture. Note: `heightmap_texture` is always sampled with linear filtering,
     * even if nearest-neighbor filtering is selected here. This is to ensure the heightmap effect
     * looks as intended. If you need sharper height transitions between pixels, resize the heightmap
     * texture in an image editor with nearest-neighbor filtering.
     *
     * Generated from Godot docs: BaseMaterial3D.set_texture_filter
     */
    fun setTextureFilter(mode: BaseMaterial3D.TextureFilter) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, segment, mode.value)
    }

    /**
     * Filter flags for the texture. Note: `heightmap_texture` is always sampled with linear filtering,
     * even if nearest-neighbor filtering is selected here. This is to ensure the heightmap effect
     * looks as intended. If you need sharper height transitions between pixels, resize the heightmap
     * texture in an image editor with nearest-neighbor filtering.
     *
     * Generated from Godot docs: BaseMaterial3D.get_texture_filter
     */
    fun getTextureFilter(): BaseMaterial3D.TextureFilter {
        checkOpen()
        return BaseMaterial3D.TextureFilter(ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, segment))
    }

    /**
     * If `true`, enables subsurface scattering transmittance. Only effective if
     * `subsurf_scatter_enabled` is `true`. See also `backlight_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_feature
     */
    fun setFeature(feature: BaseMaterial3D.Feature, enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFeatureBind, segment, feature.value, enable)
    }

    /**
     * If `true`, enables subsurface scattering transmittance. Only effective if
     * `subsurf_scatter_enabled` is `true`. See also `backlight_enabled`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_feature
     */
    fun getFeature(feature: BaseMaterial3D.Feature): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(getFeatureBind, segment, feature.value)
    }

    /**
     * The texture to use for multiplying the intensity of the subsurface scattering transmittance
     * intensity. See also `subsurf_scatter_texture`. Ignored if `subsurf_scatter_skin_mode` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_texture
     */
    fun setTexture(param: BaseMaterial3D.TextureParam, texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndObjectArg(setTextureBind, segment, param.value, texture?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * The texture to use for multiplying the intensity of the subsurface scattering transmittance
     * intensity. See also `subsurf_scatter_texture`. Ignored if `subsurf_scatter_skin_mode` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_texture
     */
    fun getTexture(param: BaseMaterial3D.TextureParam): Texture2D? {
        checkOpen()
        return Texture2D.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getTextureBind, segment, param.value))
    }

    /**
     * Specifies how the `detail_albedo` should blend with the current `ALBEDO`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_detail_blend_mode
     */
    fun setDetailBlendMode(detailBlendMode: BaseMaterial3D.BlendMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDetailBlendModeBind, segment, detailBlendMode.value)
    }

    /**
     * Specifies how the `detail_albedo` should blend with the current `ALBEDO`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_detail_blend_mode
     */
    fun getDetailBlendMode(): BaseMaterial3D.BlendMode {
        checkOpen()
        return BaseMaterial3D.BlendMode(ObjectCalls.ptrcallNoArgsRetLong(getDetailBlendModeBind, segment))
    }

    /**
     * How much to scale the `UV` coordinates. This is multiplied by `UV` in the vertex function. The Z
     * component is used when `uv1_triplanar` is enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv1_scale
     */
    fun setUv1Scale(scale: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setUv1ScaleBind, segment, scale)
    }

    /**
     * How much to scale the `UV` coordinates. This is multiplied by `UV` in the vertex function. The Z
     * component is used when `uv1_triplanar` is enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv1_scale
     */
    fun getUv1Scale(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getUv1ScaleBind, segment)
    }

    /**
     * How much to offset the `UV` coordinates. This amount will be added to `UV` in the vertex
     * function. This can be used to offset a texture. The Z component is used when `uv1_triplanar` is
     * enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv1_offset
     */
    fun setUv1Offset(offset: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setUv1OffsetBind, segment, offset)
    }

    /**
     * How much to offset the `UV` coordinates. This amount will be added to `UV` in the vertex
     * function. This can be used to offset a texture. The Z component is used when `uv1_triplanar` is
     * enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv1_offset
     */
    fun getUv1Offset(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getUv1OffsetBind, segment)
    }

    /**
     * A lower number blends the texture more softly while a higher number blends the texture more
     * sharply. Note: `uv1_triplanar_sharpness` is clamped between `0.0` and `150.0` (inclusive) as
     * values outside that range can look broken depending on the mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv1_triplanar_blend_sharpness
     */
    fun setUv1TriplanarBlendSharpness(sharpness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setUv1TriplanarBlendSharpnessBind, segment, sharpness)
    }

    /**
     * A lower number blends the texture more softly while a higher number blends the texture more
     * sharply. Note: `uv1_triplanar_sharpness` is clamped between `0.0` and `150.0` (inclusive) as
     * values outside that range can look broken depending on the mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv1_triplanar_blend_sharpness
     */
    fun getUv1TriplanarBlendSharpness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getUv1TriplanarBlendSharpnessBind, segment)
    }

    /**
     * How much to scale the `UV2` coordinates. This is multiplied by `UV2` in the vertex function. The
     * Z component is used when `uv2_triplanar` is enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv2_scale
     */
    fun setUv2Scale(scale: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setUv2ScaleBind, segment, scale)
    }

    /**
     * How much to scale the `UV2` coordinates. This is multiplied by `UV2` in the vertex function. The
     * Z component is used when `uv2_triplanar` is enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv2_scale
     */
    fun getUv2Scale(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getUv2ScaleBind, segment)
    }

    /**
     * How much to offset the `UV2` coordinates. This amount will be added to `UV2` in the vertex
     * function. This can be used to offset a texture. The Z component is used when `uv2_triplanar` is
     * enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv2_offset
     */
    fun setUv2Offset(offset: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setUv2OffsetBind, segment, offset)
    }

    /**
     * How much to offset the `UV2` coordinates. This amount will be added to `UV2` in the vertex
     * function. This can be used to offset a texture. The Z component is used when `uv2_triplanar` is
     * enabled, but it is not used anywhere else.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv2_offset
     */
    fun getUv2Offset(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getUv2OffsetBind, segment)
    }

    /**
     * A lower number blends the texture more softly while a higher number blends the texture more
     * sharply. Note: `uv2_triplanar_sharpness` is clamped between `0.0` and `150.0` (inclusive) as
     * values outside that range can look broken depending on the mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.set_uv2_triplanar_blend_sharpness
     */
    fun setUv2TriplanarBlendSharpness(sharpness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setUv2TriplanarBlendSharpnessBind, segment, sharpness)
    }

    /**
     * A lower number blends the texture more softly while a higher number blends the texture more
     * sharply. Note: `uv2_triplanar_sharpness` is clamped between `0.0` and `150.0` (inclusive) as
     * values outside that range can look broken depending on the mesh.
     *
     * Generated from Godot docs: BaseMaterial3D.get_uv2_triplanar_blend_sharpness
     */
    fun getUv2TriplanarBlendSharpness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getUv2TriplanarBlendSharpnessBind, segment)
    }

    /**
     * Controls how the object faces the camera. Note: Billboard mode is not suitable for VR because
     * the left-right vector of the camera is not horizontal when the screen is attached to your head
     * instead of on the table. See GitHub issue #41567
     * (https://github.com/godotengine/godot/issues/41567) for details.
     *
     * Generated from Godot docs: BaseMaterial3D.set_billboard_mode
     */
    fun setBillboardMode(mode: BaseMaterial3D.BillboardMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBillboardModeBind, segment, mode.value)
    }

    /**
     * Controls how the object faces the camera. Note: Billboard mode is not suitable for VR because
     * the left-right vector of the camera is not horizontal when the screen is attached to your head
     * instead of on the table. See GitHub issue #41567
     * (https://github.com/godotengine/godot/issues/41567) for details.
     *
     * Generated from Godot docs: BaseMaterial3D.get_billboard_mode
     */
    fun getBillboardMode(): BaseMaterial3D.BillboardMode {
        checkOpen()
        return BaseMaterial3D.BillboardMode(ObjectCalls.ptrcallNoArgsRetLong(getBillboardModeBind, segment))
    }

    /**
     * The number of horizontal frames in the particle sprite sheet. Only enabled when using
     * `BILLBOARD_PARTICLES`. See `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_particles_anim_h_frames
     */
    fun setParticlesAnimHFrames(frames: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setParticlesAnimHFramesBind, segment, frames)
    }

    /**
     * The number of horizontal frames in the particle sprite sheet. Only enabled when using
     * `BILLBOARD_PARTICLES`. See `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_particles_anim_h_frames
     */
    fun getParticlesAnimHFrames(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getParticlesAnimHFramesBind, segment)
    }

    /**
     * The number of vertical frames in the particle sprite sheet. Only enabled when using
     * `BILLBOARD_PARTICLES`. See `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_particles_anim_v_frames
     */
    fun setParticlesAnimVFrames(frames: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setParticlesAnimVFramesBind, segment, frames)
    }

    /**
     * The number of vertical frames in the particle sprite sheet. Only enabled when using
     * `BILLBOARD_PARTICLES`. See `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_particles_anim_v_frames
     */
    fun getParticlesAnimVFrames(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getParticlesAnimVFramesBind, segment)
    }

    /**
     * If `true`, particle animations are looped. Only enabled when using `BILLBOARD_PARTICLES`. See
     * `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_particles_anim_loop
     */
    fun setParticlesAnimLoop(loop: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setParticlesAnimLoopBind, segment, loop)
    }

    /**
     * If `true`, particle animations are looped. Only enabled when using `BILLBOARD_PARTICLES`. See
     * `billboard_mode`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_particles_anim_loop
     */
    fun getParticlesAnimLoop(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getParticlesAnimLoopBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setHeightmapDeepParallaxBind, segment, enable)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isHeightmapDeepParallaxEnabledBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setHeightmapDeepParallaxMinLayersBind, segment, layer)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightmapDeepParallaxMinLayersBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setHeightmapDeepParallaxMaxLayersBind, segment, layer)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightmapDeepParallaxMaxLayersBind, segment)
    }

    /**
     * If `true`, flips the mesh's tangent vectors when interpreting the height map. If the heightmap
     * effect looks strange when the camera moves (even with a reasonable `heightmap_scale`), try
     * setting this to `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_heightmap_deep_parallax_flip_tangent
     */
    fun setHeightmapDeepParallaxFlipTangent(flip: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setHeightmapDeepParallaxFlipTangentBind, segment, flip)
    }

    /**
     * If `true`, flips the mesh's tangent vectors when interpreting the height map. If the heightmap
     * effect looks strange when the camera moves (even with a reasonable `heightmap_scale`), try
     * setting this to `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_heightmap_deep_parallax_flip_tangent
     */
    fun getHeightmapDeepParallaxFlipTangent(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getHeightmapDeepParallaxFlipTangentBind, segment)
    }

    /**
     * If `true`, flips the mesh's binormal vectors when interpreting the height map. If the heightmap
     * effect looks strange when the camera moves (even with a reasonable `heightmap_scale`), try
     * setting this to `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_heightmap_deep_parallax_flip_binormal
     */
    fun setHeightmapDeepParallaxFlipBinormal(flip: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setHeightmapDeepParallaxFlipBinormalBind, segment, flip)
    }

    /**
     * If `true`, flips the mesh's binormal vectors when interpreting the height map. If the heightmap
     * effect looks strange when the camera moves (even with a reasonable `heightmap_scale`), try
     * setting this to `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_heightmap_deep_parallax_flip_binormal
     */
    fun getHeightmapDeepParallaxFlipBinormal(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getHeightmapDeepParallaxFlipBinormalBind, segment)
    }

    /**
     * Grows object vertices in the direction of their normals. Only effective if `grow` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_grow
     */
    fun setGrow(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setGrowBind, segment, amount)
    }

    /**
     * Grows object vertices in the direction of their normals. Only effective if `grow` is `true`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_grow
     */
    fun getGrow(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getGrowBind, segment)
    }

    /**
     * Sets how `emission` interacts with `emission_texture`. Can either add or multiply.
     *
     * Generated from Godot docs: BaseMaterial3D.set_emission_operator
     */
    fun setEmissionOperator(operator: BaseMaterial3D.EmissionOperator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setEmissionOperatorBind, segment, operator.value)
    }

    /**
     * Sets how `emission` interacts with `emission_texture`. Can either add or multiply.
     *
     * Generated from Godot docs: BaseMaterial3D.get_emission_operator
     */
    fun getEmissionOperator(): BaseMaterial3D.EmissionOperator {
        checkOpen()
        return BaseMaterial3D.EmissionOperator(ObjectCalls.ptrcallNoArgsRetLong(getEmissionOperatorBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAoLightAffectBind, segment, amount)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAoLightAffectBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAlphaScissorThresholdBind, segment, threshold)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaScissorThresholdBind, segment)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_alpha_hash_scale
     */
    fun setAlphaHashScale(threshold: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAlphaHashScaleBind, segment, threshold)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_alpha_hash_scale
     */
    fun getAlphaHashScale(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaHashScaleBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setGrowEnabledBind, segment, enable)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isGrowEnabledBind, segment)
    }

    /**
     * Specifies the channel of the `metallic_texture` in which the metallic information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.set_metallic_texture_channel
     */
    fun setMetallicTextureChannel(channel: BaseMaterial3D.TextureChannel) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setMetallicTextureChannelBind, segment, channel.value)
    }

    /**
     * Specifies the channel of the `metallic_texture` in which the metallic information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.get_metallic_texture_channel
     */
    fun getMetallicTextureChannel(): BaseMaterial3D.TextureChannel {
        checkOpen()
        return BaseMaterial3D.TextureChannel(ObjectCalls.ptrcallNoArgsRetLong(getMetallicTextureChannelBind, segment))
    }

    /**
     * Specifies the channel of the `roughness_texture` in which the roughness information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.set_roughness_texture_channel
     */
    fun setRoughnessTextureChannel(channel: BaseMaterial3D.TextureChannel) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setRoughnessTextureChannelBind, segment, channel.value)
    }

    /**
     * Specifies the channel of the `roughness_texture` in which the roughness information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.get_roughness_texture_channel
     */
    fun getRoughnessTextureChannel(): BaseMaterial3D.TextureChannel {
        checkOpen()
        return BaseMaterial3D.TextureChannel(ObjectCalls.ptrcallNoArgsRetLong(getRoughnessTextureChannelBind, segment))
    }

    /**
     * Specifies the channel of the `ao_texture` in which the ambient occlusion information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.set_ao_texture_channel
     */
    fun setAoTextureChannel(channel: BaseMaterial3D.TextureChannel) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setAoTextureChannelBind, segment, channel.value)
    }

    /**
     * Specifies the channel of the `ao_texture` in which the ambient occlusion information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored metallic in the red channel, roughness in the blue, and ambient occlusion
     * in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.get_ao_texture_channel
     */
    fun getAoTextureChannel(): BaseMaterial3D.TextureChannel {
        checkOpen()
        return BaseMaterial3D.TextureChannel(ObjectCalls.ptrcallNoArgsRetLong(getAoTextureChannelBind, segment))
    }

    /**
     * Specifies the channel of the `refraction_texture` in which the refraction information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored refraction in the red channel, roughness in the blue, and ambient
     * occlusion in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.set_refraction_texture_channel
     */
    fun setRefractionTextureChannel(channel: BaseMaterial3D.TextureChannel) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setRefractionTextureChannelBind, segment, channel.value)
    }

    /**
     * Specifies the channel of the `refraction_texture` in which the refraction information is stored.
     * This is useful when you store the information for multiple effects in a single texture. For
     * example if you stored refraction in the red channel, roughness in the blue, and ambient
     * occlusion in the green you could reduce the number of textures you use.
     *
     * Generated from Godot docs: BaseMaterial3D.get_refraction_texture_channel
     */
    fun getRefractionTextureChannel(): BaseMaterial3D.TextureChannel {
        checkOpen()
        return BaseMaterial3D.TextureChannel(ObjectCalls.ptrcallNoArgsRetLong(getRefractionTextureChannelBind, segment))
    }

    /**
     * If `true`, the proximity fade effect is enabled. The proximity fade effect fades out each pixel
     * based on its distance to another object.
     *
     * Generated from Godot docs: BaseMaterial3D.set_proximity_fade_enabled
     */
    fun setProximityFadeEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setProximityFadeEnabledBind, segment, enabled)
    }

    /**
     * If `true`, the proximity fade effect is enabled. The proximity fade effect fades out each pixel
     * based on its distance to another object.
     *
     * Generated from Godot docs: BaseMaterial3D.is_proximity_fade_enabled
     */
    fun isProximityFadeEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isProximityFadeEnabledBind, segment)
    }

    /**
     * Distance over which the fade effect takes place. The larger the distance the longer it takes for
     * an object to fade.
     *
     * Generated from Godot docs: BaseMaterial3D.set_proximity_fade_distance
     */
    fun setProximityFadeDistance(distance: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setProximityFadeDistanceBind, segment, distance)
    }

    /**
     * Distance over which the fade effect takes place. The larger the distance the longer it takes for
     * an object to fade.
     *
     * Generated from Godot docs: BaseMaterial3D.get_proximity_fade_distance
     */
    fun getProximityFadeDistance(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getProximityFadeDistanceBind, segment)
    }

    /**
     * The width of the range around the shape between the minimum and maximum representable signed
     * distance.
     *
     * Generated from Godot docs: BaseMaterial3D.set_msdf_pixel_range
     */
    fun setMsdfPixelRange(range: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMsdfPixelRangeBind, segment, range)
    }

    /**
     * The width of the range around the shape between the minimum and maximum representable signed
     * distance.
     *
     * Generated from Godot docs: BaseMaterial3D.get_msdf_pixel_range
     */
    fun getMsdfPixelRange(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMsdfPixelRangeBind, segment)
    }

    /**
     * The width of the shape outline.
     *
     * Generated from Godot docs: BaseMaterial3D.set_msdf_outline_size
     */
    fun setMsdfOutlineSize(size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMsdfOutlineSizeBind, segment, size)
    }

    /**
     * The width of the shape outline.
     *
     * Generated from Godot docs: BaseMaterial3D.get_msdf_outline_size
     */
    fun getMsdfOutlineSize(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMsdfOutlineSizeBind, segment)
    }

    /**
     * Specifies which type of fade to use. Can be any of the `DistanceFadeMode`s.
     *
     * Generated from Godot docs: BaseMaterial3D.set_distance_fade
     */
    fun setDistanceFade(mode: BaseMaterial3D.DistanceFadeMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDistanceFadeBind, segment, mode.value)
    }

    /**
     * Specifies which type of fade to use. Can be any of the `DistanceFadeMode`s.
     *
     * Generated from Godot docs: BaseMaterial3D.get_distance_fade
     */
    fun getDistanceFade(): BaseMaterial3D.DistanceFadeMode {
        checkOpen()
        return BaseMaterial3D.DistanceFadeMode(ObjectCalls.ptrcallNoArgsRetLong(getDistanceFadeBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeMaxDistanceBind, segment, distance)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeMaxDistanceBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeMinDistanceBind, segment, distance)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeMinDistanceBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setZClipScaleBind, segment, scale)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getZClipScaleBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFovOverrideBind, segment, scale)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFovOverrideBind, segment)
    }

    /**
     * The stencil effect mode.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_mode
     */
    fun setStencilMode(stencilMode: BaseMaterial3D.StencilMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setStencilModeBind, segment, stencilMode.value)
    }

    /**
     * The stencil effect mode.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_mode
     */
    fun getStencilMode(): BaseMaterial3D.StencilMode {
        checkOpen()
        return BaseMaterial3D.StencilMode(ObjectCalls.ptrcallNoArgsRetLong(getStencilModeBind, segment))
    }

    /**
     * The flags dictating how the stencil operation behaves.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_flags
     */
    fun setStencilFlags(stencilFlags: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setStencilFlagsBind, segment, stencilFlags)
    }

    /**
     * The flags dictating how the stencil operation behaves.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_flags
     */
    fun getStencilFlags(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getStencilFlagsBind, segment)
    }

    /**
     * The comparison operator to use for stencil masking operations.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_compare
     */
    fun setStencilCompare(stencilCompare: BaseMaterial3D.StencilCompare) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setStencilCompareBind, segment, stencilCompare.value)
    }

    /**
     * The comparison operator to use for stencil masking operations.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_compare
     */
    fun getStencilCompare(): BaseMaterial3D.StencilCompare {
        checkOpen()
        return BaseMaterial3D.StencilCompare(ObjectCalls.ptrcallNoArgsRetLong(getStencilCompareBind, segment))
    }

    /**
     * The stencil reference value (0-255). Typically a power of 2.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_reference
     */
    fun setStencilReference(stencilReference: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setStencilReferenceBind, segment, stencilReference)
    }

    /**
     * The stencil reference value (0-255). Typically a power of 2.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_reference
     */
    fun getStencilReference(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getStencilReferenceBind, segment)
    }

    /**
     * The primary color of the stencil effect.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_effect_color
     */
    fun setStencilEffectColor(stencilColor: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setStencilEffectColorBind, segment, stencilColor)
    }

    /**
     * The primary color of the stencil effect.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_effect_color
     */
    fun getStencilEffectColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getStencilEffectColorBind, segment)
    }

    /**
     * The outline thickness for `STENCIL_MODE_OUTLINE`.
     *
     * Generated from Godot docs: BaseMaterial3D.set_stencil_effect_outline_thickness
     */
    fun setStencilEffectOutlineThickness(stencilOutlineThickness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setStencilEffectOutlineThicknessBind, segment, stencilOutlineThickness)
    }

    /**
     * The outline thickness for `STENCIL_MODE_OUTLINE`.
     *
     * Generated from Godot docs: BaseMaterial3D.get_stencil_effect_outline_thickness
     */
    fun getStencilEffectOutlineThickness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getStencilEffectOutlineThicknessBind, segment)
    }

    @JvmInline
    value class TextureParam(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Texture specifying per-pixel color.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_ALBEDO
             */
            val ALBEDO: TextureParam get() = TextureParam(0L)
            /**
             * Texture specifying per-pixel metallic value.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_METALLIC
             */
            val METALLIC: TextureParam get() = TextureParam(1L)
            /**
             * Texture specifying per-pixel roughness value.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_ROUGHNESS
             */
            val ROUGHNESS: TextureParam get() = TextureParam(2L)
            /**
             * Texture specifying per-pixel emission color.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_EMISSION
             */
            val EMISSION: TextureParam get() = TextureParam(3L)
            /**
             * Texture specifying per-pixel normal vector.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_NORMAL
             */
            val NORMAL: TextureParam get() = TextureParam(4L)
            /**
             * Texture specifying per-pixel bent normal vector.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_BENT_NORMAL
             */
            val BENT_NORMAL: TextureParam get() = TextureParam(18L)
            /**
             * Texture specifying per-pixel rim value.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_RIM
             */
            val RIM: TextureParam get() = TextureParam(5L)
            /**
             * Texture specifying per-pixel clearcoat value.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_CLEARCOAT
             */
            val CLEARCOAT: TextureParam get() = TextureParam(6L)
            /**
             * Texture specifying per-pixel flowmap direction for use with `anisotropy`.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_FLOWMAP
             */
            val FLOWMAP: TextureParam get() = TextureParam(7L)
            /**
             * Texture specifying per-pixel ambient occlusion value.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_AMBIENT_OCCLUSION
             */
            val AMBIENT_OCCLUSION: TextureParam get() = TextureParam(8L)
            /**
             * Texture specifying per-pixel height.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_HEIGHTMAP
             */
            val HEIGHTMAP: TextureParam get() = TextureParam(9L)
            /**
             * Texture specifying per-pixel subsurface scattering.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_SUBSURFACE_SCATTERING
             */
            val SUBSURFACE_SCATTERING: TextureParam get() = TextureParam(10L)
            /**
             * Texture specifying per-pixel transmittance for subsurface scattering.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_SUBSURFACE_TRANSMITTANCE
             */
            val SUBSURFACE_TRANSMITTANCE: TextureParam get() = TextureParam(11L)
            /**
             * Texture specifying per-pixel backlight color.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_BACKLIGHT
             */
            val BACKLIGHT: TextureParam get() = TextureParam(12L)
            /**
             * Texture specifying per-pixel refraction strength.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_REFRACTION
             */
            val REFRACTION: TextureParam get() = TextureParam(13L)
            /**
             * Texture specifying per-pixel detail mask blending value.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_DETAIL_MASK
             */
            val DETAIL_MASK: TextureParam get() = TextureParam(14L)
            /**
             * Texture specifying per-pixel detail color.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_DETAIL_ALBEDO
             */
            val DETAIL_ALBEDO: TextureParam get() = TextureParam(15L)
            /**
             * Texture specifying per-pixel detail normal.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_DETAIL_NORMAL
             */
            val DETAIL_NORMAL: TextureParam get() = TextureParam(16L)
            /**
             * Texture holding ambient occlusion, roughness, and metallic.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_ORM
             */
            val ORM: TextureParam get() = TextureParam(17L)
            /**
             * Represents the size of the `TextureParam` enum.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_MAX
             */
            val MAX: TextureParam get() = TextureParam(19L)
        }
    }

    @JvmInline
    value class TextureFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The texture filter reads from the nearest pixel only. This makes the texture look pixelated from
             * up close, and grainy from a distance (due to mipmaps not being sampled).
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_FILTER_NEAREST
             */
            val NEAREST: TextureFilter get() = TextureFilter(0L)
            /**
             * The texture filter blends between the nearest 4 pixels. This makes the texture look smooth from
             * up close, and grainy from a distance (due to mipmaps not being sampled).
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_FILTER_LINEAR
             */
            val LINEAR: TextureFilter get() = TextureFilter(1L)
            /**
             * The texture filter reads from the nearest pixel and blends between the nearest 2 mipmaps (or
             * uses the nearest mipmap if
             * `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter` is `true`). This
             * makes the texture look pixelated from up close, and smooth from a distance.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS
             */
            val NEAREST_WITH_MIPMAPS: TextureFilter get() = TextureFilter(2L)
            /**
             * The texture filter blends between the nearest 4 pixels and between the nearest 2 mipmaps (or
             * uses the nearest mipmap if
             * `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter` is `true`). This
             * makes the texture look smooth from up close, and smooth from a distance.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS
             */
            val LINEAR_WITH_MIPMAPS: TextureFilter get() = TextureFilter(3L)
            /**
             * The texture filter reads from the nearest pixel and blends between 2 mipmaps (or uses the
             * nearest mipmap if `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter`
             * is `true`) based on the angle between the surface and the camera view. This makes the texture
             * look pixelated from up close, and smooth from a distance. Anisotropic filtering improves texture
             * quality on surfaces that are almost in line with the camera, but is slightly slower. The
             * anisotropic filtering level can be changed by adjusting
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC
             */
            val NEAREST_WITH_MIPMAPS_ANISOTROPIC: TextureFilter get() = TextureFilter(4L)
            /**
             * The texture filter blends between the nearest 4 pixels and blends between 2 mipmaps (or uses the
             * nearest mipmap if `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter`
             * is `true`) based on the angle between the surface and the camera view. This makes the texture
             * look smooth from up close, and smooth from a distance. Anisotropic filtering improves texture
             * quality on surfaces that are almost in line with the camera, but is slightly slower. The
             * anisotropic filtering level can be changed by adjusting
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC
             */
            val LINEAR_WITH_MIPMAPS_ANISOTROPIC: TextureFilter get() = TextureFilter(5L)
            /**
             * Represents the size of the `TextureFilter` enum.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_FILTER_MAX
             */
            val MAX: TextureFilter get() = TextureFilter(6L)
        }
    }

    @JvmInline
    value class DetailUV(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use `UV` with the detail texture.
             *
             * Generated from Godot docs: BaseMaterial3D.DETAIL_UV_1
             */
            val UV_1: DetailUV get() = DetailUV(0L)
            /**
             * Use `UV2` with the detail texture.
             *
             * Generated from Godot docs: BaseMaterial3D.DETAIL_UV_2
             */
            val UV_2: DetailUV get() = DetailUV(1L)
        }
    }

    @JvmInline
    value class Transparency(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The material will not use transparency. This is the fastest to render.
             *
             * Generated from Godot docs: BaseMaterial3D.TRANSPARENCY_DISABLED
             */
            val DISABLED: Transparency get() = Transparency(0L)
            /**
             * The material will use the texture's alpha values for transparency. This is the slowest to
             * render, and disables shadow casting.
             *
             * Generated from Godot docs: BaseMaterial3D.TRANSPARENCY_ALPHA
             */
            val ALPHA: Transparency get() = Transparency(1L)
            /**
             * The material will cut off all values below a threshold, the rest will remain opaque. The opaque
             * portions will be rendered in the depth prepass. This is faster to render than alpha blending,
             * but slower than opaque rendering. This also supports casting shadows.
             *
             * Generated from Godot docs: BaseMaterial3D.TRANSPARENCY_ALPHA_SCISSOR
             */
            val ALPHA_SCISSOR: Transparency get() = Transparency(2L)
            /**
             * The material will cut off all values below a spatially-deterministic threshold, the rest will
             * remain opaque. This is faster to render than alpha blending, but slower than opaque rendering.
             * This also supports casting shadows. Alpha hashing is suited for hair rendering.
             *
             * Generated from Godot docs: BaseMaterial3D.TRANSPARENCY_ALPHA_HASH
             */
            val ALPHA_HASH: Transparency get() = Transparency(3L)
            /**
             * The material will use the texture's alpha value for transparency, but will discard fragments
             * with an alpha of less than 0.99 during the depth prepass and fragments with an alpha less than
             * 0.1 during the shadow pass. This also supports casting shadows.
             *
             * Generated from Godot docs: BaseMaterial3D.TRANSPARENCY_ALPHA_DEPTH_PRE_PASS
             */
            val ALPHA_DEPTH_PRE_PASS: Transparency get() = Transparency(4L)
            /**
             * Represents the size of the `Transparency` enum.
             *
             * Generated from Godot docs: BaseMaterial3D.TRANSPARENCY_MAX
             */
            val MAX: Transparency get() = Transparency(5L)
        }
    }

    @JvmInline
    value class ShadingMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The object will not receive shadows. This is the fastest to render, but it disables all
             * interactions with lights.
             *
             * Generated from Godot docs: BaseMaterial3D.SHADING_MODE_UNSHADED
             */
            val UNSHADED: ShadingMode get() = ShadingMode(0L)
            /**
             * The object will be shaded per pixel. Useful for realistic shading effects.
             *
             * Generated from Godot docs: BaseMaterial3D.SHADING_MODE_PER_PIXEL
             */
            val PER_PIXEL: ShadingMode get() = ShadingMode(1L)
            /**
             * The object will be shaded per vertex. Useful when you want cheaper shaders and do not care about
             * visual quality.
             *
             * Generated from Godot docs: BaseMaterial3D.SHADING_MODE_PER_VERTEX
             */
            val PER_VERTEX: ShadingMode get() = ShadingMode(2L)
            /**
             * Represents the size of the `ShadingMode` enum.
             *
             * Generated from Godot docs: BaseMaterial3D.SHADING_MODE_MAX
             */
            val MAX: ShadingMode get() = ShadingMode(3L)
        }
    }

    @JvmInline
    value class Feature(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Constant for setting `emission_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_EMISSION
             */
            val EMISSION: Feature get() = Feature(0L)
            /**
             * Constant for setting `normal_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_NORMAL_MAPPING
             */
            val NORMAL_MAPPING: Feature get() = Feature(1L)
            /**
             * Constant for setting `rim_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_RIM
             */
            val RIM: Feature get() = Feature(2L)
            /**
             * Constant for setting `clearcoat_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_CLEARCOAT
             */
            val CLEARCOAT: Feature get() = Feature(3L)
            /**
             * Constant for setting `anisotropy_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_ANISOTROPY
             */
            val ANISOTROPY: Feature get() = Feature(4L)
            /**
             * Constant for setting `ao_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_AMBIENT_OCCLUSION
             */
            val AMBIENT_OCCLUSION: Feature get() = Feature(5L)
            /**
             * Constant for setting `heightmap_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_HEIGHT_MAPPING
             */
            val HEIGHT_MAPPING: Feature get() = Feature(6L)
            /**
             * Constant for setting `subsurf_scatter_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_SUBSURFACE_SCATTERING
             */
            val SUBSURFACE_SCATTERING: Feature get() = Feature(7L)
            /**
             * Constant for setting `subsurf_scatter_transmittance_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_SUBSURFACE_TRANSMITTANCE
             */
            val SUBSURFACE_TRANSMITTANCE: Feature get() = Feature(8L)
            /**
             * Constant for setting `backlight_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_BACKLIGHT
             */
            val BACKLIGHT: Feature get() = Feature(9L)
            /**
             * Constant for setting `refraction_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_REFRACTION
             */
            val REFRACTION: Feature get() = Feature(10L)
            /**
             * Constant for setting `detail_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_DETAIL
             */
            val DETAIL: Feature get() = Feature(11L)
            /**
             * Constant for setting `bent_normal_enabled`.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_BENT_NORMAL_MAPPING
             */
            val BENT_NORMAL_MAPPING: Feature get() = Feature(12L)
            /**
             * Represents the size of the `Feature` enum.
             *
             * Generated from Godot docs: BaseMaterial3D.FEATURE_MAX
             */
            val MAX: Feature get() = Feature(13L)
        }
    }

    @JvmInline
    value class BlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Default blend mode. The color of the object is blended over the background based on the object's
             * alpha value.
             *
             * Generated from Godot docs: BaseMaterial3D.BLEND_MODE_MIX
             */
            val MIX: BlendMode get() = BlendMode(0L)
            /**
             * The color of the object is added to the background.
             *
             * Generated from Godot docs: BaseMaterial3D.BLEND_MODE_ADD
             */
            val ADD: BlendMode get() = BlendMode(1L)
            /**
             * The color of the object is subtracted from the background.
             *
             * Generated from Godot docs: BaseMaterial3D.BLEND_MODE_SUB
             */
            val SUB: BlendMode get() = BlendMode(2L)
            /**
             * The color of the object is multiplied by the background.
             *
             * Generated from Godot docs: BaseMaterial3D.BLEND_MODE_MUL
             */
            val MUL: BlendMode get() = BlendMode(3L)
            /**
             * The color of the object is added to the background and the alpha channel is used to mask out the
             * background. This is effectively a hybrid of the blend mix and add modes, useful for effects like
             * fire where you want the flame to add but the smoke to mix. By default, this works with unshaded
             * materials using premultiplied textures. For shaded materials, use the `PREMUL_ALPHA_FACTOR`
             * built-in so that lighting can be modulated as well.
             *
             * Generated from Godot docs: BaseMaterial3D.BLEND_MODE_PREMULT_ALPHA
             */
            val PREMULT_ALPHA: BlendMode get() = BlendMode(4L)
        }
    }

    @JvmInline
    value class AlphaAntiAliasing(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disables Alpha AntiAliasing for the material.
             *
             * Generated from Godot docs: BaseMaterial3D.ALPHA_ANTIALIASING_OFF
             */
            val OFF: AlphaAntiAliasing get() = AlphaAntiAliasing(0L)
            /**
             * Enables AlphaToCoverage. Alpha values in the material are passed to the AntiAliasing sample
             * mask.
             *
             * Generated from Godot docs: BaseMaterial3D.ALPHA_ANTIALIASING_ALPHA_TO_COVERAGE
             */
            val ALPHA_TO_COVERAGE: AlphaAntiAliasing get() = AlphaAntiAliasing(1L)
            /**
             * Enables AlphaToCoverage and forces all non-zero alpha values to `1`. Alpha values in the
             * material are passed to the AntiAliasing sample mask.
             *
             * Generated from Godot docs: BaseMaterial3D.ALPHA_ANTIALIASING_ALPHA_TO_COVERAGE_AND_TO_ONE
             */
            val ALPHA_TO_COVERAGE_AND_TO_ONE: AlphaAntiAliasing get() = AlphaAntiAliasing(2L)
        }
    }

    @JvmInline
    value class DepthDrawMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Default depth draw mode. Depth is drawn only for opaque objects during the opaque prepass (if
             * any) and during the opaque pass.
             *
             * Generated from Godot docs: BaseMaterial3D.DEPTH_DRAW_OPAQUE_ONLY
             */
            val OPAQUE_ONLY: DepthDrawMode get() = DepthDrawMode(0L)
            /**
             * Objects will write to depth during the opaque and the transparent passes. Transparent objects
             * that are close to the camera may obscure other transparent objects behind them. Note: This does
             * not influence whether transparent objects are included in the depth prepass or not. For that,
             * see `Transparency`.
             *
             * Generated from Godot docs: BaseMaterial3D.DEPTH_DRAW_ALWAYS
             */
            val ALWAYS: DepthDrawMode get() = DepthDrawMode(1L)
            /**
             * Objects will not write their depth to the depth buffer, even during the depth prepass (if
             * enabled).
             *
             * Generated from Godot docs: BaseMaterial3D.DEPTH_DRAW_DISABLED
             */
            val DISABLED: DepthDrawMode get() = DepthDrawMode(2L)
        }
    }

    @JvmInline
    value class DepthTest(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Depth test will discard the pixel if it is behind other pixels.
             *
             * Generated from Godot docs: BaseMaterial3D.DEPTH_TEST_DEFAULT
             */
            val DEFAULT: DepthTest get() = DepthTest(0L)
            /**
             * Depth test will discard the pixel if it is in front of other pixels. Useful for stencil effects.
             *
             * Generated from Godot docs: BaseMaterial3D.DEPTH_TEST_INVERTED
             */
            val INVERTED: DepthTest get() = DepthTest(1L)
        }
    }

    @JvmInline
    value class CullMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Default cull mode. The back of the object is culled when not visible. Back face triangles will
             * be culled when facing the camera. This results in only the front side of triangles being drawn.
             * For closed-surface meshes, this means that only the exterior of the mesh will be visible.
             *
             * Generated from Godot docs: BaseMaterial3D.CULL_BACK
             */
            val BACK: CullMode get() = CullMode(0L)
            /**
             * Front face triangles will be culled when facing the camera. This results in only the back side
             * of triangles being drawn. For closed-surface meshes, this means that the interior of the mesh
             * will be drawn instead of the exterior.
             *
             * Generated from Godot docs: BaseMaterial3D.CULL_FRONT
             */
            val FRONT: CullMode get() = CullMode(1L)
            /**
             * No face culling is performed; both the front face and back face will be visible.
             *
             * Generated from Godot docs: BaseMaterial3D.CULL_DISABLED
             */
            val DISABLED: CullMode get() = CullMode(2L)
        }
    }

    @JvmInline
    value class Flags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disables the depth test, so this object is drawn on top of all others drawn before it. This puts
             * the object in the transparent draw pass where it is sorted based on distance to camera. Objects
             * drawn after it in the draw order may cover it. This also disables writing to depth.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_DISABLE_DEPTH_TEST
             */
            val DISABLE_DEPTH_TEST: Flags get() = Flags(0L)
            /**
             * Set `ALBEDO` to the per-vertex color specified in the mesh.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_ALBEDO_FROM_VERTEX_COLOR
             */
            val ALBEDO_FROM_VERTEX_COLOR: Flags get() = Flags(1L)
            /**
             * Vertex colors are considered to be stored in nonlinear sRGB encoding and are converted to linear
             * encoding during rendering. See also `vertex_color_is_srgb`. Note: Only effective when using the
             * Forward+ and Mobile rendering methods.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_SRGB_VERTEX_COLOR
             */
            val SRGB_VERTEX_COLOR: Flags get() = Flags(2L)
            /**
             * Uses point size to alter the size of primitive points. Also changes the albedo texture lookup to
             * use `POINT_COORD` instead of `UV`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_USE_POINT_SIZE
             */
            val USE_POINT_SIZE: Flags get() = Flags(3L)
            /**
             * Object is scaled by depth so that it always appears the same size on screen.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_FIXED_SIZE
             */
            val FIXED_SIZE: Flags get() = Flags(4L)
            /**
             * Shader will keep the scale set for the mesh. Otherwise the scale is lost when billboarding. Only
             * applies when `billboard_mode` is `BILLBOARD_ENABLED`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_BILLBOARD_KEEP_SCALE
             */
            val BILLBOARD_KEEP_SCALE: Flags get() = Flags(5L)
            /**
             * Use triplanar texture lookup for all texture lookups that would normally use `UV`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_UV1_USE_TRIPLANAR
             */
            val UV1_USE_TRIPLANAR: Flags get() = Flags(6L)
            /**
             * Use triplanar texture lookup for all texture lookups that would normally use `UV2`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_UV2_USE_TRIPLANAR
             */
            val UV2_USE_TRIPLANAR: Flags get() = Flags(7L)
            /**
             * Use triplanar texture lookup for all texture lookups that would normally use `UV`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_UV1_USE_WORLD_TRIPLANAR
             */
            val UV1_USE_WORLD_TRIPLANAR: Flags get() = Flags(8L)
            /**
             * Use triplanar texture lookup for all texture lookups that would normally use `UV2`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_UV2_USE_WORLD_TRIPLANAR
             */
            val UV2_USE_WORLD_TRIPLANAR: Flags get() = Flags(9L)
            /**
             * Use `UV2` coordinates to look up from the `ao_texture`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_AO_ON_UV2
             */
            val AO_ON_UV2: Flags get() = Flags(10L)
            /**
             * Use `UV2` coordinates to look up from the `emission_texture`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_EMISSION_ON_UV2
             */
            val EMISSION_ON_UV2: Flags get() = Flags(11L)
            /**
             * Forces the shader to convert albedo from nonlinear sRGB encoding to linear encoding. See also
             * `albedo_texture_force_srgb`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_ALBEDO_TEXTURE_FORCE_SRGB
             */
            val ALBEDO_TEXTURE_FORCE_SRGB: Flags get() = Flags(12L)
            /**
             * Disables receiving shadows from other objects.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_DONT_RECEIVE_SHADOWS
             */
            val DONT_RECEIVE_SHADOWS: Flags get() = Flags(13L)
            /**
             * Disables receiving ambient light.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_DISABLE_AMBIENT_LIGHT
             */
            val DISABLE_AMBIENT_LIGHT: Flags get() = Flags(14L)
            /**
             * Enables the shadow to opacity feature.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_USE_SHADOW_TO_OPACITY
             */
            val USE_SHADOW_TO_OPACITY: Flags get() = Flags(15L)
            /**
             * Enables the texture to repeat when UV coordinates are outside the 0-1 range. If using one of the
             * linear filtering modes, this can result in artifacts at the edges of a texture when the sampler
             * filters across the edges of the texture.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_USE_TEXTURE_REPEAT
             */
            val USE_TEXTURE_REPEAT: Flags get() = Flags(16L)
            /**
             * Invert values read from a depth texture to convert them to height values (heightmap).
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_INVERT_HEIGHTMAP
             */
            val INVERT_HEIGHTMAP: Flags get() = Flags(17L)
            /**
             * Enables the skin mode for subsurface scattering which is used to improve the look of subsurface
             * scattering when used for human skin.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_SUBSURFACE_MODE_SKIN
             */
            val SUBSURFACE_MODE_SKIN: Flags get() = Flags(18L)
            /**
             * Enables parts of the shader required for `GPUParticles3D` trails to function. This also requires
             * using a mesh with appropriate skinning, such as `RibbonTrailMesh` or `TubeTrailMesh`. Enabling
             * this feature outside of materials used in `GPUParticles3D` meshes will break material rendering.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_PARTICLE_TRAILS_MODE
             */
            val PARTICLE_TRAILS_MODE: Flags get() = Flags(19L)
            /**
             * Enables multichannel signed distance field rendering shader.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_ALBEDO_TEXTURE_MSDF
             */
            val ALBEDO_TEXTURE_MSDF: Flags get() = Flags(20L)
            /**
             * Disables receiving depth-based or volumetric fog.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_DISABLE_FOG
             */
            val DISABLE_FOG: Flags get() = Flags(21L)
            /**
             * Disables specular occlusion.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_DISABLE_SPECULAR_OCCLUSION
             */
            val DISABLE_SPECULAR_OCCLUSION: Flags get() = Flags(22L)
            /**
             * Enables using `z_clip_scale`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_USE_Z_CLIP_SCALE
             */
            val USE_Z_CLIP_SCALE: Flags get() = Flags(23L)
            /**
             * Enables using `fov_override`.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_USE_FOV_OVERRIDE
             */
            val USE_FOV_OVERRIDE: Flags get() = Flags(24L)
            /**
             * Represents the size of the `Flags` enum.
             *
             * Generated from Godot docs: BaseMaterial3D.FLAG_MAX
             */
            val MAX: Flags get() = Flags(25L)
        }
    }

    @JvmInline
    value class DiffuseMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Default diffuse scattering algorithm.
             *
             * Generated from Godot docs: BaseMaterial3D.DIFFUSE_BURLEY
             */
            val BURLEY: DiffuseMode get() = DiffuseMode(0L)
            /**
             * Diffuse scattering ignores roughness.
             *
             * Generated from Godot docs: BaseMaterial3D.DIFFUSE_LAMBERT
             */
            val LAMBERT: DiffuseMode get() = DiffuseMode(1L)
            /**
             * Extends Lambert to cover more than 90 degrees when roughness increases.
             *
             * Generated from Godot docs: BaseMaterial3D.DIFFUSE_LAMBERT_WRAP
             */
            val LAMBERT_WRAP: DiffuseMode get() = DiffuseMode(2L)
            /**
             * Uses a hard cut for lighting, with smoothing affected by roughness.
             *
             * Generated from Godot docs: BaseMaterial3D.DIFFUSE_TOON
             */
            val TOON: DiffuseMode get() = DiffuseMode(3L)
        }
    }

    @JvmInline
    value class SpecularMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Default specular blob. Note: Forward+ uses multiscattering for more accurate reflections,
             * although the impact of multiscattering is more noticeable on rough metallic surfaces than on
             * smooth, non-metallic surfaces. Note: Mobile and Compatibility don't perform multiscattering for
             * performance reasons. Instead, they perform single scattering, which means rough metallic
             * surfaces may look slightly darker than intended.
             *
             * Generated from Godot docs: BaseMaterial3D.SPECULAR_SCHLICK_GGX
             */
            val SCHLICK_GGX: SpecularMode get() = SpecularMode(0L)
            /**
             * Toon blob which changes size based on roughness.
             *
             * Generated from Godot docs: BaseMaterial3D.SPECULAR_TOON
             */
            val TOON: SpecularMode get() = SpecularMode(1L)
            /**
             * No specular blob. This is slightly faster to render than other specular modes.
             *
             * Generated from Godot docs: BaseMaterial3D.SPECULAR_DISABLED
             */
            val DISABLED: SpecularMode get() = SpecularMode(2L)
        }
    }

    @JvmInline
    value class BillboardMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Billboard mode is disabled.
             *
             * Generated from Godot docs: BaseMaterial3D.BILLBOARD_DISABLED
             */
            val DISABLED: BillboardMode get() = BillboardMode(0L)
            /**
             * The object's Z axis will always face the camera.
             *
             * Generated from Godot docs: BaseMaterial3D.BILLBOARD_ENABLED
             */
            val ENABLED: BillboardMode get() = BillboardMode(1L)
            /**
             * The object's X axis will always face the camera.
             *
             * Generated from Godot docs: BaseMaterial3D.BILLBOARD_FIXED_Y
             */
            val FIXED_Y: BillboardMode get() = BillboardMode(2L)
            /**
             * Used for particle systems when assigned to `GPUParticles3D` and `CPUParticles3D` nodes (flipbook
             * animation). Enables `particles_anim_*` properties. The `ParticleProcessMaterial.anim_speed_min`
             * or `CPUParticles3D.anim_speed_min` should also be set to a value bigger than zero for the
             * animation to play.
             *
             * Generated from Godot docs: BaseMaterial3D.BILLBOARD_PARTICLES
             */
            val PARTICLES: BillboardMode get() = BillboardMode(3L)
        }
    }

    @JvmInline
    value class TextureChannel(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Used to read from the red channel of a texture.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_CHANNEL_RED
             */
            val RED: TextureChannel get() = TextureChannel(0L)
            /**
             * Used to read from the green channel of a texture.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_CHANNEL_GREEN
             */
            val GREEN: TextureChannel get() = TextureChannel(1L)
            /**
             * Used to read from the blue channel of a texture.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_CHANNEL_BLUE
             */
            val BLUE: TextureChannel get() = TextureChannel(2L)
            /**
             * Used to read from the alpha channel of a texture.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_CHANNEL_ALPHA
             */
            val ALPHA: TextureChannel get() = TextureChannel(3L)
            /**
             * Used to read from the linear (non-perceptual) average of the red, green and blue channels of a
             * texture.
             *
             * Generated from Godot docs: BaseMaterial3D.TEXTURE_CHANNEL_GRAYSCALE
             */
            val GRAYSCALE: TextureChannel get() = TextureChannel(4L)
        }
    }

    @JvmInline
    value class EmissionOperator(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Adds the emission color to the color from the emission texture.
             *
             * Generated from Godot docs: BaseMaterial3D.EMISSION_OP_ADD
             */
            val ADD: EmissionOperator get() = EmissionOperator(0L)
            /**
             * Multiplies the emission color by the color from the emission texture.
             *
             * Generated from Godot docs: BaseMaterial3D.EMISSION_OP_MULTIPLY
             */
            val MULTIPLY: EmissionOperator get() = EmissionOperator(1L)
        }
    }

    @JvmInline
    value class DistanceFadeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not use distance fade.
             *
             * Generated from Godot docs: BaseMaterial3D.DISTANCE_FADE_DISABLED
             */
            val DISABLED: DistanceFadeMode get() = DistanceFadeMode(0L)
            /**
             * Smoothly fades the object out based on each pixel's distance from the camera using the alpha
             * channel.
             *
             * Generated from Godot docs: BaseMaterial3D.DISTANCE_FADE_PIXEL_ALPHA
             */
            val PIXEL_ALPHA: DistanceFadeMode get() = DistanceFadeMode(1L)
            /**
             * Smoothly fades the object out based on each pixel's distance from the camera using a dithering
             * approach. Dithering discards pixels based on a set pattern to smoothly fade without enabling
             * transparency. On certain hardware, this can be faster than `DISTANCE_FADE_PIXEL_ALPHA`.
             *
             * Generated from Godot docs: BaseMaterial3D.DISTANCE_FADE_PIXEL_DITHER
             */
            val PIXEL_DITHER: DistanceFadeMode get() = DistanceFadeMode(2L)
            /**
             * Smoothly fades the object out based on the object's distance from the camera using a dithering
             * approach. Dithering discards pixels based on a set pattern to smoothly fade without enabling
             * transparency. On certain hardware, this can be faster than `DISTANCE_FADE_PIXEL_ALPHA` and
             * `DISTANCE_FADE_PIXEL_DITHER`.
             *
             * Generated from Godot docs: BaseMaterial3D.DISTANCE_FADE_OBJECT_DITHER
             */
            val OBJECT_DITHER: DistanceFadeMode get() = DistanceFadeMode(3L)
        }
    }

    @JvmInline
    value class StencilMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disables stencil operations.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_MODE_DISABLED
             */
            val DISABLED: StencilMode get() = StencilMode(0L)
            /**
             * Stencil preset which applies an outline to the object. Note: Requires a `Material.next_pass`
             * material which will be automatically applied. Any manual changes made to `Material.next_pass`
             * will be lost when the stencil properties are modified or the scene is reloaded. To safely apply
             * a `Material.next_pass` material on a material that uses stencil presets, use
             * `GeometryInstance3D.material_overlay` instead.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_MODE_OUTLINE
             */
            val OUTLINE: StencilMode get() = StencilMode(1L)
            /**
             * Stencil preset which shows a silhouette of the object behind walls. Note: Requires a
             * `Material.next_pass` material which will be automatically applied. Any manual changes made to
             * `Material.next_pass` will be lost when the stencil properties are modified or the scene is
             * reloaded. To safely apply a `Material.next_pass` material on a material that uses stencil
             * presets, use `GeometryInstance3D.material_overlay` instead.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_MODE_XRAY
             */
            val XRAY: StencilMode get() = StencilMode(2L)
            /**
             * Enables stencil operations without a preset.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_MODE_CUSTOM
             */
            val CUSTOM: StencilMode get() = StencilMode(3L)
        }
    }

    @JvmInline
    value class StencilFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The material will only be rendered where it passes a stencil comparison with existing stencil
             * buffer values.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_FLAG_READ
             */
            val READ: StencilFlags get() = StencilFlags(1L)
            /**
             * The material will write the reference value to the stencil buffer where it passes the depth
             * test.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_FLAG_WRITE
             */
            val WRITE: StencilFlags get() = StencilFlags(2L)
            /**
             * The material will write the reference value to the stencil buffer where it fails the depth test.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_FLAG_WRITE_DEPTH_FAIL
             */
            val WRITE_DEPTH_FAIL: StencilFlags get() = StencilFlags(4L)
        }
    }

    @JvmInline
    value class StencilCompare(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Always passes the stencil test.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_COMPARE_ALWAYS
             */
            val ALWAYS: StencilCompare get() = StencilCompare(0L)
            /**
             * Passes the stencil test when the reference value is less than the existing stencil value.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_COMPARE_LESS
             */
            val LESS: StencilCompare get() = StencilCompare(1L)
            /**
             * Passes the stencil test when the reference value is equal to the existing stencil value.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_COMPARE_EQUAL
             */
            val EQUAL: StencilCompare get() = StencilCompare(2L)
            /**
             * Passes the stencil test when the reference value is less than or equal to the existing stencil
             * value.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_COMPARE_LESS_OR_EQUAL
             */
            val LESS_OR_EQUAL: StencilCompare get() = StencilCompare(3L)
            /**
             * Passes the stencil test when the reference value is greater than the existing stencil value.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_COMPARE_GREATER
             */
            val GREATER: StencilCompare get() = StencilCompare(4L)
            /**
             * Passes the stencil test when the reference value is not equal to the existing stencil value.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_COMPARE_NOT_EQUAL
             */
            val NOT_EQUAL: StencilCompare get() = StencilCompare(5L)
            /**
             * Passes the stencil test when the reference value is greater than or equal to the existing
             * stencil value.
             *
             * Generated from Godot docs: BaseMaterial3D.STENCIL_COMPARE_GREATER_OR_EQUAL
             */
            val GREATER_OR_EQUAL: StencilCompare get() = StencilCompare(6L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BaseMaterial3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): BaseMaterial3D? =
            if (handle.address() == 0L) null else BaseMaterial3D(GodotHandle(handle))

        // Downcast a Material to BaseMaterial3D (null if not).
        @JvmStatic
        fun fromMaterial(value: Material): BaseMaterial3D? =
            if (value.isClass("BaseMaterial3D")) BaseMaterial3D(value.handle) else null

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
