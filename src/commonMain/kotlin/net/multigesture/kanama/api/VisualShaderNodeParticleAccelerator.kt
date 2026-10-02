package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeParticleAccelerator
 */
class VisualShaderNodeParticleAccelerator(handle: GodotHandle) : VisualShaderNode(handle) {
    var mode: VisualShaderNodeParticleAccelerator.Mode
        @JvmName("modeProperty")
        get() = getMode()
        @JvmName("setModeProperty")
        set(value) = setMode(value)

    fun setMode(mode: VisualShaderNodeParticleAccelerator.Mode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setModeBind, segment, mode.value)
    }

    fun getMode(): VisualShaderNodeParticleAccelerator.Mode {
        checkOpen()
        return VisualShaderNodeParticleAccelerator.Mode(ObjectCalls.ptrcallNoArgsRetLong(getModeBind, segment))
    }

    @JvmInline
    value class Mode(val value: Long) {
        companion object {
            val LINEAR: Mode get() = Mode(0L)
            val RADIAL: Mode get() = Mode(1L)
            val TANGENTIAL: Mode get() = Mode(2L)
            val MAX: Mode get() = Mode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleAccelerator? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeParticleAccelerator? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleAccelerator(GodotHandle(handle))

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
