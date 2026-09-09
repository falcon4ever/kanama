package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: WebRTCPeerConnectionExtension
 */
class WebRTCPeerConnectionExtension(handle: MemorySegment) : WebRTCPeerConnection(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): WebRTCPeerConnectionExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WebRTCPeerConnectionExtension? =
            if (handle.address() == 0L) null else WebRTCPeerConnectionExtension(handle)

        // No MethodBinds emitted yet.
    }
}
