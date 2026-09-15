package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Blends two animations linearly inside of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeBlend2
 */
class AnimationNodeBlend2(handle: GodotHandle) : AnimationNodeSync(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeBlend2? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationNodeBlend2? =
            if (handle.address() == 0L) null else AnimationNodeBlend2(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
