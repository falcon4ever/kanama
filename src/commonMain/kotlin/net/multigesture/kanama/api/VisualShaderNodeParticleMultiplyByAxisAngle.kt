package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleMultiplyByAxisAngle
 */
class VisualShaderNodeParticleMultiplyByAxisAngle(handle: GodotHandle) : VisualShaderNode(handle) {
    var degreesMode: Boolean
        @JvmName("degreesModeProperty")
        get() = isDegreesMode()
        @JvmName("setDegreesModeProperty")
        set(value) = setDegreesMode(value)

    fun setDegreesMode(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setDegreesModeBind, segment, enabled)
    }

    fun isDegreesMode(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isDegreesModeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleMultiplyByAxisAngle? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleMultiplyByAxisAngle? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleMultiplyByAxisAngle(GodotHandle(handle))

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
