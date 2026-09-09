package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A custom effect for a `RichTextLabel`.
 *
 * Generated from Godot docs: RichTextEffect
 */
class RichTextEffect(handle: MemorySegment) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RichTextEffect? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RichTextEffect? =
            if (handle.address() == 0L) null else RichTextEffect(handle)

        // No MethodBinds emitted yet.
    }
}
