package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a 10-band equalizer audio effect to an audio bus. Gives you control over frequencies from
 * 31 Hz to 16000 Hz. Each frequency can be modulated between -60/+24 dB.
 *
 * Generated from Godot docs: AudioEffectEQ10
 */
class AudioEffectEQ10(handle: GodotHandle) : AudioEffectEQ(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectEQ10? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectEQ10? =
            if (handle.address() == 0L) null else AudioEffectEQ10(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
