package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamPlaybackSynchronized
 */
class AudioStreamPlaybackSynchronized(handle: MemorySegment) : AudioStreamPlayback(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlaybackSynchronized? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlaybackSynchronized? =
            if (handle.address() == 0L) null else AudioStreamPlaybackSynchronized(handle)

        // No MethodBinds emitted yet.
    }
}
