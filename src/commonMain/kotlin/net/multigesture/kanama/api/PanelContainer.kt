package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A container that keeps its child controls within the area of a `StyleBox`.
 *
 * Generated from Godot docs: PanelContainer
 */
open class PanelContainer(handle: GodotHandle) : Container(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PanelContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PanelContainer? =
            if (handle.address() == 0L) null else PanelContainer(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
