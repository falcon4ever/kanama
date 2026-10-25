package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A scalar integer function to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeIntFunc
 */
class VisualShaderNodeIntFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeIntFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    /**
     * A function to be applied to the scalar.
     *
     * Generated from Godot docs: VisualShaderNodeIntFunc.set_function
     */
    fun setFunction(func: VisualShaderNodeIntFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A function to be applied to the scalar.
     *
     * Generated from Godot docs: VisualShaderNodeIntFunc.get_function
     */
    fun getFunction(): VisualShaderNodeIntFunc.Function {
        checkOpen()
        val ret = VisualShaderNodeIntFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeIntFunc.Function` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeIntFunc.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeIntFunc.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Returns the absolute value of the parameter. Translates to `abs(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntFunc.FUNC_ABS
             */
            val ABS: Function get() = Function(0L)
            /**
             * Negates the `x` using `-(x)`.
             *
             * Generated from Godot docs: VisualShaderNodeIntFunc.FUNC_NEGATE
             */
            val NEGATE: Function get() = Function(1L)
            /**
             * Extracts the sign of the parameter. Translates to `sign(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntFunc.FUNC_SIGN
             */
            val SIGN: Function get() = Function(2L)
            /**
             * Returns the result of bitwise `NOT` operation on the integer. Translates to `~a` in the Godot
             * Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntFunc.FUNC_BITWISE_NOT
             */
            val BITWISE_NOT: Function get() = Function(3L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeIntFunc.FUNC_MAX
             */
            val MAX: Function get() = Function(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIntFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeIntFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeIntFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeIntFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeIntFunc(GodotHandle(handle))

        /** A new VisualShaderNodeIntFunc, GDScript's `VisualShaderNodeIntFunc.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeIntFunc =
            RefCounted.owned(VisualShaderNodeIntFunc(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeIntFunc"), "VisualShaderNodeIntFunc"))))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 424195284L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 2753496911L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntFunc", "get_function", GET_FUNCTION_HASH)
    }
}
