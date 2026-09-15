package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: TextServerAdvanced
 */
class TextServerAdvanced(handle: GodotHandle) : TextServerExtension(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextServerAdvanced? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextServerAdvanced? =
            if (handle.address() == 0L) null else TextServerAdvanced(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
