package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeCompare
 */
class VisualShaderNodeCompare(handle: GodotHandle) : VisualShaderNode(handle) {
    var type: VisualShaderNodeCompare.ComparisonType
        @JvmName("typeProperty")
        get() = getComparisonType()
        @JvmName("setTypeProperty")
        set(value) = setComparisonType(value)

    var function: VisualShaderNodeCompare.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    var condition: VisualShaderNodeCompare.Condition
        @JvmName("conditionProperty")
        get() = getCondition()
        @JvmName("setConditionProperty")
        set(value) = setCondition(value)

    fun setComparisonType(type: VisualShaderNodeCompare.ComparisonType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setComparisonTypeBind, segment, type.value)
    }

    fun getComparisonType(): VisualShaderNodeCompare.ComparisonType {
        checkOpen()
        return VisualShaderNodeCompare.ComparisonType(ObjectCalls.ptrcallNoArgsRetLong(getComparisonTypeBind, segment))
    }

    fun setFunction(func: VisualShaderNodeCompare.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeCompare.Function {
        checkOpen()
        return VisualShaderNodeCompare.Function(ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, segment))
    }

    fun setCondition(condition: VisualShaderNodeCompare.Condition) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setConditionBind, segment, condition.value)
    }

    fun getCondition(): VisualShaderNodeCompare.Condition {
        checkOpen()
        return VisualShaderNodeCompare.Condition(ObjectCalls.ptrcallNoArgsRetLong(getConditionBind, segment))
    }

    @JvmInline
    value class ComparisonType(val value: Long) {
        companion object {
            val SCALAR: ComparisonType get() = ComparisonType(0L)
            val SCALAR_INT: ComparisonType get() = ComparisonType(1L)
            val SCALAR_UINT: ComparisonType get() = ComparisonType(2L)
            val VECTOR_2D: ComparisonType get() = ComparisonType(3L)
            val VECTOR_3D: ComparisonType get() = ComparisonType(4L)
            val VECTOR_4D: ComparisonType get() = ComparisonType(5L)
            val BOOLEAN: ComparisonType get() = ComparisonType(6L)
            val TRANSFORM: ComparisonType get() = ComparisonType(7L)
            val MAX: ComparisonType get() = ComparisonType(8L)
        }
    }

    @JvmInline
    value class Function(val value: Long) {
        companion object {
            val EQUAL: Function get() = Function(0L)
            val NOT_EQUAL: Function get() = Function(1L)
            val GREATER_THAN: Function get() = Function(2L)
            val GREATER_THAN_EQUAL: Function get() = Function(3L)
            val LESS_THAN: Function get() = Function(4L)
            val LESS_THAN_EQUAL: Function get() = Function(5L)
            val MAX: Function get() = Function(6L)
        }
    }

    @JvmInline
    value class Condition(val value: Long) {
        companion object {
            val ALL: Condition get() = Condition(0L)
            val ANY: Condition get() = Condition(1L)
            val MAX: Condition get() = Condition(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCompare? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeCompare? =
            if (handle.address() == 0L) null else VisualShaderNodeCompare(GodotHandle(handle))

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
