package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: WebXRInterface
 */
class WebXRInterface(handle: MemorySegment) : XRInterface(handle) {
    var sessionMode: String
        @JvmName("sessionModeProperty")
        get() = getSessionMode()
        @JvmName("setSessionModeProperty")
        set(value) = setSessionMode(value)

    var requiredFeatures: String
        @JvmName("requiredFeaturesProperty")
        get() = getRequiredFeatures()
        @JvmName("setRequiredFeaturesProperty")
        set(value) = setRequiredFeatures(value)

    var optionalFeatures: String
        @JvmName("optionalFeaturesProperty")
        get() = getOptionalFeatures()
        @JvmName("setOptionalFeaturesProperty")
        set(value) = setOptionalFeatures(value)

    var requestedReferenceSpaceTypes: String
        @JvmName("requestedReferenceSpaceTypesProperty")
        get() = getRequestedReferenceSpaceTypes()
        @JvmName("setRequestedReferenceSpaceTypesProperty")
        set(value) = setRequestedReferenceSpaceTypes(value)

    val referenceSpaceType: String
        @JvmName("referenceSpaceTypeProperty")
        get() = getReferenceSpaceType()

    val enabledFeatures: String
        @JvmName("enabledFeaturesProperty")
        get() = getEnabledFeatures()

    val visibilityState: String
        @JvmName("visibilityStateProperty")
        get() = getVisibilityState()

    fun isSessionSupported(sessionMode: String) {
        ObjectCalls.ptrcallWithStringArg(isSessionSupportedBind, handle, sessionMode)
    }

    fun setSessionMode(sessionMode: String) {
        ObjectCalls.ptrcallWithStringArg(setSessionModeBind, handle, sessionMode)
    }

