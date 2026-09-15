package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Sampler state (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDSamplerState
 */
class RDSamplerState(handle: GodotHandle) : RefCounted(handle) {
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

    /**
     * The sampler's magnification filter. It is the filtering method used when sampling texels that
     * appear bigger than on-screen pixels.
     *
     * Generated from Godot docs: RDSamplerState.set_mag_filter
     */
    fun setMagFilter(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setMagFilterBind, segment, pMember)
    }

    /**
     * The sampler's magnification filter. It is the filtering method used when sampling texels that
     * appear bigger than on-screen pixels.
     *
     * Generated from Godot docs: RDSamplerState.get_mag_filter
     */
    fun getMagFilter(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getMagFilterBind, segment)
    }

    /**
     * The sampler's minification filter. It is the filtering method used when sampling texels that
     * appear smaller than on-screen pixels.
     *
     * Generated from Godot docs: RDSamplerState.set_min_filter
     */
    fun setMinFilter(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setMinFilterBind, segment, pMember)
    }

    /**
     * The sampler's minification filter. It is the filtering method used when sampling texels that
     * appear smaller than on-screen pixels.
     *
     * Generated from Godot docs: RDSamplerState.get_min_filter
     */
    fun getMinFilter(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getMinFilterBind, segment)
    }

    /**
     * The filtering method to use for mipmaps.
     *
     * Generated from Godot docs: RDSamplerState.set_mip_filter
     */
    fun setMipFilter(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setMipFilterBind, segment, pMember)
    }

    /**
     * The filtering method to use for mipmaps.
     *
     * Generated from Godot docs: RDSamplerState.get_mip_filter
     */
    fun getMipFilter(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getMipFilterBind, segment)
    }

    /**
     * The repeat mode to use along the U axis of UV coordinates. This affects the returned values if
     * sampling outside the UV bounds.
     *
     * Generated from Godot docs: RDSamplerState.set_repeat_u
     */
    fun setRepeatU(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setRepeatUBind, segment, pMember)
    }

    /**
     * The repeat mode to use along the U axis of UV coordinates. This affects the returned values if
     * sampling outside the UV bounds.
     *
     * Generated from Godot docs: RDSamplerState.get_repeat_u
     */
    fun getRepeatU(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getRepeatUBind, segment)
    }

    /**
     * The repeat mode to use along the V axis of UV coordinates. This affects the returned values if
     * sampling outside the UV bounds.
     *
     * Generated from Godot docs: RDSamplerState.set_repeat_v
     */
    fun setRepeatV(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setRepeatVBind, segment, pMember)
    }

    /**
     * The repeat mode to use along the V axis of UV coordinates. This affects the returned values if
     * sampling outside the UV bounds.
     *
     * Generated from Godot docs: RDSamplerState.get_repeat_v
     */
    fun getRepeatV(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getRepeatVBind, segment)
    }

    /**
     * The repeat mode to use along the W axis of UV coordinates. This affects the returned values if
     * sampling outside the UV bounds. Only effective for 3D samplers.
     *
     * Generated from Godot docs: RDSamplerState.set_repeat_w
     */
    fun setRepeatW(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setRepeatWBind, segment, pMember)
    }

    /**
     * The repeat mode to use along the W axis of UV coordinates. This affects the returned values if
     * sampling outside the UV bounds. Only effective for 3D samplers.
     *
     * Generated from Godot docs: RDSamplerState.get_repeat_w
     */
    fun getRepeatW(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getRepeatWBind, segment)
    }

    /**
     * The mipmap LOD bias to use. Positive values will make the sampler blurrier at a given distance,
     * while negative values will make the sampler sharper at a given distance (at the risk of looking
     * grainy). Recommended values are between `-0.5` and `0.0`. Only effective if the sampler has
     * mipmaps available.
     *
     * Generated from Godot docs: RDSamplerState.set_lod_bias
     */
    fun setLodBias(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setLodBiasBind, segment, pMember)
    }

    /**
     * The mipmap LOD bias to use. Positive values will make the sampler blurrier at a given distance,
     * while negative values will make the sampler sharper at a given distance (at the risk of looking
     * grainy). Recommended values are between `-0.5` and `0.0`. Only effective if the sampler has
     * mipmaps available.
     *
     * Generated from Godot docs: RDSamplerState.get_lod_bias
     */
    fun getLodBias(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getLodBiasBind, segment)
    }

    /**
     * If `true`, perform anisotropic sampling. See `anisotropy_max`.
     *
     * Generated from Godot docs: RDSamplerState.set_use_anisotropy
     */
    fun setUseAnisotropy(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUseAnisotropyBind, segment, pMember)
    }

    /**
     * If `true`, perform anisotropic sampling. See `anisotropy_max`.
     *
     * Generated from Godot docs: RDSamplerState.get_use_anisotropy
     */
    fun getUseAnisotropy(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getUseAnisotropyBind, segment)
    }

    /**
     * Maximum anisotropy that can be used when sampling. Only effective if `use_anisotropy` is `true`.
     * Higher values result in a sharper sampler at oblique angles, at the cost of performance (due to
     * memory bandwidth). This value may be limited by the graphics hardware in use. Most graphics
     * hardware only supports values up to `16.0`. If `anisotropy_max` is `1.0`, forcibly disables
     * anisotropy even if `use_anisotropy` is `true`.
     *
     * Generated from Godot docs: RDSamplerState.set_anisotropy_max
     */
    fun setAnisotropyMax(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAnisotropyMaxBind, segment, pMember)
    }

    /**
     * Maximum anisotropy that can be used when sampling. Only effective if `use_anisotropy` is `true`.
     * Higher values result in a sharper sampler at oblique angles, at the cost of performance (due to
     * memory bandwidth). This value may be limited by the graphics hardware in use. Most graphics
     * hardware only supports values up to `16.0`. If `anisotropy_max` is `1.0`, forcibly disables
     * anisotropy even if `use_anisotropy` is `true`.
     *
     * Generated from Godot docs: RDSamplerState.get_anisotropy_max
     */
    fun getAnisotropyMax(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAnisotropyMaxBind, segment)
    }

    /**
     * If `true`, returned values will be based on the comparison operation defined in `compare_op`.
     * This is a hardware-based approach and is therefore faster than performing this manually in a
     * shader. For example, compare operations are used for shadow map rendering by comparing depth
     * values from a shadow sampler.
     *
     * Generated from Godot docs: RDSamplerState.set_enable_compare
     */
    fun setEnableCompare(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnableCompareBind, segment, pMember)
    }

    /**
     * If `true`, returned values will be based on the comparison operation defined in `compare_op`.
     * This is a hardware-based approach and is therefore faster than performing this manually in a
     * shader. For example, compare operations are used for shadow map rendering by comparing depth
     * values from a shadow sampler.
     *
     * Generated from Godot docs: RDSamplerState.get_enable_compare
     */
    fun getEnableCompare(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableCompareBind, segment)
    }

    /**
     * The compare operation to use. Only effective if `enable_compare` is `true`.
     *
     * Generated from Godot docs: RDSamplerState.set_compare_op
     */
    fun setCompareOp(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCompareOpBind, segment, pMember)
    }

    /**
     * The compare operation to use. Only effective if `enable_compare` is `true`.
     *
     * Generated from Godot docs: RDSamplerState.get_compare_op
     */
    fun getCompareOp(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getCompareOpBind, segment)
    }

    /**
     * The minimum mipmap LOD bias to display (highest resolution). Only effective if the sampler has
     * mipmaps available.
     *
     * Generated from Godot docs: RDSamplerState.set_min_lod
     */
    fun setMinLod(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMinLodBind, segment, pMember)
    }

    /**
     * The minimum mipmap LOD bias to display (highest resolution). Only effective if the sampler has
     * mipmaps available.
     *
     * Generated from Godot docs: RDSamplerState.get_min_lod
     */
    fun getMinLod(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinLodBind, segment)
    }

    /**
     * The maximum mipmap LOD bias to display (lowest resolution). Only effective if the sampler has
     * mipmaps available.
     *
     * Generated from Godot docs: RDSamplerState.set_max_lod
     */
    fun setMaxLod(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMaxLodBind, segment, pMember)
    }

    /**
     * The maximum mipmap LOD bias to display (lowest resolution). Only effective if the sampler has
     * mipmaps available.
     *
     * Generated from Godot docs: RDSamplerState.get_max_lod
     */
    fun getMaxLod(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxLodBind, segment)
    }

    /**
     * The border color that will be returned when sampling outside the sampler's bounds and the
     * `repeat_u`, `repeat_v` or `repeat_w` modes have repeating disabled.
     *
     * Generated from Godot docs: RDSamplerState.set_border_color
     */
    fun setBorderColor(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBorderColorBind, segment, pMember)
    }

    /**
     * The border color that will be returned when sampling outside the sampler's bounds and the
     * `repeat_u`, `repeat_v` or `repeat_w` modes have repeating disabled.
     *
     * Generated from Godot docs: RDSamplerState.get_border_color
     */
    fun getBorderColor(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBorderColorBind, segment)
    }

    /**
     * If `true`, the texture will be sampled with coordinates ranging from 0 to the texture's
     * resolution. Otherwise, the coordinates will be normalized and range from 0 to 1.
     *
     * Generated from Godot docs: RDSamplerState.set_unnormalized_uvw
     */
    fun setUnnormalizedUvw(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUnnormalizedUvwBind, segment, pMember)
    }

    /**
     * If `true`, the texture will be sampled with coordinates ranging from 0 to the texture's
     * resolution. Otherwise, the coordinates will be normalized and range from 0 to 1.
     *
     * Generated from Godot docs: RDSamplerState.get_unnormalized_uvw
     */
    fun getUnnormalizedUvw(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getUnnormalizedUvwBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDSamplerState? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RDSamplerState? =
            if (handle.address() == 0L) null else RDSamplerState(GodotHandle(handle))

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
