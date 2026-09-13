package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Pipeline depth/stencil state (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineDepthStencilState
 */
class RDPipelineDepthStencilState(handle: GodotHandle) : RefCounted(handle) {
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

    /**
     * If `true`, enables depth testing which allows objects to be automatically occluded by other
     * objects based on their depth. This also allows objects to be partially occluded by other
     * objects. If `false`, objects will appear in the order they were drawn (like in Godot's 2D
     * renderer).
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_enable_depth_test
     */
    fun setEnableDepthTest(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnableDepthTestBind, segment, pMember)
    }

    /**
     * If `true`, enables depth testing which allows objects to be automatically occluded by other
     * objects based on their depth. This also allows objects to be partially occluded by other
     * objects. If `false`, objects will appear in the order they were drawn (like in Godot's 2D
     * renderer).
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_enable_depth_test
     */
    fun getEnableDepthTest(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDepthTestBind, segment)
    }

    /**
     * If `true`, writes to the depth buffer whenever the depth test returns `true`. Only works when
     * enable_depth_test is also `true`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_enable_depth_write
     */
    fun setEnableDepthWrite(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnableDepthWriteBind, segment, pMember)
    }

    /**
     * If `true`, writes to the depth buffer whenever the depth test returns `true`. Only works when
     * enable_depth_test is also `true`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_enable_depth_write
     */
    fun getEnableDepthWrite(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDepthWriteBind, segment)
    }

    /**
     * The method used for comparing the previous and current depth values.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_depth_compare_operator
     */
    fun setDepthCompareOperator(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDepthCompareOperatorBind, segment, pMember)
    }

    /**
     * The method used for comparing the previous and current depth values.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_depth_compare_operator
     */
    fun getDepthCompareOperator(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getDepthCompareOperatorBind, segment)
    }

    /**
     * If `true`, each depth value will be tested to see if it is between `depth_range_min` and
     * `depth_range_max`. If it is outside of these values, it is discarded.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_enable_depth_range
     */
    fun setEnableDepthRange(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnableDepthRangeBind, segment, pMember)
    }

    /**
     * If `true`, each depth value will be tested to see if it is between `depth_range_min` and
     * `depth_range_max`. If it is outside of these values, it is discarded.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_enable_depth_range
     */
    fun getEnableDepthRange(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDepthRangeBind, segment)
    }

    /**
     * The minimum depth that returns `true` for `enable_depth_range`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_depth_range_min
     */
    fun setDepthRangeMin(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDepthRangeMinBind, segment, pMember)
    }

    /**
     * The minimum depth that returns `true` for `enable_depth_range`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_depth_range_min
     */
    fun getDepthRangeMin(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthRangeMinBind, segment)
    }

    /**
     * The maximum depth that returns `true` for `enable_depth_range`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_depth_range_max
     */
    fun setDepthRangeMax(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDepthRangeMaxBind, segment, pMember)
    }

    /**
     * The maximum depth that returns `true` for `enable_depth_range`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_depth_range_max
     */
    fun getDepthRangeMax(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthRangeMaxBind, segment)
    }

    /**
     * If `true`, enables stencil testing. There are separate stencil buffers for front-facing
     * triangles and back-facing triangles. See properties that begin with "front_op" and properties
     * with "back_op" for each.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_enable_stencil
     */
    fun setEnableStencil(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnableStencilBind, segment, pMember)
    }

    /**
     * If `true`, enables stencil testing. There are separate stencil buffers for front-facing
     * triangles and back-facing triangles. See properties that begin with "front_op" and properties
     * with "back_op" for each.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_enable_stencil
     */
    fun getEnableStencil(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableStencilBind, segment)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that fail the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_fail
     */
    fun setFrontOpFail(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFrontOpFailBind, segment, pMember)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that fail the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_fail
     */
    fun getFrontOpFail(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontOpFailBind, segment)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that pass the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_pass
     */
    fun setFrontOpPass(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFrontOpPassBind, segment, pMember)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that pass the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_pass
     */
    fun getFrontOpPass(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontOpPassBind, segment)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that pass the stencil test but
     * fail the depth test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_depth_fail
     */
    fun setFrontOpDepthFail(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFrontOpDepthFailBind, segment, pMember)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that pass the stencil test but
     * fail the depth test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_depth_fail
     */
    fun getFrontOpDepthFail(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontOpDepthFailBind, segment)
    }

    /**
     * The method used for comparing the previous front stencil value and `front_op_reference`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_compare
     */
    fun setFrontOpCompare(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFrontOpCompareBind, segment, pMember)
    }

    /**
     * The method used for comparing the previous front stencil value and `front_op_reference`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_compare
     */
    fun getFrontOpCompare(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontOpCompareBind, segment)
    }

    /**
     * Selects which bits from the front stencil value will be compared.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_compare_mask
     */
    fun setFrontOpCompareMask(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setFrontOpCompareMaskBind, segment, pMember)
    }

    /**
     * Selects which bits from the front stencil value will be compared.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_compare_mask
     */
    fun getFrontOpCompareMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFrontOpCompareMaskBind, segment)
    }

    /**
     * Selects which bits from the front stencil value will be changed.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_write_mask
     */
    fun setFrontOpWriteMask(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setFrontOpWriteMaskBind, segment, pMember)
    }

    /**
     * Selects which bits from the front stencil value will be changed.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_write_mask
     */
    fun getFrontOpWriteMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFrontOpWriteMaskBind, segment)
    }

    /**
     * The value the previous front stencil value will be compared to.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_reference
     */
    fun setFrontOpReference(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setFrontOpReferenceBind, segment, pMember)
    }

    /**
     * The value the previous front stencil value will be compared to.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_reference
     */
    fun getFrontOpReference(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFrontOpReferenceBind, segment)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that fail the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_fail
     */
    fun setBackOpFail(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBackOpFailBind, segment, pMember)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that fail the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_fail
     */
    fun getBackOpFail(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBackOpFailBind, segment)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that pass the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_pass
     */
    fun setBackOpPass(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBackOpPassBind, segment, pMember)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that pass the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_pass
     */
    fun getBackOpPass(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBackOpPassBind, segment)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that pass the stencil test but
     * fail the depth test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_depth_fail
     */
    fun setBackOpDepthFail(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBackOpDepthFailBind, segment, pMember)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that pass the stencil test but
     * fail the depth test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_depth_fail
     */
    fun getBackOpDepthFail(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBackOpDepthFailBind, segment)
    }

    /**
     * The method used for comparing the previous back stencil value and `back_op_reference`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_compare
     */
    fun setBackOpCompare(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBackOpCompareBind, segment, pMember)
    }

    /**
     * The method used for comparing the previous back stencil value and `back_op_reference`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_compare
     */
    fun getBackOpCompare(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBackOpCompareBind, segment)
    }

    /**
     * Selects which bits from the back stencil value will be compared.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_compare_mask
     */
    fun setBackOpCompareMask(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setBackOpCompareMaskBind, segment, pMember)
    }

    /**
     * Selects which bits from the back stencil value will be compared.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_compare_mask
     */
    fun getBackOpCompareMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBackOpCompareMaskBind, segment)
    }

    /**
     * Selects which bits from the back stencil value will be changed.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_write_mask
     */
    fun setBackOpWriteMask(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setBackOpWriteMaskBind, segment, pMember)
    }

    /**
     * Selects which bits from the back stencil value will be changed.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_write_mask
     */
    fun getBackOpWriteMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBackOpWriteMaskBind, segment)
    }

    /**
     * The value the previous back stencil value will be compared to.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_reference
     */
    fun setBackOpReference(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setBackOpReferenceBind, segment, pMember)
    }

    /**
     * The value the previous back stencil value will be compared to.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_reference
     */
    fun getBackOpReference(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBackOpReferenceBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDPipelineDepthStencilState? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): RDPipelineDepthStencilState? =
            if (handle.address() == 0L) null else RDPipelineDepthStencilState(GodotHandle(handle))

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