    fun getSessionMode(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSessionModeBind, handle)
    }

    fun setRequiredFeatures(requiredFeatures: String) {
        ObjectCalls.ptrcallWithStringArg(setRequiredFeaturesBind, handle, requiredFeatures)
    }

    fun getRequiredFeatures(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getRequiredFeaturesBind, handle)
    }

    fun setOptionalFeatures(optionalFeatures: String) {
        ObjectCalls.ptrcallWithStringArg(setOptionalFeaturesBind, handle, optionalFeatures)
    }

    fun getOptionalFeatures(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOptionalFeaturesBind, handle)
    }

    fun getReferenceSpaceType(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getReferenceSpaceTypeBind, handle)
    }

    fun getEnabledFeatures(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEnabledFeaturesBind, handle)
    }

    fun setRequestedReferenceSpaceTypes(requestedReferenceSpaceTypes: String) {
        ObjectCalls.ptrcallWithStringArg(setRequestedReferenceSpaceTypesBind, handle, requestedReferenceSpaceTypes)
    }

    fun getRequestedReferenceSpaceTypes(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getRequestedReferenceSpaceTypesBind, handle)
    }

    fun isInputSourceActive(inputSourceId: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isInputSourceActiveBind, handle, inputSourceId)
    }

    fun getInputSourceTracker(inputSourceId: Int): XRControllerTracker? {
        return XRControllerTracker.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getInputSourceTrackerBind, handle, inputSourceId))
    }

    fun getInputSourceTargetRayMode(inputSourceId: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getInputSourceTargetRayModeBind, handle, inputSourceId)
    }

    fun getVisibilityState(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVisibilityStateBind, handle)
    }

    fun getDisplayRefreshRate(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDisplayRefreshRateBind, handle)
    }

    fun setDisplayRefreshRate(refreshRate: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDisplayRefreshRateBind, handle, refreshRate)
    }

    fun getAvailableDisplayRefreshRates(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getAvailableDisplayRefreshRatesBind, handle)
    }

    object Signals {
        const val sessionSupported: String = "session_supported"
        const val sessionStarted: String = "session_started"
        const val sessionEnded: String = "session_ended"
        const val sessionFailed: String = "session_failed"
        const val selectstart: String = "selectstart"
        const val select: String = "select"
        const val selectend: String = "selectend"
        const val squeezestart: String = "squeezestart"
        const val squeeze: String = "squeeze"
        const val squeezeend: String = "squeezeend"
        const val visibilityStateChanged: String = "visibility_state_changed"
        const val referenceSpaceReset: String = "reference_space_reset"
        const val displayRefreshRateChanged: String = "display_refresh_rate_changed"
    }

    companion object {
        const val TARGET_RAY_MODE_UNKNOWN: Long = 0L
        const val TARGET_RAY_MODE_GAZE: Long = 1L
        const val TARGET_RAY_MODE_TRACKED_POINTER: Long = 2L
        const val TARGET_RAY_MODE_SCREEN: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): WebXRInterface? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WebXRInterface? =
            if (handle.address() == 0L) null else WebXRInterface(handle)

        private const val IS_SESSION_SUPPORTED_HASH = 83702148L
        private val isSessionSupportedBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "is_session_supported", IS_SESSION_SUPPORTED_HASH)
        }

        private const val SET_SESSION_MODE_HASH = 83702148L
        private val setSessionModeBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "set_session_mode", SET_SESSION_MODE_HASH)
        }

        private const val GET_SESSION_MODE_HASH = 201670096L
        private val getSessionModeBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_session_mode", GET_SESSION_MODE_HASH)
        }

        private const val SET_REQUIRED_FEATURES_HASH = 83702148L
        private val setRequiredFeaturesBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "set_required_features", SET_REQUIRED_FEATURES_HASH)
        }

        private const val GET_REQUIRED_FEATURES_HASH = 201670096L
        private val getRequiredFeaturesBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_required_features", GET_REQUIRED_FEATURES_HASH)
        }

        private const val SET_OPTIONAL_FEATURES_HASH = 83702148L
        private val setOptionalFeaturesBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "set_optional_features", SET_OPTIONAL_FEATURES_HASH)
        }

        private const val GET_OPTIONAL_FEATURES_HASH = 201670096L
        private val getOptionalFeaturesBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_optional_features", GET_OPTIONAL_FEATURES_HASH)
        }

        private const val GET_REFERENCE_SPACE_TYPE_HASH = 201670096L
        private val getReferenceSpaceTypeBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_reference_space_type", GET_REFERENCE_SPACE_TYPE_HASH)
        }

        private const val GET_ENABLED_FEATURES_HASH = 201670096L
        private val getEnabledFeaturesBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_enabled_features", GET_ENABLED_FEATURES_HASH)
        }

        private const val SET_REQUESTED_REFERENCE_SPACE_TYPES_HASH = 83702148L
        private val setRequestedReferenceSpaceTypesBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "set_requested_reference_space_types", SET_REQUESTED_REFERENCE_SPACE_TYPES_HASH)
        }

        private const val GET_REQUESTED_REFERENCE_SPACE_TYPES_HASH = 201670096L
        private val getRequestedReferenceSpaceTypesBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_requested_reference_space_types", GET_REQUESTED_REFERENCE_SPACE_TYPES_HASH)
        }

        private const val IS_INPUT_SOURCE_ACTIVE_HASH = 1116898809L
        private val isInputSourceActiveBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "is_input_source_active", IS_INPUT_SOURCE_ACTIVE_HASH)
        }

        private const val GET_INPUT_SOURCE_TRACKER_HASH = 399776966L
        private val getInputSourceTrackerBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_input_source_tracker", GET_INPUT_SOURCE_TRACKER_HASH)
        }

        private const val GET_INPUT_SOURCE_TARGET_RAY_MODE_HASH = 2852387453L
        private val getInputSourceTargetRayModeBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_input_source_target_ray_mode", GET_INPUT_SOURCE_TARGET_RAY_MODE_HASH)
        }

        private const val GET_VISIBILITY_STATE_HASH = 201670096L
        private val getVisibilityStateBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_visibility_state", GET_VISIBILITY_STATE_HASH)
        }

        private const val GET_DISPLAY_REFRESH_RATE_HASH = 1740695150L
        private val getDisplayRefreshRateBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_display_refresh_rate", GET_DISPLAY_REFRESH_RATE_HASH)
        }

        private const val SET_DISPLAY_REFRESH_RATE_HASH = 373806689L
        private val setDisplayRefreshRateBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "set_display_refresh_rate", SET_DISPLAY_REFRESH_RATE_HASH)
        }

        private const val GET_AVAILABLE_DISPLAY_REFRESH_RATES_HASH = 3995934104L
        private val getAvailableDisplayRefreshRatesBind by lazy {
            ObjectCalls.getMethodBind("WebXRInterface", "get_available_display_refresh_rates", GET_AVAILABLE_DISPLAY_REFRESH_RATES_HASH)
        }
    }
}
