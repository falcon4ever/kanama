package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeParticleEmit
 */
class VisualShaderNodeParticleEmit(handle: MemorySegment) : VisualShaderNode(handle) {
    var flags: Long
        @JvmName("flagsProperty")
        get() = getFlags()
        @JvmName("setFlagsProperty")
        set(value) = setFlags(value)

    fun setFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setFlagsBind, handle, flags)
    }

    fun getFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFlagsBind, handle)
    }

    companion object {
        const val EMIT_FLAG_POSITION: Long = 1L
        const val EMIT_FLAG_ROT_SCALE: Long = 2L
        const val EMIT_FLAG_VELOCITY: Long = 4L
        const val EMIT_FLAG_COLOR: Long = 8L
        const val EMIT_FLAG_CUSTOM: Long = 16L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParticleEmit? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleEmit? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleEmit(handle)

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
