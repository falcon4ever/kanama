package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: OpenXRCompositionLayer
 */
open class OpenXRCompositionLayer(handle: MemorySegment) : Node3D(handle) {
    val layerViewport: SubViewport?
        @JvmName("layerViewportProperty")
        get() = getLayerViewport()

    var useAndroidSurface: Boolean
        @JvmName("useAndroidSurfaceProperty")
        get() = getUseAndroidSurface()
        @JvmName("setUseAndroidSurfaceProperty")
        set(value) = setUseAndroidSurface(value)

    var protectedContent: Boolean
        @JvmName("protectedContentProperty")
        get() = isProtectedContent()
        @JvmName("setProtectedContentProperty")
        set(value) = setProtectedContent(value)

    var androidSurfaceSize: Vector2i
        @JvmName("androidSurfaceSizeProperty")
        get() = getAndroidSurfaceSize()
        @JvmName("setAndroidSurfaceSizeProperty")
        set(value) = setAndroidSurfaceSize(value)

    var sortOrder: Int
        @JvmName("sortOrderProperty")
        get() = getSortOrder()
        @JvmName("setSortOrderProperty")
        set(value) = setSortOrder(value)

    var alphaBlend: Boolean
        @JvmName("alphaBlendProperty")
        get() = getAlphaBlend()
        @JvmName("setAlphaBlendProperty")
        set(value) = setAlphaBlend(value)

    var enableHolePunch: Boolean
        @JvmName("enableHolePunchProperty")
        get() = getEnableHolePunch()
        @JvmName("setEnableHolePunchProperty")
        set(value) = setEnableHolePunch(value)

    var eyeVisibility: Long
        @JvmName("eyeVisibilityProperty")
        get() = getEyeVisibility()
        @JvmName("setEyeVisibilityProperty")
        set(value) = setEyeVisibility(value)

    var swapchainStateMinFilter: Long
        @JvmName("swapchainStateMinFilterProperty")
        get() = getMinFilter()
        @JvmName("setSwapchainStateMinFilterProperty")
        set(value) = setMinFilter(value)

    var swapchainStateMagFilter: Long
        @JvmName("swapchainStateMagFilterProperty")
        get() = getMagFilter()
        @JvmName("setSwapchainStateMagFilterProperty")
        set(value) = setMagFilter(value)

    var swapchainStateMipmapMode: Long
        @JvmName("swapchainStateMipmapModeProperty")
        get() = getMipmapMode()
        @JvmName("setSwapchainStateMipmapModeProperty")
        set(value) = setMipmapMode(value)

    var swapchainStateHorizontalWrap: Long
        @JvmName("swapchainStateHorizontalWrapProperty")
        get() = getHorizontalWrap()
        @JvmName("setSwapchainStateHorizontalWrapProperty")
        set(value) = setHorizontalWrap(value)

    var swapchainStateVerticalWrap: Long
        @JvmName("swapchainStateVerticalWrapProperty")
        get() = getVerticalWrap()
        @JvmName("setSwapchainStateVerticalWrapProperty")
        set(value) = setVerticalWrap(value)

    var swapchainStateRedSwizzle: Long
        @JvmName("swapchainStateRedSwizzleProperty")
        get() = getRedSwizzle()
        @JvmName("setSwapchainStateRedSwizzleProperty")
        set(value) = setRedSwizzle(value)

    var swapchainStateGreenSwizzle: Long
        @JvmName("swapchainStateGreenSwizzleProperty")
        get() = getGreenSwizzle()
        @JvmName("setSwapchainStateGreenSwizzleProperty")
        set(value) = setGreenSwizzle(value)

    var swapchainStateBlueSwizzle: Long
        @JvmName("swapchainStateBlueSwizzleProperty")
        get() = getBlueSwizzle()
        @JvmName("setSwapchainStateBlueSwizzleProperty")
        set(value) = setBlueSwizzle(value)

    var swapchainStateAlphaSwizzle: Long
        @JvmName("swapchainStateAlphaSwizzleProperty")
        get() = getAlphaSwizzle()
        @JvmName("setSwapchainStateAlphaSwizzleProperty")
        set(value) = setAlphaSwizzle(value)

    var swapchainStateMaxAnisotropy: Double
        @JvmName("swapchainStateMaxAnisotropyProperty")
        get() = getMaxAnisotropy()
        @JvmName("setSwapchainStateMaxAnisotropyProperty")
        set(value) = setMaxAnisotropy(value)

