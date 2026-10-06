package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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

    var depthCompareOperator: RenderingDevice.CompareOperator
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

    var frontOpFail: RenderingDevice.StencilOperation
        @JvmName("frontOpFailProperty")
        get() = getFrontOpFail()
        @JvmName("setFrontOpFailProperty")
        set(value) = setFrontOpFail(value)

    var frontOpPass: RenderingDevice.StencilOperation
        @JvmName("frontOpPassProperty")
        get() = getFrontOpPass()
        @JvmName("setFrontOpPassProperty")
        set(value) = setFrontOpPass(value)

    var frontOpDepthFail: RenderingDevice.StencilOperation
        @JvmName("frontOpDepthFailProperty")
        get() = getFrontOpDepthFail()
        @JvmName("setFrontOpDepthFailProperty")
        set(value) = setFrontOpDepthFail(value)

    var frontOpCompare: RenderingDevice.CompareOperator
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

    var backOpFail: RenderingDevice.StencilOperation
        @JvmName("backOpFailProperty")
        get() = getBackOpFail()
        @JvmName("setBackOpFailProperty")
        set(value) = setBackOpFail(value)

    var backOpPass: RenderingDevice.StencilOperation
        @JvmName("backOpPassProperty")
        get() = getBackOpPass()
        @JvmName("setBackOpPassProperty")
        set(value) = setBackOpPass(value)

    var backOpDepthFail: RenderingDevice.StencilOperation
        @JvmName("backOpDepthFailProperty")
        get() = getBackOpDepthFail()
        @JvmName("setBackOpDepthFailProperty")
        set(value) = setBackOpDepthFail(value)

    var backOpCompare: RenderingDevice.CompareOperator
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableDepthTestBind, segment, pMember)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableDepthTestBind, segment)
    }

    /**
     * If `true`, writes to the depth buffer whenever the depth test returns `true`. Only works when
     * enable_depth_test is also `true`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_enable_depth_write
     */
    fun setEnableDepthWrite(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableDepthWriteBind, segment, pMember)
    }

    /**
     * If `true`, writes to the depth buffer whenever the depth test returns `true`. Only works when
     * enable_depth_test is also `true`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_enable_depth_write
     */
    fun getEnableDepthWrite(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableDepthWriteBind, segment)
    }

    /**
     * The method used for comparing the previous and current depth values.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_depth_compare_operator
     */
    fun setDepthCompareOperator(pMember: RenderingDevice.CompareOperator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDepthCompareOperatorBind, segment, pMember.value)
    }

    /**
     * The method used for comparing the previous and current depth values.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_depth_compare_operator
     */
    fun getDepthCompareOperator(): RenderingDevice.CompareOperator {
        checkOpen()
        return RenderingDevice.CompareOperator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDepthCompareOperatorBind, segment))
    }

    /**
     * If `true`, each depth value will be tested to see if it is between `depth_range_min` and
     * `depth_range_max`. If it is outside of these values, it is discarded.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_enable_depth_range
     */
    fun setEnableDepthRange(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableDepthRangeBind, segment, pMember)
    }

    /**
     * If `true`, each depth value will be tested to see if it is between `depth_range_min` and
     * `depth_range_max`. If it is outside of these values, it is discarded.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_enable_depth_range
     */
    fun getEnableDepthRange(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableDepthRangeBind, segment)
    }

    /**
     * The minimum depth that returns `true` for `enable_depth_range`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_depth_range_min
     */
    fun setDepthRangeMin(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthRangeMinBind, segment, pMember)
    }

    /**
     * The minimum depth that returns `true` for `enable_depth_range`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_depth_range_min
     */
    fun getDepthRangeMin(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthRangeMinBind, segment)
    }

    /**
     * The maximum depth that returns `true` for `enable_depth_range`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_depth_range_max
     */
    fun setDepthRangeMax(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthRangeMaxBind, segment, pMember)
    }

    /**
     * The maximum depth that returns `true` for `enable_depth_range`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_depth_range_max
     */
    fun getDepthRangeMax(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthRangeMaxBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableStencilBind, segment, pMember)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableStencilBind, segment)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that fail the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_fail
     */
    fun setFrontOpFail(pMember: RenderingDevice.StencilOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFrontOpFailBind, segment, pMember.value)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that fail the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_fail
     */
    fun getFrontOpFail(): RenderingDevice.StencilOperation {
        checkOpen()
        return RenderingDevice.StencilOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFrontOpFailBind, segment))
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that pass the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_pass
     */
    fun setFrontOpPass(pMember: RenderingDevice.StencilOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFrontOpPassBind, segment, pMember.value)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that pass the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_pass
     */
    fun getFrontOpPass(): RenderingDevice.StencilOperation {
        checkOpen()
        return RenderingDevice.StencilOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFrontOpPassBind, segment))
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that pass the stencil test but
     * fail the depth test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_depth_fail
     */
    fun setFrontOpDepthFail(pMember: RenderingDevice.StencilOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFrontOpDepthFailBind, segment, pMember.value)
    }

    /**
     * The operation to perform on the stencil buffer for front pixels that pass the stencil test but
     * fail the depth test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_depth_fail
     */
    fun getFrontOpDepthFail(): RenderingDevice.StencilOperation {
        checkOpen()
        return RenderingDevice.StencilOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFrontOpDepthFailBind, segment))
    }

    /**
     * The method used for comparing the previous front stencil value and `front_op_reference`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_compare
     */
    fun setFrontOpCompare(pMember: RenderingDevice.CompareOperator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFrontOpCompareBind, segment, pMember.value)
    }

    /**
     * The method used for comparing the previous front stencil value and `front_op_reference`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_compare
     */
    fun getFrontOpCompare(): RenderingDevice.CompareOperator {
        checkOpen()
        return RenderingDevice.CompareOperator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFrontOpCompareBind, segment))
    }

    /**
     * Selects which bits from the front stencil value will be compared.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_compare_mask
     */
    fun setFrontOpCompareMask(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setFrontOpCompareMaskBind, segment, pMember)
    }

    /**
     * Selects which bits from the front stencil value will be compared.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_compare_mask
     */
    fun getFrontOpCompareMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getFrontOpCompareMaskBind, segment)
    }

    /**
     * Selects which bits from the front stencil value will be changed.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_write_mask
     */
    fun setFrontOpWriteMask(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setFrontOpWriteMaskBind, segment, pMember)
    }

    /**
     * Selects which bits from the front stencil value will be changed.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_write_mask
     */
    fun getFrontOpWriteMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getFrontOpWriteMaskBind, segment)
    }

    /**
     * The value the previous front stencil value will be compared to.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_front_op_reference
     */
    fun setFrontOpReference(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setFrontOpReferenceBind, segment, pMember)
    }

    /**
     * The value the previous front stencil value will be compared to.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_front_op_reference
     */
    fun getFrontOpReference(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getFrontOpReferenceBind, segment)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that fail the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_fail
     */
    fun setBackOpFail(pMember: RenderingDevice.StencilOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBackOpFailBind, segment, pMember.value)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that fail the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_fail
     */
    fun getBackOpFail(): RenderingDevice.StencilOperation {
        checkOpen()
        return RenderingDevice.StencilOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBackOpFailBind, segment))
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that pass the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_pass
     */
    fun setBackOpPass(pMember: RenderingDevice.StencilOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBackOpPassBind, segment, pMember.value)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that pass the stencil test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_pass
     */
    fun getBackOpPass(): RenderingDevice.StencilOperation {
        checkOpen()
        return RenderingDevice.StencilOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBackOpPassBind, segment))
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that pass the stencil test but
     * fail the depth test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_depth_fail
     */
    fun setBackOpDepthFail(pMember: RenderingDevice.StencilOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBackOpDepthFailBind, segment, pMember.value)
    }

    /**
     * The operation to perform on the stencil buffer for back pixels that pass the stencil test but
     * fail the depth test.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_depth_fail
     */
    fun getBackOpDepthFail(): RenderingDevice.StencilOperation {
        checkOpen()
        return RenderingDevice.StencilOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBackOpDepthFailBind, segment))
    }

    /**
     * The method used for comparing the previous back stencil value and `back_op_reference`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_compare
     */
    fun setBackOpCompare(pMember: RenderingDevice.CompareOperator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBackOpCompareBind, segment, pMember.value)
    }

    /**
     * The method used for comparing the previous back stencil value and `back_op_reference`.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_compare
     */
    fun getBackOpCompare(): RenderingDevice.CompareOperator {
        checkOpen()
        return RenderingDevice.CompareOperator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBackOpCompareBind, segment))
    }

    /**
     * Selects which bits from the back stencil value will be compared.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_compare_mask
     */
    fun setBackOpCompareMask(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setBackOpCompareMaskBind, segment, pMember)
    }

    /**
     * Selects which bits from the back stencil value will be compared.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_compare_mask
     */
    fun getBackOpCompareMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getBackOpCompareMaskBind, segment)
    }

    /**
     * Selects which bits from the back stencil value will be changed.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_write_mask
     */
    fun setBackOpWriteMask(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setBackOpWriteMaskBind, segment, pMember)
    }

    /**
     * Selects which bits from the back stencil value will be changed.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_write_mask
     */
    fun getBackOpWriteMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getBackOpWriteMaskBind, segment)
    }

    /**
     * The value the previous back stencil value will be compared to.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.set_back_op_reference
     */
    fun setBackOpReference(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setBackOpReferenceBind, segment, pMember)
    }

    /**
     * The value the previous back stencil value will be compared to.
     *
     * Generated from Godot docs: RDPipelineDepthStencilState.get_back_op_reference
     */
    fun getBackOpReference(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getBackOpReferenceBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDPipelineDepthStencilState? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDPipelineDepthStencilState? =
            if (handle.address() == 0L) null else RefCounted.owned(RDPipelineDepthStencilState(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDPipelineDepthStencilState? =
            if (handle.address() == 0L) null else RDPipelineDepthStencilState(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ENABLE_DEPTH_TEST_HASH = 2586408642L
        @JvmField
        val setEnableDepthTestBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_enable_depth_test", SET_ENABLE_DEPTH_TEST_HASH)

        private const val GET_ENABLE_DEPTH_TEST_HASH = 36873697L
        @JvmField
        val getEnableDepthTestBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_enable_depth_test", GET_ENABLE_DEPTH_TEST_HASH)

        private const val SET_ENABLE_DEPTH_WRITE_HASH = 2586408642L
        @JvmField
        val setEnableDepthWriteBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_enable_depth_write", SET_ENABLE_DEPTH_WRITE_HASH)

        private const val GET_ENABLE_DEPTH_WRITE_HASH = 36873697L
        @JvmField
        val getEnableDepthWriteBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_enable_depth_write", GET_ENABLE_DEPTH_WRITE_HASH)

        private const val SET_DEPTH_COMPARE_OPERATOR_HASH = 2573711505L
        @JvmField
        val setDepthCompareOperatorBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_depth_compare_operator", SET_DEPTH_COMPARE_OPERATOR_HASH)

        private const val GET_DEPTH_COMPARE_OPERATOR_HASH = 269730778L
        @JvmField
        val getDepthCompareOperatorBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_depth_compare_operator", GET_DEPTH_COMPARE_OPERATOR_HASH)

        private const val SET_ENABLE_DEPTH_RANGE_HASH = 2586408642L
        @JvmField
        val setEnableDepthRangeBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_enable_depth_range", SET_ENABLE_DEPTH_RANGE_HASH)

        private const val GET_ENABLE_DEPTH_RANGE_HASH = 36873697L
        @JvmField
        val getEnableDepthRangeBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_enable_depth_range", GET_ENABLE_DEPTH_RANGE_HASH)

        private const val SET_DEPTH_RANGE_MIN_HASH = 373806689L
        @JvmField
        val setDepthRangeMinBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_depth_range_min", SET_DEPTH_RANGE_MIN_HASH)

        private const val GET_DEPTH_RANGE_MIN_HASH = 1740695150L
        @JvmField
        val getDepthRangeMinBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_depth_range_min", GET_DEPTH_RANGE_MIN_HASH)

        private const val SET_DEPTH_RANGE_MAX_HASH = 373806689L
        @JvmField
        val setDepthRangeMaxBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_depth_range_max", SET_DEPTH_RANGE_MAX_HASH)

        private const val GET_DEPTH_RANGE_MAX_HASH = 1740695150L
        @JvmField
        val getDepthRangeMaxBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_depth_range_max", GET_DEPTH_RANGE_MAX_HASH)

        private const val SET_ENABLE_STENCIL_HASH = 2586408642L
        @JvmField
        val setEnableStencilBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_enable_stencil", SET_ENABLE_STENCIL_HASH)

        private const val GET_ENABLE_STENCIL_HASH = 36873697L
        @JvmField
        val getEnableStencilBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_enable_stencil", GET_ENABLE_STENCIL_HASH)

        private const val SET_FRONT_OP_FAIL_HASH = 2092799566L
        @JvmField
        val setFrontOpFailBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_fail", SET_FRONT_OP_FAIL_HASH)

        private const val GET_FRONT_OP_FAIL_HASH = 1714732389L
        @JvmField
        val getFrontOpFailBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_fail", GET_FRONT_OP_FAIL_HASH)

        private const val SET_FRONT_OP_PASS_HASH = 2092799566L
        @JvmField
        val setFrontOpPassBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_pass", SET_FRONT_OP_PASS_HASH)

        private const val GET_FRONT_OP_PASS_HASH = 1714732389L
        @JvmField
        val getFrontOpPassBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_pass", GET_FRONT_OP_PASS_HASH)

        private const val SET_FRONT_OP_DEPTH_FAIL_HASH = 2092799566L
        @JvmField
        val setFrontOpDepthFailBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_depth_fail", SET_FRONT_OP_DEPTH_FAIL_HASH)

        private const val GET_FRONT_OP_DEPTH_FAIL_HASH = 1714732389L
        @JvmField
        val getFrontOpDepthFailBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_depth_fail", GET_FRONT_OP_DEPTH_FAIL_HASH)

        private const val SET_FRONT_OP_COMPARE_HASH = 2573711505L
        @JvmField
        val setFrontOpCompareBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_compare", SET_FRONT_OP_COMPARE_HASH)

        private const val GET_FRONT_OP_COMPARE_HASH = 269730778L
        @JvmField
        val getFrontOpCompareBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_compare", GET_FRONT_OP_COMPARE_HASH)

        private const val SET_FRONT_OP_COMPARE_MASK_HASH = 1286410249L
        @JvmField
        val setFrontOpCompareMaskBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_compare_mask", SET_FRONT_OP_COMPARE_MASK_HASH)

        private const val GET_FRONT_OP_COMPARE_MASK_HASH = 3905245786L
        @JvmField
        val getFrontOpCompareMaskBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_compare_mask", GET_FRONT_OP_COMPARE_MASK_HASH)

        private const val SET_FRONT_OP_WRITE_MASK_HASH = 1286410249L
        @JvmField
        val setFrontOpWriteMaskBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_write_mask", SET_FRONT_OP_WRITE_MASK_HASH)

        private const val GET_FRONT_OP_WRITE_MASK_HASH = 3905245786L
        @JvmField
        val getFrontOpWriteMaskBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_write_mask", GET_FRONT_OP_WRITE_MASK_HASH)

        private const val SET_FRONT_OP_REFERENCE_HASH = 1286410249L
        @JvmField
        val setFrontOpReferenceBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_front_op_reference", SET_FRONT_OP_REFERENCE_HASH)

        private const val GET_FRONT_OP_REFERENCE_HASH = 3905245786L
        @JvmField
        val getFrontOpReferenceBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_front_op_reference", GET_FRONT_OP_REFERENCE_HASH)

        private const val SET_BACK_OP_FAIL_HASH = 2092799566L
        @JvmField
        val setBackOpFailBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_fail", SET_BACK_OP_FAIL_HASH)

        private const val GET_BACK_OP_FAIL_HASH = 1714732389L
        @JvmField
        val getBackOpFailBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_fail", GET_BACK_OP_FAIL_HASH)

        private const val SET_BACK_OP_PASS_HASH = 2092799566L
        @JvmField
        val setBackOpPassBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_pass", SET_BACK_OP_PASS_HASH)

        private const val GET_BACK_OP_PASS_HASH = 1714732389L
        @JvmField
        val getBackOpPassBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_pass", GET_BACK_OP_PASS_HASH)

        private const val SET_BACK_OP_DEPTH_FAIL_HASH = 2092799566L
        @JvmField
        val setBackOpDepthFailBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_depth_fail", SET_BACK_OP_DEPTH_FAIL_HASH)

        private const val GET_BACK_OP_DEPTH_FAIL_HASH = 1714732389L
        @JvmField
        val getBackOpDepthFailBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_depth_fail", GET_BACK_OP_DEPTH_FAIL_HASH)

        private const val SET_BACK_OP_COMPARE_HASH = 2573711505L
        @JvmField
        val setBackOpCompareBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_compare", SET_BACK_OP_COMPARE_HASH)

        private const val GET_BACK_OP_COMPARE_HASH = 269730778L
        @JvmField
        val getBackOpCompareBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_compare", GET_BACK_OP_COMPARE_HASH)

        private const val SET_BACK_OP_COMPARE_MASK_HASH = 1286410249L
        @JvmField
        val setBackOpCompareMaskBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_compare_mask", SET_BACK_OP_COMPARE_MASK_HASH)

        private const val GET_BACK_OP_COMPARE_MASK_HASH = 3905245786L
        @JvmField
        val getBackOpCompareMaskBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_compare_mask", GET_BACK_OP_COMPARE_MASK_HASH)

        private const val SET_BACK_OP_WRITE_MASK_HASH = 1286410249L
        @JvmField
        val setBackOpWriteMaskBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_write_mask", SET_BACK_OP_WRITE_MASK_HASH)

        private const val GET_BACK_OP_WRITE_MASK_HASH = 3905245786L
        @JvmField
        val getBackOpWriteMaskBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_write_mask", GET_BACK_OP_WRITE_MASK_HASH)

        private const val SET_BACK_OP_REFERENCE_HASH = 1286410249L
        @JvmField
        val setBackOpReferenceBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "set_back_op_reference", SET_BACK_OP_REFERENCE_HASH)

        private const val GET_BACK_OP_REFERENCE_HASH = 3905245786L
        @JvmField
        val getBackOpReferenceBind =
            ObjectCalls.getMethodBind("RDPipelineDepthStencilState", "get_back_op_reference", GET_BACK_OP_REFERENCE_HASH)
    }
}
