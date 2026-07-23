package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
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

    fun setAreaTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setAreaTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getAreaTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAreaTextureBind, handle))
    }

    fun setAreaSize(areaSize: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setAreaSizeBind, handle, areaSize)
    }

    fun getAreaSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getAreaSizeBind, handle)
    }

    fun setAreaNormalizeEnergy(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAreaNormalizeEnergyBind, handle, enable)
    }

    fun isAreaNormalizingEnergy(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAreaNormalizingEnergyBind, handle)
    }

    companion object {
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
