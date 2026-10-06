package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OfflineMultiplayerPeer
 */
class OfflineMultiplayerPeer(handle: GodotHandle) : MultiplayerPeer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OfflineMultiplayerPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OfflineMultiplayerPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(OfflineMultiplayerPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OfflineMultiplayerPeer? =
            if (handle.address() == 0L) null else OfflineMultiplayerPeer(GodotHandle(handle))

        // Instantiate an OfflineMultiplayerPeer.
        @JvmStatic
        fun create(): OfflineMultiplayerPeer =
            RefCounted.owned(OfflineMultiplayerPeer(GodotHandle(ObjectCalls.constructObject("OfflineMultiplayerPeer"))))
    }
}
