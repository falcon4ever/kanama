package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OggPacketSequencePlayback
 */
class OggPacketSequencePlayback(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OggPacketSequencePlayback? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OggPacketSequencePlayback? =
            if (handle.address() == 0L) null else OggPacketSequencePlayback(handle)

        // No MethodBinds emitted yet.
    }
}
