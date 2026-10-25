package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * SDF raymarching algorithm to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeSDFRaymarch
 */
class VisualShaderNodeSDFRaymarch(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeSDFRaymarch? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeSDFRaymarch? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeSDFRaymarch(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeSDFRaymarch? =
            if (handle.address() == 0L) null else VisualShaderNodeSDFRaymarch(GodotHandle(handle))

        /** A new VisualShaderNodeSDFRaymarch, GDScript's `VisualShaderNodeSDFRaymarch.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeSDFRaymarch =
            RefCounted.owned(VisualShaderNodeSDFRaymarch(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeSDFRaymarch"), "VisualShaderNodeSDFRaymarch"))))
    }
}
