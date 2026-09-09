package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: ProceduralSkyMaterial
 */
class ProceduralSkyMaterial(handle: MemorySegment) : Material(handle) {
    var skyTopColor: Color
        @JvmName("skyTopColorProperty")
        get() = getSkyTopColor()
        @JvmName("setSkyTopColorProperty")
        set(value) = setSkyTopColor(value)

    var skyHorizonColor: Color
        @JvmName("skyHorizonColorProperty")
        get() = getSkyHorizonColor()
        @JvmName("setSkyHorizonColorProperty")
        set(value) = setSkyHorizonColor(value)

    var skyCurve: Double
        @JvmName("skyCurveProperty")
        get() = getSkyCurve()
        @JvmName("setSkyCurveProperty")
        set(value) = setSkyCurve(value)

    var skyEnergyMultiplier: Double
        @JvmName("skyEnergyMultiplierProperty")
        get() = getSkyEnergyMultiplier()
        @JvmName("setSkyEnergyMultiplierProperty")
        set(value) = setSkyEnergyMultiplier(value)

    var skyCoverModulate: Color
        @JvmName("skyCoverModulateProperty")
        get() = getSkyCoverModulate()
        @JvmName("setSkyCoverModulateProperty")
        set(value) = setSkyCoverModulate(value)

    var groundBottomColor: Color
        @JvmName("groundBottomColorProperty")
        get() = getGroundBottomColor()
        @JvmName("setGroundBottomColorProperty")
        set(value) = setGroundBottomColor(value)

    var groundHorizonColor: Color
        @JvmName("groundHorizonColorProperty")
        get() = getGroundHorizonColor()
        @JvmName("setGroundHorizonColorProperty")
        set(value) = setGroundHorizonColor(value)

    var groundCurve: Double
        @JvmName("groundCurveProperty")
        get() = getGroundCurve()
        @JvmName("setGroundCurveProperty")
        set(value) = setGroundCurve(value)

    var groundEnergyMultiplier: Double
        @JvmName("groundEnergyMultiplierProperty")
        get() = getGroundEnergyMultiplier()
        @JvmName("setGroundEnergyMultiplierProperty")
        set(value) = setGroundEnergyMultiplier(value)

    var sunAngleMax: Double
        @JvmName("sunAngleMaxProperty")
        get() = getSunAngleMax()
        @JvmName("setSunAngleMaxProperty")
        set(value) = setSunAngleMax(value)

    var sunCurve: Double
        @JvmName("sunCurveProperty")
        get() = getSunCurve()
        @JvmName("setSunCurveProperty")
        set(value) = setSunCurve(value)

    var useDebanding: Boolean
        @JvmName("useDebandingProperty")
        get() = getUseDebanding()
        @JvmName("setUseDebandingProperty")
        set(value) = setUseDebanding(value)

    var energyMultiplier: Double
        @JvmName("energyMultiplierProperty")
        get() = getEnergyMultiplier()
        @JvmName("setEnergyMultiplierProperty")
        set(value) = setEnergyMultiplier(value)

