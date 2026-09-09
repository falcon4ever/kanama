package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamPlaybackPlaylist
 */
class AudioStreamPlaybackPlaylist(handle: MemorySegment) : AudioStreamPlayback(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlaybackPlaylist? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlaybackPlaylist? =
            if (handle.address() == 0L) null else AudioStreamPlaybackPlaylist(handle)

        // No MethodBinds emitted yet.
    }
}
