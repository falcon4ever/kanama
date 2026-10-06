package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A time-scaling animation node used in `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeTimeScale
 */
class AnimationNodeTimeScale(handle: GodotHandle) : AnimationNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeTimeScale? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeTimeScale? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeTimeScale(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeTimeScale? =
            if (handle.address() == 0L) null else AnimationNodeTimeScale(GodotHandle(handle))
    }
}
