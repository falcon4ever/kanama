package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeOuterProduct
 */
class VisualShaderNodeOuterProduct(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeOuterProduct? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeOuterProduct? =
            if (handle.address() == 0L) null else VisualShaderNodeOuterProduct(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
