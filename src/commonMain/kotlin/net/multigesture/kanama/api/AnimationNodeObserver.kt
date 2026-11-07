package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for observer types which relay `AnimationNode` events for a particular
 * `AnimationTree` node.
 *
 * Generated from Godot docs: AnimationNodeObserver
 */
open class AnimationNodeObserver(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeObserver? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeObserver? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeObserver(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeObserver? =
            if (handle.address() == 0L) null else AnimationNodeObserver(GodotHandle(handle))
    }
}
