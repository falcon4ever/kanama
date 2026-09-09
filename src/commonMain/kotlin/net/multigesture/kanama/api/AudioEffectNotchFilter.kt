package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a notch filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectNotchFilter
 */
class AudioEffectNotchFilter(handle: MemorySegment) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectNotchFilter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectNotchFilter? =
            if (handle.address() == 0L) null else AudioEffectNotchFilter(handle)

        // No MethodBinds emitted yet.
    }
}
