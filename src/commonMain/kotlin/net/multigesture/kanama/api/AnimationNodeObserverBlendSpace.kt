package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Observer for events from `AnimationNodeBlendSpace1D` and `AnimationNodeBlendSpace2D` instances.
 *
 * Generated from Godot docs: AnimationNodeObserverBlendSpace
 */
class AnimationNodeObserverBlendSpace(handle: GodotHandle) : AnimationNodeObserver(handle) {
    // No conservative instance methods emitted yet.

    /** Signal `closest_point_changed(closest_point_name: StringName)`; see [TypedSignal]. */
    val closestPointChanged: Signal1<String>
        @JvmName("closestPointChangedTypedSignal")
        get() = Signal1(this, "closest_point_changed", SignalArgType.STRING)

    object Signals {
        const val closestPointChanged: String = "closest_point_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeObserverBlendSpace? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeObserverBlendSpace? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeObserverBlendSpace(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeObserverBlendSpace? =
            if (handle.address() == 0L) null else AnimationNodeObserverBlendSpace(GodotHandle(handle))

        /** A new AnimationNodeObserverBlendSpace, GDScript's `AnimationNodeObserverBlendSpace.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AnimationNodeObserverBlendSpace =
            RefCounted.owned(AnimationNodeObserverBlendSpace(GodotHandle(requireConstructed(ObjectCalls.constructObject("AnimationNodeObserverBlendSpace"), "AnimationNodeObserverBlendSpace"))))
    }
}
