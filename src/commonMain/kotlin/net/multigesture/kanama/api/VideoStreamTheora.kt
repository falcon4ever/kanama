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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VideoStreamTheora? =
            if (handle.address() == 0L) null else RefCounted.owned(VideoStreamTheora(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VideoStreamTheora? =
            if (handle.address() == 0L) null else VideoStreamTheora(GodotHandle(handle))
    }
}
