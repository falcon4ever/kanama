package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for audio effect resources.
 *
 * Generated from Godot docs: AudioEffect
 */
open class AudioEffect(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffect? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffect? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffect(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffect? =
            if (handle.address() == 0L) null else AudioEffect(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
