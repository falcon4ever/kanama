package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ScriptExtension
 */
class ScriptExtension(handle: MemorySegment) : Script(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ScriptExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScriptExtension? =
            if (handle.address() == 0L) null else ScriptExtension(handle)

        // No MethodBinds emitted yet.
    }
}
