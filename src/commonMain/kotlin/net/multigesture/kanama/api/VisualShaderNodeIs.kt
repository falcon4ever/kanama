package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A boolean comparison operator to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeIs
 */
class VisualShaderNodeIs(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeIs.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    /**
     * The comparison function.
     *
     * Generated from Godot docs: VisualShaderNodeIs.set_function
     */
    fun setFunction(func: VisualShaderNodeIs.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The comparison function.
     *
     * Generated from Godot docs: VisualShaderNodeIs.get_function
     */
    fun getFunction(): VisualShaderNodeIs.Function {
        checkOpen()
        val ret = VisualShaderNodeIs.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeIs.Function` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNodeIs.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeIs.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Comparison with `INF` (Infinity).
             *
             * Generated from Godot docs: VisualShaderNodeIs.FUNC_IS_INF
             */
            val IS_INF: Function get() = Function(0L)
            /**
             * Comparison with `NaN` (Not a Number; indicates invalid numeric results, such as division by
             * zero).
             *
             * Generated from Godot docs: VisualShaderNodeIs.FUNC_IS_NAN
             */
            val IS_NAN: Function get() = Function(1L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeIs.FUNC_MAX
             */
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIs? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeIs? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeIs(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeIs? =
            if (handle.address() == 0L) null else VisualShaderNodeIs(GodotHandle(handle))

        /** A new VisualShaderNodeIs, GDScript's `VisualShaderNodeIs.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeIs =
            RefCounted.owned(VisualShaderNodeIs(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeIs"))))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 1438374690L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIs", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 580678557L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIs", "get_function", GET_FUNCTION_HASH)
    }
}
