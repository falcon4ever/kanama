package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: FastNoiseLite
 */
class FastNoiseLite(handle: GodotHandle) : Noise(handle) {
    var noiseType: FastNoiseLite.NoiseType
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

    var fractalType: FastNoiseLite.FractalType
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

    var cellularDistanceFunction: FastNoiseLite.CellularDistanceFunction
        @JvmName("cellularDistanceFunctionProperty")
        get() = getCellularDistanceFunction()
        @JvmName("setCellularDistanceFunctionProperty")
        set(value) = setCellularDistanceFunction(value)

    var cellularJitter: Double
        @JvmName("cellularJitterProperty")
        get() = getCellularJitter()
        @JvmName("setCellularJitterProperty")
        set(value) = setCellularJitter(value)

    var cellularReturnType: FastNoiseLite.CellularReturnType
        @JvmName("cellularReturnTypeProperty")
        get() = getCellularReturnType()
        @JvmName("setCellularReturnTypeProperty")
        set(value) = setCellularReturnType(value)

    var domainWarpEnabled: Boolean
        @JvmName("domainWarpEnabledProperty")
        get() = isDomainWarpEnabled()
        @JvmName("setDomainWarpEnabledProperty")
        set(value) = setDomainWarpEnabled(value)

    var domainWarpType: FastNoiseLite.DomainWarpType
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

    var domainWarpFractalType: FastNoiseLite.DomainWarpFractalType
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

