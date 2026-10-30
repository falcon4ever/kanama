package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Base class used for extending the `MultiplayerAPI`.
 *
 * Generated from Godot docs: MultiplayerAPIExtension
 */
class MultiplayerAPIExtension(handle: GodotHandle) : MultiplayerAPI(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MultiplayerAPIExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): MultiplayerAPIExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(MultiplayerAPIExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): MultiplayerAPIExtension? =
            if (handle.address() == 0L) null else MultiplayerAPIExtension(GodotHandle(handle))

        /** A new MultiplayerAPIExtension, GDScript's `MultiplayerAPIExtension.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): MultiplayerAPIExtension =
            RefCounted.owned(MultiplayerAPIExtension(GodotHandle(requireConstructed(ObjectCalls.constructObject("MultiplayerAPIExtension"), "MultiplayerAPIExtension"))))
    }
}
