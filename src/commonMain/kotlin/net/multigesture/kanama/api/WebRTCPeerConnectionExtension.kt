package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: WebRTCPeerConnectionExtension
 */
class WebRTCPeerConnectionExtension(handle: GodotHandle) : WebRTCPeerConnection(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebRTCPeerConnectionExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): WebRTCPeerConnectionExtension? =
            if (handle.address() == 0L) null else WebRTCPeerConnectionExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
