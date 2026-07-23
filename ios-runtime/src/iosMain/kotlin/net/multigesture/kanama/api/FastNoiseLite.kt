package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: FastNoiseLite
 */
class FastNoiseLite(handle: MemorySegment) : Noise(handle) {
    var noiseType: Long
        @JvmName("noiseTypeProperty")
        get() = getNoiseType()
        @JvmName("setNoiseTypeProperty")
        set(value) = setNoiseType(value)

    var seed: Int
        @JvmName("seedProperty")
        get() = getSeed()
        @JvmName("setSeedProperty")
        set(value) = setSeed(value)

    var frequency: Double
        @JvmName("frequencyProperty")
        get() = getFrequency()
        @JvmName("setFrequencyProperty")
        set(value) = setFrequency(value)

    var offset: Vector3
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var fractalType: Long
        @JvmName("fractalTypeProperty")
        get() = getFractalType()
        @JvmName("setFractalTypeProperty")
        set(value) = setFractalType(value)

    var fractalOctaves: Int
        @JvmName("fractalOctavesProperty")
        get() = getFractalOctaves()
        @JvmName("setFractalOctavesProperty")
        set(value) = setFractalOctaves(value)

    var fractalLacunarity: Double
        @JvmName("fractalLacunarityProperty")
        get() = getFractalLacunarity()
        @JvmName("setFractalLacunarityProperty")
        set(value) = setFractalLacunarity(value)

    var fractalGain: Double
        @JvmName("fractalGainProperty")
        get() = getFractalGain()
        @JvmName("setFractalGainProperty")
        set(value) = setFractalGain(value)

    var fractalWeightedStrength: Double
        @JvmName("fractalWeightedStrengthProperty")
        get() = getFractalWeightedStrength()
        @JvmName("setFractalWeightedStrengthProperty")
        set(value) = setFractalWeightedStrength(value)

    var fractalPingPongStrength: Double
        @JvmName("fractalPingPongStrengthProperty")
        get() = getFractalPingPongStrength()
        @JvmName("setFractalPingPongStrengthProperty")
        set(value) = setFractalPingPongStrength(value)

    var cellularDistanceFunction: Long
        @JvmName("cellularDistanceFunctionProperty")
        get() = getCellularDistanceFunction()
        @JvmName("setCellularDistanceFunctionProperty")
        set(value) = setCellularDistanceFunction(value)

    var cellularJitter: Double
        @JvmName("cellularJitterProperty")
        get() = getCellularJitter()
        @JvmName("setCellularJitterProperty")
        set(value) = setCellularJitter(value)

    var cellularReturnType: Long
        @JvmName("cellularReturnTypeProperty")
        get() = getCellularReturnType()
        @JvmName("setCellularReturnTypeProperty")
        set(value) = setCellularReturnType(value)

    var domainWarpEnabled: Boolean
        @JvmName("domainWarpEnabledProperty")
        get() = isDomainWarpEnabled()
        @JvmName("setDomainWarpEnabledProperty")
        set(value) = setDomainWarpEnabled(value)

    var domainWarpType: Long
        @JvmName("domainWarpTypeProperty")
        get() = getDomainWarpType()
        @JvmName("setDomainWarpTypeProperty")
        set(value) = setDomainWarpType(value)

    var domainWarpAmplitude: Double
        @JvmName("domainWarpAmplitudeProperty")
        get() = getDomainWarpAmplitude()
        @JvmName("setDomainWarpAmplitudeProperty")
        set(value) = setDomainWarpAmplitude(value)

    var domainWarpFrequency: Double
        @JvmName("domainWarpFrequencyProperty")
        get() = getDomainWarpFrequency()
        @JvmName("setDomainWarpFrequencyProperty")
        set(value) = setDomainWarpFrequency(value)

    var domainWarpFractalType: Long
        @JvmName("domainWarpFractalTypeProperty")
        get() = getDomainWarpFractalType()
        @JvmName("setDomainWarpFractalTypeProperty")
        set(value) = setDomainWarpFractalType(value)

