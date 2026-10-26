package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Camera settings in an easy to use format.
 *
 * Generated from Godot docs: CameraAttributesPractical
 */
class CameraAttributesPractical(handle: GodotHandle) : CameraAttributes(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDofBlurFarEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isDofBlurFarEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Objects further from the `Camera3D` by this amount will be blurred by the depth of field effect.
     * Measured in meters.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_far_distance
     */
    fun setDofBlurFarDistance(distance: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDofBlurFarDistanceBind, segment, distance)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Objects further from the `Camera3D` by this amount will be blurred by the depth of field effect.
     * Measured in meters.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_dof_blur_far_distance
     */
    fun getDofBlurFarDistance(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDofBlurFarDistanceBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDofBlurFarTransitionBind, segment, distance)
        ObjectCalls.keepReachable(this)
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDofBlurFarTransitionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDofBlurNearEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isDofBlurNearEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Objects closer from the `Camera3D` by this amount will be blurred by the depth of field effect.
     * Measured in meters.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_near_distance
     */
    fun setDofBlurNearDistance(distance: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDofBlurNearDistanceBind, segment, distance)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Objects closer from the `Camera3D` by this amount will be blurred by the depth of field effect.
     * Measured in meters.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_dof_blur_near_distance
     */
    fun getDofBlurNearDistance(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDofBlurNearDistanceBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDofBlurNearTransitionBind, segment, distance)
        ObjectCalls.keepReachable(this)
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDofBlurNearTransitionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the maximum amount of blur. When using physically-based blur amounts, will instead act as a
     * multiplier. High values lead to an increased amount of blurriness, but can be much more
     * expensive to calculate. It is best to keep this as low as possible for a given art style.
     *
     * Generated from Godot docs: CameraAttributesPractical.set_dof_blur_amount
     */
    fun setDofBlurAmount(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDofBlurAmountBind, segment, amount)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the maximum amount of blur. When using physically-based blur amounts, will instead act as a
     * multiplier. High values lead to an increased amount of blurriness, but can be much more
     * expensive to calculate. It is best to keep this as low as possible for a given art style.
     *
     * Generated from Godot docs: CameraAttributesPractical.get_dof_blur_amount
     */
    fun getDofBlurAmount(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDofBlurAmountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAutoExposureMaxSensitivityBind, segment, maxSensitivity)
        ObjectCalls.keepReachable(this)
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAutoExposureMaxSensitivityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAutoExposureMinSensitivityBind, segment, minSensitivity)
        ObjectCalls.keepReachable(this)
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAutoExposureMinSensitivityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CameraAttributesPractical? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CameraAttributesPractical? =
            if (handle.address() == 0L) null else RefCounted.owned(CameraAttributesPractical(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CameraAttributesPractical? =
            if (handle.address() == 0L) null else CameraAttributesPractical(GodotHandle(handle))

        /** A new CameraAttributesPractical, GDScript's `CameraAttributesPractical.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): CameraAttributesPractical =
            RefCounted.owned(CameraAttributesPractical(GodotHandle(requireConstructed(ObjectCalls.constructObject("CameraAttributesPractical"), "CameraAttributesPractical"))))
    }

    private object Binds {
        private const val SET_DOF_BLUR_FAR_ENABLED_HASH = 2586408642L
        @JvmField
        val setDofBlurFarEnabledBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_far_enabled", SET_DOF_BLUR_FAR_ENABLED_HASH)

        private const val IS_DOF_BLUR_FAR_ENABLED_HASH = 36873697L
        @JvmField
        val isDofBlurFarEnabledBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "is_dof_blur_far_enabled", IS_DOF_BLUR_FAR_ENABLED_HASH)

        private const val SET_DOF_BLUR_FAR_DISTANCE_HASH = 373806689L
        @JvmField
        val setDofBlurFarDistanceBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_far_distance", SET_DOF_BLUR_FAR_DISTANCE_HASH)

        private const val GET_DOF_BLUR_FAR_DISTANCE_HASH = 1740695150L
        @JvmField
        val getDofBlurFarDistanceBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_far_distance", GET_DOF_BLUR_FAR_DISTANCE_HASH)

        private const val SET_DOF_BLUR_FAR_TRANSITION_HASH = 373806689L
        @JvmField
        val setDofBlurFarTransitionBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_far_transition", SET_DOF_BLUR_FAR_TRANSITION_HASH)

        private const val GET_DOF_BLUR_FAR_TRANSITION_HASH = 1740695150L
        @JvmField
        val getDofBlurFarTransitionBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_far_transition", GET_DOF_BLUR_FAR_TRANSITION_HASH)

        private const val SET_DOF_BLUR_NEAR_ENABLED_HASH = 2586408642L
        @JvmField
        val setDofBlurNearEnabledBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_near_enabled", SET_DOF_BLUR_NEAR_ENABLED_HASH)

        private const val IS_DOF_BLUR_NEAR_ENABLED_HASH = 36873697L
        @JvmField
        val isDofBlurNearEnabledBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "is_dof_blur_near_enabled", IS_DOF_BLUR_NEAR_ENABLED_HASH)

        private const val SET_DOF_BLUR_NEAR_DISTANCE_HASH = 373806689L
        @JvmField
        val setDofBlurNearDistanceBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_near_distance", SET_DOF_BLUR_NEAR_DISTANCE_HASH)

        private const val GET_DOF_BLUR_NEAR_DISTANCE_HASH = 1740695150L
        @JvmField
        val getDofBlurNearDistanceBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_near_distance", GET_DOF_BLUR_NEAR_DISTANCE_HASH)

        private const val SET_DOF_BLUR_NEAR_TRANSITION_HASH = 373806689L
        @JvmField
        val setDofBlurNearTransitionBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_near_transition", SET_DOF_BLUR_NEAR_TRANSITION_HASH)

        private const val GET_DOF_BLUR_NEAR_TRANSITION_HASH = 1740695150L
        @JvmField
        val getDofBlurNearTransitionBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_near_transition", GET_DOF_BLUR_NEAR_TRANSITION_HASH)

        private const val SET_DOF_BLUR_AMOUNT_HASH = 373806689L
        @JvmField
        val setDofBlurAmountBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_dof_blur_amount", SET_DOF_BLUR_AMOUNT_HASH)

        private const val GET_DOF_BLUR_AMOUNT_HASH = 1740695150L
        @JvmField
        val getDofBlurAmountBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_dof_blur_amount", GET_DOF_BLUR_AMOUNT_HASH)

        private const val SET_AUTO_EXPOSURE_MAX_SENSITIVITY_HASH = 373806689L
        @JvmField
        val setAutoExposureMaxSensitivityBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_auto_exposure_max_sensitivity", SET_AUTO_EXPOSURE_MAX_SENSITIVITY_HASH)

        private const val GET_AUTO_EXPOSURE_MAX_SENSITIVITY_HASH = 1740695150L
        @JvmField
        val getAutoExposureMaxSensitivityBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_auto_exposure_max_sensitivity", GET_AUTO_EXPOSURE_MAX_SENSITIVITY_HASH)

        private const val SET_AUTO_EXPOSURE_MIN_SENSITIVITY_HASH = 373806689L
        @JvmField
        val setAutoExposureMinSensitivityBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "set_auto_exposure_min_sensitivity", SET_AUTO_EXPOSURE_MIN_SENSITIVITY_HASH)

        private const val GET_AUTO_EXPOSURE_MIN_SENSITIVITY_HASH = 1740695150L
        @JvmField
        val getAutoExposureMinSensitivityBind =
            ObjectCalls.getMethodBind("CameraAttributesPractical", "get_auto_exposure_min_sensitivity", GET_AUTO_EXPOSURE_MIN_SENSITIVITY_HASH)
    }
}
