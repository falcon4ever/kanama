package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: WebRTCDataChannelExtension
 */
class WebRTCDataChannelExtension(handle: GodotHandle) : WebRTCDataChannel(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebRTCDataChannelExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): WebRTCDataChannelExtension? =
            if (handle.address() == 0L) null else WebRTCDataChannelExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
