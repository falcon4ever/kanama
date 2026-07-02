package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A material that defines a sky for a `Sky` resource by a set of physical properties.
 *
 * Generated from Godot docs: PhysicalSkyMaterial
 */
class PhysicalSkyMaterial(handle: MemorySegment) : Material(handle) {
    var rayleighCoefficient: Double
        @JvmName("rayleighCoefficientProperty")
        get() = getRayleighCoefficient()
        @JvmName("setRayleighCoefficientProperty")
        set(value) = setRayleighCoefficient(value)

    var rayleighColor: Color
        @JvmName("rayleighColorProperty")
        get() = getRayleighColor()
        @JvmName("setRayleighColorProperty")
        set(value) = setRayleighColor(value)

    var mieCoefficient: Double
        @JvmName("mieCoefficientProperty")
        get() = getMieCoefficient()
        @JvmName("setMieCoefficientProperty")
        set(value) = setMieCoefficient(value)

    var mieEccentricity: Double
        @JvmName("mieEccentricityProperty")
        get() = getMieEccentricity()
        @JvmName("setMieEccentricityProperty")
        set(value) = setMieEccentricity(value)

    var mieColor: Color
        @JvmName("mieColorProperty")
        get() = getMieColor()
        @JvmName("setMieColorProperty")
        set(value) = setMieColor(value)

    var turbidity: Double
        @JvmName("turbidityProperty")
        get() = getTurbidity()
        @JvmName("setTurbidityProperty")
        set(value) = setTurbidity(value)

    var sunDiskScale: Double
        @JvmName("sunDiskScaleProperty")
        get() = getSunDiskScale()
        @JvmName("setSunDiskScaleProperty")
        set(value) = setSunDiskScale(value)

    var groundColor: Color
        @JvmName("groundColorProperty")
        get() = getGroundColor()
        @JvmName("setGroundColorProperty")
        set(value) = setGroundColor(value)

    var energyMultiplier: Double
        @JvmName("energyMultiplierProperty")
        get() = getEnergyMultiplier()
        @JvmName("setEnergyMultiplierProperty")
        set(value) = setEnergyMultiplier(value)

    var useDebanding: Boolean
        @JvmName("useDebandingProperty")
        get() = getUseDebanding()
        @JvmName("setUseDebandingProperty")
        set(value) = setUseDebanding(value)

    var nightSky: Texture2D?
        @JvmName("nightSkyProperty")
        get() = getNightSky()
        @JvmName("setNightSkyProperty")
        set(value) = setNightSky(value)

