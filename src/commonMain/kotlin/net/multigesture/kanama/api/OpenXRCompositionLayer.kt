package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: OpenXRCompositionLayer
 */
open class OpenXRCompositionLayer(handle: GodotHandle) : Node3D(handle) {
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

    var eyeVisibility: OpenXRCompositionLayer.EyeVisibility
        @JvmName("eyeVisibilityProperty")
        get() = getEyeVisibility()
        @JvmName("setEyeVisibilityProperty")
        set(value) = setEyeVisibility(value)

    var swapchainStateMinFilter: OpenXRCompositionLayer.Filter
        @JvmName("swapchainStateMinFilterProperty")
        get() = getMinFilter()
        @JvmName("setSwapchainStateMinFilterProperty")
        set(value) = setMinFilter(value)

    var swapchainStateMagFilter: OpenXRCompositionLayer.Filter
        @JvmName("swapchainStateMagFilterProperty")
        get() = getMagFilter()
        @JvmName("setSwapchainStateMagFilterProperty")
        set(value) = setMagFilter(value)

    var swapchainStateMipmapMode: OpenXRCompositionLayer.MipmapMode
        @JvmName("swapchainStateMipmapModeProperty")
        get() = getMipmapMode()
        @JvmName("setSwapchainStateMipmapModeProperty")
        set(value) = setMipmapMode(value)

    var swapchainStateHorizontalWrap: OpenXRCompositionLayer.Wrap
        @JvmName("swapchainStateHorizontalWrapProperty")
        get() = getHorizontalWrap()
        @JvmName("setSwapchainStateHorizontalWrapProperty")
        set(value) = setHorizontalWrap(value)

    var swapchainStateVerticalWrap: OpenXRCompositionLayer.Wrap
        @JvmName("swapchainStateVerticalWrapProperty")
        get() = getVerticalWrap()
        @JvmName("setSwapchainStateVerticalWrapProperty")
        set(value) = setVerticalWrap(value)

    var swapchainStateRedSwizzle: OpenXRCompositionLayer.Swizzle
        @JvmName("swapchainStateRedSwizzleProperty")
        get() = getRedSwizzle()
        @JvmName("setSwapchainStateRedSwizzleProperty")
        set(value) = setRedSwizzle(value)

    var swapchainStateGreenSwizzle: OpenXRCompositionLayer.Swizzle
        @JvmName("swapchainStateGreenSwizzleProperty")
        get() = getGreenSwizzle()
        @JvmName("setSwapchainStateGreenSwizzleProperty")
        set(value) = setGreenSwizzle(value)

    var swapchainStateBlueSwizzle: OpenXRCompositionLayer.Swizzle
        @JvmName("swapchainStateBlueSwizzleProperty")
        get() = getBlueSwizzle()
        @JvmName("setSwapchainStateBlueSwizzleProperty")
        set(value) = setBlueSwizzle(value)

