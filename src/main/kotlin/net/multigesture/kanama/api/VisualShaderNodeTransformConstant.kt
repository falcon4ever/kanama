package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: VisualShaderNodeTransformConstant
 */
class VisualShaderNodeTransformConstant(handle: MemorySegment) : VisualShaderNodeConstant(handle) {
    var constant: Transform3D
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setConstantBind, handle, constant)
    }

    fun getConstant(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getConstantBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTransformConstant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTransformConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformConstant(handle)

        private const val SET_CONSTANT_HASH = 2952846383L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformConstant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 3229777777L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformConstant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
