package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Computes and stores baked lightmaps for fast global illumination.
 *
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

    /**
     * The `LightmapGIData` associated to this `LightmapGI` node. This resource is automatically
     * created after baking, and is not meant to be created manually.
     *
     * Generated from Godot docs: LightmapGI.set_light_data
     */
    fun setLightData(data: LightmapGIData?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setLightDataBind, segment, listOf(data?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `LightmapGIData` associated to this `LightmapGI` node. This resource is automatically
     * created after baking, and is not meant to be created manually.
     *
     * Generated from Godot docs: LightmapGI.get_light_data
     */
    fun getLightData(): LightmapGIData? {
        return LightmapGIData.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getLightDataBind, segment))
    }

    /**
     * The quality preset to use when baking lightmaps. This affects bake times, but output file sizes
     * remain mostly identical across quality levels. To further speed up bake times, decrease
     * `bounces`, disable `use_denoiser` and/or decrease `texel_scale`. To further increase quality,
     * enable `supersampling` and/or increase `texel_scale`.
     *
     * Generated from Godot docs: LightmapGI.set_bake_quality
     */
    fun setBakeQuality(bakeQuality: LightmapGI.BakeQuality) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBakeQualityBind, segment, bakeQuality.value)
    }

    /**
     * The quality preset to use when baking lightmaps. This affects bake times, but output file sizes
     * remain mostly identical across quality levels. To further speed up bake times, decrease
     * `bounces`, disable `use_denoiser` and/or decrease `texel_scale`. To further increase quality,
     * enable `supersampling` and/or increase `texel_scale`.
     *
     * Generated from Godot docs: LightmapGI.get_bake_quality
     */
    fun getBakeQuality(): LightmapGI.BakeQuality {
        return LightmapGI.BakeQuality(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBakeQualityBind, segment))
    }

    /**
     * Number of light bounces that are taken into account during baking. Higher values result in
     * brighter, more realistic lighting, at the cost of longer bake times. If set to `0`, only
     * environment lighting, direct light and emissive lighting is baked.
     *
     * Generated from Godot docs: LightmapGI.set_bounces
     */
    fun setBounces(bounces: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setBouncesBind, segment, bounces)
    }

    /**
     * Number of light bounces that are taken into account during baking. Higher values result in
     * brighter, more realistic lighting, at the cost of longer bake times. If set to `0`, only
     * environment lighting, direct light and emissive lighting is baked.
     *
     * Generated from Godot docs: LightmapGI.get_bounces
     */
    fun getBounces(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBouncesBind, segment)
    }

    /**
     * The energy multiplier for each bounce. Higher values will make indirect lighting brighter. A
     * value of `1.0` represents physically accurate behavior, but higher values can be used to make
     * indirect lighting propagate more visibly when using a low number of bounces. This can be used to
     * speed up bake times by lowering the number of `bounces` then increasing
     * `bounce_indirect_energy`. Note: `bounce_indirect_energy` only has an effect if `bounces` is set
     * to a value greater than or equal to `1`.
     *
     * Generated from Godot docs: LightmapGI.set_bounce_indirect_energy
     */
    fun setBounceIndirectEnergy(bounceIndirectEnergy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBounceIndirectEnergyBind, segment, bounceIndirectEnergy)
    }

    /**
     * The energy multiplier for each bounce. Higher values will make indirect lighting brighter. A
     * value of `1.0` represents physically accurate behavior, but higher values can be used to make
     * indirect lighting propagate more visibly when using a low number of bounces. This can be used to
     * speed up bake times by lowering the number of `bounces` then increasing
     * `bounce_indirect_energy`. Note: `bounce_indirect_energy` only has an effect if `bounces` is set
     * to a value greater than or equal to `1`.
     *
     * Generated from Godot docs: LightmapGI.get_bounce_indirect_energy
     */
    fun getBounceIndirectEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBounceIndirectEnergyBind, segment)
    }

    /**
     * The level of subdivision to use when automatically generating `LightmapProbe`s for dynamic
     * object lighting. Higher values result in more accurate indirect lighting on dynamic objects, at
     * the cost of longer bake times and larger file sizes. Note: Automatically generated
     * `LightmapProbe`s are not visible as nodes in the Scene tree dock, and cannot be modified this
     * way after they are generated. Note: Regardless of `generate_probes_subdiv`, direct lighting on
     * dynamic objects is always applied using `Light3D` nodes in real-time.
     *
     * Generated from Godot docs: LightmapGI.set_generate_probes
     */
    fun setGenerateProbes(subdivision: LightmapGI.GenerateProbes) {
        ObjectCalls.ptrcallWithLongArg(Binds.setGenerateProbesBind, segment, subdivision.value)
    }

    /**
     * The level of subdivision to use when automatically generating `LightmapProbe`s for dynamic
     * object lighting. Higher values result in more accurate indirect lighting on dynamic objects, at
     * the cost of longer bake times and larger file sizes. Note: Automatically generated
     * `LightmapProbe`s are not visible as nodes in the Scene tree dock, and cannot be modified this
     * way after they are generated. Note: Regardless of `generate_probes_subdiv`, direct lighting on
     * dynamic objects is always applied using `Light3D` nodes in real-time.
     *
     * Generated from Godot docs: LightmapGI.get_generate_probes
     */
    fun getGenerateProbes(): LightmapGI.GenerateProbes {
        return LightmapGI.GenerateProbes(ObjectCalls.ptrcallNoArgsRetLong(Binds.getGenerateProbesBind, segment))
    }

    /**
     * The bias to use when computing shadows. Increasing `bias` can fix shadow acne on the resulting
     * baked lightmap, but can introduce peter-panning (shadows not connecting to their casters).
     * Real-time `Light3D` shadows are not affected by this `bias` property.
     *
     * Generated from Godot docs: LightmapGI.set_bias
     */
    fun setBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBiasBind, segment, bias)
    }

    /**
     * The bias to use when computing shadows. Increasing `bias` can fix shadow acne on the resulting
     * baked lightmap, but can introduce peter-panning (shadows not connecting to their casters).
     * Real-time `Light3D` shadows are not affected by this `bias` property.
     *
     * Generated from Godot docs: LightmapGI.get_bias
     */
    fun getBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBiasBind, segment)
    }

    /**
     * The environment mode to use when baking lightmaps.
     *
     * Generated from Godot docs: LightmapGI.set_environment_mode
     */
    fun setEnvironmentMode(mode: LightmapGI.EnvironmentMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setEnvironmentModeBind, segment, mode.value)
    }

    /**
     * The environment mode to use when baking lightmaps.
     *
     * Generated from Godot docs: LightmapGI.get_environment_mode
     */
    fun getEnvironmentMode(): LightmapGI.EnvironmentMode {
        return LightmapGI.EnvironmentMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getEnvironmentModeBind, segment))
    }

    /**
     * The sky to use as a source of environment lighting. Only effective if `environment_mode` is
     * `EnvironmentMode.CUSTOM_SKY`.
     *
     * Generated from Godot docs: LightmapGI.set_environment_custom_sky
     */
    fun setEnvironmentCustomSky(sky: Sky?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setEnvironmentCustomSkyBind, segment, listOf(sky?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The sky to use as a source of environment lighting. Only effective if `environment_mode` is
     * `EnvironmentMode.CUSTOM_SKY`.
     *
     * Generated from Godot docs: LightmapGI.get_environment_custom_sky
     */
    fun getEnvironmentCustomSky(): Sky? {
        return Sky.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getEnvironmentCustomSkyBind, segment))
    }

    /**
     * The color to use for environment lighting. Only effective if `environment_mode` is
     * `EnvironmentMode.CUSTOM_COLOR`.
     *
     * Generated from Godot docs: LightmapGI.set_environment_custom_color
     */
    fun setEnvironmentCustomColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setEnvironmentCustomColorBind, segment, color)
    }

    /**
     * The color to use for environment lighting. Only effective if `environment_mode` is
     * `EnvironmentMode.CUSTOM_COLOR`.
     *
     * Generated from Godot docs: LightmapGI.get_environment_custom_color
     */
    fun getEnvironmentCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getEnvironmentCustomColorBind, segment)
    }

    /**
     * The color multiplier to use for environment lighting. Only effective if `environment_mode` is
     * `EnvironmentMode.CUSTOM_COLOR`.
     *
     * Generated from Godot docs: LightmapGI.set_environment_custom_energy
     */
    fun setEnvironmentCustomEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEnvironmentCustomEnergyBind, segment, energy)
    }

    /**
     * The color multiplier to use for environment lighting. Only effective if `environment_mode` is
     * `EnvironmentMode.CUSTOM_COLOR`.
     *
     * Generated from Godot docs: LightmapGI.get_environment_custom_energy
     */
    fun getEnvironmentCustomEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEnvironmentCustomEnergyBind, segment)
    }

    /**
     * Scales the lightmap texel density of all meshes for the current bake. This is a multiplier that
     * builds upon the existing lightmap texel size defined in each imported 3D scene, along with the
     * per-mesh density multiplier (which is designed to be used when the same mesh is used at
     * different scales). Lower values will result in faster bake times. For example, doubling
     * `texel_scale` doubles the lightmap texture resolution for all objects on each axis, so it will
     * quadruple the texel count.
     *
     * Generated from Godot docs: LightmapGI.set_texel_scale
     */
    fun setTexelScale(texelScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTexelScaleBind, segment, texelScale)
    }

    /**
     * Scales the lightmap texel density of all meshes for the current bake. This is a multiplier that
     * builds upon the existing lightmap texel size defined in each imported 3D scene, along with the
     * per-mesh density multiplier (which is designed to be used when the same mesh is used at
     * different scales). Lower values will result in faster bake times. For example, doubling
     * `texel_scale` doubles the lightmap texture resolution for all objects on each axis, so it will
     * quadruple the texel count.
     *
     * Generated from Godot docs: LightmapGI.get_texel_scale
     */
    fun getTexelScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTexelScaleBind, segment)
    }

    /**
     * The maximum texture size for the generated texture atlas. Higher values will result in fewer
     * slices being generated, but may not work on all hardware as a result of hardware limitations on
     * texture sizes. Leave `max_texture_size` at its default value of `16384` if unsure.
     *
     * Generated from Godot docs: LightmapGI.set_max_texture_size
     */
    fun setMaxTextureSize(maxTextureSize: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxTextureSizeBind, segment, maxTextureSize)
    }

    /**
     * The maximum texture size for the generated texture atlas. Higher values will result in fewer
     * slices being generated, but may not work on all hardware as a result of hardware limitations on
     * texture sizes. Leave `max_texture_size` at its default value of `16384` if unsure.
     *
     * Generated from Godot docs: LightmapGI.get_max_texture_size
     */
    fun getMaxTextureSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxTextureSizeBind, segment)
    }

    /**
     * If `true`, lightmaps are baked with the texel scale multiplied with `supersampling_factor` and
     * downsampled before saving the lightmap (so the effective texel density is identical to having
     * supersampling disabled). Supersampling provides increased lightmap quality with less noise,
     * smoother shadows and better shadowing of small-scale features in objects. However, it may result
     * in significantly increased bake times and memory usage while baking lightmaps. Padding is
     * automatically adjusted to avoid increasing light leaking.
     *
     * Generated from Godot docs: LightmapGI.set_supersampling_enabled
     */
    fun setSupersamplingEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSupersamplingEnabledBind, segment, enable)
    }

    /**
     * If `true`, lightmaps are baked with the texel scale multiplied with `supersampling_factor` and
     * downsampled before saving the lightmap (so the effective texel density is identical to having
     * supersampling disabled). Supersampling provides increased lightmap quality with less noise,
     * smoother shadows and better shadowing of small-scale features in objects. However, it may result
     * in significantly increased bake times and memory usage while baking lightmaps. Padding is
     * automatically adjusted to avoid increasing light leaking.
     *
     * Generated from Godot docs: LightmapGI.is_supersampling_enabled
     */
    fun isSupersamplingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSupersamplingEnabledBind, segment)
    }

    /**
     * The factor by which the texel density is multiplied for supersampling. For best results, use an
     * integer value. While fractional values are allowed, they can result in increased light leaking
     * and a blurry lightmap. Higher values may result in better quality, but also increase bake times
     * and memory usage while baking. See `supersampling` for more information.
     *
     * Generated from Godot docs: LightmapGI.set_supersampling_factor
     */
    fun setSupersamplingFactor(factor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSupersamplingFactorBind, segment, factor)
    }

    /**
     * The factor by which the texel density is multiplied for supersampling. For best results, use an
     * integer value. While fractional values are allowed, they can result in increased light leaking
     * and a blurry lightmap. Higher values may result in better quality, but also increase bake times
     * and memory usage while baking. See `supersampling` for more information.
     *
     * Generated from Godot docs: LightmapGI.get_supersampling_factor
     */
    fun getSupersamplingFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSupersamplingFactorBind, segment)
    }

    /**
     * If `true`, uses a GPU-based denoising algorithm on the generated lightmap. This eliminates most
     * noise within the generated lightmap at the cost of longer bake times. File sizes are generally
     * not impacted significantly by the use of a denoiser, although lossless compression may do a
     * better job at compressing a denoised image.
     *
     * Generated from Godot docs: LightmapGI.set_use_denoiser
     */
    fun setUseDenoiser(useDenoiser: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseDenoiserBind, segment, useDenoiser)
    }

    /**
     * If `true`, uses a GPU-based denoising algorithm on the generated lightmap. This eliminates most
     * noise within the generated lightmap at the cost of longer bake times. File sizes are generally
     * not impacted significantly by the use of a denoiser, although lossless compression may do a
     * better job at compressing a denoised image.
     *
     * Generated from Godot docs: LightmapGI.is_using_denoiser
     */
    fun isUsingDenoiser(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingDenoiserBind, segment)
    }

    /**
     * The strength of denoising step applied to the generated lightmaps. Only effective if
     * `use_denoiser` is `true` and `ProjectSettings.rendering/lightmapping/denoising/denoiser` is set
     * to JNLM.
     *
     * Generated from Godot docs: LightmapGI.set_denoiser_strength
     */
    fun setDenoiserStrength(denoiserStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDenoiserStrengthBind, segment, denoiserStrength)
    }

    /**
     * The strength of denoising step applied to the generated lightmaps. Only effective if
     * `use_denoiser` is `true` and `ProjectSettings.rendering/lightmapping/denoising/denoiser` is set
     * to JNLM.
     *
     * Generated from Godot docs: LightmapGI.get_denoiser_strength
     */
    fun getDenoiserStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDenoiserStrengthBind, segment)
    }

    /**
     * The distance in pixels from which the denoiser samples. Lower values preserve more details, but
     * may give blotchy results if the lightmap quality is not high enough. Only effective if
     * `use_denoiser` is `true` and `ProjectSettings.rendering/lightmapping/denoising/denoiser` is set
     * to JNLM.
     *
     * Generated from Godot docs: LightmapGI.set_denoiser_range
     */
    fun setDenoiserRange(denoiserRange: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setDenoiserRangeBind, segment, denoiserRange)
    }

    /**
     * The distance in pixels from which the denoiser samples. Lower values preserve more details, but
     * may give blotchy results if the lightmap quality is not high enough. Only effective if
     * `use_denoiser` is `true` and `ProjectSettings.rendering/lightmapping/denoising/denoiser` is set
     * to JNLM.
     *
     * Generated from Godot docs: LightmapGI.get_denoiser_range
     */
    fun getDenoiserRange(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDenoiserRangeBind, segment)
    }

    /**
     * If `true`, ignore environment lighting when baking lightmaps.
     *
     * Generated from Godot docs: LightmapGI.set_interior
     */
    fun setInterior(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setInteriorBind, segment, enable)
    }

    /**
     * If `true`, ignore environment lighting when baking lightmaps.
     *
     * Generated from Godot docs: LightmapGI.is_interior
     */
    fun isInterior(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isInteriorBind, segment)
    }

    /**
     * If `true`, bakes lightmaps to contain directional information as spherical harmonics. This
     * results in more realistic lighting appearance, especially with normal mapped materials and for
     * lights that have their direct light baked (`Light3D.light_bake_mode` set to
     * `Light3D.BakeMode.STATIC` and with `Light3D.editor_only` set to `false`). The directional
     * information is also used to provide rough reflections for static and dynamic objects. This has a
     * small run-time performance cost as the shader has to perform more work to interpret the
     * direction information from the lightmap. Directional lightmaps also take longer to bake and
     * result in larger file sizes. Note: The property's name has no relationship with
     * `DirectionalLight3D`. `directional` works with all light types.
     *
     * Generated from Godot docs: LightmapGI.set_directional
     */
    fun setDirectional(directional: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDirectionalBind, segment, directional)
    }

    /**
     * If `true`, bakes lightmaps to contain directional information as spherical harmonics. This
     * results in more realistic lighting appearance, especially with normal mapped materials and for
     * lights that have their direct light baked (`Light3D.light_bake_mode` set to
     * `Light3D.BakeMode.STATIC` and with `Light3D.editor_only` set to `false`). The directional
     * information is also used to provide rough reflections for static and dynamic objects. This has a
     * small run-time performance cost as the shader has to perform more work to interpret the
     * direction information from the lightmap. Directional lightmaps also take longer to bake and
     * result in larger file sizes. Note: The property's name has no relationship with
     * `DirectionalLight3D`. `directional` works with all light types.
     *
     * Generated from Godot docs: LightmapGI.is_directional
     */
    fun isDirectional(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDirectionalBind, segment)
    }

    /**
     * The shadowmasking policy to use for directional shadows on static objects that are baked with
     * this `LightmapGI` instance. Shadowmasking allows `DirectionalLight3D` nodes to cast shadows even
     * outside the range defined by their `DirectionalLight3D.directional_shadow_max_distance`
     * property. This is done by baking a texture that contains a shadowmap for the directional light,
     * then using this texture according to the current shadowmask mode. Note: The shadowmask texture
     * is only created if `shadowmask_mode` is not `LightmapGIData.ShadowmaskMode.NONE`. To see a
     * difference, you need to bake lightmaps again after switching from
     * `LightmapGIData.ShadowmaskMode.NONE` to any other mode.
     *
     * Generated from Godot docs: LightmapGI.set_shadowmask_mode
     */
    fun setShadowmaskMode(mode: LightmapGIData.ShadowmaskMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setShadowmaskModeBind, segment, mode.value)
    }

    /**
     * The shadowmasking policy to use for directional shadows on static objects that are baked with
     * this `LightmapGI` instance. Shadowmasking allows `DirectionalLight3D` nodes to cast shadows even
     * outside the range defined by their `DirectionalLight3D.directional_shadow_max_distance`
     * property. This is done by baking a texture that contains a shadowmap for the directional light,
     * then using this texture according to the current shadowmask mode. Note: The shadowmask texture
     * is only created if `shadowmask_mode` is not `LightmapGIData.ShadowmaskMode.NONE`. To see a
     * difference, you need to bake lightmaps again after switching from
     * `LightmapGIData.ShadowmaskMode.NONE` to any other mode.
     *
     * Generated from Godot docs: LightmapGI.get_shadowmask_mode
     */
    fun getShadowmaskMode(): LightmapGIData.ShadowmaskMode {
        return LightmapGIData.ShadowmaskMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getShadowmaskModeBind, segment))
    }

    /**
     * If `true`, a texture with the lighting information will be generated to speed up the generation
     * of indirect lighting at the cost of some accuracy. The geometry might exhibit extra light leak
     * artifacts when using low resolution lightmaps or UVs that stretch the lightmap significantly
     * across surfaces. Leave `use_texture_for_bounces` at its default value of `true` if unsure. Note:
     * `use_texture_for_bounces` only has an effect if `bounces` is set to a value greater than or
     * equal to `1`.
     *
     * Generated from Godot docs: LightmapGI.set_use_texture_for_bounces
     */
    fun setUseTextureForBounces(useTextureForBounces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseTextureForBouncesBind, segment, useTextureForBounces)
    }

    /**
     * If `true`, a texture with the lighting information will be generated to speed up the generation
     * of indirect lighting at the cost of some accuracy. The geometry might exhibit extra light leak
     * artifacts when using low resolution lightmaps or UVs that stretch the lightmap significantly
     * across surfaces. Leave `use_texture_for_bounces` at its default value of `true` if unsure. Note:
     * `use_texture_for_bounces` only has an effect if `bounces` is set to a value greater than or
     * equal to `1`.
     *
     * Generated from Godot docs: LightmapGI.is_using_texture_for_bounces
     */
    fun isUsingTextureForBounces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingTextureForBouncesBind, segment)
    }

    /**
     * The `CameraAttributes` resource that specifies exposure levels to bake at. Auto-exposure and non
     * exposure properties will be ignored. Exposure settings should be used to reduce the dynamic
     * range present when baking. If exposure is too high, the `LightmapGI` will have banding artifacts
     * or may have over-exposure artifacts.
     *
     * Generated from Godot docs: LightmapGI.set_camera_attributes
     */
    fun setCameraAttributes(cameraAttributes: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCameraAttributesBind, segment, listOf(cameraAttributes?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `CameraAttributes` resource that specifies exposure levels to bake at. Auto-exposure and non
     * exposure properties will be ignored. Exposure settings should be used to reduce the dynamic
     * range present when baking. If exposure is too high, the `LightmapGI` will have banding artifacts
     * or may have over-exposure artifacts.
     *
     * Generated from Godot docs: LightmapGI.get_camera_attributes
     */
    fun getCameraAttributes(): CameraAttributes? {
        return CameraAttributes.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCameraAttributesBind, segment))
    }

    /**
     * Godot's `LightmapGI.BakeQuality` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`LightmapGI.BakeQuality.<NAME>`).
     *
     * Generated from Godot docs: LightmapGI.BakeQuality
     */
    @JvmInline
    value class BakeQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Low bake quality (fastest bake times). The quality of this preset can be adjusted by changing
             * `ProjectSettings.rendering/lightmapping/bake_quality/low_quality_ray_count` and
             * `ProjectSettings.rendering/lightmapping/bake_quality/low_quality_probe_ray_count`.
             *
             * Generated from Godot docs: LightmapGI.BAKE_QUALITY_LOW
             */
            val LOW: BakeQuality get() = BakeQuality(0L)
            /**
             * Medium bake quality (fast bake times). The quality of this preset can be adjusted by changing
             * `ProjectSettings.rendering/lightmapping/bake_quality/medium_quality_ray_count` and
             * `ProjectSettings.rendering/lightmapping/bake_quality/medium_quality_probe_ray_count`.
             *
             * Generated from Godot docs: LightmapGI.BAKE_QUALITY_MEDIUM
             */
            val MEDIUM: BakeQuality get() = BakeQuality(1L)
            /**
             * High bake quality (slow bake times). The quality of this preset can be adjusted by changing
             * `ProjectSettings.rendering/lightmapping/bake_quality/high_quality_ray_count` and
             * `ProjectSettings.rendering/lightmapping/bake_quality/high_quality_probe_ray_count`.
             *
             * Generated from Godot docs: LightmapGI.BAKE_QUALITY_HIGH
             */
            val HIGH: BakeQuality get() = BakeQuality(2L)
            /**
             * Highest bake quality (slowest bake times). The quality of this preset can be adjusted by
             * changing `ProjectSettings.rendering/lightmapping/bake_quality/ultra_quality_ray_count` and
             * `ProjectSettings.rendering/lightmapping/bake_quality/ultra_quality_probe_ray_count`.
             *
             * Generated from Godot docs: LightmapGI.BAKE_QUALITY_ULTRA
             */
            val ULTRA: BakeQuality get() = BakeQuality(3L)
        }
    }

    /**
     * Godot's `LightmapGI.GenerateProbes` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`LightmapGI.GenerateProbes.<NAME>`).
     *
     * Generated from Godot docs: LightmapGI.GenerateProbes
     */
    @JvmInline
    value class GenerateProbes(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Don't generate lightmap probes for lighting dynamic objects.
             *
             * Generated from Godot docs: LightmapGI.GENERATE_PROBES_DISABLED
             */
            val DISABLED: GenerateProbes get() = GenerateProbes(0L)
            /**
             * Lowest level of subdivision (fastest bake times, smallest file sizes).
             *
             * Generated from Godot docs: LightmapGI.GENERATE_PROBES_SUBDIV_4
             */
            val SUBDIV_4: GenerateProbes get() = GenerateProbes(1L)
            /**
             * Low level of subdivision (fast bake times, small file sizes).
             *
             * Generated from Godot docs: LightmapGI.GENERATE_PROBES_SUBDIV_8
             */
            val SUBDIV_8: GenerateProbes get() = GenerateProbes(2L)
            /**
             * High level of subdivision (slow bake times, large file sizes).
             *
             * Generated from Godot docs: LightmapGI.GENERATE_PROBES_SUBDIV_16
             */
            val SUBDIV_16: GenerateProbes get() = GenerateProbes(3L)
            /**
             * Highest level of subdivision (slowest bake times, largest file sizes).
             *
             * Generated from Godot docs: LightmapGI.GENERATE_PROBES_SUBDIV_32
             */
            val SUBDIV_32: GenerateProbes get() = GenerateProbes(4L)
        }
    }

    /**
     * Godot's `LightmapGI.BakeError` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`LightmapGI.BakeError.<NAME>`).
     *
     * Generated from Godot docs: LightmapGI.BakeError
     */
    @JvmInline
    value class BakeError(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Lightmap baking was successful.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_OK
             */
            val OK: BakeError get() = BakeError(0L)
            /**
             * Lightmap baking failed because the root node for the edited scene could not be accessed.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_NO_SCENE_ROOT
             */
            val NO_SCENE_ROOT: BakeError get() = BakeError(1L)
            /**
             * Lightmap baking failed as the lightmap data resource is embedded in a foreign resource.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_FOREIGN_DATA
             */
            val FOREIGN_DATA: BakeError get() = BakeError(2L)
            /**
             * Lightmap baking failed as there is no lightmapper available in this Godot build.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_NO_LIGHTMAPPER
             */
            val NO_LIGHTMAPPER: BakeError get() = BakeError(3L)
            /**
             * Lightmap baking failed as the `LightmapGIData` save path isn't configured in the resource.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_NO_SAVE_PATH
             */
            val NO_SAVE_PATH: BakeError get() = BakeError(4L)
            /**
             * Lightmap baking failed as there are no meshes whose `GeometryInstance3D.gi_mode` is
             * `GeometryInstance3D.GIMode.STATIC` and with valid UV2 mapping in the current scene. You may need
             * to select 3D scenes in the Import dock and change their global illumination mode accordingly.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_NO_MESHES
             */
            val NO_MESHES: BakeError get() = BakeError(5L)
            /**
             * Lightmap baking failed as the lightmapper failed to analyze some of the meshes marked as static
             * for baking.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_MESHES_INVALID
             */
            val MESHES_INVALID: BakeError get() = BakeError(6L)
            /**
             * Lightmap baking failed as the resulting image couldn't be saved or imported by Godot after it
             * was saved.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_CANT_CREATE_IMAGE
             */
            val CANT_CREATE_IMAGE: BakeError get() = BakeError(7L)
            /**
             * The user aborted the lightmap baking operation (typically by clicking the Cancel button in the
             * progress dialog).
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_USER_ABORTED
             */
            val USER_ABORTED: BakeError get() = BakeError(8L)
            /**
             * Lightmap baking failed as the maximum texture size is too small to fit some of the meshes marked
             * for baking.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_TEXTURE_SIZE_TOO_SMALL
             */
            val TEXTURE_SIZE_TOO_SMALL: BakeError get() = BakeError(9L)
            /**
             * Lightmap baking failed as the lightmap is too small.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_LIGHTMAP_TOO_SMALL
             */
            val LIGHTMAP_TOO_SMALL: BakeError get() = BakeError(10L)
            /**
             * Lightmap baking failed as the lightmap was unable to fit into an atlas.
             *
             * Generated from Godot docs: LightmapGI.BAKE_ERROR_ATLAS_TOO_SMALL
             */
            val ATLAS_TOO_SMALL: BakeError get() = BakeError(11L)
        }
    }

    /**
     * Godot's `LightmapGI.EnvironmentMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`LightmapGI.EnvironmentMode.<NAME>`).
     *
     * Generated from Godot docs: LightmapGI.EnvironmentMode
     */
    @JvmInline
    value class EnvironmentMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Ignore environment lighting when baking lightmaps.
             *
             * Generated from Godot docs: LightmapGI.ENVIRONMENT_MODE_DISABLED
             */
            val DISABLED: EnvironmentMode get() = EnvironmentMode(0L)
            /**
             * Use the scene's environment lighting when baking lightmaps. Note: If baking lightmaps in a scene
             * with no `WorldEnvironment` node, this will act like `EnvironmentMode.DISABLED`. The editor's
             * preview sky and sun is not taken into account by `LightmapGI` when baking lightmaps.
             *
             * Generated from Godot docs: LightmapGI.ENVIRONMENT_MODE_SCENE
             */
            val SCENE: EnvironmentMode get() = EnvironmentMode(1L)
            /**
             * Use `environment_custom_sky` as a source of environment lighting when baking lightmaps.
             *
             * Generated from Godot docs: LightmapGI.ENVIRONMENT_MODE_CUSTOM_SKY
             */
            val CUSTOM_SKY: EnvironmentMode get() = EnvironmentMode(2L)
            /**
             * Use `environment_custom_color` multiplied by `environment_custom_energy` as a constant source of
             * environment lighting when baking lightmaps.
             *
             * Generated from Godot docs: LightmapGI.ENVIRONMENT_MODE_CUSTOM_COLOR
             */
            val CUSTOM_COLOR: EnvironmentMode get() = EnvironmentMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): LightmapGI? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): LightmapGI? =
            if (handle.address() == 0L) null else LightmapGI(GodotHandle(handle))

        // Instantiate a LightmapGI.
        @JvmStatic
        fun create(): LightmapGI =
            LightmapGI(GodotHandle(ObjectCalls.constructObject("LightmapGI")))
    }

    private object Binds {
        private const val SET_LIGHT_DATA_HASH = 1790597277L
        @JvmField
        val setLightDataBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_light_data", SET_LIGHT_DATA_HASH)

        private const val GET_LIGHT_DATA_HASH = 290354153L
        @JvmField
        val getLightDataBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_light_data", GET_LIGHT_DATA_HASH)

        private const val SET_BAKE_QUALITY_HASH = 1192215803L
        @JvmField
        val setBakeQualityBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_bake_quality", SET_BAKE_QUALITY_HASH)

        private const val GET_BAKE_QUALITY_HASH = 688832735L
        @JvmField
        val getBakeQualityBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_bake_quality", GET_BAKE_QUALITY_HASH)

        private const val SET_BOUNCES_HASH = 1286410249L
        @JvmField
        val setBouncesBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_bounces", SET_BOUNCES_HASH)

        private const val GET_BOUNCES_HASH = 3905245786L
        @JvmField
        val getBouncesBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_bounces", GET_BOUNCES_HASH)

        private const val SET_BOUNCE_INDIRECT_ENERGY_HASH = 373806689L
        @JvmField
        val setBounceIndirectEnergyBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_bounce_indirect_energy", SET_BOUNCE_INDIRECT_ENERGY_HASH)

        private const val GET_BOUNCE_INDIRECT_ENERGY_HASH = 1740695150L
        @JvmField
        val getBounceIndirectEnergyBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_bounce_indirect_energy", GET_BOUNCE_INDIRECT_ENERGY_HASH)

        private const val SET_GENERATE_PROBES_HASH = 549981046L
        @JvmField
        val setGenerateProbesBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_generate_probes", SET_GENERATE_PROBES_HASH)

        private const val GET_GENERATE_PROBES_HASH = 3930596226L
        @JvmField
        val getGenerateProbesBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_generate_probes", GET_GENERATE_PROBES_HASH)

        private const val SET_BIAS_HASH = 373806689L
        @JvmField
        val setBiasBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_bias", SET_BIAS_HASH)

        private const val GET_BIAS_HASH = 1740695150L
        @JvmField
        val getBiasBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_bias", GET_BIAS_HASH)

        private const val SET_ENVIRONMENT_MODE_HASH = 2282650285L
        @JvmField
        val setEnvironmentModeBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_environment_mode", SET_ENVIRONMENT_MODE_HASH)

        private const val GET_ENVIRONMENT_MODE_HASH = 4128646479L
        @JvmField
        val getEnvironmentModeBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_environment_mode", GET_ENVIRONMENT_MODE_HASH)

        private const val SET_ENVIRONMENT_CUSTOM_SKY_HASH = 3336722921L
        @JvmField
        val setEnvironmentCustomSkyBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_environment_custom_sky", SET_ENVIRONMENT_CUSTOM_SKY_HASH)

        private const val GET_ENVIRONMENT_CUSTOM_SKY_HASH = 1177136966L
        @JvmField
        val getEnvironmentCustomSkyBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_environment_custom_sky", GET_ENVIRONMENT_CUSTOM_SKY_HASH)

        private const val SET_ENVIRONMENT_CUSTOM_COLOR_HASH = 2920490490L
        @JvmField
        val setEnvironmentCustomColorBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_environment_custom_color", SET_ENVIRONMENT_CUSTOM_COLOR_HASH)

        private const val GET_ENVIRONMENT_CUSTOM_COLOR_HASH = 3444240500L
        @JvmField
        val getEnvironmentCustomColorBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_environment_custom_color", GET_ENVIRONMENT_CUSTOM_COLOR_HASH)

        private const val SET_ENVIRONMENT_CUSTOM_ENERGY_HASH = 373806689L
        @JvmField
        val setEnvironmentCustomEnergyBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_environment_custom_energy", SET_ENVIRONMENT_CUSTOM_ENERGY_HASH)

        private const val GET_ENVIRONMENT_CUSTOM_ENERGY_HASH = 1740695150L
        @JvmField
        val getEnvironmentCustomEnergyBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_environment_custom_energy", GET_ENVIRONMENT_CUSTOM_ENERGY_HASH)

        private const val SET_TEXEL_SCALE_HASH = 373806689L
        @JvmField
        val setTexelScaleBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_texel_scale", SET_TEXEL_SCALE_HASH)

        private const val GET_TEXEL_SCALE_HASH = 1740695150L
        @JvmField
        val getTexelScaleBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_texel_scale", GET_TEXEL_SCALE_HASH)

        private const val SET_MAX_TEXTURE_SIZE_HASH = 1286410249L
        @JvmField
        val setMaxTextureSizeBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_max_texture_size", SET_MAX_TEXTURE_SIZE_HASH)

        private const val GET_MAX_TEXTURE_SIZE_HASH = 3905245786L
        @JvmField
        val getMaxTextureSizeBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_max_texture_size", GET_MAX_TEXTURE_SIZE_HASH)

        private const val SET_SUPERSAMPLING_ENABLED_HASH = 2586408642L
        @JvmField
        val setSupersamplingEnabledBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_supersampling_enabled", SET_SUPERSAMPLING_ENABLED_HASH)

        private const val IS_SUPERSAMPLING_ENABLED_HASH = 36873697L
        @JvmField
        val isSupersamplingEnabledBind =
            ObjectCalls.getMethodBind("LightmapGI", "is_supersampling_enabled", IS_SUPERSAMPLING_ENABLED_HASH)

        private const val SET_SUPERSAMPLING_FACTOR_HASH = 373806689L
        @JvmField
        val setSupersamplingFactorBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_supersampling_factor", SET_SUPERSAMPLING_FACTOR_HASH)

        private const val GET_SUPERSAMPLING_FACTOR_HASH = 1740695150L
        @JvmField
        val getSupersamplingFactorBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_supersampling_factor", GET_SUPERSAMPLING_FACTOR_HASH)

        private const val SET_USE_DENOISER_HASH = 2586408642L
        @JvmField
        val setUseDenoiserBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_use_denoiser", SET_USE_DENOISER_HASH)

        private const val IS_USING_DENOISER_HASH = 36873697L
        @JvmField
        val isUsingDenoiserBind =
            ObjectCalls.getMethodBind("LightmapGI", "is_using_denoiser", IS_USING_DENOISER_HASH)

        private const val SET_DENOISER_STRENGTH_HASH = 373806689L
        @JvmField
        val setDenoiserStrengthBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_denoiser_strength", SET_DENOISER_STRENGTH_HASH)

        private const val GET_DENOISER_STRENGTH_HASH = 1740695150L
        @JvmField
        val getDenoiserStrengthBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_denoiser_strength", GET_DENOISER_STRENGTH_HASH)

        private const val SET_DENOISER_RANGE_HASH = 1286410249L
        @JvmField
        val setDenoiserRangeBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_denoiser_range", SET_DENOISER_RANGE_HASH)

        private const val GET_DENOISER_RANGE_HASH = 3905245786L
        @JvmField
        val getDenoiserRangeBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_denoiser_range", GET_DENOISER_RANGE_HASH)

        private const val SET_INTERIOR_HASH = 2586408642L
        @JvmField
        val setInteriorBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_interior", SET_INTERIOR_HASH)

        private const val IS_INTERIOR_HASH = 36873697L
        @JvmField
        val isInteriorBind =
            ObjectCalls.getMethodBind("LightmapGI", "is_interior", IS_INTERIOR_HASH)

        private const val SET_DIRECTIONAL_HASH = 2586408642L
        @JvmField
        val setDirectionalBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_directional", SET_DIRECTIONAL_HASH)

        private const val IS_DIRECTIONAL_HASH = 36873697L
        @JvmField
        val isDirectionalBind =
            ObjectCalls.getMethodBind("LightmapGI", "is_directional", IS_DIRECTIONAL_HASH)

        private const val SET_SHADOWMASK_MODE_HASH = 3451066572L
        @JvmField
        val setShadowmaskModeBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_shadowmask_mode", SET_SHADOWMASK_MODE_HASH)

        private const val GET_SHADOWMASK_MODE_HASH = 785478560L
        @JvmField
        val getShadowmaskModeBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_shadowmask_mode", GET_SHADOWMASK_MODE_HASH)

        private const val SET_USE_TEXTURE_FOR_BOUNCES_HASH = 2586408642L
        @JvmField
        val setUseTextureForBouncesBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_use_texture_for_bounces", SET_USE_TEXTURE_FOR_BOUNCES_HASH)

        private const val IS_USING_TEXTURE_FOR_BOUNCES_HASH = 36873697L
        @JvmField
        val isUsingTextureForBouncesBind =
            ObjectCalls.getMethodBind("LightmapGI", "is_using_texture_for_bounces", IS_USING_TEXTURE_FOR_BOUNCES_HASH)

        private const val SET_CAMERA_ATTRIBUTES_HASH = 2817810567L
        @JvmField
        val setCameraAttributesBind =
            ObjectCalls.getMethodBind("LightmapGI", "set_camera_attributes", SET_CAMERA_ATTRIBUTES_HASH)

        private const val GET_CAMERA_ATTRIBUTES_HASH = 3921283215L
        @JvmField
        val getCameraAttributesBind =
            ObjectCalls.getMethodBind("LightmapGI", "get_camera_attributes", GET_CAMERA_ATTRIBUTES_HASH)
    }
}
