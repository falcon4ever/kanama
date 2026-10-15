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
 * The parent class of all OpenXR composition layer nodes.
 *
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

    /**
     * The `SubViewport` to render on the composition layer.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_layer_viewport
     */
    fun setLayerViewport(viewport: SubViewport) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setLayerViewportBind, segment, listOf(viewport.segment))
        ObjectCalls.keepReachable(viewport)
    }

    /**
     * The `SubViewport` to render on the composition layer.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_layer_viewport
     */
    fun getLayerViewport(): SubViewport? {
        return SubViewport.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getLayerViewportBind, segment))
    }

    /**
     * If enabled, an Android surface will be created (with the dimensions from `android_surface_size`)
     * which will provide the 2D content for the composition layer, rather than using `layer_viewport`.
     * See `get_android_surface` for information about how to get the surface so that your application
     * can draw to it. Note: This will only work in Android builds.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_use_android_surface
     */
    fun setUseAndroidSurface(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseAndroidSurfaceBind, segment, enable)
    }

    /**
     * If enabled, an Android surface will be created (with the dimensions from `android_surface_size`)
     * which will provide the 2D content for the composition layer, rather than using `layer_viewport`.
     * See `get_android_surface` for information about how to get the surface so that your application
     * can draw to it. Note: This will only work in Android builds.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_use_android_surface
     */
    fun getUseAndroidSurface(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseAndroidSurfaceBind, segment)
    }

    /**
     * The size of the Android surface to create if `use_android_surface` is enabled.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_android_surface_size
     */
    fun setAndroidSurfaceSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setAndroidSurfaceSizeBind, segment, size)
    }

    /**
     * The size of the Android surface to create if `use_android_surface` is enabled.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_android_surface_size
     */
    fun getAndroidSurfaceSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getAndroidSurfaceSizeBind, segment)
    }

    /**
     * Enables a technique called "hole punching", which allows putting the composition layer behind
     * the main projection layer (i.e. setting `sort_order` to a negative value) while "punching a
     * hole" through everything rendered by Godot so that the layer is still visible. This can be used
     * to create the illusion that the composition layer exists in the same 3D space as everything
     * rendered by Godot, allowing objects to appear to pass both behind or in front of the composition
     * layer.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_enable_hole_punch
     */
    fun setEnableHolePunch(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableHolePunchBind, segment, enable)
    }

    /**
     * Enables a technique called "hole punching", which allows putting the composition layer behind
     * the main projection layer (i.e. setting `sort_order` to a negative value) while "punching a
     * hole" through everything rendered by Godot so that the layer is still visible. This can be used
     * to create the illusion that the composition layer exists in the same 3D space as everything
     * rendered by Godot, allowing objects to appear to pass both behind or in front of the composition
     * layer.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_enable_hole_punch
     */
    fun getEnableHolePunch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableHolePunchBind, segment)
    }

    /**
     * The sort order for this composition layer. Higher numbers will be shown in front of lower
     * numbers. Note: This will have no effect if a fallback mesh is being used.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_sort_order
     */
    fun setSortOrder(order: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSortOrderBind, segment, order)
    }

    /**
     * The sort order for this composition layer. Higher numbers will be shown in front of lower
     * numbers. Note: This will have no effect if a fallback mesh is being used.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_sort_order
     */
    fun getSortOrder(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSortOrderBind, segment)
    }

    /**
     * Enables the blending the layer using its alpha channel. Can be combined with
     * `Viewport.transparent_bg` to give the layer a transparent background.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_alpha_blend
     */
    fun setAlphaBlend(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAlphaBlendBind, segment, enabled)
    }

    /**
     * Enables the blending the layer using its alpha channel. Can be combined with
     * `Viewport.transparent_bg` to give the layer a transparent background.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_alpha_blend
     */
    fun getAlphaBlend(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAlphaBlendBind, segment)
    }

    /**
     * Returns a `JavaObject` representing an `android.view.Surface` if `use_android_surface` is
     * enabled and OpenXR has created the surface. Otherwise, this will return `null`. Note: The
     * surface can only be created during an active OpenXR session. So, if `use_android_surface` is
     * enabled outside of an OpenXR session, it won't be created until a new session fully starts.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_android_surface
     */
    fun getAndroidSurface(): JavaObject? {
        return JavaObject.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getAndroidSurfaceBind, segment))
    }

    /**
     * Returns `true` if the OpenXR runtime natively supports this composition layer type. Note: This
     * will only return an accurate result after the OpenXR session has started.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.is_natively_supported
     */
    fun isNativelySupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isNativelySupportedBind, segment)
    }

    /**
     * If enabled, the OpenXR swapchain will be created with the
     * `XR_SWAPCHAIN_CREATE_PROTECTED_CONTENT_BIT` flag, which will protect its contents from CPU
     * access. When used with an Android Surface, this may allow DRM content to be presented, and will
     * only take effect when the Surface is first created; later changes to this property will have no
     * effect.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.is_protected_content
     */
    fun isProtectedContent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isProtectedContentBind, segment)
    }

    /**
     * If enabled, the OpenXR swapchain will be created with the
     * `XR_SWAPCHAIN_CREATE_PROTECTED_CONTENT_BIT` flag, which will protect its contents from CPU
     * access. When used with an Android Surface, this may allow DRM content to be presented, and will
     * only take effect when the Surface is first created; later changes to this property will have no
     * effect.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_protected_content
     */
    fun setProtectedContent(protectedContent: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setProtectedContentBind, segment, protectedContent)
    }

    /**
     * The minification filter of the swapchain state. Note: This property only has an effect on
     * devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_min_filter
     */
    fun setMinFilter(mode: OpenXRCompositionLayer.Filter) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMinFilterBind, segment, mode.value)
    }

    /**
     * The minification filter of the swapchain state. Note: This property only has an effect on
     * devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_min_filter
     */
    fun getMinFilter(): OpenXRCompositionLayer.Filter {
        return OpenXRCompositionLayer.Filter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMinFilterBind, segment))
    }

    /**
     * The magnification filter of the swapchain state. Note: This property only has an effect on
     * devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_mag_filter
     */
    fun setMagFilter(mode: OpenXRCompositionLayer.Filter) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMagFilterBind, segment, mode.value)
    }

    /**
     * The magnification filter of the swapchain state. Note: This property only has an effect on
     * devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_mag_filter
     */
    fun getMagFilter(): OpenXRCompositionLayer.Filter {
        return OpenXRCompositionLayer.Filter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMagFilterBind, segment))
    }

    /**
     * The mipmap mode of the swapchain state. Note: This property only has an effect on devices that
     * support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_mipmap_mode
     */
    fun setMipmapMode(mode: OpenXRCompositionLayer.MipmapMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMipmapModeBind, segment, mode.value)
    }

    /**
     * The mipmap mode of the swapchain state. Note: This property only has an effect on devices that
     * support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_mipmap_mode
     */
    fun getMipmapMode(): OpenXRCompositionLayer.MipmapMode {
        return OpenXRCompositionLayer.MipmapMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMipmapModeBind, segment))
    }

    /**
     * The horizontal wrap mode of the swapchain state. Note: This property only has an effect on
     * devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_horizontal_wrap
     */
    fun setHorizontalWrap(mode: OpenXRCompositionLayer.Wrap) {
        ObjectCalls.ptrcallWithLongArg(Binds.setHorizontalWrapBind, segment, mode.value)
    }

    /**
     * The horizontal wrap mode of the swapchain state. Note: This property only has an effect on
     * devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_horizontal_wrap
     */
    fun getHorizontalWrap(): OpenXRCompositionLayer.Wrap {
        return OpenXRCompositionLayer.Wrap(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHorizontalWrapBind, segment))
    }

    /**
     * The vertical wrap mode of the swapchain state. Note: This property only has an effect on devices
     * that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_vertical_wrap
     */
    fun setVerticalWrap(mode: OpenXRCompositionLayer.Wrap) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVerticalWrapBind, segment, mode.value)
    }

    /**
     * The vertical wrap mode of the swapchain state. Note: This property only has an effect on devices
     * that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_vertical_wrap
     */
    fun getVerticalWrap(): OpenXRCompositionLayer.Wrap {
        return OpenXRCompositionLayer.Wrap(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVerticalWrapBind, segment))
    }

    /**
     * The swizzle value for the red channel of the swapchain state. Note: This property only has an
     * effect on devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan
     * extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_red_swizzle
     */
    fun setRedSwizzle(mode: OpenXRCompositionLayer.Swizzle) {
        ObjectCalls.ptrcallWithLongArg(Binds.setRedSwizzleBind, segment, mode.value)
    }

    /**
     * The swizzle value for the red channel of the swapchain state. Note: This property only has an
     * effect on devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan
     * extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_red_swizzle
     */
    fun getRedSwizzle(): OpenXRCompositionLayer.Swizzle {
        return OpenXRCompositionLayer.Swizzle(ObjectCalls.ptrcallNoArgsRetLong(Binds.getRedSwizzleBind, segment))
    }

    /**
     * The swizzle value for the green channel of the swapchain state. Note: This property only has an
     * effect on devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan
     * extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_green_swizzle
     */
    fun setGreenSwizzle(mode: OpenXRCompositionLayer.Swizzle) {
        ObjectCalls.ptrcallWithLongArg(Binds.setGreenSwizzleBind, segment, mode.value)
    }

    /**
     * The swizzle value for the green channel of the swapchain state. Note: This property only has an
     * effect on devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan
     * extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_green_swizzle
     */
    fun getGreenSwizzle(): OpenXRCompositionLayer.Swizzle {
        return OpenXRCompositionLayer.Swizzle(ObjectCalls.ptrcallNoArgsRetLong(Binds.getGreenSwizzleBind, segment))
    }

    /**
     * The swizzle value for the blue channel of the swapchain state. Note: This property only has an
     * effect on devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan
     * extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_blue_swizzle
     */
    fun setBlueSwizzle(mode: OpenXRCompositionLayer.Swizzle) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBlueSwizzleBind, segment, mode.value)
    }

    /**
     * The swizzle value for the blue channel of the swapchain state. Note: This property only has an
     * effect on devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan
     * extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_blue_swizzle
     */
    fun getBlueSwizzle(): OpenXRCompositionLayer.Swizzle {
        return OpenXRCompositionLayer.Swizzle(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBlueSwizzleBind, segment))
    }

    /**
     * The swizzle value for the alpha channel of the swapchain state. Note: This property only has an
     * effect on devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan
     * extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_alpha_swizzle
     */
    fun setAlphaSwizzle(mode: OpenXRCompositionLayer.Swizzle) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAlphaSwizzleBind, segment, mode.value)
    }

    /**
     * The swizzle value for the alpha channel of the swapchain state. Note: This property only has an
     * effect on devices that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan
     * extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_alpha_swizzle
     */
    fun getAlphaSwizzle(): OpenXRCompositionLayer.Swizzle {
        return OpenXRCompositionLayer.Swizzle(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlphaSwizzleBind, segment))
    }

    /**
     * The max anisotropy of the swapchain state. Note: This property only has an effect on devices
     * that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_max_anisotropy
     */
    fun setMaxAnisotropy(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxAnisotropyBind, segment, value)
    }

    /**
     * The max anisotropy of the swapchain state. Note: This property only has an effect on devices
     * that support the OpenXR XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_max_anisotropy
     */
    fun getMaxAnisotropy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxAnisotropyBind, segment)
    }

    /**
     * The border color of the swapchain state that is used when the wrap mode clamps to the border.
     * Note: This property only has an effect on devices that support the OpenXR
     * XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_border_color
     */
    fun setBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setBorderColorBind, segment, color)
    }

    /**
     * The border color of the swapchain state that is used when the wrap mode clamps to the border.
     * Note: This property only has an effect on devices that support the OpenXR
     * XR_FB_swapchain_update_state OpenGLES/Vulkan extensions.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_border_color
     */
    fun getBorderColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getBorderColorBind, segment)
    }

    /**
     * The eye(s) the composition layer is visible to. Note: Not all composition layer types or
     * runtimes support restricting visibility to a single eye.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.set_eye_visibility
     */
    fun setEyeVisibility(eyeVisibility: OpenXRCompositionLayer.EyeVisibility) {
        ObjectCalls.ptrcallWithLongArg(Binds.setEyeVisibilityBind, segment, eyeVisibility.value)
    }

    /**
     * The eye(s) the composition layer is visible to. Note: Not all composition layer types or
     * runtimes support restricting visibility to a single eye.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.get_eye_visibility
     */
    fun getEyeVisibility(): OpenXRCompositionLayer.EyeVisibility {
        return OpenXRCompositionLayer.EyeVisibility(ObjectCalls.ptrcallNoArgsRetLong(Binds.getEyeVisibilityBind, segment))
    }

    /**
     * Returns UV coordinates where the given ray intersects with the composition layer. `origin` and
     * `direction` must be in global space. Returns `Vector2(-1.0, -1.0)` if the ray doesn't intersect.
     *
     * Generated from Godot docs: OpenXRCompositionLayer.intersects_ray
     */
    fun intersectsRay(origin: Vector3, direction: Vector3): Vector2 {
        return ObjectCalls.ptrcallWithTwoVector3ArgsRetVector2(Binds.intersectsRayBind, segment, origin, direction)
    }

    /**
     * Godot's `OpenXRCompositionLayer.Filter` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`OpenXRCompositionLayer.Filter.<NAME>`).
     *
     * Generated from Godot docs: OpenXRCompositionLayer.Filter
     */
    @JvmInline
    value class Filter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Perform nearest-neighbor filtering when sampling the texture.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.FILTER_NEAREST
             */
            val NEAREST: Filter get() = Filter(0L)
            /**
             * Perform linear filtering when sampling the texture.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.FILTER_LINEAR
             */
            val LINEAR: Filter get() = Filter(1L)
            /**
             * Perform cubic filtering when sampling the texture.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.FILTER_CUBIC
             */
            val CUBIC: Filter get() = Filter(2L)
        }
    }

    /**
     * Godot's `OpenXRCompositionLayer.MipmapMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`OpenXRCompositionLayer.MipmapMode.<NAME>`).
     *
     * Generated from Godot docs: OpenXRCompositionLayer.MipmapMode
     */
    @JvmInline
    value class MipmapMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disable mipmapping. Note: Mipmapping can only be disabled in the Compatibility renderer.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.MIPMAP_MODE_DISABLED
             */
            val DISABLED: MipmapMode get() = MipmapMode(0L)
            /**
             * Use the mipmap of the nearest resolution.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.MIPMAP_MODE_NEAREST
             */
            val NEAREST: MipmapMode get() = MipmapMode(1L)
            /**
             * Use linear interpolation of the two mipmaps of the nearest resolution.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.MIPMAP_MODE_LINEAR
             */
            val LINEAR: MipmapMode get() = MipmapMode(2L)
        }
    }

    /**
     * Godot's `OpenXRCompositionLayer.Wrap` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`OpenXRCompositionLayer.Wrap.<NAME>`).
     *
     * Generated from Godot docs: OpenXRCompositionLayer.Wrap
     */
    @JvmInline
    value class Wrap(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Clamp the texture to its specified border color.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.WRAP_CLAMP_TO_BORDER
             */
            val CLAMP_TO_BORDER: Wrap get() = Wrap(0L)
            /**
             * Clamp the texture to its edge color.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.WRAP_CLAMP_TO_EDGE
             */
            val CLAMP_TO_EDGE: Wrap get() = Wrap(1L)
            /**
             * Repeat the texture infinitely.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.WRAP_REPEAT
             */
            val REPEAT: Wrap get() = Wrap(2L)
            /**
             * Repeat the texture infinitely, mirroring it on each repeat.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.WRAP_MIRRORED_REPEAT
             */
            val MIRRORED_REPEAT: Wrap get() = Wrap(3L)
            /**
             * Mirror the texture once and then clamp the texture to its edge color. Note: This wrap mode is
             * not available in the Compatibility renderer.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.WRAP_MIRROR_CLAMP_TO_EDGE
             */
            val MIRROR_CLAMP_TO_EDGE: Wrap get() = Wrap(4L)
        }
    }

    /**
     * Godot's `OpenXRCompositionLayer.Swizzle` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`OpenXRCompositionLayer.Swizzle.<NAME>`).
     *
     * Generated from Godot docs: OpenXRCompositionLayer.Swizzle
     */
    @JvmInline
    value class Swizzle(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Maps a color channel to the value of the red channel.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.SWIZZLE_RED
             */
            val RED: Swizzle get() = Swizzle(0L)
            /**
             * Maps a color channel to the value of the green channel.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.SWIZZLE_GREEN
             */
            val GREEN: Swizzle get() = Swizzle(1L)
            /**
             * Maps a color channel to the value of the blue channel.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.SWIZZLE_BLUE
             */
            val BLUE: Swizzle get() = Swizzle(2L)
            /**
             * Maps a color channel to the value of the alpha channel.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.SWIZZLE_ALPHA
             */
            val ALPHA: Swizzle get() = Swizzle(3L)
            /**
             * Maps a color channel to the value of zero.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.SWIZZLE_ZERO
             */
            val ZERO: Swizzle get() = Swizzle(4L)
            /**
             * Maps a color channel to the value of one.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.SWIZZLE_ONE
             */
            val ONE: Swizzle get() = Swizzle(5L)
        }
    }

    /**
     * Godot's `OpenXRCompositionLayer.EyeVisibility` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`OpenXRCompositionLayer.EyeVisibility.<NAME>`).
     *
     * Generated from Godot docs: OpenXRCompositionLayer.EyeVisibility
     */
    @JvmInline
    value class EyeVisibility(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The layer is visible to both the left and right eyes.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.EYE_VISIBILITY_BOTH
             */
            val BOTH: EyeVisibility get() = EyeVisibility(0L)
            /**
             * The layer is visible only to the left eye.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.EYE_VISIBILITY_LEFT
             */
            val LEFT: EyeVisibility get() = EyeVisibility(1L)
            /**
             * The layer is visible only to the right eye.
             *
             * Generated from Godot docs: OpenXRCompositionLayer.EYE_VISIBILITY_RIGHT
             */
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
