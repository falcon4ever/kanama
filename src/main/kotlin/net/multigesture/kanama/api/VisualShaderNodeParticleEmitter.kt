package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeParticleEmitter
 */
open class VisualShaderNodeParticleEmitter(handle: MemorySegment) : VisualShaderNode(handle) {
    var mode2d: Boolean
        @JvmName("mode2dProperty")
        get() = isMode2d()
        @JvmName("setMode2dProperty")
        set(value) = setMode2d(value)

    fun setMode2d(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMode2dBind, handle, enabled)
    }

    fun isMode2d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMode2dBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParticleEmitter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleEmitter(handle)

        private const val SET_MODE_2D_HASH = 2586408642L
        private val setMode2dBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleEmitter", "set_mode_2d", SET_MODE_2D_HASH)
        }

        private const val IS_MODE_2D_HASH = 36873697L
        private val isMode2dBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleEmitter", "is_mode_2d", IS_MODE_2D_HASH)
        }
    }
}
