package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Pipeline rasterization state (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineRasterizationState
 */
class RDPipelineRasterizationState(handle: GodotHandle) : RefCounted(handle) {
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

    var cullMode: RenderingDevice.PolygonCullMode
        @JvmName("cullModeProperty")
        get() = getCullMode()
        @JvmName("setCullModeProperty")
        set(value) = setCullMode(value)

    var frontFace: RenderingDevice.PolygonFrontFace
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

    /**
     * If `true`, clamps depth values according to the minimum and maximum depth of the associated
     * viewport.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_enable_depth_clamp
     */
    fun setEnableDepthClamp(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableDepthClampBind, segment, pMember)
    }

    /**
     * If `true`, clamps depth values according to the minimum and maximum depth of the associated
     * viewport.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_enable_depth_clamp
     */
    fun getEnableDepthClamp(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableDepthClampBind, segment)
    }

    /**
     * If `true`, primitives are discarded immediately before the rasterization stage.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_discard_primitives
     */
    fun setDiscardPrimitives(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDiscardPrimitivesBind, segment, pMember)
    }

    /**
     * If `true`, primitives are discarded immediately before the rasterization stage.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_discard_primitives
     */
    fun getDiscardPrimitives(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDiscardPrimitivesBind, segment)
    }

    /**
     * If `true`, performs wireframe rendering for triangles instead of flat or textured rendering.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_wireframe
     */
    fun setWireframe(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setWireframeBind, segment, pMember)
    }

    /**
     * If `true`, performs wireframe rendering for triangles instead of flat or textured rendering.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_wireframe
     */
    fun getWireframe(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getWireframeBind, segment)
    }

    /**
     * The cull mode to use when drawing polygons, which determines whether front faces or backfaces
     * are hidden.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_cull_mode
     */
    fun setCullMode(pMember: RenderingDevice.PolygonCullMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCullModeBind, segment, pMember.value)
    }

    /**
     * The cull mode to use when drawing polygons, which determines whether front faces or backfaces
     * are hidden.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_cull_mode
     */
    fun getCullMode(): RenderingDevice.PolygonCullMode {
        checkOpen()
        return RenderingDevice.PolygonCullMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCullModeBind, segment))
    }

    /**
     * The winding order to use to determine which face of a triangle is considered its front face.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_front_face
     */
    fun setFrontFace(pMember: RenderingDevice.PolygonFrontFace) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFrontFaceBind, segment, pMember.value)
    }

    /**
     * The winding order to use to determine which face of a triangle is considered its front face.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_front_face
     */
    fun getFrontFace(): RenderingDevice.PolygonFrontFace {
        checkOpen()
        return RenderingDevice.PolygonFrontFace(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFrontFaceBind, segment))
    }

    /**
     * If `true`, each generated depth value will by offset by some amount. The specific amount is
     * generated per polygon based on the values of `depth_bias_slope_factor` and
     * `depth_bias_constant_factor`.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_depth_bias_enabled
     */
    fun setDepthBiasEnabled(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDepthBiasEnabledBind, segment, pMember)
    }

    /**
     * If `true`, each generated depth value will by offset by some amount. The specific amount is
     * generated per polygon based on the values of `depth_bias_slope_factor` and
     * `depth_bias_constant_factor`.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_depth_bias_enabled
     */
    fun getDepthBiasEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDepthBiasEnabledBind, segment)
    }

    /**
     * A constant offset added to each depth value. Applied after `depth_bias_slope_factor`.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_depth_bias_constant_factor
     */
    fun setDepthBiasConstantFactor(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthBiasConstantFactorBind, segment, pMember)
    }

    /**
     * A constant offset added to each depth value. Applied after `depth_bias_slope_factor`.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_depth_bias_constant_factor
     */
    fun getDepthBiasConstantFactor(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthBiasConstantFactorBind, segment)
    }

    /**
     * A limit for how much each depth value can be offset. If negative, it serves as a minimum value,
     * but if positive, it serves as a maximum value.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_depth_bias_clamp
     */
    fun setDepthBiasClamp(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthBiasClampBind, segment, pMember)
    }

    /**
     * A limit for how much each depth value can be offset. If negative, it serves as a minimum value,
     * but if positive, it serves as a maximum value.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_depth_bias_clamp
     */
    fun getDepthBiasClamp(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthBiasClampBind, segment)
    }

    /**
     * A constant scale applied to the slope of each polygons' depth. Applied before
     * `depth_bias_constant_factor`.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_depth_bias_slope_factor
     */
    fun setDepthBiasSlopeFactor(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthBiasSlopeFactorBind, segment, pMember)
    }

    /**
     * A constant scale applied to the slope of each polygons' depth. Applied before
     * `depth_bias_constant_factor`.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_depth_bias_slope_factor
     */
    fun getDepthBiasSlopeFactor(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthBiasSlopeFactorBind, segment)
    }

    /**
     * The line width to use when drawing lines (in pixels). Thick lines may not be supported on all
     * hardware.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_line_width
     */
    fun setLineWidth(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLineWidthBind, segment, pMember)
    }

    /**
     * The line width to use when drawing lines (in pixels). Thick lines may not be supported on all
     * hardware.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_line_width
     */
    fun getLineWidth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLineWidthBind, segment)
    }

    /**
     * The number of control points to use when drawing a patch with tessellation enabled. Higher
     * values result in higher quality at the cost of performance.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.set_patch_control_points
     */
    fun setPatchControlPoints(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setPatchControlPointsBind, segment, pMember)
    }

    /**
     * The number of control points to use when drawing a patch with tessellation enabled. Higher
     * values result in higher quality at the cost of performance.
     *
     * Generated from Godot docs: RDPipelineRasterizationState.get_patch_control_points
     */
    fun getPatchControlPoints(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getPatchControlPointsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDPipelineRasterizationState? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDPipelineRasterizationState? =
            if (handle.address() == 0L) null else RefCounted.owned(RDPipelineRasterizationState(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDPipelineRasterizationState? =
            if (handle.address() == 0L) null else RDPipelineRasterizationState(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ENABLE_DEPTH_CLAMP_HASH = 2586408642L
        @JvmField
        val setEnableDepthClampBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_enable_depth_clamp", SET_ENABLE_DEPTH_CLAMP_HASH)

        private const val GET_ENABLE_DEPTH_CLAMP_HASH = 36873697L
        @JvmField
        val getEnableDepthClampBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_enable_depth_clamp", GET_ENABLE_DEPTH_CLAMP_HASH)

        private const val SET_DISCARD_PRIMITIVES_HASH = 2586408642L
        @JvmField
        val setDiscardPrimitivesBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_discard_primitives", SET_DISCARD_PRIMITIVES_HASH)

        private const val GET_DISCARD_PRIMITIVES_HASH = 36873697L
        @JvmField
        val getDiscardPrimitivesBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_discard_primitives", GET_DISCARD_PRIMITIVES_HASH)

        private const val SET_WIREFRAME_HASH = 2586408642L
        @JvmField
        val setWireframeBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_wireframe", SET_WIREFRAME_HASH)

        private const val GET_WIREFRAME_HASH = 36873697L
        @JvmField
        val getWireframeBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_wireframe", GET_WIREFRAME_HASH)

        private const val SET_CULL_MODE_HASH = 2662586502L
        @JvmField
        val setCullModeBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_cull_mode", SET_CULL_MODE_HASH)

        private const val GET_CULL_MODE_HASH = 2192484313L
        @JvmField
        val getCullModeBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_cull_mode", GET_CULL_MODE_HASH)

        private const val SET_FRONT_FACE_HASH = 2637251213L
        @JvmField
        val setFrontFaceBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_front_face", SET_FRONT_FACE_HASH)

        private const val GET_FRONT_FACE_HASH = 708793786L
        @JvmField
        val getFrontFaceBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_front_face", GET_FRONT_FACE_HASH)

        private const val SET_DEPTH_BIAS_ENABLED_HASH = 2586408642L
        @JvmField
        val setDepthBiasEnabledBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_depth_bias_enabled", SET_DEPTH_BIAS_ENABLED_HASH)

        private const val GET_DEPTH_BIAS_ENABLED_HASH = 36873697L
        @JvmField
        val getDepthBiasEnabledBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_depth_bias_enabled", GET_DEPTH_BIAS_ENABLED_HASH)

        private const val SET_DEPTH_BIAS_CONSTANT_FACTOR_HASH = 373806689L
        @JvmField
        val setDepthBiasConstantFactorBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_depth_bias_constant_factor", SET_DEPTH_BIAS_CONSTANT_FACTOR_HASH)

        private const val GET_DEPTH_BIAS_CONSTANT_FACTOR_HASH = 1740695150L
        @JvmField
        val getDepthBiasConstantFactorBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_depth_bias_constant_factor", GET_DEPTH_BIAS_CONSTANT_FACTOR_HASH)

        private const val SET_DEPTH_BIAS_CLAMP_HASH = 373806689L
        @JvmField
        val setDepthBiasClampBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_depth_bias_clamp", SET_DEPTH_BIAS_CLAMP_HASH)

        private const val GET_DEPTH_BIAS_CLAMP_HASH = 1740695150L
        @JvmField
        val getDepthBiasClampBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_depth_bias_clamp", GET_DEPTH_BIAS_CLAMP_HASH)

        private const val SET_DEPTH_BIAS_SLOPE_FACTOR_HASH = 373806689L
        @JvmField
        val setDepthBiasSlopeFactorBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_depth_bias_slope_factor", SET_DEPTH_BIAS_SLOPE_FACTOR_HASH)

        private const val GET_DEPTH_BIAS_SLOPE_FACTOR_HASH = 1740695150L
        @JvmField
        val getDepthBiasSlopeFactorBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_depth_bias_slope_factor", GET_DEPTH_BIAS_SLOPE_FACTOR_HASH)

        private const val SET_LINE_WIDTH_HASH = 373806689L
        @JvmField
        val setLineWidthBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_line_width", SET_LINE_WIDTH_HASH)

        private const val GET_LINE_WIDTH_HASH = 1740695150L
        @JvmField
        val getLineWidthBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_line_width", GET_LINE_WIDTH_HASH)

        private const val SET_PATCH_CONTROL_POINTS_HASH = 1286410249L
        @JvmField
        val setPatchControlPointsBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "set_patch_control_points", SET_PATCH_CONTROL_POINTS_HASH)

        private const val GET_PATCH_CONTROL_POINTS_HASH = 3905245786L
        @JvmField
        val getPatchControlPointsBind =
            ObjectCalls.getMethodBind("RDPipelineRasterizationState", "get_patch_control_points", GET_PATCH_CONTROL_POINTS_HASH)
    }
}
