package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract class for all Tweeners used by `Tween`.
 *
 * Generated from Godot docs: Tweener
 */
open class Tweener(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    /** Signal `finished()`; see [TypedSignal]. */
    val finished: Signal0
        @JvmName("finishedTypedSignal")
        get() = Signal0(this, "finished")

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Tweener? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Tweener? =
            if (handle.address() == 0L) null else RefCounted.owned(Tweener(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Tweener? =
            if (handle.address() == 0L) null else Tweener(GodotHandle(handle))
    }
}
