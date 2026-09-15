package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Provides a base class for different kinds of light nodes.
 *
 * Generated from Godot docs: Light3D
 */
open class Light3D(handle: GodotHandle) : VisualInstance3D(handle) {
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
        get() = getParam(PARAM_ENERGY)
        @JvmName("setLightEnergyProperty")
        set(value) = setParam(PARAM_ENERGY, value)

    var lightProjector: Texture2D?
        @JvmName("lightProjectorProperty")
        get() = getProjector()
        @JvmName("setLightProjectorProperty")
        set(value) = setProjector(value)

    var lightNegative: Boolean
        @JvmName("lightNegativeProperty")
        get() = isNegative()
        @JvmName("setLightNegativeProperty")
        set(value) = setNegative(value)

    var lightBakeMode: Long
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

    var shadowReverseCullFace: Boolean
        @JvmName("shadowReverseCullFaceProperty")
        get() = getShadowReverseCullFace()
        @JvmName("setShadowReverseCullFaceProperty")
        set(value) = setShadowReverseCullFace(value)

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
        ObjectCalls.ptrcallWithBoolArg(setEditorOnlyBind, segment, editorOnly)
    }

    /**
     * If `true`, the light only appears in the editor and will not be visible at runtime. If `true`,
     * the light will never be baked in `LightmapGI` regardless of its `light_bake_mode`.
     *
     * Generated from Godot docs: Light3D.is_editor_only
     */
    fun isEditorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditorOnlyBind, segment)
    }

    /**
     * The opacity to use when rendering the light's shadow map. Values lower than `1.0` make the light
     * appear through shadows. This can be used to fake global illumination at a low performance cost.
     *
     * Generated from Godot docs: Light3D.set_param
     */
    fun setParam(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, segment, param, value)
    }

    /**
     * The opacity to use when rendering the light's shadow map. Values lower than `1.0` make the light
     * appear through shadows. This can be used to fake global illumination at a low performance cost.
     *
     * Generated from Godot docs: Light3D.get_param
     */
    fun getParam(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, segment, param)
    }

    /**
     * If `true`, the light will cast real-time shadows. This has a significant performance cost. Only
     * enable shadow rendering when it makes a noticeable difference in the scene's appearance, and
     * consider using `distance_fade_enabled` to hide the light when far away from the `Camera3D`.
     *
     * Generated from Godot docs: Light3D.set_shadow
     */
    fun setShadow(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShadowBind, segment, enabled)
    }

    /**
     * If `true`, the light will cast real-time shadows. This has a significant performance cost. Only
     * enable shadow rendering when it makes a noticeable difference in the scene's appearance, and
     * consider using `distance_fade_enabled` to hide the light when far away from the `Camera3D`.
     *
     * Generated from Godot docs: Light3D.has_shadow
     */
    fun hasShadow(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasShadowBind, segment)
    }

    /**
     * If `true`, the light's effect is reversed, darkening areas and casting bright shadows.
     *
     * Generated from Godot docs: Light3D.set_negative
     */
    fun setNegative(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNegativeBind, segment, enabled)
    }

    /**
     * If `true`, the light's effect is reversed, darkening areas and casting bright shadows.
     *
     * Generated from Godot docs: Light3D.is_negative
     */
    fun isNegative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNegativeBind, segment)
    }

    /**
     * The light will affect objects in the selected layers. Note: The light cull mask is ignored by
     * `VoxelGI`, SDFGI, `LightmapGI`, and volumetric fog. These will always render lights in a way
     * that ignores the cull mask. See also `VisualInstance3D.layers`.
     *
     * Generated from Godot docs: Light3D.set_cull_mask
     */
    fun setCullMask(cullMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, segment, cullMask)
    }

    /**
     * The light will affect objects in the selected layers. Note: The light cull mask is ignored by
     * `VoxelGI`, SDFGI, `LightmapGI`, and volumetric fog. These will always render lights in a way
     * that ignores the cull mask. See also `VisualInstance3D.layers`.
     *
     * Generated from Godot docs: Light3D.get_cull_mask
     */
    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(setEnableDistanceFadeBind, segment, enable)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isDistanceFadeEnabledBind, segment)
    }

    /**
     * The distance from the camera at which the light begins to fade away (in 3D units). Note: Only
     * effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.set_distance_fade_begin
     */
    fun setDistanceFadeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeBeginBind, segment, distance)
    }

    /**
     * The distance from the camera at which the light begins to fade away (in 3D units). Note: Only
     * effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.get_distance_fade_begin
     */
    fun getDistanceFadeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeBeginBind, segment)
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
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeShadowBind, segment, distance)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeShadowBind, segment)
    }

    /**
     * Distance over which the light and its shadow fades. The light's energy and shadow's opacity is
     * progressively reduced over this distance and is completely invisible at the end. Note: Only
     * effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.set_distance_fade_length
     */
    fun setDistanceFadeLength(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeLengthBind, segment, distance)
    }

    /**
     * Distance over which the light and its shadow fades. The light's energy and shadow's opacity is
     * progressively reduced over this distance and is completely invisible at the end. Note: Only
     * effective for `OmniLight3D` and `SpotLight3D`.
     *
     * Generated from Godot docs: Light3D.get_distance_fade_length
     */
    fun getDistanceFadeLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeLengthBind, segment)
    }

    /**
     * The light's color in nonlinear sRGB encoding. An overbright color can be used to achieve a
     * result equivalent to increasing the light's `light_energy`.
     *
     * Generated from Godot docs: Light3D.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, segment, color)
    }

    /**
     * The light's color in nonlinear sRGB encoding. An overbright color can be used to achieve a
     * result equivalent to increasing the light's `light_energy`.
     *
     * Generated from Godot docs: Light3D.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, segment)
    }

    /**
     * If `true`, reverses the backface culling of the mesh. This can be useful when you have a flat
     * mesh that has a light behind it. If you need to cast a shadow on both sides of the mesh, set the
     * mesh to use double-sided shadows with `GeometryInstance3D.SHADOW_CASTING_SETTING_DOUBLE_SIDED`.
     *
     * Generated from Godot docs: Light3D.set_shadow_reverse_cull_face
     */
    fun setShadowReverseCullFace(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShadowReverseCullFaceBind, segment, enable)
    }

    /**
     * If `true`, reverses the backface culling of the mesh. This can be useful when you have a flat
     * mesh that has a light behind it. If you need to cast a shadow on both sides of the mesh, set the
     * mesh to use double-sided shadows with `GeometryInstance3D.SHADOW_CASTING_SETTING_DOUBLE_SIDED`.
     *
     * Generated from Godot docs: Light3D.get_shadow_reverse_cull_face
     */
    fun getShadowReverseCullFace(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getShadowReverseCullFaceBind, segment)
    }

    /**
     * The light will only cast shadows using objects in the selected layers.
     *
     * Generated from Godot docs: Light3D.set_shadow_caster_mask
     */
    fun setShadowCasterMask(casterMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setShadowCasterMaskBind, segment, casterMask)
    }

    /**
     * The light will only cast shadows using objects in the selected layers.
     *
     * Generated from Godot docs: Light3D.get_shadow_caster_mask
     */
    fun getShadowCasterMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getShadowCasterMaskBind, segment)
    }

    /**
     * The light's bake mode. This will affect the global illumination techniques that have an effect
     * on the light's rendering. Note: Meshes' global illumination mode will also affect the global
     * illumination rendering. See `GeometryInstance3D.gi_mode`.
     *
     * Generated from Godot docs: Light3D.set_bake_mode
     */
    fun setBakeMode(bakeMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBakeModeBind, segment, bakeMode)
    }

    /**
     * The light's bake mode. This will affect the global illumination techniques that have an effect
     * on the light's rendering. Note: Meshes' global illumination mode will also affect the global
     * illumination rendering. See `GeometryInstance3D.gi_mode`.
     *
     * Generated from Godot docs: Light3D.get_bake_mode
     */
    fun getBakeMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBakeModeBind, segment)
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
        ObjectCalls.ptrcallWithObjectArgs(setProjectorBind, segment, listOf(projector?.requireOpenHandle() ?: MemorySegment.NULL))
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
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProjectorBind, segment))
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
        ObjectCalls.ptrcallWithDoubleArg(setTemperatureBind, segment, temperature)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getTemperatureBind, segment)
    }

    /**
     * Returns the `Color` of an idealized blackbody at the given `light_temperature`. This value is
     * calculated internally based on the `light_temperature`. This `Color` is multiplied by
     * `light_color` before being sent to the `RenderingServer`.
     *
     * Generated from Godot docs: Light3D.get_correlated_color
     */
    fun getCorrelatedColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getCorrelatedColorBind, segment)
    }

    companion object {
        const val PARAM_ENERGY: Long = 0L
        const val PARAM_INDIRECT_ENERGY: Long = 1L
        const val PARAM_VOLUMETRIC_FOG_ENERGY: Long = 2L
        const val PARAM_SPECULAR: Long = 3L
        const val PARAM_RANGE: Long = 4L
        const val PARAM_SIZE: Long = 5L
        const val PARAM_ATTENUATION: Long = 6L
        const val PARAM_SPOT_ANGLE: Long = 7L
        const val PARAM_SPOT_ATTENUATION: Long = 8L
        const val PARAM_SHADOW_MAX_DISTANCE: Long = 9L
        const val PARAM_SHADOW_SPLIT_1_OFFSET: Long = 10L
        const val PARAM_SHADOW_SPLIT_2_OFFSET: Long = 11L
        const val PARAM_SHADOW_SPLIT_3_OFFSET: Long = 12L
        const val PARAM_SHADOW_FADE_START: Long = 13L
        const val PARAM_SHADOW_NORMAL_BIAS: Long = 14L
        const val PARAM_SHADOW_BIAS: Long = 15L
        const val PARAM_SHADOW_PANCAKE_SIZE: Long = 16L
        const val PARAM_SHADOW_OPACITY: Long = 17L
        const val PARAM_SHADOW_BLUR: Long = 18L
        const val PARAM_TRANSMITTANCE_BIAS: Long = 19L
        const val PARAM_INTENSITY: Long = 20L
        const val PARAM_MAX: Long = 21L
        const val BAKE_DISABLED: Long = 0L
        const val BAKE_STATIC: Long = 1L
        const val BAKE_DYNAMIC: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Light3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): Light3D? =
            if (handle.address() == 0L) null else Light3D(GodotHandle(handle))

        private const val SET_EDITOR_ONLY_HASH = 2586408642L
        private val setEditorOnlyBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_editor_only", SET_EDITOR_ONLY_HASH)
        }

        private const val IS_EDITOR_ONLY_HASH = 36873697L
        private val isEditorOnlyBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "is_editor_only", IS_EDITOR_ONLY_HASH)
        }

        private const val SET_PARAM_HASH = 1722734213L
        private val setParamBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_param", SET_PARAM_HASH)
        }

        private const val GET_PARAM_HASH = 1844084987L
        private val getParamBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_param", GET_PARAM_HASH)
        }

        private const val SET_SHADOW_HASH = 2586408642L
        private val setShadowBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_shadow", SET_SHADOW_HASH)
        }

        private const val HAS_SHADOW_HASH = 36873697L
        private val hasShadowBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "has_shadow", HAS_SHADOW_HASH)
        }

        private const val SET_NEGATIVE_HASH = 2586408642L
        private val setNegativeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_negative", SET_NEGATIVE_HASH)
        }

        private const val IS_NEGATIVE_HASH = 36873697L
        private val isNegativeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "is_negative", IS_NEGATIVE_HASH)
        }

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_cull_mask", GET_CULL_MASK_HASH)
        }

        private const val SET_ENABLE_DISTANCE_FADE_HASH = 2586408642L
        private val setEnableDistanceFadeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_enable_distance_fade", SET_ENABLE_DISTANCE_FADE_HASH)
        }

        private const val IS_DISTANCE_FADE_ENABLED_HASH = 36873697L
        private val isDistanceFadeEnabledBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "is_distance_fade_enabled", IS_DISTANCE_FADE_ENABLED_HASH)
        }

        private const val SET_DISTANCE_FADE_BEGIN_HASH = 373806689L
        private val setDistanceFadeBeginBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_begin", SET_DISTANCE_FADE_BEGIN_HASH)
        }

        private const val GET_DISTANCE_FADE_BEGIN_HASH = 1740695150L
        private val getDistanceFadeBeginBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_begin", GET_DISTANCE_FADE_BEGIN_HASH)
        }

        private const val SET_DISTANCE_FADE_SHADOW_HASH = 373806689L
        private val setDistanceFadeShadowBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_shadow", SET_DISTANCE_FADE_SHADOW_HASH)
        }

        private const val GET_DISTANCE_FADE_SHADOW_HASH = 1740695150L
        private val getDistanceFadeShadowBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_shadow", GET_DISTANCE_FADE_SHADOW_HASH)
        }

        private const val SET_DISTANCE_FADE_LENGTH_HASH = 373806689L
        private val setDistanceFadeLengthBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_length", SET_DISTANCE_FADE_LENGTH_HASH)
        }

        private const val GET_DISTANCE_FADE_LENGTH_HASH = 1740695150L
        private val getDistanceFadeLengthBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_length", GET_DISTANCE_FADE_LENGTH_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_color", GET_COLOR_HASH)
        }

        private const val SET_SHADOW_REVERSE_CULL_FACE_HASH = 2586408642L
        private val setShadowReverseCullFaceBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_shadow_reverse_cull_face", SET_SHADOW_REVERSE_CULL_FACE_HASH)
        }

        private const val GET_SHADOW_REVERSE_CULL_FACE_HASH = 36873697L
        private val getShadowReverseCullFaceBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_shadow_reverse_cull_face", GET_SHADOW_REVERSE_CULL_FACE_HASH)
        }

        private const val SET_SHADOW_CASTER_MASK_HASH = 1286410249L
        private val setShadowCasterMaskBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_shadow_caster_mask", SET_SHADOW_CASTER_MASK_HASH)
        }

        private const val GET_SHADOW_CASTER_MASK_HASH = 3905245786L
        private val getShadowCasterMaskBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_shadow_caster_mask", GET_SHADOW_CASTER_MASK_HASH)
        }

        private const val SET_BAKE_MODE_HASH = 37739303L
        private val setBakeModeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_bake_mode", SET_BAKE_MODE_HASH)
        }

        private const val GET_BAKE_MODE_HASH = 371737608L
        private val getBakeModeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_bake_mode", GET_BAKE_MODE_HASH)
        }

        private const val SET_PROJECTOR_HASH = 4051416890L
        private val setProjectorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_projector", SET_PROJECTOR_HASH)
        }

        private const val GET_PROJECTOR_HASH = 3635182373L
        private val getProjectorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_projector", GET_PROJECTOR_HASH)
        }

        private const val SET_TEMPERATURE_HASH = 373806689L
        private val setTemperatureBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_temperature", SET_TEMPERATURE_HASH)
        }

        private const val GET_TEMPERATURE_HASH = 1740695150L
        private val getTemperatureBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_temperature", GET_TEMPERATURE_HASH)
        }

        private const val GET_CORRELATED_COLOR_HASH = 3444240500L
        private val getCorrelatedColorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_correlated_color", GET_CORRELATED_COLOR_HASH)
        }
    }
}
