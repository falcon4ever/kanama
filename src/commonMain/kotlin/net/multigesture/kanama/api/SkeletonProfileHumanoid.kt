package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A humanoid `SkeletonProfile` preset.
 *
 * Generated from Godot docs: SkeletonProfileHumanoid
 */
class SkeletonProfileHumanoid(handle: MemorySegment) : SkeletonProfile(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonProfileHumanoid? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonProfileHumanoid? =
            if (handle.address() == 0L) null else SkeletonProfileHumanoid(handle)

        // No MethodBinds emitted yet.
    }
}
