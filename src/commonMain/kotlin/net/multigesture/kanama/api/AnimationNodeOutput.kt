package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * The animation output node of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeOutput
 */
class AnimationNodeOutput(handle: MemorySegment) : AnimationNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeOutput? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeOutput? =
            if (handle.address() == 0L) null else AnimationNodeOutput(handle)

        // No MethodBinds emitted yet.
    }
}
