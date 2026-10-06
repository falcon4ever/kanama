package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: PacketPeerExtension
 */
class PacketPeerExtension(handle: GodotHandle) : PacketPeer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PacketPeerExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PacketPeerExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(PacketPeerExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PacketPeerExtension? =
            if (handle.address() == 0L) null else PacketPeerExtension(GodotHandle(handle))
    }
}
