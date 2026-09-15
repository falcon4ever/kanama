package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for audio samples.
 *
 * Generated from Godot docs: AudioSample
 */
class AudioSample(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioSample? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioSample? =
            if (handle.address() == 0L) null else AudioSample(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
