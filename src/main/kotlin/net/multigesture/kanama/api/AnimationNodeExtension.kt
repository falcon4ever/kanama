package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for extending `AnimationRootNode`s from GDScript, C#, or C++.
 *
 * Generated from Godot docs: AnimationNodeExtension
 */
class AnimationNodeExtension(handle: MemorySegment) : AnimationNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        fun isLooping(nodeInfo: List<Float>): Boolean {
            return ObjectCalls.ptrcallWithPackedFloat32ListArgRetBool(isLoopingBind, MemorySegment.NULL, nodeInfo)
        }

        fun getRemainingTime(nodeInfo: List<Float>, breakLoop: Boolean): Double {
            return ObjectCalls.ptrcallWithPackedFloat32ListAndBoolArgRetDouble(getRemainingTimeBind, MemorySegment.NULL, nodeInfo, breakLoop)
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeExtension? =
            if (handle.address() == 0L) null else AnimationNodeExtension(handle)

        private const val IS_LOOPING_HASH = 2035584311L
        private val isLoopingBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeExtension", "is_looping", IS_LOOPING_HASH)
        }

        private const val GET_REMAINING_TIME_HASH = 2851904656L
        private val getRemainingTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeExtension", "get_remaining_time", GET_REMAINING_TIME_HASH)
        }
    }
}
