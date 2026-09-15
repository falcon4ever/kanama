package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: VisualShaderNodeTransformConstant
 */
class VisualShaderNodeTransformConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Transform3D
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DArg(setConstantBind, segment, constant)
    }

    fun getConstant(): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformConstant? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTransformConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformConstant(GodotHandle(handle))

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
