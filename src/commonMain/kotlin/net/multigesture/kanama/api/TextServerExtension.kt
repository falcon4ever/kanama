package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class for custom `TextServer` implementations (plugins).
 *
 * Generated from Godot docs: TextServerExtension
 */
open class TextServerExtension(handle: GodotHandle) : TextServer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextServerExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextServerExtension? =
            if (handle.address() == 0L) null else TextServerExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
