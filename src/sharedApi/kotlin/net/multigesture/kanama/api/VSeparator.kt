package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A vertical line used for separating other controls.
 *
 * Generated from Godot docs: VSeparator
 */
class VSeparator(handle: GodotHandle) : Separator(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VSeparator? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VSeparator? =
            if (handle.address() == 0L) null else VSeparator(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
