package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * An area light, such as a neon light tube or a screen.
 *
 * Generated from Godot docs: AreaLight3D
 */
class AreaLight3D(handle: GodotHandle) : Light3D(handle) {
    var areaRange: Double
        @JvmName("areaRangeProperty")
        get() = getParam(Light3D.Param.RANGE)
        @JvmName("setAreaRangeProperty")
        set(value) = setParam(Light3D.Param.RANGE, value)

    var areaAttenuation: Double
        @JvmName("areaAttenuationProperty")
        get() = getParam(Light3D.Param.ATTENUATION)
        @JvmName("setAreaAttenuationProperty")
        set(value) = setParam(Light3D.Param.ATTENUATION, value)

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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setAreaTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
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
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getAreaTextureBind, segment))
    }

    /**
     * The extents (width and height) of the area in meters.
     *
     * Generated from Godot docs: AreaLight3D.set_area_size
     */
    fun setAreaSize(areaSize: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setAreaSizeBind, segment, areaSize)
    }

    /**
     * The extents (width and height) of the area in meters.
     *
     * Generated from Godot docs: AreaLight3D.get_area_size
     */
    fun getAreaSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getAreaSizeBind, segment)
    }

    /**
     * Defines whether the energy is normalized (divided) by the surface area of the light. If set to
     * `true`, changing the size does not affect the total energy output, and does not dramatically
     * alter the brightness of the scene.
     *
     * Generated from Godot docs: AreaLight3D.set_area_normalize_energy
     */
    fun setAreaNormalizeEnergy(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAreaNormalizeEnergyBind, segment, enable)
    }

    /**
     * Defines whether the energy is normalized (divided) by the surface area of the light. If set to
     * `true`, changing the size does not affect the total energy output, and does not dramatically
     * alter the brightness of the scene.
     *
     * Generated from Godot docs: AreaLight3D.is_area_normalizing_energy
     */
    fun isAreaNormalizingEnergy(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAreaNormalizingEnergyBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AreaLight3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AreaLight3D? =
            if (handle.address() == 0L) null else AreaLight3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_AREA_TEXTURE_HASH = 4051416890L
        @JvmField
        val setAreaTextureBind =
            ObjectCalls.getMethodBind("AreaLight3D", "set_area_texture", SET_AREA_TEXTURE_HASH)

        private const val GET_AREA_TEXTURE_HASH = 3635182373L
        @JvmField
        val getAreaTextureBind =
            ObjectCalls.getMethodBind("AreaLight3D", "get_area_texture", GET_AREA_TEXTURE_HASH)

        private const val SET_AREA_SIZE_HASH = 743155724L
        @JvmField
        val setAreaSizeBind =
            ObjectCalls.getMethodBind("AreaLight3D", "set_area_size", SET_AREA_SIZE_HASH)

        private const val GET_AREA_SIZE_HASH = 3341600327L
        @JvmField
        val getAreaSizeBind =
            ObjectCalls.getMethodBind("AreaLight3D", "get_area_size", GET_AREA_SIZE_HASH)

        private const val SET_AREA_NORMALIZE_ENERGY_HASH = 2586408642L
        @JvmField
        val setAreaNormalizeEnergyBind =
            ObjectCalls.getMethodBind("AreaLight3D", "set_area_normalize_energy", SET_AREA_NORMALIZE_ENERGY_HASH)

        private const val IS_AREA_NORMALIZING_ENERGY_HASH = 36873697L
        @JvmField
        val isAreaNormalizingEnergyBind =
            ObjectCalls.getMethodBind("AreaLight3D", "is_area_normalizing_energy", IS_AREA_NORMALIZING_ENERGY_HASH)
    }
}
