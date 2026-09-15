package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A button that represents a binary choice.
 *
 * Generated from Godot docs: CheckButton
 */
class CheckButton(handle: GodotHandle) : Button(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CheckButton? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CheckButton? =
            if (handle.address() == 0L) null else CheckButton(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
