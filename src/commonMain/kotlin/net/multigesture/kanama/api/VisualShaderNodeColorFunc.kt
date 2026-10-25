package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A `Color` function to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeColorFunc
 */
class VisualShaderNodeColorFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeColorFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    /**
     * A function to be applied to the input color.
     *
     * Generated from Godot docs: VisualShaderNodeColorFunc.set_function
     */
    fun setFunction(func: VisualShaderNodeColorFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A function to be applied to the input color.
     *
     * Generated from Godot docs: VisualShaderNodeColorFunc.get_function
     */
    fun getFunction(): VisualShaderNodeColorFunc.Function {
        checkOpen()
        val ret = VisualShaderNodeColorFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeColorFunc.Function` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeColorFunc.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeColorFunc.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Converts the color to grayscale using the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorFunc.FUNC_GRAYSCALE
             */
            val GRAYSCALE: Function get() = Function(0L)
            /**
             * Converts HSV vector to RGB equivalent.
             *
             * Generated from Godot docs: VisualShaderNodeColorFunc.FUNC_HSV2RGB
             */
            val HSV2RGB: Function get() = Function(1L)
            /**
             * Converts RGB vector to HSV equivalent.
             *
             * Generated from Godot docs: VisualShaderNodeColorFunc.FUNC_RGB2HSV
             */
            val RGB2HSV: Function get() = Function(2L)
            /**
             * Applies sepia tone effect using the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorFunc.FUNC_SEPIA
             */
            val SEPIA: Function get() = Function(3L)
            /**
             * Converts color from linear encoding to nonlinear sRGB encoding using the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorFunc.FUNC_LINEAR_TO_SRGB
             */
            val LINEAR_TO_SRGB: Function get() = Function(4L)
            /**
             * Converts color from nonlinear sRGB encoding to linear encoding using the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorFunc.FUNC_SRGB_TO_LINEAR
             */
            val SRGB_TO_LINEAR: Function get() = Function(5L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeColorFunc.FUNC_MAX
             */
            val MAX: Function get() = Function(6L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeColorFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeColorFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeColorFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeColorFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeColorFunc(GodotHandle(handle))

        /** A new VisualShaderNodeColorFunc, GDScript's `VisualShaderNodeColorFunc.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeColorFunc =
            RefCounted.owned(VisualShaderNodeColorFunc(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeColorFunc"), "VisualShaderNodeColorFunc"))))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 3973396138L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 554863321L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorFunc", "get_function", GET_FUNCTION_HASH)
    }
}
