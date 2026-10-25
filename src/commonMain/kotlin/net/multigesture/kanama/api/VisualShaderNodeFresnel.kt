package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A Fresnel effect to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeFresnel
 */
class VisualShaderNodeFresnel(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFresnel? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeFresnel? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeFresnel(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeFresnel? =
            if (handle.address() == 0L) null else VisualShaderNodeFresnel(GodotHandle(handle))

        /** A new VisualShaderNodeFresnel, GDScript's `VisualShaderNodeFresnel.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeFresnel =
            RefCounted.owned(VisualShaderNodeFresnel(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeFresnel"))))
    }
}
