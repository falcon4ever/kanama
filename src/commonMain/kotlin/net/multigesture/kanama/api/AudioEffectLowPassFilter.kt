package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a low-pass filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectLowPassFilter
 */
class AudioEffectLowPassFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectLowPassFilter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectLowPassFilter? =
            if (handle.address() == 0L) null else AudioEffectLowPassFilter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
