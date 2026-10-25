package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Blends two animations additively inside of an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeAdd2
 */
class AnimationNodeAdd2(handle: GodotHandle) : AnimationNodeSync(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeAdd2? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeAdd2? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeAdd2(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeAdd2? =
            if (handle.address() == 0L) null else AnimationNodeAdd2(GodotHandle(handle))

        /** A new AnimationNodeAdd2, GDScript's `AnimationNodeAdd2.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AnimationNodeAdd2 =
            RefCounted.owned(AnimationNodeAdd2(GodotHandle(requireConstructed(ObjectCalls.constructObject("AnimationNodeAdd2"), "AnimationNodeAdd2"))))
    }
}