    var swapchainStateAlphaSwizzle: OpenXRCompositionLayer.Swizzle
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setLayerViewportBind, segment, listOf(viewport.segment))
    }

    fun getLayerViewport(): SubViewport? {
        return SubViewport.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getLayerViewportBind, segment))
    }

    fun setUseAndroidSurface(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseAndroidSurfaceBind, segment, enable)
    }

    fun getUseAndroidSurface(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseAndroidSurfaceBind, segment)
    }

    fun setAndroidSurfaceSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setAndroidSurfaceSizeBind, segment, size)
    }

    fun getAndroidSurfaceSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getAndroidSurfaceSizeBind, segment)
    }

    fun setEnableHolePunch(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableHolePunchBind, segment, enable)
    }

    fun getEnableHolePunch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableHolePunchBind, segment)
    }

    fun setSortOrder(order: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSortOrderBind, segment, order)
    }

    fun getSortOrder(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSortOrderBind, segment)
    }

    fun setAlphaBlend(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAlphaBlendBind, segment, enabled)
    }

    fun getAlphaBlend(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAlphaBlendBind, segment)
    }

    fun getAndroidSurface(): JavaObject? {
        return JavaObject.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getAndroidSurfaceBind, segment))
    }

    fun isNativelySupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isNativelySupportedBind, segment)
    }

    fun isProtectedContent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isProtectedContentBind, segment)
    }

    fun setProtectedContent(protectedContent: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setProtectedContentBind, segment, protectedContent)
    }

    fun setMinFilter(mode: OpenXRCompositionLayer.Filter) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMinFilterBind, segment, mode.value)
    }

    fun getMinFilter(): OpenXRCompositionLayer.Filter {
        return OpenXRCompositionLayer.Filter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMinFilterBind, segment))
    }

    fun setMagFilter(mode: OpenXRCompositionLayer.Filter) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMagFilterBind, segment, mode.value)
    }

    fun getMagFilter(): OpenXRCompositionLayer.Filter {
        return OpenXRCompositionLayer.Filter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMagFilterBind, segment))
    }

    fun setMipmapMode(mode: OpenXRCompositionLayer.MipmapMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMipmapModeBind, segment, mode.value)
    }

    fun getMipmapMode(): OpenXRCompositionLayer.MipmapMode {
        return OpenXRCompositionLayer.MipmapMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMipmapModeBind, segment))
    }

    fun setHorizontalWrap(mode: OpenXRCompositionLayer.Wrap) {
        ObjectCalls.ptrcallWithLongArg(Binds.setHorizontalWrapBind, segment, mode.value)
    }

    fun getHorizontalWrap(): OpenXRCompositionLayer.Wrap {
        return OpenXRCompositionLayer.Wrap(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHorizontalWrapBind, segment))
    }

    fun setVerticalWrap(mode: OpenXRCompositionLayer.Wrap) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVerticalWrapBind, segment, mode.value)
    }

    fun getVerticalWrap(): OpenXRCompositionLayer.Wrap {
        return OpenXRCompositionLayer.Wrap(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVerticalWrapBind, segment))
    }

    fun setRedSwizzle(mode: OpenXRCompositionLayer.Swizzle) {
        ObjectCalls.ptrcallWithLongArg(Binds.setRedSwizzleBind, segment, mode.value)
    }

    fun getRedSwizzle(): OpenXRCompositionLayer.Swizzle {
        return OpenXRCompositionLayer.Swizzle(ObjectCalls.ptrcallNoArgsRetLong(Binds.getRedSwizzleBind, segment))
    }

    fun setGreenSwizzle(mode: OpenXRCompositionLayer.Swizzle) {
        ObjectCalls.ptrcallWithLongArg(Binds.setGreenSwizzleBind, segment, mode.value)
    }

    fun getGreenSwizzle(): OpenXRCompositionLayer.Swizzle {
        return OpenXRCompositionLayer.Swizzle(ObjectCalls.ptrcallNoArgsRetLong(Binds.getGreenSwizzleBind, segment))
    }

    fun setBlueSwizzle(mode: OpenXRCompositionLayer.Swizzle) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBlueSwizzleBind, segment, mode.value)
    }

    fun getBlueSwizzle(): OpenXRCompositionLayer.Swizzle {
        return OpenXRCompositionLayer.Swizzle(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBlueSwizzleBind, segment))
    }

    fun setAlphaSwizzle(mode: OpenXRCompositionLayer.Swizzle) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAlphaSwizzleBind, segment, mode.value)
    }

    fun getAlphaSwizzle(): OpenXRCompositionLayer.Swizzle {
        return OpenXRCompositionLayer.Swizzle(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlphaSwizzleBind, segment))
    }

    fun setMaxAnisotropy(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxAnisotropyBind, segment, value)
    }

    fun getMaxAnisotropy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxAnisotropyBind, segment)
    }

    fun setBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setBorderColorBind, segment, color)
    }

    fun getBorderColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getBorderColorBind, segment)
    }

    fun setEyeVisibility(eyeVisibility: OpenXRCompositionLayer.EyeVisibility) {
        ObjectCalls.ptrcallWithLongArg(Binds.setEyeVisibilityBind, segment, eyeVisibility.value)
    }

    fun getEyeVisibility(): OpenXRCompositionLayer.EyeVisibility {
        return OpenXRCompositionLayer.EyeVisibility(ObjectCalls.ptrcallNoArgsRetLong(Binds.getEyeVisibilityBind, segment))
    }

    fun intersectsRay(origin: Vector3, direction: Vector3): Vector2 {
        return ObjectCalls.ptrcallWithTwoVector3ArgsRetVector2(Binds.intersectsRayBind, segment, origin, direction)
    }

    @JvmInline
    value class Filter(override val value: Long) : GodotEnumValue {
        companion object {
            val NEAREST: Filter get() = Filter(0L)
            val LINEAR: Filter get() = Filter(1L)
            val CUBIC: Filter get() = Filter(2L)
        }
    }

    @JvmInline
    value class MipmapMode(override val value: Long) : GodotEnumValue {
        companion object {
            val DISABLED: MipmapMode get() = MipmapMode(0L)
            val NEAREST: MipmapMode get() = MipmapMode(1L)
            val LINEAR: MipmapMode get() = MipmapMode(2L)
        }
    }

    @JvmInline
    value class Wrap(override val value: Long) : GodotEnumValue {
        companion object {
            val CLAMP_TO_BORDER: Wrap get() = Wrap(0L)
            val CLAMP_TO_EDGE: Wrap get() = Wrap(1L)
            val REPEAT: Wrap get() = Wrap(2L)
            val MIRRORED_REPEAT: Wrap get() = Wrap(3L)
            val MIRROR_CLAMP_TO_EDGE: Wrap get() = Wrap(4L)
        }
    }

    @JvmInline
    value class Swizzle(override val value: Long) : GodotEnumValue {
        companion object {
            val RED: Swizzle get() = Swizzle(0L)
            val GREEN: Swizzle get() = Swizzle(1L)
            val BLUE: Swizzle get() = Swizzle(2L)
            val ALPHA: Swizzle get() = Swizzle(3L)
            val ZERO: Swizzle get() = Swizzle(4L)
            val ONE: Swizzle get() = Swizzle(5L)
        }
    }

    @JvmInline
    value class EyeVisibility(override val value: Long) : GodotEnumValue {
        companion object {
            val BOTH: EyeVisibility get() = EyeVisibility(0L)
            val LEFT: EyeVisibility get() = EyeVisibility(1L)
            val RIGHT: EyeVisibility get() = EyeVisibility(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRCompositionLayer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRCompositionLayer? =
            if (handle.address() == 0L) null else OpenXRCompositionLayer(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_LAYER_VIEWPORT_HASH = 3888077664L
        @JvmField
        val setLayerViewportBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_layer_viewport", SET_LAYER_VIEWPORT_HASH)

        private const val GET_LAYER_VIEWPORT_HASH = 3750751911L
        @JvmField
        val getLayerViewportBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_layer_viewport", GET_LAYER_VIEWPORT_HASH)

        private const val SET_USE_ANDROID_SURFACE_HASH = 2586408642L
        @JvmField
        val setUseAndroidSurfaceBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_use_android_surface", SET_USE_ANDROID_SURFACE_HASH)

        private const val GET_USE_ANDROID_SURFACE_HASH = 36873697L
        @JvmField
        val getUseAndroidSurfaceBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_use_android_surface", GET_USE_ANDROID_SURFACE_HASH)

        private const val SET_ANDROID_SURFACE_SIZE_HASH = 1130785943L
        @JvmField
        val setAndroidSurfaceSizeBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_android_surface_size", SET_ANDROID_SURFACE_SIZE_HASH)

        private const val GET_ANDROID_SURFACE_SIZE_HASH = 3690982128L
        @JvmField
        val getAndroidSurfaceSizeBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_android_surface_size", GET_ANDROID_SURFACE_SIZE_HASH)

        private const val SET_ENABLE_HOLE_PUNCH_HASH = 2586408642L
        @JvmField
        val setEnableHolePunchBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_enable_hole_punch", SET_ENABLE_HOLE_PUNCH_HASH)

        private const val GET_ENABLE_HOLE_PUNCH_HASH = 36873697L
        @JvmField
        val getEnableHolePunchBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_enable_hole_punch", GET_ENABLE_HOLE_PUNCH_HASH)

        private const val SET_SORT_ORDER_HASH = 1286410249L
        @JvmField
        val setSortOrderBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_sort_order", SET_SORT_ORDER_HASH)

        private const val GET_SORT_ORDER_HASH = 3905245786L
        @JvmField
        val getSortOrderBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_sort_order", GET_SORT_ORDER_HASH)

        private const val SET_ALPHA_BLEND_HASH = 2586408642L
        @JvmField
        val setAlphaBlendBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_alpha_blend", SET_ALPHA_BLEND_HASH)

        private const val GET_ALPHA_BLEND_HASH = 36873697L
        @JvmField
        val getAlphaBlendBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_alpha_blend", GET_ALPHA_BLEND_HASH)

        private const val GET_ANDROID_SURFACE_HASH = 3277089691L
        @JvmField
        val getAndroidSurfaceBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_android_surface", GET_ANDROID_SURFACE_HASH)

        private const val IS_NATIVELY_SUPPORTED_HASH = 36873697L
        @JvmField
        val isNativelySupportedBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "is_natively_supported", IS_NATIVELY_SUPPORTED_HASH)

        private const val IS_PROTECTED_CONTENT_HASH = 36873697L
        @JvmField
        val isProtectedContentBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "is_protected_content", IS_PROTECTED_CONTENT_HASH)

        private const val SET_PROTECTED_CONTENT_HASH = 2586408642L
        @JvmField
        val setProtectedContentBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_protected_content", SET_PROTECTED_CONTENT_HASH)

        private const val SET_MIN_FILTER_HASH = 3653437593L
        @JvmField
        val setMinFilterBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_min_filter", SET_MIN_FILTER_HASH)

        private const val GET_MIN_FILTER_HASH = 845677307L
        @JvmField
        val getMinFilterBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_min_filter", GET_MIN_FILTER_HASH)

        private const val SET_MAG_FILTER_HASH = 3653437593L
        @JvmField
        val setMagFilterBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_mag_filter", SET_MAG_FILTER_HASH)

        private const val GET_MAG_FILTER_HASH = 845677307L
        @JvmField
        val getMagFilterBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_mag_filter", GET_MAG_FILTER_HASH)

        private const val SET_MIPMAP_MODE_HASH = 3271133183L
        @JvmField
        val setMipmapModeBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_mipmap_mode", SET_MIPMAP_MODE_HASH)

        private const val GET_MIPMAP_MODE_HASH = 3962697095L
        @JvmField
        val getMipmapModeBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_mipmap_mode", GET_MIPMAP_MODE_HASH)

        private const val SET_HORIZONTAL_WRAP_HASH = 15634990L
        @JvmField
        val setHorizontalWrapBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_horizontal_wrap", SET_HORIZONTAL_WRAP_HASH)

        private const val GET_HORIZONTAL_WRAP_HASH = 2798816834L
        @JvmField
        val getHorizontalWrapBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_horizontal_wrap", GET_HORIZONTAL_WRAP_HASH)

        private const val SET_VERTICAL_WRAP_HASH = 15634990L
        @JvmField
        val setVerticalWrapBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_vertical_wrap", SET_VERTICAL_WRAP_HASH)

        private const val GET_VERTICAL_WRAP_HASH = 2798816834L
        @JvmField
        val getVerticalWrapBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_vertical_wrap", GET_VERTICAL_WRAP_HASH)

        private const val SET_RED_SWIZZLE_HASH = 741598951L
        @JvmField
        val setRedSwizzleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_red_swizzle", SET_RED_SWIZZLE_HASH)

        private const val GET_RED_SWIZZLE_HASH = 2334776767L
        @JvmField
        val getRedSwizzleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_red_swizzle", GET_RED_SWIZZLE_HASH)

        private const val SET_GREEN_SWIZZLE_HASH = 741598951L
        @JvmField
        val setGreenSwizzleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_green_swizzle", SET_GREEN_SWIZZLE_HASH)

        private const val GET_GREEN_SWIZZLE_HASH = 2334776767L
        @JvmField
        val getGreenSwizzleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_green_swizzle", GET_GREEN_SWIZZLE_HASH)

        private const val SET_BLUE_SWIZZLE_HASH = 741598951L
        @JvmField
        val setBlueSwizzleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_blue_swizzle", SET_BLUE_SWIZZLE_HASH)

        private const val GET_BLUE_SWIZZLE_HASH = 2334776767L
        @JvmField
        val getBlueSwizzleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_blue_swizzle", GET_BLUE_SWIZZLE_HASH)

        private const val SET_ALPHA_SWIZZLE_HASH = 741598951L
        @JvmField
        val setAlphaSwizzleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_alpha_swizzle", SET_ALPHA_SWIZZLE_HASH)

        private const val GET_ALPHA_SWIZZLE_HASH = 2334776767L
        @JvmField
        val getAlphaSwizzleBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_alpha_swizzle", GET_ALPHA_SWIZZLE_HASH)

        private const val SET_MAX_ANISOTROPY_HASH = 373806689L
        @JvmField
        val setMaxAnisotropyBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_max_anisotropy", SET_MAX_ANISOTROPY_HASH)

        private const val GET_MAX_ANISOTROPY_HASH = 1740695150L
        @JvmField
        val getMaxAnisotropyBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_max_anisotropy", GET_MAX_ANISOTROPY_HASH)

        private const val SET_BORDER_COLOR_HASH = 2920490490L
        @JvmField
        val setBorderColorBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_border_color", SET_BORDER_COLOR_HASH)

        private const val GET_BORDER_COLOR_HASH = 3444240500L
        @JvmField
        val getBorderColorBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_border_color", GET_BORDER_COLOR_HASH)

        private const val SET_EYE_VISIBILITY_HASH = 156391336L
        @JvmField
        val setEyeVisibilityBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "set_eye_visibility", SET_EYE_VISIBILITY_HASH)

        private const val GET_EYE_VISIBILITY_HASH = 467669000L
        @JvmField
        val getEyeVisibilityBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "get_eye_visibility", GET_EYE_VISIBILITY_HASH)

        private const val INTERSECTS_RAY_HASH = 1091262597L
        @JvmField
        val intersectsRayBind =
            ObjectCalls.getMethodBind("OpenXRCompositionLayer", "intersects_ray", INTERSECTS_RAY_HASH)
    }
}
