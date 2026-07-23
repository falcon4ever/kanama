package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: Light3D
 */
open class Light3D(handle: MemorySegment) : VisualInstance3D(handle) {
    var lightIntensityLumens: Double
        @JvmName("lightIntensityLumensProperty")
        get() = getParam(20L)
        @JvmName("setLightIntensityLumensProperty")
        set(value) = setParam(20L, value)

    var lightIntensityLux: Double
        @JvmName("lightIntensityLuxProperty")
        get() = getParam(20L)
        @JvmName("setLightIntensityLuxProperty")
        set(value) = setParam(20L, value)

    var lightTemperature: Double
        @JvmName("lightTemperatureProperty")
        get() = getTemperature()
        @JvmName("setLightTemperatureProperty")
        set(value) = setTemperature(value)

    var lightColor: Color
        @JvmName("lightColorProperty")
        get() = getColor()
        @JvmName("setLightColorProperty")
        set(value) = setColor(value)

    var lightEnergy: Double
        @JvmName("lightEnergyProperty")
        get() = getParam(0L)
        @JvmName("setLightEnergyProperty")
        set(value) = setParam(0L, value)

    var lightIndirectEnergy: Double
        @JvmName("lightIndirectEnergyProperty")
        get() = getParam(1L)
        @JvmName("setLightIndirectEnergyProperty")
        set(value) = setParam(1L, value)

    var lightVolumetricFogEnergy: Double
        @JvmName("lightVolumetricFogEnergyProperty")
        get() = getParam(2L)
        @JvmName("setLightVolumetricFogEnergyProperty")
        set(value) = setParam(2L, value)

    var lightProjector: Texture2D?
        @JvmName("lightProjectorProperty")
        get() = getProjector()
        @JvmName("setLightProjectorProperty")
        set(value) = setProjector(value)

    var lightSize: Double
        @JvmName("lightSizeProperty")
        get() = getParam(5L)
        @JvmName("setLightSizeProperty")
        set(value) = setParam(5L, value)

    var lightAngularDistance: Double
        @JvmName("lightAngularDistanceProperty")
        get() = getParam(5L)
        @JvmName("setLightAngularDistanceProperty")
        set(value) = setParam(5L, value)

    var lightNegative: Boolean
        @JvmName("lightNegativeProperty")
        get() = isNegative()
        @JvmName("setLightNegativeProperty")
        set(value) = setNegative(value)

    var lightSpecular: Double
        @JvmName("lightSpecularProperty")
        get() = getParam(3L)
        @JvmName("setLightSpecularProperty")
        set(value) = setParam(3L, value)

    var lightBakeMode: Long
        @JvmName("lightBakeModeProperty")
        get() = getBakeMode()
        @JvmName("setLightBakeModeProperty")
        set(value) = setBakeMode(value)

    var lightCullMask: Long
        @JvmName("lightCullMaskProperty")
        get() = getCullMask()
        @JvmName("setLightCullMaskProperty")
        set(value) = setCullMask(value)

    var shadowEnabled: Boolean
        @JvmName("shadowEnabledProperty")
        get() = hasShadow()
        @JvmName("setShadowEnabledProperty")
        set(value) = setShadow(value)

    var shadowBias: Double
        @JvmName("shadowBiasProperty")
        get() = getParam(15L)
        @JvmName("setShadowBiasProperty")
        set(value) = setParam(15L, value)

    var shadowNormalBias: Double
        @JvmName("shadowNormalBiasProperty")
        get() = getParam(14L)
        @JvmName("setShadowNormalBiasProperty")
        set(value) = setParam(14L, value)

    var shadowReverseCullFace: Boolean
        @JvmName("shadowReverseCullFaceProperty")
        get() = getShadowReverseCullFace()
        @JvmName("setShadowReverseCullFaceProperty")
        set(value) = setShadowReverseCullFace(value)

    var shadowTransmittanceBias: Double
        @JvmName("shadowTransmittanceBiasProperty")
        get() = getParam(19L)
        @JvmName("setShadowTransmittanceBiasProperty")
        set(value) = setParam(19L, value)

    var shadowOpacity: Double
        @JvmName("shadowOpacityProperty")
        get() = getParam(17L)
        @JvmName("setShadowOpacityProperty")
        set(value) = setParam(17L, value)

    var shadowBlur: Double
        @JvmName("shadowBlurProperty")
        get() = getParam(18L)
        @JvmName("setShadowBlurProperty")
        set(value) = setParam(18L, value)

    var shadowCasterMask: Long
        @JvmName("shadowCasterMaskProperty")
        get() = getShadowCasterMask()
        @JvmName("setShadowCasterMaskProperty")
        set(value) = setShadowCasterMask(value)

    var distanceFadeEnabled: Boolean
        @JvmName("distanceFadeEnabledProperty")
        get() = isDistanceFadeEnabled()
        @JvmName("setDistanceFadeEnabledProperty")
        set(value) = setEnableDistanceFade(value)

