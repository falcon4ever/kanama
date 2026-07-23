package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OfflineMultiplayerPeer
 */
class OfflineMultiplayerPeer(handle: MemorySegment) : MultiplayerPeer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        fun fromHandle(handle: MemorySegment): OfflineMultiplayerPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OfflineMultiplayerPeer? =
            if (handle.address() == 0L) null else OfflineMultiplayerPeer(handle)

        // Instantiate an OfflineMultiplayerPeer (single-player multiplayer stub).
        fun create(): OfflineMultiplayerPeer =
            OfflineMultiplayerPeer(MemorySegment.ofAddress(IosGodot.constructObject("OfflineMultiplayerPeer")))

        // No MethodBinds emitted yet.
    }
}
