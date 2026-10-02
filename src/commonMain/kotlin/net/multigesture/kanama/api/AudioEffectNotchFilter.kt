package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a notch filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectNotchFilter
 */
class AudioEffectNotchFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectNotchFilter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectNotchFilter? =
            if (handle.address() == 0L) null else AudioEffectNotchFilter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
