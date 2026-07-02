package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeParticleMultiplyByAxisAngle
 */
class VisualShaderNodeParticleMultiplyByAxisAngle(handle: MemorySegment) : VisualShaderNode(handle) {
    var degreesMode: Boolean
        @JvmName("degreesModeProperty")
        get() = isDegreesMode()
        @JvmName("setDegreesModeProperty")
        set(value) = setDegreesMode(value)

    fun setDegreesMode(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDegreesModeBind, handle, enabled)
    }

    fun isDegreesMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDegreesModeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParticleMultiplyByAxisAngle? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleMultiplyByAxisAngle? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleMultiplyByAxisAngle(handle)

        private const val SET_DEGREES_MODE_HASH = 2586408642L
        private val setDegreesModeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMultiplyByAxisAngle", "set_degrees_mode", SET_DEGREES_MODE_HASH)
        }

        private const val IS_DEGREES_MODE_HASH = 36873697L
        private val isDegreesModeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMultiplyByAxisAngle", "is_degrees_mode", IS_DEGREES_MODE_HASH)
        }
    }
}
