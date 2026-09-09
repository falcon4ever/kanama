package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Class that can be inherited to implement custom multiplayer API networking layers via
 * GDExtension.
 *
 * Generated from Godot docs: MultiplayerPeerExtension
 */
class MultiplayerPeerExtension(handle: MemorySegment) : MultiplayerPeer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MultiplayerPeerExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MultiplayerPeerExtension? =
            if (handle.address() == 0L) null else MultiplayerPeerExtension(handle)

        // No MethodBinds emitted yet.
    }
}
