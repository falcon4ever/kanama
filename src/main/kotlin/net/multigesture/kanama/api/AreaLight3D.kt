package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * An area light, such as a neon light tube or a screen.
 *
 * Generated from Godot docs: AreaLight3D
 */
class AreaLight3D(handle: MemorySegment) : Light3D(handle) {
    var areaRange: Double
        @JvmName("areaRangeProperty")
        get() = getParam(4L)
        @JvmName("setAreaRangeProperty")
        set(value) = setParam(4L, value)

    var areaAttenuation: Double
        @JvmName("areaAttenuationProperty")
        get() = getParam(6L)
        @JvmName("setAreaAttenuationProperty")
        set(value) = setParam(6L, value)

    var areaNormalizeEnergy: Boolean
        @JvmName("areaNormalizeEnergyProperty")
        get() = isAreaNormalizingEnergy()
        @JvmName("setAreaNormalizeEnergyProperty")
        set(value) = setAreaNormalizeEnergy(value)

    var areaSize: Vector2
        @JvmName("areaSizeProperty")
        get() = getAreaSize()
        @JvmName("setAreaSizeProperty")
        set(value) = setAreaSize(value)

    var areaTexture: Texture2D?
        @JvmName("areaTextureProperty")
        get() = getAreaTexture()
        @JvmName("setAreaTextureProperty")
        set(value) = setAreaTexture(value)

    /**
     * An optional texture to use as a light source. Changing the texture at runtime might impact
     * performance, as it needs to be drawn to the area light atlas with filtered mipmaps. If no
     * texture is assigned, the area light emits uniform light across its surface. Note: Area light
     * textures are only supported in the Forward+ and Mobile rendering methods, not Compatibility. To
     * reduce the performance impact of switching textures at runtime, make sure each dimension of an
     * area texture is either a multiple of 128 pixels, or a power of two. This removes the need for a
     * scaling pass, which slows down texture changes. The textures don't necessarily have to be square
     * to be optimal. Examples of optimal texture sizes include 32x64, 128x128, and 256x384.
     *
     * Generated from Godot docs: AreaLight3D.set_area_texture
     */
    fun setAreaTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setAreaTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * An optional texture to use as a light source. Changing the texture at runtime might impact
     * performance, as it needs to be drawn to the area light atlas with filtered mipmaps. If no
     * texture is assigned, the area light emits uniform light across its surface. Note: Area light
     * textures are only supported in the Forward+ and Mobile rendering methods, not Compatibility. To
     * reduce the performance impact of switching textures at runtime, make sure each dimension of an
     * area texture is either a multiple of 128 pixels, or a power of two. This removes the need for a
     * scaling pass, which slows down texture changes. The textures don't necessarily have to be square
     * to be optimal. Examples of optimal texture sizes include 32x64, 128x128, and 256x384.
     *
     * Generated from Godot docs: AreaLight3D.get_area_texture
     */
    fun getAreaTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAreaTextureBind, handle))
    }

    /**
     * The extents (width and height) of the area in meters.
     *
     * Generated from Godot docs: AreaLight3D.set_area_size
     */
    fun setAreaSize(areaSize: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setAreaSizeBind, handle, areaSize)
    }

    /**
     * The extents (width and height) of the area in meters.
     *
     * Generated from Godot docs: AreaLight3D.get_area_size
     */
    fun getAreaSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getAreaSizeBind, handle)
    }

    /**
     * Defines whether the energy is normalized (divided) by the surface area of the light. If set to
     * `true`, changing the size does not affect the total energy output, and does not dramatically
     * alter the brightness of the scene.
     *
     * Generated from Godot docs: AreaLight3D.set_area_normalize_energy
     */
    fun setAreaNormalizeEnergy(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAreaNormalizeEnergyBind, handle, enable)
    }

    /**
     * Defines whether the energy is normalized (divided) by the surface area of the light. If set to
     * `true`, changing the size does not affect the total energy output, and does not dramatically
     * alter the brightness of the scene.
     *
     * Generated from Godot docs: AreaLight3D.is_area_normalizing_energy
     */
    fun isAreaNormalizingEnergy(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAreaNormalizingEnergyBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AreaLight3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AreaLight3D? =
            if (handle.address() == 0L) null else AreaLight3D(handle)

        private const val SET_AREA_TEXTURE_HASH = 4051416890L
        private val setAreaTextureBind by lazy {
            ObjectCalls.getMethodBind("AreaLight3D", "set_area_texture", SET_AREA_TEXTURE_HASH)
        }

        private const val GET_AREA_TEXTURE_HASH = 3635182373L
        private val getAreaTextureBind by lazy {
            ObjectCalls.getMethodBind("AreaLight3D", "get_area_texture", GET_AREA_TEXTURE_HASH)
        }

        private const val SET_AREA_SIZE_HASH = 743155724L
        private val setAreaSizeBind by lazy {
            ObjectCalls.getMethodBind("AreaLight3D", "set_area_size", SET_AREA_SIZE_HASH)
        }

        private const val GET_AREA_SIZE_HASH = 3341600327L
        private val getAreaSizeBind by lazy {
            ObjectCalls.getMethodBind("AreaLight3D", "get_area_size", GET_AREA_SIZE_HASH)
        }

        private const val SET_AREA_NORMALIZE_ENERGY_HASH = 2586408642L
        private val setAreaNormalizeEnergyBind by lazy {
            ObjectCalls.getMethodBind("AreaLight3D", "set_area_normalize_energy", SET_AREA_NORMALIZE_ENERGY_HASH)
        }

        private const val IS_AREA_NORMALIZING_ENERGY_HASH = 36873697L
        private val isAreaNormalizingEnergyBind by lazy {
            ObjectCalls.getMethodBind("AreaLight3D", "is_area_normalizing_energy", IS_AREA_NORMALIZING_ENERGY_HASH)
        }
    }
}
