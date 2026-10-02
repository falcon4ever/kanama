package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeParticleEmit
 */
class VisualShaderNodeParticleEmit(handle: GodotHandle) : VisualShaderNode(handle) {
    var flags: VisualShaderNodeParticleEmit.EmitFlags
        @JvmName("flagsProperty")
        get() = getFlags()
        @JvmName("setFlagsProperty")
        set(value) = setFlags(value)

    fun setFlags(flags: VisualShaderNodeParticleEmit.EmitFlags) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFlagsBind, segment, flags.value)
    }

    fun getFlags(): VisualShaderNodeParticleEmit.EmitFlags {
        checkOpen()
        return VisualShaderNodeParticleEmit.EmitFlags(ObjectCalls.ptrcallNoArgsRetLong(getFlagsBind, segment))
    }

    @JvmInline
    value class EmitFlags(val value: Long) {
        companion object {
            val POSITION: EmitFlags get() = EmitFlags(1L)
            val ROT_SCALE: EmitFlags get() = EmitFlags(2L)
            val VELOCITY: EmitFlags get() = EmitFlags(4L)
            val COLOR: EmitFlags get() = EmitFlags(8L)
            val CUSTOM: EmitFlags get() = EmitFlags(16L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleEmit? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeParticleEmit? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleEmit(GodotHandle(handle))

        private const val SET_FLAGS_HASH = 3960756792L
        private val setFlagsBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleEmit", "set_flags", SET_FLAGS_HASH)
        }

        private const val GET_FLAGS_HASH = 171277835L
        private val getFlagsBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleEmit", "get_flags", GET_FLAGS_HASH)
        }
    }
}
