package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Represents the output ports of a `VisualShaderGroup` within its graph.
 *
 * Generated from Godot docs: VisualShaderNodeGroupOutput
 */
class VisualShaderNodeGroupOutput(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeGroupOutput? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeGroupOutput? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeGroupOutput(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeGroupOutput? =
            if (handle.address() == 0L) null else VisualShaderNodeGroupOutput(GodotHandle(handle))

        /** A new VisualShaderNodeGroupOutput, GDScript's `VisualShaderNodeGroupOutput.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeGroupOutput =
            RefCounted.owned(VisualShaderNodeGroupOutput(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeGroupOutput"), "VisualShaderNodeGroupOutput"))))
    }
}
