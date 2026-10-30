package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * The animation output node of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeOutput
 */
class AnimationNodeOutput(handle: GodotHandle) : AnimationNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeOutput? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeOutput? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeOutput(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeOutput? =
            if (handle.address() == 0L) null else AnimationNodeOutput(GodotHandle(handle))

        /** A new AnimationNodeOutput, GDScript's `AnimationNodeOutput.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AnimationNodeOutput =
            RefCounted.owned(AnimationNodeOutput(GodotHandle(requireConstructed(ObjectCalls.constructObject("AnimationNodeOutput"), "AnimationNodeOutput"))))
    }
}
