package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Observer for events from `AnimationNodeOneShot` instances.
 *
 * Generated from Godot docs: AnimationNodeObserverOneShot
 */
class AnimationNodeObserverOneShot(handle: GodotHandle) : AnimationNodeObserver(handle) {
    // No conservative instance methods emitted yet.

    /** Signal `started()`; see [TypedSignal]. */
    val started: Signal0
        @JvmName("startedTypedSignal")
        get() = Signal0(this, "started")

    /** Signal `fade_in_finished()`; see [TypedSignal]. */
    val fadeInFinished: Signal0
        @JvmName("fadeInFinishedTypedSignal")
        get() = Signal0(this, "fade_in_finished")

    /** Signal `fade_out_started()`; see [TypedSignal]. */
    val fadeOutStarted: Signal0
        @JvmName("fadeOutStartedTypedSignal")
        get() = Signal0(this, "fade_out_started")

    /** Signal `finished()`; see [TypedSignal]. */
    val finished: Signal0
        @JvmName("finishedTypedSignal")
        get() = Signal0(this, "finished")

    object Signals {
        const val started: String = "started"
        const val fadeInFinished: String = "fade_in_finished"
        const val fadeOutStarted: String = "fade_out_started"
        const val finished: String = "finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeObserverOneShot? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeObserverOneShot? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeObserverOneShot(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeObserverOneShot? =
            if (handle.address() == 0L) null else AnimationNodeObserverOneShot(GodotHandle(handle))

        /** A new AnimationNodeObserverOneShot, GDScript's `AnimationNodeObserverOneShot.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AnimationNodeObserverOneShot =
            RefCounted.owned(AnimationNodeObserverOneShot(GodotHandle(requireConstructed(ObjectCalls.constructObject("AnimationNodeObserverOneShot"), "AnimationNodeObserverOneShot"))))
    }
}
