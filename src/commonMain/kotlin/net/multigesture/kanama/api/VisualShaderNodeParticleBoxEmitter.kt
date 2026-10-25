package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node that makes particles emitted in a box shape.
 *
 * Generated from Godot docs: VisualShaderNodeParticleBoxEmitter
 */
class VisualShaderNodeParticleBoxEmitter(handle: GodotHandle) : VisualShaderNodeParticleEmitter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleBoxEmitter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleBoxEmitter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleBoxEmitter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleBoxEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleBoxEmitter(GodotHandle(handle))

        /** A new VisualShaderNodeParticleBoxEmitter, GDScript's `VisualShaderNodeParticleBoxEmitter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeParticleBoxEmitter =
            RefCounted.owned(VisualShaderNodeParticleBoxEmitter(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeParticleBoxEmitter"))))
    }
}
