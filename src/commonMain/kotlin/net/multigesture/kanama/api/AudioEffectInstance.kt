package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

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
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectInstance? =
            if (handle.address() == 0L) null else AudioEffectInstance(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
