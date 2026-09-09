package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Blends two of three animations linearly inside of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeBlend3
 */
class AnimationNodeBlend3(handle: MemorySegment) : AnimationNodeSync(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeBlend3? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeBlend3? =
            if (handle.address() == 0L) null else AnimationNodeBlend3(handle)

        // No MethodBinds emitted yet.
    }
}
