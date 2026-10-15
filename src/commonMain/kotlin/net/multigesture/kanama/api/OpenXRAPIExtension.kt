package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2i

/**
 * Makes the OpenXR API available for GDExtension.
 *
 * Generated from Godot docs: OpenXRAPIExtension
 */
class OpenXRAPIExtension(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the version of OpenXR that was initialized. Only valid after the OpenXR instance has
     * been created. See XR_MAKE_VERSION
     * (https://registry.khronos.org/OpenXR/specs/1.1/html/xrspec.html#XR_MAKE_VERSION) for how the
     * version is calculated.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_openxr_version
     */
    fun getOpenxrVersion(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpenxrVersionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the XrInstance (https://registry.khronos.org/OpenXR/specs/1.0/man/html/XrInstance.html)
     * created during the initialization of the OpenXR API.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_instance
     */
    fun getInstance(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getInstanceBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the ID of the system, which is an XrSystemId
     * (https://registry.khronos.org/OpenXR/specs/1.0/man/html/XrSystemId.html) cast to an integer.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_system_id
     */
    fun getSystemId(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getSystemIdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the OpenXR session, which is an XrSession
     * (https://registry.khronos.org/OpenXR/specs/1.0/man/html/XrSession.html) cast to an integer.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_session
     */
    fun getSession(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getSessionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the provided XrResult
     * (https://registry.khronos.org/OpenXR/specs/1.0/man/html/XrResult.html) (cast to an integer) is
     * successful. Otherwise returns `false` and prints the XrResult
     * (https://registry.khronos.org/OpenXR/specs/1.0/man/html/XrResult.html) converted to a string,
     * with the specified additional information.
     *
     * Generated from Godot docs: OpenXRAPIExtension.xr_result
     */
    fun xrResult(result: Long, format: String, args: List<Any?>): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongStringArrayArgsRetBool(Binds.xrResultBind, segment, result, format, args)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the function pointer of the OpenXR function with the specified name, cast to an integer.
     * If the function with the given name does not exist, the method returns `0`. Note:
     * `openxr/util.h` contains utility macros for acquiring OpenXR functions, e.g.
     * `GDEXTENSION_INIT_XR_FUNC_V(xrCreateAction)`.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_instance_proc_addr
     */
    fun getInstanceProcAddr(name: String): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetLong(Binds.getInstanceProcAddrBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns an error string for the given XrResult
     * (https://registry.khronos.org/OpenXR/specs/1.0/man/html/XrResult.html).
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_error_string
     */
    fun getErrorString(result: Long): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetString(Binds.getErrorStringBind, segment, result)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the name of the specified swapchain format.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_swapchain_format_name
     */
    fun getSwapchainFormatName(swapchainFormat: Long): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetString(Binds.getSwapchainFormatNameBind, segment, swapchainFormat)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Set the object name of an OpenXR object, used for debug output. `object_type` must be a valid
     * OpenXR `XrObjectType` enum and `object_handle` must be a valid OpenXR object handle.
     *
     * Generated from Godot docs: OpenXRAPIExtension.set_object_name
     */
    fun setObjectName(objectType: Long, objectHandle: Long, objectName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongStringArgs(Binds.setObjectNameBind, segment, objectType, objectHandle, objectName)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Begins a new debug label region, this label will be reported in debug messages for any calls
     * following this until `end_debug_label_region` is called. Debug labels can be stacked.
     *
     * Generated from Godot docs: OpenXRAPIExtension.begin_debug_label_region
     */
    fun beginDebugLabelRegion(labelName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.beginDebugLabelRegionBind, segment, labelName)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Marks the end of a debug label region. Removes the latest debug label region added by calling
     * `begin_debug_label_region`.
     *
     * Generated from Godot docs: OpenXRAPIExtension.end_debug_label_region
     */
    fun endDebugLabelRegion() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.endDebugLabelRegionBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Inserts a debug label, this label is reported in any debug message resulting from the OpenXR
     * calls that follows, until any of `begin_debug_label_region`, `end_debug_label_region`, or
     * `insert_debug_label` is called.
     *
     * Generated from Godot docs: OpenXRAPIExtension.insert_debug_label
     */
    fun insertDebugLabel(labelName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.insertDebugLabelBind, segment, labelName)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the number of views. It is usually two, one for each eye, but may differ with different
     * view configurations.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_view_count
     */
    fun getViewCount(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getViewCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the view configuration type, which is an XrViewConfigurationType
     * (https://registry.khronos.org/OpenXR/specs/1.0/man/html/XrViewConfigurationType.html) cast to an
     * integer.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_view_configuration
     */
    fun getViewConfiguration(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getViewConfigurationBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if OpenXR is initialized.
     *
     * Generated from Godot docs: OpenXRAPIExtension.is_initialized
     */
    fun isInitialized(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isInitializedBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if OpenXR is running (xrBeginSession
     * (https://registry.khronos.org/OpenXR/specs/1.0/man/html/xrBeginSession.html) was successfully
     * called and the swapchains were created).
     *
     * Generated from Godot docs: OpenXRAPIExtension.is_running
     */
    fun isRunning(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isRunningBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the play space, which is an XrSpace
     * (https://registry.khronos.org/OpenXR/specs/1.0/man/html/XrSpace.html) cast to an integer.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_play_space
     */
    fun getPlaySpace(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getPlaySpaceBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the predicted display timing for the current frame.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_predicted_display_time
     */
    fun getPredictedDisplayTime(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getPredictedDisplayTimeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the predicted display timing for the next frame.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_next_frame_time
     */
    fun getNextFrameTime(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getNextFrameTimeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if OpenXR is initialized for rendering with an XR viewport.
     *
     * Generated from Godot docs: OpenXRAPIExtension.can_render
     */
    fun canRender(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.canRenderBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the `RID` corresponding to an `Action` of a matching name, optionally limited to a
     * specified action set.
     *
     * Generated from Godot docs: OpenXRAPIExtension.find_action
     */
    fun findAction(name: String, actionSet: RID): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringAndRIDArgRetRID(Binds.findActionBind, segment, name, actionSet)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the corresponding `XrAction` OpenXR handle for the given action RID.
     *
     * Generated from Godot docs: OpenXRAPIExtension.action_get_handle
     */
    fun actionGetHandle(action: RID): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithRIDArgRetLong(Binds.actionGetHandleBind, segment, action)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the corresponding `XRHandTrackerEXT` handle for the given hand index value.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_hand_tracker
     */
    fun getHandTracker(handIndex: Int): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetLong(Binds.getHandTrackerBind, segment, handIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Registers the given extension as a composition layer provider. Note: This cannot be called after
     * the OpenXR session has started. However, it can be called in
     * `OpenXRExtensionWrapper._on_session_created`.
     *
     * Generated from Godot docs: OpenXRAPIExtension.register_composition_layer_provider
     */
    fun registerCompositionLayerProvider(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.registerCompositionLayerProviderBind, segment, listOf(extension.segment))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(extension)
    }

    /**
     * Unregisters the given extension as a composition layer provider. Note: This cannot be called
     * while the OpenXR session is still running.
     *
     * Generated from Godot docs: OpenXRAPIExtension.unregister_composition_layer_provider
     */
    fun unregisterCompositionLayerProvider(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterCompositionLayerProviderBind, segment, listOf(extension.segment))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(extension)
    }

    /**
     * Registers the given extension as a provider of additional data structures to projections views.
     * Note: This cannot be called after the OpenXR session has started. However, it can be called in
     * `OpenXRExtensionWrapper._on_session_created`.
     *
     * Generated from Godot docs: OpenXRAPIExtension.register_projection_views_extension
     */
    fun registerProjectionViewsExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.registerProjectionViewsExtensionBind, segment, listOf(extension.segment))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(extension)
    }

    /**
     * Unregisters the given extension as a provider of additional data structures to projections
     * views. Note: This cannot be called while the OpenXR session is still running.
     *
     * Generated from Godot docs: OpenXRAPIExtension.unregister_projection_views_extension
     */
    fun unregisterProjectionViewsExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterProjectionViewsExtensionBind, segment, listOf(extension.segment))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(extension)
    }

    /**
     * Registers the given extension as modifying frame info via the
     * `OpenXRExtensionWrapper._set_frame_wait_info_and_get_next_pointer`,
     * `OpenXRExtensionWrapper._set_view_locate_info_and_get_next_pointer`, or
     * `OpenXRExtensionWrapper._set_frame_end_info_and_get_next_pointer` virtual methods. Note: This
     * cannot be called after the OpenXR session has started. However, it can be called in
     * `OpenXRExtensionWrapper._on_session_created`.
     *
     * Generated from Godot docs: OpenXRAPIExtension.register_frame_info_extension
     */
    fun registerFrameInfoExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.registerFrameInfoExtensionBind, segment, listOf(extension.segment))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(extension)
    }

    /**
     * Unregisters the given extension as modifying frame info. Note: This cannot be called while the
     * OpenXR session is still running.
     *
     * Generated from Godot docs: OpenXRAPIExtension.unregister_frame_info_extension
     */
    fun unregisterFrameInfoExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterFrameInfoExtensionBind, segment, listOf(extension.segment))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(extension)
    }

    /**
     * Registers the given extension as modifying `XrCompositionLayerProjection` via the
     * `OpenXRExtensionWrapper._set_projection_layer_and_get_next_pointer` virtual method. Note: This
     * cannot be called after the OpenXR session has started. However, it can be called in
     * `OpenXRExtensionWrapper._on_session_created`.
     *
     * Generated from Godot docs: OpenXRAPIExtension.register_projection_layer_extension
     */
    fun registerProjectionLayerExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.registerProjectionLayerExtensionBind, segment, listOf(extension.segment))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(extension)
    }

    /**
     * Unregisters the given extension as modifying `XrCompositionLayerProjection`. Note: This cannot
     * be called while the OpenXR session is still running.
     *
     * Generated from Godot docs: OpenXRAPIExtension.unregister_projection_layer_extension
     */
    fun unregisterProjectionLayerExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterProjectionLayerExtensionBind, segment, listOf(extension.segment))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(extension)
    }

    /**
     * Returns the near boundary value of the camera frustum. Note: This is only accessible in the
     * render thread.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_render_state_z_near
     */
    fun getRenderStateZNear(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRenderStateZNearBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the far boundary value of the camera frustum. Note: This is only accessible in the
     * render thread.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_render_state_z_far
     */
    fun getRenderStateZFar(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRenderStateZFarBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the render target of the velocity texture.
     *
     * Generated from Godot docs: OpenXRAPIExtension.set_velocity_texture
     */
    fun setVelocityTexture(renderTarget: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setVelocityTextureBind, segment, renderTarget)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the render target of the velocity depth texture.
     *
     * Generated from Godot docs: OpenXRAPIExtension.set_velocity_depth_texture
     */
    fun setVelocityDepthTexture(renderTarget: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setVelocityDepthTextureBind, segment, renderTarget)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the target size of the velocity and velocity depth textures.
     *
     * Generated from Godot docs: OpenXRAPIExtension.set_velocity_target_size
     */
    fun setVelocityTargetSize(targetSize: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.setVelocityTargetSizeBind, segment, targetSize)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of supported swapchain formats.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_supported_swapchain_formats
     */
    fun getSupportedSwapchainFormats(): List<Long> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getSupportedSwapchainFormatsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns a pointer to a new swapchain created using the provided parameters.
     *
     * Generated from Godot docs: OpenXRAPIExtension.openxr_swapchain_create
     */
    fun openxrSwapchainCreate(createFlags: Long, usageFlags: Long, swapchainFormat: Long, width: Long, height: Long, sampleCount: Long, arraySize: Long): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithThreeLongFourUInt32ArgsRetLong(Binds.openxrSwapchainCreateBind, segment, createFlags, usageFlags, swapchainFormat, width, height, sampleCount, arraySize)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Destroys the provided swapchain and frees it from memory.
     *
     * Generated from Godot docs: OpenXRAPIExtension.openxr_swapchain_free
     */
    fun openxrSwapchainFree(swapchain: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.openxrSwapchainFreeBind, segment, swapchain)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the `XrSwapchain` handle of the provided swapchain.
     *
     * Generated from Godot docs: OpenXRAPIExtension.openxr_swapchain_get_swapchain
     */
    fun openxrSwapchainGetSwapchain(swapchain: Long): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetLong(Binds.openxrSwapchainGetSwapchainBind, segment, swapchain)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Acquires the image of the provided swapchain.
     *
     * Generated from Godot docs: OpenXRAPIExtension.openxr_swapchain_acquire
     */
    fun openxrSwapchainAcquire(swapchain: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.openxrSwapchainAcquireBind, segment, swapchain)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the RID of the provided swapchain's image.
     *
     * Generated from Godot docs: OpenXRAPIExtension.openxr_swapchain_get_image
     */
    fun openxrSwapchainGetImage(swapchain: Long): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetRID(Binds.openxrSwapchainGetImageBind, segment, swapchain)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Releases the image of the provided swapchain.
     *
     * Generated from Godot docs: OpenXRAPIExtension.openxr_swapchain_release
     */
    fun openxrSwapchainRelease(swapchain: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.openxrSwapchainReleaseBind, segment, swapchain)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns a pointer to the render state's `XrCompositionLayerProjection` struct. Note: This method
     * should only be called from the rendering thread.
     *
     * Generated from Godot docs: OpenXRAPIExtension.get_projection_layer
     */
    fun getProjectionLayer(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getProjectionLayerBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the render region to `render_region`, overriding the normal render target's rect.
     *
     * Generated from Godot docs: OpenXRAPIExtension.set_render_region
     */
    fun setRenderRegion(renderRegion: Rect2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2iArg(Binds.setRenderRegionBind, segment, renderRegion)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If set to `true`, an OpenXR extension is loaded which is capable of emulating the
     * `XRInterface.EnvironmentBlendMode.ALPHA_BLEND` blend mode.
     *
     * Generated from Godot docs: OpenXRAPIExtension.set_emulate_environment_blend_mode_alpha_blend
     */
    fun setEmulateEnvironmentBlendModeAlphaBlend(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEmulateEnvironmentBlendModeAlphaBlendBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns `OpenXRAPIExtension.OpenXRAlphaBlendModeSupport` denoting if
     * `XRInterface.EnvironmentBlendMode.ALPHA_BLEND` is really supported, emulated or not supported at
     * all.
     *
     * Generated from Godot docs: OpenXRAPIExtension.is_environment_blend_mode_alpha_supported
     */
    fun isEnvironmentBlendModeAlphaSupported(): OpenXRAPIExtension.OpenXRAlphaBlendModeSupport {
        checkOpen()
        val ret = OpenXRAPIExtension.OpenXRAlphaBlendModeSupport(ObjectCalls.ptrcallNoArgsRetLong(Binds.isEnvironmentBlendModeAlphaSupportedBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Request the recommended resolution from the OpenXR runtime and update the main swapchain size if
     * it has changed.
     *
     * Generated from Godot docs: OpenXRAPIExtension.update_main_swapchain_size
     */
    fun updateMainSwapchainSize() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.updateMainSwapchainSizeBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Godot's `OpenXRAPIExtension.OpenXRAlphaBlendModeSupport` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`OpenXRAPIExtension.OpenXRAlphaBlendModeSupport.<NAME>`).
     *
     * Generated from Godot docs: OpenXRAPIExtension.OpenXRAlphaBlendModeSupport
     */
    @JvmInline
    value class OpenXRAlphaBlendModeSupport(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Means that `XRInterface.EnvironmentBlendMode.ALPHA_BLEND` isn't supported at all.
             *
             * Generated from Godot docs: OpenXRAPIExtension.OPENXR_ALPHA_BLEND_MODE_SUPPORT_NONE
             */
            val NONE: OpenXRAlphaBlendModeSupport get() = OpenXRAlphaBlendModeSupport(0L)
            /**
             * Means that `XRInterface.EnvironmentBlendMode.ALPHA_BLEND` is really supported.
             *
             * Generated from Godot docs: OpenXRAPIExtension.OPENXR_ALPHA_BLEND_MODE_SUPPORT_REAL
             */
            val REAL: OpenXRAlphaBlendModeSupport get() = OpenXRAlphaBlendModeSupport(1L)
            /**
             * Means that `XRInterface.EnvironmentBlendMode.ALPHA_BLEND` is emulated.
             *
             * Generated from Godot docs: OpenXRAPIExtension.OPENXR_ALPHA_BLEND_MODE_SUPPORT_EMULATING
             */
            val EMULATING: OpenXRAlphaBlendModeSupport get() = OpenXRAlphaBlendModeSupport(2L)
        }
    }

    companion object {
        /**
         * Returns `true` if OpenXR is enabled.
         *
         * Generated from Godot docs: OpenXRAPIExtension.openxr_is_enabled
         */
        fun openxrIsEnabled(checkRunInEditor: Boolean): Boolean {
            return ObjectCalls.ptrcallWithBoolArgRetBool(Binds.openxrIsEnabledBind, NULL_SEGMENT, checkRunInEditor)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAPIExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRAPIExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRAPIExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRAPIExtension? =
            if (handle.address() == 0L) null else OpenXRAPIExtension(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_OPENXR_VERSION_HASH = 2455072627L
        @JvmField
        val getOpenxrVersionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_openxr_version", GET_OPENXR_VERSION_HASH)

        private const val GET_INSTANCE_HASH = 2455072627L
        @JvmField
        val getInstanceBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_instance", GET_INSTANCE_HASH)

        private const val GET_SYSTEM_ID_HASH = 2455072627L
        @JvmField
        val getSystemIdBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_system_id", GET_SYSTEM_ID_HASH)

        private const val GET_SESSION_HASH = 2455072627L
        @JvmField
        val getSessionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_session", GET_SESSION_HASH)

        private const val XR_RESULT_HASH = 3886436197L
        @JvmField
        val xrResultBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "xr_result", XR_RESULT_HASH)

        private const val OPENXR_IS_ENABLED_HASH = 2703660260L
        @JvmField
        val openxrIsEnabledBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_is_enabled", OPENXR_IS_ENABLED_HASH)

        private const val GET_INSTANCE_PROC_ADDR_HASH = 1597066294L
        @JvmField
        val getInstanceProcAddrBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_instance_proc_addr", GET_INSTANCE_PROC_ADDR_HASH)

        private const val GET_ERROR_STRING_HASH = 990163283L
        @JvmField
        val getErrorStringBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_error_string", GET_ERROR_STRING_HASH)

        private const val GET_SWAPCHAIN_FORMAT_NAME_HASH = 990163283L
        @JvmField
        val getSwapchainFormatNameBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_swapchain_format_name", GET_SWAPCHAIN_FORMAT_NAME_HASH)

        private const val SET_OBJECT_NAME_HASH = 2285447957L
        @JvmField
        val setObjectNameBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_object_name", SET_OBJECT_NAME_HASH)

        private const val BEGIN_DEBUG_LABEL_REGION_HASH = 83702148L
        @JvmField
        val beginDebugLabelRegionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "begin_debug_label_region", BEGIN_DEBUG_LABEL_REGION_HASH)

        private const val END_DEBUG_LABEL_REGION_HASH = 3218959716L
        @JvmField
        val endDebugLabelRegionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "end_debug_label_region", END_DEBUG_LABEL_REGION_HASH)

        private const val INSERT_DEBUG_LABEL_HASH = 83702148L
        @JvmField
        val insertDebugLabelBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "insert_debug_label", INSERT_DEBUG_LABEL_HASH)

        private const val GET_VIEW_COUNT_HASH = 3905245786L
        @JvmField
        val getViewCountBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_view_count", GET_VIEW_COUNT_HASH)

        private const val GET_VIEW_CONFIGURATION_HASH = 3905245786L
        @JvmField
        val getViewConfigurationBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_view_configuration", GET_VIEW_CONFIGURATION_HASH)

        private const val IS_INITIALIZED_HASH = 2240911060L
        @JvmField
        val isInitializedBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "is_initialized", IS_INITIALIZED_HASH)

        private const val IS_RUNNING_HASH = 2240911060L
        @JvmField
        val isRunningBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "is_running", IS_RUNNING_HASH)

        private const val GET_PLAY_SPACE_HASH = 2455072627L
        @JvmField
        val getPlaySpaceBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_play_space", GET_PLAY_SPACE_HASH)

        private const val GET_PREDICTED_DISPLAY_TIME_HASH = 2455072627L
        @JvmField
        val getPredictedDisplayTimeBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_predicted_display_time", GET_PREDICTED_DISPLAY_TIME_HASH)

        private const val GET_NEXT_FRAME_TIME_HASH = 2455072627L
        @JvmField
        val getNextFrameTimeBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_next_frame_time", GET_NEXT_FRAME_TIME_HASH)

        private const val CAN_RENDER_HASH = 2240911060L
        @JvmField
        val canRenderBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "can_render", CAN_RENDER_HASH)

        private const val FIND_ACTION_HASH = 4106179378L
        @JvmField
        val findActionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "find_action", FIND_ACTION_HASH)

        private const val ACTION_GET_HANDLE_HASH = 3917799429L
        @JvmField
        val actionGetHandleBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "action_get_handle", ACTION_GET_HANDLE_HASH)

        private const val GET_HAND_TRACKER_HASH = 3744713108L
        @JvmField
        val getHandTrackerBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_hand_tracker", GET_HAND_TRACKER_HASH)

        private const val REGISTER_COMPOSITION_LAYER_PROVIDER_HASH = 1477360496L
        @JvmField
        val registerCompositionLayerProviderBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "register_composition_layer_provider", REGISTER_COMPOSITION_LAYER_PROVIDER_HASH)

        private const val UNREGISTER_COMPOSITION_LAYER_PROVIDER_HASH = 1477360496L
        @JvmField
        val unregisterCompositionLayerProviderBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "unregister_composition_layer_provider", UNREGISTER_COMPOSITION_LAYER_PROVIDER_HASH)

        private const val REGISTER_PROJECTION_VIEWS_EXTENSION_HASH = 1477360496L
        @JvmField
        val registerProjectionViewsExtensionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "register_projection_views_extension", REGISTER_PROJECTION_VIEWS_EXTENSION_HASH)

        private const val UNREGISTER_PROJECTION_VIEWS_EXTENSION_HASH = 1477360496L
        @JvmField
        val unregisterProjectionViewsExtensionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "unregister_projection_views_extension", UNREGISTER_PROJECTION_VIEWS_EXTENSION_HASH)

        private const val REGISTER_FRAME_INFO_EXTENSION_HASH = 1477360496L
        @JvmField
        val registerFrameInfoExtensionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "register_frame_info_extension", REGISTER_FRAME_INFO_EXTENSION_HASH)

        private const val UNREGISTER_FRAME_INFO_EXTENSION_HASH = 1477360496L
        @JvmField
        val unregisterFrameInfoExtensionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "unregister_frame_info_extension", UNREGISTER_FRAME_INFO_EXTENSION_HASH)

        private const val REGISTER_PROJECTION_LAYER_EXTENSION_HASH = 1477360496L
        @JvmField
        val registerProjectionLayerExtensionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "register_projection_layer_extension", REGISTER_PROJECTION_LAYER_EXTENSION_HASH)

        private const val UNREGISTER_PROJECTION_LAYER_EXTENSION_HASH = 1477360496L
        @JvmField
        val unregisterProjectionLayerExtensionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "unregister_projection_layer_extension", UNREGISTER_PROJECTION_LAYER_EXTENSION_HASH)

        private const val GET_RENDER_STATE_Z_NEAR_HASH = 191475506L
        @JvmField
        val getRenderStateZNearBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_render_state_z_near", GET_RENDER_STATE_Z_NEAR_HASH)

        private const val GET_RENDER_STATE_Z_FAR_HASH = 191475506L
        @JvmField
        val getRenderStateZFarBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_render_state_z_far", GET_RENDER_STATE_Z_FAR_HASH)

        private const val SET_VELOCITY_TEXTURE_HASH = 2722037293L
        @JvmField
        val setVelocityTextureBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_velocity_texture", SET_VELOCITY_TEXTURE_HASH)

        private const val SET_VELOCITY_DEPTH_TEXTURE_HASH = 2722037293L
        @JvmField
        val setVelocityDepthTextureBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_velocity_depth_texture", SET_VELOCITY_DEPTH_TEXTURE_HASH)

        private const val SET_VELOCITY_TARGET_SIZE_HASH = 1130785943L
        @JvmField
        val setVelocityTargetSizeBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_velocity_target_size", SET_VELOCITY_TARGET_SIZE_HASH)

        private const val GET_SUPPORTED_SWAPCHAIN_FORMATS_HASH = 3851388692L
        @JvmField
        val getSupportedSwapchainFormatsBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_supported_swapchain_formats", GET_SUPPORTED_SWAPCHAIN_FORMATS_HASH)

        private const val OPENXR_SWAPCHAIN_CREATE_HASH = 2162228999L
        @JvmField
        val openxrSwapchainCreateBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_create", OPENXR_SWAPCHAIN_CREATE_HASH)

        private const val OPENXR_SWAPCHAIN_FREE_HASH = 1286410249L
        @JvmField
        val openxrSwapchainFreeBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_free", OPENXR_SWAPCHAIN_FREE_HASH)

        private const val OPENXR_SWAPCHAIN_GET_SWAPCHAIN_HASH = 3744713108L
        @JvmField
        val openxrSwapchainGetSwapchainBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_get_swapchain", OPENXR_SWAPCHAIN_GET_SWAPCHAIN_HASH)

        private const val OPENXR_SWAPCHAIN_ACQUIRE_HASH = 1286410249L
        @JvmField
        val openxrSwapchainAcquireBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_acquire", OPENXR_SWAPCHAIN_ACQUIRE_HASH)

        private const val OPENXR_SWAPCHAIN_GET_IMAGE_HASH = 937000113L
        @JvmField
        val openxrSwapchainGetImageBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_get_image", OPENXR_SWAPCHAIN_GET_IMAGE_HASH)

        private const val OPENXR_SWAPCHAIN_RELEASE_HASH = 1286410249L
        @JvmField
        val openxrSwapchainReleaseBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_release", OPENXR_SWAPCHAIN_RELEASE_HASH)

        private const val GET_PROJECTION_LAYER_HASH = 2455072627L
        @JvmField
        val getProjectionLayerBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_projection_layer", GET_PROJECTION_LAYER_HASH)

        private const val SET_RENDER_REGION_HASH = 1763793166L
        @JvmField
        val setRenderRegionBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_render_region", SET_RENDER_REGION_HASH)

        private const val SET_EMULATE_ENVIRONMENT_BLEND_MODE_ALPHA_BLEND_HASH = 2586408642L
        @JvmField
        val setEmulateEnvironmentBlendModeAlphaBlendBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_emulate_environment_blend_mode_alpha_blend", SET_EMULATE_ENVIRONMENT_BLEND_MODE_ALPHA_BLEND_HASH)

        private const val IS_ENVIRONMENT_BLEND_MODE_ALPHA_SUPPORTED_HASH = 1579290861L
        @JvmField
        val isEnvironmentBlendModeAlphaSupportedBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "is_environment_blend_mode_alpha_supported", IS_ENVIRONMENT_BLEND_MODE_ALPHA_SUPPORTED_HASH)

        private const val UPDATE_MAIN_SWAPCHAIN_SIZE_HASH = 3218959716L
        @JvmField
        val updateMainSwapchainSizeBind =
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "update_main_swapchain_size", UPDATE_MAIN_SWAPCHAIN_SIZE_HASH)
    }
}
