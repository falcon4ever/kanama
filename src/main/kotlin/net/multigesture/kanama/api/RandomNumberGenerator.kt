package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides methods for generating pseudo-random numbers.
 *
 * Generated from Godot docs: RandomNumberGenerator
 */
class RandomNumberGenerator(handle: MemorySegment) : RefCounted(handle) {
    var seed: Long
        @JvmName("seedProperty")
        get() = getSeed()
        @JvmName("setSeedProperty")
        set(value) = setSeed(value)

    var state: Long
        @JvmName("stateProperty")
        get() = getState()
        @JvmName("setStateProperty")
        set(value) = setState(value)

    fun setSeed(seed: Long) {
        ObjectCalls.ptrcallWithLongArg(setSeedBind, handle, seed)
    }

    fun getSeed(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSeedBind, handle)
    }

    fun setState(state: Long) {
        ObjectCalls.ptrcallWithLongArg(setStateBind, handle, state)
    }

    fun getState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStateBind, handle)
    }

    /**
     * Returns a pseudo-random 32-bit unsigned integer between `0` and `4294967295` (inclusive).
     *
     * Generated from Godot docs: RandomNumberGenerator.randi
     */
    fun randi(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(randiBind, handle)
    }

    /**
     * Returns a pseudo-random float between `0.0` and `1.0` (inclusive).
     *
     * Generated from Godot docs: RandomNumberGenerator.randf
     */
    fun randf(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(randfBind, handle)
    }

    /**
     * Returns a normally-distributed (https://en.wikipedia.org/wiki/Normal_distribution),
     * pseudo-random floating-point number from the specified `mean` and a standard `deviation`. This
     * is also known as a Gaussian distribution. Note: This method uses the Box-Muller transform
     * (https://en.wikipedia.org/wiki/Box%E2%80%93Muller_transform) algorithm.
     *
     * Generated from Godot docs: RandomNumberGenerator.randfn
     */
    fun randfn(mean: Double = 0.0, deviation: Double = 1.0): Double {
        return ObjectCalls.ptrcallWithTwoDoubleArgsRetDouble(randfnBind, handle, mean, deviation)
    }

    fun randfRange(from: Double, to: Double): Double {
        return ObjectCalls.ptrcallWithTwoDoubleArgsRetDouble(randfRangeBind, handle, from, to)
    }

    fun randiRange(from: Int, to: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(randiRangeBind, handle, from, to)
    }

    fun randWeighted(weights: List<Float>): Long {
        return ObjectCalls.ptrcallWithPackedFloat32ListArgRetLong(randWeightedBind, handle, weights)
    }

    /**
     * Sets up a time-based seed for this `RandomNumberGenerator` instance. Unlike the `@GlobalScope`
     * random number generation functions, different `RandomNumberGenerator` instances can use
     * different seeds.
     *
     * Generated from Godot docs: RandomNumberGenerator.randomize
     */
    fun randomize() {
        ObjectCalls.ptrcallNoArgs(randomizeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RandomNumberGenerator? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RandomNumberGenerator? =
            if (handle.address() == 0L) null else RandomNumberGenerator(handle)

        private const val SET_SEED_HASH = 1286410249L
        private val setSeedBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "set_seed", SET_SEED_HASH)
        }

        private const val GET_SEED_HASH = 2455072627L
        private val getSeedBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "get_seed", GET_SEED_HASH)
        }

        private const val SET_STATE_HASH = 1286410249L
        private val setStateBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "set_state", SET_STATE_HASH)
        }

        private const val GET_STATE_HASH = 3905245786L
        private val getStateBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "get_state", GET_STATE_HASH)
        }

        private const val RANDI_HASH = 2455072627L
        private val randiBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "randi", RANDI_HASH)
        }

        private const val RANDF_HASH = 191475506L
        private val randfBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "randf", RANDF_HASH)
        }

        private const val RANDFN_HASH = 837325100L
        private val randfnBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "randfn", RANDFN_HASH)
        }

        private const val RANDF_RANGE_HASH = 4269894367L
        private val randfRangeBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "randf_range", RANDF_RANGE_HASH)
        }

        private const val RANDI_RANGE_HASH = 50157827L
        private val randiRangeBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "randi_range", RANDI_RANGE_HASH)
        }

        private const val RAND_WEIGHTED_HASH = 4189642986L
        private val randWeightedBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "rand_weighted", RAND_WEIGHTED_HASH)
        }

        private const val RANDOMIZE_HASH = 3218959716L
        private val randomizeBind by lazy {
            ObjectCalls.getMethodBind("RandomNumberGenerator", "randomize", RANDOMIZE_HASH)
        }
    }
}
