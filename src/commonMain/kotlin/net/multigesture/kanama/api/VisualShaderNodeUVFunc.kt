package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Contains functions to modify texture coordinates (`uv`) to be used within the visual shader
 * graph.
 *
 * Generated from Godot docs: VisualShaderNodeUVFunc
 */
class VisualShaderNodeUVFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeUVFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    /**
     * A function to be applied to the texture coordinates.
     *
     * Generated from Godot docs: VisualShaderNodeUVFunc.set_function
     */
    fun setFunction(func: VisualShaderNodeUVFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A function to be applied to the texture coordinates.
     *
     * Generated from Godot docs: VisualShaderNodeUVFunc.get_function
     */
    fun getFunction(): VisualShaderNodeUVFunc.Function {
        checkOpen()
        val ret = VisualShaderNodeUVFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeUVFunc.Function` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`VisualShaderNodeUVFunc.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeUVFunc.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Translates `uv` by using `scale` and `offset` values using the following formula: `uv = uv +
             * offset * scale`. `uv` port is connected to `UV` built-in by default.
             *
             * Generated from Godot docs: VisualShaderNodeUVFunc.FUNC_PANNING
             */
            val PANNING: Function get() = Function(0L)
            /**
             * Scales `uv` by using `scale` and `pivot` values using the following formula: `uv = (uv - pivot)
             * * scale + pivot`. `uv` port is connected to `UV` built-in by default.
             *
             * Generated from Godot docs: VisualShaderNodeUVFunc.FUNC_SCALING
             */
            val SCALING: Function get() = Function(1L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeUVFunc.FUNC_MAX
             */
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUVFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeUVFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeUVFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeUVFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeUVFunc(GodotHandle(handle))

        /** A new VisualShaderNodeUVFunc, GDScript's `VisualShaderNodeUVFunc.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeUVFunc =
            RefCounted.owned(VisualShaderNodeUVFunc(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeUVFunc"))))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 765791915L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUVFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 3772902164L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUVFunc", "get_function", GET_FUNCTION_HASH)
    }
}
