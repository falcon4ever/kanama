package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A button that represents a binary choice.
 *
 * Generated from Godot docs: CheckBox
 */
class CheckBox(handle: GodotHandle) : Button(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CheckBox? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CheckBox? =
            if (handle.address() == 0L) null else CheckBox(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
