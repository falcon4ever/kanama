package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithLongArg(Binds.setComparisonTypeBind, segment, type.value)
    }

    fun getComparisonType(): VisualShaderNodeCompare.ComparisonType {
        checkOpen()
        return VisualShaderNodeCompare.ComparisonType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getComparisonTypeBind, segment))
    }

    fun setFunction(func: VisualShaderNodeCompare.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeCompare.Function {
        checkOpen()
        return VisualShaderNodeCompare.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
    }

    fun setCondition(condition: VisualShaderNodeCompare.Condition) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setConditionBind, segment, condition.value)
    }

    fun getCondition(): VisualShaderNodeCompare.Condition {
        checkOpen()
        return VisualShaderNodeCompare.Condition(ObjectCalls.ptrcallNoArgsRetLong(Binds.getConditionBind, segment))
    }

    @JvmInline
    value class ComparisonType(override val value: Long) : GodotEnumValue {
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
    value class Function(override val value: Long) : GodotEnumValue {
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
    value class Condition(override val value: Long) : GodotEnumValue {
        companion object {
            val ALL: Condition get() = Condition(0L)
            val ANY: Condition get() = Condition(1L)
            val MAX: Condition get() = Condition(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCompare? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCompare? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCompare(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCompare? =
            if (handle.address() == 0L) null else VisualShaderNodeCompare(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_COMPARISON_TYPE_HASH = 516558320L
        @JvmField
        val setComparisonTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "set_comparison_type", SET_COMPARISON_TYPE_HASH)

        private const val GET_COMPARISON_TYPE_HASH = 3495315961L
        @JvmField
        val getComparisonTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "get_comparison_type", GET_COMPARISON_TYPE_HASH)

        private const val SET_FUNCTION_HASH = 2370951349L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 4089164265L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "get_function", GET_FUNCTION_HASH)

        private const val SET_CONDITION_HASH = 918742392L
        @JvmField
        val setConditionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "set_condition", SET_CONDITION_HASH)

        private const val GET_CONDITION_HASH = 3281078941L
        @JvmField
        val getConditionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCompare", "get_condition", GET_CONDITION_HASH)
    }
}
