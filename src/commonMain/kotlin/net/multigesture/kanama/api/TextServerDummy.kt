package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A dummy text server that can't render text or manage fonts.
 *
 * Generated from Godot docs: TextServerDummy
 */
class TextServerDummy(handle: GodotHandle) : TextServerExtension(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextServerDummy? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TextServerDummy? =
            if (handle.address() == 0L) null else RefCounted.owned(TextServerDummy(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TextServerDummy? =
            if (handle.address() == 0L) null else TextServerDummy(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
