package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A comparison function for common types within the visual shader graph.
 *
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

    /**
     * The type to be used in the comparison.
     *
     * Generated from Godot docs: VisualShaderNodeCompare.set_comparison_type
     */
    fun setComparisonType(type: VisualShaderNodeCompare.ComparisonType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setComparisonTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The type to be used in the comparison.
     *
     * Generated from Godot docs: VisualShaderNodeCompare.get_comparison_type
     */
    fun getComparisonType(): VisualShaderNodeCompare.ComparisonType {
        checkOpen()
        val ret = VisualShaderNodeCompare.ComparisonType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getComparisonTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * A comparison function.
     *
     * Generated from Godot docs: VisualShaderNodeCompare.set_function
     */
    fun setFunction(func: VisualShaderNodeCompare.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A comparison function.
     *
     * Generated from Godot docs: VisualShaderNodeCompare.get_function
     */
    fun getFunction(): VisualShaderNodeCompare.Function {
        checkOpen()
        val ret = VisualShaderNodeCompare.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Extra condition which is applied if `type` is set to `ComparisonType.VECTOR_3D`.
     *
     * Generated from Godot docs: VisualShaderNodeCompare.set_condition
     */
    fun setCondition(condition: VisualShaderNodeCompare.Condition) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setConditionBind, segment, condition.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Extra condition which is applied if `type` is set to `ComparisonType.VECTOR_3D`.
     *
     * Generated from Godot docs: VisualShaderNodeCompare.get_condition
     */
    fun getCondition(): VisualShaderNodeCompare.Condition {
        checkOpen()
        val ret = VisualShaderNodeCompare.Condition(ObjectCalls.ptrcallNoArgsRetLong(Binds.getConditionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeCompare.ComparisonType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeCompare.ComparisonType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeCompare.ComparisonType
     */
    @JvmInline
    value class ComparisonType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_SCALAR
             */
            val SCALAR: ComparisonType get() = ComparisonType(0L)
            /**
             * An integer scalar.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_SCALAR_INT
             */
            val SCALAR_INT: ComparisonType get() = ComparisonType(1L)
            /**
             * An unsigned integer scalar.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_SCALAR_UINT
             */
            val SCALAR_UINT: ComparisonType get() = ComparisonType(2L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_VECTOR_2D
             */
            val VECTOR_2D: ComparisonType get() = ComparisonType(3L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_VECTOR_3D
             */
            val VECTOR_3D: ComparisonType get() = ComparisonType(4L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_VECTOR_4D
             */
            val VECTOR_4D: ComparisonType get() = ComparisonType(5L)
            /**
             * A boolean type.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_BOOLEAN
             */
            val BOOLEAN: ComparisonType get() = ComparisonType(6L)
            /**
             * A transform (`mat4`) type.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_TRANSFORM
             */
            val TRANSFORM: ComparisonType get() = ComparisonType(7L)
            /**
             * Represents the size of the `ComparisonType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.CTYPE_MAX
             */
            val MAX: ComparisonType get() = ComparisonType(8L)
        }
    }

    /**
     * Godot's `VisualShaderNodeCompare.Function` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeCompare.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeCompare.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Comparison for equality (`a == b`).
             *
             * Generated from Godot docs: VisualShaderNodeCompare.FUNC_EQUAL
             */
            val EQUAL: Function get() = Function(0L)
            /**
             * Comparison for inequality (`a != b`).
             *
             * Generated from Godot docs: VisualShaderNodeCompare.FUNC_NOT_EQUAL
             */
            val NOT_EQUAL: Function get() = Function(1L)
            /**
             * Comparison for greater than (`a > b`). Cannot be used if `type` set to `ComparisonType.BOOLEAN`
             * or `ComparisonType.TRANSFORM`.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.FUNC_GREATER_THAN
             */
            val GREATER_THAN: Function get() = Function(2L)
            /**
             * Comparison for greater than or equal (`a >= b`). Cannot be used if `type` set to
             * `ComparisonType.BOOLEAN` or `ComparisonType.TRANSFORM`.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.FUNC_GREATER_THAN_EQUAL
             */
            val GREATER_THAN_EQUAL: Function get() = Function(3L)
            /**
             * Comparison for less than (`a < b`). Cannot be used if `type` set to `ComparisonType.BOOLEAN` or
             * `ComparisonType.TRANSFORM`.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.FUNC_LESS_THAN
             */
            val LESS_THAN: Function get() = Function(4L)
            /**
             * Comparison for less than or equal (`a <= b`). Cannot be used if `type` set to
             * `ComparisonType.BOOLEAN` or `ComparisonType.TRANSFORM`.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.FUNC_LESS_THAN_EQUAL
             */
            val LESS_THAN_EQUAL: Function get() = Function(5L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.FUNC_MAX
             */
            val MAX: Function get() = Function(6L)
        }
    }

    /**
     * Godot's `VisualShaderNodeCompare.Condition` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeCompare.Condition.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeCompare.Condition
     */
    @JvmInline
    value class Condition(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The result will be `true` if all components in the vector satisfy the comparison condition.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.COND_ALL
             */
            val ALL: Condition get() = Condition(0L)
            /**
             * The result will be `true` if any component in the vector satisfies the comparison condition.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.COND_ANY
             */
            val ANY: Condition get() = Condition(1L)
            /**
             * Represents the size of the `Condition` enum.
             *
             * Generated from Godot docs: VisualShaderNodeCompare.COND_MAX
             */
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
