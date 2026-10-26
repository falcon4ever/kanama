package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Generated from Godot docs: WebRTCPeerConnectionExtension
 */
class WebRTCPeerConnectionExtension(handle: GodotHandle) : WebRTCPeerConnection(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebRTCPeerConnectionExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WebRTCPeerConnectionExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(WebRTCPeerConnectionExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WebRTCPeerConnectionExtension? =
            if (handle.address() == 0L) null else WebRTCPeerConnectionExtension(GodotHandle(handle))

        /** A new WebRTCPeerConnectionExtension, GDScript's `WebRTCPeerConnectionExtension.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): WebRTCPeerConnectionExtension =
            RefCounted.owned(WebRTCPeerConnectionExtension(GodotHandle(requireConstructed(ObjectCalls.constructObject("WebRTCPeerConnectionExtension"), "WebRTCPeerConnectionExtension"))))
    }
}
