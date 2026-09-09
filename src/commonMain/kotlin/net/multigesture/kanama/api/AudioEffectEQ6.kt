package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a 6-band equalizer audio effect to an audio bus. Gives you control over frequencies from 32
 * Hz to 10000 Hz. Each frequency can be modulated between -60/+24 dB.
 *
 * Generated from Godot docs: AudioEffectEQ6
 */
class AudioEffectEQ6(handle: MemorySegment) : AudioEffectEQ(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectEQ6? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectEQ6? =
            if (handle.address() == 0L) null else AudioEffectEQ6(handle)

        // No MethodBinds emitted yet.
    }
}
