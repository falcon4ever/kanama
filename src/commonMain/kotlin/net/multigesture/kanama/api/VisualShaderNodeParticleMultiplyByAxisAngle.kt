package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        ObjectCalls.ptrcallWithBoolArg(Binds.setDegreesModeBind, segment, enabled)
    }

    fun isDegreesMode(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDegreesModeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleMultiplyByAxisAngle? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleMultiplyByAxisAngle? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleMultiplyByAxisAngle(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleMultiplyByAxisAngle? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleMultiplyByAxisAngle(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DEGREES_MODE_HASH = 2586408642L
        @JvmField
        val setDegreesModeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMultiplyByAxisAngle", "set_degrees_mode", SET_DEGREES_MODE_HASH)

        private const val IS_DEGREES_MODE_HASH = 36873697L
        @JvmField
        val isDegreesModeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMultiplyByAxisAngle", "is_degrees_mode", IS_DEGREES_MODE_HASH)
    }
}
