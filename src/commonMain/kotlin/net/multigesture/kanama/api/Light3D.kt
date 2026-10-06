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
 * Provides a base class for different kinds of light nodes.
 *
 * Generated from Godot docs: Light3D
 */
open class Light3D(handle: GodotHandle) : VisualInstance3D(handle) {
    var lightIntensityLumens: Double
        @JvmName("lightIntensityLumensProperty")
        get() = getParam(Light3D.Param.INTENSITY)
        @JvmName("setLightIntensityLumensProperty")
        set(value) = setParam(Light3D.Param.INTENSITY, value)

    var lightIntensityLux: Double
        @JvmName("lightIntensityLuxProperty")
        get() = getParam(Light3D.Param.INTENSITY)
        @JvmName("setLightIntensityLuxProperty")
        set(value) = setParam(Light3D.Param.INTENSITY, value)

    var lightTemperature: Double
        @JvmName("lightTemperatureProperty")
        get() = getTemperature()
        @JvmName("setLightTemperatureProperty")
        set(value) = setTemperature(value)

    var lightColor: Color
        @JvmName("lightColorProperty")
        get() = getColor()
        @JvmName("setLightColorProperty")
        set(value) = setColor(value)

    var lightEnergy: Double
        @JvmName("lightEnergyProperty")
        get() = getParam(Light3D.Param.ENERGY)
        @JvmName("setLightEnergyProperty")
        set(value) = setParam(Light3D.Param.ENERGY, value)

    var lightIndirectEnergy: Double
        @JvmName("lightIndirectEnergyProperty")
        get() = getParam(Light3D.Param.INDIRECT_ENERGY)
        @JvmName("setLightIndirectEnergyProperty")
        set(value) = setParam(Light3D.Param.INDIRECT_ENERGY, value)

    var lightVolumetricFogEnergy: Double
        @JvmName("lightVolumetricFogEnergyProperty")
        get() = getParam(Light3D.Param.VOLUMETRIC_FOG_ENERGY)
        @JvmName("setLightVolumetricFogEnergyProperty")
        set(value) = setParam(Light3D.Param.VOLUMETRIC_FOG_ENERGY, value)

    var lightProjector: Texture2D?
        @JvmName("lightProjectorProperty")
        get() = getProjector()
        @JvmName("setLightProjectorProperty")
        set(value) = setProjector(value)

    var lightSize: Double
        @JvmName("lightSizeProperty")
        get() = getParam(Light3D.Param.SIZE)
        @JvmName("setLightSizeProperty")
        set(value) = setParam(Light3D.Param.SIZE, value)

    var lightAngularDistance: Double
        @JvmName("lightAngularDistanceProperty")
        get() = getParam(Light3D.Param.SIZE)
        @JvmName("setLightAngularDistanceProperty")
        set(value) = setParam(Light3D.Param.SIZE, value)

    var lightNegative: Boolean
        @JvmName("lightNegativeProperty")
        get() = isNegative()
        @JvmName("setLightNegativeProperty")
        set(value) = setNegative(value)

    var lightSpecular: Double
        @JvmName("lightSpecularProperty")
        get() = getParam(Light3D.Param.SPECULAR)
        @JvmName("setLightSpecularProperty")
        set(value) = setParam(Light3D.Param.SPECULAR, value)

    var lightBakeMode: Light3D.BakeMode
        @JvmName("lightBakeModeProperty")
        get() = getBakeMode()
        @JvmName("setLightBakeModeProperty")
        set(value) = setBakeMode(value)

    var lightCullMask: Long
        @JvmName("lightCullMaskProperty")
        get() = getCullMask()
        @JvmName("setLightCullMaskProperty")
        set(value) = setCullMask(value)

    var shadowEnabled: Boolean
        @JvmName("shadowEnabledProperty")
        get() = hasShadow()
        @JvmName("setShadowEnabledProperty")
        set(value) = setShadow(value)

    var shadowBias: Double
        @JvmName("shadowBiasProperty")
        get() = getParam(Light3D.Param.SHADOW_BIAS)
        @JvmName("setShadowBiasProperty")
        set(value) = setParam(Light3D.Param.SHADOW_BIAS, value)

