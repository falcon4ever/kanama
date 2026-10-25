package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Computes a `Transform3D` function within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTransformFunc
 */
class VisualShaderNodeTransformFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeTransformFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    /**
     * The function to be computed.
     *
     * Generated from Godot docs: VisualShaderNodeTransformFunc.set_function
     */
    fun setFunction(func: VisualShaderNodeTransformFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The function to be computed.
     *
     * Generated from Godot docs: VisualShaderNodeTransformFunc.get_function
     */
    fun getFunction(): VisualShaderNodeTransformFunc.Function {
        checkOpen()
        val ret = VisualShaderNodeTransformFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeTransformFunc.Function` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTransformFunc.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTransformFunc.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Perform the inverse operation on the `Transform3D` matrix.
             *
             * Generated from Godot docs: VisualShaderNodeTransformFunc.FUNC_INVERSE
             */
            val INVERSE: Function get() = Function(0L)
            /**
             * Perform the transpose operation on the `Transform3D` matrix.
             *
             * Generated from Godot docs: VisualShaderNodeTransformFunc.FUNC_TRANSPOSE
             */
            val TRANSPOSE: Function get() = Function(1L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTransformFunc.FUNC_MAX
             */
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformFunc(GodotHandle(handle))

        /** A new VisualShaderNodeTransformFunc, GDScript's `VisualShaderNodeTransformFunc.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeTransformFunc =
            RefCounted.owned(VisualShaderNodeTransformFunc(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeTransformFunc"))))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 2900990409L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 2839926569L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformFunc", "get_function", GET_FUNCTION_HASH)
    }
}
