package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a high-pass filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectHighPassFilter
 */
class AudioEffectHighPassFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectHighPassFilter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectHighPassFilter? =
            if (handle.address() == 0L) null else AudioEffectHighPassFilter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