    var shadowNormalBias: Double
        @JvmName("shadowNormalBiasProperty")
        get() = getParam(Light3D.Param.SHADOW_NORMAL_BIAS)
        @JvmName("setShadowNormalBiasProperty")
        set(value) = setParam(Light3D.Param.SHADOW_NORMAL_BIAS, value)

    var shadowReverseCullFace: Boolean
        @JvmName("shadowReverseCullFaceProperty")
        get() = getShadowReverseCullFace()
        @JvmName("setShadowReverseCullFaceProperty")
        set(value) = setShadowReverseCullFace(value)

    var shadowTransmittanceBias: Double
        @JvmName("shadowTransmittanceBiasProperty")
        get() = getParam(Light3D.Param.TRANSMITTANCE_BIAS)
        @JvmName("setShadowTransmittanceBiasProperty")
        set(value) = setParam(Light3D.Param.TRANSMITTANCE_BIAS, value)

    var shadowOpacity: Double
        @JvmName("shadowOpacityProperty")
        get() = getParam(Light3D.Param.SHADOW_OPACITY)
        @JvmName("setShadowOpacityProperty")
        set(value) = setParam(Light3D.Param.SHADOW_OPACITY, value)

    var shadowBlur: Double
        @JvmName("shadowBlurProperty")
        get() = getParam(Light3D.Param.SHADOW_BLUR)
        @JvmName("setShadowBlurProperty")
        set(value) = setParam(Light3D.Param.SHADOW_BLUR, value)

    var shadowCasterMask: Long
        @JvmName("shadowCasterMaskProperty")
        get() = getShadowCasterMask()
        @JvmName("setShadowCasterMaskProperty")
        set(value) = setShadowCasterMask(value)

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

    var distanceFadeShadow: Double
        @JvmName("distanceFadeShadowProperty")
        get() = getDistanceFadeShadow()
        @JvmName("setDistanceFadeShadowProperty")
        set(value) = setDistanceFadeShadow(value)

    var distanceFadeLength: Double
        @JvmName("distanceFadeLengthProperty")
        get() = getDistanceFadeLength()
        @JvmName("setDistanceFadeLengthProperty")
        set(value) = setDistanceFadeLength(value)

    var editorOnly: Boolean
        @JvmName("editorOnlyProperty")
        get() = isEditorOnly()
        @JvmName("setEditorOnlyProperty")
        set(value) = setEditorOnly(value)

