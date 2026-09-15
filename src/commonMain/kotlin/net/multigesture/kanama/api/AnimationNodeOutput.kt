package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * The animation output node of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeOutput
 */
class AnimationNodeOutput(handle: GodotHandle) : AnimationNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeOutput? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationNodeOutput? =
            if (handle.address() == 0L) null else AnimationNodeOutput(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
