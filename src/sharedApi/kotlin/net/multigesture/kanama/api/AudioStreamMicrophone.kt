package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Plays real-time audio input data.
 *
 * Generated from Godot docs: AudioStreamMicrophone
 */
class AudioStreamMicrophone(handle: GodotHandle) : AudioStream(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamMicrophone? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamMicrophone? =
            if (handle.address() == 0L) null else AudioStreamMicrophone(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
