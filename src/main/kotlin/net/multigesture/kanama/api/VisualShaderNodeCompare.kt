package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeCompare
 */
class VisualShaderNodeCompare(handle: MemorySegment) : VisualShaderNode(handle) {
    var type: Long
        @JvmName("typeProperty")
        get() = getComparisonType()
        @JvmName("setTypeProperty")
        set(value) = setComparisonType(value)

    var function: Long
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    var condition: Long
        @JvmName("conditionProperty")
        get() = getCondition()
        @JvmName("setConditionProperty")
        set(value) = setCondition(value)

    fun setComparisonType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setComparisonTypeBind, handle, type)
    }

    fun getComparisonType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getComparisonTypeBind, handle)
    }

    fun setFunction(func: Long) {
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, handle, func)
    }

    fun getFunction(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, handle)
    }

    fun setCondition(condition: Long) {
        ObjectCalls.ptrcallWithLongArg(setConditionBind, handle, condition)
    }

    fun getCondition(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getConditionBind, handle)
    }

    companion object {
        const val CTYPE_SCALAR: Long = 0L
        const val CTYPE_SCALAR_INT: Long = 1L
        const val CTYPE_SCALAR_UINT: Long = 2L
        const val CTYPE_VECTOR_2D: Long = 3L
        const val CTYPE_VECTOR_3D: Long = 4L
        const val CTYPE_VECTOR_4D: Long = 5L
        const val CTYPE_BOOLEAN: Long = 6L
        const val CTYPE_TRANSFORM: Long = 7L
        const val CTYPE_MAX: Long = 8L
        const val FUNC_EQUAL: Long = 0L
        const val FUNC_NOT_EQUAL: Long = 1L
        const val FUNC_GREATER_THAN: Long = 2L
        const val FUNC_GREATER_THAN_EQUAL: Long = 3L
        const val FUNC_LESS_THAN: Long = 4L
        const val FUNC_LESS_THAN_EQUAL: Long = 5L
        const val FUNC_MAX: Long = 6L
        const val COND_ALL: Long = 0L
        const val COND_ANY: Long = 1L
        const val COND_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeCompare? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeCompare? =
            if (handle.address() == 0L) null else VisualShaderNodeCompare(handle)

        private const val SET_COMPARISON_TYPE_HASH = 516558320L
        private val setComparisonTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "set_comparison_type", SET_COMPARISON_TYPE_HASH)
        }

        private const val GET_COMPARISON_TYPE_HASH = 3495315961L
        private val getComparisonTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "get_comparison_type", GET_COMPARISON_TYPE_HASH)
        }

        private const val SET_FUNCTION_HASH = 2370951349L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 4089164265L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "get_function", GET_FUNCTION_HASH)
        }

        private const val SET_CONDITION_HASH = 918742392L
        private val setConditionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "set_condition", SET_CONDITION_HASH)
        }

        private const val GET_CONDITION_HASH = 3281078941L
        private val getConditionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "get_condition", GET_CONDITION_HASH)
        }
    }
}
