package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AnimationNodeExtension (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AnimationNodeExtension waits on: ptrcallWithPackedFloat32ListAndBoolArgRetDouble,
//   ptrcallWithPackedFloat32ListArgRetBool
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns `true` if the animation for the given `node_info` is looping.
 *
 * Generated from Godot docs: AnimationNodeExtension.is_looping
 */
fun AnimationNodeExtension.Companion.isLooping(nodeInfo: List<Float>): Boolean {
    return ObjectCalls.ptrcallWithPackedFloat32ListArgRetBool(isLoopingBind, MemorySegment.NULL, nodeInfo)
}

/**
 * Returns the animation's remaining time for the given node info. For looping animations, it will
 * only return the remaining time if `break_loop` is `true`, a large integer value will be returned
 * otherwise.
 *
 * Generated from Godot docs: AnimationNodeExtension.get_remaining_time
 */
fun AnimationNodeExtension.Companion.getRemainingTime(nodeInfo: List<Float>, breakLoop: Boolean): Double {
    return ObjectCalls.ptrcallWithPackedFloat32ListAndBoolArgRetDouble(getRemainingTimeBind, MemorySegment.NULL, nodeInfo, breakLoop)
}

private const val IS_LOOPING_HASH = 2035584311L
private val isLoopingBind by lazy {
    ObjectCalls.getMethodBind("AnimationNodeExtension", "is_looping", IS_LOOPING_HASH)
}

private const val GET_REMAINING_TIME_HASH = 2851904656L
private val getRemainingTimeBind by lazy {
    ObjectCalls.getMethodBind("AnimationNodeExtension", "get_remaining_time", GET_REMAINING_TIME_HASH)
}
