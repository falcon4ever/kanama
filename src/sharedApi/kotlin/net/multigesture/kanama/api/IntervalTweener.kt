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
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): IntervalTweener? =
            if (handle.address() == 0L) null else IntervalTweener(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
