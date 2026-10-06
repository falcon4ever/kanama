package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithLongArg(Binds.setNoiseTypeBind, segment, type.value)
    }

    fun getNoiseType(): FastNoiseLite.NoiseType {
        checkOpen()
        return FastNoiseLite.NoiseType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getNoiseTypeBind, segment))
    }

    fun setSeed(seed: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSeedBind, segment, seed)
    }

    fun getSeed(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSeedBind, segment)
    }

    fun setFrequency(freq: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFrequencyBind, segment, freq)
    }

    fun getFrequency(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFrequencyBind, segment)
    }

    fun setOffset(offset: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setOffsetBind, segment, offset)
    }

    fun getOffset(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getOffsetBind, segment)
    }

    fun setFractalType(type: FastNoiseLite.FractalType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFractalTypeBind, segment, type.value)
    }

    fun getFractalType(): FastNoiseLite.FractalType {
        checkOpen()
        return FastNoiseLite.FractalType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFractalTypeBind, segment))
    }

    fun setFractalOctaves(octaveCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFractalOctavesBind, segment, octaveCount)
    }

    fun getFractalOctaves(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFractalOctavesBind, segment)
    }

    fun setFractalLacunarity(lacunarity: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFractalLacunarityBind, segment, lacunarity)
    }

    fun getFractalLacunarity(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFractalLacunarityBind, segment)
    }

    fun setFractalGain(gain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFractalGainBind, segment, gain)
    }

    fun getFractalGain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFractalGainBind, segment)
    }

    fun setFractalWeightedStrength(weightedStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFractalWeightedStrengthBind, segment, weightedStrength)
    }

    fun getFractalWeightedStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFractalWeightedStrengthBind, segment)
    }

    fun setFractalPingPongStrength(pingPongStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFractalPingPongStrengthBind, segment, pingPongStrength)
    }

    fun getFractalPingPongStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFractalPingPongStrengthBind, segment)
    }

    fun setCellularDistanceFunction(func: FastNoiseLite.CellularDistanceFunction) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCellularDistanceFunctionBind, segment, func.value)
    }

    fun getCellularDistanceFunction(): FastNoiseLite.CellularDistanceFunction {
        checkOpen()
        return FastNoiseLite.CellularDistanceFunction(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCellularDistanceFunctionBind, segment))
    }

    fun setCellularJitter(jitter: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCellularJitterBind, segment, jitter)
    }

    fun getCellularJitter(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCellularJitterBind, segment)
    }

    fun setCellularReturnType(ret: FastNoiseLite.CellularReturnType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCellularReturnTypeBind, segment, ret.value)
    }

    fun getCellularReturnType(): FastNoiseLite.CellularReturnType {
        checkOpen()
        return FastNoiseLite.CellularReturnType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCellularReturnTypeBind, segment))
    }

    fun setDomainWarpEnabled(domainWarpEnabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDomainWarpEnabledBind, segment, domainWarpEnabled)
    }

    fun isDomainWarpEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDomainWarpEnabledBind, segment)
    }

    fun setDomainWarpType(domainWarpType: FastNoiseLite.DomainWarpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDomainWarpTypeBind, segment, domainWarpType.value)
    }

    fun getDomainWarpType(): FastNoiseLite.DomainWarpType {
        checkOpen()
        return FastNoiseLite.DomainWarpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDomainWarpTypeBind, segment))
    }

    fun setDomainWarpAmplitude(domainWarpAmplitude: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDomainWarpAmplitudeBind, segment, domainWarpAmplitude)
    }

    fun getDomainWarpAmplitude(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainWarpAmplitudeBind, segment)
    }

    fun setDomainWarpFrequency(domainWarpFrequency: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDomainWarpFrequencyBind, segment, domainWarpFrequency)
    }

    fun getDomainWarpFrequency(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainWarpFrequencyBind, segment)
    }

    fun setDomainWarpFractalType(domainWarpFractalType: FastNoiseLite.DomainWarpFractalType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDomainWarpFractalTypeBind, segment, domainWarpFractalType.value)
    }

    fun getDomainWarpFractalType(): FastNoiseLite.DomainWarpFractalType {
        checkOpen()
        return FastNoiseLite.DomainWarpFractalType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDomainWarpFractalTypeBind, segment))
    }

    fun setDomainWarpFractalOctaves(domainWarpOctaveCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDomainWarpFractalOctavesBind, segment, domainWarpOctaveCount)
    }

    fun getDomainWarpFractalOctaves(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDomainWarpFractalOctavesBind, segment)
    }

    fun setDomainWarpFractalLacunarity(domainWarpLacunarity: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDomainWarpFractalLacunarityBind, segment, domainWarpLacunarity)
    }

    fun getDomainWarpFractalLacunarity(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainWarpFractalLacunarityBind, segment)
    }

    fun setDomainWarpFractalGain(domainWarpGain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDomainWarpFractalGainBind, segment, domainWarpGain)
    }

    fun getDomainWarpFractalGain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainWarpFractalGainBind, segment)
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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): FastNoiseLite? =
            if (handle.address() == 0L) null else RefCounted.owned(FastNoiseLite(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): FastNoiseLite? =
            if (handle.address() == 0L) null else FastNoiseLite(GodotHandle(handle))

        // Instantiate a FastNoiseLite.
        @JvmStatic
        fun create(): FastNoiseLite =
            RefCounted.owned(FastNoiseLite(GodotHandle(ObjectCalls.constructObject("FastNoiseLite"))))

        // Downcast a Resource to FastNoiseLite (null if not).
        @JvmStatic
        fun fromResource(value: Resource): FastNoiseLite? =
            if (value.isClass("FastNoiseLite")) RefCounted.retained(FastNoiseLite(value.handle)) else null
    }

    private object Binds {
        private const val SET_NOISE_TYPE_HASH = 2624461392L
        @JvmField
        val setNoiseTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_noise_type", SET_NOISE_TYPE_HASH)

        private const val GET_NOISE_TYPE_HASH = 1458108610L
        @JvmField
        val getNoiseTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_noise_type", GET_NOISE_TYPE_HASH)

        private const val SET_SEED_HASH = 1286410249L
        @JvmField
        val setSeedBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_seed", SET_SEED_HASH)

        private const val GET_SEED_HASH = 3905245786L
        @JvmField
        val getSeedBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_seed", GET_SEED_HASH)

        private const val SET_FREQUENCY_HASH = 373806689L
        @JvmField
        val setFrequencyBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_frequency", SET_FREQUENCY_HASH)

        private const val GET_FREQUENCY_HASH = 1740695150L
        @JvmField
        val getFrequencyBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_frequency", GET_FREQUENCY_HASH)

        private const val SET_OFFSET_HASH = 3460891852L
        @JvmField
        val setOffsetBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_offset", SET_OFFSET_HASH)

        private const val GET_OFFSET_HASH = 3360562783L
        @JvmField
        val getOffsetBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_offset", GET_OFFSET_HASH)

        private const val SET_FRACTAL_TYPE_HASH = 4132731174L
        @JvmField
        val setFractalTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_type", SET_FRACTAL_TYPE_HASH)

        private const val GET_FRACTAL_TYPE_HASH = 1036889279L
        @JvmField
        val getFractalTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_type", GET_FRACTAL_TYPE_HASH)

        private const val SET_FRACTAL_OCTAVES_HASH = 1286410249L
        @JvmField
        val setFractalOctavesBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_octaves", SET_FRACTAL_OCTAVES_HASH)

        private const val GET_FRACTAL_OCTAVES_HASH = 3905245786L
        @JvmField
        val getFractalOctavesBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_octaves", GET_FRACTAL_OCTAVES_HASH)

        private const val SET_FRACTAL_LACUNARITY_HASH = 373806689L
        @JvmField
        val setFractalLacunarityBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_lacunarity", SET_FRACTAL_LACUNARITY_HASH)

        private const val GET_FRACTAL_LACUNARITY_HASH = 1740695150L
        @JvmField
        val getFractalLacunarityBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_lacunarity", GET_FRACTAL_LACUNARITY_HASH)

        private const val SET_FRACTAL_GAIN_HASH = 373806689L
        @JvmField
        val setFractalGainBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_gain", SET_FRACTAL_GAIN_HASH)

        private const val GET_FRACTAL_GAIN_HASH = 1740695150L
        @JvmField
        val getFractalGainBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_gain", GET_FRACTAL_GAIN_HASH)

        private const val SET_FRACTAL_WEIGHTED_STRENGTH_HASH = 373806689L
        @JvmField
        val setFractalWeightedStrengthBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_weighted_strength", SET_FRACTAL_WEIGHTED_STRENGTH_HASH)

        private const val GET_FRACTAL_WEIGHTED_STRENGTH_HASH = 1740695150L
        @JvmField
        val getFractalWeightedStrengthBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_weighted_strength", GET_FRACTAL_WEIGHTED_STRENGTH_HASH)

        private const val SET_FRACTAL_PING_PONG_STRENGTH_HASH = 373806689L
        @JvmField
        val setFractalPingPongStrengthBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_fractal_ping_pong_strength", SET_FRACTAL_PING_PONG_STRENGTH_HASH)

        private const val GET_FRACTAL_PING_PONG_STRENGTH_HASH = 1740695150L
        @JvmField
        val getFractalPingPongStrengthBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_fractal_ping_pong_strength", GET_FRACTAL_PING_PONG_STRENGTH_HASH)

        private const val SET_CELLULAR_DISTANCE_FUNCTION_HASH = 1006013267L
        @JvmField
        val setCellularDistanceFunctionBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_cellular_distance_function", SET_CELLULAR_DISTANCE_FUNCTION_HASH)

        private const val GET_CELLULAR_DISTANCE_FUNCTION_HASH = 2021274088L
        @JvmField
        val getCellularDistanceFunctionBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_cellular_distance_function", GET_CELLULAR_DISTANCE_FUNCTION_HASH)

        private const val SET_CELLULAR_JITTER_HASH = 373806689L
        @JvmField
        val setCellularJitterBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_cellular_jitter", SET_CELLULAR_JITTER_HASH)

        private const val GET_CELLULAR_JITTER_HASH = 1740695150L
        @JvmField
        val getCellularJitterBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_cellular_jitter", GET_CELLULAR_JITTER_HASH)

        private const val SET_CELLULAR_RETURN_TYPE_HASH = 2654169698L
        @JvmField
        val setCellularReturnTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_cellular_return_type", SET_CELLULAR_RETURN_TYPE_HASH)

        private const val GET_CELLULAR_RETURN_TYPE_HASH = 3699796343L
        @JvmField
        val getCellularReturnTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_cellular_return_type", GET_CELLULAR_RETURN_TYPE_HASH)

        private const val SET_DOMAIN_WARP_ENABLED_HASH = 2586408642L
        @JvmField
        val setDomainWarpEnabledBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_enabled", SET_DOMAIN_WARP_ENABLED_HASH)

        private const val IS_DOMAIN_WARP_ENABLED_HASH = 36873697L
        @JvmField
        val isDomainWarpEnabledBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "is_domain_warp_enabled", IS_DOMAIN_WARP_ENABLED_HASH)

        private const val SET_DOMAIN_WARP_TYPE_HASH = 3629692980L
        @JvmField
        val setDomainWarpTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_type", SET_DOMAIN_WARP_TYPE_HASH)

        private const val GET_DOMAIN_WARP_TYPE_HASH = 2980162020L
        @JvmField
        val getDomainWarpTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_type", GET_DOMAIN_WARP_TYPE_HASH)

        private const val SET_DOMAIN_WARP_AMPLITUDE_HASH = 373806689L
        @JvmField
        val setDomainWarpAmplitudeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_amplitude", SET_DOMAIN_WARP_AMPLITUDE_HASH)

        private const val GET_DOMAIN_WARP_AMPLITUDE_HASH = 1740695150L
        @JvmField
        val getDomainWarpAmplitudeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_amplitude", GET_DOMAIN_WARP_AMPLITUDE_HASH)

        private const val SET_DOMAIN_WARP_FREQUENCY_HASH = 373806689L
        @JvmField
        val setDomainWarpFrequencyBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_frequency", SET_DOMAIN_WARP_FREQUENCY_HASH)

        private const val GET_DOMAIN_WARP_FREQUENCY_HASH = 1740695150L
        @JvmField
        val getDomainWarpFrequencyBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_frequency", GET_DOMAIN_WARP_FREQUENCY_HASH)

        private const val SET_DOMAIN_WARP_FRACTAL_TYPE_HASH = 3999408287L
        @JvmField
        val setDomainWarpFractalTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_fractal_type", SET_DOMAIN_WARP_FRACTAL_TYPE_HASH)

        private const val GET_DOMAIN_WARP_FRACTAL_TYPE_HASH = 407716934L
        @JvmField
        val getDomainWarpFractalTypeBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_fractal_type", GET_DOMAIN_WARP_FRACTAL_TYPE_HASH)

        private const val SET_DOMAIN_WARP_FRACTAL_OCTAVES_HASH = 1286410249L
        @JvmField
        val setDomainWarpFractalOctavesBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_fractal_octaves", SET_DOMAIN_WARP_FRACTAL_OCTAVES_HASH)

        private const val GET_DOMAIN_WARP_FRACTAL_OCTAVES_HASH = 3905245786L
        @JvmField
        val getDomainWarpFractalOctavesBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_fractal_octaves", GET_DOMAIN_WARP_FRACTAL_OCTAVES_HASH)

        private const val SET_DOMAIN_WARP_FRACTAL_LACUNARITY_HASH = 373806689L
        @JvmField
        val setDomainWarpFractalLacunarityBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_fractal_lacunarity", SET_DOMAIN_WARP_FRACTAL_LACUNARITY_HASH)

        private const val GET_DOMAIN_WARP_FRACTAL_LACUNARITY_HASH = 1740695150L
        @JvmField
        val getDomainWarpFractalLacunarityBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_fractal_lacunarity", GET_DOMAIN_WARP_FRACTAL_LACUNARITY_HASH)

        private const val SET_DOMAIN_WARP_FRACTAL_GAIN_HASH = 373806689L
        @JvmField
        val setDomainWarpFractalGainBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "set_domain_warp_fractal_gain", SET_DOMAIN_WARP_FRACTAL_GAIN_HASH)

        private const val GET_DOMAIN_WARP_FRACTAL_GAIN_HASH = 1740695150L
        @JvmField
        val getDomainWarpFractalGainBind =
            ObjectCalls.getMethodBind("FastNoiseLite", "get_domain_warp_fractal_gain", GET_DOMAIN_WARP_FRACTAL_GAIN_HASH)
    }
}
