package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OfflineMultiplayerPeer
 */
class OfflineMultiplayerPeer(handle: GodotHandle) : MultiplayerPeer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OfflineMultiplayerPeer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OfflineMultiplayerPeer? =
            if (handle.address() == 0L) null else OfflineMultiplayerPeer(GodotHandle(handle))

        @JvmStatic
        fun create(): OfflineMultiplayerPeer =
            OfflineMultiplayerPeer(GodotHandle(ObjectCalls.constructObject("OfflineMultiplayerPeer")))

        // No MethodBinds emitted yet.
    }
}
