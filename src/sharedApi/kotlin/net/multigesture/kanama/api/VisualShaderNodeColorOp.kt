package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeColorOp
 */
class VisualShaderNodeColorOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: Long
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOperatorBind, segment, op)
    }

    fun getOperator(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getOperatorBind, segment)
    }

    companion object {
        const val OP_SCREEN: Long = 0L
        const val OP_DIFFERENCE: Long = 1L
        const val OP_DARKEN: Long = 2L
        const val OP_LIGHTEN: Long = 3L
        const val OP_OVERLAY: Long = 4L
        const val OP_DODGE: Long = 5L
        const val OP_BURN: Long = 6L
        const val OP_SOFT_LIGHT: Long = 7L
        const val OP_HARD_LIGHT: Long = 8L
        const val OP_MAX: Long = 9L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeColorOp? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeColorOp? =
            if (handle.address() == 0L) null else VisualShaderNodeColorOp(GodotHandle(handle))

        private const val SET_OPERATOR_HASH = 4260370673L
        private val setOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeColorOp", "set_operator", SET_OPERATOR_HASH)
        }

        private const val GET_OPERATOR_HASH = 1950956529L
        private val getOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeColorOp", "get_operator", GET_OPERATOR_HASH)
        }
    }
}