    var swapchainStateBorderColor: Color
        @JvmName("swapchainStateBorderColorProperty")
        get() = getBorderColor()
        @JvmName("setSwapchainStateBorderColorProperty")
        set(value) = setBorderColor(value)

    fun setLayerViewport(viewport: SubViewport) {
        ObjectCalls.ptrcallWithObjectArgs(setLayerViewportBind, handle, listOf(viewport.handle))
    }

    fun getLayerViewport(): SubViewport? {
        return SubViewport.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLayerViewportBind, handle))
    }

    fun setUseAndroidSurface(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAndroidSurfaceBind, handle, enable)
    }

    fun getUseAndroidSurface(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseAndroidSurfaceBind, handle)
    }

    fun setAndroidSurfaceSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setAndroidSurfaceSizeBind, handle, size)
    }

    fun getAndroidSurfaceSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getAndroidSurfaceSizeBind, handle)
    }

    fun setEnableHolePunch(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableHolePunchBind, handle, enable)
    }

    fun getEnableHolePunch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableHolePunchBind, handle)
    }

    fun setSortOrder(order: Int) {
        ObjectCalls.ptrcallWithIntArg(setSortOrderBind, handle, order)
    }

    fun getSortOrder(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSortOrderBind, handle)
    }

    fun setAlphaBlend(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAlphaBlendBind, handle, enabled)
    }

    fun getAlphaBlend(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAlphaBlendBind, handle)
    }

    fun getAndroidSurface(): JavaObject? {
        return JavaObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAndroidSurfaceBind, handle))
    }

    fun isNativelySupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNativelySupportedBind, handle)
    }

    fun isProtectedContent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProtectedContentBind, handle)
    }

    fun setProtectedContent(protectedContent: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProtectedContentBind, handle, protectedContent)
    }

    fun setMinFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMinFilterBind, handle, mode)
    }

    fun getMinFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMinFilterBind, handle)
    }

    fun setMagFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMagFilterBind, handle, mode)
    }

    fun getMagFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMagFilterBind, handle)
    }

    fun setMipmapMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMipmapModeBind, handle, mode)
    }

    fun getMipmapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMipmapModeBind, handle)
    }

    fun setHorizontalWrap(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalWrapBind, handle, mode)
    }

    fun getHorizontalWrap(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalWrapBind, handle)
    }

    fun setVerticalWrap(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalWrapBind, handle, mode)
    }

    fun getVerticalWrap(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalWrapBind, handle)
    }

    fun setRedSwizzle(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setRedSwizzleBind, handle, mode)
    }

    fun getRedSwizzle(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRedSwizzleBind, handle)
    }

    fun setGreenSwizzle(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setGreenSwizzleBind, handle, mode)
    }

    fun getGreenSwizzle(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGreenSwizzleBind, handle)
    }

    fun setBlueSwizzle(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBlueSwizzleBind, handle, mode)
    }

    fun getBlueSwizzle(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBlueSwizzleBind, handle)
    }

    fun setAlphaSwizzle(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaSwizzleBind, handle, mode)
    }

    fun getAlphaSwizzle(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaSwizzleBind, handle)
    }

    fun setMaxAnisotropy(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxAnisotropyBind, handle, value)
    }

    fun getMaxAnisotropy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxAnisotropyBind, handle)
    }

    fun setBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setBorderColorBind, handle, color)
    }

    fun getBorderColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBorderColorBind, handle)
    }

    fun setEyeVisibility(eyeVisibility: Long) {
        ObjectCalls.ptrcallWithLongArg(setEyeVisibilityBind, handle, eyeVisibility)
    }

    fun getEyeVisibility(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEyeVisibilityBind, handle)
    }

    fun intersectsRay(origin: Vector3, direction: Vector3): Vector2 {
        return ObjectCalls.ptrcallWithTwoVector3ArgsRetVector2(intersectsRayBind, handle, origin, direction)
    }

    companion object {
        const val FILTER_NEAREST: Long = 0L
        const val FILTER_LINEAR: Long = 1L
        const val FILTER_CUBIC: Long = 2L
        const val MIPMAP_MODE_DISABLED: Long = 0L
        const val MIPMAP_MODE_NEAREST: Long = 1L
        const val MIPMAP_MODE_LINEAR: Long = 2L
        const val WRAP_CLAMP_TO_BORDER: Long = 0L
        const val WRAP_CLAMP_TO_EDGE: Long = 1L
        const val WRAP_REPEAT: Long = 2L
        const val WRAP_MIRRORED_REPEAT: Long = 3L
        const val WRAP_MIRROR_CLAMP_TO_EDGE: Long = 4L
        const val SWIZZLE_RED: Long = 0L
        const val SWIZZLE_GREEN: Long = 1L
        const val SWIZZLE_BLUE: Long = 2L
        const val SWIZZLE_ALPHA: Long = 3L
        const val SWIZZLE_ZERO: Long = 4L
        const val SWIZZLE_ONE: Long = 5L
        const val EYE_VISIBILITY_BOTH: Long = 0L
        const val EYE_VISIBILITY_LEFT: Long = 1L
        const val EYE_VISIBILITY_RIGHT: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRCompositionLayer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRCompositionLayer? =
            if (handle.address() == 0L) null else OpenXRCompositionLayer(handle)

        private const val SET_LAYER_VIEWPORT_HASH = 3888077664L
        private val setLayerViewportBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_layer_viewport", SET_LAYER_VIEWPORT_HASH)
        }

        private const val GET_LAYER_VIEWPORT_HASH = 3750751911L
        private val getLayerViewportBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_layer_viewport", GET_LAYER_VIEWPORT_HASH)
        }

        private const val SET_USE_ANDROID_SURFACE_HASH = 2586408642L
        private val setUseAndroidSurfaceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_use_android_surface", SET_USE_ANDROID_SURFACE_HASH)
        }

        private const val GET_USE_ANDROID_SURFACE_HASH = 36873697L
        private val getUseAndroidSurfaceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_use_android_surface", GET_USE_ANDROID_SURFACE_HASH)
        }

        private const val SET_ANDROID_SURFACE_SIZE_HASH = 1130785943L
        private val setAndroidSurfaceSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_android_surface_size", SET_ANDROID_SURFACE_SIZE_HASH)
        }

        private const val GET_ANDROID_SURFACE_SIZE_HASH = 3690982128L
        private val getAndroidSurfaceSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_android_surface_size", GET_ANDROID_SURFACE_SIZE_HASH)
        }

        private const val SET_ENABLE_HOLE_PUNCH_HASH = 2586408642L
        private val setEnableHolePunchBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_enable_hole_punch", SET_ENABLE_HOLE_PUNCH_HASH)
        }

        private const val GET_ENABLE_HOLE_PUNCH_HASH = 36873697L
        private val getEnableHolePunchBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_enable_hole_punch", GET_ENABLE_HOLE_PUNCH_HASH)
        }

        private const val SET_SORT_ORDER_HASH = 1286410249L
        private val setSortOrderBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_sort_order", SET_SORT_ORDER_HASH)
        }

        private const val GET_SORT_ORDER_HASH = 3905245786L
        private val getSortOrderBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_sort_order", GET_SORT_ORDER_HASH)
        }

        private const val SET_ALPHA_BLEND_HASH = 2586408642L
        private val setAlphaBlendBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_alpha_blend", SET_ALPHA_BLEND_HASH)
        }

        private const val GET_ALPHA_BLEND_HASH = 36873697L
        private val getAlphaBlendBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_alpha_blend", GET_ALPHA_BLEND_HASH)
        }

        private const val GET_ANDROID_SURFACE_HASH = 3277089691L
        private val getAndroidSurfaceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_android_surface", GET_ANDROID_SURFACE_HASH)
        }

        private const val IS_NATIVELY_SUPPORTED_HASH = 36873697L
        private val isNativelySupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "is_natively_supported", IS_NATIVELY_SUPPORTED_HASH)
        }

        private const val IS_PROTECTED_CONTENT_HASH = 36873697L
        private val isProtectedContentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "is_protected_content", IS_PROTECTED_CONTENT_HASH)
        }

        private const val SET_PROTECTED_CONTENT_HASH = 2586408642L
        private val setProtectedContentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_protected_content", SET_PROTECTED_CONTENT_HASH)
        }

        private const val SET_MIN_FILTER_HASH = 3653437593L
        private val setMinFilterBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_min_filter", SET_MIN_FILTER_HASH)
        }

        private const val GET_MIN_FILTER_HASH = 845677307L
        private val getMinFilterBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_min_filter", GET_MIN_FILTER_HASH)
        }

        private const val SET_MAG_FILTER_HASH = 3653437593L
        private val setMagFilterBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_mag_filter", SET_MAG_FILTER_HASH)
        }

        private const val GET_MAG_FILTER_HASH = 845677307L
        private val getMagFilterBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_mag_filter", GET_MAG_FILTER_HASH)
        }

        private const val SET_MIPMAP_MODE_HASH = 3271133183L
        private val setMipmapModeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_mipmap_mode", SET_MIPMAP_MODE_HASH)
        }

        private const val GET_MIPMAP_MODE_HASH = 3962697095L
        private val getMipmapModeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_mipmap_mode", GET_MIPMAP_MODE_HASH)
        }

        private const val SET_HORIZONTAL_WRAP_HASH = 15634990L
        private val setHorizontalWrapBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_horizontal_wrap", SET_HORIZONTAL_WRAP_HASH)
        }

        private const val GET_HORIZONTAL_WRAP_HASH = 2798816834L
        private val getHorizontalWrapBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_horizontal_wrap", GET_HORIZONTAL_WRAP_HASH)
        }

        private const val SET_VERTICAL_WRAP_HASH = 15634990L
        private val setVerticalWrapBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_vertical_wrap", SET_VERTICAL_WRAP_HASH)
        }

        private const val GET_VERTICAL_WRAP_HASH = 2798816834L
        private val getVerticalWrapBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_vertical_wrap", GET_VERTICAL_WRAP_HASH)
        }

        private const val SET_RED_SWIZZLE_HASH = 741598951L
        private val setRedSwizzleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_red_swizzle", SET_RED_SWIZZLE_HASH)
        }

        private const val GET_RED_SWIZZLE_HASH = 2334776767L
        private val getRedSwizzleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_red_swizzle", GET_RED_SWIZZLE_HASH)
        }

        private const val SET_GREEN_SWIZZLE_HASH = 741598951L
        private val setGreenSwizzleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_green_swizzle", SET_GREEN_SWIZZLE_HASH)
        }

        private const val GET_GREEN_SWIZZLE_HASH = 2334776767L
        private val getGreenSwizzleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_green_swizzle", GET_GREEN_SWIZZLE_HASH)
        }

        private const val SET_BLUE_SWIZZLE_HASH = 741598951L
        private val setBlueSwizzleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_blue_swizzle", SET_BLUE_SWIZZLE_HASH)
        }

        private const val GET_BLUE_SWIZZLE_HASH = 2334776767L
        private val getBlueSwizzleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_blue_swizzle", GET_BLUE_SWIZZLE_HASH)
        }

        private const val SET_ALPHA_SWIZZLE_HASH = 741598951L
        private val setAlphaSwizzleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_alpha_swizzle", SET_ALPHA_SWIZZLE_HASH)
        }

        private const val GET_ALPHA_SWIZZLE_HASH = 2334776767L
        private val getAlphaSwizzleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_alpha_swizzle", GET_ALPHA_SWIZZLE_HASH)
        }

        private const val SET_MAX_ANISOTROPY_HASH = 373806689L
        private val setMaxAnisotropyBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_max_anisotropy", SET_MAX_ANISOTROPY_HASH)
        }

        private const val GET_MAX_ANISOTROPY_HASH = 1740695150L
        private val getMaxAnisotropyBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_max_anisotropy", GET_MAX_ANISOTROPY_HASH)
        }

        private const val SET_BORDER_COLOR_HASH = 2920490490L
        private val setBorderColorBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_border_color", SET_BORDER_COLOR_HASH)
        }

        private const val GET_BORDER_COLOR_HASH = 3444240500L
        private val getBorderColorBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_border_color", GET_BORDER_COLOR_HASH)
        }

        private const val SET_EYE_VISIBILITY_HASH = 156391336L
        private val setEyeVisibilityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_eye_visibility", SET_EYE_VISIBILITY_HASH)
        }

        private const val GET_EYE_VISIBILITY_HASH = 467669000L
        private val getEyeVisibilityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_eye_visibility", GET_EYE_VISIBILITY_HASH)
        }

        private const val INTERSECTS_RAY_HASH = 1091262597L
        private val intersectsRayBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "intersects_ray", INTERSECTS_RAY_HASH)
        }
    }
}
