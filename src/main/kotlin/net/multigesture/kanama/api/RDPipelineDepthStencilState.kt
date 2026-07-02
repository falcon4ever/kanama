package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Pipeline depth/stencil state (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineDepthStencilState
 */
class RDPipelineDepthStencilState(handle: MemorySegment) : RefCounted(handle) {
    var enableDepthTest: Boolean
        @JvmName("enableDepthTestProperty")
        get() = getEnableDepthTest()
        @JvmName("setEnableDepthTestProperty")
        set(value) = setEnableDepthTest(value)

    var enableDepthWrite: Boolean
        @JvmName("enableDepthWriteProperty")
        get() = getEnableDepthWrite()
        @JvmName("setEnableDepthWriteProperty")
        set(value) = setEnableDepthWrite(value)

    var depthCompareOperator: Long
        @JvmName("depthCompareOperatorProperty")
        get() = getDepthCompareOperator()
        @JvmName("setDepthCompareOperatorProperty")
        set(value) = setDepthCompareOperator(value)

    var enableDepthRange: Boolean
        @JvmName("enableDepthRangeProperty")
        get() = getEnableDepthRange()
        @JvmName("setEnableDepthRangeProperty")
        set(value) = setEnableDepthRange(value)

    var depthRangeMin: Double
        @JvmName("depthRangeMinProperty")
        get() = getDepthRangeMin()
        @JvmName("setDepthRangeMinProperty")
        set(value) = setDepthRangeMin(value)

    var depthRangeMax: Double
        @JvmName("depthRangeMaxProperty")
        get() = getDepthRangeMax()
        @JvmName("setDepthRangeMaxProperty")
        set(value) = setDepthRangeMax(value)

    var enableStencil: Boolean
        @JvmName("enableStencilProperty")
        get() = getEnableStencil()
        @JvmName("setEnableStencilProperty")
        set(value) = setEnableStencil(value)

    var frontOpFail: Long
        @JvmName("frontOpFailProperty")
        get() = getFrontOpFail()
        @JvmName("setFrontOpFailProperty")
        set(value) = setFrontOpFail(value)

    var frontOpPass: Long
        @JvmName("frontOpPassProperty")
        get() = getFrontOpPass()
        @JvmName("setFrontOpPassProperty")
        set(value) = setFrontOpPass(value)

    var frontOpDepthFail: Long
        @JvmName("frontOpDepthFailProperty")
        get() = getFrontOpDepthFail()
        @JvmName("setFrontOpDepthFailProperty")
        set(value) = setFrontOpDepthFail(value)

    var frontOpCompare: Long
        @JvmName("frontOpCompareProperty")
        get() = getFrontOpCompare()
        @JvmName("setFrontOpCompareProperty")
        set(value) = setFrontOpCompare(value)

    var frontOpCompareMask: Long
        @JvmName("frontOpCompareMaskProperty")
        get() = getFrontOpCompareMask()
        @JvmName("setFrontOpCompareMaskProperty")
        set(value) = setFrontOpCompareMask(value)

    var frontOpWriteMask: Long
        @JvmName("frontOpWriteMaskProperty")
        get() = getFrontOpWriteMask()
        @JvmName("setFrontOpWriteMaskProperty")
        set(value) = setFrontOpWriteMask(value)

    var frontOpReference: Long
        @JvmName("frontOpReferenceProperty")
        get() = getFrontOpReference()
        @JvmName("setFrontOpReferenceProperty")
        set(value) = setFrontOpReference(value)

    var backOpFail: Long
        @JvmName("backOpFailProperty")
        get() = getBackOpFail()
        @JvmName("setBackOpFailProperty")
        set(value) = setBackOpFail(value)

    var backOpPass: Long
        @JvmName("backOpPassProperty")
        get() = getBackOpPass()
        @JvmName("setBackOpPassProperty")
        set(value) = setBackOpPass(value)

    var backOpDepthFail: Long
        @JvmName("backOpDepthFailProperty")
        get() = getBackOpDepthFail()
        @JvmName("setBackOpDepthFailProperty")
        set(value) = setBackOpDepthFail(value)

    var backOpCompare: Long
        @JvmName("backOpCompareProperty")
        get() = getBackOpCompare()
        @JvmName("setBackOpCompareProperty")
        set(value) = setBackOpCompare(value)

    var backOpCompareMask: Long
        @JvmName("backOpCompareMaskProperty")
        get() = getBackOpCompareMask()
        @JvmName("setBackOpCompareMaskProperty")
        set(value) = setBackOpCompareMask(value)

    var backOpWriteMask: Long
        @JvmName("backOpWriteMaskProperty")
        get() = getBackOpWriteMask()
        @JvmName("setBackOpWriteMaskProperty")
        set(value) = setBackOpWriteMask(value)

    var backOpReference: Long
        @JvmName("backOpReferenceProperty")
        get() = getBackOpReference()
        @JvmName("setBackOpReferenceProperty")
        set(value) = setBackOpReference(value)

    fun setEnableDepthTest(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDepthTestBind, handle, pMember)
    }

    fun getEnableDepthTest(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDepthTestBind, handle)
    }

    fun setEnableDepthWrite(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDepthWriteBind, handle, pMember)
    }

    fun getEnableDepthWrite(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDepthWriteBind, handle)
    }

    fun setDepthCompareOperator(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setDepthCompareOperatorBind, handle, pMember)
    }

    fun getDepthCompareOperator(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDepthCompareOperatorBind, handle)
    }

    fun setEnableDepthRange(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDepthRangeBind, handle, pMember)
    }

    fun getEnableDepthRange(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDepthRangeBind, handle)
    }

    fun setDepthRangeMin(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthRangeMinBind, handle, pMember)
    }

    fun getDepthRangeMin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthRangeMinBind, handle)
    }

    fun setDepthRangeMax(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthRangeMaxBind, handle, pMember)
    }

    fun getDepthRangeMax(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthRangeMaxBind, handle)
    }

    fun setEnableStencil(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableStencilBind, handle, pMember)
    }

    fun getEnableStencil(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableStencilBind, handle)
    }

    fun setFrontOpFail(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFrontOpFailBind, handle, pMember)
    }

    fun getFrontOpFail(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontOpFailBind, handle)
    }

    fun setFrontOpPass(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFrontOpPassBind, handle, pMember)
    }

    fun getFrontOpPass(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontOpPassBind, handle)
    }

    fun setFrontOpDepthFail(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFrontOpDepthFailBind, handle, pMember)
    }

    fun getFrontOpDepthFail(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontOpDepthFailBind, handle)
    }

    fun setFrontOpCompare(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFrontOpCompareBind, handle, pMember)
    }

    fun getFrontOpCompare(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontOpCompareBind, handle)
    }

    fun setFrontOpCompareMask(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setFrontOpCompareMaskBind, handle, pMember)
    }

    fun getFrontOpCompareMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFrontOpCompareMaskBind, handle)
    }

    fun setFrontOpWriteMask(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setFrontOpWriteMaskBind, handle, pMember)
    }

    fun getFrontOpWriteMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFrontOpWriteMaskBind, handle)
    }

    fun setFrontOpReference(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setFrontOpReferenceBind, handle, pMember)
    }

    fun getFrontOpReference(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFrontOpReferenceBind, handle)
    }

    fun setBackOpFail(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setBackOpFailBind, handle, pMember)
    }

    fun getBackOpFail(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBackOpFailBind, handle)
    }

    fun setBackOpPass(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setBackOpPassBind, handle, pMember)
    }

    fun getBackOpPass(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBackOpPassBind, handle)
    }

    fun setBackOpDepthFail(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setBackOpDepthFailBind, handle, pMember)
    }

    fun getBackOpDepthFail(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBackOpDepthFailBind, handle)
    }

    fun setBackOpCompare(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setBackOpCompareBind, handle, pMember)
    }

    fun getBackOpCompare(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBackOpCompareBind, handle)
    }

    fun setBackOpCompareMask(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setBackOpCompareMaskBind, handle, pMember)
    }

    fun getBackOpCompareMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBackOpCompareMaskBind, handle)
    }

    fun setBackOpWriteMask(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setBackOpWriteMaskBind, handle, pMember)
    }

    fun getBackOpWriteMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBackOpWriteMaskBind, handle)
    }

    fun setBackOpReference(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setBackOpReferenceBind, handle, pMember)
    }

    fun getBackOpReference(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBackOpReferenceBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDPipelineDepthStencilState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDPipelineDepthStencilState? =
            if (handle.address() == 0L) null else RDPipelineDepthStencilState(handle)

        private const val SET_ENABLE_DEPTH_TEST_HASH = 2586408642L
        private val setEnableDepthTestBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_enable_depth_test", SET_ENABLE_DEPTH_TEST_HASH)
        }

        private const val GET_ENABLE_DEPTH_TEST_HASH = 36873697L
        private val getEnableDepthTestBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_enable_depth_test", GET_ENABLE_DEPTH_TEST_HASH)
        }

        private const val SET_ENABLE_DEPTH_WRITE_HASH = 2586408642L
        private val setEnableDepthWriteBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_enable_depth_write", SET_ENABLE_DEPTH_WRITE_HASH)
        }

        private const val GET_ENABLE_DEPTH_WRITE_HASH = 36873697L
        private val getEnableDepthWriteBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_enable_depth_write", GET_ENABLE_DEPTH_WRITE_HASH)
        }

        private const val SET_DEPTH_COMPARE_OPERATOR_HASH = 2573711505L
        private val setDepthCompareOperatorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_depth_compare_operator", SET_DEPTH_COMPARE_OPERATOR_HASH)
        }

        private const val GET_DEPTH_COMPARE_OPERATOR_HASH = 269730778L
        private val getDepthCompareOperatorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_depth_compare_operator", GET_DEPTH_COMPARE_OPERATOR_HASH)
        }

        private const val SET_ENABLE_DEPTH_RANGE_HASH = 2586408642L
        private val setEnableDepthRangeBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_enable_depth_range", SET_ENABLE_DEPTH_RANGE_HASH)
        }

        private const val GET_ENABLE_DEPTH_RANGE_HASH = 36873697L
        private val getEnableDepthRangeBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_enable_depth_range", GET_ENABLE_DEPTH_RANGE_HASH)
        }

        private const val SET_DEPTH_RANGE_MIN_HASH = 373806689L
        private val setDepthRangeMinBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_depth_range_min", SET_DEPTH_RANGE_MIN_HASH)
        }

        private const val GET_DEPTH_RANGE_MIN_HASH = 1740695150L
        private val getDepthRangeMinBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_depth_range_min", GET_DEPTH_RANGE_MIN_HASH)
        }

        private const val SET_DEPTH_RANGE_MAX_HASH = 373806689L
        private val setDepthRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_depth_range_max", SET_DEPTH_RANGE_MAX_HASH)
        }

        private const val GET_DEPTH_RANGE_MAX_HASH = 1740695150L
        private val getDepthRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_depth_range_max", GET_DEPTH_RANGE_MAX_HASH)
        }

        private const val SET_ENABLE_STENCIL_HASH = 2586408642L
        private val setEnableStencilBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_enable_stencil", SET_ENABLE_STENCIL_HASH)
        }

        private const val GET_ENABLE_STENCIL_HASH = 36873697L
        private val getEnableStencilBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_enable_stencil", GET_ENABLE_STENCIL_HASH)
        }

        private const val SET_FRONT_OP_FAIL_HASH = 2092799566L
        private val setFrontOpFailBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_fail", SET_FRONT_OP_FAIL_HASH)
        }

        private const val GET_FRONT_OP_FAIL_HASH = 1714732389L
        private val getFrontOpFailBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_fail", GET_FRONT_OP_FAIL_HASH)
        }

        private const val SET_FRONT_OP_PASS_HASH = 2092799566L
        private val setFrontOpPassBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_pass", SET_FRONT_OP_PASS_HASH)
        }

        private const val GET_FRONT_OP_PASS_HASH = 1714732389L
        private val getFrontOpPassBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_pass", GET_FRONT_OP_PASS_HASH)
        }

        private const val SET_FRONT_OP_DEPTH_FAIL_HASH = 2092799566L
        private val setFrontOpDepthFailBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_depth_fail", SET_FRONT_OP_DEPTH_FAIL_HASH)
        }

        private const val GET_FRONT_OP_DEPTH_FAIL_HASH = 1714732389L
        private val getFrontOpDepthFailBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_depth_fail", GET_FRONT_OP_DEPTH_FAIL_HASH)
        }

        private const val SET_FRONT_OP_COMPARE_HASH = 2573711505L
        private val setFrontOpCompareBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_compare", SET_FRONT_OP_COMPARE_HASH)
        }

        private const val GET_FRONT_OP_COMPARE_HASH = 269730778L
        private val getFrontOpCompareBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_compare", GET_FRONT_OP_COMPARE_HASH)
        }

        private const val SET_FRONT_OP_COMPARE_MASK_HASH = 1286410249L
        private val setFrontOpCompareMaskBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_compare_mask", SET_FRONT_OP_COMPARE_MASK_HASH)
        }

        private const val GET_FRONT_OP_COMPARE_MASK_HASH = 3905245786L
        private val getFrontOpCompareMaskBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_compare_mask", GET_FRONT_OP_COMPARE_MASK_HASH)
        }

        private const val SET_FRONT_OP_WRITE_MASK_HASH = 1286410249L
        private val setFrontOpWriteMaskBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_write_mask", SET_FRONT_OP_WRITE_MASK_HASH)
        }

        private const val GET_FRONT_OP_WRITE_MASK_HASH = 3905245786L
        private val getFrontOpWriteMaskBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_write_mask", GET_FRONT_OP_WRITE_MASK_HASH)
        }

        private const val SET_FRONT_OP_REFERENCE_HASH = 1286410249L
        private val setFrontOpReferenceBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_reference", SET_FRONT_OP_REFERENCE_HASH)
        }

        private const val GET_FRONT_OP_REFERENCE_HASH = 3905245786L
        private val getFrontOpReferenceBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_reference", GET_FRONT_OP_REFERENCE_HASH)
        }

        private const val SET_BACK_OP_FAIL_HASH = 2092799566L
        private val setBackOpFailBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_fail", SET_BACK_OP_FAIL_HASH)
        }

        private const val GET_BACK_OP_FAIL_HASH = 1714732389L
        private val getBackOpFailBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_fail", GET_BACK_OP_FAIL_HASH)
        }

        private const val SET_BACK_OP_PASS_HASH = 2092799566L
        private val setBackOpPassBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_pass", SET_BACK_OP_PASS_HASH)
        }

        private const val GET_BACK_OP_PASS_HASH = 1714732389L
        private val getBackOpPassBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_pass", GET_BACK_OP_PASS_HASH)
        }

        private const val SET_BACK_OP_DEPTH_FAIL_HASH = 2092799566L
        private val setBackOpDepthFailBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_depth_fail", SET_BACK_OP_DEPTH_FAIL_HASH)
        }

        private const val GET_BACK_OP_DEPTH_FAIL_HASH = 1714732389L
        private val getBackOpDepthFailBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_depth_fail", GET_BACK_OP_DEPTH_FAIL_HASH)
        }

        private const val SET_BACK_OP_COMPARE_HASH = 2573711505L
        private val setBackOpCompareBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_compare", SET_BACK_OP_COMPARE_HASH)
        }

        private const val GET_BACK_OP_COMPARE_HASH = 269730778L
        private val getBackOpCompareBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_compare", GET_BACK_OP_COMPARE_HASH)
        }

        private const val SET_BACK_OP_COMPARE_MASK_HASH = 1286410249L
        private val setBackOpCompareMaskBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_compare_mask", SET_BACK_OP_COMPARE_MASK_HASH)
        }

        private const val GET_BACK_OP_COMPARE_MASK_HASH = 3905245786L
        private val getBackOpCompareMaskBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_compare_mask", GET_BACK_OP_COMPARE_MASK_HASH)
        }

        private const val SET_BACK_OP_WRITE_MASK_HASH = 1286410249L
        private val setBackOpWriteMaskBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_write_mask", SET_BACK_OP_WRITE_MASK_HASH)
        }

        private const val GET_BACK_OP_WRITE_MASK_HASH = 3905245786L
        private val getBackOpWriteMaskBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_write_mask", GET_BACK_OP_WRITE_MASK_HASH)
        }

        private const val SET_BACK_OP_REFERENCE_HASH = 1286410249L
        private val setBackOpReferenceBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_reference", SET_BACK_OP_REFERENCE_HASH)
        }

        private const val GET_BACK_OP_REFERENCE_HASH = 3905245786L
        private val getBackOpReferenceBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_reference", GET_BACK_OP_REFERENCE_HASH)
        }
    }
}
