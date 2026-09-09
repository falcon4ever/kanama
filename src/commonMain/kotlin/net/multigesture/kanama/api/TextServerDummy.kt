package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A dummy text server that can't render text or manage fonts.
 *
 * Generated from Godot docs: TextServerDummy
 */
class TextServerDummy(handle: MemorySegment) : TextServerExtension(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextServerDummy? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextServerDummy? =
            if (handle.address() == 0L) null else TextServerDummy(handle)

        // No MethodBinds emitted yet.
    }
}
