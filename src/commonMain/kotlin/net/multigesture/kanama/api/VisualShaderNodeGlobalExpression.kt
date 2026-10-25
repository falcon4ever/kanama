package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A custom global visual shader graph expression written in Godot Shading Language.
 *
 * Generated from Godot docs: VisualShaderNodeGlobalExpression
 */
class VisualShaderNodeGlobalExpression(handle: GodotHandle) : VisualShaderNodeExpression(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeGlobalExpression? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeGlobalExpression? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeGlobalExpression(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeGlobalExpression? =
            if (handle.address() == 0L) null else VisualShaderNodeGlobalExpression(GodotHandle(handle))

        /** A new VisualShaderNodeGlobalExpression, GDScript's `VisualShaderNodeGlobalExpression.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeGlobalExpression =
            RefCounted.owned(VisualShaderNodeGlobalExpression(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeGlobalExpression"))))
    }
}
