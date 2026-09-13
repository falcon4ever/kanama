package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a high-shelf filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectHighShelfFilter
 */
class AudioEffectHighShelfFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectHighShelfFilter? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): AudioEffectHighShelfFilter? =
            if (handle.address() == 0L) null else AudioEffectHighShelfFilter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
