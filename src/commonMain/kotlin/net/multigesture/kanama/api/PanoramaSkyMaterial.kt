package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A material that provides a special texture to a `Sky`, usually an HDR panorama.
 *
 * Generated from Godot docs: PanoramaSkyMaterial
 */
class PanoramaSkyMaterial(handle: GodotHandle) : Material(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setPanoramaBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * `Texture2D` to be applied to the `PanoramaSkyMaterial`.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.get_panorama
     */
    fun getPanorama(): Texture2D? {
        checkOpen()
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPanoramaBind, segment))
    }

    /**
     * A boolean value to determine if the background texture should be filtered or not.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.set_filtering_enabled
     */
    fun setFilteringEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setFilteringEnabledBind, segment, enabled)
    }

    /**
     * A boolean value to determine if the background texture should be filtered or not.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.is_filtering_enabled
     */
    fun isFilteringEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isFilteringEnabledBind, segment)
    }

    /**
     * The sky's overall brightness multiplier. Higher values result in a brighter sky.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.set_energy_multiplier
     */
    fun setEnergyMultiplier(multiplier: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setEnergyMultiplierBind, segment, multiplier)
    }

    /**
     * The sky's overall brightness multiplier. Higher values result in a brighter sky.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.get_energy_multiplier
     */
    fun getEnergyMultiplier(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnergyMultiplierBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PanoramaSkyMaterial? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PanoramaSkyMaterial? =
            if (handle.address() == 0L) null else PanoramaSkyMaterial(GodotHandle(handle))

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
