package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: AudioStreamPlaybackPlaylist
 */
class AudioStreamPlaybackPlaylist(handle: GodotHandle) : AudioStreamPlayback(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlaybackPlaylist? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamPlaybackPlaylist? =
            if (handle.address() == 0L) null else AudioStreamPlaybackPlaylist(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
