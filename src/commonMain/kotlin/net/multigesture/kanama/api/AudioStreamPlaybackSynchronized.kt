package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamPlaybackSynchronized
 */
class AudioStreamPlaybackSynchronized(handle: GodotHandle) : AudioStreamPlayback(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlaybackSynchronized? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): AudioStreamPlaybackSynchronized? =
            if (handle.address() == 0L) null else AudioStreamPlaybackSynchronized(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
