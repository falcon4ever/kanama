package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Manipulates the audio it receives for a given effect.
 *
 * Generated from Godot docs: AudioEffectInstance
 */
open class AudioEffectInstance(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectInstance? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectInstance? =
            if (handle.address() == 0L) null else AudioEffectInstance(handle)

        // No MethodBinds emitted yet.
    }
}
