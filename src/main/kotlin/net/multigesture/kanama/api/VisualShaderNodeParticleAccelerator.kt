package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeParticleAccelerator
 */
class VisualShaderNodeParticleAccelerator(handle: MemorySegment) : VisualShaderNode(handle) {
    var mode: Long
        @JvmName("modeProperty")
        get() = getMode()
        @JvmName("setModeProperty")
        set(value) = setMode(value)

    fun setMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setModeBind, handle, mode)
    }

    fun getMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getModeBind, handle)
    }

    companion object {
        const val MODE_LINEAR: Long = 0L
        const val MODE_RADIAL: Long = 1L
        const val MODE_TANGENTIAL: Long = 2L
        const val MODE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParticleAccelerator? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleAccelerator? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleAccelerator(handle)

        private const val SET_MODE_HASH = 3457585749L
        private val setModeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleAccelerator", "set_mode", SET_MODE_HASH)
        }

        private const val GET_MODE_HASH = 2660365633L
        private val getModeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleAccelerator", "get_mode", GET_MODE_HASH)
        }
    }
}
