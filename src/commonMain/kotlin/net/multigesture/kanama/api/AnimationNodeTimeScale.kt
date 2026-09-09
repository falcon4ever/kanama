package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A time-scaling animation node used in `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeTimeScale
 */
class AnimationNodeTimeScale(handle: MemorySegment) : AnimationNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeTimeScale? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeTimeScale? =
            if (handle.address() == 0L) null else AnimationNodeTimeScale(handle)

        // No MethodBinds emitted yet.
    }
}
