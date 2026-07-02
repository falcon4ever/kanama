package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Pipeline rasterization state (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineRasterizationState
 */
class RDPipelineRasterizationState(handle: MemorySegment) : RefCounted(handle) {
    var enableDepthClamp: Boolean
        @JvmName("enableDepthClampProperty")
        get() = getEnableDepthClamp()
        @JvmName("setEnableDepthClampProperty")
        set(value) = setEnableDepthClamp(value)

    var discardPrimitives: Boolean
        @JvmName("discardPrimitivesProperty")
        get() = getDiscardPrimitives()
        @JvmName("setDiscardPrimitivesProperty")
        set(value) = setDiscardPrimitives(value)

    var wireframe: Boolean
        @JvmName("wireframeProperty")
        get() = getWireframe()
        @JvmName("setWireframeProperty")
        set(value) = setWireframe(value)

    var cullMode: Long
        @JvmName("cullModeProperty")
        get() = getCullMode()
        @JvmName("setCullModeProperty")
        set(value) = setCullMode(value)

    var frontFace: Long
        @JvmName("frontFaceProperty")
        get() = getFrontFace()
        @JvmName("setFrontFaceProperty")
        set(value) = setFrontFace(value)

    var depthBiasEnabled: Boolean
        @JvmName("depthBiasEnabledProperty")
        get() = getDepthBiasEnabled()
        @JvmName("setDepthBiasEnabledProperty")
        set(value) = setDepthBiasEnabled(value)

    var depthBiasConstantFactor: Double
        @JvmName("depthBiasConstantFactorProperty")
        get() = getDepthBiasConstantFactor()
        @JvmName("setDepthBiasConstantFactorProperty")
        set(value) = setDepthBiasConstantFactor(value)

    var depthBiasClamp: Double
        @JvmName("depthBiasClampProperty")
        get() = getDepthBiasClamp()
        @JvmName("setDepthBiasClampProperty")
        set(value) = setDepthBiasClamp(value)

    var depthBiasSlopeFactor: Double
        @JvmName("depthBiasSlopeFactorProperty")
        get() = getDepthBiasSlopeFactor()
        @JvmName("setDepthBiasSlopeFactorProperty")
        set(value) = setDepthBiasSlopeFactor(value)

    var lineWidth: Double
        @JvmName("lineWidthProperty")
        get() = getLineWidth()
        @JvmName("setLineWidthProperty")
        set(value) = setLineWidth(value)

    var patchControlPoints: Long
        @JvmName("patchControlPointsProperty")
        get() = getPatchControlPoints()
        @JvmName("setPatchControlPointsProperty")
        set(value) = setPatchControlPoints(value)

    fun setEnableDepthClamp(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDepthClampBind, handle, pMember)
    }

    fun getEnableDepthClamp(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDepthClampBind, handle)
    }

    fun setDiscardPrimitives(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDiscardPrimitivesBind, handle, pMember)
    }

    fun getDiscardPrimitives(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDiscardPrimitivesBind, handle)
    }

    fun setWireframe(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setWireframeBind, handle, pMember)
    }

    fun getWireframe(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getWireframeBind, handle)
    }

    fun setCullMode(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setCullModeBind, handle, pMember)
    }

    fun getCullMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCullModeBind, handle)
    }

    fun setFrontFace(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFrontFaceBind, handle, pMember)
    }

    fun getFrontFace(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFrontFaceBind, handle)
    }

    fun setDepthBiasEnabled(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDepthBiasEnabledBind, handle, pMember)
    }

    fun getDepthBiasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDepthBiasEnabledBind, handle)
    }

    fun setDepthBiasConstantFactor(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBiasConstantFactorBind, handle, pMember)
    }

    fun getDepthBiasConstantFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBiasConstantFactorBind, handle)
    }

    fun setDepthBiasClamp(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBiasClampBind, handle, pMember)
    }

    fun getDepthBiasClamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBiasClampBind, handle)
    }

    fun setDepthBiasSlopeFactor(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBiasSlopeFactorBind, handle, pMember)
    }

    fun getDepthBiasSlopeFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBiasSlopeFactorBind, handle)
    }

    fun setLineWidth(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLineWidthBind, handle, pMember)
    }

    fun getLineWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineWidthBind, handle)
    }

    fun setPatchControlPoints(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setPatchControlPointsBind, handle, pMember)
    }

    fun getPatchControlPoints(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getPatchControlPointsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDPipelineRasterizationState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDPipelineRasterizationState? =
            if (handle.address() == 0L) null else RDPipelineRasterizationState(handle)

        private const val SET_ENABLE_DEPTH_CLAMP_HASH = 2586408642L
        private val setEnableDepthClampBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_enable_depth_clamp", SET_ENABLE_DEPTH_CLAMP_HASH)
        }

        private const val GET_ENABLE_DEPTH_CLAMP_HASH = 36873697L
        private val getEnableDepthClampBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_enable_depth_clamp", GET_ENABLE_DEPTH_CLAMP_HASH)
        }

        private const val SET_DISCARD_PRIMITIVES_HASH = 2586408642L
        private val setDiscardPrimitivesBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_discard_primitives", SET_DISCARD_PRIMITIVES_HASH)
        }

        private const val GET_DISCARD_PRIMITIVES_HASH = 36873697L
        private val getDiscardPrimitivesBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_discard_primitives", GET_DISCARD_PRIMITIVES_HASH)
        }

        private const val SET_WIREFRAME_HASH = 2586408642L
        private val setWireframeBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_wireframe", SET_WIREFRAME_HASH)
        }

        private const val GET_WIREFRAME_HASH = 36873697L
        private val getWireframeBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_wireframe", GET_WIREFRAME_HASH)
        }

        private const val SET_CULL_MODE_HASH = 2662586502L
        private val setCullModeBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_cull_mode", SET_CULL_MODE_HASH)
        }

        private const val GET_CULL_MODE_HASH = 2192484313L
        private val getCullModeBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_cull_mode", GET_CULL_MODE_HASH)
        }

        private const val SET_FRONT_FACE_HASH = 2637251213L
        private val setFrontFaceBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_front_face", SET_FRONT_FACE_HASH)
        }

        private const val GET_FRONT_FACE_HASH = 708793786L
        private val getFrontFaceBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_front_face", GET_FRONT_FACE_HASH)
        }

        private const val SET_DEPTH_BIAS_ENABLED_HASH = 2586408642L
        private val setDepthBiasEnabledBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_depth_bias_enabled", SET_DEPTH_BIAS_ENABLED_HASH)
        }

        private const val GET_DEPTH_BIAS_ENABLED_HASH = 36873697L
        private val getDepthBiasEnabledBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_depth_bias_enabled", GET_DEPTH_BIAS_ENABLED_HASH)
        }

        private const val SET_DEPTH_BIAS_CONSTANT_FACTOR_HASH = 373806689L
        private val setDepthBiasConstantFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_depth_bias_constant_factor", SET_DEPTH_BIAS_CONSTANT_FACTOR_HASH)
        }

        private const val GET_DEPTH_BIAS_CONSTANT_FACTOR_HASH = 1740695150L
        private val getDepthBiasConstantFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_depth_bias_constant_factor", GET_DEPTH_BIAS_CONSTANT_FACTOR_HASH)
        }

        private const val SET_DEPTH_BIAS_CLAMP_HASH = 373806689L
        private val setDepthBiasClampBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_depth_bias_clamp", SET_DEPTH_BIAS_CLAMP_HASH)
        }

        private const val GET_DEPTH_BIAS_CLAMP_HASH = 1740695150L
        private val getDepthBiasClampBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_depth_bias_clamp", GET_DEPTH_BIAS_CLAMP_HASH)
        }

        private const val SET_DEPTH_BIAS_SLOPE_FACTOR_HASH = 373806689L
        private val setDepthBiasSlopeFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_depth_bias_slope_factor", SET_DEPTH_BIAS_SLOPE_FACTOR_HASH)
        }

        private const val GET_DEPTH_BIAS_SLOPE_FACTOR_HASH = 1740695150L
        private val getDepthBiasSlopeFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_depth_bias_slope_factor", GET_DEPTH_BIAS_SLOPE_FACTOR_HASH)
        }

        private const val SET_LINE_WIDTH_HASH = 373806689L
        private val setLineWidthBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_line_width", SET_LINE_WIDTH_HASH)
        }

        private const val GET_LINE_WIDTH_HASH = 1740695150L
        private val getLineWidthBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_line_width", GET_LINE_WIDTH_HASH)
        }

        private const val SET_PATCH_CONTROL_POINTS_HASH = 1286410249L
        private val setPatchControlPointsBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_patch_control_points", SET_PATCH_CONTROL_POINTS_HASH)
        }

        private const val GET_PATCH_CONTROL_POINTS_HASH = 3905245786L
        private val getPatchControlPointsBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_patch_control_points", GET_PATCH_CONTROL_POINTS_HASH)
        }
    }
}
