package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A container that arranges its child controls vertically.
 *
 * Generated from Godot docs: VBoxContainer
 */
open class VBoxContainer(handle: GodotHandle) : BoxContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VBoxContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VBoxContainer? =
            if (handle.address() == 0L) null else VBoxContainer(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
