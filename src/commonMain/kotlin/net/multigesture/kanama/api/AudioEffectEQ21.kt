package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a 21-band equalizer audio effect to an audio bus. Gives you control over frequencies from
 * 22 Hz to 22000 Hz. Each frequency can be modulated between -60/+24 dB.
 *
 * Generated from Godot docs: AudioEffectEQ21
 */
class AudioEffectEQ21(handle: MemorySegment) : AudioEffectEQ(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectEQ21? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectEQ21? =
            if (handle.address() == 0L) null else AudioEffectEQ21(handle)

        // No MethodBinds emitted yet.
    }
}
