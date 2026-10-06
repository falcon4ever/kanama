package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Creates an idle interval in a `Tween` animation.
 *
 * Generated from Godot docs: IntervalTweener
 */
class IntervalTweener(handle: GodotHandle) : Tweener(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): IntervalTweener? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): IntervalTweener? =
            if (handle.address() == 0L) null else RefCounted.owned(IntervalTweener(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): IntervalTweener? =
            if (handle.address() == 0L) null else IntervalTweener(GodotHandle(handle))
    }
}
