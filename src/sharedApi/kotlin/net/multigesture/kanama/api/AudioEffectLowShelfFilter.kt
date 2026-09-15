package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a low-shelf filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectLowShelfFilter
 */
class AudioEffectLowShelfFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectLowShelfFilter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectLowShelfFilter? =
            if (handle.address() == 0L) null else AudioEffectLowShelfFilter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
