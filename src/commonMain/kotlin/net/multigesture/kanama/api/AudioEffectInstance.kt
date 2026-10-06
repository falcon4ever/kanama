package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Manipulates the audio it receives for a given effect.
 *
 * Generated from Godot docs: AudioEffectInstance
 */
open class AudioEffectInstance(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectInstance? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectInstance? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectInstance(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectInstance? =
            if (handle.address() == 0L) null else AudioEffectInstance(GodotHandle(handle))
    }
}
