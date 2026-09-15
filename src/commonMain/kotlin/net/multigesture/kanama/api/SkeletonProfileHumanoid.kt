package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A humanoid `SkeletonProfile` preset.
 *
 * Generated from Godot docs: SkeletonProfileHumanoid
 */
class SkeletonProfileHumanoid(handle: GodotHandle) : SkeletonProfile(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonProfileHumanoid? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SkeletonProfileHumanoid? =
            if (handle.address() == 0L) null else SkeletonProfileHumanoid(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
