package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

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

        /** A new AudioEffect, GDScript's `AudioEffect.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AudioEffect =
            RefCounted.owned(AudioEffect(GodotHandle(requireConstructed(ObjectCalls.constructObject("AudioEffect"), "AudioEffect"))))
    }
}
