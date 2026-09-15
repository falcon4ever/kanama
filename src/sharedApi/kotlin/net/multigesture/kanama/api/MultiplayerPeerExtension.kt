package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Class that can be inherited to implement custom multiplayer API networking layers via
 * GDExtension.
 *
 * Generated from Godot docs: MultiplayerPeerExtension
 */
class MultiplayerPeerExtension(handle: GodotHandle) : MultiplayerPeer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MultiplayerPeerExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MultiplayerPeerExtension? =
            if (handle.address() == 0L) null else MultiplayerPeerExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
