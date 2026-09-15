package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A node used to override global shader parameters' values in a scene.
 *
 * Generated from Godot docs: ShaderGlobalsOverride
 */
class ShaderGlobalsOverride(handle: GodotHandle) : Node(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ShaderGlobalsOverride? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ShaderGlobalsOverride? =
            if (handle.address() == 0L) null else ShaderGlobalsOverride(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
