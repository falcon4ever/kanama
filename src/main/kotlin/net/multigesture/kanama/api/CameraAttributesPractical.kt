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

    /**
     * Enables depth of field blur for objects further than `dof_blur_far_distance`. Strength of blur
     * is controlled by `dof_blur_amount` and modulated by `dof_blur_far_transition`. Note: Depth of
     * field blur is only supported in the Forward+ and Mobile rendering methods, not Compatibility.
     * Note: Depth of field blur is not supported on viewports that have a transparent background
     * (where `Viewport.transparent_bg` is `true`).
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_far_enabled
     */
    fun setDofBlurFarEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDofBlurFarEnabledBind, handle, enabled)
    }

    /**
     * Enables depth of field blur for objects further than `dof_blur_far_distance`. Strength of blur
     * is controlled by `dof_blur_amount` and modulated by `dof_blur_far_transition`. Note: Depth of
     * field blur is only supported in the Forward+ and Mobile rendering methods, not Compatibility.
     * Note: Depth of field blur is not supported on viewports that have a transparent background
     * (where `Viewport.transparent_bg` is `true`).
     *
     * Generated from Godot docs: CameraAttributesPractical.is_dof_blur_far_enabled
     */
    fun isDofBlurFarEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDofBlurFarEnabledBind, handle)
    }

    /**
     * Objects further from the `Camera3D` by this amount will be blurred by the depth of field effect.
     * Measured in meters.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_far_distance
     */
    fun setDofBlurFarDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurFarDistanceBind, handle, distance)
    }

    /**
     * Objects further from the `Camera3D` by this amount will be blurred by the depth of field effect.
     * Measured in meters.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_dof_blur_far_distance
     */
    fun getDofBlurFarDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurFarDistanceBind, handle)
    }

    /**
     * When positive, distance over which (starting from `dof_blur_far_distance`) blur effect will
     * scale from 0 to `dof_blur_amount`. When negative, uses physically-based scaling so depth of
     * field effect will scale from 0 at `dof_blur_far_distance` and will increase in a physically
     * accurate way as objects get further from the `Camera3D`.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_far_transition
     */
    fun setDofBlurFarTransition(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurFarTransitionBind, handle, distance)
    }

    /**
     * When positive, distance over which (starting from `dof_blur_far_distance`) blur effect will
     * scale from 0 to `dof_blur_amount`. When negative, uses physically-based scaling so depth of
     * field effect will scale from 0 at `dof_blur_far_distance` and will increase in a physically
     * accurate way as objects get further from the `Camera3D`.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_dof_blur_far_transition
     */
    fun getDofBlurFarTransition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurFarTransitionBind, handle)
    }

    /**
     * Enables depth of field blur for objects closer than `dof_blur_near_distance`. Strength of blur
     * is controlled by `dof_blur_amount` and modulated by `dof_blur_near_transition`. Note: Depth of
     * field blur is only supported in the Forward+ and Mobile rendering methods, not Compatibility.
     * Note: Depth of field blur is not supported on viewports that have a transparent background
     * (where `Viewport.transparent_bg` is `true`).
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_near_enabled
     */
    fun setDofBlurNearEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDofBlurNearEnabledBind, handle, enabled)
    }

    /**
     * Enables depth of field blur for objects closer than `dof_blur_near_distance`. Strength of blur
     * is controlled by `dof_blur_amount` and modulated by `dof_blur_near_transition`. Note: Depth of
     * field blur is only supported in the Forward+ and Mobile rendering methods, not Compatibility.
     * Note: Depth of field blur is not supported on viewports that have a transparent background
     * (where `Viewport.transparent_bg` is `true`).
     *
     * Generated from Godot docs: CameraAttributesPractical.is_dof_blur_near_enabled
     */
    fun isDofBlurNearEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDofBlurNearEnabledBind, handle)
    }

    /**
     * Objects closer from the `Camera3D` by this amount will be blurred by the depth of field effect.
     * Measured in meters.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_near_distance
     */
    fun setDofBlurNearDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurNearDistanceBind, handle, distance)
    }

    /**
     * Objects closer from the `Camera3D` by this amount will be blurred by the depth of field effect.
     * Measured in meters.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_dof_blur_near_distance
     */
    fun getDofBlurNearDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurNearDistanceBind, handle)
    }

    /**
     * When positive, distance over which blur effect will scale from 0 to `dof_blur_amount`, ending at
     * `dof_blur_near_distance`. When negative, uses physically-based scaling so depth of field effect
     * will scale from 0 at `dof_blur_near_distance` and will increase in a physically accurate way as
     * objects get closer to the `Camera3D`.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_near_transition
     */
    fun setDofBlurNearTransition(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurNearTransitionBind, handle, distance)
    }

    /**
     * When positive, distance over which blur effect will scale from 0 to `dof_blur_amount`, ending at
     * `dof_blur_near_distance`. When negative, uses physically-based scaling so depth of field effect
     * will scale from 0 at `dof_blur_near_distance` and will increase in a physically accurate way as
     * objects get closer to the `Camera3D`.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_dof_blur_near_transition
     */
    fun getDofBlurNearTransition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurNearTransitionBind, handle)
    }

    /**
     * Sets the maximum amount of blur. When using physically-based blur amounts, will instead act as a
     * multiplier. High values lead to an increased amount of blurriness, but can be much more
     * expensive to calculate. It is best to keep this as low as possible for a given art style.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_amount
     */
    fun setDofBlurAmount(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDofBlurAmountBind, handle, amount)
    }

    /**
     * Sets the maximum amount of blur. When using physically-based blur amounts, will instead act as a
     * multiplier. High values lead to an increased amount of blurriness, but can be much more
     * expensive to calculate. It is best to keep this as low as possible for a given art style.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_dof_blur_amount
     */
    fun getDofBlurAmount(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDofBlurAmountBind, handle)
    }

    /**
     * The maximum sensitivity (in ISO) used when calculating auto exposure. When calculating scene
     * average luminance, color values will be clamped to at least this value. This limits the
     * auto-exposure from exposing below a certain brightness, resulting in a cut off point where the
     * scene will remain bright.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_auto_exposure_max_sensitivity
     */
    fun setAutoExposureMaxSensitivity(maxSensitivity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureMaxSensitivityBind, handle, maxSensitivity)
    }

    /**
     * The maximum sensitivity (in ISO) used when calculating auto exposure. When calculating scene
     * average luminance, color values will be clamped to at least this value. This limits the
     * auto-exposure from exposing below a certain brightness, resulting in a cut off point where the
     * scene will remain bright.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_auto_exposure_max_sensitivity
     */
    fun getAutoExposureMaxSensitivity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoExposureMaxSensitivityBind, handle)
    }

    /**
     * The minimum sensitivity (in ISO) used when calculating auto exposure. When calculating scene
     * average luminance, color values will be clamped to at least this value. This limits the
     * auto-exposure from exposing above a certain brightness, resulting in a cut off point where the
     * scene will remain dark.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_auto_exposure_min_sensitivity
     */
    fun setAutoExposureMinSensitivity(minSensitivity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureMinSensitivityBind, handle, minSensitivity)
    }

    /**
     * The minimum sensitivity (in ISO) used when calculating auto exposure. When calculating scene
     * average luminance, color values will be clamped to at least this value. This limits the
     * auto-exposure from exposing above a certain brightness, resulting in a cut off point where the
     * scene will remain dark.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_auto_exposure_min_sensitivity
     */
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
