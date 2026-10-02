package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OggPacketSequencePlayback
 */
class OggPacketSequencePlayback(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OggPacketSequencePlayback? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OggPacketSequencePlayback? =
            if (handle.address() == 0L) null else OggPacketSequencePlayback(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