    var distanceFadeBegin: Double
        @JvmName("distanceFadeBeginProperty")
        get() = getDistanceFadeBegin()
        @JvmName("setDistanceFadeBeginProperty")
        set(value) = setDistanceFadeBegin(value)

    var distanceFadeShadow: Double
        @JvmName("distanceFadeShadowProperty")
        get() = getDistanceFadeShadow()
        @JvmName("setDistanceFadeShadowProperty")
        set(value) = setDistanceFadeShadow(value)

    var distanceFadeLength: Double
        @JvmName("distanceFadeLengthProperty")
        get() = getDistanceFadeLength()
        @JvmName("setDistanceFadeLengthProperty")
        set(value) = setDistanceFadeLength(value)

    var editorOnly: Boolean
        @JvmName("editorOnlyProperty")
        get() = isEditorOnly()
        @JvmName("setEditorOnlyProperty")
        set(value) = setEditorOnly(value)

    fun setEditorOnly(editorOnly: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditorOnlyBind, handle, editorOnly)
    }

    fun isEditorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditorOnlyBind, handle)
    }

    fun setParam(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, handle, param, value)
    }

    fun getParam(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, handle, param)
    }

    fun setShadow(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShadowBind, handle, enabled)
    }

    fun hasShadow(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasShadowBind, handle)
    }

    fun setNegative(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNegativeBind, handle, enabled)
    }

    fun isNegative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNegativeBind, handle)
    }

    fun setCullMask(cullMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, handle, cullMask)
    }

    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, handle)
    }

    fun setEnableDistanceFade(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDistanceFadeBind, handle, enable)
    }

    fun isDistanceFadeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDistanceFadeEnabledBind, handle)
    }

    fun setDistanceFadeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeBeginBind, handle, distance)
    }

    fun getDistanceFadeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeBeginBind, handle)
    }

    fun setDistanceFadeShadow(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeShadowBind, handle, distance)
    }

    fun getDistanceFadeShadow(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeShadowBind, handle)
    }

    fun setDistanceFadeLength(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDistanceFadeLengthBind, handle, distance)
    }

    fun getDistanceFadeLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceFadeLengthBind, handle)
    }

    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    fun setShadowReverseCullFace(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShadowReverseCullFaceBind, handle, enable)
    }

    fun getShadowReverseCullFace(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getShadowReverseCullFaceBind, handle)
    }

    fun setShadowCasterMask(casterMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setShadowCasterMaskBind, handle, casterMask)
    }

    fun getShadowCasterMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getShadowCasterMaskBind, handle)
    }

    fun setBakeMode(bakeMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBakeModeBind, handle, bakeMode)
    }

    fun getBakeMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBakeModeBind, handle)
    }

    fun setProjector(projector: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setProjectorBind, handle, listOf(projector?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getProjector(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProjectorBind, handle))
    }

    fun setTemperature(temperature: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTemperatureBind, handle, temperature)
    }

    fun getTemperature(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTemperatureBind, handle)
    }

    fun getCorrelatedColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getCorrelatedColorBind, handle)
    }

    companion object {
        const val PARAM_ENERGY: Long = 0L
        const val PARAM_INDIRECT_ENERGY: Long = 1L
        const val PARAM_VOLUMETRIC_FOG_ENERGY: Long = 2L
        const val PARAM_SPECULAR: Long = 3L
        const val PARAM_RANGE: Long = 4L
        const val PARAM_SIZE: Long = 5L
        const val PARAM_ATTENUATION: Long = 6L
        const val PARAM_SPOT_ANGLE: Long = 7L
        const val PARAM_SPOT_ATTENUATION: Long = 8L
        const val PARAM_SHADOW_MAX_DISTANCE: Long = 9L
        const val PARAM_SHADOW_SPLIT_1_OFFSET: Long = 10L
        const val PARAM_SHADOW_SPLIT_2_OFFSET: Long = 11L
        const val PARAM_SHADOW_SPLIT_3_OFFSET: Long = 12L
        const val PARAM_SHADOW_FADE_START: Long = 13L
        const val PARAM_SHADOW_NORMAL_BIAS: Long = 14L
        const val PARAM_SHADOW_BIAS: Long = 15L
        const val PARAM_SHADOW_PANCAKE_SIZE: Long = 16L
        const val PARAM_SHADOW_OPACITY: Long = 17L
        const val PARAM_SHADOW_BLUR: Long = 18L
        const val PARAM_TRANSMITTANCE_BIAS: Long = 19L
        const val PARAM_INTENSITY: Long = 20L
        const val PARAM_MAX: Long = 21L
        const val BAKE_DISABLED: Long = 0L
        const val BAKE_STATIC: Long = 1L
        const val BAKE_DYNAMIC: Long = 2L

        fun fromHandle(handle: MemorySegment): Light3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Light3D? =
            if (handle.address() == 0L) null else Light3D(handle)

        private const val SET_EDITOR_ONLY_HASH = 2586408642L
        private val setEditorOnlyBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_editor_only", SET_EDITOR_ONLY_HASH)
        }

        private const val IS_EDITOR_ONLY_HASH = 36873697L
        private val isEditorOnlyBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "is_editor_only", IS_EDITOR_ONLY_HASH)
        }

        private const val SET_PARAM_HASH = 1722734213L
        private val setParamBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_param", SET_PARAM_HASH)
        }

        private const val GET_PARAM_HASH = 1844084987L
        private val getParamBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_param", GET_PARAM_HASH)
        }

        private const val SET_SHADOW_HASH = 2586408642L
        private val setShadowBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_shadow", SET_SHADOW_HASH)
        }

        private const val HAS_SHADOW_HASH = 36873697L
        private val hasShadowBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "has_shadow", HAS_SHADOW_HASH)
        }

        private const val SET_NEGATIVE_HASH = 2586408642L
        private val setNegativeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_negative", SET_NEGATIVE_HASH)
        }

        private const val IS_NEGATIVE_HASH = 36873697L
        private val isNegativeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "is_negative", IS_NEGATIVE_HASH)
        }

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_cull_mask", GET_CULL_MASK_HASH)
        }

        private const val SET_ENABLE_DISTANCE_FADE_HASH = 2586408642L
        private val setEnableDistanceFadeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_enable_distance_fade", SET_ENABLE_DISTANCE_FADE_HASH)
        }

        private const val IS_DISTANCE_FADE_ENABLED_HASH = 36873697L
        private val isDistanceFadeEnabledBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "is_distance_fade_enabled", IS_DISTANCE_FADE_ENABLED_HASH)
        }

        private const val SET_DISTANCE_FADE_BEGIN_HASH = 373806689L
        private val setDistanceFadeBeginBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_begin", SET_DISTANCE_FADE_BEGIN_HASH)
        }

        private const val GET_DISTANCE_FADE_BEGIN_HASH = 1740695150L
        private val getDistanceFadeBeginBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_begin", GET_DISTANCE_FADE_BEGIN_HASH)
        }

        private const val SET_DISTANCE_FADE_SHADOW_HASH = 373806689L
        private val setDistanceFadeShadowBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_shadow", SET_DISTANCE_FADE_SHADOW_HASH)
        }

        private const val GET_DISTANCE_FADE_SHADOW_HASH = 1740695150L
        private val getDistanceFadeShadowBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_shadow", GET_DISTANCE_FADE_SHADOW_HASH)
        }

        private const val SET_DISTANCE_FADE_LENGTH_HASH = 373806689L
        private val setDistanceFadeLengthBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_distance_fade_length", SET_DISTANCE_FADE_LENGTH_HASH)
        }

        private const val GET_DISTANCE_FADE_LENGTH_HASH = 1740695150L
        private val getDistanceFadeLengthBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_distance_fade_length", GET_DISTANCE_FADE_LENGTH_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_color", GET_COLOR_HASH)
        }

        private const val SET_SHADOW_REVERSE_CULL_FACE_HASH = 2586408642L
        private val setShadowReverseCullFaceBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_shadow_reverse_cull_face", SET_SHADOW_REVERSE_CULL_FACE_HASH)
        }

        private const val GET_SHADOW_REVERSE_CULL_FACE_HASH = 36873697L
        private val getShadowReverseCullFaceBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_shadow_reverse_cull_face", GET_SHADOW_REVERSE_CULL_FACE_HASH)
        }

        private const val SET_SHADOW_CASTER_MASK_HASH = 1286410249L
        private val setShadowCasterMaskBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_shadow_caster_mask", SET_SHADOW_CASTER_MASK_HASH)
        }

        private const val GET_SHADOW_CASTER_MASK_HASH = 3905245786L
        private val getShadowCasterMaskBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_shadow_caster_mask", GET_SHADOW_CASTER_MASK_HASH)
        }

        private const val SET_BAKE_MODE_HASH = 37739303L
        private val setBakeModeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_bake_mode", SET_BAKE_MODE_HASH)
        }

        private const val GET_BAKE_MODE_HASH = 371737608L
        private val getBakeModeBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_bake_mode", GET_BAKE_MODE_HASH)
        }

        private const val SET_PROJECTOR_HASH = 4051416890L
        private val setProjectorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_projector", SET_PROJECTOR_HASH)
        }

        private const val GET_PROJECTOR_HASH = 3635182373L
        private val getProjectorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_projector", GET_PROJECTOR_HASH)
        }

        private const val SET_TEMPERATURE_HASH = 373806689L
        private val setTemperatureBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "set_temperature", SET_TEMPERATURE_HASH)
        }

        private const val GET_TEMPERATURE_HASH = 1740695150L
        private val getTemperatureBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_temperature", GET_TEMPERATURE_HASH)
        }

        private const val GET_CORRELATED_COLOR_HASH = 3444240500L
        private val getCorrelatedColorBind by lazy {
            ObjectCalls.getMethodBind("Light3D", "get_correlated_color", GET_CORRELATED_COLOR_HASH)
        }
    }
}
