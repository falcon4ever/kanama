package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a band-limit filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectBandLimitFilter
 */
class AudioEffectBandLimitFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectBandLimitFilter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectBandLimitFilter? =
            if (handle.address() == 0L) null else AudioEffectBandLimitFilter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
