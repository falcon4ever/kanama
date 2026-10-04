package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: StreamPeerExtension
 */
class StreamPeerExtension(handle: GodotHandle) : StreamPeer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamPeerExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StreamPeerExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(StreamPeerExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StreamPeerExtension? =
            if (handle.address() == 0L) null else StreamPeerExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
