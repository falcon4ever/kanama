package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RandomNumberGenerator (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RandomNumberGenerator waits on: ptrcallWithPackedFloat32ListArgRetLong
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns a random integer between `0` and the size of the array that is passed as a parameter.
 * Each value in the array should be a non-negative floating-point number that represents the
 * relative likelihood that it will be returned as an index. A higher value means the value is more
 * likely to be returned as an index, while a value of `0` means it will never be returned as an
 * index. For example, if `[0.5, 1, 1, 2]` is passed as a parameter, then the method is twice as
 * likely to return `3` (the index of the value `2`) and twice as unlikely to return `0` (the index
 * of the value `0.5`) compared to the indices `1` and `2`. Prints an error and returns `-1` if the
 * array is empty or contains any negative values.
 *
 * Generated from Godot docs: RandomNumberGenerator.rand_weighted
 */
fun RandomNumberGenerator.randWeighted(weights: List<Float>): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithPackedFloat32ListArgRetLong(randWeightedBind, handle, weights)
}

private const val RAND_WEIGHTED_HASH = 4189642986L
private val randWeightedBind by lazy {
    ObjectCalls.getMethodBind("RandomNumberGenerator", "rand_weighted", RAND_WEIGHTED_HASH)
}
