package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithLongArg(Binds.setModeBind, segment, mode.value)
    }

    fun getMode(): VisualShaderNodeParticleAccelerator.Mode {
        checkOpen()
        return VisualShaderNodeParticleAccelerator.Mode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getModeBind, segment))
    }

    @JvmInline
    value class Mode(override val value: Long) : GodotEnumValue {
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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleAccelerator? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleAccelerator(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleAccelerator? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleAccelerator(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_MODE_HASH = 3457585749L
        @JvmField
        val setModeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleAccelerator", "set_mode", SET_MODE_HASH)

        private const val GET_MODE_HASH = 2660365633L
        @JvmField
        val getModeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleAccelerator", "get_mode", GET_MODE_HASH)
    }
}
