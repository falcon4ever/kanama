package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a 21-band equalizer audio effect to an audio bus. Gives you control over frequencies from
 * 22 Hz to 22000 Hz. Each frequency can be modulated between -60/+24 dB.
 *
 * Generated from Godot docs: AudioEffectEQ21
 */
class AudioEffectEQ21(handle: GodotHandle) : AudioEffectEQ(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectEQ21? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectEQ21? =
            if (handle.address() == 0L) null else AudioEffectEQ21(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
