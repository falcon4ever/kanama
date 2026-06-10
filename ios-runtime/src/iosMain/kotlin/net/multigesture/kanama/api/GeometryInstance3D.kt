package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GeometryInstance3D
 */
open class GeometryInstance3D(handle: MemorySegment) : VisualInstance3D(handle) {
    var transparency: Double
        @JvmName("transparencyProperty")
        get() = getTransparency()
        @JvmName("setTransparencyProperty")
        set(value) = setTransparency(value)

    var castShadow: Long
        @JvmName("castShadowProperty")
        get() = getCastShadowsSetting()
        @JvmName("setCastShadowProperty")
        set(value) = setCastShadowsSetting(value)

    var extraCullMargin: Double
        @JvmName("extraCullMarginProperty")
        get() = getExtraCullMargin()
        @JvmName("setExtraCullMarginProperty")
        set(value) = setExtraCullMargin(value)

    var lodBias: Double
        @JvmName("lodBiasProperty")
        get() = getLodBias()
        @JvmName("setLodBiasProperty")
        set(value) = setLodBias(value)

    var ignoreOcclusionCulling: Boolean
        @JvmName("ignoreOcclusionCullingProperty")
        get() = isIgnoringOcclusionCulling()
        @JvmName("setIgnoreOcclusionCullingProperty")
        set(value) = setIgnoreOcclusionCulling(value)

    var giMode: Long
        @JvmName("giModeProperty")
        get() = getGiMode()
        @JvmName("setGiModeProperty")
        set(value) = setGiMode(value)

    var giLightmapTexelScale: Double
        @JvmName("giLightmapTexelScaleProperty")
        get() = getLightmapTexelScale()
        @JvmName("setGiLightmapTexelScaleProperty")
        set(value) = setLightmapTexelScale(value)

    var giLightmapScale: Long
        @JvmName("giLightmapScaleProperty")
        get() = getLightmapScale()
        @JvmName("setGiLightmapScaleProperty")
        set(value) = setLightmapScale(value)

    var visibilityRangeBegin: Double
        @JvmName("visibilityRangeBeginProperty")
        get() = getVisibilityRangeBegin()
        @JvmName("setVisibilityRangeBeginProperty")
        set(value) = setVisibilityRangeBegin(value)

    var visibilityRangeBeginMargin: Double
        @JvmName("visibilityRangeBeginMarginProperty")
        get() = getVisibilityRangeBeginMargin()
        @JvmName("setVisibilityRangeBeginMarginProperty")
        set(value) = setVisibilityRangeBeginMargin(value)

    var visibilityRangeEnd: Double
        @JvmName("visibilityRangeEndProperty")
        get() = getVisibilityRangeEnd()
        @JvmName("setVisibilityRangeEndProperty")
        set(value) = setVisibilityRangeEnd(value)

    var visibilityRangeEndMargin: Double
        @JvmName("visibilityRangeEndMarginProperty")
        get() = getVisibilityRangeEndMargin()
        @JvmName("setVisibilityRangeEndMarginProperty")
        set(value) = setVisibilityRangeEndMargin(value)

    var visibilityRangeFadeMode: Long
        @JvmName("visibilityRangeFadeModeProperty")
        get() = getVisibilityRangeFadeMode()
        @JvmName("setVisibilityRangeFadeModeProperty")
        set(value) = setVisibilityRangeFadeMode(value)

    fun setCastShadowsSetting(shadowCastingSetting: Long) {
        ObjectCalls.ptrcallWithLongArg(setCastShadowsSettingBind, handle, shadowCastingSetting)
    }

