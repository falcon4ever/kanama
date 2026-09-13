package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: StreamPeerExtension
 */
class StreamPeerExtension(handle: GodotHandle) : StreamPeer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamPeerExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): StreamPeerExtension? =
            if (handle.address() == 0L) null else StreamPeerExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
