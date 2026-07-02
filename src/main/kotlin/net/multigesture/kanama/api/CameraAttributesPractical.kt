package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Camera settings in an easy to use format.
 *
 * Generated from Godot docs: CameraAttributesPractical
 */
class CameraAttributesPractical(handle: MemorySegment) : CameraAttributes(handle) {
    var dofBlurFarEnabled: Boolean
        @JvmName("dofBlurFarEnabledProperty")
        get() = isDofBlurFarEnabled()
        @JvmName("setDofBlurFarEnabledProperty")
        set(value) = setDofBlurFarEnabled(value)

    var dofBlurFarDistance: Double
        @JvmName("dofBlurFarDistanceProperty")
        get() = getDofBlurFarDistance()
        @JvmName("setDofBlurFarDistanceProperty")
        set(value) = setDofBlurFarDistance(value)

    var dofBlurFarTransition: Double
        @JvmName("dofBlurFarTransitionProperty")
        get() = getDofBlurFarTransition()
        @JvmName("setDofBlurFarTransitionProperty")
        set(value) = setDofBlurFarTransition(value)

    var dofBlurNearEnabled: Boolean
        @JvmName("dofBlurNearEnabledProperty")
        get() = isDofBlurNearEnabled()
        @JvmName("setDofBlurNearEnabledProperty")
        set(value) = setDofBlurNearEnabled(value)

    var dofBlurNearDistance: Double
        @JvmName("dofBlurNearDistanceProperty")
        get() = getDofBlurNearDistance()
        @JvmName("setDofBlurNearDistanceProperty")
        set(value) = setDofBlurNearDistance(value)

    var dofBlurNearTransition: Double
        @JvmName("dofBlurNearTransitionProperty")
        get() = getDofBlurNearTransition()
        @JvmName("setDofBlurNearTransitionProperty")
        set(value) = setDofBlurNearTransition(value)

    var dofBlurAmount: Double
        @JvmName("dofBlurAmountProperty")
        get() = getDofBlurAmount()
        @JvmName("setDofBlurAmountProperty")
        set(value) = setDofBlurAmount(value)

    var autoExposureMinSensitivity: Double
        @JvmName("autoExposureMinSensitivityProperty")
        get() = getAutoExposureMinSensitivity()
        @JvmName("setAutoExposureMinSensitivityProperty")
        set(value) = setAutoExposureMinSensitivity(value)

    var autoExposureMaxSensitivity: Double
        @JvmName("autoExposureMaxSensitivityProperty")
        get() = getAutoExposureMaxSensitivity()
        @JvmName("setAutoExposureMaxSensitivityProperty")
        set(value) = setAutoExposureMaxSensitivity(value)

