package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Meta class for playing back audio samples.
 *
 * Generated from Godot docs: AudioSamplePlayback
 */
class AudioSamplePlayback(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioSamplePlayback? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioSamplePlayback? =
            if (handle.address() == 0L) null else AudioSamplePlayback(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
