package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: OpenXRAPIExtension
 */
class OpenXRAPIExtension(handle: MemorySegment) : RefCounted(handle) {
    fun getOpenxrVersion(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOpenxrVersionBind, handle)
    }

    fun getInstance(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getInstanceBind, handle)
    }

    fun getSystemId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSystemIdBind, handle)
    }

    fun getSession(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSessionBind, handle)
    }

    fun transformFromPose(pose: MemorySegment): Transform3D {
        return ObjectCalls.ptrcallWithConstVoidPtrArgRetTransform3D(transformFromPoseBind, handle, pose)
    }

    fun xrResult(result: Long, format: String, args: List<Any?>): Boolean {
        return ObjectCalls.ptrcallWithLongStringArrayArgsRetBool(xrResultBind, handle, result, format, args)
    }

    fun getInstanceProcAddr(name: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(getInstanceProcAddrBind, handle, name)
    }

    fun getErrorString(result: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getErrorStringBind, handle, result)
    }

    fun getSwapchainFormatName(swapchainFormat: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getSwapchainFormatNameBind, handle, swapchainFormat)
    }

    fun setObjectName(objectType: Long, objectHandle: Long, objectName: String) {
        ObjectCalls.ptrcallWithTwoLongStringArgs(setObjectNameBind, handle, objectType, objectHandle, objectName)
    }

    fun beginDebugLabelRegion(labelName: String) {
        ObjectCalls.ptrcallWithStringArg(beginDebugLabelRegionBind, handle, labelName)
    }

    fun endDebugLabelRegion() {
        ObjectCalls.ptrcallNoArgs(endDebugLabelRegionBind, handle)
    }

    fun insertDebugLabel(labelName: String) {
        ObjectCalls.ptrcallWithStringArg(insertDebugLabelBind, handle, labelName)
    }

    fun getViewCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getViewCountBind, handle)
    }

    fun getViewConfiguration(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getViewConfigurationBind, handle)
    }

    fun isInitialized(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInitializedBind, handle)
    }

    fun isRunning(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRunningBind, handle)
    }

    fun setCustomPlaySpace(space: MemorySegment) {
        ObjectCalls.ptrcallWithConstVoidPtrArg(setCustomPlaySpaceBind, handle, space)
    }

    fun getPlaySpace(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPlaySpaceBind, handle)
    }

    fun getPredictedDisplayTime(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPredictedDisplayTimeBind, handle)
    }

    fun getNextFrameTime(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNextFrameTimeBind, handle)
    }

    fun canRender(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(canRenderBind, handle)
    }

    fun findAction(name: String, actionSet: RID): RID {
        return ObjectCalls.ptrcallWithStringAndRIDArgRetRID(findActionBind, handle, name, actionSet)
    }

    fun actionGetHandle(action: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(actionGetHandleBind, handle, action)
    }

    fun getHandTracker(handIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getHandTrackerBind, handle, handIndex)
    }

    fun registerCompositionLayerProvider(extension: OpenXRExtensionWrapper) {
        ObjectCalls.ptrcallWithObjectArgs(registerCompositionLayerProviderBind, handle, listOf(extension.handle))
    }

    fun unregisterCompositionLayerProvider(extension: OpenXRExtensionWrapper) {
        ObjectCalls.ptrcallWithObjectArgs(unregisterCompositionLayerProviderBind, handle, listOf(extension.handle))
    }

    fun registerProjectionViewsExtension(extension: OpenXRExtensionWrapper) {
        ObjectCalls.ptrcallWithObjectArgs(registerProjectionViewsExtensionBind, handle, listOf(extension.handle))
    }

    fun unregisterProjectionViewsExtension(extension: OpenXRExtensionWrapper) {
        ObjectCalls.ptrcallWithObjectArgs(unregisterProjectionViewsExtensionBind, handle, listOf(extension.handle))
    }

    fun registerFrameInfoExtension(extension: OpenXRExtensionWrapper) {
        ObjectCalls.ptrcallWithObjectArgs(registerFrameInfoExtensionBind, handle, listOf(extension.handle))
    }

    fun unregisterFrameInfoExtension(extension: OpenXRExtensionWrapper) {
        ObjectCalls.ptrcallWithObjectArgs(unregisterFrameInfoExtensionBind, handle, listOf(extension.handle))
    }

    fun registerProjectionLayerExtension(extension: OpenXRExtensionWrapper) {
        ObjectCalls.ptrcallWithObjectArgs(registerProjectionLayerExtensionBind, handle, listOf(extension.handle))
    }

    fun unregisterProjectionLayerExtension(extension: OpenXRExtensionWrapper) {
        ObjectCalls.ptrcallWithObjectArgs(unregisterProjectionLayerExtensionBind, handle, listOf(extension.handle))
    }

    fun getRenderStateZNear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRenderStateZNearBind, handle)
    }

    fun getRenderStateZFar(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRenderStateZFarBind, handle)
    }

    fun setVelocityTexture(renderTarget: RID) {
        ObjectCalls.ptrcallWithRIDArg(setVelocityTextureBind, handle, renderTarget)
    }

    fun setVelocityDepthTexture(renderTarget: RID) {
        ObjectCalls.ptrcallWithRIDArg(setVelocityDepthTextureBind, handle, renderTarget)
    }

    fun setVelocityTargetSize(targetSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setVelocityTargetSizeBind, handle, targetSize)
    }

    fun getSupportedSwapchainFormats(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getSupportedSwapchainFormatsBind, handle)
    }

    fun openxrSwapchainCreate(createFlags: Long, usageFlags: Long, swapchainFormat: Long, width: Long, height: Long, sampleCount: Long, arraySize: Long): Long {
        return ObjectCalls.ptrcallWithThreeLongFourUInt32ArgsRetLong(openxrSwapchainCreateBind, handle, createFlags, usageFlags, swapchainFormat, width, height, sampleCount, arraySize)
    }

    fun openxrSwapchainFree(swapchain: Long) {
        ObjectCalls.ptrcallWithLongArg(openxrSwapchainFreeBind, handle, swapchain)
    }

    fun openxrSwapchainGetSwapchain(swapchain: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(openxrSwapchainGetSwapchainBind, handle, swapchain)
    }

    fun openxrSwapchainAcquire(swapchain: Long) {
        ObjectCalls.ptrcallWithLongArg(openxrSwapchainAcquireBind, handle, swapchain)
    }

    fun openxrSwapchainGetImage(swapchain: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(openxrSwapchainGetImageBind, handle, swapchain)
    }

    fun openxrSwapchainRelease(swapchain: Long) {
        ObjectCalls.ptrcallWithLongArg(openxrSwapchainReleaseBind, handle, swapchain)
    }

    fun getProjectionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProjectionLayerBind, handle)
    }

    fun setRenderRegion(renderRegion: Rect2i) {
        ObjectCalls.ptrcallWithRect2iArg(setRenderRegionBind, handle, renderRegion)
    }

    fun setEmulateEnvironmentBlendModeAlphaBlend(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmulateEnvironmentBlendModeAlphaBlendBind, handle, enabled)
    }

    fun isEnvironmentBlendModeAlphaSupported(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(isEnvironmentBlendModeAlphaSupportedBind, handle)
    }

    fun updateMainSwapchainSize() {
        ObjectCalls.ptrcallNoArgs(updateMainSwapchainSizeBind, handle)
    }

    companion object {
        fun openxrIsEnabled(checkRunInEditor: Boolean): Boolean {
            return ObjectCalls.ptrcallWithBoolArgRetBool(openxrIsEnabledBind, MemorySegment.NULL, checkRunInEditor)
        }

        const val OPENXR_ALPHA_BLEND_MODE_SUPPORT_NONE: Long = 0L
        const val OPENXR_ALPHA_BLEND_MODE_SUPPORT_REAL: Long = 1L
        const val OPENXR_ALPHA_BLEND_MODE_SUPPORT_EMULATING: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRAPIExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRAPIExtension? =
            if (handle.address() == 0L) null else OpenXRAPIExtension(handle)

        private const val GET_OPENXR_VERSION_HASH = 2455072627L
        private val getOpenxrVersionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_openxr_version", GET_OPENXR_VERSION_HASH)
        }

        private const val GET_INSTANCE_HASH = 2455072627L
        private val getInstanceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_instance", GET_INSTANCE_HASH)
        }

        private const val GET_SYSTEM_ID_HASH = 2455072627L
        private val getSystemIdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_system_id", GET_SYSTEM_ID_HASH)
        }

        private const val GET_SESSION_HASH = 2455072627L
        private val getSessionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_session", GET_SESSION_HASH)
        }

        private const val TRANSFORM_FROM_POSE_HASH = 2963875352L
        private val transformFromPoseBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "transform_from_pose", TRANSFORM_FROM_POSE_HASH)
        }

        private const val XR_RESULT_HASH = 3886436197L
        private val xrResultBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "xr_result", XR_RESULT_HASH)
        }

        private const val OPENXR_IS_ENABLED_HASH = 2703660260L
        private val openxrIsEnabledBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_is_enabled", OPENXR_IS_ENABLED_HASH)
        }

        private const val GET_INSTANCE_PROC_ADDR_HASH = 1597066294L
        private val getInstanceProcAddrBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_instance_proc_addr", GET_INSTANCE_PROC_ADDR_HASH)
        }

        private const val GET_ERROR_STRING_HASH = 990163283L
        private val getErrorStringBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_error_string", GET_ERROR_STRING_HASH)
        }

        private const val GET_SWAPCHAIN_FORMAT_NAME_HASH = 990163283L
        private val getSwapchainFormatNameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_swapchain_format_name", GET_SWAPCHAIN_FORMAT_NAME_HASH)
        }

        private const val SET_OBJECT_NAME_HASH = 2285447957L
        private val setObjectNameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_object_name", SET_OBJECT_NAME_HASH)
        }

        private const val BEGIN_DEBUG_LABEL_REGION_HASH = 83702148L
        private val beginDebugLabelRegionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "begin_debug_label_region", BEGIN_DEBUG_LABEL_REGION_HASH)
        }

        private const val END_DEBUG_LABEL_REGION_HASH = 3218959716L
        private val endDebugLabelRegionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "end_debug_label_region", END_DEBUG_LABEL_REGION_HASH)
        }

        private const val INSERT_DEBUG_LABEL_HASH = 83702148L
        private val insertDebugLabelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "insert_debug_label", INSERT_DEBUG_LABEL_HASH)
        }

        private const val GET_VIEW_COUNT_HASH = 3905245786L
        private val getViewCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_view_count", GET_VIEW_COUNT_HASH)
        }

        private const val GET_VIEW_CONFIGURATION_HASH = 3905245786L
        private val getViewConfigurationBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_view_configuration", GET_VIEW_CONFIGURATION_HASH)
        }

        private const val IS_INITIALIZED_HASH = 2240911060L
        private val isInitializedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "is_initialized", IS_INITIALIZED_HASH)
        }

        private const val IS_RUNNING_HASH = 2240911060L
        private val isRunningBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "is_running", IS_RUNNING_HASH)
        }

        private const val SET_CUSTOM_PLAY_SPACE_HASH = 1286410249L
        private val setCustomPlaySpaceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_custom_play_space", SET_CUSTOM_PLAY_SPACE_HASH)
        }

        private const val GET_PLAY_SPACE_HASH = 2455072627L
        private val getPlaySpaceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_play_space", GET_PLAY_SPACE_HASH)
        }

        private const val GET_PREDICTED_DISPLAY_TIME_HASH = 2455072627L
        private val getPredictedDisplayTimeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_predicted_display_time", GET_PREDICTED_DISPLAY_TIME_HASH)
        }

        private const val GET_NEXT_FRAME_TIME_HASH = 2455072627L
        private val getNextFrameTimeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_next_frame_time", GET_NEXT_FRAME_TIME_HASH)
        }

        private const val CAN_RENDER_HASH = 2240911060L
        private val canRenderBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "can_render", CAN_RENDER_HASH)
        }

        private const val FIND_ACTION_HASH = 4106179378L
        private val findActionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "find_action", FIND_ACTION_HASH)
        }

        private const val ACTION_GET_HANDLE_HASH = 3917799429L
        private val actionGetHandleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "action_get_handle", ACTION_GET_HANDLE_HASH)
        }

        private const val GET_HAND_TRACKER_HASH = 3744713108L
        private val getHandTrackerBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_hand_tracker", GET_HAND_TRACKER_HASH)
        }

        private const val REGISTER_COMPOSITION_LAYER_PROVIDER_HASH = 1477360496L
        private val registerCompositionLayerProviderBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "register_composition_layer_provider", REGISTER_COMPOSITION_LAYER_PROVIDER_HASH)
        }

        private const val UNREGISTER_COMPOSITION_LAYER_PROVIDER_HASH = 1477360496L
        private val unregisterCompositionLayerProviderBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "unregister_composition_layer_provider", UNREGISTER_COMPOSITION_LAYER_PROVIDER_HASH)
        }

        private const val REGISTER_PROJECTION_VIEWS_EXTENSION_HASH = 1477360496L
        private val registerProjectionViewsExtensionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "register_projection_views_extension", REGISTER_PROJECTION_VIEWS_EXTENSION_HASH)
        }

        private const val UNREGISTER_PROJECTION_VIEWS_EXTENSION_HASH = 1477360496L
        private val unregisterProjectionViewsExtensionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "unregister_projection_views_extension", UNREGISTER_PROJECTION_VIEWS_EXTENSION_HASH)
        }

        private const val REGISTER_FRAME_INFO_EXTENSION_HASH = 1477360496L
        private val registerFrameInfoExtensionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "register_frame_info_extension", REGISTER_FRAME_INFO_EXTENSION_HASH)
        }

        private const val UNREGISTER_FRAME_INFO_EXTENSION_HASH = 1477360496L
        private val unregisterFrameInfoExtensionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "unregister_frame_info_extension", UNREGISTER_FRAME_INFO_EXTENSION_HASH)
        }

        private const val REGISTER_PROJECTION_LAYER_EXTENSION_HASH = 1477360496L
        private val registerProjectionLayerExtensionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "register_projection_layer_extension", REGISTER_PROJECTION_LAYER_EXTENSION_HASH)
        }

        private const val UNREGISTER_PROJECTION_LAYER_EXTENSION_HASH = 1477360496L
        private val unregisterProjectionLayerExtensionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "unregister_projection_layer_extension", UNREGISTER_PROJECTION_LAYER_EXTENSION_HASH)
        }

        private const val GET_RENDER_STATE_Z_NEAR_HASH = 191475506L
        private val getRenderStateZNearBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_render_state_z_near", GET_RENDER_STATE_Z_NEAR_HASH)
        }

        private const val GET_RENDER_STATE_Z_FAR_HASH = 191475506L
        private val getRenderStateZFarBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_render_state_z_far", GET_RENDER_STATE_Z_FAR_HASH)
        }

        private const val SET_VELOCITY_TEXTURE_HASH = 2722037293L
        private val setVelocityTextureBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_velocity_texture", SET_VELOCITY_TEXTURE_HASH)
        }

        private const val SET_VELOCITY_DEPTH_TEXTURE_HASH = 2722037293L
        private val setVelocityDepthTextureBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_velocity_depth_texture", SET_VELOCITY_DEPTH_TEXTURE_HASH)
        }

        private const val SET_VELOCITY_TARGET_SIZE_HASH = 1130785943L
        private val setVelocityTargetSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_velocity_target_size", SET_VELOCITY_TARGET_SIZE_HASH)
        }

        private const val GET_SUPPORTED_SWAPCHAIN_FORMATS_HASH = 3851388692L
        private val getSupportedSwapchainFormatsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_supported_swapchain_formats", GET_SUPPORTED_SWAPCHAIN_FORMATS_HASH)
        }

        private const val OPENXR_SWAPCHAIN_CREATE_HASH = 2162228999L
        private val openxrSwapchainCreateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_create", OPENXR_SWAPCHAIN_CREATE_HASH)
        }

        private const val OPENXR_SWAPCHAIN_FREE_HASH = 1286410249L
        private val openxrSwapchainFreeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_free", OPENXR_SWAPCHAIN_FREE_HASH)
        }

        private const val OPENXR_SWAPCHAIN_GET_SWAPCHAIN_HASH = 3744713108L
        private val openxrSwapchainGetSwapchainBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_get_swapchain", OPENXR_SWAPCHAIN_GET_SWAPCHAIN_HASH)
        }

        private const val OPENXR_SWAPCHAIN_ACQUIRE_HASH = 1286410249L
        private val openxrSwapchainAcquireBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_acquire", OPENXR_SWAPCHAIN_ACQUIRE_HASH)
        }

        private const val OPENXR_SWAPCHAIN_GET_IMAGE_HASH = 937000113L
        private val openxrSwapchainGetImageBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_get_image", OPENXR_SWAPCHAIN_GET_IMAGE_HASH)
        }

        private const val OPENXR_SWAPCHAIN_RELEASE_HASH = 1286410249L
        private val openxrSwapchainReleaseBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "openxr_swapchain_release", OPENXR_SWAPCHAIN_RELEASE_HASH)
        }

        private const val GET_PROJECTION_LAYER_HASH = 2455072627L
        private val getProjectionLayerBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "get_projection_layer", GET_PROJECTION_LAYER_HASH)
        }

        private const val SET_RENDER_REGION_HASH = 1763793166L
        private val setRenderRegionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_render_region", SET_RENDER_REGION_HASH)
        }

        private const val SET_EMULATE_ENVIRONMENT_BLEND_MODE_ALPHA_BLEND_HASH = 2586408642L
        private val setEmulateEnvironmentBlendModeAlphaBlendBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_emulate_environment_blend_mode_alpha_blend", SET_EMULATE_ENVIRONMENT_BLEND_MODE_ALPHA_BLEND_HASH)
        }

        private const val IS_ENVIRONMENT_BLEND_MODE_ALPHA_SUPPORTED_HASH = 1579290861L
        private val isEnvironmentBlendModeAlphaSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "is_environment_blend_mode_alpha_supported", IS_ENVIRONMENT_BLEND_MODE_ALPHA_SUPPORTED_HASH)
        }

        private const val UPDATE_MAIN_SWAPCHAIN_SIZE_HASH = 3218959716L
        private val updateMainSwapchainSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAPIExtension", "update_main_swapchain_size", UPDATE_MAIN_SWAPCHAIN_SIZE_HASH)
        }
    }
}
