package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Vector3

/**
 * Generates noise using the FastNoiseLite library.
 *
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

    /**
     * The noise algorithm used.
     *
     * Generated from Godot docs: FastNoiseLite.set_noise_type
     */
    fun setNoiseType(type: FastNoiseLite.NoiseType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setNoiseTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The noise algorithm used.
     *
     * Generated from Godot docs: FastNoiseLite.get_noise_type
     */
    fun getNoiseType(): FastNoiseLite.NoiseType {
        checkOpen()
        val ret = FastNoiseLite.NoiseType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getNoiseTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The random number seed for all noise types.
     *
     * Generated from Godot docs: FastNoiseLite.set_seed
     */
    fun setSeed(seed: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSeedBind, segment, seed)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The random number seed for all noise types.
     *
     * Generated from Godot docs: FastNoiseLite.get_seed
     */
    fun getSeed(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSeedBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The frequency for all noise types. Low frequency results in smooth noise while high frequency
     * results in rougher, more granular noise.
     *
     * Generated from Godot docs: FastNoiseLite.set_frequency
     */
    fun setFrequency(freq: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFrequencyBind, segment, freq)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The frequency for all noise types. Low frequency results in smooth noise while high frequency
     * results in rougher, more granular noise.
     *
     * Generated from Godot docs: FastNoiseLite.get_frequency
     */
    fun getFrequency(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFrequencyBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Translate the noise input coordinates by the given `Vector3`.
     *
     * Generated from Godot docs: FastNoiseLite.set_offset
     */
    fun setOffset(offset: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setOffsetBind, segment, offset)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Translate the noise input coordinates by the given `Vector3`.
     *
     * Generated from Godot docs: FastNoiseLite.get_offset
     */
    fun getOffset(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The method for combining octaves into a fractal.
     *
     * Generated from Godot docs: FastNoiseLite.set_fractal_type
     */
    fun setFractalType(type: FastNoiseLite.FractalType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFractalTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The method for combining octaves into a fractal.
     *
     * Generated from Godot docs: FastNoiseLite.get_fractal_type
     */
    fun getFractalType(): FastNoiseLite.FractalType {
        checkOpen()
        val ret = FastNoiseLite.FractalType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFractalTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The number of noise layers that are sampled to get the final value for fractal noise types.
     *
     * Generated from Godot docs: FastNoiseLite.set_fractal_octaves
     */
    fun setFractalOctaves(octaveCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFractalOctavesBind, segment, octaveCount)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The number of noise layers that are sampled to get the final value for fractal noise types.
     *
     * Generated from Godot docs: FastNoiseLite.get_fractal_octaves
     */
    fun getFractalOctaves(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getFractalOctavesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Frequency multiplier between subsequent octaves. Increasing this value results in higher octaves
     * producing noise with finer details and a rougher appearance.
     *
     * Generated from Godot docs: FastNoiseLite.set_fractal_lacunarity
     */
    fun setFractalLacunarity(lacunarity: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFractalLacunarityBind, segment, lacunarity)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Frequency multiplier between subsequent octaves. Increasing this value results in higher octaves
     * producing noise with finer details and a rougher appearance.
     *
     * Generated from Godot docs: FastNoiseLite.get_fractal_lacunarity
     */
    fun getFractalLacunarity(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFractalLacunarityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Determines the strength of each subsequent layer of noise in fractal noise. A low value places
     * more emphasis on the lower frequency base layers, while a high value puts more emphasis on the
     * higher frequency layers.
     *
     * Generated from Godot docs: FastNoiseLite.set_fractal_gain
     */
    fun setFractalGain(gain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFractalGainBind, segment, gain)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Determines the strength of each subsequent layer of noise in fractal noise. A low value places
     * more emphasis on the lower frequency base layers, while a high value puts more emphasis on the
     * higher frequency layers.
     *
     * Generated from Godot docs: FastNoiseLite.get_fractal_gain
     */
    fun getFractalGain(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFractalGainBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Higher weighting means higher octaves have less impact if lower octaves have a large impact.
     *
     * Generated from Godot docs: FastNoiseLite.set_fractal_weighted_strength
     */
    fun setFractalWeightedStrength(weightedStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFractalWeightedStrengthBind, segment, weightedStrength)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Higher weighting means higher octaves have less impact if lower octaves have a large impact.
     *
     * Generated from Godot docs: FastNoiseLite.get_fractal_weighted_strength
     */
    fun getFractalWeightedStrength(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFractalWeightedStrengthBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the strength of the fractal ping pong type.
     *
     * Generated from Godot docs: FastNoiseLite.set_fractal_ping_pong_strength
     */
    fun setFractalPingPongStrength(pingPongStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFractalPingPongStrengthBind, segment, pingPongStrength)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the strength of the fractal ping pong type.
     *
     * Generated from Godot docs: FastNoiseLite.get_fractal_ping_pong_strength
     */
    fun getFractalPingPongStrength(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFractalPingPongStrengthBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Determines how the distance to the nearest/second-nearest point is computed.
     *
     * Generated from Godot docs: FastNoiseLite.set_cellular_distance_function
     */
    fun setCellularDistanceFunction(func: FastNoiseLite.CellularDistanceFunction) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCellularDistanceFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Determines how the distance to the nearest/second-nearest point is computed.
     *
     * Generated from Godot docs: FastNoiseLite.get_cellular_distance_function
     */
    fun getCellularDistanceFunction(): FastNoiseLite.CellularDistanceFunction {
        checkOpen()
        val ret = FastNoiseLite.CellularDistanceFunction(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCellularDistanceFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Maximum distance a point can move off of its grid position. Set to `0` for an even grid.
     *
     * Generated from Godot docs: FastNoiseLite.set_cellular_jitter
     */
    fun setCellularJitter(jitter: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCellularJitterBind, segment, jitter)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Maximum distance a point can move off of its grid position. Set to `0` for an even grid.
     *
     * Generated from Godot docs: FastNoiseLite.get_cellular_jitter
     */
    fun getCellularJitter(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCellularJitterBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Return type from cellular noise calculations.
     *
     * Generated from Godot docs: FastNoiseLite.set_cellular_return_type
     */
    fun setCellularReturnType(ret: FastNoiseLite.CellularReturnType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCellularReturnTypeBind, segment, ret.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Return type from cellular noise calculations.
     *
     * Generated from Godot docs: FastNoiseLite.get_cellular_return_type
     */
    fun getCellularReturnType(): FastNoiseLite.CellularReturnType {
        checkOpen()
        val ret = FastNoiseLite.CellularReturnType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCellularReturnTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If enabled, another FastNoiseLite instance is used to warp the space, resulting in a distortion
     * of the noise.
     *
     * Generated from Godot docs: FastNoiseLite.set_domain_warp_enabled
     */
    fun setDomainWarpEnabled(domainWarpEnabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDomainWarpEnabledBind, segment, domainWarpEnabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If enabled, another FastNoiseLite instance is used to warp the space, resulting in a distortion
     * of the noise.
     *
     * Generated from Godot docs: FastNoiseLite.is_domain_warp_enabled
     */
    fun isDomainWarpEnabled(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isDomainWarpEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The warp algorithm.
     *
     * Generated from Godot docs: FastNoiseLite.set_domain_warp_type
     */
    fun setDomainWarpType(domainWarpType: FastNoiseLite.DomainWarpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDomainWarpTypeBind, segment, domainWarpType.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The warp algorithm.
     *
     * Generated from Godot docs: FastNoiseLite.get_domain_warp_type
     */
    fun getDomainWarpType(): FastNoiseLite.DomainWarpType {
        checkOpen()
        val ret = FastNoiseLite.DomainWarpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDomainWarpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the maximum warp distance from the origin.
     *
     * Generated from Godot docs: FastNoiseLite.set_domain_warp_amplitude
     */
    fun setDomainWarpAmplitude(domainWarpAmplitude: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDomainWarpAmplitudeBind, segment, domainWarpAmplitude)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the maximum warp distance from the origin.
     *
     * Generated from Godot docs: FastNoiseLite.get_domain_warp_amplitude
     */
    fun getDomainWarpAmplitude(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainWarpAmplitudeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Frequency of the noise which warps the space. Low frequency results in smooth noise while high
     * frequency results in rougher, more granular noise.
     *
     * Generated from Godot docs: FastNoiseLite.set_domain_warp_frequency
     */
    fun setDomainWarpFrequency(domainWarpFrequency: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDomainWarpFrequencyBind, segment, domainWarpFrequency)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Frequency of the noise which warps the space. Low frequency results in smooth noise while high
     * frequency results in rougher, more granular noise.
     *
     * Generated from Godot docs: FastNoiseLite.get_domain_warp_frequency
     */
    fun getDomainWarpFrequency(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainWarpFrequencyBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The method for combining octaves into a fractal which is used to warp the space.
     *
     * Generated from Godot docs: FastNoiseLite.set_domain_warp_fractal_type
     */
    fun setDomainWarpFractalType(domainWarpFractalType: FastNoiseLite.DomainWarpFractalType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDomainWarpFractalTypeBind, segment, domainWarpFractalType.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The method for combining octaves into a fractal which is used to warp the space.
     *
     * Generated from Godot docs: FastNoiseLite.get_domain_warp_fractal_type
     */
    fun getDomainWarpFractalType(): FastNoiseLite.DomainWarpFractalType {
        checkOpen()
        val ret = FastNoiseLite.DomainWarpFractalType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDomainWarpFractalTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The number of noise layers that are sampled to get the final value for the fractal noise which
     * warps the space.
     *
     * Generated from Godot docs: FastNoiseLite.set_domain_warp_fractal_octaves
     */
    fun setDomainWarpFractalOctaves(domainWarpOctaveCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDomainWarpFractalOctavesBind, segment, domainWarpOctaveCount)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The number of noise layers that are sampled to get the final value for the fractal noise which
     * warps the space.
     *
     * Generated from Godot docs: FastNoiseLite.get_domain_warp_fractal_octaves
     */
    fun getDomainWarpFractalOctaves(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getDomainWarpFractalOctavesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The change in frequency between octaves, also known as "lacunarity", of the fractal noise which
     * warps the space. Increasing this value results in higher octaves, producing noise with finer
     * details and a rougher appearance.
     *
     * Generated from Godot docs: FastNoiseLite.set_domain_warp_fractal_lacunarity
     */
    fun setDomainWarpFractalLacunarity(domainWarpLacunarity: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDomainWarpFractalLacunarityBind, segment, domainWarpLacunarity)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The change in frequency between octaves, also known as "lacunarity", of the fractal noise which
     * warps the space. Increasing this value results in higher octaves, producing noise with finer
     * details and a rougher appearance.
     *
     * Generated from Godot docs: FastNoiseLite.get_domain_warp_fractal_lacunarity
     */
    fun getDomainWarpFractalLacunarity(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainWarpFractalLacunarityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Determines the strength of each subsequent layer of the noise which is used to warp the space. A
     * low value places more emphasis on the lower frequency base layers, while a high value puts more
     * emphasis on the higher frequency layers.
     *
     * Generated from Godot docs: FastNoiseLite.set_domain_warp_fractal_gain
     */
    fun setDomainWarpFractalGain(domainWarpGain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDomainWarpFractalGainBind, segment, domainWarpGain)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Determines the strength of each subsequent layer of the noise which is used to warp the space. A
     * low value places more emphasis on the lower frequency base layers, while a high value puts more
     * emphasis on the higher frequency layers.
     *
     * Generated from Godot docs: FastNoiseLite.get_domain_warp_fractal_gain
     */
    fun getDomainWarpFractalGain(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDomainWarpFractalGainBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `FastNoiseLite.NoiseType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`FastNoiseLite.NoiseType.<NAME>`).
     *
     * Generated from Godot docs: FastNoiseLite.NoiseType
     */
    @JvmInline
    value class NoiseType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A lattice of points are assigned random values then interpolated based on neighboring values.
             *
             * Generated from Godot docs: FastNoiseLite.TYPE_VALUE
             */
            val VALUE: NoiseType get() = NoiseType(5L)
            /**
             * Similar to value noise (`NoiseType.VALUE`), but slower. Has more variance in peaks and valleys.
             * Cubic noise can be used to avoid certain artifacts when using value noise to create a bumpmap.
             * In general, you should always use this mode if the value noise is being used for a heightmap or
             * bumpmap.
             *
             * Generated from Godot docs: FastNoiseLite.TYPE_VALUE_CUBIC
             */
            val VALUE_CUBIC: NoiseType get() = NoiseType(4L)
            /**
             * A lattice of random gradients. Their dot products are interpolated to obtain values in between
             * the lattices.
             *
             * Generated from Godot docs: FastNoiseLite.TYPE_PERLIN
             */
            val PERLIN: NoiseType get() = NoiseType(3L)
            /**
             * Cellular includes both Worley noise and Voronoi diagrams which creates various regions of the
             * same value.
             *
             * Generated from Godot docs: FastNoiseLite.TYPE_CELLULAR
             */
            val CELLULAR: NoiseType get() = NoiseType(2L)
            /**
             * As opposed to `NoiseType.PERLIN`, gradients exist in a simplex lattice rather than a grid
             * lattice, avoiding directional artifacts. Internally uses FastNoiseLite's OpenSimplex2 noise
             * type.
             *
             * Generated from Godot docs: FastNoiseLite.TYPE_SIMPLEX
             */
            val SIMPLEX: NoiseType get() = NoiseType(0L)
            /**
             * Modified, higher quality version of `NoiseType.SIMPLEX`, but slower. Internally uses
             * FastNoiseLite's OpenSimplex2S noise type.
             *
             * Generated from Godot docs: FastNoiseLite.TYPE_SIMPLEX_SMOOTH
             */
            val SIMPLEX_SMOOTH: NoiseType get() = NoiseType(1L)
        }
    }

    /**
     * Godot's `FastNoiseLite.FractalType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`FastNoiseLite.FractalType.<NAME>`).
     *
     * Generated from Godot docs: FastNoiseLite.FractalType
     */
    @JvmInline
    value class FractalType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No fractal noise.
             *
             * Generated from Godot docs: FastNoiseLite.FRACTAL_NONE
             */
            val NONE: FractalType get() = FractalType(0L)
            /**
             * Method using Fractional Brownian Motion to combine octaves into a fractal.
             *
             * Generated from Godot docs: FastNoiseLite.FRACTAL_FBM
             */
            val FBM: FractalType get() = FractalType(1L)
            /**
             * Method of combining octaves into a fractal resulting in a "ridged" look.
             *
             * Generated from Godot docs: FastNoiseLite.FRACTAL_RIDGED
             */
            val RIDGED: FractalType get() = FractalType(2L)
            /**
             * Method of combining octaves into a fractal with a ping pong effect.
             *
             * Generated from Godot docs: FastNoiseLite.FRACTAL_PING_PONG
             */
            val PING_PONG: FractalType get() = FractalType(3L)
        }
    }

    /**
     * Godot's `FastNoiseLite.CellularDistanceFunction` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`FastNoiseLite.CellularDistanceFunction.<NAME>`).
     *
     * Generated from Godot docs: FastNoiseLite.CellularDistanceFunction
     */
    @JvmInline
    value class CellularDistanceFunction(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Euclidean distance to the nearest point.
             *
             * Generated from Godot docs: FastNoiseLite.DISTANCE_EUCLIDEAN
             */
            val EUCLIDEAN: CellularDistanceFunction get() = CellularDistanceFunction(0L)
            /**
             * Squared Euclidean distance to the nearest point.
             *
             * Generated from Godot docs: FastNoiseLite.DISTANCE_EUCLIDEAN_SQUARED
             */
            val EUCLIDEAN_SQUARED: CellularDistanceFunction get() = CellularDistanceFunction(1L)
            /**
             * Manhattan distance (taxicab metric) to the nearest point.
             *
             * Generated from Godot docs: FastNoiseLite.DISTANCE_MANHATTAN
             */
            val MANHATTAN: CellularDistanceFunction get() = CellularDistanceFunction(2L)
            /**
             * Blend of `CellularDistanceFunction.EUCLIDEAN` and `CellularDistanceFunction.MANHATTAN` to give
             * curved cell boundaries.
             *
             * Generated from Godot docs: FastNoiseLite.DISTANCE_HYBRID
             */
            val HYBRID: CellularDistanceFunction get() = CellularDistanceFunction(3L)
        }
    }

    /**
     * Godot's `FastNoiseLite.CellularReturnType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`FastNoiseLite.CellularReturnType.<NAME>`).
     *
     * Generated from Godot docs: FastNoiseLite.CellularReturnType
     */
    @JvmInline
    value class CellularReturnType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The cellular distance function will return the same value for all points within a cell.
             *
             * Generated from Godot docs: FastNoiseLite.RETURN_CELL_VALUE
             */
            val CELL_VALUE: CellularReturnType get() = CellularReturnType(0L)
            /**
             * The cellular distance function will return a value determined by the distance to the nearest
             * point.
             *
             * Generated from Godot docs: FastNoiseLite.RETURN_DISTANCE
             */
            val DISTANCE: CellularReturnType get() = CellularReturnType(1L)
            /**
             * The cellular distance function returns the distance to the second-nearest point.
             *
             * Generated from Godot docs: FastNoiseLite.RETURN_DISTANCE2
             */
            val DISTANCE2: CellularReturnType get() = CellularReturnType(2L)
            /**
             * The distance to the nearest point is added to the distance to the second-nearest point.
             *
             * Generated from Godot docs: FastNoiseLite.RETURN_DISTANCE2_ADD
             */
            val DISTANCE2_ADD: CellularReturnType get() = CellularReturnType(3L)
            /**
             * The distance to the nearest point is subtracted from the distance to the second-nearest point.
             *
             * Generated from Godot docs: FastNoiseLite.RETURN_DISTANCE2_SUB
             */
            val DISTANCE2_SUB: CellularReturnType get() = CellularReturnType(4L)
            /**
             * The distance to the nearest point is multiplied with the distance to the second-nearest point.
             *
             * Generated from Godot docs: FastNoiseLite.RETURN_DISTANCE2_MUL
             */
            val DISTANCE2_MUL: CellularReturnType get() = CellularReturnType(5L)
            /**
             * The distance to the nearest point is divided by the distance to the second-nearest point.
             *
             * Generated from Godot docs: FastNoiseLite.RETURN_DISTANCE2_DIV
             */
            val DISTANCE2_DIV: CellularReturnType get() = CellularReturnType(6L)
        }
    }

    /**
     * Godot's `FastNoiseLite.DomainWarpType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`FastNoiseLite.DomainWarpType.<NAME>`).
     *
     * Generated from Godot docs: FastNoiseLite.DomainWarpType
     */
    @JvmInline
    value class DomainWarpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The domain is warped using the simplex noise algorithm.
             *
             * Generated from Godot docs: FastNoiseLite.DOMAIN_WARP_SIMPLEX
             */
            val SIMPLEX: DomainWarpType get() = DomainWarpType(0L)
            /**
             * The domain is warped using a simplified version of the simplex noise algorithm.
             *
             * Generated from Godot docs: FastNoiseLite.DOMAIN_WARP_SIMPLEX_REDUCED
             */
            val SIMPLEX_REDUCED: DomainWarpType get() = DomainWarpType(1L)
            /**
             * The domain is warped using a simple noise grid (not as smooth as the other methods, but more
             * performant).
             *
             * Generated from Godot docs: FastNoiseLite.DOMAIN_WARP_BASIC_GRID
             */
            val BASIC_GRID: DomainWarpType get() = DomainWarpType(2L)
        }
    }

    /**
     * Godot's `FastNoiseLite.DomainWarpFractalType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`FastNoiseLite.DomainWarpFractalType.<NAME>`).
     *
     * Generated from Godot docs: FastNoiseLite.DomainWarpFractalType
     */
    @JvmInline
    value class DomainWarpFractalType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No fractal noise for warping the space.
             *
             * Generated from Godot docs: FastNoiseLite.DOMAIN_WARP_FRACTAL_NONE
             */
            val NONE: DomainWarpFractalType get() = DomainWarpFractalType(0L)
            /**
             * Warping the space progressively, octave for octave, resulting in a more "liquified" distortion.
             *
             * Generated from Godot docs: FastNoiseLite.DOMAIN_WARP_FRACTAL_PROGRESSIVE
             */
            val PROGRESSIVE: DomainWarpFractalType get() = DomainWarpFractalType(1L)
            /**
             * Warping the space independently for each octave, resulting in a more chaotic distortion.
             *
             * Generated from Godot docs: FastNoiseLite.DOMAIN_WARP_FRACTAL_INDEPENDENT
             */
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

        /** A new FastNoiseLite, GDScript's `FastNoiseLite.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): FastNoiseLite =
            RefCounted.owned(FastNoiseLite(GodotHandle(requireConstructed(ObjectCalls.constructObject("FastNoiseLite"), "FastNoiseLite"))))

        // Downcast a Resource to FastNoiseLite (null if not).
        @JvmStatic
        fun fromResource(value: Resource): FastNoiseLite? =
            if (value.isClass("FastNoiseLite")) RefCounted.retained(FastNoiseLite(value.handle), value) else null
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