    fun getCastShadowsSetting(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCastShadowsSettingBind, handle)
    }

    fun setLodBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLodBiasBind, handle, bias)
    }

    fun getLodBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLodBiasBind, handle)
    }

    fun setTransparency(transparency: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTransparencyBind, handle, transparency)
    }

    fun getTransparency(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTransparencyBind, handle)
    }

    fun setVisibilityRangeEndMargin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeEndMarginBind, handle, distance)
    }

    fun getVisibilityRangeEndMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeEndMarginBind, handle)
    }

    fun setVisibilityRangeEnd(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeEndBind, handle, distance)
    }

    fun getVisibilityRangeEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeEndBind, handle)
    }

    fun setVisibilityRangeBeginMargin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeBeginMarginBind, handle, distance)
    }

    fun getVisibilityRangeBeginMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeBeginMarginBind, handle)
    }

    fun setVisibilityRangeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeBeginBind, handle, distance)
    }

    fun getVisibilityRangeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeBeginBind, handle)
    }

    fun setVisibilityRangeFadeMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibilityRangeFadeModeBind, handle, mode)
    }

    fun getVisibilityRangeFadeMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibilityRangeFadeModeBind, handle)
    }

    fun setExtraCullMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExtraCullMarginBind, handle, margin)
    }

    fun getExtraCullMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExtraCullMarginBind, handle)
    }

    fun setLightmapTexelScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLightmapTexelScaleBind, handle, scale)
    }

    fun getLightmapTexelScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLightmapTexelScaleBind, handle)
    }

    fun setLightmapScale(scale: Long) {
        ObjectCalls.ptrcallWithLongArg(setLightmapScaleBind, handle, scale)
    }

    fun getLightmapScale(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLightmapScaleBind, handle)
    }

    fun setGiMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setGiModeBind, handle, mode)
    }

    fun getGiMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGiModeBind, handle)
    }

    fun setIgnoreOcclusionCulling(ignoreCulling: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreOcclusionCullingBind, handle, ignoreCulling)
    }

    fun isIgnoringOcclusionCulling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringOcclusionCullingBind, handle)
    }

    companion object {
        const val SHADOW_CASTING_SETTING_OFF: Long = 0L
        const val SHADOW_CASTING_SETTING_ON: Long = 1L
        const val SHADOW_CASTING_SETTING_DOUBLE_SIDED: Long = 2L
        const val SHADOW_CASTING_SETTING_SHADOWS_ONLY: Long = 3L
        const val GI_MODE_DISABLED: Long = 0L
        const val GI_MODE_STATIC: Long = 1L
        const val GI_MODE_DYNAMIC: Long = 2L
        const val LIGHTMAP_SCALE_1X: Long = 0L
        const val LIGHTMAP_SCALE_2X: Long = 1L
        const val LIGHTMAP_SCALE_4X: Long = 2L
        const val LIGHTMAP_SCALE_8X: Long = 3L
        const val LIGHTMAP_SCALE_MAX: Long = 4L
        const val VISIBILITY_RANGE_FADE_DISABLED: Long = 0L
        const val VISIBILITY_RANGE_FADE_SELF: Long = 1L
        const val VISIBILITY_RANGE_FADE_DEPENDENCIES: Long = 2L

        fun fromHandle(handle: MemorySegment): GeometryInstance3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GeometryInstance3D? =
            if (handle.address() == 0L) null else GeometryInstance3D(handle)

        private const val SET_CAST_SHADOWS_SETTING_HASH = 856677339L
        private val setCastShadowsSettingBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_cast_shadows_setting", SET_CAST_SHADOWS_SETTING_HASH)
        }

        private const val GET_CAST_SHADOWS_SETTING_HASH = 3383019359L
        private val getCastShadowsSettingBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_cast_shadows_setting", GET_CAST_SHADOWS_SETTING_HASH)
        }

        private const val SET_LOD_BIAS_HASH = 373806689L
        private val setLodBiasBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_lod_bias", SET_LOD_BIAS_HASH)
        }

        private const val GET_LOD_BIAS_HASH = 1740695150L
        private val getLodBiasBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_lod_bias", GET_LOD_BIAS_HASH)
        }

        private const val SET_TRANSPARENCY_HASH = 373806689L
        private val setTransparencyBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_transparency", SET_TRANSPARENCY_HASH)
        }

        private const val GET_TRANSPARENCY_HASH = 1740695150L
        private val getTransparencyBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_transparency", GET_TRANSPARENCY_HASH)
        }

        private const val SET_VISIBILITY_RANGE_END_MARGIN_HASH = 373806689L
        private val setVisibilityRangeEndMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_end_margin", SET_VISIBILITY_RANGE_END_MARGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_END_MARGIN_HASH = 1740695150L
        private val getVisibilityRangeEndMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_end_margin", GET_VISIBILITY_RANGE_END_MARGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_END_HASH = 373806689L
        private val setVisibilityRangeEndBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_end", SET_VISIBILITY_RANGE_END_HASH)
        }

        private const val GET_VISIBILITY_RANGE_END_HASH = 1740695150L
        private val getVisibilityRangeEndBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_end", GET_VISIBILITY_RANGE_END_HASH)
        }

        private const val SET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH = 373806689L
        private val setVisibilityRangeBeginMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_begin_margin", SET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH = 1740695150L
        private val getVisibilityRangeBeginMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_begin_margin", GET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_BEGIN_HASH = 373806689L
        private val setVisibilityRangeBeginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_begin", SET_VISIBILITY_RANGE_BEGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_BEGIN_HASH = 1740695150L
        private val getVisibilityRangeBeginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_begin", GET_VISIBILITY_RANGE_BEGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_FADE_MODE_HASH = 1440117808L
        private val setVisibilityRangeFadeModeBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_fade_mode", SET_VISIBILITY_RANGE_FADE_MODE_HASH)
        }

        private const val GET_VISIBILITY_RANGE_FADE_MODE_HASH = 2067221882L
        private val getVisibilityRangeFadeModeBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_fade_mode", GET_VISIBILITY_RANGE_FADE_MODE_HASH)
        }

        private const val SET_EXTRA_CULL_MARGIN_HASH = 373806689L
        private val setExtraCullMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_extra_cull_margin", SET_EXTRA_CULL_MARGIN_HASH)
        }

        private const val GET_EXTRA_CULL_MARGIN_HASH = 1740695150L
        private val getExtraCullMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_extra_cull_margin", GET_EXTRA_CULL_MARGIN_HASH)
        }

        private const val SET_LIGHTMAP_TEXEL_SCALE_HASH = 373806689L
        private val setLightmapTexelScaleBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_lightmap_texel_scale", SET_LIGHTMAP_TEXEL_SCALE_HASH)
        }

        private const val GET_LIGHTMAP_TEXEL_SCALE_HASH = 1740695150L
        private val getLightmapTexelScaleBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_lightmap_texel_scale", GET_LIGHTMAP_TEXEL_SCALE_HASH)
        }

        private const val SET_LIGHTMAP_SCALE_HASH = 2462696582L
        private val setLightmapScaleBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_lightmap_scale", SET_LIGHTMAP_SCALE_HASH)
        }

        private const val GET_LIGHTMAP_SCALE_HASH = 798767852L
        private val getLightmapScaleBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_lightmap_scale", GET_LIGHTMAP_SCALE_HASH)
        }

        private const val SET_GI_MODE_HASH = 2548557163L
        private val setGiModeBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_gi_mode", SET_GI_MODE_HASH)
        }

        private const val GET_GI_MODE_HASH = 2188566509L
        private val getGiModeBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_gi_mode", GET_GI_MODE_HASH)
        }

        private const val SET_IGNORE_OCCLUSION_CULLING_HASH = 2586408642L
        private val setIgnoreOcclusionCullingBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_ignore_occlusion_culling", SET_IGNORE_OCCLUSION_CULLING_HASH)
        }

        private const val IS_IGNORING_OCCLUSION_CULLING_HASH = 2240911060L
        private val isIgnoringOcclusionCullingBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "is_ignoring_occlusion_culling", IS_IGNORING_OCCLUSION_CULLING_HASH)
        }
    }
}
