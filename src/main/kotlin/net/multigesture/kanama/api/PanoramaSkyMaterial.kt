package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A material that provides a special texture to a `Sky`, usually an HDR panorama.
 *
 * Generated from Godot docs: PanoramaSkyMaterial
 */
class PanoramaSkyMaterial(handle: MemorySegment) : Material(handle) {
    var panorama: Texture2D?
        @JvmName("panoramaProperty")
        get() = getPanorama()
        @JvmName("setPanoramaProperty")
        set(value) = setPanorama(value)

    var filter: Boolean
        @JvmName("filterProperty")
        get() = isFilteringEnabled()
        @JvmName("setFilterProperty")
        set(value) = setFilteringEnabled(value)

    var energyMultiplier: Double
        @JvmName("energyMultiplierProperty")
        get() = getEnergyMultiplier()
        @JvmName("setEnergyMultiplierProperty")
        set(value) = setEnergyMultiplier(value)

    fun setPanorama(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setPanoramaBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getPanorama(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPanoramaBind, handle))
    }

    fun setFilteringEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilteringEnabledBind, handle, enabled)
    }

    fun isFilteringEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFilteringEnabledBind, handle)
    }

    fun setEnergyMultiplier(multiplier: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnergyMultiplierBind, handle, multiplier)
    }

    fun getEnergyMultiplier(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnergyMultiplierBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PanoramaSkyMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PanoramaSkyMaterial? =
            if (handle.address() == 0L) null else PanoramaSkyMaterial(handle)

        private const val SET_PANORAMA_HASH = 4051416890L
        private val setPanoramaBind by lazy {
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "set_panorama", SET_PANORAMA_HASH)
        }

        private const val GET_PANORAMA_HASH = 3635182373L
        private val getPanoramaBind by lazy {
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "get_panorama", GET_PANORAMA_HASH)
        }

        private const val SET_FILTERING_ENABLED_HASH = 2586408642L
        private val setFilteringEnabledBind by lazy {
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "set_filtering_enabled", SET_FILTERING_ENABLED_HASH)
        }

        private const val IS_FILTERING_ENABLED_HASH = 36873697L
        private val isFilteringEnabledBind by lazy {
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "is_filtering_enabled", IS_FILTERING_ENABLED_HASH)
        }

        private const val SET_ENERGY_MULTIPLIER_HASH = 373806689L
        private val setEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "set_energy_multiplier", SET_ENERGY_MULTIPLIER_HASH)
        }

        private const val GET_ENERGY_MULTIPLIER_HASH = 1740695150L
        private val getEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "get_energy_multiplier", GET_ENERGY_MULTIPLIER_HASH)
        }
    }
}
