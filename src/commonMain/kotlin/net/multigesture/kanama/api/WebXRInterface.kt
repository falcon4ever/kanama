package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: WebXRInterface
 */
class WebXRInterface(handle: GodotHandle) : XRInterface(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.isSessionSupportedBind, segment, sessionMode)
    }

    fun setSessionMode(sessionMode: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setSessionModeBind, segment, sessionMode)
    }

    fun getSessionMode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSessionModeBind, segment)
    }

    fun setRequiredFeatures(requiredFeatures: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setRequiredFeaturesBind, segment, requiredFeatures)
    }

    fun getRequiredFeatures(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getRequiredFeaturesBind, segment)
    }

    fun setOptionalFeatures(optionalFeatures: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setOptionalFeaturesBind, segment, optionalFeatures)
    }

    fun getOptionalFeatures(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getOptionalFeaturesBind, segment)
    }

    fun getReferenceSpaceType(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getReferenceSpaceTypeBind, segment)
    }

    fun getEnabledFeatures(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getEnabledFeaturesBind, segment)
    }

    fun setRequestedReferenceSpaceTypes(requestedReferenceSpaceTypes: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setRequestedReferenceSpaceTypesBind, segment, requestedReferenceSpaceTypes)
    }

    fun getRequestedReferenceSpaceTypes(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getRequestedReferenceSpaceTypesBind, segment)
    }

    fun isInputSourceActive(inputSourceId: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isInputSourceActiveBind, segment, inputSourceId)
    }

    fun getInputSourceTracker(inputSourceId: Int): XRControllerTracker? {
        checkOpen()
        return XRControllerTracker.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getInputSourceTrackerBind, segment, inputSourceId))
    }

    fun getInputSourceTargetRayMode(inputSourceId: Int): WebXRInterface.TargetRayMode {
        checkOpen()
        return WebXRInterface.TargetRayMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getInputSourceTargetRayModeBind, segment, inputSourceId))
    }

    fun getVisibilityState(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getVisibilityStateBind, segment)
    }

    fun getDisplayRefreshRate(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDisplayRefreshRateBind, segment)
    }

    fun setDisplayRefreshRate(refreshRate: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDisplayRefreshRateBind, segment, refreshRate)
    }

    fun getAvailableDisplayRefreshRates(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getAvailableDisplayRefreshRatesBind, segment)
    }

    /** Signal `session_supported(session_mode: String, supported: bool)`; see [TypedSignal]. */
    val sessionSupported: Signal2<String, Boolean>
        @JvmName("sessionSupportedTypedSignal")
        get() = Signal2(this, "session_supported", SignalArgType.STRING, SignalArgType.BOOLEAN)

    /** Signal `session_started()`; see [TypedSignal]. */
    val sessionStarted: Signal0
        @JvmName("sessionStartedTypedSignal")
        get() = Signal0(this, "session_started")

    /** Signal `session_ended()`; see [TypedSignal]. */
    val sessionEnded: Signal0
        @JvmName("sessionEndedTypedSignal")
        get() = Signal0(this, "session_ended")

    /** Signal `session_failed(message: String)`; see [TypedSignal]. */
    val sessionFailed: Signal1<String>
        @JvmName("sessionFailedTypedSignal")
        get() = Signal1(this, "session_failed", SignalArgType.STRING)

    /** Signal `selectstart(input_source_id: int)`; see [TypedSignal]. */
    val selectstart: Signal1<Long>
        @JvmName("selectstartTypedSignal")
        get() = Signal1(this, "selectstart", SignalArgType.LONG)

    /** Signal `select(input_source_id: int)`; see [TypedSignal]. */
    val select: Signal1<Long>
        @JvmName("selectTypedSignal")
        get() = Signal1(this, "select", SignalArgType.LONG)

    /** Signal `selectend(input_source_id: int)`; see [TypedSignal]. */
    val selectend: Signal1<Long>
        @JvmName("selectendTypedSignal")
        get() = Signal1(this, "selectend", SignalArgType.LONG)

    /** Signal `squeezestart(input_source_id: int)`; see [TypedSignal]. */
    val squeezestart: Signal1<Long>
        @JvmName("squeezestartTypedSignal")
        get() = Signal1(this, "squeezestart", SignalArgType.LONG)

    /** Signal `squeeze(input_source_id: int)`; see [TypedSignal]. */
    val squeeze: Signal1<Long>
        @JvmName("squeezeTypedSignal")
        get() = Signal1(this, "squeeze", SignalArgType.LONG)

    /** Signal `squeezeend(input_source_id: int)`; see [TypedSignal]. */
    val squeezeend: Signal1<Long>
        @JvmName("squeezeendTypedSignal")
        get() = Signal1(this, "squeezeend", SignalArgType.LONG)

    /** Signal `visibility_state_changed()`; see [TypedSignal]. */
    val visibilityStateChanged: Signal0
        @JvmName("visibilityStateChangedTypedSignal")
        get() = Signal0(this, "visibility_state_changed")

    /** Signal `reference_space_reset()`; see [TypedSignal]. */
    val referenceSpaceReset: Signal0
        @JvmName("referenceSpaceResetTypedSignal")
        get() = Signal0(this, "reference_space_reset")

    /** Signal `display_refresh_rate_changed()`; see [TypedSignal]. */
    val displayRefreshRateChanged: Signal0
        @JvmName("displayRefreshRateChangedTypedSignal")
        get() = Signal0(this, "display_refresh_rate_changed")

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

    @JvmInline
    value class TargetRayMode(override val value: Long) : GodotEnumValue {
        companion object {
            val UNKNOWN: TargetRayMode get() = TargetRayMode(0L)
            val GAZE: TargetRayMode get() = TargetRayMode(1L)
            val TRACKED_POINTER: TargetRayMode get() = TargetRayMode(2L)
            val SCREEN: TargetRayMode get() = TargetRayMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebXRInterface? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WebXRInterface? =
            if (handle.address() == 0L) null else RefCounted.owned(WebXRInterface(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WebXRInterface? =
            if (handle.address() == 0L) null else WebXRInterface(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_SESSION_SUPPORTED_HASH = 83702148L
        @JvmField
        val isSessionSupportedBind =
            ObjectCalls.getMethodBind("WebXRInterface", "is_session_supported", IS_SESSION_SUPPORTED_HASH)

        private const val SET_SESSION_MODE_HASH = 83702148L
        @JvmField
        val setSessionModeBind =
            ObjectCalls.getMethodBind("WebXRInterface", "set_session_mode", SET_SESSION_MODE_HASH)

        private const val GET_SESSION_MODE_HASH = 201670096L
        @JvmField
        val getSessionModeBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_session_mode", GET_SESSION_MODE_HASH)

        private const val SET_REQUIRED_FEATURES_HASH = 83702148L
        @JvmField
        val setRequiredFeaturesBind =
            ObjectCalls.getMethodBind("WebXRInterface", "set_required_features", SET_REQUIRED_FEATURES_HASH)

        private const val GET_REQUIRED_FEATURES_HASH = 201670096L
        @JvmField
        val getRequiredFeaturesBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_required_features", GET_REQUIRED_FEATURES_HASH)

        private const val SET_OPTIONAL_FEATURES_HASH = 83702148L
        @JvmField
        val setOptionalFeaturesBind =
            ObjectCalls.getMethodBind("WebXRInterface", "set_optional_features", SET_OPTIONAL_FEATURES_HASH)

        private const val GET_OPTIONAL_FEATURES_HASH = 201670096L
        @JvmField
        val getOptionalFeaturesBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_optional_features", GET_OPTIONAL_FEATURES_HASH)

        private const val GET_REFERENCE_SPACE_TYPE_HASH = 201670096L
        @JvmField
        val getReferenceSpaceTypeBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_reference_space_type", GET_REFERENCE_SPACE_TYPE_HASH)

        private const val GET_ENABLED_FEATURES_HASH = 201670096L
        @JvmField
        val getEnabledFeaturesBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_enabled_features", GET_ENABLED_FEATURES_HASH)

        private const val SET_REQUESTED_REFERENCE_SPACE_TYPES_HASH = 83702148L
        @JvmField
        val setRequestedReferenceSpaceTypesBind =
            ObjectCalls.getMethodBind("WebXRInterface", "set_requested_reference_space_types", SET_REQUESTED_REFERENCE_SPACE_TYPES_HASH)

        private const val GET_REQUESTED_REFERENCE_SPACE_TYPES_HASH = 201670096L
        @JvmField
        val getRequestedReferenceSpaceTypesBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_requested_reference_space_types", GET_REQUESTED_REFERENCE_SPACE_TYPES_HASH)

        private const val IS_INPUT_SOURCE_ACTIVE_HASH = 1116898809L
        @JvmField
        val isInputSourceActiveBind =
            ObjectCalls.getMethodBind("WebXRInterface", "is_input_source_active", IS_INPUT_SOURCE_ACTIVE_HASH)

        private const val GET_INPUT_SOURCE_TRACKER_HASH = 399776966L
        @JvmField
        val getInputSourceTrackerBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_input_source_tracker", GET_INPUT_SOURCE_TRACKER_HASH)

        private const val GET_INPUT_SOURCE_TARGET_RAY_MODE_HASH = 2852387453L
        @JvmField
        val getInputSourceTargetRayModeBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_input_source_target_ray_mode", GET_INPUT_SOURCE_TARGET_RAY_MODE_HASH)

        private const val GET_VISIBILITY_STATE_HASH = 201670096L
        @JvmField
        val getVisibilityStateBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_visibility_state", GET_VISIBILITY_STATE_HASH)

        private const val GET_DISPLAY_REFRESH_RATE_HASH = 1740695150L
        @JvmField
        val getDisplayRefreshRateBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_display_refresh_rate", GET_DISPLAY_REFRESH_RATE_HASH)

        private const val SET_DISPLAY_REFRESH_RATE_HASH = 373806689L
        @JvmField
        val setDisplayRefreshRateBind =
            ObjectCalls.getMethodBind("WebXRInterface", "set_display_refresh_rate", SET_DISPLAY_REFRESH_RATE_HASH)

        private const val GET_AVAILABLE_DISPLAY_REFRESH_RATES_HASH = 3995934104L
        @JvmField
        val getAvailableDisplayRefreshRatesBind =
            ObjectCalls.getMethodBind("WebXRInterface", "get_available_display_refresh_rates", GET_AVAILABLE_DISPLAY_REFRESH_RATES_HASH)
    }
}