    fun setNoiseType(type: FastNoiseLite.NoiseType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setNoiseTypeBind, segment, type.value)
    }

    fun getNoiseType(): FastNoiseLite.NoiseType {
        checkOpen()
        return FastNoiseLite.NoiseType(ObjectCalls.ptrcallNoArgsRetLong(getNoiseTypeBind, segment))
    }

    fun setSeed(seed: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSeedBind, segment, seed)
    }

    fun getSeed(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSeedBind, segment)
    }

    fun setFrequency(freq: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFrequencyBind, segment, freq)
    }

    fun getFrequency(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrequencyBind, segment)
    }

    fun setOffset(offset: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setOffsetBind, segment, offset)
    }

    fun getOffset(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getOffsetBind, segment)
    }

    fun setFractalType(type: FastNoiseLite.FractalType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFractalTypeBind, segment, type.value)
    }

    fun getFractalType(): FastNoiseLite.FractalType {
        checkOpen()
        return FastNoiseLite.FractalType(ObjectCalls.ptrcallNoArgsRetLong(getFractalTypeBind, segment))
    }

    fun setFractalOctaves(octaveCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setFractalOctavesBind, segment, octaveCount)
    }

    fun getFractalOctaves(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFractalOctavesBind, segment)
    }

    fun setFractalLacunarity(lacunarity: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFractalLacunarityBind, segment, lacunarity)
    }

    fun getFractalLacunarity(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFractalLacunarityBind, segment)
    }

    fun setFractalGain(gain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFractalGainBind, segment, gain)
    }

    fun getFractalGain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFractalGainBind, segment)
    }

    fun setFractalWeightedStrength(weightedStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFractalWeightedStrengthBind, segment, weightedStrength)
    }

    fun getFractalWeightedStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFractalWeightedStrengthBind, segment)
    }

    fun setFractalPingPongStrength(pingPongStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFractalPingPongStrengthBind, segment, pingPongStrength)
    }

    fun getFractalPingPongStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFractalPingPongStrengthBind, segment)
    }

    fun setCellularDistanceFunction(func: FastNoiseLite.CellularDistanceFunction) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCellularDistanceFunctionBind, segment, func.value)
    }

    fun getCellularDistanceFunction(): FastNoiseLite.CellularDistanceFunction {
        checkOpen()
        return FastNoiseLite.CellularDistanceFunction(ObjectCalls.ptrcallNoArgsRetLong(getCellularDistanceFunctionBind, segment))
    }

    fun setCellularJitter(jitter: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setCellularJitterBind, segment, jitter)
    }

    fun getCellularJitter(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellularJitterBind, segment)
    }

    fun setCellularReturnType(ret: FastNoiseLite.CellularReturnType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCellularReturnTypeBind, segment, ret.value)
    }

    fun getCellularReturnType(): FastNoiseLite.CellularReturnType {
        checkOpen()
        return FastNoiseLite.CellularReturnType(ObjectCalls.ptrcallNoArgsRetLong(getCellularReturnTypeBind, segment))
    }

    fun setDomainWarpEnabled(domainWarpEnabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setDomainWarpEnabledBind, segment, domainWarpEnabled)
    }

    fun isDomainWarpEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isDomainWarpEnabledBind, segment)
    }

    fun setDomainWarpType(domainWarpType: FastNoiseLite.DomainWarpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDomainWarpTypeBind, segment, domainWarpType.value)
    }

    fun getDomainWarpType(): FastNoiseLite.DomainWarpType {
        checkOpen()
        return FastNoiseLite.DomainWarpType(ObjectCalls.ptrcallNoArgsRetLong(getDomainWarpTypeBind, segment))
    }

    fun setDomainWarpAmplitude(domainWarpAmplitude: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDomainWarpAmplitudeBind, segment, domainWarpAmplitude)
    }

    fun getDomainWarpAmplitude(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainWarpAmplitudeBind, segment)
    }

    fun setDomainWarpFrequency(domainWarpFrequency: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDomainWarpFrequencyBind, segment, domainWarpFrequency)
    }

    fun getDomainWarpFrequency(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainWarpFrequencyBind, segment)
    }

    fun setDomainWarpFractalType(domainWarpFractalType: FastNoiseLite.DomainWarpFractalType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDomainWarpFractalTypeBind, segment, domainWarpFractalType.value)
    }

    fun getDomainWarpFractalType(): FastNoiseLite.DomainWarpFractalType {
        checkOpen()
        return FastNoiseLite.DomainWarpFractalType(ObjectCalls.ptrcallNoArgsRetLong(getDomainWarpFractalTypeBind, segment))
    }

    fun setDomainWarpFractalOctaves(domainWarpOctaveCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setDomainWarpFractalOctavesBind, segment, domainWarpOctaveCount)
    }

    fun getDomainWarpFractalOctaves(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getDomainWarpFractalOctavesBind, segment)
    }

    fun setDomainWarpFractalLacunarity(domainWarpLacunarity: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDomainWarpFractalLacunarityBind, segment, domainWarpLacunarity)
    }

    fun getDomainWarpFractalLacunarity(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainWarpFractalLacunarityBind, segment)
    }

    fun setDomainWarpFractalGain(domainWarpGain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDomainWarpFractalGainBind, segment, domainWarpGain)
    }

    fun getDomainWarpFractalGain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainWarpFractalGainBind, segment)
    }

    @JvmInline
    value class NoiseType(override val value: Long) : GodotEnumValue {
        companion object {
            val VALUE: NoiseType get() = NoiseType(5L)
            val VALUE_CUBIC: NoiseType get() = NoiseType(4L)
            val PERLIN: NoiseType get() = NoiseType(3L)
            val CELLULAR: NoiseType get() = NoiseType(2L)
            val SIMPLEX: NoiseType get() = NoiseType(0L)
            val SIMPLEX_SMOOTH: NoiseType get() = NoiseType(1L)
        }
    }

    @JvmInline
    value class FractalType(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: FractalType get() = FractalType(0L)
            val FBM: FractalType get() = FractalType(1L)
            val RIDGED: FractalType get() = FractalType(2L)
            val PING_PONG: FractalType get() = FractalType(3L)
        }
    }

    @JvmInline
    value class CellularDistanceFunction(override val value: Long) : GodotEnumValue {
        companion object {
            val EUCLIDEAN: CellularDistanceFunction get() = CellularDistanceFunction(0L)
            val EUCLIDEAN_SQUARED: CellularDistanceFunction get() = CellularDistanceFunction(1L)
            val MANHATTAN: CellularDistanceFunction get() = CellularDistanceFunction(2L)
            val HYBRID: CellularDistanceFunction get() = CellularDistanceFunction(3L)
        }
    }

    @JvmInline
    value class CellularReturnType(override val value: Long) : GodotEnumValue {
        companion object {
            val CELL_VALUE: CellularReturnType get() = CellularReturnType(0L)
            val DISTANCE: CellularReturnType get() = CellularReturnType(1L)
            val DISTANCE2: CellularReturnType get() = CellularReturnType(2L)
            val DISTANCE2_ADD: CellularReturnType get() = CellularReturnType(3L)
            val DISTANCE2_SUB: CellularReturnType get() = CellularReturnType(4L)
            val DISTANCE2_MUL: CellularReturnType get() = CellularReturnType(5L)
            val DISTANCE2_DIV: CellularReturnType get() = CellularReturnType(6L)
        }
    }

    @JvmInline
    value class DomainWarpType(override val value: Long) : GodotEnumValue {
        companion object {
            val SIMPLEX: DomainWarpType get() = DomainWarpType(0L)
            val SIMPLEX_REDUCED: DomainWarpType get() = DomainWarpType(1L)
            val BASIC_GRID: DomainWarpType get() = DomainWarpType(2L)
        }
    }

    @JvmInline
    value class DomainWarpFractalType(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: DomainWarpFractalType get() = DomainWarpFractalType(0L)
            val PROGRESSIVE: DomainWarpFractalType get() = DomainWarpFractalType(1L)
            val INDEPENDENT: DomainWarpFractalType get() = DomainWarpFractalType(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FastNoiseLite? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): FastNoiseLite? =
            if (handle.address() == 0L) null else FastNoiseLite(GodotHandle(handle))

        // Instantiate a FastNoiseLite.
        @JvmStatic
        fun create(): FastNoiseLite =
            FastNoiseLite(GodotHandle(ObjectCalls.constructObject("FastNoiseLite")))

        // Downcast a Resource to FastNoiseLite (null if not).
        @JvmStatic
        fun fromResource(value: Resource): FastNoiseLite? =
            if (value.isClass("FastNoiseLite")) FastNoiseLite(value.handle) else null

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
