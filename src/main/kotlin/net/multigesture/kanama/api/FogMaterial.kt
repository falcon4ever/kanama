package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A material that controls how volumetric fog is rendered, to be assigned to a `FogVolume`.
 *
 * Generated from Godot docs: FogMaterial
 */
class FogMaterial(handle: MemorySegment) : Material(handle) {
    var density: Double
        @JvmName("densityProperty")
        get() = getDensity()
        @JvmName("setDensityProperty")
        set(value) = setDensity(value)

    var albedo: Color
        @JvmName("albedoProperty")
        get() = getAlbedo()
        @JvmName("setAlbedoProperty")
        set(value) = setAlbedo(value)

    var emission: Color
        @JvmName("emissionProperty")
        get() = getEmission()
        @JvmName("setEmissionProperty")
        set(value) = setEmission(value)

    var heightFalloff: Double
        @JvmName("heightFalloffProperty")
        get() = getHeightFalloff()
        @JvmName("setHeightFalloffProperty")
        set(value) = setHeightFalloff(value)

    var edgeFade: Double
        @JvmName("edgeFadeProperty")
        get() = getEdgeFade()
        @JvmName("setEdgeFadeProperty")
        set(value) = setEdgeFade(value)

    var densityTexture: Texture3D?
        @JvmName("densityTextureProperty")
        get() = getDensityTexture()
        @JvmName("setDensityTextureProperty")
        set(value) = setDensityTexture(value)

