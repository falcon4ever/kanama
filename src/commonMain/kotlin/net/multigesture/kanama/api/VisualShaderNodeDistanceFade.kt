package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node representing distance fade effect.
 *
 * Generated from Godot docs: VisualShaderNodeDistanceFade
 */
class VisualShaderNodeDistanceFade(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeDistanceFade? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeDistanceFade? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeDistanceFade(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeDistanceFade? =
            if (handle.address() == 0L) null else VisualShaderNodeDistanceFade(GodotHandle(handle))

        /** A new VisualShaderNodeDistanceFade, GDScript's `VisualShaderNodeDistanceFade.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeDistanceFade =
            RefCounted.owned(VisualShaderNodeDistanceFade(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeDistanceFade"))))
    }
}
