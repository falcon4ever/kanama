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
 * Generated from Godot docs: OpenXRAPIExtension
 */
class OpenXRAPIExtension(handle: GodotHandle) : RefCounted(handle) {
    fun getOpenxrVersion(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpenxrVersionBind, segment)
    }

    fun getInstance(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getInstanceBind, segment)
    }

    fun getSystemId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getSystemIdBind, segment)
    }

    fun getSession(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getSessionBind, segment)
    }

    fun xrResult(result: Long, format: String, args: List<Any?>): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongStringArrayArgsRetBool(Binds.xrResultBind, segment, result, format, args)
    }

    fun getInstanceProcAddr(name: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(Binds.getInstanceProcAddrBind, segment, name)
    }

    fun getErrorString(result: Long): String {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetString(Binds.getErrorStringBind, segment, result)
    }

    fun getSwapchainFormatName(swapchainFormat: Long): String {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetString(Binds.getSwapchainFormatNameBind, segment, swapchainFormat)
    }

    fun setObjectName(objectType: Long, objectHandle: Long, objectName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongStringArgs(Binds.setObjectNameBind, segment, objectType, objectHandle, objectName)
    }

    fun beginDebugLabelRegion(labelName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.beginDebugLabelRegionBind, segment, labelName)
    }

    fun endDebugLabelRegion() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.endDebugLabelRegionBind, segment)
    }

    fun insertDebugLabel(labelName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.insertDebugLabelBind, segment, labelName)
    }

    fun getViewCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getViewCountBind, segment)
    }

    fun getViewConfiguration(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getViewConfigurationBind, segment)
    }

    fun isInitialized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isInitializedBind, segment)
    }

    fun isRunning(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRunningBind, segment)
    }

    fun getPlaySpace(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getPlaySpaceBind, segment)
    }

    fun getPredictedDisplayTime(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getPredictedDisplayTimeBind, segment)
    }

    fun getNextFrameTime(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getNextFrameTimeBind, segment)
    }

    fun canRender(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.canRenderBind, segment)
    }

    fun findAction(name: String, actionSet: RID): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndRIDArgRetRID(Binds.findActionBind, segment, name, actionSet)
    }

    fun actionGetHandle(action: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.actionGetHandleBind, segment, action)
    }

    fun getHandTracker(handIndex: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(Binds.getHandTrackerBind, segment, handIndex)
    }

    fun registerCompositionLayerProvider(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.registerCompositionLayerProviderBind, segment, listOf(extension.segment))
    }

    fun unregisterCompositionLayerProvider(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterCompositionLayerProviderBind, segment, listOf(extension.segment))
    }

    fun registerProjectionViewsExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.registerProjectionViewsExtensionBind, segment, listOf(extension.segment))
    }

    fun unregisterProjectionViewsExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterProjectionViewsExtensionBind, segment, listOf(extension.segment))
    }

    fun registerFrameInfoExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.registerFrameInfoExtensionBind, segment, listOf(extension.segment))
    }

    fun unregisterFrameInfoExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterFrameInfoExtensionBind, segment, listOf(extension.segment))
    }

    fun registerProjectionLayerExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.registerProjectionLayerExtensionBind, segment, listOf(extension.segment))
    }

    fun unregisterProjectionLayerExtension(extension: OpenXRExtensionWrapper) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterProjectionLayerExtensionBind, segment, listOf(extension.segment))
    }

    fun getRenderStateZNear(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRenderStateZNearBind, segment)
    }

    fun getRenderStateZFar(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRenderStateZFarBind, segment)
    }

    fun setVelocityTexture(renderTarget: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setVelocityTextureBind, segment, renderTarget)
    }

    fun setVelocityDepthTexture(renderTarget: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setVelocityDepthTextureBind, segment, renderTarget)
    }

    fun setVelocityTargetSize(targetSize: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.setVelocityTargetSizeBind, segment, targetSize)
    }

    fun getSupportedSwapchainFormats(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getSupportedSwapchainFormatsBind, segment)
    }

    fun openxrSwapchainCreate(createFlags: Long, usageFlags: Long, swapchainFormat: Long, width: Long, height: Long, sampleCount: Long, arraySize: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeLongFourUInt32ArgsRetLong(Binds.openxrSwapchainCreateBind, segment, createFlags, usageFlags, swapchainFormat, width, height, sampleCount, arraySize)
    }

    fun openxrSwapchainFree(swapchain: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.openxrSwapchainFreeBind, segment, swapchain)
    }

    fun openxrSwapchainGetSwapchain(swapchain: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(Binds.openxrSwapchainGetSwapchainBind, segment, swapchain)
    }

    fun openxrSwapchainAcquire(swapchain: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.openxrSwapchainAcquireBind, segment, swapchain)
    }

    fun openxrSwapchainGetImage(swapchain: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetRID(Binds.openxrSwapchainGetImageBind, segment, swapchain)
    }

    fun openxrSwapchainRelease(swapchain: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.openxrSwapchainReleaseBind, segment, swapchain)
    }

    fun getProjectionLayer(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getProjectionLayerBind, segment)
    }

    fun setRenderRegion(renderRegion: Rect2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2iArg(Binds.setRenderRegionBind, segment, renderRegion)
    }

    fun setEmulateEnvironmentBlendModeAlphaBlend(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEmulateEnvironmentBlendModeAlphaBlendBind, segment, enabled)
    }

    fun isEnvironmentBlendModeAlphaSupported(): OpenXRAPIExtension.OpenXRAlphaBlendModeSupport {
        checkOpen()
        return OpenXRAPIExtension.OpenXRAlphaBlendModeSupport(ObjectCalls.ptrcallNoArgsRetLong(Binds.isEnvironmentBlendModeAlphaSupportedBind, segment))
    }

    fun updateMainSwapchainSize() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.updateMainSwapchainSizeBind, segment)
    }

    @JvmInline
    value class OpenXRAlphaBlendModeSupport(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: OpenXRAlphaBlendModeSupport get() = OpenXRAlphaBlendModeSupport(0L)
            val REAL: OpenXRAlphaBlendModeSupport get() = OpenXRAlphaBlendModeSupport(1L)
            val EMULATING: OpenXRAlphaBlendModeSupport get() = OpenXRAlphaBlendModeSupport(2L)
        }
    }

    companion object {
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
