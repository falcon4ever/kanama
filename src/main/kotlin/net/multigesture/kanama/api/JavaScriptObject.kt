package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A wrapper class for web native JavaScript objects.
 *
 * Generated from Godot docs: JavaScriptObject
 */
class JavaScriptObject(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): JavaScriptObject? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JavaScriptObject? =
            if (handle.address() == 0L) null else JavaScriptObject(handle)

        // No MethodBinds emitted yet.
    }
}
