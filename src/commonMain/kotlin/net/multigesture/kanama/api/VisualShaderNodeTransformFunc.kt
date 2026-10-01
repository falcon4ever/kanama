package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTransformFunc
 */
class VisualShaderNodeTransformFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: Long
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, segment, func)
    }

    fun getFunction(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, segment)
    }

    companion object {
        const val FUNC_INVERSE: Long = 0L
        const val FUNC_TRANSPOSE: Long = 1L
        const val FUNC_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformFunc? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTransformFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformFunc(GodotHandle(handle))

        private const val SET_FUNCTION_HASH = 2900990409L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 2839926569L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformFunc", "get_function", GET_FUNCTION_HASH)
        }
    }
}
