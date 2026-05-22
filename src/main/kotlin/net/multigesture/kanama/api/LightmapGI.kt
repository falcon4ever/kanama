package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Computes and stores baked lightmaps for fast global illumination.
 *
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

    /**
     * The `LightmapGIData` associated to this `LightmapGI` node. This resource is automatically
     * created after baking, and is not meant to be created manually.
     *
     * Generated from Godot docs: LightmapGI.set_light_data
     */
    fun setLightData(data: LightmapGIData?) {
        ObjectCalls.ptrcallWithObjectArgs(setLightDataBind, handle, listOf(data?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `LightmapGIData` associated to this `LightmapGI` node. This resource is automatically
     * created after baking, and is not meant to be created manually.
     *
     * Generated from Godot docs: LightmapGI.get_light_data
     */
    fun getLightData(): LightmapGIData? {
        return LightmapGIData.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLightDataBind, handle))
    }

    /**
     * The quality preset to use when baking lightmaps. This affects bake times, but output file sizes
     * remain mostly identical across quality levels. To further speed up bake times, decrease
     * `bounces`, disable `use_denoiser` and/or decrease `texel_scale`. To further increase quality,
     * enable `supersampling` and/or increase `texel_scale`.
     *
     * Generated from Godot docs: LightmapGI.set_bake_quality
     */
    fun setBakeQuality(bakeQuality: Long) {
        ObjectCalls.ptrcallWithLongArg(setBakeQualityBind, handle, bakeQuality)
    }

    /**
     * The quality preset to use when baking lightmaps. This affects bake times, but output file sizes
     * remain mostly identical across quality levels. To further speed up bake times, decrease
     * `bounces`, disable `use_denoiser` and/or decrease `texel_scale`. To further increase quality,
     * enable `supersampling` and/or increase `texel_scale`.
     *
     * Generated from Godot docs: LightmapGI.get_bake_quality
     */
    fun getBakeQuality(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBakeQualityBind, handle)
    }

    /**
     * Number of light bounces that are taken into account during baking. Higher values result in
     * brighter, more realistic lighting, at the cost of longer bake times. If set to `0`, only
     * environment lighting, direct light and emissive lighting is baked.
     *
     * Generated from Godot docs: LightmapGI.set_bounces
     */
    fun setBounces(bounces: Int) {
        ObjectCalls.ptrcallWithIntArg(setBouncesBind, handle, bounces)
    }

    /**
     * Number of light bounces that are taken into account during baking. Higher values result in
     * brighter, more realistic lighting, at the cost of longer bake times. If set to `0`, only
     * environment lighting, direct light and emissive lighting is baked.
     *
     * Generated from Godot docs: LightmapGI.get_bounces
     */
    fun getBounces(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBouncesBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setBounceIndirectEnergyBind, handle, bounceIndirectEnergy)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getBounceIndirectEnergyBind, handle)
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
    fun setGenerateProbes(subdivision: Long) {
        ObjectCalls.ptrcallWithLongArg(setGenerateProbesBind, handle, subdivision)
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
    fun getGenerateProbes(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGenerateProbesBind, handle)
    }

    /**
     * The bias to use when computing shadows. Increasing `bias` can fix shadow acne on the resulting
     * baked lightmap, but can introduce peter-panning (shadows not connecting to their casters).
     * Real-time `Light3D` shadows are not affected by this `bias` property.
     *
     * Generated from Godot docs: LightmapGI.set_bias
     */
    fun setBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBiasBind, handle, bias)
    }

    /**
     * The bias to use when computing shadows. Increasing `bias` can fix shadow acne on the resulting
     * baked lightmap, but can introduce peter-panning (shadows not connecting to their casters).
     * Real-time `Light3D` shadows are not affected by this `bias` property.
     *
     * Generated from Godot docs: LightmapGI.get_bias
     */
    fun getBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBiasBind, handle)
    }

    /**
     * The environment mode to use when baking lightmaps.
     *
     * Generated from Godot docs: LightmapGI.set_environment_mode
     */
    fun setEnvironmentMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setEnvironmentModeBind, handle, mode)
    }

    /**
     * The environment mode to use when baking lightmaps.
     *
     * Generated from Godot docs: LightmapGI.get_environment_mode
     */
    fun getEnvironmentMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEnvironmentModeBind, handle)
    }

    /**
     * The sky to use as a source of environment lighting. Only effective if `environment_mode` is
     * `ENVIRONMENT_MODE_CUSTOM_SKY`.
     *
     * Generated from Godot docs: LightmapGI.set_environment_custom_sky
     */
    fun setEnvironmentCustomSky(sky: Sky?) {
        ObjectCalls.ptrcallWithObjectArgs(setEnvironmentCustomSkyBind, handle, listOf(sky?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The sky to use as a source of environment lighting. Only effective if `environment_mode` is
     * `ENVIRONMENT_MODE_CUSTOM_SKY`.
     *
     * Generated from Godot docs: LightmapGI.get_environment_custom_sky
     */
    fun getEnvironmentCustomSky(): Sky? {
        return Sky.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEnvironmentCustomSkyBind, handle))
    }

    /**
     * The color to use for environment lighting. Only effective if `environment_mode` is
     * `ENVIRONMENT_MODE_CUSTOM_COLOR`.
     *
     * Generated from Godot docs: LightmapGI.set_environment_custom_color
     */
    fun setEnvironmentCustomColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setEnvironmentCustomColorBind, handle, color)
    }

    /**
     * The color to use for environment lighting. Only effective if `environment_mode` is
     * `ENVIRONMENT_MODE_CUSTOM_COLOR`.
     *
     * Generated from Godot docs: LightmapGI.get_environment_custom_color
     */
    fun getEnvironmentCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getEnvironmentCustomColorBind, handle)
    }

    /**
     * The color multiplier to use for environment lighting. Only effective if `environment_mode` is
     * `ENVIRONMENT_MODE_CUSTOM_COLOR`.
     *
     * Generated from Godot docs: LightmapGI.set_environment_custom_energy
     */
    fun setEnvironmentCustomEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnvironmentCustomEnergyBind, handle, energy)
    }

    /**
     * The color multiplier to use for environment lighting. Only effective if `environment_mode` is
     * `ENVIRONMENT_MODE_CUSTOM_COLOR`.
     *
     * Generated from Godot docs: LightmapGI.get_environment_custom_energy
     */
    fun getEnvironmentCustomEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnvironmentCustomEnergyBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setTexelScaleBind, handle, texelScale)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getTexelScaleBind, handle)
    }

    /**
     * The maximum texture size for the generated texture atlas. Higher values will result in fewer
     * slices being generated, but may not work on all hardware as a result of hardware limitations on
     * texture sizes. Leave `max_texture_size` at its default value of `16384` if unsure.
     *
     * Generated from Godot docs: LightmapGI.set_max_texture_size
     */
    fun setMaxTextureSize(maxTextureSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxTextureSizeBind, handle, maxTextureSize)
    }

    /**
     * The maximum texture size for the generated texture atlas. Higher values will result in fewer
     * slices being generated, but may not work on all hardware as a result of hardware limitations on
     * texture sizes. Leave `max_texture_size` at its default value of `16384` if unsure.
     *
     * Generated from Godot docs: LightmapGI.get_max_texture_size
     */
    fun getMaxTextureSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxTextureSizeBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setSupersamplingEnabledBind, handle, enable)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isSupersamplingEnabledBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setSupersamplingFactorBind, handle, factor)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getSupersamplingFactorBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setUseDenoiserBind, handle, useDenoiser)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingDenoiserBind, handle)
    }

    /**
     * The strength of denoising step applied to the generated lightmaps. Only effective if
     * `use_denoiser` is `true` and `ProjectSettings.rendering/lightmapping/denoising/denoiser` is set
     * to JNLM.
     *
     * Generated from Godot docs: LightmapGI.set_denoiser_strength
     */
    fun setDenoiserStrength(denoiserStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDenoiserStrengthBind, handle, denoiserStrength)
    }

    /**
     * The strength of denoising step applied to the generated lightmaps. Only effective if
     * `use_denoiser` is `true` and `ProjectSettings.rendering/lightmapping/denoising/denoiser` is set
     * to JNLM.
     *
     * Generated from Godot docs: LightmapGI.get_denoiser_strength
     */
    fun getDenoiserStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDenoiserStrengthBind, handle)
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
        ObjectCalls.ptrcallWithIntArg(setDenoiserRangeBind, handle, denoiserRange)
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
        return ObjectCalls.ptrcallNoArgsRetInt(getDenoiserRangeBind, handle)
    }

    /**
     * If `true`, ignore environment lighting when baking lightmaps.
     *
     * Generated from Godot docs: LightmapGI.set_interior
     */
    fun setInterior(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInteriorBind, handle, enable)
    }

    /**
     * If `true`, ignore environment lighting when baking lightmaps.
     *
     * Generated from Godot docs: LightmapGI.is_interior
     */
    fun isInterior(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInteriorBind, handle)
    }

    /**
     * If `true`, bakes lightmaps to contain directional information as spherical harmonics. This
     * results in more realistic lighting appearance, especially with normal mapped materials and for
     * lights that have their direct light baked (`Light3D.light_bake_mode` set to
     * `Light3D.BAKE_STATIC` and with `Light3D.editor_only` set to `false`). The directional
     * information is also used to provide rough reflections for static and dynamic objects. This has a
     * small run-time performance cost as the shader has to perform more work to interpret the
     * direction information from the lightmap. Directional lightmaps also take longer to bake and
     * result in larger file sizes. Note: The property's name has no relationship with
     * `DirectionalLight3D`. `directional` works with all light types.
     *
     * Generated from Godot docs: LightmapGI.set_directional
     */
    fun setDirectional(directional: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDirectionalBind, handle, directional)
    }

    /**
     * If `true`, bakes lightmaps to contain directional information as spherical harmonics. This
     * results in more realistic lighting appearance, especially with normal mapped materials and for
     * lights that have their direct light baked (`Light3D.light_bake_mode` set to
     * `Light3D.BAKE_STATIC` and with `Light3D.editor_only` set to `false`). The directional
     * information is also used to provide rough reflections for static and dynamic objects. This has a
     * small run-time performance cost as the shader has to perform more work to interpret the
     * direction information from the lightmap. Directional lightmaps also take longer to bake and
     * result in larger file sizes. Note: The property's name has no relationship with
     * `DirectionalLight3D`. `directional` works with all light types.
     *
     * Generated from Godot docs: LightmapGI.is_directional
     */
    fun isDirectional(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDirectionalBind, handle)
    }

    /**
     * The shadowmasking policy to use for directional shadows on static objects that are baked with
     * this `LightmapGI` instance. Shadowmasking allows `DirectionalLight3D` nodes to cast shadows even
     * outside the range defined by their `DirectionalLight3D.directional_shadow_max_distance`
     * property. This is done by baking a texture that contains a shadowmap for the directional light,
     * then using this texture according to the current shadowmask mode. Note: The shadowmask texture
     * is only created if `shadowmask_mode` is not `LightmapGIData.SHADOWMASK_MODE_NONE`. To see a
     * difference, you need to bake lightmaps again after switching from
     * `LightmapGIData.SHADOWMASK_MODE_NONE` to any other mode.
     *
     * Generated from Godot docs: LightmapGI.set_shadowmask_mode
     */
    fun setShadowmaskMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setShadowmaskModeBind, handle, mode)
    }

    /**
     * The shadowmasking policy to use for directional shadows on static objects that are baked with
     * this `LightmapGI` instance. Shadowmasking allows `DirectionalLight3D` nodes to cast shadows even
     * outside the range defined by their `DirectionalLight3D.directional_shadow_max_distance`
     * property. This is done by baking a texture that contains a shadowmap for the directional light,
     * then using this texture according to the current shadowmask mode. Note: The shadowmask texture
     * is only created if `shadowmask_mode` is not `LightmapGIData.SHADOWMASK_MODE_NONE`. To see a
     * difference, you need to bake lightmaps again after switching from
     * `LightmapGIData.SHADOWMASK_MODE_NONE` to any other mode.
     *
     * Generated from Godot docs: LightmapGI.get_shadowmask_mode
     */
    fun getShadowmaskMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShadowmaskModeBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setUseTextureForBouncesBind, handle, useTextureForBounces)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTextureForBouncesBind, handle)
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
        ObjectCalls.ptrcallWithObjectArgs(setCameraAttributesBind, handle, listOf(cameraAttributes?.requireOpenHandle() ?: MemorySegment.NULL))
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
        return CameraAttributes.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCameraAttributesBind, handle))
    }

    companion object {
        @JvmStatic
        fun create(): LightmapGI =
            LightmapGI(ObjectCalls.constructObject("LightmapGI"))

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