    fun setDofBlurFarEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDofBlurFarEnabledBind, handle, enabled)
    }

    fun isDofBlurFarEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDofBlurFarEnabledBind, handle)
    }

    fun setDofBlurFarDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurFarDistanceBind, handle, distance)
    }

    fun getDofBlurFarDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurFarDistanceBind, handle)
    }

    fun setDofBlurFarTransition(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurFarTransitionBind, handle, distance)
    }

    fun getDofBlurFarTransition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurFarTransitionBind, handle)
    }

    fun setDofBlurNearEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDofBlurNearEnabledBind, handle, enabled)
    }

    fun isDofBlurNearEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDofBlurNearEnabledBind, handle)
    }

    fun setDofBlurNearDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurNearDistanceBind, handle, distance)
    }

    fun getDofBlurNearDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurNearDistanceBind, handle)
    }

    fun setDofBlurNearTransition(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurNearTransitionBind, handle, distance)
    }

    fun getDofBlurNearTransition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurNearTransitionBind, handle)
    }

    fun setDofBlurAmount(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurAmountBind, handle, amount)
    }

    fun getDofBlurAmount(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurAmountBind, handle)
    }

    fun setAutoExposureMaxSensitivity(maxSensitivity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureMaxSensitivityBind, handle, maxSensitivity)
    }

    fun getAutoExposureMaxSensitivity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoExposureMaxSensitivityBind, handle)
    }

    fun setAutoExposureMinSensitivity(minSensitivity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureMinSensitivityBind, handle, minSensitivity)
    }

    fun getAutoExposureMinSensitivity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoExposureMinSensitivityBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CameraAttributesPractical? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CameraAttributesPractical? =
            if (handle.address() == 0L) null else CameraAttributesPractical(handle)

        private const val SET_DOF_BLUR_FAR_ENABLED_HASH = 2586408642L
        private val setDofBlurFarEnabledBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_far_enabled", SET_DOF_BLUR_FAR_ENABLED_HASH)
        }

        private const val IS_DOF_BLUR_FAR_ENABLED_HASH = 36873697L
        private val isDofBlurFarEnabledBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "is_dof_blur_far_enabled", IS_DOF_BLUR_FAR_ENABLED_HASH)
        }

        private const val SET_DOF_BLUR_FAR_DISTANCE_HASH = 373806689L
        private val setDofBlurFarDistanceBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_far_distance", SET_DOF_BLUR_FAR_DISTANCE_HASH)
        }

        private const val GET_DOF_BLUR_FAR_DISTANCE_HASH = 1740695150L
        private val getDofBlurFarDistanceBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_far_distance", GET_DOF_BLUR_FAR_DISTANCE_HASH)
        }

        private const val SET_DOF_BLUR_FAR_TRANSITION_HASH = 373806689L
        private val setDofBlurFarTransitionBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_far_transition", SET_DOF_BLUR_FAR_TRANSITION_HASH)
        }

        private const val GET_DOF_BLUR_FAR_TRANSITION_HASH = 1740695150L
        private val getDofBlurFarTransitionBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_far_transition", GET_DOF_BLUR_FAR_TRANSITION_HASH)
        }

        private const val SET_DOF_BLUR_NEAR_ENABLED_HASH = 2586408642L
        private val setDofBlurNearEnabledBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_near_enabled", SET_DOF_BLUR_NEAR_ENABLED_HASH)
        }

        private const val IS_DOF_BLUR_NEAR_ENABLED_HASH = 36873697L
        private val isDofBlurNearEnabledBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "is_dof_blur_near_enabled", IS_DOF_BLUR_NEAR_ENABLED_HASH)
        }

        private const val SET_DOF_BLUR_NEAR_DISTANCE_HASH = 373806689L
        private val setDofBlurNearDistanceBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_near_distance", SET_DOF_BLUR_NEAR_DISTANCE_HASH)
        }

        private const val GET_DOF_BLUR_NEAR_DISTANCE_HASH = 1740695150L
        private val getDofBlurNearDistanceBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_near_distance", GET_DOF_BLUR_NEAR_DISTANCE_HASH)
        }

        private const val SET_DOF_BLUR_NEAR_TRANSITION_HASH = 373806689L
        private val setDofBlurNearTransitionBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_near_transition", SET_DOF_BLUR_NEAR_TRANSITION_HASH)
        }

        private const val GET_DOF_BLUR_NEAR_TRANSITION_HASH = 1740695150L
        private val getDofBlurNearTransitionBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_near_transition", GET_DOF_BLUR_NEAR_TRANSITION_HASH)
        }

        private const val SET_DOF_BLUR_AMOUNT_HASH = 373806689L
        private val setDofBlurAmountBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_amount", SET_DOF_BLUR_AMOUNT_HASH)
        }

        private const val GET_DOF_BLUR_AMOUNT_HASH = 1740695150L
        private val getDofBlurAmountBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_amount", GET_DOF_BLUR_AMOUNT_HASH)
        }

        private const val SET_AUTO_EXPOSURE_MAX_SENSITIVITY_HASH = 373806689L
        private val setAutoExposureMaxSensitivityBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_auto_exposure_max_sensitivity", SET_AUTO_EXPOSURE_MAX_SENSITIVITY_HASH)
        }

        private const val GET_AUTO_EXPOSURE_MAX_SENSITIVITY_HASH = 1740695150L
        private val getAutoExposureMaxSensitivityBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_auto_exposure_max_sensitivity", GET_AUTO_EXPOSURE_MAX_SENSITIVITY_HASH)
        }

        private const val SET_AUTO_EXPOSURE_MIN_SENSITIVITY_HASH = 373806689L
        private val setAutoExposureMinSensitivityBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_auto_exposure_min_sensitivity", SET_AUTO_EXPOSURE_MIN_SENSITIVITY_HASH)
        }

        private const val GET_AUTO_EXPOSURE_MIN_SENSITIVITY_HASH = 1740695150L
        private val getAutoExposureMinSensitivityBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_auto_exposure_min_sensitivity", GET_AUTO_EXPOSURE_MIN_SENSITIVITY_HASH)
        }
    }
}
