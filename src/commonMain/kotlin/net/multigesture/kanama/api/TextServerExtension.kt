package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for custom `TextServer` implementations (plugins).
 *
 * Generated from Godot docs: TextServerExtension
 */
open class TextServerExtension(handle: GodotHandle) : TextServer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextServerExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TextServerExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(TextServerExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TextServerExtension? =
            if (handle.address() == 0L) null else TextServerExtension(GodotHandle(handle))
    }
}
