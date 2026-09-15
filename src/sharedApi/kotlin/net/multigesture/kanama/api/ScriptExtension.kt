package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ScriptExtension
 */
class ScriptExtension(handle: GodotHandle) : Script(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScriptExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScriptExtension? =
            if (handle.address() == 0L) null else ScriptExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
