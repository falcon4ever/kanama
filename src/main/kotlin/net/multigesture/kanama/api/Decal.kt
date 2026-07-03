package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * Node that projects a texture onto a `MeshInstance3D`.
 *
 * Generated from Godot docs: Decal
 */
class Decal(handle: MemorySegment) : VisualInstance3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

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
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
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
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
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
    fun setTexture(type: Long, texture: Texture2D?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(setTextureBind, handle, type, texture?.requireOpenHandle() ?: MemorySegment.NULL)
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
    fun getTexture(type: Long): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getTextureBind, handle, type))
    }

    /**
     * Energy multiplier for the emission texture. This will make the decal emit light at a higher or
     * lower intensity, independently of the albedo color. See also `modulate`.
     *
     * Generated from Godot docs: Decal.set_emission_energy
     */
    fun setEmissionEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionEnergyBind, handle, energy)
    }

    /**
     * Energy multiplier for the emission texture. This will make the decal emit light at a higher or
     * lower intensity, independently of the albedo color. See also `modulate`.
     *
     * Generated from Godot docs: Decal.get_emission_energy
     */
    fun getEmissionEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionEnergyBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setAlbedoMixBind, handle, energy)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlbedoMixBind, handle)
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
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, color)
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
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    /**
     * Sets the curve over which the decal will fade as the surface gets further from the center of the
     * `AABB`. Only positive values are valid (negative values will be clamped to `0.0`). See also
     * `lower_fade`.
     *
     * Generated from Godot docs: Decal.set_upper_fade
     */
    fun setUpperFade(fade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setUpperFadeBind, handle, fade)
    }

    /**
     * Sets the curve over which the decal will fade as the surface gets further from the center of the
     * `AABB`. Only positive values are valid (negative values will be clamped to `0.0`). See also
     * `lower_fade`.
     *
     * Generated from Godot docs: Decal.get_upper_fade
     */
    fun getUpperFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUpperFadeBind, handle)
    }

    /**
     * Sets the curve over which the decal will fade as the surface gets further from the center of the
     * `AABB`. Only positive values are valid (negative values will be clamped to `0.0`). See also
     * `upper_fade`.
     *
     * Generated from Godot docs: Decal.set_lower_fade
     */
    fun setLowerFade(fade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLowerFadeBind, handle, fade)
    }

    /**
     * Sets the curve over which the decal will fade as the surface gets further from the center of the
     * `AABB`. Only positive values are valid (negative values will be clamped to `0.0`). See also
     * `upper_fade`.
     *
     * Generated from Godot docs: Decal.get_lower_fade
     */
    fun getLowerFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLowerFadeBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setNormalFadeBind, handle, fade)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getNormalFadeBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setEnableDistanceFadeBind, handle, enable)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isDistanceFadeEnabledBind, handle)
    }

    /**
     * The distance from the camera at which the Decal begins to fade away (in 3D units).
     *
     * Generated from Godot docs: Decal.set_distance_fade_begin
     */
    fun setDistanceFadeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeBeginBind, handle, distance)
    }

    /**
     * The distance from the camera at which the Decal begins to fade away (in 3D units).
     *
     * Generated from Godot docs: Decal.get_distance_fade_begin
     */
    fun getDistanceFadeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeBeginBind, handle)
    }

    /**
     * The distance over which the Decal fades (in 3D units). The Decal becomes slowly more transparent
     * over this distance and is completely invisible at the end. Higher values result in a smoother
     * fade-out transition, which is more suited when the camera moves fast.
     *
     * Generated from Godot docs: Decal.set_distance_fade_length
     */
    fun setDistanceFadeLength(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeLengthBind, handle, distance)
    }

    /**
     * The distance over which the Decal fades (in 3D units). The Decal becomes slowly more transparent
     * over this distance and is completely invisible at the end. Higher values result in a smoother
     * fade-out transition, which is more suited when the camera moves fast.
     *
     * Generated from Godot docs: Decal.get_distance_fade_length
     */
    fun getDistanceFadeLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeLengthBind, handle)
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
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, handle, mask)
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
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, handle)
    }

    companion object {
        const val TEXTURE_ALBEDO: Long = 0L
        const val TEXTURE_NORMAL: Long = 1L
        const val TEXTURE_ORM: Long = 2L
        const val TEXTURE_EMISSION: Long = 3L
        const val TEXTURE_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Decal? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Decal? =
            if (handle.address() == 0L) null else Decal(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_size", GET_SIZE_HASH)
        }

        private const val SET_TEXTURE_HASH = 2086764391L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3244119503L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_EMISSION_ENERGY_HASH = 373806689L
        private val setEmissionEnergyBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_emission_energy", SET_EMISSION_ENERGY_HASH)
        }

        private const val GET_EMISSION_ENERGY_HASH = 1740695150L
        private val getEmissionEnergyBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_emission_energy", GET_EMISSION_ENERGY_HASH)
        }

        private const val SET_ALBEDO_MIX_HASH = 373806689L
        private val setAlbedoMixBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_albedo_mix", SET_ALBEDO_MIX_HASH)
        }

        private const val GET_ALBEDO_MIX_HASH = 1740695150L
        private val getAlbedoMixBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_albedo_mix", GET_ALBEDO_MIX_HASH)
        }

        private const val SET_MODULATE_HASH = 2920490490L
        private val setModulateBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_modulate", SET_MODULATE_HASH)
        }

        private const val GET_MODULATE_HASH = 3444240500L
        private val getModulateBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_modulate", GET_MODULATE_HASH)
        }

        private const val SET_UPPER_FADE_HASH = 373806689L
        private val setUpperFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_upper_fade", SET_UPPER_FADE_HASH)
        }

        private const val GET_UPPER_FADE_HASH = 1740695150L
        private val getUpperFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_upper_fade", GET_UPPER_FADE_HASH)
        }

        private const val SET_LOWER_FADE_HASH = 373806689L
        private val setLowerFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_lower_fade", SET_LOWER_FADE_HASH)
        }

        private const val GET_LOWER_FADE_HASH = 1740695150L
        private val getLowerFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_lower_fade", GET_LOWER_FADE_HASH)
        }

        private const val SET_NORMAL_FADE_HASH = 373806689L
        private val setNormalFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_normal_fade", SET_NORMAL_FADE_HASH)
        }

        private const val GET_NORMAL_FADE_HASH = 1740695150L
        private val getNormalFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_normal_fade", GET_NORMAL_FADE_HASH)
        }

        private const val SET_ENABLE_DISTANCE_FADE_HASH = 2586408642L
        private val setEnableDistanceFadeBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_enable_distance_fade", SET_ENABLE_DISTANCE_FADE_HASH)
        }

        private const val IS_DISTANCE_FADE_ENABLED_HASH = 36873697L
        private val isDistanceFadeEnabledBind by lazy {
            ObjectCalls.getMethodBind("Decal", "is_distance_fade_enabled", IS_DISTANCE_FADE_ENABLED_HASH)
        }

        private const val SET_DISTANCE_FADE_BEGIN_HASH = 373806689L
        private val setDistanceFadeBeginBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_distance_fade_begin", SET_DISTANCE_FADE_BEGIN_HASH)
        }

        private const val GET_DISTANCE_FADE_BEGIN_HASH = 1740695150L
        private val getDistanceFadeBeginBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_distance_fade_begin", GET_DISTANCE_FADE_BEGIN_HASH)
        }

        private const val SET_DISTANCE_FADE_LENGTH_HASH = 373806689L
        private val setDistanceFadeLengthBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_distance_fade_length", SET_DISTANCE_FADE_LENGTH_HASH)
        }

        private const val GET_DISTANCE_FADE_LENGTH_HASH = 1740695150L
        private val getDistanceFadeLengthBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_distance_fade_length", GET_DISTANCE_FADE_LENGTH_HASH)
        }

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Decal", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Decal", "get_cull_mask", GET_CULL_MASK_HASH)
        }
    }
}
