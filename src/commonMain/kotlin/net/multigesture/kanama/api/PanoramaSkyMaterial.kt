package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPanoramaBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * `Texture2D` to be applied to the `PanoramaSkyMaterial`.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.get_panorama
     */
    fun getPanorama(): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPanoramaBind, segment))
    }

    /**
     * A boolean value to determine if the background texture should be filtered or not.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.set_filtering_enabled
     */
    fun setFilteringEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setFilteringEnabledBind, segment, enabled)
    }

    /**
     * A boolean value to determine if the background texture should be filtered or not.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.is_filtering_enabled
     */
    fun isFilteringEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFilteringEnabledBind, segment)
    }

    /**
     * The sky's overall brightness multiplier. Higher values result in a brighter sky.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.set_energy_multiplier
     */
    fun setEnergyMultiplier(multiplier: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEnergyMultiplierBind, segment, multiplier)
    }

    /**
     * The sky's overall brightness multiplier. Higher values result in a brighter sky.
     *
     * Generated from Godot docs: PanoramaSkyMaterial.get_energy_multiplier
     */
    fun getEnergyMultiplier(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEnergyMultiplierBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PanoramaSkyMaterial? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PanoramaSkyMaterial? =
            if (handle.address() == 0L) null else RefCounted.owned(PanoramaSkyMaterial(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PanoramaSkyMaterial? =
            if (handle.address() == 0L) null else PanoramaSkyMaterial(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PANORAMA_HASH = 4051416890L
        @JvmField
        val setPanoramaBind =
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "set_panorama", SET_PANORAMA_HASH)

        private const val GET_PANORAMA_HASH = 3635182373L
        @JvmField
        val getPanoramaBind =
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "get_panorama", GET_PANORAMA_HASH)

        private const val SET_FILTERING_ENABLED_HASH = 2586408642L
        @JvmField
        val setFilteringEnabledBind =
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "set_filtering_enabled", SET_FILTERING_ENABLED_HASH)

        private const val IS_FILTERING_ENABLED_HASH = 36873697L
        @JvmField
        val isFilteringEnabledBind =
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "is_filtering_enabled", IS_FILTERING_ENABLED_HASH)

        private const val SET_ENERGY_MULTIPLIER_HASH = 373806689L
        @JvmField
        val setEnergyMultiplierBind =
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "set_energy_multiplier", SET_ENERGY_MULTIPLIER_HASH)

        private const val GET_ENERGY_MULTIPLIER_HASH = 1740695150L
        @JvmField
        val getEnergyMultiplierBind =
            ObjectCalls.getMethodBind("PanoramaSkyMaterial", "get_energy_multiplier", GET_ENERGY_MULTIPLIER_HASH)
    }
}
