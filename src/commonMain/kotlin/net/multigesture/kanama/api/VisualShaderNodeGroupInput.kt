package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Represents the input ports of a `VisualShaderGroup` within its graph.
 *
 * Generated from Godot docs: VisualShaderNodeGroupInput
 */
class VisualShaderNodeGroupInput(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeGroupInput? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeGroupInput? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeGroupInput(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeGroupInput? =
            if (handle.address() == 0L) null else VisualShaderNodeGroupInput(GodotHandle(handle))

        /** A new VisualShaderNodeGroupInput, GDScript's `VisualShaderNodeGroupInput.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeGroupInput =
            RefCounted.owned(VisualShaderNodeGroupInput(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeGroupInput"), "VisualShaderNodeGroupInput"))))
    }
}
