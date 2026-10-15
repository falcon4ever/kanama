package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node that forces to emit a particle from a sub-emitter.
 *
 * Generated from Godot docs: VisualShaderNodeParticleEmit
 */
class VisualShaderNodeParticleEmit(handle: GodotHandle) : VisualShaderNode(handle) {
    var flags: VisualShaderNodeParticleEmit.EmitFlags
        @JvmName("flagsProperty")
        get() = getFlags()
        @JvmName("setFlagsProperty")
        set(value) = setFlags(value)

    /**
     * Flags used to override the properties defined in the sub-emitter's process material.
     *
     * Generated from Godot docs: VisualShaderNodeParticleEmit.set_flags
     */
    fun setFlags(flags: VisualShaderNodeParticleEmit.EmitFlags) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFlagsBind, segment, flags.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Flags used to override the properties defined in the sub-emitter's process material.
     *
     * Generated from Godot docs: VisualShaderNodeParticleEmit.get_flags
     */
    fun getFlags(): VisualShaderNodeParticleEmit.EmitFlags {
        checkOpen()
        val ret = VisualShaderNodeParticleEmit.EmitFlags(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFlagsBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeParticleEmit.EmitFlags` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeParticleEmit.EmitFlags.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeParticleEmit.EmitFlags
     */
    @JvmInline
    value class EmitFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * If enabled, the particle starts with the position defined by this node.
             *
             * Generated from Godot docs: VisualShaderNodeParticleEmit.EMIT_FLAG_POSITION
             */
            val POSITION: EmitFlags get() = EmitFlags(1L)
            /**
             * If enabled, the particle starts with the rotation and scale defined by this node.
             *
             * Generated from Godot docs: VisualShaderNodeParticleEmit.EMIT_FLAG_ROT_SCALE
             */
            val ROT_SCALE: EmitFlags get() = EmitFlags(2L)
            /**
             * If enabled,the particle starts with the velocity defined by this node.
             *
             * Generated from Godot docs: VisualShaderNodeParticleEmit.EMIT_FLAG_VELOCITY
             */
            val VELOCITY: EmitFlags get() = EmitFlags(4L)
            /**
             * If enabled, the particle starts with the color defined by this node.
             *
             * Generated from Godot docs: VisualShaderNodeParticleEmit.EMIT_FLAG_COLOR
             */
            val COLOR: EmitFlags get() = EmitFlags(8L)
            /**
             * If enabled, the particle starts with the `CUSTOM` data defined by this node.
             *
             * Generated from Godot docs: VisualShaderNodeParticleEmit.EMIT_FLAG_CUSTOM
             */
            val CUSTOM: EmitFlags get() = EmitFlags(16L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleEmit? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleEmit? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleEmit(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleEmit? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleEmit(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FLAGS_HASH = 3960756792L
        @JvmField
        val setFlagsBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleEmit", "set_flags", SET_FLAGS_HASH)

        private const val GET_FLAGS_HASH = 171277835L
        @JvmField
        val getFlagsBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleEmit", "get_flags", GET_FLAGS_HASH)
    }
}
