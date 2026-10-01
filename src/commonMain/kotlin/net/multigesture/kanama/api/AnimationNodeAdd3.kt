package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Blends two of three animations additively inside of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeAdd3
 */
class AnimationNodeAdd3(handle: GodotHandle) : AnimationNodeSync(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeAdd3? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationNodeAdd3? =
            if (handle.address() == 0L) null else AnimationNodeAdd3(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
