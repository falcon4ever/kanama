package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A visual shader node that makes particles emitted in a sphere shape.
 *
 * Generated from Godot docs: VisualShaderNodeParticleSphereEmitter
 */
class VisualShaderNodeParticleSphereEmitter(handle: GodotHandle) : VisualShaderNodeParticleEmitter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleSphereEmitter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleSphereEmitter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleSphereEmitter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleSphereEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleSphereEmitter(GodotHandle(handle))

        /** A new VisualShaderNodeParticleSphereEmitter, GDScript's `VisualShaderNodeParticleSphereEmitter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeParticleSphereEmitter =
            RefCounted.owned(VisualShaderNodeParticleSphereEmitter(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeParticleSphereEmitter"), "VisualShaderNodeParticleSphereEmitter"))))
    }
}
