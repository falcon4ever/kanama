package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VideoStreamTheora
 */
class VideoStreamTheora(handle: GodotHandle) : VideoStream(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VideoStreamTheora? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VideoStreamTheora? =
            if (handle.address() == 0L) null else VideoStreamTheora(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
