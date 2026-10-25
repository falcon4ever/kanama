package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * An unsigned scalar integer function to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeUIntFunc
 */
class VisualShaderNodeUIntFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeUIntFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    /**
     * A function to be applied to the scalar.
     *
     * Generated from Godot docs: VisualShaderNodeUIntFunc.set_function
     */
    fun setFunction(func: VisualShaderNodeUIntFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A function to be applied to the scalar.
     *
     * Generated from Godot docs: VisualShaderNodeUIntFunc.get_function
     */
    fun getFunction(): VisualShaderNodeUIntFunc.Function {
        checkOpen()
        val ret = VisualShaderNodeUIntFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeUIntFunc.Function` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeUIntFunc.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeUIntFunc.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Negates the `x` using `-(x)`.
             *
             * Generated from Godot docs: VisualShaderNodeUIntFunc.FUNC_NEGATE
             */
            val NEGATE: Function get() = Function(0L)
            /**
             * Returns the result of bitwise `NOT` operation on the integer. Translates to `~a` in the Godot
             * Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeUIntFunc.FUNC_BITWISE_NOT
             */
            val BITWISE_NOT: Function get() = Function(1L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeUIntFunc.FUNC_MAX
             */
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUIntFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeUIntFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeUIntFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeUIntFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntFunc(GodotHandle(handle))

        /** A new VisualShaderNodeUIntFunc, GDScript's `VisualShaderNodeUIntFunc.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeUIntFunc =
            RefCounted.owned(VisualShaderNodeUIntFunc(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeUIntFunc"), "VisualShaderNodeUIntFunc"))))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 2273148961L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUIntFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 4187123296L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUIntFunc", "get_function", GET_FUNCTION_HASH)
    }
}