    fun setSkyTopColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setSkyTopColorBind, handle, color)
    }

    fun getSkyTopColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getSkyTopColorBind, handle)
    }

    fun setSkyHorizonColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setSkyHorizonColorBind, handle, color)
    }

    fun getSkyHorizonColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getSkyHorizonColorBind, handle)
    }

    fun setSkyCurve(curve: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSkyCurveBind, handle, curve)
    }

    fun getSkyCurve(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSkyCurveBind, handle)
    }

    fun setSkyEnergyMultiplier(multiplier: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSkyEnergyMultiplierBind, handle, multiplier)
    }

    fun getSkyEnergyMultiplier(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSkyEnergyMultiplierBind, handle)
    }

    fun setSkyCoverModulate(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setSkyCoverModulateBind, handle, color)
    }

    fun getSkyCoverModulate(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getSkyCoverModulateBind, handle)
    }

    fun setGroundBottomColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setGroundBottomColorBind, handle, color)
    }

    fun getGroundBottomColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getGroundBottomColorBind, handle)
    }

    fun setGroundHorizonColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(setGroundHorizonColorBind, handle, color)
    }

    fun getGroundHorizonColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(getGroundHorizonColorBind, handle)
    }

    fun setGroundCurve(curve: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setGroundCurveBind, handle, curve)
    }

    fun getGroundCurve(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getGroundCurveBind, handle)
    }

    fun setGroundEnergyMultiplier(energy: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setGroundEnergyMultiplierBind, handle, energy)
    }

    fun getGroundEnergyMultiplier(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getGroundEnergyMultiplierBind, handle)
    }

    fun setSunAngleMax(degrees: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSunAngleMaxBind, handle, degrees)
    }

    fun getSunAngleMax(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSunAngleMaxBind, handle)
    }

    fun setSunCurve(curve: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSunCurveBind, handle, curve)
    }

    fun getSunCurve(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSunCurveBind, handle)
    }

    fun setUseDebanding(useDebanding: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUseDebandingBind, handle, useDebanding)
    }

    fun getUseDebanding(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getUseDebandingBind, handle)
    }

    fun setEnergyMultiplier(multiplier: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setEnergyMultiplierBind, handle, multiplier)
    }

    fun getEnergyMultiplier(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnergyMultiplierBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): ProceduralSkyMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ProceduralSkyMaterial? =
            if (handle.address() == 0L) null else ProceduralSkyMaterial(handle)

        // KANAMA-IOS-SUGAR: [glue] downcast a Resource (null if not), mirroring the desktop
        // helper and the ShaderMaterial.fromResource pattern. Re-add after regeneration.
        fun fromResource(value: Resource?): ProceduralSkyMaterial? =
            value?.takeIf { it.isClass("ProceduralSkyMaterial") }?.let { ProceduralSkyMaterial(it.handle) }

        private const val SET_SKY_TOP_COLOR_HASH = 2920490490L
        private val setSkyTopColorBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_sky_top_color", SET_SKY_TOP_COLOR_HASH)
        }

        private const val GET_SKY_TOP_COLOR_HASH = 3444240500L
        private val getSkyTopColorBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_sky_top_color", GET_SKY_TOP_COLOR_HASH)
        }

        private const val SET_SKY_HORIZON_COLOR_HASH = 2920490490L
        private val setSkyHorizonColorBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_sky_horizon_color", SET_SKY_HORIZON_COLOR_HASH)
        }

        private const val GET_SKY_HORIZON_COLOR_HASH = 3444240500L
        private val getSkyHorizonColorBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_sky_horizon_color", GET_SKY_HORIZON_COLOR_HASH)
        }

        private const val SET_SKY_CURVE_HASH = 373806689L
        private val setSkyCurveBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_sky_curve", SET_SKY_CURVE_HASH)
        }

        private const val GET_SKY_CURVE_HASH = 1740695150L
        private val getSkyCurveBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_sky_curve", GET_SKY_CURVE_HASH)
        }

        private const val SET_SKY_ENERGY_MULTIPLIER_HASH = 373806689L
        private val setSkyEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_sky_energy_multiplier", SET_SKY_ENERGY_MULTIPLIER_HASH)
        }

        private const val GET_SKY_ENERGY_MULTIPLIER_HASH = 1740695150L
        private val getSkyEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_sky_energy_multiplier", GET_SKY_ENERGY_MULTIPLIER_HASH)
        }

        private const val SET_SKY_COVER_MODULATE_HASH = 2920490490L
        private val setSkyCoverModulateBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_sky_cover_modulate", SET_SKY_COVER_MODULATE_HASH)
        }

        private const val GET_SKY_COVER_MODULATE_HASH = 3444240500L
        private val getSkyCoverModulateBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_sky_cover_modulate", GET_SKY_COVER_MODULATE_HASH)
        }

        private const val SET_GROUND_BOTTOM_COLOR_HASH = 2920490490L
        private val setGroundBottomColorBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_ground_bottom_color", SET_GROUND_BOTTOM_COLOR_HASH)
        }

        private const val GET_GROUND_BOTTOM_COLOR_HASH = 3444240500L
        private val getGroundBottomColorBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_ground_bottom_color", GET_GROUND_BOTTOM_COLOR_HASH)
        }

        private const val SET_GROUND_HORIZON_COLOR_HASH = 2920490490L
        private val setGroundHorizonColorBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_ground_horizon_color", SET_GROUND_HORIZON_COLOR_HASH)
        }

        private const val GET_GROUND_HORIZON_COLOR_HASH = 3444240500L
        private val getGroundHorizonColorBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_ground_horizon_color", GET_GROUND_HORIZON_COLOR_HASH)
        }

        private const val SET_GROUND_CURVE_HASH = 373806689L
        private val setGroundCurveBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_ground_curve", SET_GROUND_CURVE_HASH)
        }

        private const val GET_GROUND_CURVE_HASH = 1740695150L
        private val getGroundCurveBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_ground_curve", GET_GROUND_CURVE_HASH)
        }

        private const val SET_GROUND_ENERGY_MULTIPLIER_HASH = 373806689L
        private val setGroundEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_ground_energy_multiplier", SET_GROUND_ENERGY_MULTIPLIER_HASH)
        }

        private const val GET_GROUND_ENERGY_MULTIPLIER_HASH = 1740695150L
        private val getGroundEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_ground_energy_multiplier", GET_GROUND_ENERGY_MULTIPLIER_HASH)
        }

        private const val SET_SUN_ANGLE_MAX_HASH = 373806689L
        private val setSunAngleMaxBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_sun_angle_max", SET_SUN_ANGLE_MAX_HASH)
        }

        private const val GET_SUN_ANGLE_MAX_HASH = 1740695150L
        private val getSunAngleMaxBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_sun_angle_max", GET_SUN_ANGLE_MAX_HASH)
        }

        private const val SET_SUN_CURVE_HASH = 373806689L
        private val setSunCurveBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_sun_curve", SET_SUN_CURVE_HASH)
        }

        private const val GET_SUN_CURVE_HASH = 1740695150L
        private val getSunCurveBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_sun_curve", GET_SUN_CURVE_HASH)
        }

        private const val SET_USE_DEBANDING_HASH = 2586408642L
        private val setUseDebandingBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_use_debanding", SET_USE_DEBANDING_HASH)
        }

        private const val GET_USE_DEBANDING_HASH = 36873697L
        private val getUseDebandingBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_use_debanding", GET_USE_DEBANDING_HASH)
        }

        private const val SET_ENERGY_MULTIPLIER_HASH = 373806689L
        private val setEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "set_energy_multiplier", SET_ENERGY_MULTIPLIER_HASH)
        }

        private const val GET_ENERGY_MULTIPLIER_HASH = 1740695150L
        private val getEnergyMultiplierBind by lazy {
            ObjectCalls.getMethodBind("ProceduralSkyMaterial", "get_energy_multiplier", GET_ENERGY_MULTIPLIER_HASH)
        }
    }
}
