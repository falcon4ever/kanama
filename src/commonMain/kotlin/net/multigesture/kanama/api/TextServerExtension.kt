package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class for custom `TextServer` implementations (plugins).
 *
 * Generated from Godot docs: TextServerExtension
 */
open class TextServerExtension(handle: MemorySegment) : TextServer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextServerExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextServerExtension? =
            if (handle.address() == 0L) null else TextServerExtension(handle)

        // No MethodBinds emitted yet.
    }
}
