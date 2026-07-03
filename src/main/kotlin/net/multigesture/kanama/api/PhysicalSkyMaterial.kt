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

    /**
     * Controls the strength of the Rayleigh scattering
     * (https://en.wikipedia.org/wiki/Rayleigh_scattering). Rayleigh scattering results from light
     * colliding with small particles. It is responsible for the blue color of the sky.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_rayleigh_coefficient
     */
    fun setRayleighCoefficient(rayleigh: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRayleighCoefficientBind, handle, rayleigh)
    }

    /**
     * Controls the strength of the Rayleigh scattering
     * (https://en.wikipedia.org/wiki/Rayleigh_scattering). Rayleigh scattering results from light
     * colliding with small particles. It is responsible for the blue color of the sky.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_rayleigh_coefficient
     */
    fun getRayleighCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRayleighCoefficientBind, handle)
    }

    /**
     * Controls the `Color` of the Rayleigh scattering
     * (https://en.wikipedia.org/wiki/Rayleigh_scattering). While not physically accurate, this allows
     * for the creation of alien-looking planets. For example, setting this to a red `Color` results in
     * a Mars-looking atmosphere with a corresponding blue sunset.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_rayleigh_color
     */
    fun setRayleighColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setRayleighColorBind, handle, color)
    }

    /**
     * Controls the `Color` of the Rayleigh scattering
     * (https://en.wikipedia.org/wiki/Rayleigh_scattering). While not physically accurate, this allows
     * for the creation of alien-looking planets. For example, setting this to a red `Color` results in
     * a Mars-looking atmosphere with a corresponding blue sunset.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_rayleigh_color
     */
    fun getRayleighColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getRayleighColorBind, handle)
    }

    /**
     * Controls the strength of Mie scattering (https://en.wikipedia.org/wiki/Mie_scattering) for the
     * sky. Mie scattering results from light colliding with larger particles (like water). On earth,
     * Mie scattering results in a whitish color around the sun and horizon.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_mie_coefficient
     */
    fun setMieCoefficient(mie: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMieCoefficientBind, handle, mie)
    }

    /**
     * Controls the strength of Mie scattering (https://en.wikipedia.org/wiki/Mie_scattering) for the
     * sky. Mie scattering results from light colliding with larger particles (like water). On earth,
     * Mie scattering results in a whitish color around the sun and horizon.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_mie_coefficient
     */
    fun getMieCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMieCoefficientBind, handle)
    }

    /**
     * Controls the direction of the Mie scattering (https://en.wikipedia.org/wiki/Mie_scattering). A
     * value of `1` means that when light hits a particle it's passing through straight forward. A
     * value of `-1` means that all light is scatter backwards.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_mie_eccentricity
     */
    fun setMieEccentricity(eccentricity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMieEccentricityBind, handle, eccentricity)
    }

    /**
     * Controls the direction of the Mie scattering (https://en.wikipedia.org/wiki/Mie_scattering). A
     * value of `1` means that when light hits a particle it's passing through straight forward. A
     * value of `-1` means that all light is scatter backwards.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_mie_eccentricity
     */
    fun getMieEccentricity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMieEccentricityBind, handle)
    }

    /**
     * Controls the `Color` of the Mie scattering (https://en.wikipedia.org/wiki/Mie_scattering)
     * effect. While not physically accurate, this allows for the creation of alien-looking planets.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_mie_color
     */
    fun setMieColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setMieColorBind, handle, color)
    }

    /**
     * Controls the `Color` of the Mie scattering (https://en.wikipedia.org/wiki/Mie_scattering)
     * effect. While not physically accurate, this allows for the creation of alien-looking planets.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_mie_color
     */
    fun getMieColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getMieColorBind, handle)
    }

    /**
     * Sets the thickness of the atmosphere. High turbidity creates a foggy-looking atmosphere, while a
     * low turbidity results in a clearer atmosphere.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_turbidity
     */
    fun setTurbidity(turbidity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTurbidityBind, handle, turbidity)
    }

    /**
     * Sets the thickness of the atmosphere. High turbidity creates a foggy-looking atmosphere, while a
     * low turbidity results in a clearer atmosphere.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_turbidity
     */
    fun getTurbidity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTurbidityBind, handle)
    }

    /**
     * Sets the size of the sun disk. Default value is based on Sol's perceived size from Earth.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_sun_disk_scale
     */
    fun setSunDiskScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSunDiskScaleBind, handle, scale)
    }

    /**
     * Sets the size of the sun disk. Default value is based on Sol's perceived size from Earth.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_sun_disk_scale
     */
    fun getSunDiskScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSunDiskScaleBind, handle)
    }

    /**
     * Modulates the `Color` on the bottom half of the sky to represent the ground.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_ground_color
     */
    fun setGroundColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setGroundColorBind, handle, color)
    }

    /**
     * Modulates the `Color` on the bottom half of the sky to represent the ground.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_ground_color
     */
    fun getGroundColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getGroundColorBind, handle)
    }

    /**
     * The sky's overall brightness multiplier. Higher values result in a brighter sky.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_energy_multiplier
     */
    fun setEnergyMultiplier(multiplier: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnergyMultiplierBind, handle, multiplier)
    }

    /**
     * The sky's overall brightness multiplier. Higher values result in a brighter sky.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_energy_multiplier
     */
    fun getEnergyMultiplier(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnergyMultiplierBind, handle)
    }

    /**
     * If `true`, enables debanding. Debanding adds a small amount of noise which helps reduce banding
     * that appears from the smooth changes in color in the sky.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_use_debanding
     */
    fun setUseDebanding(useDebanding: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseDebandingBind, handle, useDebanding)
    }

    /**
     * If `true`, enables debanding. Debanding adds a small amount of noise which helps reduce banding
     * that appears from the smooth changes in color in the sky.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_use_debanding
     */
    fun getUseDebanding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseDebandingBind, handle)
    }

    /**
     * `Texture2D` for the night sky. This is added to the sky, so if it is bright enough, it may be
     * visible during the day.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.set_night_sky
     */
    fun setNightSky(nightSky: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setNightSkyBind, handle, listOf(nightSky?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Texture2D` for the night sky. This is added to the sky, so if it is bright enough, it may be
     * visible during the day.
     *
     * Generated from Godot docs: PhysicalSkyMaterial.get_night_sky
     */
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
