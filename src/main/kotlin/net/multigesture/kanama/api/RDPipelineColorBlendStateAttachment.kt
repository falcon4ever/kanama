package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Pipeline color blend state attachment (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineColorBlendStateAttachment
 */
class RDPipelineColorBlendStateAttachment(handle: MemorySegment) : RefCounted(handle) {
    var enableBlend: Boolean
        @JvmName("enableBlendProperty")
        get() = getEnableBlend()
        @JvmName("setEnableBlendProperty")
        set(value) = setEnableBlend(value)

    var srcColorBlendFactor: Long
        @JvmName("srcColorBlendFactorProperty")
        get() = getSrcColorBlendFactor()
        @JvmName("setSrcColorBlendFactorProperty")
        set(value) = setSrcColorBlendFactor(value)

    var dstColorBlendFactor: Long
        @JvmName("dstColorBlendFactorProperty")
        get() = getDstColorBlendFactor()
        @JvmName("setDstColorBlendFactorProperty")
        set(value) = setDstColorBlendFactor(value)

    var colorBlendOp: Long
        @JvmName("colorBlendOpProperty")
        get() = getColorBlendOp()
        @JvmName("setColorBlendOpProperty")
        set(value) = setColorBlendOp(value)

    var srcAlphaBlendFactor: Long
        @JvmName("srcAlphaBlendFactorProperty")
        get() = getSrcAlphaBlendFactor()
        @JvmName("setSrcAlphaBlendFactorProperty")
        set(value) = setSrcAlphaBlendFactor(value)

    var dstAlphaBlendFactor: Long
        @JvmName("dstAlphaBlendFactorProperty")
        get() = getDstAlphaBlendFactor()
        @JvmName("setDstAlphaBlendFactorProperty")
        set(value) = setDstAlphaBlendFactor(value)

    var alphaBlendOp: Long
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

    fun setAsMix() {
        ObjectCalls.ptrcallNoArgs(setAsMixBind, handle)
    }

    fun setEnableBlend(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableBlendBind, handle, pMember)
    }

    fun getEnableBlend(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableBlendBind, handle)
    }

    fun setSrcColorBlendFactor(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSrcColorBlendFactorBind, handle, pMember)
    }

    fun getSrcColorBlendFactor(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSrcColorBlendFactorBind, handle)
    }

    fun setDstColorBlendFactor(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setDstColorBlendFactorBind, handle, pMember)
    }

    fun getDstColorBlendFactor(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDstColorBlendFactorBind, handle)
    }

    fun setColorBlendOp(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setColorBlendOpBind, handle, pMember)
    }

    fun getColorBlendOp(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getColorBlendOpBind, handle)
    }

    fun setSrcAlphaBlendFactor(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSrcAlphaBlendFactorBind, handle, pMember)
    }

    fun getSrcAlphaBlendFactor(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSrcAlphaBlendFactorBind, handle)
    }

    fun setDstAlphaBlendFactor(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setDstAlphaBlendFactorBind, handle, pMember)
    }

    fun getDstAlphaBlendFactor(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDstAlphaBlendFactorBind, handle)
    }

    fun setAlphaBlendOp(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaBlendOpBind, handle, pMember)
    }

    fun getAlphaBlendOp(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaBlendOpBind, handle)
    }

    fun setWriteR(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setWriteRBind, handle, pMember)
    }

    fun getWriteR(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getWriteRBind, handle)
    }

    fun setWriteG(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setWriteGBind, handle, pMember)
    }

    fun getWriteG(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getWriteGBind, handle)
    }

    fun setWriteB(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setWriteBBind, handle, pMember)
    }

    fun getWriteB(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getWriteBBind, handle)
    }

    fun setWriteA(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setWriteABind, handle, pMember)
    }

    fun getWriteA(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getWriteABind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDPipelineColorBlendStateAttachment? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDPipelineColorBlendStateAttachment? =
            if (handle.address() == 0L) null else RDPipelineColorBlendStateAttachment(handle)

        private const val SET_AS_MIX_HASH = 3218959716L
        private val setAsMixBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_as_mix", SET_AS_MIX_HASH)
        }

        private const val SET_ENABLE_BLEND_HASH = 2586408642L
        private val setEnableBlendBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_enable_blend", SET_ENABLE_BLEND_HASH)
        }

        private const val GET_ENABLE_BLEND_HASH = 36873697L
        private val getEnableBlendBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_enable_blend", GET_ENABLE_BLEND_HASH)
        }

        private const val SET_SRC_COLOR_BLEND_FACTOR_HASH = 2251019273L
        private val setSrcColorBlendFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_src_color_blend_factor", SET_SRC_COLOR_BLEND_FACTOR_HASH)
        }

        private const val GET_SRC_COLOR_BLEND_FACTOR_HASH = 3691288359L
        private val getSrcColorBlendFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_src_color_blend_factor", GET_SRC_COLOR_BLEND_FACTOR_HASH)
        }

        private const val SET_DST_COLOR_BLEND_FACTOR_HASH = 2251019273L
        private val setDstColorBlendFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_dst_color_blend_factor", SET_DST_COLOR_BLEND_FACTOR_HASH)
        }

        private const val GET_DST_COLOR_BLEND_FACTOR_HASH = 3691288359L
        private val getDstColorBlendFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_dst_color_blend_factor", GET_DST_COLOR_BLEND_FACTOR_HASH)
        }

        private const val SET_COLOR_BLEND_OP_HASH = 3073022720L
        private val setColorBlendOpBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_color_blend_op", SET_COLOR_BLEND_OP_HASH)
        }

        private const val GET_COLOR_BLEND_OP_HASH = 1385093561L
        private val getColorBlendOpBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_color_blend_op", GET_COLOR_BLEND_OP_HASH)
        }

        private const val SET_SRC_ALPHA_BLEND_FACTOR_HASH = 2251019273L
        private val setSrcAlphaBlendFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_src_alpha_blend_factor", SET_SRC_ALPHA_BLEND_FACTOR_HASH)
        }

        private const val GET_SRC_ALPHA_BLEND_FACTOR_HASH = 3691288359L
        private val getSrcAlphaBlendFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_src_alpha_blend_factor", GET_SRC_ALPHA_BLEND_FACTOR_HASH)
        }

        private const val SET_DST_ALPHA_BLEND_FACTOR_HASH = 2251019273L
        private val setDstAlphaBlendFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_dst_alpha_blend_factor", SET_DST_ALPHA_BLEND_FACTOR_HASH)
        }

        private const val GET_DST_ALPHA_BLEND_FACTOR_HASH = 3691288359L
        private val getDstAlphaBlendFactorBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_dst_alpha_blend_factor", GET_DST_ALPHA_BLEND_FACTOR_HASH)
        }

        private const val SET_ALPHA_BLEND_OP_HASH = 3073022720L
        private val setAlphaBlendOpBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_alpha_blend_op", SET_ALPHA_BLEND_OP_HASH)
        }

        private const val GET_ALPHA_BLEND_OP_HASH = 1385093561L
        private val getAlphaBlendOpBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_alpha_blend_op", GET_ALPHA_BLEND_OP_HASH)
        }

        private const val SET_WRITE_R_HASH = 2586408642L
        private val setWriteRBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_write_r", SET_WRITE_R_HASH)
        }

        private const val GET_WRITE_R_HASH = 36873697L
        private val getWriteRBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_write_r", GET_WRITE_R_HASH)
        }

        private const val SET_WRITE_G_HASH = 2586408642L
        private val setWriteGBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_write_g", SET_WRITE_G_HASH)
        }

        private const val GET_WRITE_G_HASH = 36873697L
        private val getWriteGBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_write_g", GET_WRITE_G_HASH)
        }

        private const val SET_WRITE_B_HASH = 2586408642L
        private val setWriteBBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_write_b", SET_WRITE_B_HASH)
        }

        private const val GET_WRITE_B_HASH = 36873697L
        private val getWriteBBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_write_b", GET_WRITE_B_HASH)
        }

        private const val SET_WRITE_A_HASH = 2586408642L
        private val setWriteABind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "set_write_a", SET_WRITE_A_HASH)
        }

        private const val GET_WRITE_A_HASH = 36873697L
        private val getWriteABind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendStateAttachment", "get_write_a", GET_WRITE_A_HASH)
        }
    }
}
