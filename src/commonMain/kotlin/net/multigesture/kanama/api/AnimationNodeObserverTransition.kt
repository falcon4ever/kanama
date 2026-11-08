package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Observer for events from `AnimationNodeTransition` instances.
 *
 * Generated from Godot docs: AnimationNodeObserverTransition
 */
class AnimationNodeObserverTransition(handle: GodotHandle) : AnimationNodeObserver(handle) {
    // No conservative instance methods emitted yet.

    /** Signal `state_started(state: String)`; see [TypedSignal]. */
    val stateStarted: Signal1<String>
        @JvmName("stateStartedTypedSignal")
        get() = Signal1(this, "state_started", SignalArgType.STRING)

    /** Signal `state_finished(state: String)`; see [TypedSignal]. */
    val stateFinished: Signal1<String>
        @JvmName("stateFinishedTypedSignal")
        get() = Signal1(this, "state_finished", SignalArgType.STRING)

    object Signals {
        const val stateStarted: String = "state_started"
        const val stateFinished: String = "state_finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeObserverTransition? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeObserverTransition? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeObserverTransition(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeObserverTransition? =
            if (handle.address() == 0L) null else AnimationNodeObserverTransition(GodotHandle(handle))

        /** A new AnimationNodeObserverTransition, GDScript's `AnimationNodeObserverTransition.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AnimationNodeObserverTransition =
            RefCounted.owned(AnimationNodeObserverTransition(GodotHandle(requireConstructed(ObjectCalls.constructObject("AnimationNodeObserverTransition"), "AnimationNodeObserverTransition"))))
    }
}
