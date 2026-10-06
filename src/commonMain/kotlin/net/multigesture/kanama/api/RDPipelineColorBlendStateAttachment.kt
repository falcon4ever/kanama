package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Pipeline color blend state attachment (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineColorBlendStateAttachment
 */
class RDPipelineColorBlendStateAttachment(handle: GodotHandle) : RefCounted(handle) {
    var enableBlend: Boolean
        @JvmName("enableBlendProperty")
        get() = getEnableBlend()
        @JvmName("setEnableBlendProperty")
        set(value) = setEnableBlend(value)

    var srcColorBlendFactor: RenderingDevice.BlendFactor
        @JvmName("srcColorBlendFactorProperty")
        get() = getSrcColorBlendFactor()
        @JvmName("setSrcColorBlendFactorProperty")
        set(value) = setSrcColorBlendFactor(value)

    var dstColorBlendFactor: RenderingDevice.BlendFactor
        @JvmName("dstColorBlendFactorProperty")
        get() = getDstColorBlendFactor()
        @JvmName("setDstColorBlendFactorProperty")
        set(value) = setDstColorBlendFactor(value)

    var colorBlendOp: RenderingDevice.BlendOperation
        @JvmName("colorBlendOpProperty")
        get() = getColorBlendOp()
        @JvmName("setColorBlendOpProperty")
        set(value) = setColorBlendOp(value)

    var srcAlphaBlendFactor: RenderingDevice.BlendFactor
        @JvmName("srcAlphaBlendFactorProperty")
        get() = getSrcAlphaBlendFactor()
        @JvmName("setSrcAlphaBlendFactorProperty")
        set(value) = setSrcAlphaBlendFactor(value)

    var dstAlphaBlendFactor: RenderingDevice.BlendFactor
        @JvmName("dstAlphaBlendFactorProperty")
        get() = getDstAlphaBlendFactor()
        @JvmName("setDstAlphaBlendFactorProperty")
        set(value) = setDstAlphaBlendFactor(value)

    var alphaBlendOp: RenderingDevice.BlendOperation
        @JvmName("alphaBlendOpProperty")
        get() = getAlphaBlendOp()
        @JvmName("setAlphaBlendOpProperty")
        set(value) = setAlphaBlendOp(value)

    var writeR: Boolean
        @JvmName("writeRProperty")
        get() = getWriteR()
        @JvmName("setWriteRProperty")
        set(value) = setWriteR(value)

    var writeG: Boolean
        @JvmName("writeGProperty")
        get() = getWriteG()
        @JvmName("setWriteGProperty")
        set(value) = setWriteG(value)

    var writeB: Boolean
        @JvmName("writeBProperty")
        get() = getWriteB()
        @JvmName("setWriteBProperty")
        set(value) = setWriteB(value)

    var writeA: Boolean
        @JvmName("writeAProperty")
        get() = getWriteA()
        @JvmName("setWriteAProperty")
        set(value) = setWriteA(value)

    /**
     * Convenience method to perform standard mix blending with straight (non-premultiplied) alpha.
     * This sets `enable_blend` to `true`, `src_color_blend_factor` to
     * `RenderingDevice.BlendFactor.SRC_ALPHA`, `dst_color_blend_factor` to
     * `RenderingDevice.BlendFactor.ONE_MINUS_SRC_ALPHA`, `src_alpha_blend_factor` to
     * `RenderingDevice.BlendFactor.SRC_ALPHA` and `dst_alpha_blend_factor` to
     * `RenderingDevice.BlendFactor.ONE_MINUS_SRC_ALPHA`.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_as_mix
     */
    fun setAsMix() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.setAsMixBind, segment)
    }

    /**
     * If `true`, performs blending between the source and destination according to the factors defined
     * in `src_color_blend_factor`, `dst_color_blend_factor`, `src_alpha_blend_factor` and
     * `dst_alpha_blend_factor`. The blend modes `color_blend_op` and `alpha_blend_op` are also taken
     * into account, with `write_r`, `write_g`, `write_b` and `write_a` controlling the output.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_enable_blend
     */
    fun setEnableBlend(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableBlendBind, segment, pMember)
    }

    /**
     * If `true`, performs blending between the source and destination according to the factors defined
     * in `src_color_blend_factor`, `dst_color_blend_factor`, `src_alpha_blend_factor` and
     * `dst_alpha_blend_factor`. The blend modes `color_blend_op` and `alpha_blend_op` are also taken
     * into account, with `write_r`, `write_g`, `write_b` and `write_a` controlling the output.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_enable_blend
     */
    fun getEnableBlend(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableBlendBind, segment)
    }

    /**
     * Controls how the blend factor for the color channels is determined based on the source's
     * fragments.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_src_color_blend_factor
     */
    fun setSrcColorBlendFactor(pMember: RenderingDevice.BlendFactor) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSrcColorBlendFactorBind, segment, pMember.value)
    }

    /**
     * Controls how the blend factor for the color channels is determined based on the source's
     * fragments.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_src_color_blend_factor
     */
    fun getSrcColorBlendFactor(): RenderingDevice.BlendFactor {
        checkOpen()
        return RenderingDevice.BlendFactor(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSrcColorBlendFactorBind, segment))
    }

    /**
     * Controls how the blend factor for the color channels is determined based on the destination's
     * fragments.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_dst_color_blend_factor
     */
    fun setDstColorBlendFactor(pMember: RenderingDevice.BlendFactor) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDstColorBlendFactorBind, segment, pMember.value)
    }

    /**
     * Controls how the blend factor for the color channels is determined based on the destination's
     * fragments.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_dst_color_blend_factor
     */
    fun getDstColorBlendFactor(): RenderingDevice.BlendFactor {
        checkOpen()
        return RenderingDevice.BlendFactor(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDstColorBlendFactorBind, segment))
    }

    /**
     * The blend mode to use for the red/green/blue color channels.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_color_blend_op
     */
    fun setColorBlendOp(pMember: RenderingDevice.BlendOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setColorBlendOpBind, segment, pMember.value)
    }

    /**
     * The blend mode to use for the red/green/blue color channels.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_color_blend_op
     */
    fun getColorBlendOp(): RenderingDevice.BlendOperation {
        checkOpen()
        return RenderingDevice.BlendOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getColorBlendOpBind, segment))
    }

    /**
     * Controls how the blend factor for the alpha channel is determined based on the source's
     * fragments.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_src_alpha_blend_factor
     */
    fun setSrcAlphaBlendFactor(pMember: RenderingDevice.BlendFactor) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSrcAlphaBlendFactorBind, segment, pMember.value)
    }

    /**
     * Controls how the blend factor for the alpha channel is determined based on the source's
     * fragments.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_src_alpha_blend_factor
     */
    fun getSrcAlphaBlendFactor(): RenderingDevice.BlendFactor {
        checkOpen()
        return RenderingDevice.BlendFactor(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSrcAlphaBlendFactorBind, segment))
    }

    /**
     * Controls how the blend factor for the alpha channel is determined based on the destination's
     * fragments.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_dst_alpha_blend_factor
     */
    fun setDstAlphaBlendFactor(pMember: RenderingDevice.BlendFactor) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDstAlphaBlendFactorBind, segment, pMember.value)
    }

    /**
     * Controls how the blend factor for the alpha channel is determined based on the destination's
     * fragments.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_dst_alpha_blend_factor
     */
    fun getDstAlphaBlendFactor(): RenderingDevice.BlendFactor {
        checkOpen()
        return RenderingDevice.BlendFactor(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDstAlphaBlendFactorBind, segment))
    }

    /**
     * The blend mode to use for the alpha channel.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_alpha_blend_op
     */
    fun setAlphaBlendOp(pMember: RenderingDevice.BlendOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAlphaBlendOpBind, segment, pMember.value)
    }

    /**
     * The blend mode to use for the alpha channel.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_alpha_blend_op
     */
    fun getAlphaBlendOp(): RenderingDevice.BlendOperation {
        checkOpen()
        return RenderingDevice.BlendOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlphaBlendOpBind, segment))
    }

    /**
     * If `true`, writes the new red color channel to the final result.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_write_r
     */
    fun setWriteR(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setWriteRBind, segment, pMember)
    }

    /**
     * If `true`, writes the new red color channel to the final result.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_write_r
     */
    fun getWriteR(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getWriteRBind, segment)
    }

    /**
     * If `true`, writes the new green color channel to the final result.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_write_g
     */
    fun setWriteG(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setWriteGBind, segment, pMember)
    }

    /**
     * If `true`, writes the new green color channel to the final result.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_write_g
     */
    fun getWriteG(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getWriteGBind, segment)
    }

    /**
     * If `true`, writes the new blue color channel to the final result.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_write_b
     */
    fun setWriteB(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setWriteBBind, segment, pMember)
    }

    /**
     * If `true`, writes the new blue color channel to the final result.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_write_b
     */
    fun getWriteB(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getWriteBBind, segment)
    }

    /**
     * If `true`, writes the new alpha channel to the final result.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.set_write_a
     */
    fun setWriteA(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setWriteABind, segment, pMember)
    }

    /**
     * If `true`, writes the new alpha channel to the final result.
     *
     * Generated from Godot docs: RDPipelineColorBlendStateAttachment.get_write_a
     */
    fun getWriteA(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getWriteABind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDPipelineColorBlendStateAttachment? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDPipelineColorBlendStateAttachment? =
            if (handle.address() == 0L) null else RefCounted.owned(RDPipelineColorBlendStateAttachment(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDPipelineColorBlendStateAttachment? =
            if (handle.address() == 0L) null else RDPipelineColorBlendStateAttachment(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_AS_MIX_HASH = 3218959716L
        @JvmField
        val setAsMixBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_as_mix", SET_AS_MIX_HASH)

        private const val SET_ENABLE_BLEND_HASH = 2586408642L
        @JvmField
        val setEnableBlendBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_enable_blend", SET_ENABLE_BLEND_HASH)

        private const val GET_ENABLE_BLEND_HASH = 36873697L
        @JvmField
        val getEnableBlendBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_enable_blend", GET_ENABLE_BLEND_HASH)

        private const val SET_SRC_COLOR_BLEND_FACTOR_HASH = 2251019273L
        @JvmField
        val setSrcColorBlendFactorBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_src_color_blend_factor", SET_SRC_COLOR_BLEND_FACTOR_HASH)

        private const val GET_SRC_COLOR_BLEND_FACTOR_HASH = 3691288359L
        @JvmField
        val getSrcColorBlendFactorBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_src_color_blend_factor", GET_SRC_COLOR_BLEND_FACTOR_HASH)

        private const val SET_DST_COLOR_BLEND_FACTOR_HASH = 2251019273L
        @JvmField
        val setDstColorBlendFactorBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_dst_color_blend_factor", SET_DST_COLOR_BLEND_FACTOR_HASH)

        private const val GET_DST_COLOR_BLEND_FACTOR_HASH = 3691288359L
        @JvmField
        val getDstColorBlendFactorBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_dst_color_blend_factor", GET_DST_COLOR_BLEND_FACTOR_HASH)

        private const val SET_COLOR_BLEND_OP_HASH = 3073022720L
        @JvmField
        val setColorBlendOpBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_color_blend_op", SET_COLOR_BLEND_OP_HASH)

        private const val GET_COLOR_BLEND_OP_HASH = 1385093561L
        @JvmField
        val getColorBlendOpBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_color_blend_op", GET_COLOR_BLEND_OP_HASH)

        private const val SET_SRC_ALPHA_BLEND_FACTOR_HASH = 2251019273L
        @JvmField
        val setSrcAlphaBlendFactorBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_src_alpha_blend_factor", SET_SRC_ALPHA_BLEND_FACTOR_HASH)

        private const val GET_SRC_ALPHA_BLEND_FACTOR_HASH = 3691288359L
        @JvmField
        val getSrcAlphaBlendFactorBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_src_alpha_blend_factor", GET_SRC_ALPHA_BLEND_FACTOR_HASH)

        private const val SET_DST_ALPHA_BLEND_FACTOR_HASH = 2251019273L
        @JvmField
        val setDstAlphaBlendFactorBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_dst_alpha_blend_factor", SET_DST_ALPHA_BLEND_FACTOR_HASH)

        private const val GET_DST_ALPHA_BLEND_FACTOR_HASH = 3691288359L
        @JvmField
        val getDstAlphaBlendFactorBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_dst_alpha_blend_factor", GET_DST_ALPHA_BLEND_FACTOR_HASH)

        private const val SET_ALPHA_BLEND_OP_HASH = 3073022720L
        @JvmField
        val setAlphaBlendOpBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_alpha_blend_op", SET_ALPHA_BLEND_OP_HASH)

        private const val GET_ALPHA_BLEND_OP_HASH = 1385093561L
        @JvmField
        val getAlphaBlendOpBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_alpha_blend_op", GET_ALPHA_BLEND_OP_HASH)

        private const val SET_WRITE_R_HASH = 2586408642L
        @JvmField
        val setWriteRBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_write_r", SET_WRITE_R_HASH)

        private const val GET_WRITE_R_HASH = 36873697L
        @JvmField
        val getWriteRBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_write_r", GET_WRITE_R_HASH)

        private const val SET_WRITE_G_HASH = 2586408642L
        @JvmField
        val setWriteGBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_write_g", SET_WRITE_G_HASH)

        private const val GET_WRITE_G_HASH = 36873697L
        @JvmField
        val getWriteGBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_write_g", GET_WRITE_G_HASH)

        private const val SET_WRITE_B_HASH = 2586408642L
        @JvmField
        val setWriteBBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_write_b", SET_WRITE_B_HASH)

        private const val GET_WRITE_B_HASH = 36873697L
        @JvmField
        val getWriteBBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_write_b", GET_WRITE_B_HASH)

        private const val SET_WRITE_A_HASH = 2586408642L
        @JvmField
        val setWriteABind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_write_a", SET_WRITE_A_HASH)

        private const val GET_WRITE_A_HASH = 36873697L
        @JvmField
        val getWriteABind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_write_a", GET_WRITE_A_HASH)
    }
}
