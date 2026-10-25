package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A `Cubemap` parameter node to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeCubemapParameter
 */
class VisualShaderNodeCubemapParameter(handle: GodotHandle) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCubemapParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCubemapParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCubemapParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCubemapParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeCubemapParameter(GodotHandle(handle))

        /** A new VisualShaderNodeCubemapParameter, GDScript's `VisualShaderNodeCubemapParameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeCubemapParameter =
            RefCounted.owned(VisualShaderNodeCubemapParameter(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeCubemapParameter"), "VisualShaderNodeCubemapParameter"))))
    }
}