    var domainWarpFractalOctaves: Int
        @JvmName("domainWarpFractalOctavesProperty")
        get() = getDomainWarpFractalOctaves()
        @JvmName("setDomainWarpFractalOctavesProperty")
        set(value) = setDomainWarpFractalOctaves(value)

    var domainWarpFractalLacunarity: Double
        @JvmName("domainWarpFractalLacunarityProperty")
        get() = getDomainWarpFractalLacunarity()
        @JvmName("setDomainWarpFractalLacunarityProperty")
        set(value) = setDomainWarpFractalLacunarity(value)

    var domainWarpFractalGain: Double
        @JvmName("domainWarpFractalGainProperty")
        get() = getDomainWarpFractalGain()
        @JvmName("setDomainWarpFractalGainProperty")
        set(value) = setDomainWarpFractalGain(value)

    fun setNoiseType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setNoiseTypeBind, handle, type)
    }

    fun getNoiseType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNoiseTypeBind, handle)
    }

    fun setSeed(seed: Int) {
        ObjectCalls.ptrcallWithIntArg(setSeedBind, handle, seed)
    }

    fun getSeed(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSeedBind, handle)
    }

    fun setFrequency(freq: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFrequencyBind, handle, freq)
    }

    fun getFrequency(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrequencyBind, handle)
    }

    fun setOffset(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setOffsetBind, handle, offset)
    }

    fun getOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getOffsetBind, handle)
    }

    fun setFractalType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setFractalTypeBind, handle, type)
    }

    fun getFractalType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFractalTypeBind, handle)
    }

    fun setFractalOctaves(octaveCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setFractalOctavesBind, handle, octaveCount)
    }

    fun getFractalOctaves(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFractalOctavesBind, handle)
    }

    fun setFractalLacunarity(lacunarity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFractalLacunarityBind, handle, lacunarity)
    }

    fun getFractalLacunarity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFractalLacunarityBind, handle)
    }

    fun setFractalGain(gain: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFractalGainBind, handle, gain)
    }

    fun getFractalGain(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFractalGainBind, handle)
    }

    fun setFractalWeightedStrength(weightedStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFractalWeightedStrengthBind, handle, weightedStrength)
    }

    fun getFractalWeightedStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFractalWeightedStrengthBind, handle)
    }

    fun setFractalPingPongStrength(pingPongStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFractalPingPongStrengthBind, handle, pingPongStrength)
    }

    fun getFractalPingPongStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFractalPingPongStrengthBind, handle)
    }

    fun setCellularDistanceFunction(func: Long) {
        ObjectCalls.ptrcallWithLongArg(setCellularDistanceFunctionBind, handle, func)
    }

    fun getCellularDistanceFunction(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCellularDistanceFunctionBind, handle)
    }

    fun setCellularJitter(jitter: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCellularJitterBind, handle, jitter)
    }

    fun getCellularJitter(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellularJitterBind, handle)
    }

    fun setCellularReturnType(ret: Long) {
        ObjectCalls.ptrcallWithLongArg(setCellularReturnTypeBind, handle, ret)
    }

    fun getCellularReturnType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCellularReturnTypeBind, handle)
    }

    fun setDomainWarpEnabled(domainWarpEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDomainWarpEnabledBind, handle, domainWarpEnabled)
    }

    fun isDomainWarpEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDomainWarpEnabledBind, handle)
    }

    fun setDomainWarpType(domainWarpType: Long) {
        ObjectCalls.ptrcallWithLongArg(setDomainWarpTypeBind, handle, domainWarpType)
    }

    fun getDomainWarpType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDomainWarpTypeBind, handle)
    }

    fun setDomainWarpAmplitude(domainWarpAmplitude: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDomainWarpAmplitudeBind, handle, domainWarpAmplitude)
    }

    fun getDomainWarpAmplitude(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainWarpAmplitudeBind, handle)
    }

    fun setDomainWarpFrequency(domainWarpFrequency: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDomainWarpFrequencyBind, handle, domainWarpFrequency)
    }

    fun getDomainWarpFrequency(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainWarpFrequencyBind, handle)
    }

    fun setDomainWarpFractalType(domainWarpFractalType: Long) {
        ObjectCalls.ptrcallWithLongArg(setDomainWarpFractalTypeBind, handle, domainWarpFractalType)
    }

    fun getDomainWarpFractalType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDomainWarpFractalTypeBind, handle)
    }

    fun setDomainWarpFractalOctaves(domainWarpOctaveCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setDomainWarpFractalOctavesBind, handle, domainWarpOctaveCount)
    }

    fun getDomainWarpFractalOctaves(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDomainWarpFractalOctavesBind, handle)
    }

    fun setDomainWarpFractalLacunarity(domainWarpLacunarity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDomainWarpFractalLacunarityBind, handle, domainWarpLacunarity)
    }

    fun getDomainWarpFractalLacunarity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainWarpFractalLacunarityBind, handle)
    }

    fun setDomainWarpFractalGain(domainWarpGain: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDomainWarpFractalGainBind, handle, domainWarpGain)
    }

    fun getDomainWarpFractalGain(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainWarpFractalGainBind, handle)
    }

    companion object {
        const val TYPE_VALUE: Long = 5L
        const val TYPE_VALUE_CUBIC: Long = 4L
        const val TYPE_PERLIN: Long = 3L
        const val TYPE_CELLULAR: Long = 2L
        const val TYPE_SIMPLEX: Long = 0L
        const val TYPE_SIMPLEX_SMOOTH: Long = 1L
        const val FRACTAL_NONE: Long = 0L
        const val FRACTAL_FBM: Long = 1L
        const val FRACTAL_RIDGED: Long = 2L
        const val FRACTAL_PING_PONG: Long = 3L
        const val DISTANCE_EUCLIDEAN: Long = 0L
        const val DISTANCE_EUCLIDEAN_SQUARED: Long = 1L
        const val DISTANCE_MANHATTAN: Long = 2L
        const val DISTANCE_HYBRID: Long = 3L
        const val RETURN_CELL_VALUE: Long = 0L
        const val RETURN_DISTANCE: Long = 1L
        const val RETURN_DISTANCE2: Long = 2L
        const val RETURN_DISTANCE2_ADD: Long = 3L
        const val RETURN_DISTANCE2_SUB: Long = 4L
        const val RETURN_DISTANCE2_MUL: Long = 5L
        const val RETURN_DISTANCE2_DIV: Long = 6L
        const val DOMAIN_WARP_SIMPLEX: Long = 0L
        const val DOMAIN_WARP_SIMPLEX_REDUCED: Long = 1L
        const val DOMAIN_WARP_BASIC_GRID: Long = 2L
        const val DOMAIN_WARP_FRACTAL_NONE: Long = 0L
        const val DOMAIN_WARP_FRACTAL_PROGRESSIVE: Long = 1L
        const val DOMAIN_WARP_FRACTAL_INDEPENDENT: Long = 2L

        fun fromHandle(handle: MemorySegment): FastNoiseLite? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FastNoiseLite? =
            if (handle.address() == 0L) null else FastNoiseLite(handle)

        // Instantiate a FastNoiseLite (RefCounted noise generator).
        fun create(): FastNoiseLite =
            FastNoiseLite(MemorySegment.ofAddress(IosGodot.constructObject("FastNoiseLite")))

        private const val SET_NOISE_TYPE_HASH = 2624461392L
        private val setNoiseTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_noise_type", SET_NOISE_TYPE_HASH)
        }

        private const val GET_NOISE_TYPE_HASH = 1458108610L
        private val getNoiseTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_noise_type", GET_NOISE_TYPE_HASH)
        }

        private const val SET_SEED_HASH = 1286410249L
        private val setSeedBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_seed", SET_SEED_HASH)
        }

        private const val GET_SEED_HASH = 3905245786L
        private val getSeedBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_seed", GET_SEED_HASH)
        }

        private const val SET_FREQUENCY_HASH = 373806689L
        private val setFrequencyBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_frequency", SET_FREQUENCY_HASH)
        }

        private const val GET_FREQUENCY_HASH = 1740695150L
        private val getFrequencyBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_frequency", GET_FREQUENCY_HASH)
        }

        private const val SET_OFFSET_HASH = 3460891852L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3360562783L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_FRACTAL_TYPE_HASH = 4132731174L
        private val setFractalTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_type", SET_FRACTAL_TYPE_HASH)
        }

        private const val GET_FRACTAL_TYPE_HASH = 1036889279L
        private val getFractalTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_type", GET_FRACTAL_TYPE_HASH)
        }

        private const val SET_FRACTAL_OCTAVES_HASH = 1286410249L
        private val setFractalOctavesBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_octaves", SET_FRACTAL_OCTAVES_HASH)
        }

        private const val GET_FRACTAL_OCTAVES_HASH = 3905245786L
        private val getFractalOctavesBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_octaves", GET_FRACTAL_OCTAVES_HASH)
        }

        private const val SET_FRACTAL_LACUNARITY_HASH = 373806689L
        private val setFractalLacunarityBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_lacunarity", SET_FRACTAL_LACUNARITY_HASH)
        }

        private const val GET_FRACTAL_LACUNARITY_HASH = 1740695150L
        private val getFractalLacunarityBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_lacunarity", GET_FRACTAL_LACUNARITY_HASH)
        }

        private const val SET_FRACTAL_GAIN_HASH = 373806689L
        private val setFractalGainBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_gain", SET_FRACTAL_GAIN_HASH)
        }

        private const val GET_FRACTAL_GAIN_HASH = 1740695150L
        private val getFractalGainBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_gain", GET_FRACTAL_GAIN_HASH)
        }

        private const val SET_FRACTAL_WEIGHTED_STRENGTH_HASH = 373806689L
        private val setFractalWeightedStrengthBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_weighted_strength", SET_FRACTAL_WEIGHTED_STRENGTH_HASH)
        }

        private const val GET_FRACTAL_WEIGHTED_STRENGTH_HASH = 1740695150L
        private val getFractalWeightedStrengthBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_weighted_strength", GET_FRACTAL_WEIGHTED_STRENGTH_HASH)
        }

        private const val SET_FRACTAL_PING_PONG_STRENGTH_HASH = 373806689L
        private val setFractalPingPongStrengthBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_ping_pong_strength", SET_FRACTAL_PING_PONG_STRENGTH_HASH)
        }

        private const val GET_FRACTAL_PING_PONG_STRENGTH_HASH = 1740695150L
        private val getFractalPingPongStrengthBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_ping_pong_strength", GET_FRACTAL_PING_PONG_STRENGTH_HASH)
        }

        private const val SET_CELLULAR_DISTANCE_FUNCTION_HASH = 1006013267L
        private val setCellularDistanceFunctionBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_cellular_distance_function", SET_CELLULAR_DISTANCE_FUNCTION_HASH)
        }

        private const val GET_CELLULAR_DISTANCE_FUNCTION_HASH = 2021274088L
        private val getCellularDistanceFunctionBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_cellular_distance_function", GET_CELLULAR_DISTANCE_FUNCTION_HASH)
        }

        private const val SET_CELLULAR_JITTER_HASH = 373806689L
        private val setCellularJitterBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_cellular_jitter", SET_CELLULAR_JITTER_HASH)
        }

        private const val GET_CELLULAR_JITTER_HASH = 1740695150L
        private val getCellularJitterBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_cellular_jitter", GET_CELLULAR_JITTER_HASH)
        }

        private const val SET_CELLULAR_RETURN_TYPE_HASH = 2654169698L
        private val setCellularReturnTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_cellular_return_type", SET_CELLULAR_RETURN_TYPE_HASH)
        }

        private const val GET_CELLULAR_RETURN_TYPE_HASH = 3699796343L
        private val getCellularReturnTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_cellular_return_type", GET_CELLULAR_RETURN_TYPE_HASH)
        }

        private const val SET_DOMAIN_WARP_ENABLED_HASH = 2586408642L
        private val setDomainWarpEnabledBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_enabled", SET_DOMAIN_WARP_ENABLED_HASH)
        }

        private const val IS_DOMAIN_WARP_ENABLED_HASH = 36873697L
        private val isDomainWarpEnabledBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "is_domain_warp_enabled", IS_DOMAIN_WARP_ENABLED_HASH)
        }

        private const val SET_DOMAIN_WARP_TYPE_HASH = 3629692980L
        private val setDomainWarpTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_type", SET_DOMAIN_WARP_TYPE_HASH)
        }

        private const val GET_DOMAIN_WARP_TYPE_HASH = 2980162020L
        private val getDomainWarpTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_type", GET_DOMAIN_WARP_TYPE_HASH)
        }

        private const val SET_DOMAIN_WARP_AMPLITUDE_HASH = 373806689L
        private val setDomainWarpAmplitudeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_amplitude", SET_DOMAIN_WARP_AMPLITUDE_HASH)
        }

        private const val GET_DOMAIN_WARP_AMPLITUDE_HASH = 1740695150L
        private val getDomainWarpAmplitudeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_amplitude", GET_DOMAIN_WARP_AMPLITUDE_HASH)
        }

        private const val SET_DOMAIN_WARP_FREQUENCY_HASH = 373806689L
        private val setDomainWarpFrequencyBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_frequency", SET_DOMAIN_WARP_FREQUENCY_HASH)
        }

        private const val GET_DOMAIN_WARP_FREQUENCY_HASH = 1740695150L
        private val getDomainWarpFrequencyBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_frequency", GET_DOMAIN_WARP_FREQUENCY_HASH)
        }

        private const val SET_DOMAIN_WARP_FRACTAL_TYPE_HASH = 3999408287L
        private val setDomainWarpFractalTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_fractal_type", SET_DOMAIN_WARP_FRACTAL_TYPE_HASH)
        }

        private const val GET_DOMAIN_WARP_FRACTAL_TYPE_HASH = 407716934L
        private val getDomainWarpFractalTypeBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_fractal_type", GET_DOMAIN_WARP_FRACTAL_TYPE_HASH)
        }

        private const val SET_DOMAIN_WARP_FRACTAL_OCTAVES_HASH = 1286410249L
        private val setDomainWarpFractalOctavesBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_fractal_octaves", SET_DOMAIN_WARP_FRACTAL_OCTAVES_HASH)
        }

        private const val GET_DOMAIN_WARP_FRACTAL_OCTAVES_HASH = 3905245786L
        private val getDomainWarpFractalOctavesBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_fractal_octaves", GET_DOMAIN_WARP_FRACTAL_OCTAVES_HASH)
        }

        private const val SET_DOMAIN_WARP_FRACTAL_LACUNARITY_HASH = 373806689L
        private val setDomainWarpFractalLacunarityBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_fractal_lacunarity", SET_DOMAIN_WARP_FRACTAL_LACUNARITY_HASH)
        }

        private const val GET_DOMAIN_WARP_FRACTAL_LACUNARITY_HASH = 1740695150L
        private val getDomainWarpFractalLacunarityBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_fractal_lacunarity", GET_DOMAIN_WARP_FRACTAL_LACUNARITY_HASH)
        }

        private const val SET_DOMAIN_WARP_FRACTAL_GAIN_HASH = 373806689L
        private val setDomainWarpFractalGainBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_fractal_gain", SET_DOMAIN_WARP_FRACTAL_GAIN_HASH)
        }

        private const val GET_DOMAIN_WARP_FRACTAL_GAIN_HASH = 1740695150L
        private val getDomainWarpFractalGainBind by lazy {
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_fractal_gain", GET_DOMAIN_WARP_FRACTAL_GAIN_HASH)
        }
    }
}
