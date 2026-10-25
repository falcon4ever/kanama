package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Blends two animations subtractively inside of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeSub2
 */
class AnimationNodeSub2(handle: GodotHandle) : AnimationNodeSync(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeSub2? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeSub2? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeSub2(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeSub2? =
            if (handle.address() == 0L) null else AnimationNodeSub2(GodotHandle(handle))

        /** A new AnimationNodeSub2, GDScript's `AnimationNodeSub2.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AnimationNodeSub2 =
            RefCounted.owned(AnimationNodeSub2(GodotHandle(requireConstructed(ObjectCalls.constructObject("AnimationNodeSub2"), "AnimationNodeSub2"))))
    }
}