    fun setRayleighCoefficient(rayleigh: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRayleighCoefficientBind, handle, rayleigh)
    }

    fun getRayleighCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRayleighCoefficientBind, handle)
    }

    fun setRayleighColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setRayleighColorBind, handle, color)
    }

    fun getRayleighColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getRayleighColorBind, handle)
    }

    fun setMieCoefficient(mie: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMieCoefficientBind, handle, mie)
    }

    fun getMieCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMieCoefficientBind, handle)
    }

    fun setMieEccentricity(eccentricity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMieEccentricityBind, handle, eccentricity)
    }

    fun getMieEccentricity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMieEccentricityBind, handle)
    }

    fun setMieColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setMieColorBind, handle, color)
    }

    fun getMieColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getMieColorBind, handle)
    }

    fun setTurbidity(turbidity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTurbidityBind, handle, turbidity)
    }

    fun getTurbidity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTurbidityBind, handle)
    }

    fun setSunDiskScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSunDiskScaleBind, handle, scale)
    }

    fun getSunDiskScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSunDiskScaleBind, handle)
    }

    fun setGroundColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setGroundColorBind, handle, color)
    }

    fun getGroundColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getGroundColorBind, handle)
    }

    fun setEnergyMultiplier(multiplier: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnergyMultiplierBind, handle, multiplier)
    }

    fun getEnergyMultiplier(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnergyMultiplierBind, handle)
    }

    fun setUseDebanding(useDebanding: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseDebandingBind, handle, useDebanding)
    }

    fun getUseDebanding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseDebandingBind, handle)
    }

    fun setNightSky(nightSky: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setNightSkyBind, handle, listOf(nightSky?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getNightSky(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNightSkyBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicalSkyMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicalSkyMaterial? =
            if (handle.address() == 0L) null else PhysicalSkyMaterial(handle)

        private const val SET_RAYLEIGH_COEFFICIENT_HASH = 373806689L
        private val setRayleighCoefficientBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_rayleigh_coefficient", SET_RAYLEIGH_COEFFICIENT_HASH)
        }

        private const val GET_RAYLEIGH_COEFFICIENT_HASH = 1740695150L
        private val getRayleighCoefficientBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_rayleigh_coefficient", GET_RAYLEIGH_COEFFICIENT_HASH)
        }

        private const val SET_RAYLEIGH_COLOR_HASH = 2920490490L
        private val setRayleighColorBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_rayleigh_color", SET_RAYLEIGH_COLOR_HASH)
        }

        private const val GET_RAYLEIGH_COLOR_HASH = 3444240500L
        private val getRayleighColorBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_rayleigh_color", GET_RAYLEIGH_COLOR_HASH)
        }

        private const val SET_MIE_COEFFICIENT_HASH = 373806689L
        private val setMieCoefficientBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_mie_coefficient", SET_MIE_COEFFICIENT_HASH)
        }

        private const val GET_MIE_COEFFICIENT_HASH = 1740695150L
        private val getMieCoefficientBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_mie_coefficient", GET_MIE_COEFFICIENT_HASH)
        }

        private const val SET_MIE_ECCENTRICITY_HASH = 373806689L
        private val setMieEccentricityBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_mie_eccentricity", SET_MIE_ECCENTRICITY_HASH)
        }

        private const val GET_MIE_ECCENTRICITY_HASH = 1740695150L
        private val getMieEccentricityBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_mie_eccentricity", GET_MIE_ECCENTRICITY_HASH)
        }

        private const val SET_MIE_COLOR_HASH = 2920490490L
        private val setMieColorBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_mie_color", SET_MIE_COLOR_HASH)
        }

        private const val GET_MIE_COLOR_HASH = 3444240500L
        private val getMieColorBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_mie_color", GET_MIE_COLOR_HASH)
        }

        private const val SET_TURBIDITY_HASH = 373806689L
        private val setTurbidityBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_turbidity", SET_TURBIDITY_HASH)
        }

        private const val GET_TURBIDITY_HASH = 1740695150L
        private val getTurbidityBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_turbidity", GET_TURBIDITY_HASH)
        }

        private const val SET_SUN_DISK_SCALE_HASH = 373806689L
        private val setSunDiskScaleBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_sun_disk_scale", SET_SUN_DISK_SCALE_HASH)
        }

        private const val GET_SUN_DISK_SCALE_HASH = 1740695150L
        private val getSunDiskScaleBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_sun_disk_scale", GET_SUN_DISK_SCALE_HASH)
        }

        private const val SET_GROUND_COLOR_HASH = 2920490490L
        private val setGroundColorBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_ground_color", SET_GROUND_COLOR_HASH)
        }

        private const val GET_GROUND_COLOR_HASH = 3444240500L
        private val getGroundColorBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_ground_color", GET_GROUND_COLOR_HASH)
        }

        private const val SET_ENERGY_MULTIPLIER_HASH = 373806689L
        private val setEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_energy_multiplier", SET_ENERGY_MULTIPLIER_HASH)
        }

        private const val GET_ENERGY_MULTIPLIER_HASH = 1740695150L
        private val getEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_energy_multiplier", GET_ENERGY_MULTIPLIER_HASH)
        }

        private const val SET_USE_DEBANDING_HASH = 2586408642L
        private val setUseDebandingBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_use_debanding", SET_USE_DEBANDING_HASH)
        }

        private const val GET_USE_DEBANDING_HASH = 36873697L
        private val getUseDebandingBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_use_debanding", GET_USE_DEBANDING_HASH)
        }

        private const val SET_NIGHT_SKY_HASH = 4051416890L
        private val setNightSkyBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "set_night_sky", SET_NIGHT_SKY_HASH)
        }

        private const val GET_NIGHT_SKY_HASH = 3635182373L
        private val getNightSkyBind by lazy {
            ObjectCalls.getMethodBind("PhysicalSkyMaterial", "get_night_sky", GET_NIGHT_SKY_HASH)
        }
    }
}
