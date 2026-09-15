package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Blends two animations additively inside of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeAdd2
 */
class AnimationNodeAdd2(handle: GodotHandle) : AnimationNodeSync(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeAdd2? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationNodeAdd2? =
            if (handle.address() == 0L) null else AnimationNodeAdd2(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
