package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: TextServerAdvanced
 */
class TextServerAdvanced(handle: MemorySegment) : TextServerExtension(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextServerAdvanced? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextServerAdvanced? =
            if (handle.address() == 0L) null else TextServerAdvanced(handle)

        // No MethodBinds emitted yet.
    }
}
