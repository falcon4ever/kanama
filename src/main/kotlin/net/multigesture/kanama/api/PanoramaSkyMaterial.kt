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

    /**
     * `Texture2D` to be applied to the `PanoramaSkyMaterial`.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.set_panorama
     */
    fun setPanorama(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setPanoramaBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Texture2D` to be applied to the `PanoramaSkyMaterial`.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.get_panorama
     */
    fun getPanorama(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPanoramaBind, handle))
    }

    /**
     * A boolean value to determine if the background texture should be filtered or not.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.set_filtering_enabled
     */
    fun setFilteringEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilteringEnabledBind, handle, enabled)
    }

    /**
     * A boolean value to determine if the background texture should be filtered or not.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.is_filtering_enabled
     */
    fun isFilteringEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFilteringEnabledBind, handle)
    }

    /**
     * The sky's overall brightness multiplier. Higher values result in a brighter sky.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.set_energy_multiplier
     */
    fun setEnergyMultiplier(multiplier: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnergyMultiplierBind, handle, multiplier)
    }

    /**
     * The sky's overall brightness multiplier. Higher values result in a brighter sky.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.get_energy_multiplier
     */
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