    /**
     * The density of the `FogVolume`. Denser objects are more opaque, but may suffer from
     * under-sampling artifacts that look like stripes. Negative values can be used to subtract fog
     * from other `FogVolume`s or global volumetric fog. Note: Due to limited precision, `density`
     * values between `-0.001` and `0.001` (exclusive) act like `0.0`. This does not apply to
     * `Environment.volumetric_fog_density`.
     *
     * Generated from Godot docs: FogMaterial.set_density
     */
    fun setDensity(density: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDensityBind, handle, density)
    }

    /**
     * The density of the `FogVolume`. Denser objects are more opaque, but may suffer from
     * under-sampling artifacts that look like stripes. Negative values can be used to subtract fog
     * from other `FogVolume`s or global volumetric fog. Note: Due to limited precision, `density`
     * values between `-0.001` and `0.001` (exclusive) act like `0.0`. This does not apply to
     * `Environment.volumetric_fog_density`.
     *
     * Generated from Godot docs: FogMaterial.get_density
     */
    fun getDensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDensityBind, handle)
    }

    /**
     * The single-scattering `Color` of the `FogVolume`. Internally, `albedo` is converted into
     * single-scattering, which is additively blended with other `FogVolume`s and the
     * `Environment.volumetric_fog_albedo`.
     *
     * Generated from Godot docs: FogMaterial.set_albedo
     */
    fun setAlbedo(albedo: Color) {
        ObjectCalls.ptrcallWithColorArg(setAlbedoBind, handle, albedo)
    }

    /**
     * The single-scattering `Color` of the `FogVolume`. Internally, `albedo` is converted into
     * single-scattering, which is additively blended with other `FogVolume`s and the
     * `Environment.volumetric_fog_albedo`.
     *
     * Generated from Godot docs: FogMaterial.get_albedo
     */
    fun getAlbedo(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getAlbedoBind, handle)
    }

    /**
     * The `Color` of the light emitted by the `FogVolume`. Emitted light will not cast light or
     * shadows on other objects, but can be useful for modulating the `Color` of the `FogVolume`
     * independently from light sources.
     *
     * Generated from Godot docs: FogMaterial.set_emission
     */
    fun setEmission(emission: Color) {
        ObjectCalls.ptrcallWithColorArg(setEmissionBind, handle, emission)
    }

    /**
     * The `Color` of the light emitted by the `FogVolume`. Emitted light will not cast light or
     * shadows on other objects, but can be useful for modulating the `Color` of the `FogVolume`
     * independently from light sources.
     *
     * Generated from Godot docs: FogMaterial.get_emission
     */
    fun getEmission(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getEmissionBind, handle)
    }

    /**
     * The rate by which the height-based fog decreases in density as height increases in world space.
     * A high falloff will result in a sharp transition, while a low falloff will result in a smoother
     * transition. A value of `0.0` results in uniform-density fog. The height threshold is determined
     * by the height of the associated `FogVolume`.
     *
     * Generated from Godot docs: FogMaterial.set_height_falloff
     */
    fun setHeightFalloff(heightFalloff: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightFalloffBind, handle, heightFalloff)
    }

    /**
     * The rate by which the height-based fog decreases in density as height increases in world space.
     * A high falloff will result in a sharp transition, while a low falloff will result in a smoother
     * transition. A value of `0.0` results in uniform-density fog. The height threshold is determined
     * by the height of the associated `FogVolume`.
     *
     * Generated from Godot docs: FogMaterial.get_height_falloff
     */
    fun getHeightFalloff(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightFalloffBind, handle)
    }

    /**
     * The hardness of the edges of the `FogVolume`. A higher value will result in softer edges, while
     * a lower value will result in harder edges.
     *
     * Generated from Godot docs: FogMaterial.set_edge_fade
     */
    fun setEdgeFade(edgeFade: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEdgeFadeBind, handle, edgeFade)
    }

    /**
     * The hardness of the edges of the `FogVolume`. A higher value will result in softer edges, while
     * a lower value will result in harder edges.
     *
     * Generated from Godot docs: FogMaterial.get_edge_fade
     */
    fun getEdgeFade(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEdgeFadeBind, handle)
    }

    /**
     * The 3D texture that is used to scale the `density` of the `FogVolume`. This can be used to vary
     * fog density within the `FogVolume` with any kind of static pattern. For animated effects,
     * consider using a custom fog shader
     * ($DOCS_URL/tutorials/shaders/shader_reference/fog_shader.html).
     *
     * Generated from Godot docs: FogMaterial.set_density_texture
     */
    fun setDensityTexture(densityTexture: Texture3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setDensityTextureBind, handle, listOf(densityTexture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The 3D texture that is used to scale the `density` of the `FogVolume`. This can be used to vary
     * fog density within the `FogVolume` with any kind of static pattern. For animated effects,
     * consider using a custom fog shader
     * ($DOCS_URL/tutorials/shaders/shader_reference/fog_shader.html).
     *
     * Generated from Godot docs: FogMaterial.get_density_texture
     */
    fun getDensityTexture(): Texture3D? {
        return Texture3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDensityTextureBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): FogMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FogMaterial? =
            if (handle.address() == 0L) null else FogMaterial(handle)

        private const val SET_DENSITY_HASH = 373806689L
        private val setDensityBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "set_density", SET_DENSITY_HASH)
        }

        private const val GET_DENSITY_HASH = 1740695150L
        private val getDensityBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "get_density", GET_DENSITY_HASH)
        }

        private const val SET_ALBEDO_HASH = 2920490490L
        private val setAlbedoBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "set_albedo", SET_ALBEDO_HASH)
        }

        private const val GET_ALBEDO_HASH = 3444240500L
        private val getAlbedoBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "get_albedo", GET_ALBEDO_HASH)
        }

        private const val SET_EMISSION_HASH = 2920490490L
        private val setEmissionBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "set_emission", SET_EMISSION_HASH)
        }

        private const val GET_EMISSION_HASH = 3444240500L
        private val getEmissionBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "get_emission", GET_EMISSION_HASH)
        }

        private const val SET_HEIGHT_FALLOFF_HASH = 373806689L
        private val setHeightFalloffBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "set_height_falloff", SET_HEIGHT_FALLOFF_HASH)
        }

        private const val GET_HEIGHT_FALLOFF_HASH = 1740695150L
        private val getHeightFalloffBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "get_height_falloff", GET_HEIGHT_FALLOFF_HASH)
        }

        private const val SET_EDGE_FADE_HASH = 373806689L
        private val setEdgeFadeBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "set_edge_fade", SET_EDGE_FADE_HASH)
        }

        private const val GET_EDGE_FADE_HASH = 1740695150L
        private val getEdgeFadeBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "get_edge_fade", GET_EDGE_FADE_HASH)
        }

        private const val SET_DENSITY_TEXTURE_HASH = 1188404210L
        private val setDensityTextureBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "set_density_texture", SET_DENSITY_TEXTURE_HASH)
        }

        private const val GET_DENSITY_TEXTURE_HASH = 373985333L
        private val getDensityTextureBind by lazy {
            ObjectCalls.getMethodBind("FogMaterial", "get_density_texture", GET_DENSITY_TEXTURE_HASH)
        }
    }
}
