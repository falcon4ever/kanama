package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * Node that projects a texture onto a `MeshInstance3D`.
 *
 * Generated from Godot docs: Decal
 */
class Decal(handle: GodotHandle) : VisualInstance3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var textureAlbedo: Texture2D?
        @JvmName("textureAlbedoProperty")
        get() = getTexture(Decal.DecalTexture.ALBEDO)
        @JvmName("setTextureAlbedoProperty")
        set(value) = setTexture(Decal.DecalTexture.ALBEDO, value)

    var textureNormal: Texture2D?
        @JvmName("textureNormalProperty")
        get() = getTexture(Decal.DecalTexture.NORMAL)
        @JvmName("setTextureNormalProperty")
        set(value) = setTexture(Decal.DecalTexture.NORMAL, value)

    var textureOrm: Texture2D?
        @JvmName("textureOrmProperty")
        get() = getTexture(Decal.DecalTexture.ORM)
        @JvmName("setTextureOrmProperty")
        set(value) = setTexture(Decal.DecalTexture.ORM, value)

    var textureEmission: Texture2D?
        @JvmName("textureEmissionProperty")
        get() = getTexture(Decal.DecalTexture.EMISSION)
        @JvmName("setTextureEmissionProperty")
        set(value) = setTexture(Decal.DecalTexture.EMISSION, value)

    var emissionEnergy: Double
        @JvmName("emissionEnergyProperty")
        get() = getEmissionEnergy()
        @JvmName("setEmissionEnergyProperty")
        set(value) = setEmissionEnergy(value)

    var modulate: Color
        @JvmName("modulateProperty")
        get() = getModulate()
        @JvmName("setModulateProperty")
        set(value) = setModulate(value)

    var albedoMix: Double
        @JvmName("albedoMixProperty")
        get() = getAlbedoMix()
        @JvmName("setAlbedoMixProperty")
        set(value) = setAlbedoMix(value)

    var normalFade: Double
        @JvmName("normalFadeProperty")
        get() = getNormalFade()
        @JvmName("setNormalFadeProperty")
        set(value) = setNormalFade(value)

    var upperFade: Double
        @JvmName("upperFadeProperty")
        get() = getUpperFade()
        @JvmName("setUpperFadeProperty")
        set(value) = setUpperFade(value)

    var lowerFade: Double
        @JvmName("lowerFadeProperty")
        get() = getLowerFade()
        @JvmName("setLowerFadeProperty")
        set(value) = setLowerFade(value)

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

    var distanceFadeLength: Double
        @JvmName("distanceFadeLengthProperty")
        get() = getDistanceFadeLength()
        @JvmName("setDistanceFadeLengthProperty")
        set(value) = setDistanceFadeLength(value)

    var cullMask: Long
        @JvmName("cullMaskProperty")
        get() = getCullMask()
        @JvmName("setCullMaskProperty")
        set(value) = setCullMask(value)

    /**
     * Sets the size of the `AABB` used by the decal. All dimensions must be set to a value greater
     * than zero (they will be clamped to `0.001` if this is not the case). The AABB goes from
     * `-size/2` to `size/2`. Note: To improve culling efficiency of "hard surface" decals, set their
     * `upper_fade` and `lower_fade` to `0.0` and set the Y component of the `size` as low as possible.
     * This will reduce the decals' AABB size without affecting their appearance.
     *
     * Generated from Godot docs: Decal.set_size
     */
    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * Sets the size of the `AABB` used by the decal. All dimensions must be set to a value greater
     * than zero (they will be clamped to `0.001` if this is not the case). The AABB goes from
     * `-size/2` to `size/2`. Note: To improve culling efficiency of "hard surface" decals, set their
     * `upper_fade` and `lower_fade` to `0.0` and set the Y component of the `size` as low as possible.
     * This will reduce the decals' AABB size without affecting their appearance.
     *
     * Generated from Godot docs: Decal.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    /**
     * `Texture2D` storing ambient occlusion, roughness, and metallic for the decal. Use this to add
     * extra detail to decals. Note: Unlike `BaseMaterial3D` whose filter mode can be adjusted on a
     * per-material basis, the filter mode for `Decal` textures is set globally with
     * `ProjectSettings.rendering/textures/decals/filter`. Note: Setting this texture alone will not
     * result in a visible decal, as `texture_albedo` must also be set. To create an ORM-only decal,
     * load an albedo texture into `texture_albedo` and set `albedo_mix` to `0.0`. The albedo texture's
     * alpha channel will be used to determine where the underlying surface's ORM map should be
     * overridden (and its intensity). Note: Due to technical limitations, modifying the underlying
     * surface's roughness using `texture_orm` does not affect screen-space reflections
     * (`Environment.ssr_enabled`), reflections from `VoxelGI`, and reflections from SDFGI
     * (`Environment.sdfgi_enabled`). Only reflections from `ReflectionProbe`s are affected.
     *
     * Generated from Godot docs: Decal.set_texture
     */
    fun setTexture(type: Decal.DecalTexture, texture: Texture2D?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(Binds.setTextureBind, segment, type.value, texture?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * `Texture2D` storing ambient occlusion, roughness, and metallic for the decal. Use this to add
     * extra detail to decals. Note: Unlike `BaseMaterial3D` whose filter mode can be adjusted on a
     * per-material basis, the filter mode for `Decal` textures is set globally with
     * `ProjectSettings.rendering/textures/decals/filter`. Note: Setting this texture alone will not
     * result in a visible decal, as `texture_albedo` must also be set. To create an ORM-only decal,
     * load an albedo texture into `texture_albedo` and set `albedo_mix` to `0.0`. The albedo texture's
     * alpha channel will be used to determine where the underlying surface's ORM map should be
     * overridden (and its intensity). Note: Due to technical limitations, modifying the underlying
     * surface's roughness using `texture_orm` does not affect screen-space reflections
     * (`Environment.ssr_enabled`), reflections from `VoxelGI`, and reflections from SDFGI
     * (`Environment.sdfgi_enabled`). Only reflections from `ReflectionProbe`s are affected.
     *
     * Generated from Godot docs: Decal.get_texture
     */
    fun getTexture(type: Decal.DecalTexture): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithLongArgRetObject(Binds.getTextureBind, segment, type.value))
    }

    /**
     * Energy multiplier for the emission texture. This will make the decal emit light at a higher or
     * lower intensity, independently of the albedo color. See also `modulate`.
     *
     * Generated from Godot docs: Decal.set_emission_energy
     */
    fun setEmissionEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEmissionEnergyBind, segment, energy)
    }

    /**
     * Energy multiplier for the emission texture. This will make the decal emit light at a higher or
     * lower intensity, independently of the albedo color. See also `modulate`.
     *
     * Generated from Godot docs: Decal.get_emission_energy
     */
    fun getEmissionEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEmissionEnergyBind, segment)
    }

    /**
     * Blends the albedo `Color` of the decal with albedo `Color` of the underlying mesh. This can be
     * set to `0.0` to create a decal that only affects normal or ORM. In this case, an albedo texture
     * is still required as its alpha channel will determine where the normal and ORM will be
     * overridden. See also `modulate`.
     *
     * Generated from Godot docs: Decal.set_albedo_mix
     */
    fun setAlbedoMix(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAlbedoMixBind, segment, energy)
    }

    /**
     * Blends the albedo `Color` of the decal with albedo `Color` of the underlying mesh. This can be
     * set to `0.0` to create a decal that only affects normal or ORM. In this case, an albedo texture
     * is still required as its alpha channel will determine where the normal and ORM will be
     * overridden. See also `modulate`.
     *
     * Generated from Godot docs: Decal.get_albedo_mix
     */
    fun getAlbedoMix(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAlbedoMixBind, segment)
    }

    /**
     * Changes the `Color` of the Decal by multiplying the albedo and emission colors with this value.
     * The alpha component is only taken into account when multiplying the albedo color, not the
     * emission color. See also `emission_energy` and `albedo_mix` to change the emission and albedo
     * intensity independently of each other.
     *
     * Generated from Godot docs: Decal.set_modulate
     */
    fun setModulate(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setModulateBind, segment, color)
    }

    /**
     * Changes the `Color` of the Decal by multiplying the albedo and emission colors with this value.
     * The alpha component is only taken into account when multiplying the albedo color, not the
     * emission color. See also `emission_energy` and `albedo_mix` to change the emission and albedo
     * intensity independently of each other.
     *
     * Generated from Godot docs: Decal.get_modulate
     */
    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getModulateBind, segment)
    }

    /**
     * Sets the curve over which the decal will fade as the surface gets further from the center of the
     * `AABB`. Only positive values are valid (negative values will be clamped to `0.0`). See also
     * `lower_fade`.
     *
     * Generated from Godot docs: Decal.set_upper_fade
     */
    fun setUpperFade(fade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setUpperFadeBind, segment, fade)
    }

    /**
     * Sets the curve over which the decal will fade as the surface gets further from the center of the
     * `AABB`. Only positive values are valid (negative values will be clamped to `0.0`). See also
     * `lower_fade`.
     *
     * Generated from Godot docs: Decal.get_upper_fade
     */
    fun getUpperFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getUpperFadeBind, segment)
    }

    /**
     * Sets the curve over which the decal will fade as the surface gets further from the center of the
     * `AABB`. Only positive values are valid (negative values will be clamped to `0.0`). See also
     * `upper_fade`.
     *
     * Generated from Godot docs: Decal.set_lower_fade
     */
    fun setLowerFade(fade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLowerFadeBind, segment, fade)
    }

    /**
     * Sets the curve over which the decal will fade as the surface gets further from the center of the
     * `AABB`. Only positive values are valid (negative values will be clamped to `0.0`). See also
     * `upper_fade`.
     *
     * Generated from Godot docs: Decal.get_lower_fade
     */
    fun getLowerFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLowerFadeBind, segment)
    }

    /**
     * Fades the Decal if the angle between the Decal's `AABB` and the target surface becomes too
     * large. A value of `0` projects the Decal regardless of angle, a value of `1` limits the Decal to
     * surfaces that are nearly perpendicular. Note: Setting `normal_fade` to a value greater than
     * `0.0` has a small performance cost due to the added normal angle computations.
     *
     * Generated from Godot docs: Decal.set_normal_fade
     */
    fun setNormalFade(fade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setNormalFadeBind, segment, fade)
    }

    /**
     * Fades the Decal if the angle between the Decal's `AABB` and the target surface becomes too
     * large. A value of `0` projects the Decal regardless of angle, a value of `1` limits the Decal to
     * surfaces that are nearly perpendicular. Note: Setting `normal_fade` to a value greater than
     * `0.0` has a small performance cost due to the added normal angle computations.
     *
     * Generated from Godot docs: Decal.get_normal_fade
     */
    fun getNormalFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getNormalFadeBind, segment)
    }

    /**
     * If `true`, decals will smoothly fade away when far from the active `Camera3D` starting at
     * `distance_fade_begin`. The Decal will fade out over `distance_fade_begin` +
     * `distance_fade_length`, after which it will be culled and not sent to the shader at all. Use
     * this to reduce the number of active Decals in a scene and thus improve performance.
     *
     * Generated from Godot docs: Decal.set_enable_distance_fade
     */
    fun setEnableDistanceFade(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableDistanceFadeBind, segment, enable)
    }

    /**
     * If `true`, decals will smoothly fade away when far from the active `Camera3D` starting at
     * `distance_fade_begin`. The Decal will fade out over `distance_fade_begin` +
     * `distance_fade_length`, after which it will be culled and not sent to the shader at all. Use
     * this to reduce the number of active Decals in a scene and thus improve performance.
     *
     * Generated from Godot docs: Decal.is_distance_fade_enabled
     */
    fun isDistanceFadeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDistanceFadeEnabledBind, segment)
    }

    /**
     * The distance from the camera at which the Decal begins to fade away (in 3D units).
     *
     * Generated from Godot docs: Decal.set_distance_fade_begin
     */
    fun setDistanceFadeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDistanceFadeBeginBind, segment, distance)
    }

    /**
     * The distance from the camera at which the Decal begins to fade away (in 3D units).
     *
     * Generated from Godot docs: Decal.get_distance_fade_begin
     */
    fun getDistanceFadeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDistanceFadeBeginBind, segment)
    }

    /**
     * The distance over which the Decal fades (in 3D units). The Decal becomes slowly more transparent
     * over this distance and is completely invisible at the end. Higher values result in a smoother
     * fade-out transition, which is more suited when the camera moves fast.
     *
     * Generated from Godot docs: Decal.set_distance_fade_length
     */
    fun setDistanceFadeLength(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDistanceFadeLengthBind, segment, distance)
    }

    /**
     * The distance over which the Decal fades (in 3D units). The Decal becomes slowly more transparent
     * over this distance and is completely invisible at the end. Higher values result in a smoother
     * fade-out transition, which is more suited when the camera moves fast.
     *
     * Generated from Godot docs: Decal.get_distance_fade_length
     */
    fun getDistanceFadeLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDistanceFadeLengthBind, segment)
    }

    /**
     * Specifies which `VisualInstance3D.layers` this decal will project on. By default, Decals affect
     * all layers. This is used so you can specify which types of objects receive the Decal and which
     * do not. This is especially useful so you can ensure that dynamic objects don't accidentally
     * receive a Decal intended for the terrain under them.
     *
     * Generated from Godot docs: Decal.set_cull_mask
     */
    fun setCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCullMaskBind, segment, mask)
    }

    /**
     * Specifies which `VisualInstance3D.layers` this decal will project on. By default, Decals affect
     * all layers. This is used so you can specify which types of objects receive the Decal and which
     * do not. This is especially useful so you can ensure that dynamic objects don't accidentally
     * receive a Decal intended for the terrain under them.
     *
     * Generated from Godot docs: Decal.get_cull_mask
     */
    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCullMaskBind, segment)
    }

    /**
     * Godot's `Decal.DecalTexture` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Decal.DecalTexture.<NAME>`).
     *
     * Generated from Godot docs: Decal.DecalTexture
     */
    @JvmInline
    value class DecalTexture(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * `Texture2D` corresponding to `texture_albedo`.
             *
             * Generated from Godot docs: Decal.TEXTURE_ALBEDO
             */
            val ALBEDO: DecalTexture get() = DecalTexture(0L)
            /**
             * `Texture2D` corresponding to `texture_normal`.
             *
             * Generated from Godot docs: Decal.TEXTURE_NORMAL
             */
            val NORMAL: DecalTexture get() = DecalTexture(1L)
            /**
             * `Texture2D` corresponding to `texture_orm`.
             *
             * Generated from Godot docs: Decal.TEXTURE_ORM
             */
            val ORM: DecalTexture get() = DecalTexture(2L)
            /**
             * `Texture2D` corresponding to `texture_emission`.
             *
             * Generated from Godot docs: Decal.TEXTURE_EMISSION
             */
            val EMISSION: DecalTexture get() = DecalTexture(3L)
            /**
             * Max size of `DecalTexture` enum.
             *
             * Generated from Godot docs: Decal.TEXTURE_MAX
             */
            val MAX: DecalTexture get() = DecalTexture(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Decal? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Decal? =
            if (handle.address() == 0L) null else Decal(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("Decal", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("Decal", "get_size", GET_SIZE_HASH)

        private const val SET_TEXTURE_HASH = 2086764391L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("Decal", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 3244119503L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("Decal", "get_texture", GET_TEXTURE_HASH)

        private const val SET_EMISSION_ENERGY_HASH = 373806689L
        @JvmField
        val setEmissionEnergyBind =
            ObjectCalls.getMethodBind("Decal", "set_emission_energy", SET_EMISSION_ENERGY_HASH)

        private const val GET_EMISSION_ENERGY_HASH = 1740695150L
        @JvmField
        val getEmissionEnergyBind =
            ObjectCalls.getMethodBind("Decal", "get_emission_energy", GET_EMISSION_ENERGY_HASH)

        private const val SET_ALBEDO_MIX_HASH = 373806689L
        @JvmField
        val setAlbedoMixBind =
            ObjectCalls.getMethodBind("Decal", "set_albedo_mix", SET_ALBEDO_MIX_HASH)

        private const val GET_ALBEDO_MIX_HASH = 1740695150L
        @JvmField
        val getAlbedoMixBind =
            ObjectCalls.getMethodBind("Decal", "get_albedo_mix", GET_ALBEDO_MIX_HASH)

        private const val SET_MODULATE_HASH = 2920490490L
        @JvmField
        val setModulateBind =
            ObjectCalls.getMethodBind("Decal", "set_modulate", SET_MODULATE_HASH)

        private const val GET_MODULATE_HASH = 3444240500L
        @JvmField
        val getModulateBind =
            ObjectCalls.getMethodBind("Decal", "get_modulate", GET_MODULATE_HASH)

        private const val SET_UPPER_FADE_HASH = 373806689L
        @JvmField
        val setUpperFadeBind =
            ObjectCalls.getMethodBind("Decal", "set_upper_fade", SET_UPPER_FADE_HASH)

        private const val GET_UPPER_FADE_HASH = 1740695150L
        @JvmField
        val getUpperFadeBind =
            ObjectCalls.getMethodBind("Decal", "get_upper_fade", GET_UPPER_FADE_HASH)

        private const val SET_LOWER_FADE_HASH = 373806689L
        @JvmField
        val setLowerFadeBind =
            ObjectCalls.getMethodBind("Decal", "set_lower_fade", SET_LOWER_FADE_HASH)

        private const val GET_LOWER_FADE_HASH = 1740695150L
        @JvmField
        val getLowerFadeBind =
            ObjectCalls.getMethodBind("Decal", "get_lower_fade", GET_LOWER_FADE_HASH)

        private const val SET_NORMAL_FADE_HASH = 373806689L
        @JvmField
        val setNormalFadeBind =
            ObjectCalls.getMethodBind("Decal", "set_normal_fade", SET_NORMAL_FADE_HASH)

        private const val GET_NORMAL_FADE_HASH = 1740695150L
        @JvmField
        val getNormalFadeBind =
            ObjectCalls.getMethodBind("Decal", "get_normal_fade", GET_NORMAL_FADE_HASH)

        private const val SET_ENABLE_DISTANCE_FADE_HASH = 2586408642L
        @JvmField
        val setEnableDistanceFadeBind =
            ObjectCalls.getMethodBind("Decal", "set_enable_distance_fade", SET_ENABLE_DISTANCE_FADE_HASH)

        private const val IS_DISTANCE_FADE_ENABLED_HASH = 36873697L
        @JvmField
        val isDistanceFadeEnabledBind =
            ObjectCalls.getMethodBind("Decal", "is_distance_fade_enabled", IS_DISTANCE_FADE_ENABLED_HASH)

        private const val SET_DISTANCE_FADE_BEGIN_HASH = 373806689L
        @JvmField
        val setDistanceFadeBeginBind =
            ObjectCalls.getMethodBind("Decal", "set_distance_fade_begin", SET_DISTANCE_FADE_BEGIN_HASH)

        private const val GET_DISTANCE_FADE_BEGIN_HASH = 1740695150L
        @JvmField
        val getDistanceFadeBeginBind =
            ObjectCalls.getMethodBind("Decal", "get_distance_fade_begin", GET_DISTANCE_FADE_BEGIN_HASH)

        private const val SET_DISTANCE_FADE_LENGTH_HASH = 373806689L
        @JvmField
        val setDistanceFadeLengthBind =
            ObjectCalls.getMethodBind("Decal", "set_distance_fade_length", SET_DISTANCE_FADE_LENGTH_HASH)

        private const val GET_DISTANCE_FADE_LENGTH_HASH = 1740695150L
        @JvmField
        val getDistanceFadeLengthBind =
            ObjectCalls.getMethodBind("Decal", "get_distance_fade_length", GET_DISTANCE_FADE_LENGTH_HASH)

        private const val SET_CULL_MASK_HASH = 1286410249L
        @JvmField
        val setCullMaskBind =
            ObjectCalls.getMethodBind("Decal", "set_cull_mask", SET_CULL_MASK_HASH)

        private const val GET_CULL_MASK_HASH = 3905245786L
        @JvmField
        val getCullMaskBind =
            ObjectCalls.getMethodBind("Decal", "get_cull_mask", GET_CULL_MASK_HASH)
    }
}
