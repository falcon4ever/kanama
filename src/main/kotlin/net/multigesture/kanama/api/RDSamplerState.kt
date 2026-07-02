package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Sampler state (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDSamplerState
 */
class RDSamplerState(handle: MemorySegment) : RefCounted(handle) {
    var magFilter: Long
        @JvmName("magFilterProperty")
        get() = getMagFilter()
        @JvmName("setMagFilterProperty")
        set(value) = setMagFilter(value)

    var minFilter: Long
        @JvmName("minFilterProperty")
        get() = getMinFilter()
        @JvmName("setMinFilterProperty")
        set(value) = setMinFilter(value)

    var mipFilter: Long
        @JvmName("mipFilterProperty")
        get() = getMipFilter()
        @JvmName("setMipFilterProperty")
        set(value) = setMipFilter(value)

    var repeatU: Long
        @JvmName("repeatUProperty")
        get() = getRepeatU()
        @JvmName("setRepeatUProperty")
        set(value) = setRepeatU(value)

    var repeatV: Long
        @JvmName("repeatVProperty")
        get() = getRepeatV()
        @JvmName("setRepeatVProperty")
        set(value) = setRepeatV(value)

    var repeatW: Long
        @JvmName("repeatWProperty")
        get() = getRepeatW()
        @JvmName("setRepeatWProperty")
        set(value) = setRepeatW(value)

    var lodBias: Double
        @JvmName("lodBiasProperty")
        get() = getLodBias()
        @JvmName("setLodBiasProperty")
        set(value) = setLodBias(value)

    var useAnisotropy: Boolean
        @JvmName("useAnisotropyProperty")
        get() = getUseAnisotropy()
        @JvmName("setUseAnisotropyProperty")
        set(value) = setUseAnisotropy(value)

    var anisotropyMax: Double
        @JvmName("anisotropyMaxProperty")
        get() = getAnisotropyMax()
        @JvmName("setAnisotropyMaxProperty")
        set(value) = setAnisotropyMax(value)

    var enableCompare: Boolean
        @JvmName("enableCompareProperty")
        get() = getEnableCompare()
        @JvmName("setEnableCompareProperty")
        set(value) = setEnableCompare(value)

    var compareOp: Long
        @JvmName("compareOpProperty")
        get() = getCompareOp()
        @JvmName("setCompareOpProperty")
        set(value) = setCompareOp(value)

    var minLod: Double
        @JvmName("minLodProperty")
        get() = getMinLod()
        @JvmName("setMinLodProperty")
        set(value) = setMinLod(value)

    var maxLod: Double
        @JvmName("maxLodProperty")
        get() = getMaxLod()
        @JvmName("setMaxLodProperty")
        set(value) = setMaxLod(value)

    var borderColor: Long
        @JvmName("borderColorProperty")
        get() = getBorderColor()
        @JvmName("setBorderColorProperty")
        set(value) = setBorderColor(value)

    var unnormalizedUvw: Boolean
        @JvmName("unnormalizedUvwProperty")
        get() = getUnnormalizedUvw()
        @JvmName("setUnnormalizedUvwProperty")
        set(value) = setUnnormalizedUvw(value)

    fun setMagFilter(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setMagFilterBind, handle, pMember)
    }

    fun getMagFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMagFilterBind, handle)
    }

    fun setMinFilter(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setMinFilterBind, handle, pMember)
    }

    fun getMinFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMinFilterBind, handle)
    }

    fun setMipFilter(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setMipFilterBind, handle, pMember)
    }

    fun getMipFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMipFilterBind, handle)
    }

    fun setRepeatU(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setRepeatUBind, handle, pMember)
    }

    fun getRepeatU(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRepeatUBind, handle)
    }

    fun setRepeatV(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setRepeatVBind, handle, pMember)
    }

    fun getRepeatV(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRepeatVBind, handle)
    }

    fun setRepeatW(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setRepeatWBind, handle, pMember)
    }

    fun getRepeatW(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRepeatWBind, handle)
    }

    fun setLodBias(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLodBiasBind, handle, pMember)
    }

    fun getLodBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLodBiasBind, handle)
    }

    fun setUseAnisotropy(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAnisotropyBind, handle, pMember)
    }

    fun getUseAnisotropy(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseAnisotropyBind, handle)
    }

    fun setAnisotropyMax(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAnisotropyMaxBind, handle, pMember)
    }

    fun getAnisotropyMax(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAnisotropyMaxBind, handle)
    }

    fun setEnableCompare(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableCompareBind, handle, pMember)
    }

    fun getEnableCompare(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableCompareBind, handle)
    }

    fun setCompareOp(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setCompareOpBind, handle, pMember)
    }

    fun getCompareOp(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCompareOpBind, handle)
    }

    fun setMinLod(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinLodBind, handle, pMember)
    }

    fun getMinLod(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinLodBind, handle)
    }

    fun setMaxLod(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxLodBind, handle, pMember)
    }

    fun getMaxLod(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxLodBind, handle)
    }

    fun setBorderColor(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setBorderColorBind, handle, pMember)
    }

    fun getBorderColor(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBorderColorBind, handle)
    }

    fun setUnnormalizedUvw(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUnnormalizedUvwBind, handle, pMember)
    }

    fun getUnnormalizedUvw(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUnnormalizedUvwBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDSamplerState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDSamplerState? =
            if (handle.address() == 0L) null else RDSamplerState(handle)

        private const val SET_MAG_FILTER_HASH = 1493420382L
        private val setMagFilterBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_mag_filter", SET_MAG_FILTER_HASH)
        }

        private const val GET_MAG_FILTER_HASH = 2209202801L
        private val getMagFilterBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_mag_filter", GET_MAG_FILTER_HASH)
        }

        private const val SET_MIN_FILTER_HASH = 1493420382L
        private val setMinFilterBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_min_filter", SET_MIN_FILTER_HASH)
        }

        private const val GET_MIN_FILTER_HASH = 2209202801L
        private val getMinFilterBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_min_filter", GET_MIN_FILTER_HASH)
        }

        private const val SET_MIP_FILTER_HASH = 1493420382L
        private val setMipFilterBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_mip_filter", SET_MIP_FILTER_HASH)
        }

        private const val GET_MIP_FILTER_HASH = 2209202801L
        private val getMipFilterBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_mip_filter", GET_MIP_FILTER_HASH)
        }

        private const val SET_REPEAT_U_HASH = 246127626L
        private val setRepeatUBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_repeat_u", SET_REPEAT_U_HASH)
        }

        private const val GET_REPEAT_U_HASH = 3227895872L
        private val getRepeatUBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_repeat_u", GET_REPEAT_U_HASH)
        }

        private const val SET_REPEAT_V_HASH = 246127626L
        private val setRepeatVBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_repeat_v", SET_REPEAT_V_HASH)
        }

        private const val GET_REPEAT_V_HASH = 3227895872L
        private val getRepeatVBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_repeat_v", GET_REPEAT_V_HASH)
        }

        private const val SET_REPEAT_W_HASH = 246127626L
        private val setRepeatWBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_repeat_w", SET_REPEAT_W_HASH)
        }

        private const val GET_REPEAT_W_HASH = 3227895872L
        private val getRepeatWBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_repeat_w", GET_REPEAT_W_HASH)
        }

        private const val SET_LOD_BIAS_HASH = 373806689L
        private val setLodBiasBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_lod_bias", SET_LOD_BIAS_HASH)
        }

        private const val GET_LOD_BIAS_HASH = 1740695150L
        private val getLodBiasBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_lod_bias", GET_LOD_BIAS_HASH)
        }

        private const val SET_USE_ANISOTROPY_HASH = 2586408642L
        private val setUseAnisotropyBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_use_anisotropy", SET_USE_ANISOTROPY_HASH)
        }

        private const val GET_USE_ANISOTROPY_HASH = 36873697L
        private val getUseAnisotropyBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_use_anisotropy", GET_USE_ANISOTROPY_HASH)
        }

        private const val SET_ANISOTROPY_MAX_HASH = 373806689L
        private val setAnisotropyMaxBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_anisotropy_max", SET_ANISOTROPY_MAX_HASH)
        }

        private const val GET_ANISOTROPY_MAX_HASH = 1740695150L
        private val getAnisotropyMaxBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_anisotropy_max", GET_ANISOTROPY_MAX_HASH)
        }

        private const val SET_ENABLE_COMPARE_HASH = 2586408642L
        private val setEnableCompareBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_enable_compare", SET_ENABLE_COMPARE_HASH)
        }

        private const val GET_ENABLE_COMPARE_HASH = 36873697L
        private val getEnableCompareBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_enable_compare", GET_ENABLE_COMPARE_HASH)
        }

        private const val SET_COMPARE_OP_HASH = 2573711505L
        private val setCompareOpBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_compare_op", SET_COMPARE_OP_HASH)
        }

        private const val GET_COMPARE_OP_HASH = 269730778L
        private val getCompareOpBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_compare_op", GET_COMPARE_OP_HASH)
        }

        private const val SET_MIN_LOD_HASH = 373806689L
        private val setMinLodBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_min_lod", SET_MIN_LOD_HASH)
        }

        private const val GET_MIN_LOD_HASH = 1740695150L
        private val getMinLodBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_min_lod", GET_MIN_LOD_HASH)
        }

        private const val SET_MAX_LOD_HASH = 373806689L
        private val setMaxLodBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_max_lod", SET_MAX_LOD_HASH)
        }

        private const val GET_MAX_LOD_HASH = 1740695150L
        private val getMaxLodBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_max_lod", GET_MAX_LOD_HASH)
        }

        private const val SET_BORDER_COLOR_HASH = 1115869595L
        private val setBorderColorBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_border_color", SET_BORDER_COLOR_HASH)
        }

        private const val GET_BORDER_COLOR_HASH = 3514246478L
        private val getBorderColorBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_border_color", GET_BORDER_COLOR_HASH)
        }

        private const val SET_UNNORMALIZED_UVW_HASH = 2586408642L
        private val setUnnormalizedUvwBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "set_unnormalized_uvw", SET_UNNORMALIZED_UVW_HASH)
        }

        private const val GET_UNNORMALIZED_UVW_HASH = 36873697L
        private val getUnnormalizedUvwBind by lazy {
            ObjectCalls.getMethodBind("RDSamplerState", "get_unnormalized_uvw", GET_UNNORMALIZED_UVW_HASH)
        }
    }
}