    /**
     * If `true`, the light only appears in the editor and will not be visible at runtime. If `true`,
     * the light will never be baked in `LightmapGI` regardless of its `light_bake_mode`.
     *
     * Generated from Godot docs: Light3D.set_editor_only
     */
    fun setEditorOnly(editorOnly: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEditorOnlyBind, segment, editorOnly)
    }

    /**
     * If `true`, the light only appears in the editor and will not be visible at runtime. If `true`,
     * the light will never be baked in `LightmapGI` regardless of its `light_bake_mode`.
     *
     * Generated from Godot docs: Light3D.is_editor_only
     */
    fun isEditorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEditorOnlyBind, segment)
    }

    /**
     * The opacity to use when rendering the light's shadow map. Values lower than `1.0` make the light
     * appear through shadows. This can be used to fake global illumination at a low performance cost.
     *
     * Generated from Godot docs: Light3D.set_param
     */
    fun setParam(param: Light3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(Binds.setParamBind, segment, param.value, value)
    }

    /**
     * The opacity to use when rendering the light's shadow map. Values lower than `1.0` make the light
     * appear through shadows. This can be used to fake global illumination at a low performance cost.
     *
     * Generated from Godot docs: Light3D.get_param
     */
    fun getParam(param: Light3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getParamBind, segment, param.value)
    }

    /**
     * If `true`, the light will cast real-time shadows. This has a significant performance cost. Only
     * enable shadow rendering when it makes a noticeable difference in the scene's appearance, and
     * consider using `distance_fade_enabled` to hide the light when far away from the `Camera3D`.
     *
     * Generated from Godot docs: Light3D.set_shadow
     */
    fun setShadow(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShadowBind, segment, enabled)
    }

    /**
     * If `true`, the light will cast real-time shadows. This has a significant performance cost. Only
     * enable shadow rendering when it makes a noticeable difference in the scene's appearance, and
     * consider using `distance_fade_enabled` to hide the light when far away from the `Camera3D`.
     *
     * Generated from Godot docs: Light3D.has_shadow
     */
    fun hasShadow(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasShadowBind, segment)
    }

    /**
     * If `true`, the light's effect is reversed, darkening areas and casting bright shadows.
     *
     * Generated from Godot docs: Light3D.set_negative
     */
    fun setNegative(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setNegativeBind, segment, enabled)
    }

    /**
     * If `true`, the light's effect is reversed, darkening areas and casting bright shadows.
     *
     * Generated from Godot docs: Light3D.is_negative
     */
    fun isNegative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isNegativeBind, segment)
    }

    /**
     * The light will affect objects in the selected layers. Note: The light cull mask is ignored by
     * `VoxelGI`, SDFGI, `LightmapGI`, and volumetric fog. These will always render lights in a way
     * that ignores the cull mask. See also `VisualInstance3D.layers`.
     *
     * Generated from Godot docs: Light3D.set_cull_mask
     */
    fun setCullMask(cullMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCullMaskBind, segment, cullMask)
    }

    /**
     * The light will affect objects in the selected layers. Note: The light cull mask is ignored by
     * `VoxelGI`, SDFGI, `LightmapGI`, and volumetric fog. These will always render lights in a way
     * that ignores the cull mask. See also `VisualInstance3D.layers`.
     *
     * Generated from Godot docs: Light3D.get_cull_mask
     */
    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCullMaskBind, segment)
    }

    /**
     * If `true`, the light will smoothly fade away when far from the active `Camera3D` starting at
     * `distance_fade_begin`. This acts as a form of level of detail (LOD). The light will fade out
     * over `distance_fade_begin` + `distance_fade_length`, after which it will be culled and not sent
     * to the shader at all. Use this to reduce the number of active lights in a scene and thus improve
     * performance. Note: Only effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.set_enable_distance_fade
     */
    fun setEnableDistanceFade(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableDistanceFadeBind, segment, enable)
    }

    /**
     * If `true`, the light will smoothly fade away when far from the active `Camera3D` starting at
     * `distance_fade_begin`. This acts as a form of level of detail (LOD). The light will fade out
     * over `distance_fade_begin` + `distance_fade_length`, after which it will be culled and not sent
     * to the shader at all. Use this to reduce the number of active lights in a scene and thus improve
     * performance. Note: Only effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.is_distance_fade_enabled
     */
    fun isDistanceFadeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDistanceFadeEnabledBind, segment)
    }

    /**
     * The distance from the camera at which the light begins to fade away (in 3D units). Note: Only
     * effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.set_distance_fade_begin
     */
    fun setDistanceFadeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDistanceFadeBeginBind, segment, distance)
    }

    /**
     * The distance from the camera at which the light begins to fade away (in 3D units). Note: Only
     * effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.get_distance_fade_begin
     */
    fun getDistanceFadeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDistanceFadeBeginBind, segment)
    }

    /**
     * The distance from the camera at which the light's shadow cuts off (in 3D units). Set this to a
     * value lower than `distance_fade_begin` + `distance_fade_length` to further improve performance,
     * as shadow rendering is often more expensive than light rendering itself. Note: Only effective
     * for `OmniLight3D` and `SpotLight3D`, and only when `shadow_enabled` is `true`.
     *
     * Generated from Godot docs: Light3D.set_distance_fade_shadow
     */
    fun setDistanceFadeShadow(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDistanceFadeShadowBind, segment, distance)
    }

    /**
     * The distance from the camera at which the light's shadow cuts off (in 3D units). Set this to a
     * value lower than `distance_fade_begin` + `distance_fade_length` to further improve performance,
     * as shadow rendering is often more expensive than light rendering itself. Note: Only effective
     * for `OmniLight3D` and `SpotLight3D`, and only when `shadow_enabled` is `true`.
     *
     * Generated from Godot docs: Light3D.get_distance_fade_shadow
     */
    fun getDistanceFadeShadow(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDistanceFadeShadowBind, segment)
    }

    /**
     * Distance over which the light and its shadow fades. The light's energy and shadow's opacity is
     * progressively reduced over this distance and is completely invisible at the end. Note: Only
     * effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.set_distance_fade_length
     */
    fun setDistanceFadeLength(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDistanceFadeLengthBind, segment, distance)
    }

    /**
     * Distance over which the light and its shadow fades. The light's energy and shadow's opacity is
     * progressively reduced over this distance and is completely invisible at the end. Note: Only
     * effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.get_distance_fade_length
     */
    fun getDistanceFadeLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDistanceFadeLengthBind, segment)
    }

    /**
     * The light's color in nonlinear sRGB encoding. An overbright color can be used to achieve a
     * result equivalent to increasing the light's `light_energy`.
     *
     * Generated from Godot docs: Light3D.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
    }

    /**
     * The light's color in nonlinear sRGB encoding. An overbright color can be used to achieve a
     * result equivalent to increasing the light's `light_energy`.
     *
     * Generated from Godot docs: Light3D.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getColorBind, segment)
    }

    /**
     * If `true`, reverses the backface culling of the mesh. This can be useful when you have a flat
     * mesh that has a light behind it. If you need to cast a shadow on both sides of the mesh, set the
     * mesh to use double-sided shadows with `GeometryInstance3D.ShadowCastingSetting.DOUBLE_SIDED`.
     *
     * Generated from Godot docs: Light3D.set_shadow_reverse_cull_face
     */
    fun setShadowReverseCullFace(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShadowReverseCullFaceBind, segment, enable)
    }

    /**
     * If `true`, reverses the backface culling of the mesh. This can be useful when you have a flat
     * mesh that has a light behind it. If you need to cast a shadow on both sides of the mesh, set the
     * mesh to use double-sided shadows with `GeometryInstance3D.ShadowCastingSetting.DOUBLE_SIDED`.
     *
     * Generated from Godot docs: Light3D.get_shadow_reverse_cull_face
     */
    fun getShadowReverseCullFace(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getShadowReverseCullFaceBind, segment)
    }

    /**
     * The light will only cast shadows using objects in the selected layers.
     *
     * Generated from Godot docs: Light3D.set_shadow_caster_mask
     */
    fun setShadowCasterMask(casterMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setShadowCasterMaskBind, segment, casterMask)
    }

    /**
     * The light will only cast shadows using objects in the selected layers.
     *
     * Generated from Godot docs: Light3D.get_shadow_caster_mask
     */
    fun getShadowCasterMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getShadowCasterMaskBind, segment)
    }

    /**
     * The light's bake mode. This will affect the global illumination techniques that have an effect
     * on the light's rendering. Note: Meshes' global illumination mode will also affect the global
     * illumination rendering. See `GeometryInstance3D.gi_mode`.
     *
     * Generated from Godot docs: Light3D.set_bake_mode
     */
    fun setBakeMode(bakeMode: Light3D.BakeMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBakeModeBind, segment, bakeMode.value)
    }

    /**
     * The light's bake mode. This will affect the global illumination techniques that have an effect
     * on the light's rendering. Note: Meshes' global illumination mode will also affect the global
     * illumination rendering. See `GeometryInstance3D.gi_mode`.
     *
     * Generated from Godot docs: Light3D.get_bake_mode
     */
    fun getBakeMode(): Light3D.BakeMode {
        return Light3D.BakeMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBakeModeBind, segment))
    }

    /**
     * `Texture2D` projected by light. `shadow_enabled` must be on for the projector to work. Light
     * projectors make the light appear as if it is shining through a colored but transparent object,
     * almost like light shining through stained-glass. Note: Unlike `BaseMaterial3D` whose filter mode
     * can be adjusted on a per-material basis, the filter mode for light projector textures is set
     * globally with `ProjectSettings.rendering/textures/light_projectors/filter`. Note: Light
     * projector textures are only supported in the Forward+ and Mobile rendering methods, not
     * Compatibility.
     *
     * Generated from Godot docs: Light3D.set_projector
     */
    fun setProjector(projector: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setProjectorBind, segment, listOf(projector?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * `Texture2D` projected by light. `shadow_enabled` must be on for the projector to work. Light
     * projectors make the light appear as if it is shining through a colored but transparent object,
     * almost like light shining through stained-glass. Note: Unlike `BaseMaterial3D` whose filter mode
     * can be adjusted on a per-material basis, the filter mode for light projector textures is set
     * globally with `ProjectSettings.rendering/textures/light_projectors/filter`. Note: Light
     * projector textures are only supported in the Forward+ and Mobile rendering methods, not
     * Compatibility.
     *
     * Generated from Godot docs: Light3D.get_projector
     */
    fun getProjector(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getProjectorBind, segment))
    }

    /**
     * Sets the color temperature of the light source, measured in Kelvin. This is used to calculate a
     * correlated color temperature which tints the `light_color`. The sun on a cloudy day is
     * approximately 6500 Kelvin, on a clear day it is between 5500 to 6000 Kelvin, and on a clear day
     * at sunrise or sunset it ranges to around 1850 Kelvin.
     *
     * Generated from Godot docs: Light3D.set_temperature
     */
    fun setTemperature(temperature: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTemperatureBind, segment, temperature)
    }

    /**
     * Sets the color temperature of the light source, measured in Kelvin. This is used to calculate a
     * correlated color temperature which tints the `light_color`. The sun on a cloudy day is
     * approximately 6500 Kelvin, on a clear day it is between 5500 to 6000 Kelvin, and on a clear day
     * at sunrise or sunset it ranges to around 1850 Kelvin.
     *
     * Generated from Godot docs: Light3D.get_temperature
     */
    fun getTemperature(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTemperatureBind, segment)
    }

    /**
     * Returns the `Color` of an idealized blackbody at the given `light_temperature`. This value is
     * calculated internally based on the `light_temperature`. This `Color` is multiplied by
     * `light_color` before being sent to the `RenderingServer`.
     *
     * Generated from Godot docs: Light3D.get_correlated_color
     */
    fun getCorrelatedColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getCorrelatedColorBind, segment)
    }

    /**
     * Godot's `Light3D.Param` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Light3D.Param.<NAME>`).
     *
     * Generated from Godot docs: Light3D.Param
     */
    @JvmInline
    value class Param(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Constant for accessing `light_energy`.
             *
             * Generated from Godot docs: Light3D.PARAM_ENERGY
             */
            val ENERGY: Param get() = Param(0L)
            /**
             * Constant for accessing `light_indirect_energy`.
             *
             * Generated from Godot docs: Light3D.PARAM_INDIRECT_ENERGY
             */
            val INDIRECT_ENERGY: Param get() = Param(1L)
            /**
             * Constant for accessing `light_volumetric_fog_energy`.
             *
             * Generated from Godot docs: Light3D.PARAM_VOLUMETRIC_FOG_ENERGY
             */
            val VOLUMETRIC_FOG_ENERGY: Param get() = Param(2L)
            /**
             * Constant for accessing `light_specular`.
             *
             * Generated from Godot docs: Light3D.PARAM_SPECULAR
             */
            val SPECULAR: Param get() = Param(3L)
            /**
             * Constant for accessing `OmniLight3D.omni_range` or `SpotLight3D.spot_range`.
             *
             * Generated from Godot docs: Light3D.PARAM_RANGE
             */
            val RANGE: Param get() = Param(4L)
            /**
             * Constant for accessing `light_size`.
             *
             * Generated from Godot docs: Light3D.PARAM_SIZE
             */
            val SIZE: Param get() = Param(5L)
            /**
             * Constant for accessing `OmniLight3D.omni_attenuation` or `SpotLight3D.spot_attenuation`.
             *
             * Generated from Godot docs: Light3D.PARAM_ATTENUATION
             */
            val ATTENUATION: Param get() = Param(6L)
            /**
             * Constant for accessing `SpotLight3D.spot_angle`.
             *
             * Generated from Godot docs: Light3D.PARAM_SPOT_ANGLE
             */
            val SPOT_ANGLE: Param get() = Param(7L)
            /**
             * Constant for accessing `SpotLight3D.spot_angle_attenuation`.
             *
             * Generated from Godot docs: Light3D.PARAM_SPOT_ATTENUATION
             */
            val SPOT_ATTENUATION: Param get() = Param(8L)
            /**
             * Constant for accessing `DirectionalLight3D.directional_shadow_max_distance`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_MAX_DISTANCE
             */
            val SHADOW_MAX_DISTANCE: Param get() = Param(9L)
            /**
             * Constant for accessing `DirectionalLight3D.directional_shadow_split_1`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_SPLIT_1_OFFSET
             */
            val SHADOW_SPLIT_1_OFFSET: Param get() = Param(10L)
            /**
             * Constant for accessing `DirectionalLight3D.directional_shadow_split_2`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_SPLIT_2_OFFSET
             */
            val SHADOW_SPLIT_2_OFFSET: Param get() = Param(11L)
            /**
             * Constant for accessing `DirectionalLight3D.directional_shadow_split_3`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_SPLIT_3_OFFSET
             */
            val SHADOW_SPLIT_3_OFFSET: Param get() = Param(12L)
            /**
             * Constant for accessing `DirectionalLight3D.directional_shadow_fade_start`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_FADE_START
             */
            val SHADOW_FADE_START: Param get() = Param(13L)
            /**
             * Constant for accessing `shadow_normal_bias`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_NORMAL_BIAS
             */
            val SHADOW_NORMAL_BIAS: Param get() = Param(14L)
            /**
             * Constant for accessing `shadow_bias`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_BIAS
             */
            val SHADOW_BIAS: Param get() = Param(15L)
            /**
             * Constant for accessing `DirectionalLight3D.directional_shadow_pancake_size`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_PANCAKE_SIZE
             */
            val SHADOW_PANCAKE_SIZE: Param get() = Param(16L)
            /**
             * Constant for accessing `shadow_opacity`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_OPACITY
             */
            val SHADOW_OPACITY: Param get() = Param(17L)
            /**
             * Constant for accessing `shadow_blur`.
             *
             * Generated from Godot docs: Light3D.PARAM_SHADOW_BLUR
             */
            val SHADOW_BLUR: Param get() = Param(18L)
            /**
             * Constant for accessing `shadow_transmittance_bias`.
             *
             * Generated from Godot docs: Light3D.PARAM_TRANSMITTANCE_BIAS
             */
            val TRANSMITTANCE_BIAS: Param get() = Param(19L)
            /**
             * Constant for accessing `light_intensity_lumens` and `light_intensity_lux`. Only used when
             * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is `true`.
             *
             * Generated from Godot docs: Light3D.PARAM_INTENSITY
             */
            val INTENSITY: Param get() = Param(20L)
            /**
             * Represents the size of the `Param` enum.
             *
             * Generated from Godot docs: Light3D.PARAM_MAX
             */
            val MAX: Param get() = Param(21L)
        }
    }

    /**
     * Godot's `Light3D.BakeMode` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Light3D.BakeMode.<NAME>`).
     *
     * Generated from Godot docs: Light3D.BakeMode
     */
    @JvmInline
    value class BakeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Light is ignored when baking. This is the fastest mode, but the light will not be taken into
             * account when baking global illumination. This mode should generally be used for dynamic lights
             * that change quickly, as the effect of global illumination is less noticeable on those lights.
             * Note: Hiding a light does not affect baking `LightmapGI`. Hiding a light will still affect
             * baking `VoxelGI` and SDFGI (see `Environment.sdfgi_enabled`).
             *
             * Generated from Godot docs: Light3D.BAKE_DISABLED
             */
            val DISABLED: BakeMode get() = BakeMode(0L)
            /**
             * Light is taken into account in static baking (`VoxelGI`, `LightmapGI`, SDFGI
             * (`Environment.sdfgi_enabled`)). The light can be moved around or modified, but its global
             * illumination will not update in real-time. Note: The light is not baked in `LightmapGI` if
             * `editor_only` is `true`. Note: When using `LightmapGI`, both the direct and indirect light are
             * baked. Since direct light is baked, the light doesn't display a specular lobe on static
             * lightmapped meshes. Shadows on static lightmapped meshes will also look less detailed, but the
             * light still casts shadows that can be displayed on dynamic objects. Since real-time light
             * computations are skipped on static lightmapped meshes, this bake mode improves runtime
             * performance compared to `BakeMode.DYNAMIC` and `BakeMode.DISABLED`.
             *
             * Generated from Godot docs: Light3D.BAKE_STATIC
             */
            val STATIC: BakeMode get() = BakeMode(1L)
            /**
             * Light is taken into account in dynamic baking (`VoxelGI` and SDFGI
             * (`Environment.sdfgi_enabled`)). The light can be moved around or modified with global
             * illumination updating in real-time. The light's global illumination appearance will be slightly
             * different compared to `BakeMode.STATIC`. This has a greater performance cost compared to
             * `BakeMode.STATIC`. When using SDFGI, the update speed of dynamic lights is affected by
             * `ProjectSettings.rendering/global_illumination/sdfgi/frames_to_update_lights`. Note: When using
             * `LightmapGI`, the light's indirect light is baked, but direct light and shadows remain
             * real-time. This mode allows performing subtle changes to a light's color, energy, and position
             * while still looking fairly correct. For example, you can use this to create flickering static
             * torches that have their indirect light baked.
             *
             * Generated from Godot docs: Light3D.BAKE_DYNAMIC
             */
            val DYNAMIC: BakeMode get() = BakeMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Light3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Light3D? =
            if (handle.address() == 0L) null else Light3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_EDITOR_ONLY_HASH = 2586408642L
        @JvmField
        val setEditorOnlyBind =
            ObjectCalls.getMethodBind("Light3D", "set_editor_only", SET_EDITOR_ONLY_HASH)

        private const val IS_EDITOR_ONLY_HASH = 36873697L
        @JvmField
        val isEditorOnlyBind =
            ObjectCalls.getMethodBind("Light3D", "is_editor_only", IS_EDITOR_ONLY_HASH)

        private const val SET_PARAM_HASH = 1722734213L
        @JvmField
        val setParamBind =
            ObjectCalls.getMethodBind("Light3D", "set_param", SET_PARAM_HASH)

        private const val GET_PARAM_HASH = 1844084987L
        @JvmField
        val getParamBind =
            ObjectCalls.getMethodBind("Light3D", "get_param", GET_PARAM_HASH)

        private const val SET_SHADOW_HASH = 2586408642L
        @JvmField
        val setShadowBind =
            ObjectCalls.getMethodBind("Light3D", "set_shadow", SET_SHADOW_HASH)

        private const val HAS_SHADOW_HASH = 36873697L
        @JvmField
        val hasShadowBind =
            ObjectCalls.getMethodBind("Light3D", "has_shadow", HAS_SHADOW_HASH)

        private const val SET_NEGATIVE_HASH = 2586408642L
        @JvmField
        val setNegativeBind =
            ObjectCalls.getMethodBind("Light3D", "set_negative", SET_NEGATIVE_HASH)

        private const val IS_NEGATIVE_HASH = 36873697L
        @JvmField
        val isNegativeBind =
            ObjectCalls.getMethodBind("Light3D", "is_negative", IS_NEGATIVE_HASH)

        private const val SET_CULL_MASK_HASH = 1286410249L
        @JvmField
        val setCullMaskBind =
            ObjectCalls.getMethodBind("Light3D", "set_cull_mask", SET_CULL_MASK_HASH)

        private const val GET_CULL_MASK_HASH = 3905245786L
        @JvmField
        val getCullMaskBind =
            ObjectCalls.getMethodBind("Light3D", "get_cull_mask", GET_CULL_MASK_HASH)

        private const val SET_ENABLE_DISTANCE_FADE_HASH = 2586408642L
        @JvmField
        val setEnableDistanceFadeBind =
            ObjectCalls.getMethodBind("Light3D", "set_enable_distance_fade", SET_ENABLE_DISTANCE_FADE_HASH)

        private const val IS_DISTANCE_FADE_ENABLED_HASH = 36873697L
        @JvmField
        val isDistanceFadeEnabledBind =
            ObjectCalls.getMethodBind("Light3D", "is_distance_fade_enabled", IS_DISTANCE_FADE_ENABLED_HASH)

        private const val SET_DISTANCE_FADE_BEGIN_HASH = 373806689L
        @JvmField
        val setDistanceFadeBeginBind =
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_begin", SET_DISTANCE_FADE_BEGIN_HASH)

        private const val GET_DISTANCE_FADE_BEGIN_HASH = 1740695150L
        @JvmField
        val getDistanceFadeBeginBind =
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_begin", GET_DISTANCE_FADE_BEGIN_HASH)

        private const val SET_DISTANCE_FADE_SHADOW_HASH = 373806689L
        @JvmField
        val setDistanceFadeShadowBind =
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_shadow", SET_DISTANCE_FADE_SHADOW_HASH)

        private const val GET_DISTANCE_FADE_SHADOW_HASH = 1740695150L
        @JvmField
        val getDistanceFadeShadowBind =
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_shadow", GET_DISTANCE_FADE_SHADOW_HASH)

        private const val SET_DISTANCE_FADE_LENGTH_HASH = 373806689L
        @JvmField
        val setDistanceFadeLengthBind =
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_length", SET_DISTANCE_FADE_LENGTH_HASH)

        private const val GET_DISTANCE_FADE_LENGTH_HASH = 1740695150L
        @JvmField
        val getDistanceFadeLengthBind =
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_length", GET_DISTANCE_FADE_LENGTH_HASH)

        private const val SET_COLOR_HASH = 2920490490L
        @JvmField
        val setColorBind =
            ObjectCalls.getMethodBind("Light3D", "set_color", SET_COLOR_HASH)

        private const val GET_COLOR_HASH = 3444240500L
        @JvmField
        val getColorBind =
            ObjectCalls.getMethodBind("Light3D", "get_color", GET_COLOR_HASH)

        private const val SET_SHADOW_REVERSE_CULL_FACE_HASH = 2586408642L
        @JvmField
        val setShadowReverseCullFaceBind =
            ObjectCalls.getMethodBind("Light3D", "set_shadow_reverse_cull_face", SET_SHADOW_REVERSE_CULL_FACE_HASH)

        private const val GET_SHADOW_REVERSE_CULL_FACE_HASH = 36873697L
        @JvmField
        val getShadowReverseCullFaceBind =
            ObjectCalls.getMethodBind("Light3D", "get_shadow_reverse_cull_face", GET_SHADOW_REVERSE_CULL_FACE_HASH)

        private const val SET_SHADOW_CASTER_MASK_HASH = 1286410249L
        @JvmField
        val setShadowCasterMaskBind =
            ObjectCalls.getMethodBind("Light3D", "set_shadow_caster_mask", SET_SHADOW_CASTER_MASK_HASH)

        private const val GET_SHADOW_CASTER_MASK_HASH = 3905245786L
        @JvmField
        val getShadowCasterMaskBind =
            ObjectCalls.getMethodBind("Light3D", "get_shadow_caster_mask", GET_SHADOW_CASTER_MASK_HASH)

        private const val SET_BAKE_MODE_HASH = 37739303L
        @JvmField
        val setBakeModeBind =
            ObjectCalls.getMethodBind("Light3D", "set_bake_mode", SET_BAKE_MODE_HASH)

        private const val GET_BAKE_MODE_HASH = 371737608L
        @JvmField
        val getBakeModeBind =
            ObjectCalls.getMethodBind("Light3D", "get_bake_mode", GET_BAKE_MODE_HASH)

        private const val SET_PROJECTOR_HASH = 4051416890L
        @JvmField
        val setProjectorBind =
            ObjectCalls.getMethodBind("Light3D", "set_projector", SET_PROJECTOR_HASH)

        private const val GET_PROJECTOR_HASH = 3635182373L
        @JvmField
        val getProjectorBind =
            ObjectCalls.getMethodBind("Light3D", "get_projector", GET_PROJECTOR_HASH)

        private const val SET_TEMPERATURE_HASH = 373806689L
        @JvmField
        val setTemperatureBind =
            ObjectCalls.getMethodBind("Light3D", "set_temperature", SET_TEMPERATURE_HASH)

        private const val GET_TEMPERATURE_HASH = 1740695150L
        @JvmField
        val getTemperatureBind =
            ObjectCalls.getMethodBind("Light3D", "get_temperature", GET_TEMPERATURE_HASH)

        private const val GET_CORRELATED_COLOR_HASH = 3444240500L
        @JvmField
        val getCorrelatedColorBind =
            ObjectCalls.getMethodBind("Light3D", "get_correlated_color", GET_CORRELATED_COLOR_HASH)
    }
}
