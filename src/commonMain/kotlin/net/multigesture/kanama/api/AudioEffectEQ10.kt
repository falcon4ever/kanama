package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a 10-band equalizer audio effect to an audio bus. Gives you control over frequencies from
 * 31 Hz to 16000 Hz. Each frequency can be modulated between -60/+24 dB.
 *
 * Generated from Godot docs: AudioEffectEQ10
 */
class AudioEffectEQ10(handle: MemorySegment) : AudioEffectEQ(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectEQ10? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectEQ10? =
            if (handle.address() == 0L) null else AudioEffectEQ10(handle)

        // No MethodBinds emitted yet.
    }
}
