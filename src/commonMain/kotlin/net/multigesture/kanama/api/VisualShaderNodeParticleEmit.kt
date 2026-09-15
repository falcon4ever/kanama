package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleEmit
 */
class VisualShaderNodeParticleEmit(handle: GodotHandle) : VisualShaderNode(handle) {
    var flags: Long
        @JvmName("flagsProperty")
        get() = getFlags()
        @JvmName("setFlagsProperty")
        set(value) = setFlags(value)

    fun setFlags(flags: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFlagsBind, segment, flags)
    }

    fun getFlags(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFlagsBind, segment)
    }

    companion object {
        const val EMIT_FLAG_POSITION: Long = 1L
        const val EMIT_FLAG_ROT_SCALE: Long = 2L
        const val EMIT_FLAG_VELOCITY: Long = 4L
        const val EMIT_FLAG_COLOR: Long = 8L
        const val EMIT_FLAG_CUSTOM: Long = 16L

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
