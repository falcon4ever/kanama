package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for extending `AnimationRootNode`s from GDScript, C#, or C++.
 *
 * Generated from Godot docs: AnimationNodeExtension
 */
class AnimationNodeExtension(handle: GodotHandle) : AnimationNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        /**
         * Returns `true` if the animation for the given `node_info` is looping.
         *
         * Generated from Godot docs: AnimationNodeExtension.is_looping
         */
        fun isLooping(nodeInfo: List<Float>): Boolean {
            return ObjectCalls.ptrcallWithPackedFloat32ListArgRetBool(isLoopingBind, NULL_SEGMENT, nodeInfo)
        }

        /**
         * Returns the animation's remaining time for the given node info. For looping animations, it will
         * only return the remaining time if `break_loop` is `true`, a large integer value will be returned
         * otherwise.
         *
         * Generated from Godot docs: AnimationNodeExtension.get_remaining_time
         */
        fun getRemainingTime(nodeInfo: List<Float>, breakLoop: Boolean): Double {
            return ObjectCalls.ptrcallWithPackedFloat32ListAndBoolArgRetDouble(getRemainingTimeBind, NULL_SEGMENT, nodeInfo, breakLoop)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationNodeExtension? =
            if (handle.address() == 0L) null else AnimationNodeExtension(GodotHandle(handle))

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
